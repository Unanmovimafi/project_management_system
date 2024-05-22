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
    private String assessmentID;
    private String destFile;
    
    
    public void setID(String ID) {
        this.ID = ID;
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
        
        try {BufferedReader br = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assessment.txt")); 
            while ((line = br.readLine()) != null) {
                String[] record = line.split("\t");
                        if (ID.equals(record[5])){
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
                                    refreshStudentListTable(record[0]);
                                    pStudentSubmittedAssessment.setVisible(true);
                                    pDashboard.setVisible(false);
                                    pSupervisee.setVisible(false);
                                    pAssignment.setVisible(false);
                                    
                                    ModuleLabel1.setText(record[1]);
                                    Description.setText(record[3]);
                                    }
                            });
                            pSupervisorAssessment.add(jPanel1);
                            count = count + 1;
                            break;
                        }
                    }
                }catch (Exception e) {
                    e.getMessage();
                }
        pSupervisorAssessment.setPreferredSize(new Dimension(350, count*55));
        jScrollPane1.setViewportView(pSupervisorAssessment);
        
        
        pSupervisorAssessment.revalidate();
        pSupervisorAssessment.repaint();  
    }
    
    private void createSecondMarkerAssessmentPanels() {
        
        pSecondMarkerAssessment.removeAll();
        
        String line;
        String line2;
        int count = 0;
        
        try {BufferedReader br = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assessment.txt")); 
            while ((line = br.readLine()) != null) {
                String[] record = line.split("\t");
                        if (ID.equals(record[6])){
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
                                    
                                    refreshStudentListTable(record[0]);
                                    pStudentSubmittedAssessment.setVisible(true);
                                    pDashboard.setVisible(false);
                                    pSupervisee.setVisible(false);
                                    pAssignment.setVisible(false);
                                    
                                    ModuleLabel1.setText(record[1]);
                                    Description.setText(record[3]);
                                    }
                            });
                            pSecondMarkerAssessment.add(jPanel1);
                            count = count + 1;
                            break;
                        }
                    }
                }catch (Exception e) {
                    e.getMessage();
                }
        pSecondMarkerAssessment.setPreferredSize(new Dimension(350, count*55));
        jScrollPane2.setViewportView(pSecondMarkerAssessment);
        
        
        pSecondMarkerAssessment.revalidate();
        pSecondMarkerAssessment.repaint();  
    }
    

    public void refreshSupverviseeTable() {
        DefaultTableModel model = (DefaultTableModel)tLSuperviseeList.getModel();
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
            
            if (assessmentSupervisorID.equals(ID) || assessmentSecondMarkerID.equals(ID)){
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
        pSupervisee.setVisible(false);
        pAssignment.setVisible(false);
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
        pDashboard = new javax.swing.JPanel();
        mainTitleLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pSupervisorAssessment = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        pSecondMarkerAssessment = new javax.swing.JPanel();
        pHome = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
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
        pAssignment = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        StudentDetailsLabel = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        tfStudentID = new javax.swing.JTextField();
        tfStudentName = new javax.swing.JTextField();
        bStudentApply = new javax.swing.JButton();
        bStudentClear = new javax.swing.JButton();
        StudentTableSP = new javax.swing.JScrollPane();
        tStudentList = new javax.swing.JTable();
        IntakeCodeLB = new javax.swing.JLabel();
        StudentIDLB = new javax.swing.JLabel();
        StudentNameLB = new javax.swing.JLabel();
        TotalStudentTF = new javax.swing.JTextField();
        TotalLB = new javax.swing.JLabel();
        tfIntakeCode = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        bEditStudentr = new javax.swing.JButton();
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
        ModuleLabel1 = new javax.swing.JLabel();
        studentScrollPane = new javax.swing.JScrollPane();
        studentTable = new javax.swing.JTable();
        searchLabel = new javax.swing.JLabel();
        searchTextfield = new javax.swing.JTextField();
        lStudentID = new javax.swing.JLabel();
        lStudentName = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        AssignmentSideButton = new javax.swing.JButton();
        SuperviseeSideButton = new javax.swing.JButton();
        HomeSideButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1500, 780));

        pDashboard.setBackground(new java.awt.Color(252, 247, 204));
        pDashboard.setPreferredSize(new java.awt.Dimension(1500, 780));

        mainTitleLabel.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        mainTitleLabel.setText("Reports Marking");
        mainTitleLabel.setPreferredSize(new java.awt.Dimension(200, 160));

        jScrollPane1.setViewportView(pSupervisorAssessment);

        jScrollPane2.setViewportView(pSecondMarkerAssessment);

        javax.swing.GroupLayout pDashboardLayout = new javax.swing.GroupLayout(pDashboard);
        pDashboard.setLayout(pDashboardLayout);
        pDashboardLayout.setHorizontalGroup(
            pDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDashboardLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pDashboardLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(mainTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(907, Short.MAX_VALUE))
        );
        pDashboardLayout.setVerticalGroup(
            pDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDashboardLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(mainTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(88, Short.MAX_VALUE))
        );

        pHome.setBackground(new java.awt.Color(252, 247, 204));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("Lecturer Dashboard");

        jButton3.setText("jButton3");

        jButton4.setText("jButton4");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(151, 151, 151)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(119, 119, 119)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(244, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(109, Short.MAX_VALUE))
        );

        jButton5.setText("jButton5");

        jButton6.setText("jButton6");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(163, 163, 163)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(246, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pHomeLayout = new javax.swing.GroupLayout(pHome);
        pHome.setLayout(pHomeLayout);
        pHomeLayout.setHorizontalGroup(
            pHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pHomeLayout.createSequentialGroup()
                .addGroup(pHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pHomeLayout.createSequentialGroup()
                        .addGap(371, 371, 371)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pHomeLayout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addGroup(pHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(838, Short.MAX_VALUE))
        );
        pHomeLayout.setVerticalGroup(
            pHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pHomeLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel1)
                .addGap(59, 59, 59)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(282, Short.MAX_VALUE))
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
                .addContainerGap(394, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(tfLecturerID, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfLecturerName, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(bLecturerApply)
                        .addGap(34, 34, 34)
                        .addComponent(bLecturerClear))
                    .addComponent(tSuperviseeScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1042, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGap(32, 32, 32)
                .addComponent(tSuperviseeScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pSuperviseeLayout = new javax.swing.GroupLayout(pSupervisee);
        pSupervisee.setLayout(pSuperviseeLayout);
        pSuperviseeLayout.setHorizontalGroup(
            pSuperviseeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 1925, Short.MAX_VALUE)
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

        pAssignment.setBackground(new java.awt.Color(255, 255, 255));
        pAssignment.setPreferredSize(new java.awt.Dimension(1500, 780));

        jPanel13.setBackground(new java.awt.Color(0, 50, 79));

        StudentDetailsLabel.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        StudentDetailsLabel.setForeground(new java.awt.Color(255, 255, 255));
        StudentDetailsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        StudentDetailsLabel.setText("Supervisee Details");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(509, 509, 509)
                .addComponent(StudentDetailsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(StudentDetailsLabel)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel15.setBackground(new java.awt.Color(204, 204, 204));

        bStudentApply.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bStudentApply.setText("APPLY");
        bStudentApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bStudentApplyActionPerformed(evt);
            }
        });

        bStudentClear.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bStudentClear.setText("CLEAR");
        bStudentClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bStudentClearActionPerformed(evt);
            }
        });

        tStudentList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Intake Code", "Gender", "Nationality", "DoB", "Contact Number", "Email"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tStudentList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tStudentListMouseReleased(evt);
            }
        });
        StudentTableSP.setViewportView(tStudentList);

        IntakeCodeLB.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        IntakeCodeLB.setText("Intake Code:");

        StudentIDLB.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        StudentIDLB.setText("Student ID:");

        StudentNameLB.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        StudentNameLB.setText("Student Name:");

        TotalLB.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TotalLB.setText("Total:");

        jPanel14.setBackground(new java.awt.Color(249, 244, 202));

        bEditStudentr.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bEditStudentr.setText("Edit Supervisee");
        bEditStudentr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEditStudentrActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bEditStudentr, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 15, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(157, 157, 157)
                .addComponent(bEditStudentr, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(512, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(IntakeCodeLB)
                                .addGap(18, 18, 18)
                                .addComponent(tfIntakeCode, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(StudentIDLB)
                                .addGap(18, 18, 18)
                                .addComponent(tfStudentID, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(StudentNameLB))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(TotalLB)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TotalStudentTF, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(bStudentClear, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bStudentApply, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tfStudentName, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(StudentTableSP, javax.swing.GroupLayout.PREFERRED_SIZE, 1045, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(643, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap(77, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addComponent(IntakeCodeLB)
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(StudentIDLB)
                            .addComponent(tfStudentID, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfIntakeCode)
                            .addComponent(StudentNameLB)
                            .addComponent(tfStudentName, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TotalStudentTF, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bStudentClear)
                        .addComponent(bStudentApply))
                    .addComponent(TotalLB))
                .addGap(18, 18, 18)
                .addComponent(StudentTableSP, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
            .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pAssignmentLayout = new javax.swing.GroupLayout(pAssignment);
        pAssignment.setLayout(pAssignmentLayout);
        pAssignmentLayout.setHorizontalGroup(
            pAssignmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pAssignmentLayout.setVerticalGroup(
            pAssignmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pAssignmentLayout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        markLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        markLabel.setText("Mark:");

        gradeButton.setText("Grade");

        cancelButton.setText("Cancel");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(DescriptionTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(Description))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(downloadButton)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(markLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(feedbackLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(feedbackTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 707, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(markTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(452, 452, 452)
                        .addComponent(gradeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(cancelButton))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1070, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DescriptionTitle)
                    .addComponent(Description))
                .addGap(35, 35, 35)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(downloadButton)
                .addGap(19, 19, 19)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(feedbackTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(feedbackLabel))
                .addGap(39, 39, 39)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(markLabel)
                    .addComponent(markTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gradeButton)
                    .addComponent(cancelButton))
                .addGap(54, 54, 54))
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

        lStudentID.setText("jLabel8");

        lStudentName.setText("jLabel9");

        javax.swing.GroupLayout pStudentSubmittedAssessmentLayout = new javax.swing.GroupLayout(pStudentSubmittedAssessment);
        pStudentSubmittedAssessment.setLayout(pStudentSubmittedAssessmentLayout);
        pStudentSubmittedAssessmentLayout.setHorizontalGroup(
            pStudentSubmittedAssessmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pStudentSubmittedAssessmentLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(pStudentSubmittedAssessmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pStudentSubmittedAssessmentLayout.createSequentialGroup()
                        .addGroup(pStudentSubmittedAssessmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ModuleLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pStudentSubmittedAssessmentLayout.createSequentialGroup()
                                .addGap(240, 240, 240)
                                .addGroup(pStudentSubmittedAssessmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lStudentName)
                                    .addComponent(lStudentID))))
                        .addGap(69, 69, 69)
                        .addGroup(pStudentSubmittedAssessmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(studentScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pStudentSubmittedAssessmentLayout.createSequentialGroup()
                                .addComponent(searchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchTextfield, javax.swing.GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)))))
                .addContainerGap(357, Short.MAX_VALUE))
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
                .addGap(40, 40, 40)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(212, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pSupervisee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pAssignment, javax.swing.GroupLayout.DEFAULT_SIZE, 1925, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addComponent(pDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, 1919, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pStudentSubmittedAssessment, javax.swing.GroupLayout.DEFAULT_SIZE, 1925, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pSupervisee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pAssignment, javax.swing.GroupLayout.DEFAULT_SIZE, 939, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addComponent(pDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, 933, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pStudentSubmittedAssessment, javax.swing.GroupLayout.DEFAULT_SIZE, 939, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 50, 79));

        AssignmentSideButton.setText("ASSIGNMENT");
        AssignmentSideButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AssignmentSideButtonActionPerformed(evt);
            }
        });

        SuperviseeSideButton.setText("SUPERVISEE");
        SuperviseeSideButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SuperviseeSideButtonActionPerformed(evt);
            }
        });

        HomeSideButton.setText("HOME");
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
                    .addComponent(AssignmentSideButton, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(HomeSideButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(SuperviseeSideButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)))
                .addGap(17, 17, 17))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(HomeSideButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SuperviseeSideButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jButton1)
                .addGap(77, 77, 77)
                .addComponent(AssignmentSideButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(556, Short.MAX_VALUE))
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
        pAssignment.setVisible(false);
    }//GEN-LAST:event_SuperviseeSideButtonActionPerformed

    private void bStudentClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bStudentClearActionPerformed
        // TODO add your handling code here:
