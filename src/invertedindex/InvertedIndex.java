package invertedindex;

import java.io.*;
import java.util.*;
import java.lang.*;
import java.util.regex.*;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import searchengine.Index;


public class InvertedIndex {
//public String dirName = "fileindex";

    public static final int MAX_RETRIEVALS = 30;
    public Map<String, TokenInfo> tokenHash = null;
    public List<DocumentReference> docRefs = null;
    public File dirFile = null;
    public boolean stem = true;
    JTextArea indexStatus;
    JTable resultingTable;
    DefaultTableModel model;

    public InvertedIndex(File dirFile, boolean stem, JTextArea status) {
        indexStatus = status;
        this.dirFile = dirFile;
        this.stem = stem;

        tokenHash = new HashMap<String, TokenInfo>();
        docRefs = new ArrayList<DocumentReference>();
        indexDocuments();
    }

    protected void indexDocuments() {
        if (!tokenHash.isEmpty() || !docRefs.isEmpty()) {
            throw new IllegalStateException("Cannot indexDocuments more than once in the same InvertedIndex");
        }

        DocumentIterator docIter = new DocumentIterator(dirFile, stem);
        System.out.println("Indexing documents in " + dirFile);
        indexStatus.append("Indexing documents in " + dirFile);

        while (docIter.hasMoreDocuments()) {
            FileDocument doc = docIter.nextDocument();

            System.out.print(doc.file.getName() + ",");
            indexStatus.append("\n"+doc.file.getName() + ",");
            HashMapVector vector = doc.hashMapVector();
            indexDocument(doc, vector);
        }

        computeIDFandDocumentLengths();
        System.out.println("\nIndexed " + docRefs.size() + " documents with " + size() + " unique terms.");
        indexStatus.append("\nIndexed " + docRefs.size() + " documents with " + size() + " unique terms.");

    }

    public void processQueries(String q, JTable table) throws IOException {
        String query = q;
        resultingTable = table;
        HashMapVector queryVector = (new TextStringDocument(query, stem)).hashMapVector();
        Retrieval[] retrievals = retrieve(queryVector);
        if (retrievals.length == 0) {
            System.out.println("\nNo matching documents found.");
            indexStatus.append("\nNo matching documents found.");

        } else {
            System.out.println("\nTop " + MAX_RETRIEVALS + " matching Documents from most to least relevant:");
            indexStatus.append("\nTop " + MAX_RETRIEVALS + " matching Documents from most to least relevant:");
            printRetrievals(retrievals, 0);

        }
    }

    protected void indexDocument(FileDocument doc, HashMapVector vector) {

        DocumentReference docRef = new DocumentReference(doc);

        docRefs.add(docRef);

        for (Map.Entry<String, Weight> entry : vector.entrySet()) {

            String token = entry.getKey();

            int count = (int) entry.getValue().getValue();

            indexToken(token, count, docRef);
        }
    }

    protected void indexToken(String token, int count, DocumentReference docRef) {

        TokenInfo tokenInfo = tokenHash.get(token);
        if (tokenInfo == null) {
            tokenInfo = new TokenInfo();
            tokenHash.put(token, tokenInfo);
        }

        tokenInfo.occList.add(new TokenOccurrence(docRef, count));
    }

    protected void computeIDFandDocumentLengths() {

        double N = docRefs.size();

        Iterator<Map.Entry<String, TokenInfo>> mapEntries = tokenHash.entrySet().iterator();
        while (mapEntries.hasNext()) {

            Map.Entry<String, TokenInfo> entry = mapEntries.next();

            TokenInfo tokenInfo = entry.getValue();

            double numDocRefs = tokenInfo.occList.size();

            double idf = Math.log(N / numDocRefs);
            // System.out.println(token + " occurs in " + Math.round(numDocRefs) + " docs so IDF=" + idf);
            if (idf == 0.0) {
                mapEntries.remove();
            } else {
                tokenInfo.idf = idf;

                for (TokenOccurrence occ : tokenInfo.occList) {
                    occ.docRef.length = occ.docRef.length + Math.pow(idf * occ.count, 2);
                }
            }
        }

        for (DocumentReference docRef : docRefs) {
            docRef.length = Math.sqrt(docRef.length);
        }
    }

