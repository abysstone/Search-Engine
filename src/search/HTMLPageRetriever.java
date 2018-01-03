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
public class HTMLPageRetriever {
    
    public HTMLPageRetriever() {
       
  }
    
   

    HTMLPage getHTMLPage(URL link) throws Exception {
        return new HTMLPage(link, WebPage.getWebPage(link));
    }

    
}