//        selectionID = "-1";
//        
//        tfIntakeCode.setText("");
//        tfStudentID.setText("");
//        tfStudentName.setText("");
//        refreshStudentTable("","","");
    }//GEN-LAST:event_bStudentClearActionPerformed

    private void HomeSideButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeSideButtonActionPerformed
        // TODO add your handling code here:
        selectionID = "-1";
        
        pHome.setVisible(true);
        pSupervisee.setVisible(false);
        pAssignment.setVisible(false);
    }//GEN-LAST:event_HomeSideButtonActionPerformed

    private void AssignmentSideButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AssignmentSideButtonActionPerformed
         //TODO add your handling code here:
        pAssignment.setVisible(true);
        pSupervisee.setVisible(false);
        pHome.setVisible(false);
    }//GEN-LAST:event_AssignmentSideButtonActionPerformed

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

    private void bStudentApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bStudentApplyActionPerformed
        // TODO add your handling code here:
//        String studentID = tfStudentID.getText().toLowerCase();
//        String studentName = tfStudentName.getText().toLowerCase();
//        String intakeCode = tfIntakeCode.getText().toLowerCase();
//        refreshStudentTable(studentID, studentName, intakeCode);
    }//GEN-LAST:event_bStudentApplyActionPerformed

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

    private void tStudentListMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tStudentListMouseReleased
        // TODO add your handling code here:
        //The id of the select record
        selectionID = tStudentList.getModel().getValueAt(tStudentList.getSelectedRow(), 0).toString();
        
    }//GEN-LAST:event_tStudentListMouseReleased

    private void bEditStudentrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEditStudentrActionPerformed
        // TODO add your handling code here:
