package invertedindex;


public class Retrieval implements Comparable {

 
  public DocumentReference docRef;
 
  public double score;

  
  public Retrieval(DocumentReference docRef, double score) {
    this.docRef = docRef;
    this.score = score;
  }

  
  public int compareTo(Object obj) {
    Retrieval retrieval = (Retrieval) obj;
    if (score == retrieval.score)
      return 0;
    else if (score > retrieval.score)
      return -1;
    else return 1;
  }

}
