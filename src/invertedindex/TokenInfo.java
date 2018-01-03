package invertedindex;

import java.util.*;



public class TokenInfo {

  public double idf;

 
  public List<TokenOccurrence> occList;

  public TokenInfo() {
    occList = new ArrayList<TokenOccurrence>();
    idf = 0.0;
  }
}
