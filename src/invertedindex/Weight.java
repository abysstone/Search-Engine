package invertedindex;

public class Weight {
 
  protected double value = 0;

 
  public double increment() {
    return ++value;
  }

 
  public double increment(int n) {
    value = value + n;
    return value;
  }

 
  public double increment(double n) {
    value = value + n;
    return value;
  }

 
  public double decrement() {
    return --value;
  }

  
  public double decrement(int n) {
    value = value - n;
    return value;
  }


  public double decrement(double n) {
    value = value - n;
    return value;
  }

 
  public double getValue() {
    return value;
  }

  
  public double setValue(int value) {
    this.value = value;
    return value;
  }

  
  public double setValue(double value) {
    this.value = value;
    return value;
  }
}
