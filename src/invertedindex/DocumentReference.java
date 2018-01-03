package invertedindex;

import java.io.*;


public class DocumentReference {

  public File file = null;
  
  public double length = 0.0;

  public DocumentReference(File file, double length) {
    this.file = file;
    this.length = length;
  }

  public DocumentReference(FileDocument doc) {
    this(doc.file, 0.0);
  }

  public String toString() {
    return file.getName();
  }


  public Document getDocument(short docType, boolean stem) {
    Document doc = null;
    doc = new HTMLFileDocument(file, stem);
    
    return doc;
  }



}
