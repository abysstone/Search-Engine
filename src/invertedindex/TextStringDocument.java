package invertedindex;

import java.io.*;
import java.util.*;


public class TextStringDocument extends Document {

 
  public static final String tokenizerDelim = " \t\n\r\f\'\"\\1234567890!@#$%^&*()_+-={}|[]:;<,>.?/`~";

  
  protected StringTokenizer tokenizer = null;

 
  public TextStringDocument(String string, boolean stem) {
    super(stem);
    this.tokenizer = new StringTokenizer(string, tokenizerDelim);
    prepareNextToken();
  }

  
  protected String getNextCandidateToken() {
    if (tokenizer == null || !tokenizer.hasMoreTokens())
      return null;
    return tokenizer.nextToken();
  }

  
 
}
