/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package samplefinal;

import java.awt.Color;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

import javax.swing.plaf.basic.BasicTabbedPaneUI;

import javax.swing.JScrollPane;
import javax.swing.JComponent;
import javax.swing.JRadioButton;



/**
 *
 * @author Lenovo
 */

public class Frm_Quiz extends javax.swing.JFrame {

    /**
     * Creates new form Frm_Quiz
     */
    private int mouseX, mouseY;
    private boolean isMaximized;
    
    private final String username;
    private final String role;      
    
    // attempts per user (keeps count while program is running)
    private static final int MAX_ATTEMPTS = 3;
    private static final Map<String, Integer> attemptCountByUser = new HashMap<>();

    private javax.swing.Timer quizTimer;
    private int secondsLeft = 10 * 60; // 10 minutes
    
    private int score = 0;

    private static final int TOTAL_QUESTIONS = 17;
    private boolean[] answersCorrect = new boolean[TOTAL_QUESTIONS];

    // result tab index (last tab)
    private int getResultTabIndex() {
        return jTabbedPane1.getTabCount() - 1;
    }
    
    private javax.swing.ButtonGroup bgQ2 = new javax.swing.ButtonGroup();
    private javax.swing.ButtonGroup bgQ3 = new javax.swing.ButtonGroup();
    private javax.swing.ButtonGroup bgQ5 = new javax.swing.ButtonGroup();
    private javax.swing.ButtonGroup bgQ6 = new javax.swing.ButtonGroup();
    private javax.swing.ButtonGroup bgQ7 = new javax.swing.ButtonGroup();
    private javax.swing.ButtonGroup bgQ8 = new javax.swing.ButtonGroup();
    private javax.swing.ButtonGroup bgQ9 = new javax.swing.ButtonGroup();
    private javax.swing.ButtonGroup bgQ10 = new javax.swing.ButtonGroup();
    private javax.swing.ButtonGroup bgQ11 = new javax.swing.ButtonGroup();
    private javax.swing.ButtonGroup bgQ12 = new javax.swing.ButtonGroup();
    private javax.swing.ButtonGroup bgQ14 = new javax.swing.ButtonGroup();
    private javax.swing.ButtonGroup bgQ15 = new javax.swing.ButtonGroup();
    private javax.swing.ButtonGroup bgQ16 = new javax.swing.ButtonGroup();
    private javax.swing.ButtonGroup bgQ17 = new javax.swing.ButtonGroup();
    


    public Frm_Quiz(String username, String role) {
        this.username = username;
        this.role = role;
        initComponents();
        setLocationRelativeTo(null);
        setupButtonGroups();
        
        lblUser.setText("User: " + username);
        lblRole.setText("Role: " + role);
        lblTimer.setText("Time: 10:00"); // placeholder for now
        
        makeResponsiveLayout();
        makeStartTabResponsive();

        setupStartTabText();
        updateAttemptText();
        disableQuizTabUntilStarted();
        
        
        
        hideTabbedPaneHeader();
        
        makeQuestionResponsive(
            pnlq1,
            lblquestion,
            new JComponent[]{ cbxphysicsC, cbxchemC, cbxastrology, cbxbioC },
            btnNext
        );
        makeQuestionResponsive(
            pnlq2,
            lblquestion1,
            new JComponent[] { rdbA, rdbB, rdbCC, rdbD },
            btnNext1
        );
        makeQuestionResponsive(
            pnlq3,
            lblquestion2,
            new JComponent[]{ rdbA3, rdbB3, rdbC3C, rdbD3 },
            btnNext2
        );
        makeQuestionResponsive(
            pnlq4,
            lblquestion3,
            new JComponent[]{ cbxA4, cbxB4C, cbxC4C, cbxD4C },
            btnNext3
        );
        makeQuestionResponsive(
            pnlq5,
            lblquestion4,
            new JComponent[]{ rdbA5, rdbB5, rdbC5, rdbD5C },
            btnNext4
        );
        makeQuestionResponsive(
            pnlq6,
            lblquestion5,
            new JComponent[] { rdbA6, rdbB6C, rdbC6, rdbD6 },
            btnNext5
        );
        makeQuestionResponsive(
            pnlq7,
            lblquestion6,
            new JComponent[] { rdbA7, rdbB7, rdbC7, rdbD7C },
            btnNext6
        );
        makeQuestionResponsive(
            pnlq8,
            lblquestion7,
            new JComponent[]{ rdbA8, rdbB8, rdbC8C, rdbD8 },
            btnNext7
        );
        makeQuestionResponsive(
            pnlq9,
            lblquestion8,
            new JComponent[]{ rdbA9C, rdbB9, rdbC9, rdbD9 },
            btnNext8
        );
        makeQuestionResponsive(
            pnlq10,
            lblquestion9,
            new JComponent[] { rdbA10, rdbB10C, rdbC10, rdbD10 },
            btnNext9
        );
        makeQuestionResponsive(
            pnlq11,
            lblquestion10,
            new JComponent[] { rdbA11, rdbB11, rdbC11, rdbD11C },
            btnNext10
        );
        makeQuestionResponsive(
            pnlq12,
            lblquestion11,
            new JComponent[]{ rdbA12, rdbA12C, rdbC12, rdbD12 },
            btnNext11
        );
        makeQuestionResponsive(
            pnlq13,
            lblquestion12,
            new JComponent[] { cbxA13, cbxB13C, cbxC13, cbxD13C },
            btnNext12
        );
        makeQuestionResponsive(
            pnlq14,
            lblquestion13,
            new JComponent[]{ rdbA14C, rdbB14, rdbC14, rdbD14 },
            btnNext13
        );
        makeQuestionResponsive(
            pnlq15,
            lblquestion14,
            new JComponent[]{ rdbA15, rdbB15, rdbC15C, rdbD15 },
            btnNext14
        );
        makeQuestionResponsive(
            pnlq16,
            lblquestion15,
            new JComponent[]{ rdbA16C, rdbB16, rdbC16, rdbD16 },
            btnNext15
        );
        makeQuestionResponsive(
            pnlq17,
            lblquestion16,
            new JComponent[]{ rdbA17, rdbB17, rdbC17C, rdbD17 },
            btnSubmit
        );
        
        
        makeResultTabResponsive();


    }
    
    public Frm_Quiz() {
        this("TestUser", "Admin"); // temporary test values
    }
    
