
package searchengine;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import search.*;
import invertedindex.*;
import java.io.File;
/**
 *
 * @author sharpcoder
 */
public class CrawlerGUI extends javax.swing.JFrame {
JTextArea crawlstatus;
InvertedIndex index ;
  
    public CrawlerGUI() {
        initComponents();
        
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        forLabelStartURL = new javax.swing.JLabel();
        strturl = new javax.swing.JTextField();
        startCrawler = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        crawlerStatus = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        startIndexer = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        showIndex = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        maxCount = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Search Engine");

        jLabel1.setFont(new java.awt.Font("Monotype Corsiva", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Crawler Admin");

        forLabelStartURL.setFont(new java.awt.Font("Monotype Corsiva", 0, 24)); // NOI18N
        forLabelStartURL.setText("Enter the Starting URL to start Crawler:");

        strturl.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        startCrawler.setFont(new java.awt.Font("Monotype Corsiva", 0, 18)); // NOI18N
        startCrawler.setText("Start Crawler");
        startCrawler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startCrawlerActionPerformed(evt);
            }
        });

        crawlerStatus.setColumns(20);
        crawlerStatus.setRows(5);
        crawlerStatus.setText("   ");
        jScrollPane1.setViewportView(crawlerStatus);

        jLabel2.setText("Crawler Status:");

        startIndexer.setFont(new java.awt.Font("Monotype Corsiva", 0, 18)); // NOI18N
        startIndexer.setText("Start Indexer");
        startIndexer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startIndexerActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Monotype Corsiva", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Information Retrieval and Search Engine");

        showIndex.setFont(new java.awt.Font("Monotype Corsiva", 0, 18)); // NOI18N
        showIndex.setText("Show Index");
        showIndex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showIndexActionPerformed(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("Developed by : Manas Bhatt");

        jLabel5.setFont(new java.awt.Font("Monotype Corsiva", 0, 24)); // NOI18N
        jLabel5.setText("Enter maximum links to crawl:");

        maxCount.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addComponent(startCrawler, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(startIndexer, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(showIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane1)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(forLabelStartURL, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel5)
                                .addGap(37, 37, 37)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(strturl, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(maxCount, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(forLabelStartURL, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(strturl, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(maxCount, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(jLabel5)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startCrawler, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startIndexer, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(showIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
  
    
    private void startCrawlerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startCrawlerActionPerformed
        try {
            // TODO add your handling code here:
            String startURL=strturl.getText().trim();
            int maxcount2= Integer.parseInt(maxCount.getText());
            new Crawler().crawl(startURL,crawlerStatus,maxcount2);
        } catch (IOException ex) {
            Logger.getLogger(CrawlerGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_startCrawlerActionPerformed

    private void startIndexerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startIndexerActionPerformed
        String dirName="fileindex";
        boolean stem=true;
        index = new InvertedIndex(new File(dirName), stem, crawlerStatus);
        Searcher s=new Searcher();
        s.setVisible(true);
        s.setReference(index);
    }//GEN-LAST:event_startIndexerActionPerformed

    private void showIndexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showIndexActionPerformed
        Index i= new Index();
        i.setVisible(true);
       
    try {
        index.print(i.jtbl);
    } catch (IOException ex) {
        Logger.getLogger(CrawlerGUI.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_showIndexActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea crawlerStatus;
    private javax.swing.JLabel forLabelStartURL;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField maxCount;
    private javax.swing.JButton showIndex;
    private javax.swing.JButton startCrawler;
    private javax.swing.JButton startIndexer;
    private javax.swing.JTextField strturl;
    // End of variables declaration//GEN-END:variables
}
