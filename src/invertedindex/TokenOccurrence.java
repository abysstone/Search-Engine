package invertedindex;


public class TokenOccurrence {
  
  public DocumentReference docRef = null;
 
  public int count = 0;

  
  public TokenOccurrence(DocumentReference docRef, int count) {
    this.docRef = docRef;
    this.count = count;
  }


}
