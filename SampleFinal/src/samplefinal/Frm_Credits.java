/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package samplefinal;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.SwingUtilities;

/**
 *
 * @author Lenovo
 */
public class Frm_Credits extends javax.swing.JFrame {

    /**
     * Creates new form Frm_Credits
     */
    
    private int mouseX, mouseY;
    private boolean isMaximized;
    
    private final String username;
    private final String role;    
    
    private java.awt.Rectangle origPanel2Bounds;
    private java.awt.Rectangle origBackBounds;
    private java.awt.Dimension origFrameSize;

    
    public Frm_Credits(String username, String role) {
        this.username = username;
        this.role = role;
        initComponents();
        jPanel1.setLayout(null);
        SwingUtilities.invokeLater(() -> {
            origPanel2Bounds = jPanel2.getBounds();
            origBackBounds = btnBack.getBounds();
            origFrameSize = getSize();
        });
        setLocationRelativeTo(null);
        
        applyResponsiveCredits();
    }
    
    public Frm_Credits() {
        this("TestUser", "Admin"); // fallback if you run Frm_Chatbot directly
    }
    
    private void applyResponsiveCredits() {
        // Fake names (edit later)
        lblGrpMem.setText("<html>"
                + "• Juan Dela Cruz<br>"
                + "• Maria Santos<br>"
                + "• Carlo Reyes<br>"
                + "• Angela Lopez<br>"
                + "• Kevin Bautista"
                + "</html>");

        lblDesc.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblGrpMem.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        // Save original layout AFTER initComponents() has laid everything out
        SwingUtilities.invokeLater(() -> {
            if (origPanel2Bounds == null) {
                origPanel2Bounds = jPanel2.getBounds();
                origBackBounds = btnBack.getBounds();
                origFrameSize = getSize();
            }

            // Add resize listener once
            addComponentListener(new java.awt.event.ComponentAdapter() {
                @Override
                public void componentResized(java.awt.event.ComponentEvent e) {

                    if (origPanel2Bounds == null || origBackBounds == null || origFrameSize == null) return;

                    int frameW = getWidth();
                    int frameH = getHeight();

                    // IMPORTANT: manual bounds mode
                    jPanel1.setLayout(null);

                    // ===== Titlebar full width =====
                    int tbH = titlebarpnl3.getHeight();
                    if (tbH <= 0) tbH = 56;
                    titlebarpnl3.setBounds(0, 0, frameW, tbH);

                    // ===== "CREDITS" title full width =====
                    int titleY = tbH + 18;
                    int titleH = jLabel1.getPreferredSize().height;
                    if (titleH <= 0) titleH = 76; // fallback
                    jLabel1.setBounds(0, titleY, frameW, titleH);

                    // ===== scale card width based on original frame size =====
                    double scaleX = frameW / (double) origFrameSize.width;
                    double scaleY = frameH / (double) origFrameSize.height;
                    double scale = Math.min(scaleX, scaleY);
                    scale = Math.max(1.0, Math.min(scale, 1.45)); // do not oversize too much

                    int cardW = (int) (origPanel2Bounds.width * scale);

                    // keep side margins
                    int sideMargin = 30;
                    cardW = Math.min(cardW, frameW - sideMargin * 2);
                    cardW = Math.max(1100, cardW); // minimum so it doesn't become tiny

                    // ✅ cardW already computed
                    int neededCardH = layoutCardContents(cardW);

                    // compute max available height
                    int topArea = titleY + titleH + 25;
                    int spaceBelow = origBackBounds.height + 60;
                    int maxCardH = frameH - topArea - spaceBelow;

                    // final card height (don’t exceed available space)
                    int cardH = Math.min(
                        Math.max(neededCardH, origPanel2Bounds.height), // never smaller than original
                        maxCardH
                    );
                    // center panel
                    int x = (frameW - cardW) / 2;
                    int availableH = frameH - topArea - spaceBelow;
                    int y = topArea + Math.max(10, (availableH - cardH) / 2);

                    jPanel2.setBounds(x, y, cardW, cardH);

                    // re-layout now that height/width applied
                    layoutCardContents(cardW);

                    // back button
                    int backW = origBackBounds.width;
                    int backH = origBackBounds.height;
                    int backX = (frameW - backW) / 2;
                    int backY = y + cardH + 25;
                    btnBack.setBounds(backX, backY, backW, backH);

                    // repaint
                    jPanel1.revalidate();
                    jPanel1.repaint();
                }
            });

            // Trigger once
            dispatchEvent(new java.awt.event.ComponentEvent(this, java.awt.event.ComponentEvent.COMPONENT_RESIZED));
        });
        
    }
    
    private int htmlHeight(javax.swing.JLabel label, int width) {
         label.setSize(new java.awt.Dimension(width, Integer.MAX_VALUE));
        return label.getPreferredSize().height;
    }
    
