/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search;
import java.net.*;
/**
 *
 * @author sharpcoder
 */
public final class URLChecker {

  
  public static URL getURL(String urlString) throws MalformedURLException {

    String checkedUrl = urlString.replace(' ', '+');

    return new URL(checkedUrl);
  }

  

  private URLChecker() {
   
  }
}