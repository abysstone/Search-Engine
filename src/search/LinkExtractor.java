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
import java.net.*;
import java.io.*;
import javax.swing.text.*;
import javax.swing.text.html.*;
import javax.swing.text.html.parser.*;
import java.util.*;


public class LinkExtractor extends HTMLEditorKit.ParserCallback {

 
  protected List<URL> links;

 
  protected HTMLPage page;

 
  protected URL url;

  
  LinkExtractor(HTMLPage page) {
    this.links = new LinkedList<URL>();
    this.page = page;
    this.url = HTMLPage.addEndSlash(page.getLink());
  }

 
  public void handleText(char[] text, int position) {
  }


  public void handleStartTag(HTML.Tag tag, MutableAttributeSet attributes, int position) {

    if (tag == HTML.Tag.A) {
      addLink(attributes, HTML.Attribute.HREF);
    }
  }

 
  public void handleEndTag(HTML.Tag tag, int position) {
  }

  
  public void handleSimpleTag(HTML.Tag tag, MutableAttributeSet attributes, int position) {

    if (tag.equals(HTML.Tag.FRAME)) {
      addLink(attributes, HTML.Attribute.SRC);
    }
  }

 
  public List<URL> extractLinks() {
    HTMLParserMaker kit = new HTMLParserMaker();
    HTMLEditorKit.Parser parser = kit.getParser();
    StringReader reader = new StringReader(page.getText());
   
    try {
      parser.parse(reader, this, true);
    }
    catch (ChangedCharSetException e) {
      System.err.println("LinkExtractor.extractLinks(): " + e);
    }
    catch (IOException e) {
      System.err.println("LinkExtractor.extractLinks(): " + e);
    }
    // Set out-links for the page
    page.setOutLinks(this.links);
    return this.links;
  }


  protected void addLink(MutableAttributeSet attributes, HTML.Attribute attr) {
    if (attributes.isDefined(attr)) {
      String link = (String) attributes.getAttribute(attr);
      try {
        URL completeURL = new URL(this.url, link);
        
        if (!link.startsWith("#"))
          this.links.add(completeURL);
      }
      catch (MalformedURLException e) {
        System.err.println("LinkExtractor: " + e);
       
      }
    }
  }


}