    public void print(JTable jprint) throws IOException {

        for (Map.Entry<String, TokenInfo> entry : tokenHash.entrySet()) {
            String token = entry.getKey();
            DefaultTableModel model2 = (DefaultTableModel) jprint.getModel();

            System.out.println(token + " (IDF=" + entry.getValue().idf + ") occurs in:");
           // indexStatus.append(token + " (IDF=" + entry.getValue().idf + ") occurs in:");
            
            for (TokenOccurrence occ : entry.getValue().occList) {
                String getLink2=linkReader(occ.docRef.file);
                model2.addRow(new Object[]{token,entry.getValue().idf,getLink2,occ.count,occ.docRef.length});
                System.out.println("   " + occ.docRef.file.getName() + " " + occ.count
                        + " times; |D|=" + occ.docRef.length);
                //indexStatus.append("   " + occ.docRef.file.getName() + " " + occ.count
                    //    + " times; |D|=" + occ.docRef.length);
            }
        }
    }

    public int size() {
        return tokenHash.size();
    }

    public void clear() {
        docRefs.clear();
        tokenHash.clear();
    }

    public Retrieval[] retrieve(Document doc) {
        return retrieve(doc.hashMapVector());
    }

    public Retrieval[] retrieve(HashMapVector vector) {

        Map<DocumentReference, DoubleValue> retrievalHash
                = new HashMap<DocumentReference, DoubleValue>();

        double queryLength = 0.0;

        for (Map.Entry<String, Weight> entry : vector.entrySet()) {
            String token = entry.getKey();
            double count = entry.getValue().getValue();

            queryLength = queryLength + incorporateToken(token, count, retrievalHash);
        }

        queryLength = Math.sqrt(queryLength);

        Retrieval[] retrievals = new Retrieval[retrievalHash.size()];

        int retrievalCount = 0;
        for (Map.Entry<DocumentReference, DoubleValue> entry : retrievalHash.entrySet()) {
            DocumentReference docRef = entry.getKey();
            double score = entry.getValue().value;
            retrievals[retrievalCount++] = getRetrieval(queryLength, docRef, score);
        }

        Arrays.sort(retrievals);
        return retrievals;
    }

    protected Retrieval getRetrieval(double queryLength, DocumentReference docRef, double score) {
        score = score / (queryLength * docRef.length);

        return new Retrieval(docRef, score);
    }

    public double incorporateToken(String token, double count,
            Map<DocumentReference, DoubleValue> retrievalHash) {
        TokenInfo tokenInfo = tokenHash.get(token);

        if (tokenInfo == null) {
            return 0.0;
        }

        double weight = tokenInfo.idf * count;

        for (TokenOccurrence occ : tokenInfo.occList) {
            DoubleValue val;
            val = retrievalHash.get(occ.docRef);
            if (val == null) {

                val = new DoubleValue(0.0);
                retrievalHash.put(occ.docRef, val);
            }

            val.value = val.value + weight * tokenInfo.idf * occ.count;
        }
        return weight * weight;
    }
    
    public String linkReader(File f) throws FileNotFoundException, IOException{
        BufferedReader br2= new BufferedReader(new FileReader(f) );
        String str;
        str=br2.readLine();
        str = str.substring(str.indexOf("\"") + 1);
str = str.substring(0, str.indexOf("\""));
        return str;
    }

    public void printRetrievals(Retrieval[] retrievals, int start) throws IOException {
        System.out.println("");
        if (start >= retrievals.length) {
            System.out.println("No more retrievals.");
            indexStatus.append("No more retrievals.");
        }
        for (int i = start; i < Math.min(retrievals.length, start + MAX_RETRIEVALS); i++) {
            model = (DefaultTableModel) resultingTable.getModel();
            String getLink=linkReader(retrievals[i].docRef.file);
           
            model.addRow(new Object[]{retrievals[i].docRef.file.getName(),retrievals[i].score,getLink});
            System.out.println(retrievals[i].docRef.file.getName()
                    + " Score: "
                    + retrievals[i].score);

            
        }
    }

}