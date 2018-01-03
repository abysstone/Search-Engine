/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search;

import java.util.*;
import java.io.*;
import java.net.*;
import javax.swing.JTextArea;

/**
 *
 * @author sharpcoder
 */
public class Crawler {

    LinkedHashSet<URL> linksToVisit = new LinkedHashSet<URL>();
    HTMLPageRetriever retriever = new HTMLPageRetriever();
    File saveDir;
    int count = 0;
    static int maxcount = 20;
    HashSet crawledList;
    URL url;
    JTextArea crawlerStatus;
    static int crawling = 1;
    
 

    public void crawl(String surl, JTextArea status,int mc) throws IOException {
        crawlerStatus = status;
        maxcount=mc;
        // if(crawling!=0)
    
        surl=surl.trim();
        url = new URL(surl);
        saveDir = new File("fileindex");
        if (!saveDir.exists()) {
            if (!saveDir.mkdir()) {
                throw new IllegalArgumentException("Failed to create directory " + saveDir.toString());
            } else {
                System.out.println("Created destination directory " + saveDir.toString());
            }
        }

        linksToVisit.add(url);

        doCrawl();

    }
    

    protected boolean linkToHTMLPage(URL link) {
        String extension = Utilities.fileExtension(link.getPath());
        if (extension.equals("") || extension.equalsIgnoreCase("html")
                || extension.equalsIgnoreCase("htm") || extension.equalsIgnoreCase("shtml") || extension.equalsIgnoreCase("php") || extension.equalsIgnoreCase("jsp")) {
            return true;
        }
        return false;
    }

    protected List<URL> getNewLinks(HTMLPage page) {
        return new LinkExtractor(page).extractLinks();
    }

    protected void indexPage(HTMLPage page) {
        page.write(saveDir,
                "P" + Utilities.padWithZeros(count, (int) Math.floor(Utilities.log(maxcount, 10)) + 1));
    }

    public void doCrawl() throws IOException {
        //HttpServletRequest req;

        if (linksToVisit.size() == 0) {
            crawlerStatus.append("\nExiting: No pages to visit or Crawler Stopped");

        }
        crawledList = new HashSet();
        while (linksToVisit.size() > 0 && count < maxcount && crawling == 1) {

            URL link = (URL) linksToVisit.iterator().next();
            // System.out.println(link);
            linksToVisit.remove(link);
            //URL link = verifyURL(url);
          crawlerStatus.append("\nTrying: " + link);
           
           System.out.println("Trying: " + link);

           if (!crawledList.add(link)) {
                crawlerStatus.append("\nAlready visited");
                System.out.println("Already visited");
                continue;
            }
            crawledList.add(link);
            if (!linkToHTMLPage(link)) {
                crawlerStatus.append("\nNot HTML/PHP/JSP Page");
                System.out.println("Not HTML/PHP/JSP Page");
                continue;
            }
            HTMLPage currentPage = null;

            try {
                currentPage = retriever.getHTMLPage(link);
            } catch (Exception e) {
                System.out.println("<p>" + e + "</p>");
                crawlerStatus.append(e.toString());
                continue;
            }
            if (currentPage.empty()) {
                crawlerStatus.append("\nNo Page Found");
                 System.out.println("No Page Found");
                continue;
            }
            if (currentPage.indexAllowed()) {
                count++;
                crawlerStatus.append("\nIndexing" + "(" + count + "): " + link);
                System.out.println("Indexing" + "(" + count + "): " + link);
                indexPage(currentPage);
            }
            if (count < maxcount) {
                List<URL> newLinks = getNewLinks(currentPage);
                //  pw.println("Adding the following links" + newLinks);
                // Add new links to end of queue
                linksToVisit.addAll(newLinks);
            }
        }
        crawlerStatus.append("\nCrawler stopped");
    }
    
  /*  public static void main(String[] args) throws IOException{
        new Crawler().crawl();
    
    }
*/
}