    private int layoutCardContents(int cardW) {
        int pad = 50;

        // fixed sizes (NOT responsive)
        int descW = 900;   // pick what looks good in NORMAL
        int descH = 180;

        int y = 20;

        // Title stays centered
        lblProject1.setBounds(pad, y, cardW - pad * 2, 45);
        y += 55;

        // ✅ fixed HTML width (not innerW)
        lblDesc.setText(
           "<html><div style='text-align:center; width:900px; line-height:1.5;'>"
                + "This project is a Java Swing–based desktop application that combines "
                + "a simple chatbot for basic user interaction,"
                + "a quiz system with recommendations, and "
                + "data visualization using bar and pie charts to monitor information."
                + "The application demonstrates user interaction and monitoring "
                + "without the use of a database, focusing on GUI design, "
                + "event handling, and basic analytics in one integrated system."
                + "It serves as a learning project that showcases how multiple "
                + "features can be combined into a single Java desktop application.<br><br>"
                + "<b>GitHub Repository:</b><br>"
                + "https://github.com/kqye/Simple-Chatbot-and-Quiz-Program-with-recommendation-and-data-visualization"
                + "</div></html>"
        );

        // ✅ center the fixed label inside the card
        int descX = (cardW - descW) / 2;
        lblDesc.setBounds(descX, y, descW, descH);

        y += descH + 30;

        lblGrp.setBounds(pad, y, cardW - pad * 2, 35);
        y += 45;

        lblGrpMem.setBounds(pad + 30, y, cardW - pad * 2, 140);
        y += 140 + 35;

        lblInst.setBounds(pad, y, 160, 35);
        lblInstName.setBounds(pad + 160, y, cardW - pad * 2 - 160, 35);

        return y + 65; // needed card height
      
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        titlebarpnl3 = new javax.swing.JPanel();
        closebtn = new javax.swing.JButton();
        minimizebtn = new javax.swing.JButton();
        maximizebtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblGrp = new javax.swing.JLabel();
        lblDesc = new javax.swing.JLabel();
        lblProject1 = new javax.swing.JLabel();
        lblInst = new javax.swing.JLabel();
        lblGrpMem = new javax.swing.JLabel();
        lblInstName = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1400, 800));

        jPanel1.setBackground(new java.awt.Color(184, 49, 67));

        titlebarpnl3.setBackground(new java.awt.Color(153, 0, 0));
        titlebarpnl3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                titlebarpnl3MouseDragged(evt);
            }
        });
        titlebarpnl3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                titlebarpnl3MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                titlebarpnl3MouseReleased(evt);
            }
        });

        closebtn.setBackground(new java.awt.Color(194, 89, 86));
        closebtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close (1).png"))); // NOI18N
        closebtn.setBorder(null);
        closebtn.setBorderPainted(false);
        closebtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                closebtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                closebtnMouseExited(evt);
            }
        });
        closebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closebtnActionPerformed(evt);
            }
        });

        minimizebtn.setBackground(new java.awt.Color(194, 89, 86));
        minimizebtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/minimize-sign.png"))); // NOI18N
        minimizebtn.setBorder(null);
        minimizebtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                minimizebtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                minimizebtnMouseExited(evt);
            }
        });
        minimizebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minimizebtnActionPerformed(evt);
            }
        });

        maximizebtn.setBackground(new java.awt.Color(194, 89, 86));
        maximizebtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/maximize.png"))); // NOI18N
        maximizebtn.setBorder(null);
        maximizebtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                maximizebtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                maximizebtnMouseExited(evt);
            }
        });
        maximizebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maximizebtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout titlebarpnl3Layout = new javax.swing.GroupLayout(titlebarpnl3);
        titlebarpnl3.setLayout(titlebarpnl3Layout);
        titlebarpnl3Layout.setHorizontalGroup(
            titlebarpnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titlebarpnl3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(minimizebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(maximizebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(closebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        titlebarpnl3Layout.setVerticalGroup(
            titlebarpnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titlebarpnl3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(titlebarpnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(minimizebtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(closebtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(maximizebtn, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.setBackground(java.awt.Color.white);

        lblGrp.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblGrp.setForeground(new java.awt.Color(204, 0, 0));
        lblGrp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblGrp.setText("Group Members: ");

        lblDesc.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        lblDesc.setForeground(new java.awt.Color(0, 0, 0));
        lblDesc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDesc.setText("desc");

        lblProject1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        lblProject1.setForeground(new java.awt.Color(204, 0, 0));
        lblProject1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProject1.setText("Simple Chatbot, Quiz, Recommendation, and Data Visualization System");

        lblInst.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblInst.setForeground(new java.awt.Color(204, 0, 0));
        lblInst.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblInst.setText("Instructor: ");

        lblGrpMem.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        lblGrpMem.setForeground(new java.awt.Color(0, 0, 0));
        lblGrpMem.setText("- ");

        lblInstName.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        lblInstName.setForeground(new java.awt.Color(0, 0, 0));
        lblInstName.setText("Mr. Norris Alexis P. Amora");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblProject1, javax.swing.GroupLayout.PREFERRED_SIZE, 1119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(124, 124, 124)
                                .addComponent(lblGrpMem, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblGrp, javax.swing.GroupLayout.PREFERRED_SIZE, 895, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(lblInst)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblInstName, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 1207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblProject1)
                .addGap(18, 18, 18)
                .addComponent(lblDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(lblGrp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblGrpMem, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInst)
                    .addComponent(lblInstName))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Broadway", 0, 55)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CREDITS");

        btnBack.setBackground(new java.awt.Color(51, 51, 51));
        btnBack.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("Back");
        btnBack.setBorder(null);
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBackMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBackMouseExited(evt);
            }
        });
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlebarpnl3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(524, 524, 524)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1092, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(titlebarpnl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closebtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closebtnMouseEntered
        // TODO add your handling code here:
        closebtn.setBackground(new Color(170, 70, 68));
    }//GEN-LAST:event_closebtnMouseEntered

    private void closebtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closebtnMouseExited
        // TODO add your handling code here:
        closebtn.setBackground(new Color(194, 89, 86));
    }//GEN-LAST:event_closebtnMouseExited

    private void closebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closebtnActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_closebtnActionPerformed

    private void minimizebtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizebtnMouseEntered
        // TODO add your handling code here:
        minimizebtn.setBackground(new Color(170, 70, 68));
    }//GEN-LAST:event_minimizebtnMouseEntered

    private void minimizebtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizebtnMouseExited
        // TODO add your handling code here:
        minimizebtn.setBackground(new Color(194, 89, 86));
    }//GEN-LAST:event_minimizebtnMouseExited

    private void minimizebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minimizebtnActionPerformed
        // TODO add your handling code here:
        setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_minimizebtnActionPerformed

    private void maximizebtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_maximizebtnMouseEntered
        // TODO add your handling code here:
        maximizebtn.setBackground(new Color(170, 70, 68));
    }//GEN-LAST:event_maximizebtnMouseEntered

    private void maximizebtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_maximizebtnMouseExited
        // TODO add your handling code here:
        maximizebtn.setBackground(new Color(194, 89, 86));
    }//GEN-LAST:event_maximizebtnMouseExited

    private void maximizebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maximizebtnActionPerformed

        // TODO add your handling code here:
        if (!isMaximized) {
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            isMaximized = true;
        } else {
            setExtendedState(JFrame.NORMAL);
            setSize(1400, 800);
            setLocationRelativeTo(null);
            isMaximized = false;
        }
        revalidate();
        repaint();
    }//GEN-LAST:event_maximizebtnActionPerformed

    private void titlebarpnl3MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_titlebarpnl3MouseDragged
        // TODO add your handling code here:
        if (isMaximized) return; // optional: disable dragging when maximized
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        setLocation(x - mouseX, y - mouseY);
    }//GEN-LAST:event_titlebarpnl3MouseDragged

    private void titlebarpnl3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_titlebarpnl3MousePressed
        // TODO add your handling code here:
        mouseX = evt.getX();
        mouseY = evt.getY();
    }//GEN-LAST:event_titlebarpnl3MousePressed

    private void titlebarpnl3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_titlebarpnl3MouseReleased

    }//GEN-LAST:event_titlebarpnl3MouseReleased

    private void btnBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBackMouseEntered

    private void btnBackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBackMouseExited

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here

        // 1) get current state of chatbot (NORMAL / MAXIMIZED / ICONIFIED)
        int state = this.getExtendedState();

        // 2) close chatbot
        this.dispose();

        // 3) open dashboard and apply same state
        Frm_Dashboard dashboard = new Frm_Dashboard(username, role);
        dashboard.setExtendedState(state);

        // optional: if you want it to appear in the same screen position when NORMAL
        // dashboard.setLocation(this.getLocation());

        dashboard.setVisible(true);

    }//GEN-LAST:event_btnBackActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Frm_Credits.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frm_Credits.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frm_Credits.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm_Credits.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frm_Credits().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton closebtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblDesc;
    private javax.swing.JLabel lblGrp;
    private javax.swing.JLabel lblGrpMem;
    private javax.swing.JLabel lblInst;
    private javax.swing.JLabel lblInstName;
    private javax.swing.JLabel lblProject1;
    private javax.swing.JButton maximizebtn;
    private javax.swing.JButton minimizebtn;
    private javax.swing.JPanel titlebarpnl3;
    // End of variables declaration//GEN-END:variables
}
