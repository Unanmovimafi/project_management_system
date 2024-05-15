/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Project_Management_System;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Owner
 */
public class StudentHome extends javax.swing.JFrame {
    
    private String ID;
    private String assessmentID;
    private File sourceFile;
    private File destinationFile;
    private String strDesFile;
    
    private int rowOfAssignmentStudentSubmission = -1;
    
    public void setID(String ID) {
        this.ID = ID;
        createAssessmentPanels();
    }
    
    public void refreshSubmissionPanel(String IDOfAssessment) {
        String line2;
        boolean isSubmittedFile = false;
        String submittedFile = "";
        rowOfAssignmentStudentSubmission = -1;
        
        try {BufferedReader br2 = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assessment_student.txt")); 
                        while ((line2 = br2.readLine()) != null) {
                            String[] record2 = line2.split("\t");
                            rowOfAssignmentStudentSubmission = rowOfAssignmentStudentSubmission + 1;
                            if (record2[0].equals(assessmentID) && record2[1].equals(ID) && !record2[2].equals("NA")) {
                                submittedFile = record2[2];
                                isSubmittedFile = true;
                                break;
                            }
                        }
                        
                    } catch (Exception e) {
                        e.getMessage();
                    }
        
        String line;
        String[] assessInfo = new String[8];
                
        try {BufferedReader br = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assessment.txt")); 
                        while ((line = br.readLine()) != null) {
                            String[] record = line.split("\t");
                            if (record[0].equals(assessmentID)) {
                                assessInfo = record;
                                break;
                            }
                        }
                        
                    } catch (Exception e) {
                        e.getMessage();
                    }
                    
                    if (isSubmittedFile){
                        String FileName = submittedFile.substring(submittedFile.lastIndexOf("\\")+1);
                        lSubmissionStatus.setText("Submitted");
                        lSubmittedFile.setText(submittedFile);
                        
                        lFileName.setText(submittedFile);
                    } else {
                        lSubmissionStatus.setText("Not Submitted");
                        lSubmittedFile.setText("");
                        lFileName.setText(submittedFile);
                        rowOfAssignmentStudentSubmission = - 1;
                    }
                    AssignmentLabel.setText(assessInfo[1]);
                    moduleLabel.setText(assessInfo[1]);
                    Description.setText(assessInfo[3]);
    }
    
