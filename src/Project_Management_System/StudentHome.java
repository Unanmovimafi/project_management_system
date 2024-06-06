/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Project_Management_System;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
    
    ImageIcon icon = new ImageIcon("src\\Project_Management_System\\logo\\University_Logo.png");
    private Image scaledLogo = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
    ImageIcon scaledIcon = new ImageIcon(scaledLogo);

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
        } catch (Exception e) {
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
        DefaultTableModel model = (DefaultTableModel) tResult.getModel();
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
                } catch (Exception e) {
                    firstMark = "NA";
                    gradeA = "";
                }

                String secondMark;
                String gradeB;

                try {
                    secondMark = Integer.toString(Integer.parseInt(assessmentStudentRecord[4]));
                    gradeB = calculateGrade(Integer.parseInt(secondMark));
                } catch (Exception e) {
                    secondMark = "NA";
                    gradeB = "";
                }

                String average;
                String gradeC;

                try {
                    average = Integer.toString((Integer.parseInt(firstMark) + Integer.parseInt(secondMark)) / 2);
                    gradeC = calculateGrade(Integer.parseInt(average));
                } catch (Exception e) {
                    average = "NA";
                    gradeC = "";
                }

                model.addRow(new Object[]{assessmentStudentRecord[0], assessmentName, firstMark + gradeA, secondMark + gradeB, average + gradeC});
            }

            assessmentStudentReader.close();
            assessmentReader.close();
        } catch (Exception e) {
            e.printStackTrace(); // Print stack trace for any exceptions
        }
    }
    
    
    public void setHello() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\student.txt"))) {
            String line;
            String[] record;
            while ((line = reader.readLine()) != null) {
                record = line.split("\t");
                if (record[0].equals(ID)) {
                    // reutrn the line of the line_num in text file
                    lHelloWorld.setText("Hi " + record[1]);
                    break;
                }

            }
        } catch (Exception e) {
            e.getMessage();
        }

    }
   

    public void setID(String ID) {
        this.ID = ID;
        setHello();
        createAssessmentPanels("IN PROGRESS");
    }

    public void refreshSubmissionPanel(String IDOfAssessment) {
        String line2;
        boolean isSubmittedFile = false;
        String submittedFile = "";
        rowOfAssignmentStudentSubmission = -1;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            BufferedReader br2 = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assessment_student.txt"));
            while ((line2 = br2.readLine()) != null) {
                String[] record2 = line2.split("\t");
                rowOfAssignmentStudentSubmission++;

                if (record2[0].equals(assessmentID) && record2[1].equals(ID)) {
                    svGrade.setText(record2[5]);
                    svFeedback.setText(record2[6]);
                    smGrade.setText(record2[7]);
                    smFeedback.setText(record2[8]);
                    lSubmitFileDate.setText(record2[3]);
                    
                    lSubmitFileTime.setText(record2[4]);

                    if (!record2[2].equals("NA")) {
                        submittedFile = record2[2];
                        isSubmittedFile = true;
                    }
                    System.out.println("212");
                    if (record2[11].equals("PENDING")) {
                        dcBookPresentation.setEnabled(false);
                        tfBookPresentationTime.setEnabled(false);
                        dcBookPresentation.setDate(dateFormat.parse(record2[9]));
                        tfBookPresentationTime.setText(record2[10]);
                        lPresentationStatus.setText("Pending");
                        bBookPresentation.setEnabled(false);
                    } else if (record2[11].equals("ACCEPT")) {
                        dcBookPresentation.setEnabled(false);
                        tfBookPresentationTime.setEnabled(false);
                        dcBookPresentation.setDate(dateFormat.parse(record2[9]));
                        System.out.println(dcBookPresentation);
                        tfBookPresentationTime.setText(record2[10]);
                        lPresentationStatus.setText("Accept");
                        bBookPresentation.setEnabled(false);
                    } else if (record2[11].equals("REJECT")) {
                        dcBookPresentation.setEnabled(true);
                        tfBookPresentationTime.setEnabled(true);
                        dcBookPresentation.setDate(dateFormat.parse(record2[9]));
                        tfBookPresentationTime.setText(record2[10]);
                        lPresentationStatus.setText("Reject (Please re-select your presentation date & time)");
                        bBookPresentation.setEnabled(true);
                    } else if (record2[11].equals("NA")) {
                        dcBookPresentation.setEnabled(true);
                        tfBookPresentationTime.setEnabled(true);
                        lPresentationStatus.setText("Haven't Booked Any Presentation");
                        bBookPresentation.setEnabled(true);
                    }
                }

            }

        } catch (Exception e) {
            e.getMessage();
        }

        String line;
        String[] assessInfo = new String[8];

        try {
            BufferedReader br = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assessment.txt"));
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

        if (isSubmittedFile) {
            String FileName = submittedFile.substring(submittedFile.lastIndexOf("\\") + 1);
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

    private void createAssessmentPanels(String PassOrPresent) {

        pAssessment.removeAll();
        LocalDateTime presentDate = LocalDateTime.now();
        Date presentDateAsDate = Date.from(presentDate.atZone(ZoneId.systemDefault()).toInstant());
        String line;
        String line2;
        int count = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            BufferedReader br2 = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assessment_student.txt"));
            while ((line2 = br2.readLine()) != null) {
                String[] record2 = line2.split("\t");
                try {
                    BufferedReader br = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assessment.txt"));
                    while ((line = br.readLine()) != null) {
                        String[] record = line.split("\t");
                        Date dueDate = dateFormat.parse(record[4]);
                        if (dueDate.compareTo(presentDateAsDate) >= 0 && PassOrPresent.equals("IN PROGRESS")) {

                            if (ID.equals(record2[1]) && record2[0].equals(record[0])) {
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

                                        pAssessmentStatus.setVisible(true);
                                        pDashboard.setVisible(false);
                                        pSubmitAssessment.setVisible(false);
                                        pProfile.setVisible(false);
                                        pResult.setVisible(false);

                                        moduleLabel.setText(record[1]);
                                        ModuleLabel1.setText(record[1]);
                                        
                                        bSubmitSubmission.setEnabled(true);
                                    }
                                });
                                pAssessment.add(jPanel1);
                                count = count + 1;
                                break;
                            }
                        } else if (dueDate.compareTo(presentDateAsDate) < 0&& PassOrPresent.equals("PAST")) {
                            if (ID.equals(record2[1]) && record2[0].equals(record[0])) {
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

                                        pAssessmentStatus.setVisible(true);
                                        pDashboard.setVisible(false);
                                        pSubmitAssessment.setVisible(false);
                                        pProfile.setVisible(false);
                                        pResult.setVisible(false);
                                        
                                        moduleLabel.setText(record[1]);
                                        ModuleLabel1.setText(record[1]);
                                        bSubmitSubmission.setEnabled(false);
                                    }
                                });
                                pAssessment.add(jPanel1);
                                count = count + 1;
                                break;
                            }

                        }
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            }

        } catch (Exception e) {
            e.getMessage();
        }
        pAssessment.setPreferredSize(new Dimension(350, count * 55));
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
        pSubmitAssessment.setVisible(false);
        pAssessmentStatus.setVisible(false);
        pProfile.setVisible(false);
        pResult.setVisible(false);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        sidePanel = new javax.swing.JPanel();
        resultButton = new javax.swing.JButton();
        profileButton = new javax.swing.JButton();
        bDashboard = new javax.swing.JButton();
        studentLogoutBtn = new javax.swing.JButton();
        lHelloWorld = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        pResult = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tResult = new javax.swing.JTable();
        pDashboard = new javax.swing.JPanel();
        mainTitleLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pAssessment = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        pSubmitAssessment = new javax.swing.JPanel();
        AssessmentPanel = new javax.swing.JPanel();
        FileSubmissionLabel = new javax.swing.JLabel();
        FilePanel = new javax.swing.JPanel();
        UploadBtn = new javax.swing.JButton();
        lFileName = new javax.swing.JLabel();
        bSaveFile = new javax.swing.JButton();
        bRemoveSub = new javax.swing.JButton();
        bBackAssignmentSubmission = new javax.swing.JButton();
        moduleLabel = new javax.swing.JLabel();
        pAssessmentStatus = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        filePanel = new javax.swing.JPanel();
        lSubmittedFile = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        bSubmitSubmission = new javax.swing.JButton();
        bCancel = new javax.swing.JButton();
        FileSubmissionLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Description = new javax.swing.JLabel();
        DescriptionTitle = new javax.swing.JLabel();
        lSubmissionStatus = new javax.swing.JLabel();
        svGrade = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        svFeedback = new javax.swing.JLabel();
        smGrade = new javax.swing.JLabel();
        smFeedback = new javax.swing.JLabel();
        lSubmitFileDate = new javax.swing.JLabel();
        lSubmitFileTime = new javax.swing.JLabel();
        ModuleLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        tfBookPresentationTime = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lPresentationStatus = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        bBookPresentation = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        dcBookPresentation = new com.toedter.calendar.JDateChooser();
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

        resultButton.setBackground(new java.awt.Color(255, 255, 204));
        resultButton.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        resultButton.setText("RESULT");
        resultButton.setPreferredSize(new java.awt.Dimension(184, 40));
        resultButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resultButtonActionPerformed(evt);
            }
        });

        profileButton.setBackground(new java.awt.Color(255, 255, 204));
        profileButton.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        profileButton.setText("PROFILE");
        profileButton.setPreferredSize(new java.awt.Dimension(184, 40));
        profileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileButtonActionPerformed(evt);
            }
        });

        bDashboard.setBackground(new java.awt.Color(255, 255, 204));
        bDashboard.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        bDashboard.setText("DASHBOARD");
        bDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDashboardActionPerformed(evt);
            }
        });

        studentLogoutBtn.setBackground(new java.awt.Color(255, 255, 204));
        studentLogoutBtn.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        studentLogoutBtn.setText("LOGOUT");
        studentLogoutBtn.setPreferredSize(new java.awt.Dimension(184, 40));
        studentLogoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentLogoutBtnActionPerformed(evt);
            }
        });

        lHelloWorld.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        lHelloWorld.setForeground(new java.awt.Color(255, 255, 255));
        lHelloWorld.setText("jLabel22");

        jLabel22.setIcon(scaledIcon);

        javax.swing.GroupLayout sidePanelLayout = new javax.swing.GroupLayout(sidePanel);
        sidePanel.setLayout(sidePanelLayout);
        sidePanelLayout.setHorizontalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addGroup(sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sidePanelLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(resultButton, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                            .addComponent(profileButton, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                            .addComponent(bDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(sidePanelLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(studentLogoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sidePanelLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sidePanelLayout.createSequentialGroup()
                .addGap(0, 31, Short.MAX_VALUE)
                .addComponent(lHelloWorld, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        sidePanelLayout.setVerticalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(lHelloWorld)
                .addGap(46, 46, 46)
                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                .addGap(31, 31, 31)
                .addComponent(bDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(resultButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(profileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(317, 317, 317)
                .addComponent(studentLogoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        pResult.setBackground(new java.awt.Color(255, 255, 204));

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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1422, Short.MAX_VALUE))
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

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "IN PROGRESS", "PAST" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pDashboardLayout = new javax.swing.GroupLayout(pDashboard);
        pDashboard.setLayout(pDashboardLayout);
        pDashboardLayout.setHorizontalGroup(
            pDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pDashboardLayout.createSequentialGroup()
                .addContainerGap(122, Short.MAX_VALUE)
                .addGroup(pDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(99, Short.MAX_VALUE))
        );

        pSubmitAssessment.setBackground(new java.awt.Color(252, 247, 204));
        pSubmitAssessment.setPreferredSize(new java.awt.Dimension(1500, 780));

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

        javax.swing.GroupLayout pSubmitAssessmentLayout = new javax.swing.GroupLayout(pSubmitAssessment);
        pSubmitAssessment.setLayout(pSubmitAssessmentLayout);
        pSubmitAssessmentLayout.setHorizontalGroup(
            pSubmitAssessmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pSubmitAssessmentLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pSubmitAssessmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(moduleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(AssessmentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(137, Short.MAX_VALUE))
        );
        pSubmitAssessmentLayout.setVerticalGroup(
            pSubmitAssessmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pSubmitAssessmentLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(moduleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(AssessmentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pAssessmentStatus.setBackground(new java.awt.Color(252, 247, 204));
        pAssessmentStatus.setPreferredSize(new java.awt.Dimension(1500, 780));

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
                .addComponent(lSubmittedFile, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
                .addContainerGap())
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(filePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
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

        bSubmitSubmission.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bSubmitSubmission.setText("Submit/Edit Submission");
        bSubmitSubmission.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSubmitSubmissionActionPerformed(evt);
            }
        });

        bCancel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bCancel.setText("Cancel");
        bCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelActionPerformed(evt);
            }
        });

        FileSubmissionLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        FileSubmissionLabel1.setText("Grade:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Submission Status:");

        Description.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Description.setText("jLabel4");

        DescriptionTitle.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        DescriptionTitle.setText("Description:");

        lSubmissionStatus.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lSubmissionStatus.setText("jLabel4");

        svGrade.setText("Grade");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel18.setText("Feedback:");

        svFeedback.setText("Feedback");

        smGrade.setText("SecondMarker Grade");

        smFeedback.setText("SecondMarker Feedback");

        lSubmitFileDate.setText("Submission Date");

        lSubmitFileTime.setText("Submission Time");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1070, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(DescriptionTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Description)
                                    .addComponent(lSubmissionStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(287, 287, 287)
                        .addComponent(bSubmitSubmission)
                        .addGap(46, 46, 46)
                        .addComponent(bCancel))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(FileSubmissionLabel1)
                            .addComponent(jLabel18))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(svGrade)
                            .addComponent(smFeedback, javax.swing.GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)
                            .addComponent(svFeedback, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(smGrade)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lSubmitFileDate)
                .addGap(18, 18, 18)
                .addComponent(lSubmitFileTime)
                .addGap(400, 400, 400))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DescriptionTitle)
                    .addComponent(Description))
                .addGap(39, 39, 39)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lSubmissionStatus))
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lSubmitFileDate)
                    .addComponent(lSubmitFileTime))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bSubmitSubmission)
                    .addComponent(bCancel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FileSubmissionLabel1)
                    .addComponent(svGrade)
                    .addComponent(smGrade))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(svFeedback))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(smFeedback)
                .addGap(18, 18, 18))
        );

        ModuleLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        ModuleLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ModuleLabel1.setPreferredSize(new java.awt.Dimension(200, 160));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tfBookPresentationTime.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfBookPresentationTime.setText("jTextField2");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setText("Presentation Date:");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setText("Presentation Time:");

        lPresentationStatus.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lPresentationStatus.setText("jLabel17");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setText("Status:");

        bBookPresentation.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bBookPresentation.setText("Book");
        bBookPresentation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBookPresentationActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Presentation Booking");

        dcBookPresentation.setDateFormatString("dd/MM/yyyy");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(58, 58, 58)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lPresentationStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfBookPresentationTime, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                    .addComponent(dcBookPresentation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(80, 80, 80))
            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(189, 189, 189)
                .addComponent(bBookPresentation)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel21)
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(dcBookPresentation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(tfBookPresentationTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lPresentationStatus)
                    .addComponent(jLabel20))
                .addGap(81, 81, 81)
                .addComponent(bBookPresentation)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pAssessmentStatusLayout = new javax.swing.GroupLayout(pAssessmentStatus);
        pAssessmentStatus.setLayout(pAssessmentStatusLayout);
        pAssessmentStatusLayout.setHorizontalGroup(
            pAssessmentStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pAssessmentStatusLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(pAssessmentStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ModuleLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pAssessmentStatusLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 868, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pAssessmentStatusLayout.setVerticalGroup(
            pAssessmentStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pAssessmentStatusLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(ModuleLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addGroup(pAssessmentStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        tfID.setEnabled(false);

        tfName.setEnabled(false);

        tfContactNumber.setEnabled(false);

        tfEmail.setEnabled(false);

        tfAddress.setEnabled(false);

        buttonGroup1.add(rbMale);
        rbMale.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rbMale.setText("Male");
        rbMale.setEnabled(false);

        buttonGroup1.add(rbFemale);
        rbFemale.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rbFemale.setText("Female");
        rbFemale.setEnabled(false);

        bChangePassword.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bChangePassword.setText("Change Password");
        bChangePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bChangePasswordActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Intake Code:");

        tfIntakeCode.setEnabled(false);
        tfIntakeCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfIntakeCodeActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("DOB:");

        tfNationality.setEnabled(false);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("IC/Passport No.:");

        tfIC.setEnabled(false);

        tfDoB.setEnabled(false);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setText("New Password:");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setText("Confirm Password:");

        javax.swing.GroupLayout pProfileLayout = new javax.swing.GroupLayout(pProfile);
        pProfile.setLayout(pProfileLayout);
        pProfileLayout.setHorizontalGroup(
            pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pProfileLayout.createSequentialGroup()
                .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pProfileLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pProfileLayout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(7, 7, 7))
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16))
                        .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pProfileLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(tfID, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tfName, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                                        .addGroup(pProfileLayout.createSequentialGroup()
                                            .addComponent(rbMale, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(rbFemale, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(tfIntakeCode)
                                        .addComponent(tfIC))
                                    .addComponent(tfNationality, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfDoB, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfContactNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pProfileLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfAddress, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfOldPassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfNewPassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfConfirmPassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(pProfileLayout.createSequentialGroup()
                        .addGap(329, 329, 329)
                        .addComponent(bChangePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(637, Short.MAX_VALUE))
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
                .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pProfileLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(tfIC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pProfileLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(tfIntakeCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(rbMale)
                    .addComponent(rbFemale))
                .addGap(25, 25, 25)
                .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(tfNationality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(tfDoB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
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
                .addGap(18, 18, 18)
                .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(45, 45, 45)
                .addComponent(bChangePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(156, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pSubmitAssessment, javax.swing.GroupLayout.PREFERRED_SIZE, 1326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 1326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pAssessmentStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 1326, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(pSubmitAssessment, javax.swing.GroupLayout.DEFAULT_SIZE, 862, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pAssessmentStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 862, Short.MAX_VALUE)
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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void resultButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resultButtonActionPerformed
        // TODO add your handling code here:
        refreshResult();
        
        pResult.setVisible(true);
        
        pDashboard.setVisible(false);
        pSubmitAssessment.setVisible(false);
        pAssessmentStatus.setVisible(false);
        pProfile.setVisible(false);
    }//GEN-LAST:event_resultButtonActionPerformed

    private void profileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileButtonActionPerformed
        // TODO add your handling code here:
        pProfile.setVisible(true);
        
        pResult.setVisible(false);
        pDashboard.setVisible(false);
        pSubmitAssessment.setVisible(false);
        pAssessmentStatus.setVisible(false);
        
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
        
        pSubmitAssessment.setVisible(true);
        pAssessmentStatus.setVisible(false);
        pDashboard.setVisible(false);
        pProfile.setVisible(false);
        pResult.setVisible(false);
        
    }//GEN-LAST:event_bSubmitSubmissionActionPerformed

    private void bCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelActionPerformed
        // TODO add your handling code here:
        
        rowOfAssignmentStudentSubmission = -1;
        
        
        pDashboard.setVisible(true);
        pSubmitAssessment.setVisible(false);
        pAssessmentStatus.setVisible(false);
        pProfile.setVisible(false);
        pResult.setVisible(false);
    }//GEN-LAST:event_bCancelActionPerformed

    private void bBackAssignmentSubmissionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBackAssignmentSubmissionActionPerformed
        // TODO add your handling code here:

        refreshSubmissionPanel(assessmentID);

        pAssessmentStatus.setVisible(true);
        pSubmitAssessment.setVisible(false);
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
                String newRecord[] = null;

                try {

                    BufferedReader reader = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assessment_student.txt"));
                    String line;

                    while ((line = reader.readLine()) != null) {
                        String[] record = line.split("\t");
                        //Read and add all the lines in text file to variable line
                        lines.add(line);
                        //skip the speific record
                        if (record[0].equals(assessmentID) && record[1].equals(ID)) {
                            newRecord = line.split("\t");
                            newRecord[2] = "NA";
                            newRecord[3] = "NA";
                            newRecord[4] = "NA";
                        }
                    }
                    reader.close();
                }
                catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                
                
                System.out.println(rowOfAssignmentStudentSubmission);
                System.out.println(Arrays.toString(newRecord));
                lines.set(rowOfAssignmentStudentSubmission, String.join("\t", newRecord));

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

                pAssessmentStatus.setVisible(true);
                pDashboard.setVisible(false);
                pSubmitAssessment.setVisible(false);
                pProfile.setVisible(false);
                pResult.setVisible(false);

            }
            else{
                JOptionPane.showMessageDialog(null, "There is no file to be remove.");
            }
        }
    }//GEN-LAST:event_bRemoveSubActionPerformed

    private void bSaveFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveFileActionPerformed
        // TODO add your handling code here:
        
        
        
        
        String[] oldRecord = null;
        int actualRow = 0;

        if (rowOfAssignmentStudentSubmission != -1) {
            JOptionPane.showMessageDialog(null, "Only can submit new file after remove old file.");
        } else {

            if (sourceFile != null) {
                
                
                
                SimpleDateFormat dsdf = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat tsdf = new SimpleDateFormat("HH:mm");
                String date = dsdf.format(new Date());
                String time = tsdf.format(new Date());
                
                
                List<String> lines = new ArrayList<>();
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assessment_student.txt"));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        rowOfAssignmentStudentSubmission = rowOfAssignmentStudentSubmission + 1;
                        String[] record = line.split("\t");
                        lines.add(line);
                        //Read and add all the lines in text file to variable line
                        if (record[0].equals(assessmentID) && record[1].equals(ID)) {
                            oldRecord = line.split("\t");
                            oldRecord[2] = strDesFile;
                            oldRecord[3] = date;
                            oldRecord[4] = time;
                            actualRow = rowOfAssignmentStudentSubmission;
                        }
                    }

                    reader.close();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }

                lines.set(actualRow, String.join("\t", oldRecord));

                try {
                    //Write the information to the text file
                    BufferedWriter writer = new BufferedWriter(new FileWriter("src\\Project_Management_System\\database\\assessment_student.txt"));
                    for (String line : lines) {
                        writer.write(line);
                        writer.newLine();
                    }
                    writer.close();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }

                try {
                    //Copy the file to folder
                    Files.copy(sourceFile.toPath(), destinationFile.toPath());
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }

                refreshSubmissionPanel(assessmentID);

                pAssessmentStatus.setVisible(true);
                pDashboard.setVisible(false);
                pSubmitAssessment.setVisible(false);
                pProfile.setVisible(false);
                pResult.setVisible(false);

            } else {
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
        
        pSubmitAssessment.setVisible(false);
        pAssessmentStatus.setVisible(false);
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
        
            pSubmitAssessment.setVisible(false);
            pAssessmentStatus.setVisible(false);
            pResult.setVisible(false);
            pProfile.setVisible(false);
        }
        else{
            JOptionPane.showMessageDialog(null, "Password Wrong!");
        }
    }//GEN-LAST:event_bChangePasswordActionPerformed

    private void bBookPresentationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBookPresentationActionPerformed
        // TODO add your handling code here:
        int row = -1;
        int actualRow = 0;
        String[] oldRecord = null;
        List<String> lines = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        if (sdf.format(dcBookPresentation.getDate()).isEmpty() || tfBookPresentationTime.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a date and time.");
        } else {
            try {
                BufferedReader reader = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assessment_student.txt"));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] record = line.split("\t");
                    row = row + 1;
                    //Read and add all the lines in text file to variable line
                    lines.add(line);
                    if (record[0].equals(assessmentID) && record[1].equals(ID)) {
                        oldRecord = line.split("\t");
                        oldRecord[9] = sdf.format(dcBookPresentation.getDate());
                        oldRecord[10] = tfBookPresentationTime.getText();
                        oldRecord[11] = "PENDING";
                        actualRow = row;
                    }
                }

                reader.close();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

            lines.set(actualRow, String.join("\t", oldRecord));

            try {
                //Write the information to the text file
                BufferedWriter writer = new BufferedWriter(new FileWriter("src\\Project_Management_System\\database\\assessment_student.txt"));
                for (String line : lines) {
                    writer.write(line);
                    writer.write("\n");
                }
                writer.close();

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            JOptionPane.showMessageDialog(null, "You successfully book the presentation!");
        }
    }//GEN-LAST:event_bBookPresentationActionPerformed

    private void studentLogoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentLogoutBtnActionPerformed
        // TODO add your handling code here:
        this.dispose();
        LoginPage lp = new LoginPage();

        lp.pack();
        lp.setLocationRelativeTo(null);
        lp.setVisible(true);

    }//GEN-LAST:event_studentLogoutBtnActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        String AssessPreorPass = (String) jComboBox1.getSelectedItem();
        if (AssessPreorPass.equals("IN PROGRESS")) {
            createAssessmentPanels("IN PROGRESS");
        } else if (AssessPreorPass.equals("PAST")) {

            createAssessmentPanels("PAST");
        }

    }//GEN-LAST:event_jComboBox1ActionPerformed

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
    private javax.swing.JButton bBookPresentation;
    private javax.swing.JButton bCancel;
    private javax.swing.JButton bChangePassword;
    private javax.swing.JButton bDashboard;
    private javax.swing.JButton bRemoveSub;
    private javax.swing.JButton bSaveFile;
    private javax.swing.JButton bSubmitSubmission;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser dcBookPresentation;
    private javax.swing.JPanel filePanel;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lFileName;
    private javax.swing.JLabel lHelloWorld;
    private javax.swing.JLabel lPresentationStatus;
    private javax.swing.JLabel lSubmissionStatus;
    private javax.swing.JLabel lSubmitFileDate;
    private javax.swing.JLabel lSubmitFileTime;
    private javax.swing.JLabel lSubmittedFile;
    private javax.swing.JLabel mainTitleLabel;
    private javax.swing.JLabel moduleLabel;
    private javax.swing.JPanel pAssessment;
    private javax.swing.JPanel pAssessmentStatus;
    private javax.swing.JPanel pDashboard;
    private javax.swing.JPanel pProfile;
    private javax.swing.JPanel pResult;
    private javax.swing.JPanel pSubmitAssessment;
    private javax.swing.JButton profileButton;
    private javax.swing.JRadioButton rbFemale;
    private javax.swing.JRadioButton rbMale;
    private javax.swing.JButton resultButton;
    private javax.swing.JPanel sidePanel;
    private javax.swing.JLabel smFeedback;
    private javax.swing.JLabel smGrade;
    private javax.swing.JButton studentLogoutBtn;
    private javax.swing.JLabel svFeedback;
    private javax.swing.JLabel svGrade;
    private javax.swing.JTable tResult;
    private javax.swing.JTextField tfAddress;
    private javax.swing.JTextField tfBookPresentationTime;
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
