/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samplefinal;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Timer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JButton;
/**
 *
 * @author acer
 */
public class Frm_Dashboard extends javax.swing.JFrame {

    String username;
    String role;
    
    private int mouseX, mouseY;
    private boolean isMaximized;
    
    private Timer dateTimeTimer;


    public Frm_Dashboard (String username, String role) {
        this.username = username;
        this.role = role;

        initComponents();
        
        // set static texts
        lblWelcome.setText("Welcome, " + username + "!");
        lblRole.setText("Role: " + role);

        // start clock/date updater
        startDateTimeUpdater();
        
        applyAccessLevel();
        jPanel3.revalidate();
        jPanel3.repaint();
        setupDashboardButtons();
        
        setupDashboardButtons();

        // Reset main panel layout
        jPanel1.setLayout(new BorderLayout());
        jPanel1.removeAll();
        jPanel1.add(titlebarpnl3, BorderLayout.NORTH);

        // Main vertical content holder
        JPanel content = new JPanel();
        content.setOpaque(false);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        // Title label near panel
        jLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(jLabel1);
        content.add(Box.createVerticalStrut(10));

        // Wrapper that allows jPanel3 to grow in fullscreen
        JPanel centerWrapper = new JPanel(new BorderLayout());
        centerWrapper.setOpaque(false);
        centerWrapper.setBorder(
            BorderFactory.createEmptyBorder(0, 60, 30, 60) // margins
        );

        // IMPORTANT: add jPanel3 ONLY ONCE
        centerWrapper.add(jPanel3, BorderLayout.CENTER);

        // Push panel toward bottom on tall screens
        content.add(Box.createVerticalGlue());
        content.add(centerWrapper);

        jPanel1.add(content, BorderLayout.CENTER);

        // Refresh UI
        jPanel1.revalidate();
        jPanel1.repaint();
        
        setLocationRelativeTo(null);


    }
    
    private void startDateTimeUpdater() {
        DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("MMMM d, yyyy"); // e.g. August 3, 2025
        DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern("hh:mm:ss a");   // e.g. 09:14:05 PM

        // update immediately once
        updateDateTime(dateFmt, timeFmt);

        // then update every 1 second
        dateTimeTimer = new Timer(1000, e -> updateDateTime(dateFmt, timeFmt));
        dateTimeTimer.start();
    }

    private void updateDateTime(DateTimeFormatter dateFmt, DateTimeFormatter timeFmt) {
        LocalDateTime now = LocalDateTime.now();
        lblDate.setText("Date: " + now.format(dateFmt));
        lblTime.setText("Time: " + now.format(timeFmt));
    }
    
    private void applyAccessLevel() {
        String r = (role == null) ? "" : role.trim().toUpperCase();

        boolean isAdmin = r.equals("ADMIN");
        boolean isUser  = r.equals("USER");
        boolean isGuest = r.equals("GUEST");

        // B1 Take Quiz
        btnQuiz.setVisible(isAdmin || isUser);

        // B2 Simple Chatbot
        btnChatbot.setVisible(isAdmin || isUser);

        // B3 Data Visualization
        btnDataViz.setVisible(isAdmin || isGuest);

        // B4 Credits (everyone)
        btnCredits.setVisible(true);
    }
    
    private JPanel menuRow;   // make it a field if you want to refresh later

    private void setupDashboardButtons() {

        jPanel3.setLayout(new BorderLayout());

        // ================= TOP: INFO LABELS =================
        JPanel infoPanel = new JPanel(new GridLayout(2, 2, 40, 10));
        infoPanel.setOpaque(false);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(30, 60, 20, 60));

        infoPanel.add(lblWelcome);
        infoPanel.add(lblDate);
        infoPanel.add(lblRole);
        infoPanel.add(lblTime);

        jPanel3.add(infoPanel, BorderLayout.NORTH);

