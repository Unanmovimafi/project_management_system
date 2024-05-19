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
import javax.swing.table.DefaultTableModel;

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
    private String[] studentRecord = new String[11];
    private int rowOfStudentRecord = -1;
    
    private int rowOfAssignmentStudentSubmission = -1;
    
    public List<String> getAllStudentRecord() {
        try {
            
            List<String> lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\student.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                //Read and add all the lines in text file to variable line
                lines.add(line);
            }
            reader.close();
            return lines;
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    } 
    
    private String calculateGrade(int mark) {
        if (mark >= 80) {
            return "(A+)";
        } else if (mark >= 75) {
            return "(A)";
        } else if (mark >= 70) {
            return "(B+)";
        } else if (mark >= 65) {
            return "(B)";
        } else if (mark >= 60) {
            return "(C+)";
        } else if (mark >= 55) {
            return "(C)";
        } else if (mark >= 50) {
            return "(C-)";
        } else if (mark >= 40) {
            return "(D)";
        } else if (mark >= 30) {
            return "(F+)";
        } else if (mark >= 20) {
            return "(F)";
        } else {
            return "(F-)";
        }
    }
        
    public void refreshResult() {
        DefaultTableModel model = (DefaultTableModel)tResult.getModel();
        model.setRowCount(0);
        
         try {
            BufferedReader assessmentStudentReader = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assessment_student.txt"));
            BufferedReader assessmentReader = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assessment.txt"));

            String assessmentStudentLine, assessmentLine;

           while ((assessmentStudentLine = assessmentStudentReader.readLine()) != null) {
            String[] assessmentStudentRecord = assessmentStudentLine.split("\t");

            // Find assessment details
            String assessmentName = "";
            while ((assessmentLine = assessmentReader.readLine()) != null) {
                String[] assessmentRecord = assessmentLine.split("\t");
                if (assessmentRecord[0].equals(assessmentStudentRecord[0])) {
                    assessmentName = assessmentRecord[1];
                    break;
                }
            }
            
            String strAverage;
            
            String firstMark;
            String gradeA;
            try {
            firstMark = Integer.toString(Integer.parseInt(assessmentStudentRecord[3]));
            gradeA = calculateGrade(Integer.parseInt(firstMark));
            }catch (Exception e) {
                firstMark = "NA";
                gradeA = "";
            }
            
            String secondMark;
            String gradeB;
            
            try {
            secondMark = Integer.toString(Integer.parseInt(assessmentStudentRecord[4]));
            gradeB = calculateGrade(Integer.parseInt(secondMark));
            }catch (Exception e) {
                secondMark= "NA";
                gradeB = "";
            }
            
            
            String average;
            String gradeC;
            
            try {
                average = Integer.toString((Integer.parseInt(firstMark) + Integer.parseInt(secondMark)) / 2);
                gradeC = calculateGrade(Integer.parseInt(average));
            }catch (Exception e) {
                average = "NA";
                gradeC = "";
            }
            
            model.addRow(new Object[]{assessmentStudentRecord[0], assessmentName, firstMark + gradeA, secondMark+gradeB, average + gradeC});
        }

        assessmentStudentReader.close();
        assessmentReader.close();
    } catch (Exception e) {
        e.printStackTrace(); // Print stack trace for any exceptions
    }
    }
    
        
    
    
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
        pProfile.setVisible(false);
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
        bDashboard = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        pResult = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tResult = new javax.swing.JTable();
        pDashboard = new javax.swing.JPanel();
        mainTitleLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pAssessment = new javax.swing.JPanel();
        pAssignmentSubmission = new javax.swing.JPanel();
        AssessmentPanel = new javax.swing.JPanel();
        FileSubmissionLabel = new javax.swing.JLabel();
        FilePanel = new javax.swing.JPanel();
        UploadBtn = new javax.swing.JButton();
        lFileName = new javax.swing.JLabel();
        bSaveFile = new javax.swing.JButton();
        bRemoveSub = new javax.swing.JButton();
        bBackAssignmentSubmission = new javax.swing.JButton();
        moduleLabel = new javax.swing.JLabel();
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
        Description = new javax.swing.JLabel();
        DescriptionTitle = new javax.swing.JLabel();
        lSubmissionStatus = new javax.swing.JLabel();
        ModuleLabel1 = new javax.swing.JLabel();
        pProfile = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tfID = new javax.swing.JTextField();
        tfName = new javax.swing.JTextField();
        tfContactNumber = new javax.swing.JTextField();
        tfOldPassword = new javax.swing.JTextField();
        tfEmail = new javax.swing.JTextField();
        tfAddress = new javax.swing.JTextField();
        rbMale = new javax.swing.JRadioButton();
        rbFemale = new javax.swing.JRadioButton();
        bChangePassword = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        tfIntakeCode = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        tfNationality = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        tfIC = new javax.swing.JTextField();
        tfDoB = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        tfNewPassword = new javax.swing.JTextField();
        tfConfirmPassword = new javax.swing.JTextField();

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

        bDashboard.setBackground(new java.awt.Color(255, 255, 204));
        bDashboard.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        bDashboard.setText("Dashboard");
        bDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDashboardActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sidePanelLayout = new javax.swing.GroupLayout(sidePanel);
        sidePanel.setLayout(sidePanelLayout);
        sidePanelLayout.setHorizontalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(resultButton, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                    .addComponent(presentationButton, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                    .addComponent(profileButton, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                    .addComponent(bDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        sidePanelLayout.setVerticalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(presentationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(resultButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(profileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(393, 393, 393))
        );

        tResult.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Assesment Code", "Assesment Name", "First Mark", "Second Mark", "Average Mark"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tResult);

        javax.swing.GroupLayout pResultLayout = new javax.swing.GroupLayout(pResult);
        pResult.setLayout(pResultLayout);
        pResultLayout.setHorizontalGroup(
            pResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pResultLayout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1458, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1146, Short.MAX_VALUE))
        );
        pResultLayout.setVerticalGroup(
            pResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pResultLayout.createSequentialGroup()
                .addGap(145, 145, 145)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(117, Short.MAX_VALUE))
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
                .addGap(28, 28, 28)
                .addComponent(mainTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(140, Short.MAX_VALUE))
        );

        pAssignmentSubmission.setBackground(new java.awt.Color(252, 247, 204));
        pAssignmentSubmission.setPreferredSize(new java.awt.Dimension(1500, 780));

        AssessmentPanel.setBackground(new java.awt.Color(255, 255, 255));
        AssessmentPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        FileSubmissionLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        FileSubmissionLabel.setText("File Submission:");

        UploadBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        UploadBtn.setText("Upload a File");
        UploadBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UploadBtnActionPerformed(evt);
            }
        });

        lFileName.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lFileName.setText("jLabel1");

        javax.swing.GroupLayout FilePanelLayout = new javax.swing.GroupLayout(FilePanel);
        FilePanel.setLayout(FilePanelLayout);
        FilePanelLayout.setHorizontalGroup(
            FilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FilePanelLayout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(UploadBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 629, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FilePanelLayout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addComponent(lFileName, javax.swing.GroupLayout.PREFERRED_SIZE, 809, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );
        FilePanelLayout.setVerticalGroup(
            FilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FilePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(UploadBtn)
                .addGap(39, 39, 39)
                .addComponent(lFileName, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        bSaveFile.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bSaveFile.setText("Save");
        bSaveFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSaveFileActionPerformed(evt);
            }
        });

        bRemoveSub.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bRemoveSub.setText("Remove");
        bRemoveSub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRemoveSubActionPerformed(evt);
            }
        });

        bBackAssignmentSubmission.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bBackAssignmentSubmission.setText("Back");
        bBackAssignmentSubmission.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBackAssignmentSubmissionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AssessmentPanelLayout = new javax.swing.GroupLayout(AssessmentPanel);
        AssessmentPanel.setLayout(AssessmentPanelLayout);
        AssessmentPanelLayout.setHorizontalGroup(
            AssessmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AssessmentPanelLayout.createSequentialGroup()
                .addGroup(AssessmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AssessmentPanelLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(FileSubmissionLabel)
                        .addGap(40, 40, 40)
                        .addComponent(FilePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AssessmentPanelLayout.createSequentialGroup()
                        .addGap(485, 485, 485)
                        .addComponent(bSaveFile, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(bRemoveSub, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AssessmentPanelLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(bBackAssignmentSubmission, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        AssessmentPanelLayout.setVerticalGroup(
            AssessmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AssessmentPanelLayout.createSequentialGroup()
                .addGroup(AssessmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AssessmentPanelLayout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(FilePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AssessmentPanelLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(bBackAssignmentSubmission)
                        .addGap(118, 118, 118)
                        .addComponent(FileSubmissionLabel)))
                .addGap(30, 30, 30)
                .addGroup(AssessmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bSaveFile, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bRemoveSub, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(118, Short.MAX_VALUE))
        );

        moduleLabel.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        moduleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        moduleLabel.setPreferredSize(new java.awt.Dimension(200, 160));

        javax.swing.GroupLayout pAssignmentSubmissionLayout = new javax.swing.GroupLayout(pAssignmentSubmission);
        pAssignmentSubmission.setLayout(pAssignmentSubmissionLayout);
        pAssignmentSubmissionLayout.setHorizontalGroup(
            pAssignmentSubmissionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pAssignmentSubmissionLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pAssignmentSubmissionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(moduleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(AssessmentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(311, Short.MAX_VALUE))
        );
        pAssignmentSubmissionLayout.setVerticalGroup(
            pAssignmentSubmissionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pAssignmentSubmissionLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(moduleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
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

        lSubmittedFile.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lSubmittedFile.setText("jLabel4");

        javax.swing.GroupLayout filePanelLayout = new javax.swing.GroupLayout(filePanel);
        filePanel.setLayout(filePanelLayout);
        filePanelLayout.setHorizontalGroup(
            filePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lSubmittedFile, javax.swing.GroupLayout.PREFERRED_SIZE, 799, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        filePanelLayout.setVerticalGroup(
            filePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filePanelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lSubmittedFile)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("File Submission");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
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

        Description.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Description.setText("jLabel4");

        DescriptionTitle.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        DescriptionTitle.setText("Description:");

        lSubmissionStatus.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lSubmissionStatus.setText("jLabel4");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(FileSubmissionLabel1)
                        .addGap(30, 30, 30)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1070, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Description)
                                    .addComponent(lSubmissionStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(DescriptionTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(376, 376, 376)
                        .addComponent(bSubmitSubmission)
                        .addGap(46, 46, 46)
                        .addComponent(bCancel))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DescriptionTitle)
                    .addComponent(Description))
                .addGap(42, 42, 42)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lSubmissionStatus))
                .addGap(46, 46, 46)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bSubmitSubmission)
                    .addComponent(bCancel))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(FileSubmissionLabel1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33))
        );

        ModuleLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        ModuleLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ModuleLabel1.setPreferredSize(new java.awt.Dimension(200, 160));

        javax.swing.GroupLayout pSubmittedAssignLayout = new javax.swing.GroupLayout(pSubmittedAssign);
        pSubmittedAssign.setLayout(pSubmittedAssignLayout);
        pSubmittedAssignLayout.setHorizontalGroup(
            pSubmittedAssignLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pSubmittedAssignLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(pSubmittedAssignLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ModuleLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(128, Short.MAX_VALUE))
        );
        pSubmittedAssignLayout.setVerticalGroup(
            pSubmittedAssignLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pSubmittedAssignLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(ModuleLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pProfile.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("ID:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Name:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Contact Number:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Gender:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Address:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Email:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Nationality:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Old Password:");

        tfID.setEditable(false);

        tfName.setEditable(false);

        tfContactNumber.setEditable(false);

        tfEmail.setEditable(false);

        tfAddress.setEditable(false);

        rbMale.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rbMale.setText("Male");

        rbFemale.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rbFemale.setText("Female");

        bChangePassword.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bChangePassword.setText("Change Password");
        bChangePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bChangePasswordActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Intake Code:");

        tfIntakeCode.setEditable(false);
        tfIntakeCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfIntakeCodeActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("DOB:");

        tfNationality.setEditable(false);

        jLabel14.setText("IC/Passport No.:");

        tfIC.setEditable(false);

        tfDoB.setEditable(false);

        jLabel15.setText("New Password:");

        jLabel16.setText("Confirm Password:");

        javax.swing.GroupLayout pProfileLayout = new javax.swing.GroupLayout(pProfile);
        pProfile.setLayout(pProfileLayout);
        pProfileLayout.setHorizontalGroup(
            pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pProfileLayout.createSequentialGroup()
                .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pProfileLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(pProfileLayout.createSequentialGroup()
                                    .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel14)
                                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addComponent(jLabel16))
                                    .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pProfileLayout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(tfAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(pProfileLayout.createSequentialGroup()
                                            .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(pProfileLayout.createSequentialGroup()
                                                    .addGap(22, 22, 22)
                                                    .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(tfID, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(tfName, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                                                        .addGroup(pProfileLayout.createSequentialGroup()
                                                            .addComponent(rbMale, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(rbFemale, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(tfIntakeCode)
                                                        .addComponent(tfIC)))
                                                .addGroup(pProfileLayout.createSequentialGroup()
                                                    .addGap(18, 18, 18)
                                                    .addComponent(tfNationality, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(pProfileLayout.createSequentialGroup()
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(tfOldPassword)
                                                        .addComponent(tfNewPassword)
                                                        .addComponent(tfConfirmPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE))))
                                            .addGap(5, 5, 5)))))))
                    .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(pProfileLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(tfDoB, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pProfileLayout.createSequentialGroup()
                            .addGap(39, 39, 39)
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tfContactNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pProfileLayout.createSequentialGroup()
                        .addGap(329, 329, 329)
                        .addComponent(bChangePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(673, Short.MAX_VALUE))
        );
        pProfileLayout.setVerticalGroup(
            pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pProfileLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tfID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(tfIC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfIntakeCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(34, 34, 34)
                .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(rbMale)
                    .addComponent(rbFemale))
                .addGap(18, 18, 18)
                .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(tfNationality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(tfDoB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tfContactNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(71, 71, 71)
                .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tfAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(tfOldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(tfNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(tfConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addComponent(bChangePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(144, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pAssignmentSubmission, javax.swing.GroupLayout.PREFERRED_SIZE, 1326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 1326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pSubmittedAssign, javax.swing.GroupLayout.PREFERRED_SIZE, 1326, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pResult, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(pProfile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 1357, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, 862, Short.MAX_VALUE)
                    .addComponent(pAssignmentSubmission, javax.swing.GroupLayout.DEFAULT_SIZE, 862, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pSubmittedAssign, javax.swing.GroupLayout.DEFAULT_SIZE, 862, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pResult, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pProfile, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addComponent(sidePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 874, Short.MAX_VALUE)
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
        refreshResult();
        
        pResult.setVisible(true);
        
        pDashboard.setVisible(false);
        pAssignmentSubmission.setVisible(false);
        pSubmittedAssign.setVisible(false);
        pProfile.setVisible(false);
    }//GEN-LAST:event_resultButtonActionPerformed

    private void profileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileButtonActionPerformed
        // TODO add your handling code here:
        pProfile.setVisible(true);
        
        pResult.setVisible(false);
        pDashboard.setVisible(false);
        pAssignmentSubmission.setVisible(false);
        pSubmittedAssign.setVisible(false);
        
        try (BufferedReader reader = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\student.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                studentRecord = line.split("\t");
                rowOfStudentRecord ++;
                if (studentRecord[0].equals(ID)) {
                    // reutrn the line of the line_num in text file
                    break;
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        
        tfID.setText(studentRecord[0]);
        tfName.setText(studentRecord[1]);
        tfIC.setText(studentRecord[2]);
        tfIntakeCode.setText(studentRecord[3]);
        
        if (studentRecord[4].equals("MALE")){
            rbMale.setSelected(true);
        }
        else if (studentRecord[4].equals("FEMALE")){
            rbFemale.setSelected(true);
        }
        
        tfNationality.setText(studentRecord[5]);
        tfDoB.setText(studentRecord[6]);
        tfContactNumber.setText(studentRecord[7]);
        
        tfEmail.setText(studentRecord[9]);
        tfAddress.setText(studentRecord[10]);
    }//GEN-LAST:event_profileButtonActionPerformed

    private void bSubmitSubmissionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSubmitSubmissionActionPerformed
        // TODO add your handling code here:
        sourceFile = null;
        destinationFile = null;
        
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

        refreshSubmissionPanel(assessmentID);

        pSubmittedAssign.setVisible(true);
        pAssignmentSubmission.setVisible(false);
        pDashboard.setVisible(false);
        pResult.setVisible(false);
        pProfile.setVisible(false);
    }//GEN-LAST:event_bBackAssignmentSubmissionActionPerformed

    private void bRemoveSubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRemoveSubActionPerformed
        // TODO add your handling code here:
        String oldFilePath = null;

        if (sourceFile != null) {
            lFileName.setText("");
            sourceFile = null;
            destinationFile = null;
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
    }//GEN-LAST:event_bRemoveSubActionPerformed

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

    private void bDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDashboardActionPerformed
        // TODO add your handling code here:
        pDashboard.setVisible(true);
        
        pAssignmentSubmission.setVisible(false);
        pSubmittedAssign.setVisible(false);
        pResult.setVisible(false);
        pProfile.setVisible(false);
        
    }//GEN-LAST:event_bDashboardActionPerformed

    private void tfIntakeCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfIntakeCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfIntakeCodeActionPerformed

    private void bChangePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bChangePasswordActionPerformed
        // TODO add your handling code here:
        if (tfOldPassword.getText().isEmpty()|| tfNewPassword.getText().isEmpty()|| tfConfirmPassword.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter all fields.");
        }

        else if(tfOldPassword.getText().equals(studentRecord[8]) && (tfNewPassword.getText().equals(tfConfirmPassword.getText()))){
            //Get the value from each text field
            studentRecord[8] = tfNewPassword.getText();

            try {
                List<String> lines = getAllStudentRecord();
                //Write the information to the text file
                String editedStudent = String.join("\t", studentRecord);

                lines.set(rowOfStudentRecord, editedStudent);
                BufferedWriter writer = new BufferedWriter(new FileWriter("src\\Project_Management_System\\database\\student.txt"));
                for (String updatedLine : lines) {
                    writer.write(updatedLine);
                    writer.newLine();
                }

                writer.close();

            }
            catch (Exception e) {
                System.err.println(e.getMessage());
            }

            JOptionPane.showMessageDialog(null, "Successfully Saved!");
            pDashboard.setVisible(true);
        
            pAssignmentSubmission.setVisible(false);
            pSubmittedAssign.setVisible(false);
            pResult.setVisible(false);
            pProfile.setVisible(false);
        }
        else{
            JOptionPane.showMessageDialog(null, "Password Wrong!");
        }
    }//GEN-LAST:event_bChangePasswordActionPerformed

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
    private javax.swing.JLabel Description;
    private javax.swing.JLabel DescriptionTitle;
    private javax.swing.JPanel FilePanel;
    private javax.swing.JLabel FileSubmissionLabel;
    private javax.swing.JLabel FileSubmissionLabel1;
    private javax.swing.JLabel ModuleLabel1;
    private javax.swing.JButton UploadBtn;
    private javax.swing.JButton bBackAssignmentSubmission;
    private javax.swing.JButton bCancel;
    private javax.swing.JButton bChangePassword;
    private javax.swing.JButton bDashboard;
    private javax.swing.JButton bRemoveSub;
    private javax.swing.JButton bSaveFile;
    private javax.swing.JButton bSubmitSubmission;
    private javax.swing.JPanel filePanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lFileName;
    private javax.swing.JLabel lSubmissionStatus;
    private javax.swing.JLabel lSubmittedFile;
    private javax.swing.JLabel mainTitleLabel;
    private javax.swing.JLabel moduleLabel;
    private javax.swing.JPanel pAssessment;
    private javax.swing.JPanel pAssignmentSubmission;
    private javax.swing.JPanel pDashboard;
    private javax.swing.JPanel pProfile;
    private javax.swing.JPanel pResult;
    private javax.swing.JPanel pSubmittedAssign;
    private javax.swing.JButton presentationButton;
    private javax.swing.JButton profileButton;
    private javax.swing.JRadioButton rbFemale;
    private javax.swing.JRadioButton rbMale;
    private javax.swing.JButton resultButton;
    private javax.swing.JPanel sidePanel;
    private javax.swing.JTable tResult;
    private javax.swing.JTextField tfAddress;
    private javax.swing.JTextField tfConfirmPassword;
    private javax.swing.JTextField tfContactNumber;
    private javax.swing.JTextField tfDoB;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfIC;
    private javax.swing.JTextField tfID;
    private javax.swing.JTextField tfIntakeCode;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfNationality;
    private javax.swing.JTextField tfNewPassword;
    private javax.swing.JTextField tfOldPassword;
    // End of variables declaration//GEN-END:variables
}
