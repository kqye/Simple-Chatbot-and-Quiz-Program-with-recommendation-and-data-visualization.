/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package samplefinal;

import java.awt.*;
import java.util.Random;
import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.Box;
import javax.swing.border.EmptyBorder;


/**
 *
 * @author Lenovo
 */
public class Frm_DataViz extends javax.swing.JFrame {

    /**
     * Creates new form Frm_DataViz
     */
    
    private int mouseX, mouseY;
    private boolean isMaximized;

    // Scroll UI
    private JScrollPane scrollPane;
    private JPanel scrollContent;

    // Bottom buttons
    private JButton btnBack;
    private JButton btnRefresh;

    // No DB: simulated data
    private final Random rnd = new Random();
    
    private final String username;
    private final String role;     

    public Frm_DataViz(String username, String role) {
        this.username = username;
        this.role = role;
        initComponents();
        setLocationRelativeTo(null);

        setupScrollableLayout();
        buildScrollCharts(); // initial load
    }
    
    public Frm_DataViz() {
        this("TestUser", "Admin"); // fallback if you run Frm_Chatbot directly
    }

    // -------------------- SCROLLABLE LAYOUT --------------------
    private void setupScrollableLayout() {
        jPanel1.setBackground(new Color(25, 25, 25));
        jPanel1.setLayout(new BorderLayout());

        // Titlebar stays on top
        jPanel1.add(titlebarpnl3, BorderLayout.NORTH);

        // Wrapper holds title + scroll pane
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(new Color(25, 25, 25));

        // Title label area
        JPanel titleArea = new JPanel(new BorderLayout());
        titleArea.setBackground(new Color(25, 25, 25));
        titleArea.setBorder(new EmptyBorder(12, 12, 8, 12));
        titleArea.add(jLabel1, BorderLayout.CENTER);

        // Scroll content panel (vertical)
        scrollContent = new JPanel();
        scrollContent.setBackground(new Color(25, 25, 25));
        scrollContent.setLayout(new BoxLayout(scrollContent, BoxLayout.Y_AXIS));
        scrollContent.setBorder(new EmptyBorder(10, 18, 18, 18));

        scrollPane = new JScrollPane(scrollContent);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(new Color(25, 25, 25));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // smoother scroll

        wrapper.add(titleArea, BorderLayout.NORTH);
        wrapper.add(scrollPane, BorderLayout.CENTER);

        jPanel1.add(wrapper, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    // -------------------- BUILD 7 CHARTS + DESCRIPTIONS --------------------
    private void buildScrollCharts() {
        scrollContent.removeAll();

        // 1) BAR: Cases per Disease
        addChartSection(
                "Cases per Disease",
                createBarChart(
                        "Cases per Disease",
                        new String[]{"Flu", "Dengue", "COVID-19", "Pneumonia", "TB"},
                        new int[]{rand(20, 90), rand(10, 60), rand(5, 50), rand(10, 70), rand(5, 40)},
                        "Disease", "Patients"
                ),
                "Shows the number of patients per disease.\n"
                    + "Useful for identifying which diseases are currently most common "
                    + "and need priority resources."
        );

        // 2) PIE: Gender Distribution
        addChartSection(
                "Gender Distribution",
                createPieChart(
                        "Gender Distribution",
                        new String[]{"Male", "Female"},
                        new int[]{rand(50, 150), rand(50, 150)}
                ),
                
                "Shows the patient population by gender."
                    + "Useful for demographic monitoring and reporting. "
                    
                
        );

        // 3) BAR: Age Group
        addChartSection(
                "Patients by Age Group",
                createBarChart(
                        "Patients by Age Group",
                        new String[]{"0-12", "13-19", "20-35", "36-59", "60+"},
                        new int[]{rand(5, 40), rand(5, 35), rand(20, 100), rand(15, 90), rand(10, 70)},
                        "Age Group", "Patients"
                ),
                
                " Shows how patients are distributed by age groups."
                    + " Useful for spotting which age group is most affected and planning targeted care."
                
        );

        // 4) PIE: Admission Status
        addChartSection(
                "Admission Status",
                createPieChart(
                        "Admission Status",
                        new String[]{"Admitted", "Discharged", "Observation"},
                        new int[]{rand(10, 80), rand(10, 120), rand(5, 50)}
                ),
             
                " Shows the current status of patients (admitted, discharged, observation)."
                    + " Useful for monitoring patient flow and hospital capacity."
        );

        // 5) BAR: Monthly New Cases
        addChartSection(
                "Monthly New Cases",
                createBarChart(
                        "Monthly New Cases",
                        new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun"},
                        new int[]{rand(20, 120), rand(20, 120), rand(20, 120), rand(20, 120), rand(20, 120), rand(20, 120)},
                        "Month", "Cases"
                ),
               
                " Shows new cases per month."
                    + " Useful for trend monitoring and detecting spikes/outbreak patterns."
                
        );

        // 6) BAR: Ward Occupancy
        addChartSection(
                "Ward Occupancy",
                createBarChart(
                        "Ward Occupancy",
                        new String[]{"ER", "Ward A", "Ward B", "ICU"},
                        new int[]{rand(5, 40), rand(10, 70), rand(10, 70), rand(2, 20)},
                        "Ward", "Patients"
                ),
                
                " Shows how many patients are currently in each ward."
                    + " Useful for capacity planning and identifying overloaded areas (e.g., ICU)."
        );

        // 7) PIE: Severity Level
        addChartSection(
                "Severity Level",
                createPieChart(
                        "Severity Level",
                        new String[]{"Mild", "Moderate", "Severe"},
                        new int[]{rand(30, 160), rand(10, 90), rand(2, 40)}
                ),
                "Shows patient severity levels. "
                    + " Useful for prioritizing critical cases and checking healthcare workload."
        );

        // Bottom buttons AFTER ALL CHARTS
        addBottomButtons();

        scrollContent.revalidate();
        scrollContent.repaint();
    }

    private void addChartSection(String sectionTitle, JPanel chartPanel, String description) {
        // Section title label
        JLabel title = new JLabel(sectionTitle);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setBorder(new EmptyBorder(14, 0, 8, 0));

        // Row: chart (left) + description (right)
        JPanel row = new JPanel(new GridBagLayout());
        row.setBackground(new Color(25, 25, 25));
        row.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(70, 70, 70)),
                new EmptyBorder(12, 12, 12, 12)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 12);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.65;
        gbc.fill = GridBagConstraints.BOTH;

        chartPanel.setPreferredSize(new Dimension(720, 340));
        row.add(chartPanel, gbc);

        // Description label (use HTML for wrapping)
        JLabel desc = new JLabel(toHtml(description));
        desc.setForeground(Color.WHITE);
        desc.setFont(new Font("SansSerif", Font.PLAIN, 16));
        desc.setVerticalAlignment(SwingConstants.TOP);

        JPanel descWrap = new JPanel(new BorderLayout());
        descWrap.setBackground(new Color(25, 25, 25));
        descWrap.add(desc, BorderLayout.NORTH);

        gbc.gridx = 1;
        gbc.weightx = 0.35;
        gbc.insets = new Insets(0, 12, 0, 0);
        row.add(descWrap, gbc);

        // Add to scrollContent
        scrollContent.add(title);
        scrollContent.add(row);
        scrollContent.add(Box.createVerticalStrut(10));
    }