        // ================= CENTER: MENU BUTTONS (COLLAPSES) =================
        // ================= CENTER: MENU BUTTONS (COLLAPSE + FIXED SIZE) =================
        JPanel menuRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 0));
        menuRow.setOpaque(false);
        menuRow.setBorder(BorderFactory.createEmptyBorder(40, 40, 30, 40));

        // icon above text
        styleMenuButton(btnQuiz);
        styleMenuButton(btnChatbot);
        styleMenuButton(btnDataViz);
        styleMenuButton(btnCredits);

        // lock card sizes
        setCardSize(btnQuiz, 260, 240);
        setCardSize(btnChatbot, 260, 240);
        setCardSize(btnDataViz, 260, 240);
        setCardSize(btnCredits, 260, 240);

        menuRow.add(btnQuiz);
        menuRow.add(btnChatbot);
        menuRow.add(btnDataViz);
        menuRow.add(btnCredits);

        // Center the row without resizing the buttons
        JPanel menuArea = new JPanel(new GridBagLayout());
        menuArea.setOpaque(false);
        menuArea.add(menuRow);

        jPanel3.add(menuArea, BorderLayout.CENTER);


        // ================= SOUTH: LOGOUT =================
        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        logoutPanel.setOpaque(false);
        logoutPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));
        
        btnLogout.setBorder(BorderFactory.createEmptyBorder(10, 28, 10, 28));
        btnLogout.setFocusPainted(false);
        btnLogout.setBorderPainted(false);
        btnLogout.setContentAreaFilled(true);
        btnLogout.setOpaque(true);
        
        logoutPanel.add(btnLogout);

        jPanel3.add(logoutPanel, BorderLayout.SOUTH);
    }
    
    private void setCardSize(JButton b, int w, int h) {
        Dimension d = new Dimension(w, h);
        b.setPreferredSize(d);
        b.setMinimumSize(d);
        b.setMaximumSize(d);
    }


    
    private void styleMenuButton(javax.swing.JButton b) {
        b.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        b.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        b.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        b.setVerticalAlignment(javax.swing.SwingConstants.CENTER);

        b.setIconTextGap(12);          // space between icon and text
        b.setFocusPainted(false);
        // optional: b.setBorderPainted(false);
        // optional: b.setContentAreaFilled(true);
    }



    
    public Frm_Dashboard() {
        initComponents();
        
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
        jPanel3 = new javax.swing.JPanel();
        lblWelcome = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        lblRole = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        btnQuiz = new javax.swing.JButton();
        btnChatbot = new javax.swing.JButton();
        btnDataViz = new javax.swing.JButton();
        btnCredits = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(184, 49, 67));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1400, 800));
        addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                formHierarchyChanged(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(184, 49, 67));
        jPanel1.setPreferredSize(new java.awt.Dimension(1207, 621));

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
                    .addComponent(maximizebtn, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Broadway", 0, 55)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MENU DASHBOARD");

        jPanel3.setBackground(new java.awt.Color(255, 153, 153));
        jPanel3.setPreferredSize(new java.awt.Dimension(1127, 582));

        lblWelcome.setBackground(new java.awt.Color(255, 153, 153));
        lblWelcome.setFont(new java.awt.Font("Segoe UI Semibold", 1, 36)); // NOI18N
        lblWelcome.setForeground(new java.awt.Color(204, 0, 0));
        lblWelcome.setText("User:");

        lblTime.setBackground(new java.awt.Color(255, 153, 153));
        lblTime.setFont(new java.awt.Font("Segoe UI Semibold", 1, 36)); // NOI18N
        lblTime.setForeground(new java.awt.Color(204, 0, 0));
        lblTime.setText("Time:");

        lblRole.setBackground(new java.awt.Color(255, 153, 153));
        lblRole.setFont(new java.awt.Font("Segoe UI Semibold", 1, 36)); // NOI18N
        lblRole.setForeground(new java.awt.Color(204, 0, 0));
        lblRole.setText("Role:");

        lblDate.setBackground(new java.awt.Color(255, 153, 153));
        lblDate.setFont(new java.awt.Font("Segoe UI Semibold", 1, 36)); // NOI18N
        lblDate.setForeground(new java.awt.Color(204, 0, 0));
        lblDate.setText("Date:");

        btnLogout.setBackground(new java.awt.Color(51, 51, 51));
        btnLogout.setFont(new java.awt.Font("Segoe UI Semibold", 1, 36)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setText("LOGOUT");
        btnLogout.setBorder(null);
        btnLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLogoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLogoutMouseExited(evt);
            }
        });
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        btnQuiz.setBackground(new java.awt.Color(204, 0, 0));
        btnQuiz.setFont(new java.awt.Font("Segoe UI Semibold", 1, 36)); // NOI18N
        btnQuiz.setForeground(new java.awt.Color(255, 255, 255));
        btnQuiz.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/brain.png"))); // NOI18N
        btnQuiz.setText("QUIZ");
        btnQuiz.setBorder(null);
        btnQuiz.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnQuizMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnQuizMouseExited(evt);
            }
        });
        btnQuiz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuizActionPerformed(evt);
            }
        });

        btnChatbot.setBackground(new java.awt.Color(204, 0, 0));
        btnChatbot.setFont(new java.awt.Font("Segoe UI Semibold", 1, 36)); // NOI18N
        btnChatbot.setForeground(new java.awt.Color(255, 255, 255));
        btnChatbot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bot.png"))); // NOI18N
        btnChatbot.setText("CHATBOT");
        btnChatbot.setBorder(null);
        btnChatbot.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnChatbotMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnChatbotMouseExited(evt);
            }
        });
        btnChatbot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChatbotActionPerformed(evt);
            }
        });

        btnDataViz.setBackground(new java.awt.Color(204, 0, 0));
        btnDataViz.setFont(new java.awt.Font("Segoe UI Semibold", 1, 36)); // NOI18N
        btnDataViz.setForeground(new java.awt.Color(255, 255, 255));
        btnDataViz.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/analysis.png"))); // NOI18N
        btnDataViz.setText("DATA");
        btnDataViz.setBorder(null);
        btnDataViz.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDataVizMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDataVizMouseExited(evt);
            }
        });
        btnDataViz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataVizActionPerformed(evt);
            }
        });

        btnCredits.setBackground(new java.awt.Color(204, 0, 0));
        btnCredits.setFont(new java.awt.Font("Segoe UI Semibold", 1, 36)); // NOI18N
        btnCredits.setForeground(new java.awt.Color(255, 255, 255));
        btnCredits.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/info.png"))); // NOI18N
        btnCredits.setText("CREDITS");
        btnCredits.setBorder(null);
        btnCredits.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCreditsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCreditsMouseExited(evt);
            }
        });
        btnCredits.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreditsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRole, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTime, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(95, 95, 95))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(btnQuiz, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(btnChatbot, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(btnDataViz, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btnCredits, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(429, 429, 429)
                        .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRole)
                    .addComponent(lblTime))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnQuiz, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnChatbot, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCredits, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDataViz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlebarpnl3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(titlebarpnl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_formHierarchyChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_formHierarchyChanged

    private void titlebarpnl3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_titlebarpnl3MouseReleased

    }//GEN-LAST:event_titlebarpnl3MouseReleased

    private void titlebarpnl3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_titlebarpnl3MousePressed
        // TODO add your handling code here:
        mouseX = evt.getX();
        mouseY = evt.getY();
    }//GEN-LAST:event_titlebarpnl3MousePressed

    private void titlebarpnl3MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_titlebarpnl3MouseDragged
        // TODO add your handling code here:
        if (isMaximized) return; // optional: disable dragging when maximized
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        setLocation(x - mouseX, y - mouseY);
    }//GEN-LAST:event_titlebarpnl3MouseDragged

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

    private void maximizebtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_maximizebtnMouseExited
        // TODO add your handling code here:
        maximizebtn.setBackground(new Color(194, 89, 86));
    }//GEN-LAST:event_maximizebtnMouseExited

    private void maximizebtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_maximizebtnMouseEntered
        // TODO add your handling code here:
        maximizebtn.setBackground(new Color(170, 70, 68));
    }//GEN-LAST:event_maximizebtnMouseEntered

    private void minimizebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minimizebtnActionPerformed
        // TODO add your handling code here:
        setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_minimizebtnActionPerformed

    private void minimizebtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizebtnMouseExited
        // TODO add your handling code here:
        minimizebtn.setBackground(new Color(194, 89, 86));
    }//GEN-LAST:event_minimizebtnMouseExited

    private void minimizebtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizebtnMouseEntered
        // TODO add your handling code here:
        minimizebtn.setBackground(new Color(170, 70, 68));
    }//GEN-LAST:event_minimizebtnMouseEntered

    private void closebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closebtnActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_closebtnActionPerformed

    private void closebtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closebtnMouseExited
        // TODO add your handling code here:
        closebtn.setBackground(new Color(194, 89, 86));
    }//GEN-LAST:event_closebtnMouseExited

    private void closebtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closebtnMouseEntered
        // TODO add your handling code here:
        closebtn.setBackground(new Color(170, 70, 68));
    }//GEN-LAST:event_closebtnMouseEntered

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
         int choice = javax.swing.JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to log out?",
            "Confirm Logout",
            javax.swing.JOptionPane.YES_NO_OPTION,
            javax.swing.JOptionPane.QUESTION_MESSAGE
        );

        if (choice == javax.swing.JOptionPane.YES_OPTION) {
            System.exit(0); // closes the program
        }
        
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnQuizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuizActionPerformed
        // TODO add your handling code here:
        Frm_Quiz quiz = new Frm_Quiz(username, role);

        quiz.setExtendedState(this.getExtendedState()); // keep fullscreen/normal
        quiz.setVisible(true);

        this.dispose(); // close dashboard
    }//GEN-LAST:event_btnQuizActionPerformed

    private void btnChatbotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChatbotActionPerformed
        // TODO add your handling code here:
        Frm_Chatbot chatbot = new Frm_Chatbot(username, role);

        chatbot.setExtendedState(this.getExtendedState()); // keep fullscreen/normal
        chatbot.setVisible(true);

        this.dispose(); // close dashboard
    }//GEN-LAST:event_btnChatbotActionPerformed

    private void btnDataVizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataVizActionPerformed
        // TODO add your handling code here:
        Frm_DataViz dataviz = new Frm_DataViz(username, role);

        dataviz.setExtendedState(this.getExtendedState()); // keep fullscreen/normal
        dataviz.setVisible(true);

        this.dispose(); // close dashboard
    }//GEN-LAST:event_btnDataVizActionPerformed

    private void btnCreditsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreditsActionPerformed
        // TODO add your handling code here:
        Frm_Credits credits = new Frm_Credits(username, role);

        credits.setExtendedState(this.getExtendedState()); // keep fullscreen/normal
        credits.setVisible(true);

        this.dispose(); // close dashboard
    }//GEN-LAST:event_btnCreditsActionPerformed

    private void btnQuizMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQuizMouseEntered
        // TODO add your handling code here:
        btnQuiz.setBackground(new Color(170, 70, 68));
    }//GEN-LAST:event_btnQuizMouseEntered

    private void btnQuizMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQuizMouseExited
        // TODO add your handling code here:
        btnQuiz.setBackground(new Color(204,0,0));
    }//GEN-LAST:event_btnQuizMouseExited

    private void btnChatbotMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChatbotMouseEntered
        // TODO add your handling code here:
        btnChatbot.setBackground(new Color(170, 70, 68));
    }//GEN-LAST:event_btnChatbotMouseEntered

    private void btnChatbotMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChatbotMouseExited
        // TODO add your handling code here:
        btnChatbot.setBackground(new Color(204,0,0));
    }//GEN-LAST:event_btnChatbotMouseExited

    private void btnDataVizMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDataVizMouseEntered
        // TODO add your handling code here:
        btnDataViz.setBackground(new Color(170, 70, 68));
    }//GEN-LAST:event_btnDataVizMouseEntered

    private void btnDataVizMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDataVizMouseExited
        // TODO add your handling code here:
        btnDataViz.setBackground(new Color(204,0,0));
    }//GEN-LAST:event_btnDataVizMouseExited

    private void btnCreditsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCreditsMouseEntered
        // TODO add your handling code here:
        btnCredits.setBackground(new Color(170, 70, 68));
    }//GEN-LAST:event_btnCreditsMouseEntered

    private void btnCreditsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCreditsMouseExited
        // TODO add your handling code here:
        btnCredits.setBackground(new Color(204,0,0));
    }//GEN-LAST:event_btnCreditsMouseExited

    private void btnLogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseEntered
        // TODO add your handling code here:
        btnLogout.setBackground(new Color(102,102,102));
    }//GEN-LAST:event_btnLogoutMouseEntered

    private void btnLogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseExited
         // TODO add your handling code here:
        btnLogout.setBackground(new Color(51,51,51));
    }//GEN-LAST:event_btnLogoutMouseExited

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
            java.util.logging.Logger.getLogger(Frm_Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frm_Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frm_Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm_Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frm_Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChatbot;
    private javax.swing.JButton btnCredits;
    private javax.swing.JButton btnDataViz;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnQuiz;
    private javax.swing.JButton closebtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblRole;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JButton maximizebtn;
    private javax.swing.JButton minimizebtn;
    private javax.swing.JPanel titlebarpnl3;
    // End of variables declaration//GEN-END:variables
}
