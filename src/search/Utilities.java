/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search;

/**
 *
 * @author sharpcoder
 */
public class Utilities {

    public static int indexOfIgnoreCase(String string, String substring, int fromIndex) {
        for (int i = fromIndex; i < string.length(); i++) {
            if (startsWithIgnoreCase(string, substring, i)) {
                return i;
            }
        }
        return -1;
    }

    public static int indexOfIgnoreCase(String string, String substring) {
        return indexOfIgnoreCase(string, substring, 0);
    }

    public static boolean startsWithIgnoreCase(String string, String substring, int fromIndex) {
        if ((fromIndex < 0) || ((fromIndex + substring.length()) > string.length())) {
            return false;
        }
        for (int i = 0; i < substring.length(); i++) {
            if (Character.toUpperCase(string.charAt(fromIndex + i)) != Character.toUpperCase(substring.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean startsWithIgnoreCase(String string, String substring) {
        return startsWithIgnoreCase(string, substring, 0);
    }

    public static String fileExtension(String fileName) {
        int pos = fileName.lastIndexOf(".");
        if (pos == -1) {
            return "";
        } else {
            return fileName.substring(pos + 1);
        }
    }

    public static String padToLeft(String string, int length, char ch) {
        if (string.length() >= length) {
            return string;
        }
        StringBuffer stringBuf = new StringBuffer(length);
        for (int i = 0; i < (length - string.length()); i++) {
            stringBuf.append(ch);
        }
        stringBuf.append(string);
        return stringBuf.toString();
    }

  
    public static String padToLeft(String string, int length) {
        return padToLeft(string, length, ' ');
    }

    
    public static String padToLeft(double x, int length) {
        return padToLeft(Double.toString(x), length);
    }

  
    public static String padToLeft(int x, int length) {
        return padToLeft(Integer.toString(x), length);
    }

    public static String padWithZeros(int x, int length) {
        return padToLeft(Integer.toString(x), length, '0');
    }

    public static String padWithZeros(double x, int length) {
        return padToLeft(Double.toString(x), length, '0');
    }
    
    
     public static double log(double x, double base) {
    return Math.log(x) / Math.log(base);
  }

  public static double log(int x, int base) {
    return log((double) x, (double) base);
  }

  public static double log(double x, int base) {
    return log(x, (double) base);
  }

  public static double log(int x, double base) {
    return log((double) x, base);
  }

}
