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


import java.util.*;
import java.io.*;



import java.net.*;



public class HTMLPage {


  protected final URL link;

  protected final String text;

 
  protected List<URL> outLinks;

  



   

    HTMLPage(URL url, String webPage) {
           this.link = url;
    this.text = webPage;
    }

  public String getText() {
    return text;
  }


  public URL getLink() {
    return link;
  }


  public void setOutLinks(List<URL> links) {
    outLinks = links;
  }

  
  public List<URL> getOutLinks() {
    return outLinks;
  }


  public boolean indexAllowed() {
    return true;
  }

 
  public boolean empty() {
    if (text.equals("") ||
        Utilities.indexOfIgnoreCase(text, "<title>404 Not Found") >= 0)
      return true;
    return false;
  }


  public void write(File dir, String name) {
    try {
      PrintWriter out = new PrintWriter(new FileWriter(new File(dir, name + ".html")));

      out.println("<base href=\"" + addEndSlash(link) + "\">");
      out.print(text);
      out.close();
    }
    catch (IOException e) {
      System.err.println("HTMLPage.write(): " + e);
    }
  }



  public static URL addEndSlash(URL url) {
    String fileName = url.getPath();
    if (Utilities.fileExtension(fileName).equals(""))
      try {
        return new URL(url.toString() + "/");
      }
      catch (MalformedURLException e) {
        System.err.println("HTMLPage: " + e);
      }
    return url;
  }


}

