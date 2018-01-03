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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;



public class WebPage {

 
  public static String getWebPage(URL urlString) {
    String page = null;
    try {
        String urls=urlString.toString();
      URL url = URLChecker.getURL(urls);
      page = getWPage(url);
    }
    catch (MalformedURLException e) {
      System.out.println("WebPage.getWebPage(): " + e.toString());
    }
    return page;
  }

 
  public static String getWPage(URL url) {

    
    StringBuffer page = new StringBuffer();

    try {
      URLConnection connection = url.openConnection();
      String line;
      BufferedReader in;
      if (connection.getContentEncoding() == null)
        in = new BufferedReader(new InputStreamReader(url.openStream()));
      else
        in = new BufferedReader(new InputStreamReader(url.openStream(),
            connection.getContentEncoding()));
      while ((line = in.readLine()) != null)
        page.append(line).append('\n');

      in.close();
    }
    catch (UnsupportedEncodingException e) {
      System.err.println("WebPage.getWebPage(): " + e);
    }
    catch (IOException e) {
      System.err.println("WebPage.getWebPage(): " + e);
    }

    return page.toString();
  }
}