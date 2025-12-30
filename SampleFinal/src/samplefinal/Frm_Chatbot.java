/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package samplefinal;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
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
public class Frm_Chatbot extends javax.swing.JFrame {

    /**
     * Creates new form Frm_Chatbot
     */
    
    private int mouseX, mouseY;
    private boolean isMaximized;
    
    // ===== CHATBOT SETTINGS =====
    private final String BOT_NAME = "KursoBot";

    // conversation step
    private int step = 0;

    // scores for course recommendations
    private int scoreIT = 0;
    private int scoreEng = 0;
    private int scoreBusiness = 0;
    private int scoreEducation = 0;
    private int scoreHealth = 0;
    private int scoreArts = 0;

    // store last user answers if you want
    private String lastAnswer = "";
    private final String username;
    private final String role;        
            
    public Frm_Chatbot(String username, String role) {
        this.username = username;
        this.role = role;
        
        initComponents();
        setLocationRelativeTo(null);
        makeChatbotResponsive();
        startChatbot();
    }

    public Frm_Chatbot() {
        this("TestUser", "Admin"); // fallback if you run Frm_Chatbot directly
    }
    
    private void makeChatbotResponsive() {

        // Root panel (the main container in your form)
        jPanel1.removeAll();
        jPanel1.setLayout(new BorderLayout());
        jPanel1.setOpaque(true);

        // ===== TOP: title bar + title label =====
        JPanel top = new JPanel();
        top.setOpaque(false);
        top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));

        top.add(titlebarpnl3); // your custom titlebar panel

        jLabel1.setAlignmentX(Component.CENTER_ALIGNMENT); // "CHATBOT"
        top.add(Box.createVerticalStrut(10));
        top.add(jLabel1);
        top.add(Box.createVerticalStrut(10));

        jPanel1.add(top, BorderLayout.NORTH);

        // ===== CENTER WRAPPER: adds margins and centers the chat area =====
        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setOpaque(false);
        centerWrapper.setBorder(new EmptyBorder(20, 80, 40, 80)); // top, left, bottom, right

        // This panel is the "chat box" container (scroll + input row + back button)
        JPanel chatBox = new JPanel();
        chatBox.setOpaque(false);
        chatBox.setLayout(new BoxLayout(chatBox, BoxLayout.Y_AXIS));

        int maxWidth = 1100; // max width of chat area
        chatBox.setMaximumSize(new Dimension(maxWidth, Integer.MAX_VALUE));

        // ===== Chat Area (scrollpane) =====
        jScrollPane1.setAlignmentX(Component.CENTER_ALIGNMENT);
        jScrollPane1.setPreferredSize(new Dimension(maxWidth, 430));
        jScrollPane1.setMaximumSize(new Dimension(maxWidth, 600));
        chatBox.add(jScrollPane1);

        chatBox.add(Box.createVerticalStrut(15));

        // ===== Input Row (textfield + send button aligned) =====
        JPanel inputRow = new JPanel();
        inputRow.setOpaque(false);
        inputRow.setLayout(new BoxLayout(inputRow, BoxLayout.X_AXIS));
        inputRow.setAlignmentX(Component.CENTER_ALIGNMENT);

        int inputH = 55;
        int sendW = 70;

        txtField.setPreferredSize(new Dimension(maxWidth - sendW - 10, inputH));
        txtField.setMaximumSize(new Dimension(maxWidth - sendW - 10, inputH));

        btnSend.setPreferredSize(new Dimension(sendW, inputH));
        btnSend.setMaximumSize(new Dimension(sendW, inputH));

        inputRow.add(txtField);
        inputRow.add(Box.createHorizontalStrut(10));
        inputRow.add(btnSend);

        chatBox.add(inputRow);

        // space between input and back button
        chatBox.add(Box.createVerticalStrut(20));

        // ===== BACK BUTTON ROW (CENTERED BELOW) =====
        JPanel backRow = new JPanel();
        backRow.setOpaque(false);
        backRow.setLayout(new BoxLayout(backRow, BoxLayout.X_AXIS));
        backRow.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnBack.setPreferredSize(new Dimension(160, 45));
        btnBack.setMaximumSize(new Dimension(160, 45));

        backRow.add(btnBack);

        chatBox.add(backRow);

        // optional bottom space
        chatBox.add(Box.createVerticalStrut(20));

        // Put chatBox into centerWrapper (centered)
        centerWrapper.add(chatBox, new GridBagConstraints());

        jPanel1.add(centerWrapper, BorderLayout.CENTER);

        // Refresh
        jPanel1.revalidate();
        jPanel1.repaint();
    }

    private void addChat(String speaker, String msg) {
        txtArea.append(speaker + ": " + msg + "\n\n");
        txtArea.setCaretPosition(txtArea.getDocument().getLength());
    }

    private String norm(String s) {
        return s.trim().toLowerCase();
    }

    private boolean isYes(String s) {
        s = norm(s);
        return s.equals("oo") || s.equals("yes") || s.equals("y") || s.equals("opo") || s.equals("oo po");
    }

    private boolean isNo(String s) {
        s = norm(s);
        return s.equals("hindi") || s.equals("no") || s.equals("n") || s.equals("hinde") || s.equals("di") || s.equals("hindi po");
    }
    
    private void startChatbot() {
        step = 0;
        resetScores();
        addChat(BOT_NAME, "Kamusta! Ako si " + BOT_NAME + ". Tutulungan kitang pumili ng tamang kurso sa college/university.");
        addChat(BOT_NAME, "Handa ka na ba? (Oo/Hindi)");
    }


    private void resetScores() {
        scoreIT = scoreEng = scoreBusiness = scoreEducation = scoreHealth = scoreArts = 0;
    }
    
    private void handleChatbot(String input) {
        String a = norm(input);
        lastAnswer = a;

        switch(step) {

            // Q1
            case 0:
                if (isYes(a)) {
                    step++;
                    addChat(BOT_NAME, "Ayos! Unang tanong: Mas gusto mo ba ang Math at problem solving? (Oo/Hindi)");
                } else if (isNo(a)) {
                    addChat(BOT_NAME, "Sige, kapag handa ka na, sabihin mo lang 'Oo'. 🙂");
                } else {
                    addChat(BOT_NAME, "Pakisagot: Oo o Hindi 🙂");
                }
                break;

            // Q2
            case 1:
                if (isYes(a)) { scoreEng += 2; scoreIT += 1; }
                else if (isNo(a)) { scoreArts += 1; scoreEducation += 1; scoreBusiness += 1; }
                else { addChat(BOT_NAME, "Oo/Hindi lang muna 🙂"); return; }

                step++;
                addChat(BOT_NAME, "Mahilig ka ba sa computers/technology? (Oo/Hindi)");
                break;

            // Q3
            case 2:
                if (isYes(a)) { scoreIT += 3; }
                else if (isNo(a)) { scoreHealth += 1; scoreEducation += 1; }
                else { addChat(BOT_NAME, "Oo/Hindi lang muna 🙂"); return; }

                step++;
                addChat(BOT_NAME, "Mas gusto mo ba ang pagdidisenyo/creativity (drawing, video, writing)? (Oo/Hindi)");
                break;

            // Q4
            case 3:
                if (isYes(a)) { scoreArts += 3; }
                else if (isNo(a)) { scoreEng += 1; scoreBusiness += 1; }
                else { addChat(BOT_NAME, "Oo/Hindi lang muna 🙂"); return; }

                step++;
                addChat(BOT_NAME, "Interesado ka bang tumulong sa may sakit o health-related work? (Oo/Hindi)");
                break;

            // Q5
            case 4:
                if (isYes(a)) { scoreHealth += 3; }
                else if (isNo(a)) { scoreIT += 1; scoreBusiness += 1; }
                else { addChat(BOT_NAME, "Oo/Hindi lang muna 🙂"); return; }

                step++;
                addChat(BOT_NAME, "Gusto mo bang magturo o mag-explain sa ibang tao? (Oo/Hindi)");
                break;

            // Q6
            case 5:
                if (isYes(a)) { scoreEducation += 3; }
                else if (isNo(a)) { scoreEng += 1; scoreIT += 1; }
                else { addChat(BOT_NAME, "Oo/Hindi lang muna 🙂"); return; }

                step++;
                addChat(BOT_NAME, "Mahilig ka ba sa leadership, negosyo, o pag-manage ng pera? (Oo/Hindi)");
                break;

            // Q7
            case 6:
                if (isYes(a)) { scoreBusiness += 3; }
                else if (isNo(a)) { scoreArts += 1; scoreHealth += 1; }
                else { addChat(BOT_NAME, "Oo/Hindi lang muna 🙂"); return; }

                step++;
                addChat(BOT_NAME, "Kaya mo ba ang pressure at deadlines? (Oo/Hindi)");
                break;

            // Q8
            case 7:
                if (isYes(a)) { scoreIT += 1; scoreEng += 1; scoreBusiness += 1; scoreHealth += 1; }
                else if (isNo(a)) { scoreEducation += 1; scoreArts += 1; }
                else { addChat(BOT_NAME, "Oo/Hindi lang muna 🙂"); return; }

                step++;
                addChat(BOT_NAME, "Mas prefer mo ba ang hands-on/practical work kaysa puro theory? (Oo/Hindi)");
                break;

            // Q9
            case 8:
                if (isYes(a)) { scoreEng += 2; scoreHealth += 1; scoreIT += 1; }
                else if (isNo(a)) { scoreBusiness += 1; scoreEducation += 1; scoreArts += 1; }
                else { addChat(BOT_NAME, "Oo/Hindi lang muna 🙂"); return; }

                step++;
                addChat(BOT_NAME, "Mas gusto mo ba ng kursong maraming job opportunities (high demand)? (Oo/Hindi)");
                break;

            // Q10
            case 9:
                if (isYes(a)) { scoreIT += 1; scoreEng += 1; scoreHealth += 1; scoreBusiness += 1; }
                else if (isNo(a)) { scoreArts += 1; scoreEducation += 1; }
                else { addChat(BOT_NAME, "Oo/Hindi lang muna 🙂"); return; }

                step++;
                addChat(BOT_NAME, "Mas gusto mo ba ng work na may interaction sa tao (clients/students/patients)? (Oo/Hindi)");
                break;

            // Q11
            case 10:
                if (isYes(a)) { scoreBusiness += 1; scoreEducation += 2; scoreHealth += 2; }
                else if (isNo(a)) { scoreIT += 2; scoreEng += 2; }
                else { addChat(BOT_NAME, "Oo/Hindi lang muna 🙂"); return; }

                step++;
                addChat(BOT_NAME, "Mas gusto mo ba ng course na may board exam? (Oo/Hindi)");
                break;

            // Q12
            case 11:
                if (isYes(a)) { scoreEng += 2; scoreHealth += 2; scoreEducation += 2; }
                else if (isNo(a)) { scoreIT += 1; scoreBusiness += 1; scoreArts += 1; }
                else { addChat(BOT_NAME, "Oo/Hindi lang muna 🙂"); return; }

                // FINAL
                step++;
                showFinalRecommendation();
                break;

            default:
                // after recommendation, allow restart
                if (a.contains("restart") || a.contains("ulit") || a.contains("retake")) {
                    startChatbot();
                } else {
                    addChat(BOT_NAME, "Kung gusto mo ulitin, type mo: 'ulit' or 'restart'.");
                }
                break;
        }
    }
    
    private void showFinalRecommendation() {
        String best = "IT / Computer Science";
        int bestScore = scoreIT;

        if (scoreEng > bestScore) { bestScore = scoreEng; best = "Engineering (Civil/Mechanical/Electrical)"; }
        if (scoreBusiness > bestScore) { bestScore = scoreBusiness; best = "Business / Accountancy / Entrepreneurship"; }
        if (scoreEducation > bestScore) { bestScore = scoreEducation; best = "Education / Teaching"; }
        if (scoreHealth > bestScore) { bestScore = scoreHealth; best = "Health Sciences (Nursing/MedTech/etc.)"; }
        if (scoreArts > bestScore) { bestScore = scoreArts; best = "Arts / Multimedia / Communication"; }

        addChat(BOT_NAME, "✅ Salamat! Base sa sagot mo, ito ang pinakamatch na kurso para sa’yo:");
        addChat(BOT_NAME, "🎓 RECOMMENDATION: " + best);

        addChat(BOT_NAME,
            "📌 Scores:\n"
            + "IT=" + scoreIT
            + " | Eng=" + scoreEng
            + " | Business=" + scoreBusiness
            + " | Education=" + scoreEducation
            + " | Health=" + scoreHealth
            + " | Arts=" + scoreArts
        );

        addChat(BOT_NAME, "Kung gusto mo ulitin at mag-iba ng sagot, type mo: 'ulit' 🙂");
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
        btnBack = new javax.swing.JButton();
        titlebarpnl3 = new javax.swing.JPanel();
        closebtn = new javax.swing.JButton();
        minimizebtn = new javax.swing.JButton();
        maximizebtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        txtField = new javax.swing.JTextField();
        btnSend = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1400, 800));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

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

        jLabel1.setFont(new java.awt.Font("Broadway", 0, 55)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CHATBOT");

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        txtArea.setBackground(new java.awt.Color(255, 153, 153));
        txtArea.setColumns(20);
        txtArea.setFont(new java.awt.Font("Segoe UI Emoji", 0, 18)); // NOI18N
        txtArea.setForeground(new java.awt.Color(0, 0, 0));
        txtArea.setRows(5);
        txtArea.setWrapStyleWord(true);
        txtArea.setBorder(null);
        txtArea.setSelectedTextColor(new java.awt.Color(204, 0, 0));
        jScrollPane1.setViewportView(txtArea);

        txtField.setBackground(new java.awt.Color(51, 51, 51));
        txtField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtField.setForeground(new java.awt.Color(255, 255, 255));
        txtField.setBorder(null);
        txtField.setOpaque(true);

        btnSend.setBackground(new java.awt.Color(0, 0, 0));
        btnSend.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/send.png"))); // NOI18N
        btnSend.setBorder(null);
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtField, javax.swing.GroupLayout.PREFERRED_SIZE, 968, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSend, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(txtField))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlebarpnl3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 63, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(537, 537, 537))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(titlebarpnl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 37, Short.MAX_VALUE))
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

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        // TODO add your handling code here:
        String userMsg = txtField.getText().trim();
        if (userMsg.isEmpty()) return;

        addChat(username, userMsg);   // show user's name
        txtField.setText("");

        handleChatbot(userMsg);
    }//GEN-LAST:event_btnSendActionPerformed

    private void btnBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBackMouseEntered

    private void btnBackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBackMouseExited

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        txtArea.setText("");
        txtField.setText("");

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
            java.util.logging.Logger.getLogger(Frm_Chatbot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frm_Chatbot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frm_Chatbot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm_Chatbot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frm_Chatbot().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnSend;
    private javax.swing.JButton closebtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton maximizebtn;
    private javax.swing.JButton minimizebtn;
    private javax.swing.JPanel titlebarpnl3;
    private javax.swing.JTextArea txtArea;
    private javax.swing.JTextField txtField;
    // End of variables declaration//GEN-END:variables
}
