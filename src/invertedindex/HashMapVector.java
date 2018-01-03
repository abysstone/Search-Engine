package invertedindex;

import java.util.*;





public class HashMapVector {
 
  public Map<String, Weight> hashMap = new HashMap<String, Weight>();

 
  public int size() {
    return hashMap.size();
  }

  
  public void clear() {
    hashMap.clear();
  }

 
  public Set<Map.Entry<String, Weight>> entrySet() {
    return hashMap.entrySet();
  }

 
  public double increment(String token, double amount) {
    Weight weight = hashMap.get(token);
    if (weight == null) {
      
      weight = new Weight();
      hashMap.put(token, weight);
    }
    
    weight.increment(amount);
    return weight.getValue();
  }

  public double getWeight(String token) {
    Weight weight = hashMap.get(token);
    if (weight == null)
      return 0.0;
    else
      return weight.getValue();
  }


  public double increment(String token) {
    return increment(token, 1.0);
  }

  
  public double increment(String token, int amount) {
    return increment(token, (double) amount);
  }

 
  public void add(HashMapVector vector) {
    for (Map.Entry<String, Weight> entry : vector.entrySet()) {
     
      String token = entry.getKey();
      
      double weight = entry.getValue().getValue();
      increment(token, weight);
    }
  }

  
  public void addScaled(HashMapVector vector, double scalingFactor) {
    for (Map.Entry<String, Weight> entry : vector.entrySet()) {
      
      String token = entry.getKey();
      
      double weight = entry.getValue().getValue();
      increment(token, scalingFactor * weight);
    }
  }


  public void subtract(HashMapVector vector) {
    for (Map.Entry<String, Weight> entry : vector.entrySet()) {
    
      String token = entry.getKey();
      
      double weight = entry.getValue().getValue();
      increment(token, -weight);
    }
  }


 
  public void multiply(double factor) {
    for (Map.Entry<String, Weight> entry : entrySet()) {
      
      Weight weight = entry.getValue();
      weight.setValue(factor * weight.getValue());
    }
  }


  
  public HashMapVector copy() {
    HashMapVector result = new HashMapVector();
    for (Map.Entry<String, Weight> entry : entrySet()) {
    
      String token = entry.getKey();
     
      double weight = entry.getValue().getValue();
      result.increment(token, weight);
    }
    return result;
  }

  
  public double maxWeight() {
    double maxWeight = Double.NEGATIVE_INFINITY;
    for (Map.Entry<String, Weight> entry : entrySet()) {
      
      double weight = entry.getValue().getValue();
      if (weight > maxWeight)
        maxWeight = weight;
    }
    return maxWeight;
  }


  
  public void print() {
    for (Map.Entry<String, Weight> entry : entrySet()) {
     
      System.out.println(entry.getKey() + ":" + entry.getValue().getValue());
    }
  }

  
  public String toString() {
    String ret = "";
    for (Map.Entry<String, Weight> entry : entrySet()) {
      
      ret += entry.getKey() + ": " + entry.getValue().getValue() + " ";
    }
    return ret;
  }


  public double cosineTo(HashMapVector otherVector) {
    return cosineTo(otherVector, otherVector.length());
  }

  public double cosineTo(HashMapVector otherVector, double length) {
   
    double sum = 0;

    double dotProd = 0;

    for (Map.Entry<String, Weight> entry : entrySet()) {
   
      String token = entry.getKey();
  
      double weight = entry.getValue().getValue();
      double otherWeight = otherVector.getWeight(token);

      dotProd += weight * otherWeight;
      sum += weight * weight;
    }
 
    return (dotProd / (Math.sqrt(sum) * length));
  }

  
  public double length() {
   
    double sum = 0;
    for (Map.Entry<String, Weight> entry : entrySet()) {
   
      double weight = entry.getValue().getValue();
      sum += weight * weight;
    }
    return Math.sqrt(sum);
  }

}





