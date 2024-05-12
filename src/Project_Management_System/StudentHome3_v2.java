/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Project_Management_System;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Owner
 */
public class StudentHome3_v2 extends javax.swing.JFrame {
    
private void createAssignmentPanels(String assessmentID) {
        String line;
        
        try {BufferedReader br = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assessment_assignment.txt")); 
            while ((line = br.readLine()) != null) {
                String[] record = line.split("\t");
                
                System.out.println(record[1]);
                if(record[1].equals(assessmentID)){
                
                javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
                javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
                
                jLabel1.setText(record[2]);
                jLabel2.setText(record[3]);
                
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addContainerGap(100, Short.MAX_VALUE))
                );
                jPanel1Layout.setVerticalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
        
                jPanel1.setBorder(BorderFactory.createLineBorder(Color.black));
                
                jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    // TODO add your handling code here:
                    
                    
                    
                    
                    }
                });
                
                
                pAssignment.add(jPanel1);
            }
            jScrollPane2.setViewportView(pAssignment);
            }
        } catch (Exception e) {
        e.getMessage();
    }
    }
 
    private void createAssessmentPanels() {
        String line;
        try {BufferedReader br = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assessment.txt")); 
            while ((line = br.readLine()) != null) {
                String[] record = line.split("\t");
                
                javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
                javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
                
                jLabel1.setText(record[0]);
                jLabel2.setText(record[1]);
                
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addContainerGap(100, Short.MAX_VALUE))
                );
                jPanel1Layout.setVerticalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
        
        jPanel1.setBorder(BorderFactory.createLineBorder(Color.black));
                
                jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    // TODO add your handling code here:
                    System.out.println(record[0]);
                    createAssignmentPanels(record[0]);
                    
                    pInsideAssessment.setVisible(true);
                    jPanel2.setVisible(false);
                    ModuleLabel.setText(record[1]);
                    
                    }
                });
                
                
                pAssessment.add(jPanel1);
            }
            jScrollPane1.setViewportView(pAssessment);
        } catch (Exception e) {
        e.getMessage();
    }
    }
    /**
     * Creates new form StudentHome
     */
    public StudentHome3_v2() {
        initComponents();
         createAssessmentPanels();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sidePanel = new javax.swing.JPanel();
        presentationButton = new javax.swing.JButton();
        resultButton = new javax.swing.JButton();
        profileButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        mainTitleLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pAssessment = new javax.swing.JPanel();
        pInsideAssessment = new javax.swing.JPanel();
        ModuleLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        pAssignment = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        AssessmentPanel = new javax.swing.JPanel();
        Description = new javax.swing.JLabel();
        FileSubmissionLabel = new javax.swing.JLabel();
        FilePanel = new javax.swing.JPanel();
        UploadBtn = new javax.swing.JButton();
        filePathLabel = new javax.swing.JLabel();
        SubmitFileBtn = new javax.swing.JButton();
        ClearBtn = new javax.swing.JButton();
        AssignmentLabel = new javax.swing.JLabel();
        moduleLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        sidePanel.setBackground(new java.awt.Color(1, 51, 80));
        sidePanel.setPreferredSize(new java.awt.Dimension(244, 284));

        presentationButton.setBackground(new java.awt.Color(255, 255, 204));
        presentationButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        presentationButton.setText("Presentation Slot");
        presentationButton.setPreferredSize(new java.awt.Dimension(184, 40));
        presentationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                presentationButtonActionPerformed(evt);
            }
        });

        resultButton.setBackground(new java.awt.Color(255, 255, 204));
        resultButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        resultButton.setText("Result");
        resultButton.setPreferredSize(new java.awt.Dimension(184, 40));
        resultButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resultButtonActionPerformed(evt);
            }
        });

        profileButton.setBackground(new java.awt.Color(255, 255, 204));
        profileButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        profileButton.setText("Profile");
        profileButton.setPreferredSize(new java.awt.Dimension(184, 40));
        profileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sidePanelLayout = new javax.swing.GroupLayout(sidePanel);
        sidePanel.setLayout(sidePanelLayout);
        sidePanelLayout.setHorizontalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(resultButton, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(presentationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(profileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        sidePanelLayout.setVerticalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addContainerGap(234, Short.MAX_VALUE)
                .addComponent(presentationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(resultButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(profileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(393, 393, 393))
        );

        jPanel2.setBackground(new java.awt.Color(252, 247, 204));
        jPanel2.setPreferredSize(new java.awt.Dimension(1500, 780));

        mainTitleLabel.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        mainTitleLabel.setText("Student Dashboard");
        mainTitleLabel.setPreferredSize(new java.awt.Dimension(200, 160));

        pAssessment.setLayout(new java.awt.GridLayout(0, 1));
        jScrollPane1.setViewportView(pAssessment);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(122, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mainTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(736, 736, 736))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(mainTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pInsideAssessment.setBackground(new java.awt.Color(252, 247, 204));
        pInsideAssessment.setPreferredSize(new java.awt.Dimension(1500, 780));

        ModuleLabel.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        ModuleLabel.setPreferredSize(new java.awt.Dimension(200, 160));

        pAssignment.setLayout(new java.awt.GridLayout(0, 1));
        jScrollPane2.setViewportView(pAssignment);

        javax.swing.GroupLayout pInsideAssessmentLayout = new javax.swing.GroupLayout(pInsideAssessment);
        pInsideAssessment.setLayout(pInsideAssessmentLayout);
        pInsideAssessmentLayout.setHorizontalGroup(
            pInsideAssessmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pInsideAssessmentLayout.createSequentialGroup()
                .addGroup(pInsideAssessmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pInsideAssessmentLayout.createSequentialGroup()
                        .addGap(315, 315, 315)
                        .addComponent(ModuleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 1127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pInsideAssessmentLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pInsideAssessmentLayout.setVerticalGroup(
            pInsideAssessmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pInsideAssessmentLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(ModuleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(158, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(252, 247, 204));
        jPanel3.setPreferredSize(new java.awt.Dimension(1500, 780));

        AssessmentPanel.setBackground(new java.awt.Color(255, 255, 255));
        AssessmentPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        FileSubmissionLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        FileSubmissionLabel.setText("File Submission:");

        UploadBtn.setText("Upload a File");
        UploadBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UploadBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout FilePanelLayout = new javax.swing.GroupLayout(FilePanel);
        FilePanel.setLayout(FilePanelLayout);
        FilePanelLayout.setHorizontalGroup(
            FilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FilePanelLayout.createSequentialGroup()
                .addGroup(FilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FilePanelLayout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(UploadBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 629, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(FilePanelLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(filePathLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 805, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        FilePanelLayout.setVerticalGroup(
            FilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FilePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(UploadBtn)
                .addGap(35, 35, 35)
                .addComponent(filePathLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(87, Short.MAX_VALUE))
        );

        SubmitFileBtn.setText("SUBMIT");
        SubmitFileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitFileBtnActionPerformed(evt);
            }
        });

        ClearBtn.setText("CLEAR");
        ClearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AssessmentPanelLayout = new javax.swing.GroupLayout(AssessmentPanel);
        AssessmentPanel.setLayout(AssessmentPanelLayout);
        AssessmentPanelLayout.setHorizontalGroup(
            AssessmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AssessmentPanelLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(AssessmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AssessmentPanelLayout.createSequentialGroup()
                        .addComponent(FileSubmissionLabel)
                        .addGap(18, 18, 18)
                        .addComponent(FilePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(AssessmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(Description, javax.swing.GroupLayout.DEFAULT_SIZE, 997, Short.MAX_VALUE)
                        .addComponent(AssignmentLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(75, Short.MAX_VALUE))
            .addGroup(AssessmentPanelLayout.createSequentialGroup()
                .addGap(485, 485, 485)
                .addComponent(SubmitFileBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(ClearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        AssessmentPanelLayout.setVerticalGroup(
            AssessmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AssessmentPanelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(Description)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AssignmentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(AssessmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AssessmentPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(FilePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AssessmentPanelLayout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(FileSubmissionLabel)))
                .addGap(30, 30, 30)
                .addGroup(AssessmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SubmitFileBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ClearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(181, Short.MAX_VALUE))
        );

        moduleLabel.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        moduleLabel.setPreferredSize(new java.awt.Dimension(200, 160));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(294, 294, 294)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(moduleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 1127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AssessmentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(1151, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(moduleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(AssessmentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pInsideAssessment, javax.swing.GroupLayout.PREFERRED_SIZE, 1326, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1326, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 849, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pInsideAssessment, javax.swing.GroupLayout.DEFAULT_SIZE, 849, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(45, 45, 45)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(46, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(sidePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sidePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 861, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void presentationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_presentationButtonActionPerformed
        // TODO add your handling code here:
        new StudentPresentationSlot().setVisible(true);
        dispose();
       
    }//GEN-LAST:event_presentationButtonActionPerformed

    private void resultButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resultButtonActionPerformed
        // TODO add your handling code here:
        new StudentAsmSubmissionResult().setVisible(true);
        dispose();
    }//GEN-LAST:event_resultButtonActionPerformed

    private void profileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileButtonActionPerformed
        // TODO add your handling code here:
        new StudentProfile().setVisible(true);
        dispose();
    }//GEN-LAST:event_profileButtonActionPerformed

    private void UploadBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UploadBtnActionPerformed
        // Create a file chooser
        JFileChooser fileChooser = new JFileChooser();

        // Show open dialog
        int result = fileChooser.showOpenDialog(this);

        // Check if the user selected a file
        if (result == JFileChooser.APPROVE_OPTION) {
            // Get the selected file
            File selectedFile = fileChooser.getSelectedFile();

            // Create a JPanel to hold file name and checkbox
            JPanel fileEntryPanel = new JPanel();
            fileEntryPanel.setLayout(new BorderLayout());

            // Create a JLabel for the file name
            JLabel fileNameLabel = new JLabel(selectedFile.getName());
            fileNameLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            fileNameLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    String newName = JOptionPane.showInputDialog("Enter new name:");
                    if (newName != null && !newName.isEmpty()) {
                        fileNameLabel.setText(newName);
                    }
                }
            });

            // Create a JCheckBox for selection
            JCheckBox fileCheckBox = new JCheckBox();
            fileCheckBox.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Add components to the file entry panel
            fileEntryPanel.add(fileNameLabel, BorderLayout.CENTER);
            fileEntryPanel.add(fileCheckBox, BorderLayout.WEST);

            // Add the file entry panel to the file panel
            FilePanel.add(fileEntryPanel);
            FilePanel.revalidate();
            FilePanel.repaint();
        }

    }//GEN-LAST:event_UploadBtnActionPerformed

    private void SubmitFileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitFileBtnActionPerformed

    }//GEN-LAST:event_SubmitFileBtnActionPerformed

    private void ClearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearBtnActionPerformed
        // Iterate over all components in FilePanel to find the selected checkbox
        for (Component component : FilePanel.getComponents()) {
            if (component instanceof JPanel) {
                JPanel fileEntryPanel = (JPanel) component;
                Component[] components = fileEntryPanel.getComponents();
                if (components.length == 2 && components[0] instanceof JCheckBox) {
                    JCheckBox checkBox = (JCheckBox) components[0];
                    if (checkBox.isSelected()) {
                        // Get the file path from the action command of the checkbox
                        String filePath = checkBox.getActionCommand();

                        // Create a File object
                        File file = new File(filePath);

                        // Delete the file
                        if (file.delete()) {
                            System.out.println("File deleted successfully");
                            // Remove the file entry panel from the FilePanel
                            FilePanel.remove(fileEntryPanel);
                            FilePanel.revalidate();
                            FilePanel.repaint();
                        } else {
                            System.out.println("Failed to delete the file");
                            System.out.println("File path: " + filePath);
                        }
                        break; // Exit loop after deleting the file
                    }
                }
            }
        }

    }//GEN-LAST:event_ClearBtnActionPerformed

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
            java.util.logging.Logger.getLogger(StudentHome3_v2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentHome3_v2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentHome3_v2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentHome3_v2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentHome3_v2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AssessmentPanel;
    private javax.swing.JLabel AssignmentLabel;
    private javax.swing.JButton ClearBtn;
    private javax.swing.JLabel Description;
    private javax.swing.JPanel FilePanel;
    private javax.swing.JLabel FileSubmissionLabel;
    private javax.swing.JLabel ModuleLabel;
    private javax.swing.JButton SubmitFileBtn;
    private javax.swing.JButton UploadBtn;
    private javax.swing.JLabel filePathLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel mainTitleLabel;
    private javax.swing.JLabel moduleLabel;
    private javax.swing.JPanel pAssessment;
    private javax.swing.JPanel pAssignment;
    private javax.swing.JPanel pInsideAssessment;
    private javax.swing.JButton presentationButton;
    private javax.swing.JButton profileButton;
    private javax.swing.JButton resultButton;
    private javax.swing.JPanel sidePanel;
    // End of variables declaration//GEN-END:variables
}
