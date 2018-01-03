package invertedindex;

import java.io.*;
import java.util.*;

public abstract class Document {
 
  protected static final String stopWordsFile = "stopwords/stopwords.txt";
 
  protected static final int numStopWords = 514;
  
  protected static HashSet<String> stopWords = null;
  
  protected static Porter stemmer = new Porter();

  
  protected String nextToken = null;
  
  protected int numTokens = 0;
  
  protected boolean stem = false;

 
  public Document(boolean stem) {
    this.stem = stem;
    if (stopWords == null)
      loadStopWords();
  }
  public boolean hasMoreTokens() {
    if (nextToken == null)
      return false;
    else
      return true;
  }
  public String nextToken() {
    String token = nextToken;
    if (token == null) return null;
    prepareNextToken();
    numTokens++;
    return token;
  }

  protected void prepareNextToken() {
   
    do {
      nextToken = getNextCandidateToken();
      if (nextToken == null) return; 
    
      nextToken = nextToken.toLowerCase();
      
      if (stopWords.contains(nextToken) || !allLetters(nextToken))
        nextToken = null;
      else if (stem) {
        nextToken = stemmer.stripAffixes(nextToken);
        if (stopWords.contains(nextToken))
          nextToken = null;
      }
    }
    while (nextToken == null);
  }

 
  protected boolean allLetters(String token) {
    for (int i = 0; i < token.length(); i++) {
      if (!Character.isLetter(token.charAt(i)))
        return false;
    }
    return true;
  }
  protected abstract String getNextCandidateToken();


  public int numberOfTokens() {
    if (nextToken == null)
      return numTokens;
    else
      return -1;
  }

 
  protected static void loadStopWords() {
   
    int HashMapSize = (int) (numStopWords / 0.75 + 10);
    stopWords = new HashSet<String>(HashMapSize);
    String line;
    try {
      
      BufferedReader in = new BufferedReader(new FileReader(stopWordsFile));
    
      while ((line = in.readLine()) != null) {
       
        stopWords.add(line);
      }
      in.close();
    }
    catch (IOException e) {
      System.out.println("\nCould not load stopwords file: " + stopWordsFile);
      System.exit(1);
    }
  }

 
  public HashMapVector hashMapVector() {
    if (numTokens != 0)
      return null;
    HashMapVector vector = new HashMapVector();
    
    while (hasMoreTokens()) {
      String token = nextToken();
      vector.increment(token);
    }
    return vector;
  }

  public void printVector() {
    hashMapVector().print();
  }


}
