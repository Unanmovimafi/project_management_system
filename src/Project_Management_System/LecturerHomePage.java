/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Project_Management_System;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class LecturerHomePage extends javax.swing.JFrame {
    private String ID;
    private String[] assessmentRecord;
    private String[] assessmentStudentRecord;
    private String destFile;
    private int count = -1;
    
    ImageIcon icon = new ImageIcon("src\\Project_Management_System\\logo\\University_Logo.png");
    private Image scaledLogo = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
    ImageIcon scaledIcon = new ImageIcon(scaledLogo);
    
    public List<String> getAllLecturerRecord() {
        try {

            List<String> lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\lecturer.txt"));
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
    
    
    public void setHello() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\lecturer.txt"))) {
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

    private String[] lecRecord;

    int countOfLecRecord = -1;

    public void setID(String ID) {
        this.ID = ID;
        countAssessments();
        countSupervisees();
        setHello();
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
                            pProfile.setVisible(false);
                            ModuleLabel1.setText(record[1]);
                            Description.setText(record[3]);
                            bAcceptPresentation.setEnabled(false);
                            bRejectPresentation.setEnabled(false);
                            
                            lStudentID.setText("");
                            lStudentName.setText("");
                            lSubmittedFile.setText("");
                            feedbackTextfield.setEnabled(false);
                            markTextfield.setEnabled(false);
                            tfSecondFB.setEnabled(false);
                            tfSecondMark.setEnabled(false);
                            downloadButton.setEnabled(false);
                            bAcceptPresentation.setEnabled(false);
                            bRejectPresentation.setEnabled(false);
                            gradeButton.setEnabled(false);
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
                            pProfile.setVisible(false);

                            ModuleLabel1.setText(record[1]);
                            Description.setText(record[3]);
                            
                            
                            lStudentID.setText("");
                            lStudentName.setText("");
                            lSubmittedFile.setText("");
                            feedbackTextfield.setEnabled(false);
                            markTextfield.setEnabled(false);
                            tfSecondFB.setEnabled(false);
                            tfSecondMark.setEnabled(false);
                            downloadButton.setEnabled(false);
                            bAcceptPresentation.setEnabled(false);
                            bRejectPresentation.setEnabled(false);
                            gradeButton.setEnabled(false);
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
    

    public void refreshSupverviseeTable(String StuID, String IntakeCode, String AssType) {
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
                if(AssType.equals("ALL")){
                    AssType = "";
                }
                if (assessmentSupervisorID.equals(ID) && studentID.toLowerCase().startsWith(StuID) && intake.toLowerCase().startsWith(IntakeCode) && assessmentType.toLowerCase().startsWith(AssType)) {
                    model.addRow(new Object[]{intake, studentID, studentName, assessmentID, assessmentType, assessmentName});
                }
            }

            assessmentStudentReader.close();
        } catch (Exception e) {
            e.printStackTrace(); // Print stack trace for any exceptions
        }
    }
    
    public LecturerHomePage() {
        initComponents();
        pHome.setVisible(true);

        pReportsMarking.setVisible(false);
        pSupervisee.setVisible(false);
        pStudentSubmittedAssessment.setVisible(false);
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
        tSuperviseeScrollPane2 = new javax.swing.JScrollPane();
        tLSuperviseeList = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        tfSupStuID = new javax.swing.JTextField();
        bLecturerApply = new javax.swing.JButton();
        bLecturerClear = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cbAssType = new javax.swing.JComboBox<>();
        tfSupStuIntakeCode = new javax.swing.JTextField();
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
        jPanel12 = new javax.swing.JPanel();
        TotalPresentationLabel = new javax.swing.JLabel();
        TotalSuperviseeLabel = new javax.swing.JLabel();
        TotalAssessmentLabel = new javax.swing.JLabel();
        TotalUngradedLB = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        pProfile = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        tfID = new javax.swing.JTextField();
        tfName = new javax.swing.JTextField();
        tfContactNumber = new javax.swing.JTextField();
        tfOldPassword = new javax.swing.JTextField();
        tfEmail = new javax.swing.JTextField();
        tfAddress = new javax.swing.JTextField();
        rbMale = new javax.swing.JRadioButton();
        rbFemale = new javax.swing.JRadioButton();
        bChangePassword = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        tfRole = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        tfNationality = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        tfIC = new javax.swing.JTextField();
        tfDoB = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        tfNewPassword = new javax.swing.JTextField();
        tfConfirmPassword = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        SuperviseeSideButton = new javax.swing.JButton();
        HomeSideButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        lecLogoutBtn = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        lHelloWorld = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1500, 780));

        pReportsMarking.setBackground(new java.awt.Color(252, 247, 204));
        pReportsMarking.setPreferredSize(new java.awt.Dimension(1500, 780));

        mainTitleLabel.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        mainTitleLabel.setText("Reports Marking");
        mainTitleLabel.setPreferredSize(new java.awt.Dimension(200, 160));

        jScrollPane1.setViewportView(pSupervisorAssessment);

        jScrollPane2.setViewportView(pSecondMarkerAssessment);

        javax.swing.GroupLayout pReportsMarkingLayout = new javax.swing.GroupLayout(pReportsMarking);
        pReportsMarking.setLayout(pReportsMarkingLayout);
        pReportsMarkingLayout.setHorizontalGroup(
            pReportsMarkingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pReportsMarkingLayout.createSequentialGroup()
                .addGroup(pReportsMarkingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pReportsMarkingLayout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pReportsMarkingLayout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(mainTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(404, Short.MAX_VALUE))
        );
        pReportsMarkingLayout.setVerticalGroup(
            pReportsMarkingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pReportsMarkingLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(mainTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pReportsMarkingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        pSupervisee.setBackground(new java.awt.Color(255, 255, 255));

        jPanel9.setBackground(new java.awt.Color(0, 50, 79));
        jPanel9.setPreferredSize(new java.awt.Dimension(259, 94));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Supervisee Details");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel3)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(204, 204, 204));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Assessment Type:");

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

        tfSupStuID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSupStuIDActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(154, 154, 154)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setText("Intake code");

        cbAssType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ALL", "INTERNSHIP", "INVESTIGATION REPORTS", "CP1", "CP2", "RMCP", "FYP" }));

        tfSupStuIntakeCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSupStuIntakeCodeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tSuperviseeScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1042, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfSupStuIntakeCode))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfSupStuID, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(32, 32, 32)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(cbAssType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(241, 241, 241)
                        .addComponent(bLecturerApply)
                        .addGap(34, 34, 34)
                        .addComponent(bLecturerClear)))
                .addGap(18, 18, 18)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(bLecturerApply)
                            .addComponent(bLecturerClear)
                            .addComponent(jLabel6)
                            .addComponent(cbAssType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(tfSupStuID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(tfSupStuIntakeCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(tSuperviseeScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(216, Short.MAX_VALUE))
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pSuperviseeLayout = new javax.swing.GroupLayout(pSupervisee);
        pSupervisee.setLayout(pSuperviseeLayout);
        pSuperviseeLayout.setHorizontalGroup(
            pSuperviseeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 1987, Short.MAX_VALUE)
            .addGroup(pSuperviseeLayout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
                .addComponent(lSubmittedFile, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
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
                .addContainerGap(17, Short.MAX_VALUE))
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
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Feedback:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Mark:");

        tfSecondFB.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        tfSecondMark.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfSecondMark.setText("jTextField2");

        SubmissionDateLabel.setText("Submission Date");

        SubmissionTimeLabel.setText("Submission Time");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(SubmissionDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SubmissionTimeLabel)
                        .addGap(300, 300, 300))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(downloadButton)
                        .addGap(272, 272, 272))))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(359, 359, 359)
                        .addComponent(gradeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(cancelButton))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(feedbackLabel)
                                    .addComponent(markLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tfSecondFB, javax.swing.GroupLayout.PREFERRED_SIZE, 704, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tfSecondMark, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(markTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(feedbackTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 707, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(DescriptionTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Description, javax.swing.GroupLayout.PREFERRED_SIZE, 911, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DescriptionTitle)
                    .addComponent(Description))
                .addGap(13, 13, 13)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SubmissionTimeLabel)
                    .addComponent(SubmissionDateLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(downloadButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(feedbackLabel)
                    .addComponent(feedbackTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(markTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(markLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfSecondFB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(17, 17, 17)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfSecondMark, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
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
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(bAcceptPresentation)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bRejectPresentation)
                            .addComponent(lPresentationTime, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(228, 228, 228))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lStudentPresentationDate, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(254, 254, 254))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lStudentPresentationStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lStudentPresentationStatus)
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bAcceptPresentation)
                    .addComponent(bRejectPresentation))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pStudentSubmittedAssessmentLayout = new javax.swing.GroupLayout(pStudentSubmittedAssessment);
        pStudentSubmittedAssessment.setLayout(pStudentSubmittedAssessmentLayout);
        pStudentSubmittedAssessmentLayout.setHorizontalGroup(
            pStudentSubmittedAssessmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pStudentSubmittedAssessmentLayout.createSequentialGroup()
                .addGroup(pStudentSubmittedAssessmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pStudentSubmittedAssessmentLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 883, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pStudentSubmittedAssessmentLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(pStudentSubmittedAssessmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ModuleLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pStudentSubmittedAssessmentLayout.createSequentialGroup()
                                .addGap(186, 186, 186)
                                .addGroup(pStudentSubmittedAssessmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lStudentID)
                                    .addComponent(lStudentName))))
                        .addGap(69, 69, 69)
                        .addGroup(pStudentSubmittedAssessmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pStudentSubmittedAssessmentLayout.createSequentialGroup()
                                .addComponent(searchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchTextfield))
                            .addComponent(studentScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addGap(26, 26, 26)
                        .addComponent(ModuleLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(lStudentName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lStudentID)))
                .addGroup(pStudentSubmittedAssessmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pStudentSubmittedAssessmentLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pStudentSubmittedAssessmentLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pHome.setBackground(new java.awt.Color(252, 247, 204));

        jLabel10.setFont(new java.awt.Font("Segoe UI Black", 1, 48)); // NOI18N
        jLabel10.setText("Lecture Dashboard");

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel12.setForeground(new java.awt.Color(255, 255, 255));

        TotalPresentationLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        TotalPresentationLabel.setText("Total Pending Presentation Requests:");

        TotalSuperviseeLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        TotalSuperviseeLabel.setText("Total Supervisees:");

        TotalAssessmentLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        TotalAssessmentLabel.setText("Total Assessment:");

        TotalUngradedLB.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        TotalUngradedLB.setText("Total Ungraded Reports:");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("jLabel1");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setText("jLabel12");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setText("jLabel13");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel14.setText("jLabel14");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13)
                    .addComponent(jLabel12)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(TotalUngradedLB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TotalSuperviseeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TotalPresentationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TotalAssessmentLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel1))
                .addContainerGap(175, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(TotalPresentationLabel)
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addGap(44, 44, 44)
                .addComponent(TotalSuperviseeLabel)
                .addGap(39, 39, 39)
                .addComponent(jLabel12)
                .addGap(35, 35, 35)
                .addComponent(TotalAssessmentLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(28, 28, 28)
                .addComponent(TotalUngradedLB)
                .addGap(28, 28, 28)
                .addComponent(jLabel14)
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(522, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pHomeLayout = new javax.swing.GroupLayout(pHome);
        pHome.setLayout(pHomeLayout);
        pHomeLayout.setHorizontalGroup(
            pHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pHomeLayout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addGroup(pHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(791, Short.MAX_VALUE))
        );
        pHomeLayout.setVerticalGroup(
            pHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pHomeLayout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jLabel10)
                .addGap(43, 43, 43)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(186, Short.MAX_VALUE))
        );

        pProfile.setBackground(new java.awt.Color(255, 255, 255));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setText("ID:");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel22.setText("Name:");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setText("Contact Number:");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel24.setText("Gender:");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel25.setText("Address:");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel26.setText("Email:");

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel27.setText("Nationality:");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel28.setText("Old Password:");

        tfID.setEnabled(false);

        tfName.setEnabled(false);

        tfContactNumber.setEnabled(false);

        tfEmail.setEnabled(false);

        tfAddress.setEnabled(false);

        rbMale.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rbMale.setText("Male");
        rbMale.setEnabled(false);

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

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel29.setText("Role");

        tfRole.setEnabled(false);
        tfRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfRoleActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel30.setText("DOB:");

        tfNationality.setEnabled(false);

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel31.setText("IC/Passport No.:");

        tfIC.setEnabled(false);

        tfDoB.setEnabled(false);

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel32.setText("New Password:");

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel33.setText("Confirm Password:");

        javax.swing.GroupLayout pProfileLayout = new javax.swing.GroupLayout(pProfile);
        pProfile.setLayout(pProfileLayout);
        pProfileLayout.setHorizontalGroup(
            pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pProfileLayout.createSequentialGroup()
                .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pProfileLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pProfileLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pProfileLayout.createSequentialGroup()
                                                .addComponent(jLabel23)
                                                .addGap(7, 7, 7))
                                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel33)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pProfileLayout.createSequentialGroup()
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10))
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(tfNationality, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pProfileLayout.createSequentialGroup()
                                    .addGap(22, 22, 22)
                                    .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(tfID, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tfName, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                                        .addGroup(pProfileLayout.createSequentialGroup()
                                            .addComponent(rbMale, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(rbFemale, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(tfRole)
                                        .addComponent(tfIC))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pProfileLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfDoB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfContactNumber, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfOldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(pProfileLayout.createSequentialGroup()
                        .addGap(248, 248, 248)
                        .addComponent(bChangePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        pProfileLayout.setVerticalGroup(
            pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pProfileLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel21)
                    .addComponent(tfID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(tfIC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(tfRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(rbMale)
                    .addComponent(rbFemale))
                .addGap(18, 18, 18)
                .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(tfNationality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(tfDoB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(tfContactNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(tfAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(tfOldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(tfNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(tfConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(bChangePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(321, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1987, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pSupervisee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addComponent(pReportsMarking, javax.swing.GroupLayout.DEFAULT_SIZE, 1981, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pStudentSubmittedAssessment, javax.swing.GroupLayout.DEFAULT_SIZE, 1987, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pProfile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(307, Short.MAX_VALUE)))
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
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pProfile, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 50, 79));

        SuperviseeSideButton.setBackground(new java.awt.Color(255, 255, 206));
        SuperviseeSideButton.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        SuperviseeSideButton.setText("SUPERVISEE");
        SuperviseeSideButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SuperviseeSideButtonActionPerformed(evt);
            }
        });

        HomeSideButton.setBackground(new java.awt.Color(255, 255, 206));
        HomeSideButton.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        HomeSideButton.setText("DASHBOARD");
        HomeSideButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeSideButtonActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 255, 206));
        jButton1.setFont(new java.awt.Font("Segoe UI Black", 1, 16)); // NOI18N
        jButton1.setText("GRADE REPORT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lecLogoutBtn.setBackground(new java.awt.Color(255, 255, 206));
        lecLogoutBtn.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        lecLogoutBtn.setText("LOGOUT");
        lecLogoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lecLogoutBtnActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 206));
        jButton2.setFont(new java.awt.Font("Segoe UI Black", 1, 16)); // NOI18N
        jButton2.setText("PROFILE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        lHelloWorld.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        lHelloWorld.setForeground(new java.awt.Color(255, 255, 255));
        lHelloWorld.setText("jLabel16");

        jLabel16.setIcon(scaledIcon);

        jButton3.setBackground(new java.awt.Color(255, 255, 206));
        jButton3.setFont(new java.awt.Font("Segoe UI Black", 1, 16)); // NOI18N
        jButton3.setText("FEEDBACK");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lHelloWorld, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(HomeSideButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(SuperviseeSideButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(lecLogoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)))))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lHelloWorld)
                .addGap(33, 33, 33)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(HomeSideButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(SuperviseeSideButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82)
                .addComponent(lecLogoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105))
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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void SuperviseeSideButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SuperviseeSideButtonActionPerformed
        // TODO add your handling code here:
        refreshSupverviseeTable("","","");
        
        pSupervisee.setVisible(true);
        pHome.setVisible(false);

        pReportsMarking.setVisible(false);
        pStudentSubmittedAssessment.setVisible(false);
        pProfile.setVisible(false);
    }//GEN-LAST:event_SuperviseeSideButtonActionPerformed

    private void HomeSideButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeSideButtonActionPerformed
        // TODO add your handling code here:
        selectionID = "-1";
        
        pHome.setVisible(true);
        pSupervisee.setVisible(false);
        pStudentSubmittedAssessment.setVisible(false);
        pReportsMarking.setVisible(false);
        
        pProfile.setVisible(false);
    }//GEN-LAST:event_HomeSideButtonActionPerformed

    private void bLecturerApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLecturerApplyActionPerformed
        // TODO add your handling code here:
        refreshSupverviseeTable(tfSupStuID.getText(),tfSupStuIntakeCode.getText(),cbAssType.getSelectedItem().toString());
    }//GEN-LAST:event_bLecturerApplyActionPerformed

    private void bLecturerClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLecturerClearActionPerformed
        // TODO add your handling code here:
        tfSupStuID.setText("");
        tfSupStuIntakeCode.setText("");
        refreshSupverviseeTable("","","");
    }//GEN-LAST:event_bLecturerClearActionPerformed

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
        
        pProfile.setVisible(false);
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

        gradeButton.setEnabled(true);
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
                            feedbackTextfield.setEnabled(true);
                            markTextfield.setEnabled(true);
                            tfSecondFB.setEnabled(false);
                            tfSecondMark.setEnabled(false);

                        } else if (assstudentRecord[6].equals(ID)) {
                            feedbackTextfield.setEnabled(false);
                            markTextfield.setEnabled(false);
                            tfSecondFB.setEnabled(true);
                            tfSecondMark.setEnabled(true);
                        }
                        break;
                    }
                }
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
                    downloadButton.setEnabled(true);
                    destFile = record[2];
                }

                if (assessmentRecord[6].equals(ID)) {
                    if (assessmentRecord[0].equals(record[0]) && selectionID.equals(record[1])) {

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
                            lStudentPresentationStatus.setText("Second Marker accept the Presentation");
                            bAcceptPresentation.setEnabled(false);
                            bRejectPresentation.setEnabled(false);
                        } else if (record[11].equals("REJECT")) {
                            lStudentPresentationDate.setText(record[9]);
                            lPresentationTime.setText(record[10]);
                            lStudentPresentationStatus.setText("Waiting the Student to re-select the presentation date and time.");
                            bAcceptPresentation.setEnabled(false);
                            bRejectPresentation.setEnabled(false);
                        }
                    }
                } else {
                    if (record[11].equals("NA")) {
                        lStudentPresentationDate.setText("NA");
                        lPresentationTime.setText("NA");
                        lStudentPresentationStatus.setText("Student Haven't book Presentation");

                    } else if (record[11].equals("PENDING")) {
                        lStudentPresentationDate.setText(record[9]);
                        lPresentationTime.setText(record[10]);
                        lStudentPresentationStatus.setText("Waiting Approval");
                    } else if (record[11].equals("ACCEPT")) {
                        lStudentPresentationDate.setText(record[9]);
                        lPresentationTime.setText(record[10]);
                        lStudentPresentationStatus.setText("Second Marker accept the Presentation");
                    } else if (record[11].equals("REJECT")) {
                        lStudentPresentationDate.setText(record[9]);
                        lPresentationTime.setText(record[10]);
                        lStudentPresentationStatus.setText("Waiting the Student to re-select the presentation date and time.");
                    }

                    bAcceptPresentation.setEnabled(false);
                    bRejectPresentation.setEnabled(false);
                }
                break;
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

    private void lecLogoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lecLogoutBtnActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "Logged Out Successfully!");
        this.dispose();
        LoginPage lp = new LoginPage();

        lp.pack();
        lp.setLocationRelativeTo(null);
        lp.setVisible(true);
    }//GEN-LAST:event_lecLogoutBtnActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        pProfile.setVisible(true);
        
        pHome.setVisible(false);
        pStudentSubmittedAssessment.setVisible(false);
        pReportsMarking.setVisible(false);
        pSupervisee.setVisible(false);
        try (BufferedReader reader = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\lecturer.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lecRecord = line.split("\t");
                countOfLecRecord ++;
                if (lecRecord[0].equals(ID)) {
                    // reutrn the line of the line_num in text file
                    break;
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        
        tfID.setText(lecRecord[0]);
        tfName.setText(lecRecord[1]);
        tfIC.setText(lecRecord[2]);
        tfRole.setText(lecRecord[3]);
        
        if (lecRecord[4].equals("MALE")){
            rbMale.setSelected(true);
        }
        else if (lecRecord[4].equals("FEMALE")){
            rbFemale.setSelected(true);
        }
        
        tfNationality.setText(lecRecord[5]);
        tfDoB.setText(lecRecord[6]);
        tfContactNumber.setText(lecRecord[7]);
        
        tfEmail.setText(lecRecord[9]);
        tfAddress.setText(lecRecord[10]);
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void bChangePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bChangePasswordActionPerformed
        // TODO add your handling code here:
        if (tfOldPassword.getText().isEmpty() || tfNewPassword.getText().isEmpty() || tfConfirmPassword.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter all fields.");
        } else if (tfOldPassword.getText().equals(lecRecord[8]) && (tfNewPassword.getText().equals(tfConfirmPassword.getText()))) {
            //Get the value from each text field
            lecRecord[8] = tfNewPassword.getText();

            try {
                List<String> lines = getAllLecturerRecord();
                //Write the information to the text file
                String editedStudent = String.join("\t", lecRecord);

                lines.set(countOfLecRecord, editedStudent);
                BufferedWriter writer = new BufferedWriter(new FileWriter("src\\Project_Management_System\\database\\lecturer.txt"));
                for (String updatedLine : lines) {
                    writer.write(updatedLine);
                    writer.newLine();
                }

                writer.close();

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

            JOptionPane.showMessageDialog(null, "Successfully Saved!");
            pHome.setVisible(true);
            pProfile.setVisible(false);
            pStudentSubmittedAssessment.setVisible(false);
            pReportsMarking.setVisible(false);
            pSupervisee.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Password Wrong!");
        }
    }//GEN-LAST:event_bChangePasswordActionPerformed

    private void tfRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfRoleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfRoleActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // TODO add your handling code here:
        pReportsMarking.setVisible(true);
        
        pSupervisee.setVisible(false);
        pHome.setVisible(false);
        pStudentSubmittedAssessment.setVisible(false);
        pProfile.setVisible(false);
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void tfSupStuIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSupStuIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSupStuIDActionPerformed

    private void tfSupStuIntakeCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSupStuIntakeCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSupStuIntakeCodeActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Feedback fb = new Feedback();
        fb.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(LecturerHomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LecturerHomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LecturerHomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LecturerHomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LecturerHomePage().setVisible(true);
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
    private javax.swing.JButton bAcceptPresentation;
    private javax.swing.JButton bChangePassword;
    private javax.swing.JButton bLecturerApply;
    private javax.swing.JButton bLecturerClear;
    private javax.swing.JButton bRejectPresentation;
    private javax.swing.JButton cancelButton;
    private javax.swing.JComboBox<String> cbAssType;
    private javax.swing.JButton downloadButton;
    private javax.swing.JLabel feedbackLabel;
    private javax.swing.JTextField feedbackTextfield;
    private javax.swing.JPanel filePanel;
    private javax.swing.JButton gradeButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
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
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lHelloWorld;
    private javax.swing.JLabel lPresentationTime;
    private javax.swing.JLabel lStudentID;
    private javax.swing.JLabel lStudentName;
    private javax.swing.JLabel lStudentPresentationDate;
    private javax.swing.JLabel lStudentPresentationStatus;
    private javax.swing.JLabel lSubmittedFile;
    private javax.swing.JButton lecLogoutBtn;
    private javax.swing.JLabel mainTitleLabel;
    private javax.swing.JLabel markLabel;
    private javax.swing.JTextField markTextfield;
    private javax.swing.JPanel pHome;
    private javax.swing.JPanel pProfile;
    private javax.swing.JPanel pReportsMarking;
    private javax.swing.JPanel pSecondMarkerAssessment;
    private javax.swing.JPanel pStudentSubmittedAssessment;
    private javax.swing.JPanel pSupervisee;
    private javax.swing.JPanel pSupervisorAssessment;
    private javax.swing.JRadioButton rbFemale;
    private javax.swing.JRadioButton rbMale;
    private javax.swing.JLabel searchLabel;
    private javax.swing.JTextField searchTextfield;
    private javax.swing.JScrollPane studentScrollPane;
    private javax.swing.JTable studentTable;
    private javax.swing.JTable tLSuperviseeList;
    private javax.swing.JScrollPane tSuperviseeScrollPane2;
    private javax.swing.JTextField tfAddress;
    private javax.swing.JTextField tfConfirmPassword;
    private javax.swing.JTextField tfContactNumber;
    private javax.swing.JTextField tfDoB;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfIC;
    private javax.swing.JTextField tfID;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfNationality;
    private javax.swing.JTextField tfNewPassword;
    private javax.swing.JTextField tfOldPassword;
    private javax.swing.JTextField tfRole;
    private javax.swing.JTextField tfSecondFB;
    private javax.swing.JTextField tfSecondMark;
    private javax.swing.JTextField tfSupStuID;
    private javax.swing.JTextField tfSupStuIntakeCode;
    // End of variables declaration//GEN-END:variables
}