//        EditStudentDetails editStu = new EditStudentDetails();
//        if (selectionID == "-1") {
//            //If no selected then notify user to select
//            JOptionPane.showMessageDialog(null, "Please select a product to edit!");
//        }
//        else{
//            
//            //Set and pass record and this page to EditProduct
//            editStu.setAdminPageInstance(this);
//            editStu.setRecordData(selectionID);
//            
//            //Open EditProduct page
//            editStu.setVisible(true);
//        }
//        
    }//GEN-LAST:event_bEditStudentrActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        createSupervisorAssessmentPanels();
        createSecondMarkerAssessmentPanels();
        pDashboard.setVisible(true);
        
        pAssignment.setVisible(false);
        pSupervisee.setVisible(false);
        pHome.setVisible(false);
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
        
        System.out.println(destFile);
        
        
        
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
        try {BufferedReader br = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assessment_student.txt"));
            while ((line = br.readLine()) != null) {
                String[] record = line.split("\t");
                String studentLine;
                String studentName = "";
                BufferedReader studentReader = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\student.txt"));
                while ((studentLine = studentReader.readLine()) != null) {
                    String[] studentRecord = studentLine.split("\t");
                    if (studentRecord[0].equals(record[1])) {
                        studentName = studentRecord[1];
                        break;
                    }
                }

                if (assessmentID.equals(record[0]) && selectionID.equals(record[1])){
                    lStudentID.setText(record[1]);
                    lStudentName.setText(studentName);
                    lSubmittedFile.setText(record[2]);
                    feedbackTextfield.setText(record[6]);
                    markTextfield.setText(record[5]);
                    destFile = record[2];
                }
            
            }
        }catch (Exception e) {
            e.getMessage();
        }
        
        
        
    }//GEN-LAST:event_studentTableMouseReleased

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
    private javax.swing.JButton AssignmentSideButton;
    private javax.swing.JLabel Description;
    private javax.swing.JLabel DescriptionTitle;
    private javax.swing.JButton HomeSideButton;
    private javax.swing.JLabel IntakeCodeLB;
    private javax.swing.JLabel ModuleLabel1;
    private javax.swing.JLabel StudentDetailsLabel;
    private javax.swing.JLabel StudentIDLB;
    private javax.swing.JLabel StudentNameLB;
    private javax.swing.JScrollPane StudentTableSP;
    private javax.swing.JButton SuperviseeSideButton;
    private javax.swing.JLabel TotalLB;
    private javax.swing.JTextField TotalStudentTF;
    private javax.swing.JButton bEditStudentr;
    private javax.swing.JButton bLecturerApply;
    private javax.swing.JButton bLecturerClear;
    private javax.swing.JButton bStudentApply;
    private javax.swing.JButton bStudentClear;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton downloadButton;
    private javax.swing.JLabel feedbackLabel;
    private javax.swing.JTextField feedbackTextfield;
    private javax.swing.JPanel filePanel;
    private javax.swing.JButton gradeButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lStudentID;
    private javax.swing.JLabel lStudentName;
    private javax.swing.JLabel lSubmittedFile;
    private javax.swing.JLabel mainTitleLabel;
    private javax.swing.JLabel markLabel;
    private javax.swing.JTextField markTextfield;
    private javax.swing.JPanel pAssignment;
    private javax.swing.JPanel pDashboard;
    private javax.swing.JPanel pHome;
    private javax.swing.JPanel pSecondMarkerAssessment;
    private javax.swing.JPanel pStudentSubmittedAssessment;
    private javax.swing.JPanel pSupervisee;
    private javax.swing.JPanel pSupervisorAssessment;
    private javax.swing.JLabel searchLabel;
    private javax.swing.JTextField searchTextfield;
    private javax.swing.JScrollPane studentScrollPane;
    private javax.swing.JTable studentTable;
    private javax.swing.JTable tLSuperviseeList;
    private javax.swing.JTable tStudentList;
    private javax.swing.JScrollPane tSuperviseeScrollPane2;
    private javax.swing.JTextField tfIntakeCode;
    private javax.swing.JTextField tfLecturerID;
    private javax.swing.JTextField tfLecturerName;
    private javax.swing.JTextField tfStudentID;
    private javax.swing.JTextField tfStudentName;
    // End of variables declaration//GEN-END:variables
}
