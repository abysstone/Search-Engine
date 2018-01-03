package invertedindex;

import java.io.*;



public class DocumentIterator {

 

 
  protected File[] files = null;
  
 
  protected int position = 0;
  

  protected boolean stem = false;

 
  DocumentIterator(File dirFile, boolean stem) {
   
   
      files = dirFile.listFiles();
    
    position = 0;
    
    this.stem = stem;
  }
  public FileDocument nextDocument() {
    if (position >= files.length)
      return null;
    FileDocument doc = null;
  
        doc = new HTMLFileDocument(files[position], stem);
      position++;
    return doc;
  }

  
  public boolean hasMoreDocuments() {
    if (files != null && position < files.length)
      return true;
    else
      return false;
  }


}

	
	
