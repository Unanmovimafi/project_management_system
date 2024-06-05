/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Project_Management_System;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class LecturerSuperviseeList extends javax.swing.JFrame {
    private String ID;
    private String[] assessmentRecord;
    private String[] assessmentStudentRecord;
    private String destFile;
    private int count = -1;
    
    
    public void setID(String ID) {
        this.ID = ID;
        countAssessments();
        countSupervisees();
    }
    
    private void countAssessments() {
        int assessmentCount = 0;
        String filePath = "src\\Project_Management_System\\database\\assessment.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] record = line.split("\t");
                if (record.length >= 6) {
                    if (record[5].equals(ID) || record[6].equals(ID)) {
                        assessmentCount++;
                    }
                } else {
                    System.out.println("Invalid line format in assessment.txt: " + line);
                }
            }
            jLabel13.setText(Integer.toString(assessmentCount));
        } catch (IOException e) {
            System.err.println("Error reading assessment.txt: " + e.getMessage());
        }
    }

    private void countSupervisees() {
        int SuperviseesCount = 0;
        int PendingPresentationCount = 0;

        try (BufferedReader reader2 = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assessment_student.txt"))) {
            String line2;
            while ((line2 = reader2.readLine()) != null) {
                String[] record2 = line2.split("\t");
                try (BufferedReader reader = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assessment.txt"))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] record = line.split("\t");
                        if (record[5].equals(ID) && record[0].equals(record2[0])) {
                            SuperviseesCount++;
                        }
                        if (record[5].equals(ID) && record[0].equals(record2[0]) && record2[11].equals("PENDING")) {
                            PendingPresentationCount++;
                        }
                    }

                } catch (IOException e) {
                    System.err.println("Error reading assessment.txt: " + e.getMessage());
                }

            }
        } catch (IOException e) {
            System.err.println("Error reading assessment.txt: " + e.getMessage());
        }

        jLabel12.setText(Integer.toString(SuperviseesCount));
        jLabel1.setText(Integer.toString(PendingPresentationCount));

    }

    private void countUngradedReport() {
        int UngradedReport = 0;

        try (BufferedReader reader2 = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assessment_student.txt"))) {
            String line2;
            while ((line2 = reader2.readLine()) != null) {
                String[] record2 = line2.split("\t");
                try (BufferedReader reader = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assessment.txt"))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] record = line.split("\t");
                        if (record.length >= 6) {
                            if (record[5].equals(ID) && record[0].equals(record2[0]) && record2[5].equals("NA")) {
                                UngradedReport++;
                            }
                        } else if (record[6].equals(ID) && record[0].equals(record2[0]) && record2[7].equals("NA")) {
                            UngradedReport++;
                        }
                    }

                } catch (IOException e) {
                    System.err.println("Error reading assessment.txt: " + e.getMessage());
                }

            }
        } catch (IOException e) {
            System.err.println("Error reading assessment.txt: " + e.getMessage());
        }

        jLabel14.setText(Integer.toString(UngradedReport));

    }

    
    
    public List<String> getAllAssessStuRecord() {
        try {

            List<String> lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assessment_student.txt"));
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

    
    private void refreshStudentListTable(String assessmentID){
        DefaultTableModel model = (DefaultTableModel)studentTable.getModel();
        model.setRowCount(0);
        
        String line;
        try {BufferedReader br = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assessment_student.txt"));
            while ((line = br.readLine()) != null) {
                String[] record = line.split("\t");
                
                String studentID = record[1];
                String studentLine;
                String studentName = "";
                String intake = "";
                BufferedReader studentReader = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\student.txt"));
                while ((studentLine = studentReader.readLine()) != null) {
                    String[] studentRecord = studentLine.split("\t");
                    if (studentRecord[0].equals(studentID)) {
                        studentName = studentRecord[1];
                        intake = studentRecord[3];
                        break;
                    }
                }

                if (assessmentID.equals(record[0])){
                    model.addRow(new Object[]{intake, studentID, studentName});
                }
            
            }
        }catch (Exception e) {
            e.getMessage();
        }
        
        
        
    }
    
    private void createSupervisorAssessmentPanels() {

        pSupervisorAssessment.removeAll();

        String line;
        String line2;
        int count = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assessment.txt"));
            while ((line = br.readLine()) != null) {
                String[] record = line.split("\t");
                if (ID.equals(record[5])) {
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
                            assessmentRecord = record;
                            refreshStudentListTable(record[0]);
                            pStudentSubmittedAssessment.setVisible(true);
                            pReportsMarking.setVisible(false);
                            pSupervisee.setVisible(false);
                            pHome.setVisible(false);

                            ModuleLabel1.setText(record[1]);
                            Description.setText(record[3]);
                        }
                    });
                    pSupervisorAssessment.add(jPanel1);
                    count = count + 1;
                    break;
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        pSupervisorAssessment.setPreferredSize(new Dimension(350, count * 55));
        jScrollPane1.setViewportView(pSupervisorAssessment);

        pSupervisorAssessment.revalidate();
        pSupervisorAssessment.repaint();
    }
    
    private void createSecondMarkerAssessmentPanels() {

        pSecondMarkerAssessment.removeAll();

        String line;
        String line2;
        int count = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assessment.txt"));
            while ((line = br.readLine()) != null) {
                String[] record = line.split("\t");
                if (ID.equals(record[6])) {
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
                            assessmentRecord = record;

                            refreshStudentListTable(record[0]);
                            pStudentSubmittedAssessment.setVisible(true);
                            pReportsMarking.setVisible(false);
                            pSupervisee.setVisible(false);
                            pHome.setVisible(false);

                            ModuleLabel1.setText(record[1]);
                            Description.setText(record[3]);
                        }
                    });
                    pSecondMarkerAssessment.add(jPanel1);
                    count = count + 1;
                    break;
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        pSecondMarkerAssessment.setPreferredSize(new Dimension(350, count * 55));
        jScrollPane2.setViewportView(pSecondMarkerAssessment);

        pSecondMarkerAssessment.revalidate();
        pSecondMarkerAssessment.repaint();
    }
    

    public void refreshSupverviseeTable() {
        DefaultTableModel model = (DefaultTableModel) tLSuperviseeList.getModel();
        model.setRowCount(0);

        try {
            BufferedReader assessmentStudentReader = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assessment_student.txt"));

            String assessmentStudentLine, assessmentLine;

            while ((assessmentStudentLine = assessmentStudentReader.readLine()) != null) {
                String[] assessmentStudentRecord = assessmentStudentLine.split("\t");
                String studentID = assessmentStudentRecord[1];
                String assessmentID = assessmentStudentRecord[0];

                // Find student details
                String studentName = "";
                String intake = "";

                BufferedReader studentReader = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\student.txt"));
                BufferedReader assessmentReader = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assessment.txt"));

                String studentLine;
                while ((studentLine = studentReader.readLine()) != null) {
                    String[] studentRecord = studentLine.split("\t");
                    if (studentRecord[0].equalsIgnoreCase(studentID)) {
                        studentName = studentRecord[1];
                        intake = studentRecord[3];
                        break;
                    }
                }

                // Find assessment details
                String assessmentType = "";
                String assessmentName = "";
                String assessmentSupervisorID = "";
                String assessmentSecondMarkerID = "";
                while ((assessmentLine = assessmentReader.readLine()) != null) {
                    String[] assessmentRecord = assessmentLine.split("\t");
                    if (assessmentRecord[0].equalsIgnoreCase(assessmentID)) {
                        assessmentName = assessmentRecord[1];
                        assessmentType = assessmentRecord[2];
                        assessmentSupervisorID = assessmentRecord[5];
                        assessmentSecondMarkerID = assessmentRecord[6];
                        break;
                    }
                }

                if (assessmentSupervisorID.equals(ID) || assessmentSecondMarkerID.equals(ID)) {
                    model.addRow(new Object[]{intake, studentID, studentName, assessmentID, assessmentType, assessmentName});
                }
            }

            assessmentStudentReader.close();
        } catch (Exception e) {
            e.printStackTrace(); // Print stack trace for any exceptions
        }
    }
    
    public LecturerSuperviseeList() {
        initComponents();
        pHome.setVisible(true);
        
        pReportsMarking.setVisible(false);
        pSupervisee.setVisible(false);
        pStudentSubmittedAssessment.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        pReportsMarking = new javax.swing.JPanel();
        mainTitleLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pSupervisorAssessment = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        pSecondMarkerAssessment = new javax.swing.JPanel();
        pSupervisee = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        tfLecturerName = new javax.swing.JTextField();
        tSuperviseeScrollPane2 = new javax.swing.JScrollPane();
        tLSuperviseeList = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        tfLecturerID = new javax.swing.JTextField();
        bLecturerApply = new javax.swing.JButton();
        bLecturerClear = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        pStudentSubmittedAssessment = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        filePanel = new javax.swing.JPanel();
        lSubmittedFile = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        downloadButton = new javax.swing.JButton();
        feedbackLabel = new javax.swing.JLabel();
        Description = new javax.swing.JLabel();
        DescriptionTitle = new javax.swing.JLabel();
        feedbackTextfield = new javax.swing.JTextField();
        markLabel = new javax.swing.JLabel();
        markTextfield = new javax.swing.JTextField();
        gradeButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tfSecondFB = new javax.swing.JTextField();
        tfSecondMark = new javax.swing.JTextField();
        SubmissionDateLabel = new javax.swing.JLabel();
        SubmissionTimeLabel = new javax.swing.JLabel();
        ModuleLabel1 = new javax.swing.JLabel();
        studentScrollPane = new javax.swing.JScrollPane();
        studentTable = new javax.swing.JTable();
        searchLabel = new javax.swing.JLabel();
        searchTextfield = new javax.swing.JTextField();
        lStudentID = new javax.swing.JLabel();
        lStudentName = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lStudentPresentationDate = new javax.swing.JLabel();
        lPresentationTime = new javax.swing.JLabel();
        lStudentPresentationStatus = new javax.swing.JLabel();
        bAcceptPresentation = new javax.swing.JButton();
        bRejectPresentation = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        pHome = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        presentationLB = new javax.swing.JLabel();
        UpPresentationLB = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        TotalPresentationLabel = new javax.swing.JLabel();
        TotalSuperviseeLabel = new javax.swing.JLabel();
        TotalAssessmentLabel = new javax.swing.JLabel();
        TotalUngradedLB = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        SuperviseeSideButton = new javax.swing.JButton();
        HomeSideButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1500, 780));

        pReportsMarking.setBackground(new java.awt.Color(252, 247, 204));
        pReportsMarking.setPreferredSize(new java.awt.Dimension(1500, 780));

        mainTitleLabel.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        mainTitleLabel.setText("Reports Marking");
        mainTitleLabel.setPreferredSize(new java.awt.Dimension(200, 160));

        jScrollPane1.setViewportView(pSupervisorAssessment);

        jScrollPane2.setViewportView(pSecondMarkerAssessment);

        javax.swing.GroupLayout pReportsMarkingLayout = new javax.swing.GroupLayout(pReportsMarking);
        pReportsMarking.setLayout(pReportsMarkingLayout);
        pReportsMarkingLayout.setHorizontalGroup(
            pReportsMarkingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pReportsMarkingLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pReportsMarkingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pReportsMarkingLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(mainTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(907, Short.MAX_VALUE))
        );
        pReportsMarkingLayout.setVerticalGroup(
            pReportsMarkingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pReportsMarkingLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(mainTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pReportsMarkingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(211, Short.MAX_VALUE))
        );

        pSupervisee.setBackground(new java.awt.Color(255, 255, 255));

        jPanel9.setBackground(new java.awt.Color(0, 50, 79));
        jPanel9.setPreferredSize(new java.awt.Dimension(259, 94));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Supervisee Details");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 649, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel3)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(204, 204, 204));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Assessment Name:");

        tSuperviseeScrollPane2.setBackground(new java.awt.Color(255, 255, 255));

        tLSuperviseeList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Intake", "Student ID", "Student Name", "Assessment ID", "Assessment Type", "Assessment Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tLSuperviseeList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tLSuperviseeListMouseReleased(evt);
            }
        });
        tSuperviseeScrollPane2.setViewportView(tLSuperviseeList);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Student ID:");

        bLecturerApply.setText("Apply");
        bLecturerApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLecturerApplyActionPerformed(evt);
            }
        });

        bLecturerClear.setText("Clear");
        bLecturerClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLecturerClearActionPerformed(evt);
            }
        });

        jPanel10.setBackground(new java.awt.Color(252, 247, 204));

        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton7.setText("Add New Lecturer");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton8.setText("Edit Lecturer");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(154, 154, 154)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel4)
                .addGap(174, 174, 174)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(517, Short.MAX_VALUE))
        );

        jLabel15.setText("Intake code");

        jLabel16.setText("Assess Type");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tSuperviseeScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1042, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(tfLecturerID, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addComponent(jLabel15)))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel16))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfLecturerName, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(72, 72, 72)
                                .addComponent(bLecturerApply)
                                .addGap(34, 34, 34)
                                .addComponent(bLecturerClear)))))
                .addGap(18, 18, 18)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tfLecturerName, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bLecturerApply)
                    .addComponent(bLecturerClear)
                    .addComponent(tfLecturerID, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(4, 4, 4)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tSuperviseeScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pSuperviseeLayout = new javax.swing.GroupLayout(pSupervisee);
        pSupervisee.setLayout(pSuperviseeLayout);
        pSuperviseeLayout.setHorizontalGroup(
            pSuperviseeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 1929, Short.MAX_VALUE)
            .addGroup(pSuperviseeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(642, Short.MAX_VALUE))
        );
        pSuperviseeLayout.setVerticalGroup(
            pSuperviseeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pSuperviseeLayout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pStudentSubmittedAssessment.setBackground(new java.awt.Color(252, 247, 204));
        pStudentSubmittedAssessment.setPreferredSize(new java.awt.Dimension(1500, 780));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Assessment Submission");

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));

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

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("File Submission");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(filePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(filePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel7)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        downloadButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        downloadButton.setText("Download Submission File");
        downloadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downloadButtonActionPerformed(evt);
            }
        });

        feedbackLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        feedbackLabel.setText("Feedback:");

        Description.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Description.setText("jLabel4");

        DescriptionTitle.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        DescriptionTitle.setText("Description:");

        feedbackTextfield.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        markLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        markLabel.setText("Mark:");

        markTextfield.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        gradeButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        gradeButton.setText("Grade");
        gradeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gradeButtonActionPerformed(evt);
            }
        });

        cancelButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cancelButton.setText("Cancel");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Feedback:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Mark:");

        tfSecondFB.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfSecondFB.setText("jTextField1");

        tfSecondMark.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfSecondMark.setText("jTextField2");

        SubmissionDateLabel.setText("Submission Date");

        SubmissionTimeLabel.setText("Submission Time");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(DescriptionTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Description, javax.swing.GroupLayout.PREFERRED_SIZE, 911, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(451, 451, 451)
                        .addComponent(gradeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(cancelButton))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(markLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(feedbackLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(markTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfSecondFB, javax.swing.GroupLayout.PREFERRED_SIZE, 704, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfSecondMark, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(feedbackTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 707, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(SubmissionDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(SubmissionTimeLabel)
                        .addGap(47, 47, 47))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(downloadButton)
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1062, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DescriptionTitle)
                    .addComponent(Description))
                .addGap(13, 13, 13)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SubmissionDateLabel)
                    .addComponent(SubmissionTimeLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(downloadButton)
                .addGap(11, 11, 11)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(feedbackLabel)
                    .addComponent(feedbackTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(markTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(markLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(tfSecondFB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(tfSecondMark, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gradeButton)
                    .addComponent(cancelButton))
                .addGap(29, 29, 29))
        );

        ModuleLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        ModuleLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ModuleLabel1.setPreferredSize(new java.awt.Dimension(200, 160));

        studentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Intake", "ID", "Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        studentTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                studentTableMouseReleased(evt);
            }
        });
        studentScrollPane.setViewportView(studentTable);

        searchLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        searchLabel.setText("Search:");

        lStudentID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lStudentID.setText("Student ID");

        lStudentName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lStudentName.setText("Student Name");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lStudentPresentationDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lStudentPresentationDate.setText("jLabel15");

        lPresentationTime.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lPresentationTime.setText("jLabel16");

        lStudentPresentationStatus.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lStudentPresentationStatus.setText("jLabel15");

        bAcceptPresentation.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bAcceptPresentation.setText("Accept");
        bAcceptPresentation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAcceptPresentationActionPerformed(evt);
            }
        });

        bRejectPresentation.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bRejectPresentation.setText("Reject");
        bRejectPresentation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRejectPresentationActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setText("Presentation Date:");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setText("Presentation Time:");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setText("Status:");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Presentation Booking");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lStudentPresentationDate, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lStudentPresentationStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lPresentationTime, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))))
                .addGap(42, 42, 42))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addComponent(bAcceptPresentation)
                .addGap(37, 37, 37)
                .addComponent(bRejectPresentation)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel20)
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(lStudentPresentationDate))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(lPresentationTime))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lStudentPresentationStatus)
                    .addComponent(jLabel19))
                .addGap(73, 73, 73)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bAcceptPresentation)
                    .addComponent(bRejectPresentation))
                .addContainerGap(69, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pStudentSubmittedAssessmentLayout = new javax.swing.GroupLayout(pStudentSubmittedAssessment);
        pStudentSubmittedAssessment.setLayout(pStudentSubmittedAssessmentLayout);
        pStudentSubmittedAssessmentLayout.setHorizontalGroup(
            pStudentSubmittedAssessmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pStudentSubmittedAssessmentLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(pStudentSubmittedAssessmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pStudentSubmittedAssessmentLayout.createSequentialGroup()
                        .addGroup(pStudentSubmittedAssessmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ModuleLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pStudentSubmittedAssessmentLayout.createSequentialGroup()
                                .addGap(240, 240, 240)
                                .addGroup(pStudentSubmittedAssessmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lStudentName)
                                    .addComponent(lStudentID))))
                        .addGap(69, 69, 69)
                        .addGroup(pStudentSubmittedAssessmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pStudentSubmittedAssessmentLayout.createSequentialGroup()
                                .addComponent(searchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchTextfield))
                            .addGroup(pStudentSubmittedAssessmentLayout.createSequentialGroup()
                                .addComponent(studentScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(289, 289, 289))
                    .addGroup(pStudentSubmittedAssessmentLayout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 1095, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 41, Short.MAX_VALUE))))
        );
        pStudentSubmittedAssessmentLayout.setVerticalGroup(
            pStudentSubmittedAssessmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pStudentSubmittedAssessmentLayout.createSequentialGroup()
                .addGroup(pStudentSubmittedAssessmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pStudentSubmittedAssessmentLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(pStudentSubmittedAssessmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchLabel)
                            .addComponent(searchTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(studentScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pStudentSubmittedAssessmentLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(ModuleLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(lStudentID)
                        .addGap(18, 18, 18)
                        .addComponent(lStudentName)))
                .addGap(29, 29, 29)
                .addGroup(pStudentSubmittedAssessmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pHome.setBackground(new java.awt.Color(252, 247, 204));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel10.setText("Lecture Dashboard");

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("jButton4");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setText("Quick Access");

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.setForeground(new java.awt.Color(255, 255, 255));

        presentationLB.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        presentationLB.setText("Upcoming Presentation:");

        UpPresentationLB.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        UpPresentationLB.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(presentationLB)
                    .addComponent(UpPresentationLB, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(presentationLB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(UpPresentationLB, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel12.setForeground(new java.awt.Color(255, 255, 255));

        TotalPresentationLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TotalPresentationLabel.setText("Total Pending Presentation Requests:");

        TotalSuperviseeLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TotalSuperviseeLabel.setText("Total Supervisees:");

        TotalAssessmentLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TotalAssessmentLabel.setText("Total Assessment:");

        TotalUngradedLB.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TotalUngradedLB.setText("Total Ungraded Reports:");

        jLabel1.setText("jLabel1");

        jLabel12.setText("jLabel12");

        jLabel13.setText("jLabel13");

        jLabel14.setText("jLabel14");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TotalUngradedLB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TotalSuperviseeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TotalPresentationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 253, Short.MAX_VALUE)
                    .addComponent(TotalAssessmentLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addContainerGap(124, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TotalPresentationLabel)
                    .addComponent(jLabel1))
                .addGap(29, 29, 29)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TotalSuperviseeLabel)
                    .addComponent(jLabel12))
                .addGap(33, 33, 33)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TotalAssessmentLabel)
                    .addComponent(jLabel13))
                .addGap(32, 32, 32)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TotalUngradedLB)
                    .addComponent(jLabel14))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(69, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(78, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pHomeLayout = new javax.swing.GroupLayout(pHome);
        pHome.setLayout(pHomeLayout);
        pHomeLayout.setHorizontalGroup(
            pHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pHomeLayout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addGroup(pHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(799, Short.MAX_VALUE))
        );
        pHomeLayout.setVerticalGroup(
            pHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pHomeLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel10)
                .addGap(62, 62, 62)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(236, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1929, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pSupervisee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addComponent(pReportsMarking, javax.swing.GroupLayout.DEFAULT_SIZE, 1923, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pStudentSubmittedAssessment, javax.swing.GroupLayout.DEFAULT_SIZE, 1929, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 939, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pSupervisee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addComponent(pReportsMarking, javax.swing.GroupLayout.DEFAULT_SIZE, 933, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pStudentSubmittedAssessment, javax.swing.GroupLayout.DEFAULT_SIZE, 939, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 50, 79));

        SuperviseeSideButton.setText("SUPERVISEE");
        SuperviseeSideButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SuperviseeSideButtonActionPerformed(evt);
            }
        });

        HomeSideButton.setText("Dashboard");
        HomeSideButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeSideButtonActionPerformed(evt);
            }
        });

        jButton1.setText("Reports Marking");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(HomeSideButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(SuperviseeSideButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)))
                .addGap(18, 18, 18))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(HomeSideButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SuperviseeSideButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(663, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1934, 1934, 1934))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(210, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SuperviseeSideButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SuperviseeSideButtonActionPerformed
        // TODO add your handling code here:
        selectionID = "-1";
        
        pSupervisee.setVisible(true);
        refreshSupverviseeTable();
        pHome.setVisible(false);
        pStudentSubmittedAssessment.setVisible(true);
        pReportsMarking.setVisible(false);
    }//GEN-LAST:event_SuperviseeSideButtonActionPerformed

    private void HomeSideButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeSideButtonActionPerformed
        // TODO add your handling code here:
        selectionID = "-1";
        
        pHome.setVisible(true);
        pSupervisee.setVisible(false);
        pStudentSubmittedAssessment.setVisible(false);
        pReportsMarking.setVisible(false);
    }//GEN-LAST:event_HomeSideButtonActionPerformed

    private void bLecturerApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLecturerApplyActionPerformed
        // TODO add your handling code here:
        String lecturerID = tfLecturerID.getText().toLowerCase();
        String lecturerName = tfLecturerName.getText().toLowerCase();
        refreshSupverviseeTable();
    }//GEN-LAST:event_bLecturerApplyActionPerformed

    private void bLecturerClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLecturerClearActionPerformed
        // TODO add your handling code here:
        tfLecturerID.setText("");
        tfLecturerName.setText("");
        refreshSupverviseeTable();
    }//GEN-LAST:event_bLecturerClearActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        
        AddLecturerDetails addLec = new AddLecturerDetails();
        
        addLec.pack();
        addLec.setLocationRelativeTo(null);
        
        addLec.setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        
