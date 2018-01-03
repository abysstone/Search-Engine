package invertedindex;

import java.io.*;
import java.util.*;
import javax.swing.text.html.*;
import javax.swing.text.html.parser.*;


class HTMLFileParserThread extends Thread {

  
  protected File file;
 
  protected BufferedReader reader;
  
  protected PrintWriter writer;

  
  public HTMLFileParserThread(File file, BufferedReader reader, Writer writer) {
    this.file = file;
    this.reader = reader;
    this.writer = new PrintWriter(writer);
  }

 
  public void run() {
    try {
    
      HTMLEditorKit.ParserCallback callback =
          new HTMLEditorKit.ParserCallback() {
            public void handleText(char[] data, int pos) {
              
              writer.println(data);
            }
          };
      
      new ParserDelegator().parse(reader, callback, true);
      
      reader.close();
      writer.close();
    } catch (IOException e) {
      System.out.println("\nCould not read HTMLFileDocument: " + file);
      System.exit(1);
    }
  }

}


public class HTMLFileDocument extends FileDocument {

 
  public static final String tokenizerDelim = " \t\n\r\f\'\"\\1234567890!@#$%^&*()_+-={}|[]:;<,>.?/`~";


   
  protected StringTokenizer tokenizer = null;


  protected BufferedReader textReader = null;

  
  public HTMLFileDocument(File file, boolean stem) {
    super(file, stem);  
    try {
      
      PipedWriter textWriter = new PipedWriter();
      textReader = new BufferedReader(new PipedReader(textWriter));
      HTMLFileParserThread thread = new HTMLFileParserThread(file, reader, textWriter);
     
      thread.start();

      String line = textReader.readLine();
      if (line != null) {
        this.tokenizer = new StringTokenizer(line, tokenizerDelim);
      }
      prepareNextToken();  
    }
    catch (IOException e) {
      System.out.println("\nCould not read HTMLFileDocument: " + file);
      System.exit(1);
    }
  }

  public HTMLFileDocument(String fileName, boolean stem) {
    this(new File(fileName), stem);
  }

 
  protected String getNextCandidateToken() {
    if (tokenizer == null)
      return null;
    String candidateToken = null;
    try {
      
      while (!tokenizer.hasMoreTokens()) {
    
        String line = textReader.readLine();
        if (line == null) {
          
          textReader.close();
          return null;
        } else
         
          tokenizer = new StringTokenizer(line, tokenizerDelim);
      }
      
      candidateToken = tokenizer.nextToken();
    }
    catch (IOException e) {
      System.out.println("\nCould not read from HTMLFileDocument: " + file);
      System.exit(1);
    }
    return candidateToken;
  }



}

