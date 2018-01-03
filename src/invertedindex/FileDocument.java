package invertedindex;

import java.io.*;


public abstract class FileDocument extends Document {


  public File file = null;
 
  protected BufferedReader reader = null;

  
  public FileDocument(File file, boolean stem) {
    super(stem);
    this.file = file;
    try {
      this.reader = new BufferedReader(new FileReader(file));
    }
    catch (IOException e) {
      System.out.println("\nCould not open FileDocument: " + file);
      System.exit(1);
    }
  }

}