    private void addBottomButtons() {
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 14, 16));
        bottom.setBackground(new Color(25, 25, 25));
        bottom.setBorder(new EmptyBorder(10, 0, 20, 0));

        btnBack = new JButton("Back");
        btnRefresh = new JButton("Refresh Data");

        styleFlatButton(btnBack);
        styleFlatButton(btnRefresh);

        btnBack.addActionListener(e -> {
            int state = this.getExtendedState();
            this.dispose();

            Frm_Dashboard dashboard = new Frm_Dashboard(username, role);
            dashboard.setExtendedState(state);
            dashboard.setVisible(true);
        });

        btnRefresh.addActionListener(e -> buildScrollCharts());

        bottom.add(btnBack);
        bottom.add(btnRefresh);

        scrollContent.add(Box.createVerticalStrut(10));
        scrollContent.add(bottom);
    }

    private void styleFlatButton(JButton btn) {
        Color normal = new Color(153, 0, 0);      // default red
        Color hover  = new Color(180, 50, 50);    // hover red

        btn.setBackground(normal);
        btn.setForeground(Color.WHITE);

        btn.setBorder(null);          // NO BORDER
        btn.setFocusPainted(false);   // remove focus outline
        btn.setContentAreaFilled(true);
        btn.setOpaque(true);

        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setFont(new Font("SansSerif", Font.BOLD, 15));
        btn.setPreferredSize(new Dimension(160, 44));

        // Hover effect
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn.setBackground(hover);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                btn.setBackground(normal);
            }
        });
    }

    private String toHtml(String text) {
        // Wrap text nicely in JLabel
        String t = text.trim().replace("\n", "<br>");
        return "<html><div style='width:260px;'>" + t + "</div></html>";
    }

    private int rand(int min, int max) {
        return rnd.nextInt((max - min) + 1) + min;
    }

    // -------------------- CHART FACTORIES --------------------
    private JPanel createBarChart(String title, String[] categories, int[] values, String xLabel, String yLabel) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < categories.length; i++) {
            dataset.addValue(values[i], "Count", categories[i]);
        }

        JFreeChart chart = ChartFactory.createBarChart(
                title, xLabel, yLabel, dataset,
                PlotOrientation.VERTICAL, false, true, false
        );

        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        panel.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80)));

        JPanel wrap = new JPanel(new BorderLayout());
        wrap.setBackground(new Color(25, 25, 25));
        wrap.add(panel, BorderLayout.CENTER);

        return wrap;
    }

    private JPanel createPieChart(String title, String[] labels, int[] values) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (int i = 0; i < labels.length; i++) {
            dataset.setValue(labels[i], values[i]);
        }

        JFreeChart chart = ChartFactory.createPieChart(title, dataset, true, true, false);

        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        panel.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80)));

        JPanel wrap = new JPanel(new BorderLayout());
        wrap.setBackground(new Color(25, 25, 25));
        wrap.add(panel, BorderLayout.CENTER);

        return wrap;
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
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1400, 800));

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

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Simple Chatbot, Quiz, Recommendation, and Data Visualization System");

        javax.swing.GroupLayout titlebarpnl3Layout = new javax.swing.GroupLayout(titlebarpnl3);
        titlebarpnl3.setLayout(titlebarpnl3Layout);
        titlebarpnl3Layout.setHorizontalGroup(
            titlebarpnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titlebarpnl3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 632, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    .addComponent(maximizebtn, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Broadway", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PATIENT AND DISEASES MONITORING DATA VISUALIZATION");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlebarpnl3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1204, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(titlebarpnl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 479, Short.MAX_VALUE))
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
            java.util.logging.Logger.getLogger(Frm_DataViz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frm_DataViz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frm_DataViz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm_DataViz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frm_DataViz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closebtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton maximizebtn;
    private javax.swing.JButton minimizebtn;
    private javax.swing.JPanel titlebarpnl3;
    // End of variables declaration//GEN-END:variables
}