    private void makeResponsiveLayout() {
        // Replace NetBeans GroupLayout with a responsive layout
        jPanel1.setLayout(new BorderLayout());
        jPanel1.removeAll();

        // ===== NORTH: titlebar + header =====
        JPanel north = new JPanel();
        north.setOpaque(false);
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));

        // Title bar stays at top
        north.add(titlebarpnl3);

        // Header: QUIZ + timer (centered)
        JPanel header = new JPanel();
        header.setOpaque(false);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        jLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTimer.setAlignmentX(Component.CENTER_ALIGNMENT);

        header.add(Box.createVerticalStrut(10));
        header.add(jLabel1);
        header.add(Box.createVerticalStrut(5));
        header.add(lblTimer);
        header.add(Box.createVerticalStrut(10));

        north.add(header);

        jPanel1.add(north, BorderLayout.NORTH);

        // ===== CENTER: tabbed pane with margins =====
        JPanel centerWrapper = new JPanel(new BorderLayout());
        centerWrapper.setOpaque(false);

        // margins around your JTabbedPane (responsive)
        centerWrapper.setBorder(BorderFactory.createEmptyBorder(10, 60, 10, 60));
        centerWrapper.add(jTabbedPane1, BorderLayout.CENTER);

        jPanel1.add(centerWrapper, BorderLayout.CENTER);

        // ===== SOUTH: user + role (bottom-left) =====
        JPanel south = new JPanel();
        south.setOpaque(false);
        south.setLayout(new BoxLayout(south, BoxLayout.Y_AXIS));
        south.setBorder(BorderFactory.createEmptyBorder(0, 60, 20, 60));

        lblUser.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblRole.setAlignmentX(Component.LEFT_ALIGNMENT);

        south.add(lblUser);
        south.add(Box.createVerticalStrut(5));
        south.add(lblRole);

        jPanel1.add(south, BorderLayout.SOUTH);

        // Refresh
        jPanel1.revalidate();
        jPanel1.repaint();
    }
    
    private void makeStartTabResponsive() {
        pnlStart.removeAll();
        pnlStart.setLayout(new BorderLayout());

        // ===== CENTER CONTENT =====
        JPanel center = new JPanel();
        center.setOpaque(false);
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80));

        lblDescription.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblAttemptDesc.setAlignmentX(Component.CENTER_ALIGNMENT);

        center.add(Box.createVerticalGlue());
        center.add(lblDescription);
        center.add(Box.createVerticalStrut(25));
        center.add(lblAttemptDesc);
        center.add(Box.createVerticalGlue());

        // ===== BOTTOM BUTTONS (STACKED) =====
        JPanel buttons = new JPanel();
        buttons.setOpaque(false);
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        buttons.setBorder(BorderFactory.createEmptyBorder(30, 0, 40, 0));

        btnTakeQuiz.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnTakeQuiz.setPreferredSize(new Dimension(220, 60));
        btnBack.setPreferredSize(new Dimension(220, 60));
        btnTakeQuiz.setMaximumSize(new Dimension(220, 60));
        btnBack.setMaximumSize(new Dimension(220, 60));

        buttons.add(btnTakeQuiz);
        buttons.add(Box.createVerticalStrut(20));
        buttons.add(btnBack);

        pnlStart.add(center, BorderLayout.CENTER);
        pnlStart.add(buttons, BorderLayout.SOUTH);

        pnlStart.revalidate();
        pnlStart.repaint();
    }


    
    private void setupStartTabText() {
        // Use HTML so you can do multi-line text in a JLabel
        lblDescription.setText(
        "<html><div style='text-align:center;'>"
        + "<b>SCIENCE QUIZ</b><br><br>"
        + "This quiz contains basic knowledge in Science.<br>"
        + "It consists of check boxes and multiple-choice questions.<br>"
        + "You have 10 minutes to answer the following questions.<br>"
        + "Choose the best answer for each question."
        + "</div></html>"
    );
    }

    private void updateAttemptText() {
        int used = attemptCountByUser.getOrDefault(username, 0);
        int left = MAX_ATTEMPTS - used;

        lblAttemptDesc.setText("Attempts left: " + left + " / " + MAX_ATTEMPTS);

        // disable Take Quiz if no attempts left
        btnTakeQuiz.setEnabled(left > 0);
    }

    private void disableQuizTabUntilStarted() {
            enableQuizTabs(false);
    }
    
    private void enableQuizTabs(boolean enabled) {
        // tab 0 is Start, tab 1..17 are questions
        for (int i = 1; i < jTabbedPane1.getTabCount(); i++) {
            jTabbedPane1.setEnabledAt(i, enabled);
        }
    }

    private void goToTab(int index) {
        jTabbedPane1.setSelectedIndex(index);
    }

    private void startTimer() {
        // reset time each attempt (optional; remove if you want continue from previous)
        secondsLeft = 10 * 60;

        if (quizTimer != null) quizTimer.stop();

        quizTimer = new javax.swing.Timer(1000, (ActionEvent e) -> {
            secondsLeft--;

            int min = secondsLeft / 60;
            int sec = secondsLeft % 60;

            lblTimer.setText(String.format("Time: %02d:%02d", min, sec));

            if (secondsLeft <= 0) {
                quizTimer.stop();
                lblTimer.setText("Time: 00:00");
                JOptionPane.showMessageDialog(this, "Time is up! Submitting quiz.");
                submitQuiz(); // auto-submit
            }
        });

        quizTimer.start();
    }

    private void stopTimer() {
        if (quizTimer != null) quizTimer.stop();
    }

    private void incrementAttempt() {
        int used = attemptCountByUser.getOrDefault(username, 0);
        used++;
        attemptCountByUser.put(username, used);
        updateAttemptText();
    }

    private void submitQuiz() {
        stopTimer();

        // For now just show a message. Later we compute score + summary panel.
        JOptionPane.showMessageDialog(this, "Quiz submitted!");

        enableQuizTabs(false);
        goToTab(0);
    }
    
    

    private void hideTabbedPaneHeader() {
        jTabbedPane1.setUI(new BasicTabbedPaneUI() {
            @Override
            protected int calculateTabAreaHeight(int tabPlacement, int horizRunCount, int maxTabHeight) {
                return 0; // hide tabs
            }

            @Override
            protected int calculateTabAreaWidth(int tabPlacement, int vertRunCount, int maxTabWidth) {
                return 0; // hide tabs (left/right placement safety)
            }
        });
    }
    
    private void lockToTab(int index) {
        for (int i = 0; i < jTabbedPane1.getTabCount(); i++) {
            jTabbedPane1.setEnabledAt(i, i == index);
        }
        jTabbedPane1.setSelectedIndex(index);
    }

    private void goNextLocked() {
        int i = jTabbedPane1.getSelectedIndex();
        if (i < jTabbedPane1.getTabCount() - 1) {
            lockToTab(i + 1);
        }
    }

    private void makeQuestionResponsive(JPanel panel, JLabel question, JComponent[] choices, JButton nextBtn) {
        panel.removeAll();
        panel.setLayout(new BorderLayout());

        JPanel content = new JPanel();
        content.setOpaque(false);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));

        question.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Make long questions wrap using HTML (responsive width)
        question.setText("<html><div style='width:100%'>" + question.getText() + "</div></html>");

        content.add(question);
        content.add(Box.createVerticalStrut(15));

        for (JComponent c : choices) {
            c.setAlignmentX(Component.LEFT_ALIGNMENT);
            content.add(c);
            content.add(Box.createVerticalStrut(10));
        }

        JScrollPane scroll = new JScrollPane(content);
        scroll.setBorder(null);
        scroll.getViewport().setOpaque(false);
        scroll.setOpaque(false);

        JPanel bottom = new JPanel();
        bottom.setOpaque(false);
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
        bottom.setBorder(BorderFactory.createEmptyBorder(10, 0, 30, 0));

        nextBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        nextBtn.setMaximumSize(new Dimension(180, 55));
        bottom.add(nextBtn);

        panel.add(scroll, BorderLayout.CENTER);
        panel.add(bottom, BorderLayout.SOUTH);

        panel.revalidate();
        panel.repaint();
    }
    
    private boolean isCheckboxCorrect(JCheckBox[] boxes, boolean[] shouldBeSelected) {
        for (int i = 0; i < boxes.length; i++) {
            if (boxes[i].isSelected() != shouldBeSelected[i]) return false;
        }
        return true;
    }

    private boolean isRadioCorrect(JRadioButton correct) {
        return correct.isSelected();
    }
    
    
    private boolean isExactChecked(javax.swing.JCheckBox box, boolean shouldBeChecked) {
        return box.isSelected() == shouldBeChecked;
    }

    private int gradeQuiz() {
        // Q1 (checkbox)
        answersCorrect[0] =
                isExactChecked(cbxphysicsC, true) &&
                isExactChecked(cbxchemC, true) &&
                isExactChecked(cbxbioC, true) &&
                isExactChecked(cbxastrology, false);

        // Q2
        answersCorrect[1] = rdbCC.isSelected();

        // Q3
        answersCorrect[2] = rdbC3C.isSelected();

        // Q4 (checkbox)
        answersCorrect[3] =
                isExactChecked(cbxA4, false) &&
                isExactChecked(cbxB4C, true) &&
                isExactChecked(cbxC4C, true) &&
                isExactChecked(cbxD4C, true);

        // Q5
        answersCorrect[4] = rdbD5C.isSelected();

        // Q6
        answersCorrect[5] = rdbB6C.isSelected();

        // Q7
        answersCorrect[6] = rdbD7C.isSelected();

        // Q8
        answersCorrect[7] = rdbC8C.isSelected();

        // Q9
        answersCorrect[8] = rdbA9C.isSelected();

        // Q10
        answersCorrect[9] = rdbB10C.isSelected();

        // Q11
        answersCorrect[10] = rdbD11C.isSelected();

        // Q12 (Proton)
        answersCorrect[11] = rdbA12C.isSelected();

        // Q13 (checkbox)
        answersCorrect[12] =
                isExactChecked(cbxA13, false) &&
                isExactChecked(cbxB13C, true) &&
                isExactChecked(cbxC13, false) &&
                isExactChecked(cbxD13C, true);

        // Q14
        answersCorrect[13] = rdbA14C.isSelected();

        // Q15
        answersCorrect[14] = rdbC15C.isSelected();

        // Q16
        answersCorrect[15] = rdbA16C.isSelected();

        // Q17
        answersCorrect[16] = rdbC17C.isSelected();

        int score = 0;
        for (boolean b : answersCorrect) if (b) score++;
        return score;
    }

    private void setRemarkAndRecommendation(int score) {
        double percent = (score * 100.0) / TOTAL_QUESTIONS;

        if (percent >= 90) {
            lblRemarkResult.setText("Remarks: Excellent!");
            lblRecommendation.setText("<html>Recommendation:<br>Keep it up. Try harder science quizzes next.</html>");
        } else if (percent >= 75) {
            lblRemarkResult.setText("Remarks: Very Good!");
            lblRecommendation.setText("<html>Recommendation:<br>Review a few topics you missed and retry.</html>");
        } else if (percent >= 60) {
            lblRemarkResult.setText("Remarks: Good");
            lblRecommendation.setText("<html>Recommendation:<br>Practice more on weak areas (Physics/Chem/Bio).</html>");
        } else {
            lblRemarkResult.setText("Remarks: Needs Improvement");
            lblRecommendation.setText("<html>Recommendation:<br>Re-read notes and retake the quiz to improve.</html>");
        }
    }
    
    private String getQuizDuration() {
            int total = 10 * 60;
            int used = total - secondsLeft;

            int min = used / 60;
            int sec = used % 60;

            return String.format("%02d:%02d mins", min, sec);
    }


    private void showResultsTab() {
        int score = gradeQuiz();
        int wrong = TOTAL_QUESTIONS - score;

        // score
        lblScoreResult.setText("Result: " + score + " / " + TOTAL_QUESTIONS);

        // attempt label (optional)
        int used = attemptCountByUser.getOrDefault(username, 0);
        lblAttemptMsg.setText(
            "Quiz Summary (Attempt #" + used + " - Duration: " + getQuizDuration() + ")"
        );


        // build Correct/Wrong question list
        StringBuilder correctQs = new StringBuilder();
        StringBuilder wrongQs = new StringBuilder();

        for (int i = 0; i < TOTAL_QUESTIONS; i++) {
            if (answersCorrect[i]) correctQs.append("Q").append(i + 1).append(", ");
            else wrongQs.append("Q").append(i + 1).append(", ");
        }

        String correctText = (correctQs.length() > 0)
                ? correctQs.substring(0, correctQs.length() - 2)
                : "None";

        String wrongText = (wrongQs.length() > 0)
                ? wrongQs.substring(0, wrongQs.length() - 2)
                : "None";

        lblCorrectQuestions.setText("<html><b>Correct Questions:</b><br>" + correctText + "</html>");
        lblWrongQuestions.setText("<html><b>Wrong Questions:</b><br>" + wrongText + "</html>");
        
        lblRecommendation.setText(toWrapHtml(lblRecommendation.getText(), 520));
        lblCorrectQuestions.setText(toWrapHtml(lblCorrectQuestions.getText(), 900));
        lblWrongQuestions.setText(toWrapHtml(lblWrongQuestions.getText(), 900));

        pnlResult.revalidate();
        pnlResult.repaint();


        // remarks + recommendation
        setRemarkAndRecommendation(score);

        // stop timer + show only result
        stopTimer();
        lockToTab(getResultTabIndex());
    }
    
    private void setupButtonGroups() {
        bgQ2.add(rdbA); bgQ2.add(rdbB); bgQ2.add(rdbCC); bgQ2.add(rdbD);

        bgQ3.add(rdbA3); bgQ3.add(rdbB3); bgQ3.add(rdbC3C); bgQ3.add(rdbD3);

        bgQ5.add(rdbA5); bgQ5.add(rdbB5); bgQ5.add(rdbC5); bgQ5.add(rdbD5C);

        bgQ6.add(rdbA6); bgQ6.add(rdbB6C); bgQ6.add(rdbC6); bgQ6.add(rdbD6);

        bgQ7.add(rdbA7); bgQ7.add(rdbB7); bgQ7.add(rdbC7); bgQ7.add(rdbD7C);

        bgQ8.add(rdbA8); bgQ8.add(rdbB8); bgQ8.add(rdbC8C); bgQ8.add(rdbD8);

        bgQ9.add(rdbA9C); bgQ9.add(rdbB9); bgQ9.add(rdbC9); bgQ9.add(rdbD9);

        bgQ10.add(rdbA10); bgQ10.add(rdbB10C); bgQ10.add(rdbC10); bgQ10.add(rdbD10);

        bgQ11.add(rdbA11); bgQ11.add(rdbB11); bgQ11.add(rdbC11); bgQ11.add(rdbD11C);

        bgQ12.add(rdbA12); bgQ12.add(rdbA12C); bgQ12.add(rdbC12); bgQ12.add(rdbD12);

        bgQ14.add(rdbA14C); bgQ14.add(rdbB14); bgQ14.add(rdbC14); bgQ14.add(rdbD14);

        bgQ15.add(rdbA15); bgQ15.add(rdbB15); bgQ15.add(rdbC15C); bgQ15.add(rdbD15);

        bgQ16.add(rdbA16C); bgQ16.add(rdbB16); bgQ16.add(rdbC16); bgQ16.add(rdbD16);

        bgQ17.add(rdbA17); bgQ17.add(rdbB17); bgQ17.add(rdbC17C); bgQ17.add(rdbD17);
    }
    
    private void clearAllAnswers() {
        // Checkboxes
        cbxphysicsC.setSelected(false);
        cbxchemC.setSelected(false);
        cbxbioC.setSelected(false);
        cbxastrology.setSelected(false);

        cbxA4.setSelected(false);
        cbxB4C.setSelected(false);
        cbxC4C.setSelected(false);
        cbxD4C.setSelected(false);

        cbxA13.setSelected(false);
        cbxB13C.setSelected(false);
        cbxC13.setSelected(false);
        cbxD13C.setSelected(false);

        // Radios (clear groups the correct way)
        bgQ2.clearSelection();
        bgQ3.clearSelection();
        bgQ5.clearSelection();
        bgQ6.clearSelection();
        bgQ7.clearSelection();
        bgQ8.clearSelection();
        bgQ9.clearSelection();
        bgQ10.clearSelection();
        bgQ11.clearSelection();
        bgQ12.clearSelection();
        bgQ14.clearSelection();
        bgQ15.clearSelection();
        bgQ16.clearSelection();
        bgQ17.clearSelection();

        // Optional: refresh UI
        jTabbedPane1.revalidate();
        jTabbedPane1.repaint();

    }

    private void makeResultTabResponsive() {
        
        Color bg = pnlResult.getBackground(); // <-- the color you set in Properties
        pnlResult.removeAll();
        pnlResult.setOpaque(true);
        pnlResult.setLayout(new BorderLayout());

        // Main wrapper (vertical)
        JPanel wrapper = new JPanel();
        wrapper.setOpaque(false);
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));

        // ----- TOP ROW: left info + right recommendation -----
        JPanel topRow = new JPanel();
        topRow.setOpaque(false);
        topRow.setLayout(new BoxLayout(topRow, BoxLayout.X_AXIS));
        topRow.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Left column
        JPanel leftCol = new JPanel();
        leftCol.setOpaque(false);
        leftCol.setLayout(new BoxLayout(leftCol, BoxLayout.Y_AXIS));
        leftCol.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblScoreResult.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblRemarkResult.setAlignmentX(Component.LEFT_ALIGNMENT);

        leftCol.add(lblScoreResult);
        leftCol.add(Box.createVerticalStrut(10));
        leftCol.add(lblRemarkResult);

        // Right column (Recommendation)
        JPanel rightCol = new JPanel();
        rightCol.setOpaque(false);
        rightCol.setLayout(new BoxLayout(rightCol, BoxLayout.Y_AXIS));
        rightCol.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Wrap recommendation (important)
        lblRecommendation.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblRecommendation.setText(toWrapHtml(lblRecommendation.getText(), 520));

        rightCol.add(lblRecommendation);

        topRow.add(leftCol);
        topRow.add(Box.createHorizontalGlue());      // pushes recommendation to the right
        topRow.add(Box.createHorizontalStrut(50));
        topRow.add(rightCol);

        // ----- SUMMARY SECTION -----
        JPanel summary = new JPanel();
        summary.setOpaque(false);
        summary.setLayout(new BoxLayout(summary, BoxLayout.Y_AXIS));
        summary.setAlignmentX(Component.LEFT_ALIGNMENT);
        summary.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

        lblAttemptMsg.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblCorrectQuestions.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblWrongQuestions.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Wrap long lists so they don't overlap
        lblCorrectQuestions.setText(toWrapHtml(lblCorrectQuestions.getText(), 900));
        lblWrongQuestions.setText(toWrapHtml(lblWrongQuestions.getText(), 900));

        summary.add(lblAttemptMsg);
        summary.add(Box.createVerticalStrut(15));
        summary.add(lblCorrectQuestions);
        summary.add(Box.createVerticalStrut(15));
        summary.add(lblWrongQuestions);

        // ----- BUTTONS -----
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 0));
        buttons.setOpaque(false);
        buttons.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttons.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));

        btnRetake.setPreferredSize(new Dimension(200, 60));
        btnBack1.setPreferredSize(new Dimension(200, 60));

        buttons.add(btnRetake);
        buttons.add(btnBack1);

        // Put together
        wrapper.add(topRow);
        wrapper.add(summary);
        wrapper.add(buttons);

        pnlResult.add(wrapper, BorderLayout.CENTER);

        pnlResult.revalidate();
        pnlResult.repaint();
    }
    
    private String toWrapHtml(String text, int widthPx) {
        if (text == null) return "";
        // If it already contains html, keep it but enforce a width
        String clean = text.replaceAll("(?i)</?html>", "");
        return "<html><div style='width:" + widthPx + "px;'>" + clean + "</div></html>";
    }
    
    private void applySameBackground(JComponent comp, Color bg) {
        comp.setBackground(bg);
        comp.setOpaque(true);
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
        lblTimer = new javax.swing.JLabel();
        lblUser = new javax.swing.JLabel();
        lblRole = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlStart = new javax.swing.JPanel();
        lblAttemptDesc = new javax.swing.JLabel();
        lblDescription = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        btnTakeQuiz = new javax.swing.JButton();
        pnlq1 = new javax.swing.JPanel();
        btnNext = new javax.swing.JButton();
        lblquestion = new javax.swing.JLabel();
        cbxphysicsC = new javax.swing.JCheckBox();
        cbxchemC = new javax.swing.JCheckBox();
        cbxbioC = new javax.swing.JCheckBox();
        cbxastrology = new javax.swing.JCheckBox();
        pnlq2 = new javax.swing.JPanel();
        lblquestion1 = new javax.swing.JLabel();
        rdbA = new javax.swing.JRadioButton();
        rdbB = new javax.swing.JRadioButton();
        rdbCC = new javax.swing.JRadioButton();
        rdbD = new javax.swing.JRadioButton();
        btnNext1 = new javax.swing.JButton();
        pnlq3 = new javax.swing.JPanel();
        lblquestion2 = new javax.swing.JLabel();
        rdbA3 = new javax.swing.JRadioButton();
        rdbB3 = new javax.swing.JRadioButton();
        rdbC3C = new javax.swing.JRadioButton();
        rdbD3 = new javax.swing.JRadioButton();
        btnNext2 = new javax.swing.JButton();
        pnlq4 = new javax.swing.JPanel();
        btnNext3 = new javax.swing.JButton();
        lblquestion3 = new javax.swing.JLabel();
        cbxA4 = new javax.swing.JCheckBox();
        cbxB4C = new javax.swing.JCheckBox();
        cbxD4C = new javax.swing.JCheckBox();
        cbxC4C = new javax.swing.JCheckBox();
        pnlq5 = new javax.swing.JPanel();
        rdbA5 = new javax.swing.JRadioButton();
        lblquestion4 = new javax.swing.JLabel();
        rdbB5 = new javax.swing.JRadioButton();
        rdbC5 = new javax.swing.JRadioButton();
        rdbD5C = new javax.swing.JRadioButton();
        btnNext4 = new javax.swing.JButton();
        pnlq6 = new javax.swing.JPanel();
        lblquestion5 = new javax.swing.JLabel();
        rdbA6 = new javax.swing.JRadioButton();
        rdbB6C = new javax.swing.JRadioButton();
        rdbC6 = new javax.swing.JRadioButton();
        rdbD6 = new javax.swing.JRadioButton();
        btnNext5 = new javax.swing.JButton();
        pnlq7 = new javax.swing.JPanel();
        lblquestion6 = new javax.swing.JLabel();
        rdbA7 = new javax.swing.JRadioButton();
        rdbB7 = new javax.swing.JRadioButton();
        rdbC7 = new javax.swing.JRadioButton();
        rdbD7C = new javax.swing.JRadioButton();
        btnNext6 = new javax.swing.JButton();
        pnlq8 = new javax.swing.JPanel();
        lblquestion7 = new javax.swing.JLabel();
        rdbA8 = new javax.swing.JRadioButton();
        rdbB8 = new javax.swing.JRadioButton();
        rdbC8C = new javax.swing.JRadioButton();
        rdbD8 = new javax.swing.JRadioButton();
        btnNext7 = new javax.swing.JButton();
        pnlq9 = new javax.swing.JPanel();
        lblquestion8 = new javax.swing.JLabel();
        rdbA9C = new javax.swing.JRadioButton();
        rdbB9 = new javax.swing.JRadioButton();
        rdbC9 = new javax.swing.JRadioButton();
        rdbD9 = new javax.swing.JRadioButton();
        btnNext8 = new javax.swing.JButton();
        pnlq10 = new javax.swing.JPanel();
        lblquestion9 = new javax.swing.JLabel();
        rdbA10 = new javax.swing.JRadioButton();
        rdbB10C = new javax.swing.JRadioButton();
        rdbC10 = new javax.swing.JRadioButton();
        rdbD10 = new javax.swing.JRadioButton();
        btnNext9 = new javax.swing.JButton();
        pnlq11 = new javax.swing.JPanel();
        lblquestion10 = new javax.swing.JLabel();
        rdbA11 = new javax.swing.JRadioButton();
        rdbB11 = new javax.swing.JRadioButton();
        rdbC11 = new javax.swing.JRadioButton();
        rdbD11C = new javax.swing.JRadioButton();
        btnNext10 = new javax.swing.JButton();
        pnlq12 = new javax.swing.JPanel();
        lblquestion11 = new javax.swing.JLabel();
        rdbA12 = new javax.swing.JRadioButton();
        rdbA12C = new javax.swing.JRadioButton();
        rdbC12 = new javax.swing.JRadioButton();
        rdbD12 = new javax.swing.JRadioButton();
        btnNext11 = new javax.swing.JButton();
        pnlq13 = new javax.swing.JPanel();
        lblquestion12 = new javax.swing.JLabel();
        cbxA13 = new javax.swing.JCheckBox();
        cbxB13C = new javax.swing.JCheckBox();
        cbxC13 = new javax.swing.JCheckBox();
        cbxD13C = new javax.swing.JCheckBox();
        btnNext12 = new javax.swing.JButton();
        pnlq14 = new javax.swing.JPanel();
        lblquestion13 = new javax.swing.JLabel();
        rdbA14C = new javax.swing.JRadioButton();
        rdbB14 = new javax.swing.JRadioButton();
        rdbC14 = new javax.swing.JRadioButton();
        rdbD14 = new javax.swing.JRadioButton();
        btnNext13 = new javax.swing.JButton();
        pnlq15 = new javax.swing.JPanel();
        lblquestion14 = new javax.swing.JLabel();
        rdbA15 = new javax.swing.JRadioButton();
        rdbB15 = new javax.swing.JRadioButton();
        rdbC15C = new javax.swing.JRadioButton();
        rdbD15 = new javax.swing.JRadioButton();
        btnNext14 = new javax.swing.JButton();
        pnlq16 = new javax.swing.JPanel();
        lblquestion15 = new javax.swing.JLabel();
        rdbA16C = new javax.swing.JRadioButton();
        rdbB16 = new javax.swing.JRadioButton();
        rdbC16 = new javax.swing.JRadioButton();
        rdbD16 = new javax.swing.JRadioButton();
        btnNext15 = new javax.swing.JButton();
        pnlq17 = new javax.swing.JPanel();
        lblquestion16 = new javax.swing.JLabel();
        rdbA17 = new javax.swing.JRadioButton();
        rdbB17 = new javax.swing.JRadioButton();
        rdbC17C = new javax.swing.JRadioButton();
        rdbD17 = new javax.swing.JRadioButton();
        btnSubmit = new javax.swing.JButton();
        pnlResult = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblScoreResult = new javax.swing.JLabel();
        lblRemarkResult = new javax.swing.JLabel();
        lblRecommendation = new javax.swing.JLabel();
        btnRetake = new javax.swing.JButton();
        btnBack1 = new javax.swing.JButton();
        lblCorrectQuestions = new javax.swing.JLabel();
        lblWrongQuestions = new javax.swing.JLabel();
        lblAttemptMsg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1400, 800));

        jPanel1.setBackground(new java.awt.Color(51, 0, 0));

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

        jLabel1.setFont(new java.awt.Font("Broadway", 0, 55)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SCIENCE QUIZ");

        lblTimer.setBackground(new java.awt.Color(255, 153, 153));
        lblTimer.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        lblTimer.setForeground(new java.awt.Color(255, 255, 255));
        lblTimer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTimer.setText("Timer");

        lblUser.setBackground(new java.awt.Color(255, 153, 153));
        lblUser.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        lblUser.setForeground(new java.awt.Color(255, 255, 255));
        lblUser.setText("User: ");

        lblRole.setBackground(new java.awt.Color(255, 153, 153));
        lblRole.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        lblRole.setForeground(new java.awt.Color(255, 255, 255));
        lblRole.setText("Role:");

        jTabbedPane1.setBackground(new java.awt.Color(51, 0, 0));
        jTabbedPane1.setOpaque(true);

        pnlStart.setBackground(new java.awt.Color(51, 0, 0));
        pnlStart.setForeground(new java.awt.Color(0, 0, 0));

        lblAttemptDesc.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        lblAttemptDesc.setForeground(new java.awt.Color(255, 255, 255));
        lblAttemptDesc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAttemptDesc.setText("Attempt");

        lblDescription.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        lblDescription.setForeground(new java.awt.Color(255, 255, 255));
        lblDescription.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDescription.setText("Description");

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

        btnTakeQuiz.setBackground(new java.awt.Color(204, 0, 0));
        btnTakeQuiz.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        btnTakeQuiz.setForeground(new java.awt.Color(255, 255, 255));
        btnTakeQuiz.setText("Take Quiz");
        btnTakeQuiz.setBorder(null);
        btnTakeQuiz.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnTakeQuizMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnTakeQuizMouseExited(evt);
            }
        });
        btnTakeQuiz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTakeQuizActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlStartLayout = new javax.swing.GroupLayout(pnlStart);
        pnlStart.setLayout(pnlStartLayout);
        pnlStartLayout.setHorizontalGroup(
            pnlStartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStartLayout.createSequentialGroup()
                .addGap(0, 240, Short.MAX_VALUE)
                .addComponent(lblDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 758, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(183, 183, 183))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStartLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlStartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStartLayout.createSequentialGroup()
                        .addComponent(lblAttemptDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(339, 339, 339))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStartLayout.createSequentialGroup()
                        .addGroup(pnlStartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTakeQuiz, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(470, 470, 470))))
        );
        pnlStartLayout.setVerticalGroup(
            pnlStartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStartLayout.createSequentialGroup()
                .addComponent(lblDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAttemptDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTakeQuiz, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );

        jTabbedPane1.addTab("Start", pnlStart);

        pnlq1.setBackground(new java.awt.Color(255, 204, 204));

        btnNext.setBackground(new java.awt.Color(204, 0, 0));
        btnNext.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        btnNext.setForeground(new java.awt.Color(255, 255, 255));
        btnNext.setText("Next");
        btnNext.setBorder(null);
        btnNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNextMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNextMouseExited(evt);
            }
        });
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        lblquestion.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        lblquestion.setForeground(new java.awt.Color(204, 0, 0));
        lblquestion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblquestion.setText("1. Which of the following are branches of science?");

        cbxphysicsC.setBackground(new java.awt.Color(255, 204, 204));
        cbxphysicsC.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbxphysicsC.setForeground(new java.awt.Color(204, 0, 0));
        cbxphysicsC.setText("Physics");

        cbxchemC.setBackground(new java.awt.Color(255, 204, 204));
        cbxchemC.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbxchemC.setForeground(new java.awt.Color(204, 0, 0));
        cbxchemC.setText("Chemistry");
        cbxchemC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxchemCActionPerformed(evt);
            }
        });

        cbxbioC.setBackground(new java.awt.Color(255, 204, 204));
        cbxbioC.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbxbioC.setForeground(new java.awt.Color(204, 0, 0));
        cbxbioC.setText("Biology");

        cbxastrology.setBackground(new java.awt.Color(255, 204, 204));
        cbxastrology.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbxastrology.setForeground(new java.awt.Color(204, 0, 0));
        cbxastrology.setText("Astrology");

        javax.swing.GroupLayout pnlq1Layout = new javax.swing.GroupLayout(pnlq1);
        pnlq1.setLayout(pnlq1Layout);
        pnlq1Layout.setHorizontalGroup(
            pnlq1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlq1Layout.createSequentialGroup()
                .addGroup(pnlq1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlq1Layout.createSequentialGroup()
                        .addGap(478, 478, 478)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlq1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(lblquestion, javax.swing.GroupLayout.PREFERRED_SIZE, 758, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlq1Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addGroup(pnlq1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxchemC, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxphysicsC, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxbioC, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxastrology, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(373, Short.MAX_VALUE))
        );
        pnlq1Layout.setVerticalGroup(
            pnlq1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlq1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lblquestion, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxphysicsC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxchemC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxastrology)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxbioC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        jTabbedPane1.addTab("Q1", pnlq1);

        pnlq2.setBackground(new java.awt.Color(204, 204, 204));
        pnlq2.setForeground(new java.awt.Color(204, 0, 0));

        lblquestion1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        lblquestion1.setForeground(new java.awt.Color(204, 0, 0));
        lblquestion1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblquestion1.setText("2. What is quantum entanglement? ");

        rdbA.setBackground(new java.awt.Color(204, 204, 204));
        rdbA.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbA.setForeground(new java.awt.Color(204, 0, 0));
        rdbA.setText("A. Overlapping wave functions");
        rdbA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbAActionPerformed(evt);
            }
        });

        rdbB.setBackground(new java.awt.Color(204, 204, 204));
        rdbB.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbB.setForeground(new java.awt.Color(204, 0, 0));
        rdbB.setText("B. Energy level splitting");

        rdbCC.setBackground(new java.awt.Color(204, 204, 204));
        rdbCC.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbCC.setForeground(new java.awt.Color(204, 0, 0));
        rdbCC.setText("C. Correlation between particles regardless of distance");

        rdbD.setBackground(new java.awt.Color(204, 204, 204));
        rdbD.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbD.setForeground(new java.awt.Color(204, 0, 0));
        rdbD.setText("D. Exchange of virtual particles");

        btnNext1.setBackground(new java.awt.Color(204, 0, 0));
        btnNext1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        btnNext1.setForeground(new java.awt.Color(255, 255, 255));
        btnNext1.setText("Next");
        btnNext1.setBorder(null);
        btnNext1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNext1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNext1MouseExited(evt);
            }
        });
        btnNext1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlq2Layout = new javax.swing.GroupLayout(pnlq2);
        pnlq2.setLayout(pnlq2Layout);
        pnlq2Layout.setHorizontalGroup(
            pnlq2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlq2Layout.createSequentialGroup()
                .addGroup(pnlq2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlq2Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(lblquestion1, javax.swing.GroupLayout.PREFERRED_SIZE, 758, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlq2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlq2Layout.createSequentialGroup()
                            .addGap(88, 88, 88)
                            .addGroup(pnlq2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(rdbA, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(rdbD, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(pnlq2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(rdbB, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlq2Layout.createSequentialGroup()
                        .addGap(473, 473, 473)
                        .addComponent(btnNext1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlq2Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(rdbCC, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(379, Short.MAX_VALUE))
        );
        pnlq2Layout.setVerticalGroup(
            pnlq2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlq2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblquestion1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbA)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbCC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(btnNext1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jTabbedPane1.addTab("Q2", pnlq2);

        pnlq3.setBackground(new java.awt.Color(255, 153, 153));

        lblquestion2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        lblquestion2.setForeground(new java.awt.Color(204, 0, 0));
        lblquestion2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblquestion2.setText("3. Which scientific law states that energy cannot be created nor destroyed, only transformed from one form to another? ");

        rdbA3.setBackground(new java.awt.Color(255, 153, 153));
        rdbA3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbA3.setForeground(new java.awt.Color(204, 0, 0));
        rdbA3.setText("A. Newton’s First Law of Motion");
        rdbA3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbA3ActionPerformed(evt);
            }
        });

        rdbB3.setBackground(new java.awt.Color(255, 153, 153));
        rdbB3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbB3.setForeground(new java.awt.Color(204, 0, 0));
        rdbB3.setText("B. Law of Conservation of Mass");

        rdbC3C.setBackground(new java.awt.Color(255, 153, 153));
        rdbC3C.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbC3C.setForeground(new java.awt.Color(204, 0, 0));
        rdbC3C.setText("C. Law of Conservation of Energy");

        rdbD3.setBackground(new java.awt.Color(255, 153, 153));
        rdbD3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbD3.setForeground(new java.awt.Color(204, 0, 0));
        rdbD3.setText("D. Boyle’s Law");

        btnNext2.setBackground(new java.awt.Color(204, 0, 0));
        btnNext2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        btnNext2.setForeground(new java.awt.Color(255, 255, 255));
        btnNext2.setText("Next");
        btnNext2.setBorder(null);
        btnNext2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNext2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNext2MouseExited(evt);
            }
        });
        btnNext2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlq3Layout = new javax.swing.GroupLayout(pnlq3);
        pnlq3.setLayout(pnlq3Layout);
        pnlq3Layout.setHorizontalGroup(
            pnlq3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlq3Layout.createSequentialGroup()
                .addGroup(pnlq3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlq3Layout.createSequentialGroup()
                        .addGap(473, 473, 473)
                        .addComponent(btnNext2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlq3Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(rdbC3C, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlq3Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addGroup(pnlq3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdbB3, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdbD3, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdbA3, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnlq3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblquestion2, javax.swing.GroupLayout.PREFERRED_SIZE, 1052, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(123, Short.MAX_VALUE))
        );
        pnlq3Layout.setVerticalGroup(
            pnlq3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlq3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblquestion2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rdbA3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbB3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbC3C)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbD3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(btnNext2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jTabbedPane1.addTab("Q3", pnlq3);

        pnlq4.setBackground(new java.awt.Color(255, 204, 204));

        btnNext3.setBackground(new java.awt.Color(204, 0, 0));
        btnNext3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        btnNext3.setForeground(new java.awt.Color(255, 255, 255));
        btnNext3.setText("Next");
        btnNext3.setBorder(null);
        btnNext3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNext3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNext3MouseExited(evt);
            }
        });
        btnNext3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext3ActionPerformed(evt);
            }
        });

        lblquestion3.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        lblquestion3.setForeground(new java.awt.Color(204, 0, 0));
        lblquestion3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblquestion3.setText("4. Which statements are related to Newton’s Laws of Motion? ");

        cbxA4.setBackground(new java.awt.Color(255, 204, 204));
        cbxA4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbxA4.setForeground(new java.awt.Color(204, 0, 0));
        cbxA4.setText("Energy cannot be created nor destroyed");

        cbxB4C.setBackground(new java.awt.Color(255, 204, 204));
        cbxB4C.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbxB4C.setForeground(new java.awt.Color(204, 0, 0));
        cbxB4C.setText("An object at rest stays at rest unless acted upon by an external force");
        cbxB4C.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxB4CActionPerformed(evt);
            }
        });

        cbxD4C.setBackground(new java.awt.Color(255, 204, 204));
        cbxD4C.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbxD4C.setForeground(new java.awt.Color(204, 0, 0));
        cbxD4C.setText("Every action has an equal and opposite reaction");

        cbxC4C.setBackground(new java.awt.Color(255, 204, 204));
        cbxC4C.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbxC4C.setForeground(new java.awt.Color(204, 0, 0));
        cbxC4C.setText("Force is equal to mass multiplied by acceleration");

        javax.swing.GroupLayout pnlq4Layout = new javax.swing.GroupLayout(pnlq4);
        pnlq4.setLayout(pnlq4Layout);
        pnlq4Layout.setHorizontalGroup(
            pnlq4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlq4Layout.createSequentialGroup()
                .addGroup(pnlq4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlq4Layout.createSequentialGroup()
                        .addGap(478, 478, 478)
                        .addComponent(btnNext3, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlq4Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(lblquestion3, javax.swing.GroupLayout.PREFERRED_SIZE, 758, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlq4Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addGroup(pnlq4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxA4, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxD4C, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxC4C, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxB4C, javax.swing.GroupLayout.PREFERRED_SIZE, 759, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(339, Short.MAX_VALUE))
        );
        pnlq4Layout.setVerticalGroup(
            pnlq4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlq4Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lblquestion3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxA4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxB4C)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxC4C)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxD4C)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(btnNext3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        jTabbedPane1.addTab("Q4", pnlq4);
        pnlq4.getAccessibleContext().setAccessibleName("");

        pnlq5.setBackground(new java.awt.Color(204, 204, 204));

        rdbA5.setBackground(new java.awt.Color(204, 204, 204));
        rdbA5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbA5.setForeground(new java.awt.Color(204, 0, 0));
        rdbA5.setText("A. Joule");
        rdbA5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbA5ActionPerformed(evt);
            }
        });

        lblquestion4.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        lblquestion4.setForeground(new java.awt.Color(204, 0, 0));
        lblquestion4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblquestion4.setText("5. What is the SI unit of force?");

        rdbB5.setBackground(new java.awt.Color(204, 204, 204));
        rdbB5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbB5.setForeground(new java.awt.Color(204, 0, 0));
        rdbB5.setText("B. Watt ");

        rdbC5.setBackground(new java.awt.Color(204, 204, 204));
        rdbC5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbC5.setForeground(new java.awt.Color(204, 0, 0));
        rdbC5.setText("C. Pascal");

        rdbD5C.setBackground(new java.awt.Color(204, 204, 204));
        rdbD5C.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbD5C.setForeground(new java.awt.Color(204, 0, 0));
        rdbD5C.setText("D. Newton");

        btnNext4.setBackground(new java.awt.Color(204, 0, 0));
        btnNext4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        btnNext4.setForeground(new java.awt.Color(255, 255, 255));
        btnNext4.setText("Next");
        btnNext4.setBorder(null);
        btnNext4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNext4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNext4MouseExited(evt);
            }
        });
        btnNext4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlq5Layout = new javax.swing.GroupLayout(pnlq5);
        pnlq5.setLayout(pnlq5Layout);
        pnlq5Layout.setHorizontalGroup(
            pnlq5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlq5Layout.createSequentialGroup()
                .addGroup(pnlq5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlq5Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(lblquestion4, javax.swing.GroupLayout.PREFERRED_SIZE, 758, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlq5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlq5Layout.createSequentialGroup()
                            .addGap(88, 88, 88)
                            .addGroup(pnlq5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(rdbA5, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(rdbD5C, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(pnlq5Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(rdbB5, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlq5Layout.createSequentialGroup()
                        .addGap(473, 473, 473)
                        .addComponent(btnNext4, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlq5Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(rdbC5, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(379, Short.MAX_VALUE))
        );
        pnlq5Layout.setVerticalGroup(
            pnlq5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlq5Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblquestion4, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbA5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbB5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbC5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbD5C)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(btnNext4, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jTabbedPane1.addTab("Q5", pnlq5);

        pnlq6.setBackground(new java.awt.Color(255, 153, 153));

        lblquestion5.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        lblquestion5.setForeground(new java.awt.Color(204, 0, 0));
        lblquestion5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblquestion5.setText("6. Which quantity remains constant when no external force acts on a system? ");

        rdbA6.setBackground(new java.awt.Color(255, 153, 153));
        rdbA6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbA6.setForeground(new java.awt.Color(204, 0, 0));
        rdbA6.setText("A. Acceleration");
        rdbA6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbA6ActionPerformed(evt);
            }
        });

        rdbB6C.setBackground(new java.awt.Color(255, 153, 153));
        rdbB6C.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbB6C.setForeground(new java.awt.Color(204, 0, 0));
        rdbB6C.setText("B. Momentum");

        rdbC6.setBackground(new java.awt.Color(255, 153, 153));
        rdbC6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbC6.setForeground(new java.awt.Color(204, 0, 0));
        rdbC6.setText("C. Kinetic energy");

        rdbD6.setBackground(new java.awt.Color(255, 153, 153));
        rdbD6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbD6.setForeground(new java.awt.Color(204, 0, 0));
        rdbD6.setText("D. Velocity");

        btnNext5.setBackground(new java.awt.Color(204, 0, 0));
        btnNext5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        btnNext5.setForeground(new java.awt.Color(255, 255, 255));
        btnNext5.setText("Next");
        btnNext5.setBorder(null);
        btnNext5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNext5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNext5MouseExited(evt);
            }
        });
        btnNext5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlq6Layout = new javax.swing.GroupLayout(pnlq6);
        pnlq6.setLayout(pnlq6Layout);
        pnlq6Layout.setHorizontalGroup(
            pnlq6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlq6Layout.createSequentialGroup()
                .addGroup(pnlq6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlq6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlq6Layout.createSequentialGroup()
                            .addGap(88, 88, 88)
                            .addGroup(pnlq6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(rdbA6, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(rdbD6, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(pnlq6Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(rdbB6C, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlq6Layout.createSequentialGroup()
                        .addGap(473, 473, 473)
                        .addComponent(btnNext5, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlq6Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(rdbC6, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlq6Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(lblquestion5, javax.swing.GroupLayout.PREFERRED_SIZE, 976, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(161, Short.MAX_VALUE))
        );
        pnlq6Layout.setVerticalGroup(
            pnlq6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlq6Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblquestion5, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbA6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbB6C)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbC6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbD6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(btnNext5, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jTabbedPane1.addTab("Q6", pnlq6);

        pnlq7.setBackground(new java.awt.Color(255, 204, 204));

        lblquestion6.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        lblquestion6.setForeground(new java.awt.Color(204, 0, 0));
        lblquestion6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblquestion6.setText("7. In an expanding universe, redshift primarily results from:");

        rdbA7.setBackground(new java.awt.Color(255, 204, 204));
        rdbA7.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbA7.setForeground(new java.awt.Color(204, 0, 0));
        rdbA7.setText("A. Energy loss of photons in interstellar space");
        rdbA7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbA7ActionPerformed(evt);
            }
        });

        rdbB7.setBackground(new java.awt.Color(255, 204, 204));
        rdbB7.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbB7.setForeground(new java.awt.Color(204, 0, 0));
        rdbB7.setText("B. Gravitational attraction between galaxies");

        rdbC7.setBackground(new java.awt.Color(255, 204, 204));
        rdbC7.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbC7.setForeground(new java.awt.Color(204, 0, 0));
        rdbC7.setText("C. Motion through a fixed background medium");

        rdbD7C.setBackground(new java.awt.Color(255, 204, 204));
        rdbD7C.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbD7C.setForeground(new java.awt.Color(204, 0, 0));
        rdbD7C.setText("D. Expansion of space stretching light waves");

        btnNext6.setBackground(new java.awt.Color(204, 0, 0));
        btnNext6.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        btnNext6.setForeground(new java.awt.Color(255, 255, 255));
        btnNext6.setText("Next");
        btnNext6.setBorder(null);
        btnNext6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNext6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNext6MouseExited(evt);
            }
        });
        btnNext6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlq7Layout = new javax.swing.GroupLayout(pnlq7);
        pnlq7.setLayout(pnlq7Layout);
        pnlq7Layout.setHorizontalGroup(
            pnlq7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlq7Layout.createSequentialGroup()
                .addGroup(pnlq7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlq7Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(lblquestion6, javax.swing.GroupLayout.PREFERRED_SIZE, 758, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlq7Layout.createSequentialGroup()
                        .addGap(473, 473, 473)
                        .addComponent(btnNext6, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlq7Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(rdbC7, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlq7Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addGroup(pnlq7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdbA7, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdbB7, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdbD7C, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(323, 323, 323))
        );
        pnlq7Layout.setVerticalGroup(
            pnlq7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlq7Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblquestion6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbA7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbB7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbC7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbD7C)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(btnNext6, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jTabbedPane1.addTab("Q7", pnlq7);

        pnlq8.setBackground(new java.awt.Color(204, 204, 204));

        lblquestion7.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        lblquestion7.setForeground(new java.awt.Color(204, 0, 0));
        lblquestion7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblquestion7.setText("8. Which principle explains why no object with mass can reach the speed of light?");

        rdbA8.setBackground(new java.awt.Color(204, 204, 204));
        rdbA8.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbA8.setForeground(new java.awt.Color(204, 0, 0));
        rdbA8.setText("A. Newton’s Second Law");
        rdbA8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbA8ActionPerformed(evt);
            }
        });

        rdbB8.setBackground(new java.awt.Color(204, 204, 204));
        rdbB8.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbB8.setForeground(new java.awt.Color(204, 0, 0));
        rdbB8.setText("B. Conservation of Momentum");
        rdbB8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbB8ActionPerformed(evt);
            }
        });

        rdbC8C.setBackground(new java.awt.Color(204, 204, 204));
        rdbC8C.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbC8C.setForeground(new java.awt.Color(204, 0, 0));
        rdbC8C.setText("C. Special Theory of Relativity ");

        rdbD8.setBackground(new java.awt.Color(204, 204, 204));
        rdbD8.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbD8.setForeground(new java.awt.Color(204, 0, 0));
        rdbD8.setText("D. Heisenberg Uncertainty Principle");

        btnNext7.setBackground(new java.awt.Color(204, 0, 0));
        btnNext7.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        btnNext7.setForeground(new java.awt.Color(255, 255, 255));
        btnNext7.setText("Next");
        btnNext7.setBorder(null);
        btnNext7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNext7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNext7MouseExited(evt);
            }
        });
        btnNext7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlq8Layout = new javax.swing.GroupLayout(pnlq8);
        pnlq8.setLayout(pnlq8Layout);
        pnlq8Layout.setHorizontalGroup(
            pnlq8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlq8Layout.createSequentialGroup()
                .addGroup(pnlq8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlq8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlq8Layout.createSequentialGroup()
                            .addGap(88, 88, 88)
                            .addComponent(rdbA8, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlq8Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(rdbB8, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlq8Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(rdbC8C, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlq8Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(lblquestion7, javax.swing.GroupLayout.PREFERRED_SIZE, 936, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlq8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(pnlq8Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(rdbD8, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlq8Layout.createSequentialGroup()
                            .addGap(473, 473, 473)
                            .addComponent(btnNext7, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(201, Short.MAX_VALUE))
        );
        pnlq8Layout.setVerticalGroup(
            pnlq8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlq8Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblquestion7, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbA8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbB8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbC8C)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbD8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(btnNext7, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jTabbedPane1.addTab("Q8", pnlq8);

        pnlq9.setBackground(new java.awt.Color(255, 153, 153));

        lblquestion8.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        lblquestion8.setForeground(new java.awt.Color(204, 0, 0));
        lblquestion8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblquestion8.setText("9. Which organelle is primarily responsible for ATP production in eukaryotic cells? ");

        rdbA9C.setBackground(new java.awt.Color(255, 153, 153));
        rdbA9C.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbA9C.setForeground(new java.awt.Color(204, 0, 0));
        rdbA9C.setText("A. Mitochondrion ");
        rdbA9C.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbA9CActionPerformed(evt);
            }
        });

        rdbB9.setBackground(new java.awt.Color(255, 153, 153));
        rdbB9.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbB9.setForeground(new java.awt.Color(204, 0, 0));
        rdbB9.setText("B. Endoplasmic reticulum");

        rdbC9.setBackground(new java.awt.Color(255, 153, 153));
        rdbC9.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbC9.setForeground(new java.awt.Color(204, 0, 0));
        rdbC9.setText("C. Golgi apparatus ");

        rdbD9.setBackground(new java.awt.Color(255, 153, 153));
        rdbD9.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbD9.setForeground(new java.awt.Color(204, 0, 0));
        rdbD9.setText("D. Ribosome ");

        btnNext8.setBackground(new java.awt.Color(204, 0, 0));
        btnNext8.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        btnNext8.setForeground(new java.awt.Color(255, 255, 255));
        btnNext8.setText("Next");
        btnNext8.setBorder(null);
        btnNext8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNext8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNext8MouseExited(evt);
            }
        });
        btnNext8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlq9Layout = new javax.swing.GroupLayout(pnlq9);
        pnlq9.setLayout(pnlq9Layout);
        pnlq9Layout.setHorizontalGroup(
            pnlq9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlq9Layout.createSequentialGroup()
                .addGroup(pnlq9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlq9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlq9Layout.createSequentialGroup()
                            .addGap(88, 88, 88)
                            .addGroup(pnlq9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(rdbA9C, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(rdbD9, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(pnlq9Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(rdbB9, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlq9Layout.createSequentialGroup()
                        .addGap(473, 473, 473)
                        .addComponent(btnNext8, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlq9Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(rdbC9, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlq9Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(lblquestion8)))
                .addContainerGap(240, Short.MAX_VALUE))
        );
        pnlq9Layout.setVerticalGroup(
            pnlq9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlq9Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblquestion8, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbA9C)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbB9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbC9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbD9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(btnNext8, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jTabbedPane1.addTab("Q9", pnlq9);

        pnlq10.setBackground(new java.awt.Color(255, 204, 204));

        lblquestion9.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        lblquestion9.setForeground(new java.awt.Color(204, 0, 0));
        lblquestion9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblquestion9.setText("10. Which celestial object defines the boundary of a black hole? ");

        rdbA10.setBackground(new java.awt.Color(255, 204, 204));
        rdbA10.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbA10.setForeground(new java.awt.Color(204, 0, 0));
        rdbA10.setText("A. Singularity ");
        rdbA10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbA10ActionPerformed(evt);
            }
        });

        rdbB10C.setBackground(new java.awt.Color(255, 204, 204));
        rdbB10C.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbB10C.setForeground(new java.awt.Color(204, 0, 0));
        rdbB10C.setText("B. Event horizon ");

        rdbC10.setBackground(new java.awt.Color(255, 204, 204));
        rdbC10.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbC10.setForeground(new java.awt.Color(204, 0, 0));
        rdbC10.setText("C. Photon sphere ");

        rdbD10.setBackground(new java.awt.Color(255, 204, 204));
        rdbD10.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbD10.setForeground(new java.awt.Color(204, 0, 0));
        rdbD10.setText("D. Accretion Disk ");

        btnNext9.setBackground(new java.awt.Color(204, 0, 0));
        btnNext9.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        btnNext9.setForeground(new java.awt.Color(255, 255, 255));
        btnNext9.setText("Next");
        btnNext9.setBorder(null);
        btnNext9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNext9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNext9MouseExited(evt);
            }
        });
        btnNext9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlq10Layout = new javax.swing.GroupLayout(pnlq10);
        pnlq10.setLayout(pnlq10Layout);
        pnlq10Layout.setHorizontalGroup(
            pnlq10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlq10Layout.createSequentialGroup()
                .addGroup(pnlq10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlq10Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(lblquestion9, javax.swing.GroupLayout.PREFERRED_SIZE, 758, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlq10Layout.createSequentialGroup()
                        .addGap(473, 473, 473)
                        .addComponent(btnNext9, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlq10Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(rdbC10, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlq10Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addGroup(pnlq10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdbA10, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdbB10C, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdbD10, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(323, 323, 323))
        );
        pnlq10Layout.setVerticalGroup(
            pnlq10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlq10Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblquestion9, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbA10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbB10C)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbC10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbD10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(btnNext9, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jTabbedPane1.addTab("Q10", pnlq10);

        pnlq11.setBackground(new java.awt.Color(204, 204, 204));

        lblquestion10.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        lblquestion10.setForeground(new java.awt.Color(204, 0, 0));
        lblquestion10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblquestion10.setText("11. Which planet has the highest average surface temperature in the Solar System? ");

        rdbA11.setBackground(new java.awt.Color(204, 204, 204));
        rdbA11.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbA11.setForeground(new java.awt.Color(204, 0, 0));
        rdbA11.setText("A. Mercury");
        rdbA11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbA11ActionPerformed(evt);
            }
        });

        rdbB11.setBackground(new java.awt.Color(204, 204, 204));
        rdbB11.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbB11.setForeground(new java.awt.Color(204, 0, 0));
        rdbB11.setText("B. Saturn");
        rdbB11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbB11ActionPerformed(evt);
            }
        });

        rdbC11.setBackground(new java.awt.Color(204, 204, 204));
        rdbC11.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbC11.setForeground(new java.awt.Color(204, 0, 0));
        rdbC11.setText("C. Neptune");

        rdbD11C.setBackground(new java.awt.Color(204, 204, 204));
        rdbD11C.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbD11C.setForeground(new java.awt.Color(204, 0, 0));
        rdbD11C.setText("D. Venus");

        btnNext10.setBackground(new java.awt.Color(204, 0, 0));
        btnNext10.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        btnNext10.setForeground(new java.awt.Color(255, 255, 255));
        btnNext10.setText("Next");
        btnNext10.setBorder(null);
        btnNext10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNext10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNext10MouseExited(evt);
            }
        });
        btnNext10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlq11Layout = new javax.swing.GroupLayout(pnlq11);
        pnlq11.setLayout(pnlq11Layout);
        pnlq11Layout.setHorizontalGroup(
            pnlq11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlq11Layout.createSequentialGroup()
                .addGroup(pnlq11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlq11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlq11Layout.createSequentialGroup()
                            .addGap(88, 88, 88)
                            .addGroup(pnlq11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(rdbA11, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(rdbD11C, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(pnlq11Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(rdbB11, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlq11Layout.createSequentialGroup()
                        .addGap(473, 473, 473)
                        .addComponent(btnNext10, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlq11Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(rdbC11, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlq11Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(lblquestion10, javax.swing.GroupLayout.PREFERRED_SIZE, 936, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(201, Short.MAX_VALUE))
        );
        pnlq11Layout.setVerticalGroup(
            pnlq11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlq11Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblquestion10, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbA11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbB11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbC11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbD11C)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(btnNext10, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jTabbedPane1.addTab("Q11", pnlq11);

        pnlq12.setBackground(new java.awt.Color(255, 153, 153));

        lblquestion11.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        lblquestion11.setForeground(new java.awt.Color(204, 0, 0));
        lblquestion11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblquestion11.setText("12. Which subatomic particle determines the identity of an element? ");

        rdbA12.setBackground(new java.awt.Color(255, 153, 153));
        rdbA12.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbA12.setForeground(new java.awt.Color(204, 0, 0));
        rdbA12.setText("A. Electron ");
        rdbA12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbA12ActionPerformed(evt);
            }
        });

        rdbA12C.setBackground(new java.awt.Color(255, 153, 153));
        rdbA12C.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbA12C.setForeground(new java.awt.Color(204, 0, 0));
        rdbA12C.setText("B. Proton ");

        rdbC12.setBackground(new java.awt.Color(255, 153, 153));
        rdbC12.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbC12.setForeground(new java.awt.Color(204, 0, 0));
        rdbC12.setText("C. Neutron");

        rdbD12.setBackground(new java.awt.Color(255, 153, 153));
        rdbD12.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbD12.setForeground(new java.awt.Color(204, 0, 0));
        rdbD12.setText("D. Positron ");

        btnNext11.setBackground(new java.awt.Color(204, 0, 0));
        btnNext11.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        btnNext11.setForeground(new java.awt.Color(255, 255, 255));
        btnNext11.setText("Next");
        btnNext11.setBorder(null);
        btnNext11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNext11MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNext11MouseExited(evt);
            }
        });
        btnNext11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlq12Layout = new javax.swing.GroupLayout(pnlq12);
        pnlq12.setLayout(pnlq12Layout);
        pnlq12Layout.setHorizontalGroup(
            pnlq12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlq12Layout.createSequentialGroup()
                .addGroup(pnlq12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlq12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlq12Layout.createSequentialGroup()
                            .addGap(88, 88, 88)
                            .addGroup(pnlq12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(rdbA12, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(rdbD12, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(pnlq12Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(rdbA12C, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlq12Layout.createSequentialGroup()
                        .addGap(473, 473, 473)
                        .addComponent(btnNext11, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlq12Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(rdbC12, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlq12Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(lblquestion11)))
                .addContainerGap(388, Short.MAX_VALUE))
        );
        pnlq12Layout.setVerticalGroup(
            pnlq12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlq12Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblquestion11, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbA12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbA12C)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbC12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbD12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(btnNext11, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jTabbedPane1.addTab("Q12", pnlq12);

        pnlq13.setBackground(new java.awt.Color(255, 204, 204));

        lblquestion12.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        lblquestion12.setForeground(new java.awt.Color(204, 0, 0));
        lblquestion12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblquestion12.setText("13. Which of the following are examples of physical changes? ");

        cbxA13.setBackground(new java.awt.Color(255, 204, 204));
        cbxA13.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbxA13.setForeground(new java.awt.Color(204, 0, 0));
        cbxA13.setText("Burning paper ");

        cbxB13C.setBackground(new java.awt.Color(255, 204, 204));
        cbxB13C.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbxB13C.setForeground(new java.awt.Color(204, 0, 0));
        cbxB13C.setText("Melting Ice ");
        cbxB13C.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxB13CActionPerformed(evt);
            }
        });

        cbxC13.setBackground(new java.awt.Color(255, 204, 204));
        cbxC13.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbxC13.setForeground(new java.awt.Color(204, 0, 0));
        cbxC13.setText("Rusting");

        cbxD13C.setBackground(new java.awt.Color(255, 204, 204));
        cbxD13C.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbxD13C.setForeground(new java.awt.Color(204, 0, 0));
        cbxD13C.setText("Cutting Wood");

        btnNext12.setBackground(new java.awt.Color(204, 0, 0));
        btnNext12.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        btnNext12.setForeground(new java.awt.Color(255, 255, 255));
        btnNext12.setText("Next");
        btnNext12.setBorder(null);
        btnNext12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNext12MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNext12MouseExited(evt);
            }
        });
        btnNext12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlq13Layout = new javax.swing.GroupLayout(pnlq13);
        pnlq13.setLayout(pnlq13Layout);
        pnlq13Layout.setHorizontalGroup(
            pnlq13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlq13Layout.createSequentialGroup()
                .addGroup(pnlq13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlq13Layout.createSequentialGroup()
                        .addGap(478, 478, 478)
                        .addComponent(btnNext12, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlq13Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(lblquestion12, javax.swing.GroupLayout.PREFERRED_SIZE, 758, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlq13Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addGroup(pnlq13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxB13C, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxA13, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxD13C, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxC13, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(373, Short.MAX_VALUE))
        );
        pnlq13Layout.setVerticalGroup(
            pnlq13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlq13Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lblquestion12, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxA13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxB13C)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxC13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxD13C)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(btnNext12, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        jTabbedPane1.addTab("Q13", pnlq13);

        pnlq14.setBackground(new java.awt.Color(204, 204, 204));

        lblquestion13.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        lblquestion13.setForeground(new java.awt.Color(204, 0, 0));
        lblquestion13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblquestion13.setText("14. Which gas is most abundant in Earth’s atmosphere? ");

        rdbA14C.setBackground(new java.awt.Color(204, 204, 204));
        rdbA14C.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbA14C.setForeground(new java.awt.Color(204, 0, 0));
        rdbA14C.setText("A. Nitrogen");
        rdbA14C.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbA14CActionPerformed(evt);
            }
        });

        rdbB14.setBackground(new java.awt.Color(204, 204, 204));
        rdbB14.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbB14.setForeground(new java.awt.Color(204, 0, 0));
        rdbB14.setText("B. Carbon dioxide");
        rdbB14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbB14ActionPerformed(evt);
            }
        });

        rdbC14.setBackground(new java.awt.Color(204, 204, 204));
        rdbC14.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbC14.setForeground(new java.awt.Color(204, 0, 0));
        rdbC14.setText("C. Argon ");

        rdbD14.setBackground(new java.awt.Color(204, 204, 204));
        rdbD14.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbD14.setForeground(new java.awt.Color(204, 0, 0));
        rdbD14.setText("D. Oxygen");

        btnNext13.setBackground(new java.awt.Color(204, 0, 0));
        btnNext13.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        btnNext13.setForeground(new java.awt.Color(255, 255, 255));
        btnNext13.setText("Next");
        btnNext13.setBorder(null);
        btnNext13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNext13MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNext13MouseExited(evt);
            }
        });
        btnNext13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlq14Layout = new javax.swing.GroupLayout(pnlq14);
        pnlq14.setLayout(pnlq14Layout);
        pnlq14Layout.setHorizontalGroup(
            pnlq14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlq14Layout.createSequentialGroup()
                .addGroup(pnlq14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlq14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlq14Layout.createSequentialGroup()
                            .addGap(88, 88, 88)
                            .addComponent(rdbA14C, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlq14Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(rdbB14, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlq14Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(rdbC14, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlq14Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(lblquestion13, javax.swing.GroupLayout.PREFERRED_SIZE, 936, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlq14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(pnlq14Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(rdbD14, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlq14Layout.createSequentialGroup()
                            .addGap(473, 473, 473)
                            .addComponent(btnNext13, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(201, Short.MAX_VALUE))
        );
        pnlq14Layout.setVerticalGroup(
            pnlq14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlq14Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblquestion13, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbA14C)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbB14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbC14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbD14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(btnNext13, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jTabbedPane1.addTab("Q14", pnlq14);

        pnlq15.setBackground(new java.awt.Color(255, 153, 153));

        lblquestion14.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        lblquestion14.setForeground(new java.awt.Color(204, 0, 0));
        lblquestion14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblquestion14.setText("15. Which layer of Earth is mainly responsible for generating the planet’s magnetic field? ");

        rdbA15.setBackground(new java.awt.Color(255, 153, 153));
        rdbA15.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbA15.setForeground(new java.awt.Color(204, 0, 0));
        rdbA15.setText("A. Crust");
        rdbA15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbA15ActionPerformed(evt);
            }
        });

        rdbB15.setBackground(new java.awt.Color(255, 153, 153));
        rdbB15.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbB15.setForeground(new java.awt.Color(204, 0, 0));
        rdbB15.setText("B. Mantle ");

        rdbC15C.setBackground(new java.awt.Color(255, 153, 153));
        rdbC15C.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbC15C.setForeground(new java.awt.Color(204, 0, 0));
        rdbC15C.setText("C. Outer core");

        rdbD15.setBackground(new java.awt.Color(255, 153, 153));
        rdbD15.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbD15.setForeground(new java.awt.Color(204, 0, 0));
        rdbD15.setText("D. Inner core");

        btnNext14.setBackground(new java.awt.Color(204, 0, 0));
        btnNext14.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        btnNext14.setForeground(new java.awt.Color(255, 255, 255));
        btnNext14.setText("Next");
        btnNext14.setBorder(null);
        btnNext14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNext14MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNext14MouseExited(evt);
            }
        });
        btnNext14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlq15Layout = new javax.swing.GroupLayout(pnlq15);
        pnlq15.setLayout(pnlq15Layout);
        pnlq15Layout.setHorizontalGroup(
            pnlq15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlq15Layout.createSequentialGroup()
                .addGroup(pnlq15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlq15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlq15Layout.createSequentialGroup()
                            .addGap(88, 88, 88)
                            .addGroup(pnlq15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(rdbA15, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(rdbD15, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(pnlq15Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(rdbB15, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlq15Layout.createSequentialGroup()
                        .addGap(473, 473, 473)
                        .addComponent(btnNext14, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlq15Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(rdbC15C, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlq15Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(lblquestion14)))
                .addContainerGap(169, Short.MAX_VALUE))
        );
        pnlq15Layout.setVerticalGroup(
            pnlq15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlq15Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblquestion14, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbA15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbB15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbC15C)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbD15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(btnNext14, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jTabbedPane1.addTab("Q15", pnlq15);

        pnlq16.setBackground(new java.awt.Color(255, 204, 204));

        lblquestion15.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        lblquestion15.setForeground(new java.awt.Color(204, 0, 0));
        lblquestion15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblquestion15.setText("16. Which phenomenon allows particles to pass through classically forbidden regions? ");

        rdbA16C.setBackground(new java.awt.Color(255, 204, 204));
        rdbA16C.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbA16C.setForeground(new java.awt.Color(204, 0, 0));
        rdbA16C.setText("A. Quantum Tunneling");
        rdbA16C.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbA16CActionPerformed(evt);
            }
        });

        rdbB16.setBackground(new java.awt.Color(255, 204, 204));
        rdbB16.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbB16.setForeground(new java.awt.Color(204, 0, 0));
        rdbB16.setText("B. Diffraction");

        rdbC16.setBackground(new java.awt.Color(255, 204, 204));
        rdbC16.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbC16.setForeground(new java.awt.Color(204, 0, 0));
        rdbC16.setText("C. Superposition");

        rdbD16.setBackground(new java.awt.Color(255, 204, 204));
        rdbD16.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbD16.setForeground(new java.awt.Color(204, 0, 0));
        rdbD16.setText("D. Region Mapping");

        btnNext15.setBackground(new java.awt.Color(204, 0, 0));
        btnNext15.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        btnNext15.setForeground(new java.awt.Color(255, 255, 255));
        btnNext15.setText("Next");
        btnNext15.setBorder(null);
        btnNext15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNext15MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNext15MouseExited(evt);
            }
        });
        btnNext15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlq16Layout = new javax.swing.GroupLayout(pnlq16);
        pnlq16.setLayout(pnlq16Layout);
        pnlq16Layout.setHorizontalGroup(
            pnlq16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlq16Layout.createSequentialGroup()
                .addGroup(pnlq16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlq16Layout.createSequentialGroup()
                        .addGap(473, 473, 473)
                        .addComponent(btnNext15, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlq16Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(rdbC16, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlq16Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addGroup(pnlq16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdbA16C, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdbB16, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdbD16, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlq16Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(lblquestion15, javax.swing.GroupLayout.PREFERRED_SIZE, 1025, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(112, Short.MAX_VALUE))
        );
        pnlq16Layout.setVerticalGroup(
            pnlq16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlq16Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblquestion15, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbA16C)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbB16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbC16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbD16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(btnNext15, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jTabbedPane1.addTab("Q16", pnlq16);

        pnlq17.setBackground(new java.awt.Color(204, 204, 204));

        lblquestion16.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        lblquestion16.setForeground(new java.awt.Color(204, 0, 0));
        lblquestion16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblquestion16.setText("17. Which particle is responsible for mediating electromagnetic interactions in quantum theory?");

        rdbA17.setBackground(new java.awt.Color(204, 204, 204));
        rdbA17.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbA17.setForeground(new java.awt.Color(204, 0, 0));
        rdbA17.setText("A. Electron");
        rdbA17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbA17ActionPerformed(evt);
            }
        });

        rdbB17.setBackground(new java.awt.Color(204, 204, 204));
        rdbB17.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbB17.setForeground(new java.awt.Color(204, 0, 0));
        rdbB17.setText(" B. Neutrino");
        rdbB17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbB17ActionPerformed(evt);
            }
        });

        rdbC17C.setBackground(new java.awt.Color(204, 204, 204));
        rdbC17C.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbC17C.setForeground(new java.awt.Color(204, 0, 0));
        rdbC17C.setText(" C. Photon");

        rdbD17.setBackground(new java.awt.Color(204, 204, 204));
        rdbD17.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdbD17.setForeground(new java.awt.Color(204, 0, 0));
        rdbD17.setText(" D. Gluon");

        btnSubmit.setBackground(new java.awt.Color(204, 0, 0));
        btnSubmit.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        btnSubmit.setForeground(new java.awt.Color(255, 255, 255));
        btnSubmit.setText("Submit");
        btnSubmit.setBorder(null);
        btnSubmit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSubmitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSubmitMouseExited(evt);
            }
        });
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlq17Layout = new javax.swing.GroupLayout(pnlq17);
        pnlq17.setLayout(pnlq17Layout);
        pnlq17Layout.setHorizontalGroup(
            pnlq17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlq17Layout.createSequentialGroup()
                .addGroup(pnlq17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlq17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlq17Layout.createSequentialGroup()
                            .addGap(88, 88, 88)
                            .addComponent(rdbA17, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlq17Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(rdbB17, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlq17Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(rdbC17C, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlq17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(pnlq17Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(rdbD17, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlq17Layout.createSequentialGroup()
                            .addGap(473, 473, 473)
                            .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlq17Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(lblquestion16)))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        pnlq17Layout.setVerticalGroup(
            pnlq17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlq17Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblquestion16, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbA17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbB17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbC17C)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbD17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jTabbedPane1.addTab("Q17", pnlq17);

        pnlResult.setBackground(new java.awt.Color(51, 0, 0));

        jLabel2.setFont(new java.awt.Font("Broadway", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText(" QUIZ RESULT");

        lblScoreResult.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        lblScoreResult.setForeground(new java.awt.Color(255, 255, 255));
        lblScoreResult.setText("Result: ");

        lblRemarkResult.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        lblRemarkResult.setForeground(new java.awt.Color(255, 255, 255));
        lblRemarkResult.setText("Remarks: ");

        lblRecommendation.setBackground(new java.awt.Color(255, 153, 153));
        lblRecommendation.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        lblRecommendation.setForeground(new java.awt.Color(255, 255, 255));
        lblRecommendation.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRecommendation.setText("Recommendation");

        btnRetake.setBackground(new java.awt.Color(204, 0, 0));
        btnRetake.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        btnRetake.setForeground(new java.awt.Color(255, 255, 255));
        btnRetake.setText("Retake");
        btnRetake.setBorder(null);
        btnRetake.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRetakeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRetakeMouseExited(evt);
            }
        });
        btnRetake.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetakeActionPerformed(evt);
            }
        });

        btnBack1.setBackground(new java.awt.Color(51, 51, 51));
        btnBack1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        btnBack1.setForeground(new java.awt.Color(255, 255, 255));
        btnBack1.setText("Back");
        btnBack1.setBorder(null);
        btnBack1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBack1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBack1MouseExited(evt);
            }
        });
        btnBack1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBack1ActionPerformed(evt);
            }
        });

        lblCorrectQuestions.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        lblCorrectQuestions.setForeground(new java.awt.Color(255, 255, 255));
        lblCorrectQuestions.setText("Correct Questions: ");

        lblWrongQuestions.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        lblWrongQuestions.setForeground(new java.awt.Color(255, 255, 255));
        lblWrongQuestions.setText("Wrong Questions: ");

        lblAttemptMsg.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        lblAttemptMsg.setForeground(new java.awt.Color(255, 255, 255));
        lblAttemptMsg.setText("Quiz Summary (Attempt #)");

        javax.swing.GroupLayout pnlResultLayout = new javax.swing.GroupLayout(pnlResult);
        pnlResult.setLayout(pnlResultLayout);
        pnlResultLayout.setHorizontalGroup(
            pnlResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResultLayout.createSequentialGroup()
                .addGroup(pnlResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlResultLayout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 823, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlResultLayout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addGroup(pnlResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblRemarkResult, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblScoreResult, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAttemptMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblRecommendation, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(51, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlResultLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCorrectQuestions, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRetake, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlResultLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(btnBack1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlResultLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(lblWrongQuestions, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(209, 209, 209))
        );
        pnlResultLayout.setVerticalGroup(
            pnlResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResultLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlResultLayout.createSequentialGroup()
                        .addComponent(lblScoreResult, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblRemarkResult))
                    .addComponent(lblRecommendation, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(lblAttemptMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCorrectQuestions, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblWrongQuestions, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(pnlResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRetake, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        jTabbedPane1.addTab("Result", pnlResult);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlebarpnl3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(455, 455, 455)
                .addComponent(lblTimer, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblUser, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTabbedPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(lblRole, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(104, 104, 104))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(titlebarpnl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTimer)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRole)
                .addGap(0, 10, Short.MAX_VALUE))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("");

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

    private void btnTakeQuizMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTakeQuizMouseEntered
        // TODO add your handling code here:
        btnTakeQuiz.setBackground(new Color(170, 70, 68));

    }//GEN-LAST:event_btnTakeQuizMouseEntered

    private void btnTakeQuizMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTakeQuizMouseExited
        // TODO add your handling code here:
        btnTakeQuiz.setBackground(new Color(204,0,0));
    }//GEN-LAST:event_btnTakeQuizMouseExited

    private void btnBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseEntered
        // TODO add your handling code here:
        btnBack.setBackground(new Color(102,102,102));
    }//GEN-LAST:event_btnBackMouseEntered

    private void btnBackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseExited
        // TODO add your handling code here:
        btnBack.setBackground(new Color(51,51,51));
    }//GEN-LAST:event_btnBackMouseExited

    private void btnNextMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNextMouseEntered
        // TODO add your handling code here:
        btnNext.setBackground(new Color(170, 70, 68));
    }//GEN-LAST:event_btnNextMouseEntered

    private void btnNextMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNextMouseExited
        // TODO add your handling code here:
        btnNext.setBackground(new Color(204,0,0));
    }//GEN-LAST:event_btnNextMouseExited

    private void cbxchemCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxchemCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxchemCActionPerformed

    private void btnNext1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNext1MouseEntered
        // TODO add your handling code here:
        btnNext1.setBackground(new Color(170, 70, 68));
    }//GEN-LAST:event_btnNext1MouseEntered

    private void btnNext1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNext1MouseExited
        // TODO add your handling code here:
        btnNext1.setBackground(new Color(204,0,0));
    }//GEN-LAST:event_btnNext1MouseExited

    private void rdbAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbAActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_rdbAActionPerformed

    private void rdbA3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbA3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdbA3ActionPerformed

    private void btnNext2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNext2MouseEntered
        // TODO add your handling code here:
        btnNext2.setBackground(new Color(170, 70, 68));
    }//GEN-LAST:event_btnNext2MouseEntered

    private void btnNext2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNext2MouseExited
        // TODO add your handling code here:
        btnNext2.setBackground(new Color(204,0,0));
    }//GEN-LAST:event_btnNext2MouseExited

    private void btnNext3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNext3MouseEntered
        // TODO add your handling code here:
        btnNext3.setBackground(new Color(170, 70, 68));
    }//GEN-LAST:event_btnNext3MouseEntered

    private void btnNext3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNext3MouseExited
        // TODO add your handling code here:
        btnNext3.setBackground(new Color(204,0,0));
    }//GEN-LAST:event_btnNext3MouseExited

    private void cbxB4CActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxB4CActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxB4CActionPerformed

    private void rdbA5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbA5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdbA5ActionPerformed

    private void btnNext4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNext4MouseEntered
        // TODO add your handling code here:
        btnNext4.setBackground(new Color(170, 70, 68));
    }//GEN-LAST:event_btnNext4MouseEntered

    private void btnNext4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNext4MouseExited
        // TODO add your handling code here:
        btnNext4.setBackground(new Color(204,0,0));
    }//GEN-LAST:event_btnNext4MouseExited

    private void rdbA6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbA6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdbA6ActionPerformed

    private void btnNext5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNext5MouseEntered
        // TODO add your handling code here:
        btnNext5.setBackground(new Color(170, 70, 68));
    }//GEN-LAST:event_btnNext5MouseEntered

    private void btnNext5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNext5MouseExited
        // TODO add your handling code here:
        btnNext5.setBackground(new Color(204,0,0));
    }//GEN-LAST:event_btnNext5MouseExited

    private void rdbA7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbA7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdbA7ActionPerformed

    private void btnNext6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNext6MouseEntered
        // TODO add your handling code here:
        btnNext6.setBackground(new Color(170, 70, 68));
    }//GEN-LAST:event_btnNext6MouseEntered

    private void btnNext6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNext6MouseExited
        // TODO add your handling code here:
        btnNext6.setBackground(new Color(204,0,0));
    }//GEN-LAST:event_btnNext6MouseExited

    private void rdbA8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbA8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdbA8ActionPerformed

    private void btnNext7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNext7MouseEntered
        // TODO add your handling code here:
        btnNext7.setBackground(new Color(170, 70, 68));
    }//GEN-LAST:event_btnNext7MouseEntered

    private void btnNext7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNext7MouseExited
        // TODO add your handling code here:
        btnNext7.setBackground(new Color(204,0,0));
    }//GEN-LAST:event_btnNext7MouseExited

    private void rdbB8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbB8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdbB8ActionPerformed

    private void rdbA9CActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbA9CActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdbA9CActionPerformed

    private void btnNext8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNext8MouseEntered
        // TODO add your handling code here:
        btnNext8.setBackground(new Color(170, 70, 68));
    }//GEN-LAST:event_btnNext8MouseEntered

    private void btnNext8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNext8MouseExited
        // TODO add your handling code here:
        btnNext8.setBackground(new Color(204,0,0));
    }//GEN-LAST:event_btnNext8MouseExited

    private void rdbA10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbA10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdbA10ActionPerformed

    private void btnNext9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNext9MouseEntered
        // TODO add your handling code here:
        btnNext9.setBackground(new Color(170, 70, 68));
    }//GEN-LAST:event_btnNext9MouseEntered

    private void btnNext9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNext9MouseExited
        // TODO add your handling code here:
        btnNext9.setBackground(new Color(204,0,0));
    }//GEN-LAST:event_btnNext9MouseExited

    private void rdbA11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbA11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdbA11ActionPerformed

    private void rdbB11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbB11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdbB11ActionPerformed

    private void btnNext10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNext10MouseEntered
        // TODO add your handling code here:
        btnNext10.setBackground(new Color(170, 70, 68));
    }//GEN-LAST:event_btnNext10MouseEntered

    private void btnNext10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNext10MouseExited
        // TODO add your handling code here:
        btnNext10.setBackground(new Color(204,0,0));
    }//GEN-LAST:event_btnNext10MouseExited

    private void rdbA12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbA12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdbA12ActionPerformed

    private void btnNext11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNext11MouseEntered
        // TODO add your handling code here:
        btnNext11.setBackground(new Color(170, 70, 68));
    }//GEN-LAST:event_btnNext11MouseEntered

    private void btnNext11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNext11MouseExited
        // TODO add your handling code here:
        btnNext11.setBackground(new Color(204,0,0));
    }//GEN-LAST:event_btnNext11MouseExited

    private void cbxB13CActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxB13CActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxB13CActionPerformed

    private void btnNext12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNext12MouseEntered
        // TODO add your handling code here:
        btnNext12.setBackground(new Color(170, 70, 68));
    }//GEN-LAST:event_btnNext12MouseEntered

    private void btnNext12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNext12MouseExited
        // TODO add your handling code here:
        btnNext12.setBackground(new Color(204,0,0));
    }//GEN-LAST:event_btnNext12MouseExited

    private void rdbA14CActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbA14CActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdbA14CActionPerformed

    private void rdbB14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbB14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdbB14ActionPerformed

    private void btnNext13MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNext13MouseEntered
        // TODO add your handling code here:
        btnNext13.setBackground(new Color(170, 70, 68));
    }//GEN-LAST:event_btnNext13MouseEntered

    private void btnNext13MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNext13MouseExited
        // TODO add your handling code here:
        btnNext13.setBackground(new Color(204,0,0));
    }//GEN-LAST:event_btnNext13MouseExited

    private void rdbA15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbA15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdbA15ActionPerformed

    private void btnNext14MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNext14MouseEntered
        // TODO add your handling code here:
        btnNext14.setBackground(new Color(170, 70, 68));
    }//GEN-LAST:event_btnNext14MouseEntered

    private void btnNext14MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNext14MouseExited
        // TODO add your handling code here:
        btnNext14.setBackground(new Color(204,0,0));
    }//GEN-LAST:event_btnNext14MouseExited

    private void rdbA16CActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbA16CActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdbA16CActionPerformed

    private void btnNext15MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNext15MouseEntered
        // TODO add your handling code here:
        btnNext15.setBackground(new Color(170, 70, 68));
    }//GEN-LAST:event_btnNext15MouseEntered

    private void btnNext15MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNext15MouseExited
        // TODO add your handling code here:
        btnNext15.setBackground(new Color(204,0,0));
    }//GEN-LAST:event_btnNext15MouseExited

    private void rdbA17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbA17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdbA17ActionPerformed

    private void rdbB17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbB17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdbB17ActionPerformed

    private void btnSubmitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSubmitMouseEntered
        // TODO add your handling code here:
        btnSubmit.setBackground(new Color(170, 70, 68));
    }//GEN-LAST:event_btnSubmitMouseEntered

    private void btnSubmitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSubmitMouseExited
        // TODO add your handling code here:
        btnSubmit.setBackground(new Color(204,0,0));
    }//GEN-LAST:event_btnSubmitMouseExited

    private void btnTakeQuizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTakeQuizActionPerformed
        // TODO add your handling code here:
        int used = attemptCountByUser.getOrDefault(username, 0);
        if (used >= MAX_ATTEMPTS) {
            JOptionPane.showMessageDialog(this, "No attempts left.");
            return;
        }

        incrementAttempt();
        startTimer();

        lockToTab(1); // show ONLY Q1
    }//GEN-LAST:event_btnTakeQuizActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        stopTimer();
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

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        goNextLocked();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnNext1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext1ActionPerformed
        // TODO add your handling code here:
        goNextLocked();
    }//GEN-LAST:event_btnNext1ActionPerformed

    private void btnNext2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext2ActionPerformed
        // TODO add your handling code here:
        goNextLocked();
    }//GEN-LAST:event_btnNext2ActionPerformed

    private void btnNext3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext3ActionPerformed
        // TODO add your handling code here:
        goNextLocked();
    }//GEN-LAST:event_btnNext3ActionPerformed

    private void btnNext4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext4ActionPerformed
        // TODO add your handling code here:
        goNextLocked();
    }//GEN-LAST:event_btnNext4ActionPerformed

    private void btnNext5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext5ActionPerformed
         // TODO add your handling code here:
         goNextLocked();
    }//GEN-LAST:event_btnNext5ActionPerformed

    private void btnNext6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext6ActionPerformed
        // TODO add your handling code here:
        goNextLocked();
    }//GEN-LAST:event_btnNext6ActionPerformed

    private void btnNext7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext7ActionPerformed
        // TODO add your handling code here:
        goNextLocked();
    }//GEN-LAST:event_btnNext7ActionPerformed

    private void btnNext8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext8ActionPerformed
        // TODO add your handling code here:
        goNextLocked();
    }//GEN-LAST:event_btnNext8ActionPerformed

    private void btnNext9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext9ActionPerformed
        // TODO add your handling code here:
        goNextLocked();
    }//GEN-LAST:event_btnNext9ActionPerformed

    private void btnNext10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext10ActionPerformed
        // TODO add your handling code here:
        goNextLocked();
    }//GEN-LAST:event_btnNext10ActionPerformed

    private void btnNext11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext11ActionPerformed
         // TODO add your handling code here:
         goNextLocked();
    }//GEN-LAST:event_btnNext11ActionPerformed

    private void btnNext12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext12ActionPerformed
        // TODO add your handling code here:
        goNextLocked();
    }//GEN-LAST:event_btnNext12ActionPerformed

    private void btnNext13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext13ActionPerformed
        // TODO add your handling code here:
        goNextLocked();
    }//GEN-LAST:event_btnNext13ActionPerformed

    private void btnNext14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext14ActionPerformed
        // TODO add your handling code here:
        goNextLocked();
    }//GEN-LAST:event_btnNext14ActionPerformed

    private void btnNext15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext15ActionPerformed
        // TODO add your handling code here:
        goNextLocked();
    }//GEN-LAST:event_btnNext15ActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        // TODO add your handling code here:
        showResultsTab();
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void btnRetakeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRetakeMouseEntered
        // TODO add your handling code here:
        btnRetake.setBackground(new Color(170, 70, 68));
        
    }//GEN-LAST:event_btnRetakeMouseEntered

    private void btnRetakeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRetakeMouseExited
        // TODO add your handling code here:
        btnRetake.setBackground(new Color(204,0,0));
    }//GEN-LAST:event_btnRetakeMouseExited

    private void btnRetakeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetakeActionPerformed
         // TODO add your handling code here: 
        
         
        int used = attemptCountByUser.getOrDefault(username, 0);
        if (used >= MAX_ATTEMPTS) {
            JOptionPane.showMessageDialog(this, "No attempts left. You will be redirected to the dashboard.");
                
            int state = this.getExtendedState();
            this.dispose();
            Frm_Dashboard dashboard = new Frm_Dashboard(username, role);
            dashboard.setExtendedState(state);
            dashboard.setVisible(true);
        }
        clearAllAnswers();
        incrementAttempt();
        startTimer();
        lockToTab(1); // back to Q1 only
    }//GEN-LAST:event_btnRetakeActionPerformed

    private void btnBack1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBack1MouseEntered
        // TODO add your handling code here:
        btnBack1.setBackground(new Color(102,102,102));
        
    }//GEN-LAST:event_btnBack1MouseEntered

    private void btnBack1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBack1MouseExited
        // TODO add your handling code here:
        btnBack1.setBackground(new Color(51,51,51));
    }//GEN-LAST:event_btnBack1MouseExited

    private void btnBack1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBack1ActionPerformed
        // TODO add your handling code here:
        stopTimer();
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
    }//GEN-LAST:event_btnBack1ActionPerformed

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
            java.util.logging.Logger.getLogger(Frm_Quiz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frm_Quiz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frm_Quiz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm_Quiz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frm_Quiz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnBack1;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnNext1;
    private javax.swing.JButton btnNext10;
    private javax.swing.JButton btnNext11;
    private javax.swing.JButton btnNext12;
    private javax.swing.JButton btnNext13;
    private javax.swing.JButton btnNext14;
    private javax.swing.JButton btnNext15;
    private javax.swing.JButton btnNext2;
    private javax.swing.JButton btnNext3;
    private javax.swing.JButton btnNext4;
    private javax.swing.JButton btnNext5;
    private javax.swing.JButton btnNext6;
    private javax.swing.JButton btnNext7;
    private javax.swing.JButton btnNext8;
    private javax.swing.JButton btnNext9;
    private javax.swing.JButton btnRetake;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JButton btnTakeQuiz;
    private javax.swing.JCheckBox cbxA13;
    private javax.swing.JCheckBox cbxA4;
    private javax.swing.JCheckBox cbxB13C;
    private javax.swing.JCheckBox cbxB4C;
    private javax.swing.JCheckBox cbxC13;
    private javax.swing.JCheckBox cbxC4C;
    private javax.swing.JCheckBox cbxD13C;
    private javax.swing.JCheckBox cbxD4C;
    private javax.swing.JCheckBox cbxastrology;
    private javax.swing.JCheckBox cbxbioC;
    private javax.swing.JCheckBox cbxchemC;
    private javax.swing.JCheckBox cbxphysicsC;
    private javax.swing.JButton closebtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblAttemptDesc;
    private javax.swing.JLabel lblAttemptMsg;
    private javax.swing.JLabel lblCorrectQuestions;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblRecommendation;
    private javax.swing.JLabel lblRemarkResult;
    private javax.swing.JLabel lblRole;
    private javax.swing.JLabel lblScoreResult;
    private javax.swing.JLabel lblTimer;
    private javax.swing.JLabel lblUser;
    private javax.swing.JLabel lblWrongQuestions;
    private javax.swing.JLabel lblquestion;
    private javax.swing.JLabel lblquestion1;
    private javax.swing.JLabel lblquestion10;
    private javax.swing.JLabel lblquestion11;
    private javax.swing.JLabel lblquestion12;
    private javax.swing.JLabel lblquestion13;
    private javax.swing.JLabel lblquestion14;
    private javax.swing.JLabel lblquestion15;
    private javax.swing.JLabel lblquestion16;
    private javax.swing.JLabel lblquestion2;
    private javax.swing.JLabel lblquestion3;
    private javax.swing.JLabel lblquestion4;
    private javax.swing.JLabel lblquestion5;
    private javax.swing.JLabel lblquestion6;
    private javax.swing.JLabel lblquestion7;
    private javax.swing.JLabel lblquestion8;
    private javax.swing.JLabel lblquestion9;
    private javax.swing.JButton maximizebtn;
    private javax.swing.JButton minimizebtn;
    private javax.swing.JPanel pnlResult;
    private javax.swing.JPanel pnlStart;
    private javax.swing.JPanel pnlq1;
    private javax.swing.JPanel pnlq10;
    private javax.swing.JPanel pnlq11;
    private javax.swing.JPanel pnlq12;
    private javax.swing.JPanel pnlq13;
    private javax.swing.JPanel pnlq14;
    private javax.swing.JPanel pnlq15;
    private javax.swing.JPanel pnlq16;
    private javax.swing.JPanel pnlq17;
    private javax.swing.JPanel pnlq2;
    private javax.swing.JPanel pnlq3;
    private javax.swing.JPanel pnlq4;
    private javax.swing.JPanel pnlq5;
    private javax.swing.JPanel pnlq6;
    private javax.swing.JPanel pnlq7;
    private javax.swing.JPanel pnlq8;
    private javax.swing.JPanel pnlq9;
    private javax.swing.JRadioButton rdbA;
    private javax.swing.JRadioButton rdbA10;
    private javax.swing.JRadioButton rdbA11;
    private javax.swing.JRadioButton rdbA12;
    private javax.swing.JRadioButton rdbA12C;
    private javax.swing.JRadioButton rdbA14C;
    private javax.swing.JRadioButton rdbA15;
    private javax.swing.JRadioButton rdbA16C;
    private javax.swing.JRadioButton rdbA17;
    private javax.swing.JRadioButton rdbA3;
    private javax.swing.JRadioButton rdbA5;
    private javax.swing.JRadioButton rdbA6;
    private javax.swing.JRadioButton rdbA7;
    private javax.swing.JRadioButton rdbA8;
    private javax.swing.JRadioButton rdbA9C;
    private javax.swing.JRadioButton rdbB;
    private javax.swing.JRadioButton rdbB10C;
    private javax.swing.JRadioButton rdbB11;
    private javax.swing.JRadioButton rdbB14;
    private javax.swing.JRadioButton rdbB15;
    private javax.swing.JRadioButton rdbB16;
    private javax.swing.JRadioButton rdbB17;
    private javax.swing.JRadioButton rdbB3;
    private javax.swing.JRadioButton rdbB5;
    private javax.swing.JRadioButton rdbB6C;
    private javax.swing.JRadioButton rdbB7;
    private javax.swing.JRadioButton rdbB8;
    private javax.swing.JRadioButton rdbB9;
    private javax.swing.JRadioButton rdbC10;
    private javax.swing.JRadioButton rdbC11;
    private javax.swing.JRadioButton rdbC12;
    private javax.swing.JRadioButton rdbC14;
    private javax.swing.JRadioButton rdbC15C;
    private javax.swing.JRadioButton rdbC16;
    private javax.swing.JRadioButton rdbC17C;
    private javax.swing.JRadioButton rdbC3C;
    private javax.swing.JRadioButton rdbC5;
    private javax.swing.JRadioButton rdbC6;
    private javax.swing.JRadioButton rdbC7;
    private javax.swing.JRadioButton rdbC8C;
    private javax.swing.JRadioButton rdbC9;
    private javax.swing.JRadioButton rdbCC;
    private javax.swing.JRadioButton rdbD;
    private javax.swing.JRadioButton rdbD10;
    private javax.swing.JRadioButton rdbD11C;
    private javax.swing.JRadioButton rdbD12;
    private javax.swing.JRadioButton rdbD14;
    private javax.swing.JRadioButton rdbD15;
    private javax.swing.JRadioButton rdbD16;
    private javax.swing.JRadioButton rdbD17;
    private javax.swing.JRadioButton rdbD3;
    private javax.swing.JRadioButton rdbD5C;
    private javax.swing.JRadioButton rdbD6;
    private javax.swing.JRadioButton rdbD7C;
    private javax.swing.JRadioButton rdbD8;
    private javax.swing.JRadioButton rdbD9;
    private javax.swing.JPanel titlebarpnl3;
    // End of variables declaration//GEN-END:variables
}