//        
//        
//        
//        
//        
//        EditLecturerDetails eld = new EditLecturerDetails();
//        if (selectionID == "-1") {
//            //If no selected then notify user to select
//            JOptionPane.showMessageDialog(null, "Please select a product to edit!");
//        }
//        else{
//            
//            //Set and pass record and this page to EditProduct
//            eld.setAdminPageInstance(this);
//            eld.setRecordData(selectionID);
//            
//            //Open EditProduct page
//            eld.setVisible(true);
//        }
//        
//        
        
    }//GEN-LAST:event_jButton8ActionPerformed

    private void tLSuperviseeListMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tLSuperviseeListMouseReleased
        // TODO add your handling code here:
        //The id of the select record
        selectionID = tLSuperviseeList.getModel().getValueAt(tLSuperviseeList.getSelectedRow(), 0).toString();
    }//GEN-LAST:event_tLSuperviseeListMouseReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        createSupervisorAssessmentPanels();
        createSecondMarkerAssessmentPanels();
        selectionID = "-1";
        
        pReportsMarking.setVisible(true);
        
        pSupervisee.setVisible(false);
        pHome.setVisible(false);
        pStudentSubmittedAssessment.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void downloadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downloadButtonActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.showOpenDialog(null);
        File file=chooser.getSelectedFile();
        String stuSubmiteedFileName = destFile.substring(destFile.lastIndexOf("\\"));
        String filePath = file.getAbsolutePath() + stuSubmiteedFileName;
        
        
        
        
        try {
                Files.copy(new File(destFile).toPath(), new File(filePath).toPath());

            } catch (Exception e) {
                e.getMessage();
            }
            
    }//GEN-LAST:event_downloadButtonActionPerformed

    private void studentTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentTableMouseReleased
        // TODO add your handling code here:
        String selectionID = studentTable.getModel().getValueAt(studentTable.getSelectedRow(), 1).toString();
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assessment_student.txt"));
            while ((line = br.readLine()) != null) {
                count = count + 1;
                String[] record = line.split("\t");
                String assstudentLine;
                String studentName = "";
                BufferedReader assessmentReader = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assessment.txt"));
                while ((assstudentLine = assessmentReader.readLine()) != null) {
                    String[] assstudentRecord = assstudentLine.split("\t");
                    if (assstudentRecord[0].equals(record[0])) {
                        if (assstudentRecord[5].equals(ID)) {
                            feedbackTextfield.setEditable(true);
                            markTextfield.setEditable(true);
                            tfSecondFB.setEditable(false);
                            tfSecondMark.setEditable(false);

                        } else if (assstudentRecord[6].equals(ID)) {
                            feedbackTextfield.setEditable(false);
                            markTextfield.setEditable(false);
                            tfSecondFB.setEditable(true);
                            tfSecondMark.setEditable(true);
                        }
                        break;
                    }
                }

                if (assessmentRecord[0].equals(record[0]) && selectionID.equals(record[1])) {
                    lStudentID.setText(record[1]);
                    lStudentName.setText(studentName);
                    lSubmittedFile.setText(record[2]);
                    feedbackTextfield.setText(record[6]);
                    markTextfield.setText(record[5]);

                    tfSecondFB.setText(record[8]);
                    tfSecondMark.setText(record[7]);

                    assessmentStudentRecord = record;

                    if (record[2].equals("NA")) {
                        downloadButton.setEnabled(false);
                    } else {
                        destFile = record[2];
                    }

                    if (record[11].equals("NA")) {
                        lStudentPresentationDate.setText("NA");
                        lPresentationTime.setText("NA");
                        lStudentPresentationStatus.setText("Student Haven't book Presentation");
                        bAcceptPresentation.setEnabled(false);
                        bRejectPresentation.setEnabled(false);

                    } else if (record[11].equals("PENDING")) {
                        lStudentPresentationDate.setText(record[9]);
                        lPresentationTime.setText(record[10]);
                        lStudentPresentationStatus.setText("Waiting Approval");
                        bAcceptPresentation.setEnabled(true);
                        bRejectPresentation.setEnabled(true);
                    } else if (record[11].equals("ACCEPT")) {
                        lStudentPresentationDate.setText(record[9]);
                        lPresentationTime.setText(record[10]);
                        lStudentPresentationStatus.setText("You jave accept the Presentation");
                        bAcceptPresentation.setEnabled(false);
                        bRejectPresentation.setEnabled(false);
                    } else if (record[11].equals("REJECT")) {
                        lStudentPresentationDate.setText(record[9]);
                        lPresentationTime.setText(record[10]);
                        lStudentPresentationStatus.setText("Waiting the Student to re-select the presentation date and time.");
                        bAcceptPresentation.setEnabled(false);
                        bRejectPresentation.setEnabled(false);
                    }

                    break;
                }

            }
        } catch (Exception e) {
            e.getMessage();
        }


    }//GEN-LAST:event_studentTableMouseReleased

    private void gradeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gradeButtonActionPerformed
        // TODO add your handling code here:
        assessmentStudentRecord[5] = markTextfield.getText();
        assessmentStudentRecord[6] = feedbackTextfield.getText();
        assessmentStudentRecord[7] = tfSecondMark.getText();
        assessmentStudentRecord[8] = tfSecondFB.getText();

        try {
            List<String> lines = getAllAssessStuRecord();
            //Write the information to the text file
            String strAssStuRecord = String.join("\t", assessmentStudentRecord);
            lines.set(count, strAssStuRecord);
            BufferedWriter writer = new BufferedWriter(new FileWriter("src\\Project_Management_System\\database\\assessment_student.txt"));
            for (String updatedLine : lines) {
                writer.write(updatedLine);
                writer.newLine();
            }

            writer.close();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }//GEN-LAST:event_gradeButtonActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton3ActionPerformed

    private void bAcceptPresentationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAcceptPresentationActionPerformed
        // TODO add your handling code here:

        assessmentStudentRecord[11] = "ACCEPT";
        
        try {
            List<String> lines = getAllAssessStuRecord();
            //Write the information to the text file
            String strAssStuRecord = String.join("\t", assessmentStudentRecord);
            lines.set(count, strAssStuRecord);
            BufferedWriter writer = new BufferedWriter(new FileWriter("src\\Project_Management_System\\database\\assessment_student.txt"));
            for (String updatedLine : lines) {
                writer.write(updatedLine);
                writer.newLine();
            }

            writer.close();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
        
        bAcceptPresentation.setEnabled(false);
        bRejectPresentation.setEnabled(false);
    }//GEN-LAST:event_bAcceptPresentationActionPerformed

    private void bRejectPresentationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRejectPresentationActionPerformed
        // TODO add your handling code here:
        assessmentStudentRecord[11] = "REJECT";
        
        try {
            List<String> lines = getAllAssessStuRecord();
            //Write the information to the text file
            String strAssStuRecord = String.join("\t", assessmentStudentRecord);
            lines.set(count, strAssStuRecord);
            BufferedWriter writer = new BufferedWriter(new FileWriter("src\\Project_Management_System\\database\\assessment_student.txt"));
            for (String updatedLine : lines) {
                writer.write(updatedLine);
                writer.newLine();
            }

            writer.close();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
        bAcceptPresentation.setEnabled(false);
        bRejectPresentation.setEnabled(false);
    }//GEN-LAST:event_bRejectPresentationActionPerformed

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
            java.util.logging.Logger.getLogger(LecturerSuperviseeList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LecturerSuperviseeList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LecturerSuperviseeList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LecturerSuperviseeList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LecturerSuperviseeList().setVisible(true);
            }
        });
    }
    
    private String selectionID = "-1";

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Description;
    private javax.swing.JLabel DescriptionTitle;
    private javax.swing.JButton HomeSideButton;
    private javax.swing.JLabel ModuleLabel1;
    private javax.swing.JLabel SubmissionDateLabel;
    private javax.swing.JLabel SubmissionTimeLabel;
    private javax.swing.JButton SuperviseeSideButton;
    private javax.swing.JLabel TotalAssessmentLabel;
    private javax.swing.JLabel TotalPresentationLabel;
    private javax.swing.JLabel TotalSuperviseeLabel;
    private javax.swing.JLabel TotalUngradedLB;
    private javax.swing.JLabel UpPresentationLB;
    private javax.swing.JButton bAcceptPresentation;
    private javax.swing.JButton bLecturerApply;
    private javax.swing.JButton bLecturerClear;
    private javax.swing.JButton bRejectPresentation;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton downloadButton;
    private javax.swing.JLabel feedbackLabel;
    private javax.swing.JTextField feedbackTextfield;
    private javax.swing.JPanel filePanel;
    private javax.swing.JButton gradeButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lPresentationTime;
    private javax.swing.JLabel lStudentID;
    private javax.swing.JLabel lStudentName;
    private javax.swing.JLabel lStudentPresentationDate;
    private javax.swing.JLabel lStudentPresentationStatus;
    private javax.swing.JLabel lSubmittedFile;
    private javax.swing.JLabel mainTitleLabel;
    private javax.swing.JLabel markLabel;
    private javax.swing.JTextField markTextfield;
    private javax.swing.JPanel pHome;
    private javax.swing.JPanel pReportsMarking;
    private javax.swing.JPanel pSecondMarkerAssessment;
    private javax.swing.JPanel pStudentSubmittedAssessment;
    private javax.swing.JPanel pSupervisee;
    private javax.swing.JPanel pSupervisorAssessment;
    private javax.swing.JLabel presentationLB;
    private javax.swing.JLabel searchLabel;
    private javax.swing.JTextField searchTextfield;
    private javax.swing.JScrollPane studentScrollPane;
    private javax.swing.JTable studentTable;
    private javax.swing.JTable tLSuperviseeList;
    private javax.swing.JScrollPane tSuperviseeScrollPane2;
    private javax.swing.JTextField tfLecturerID;
    private javax.swing.JTextField tfLecturerName;
    private javax.swing.JTextField tfSecondFB;
    private javax.swing.JTextField tfSecondMark;
    // End of variables declaration//GEN-END:variables
}