    private void createAssessmentPanels() {
        
        pAssessment.removeAll();
        
        String line;
        String line2;
        int count = 0;
        
                try {BufferedReader br2 = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assessment_student.txt")); 
                    while ((line2 = br2.readLine()) != null) {
                        String[] record2 = line2.split("\t");
        try {BufferedReader br = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assessment.txt")); 
            while ((line = br.readLine()) != null) {
                String[] record = line.split("\t");
                        if (ID.equals(record2[1]) && record2[0].equals(record[0])){
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

                            jPanel1.setPreferredSize(new Dimension(450, 50));
                            jPanel1.setBorder(BorderFactory.createLineBorder(Color.black));
                            jPanel1.setCursor(new Cursor(Cursor.HAND_CURSOR));

                            jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
                                public void mouseReleased(java.awt.event.MouseEvent evt) {
                                    // TODO add your handling code here:
                                    assessmentID = record[0];
                                    refreshSubmissionPanel(record[0]);
                                    
                                    pSubmittedAssign.setVisible(true);
                                    pDashboard.setVisible(false);
                                    pAssignmentSubmission.setVisible(false);
                                    
                                    moduleLabel.setText(record[1]);
                                    ModuleLabel1.setText(record[1]);
                                    }
                            });
                            pAssessment.add(jPanel1);
                            count = count + 1;
                            break;
                        }
                    }
                }catch (Exception e) {
                    e.getMessage();
                }
            }
            
        } catch (Exception e) {
        e.getMessage();
        }
        pAssessment.setPreferredSize(new Dimension(350, count*55));
        jScrollPane1.setViewportView(pAssessment);
        
        
        pAssessment.revalidate();
        pAssessment.repaint();  
    }
    /**
     * Creates new form StudentHome
     */
    public StudentHome() {
        initComponents();
        pDashboard.setVisible(true);
        pAssignmentSubmission.setVisible(false);
        pSubmittedAssign.setVisible(false);
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
        pDashboard = new javax.swing.JPanel();
        mainTitleLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pAssessment = new javax.swing.JPanel();
        pAssignmentSubmission = new javax.swing.JPanel();
        AssessmentPanel = new javax.swing.JPanel();
        FileSubmissionLabel = new javax.swing.JLabel();
        FilePanel = new javax.swing.JPanel();
        UploadBtn = new javax.swing.JButton();
        filePathLabel = new javax.swing.JLabel();
        lFileName = new javax.swing.JLabel();
        bSaveFile = new javax.swing.JButton();
        bRemoveSub = new javax.swing.JButton();
        moduleLabel = new javax.swing.JLabel();
        bBackAssignmentSubmission = new javax.swing.JButton();
        pSubmittedAssign = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        filePanel = new javax.swing.JPanel();
        lSubmittedFile = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        bSubmitSubmission = new javax.swing.JButton();
        bCancel = new javax.swing.JButton();
        FileSubmissionLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lSubmissionStatus = new javax.swing.JLabel();
        ModuleLabel1 = new javax.swing.JLabel();
        AssignmentLabel = new javax.swing.JLabel();
        Description = new javax.swing.JLabel();

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

        pDashboard.setBackground(new java.awt.Color(252, 247, 204));
        pDashboard.setPreferredSize(new java.awt.Dimension(1500, 780));

        mainTitleLabel.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        mainTitleLabel.setText("Student Dashboard");
        mainTitleLabel.setPreferredSize(new java.awt.Dimension(200, 160));

        jScrollPane1.setViewportView(pAssessment);

        javax.swing.GroupLayout pDashboardLayout = new javax.swing.GroupLayout(pDashboard);
        pDashboard.setLayout(pDashboardLayout);
        pDashboardLayout.setHorizontalGroup(
            pDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pDashboardLayout.createSequentialGroup()
                .addContainerGap(122, Short.MAX_VALUE)
                .addGroup(pDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mainTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(736, 736, 736))
        );
        pDashboardLayout.setVerticalGroup(
            pDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDashboardLayout.createSequentialGroup()
                .addComponent(mainTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(155, Short.MAX_VALUE))
        );

        pAssignmentSubmission.setBackground(new java.awt.Color(252, 247, 204));
        pAssignmentSubmission.setPreferredSize(new java.awt.Dimension(1500, 780));

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

        lFileName.setText("jLabel1");

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
                        .addGap(338, 338, 338)
                        .addComponent(lFileName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filePathLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 805, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        FilePanelLayout.setVerticalGroup(
            FilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FilePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(UploadBtn)
                .addGroup(FilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FilePanelLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(filePathLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(FilePanelLayout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(lFileName)))
                .addContainerGap(87, Short.MAX_VALUE))
        );

        bSaveFile.setText("Save");
        bSaveFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSaveFileActionPerformed(evt);
            }
        });

        bRemoveSub.setText("Remove");
        bRemoveSub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRemoveSubActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AssessmentPanelLayout = new javax.swing.GroupLayout(AssessmentPanel);
        AssessmentPanel.setLayout(AssessmentPanelLayout);
        AssessmentPanelLayout.setHorizontalGroup(
            AssessmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AssessmentPanelLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(FileSubmissionLabel)
                .addGap(18, 18, 18)
                .addComponent(FilePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(75, Short.MAX_VALUE))
            .addGroup(AssessmentPanelLayout.createSequentialGroup()
                .addGap(485, 485, 485)
                .addComponent(bSaveFile, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(bRemoveSub, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        AssessmentPanelLayout.setVerticalGroup(
            AssessmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AssessmentPanelLayout.createSequentialGroup()
                .addGroup(AssessmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AssessmentPanelLayout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(FilePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AssessmentPanelLayout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(FileSubmissionLabel)))
                .addGap(30, 30, 30)
                .addGroup(AssessmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bSaveFile, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bRemoveSub, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(181, Short.MAX_VALUE))
        );

        moduleLabel.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        moduleLabel.setPreferredSize(new java.awt.Dimension(200, 160));

        bBackAssignmentSubmission.setText("Back");
        bBackAssignmentSubmission.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBackAssignmentSubmissionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pAssignmentSubmissionLayout = new javax.swing.GroupLayout(pAssignmentSubmission);
        pAssignmentSubmission.setLayout(pAssignmentSubmissionLayout);
        pAssignmentSubmissionLayout.setHorizontalGroup(
            pAssignmentSubmissionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pAssignmentSubmissionLayout.createSequentialGroup()
                .addGap(294, 294, 294)
                .addGroup(pAssignmentSubmissionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pAssignmentSubmissionLayout.createSequentialGroup()
                        .addComponent(bBackAssignmentSubmission)
                        .addGap(274, 274, 274)
                        .addComponent(moduleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 1127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(AssessmentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pAssignmentSubmissionLayout.setVerticalGroup(
            pAssignmentSubmissionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pAssignmentSubmissionLayout.createSequentialGroup()
                .addGroup(pAssignmentSubmissionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pAssignmentSubmissionLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(moduleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pAssignmentSubmissionLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(bBackAssignmentSubmission)
                        .addGap(7, 7, 7)))
                .addComponent(AssessmentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pSubmittedAssign.setBackground(new java.awt.Color(252, 247, 204));
        pSubmittedAssign.setPreferredSize(new java.awt.Dimension(1500, 780));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Submission Status");

        filePanel.setBackground(new java.awt.Color(255, 255, 255));

        lSubmittedFile.setText("jLabel4");

        javax.swing.GroupLayout filePanelLayout = new javax.swing.GroupLayout(filePanel);
        filePanel.setLayout(filePanelLayout);
        filePanelLayout.setHorizontalGroup(
            filePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filePanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(lSubmittedFile)
                .addContainerGap(782, Short.MAX_VALUE))
        );
        filePanelLayout.setVerticalGroup(
            filePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filePanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lSubmittedFile)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("File Submission");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(filePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(filePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel2)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        bSubmitSubmission.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        bSubmitSubmission.setText("Submit/Edit Submission");
        bSubmitSubmission.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSubmitSubmissionActionPerformed(evt);
            }
        });

        bCancel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        bCancel.setText("Cancel");
        bCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelActionPerformed(evt);
            }
        });

        FileSubmissionLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        FileSubmissionLabel1.setText("Grading Status:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 216, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 56, Short.MAX_VALUE)
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Submission Status:");

        lSubmissionStatus.setText("jLabel4");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lSubmissionStatus)
                .addContainerGap(162, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lSubmissionStatus)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(375, 375, 375)
                        .addComponent(bSubmitSubmission)
                        .addGap(51, 51, 51)
                        .addComponent(bCancel))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(FileSubmissionLabel1)
                        .addGap(37, 37, 37)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1070, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(49, 49, 49))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)))
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bSubmitSubmission)
                    .addComponent(bCancel))
                .addGap(37, 37, 37)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(FileSubmissionLabel1)
                        .addGap(16, 16, 16)))
                .addGap(65, 65, 65))
        );

        ModuleLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        ModuleLabel1.setPreferredSize(new java.awt.Dimension(200, 160));

        AssignmentLabel.setText("jLabel4");

        Description.setText("jLabel4");

        javax.swing.GroupLayout pSubmittedAssignLayout = new javax.swing.GroupLayout(pSubmittedAssign);
        pSubmittedAssign.setLayout(pSubmittedAssignLayout);
        pSubmittedAssignLayout.setHorizontalGroup(
            pSubmittedAssignLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pSubmittedAssignLayout.createSequentialGroup()
                .addGap(315, 315, 315)
                .addGroup(pSubmittedAssignLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pSubmittedAssignLayout.createSequentialGroup()
                        .addComponent(AssignmentLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ModuleLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pSubmittedAssignLayout.createSequentialGroup()
                        .addGroup(pSubmittedAssignLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Description)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pSubmittedAssignLayout.setVerticalGroup(
            pSubmittedAssignLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pSubmittedAssignLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(pSubmittedAssignLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ModuleLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AssignmentLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Description)
                .addGap(8, 8, 8)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 1326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pAssignmentSubmission, javax.swing.GroupLayout.PREFERRED_SIZE, 1326, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pSubmittedAssign, javax.swing.GroupLayout.PREFERRED_SIZE, 1326, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, 849, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(45, 45, 45)
                    .addComponent(pAssignmentSubmission, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(46, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pSubmittedAssign, javax.swing.GroupLayout.DEFAULT_SIZE, 849, Short.MAX_VALUE)
                    .addContainerGap()))
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
        dispose();
    }//GEN-LAST:event_resultButtonActionPerformed

    private void profileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileButtonActionPerformed
        // TODO add your handling code here:
        new StudentProfile().setVisible(true);
        dispose();
    }//GEN-LAST:event_profileButtonActionPerformed

    private void UploadBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UploadBtnActionPerformed
        // TODO add your handling code here:
        if (rowOfAssignmentStudentSubmission != -1){
            JOptionPane.showMessageDialog(null, "Only can submit new file after remove old file.");
        }
        else{
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File file=chooser.getSelectedFile();
        if (file != null){
            String filePath = file.getAbsolutePath();
            sourceFile = new File(filePath);
            String FileName = filePath.substring(filePath.lastIndexOf("\\")+1);
            
            lFileName.setText(FileName);
            File destinationDir = new File ("src\\Project_Management_System\\storage\\" + assessmentID + "\\" +ID);
            strDesFile = "src\\Project_Management_System\\storage\\" + assessmentID +"\\" +ID + "\\" +FileName;
            destinationFile = new File (strDesFile);
            
                if (!destinationDir.exists()){
                    destinationDir.mkdirs();
                }
        }}
    }//GEN-LAST:event_UploadBtnActionPerformed

    private void bSaveFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveFileActionPerformed
        // TODO add your handling code here:
        if (rowOfAssignmentStudentSubmission != -1){
            JOptionPane.showMessageDialog(null, "Only can submit new file after remove old file.");
        } else{

            if (sourceFile != null) {
            String newRecord = "";
            
            List<String> lines = new ArrayList<>();
            try {
            BufferedReader reader = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assessment_student.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                rowOfAssignmentStudentSubmission= rowOfAssignmentStudentSubmission + 1;
                String[] record = line.split("\t");
                //Read and add all the lines in text file to variable line
                lines.add(line);
                if (record[0].equals(assessmentID) && record[1].equals(ID)) {
                        String[] oldRecord = line.split("\t");
                        oldRecord[2] = strDesFile;
                        newRecord = oldRecord[0] + "\t" +oldRecord[1] +"\t" + oldRecord[2] +"\t" + oldRecord[3] + "\t" +oldRecord[4];
                        break;
                    }
            }
                
            
            reader.close();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
            
            lines.set(rowOfAssignmentStudentSubmission, newRecord);
            
            
                try {
                    //Write the information to the text file
                    BufferedWriter writer = new BufferedWriter(new FileWriter("src\\Project_Management_System\\database\\assessment_student.txt"));
                    for (String line : lines){
                        
                        writer.write(line);
                    }
                    writer.close();
                    } 
                catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                
                try {
                    //Copy the file to folder
                    Files.copy(sourceFile.toPath(), destinationFile.toPath());
                    } 
                catch (Exception e) {
                   System.err.println(e.getMessage());
                }
                
                    refreshSubmissionPanel(assessmentID);

            pSubmittedAssign.setVisible(true);
            pDashboard.setVisible(false);
            pAssignmentSubmission.setVisible(false);

            }
            else{
                JOptionPane.showMessageDialog(null, "Please at least select a file");
            }
        }
        
    }//GEN-LAST:event_bSaveFileActionPerformed

    private void bRemoveSubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRemoveSubActionPerformed
        // TODO add your handling code here:
        String oldFilePath = null;
        
        
        if (sourceFile != null) {
            lFileName.setText("");
            }
            else{
                if (rowOfAssignmentStudentSubmission != -1){



                    List<String> lines = new ArrayList<>();
                    String newRecord = "";

                    try {

                    BufferedReader reader = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assessment_student.txt"));
                    String line;

                    while ((line = reader.readLine()) != null) {
                        String[] record = line.split("\t");
                        //Read and add all the lines in text file to variable line
                        lines.add(line);
                            //skip the speific record
                            if (record[0].equals(assessmentID) && record[1].equals(ID)) {
                                String[] oldRecord = record;
                                newRecord = oldRecord[0] +"\t" + oldRecord[1] + "\t" +"NA" +"\t" + oldRecord[3] +"\t" + oldRecord[4];
                                oldFilePath = line.split("\t")[2];
                            }
                    }
                    reader.close();
                }
                catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                    lines.set(rowOfAssignmentStudentSubmission, newRecord);

                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter("src\\Project_Management_System\\database\\assessment_student.txt"));
                        for (String updatedLine : lines) {
                            //rewrite other line to file
                            writer.write(updatedLine);
                            writer.newLine();

                        }
                        writer.close();
                        }
                    catch (Exception e) {
                       e.getMessage();
                    }

                    try {
                        //Delete the old file
                       File oldFile = new File (oldFilePath);
                       oldFile.delete();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    
                    refreshSubmissionPanel(assessmentID);
            
            pSubmittedAssign.setVisible(true);
            pDashboard.setVisible(false);
            pAssignmentSubmission.setVisible(false);

                }
                else{
                    JOptionPane.showMessageDialog(null, "There is no file to be remove.");
                }
        }
        
        
            sourceFile = null;
            destinationFile = null;
        
            
    }//GEN-LAST:event_bRemoveSubActionPerformed

    private void bSubmitSubmissionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSubmitSubmissionActionPerformed
        // TODO add your handling code here:
        
        lFileName.setText(lSubmittedFile.getText());
        
        pAssignmentSubmission.setVisible(true);
        pSubmittedAssign.setVisible(false);
        pDashboard.setVisible(false);
        
    }//GEN-LAST:event_bSubmitSubmissionActionPerformed

    private void bCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelActionPerformed
        // TODO add your handling code here:
        
        rowOfAssignmentStudentSubmission = -1;
        
        
        pDashboard.setVisible(true);
        pAssignmentSubmission.setVisible(false);
        pSubmittedAssign.setVisible(false);
    }//GEN-LAST:event_bCancelActionPerformed

    private void bBackAssignmentSubmissionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBackAssignmentSubmissionActionPerformed
        // TODO add your handling code here:
        sourceFile = null;
        destinationFile = null;
        
        refreshSubmissionPanel(assessmentID);
        
        pSubmittedAssign.setVisible(true);
        pAssignmentSubmission.setVisible(false);
        pDashboard.setVisible(false);
    }//GEN-LAST:event_bBackAssignmentSubmissionActionPerformed

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
            java.util.logging.Logger.getLogger(StudentHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new StudentHome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AssessmentPanel;
    private javax.swing.JLabel AssignmentLabel;
    private javax.swing.JLabel Description;
    private javax.swing.JPanel FilePanel;
    private javax.swing.JLabel FileSubmissionLabel;
    private javax.swing.JLabel FileSubmissionLabel1;
    private javax.swing.JLabel ModuleLabel1;
    private javax.swing.JButton UploadBtn;
    private javax.swing.JButton bBackAssignmentSubmission;
    private javax.swing.JButton bCancel;
    private javax.swing.JButton bRemoveSub;
    private javax.swing.JButton bSaveFile;
    private javax.swing.JButton bSubmitSubmission;
    private javax.swing.JPanel filePanel;
    private javax.swing.JLabel filePathLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lFileName;
    private javax.swing.JLabel lSubmissionStatus;
    private javax.swing.JLabel lSubmittedFile;
    private javax.swing.JLabel mainTitleLabel;
    private javax.swing.JLabel moduleLabel;
    private javax.swing.JPanel pAssessment;
    private javax.swing.JPanel pAssignmentSubmission;
    private javax.swing.JPanel pDashboard;
    private javax.swing.JPanel pSubmittedAssign;
    private javax.swing.JButton presentationButton;
    private javax.swing.JButton profileButton;
    private javax.swing.JButton resultButton;
    private javax.swing.JPanel sidePanel;
    // End of variables declaration//GEN-END:variables
}
