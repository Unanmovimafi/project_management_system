/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Project_Management_System;

import java.awt.Image;
import java.io.*;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author User
 */
public class AdminHomePage extends javax.swing.JFrame {
    
    
    ImageIcon icon = new ImageIcon("src\\Project_Management_System\\logo\\University_Logo.png");
    private Image scaledLogo = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
    ImageIcon scaledIcon = new ImageIcon(scaledLogo);
    
    private void ImportLecCSV(){
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File file=chooser.getSelectedFile();
        if (file != null){
            String filePath = file.getAbsolutePath();
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line2;

                while ((line2 = br.readLine()) != null){
                    String[] values = line2.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                    String newID = getLecNewID();
                    try {
                    StringBuilder product = new StringBuilder();
                    String password = newID + "@12345";
                    
                    values[9] = values[9].replaceAll("^\"|\"$", "");
                    
                    //Write the information to the text file
                    product.append(newID + "\t" + values[1] + "\t" +values[2]+ "\t" + values[3] + "\t" + values[4] + "\t" + values[5] + "\t" +values[6] + "\t" + values[7] + "\t" + password + "\t" + values[8] + "\t" + values[9]);
                    BufferedWriter writer = new BufferedWriter(new FileWriter("src\\Project_Management_System\\database\\lecturer.txt", true));
                    writer.write(product + "\n");
                    writer.close();
                    } 
                     catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                }
                JOptionPane.showMessageDialog(null, "Successfully Import!");
            }
            catch (Exception e) {
                e.getMessage();
                JOptionPane.showMessageDialog(null, "Invalid File or Format Wrong in CSV File!");
            }
        }
        refreshLecturerTable("","");
    }

    private void ImportStuCSV(){
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File file=chooser.getSelectedFile();
        if (file != null){
            String filePath = file.getAbsolutePath();
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line2;

                while ((line2 = br.readLine()) != null){
                    String[] values = line2.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                    String newID = getStuNewID();
                    try {
                    StringBuilder product = new StringBuilder();
                    String password = newID +  "@12345";
                    
                    values[9] = values[9].replaceAll("^\"|\"$", "");
                    
                    //Write the information to the text file
                    product.append(newID + "\t" + values[1] + "\t" +values[2]+ "\t" + values[3] + "\t" + values[4] + "\t" + values[5] + "\t" +values[6] + "\t" + values[7] + "\t" + password + "\t" + values[8] + "\t" + values[9]);
                    BufferedWriter writer = new BufferedWriter(new FileWriter("src\\Project_Management_System\\database\\student.txt", true));
                    writer.write(product + "\n");
                    writer.close();
                    }
                    
                     catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                }
                JOptionPane.showMessageDialog(null, "Successfully Import!");
            }
            catch (Exception e) {
                e.getMessage();
                JOptionPane.showMessageDialog(null, "Invalid File or Format Wrong in CSV File!");
            }
        }
        refreshStudentTable("","","");
        
        
        
    }
    
    public void refreshFeedbackTable(String Title) {
        DefaultTableModel model = (DefaultTableModel)tFeedback.getModel();
        model.setRowCount(0);
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\feedback.txt"));
            while ((line = br.readLine()) != null) {
                String[] record = line.split("\t");
                if (record[0].toLowerCase().startsWith(Title)) {
                    String [] newRecord = new String[2];
                    System.arraycopy(record, 0, newRecord, 0, 2);
                    //Add the record to Table
                    model.addRow(newRecord);
                    }
                }
            br.close();
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    
    public void refreshLecturerTable(String IDOfLecturer, String nameOfLecturer) {
        DefaultTableModel model = (DefaultTableModel)tLecturerList.getModel();
        model.setRowCount(0);
        String line;
        
        try {
            BufferedReader br = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\lecturer.txt"));
            while ((line = br.readLine()) != null) {
                String[] record = line.split("\t");
                if (record[0].toLowerCase().startsWith(IDOfLecturer) && record[1].toLowerCase().startsWith(nameOfLecturer) ) {
                    String [] newRecord = new String[record.length - 3];
                    //Skip IC, Password, Address
                    System.arraycopy(record, 0, newRecord, 0, 2);
                    System.arraycopy(record, 3, newRecord, 2, 5);
                    System.arraycopy(record, 9, newRecord, 7, 1);
                    //Add the record to Table
                    model.addRow(newRecord);
                    }
                }
            br.close();
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public void refreshStudentTable(String IDOfStudent, String nameOfStudent, String intakeCode) {
        DefaultTableModel model = (DefaultTableModel)tStudentList.getModel();
        model.setRowCount(0);
        String line;
        
        try {
            BufferedReader br = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\student.txt"));
            while ((line = br.readLine()) != null) {
                String[] record = line.split("\t");
                
                if (record[0].toLowerCase().startsWith(IDOfStudent) && record[1].toLowerCase().startsWith(nameOfStudent) && record[3].toLowerCase().startsWith(intakeCode)) {
                    String [] newRecord = new String[record.length - 3];
                    //Skip IC, Password, Address
                    System.arraycopy(record, 0, newRecord, 0, 2);
                    System.arraycopy(record, 3, newRecord, 2, 5);
                    System.arraycopy(record, 9, newRecord, 7, 1);
                    //Add the record to Table
                    model.addRow(newRecord);
                    }
                }
            br.close();
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    private String getLecNewID() {
        int maximumNumber = 0;
        String line;
        String newID;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\lecturer.txt"));

            while ((line = bufferedReader.readLine()) != null) {
                String[] lineArray = line.split("\t");
                String numberOfID = lineArray[0].substring(2);
                int currentNumber = Integer.parseInt(numberOfID);
                if (currentNumber > maximumNumber) {
                    //Get the maximum id in text file
                    maximumNumber = currentNumber;
                }
            }
            //The maximum number plus one to be the id
            maximumNumber = maximumNumber + 1;
            bufferedReader.close();
            String newLecturerID = String.format("%05d", maximumNumber);
            return newID = "LR" + newLecturerID;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
    
    private String getStuNewID(){
        int maximumNumber = 0;
        String line;
        String newID;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\student.txt"));

            while ((line = bufferedReader.readLine()) != null) {
                String[] lineArray = line.split("\t");
                String numberOfID = lineArray[0].substring(2);
                int currentNumber = Integer.parseInt(numberOfID);
                if (currentNumber > maximumNumber) {
                    //Get the maximum id in text file
                    maximumNumber = currentNumber;
                }
            }
            //The maximum number plus one to be the id
            maximumNumber = maximumNumber + 1;
            bufferedReader.close();
            String newLecturerID = String.format("%05d", maximumNumber);
            return newID = "LR" + newLecturerID;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
    
    /**
     * Creates new form AdminHomePage
     */
    public AdminHomePage() {
        initComponents();
        pHome.setVisible(true);
        pLecturer.setVisible(false);
        pStudent.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        bStudent = new javax.swing.JButton();
        bLecturer = new javax.swing.JButton();
        bHome = new javax.swing.JButton();
        adminLogoutBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        bStudent1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tFeedback = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        pHome = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        pLecturer = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        tfLecturerName = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tLecturerList = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        tfLecturerID = new javax.swing.JTextField();
        bLecturerApply = new javax.swing.JButton();
        bLecturerClear = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        bImportLecturer = new javax.swing.JButton();
        btnExportLecturer = new javax.swing.JButton();
        pStudent = new javax.swing.JPanel();
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
        AddStudentBtn = new javax.swing.JButton();
        bEditStudentr = new javax.swing.JButton();
        bImportStudent = new javax.swing.JButton();
        btnExportStudent = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1500, 780));

        jPanel2.setBackground(new java.awt.Color(0, 50, 79));

        bStudent.setBackground(new java.awt.Color(255, 255, 206));
        bStudent.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        bStudent.setText("STUDENT");
        bStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bStudentActionPerformed(evt);
            }
        });

        bLecturer.setBackground(new java.awt.Color(255, 255, 206));
        bLecturer.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        bLecturer.setText("LECTURER");
        bLecturer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLecturerActionPerformed(evt);
            }
        });

        bHome.setBackground(new java.awt.Color(255, 255, 206));
        bHome.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        bHome.setText("HOME");
        bHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bHomeActionPerformed(evt);
            }
        });

        adminLogoutBtn.setBackground(new java.awt.Color(255, 255, 206));
        adminLogoutBtn.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        adminLogoutBtn.setText("LOGOUT");
        adminLogoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminLogoutBtnActionPerformed(evt);
            }
        });

        jLabel2.setIcon(scaledIcon);

        bStudent1.setBackground(new java.awt.Color(255, 255, 206));
        bStudent1.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        bStudent1.setText("FEEDBACK");
        bStudent1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bStudent1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(adminLogoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(bLecturer, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bHome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bStudent1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bHome, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(bLecturer, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(bStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(bStudent1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(adminLogoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        tFeedback.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Index", "Title"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tFeedback);

        jButton1.setText("VIEW");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 794, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(jButton1)
                .addContainerGap(896, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(jButton1)))
                .addContainerGap(111, Short.MAX_VALUE))
        );

        pHome.setBackground(new java.awt.Color(252, 247, 204));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        jLabel1.setText("Admin Dashboard");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton3.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jButton3.setText("IMPORT LECTURER CSV");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jButton4.setText("ADD LECTURER");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton5.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jButton5.setText("IMPORT STUDENT CSV");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jButton6.setText("ADD STUDENT");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(112, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Quick Access Functions:");

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
                        .addGap(158, 158, 158)
                        .addGroup(pHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(877, Short.MAX_VALUE))
        );
        pHomeLayout.setVerticalGroup(
            pHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pHomeLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel1)
                .addGap(60, 60, 60)
                .addComponent(jLabel7)
                .addGap(28, 28, 28)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(102, Short.MAX_VALUE))
        );

        pLecturer.setBackground(new java.awt.Color(255, 255, 255));

        jPanel9.setBackground(new java.awt.Color(0, 50, 79));
        jPanel9.setPreferredSize(new java.awt.Dimension(259, 94));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Lecturer Details");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(543, 543, 543)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        jLabel5.setText("Lecturer Name:");

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));

        tLecturerList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Role", "Gender", "Nationality", "DoB", "Contact Number", "Email"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tLecturerList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tLecturerListMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tLecturerList);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Lecturer ID:");

        bLecturerApply.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bLecturerApply.setText("Apply");
        bLecturerApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLecturerApplyActionPerformed(evt);
            }
        });

        bLecturerClear.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bLecturerClear.setText("Clear");
        bLecturerClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLecturerClearActionPerformed(evt);
            }
        });

        jPanel10.setBackground(new java.awt.Color(252, 247, 204));

        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton7.setText("Add New Lecturer");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton8.setText("Edit Lecturer");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        bImportLecturer.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bImportLecturer.setText("Import Lecturer CSV");
        bImportLecturer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bImportLecturerActionPerformed(evt);
            }
        });

        btnExportLecturer.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnExportLecturer.setText("Export");
        btnExportLecturer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportLecturerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bImportLecturer, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                            .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnExportLecturer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel4)
                .addGap(49, 49, 49)
                .addComponent(bImportLecturer, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExportLecturer, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tfLecturerName, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86)
                        .addComponent(bLecturerApply)
                        .addGap(27, 27, 27)
                        .addComponent(bLecturerClear))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1042, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(118, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tfLecturerName, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bLecturerApply, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bLecturerClear, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfLecturerID, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(32, 32, 32)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pLecturerLayout = new javax.swing.GroupLayout(pLecturer);
        pLecturer.setLayout(pLecturerLayout);
        pLecturerLayout.setHorizontalGroup(
            pLecturerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 1886, Short.MAX_VALUE)
            .addGroup(pLecturerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(445, Short.MAX_VALUE))
        );
        pLecturerLayout.setVerticalGroup(
            pLecturerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pLecturerLayout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pStudent.setBackground(new java.awt.Color(255, 255, 255));
        pStudent.setPreferredSize(new java.awt.Dimension(1500, 780));

        jPanel13.setBackground(new java.awt.Color(0, 50, 79));

        StudentDetailsLabel.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        StudentDetailsLabel.setForeground(new java.awt.Color(255, 255, 255));
        StudentDetailsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        StudentDetailsLabel.setText("Student Details");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(509, 509, 509)
                .addComponent(StudentDetailsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(669, Short.MAX_VALUE))
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

        AddStudentBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        AddStudentBtn.setText("Add New Student");
        AddStudentBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddStudentBtnActionPerformed(evt);
            }
        });

        bEditStudentr.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bEditStudentr.setText("Edit Student");
        bEditStudentr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEditStudentrActionPerformed(evt);
            }
        });

        bImportStudent.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bImportStudent.setText("Import Student CSV");
        bImportStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bImportStudentActionPerformed(evt);
            }
        });

        btnExportStudent.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnExportStudent.setText("Export");
        btnExportStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportStudentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnExportStudent, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bImportStudent, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                    .addComponent(AddStudentBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bEditStudentr, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 15, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(bImportStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(AddStudentBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bEditStudentr, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExportStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(IntakeCodeLB)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(TotalLB)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TotalStudentTF, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(112, 112, 112)
                        .addComponent(tfIntakeCode, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(StudentIDLB)
                        .addGap(18, 18, 18)
                        .addComponent(tfStudentID, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(StudentNameLB)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(bStudentClear, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bStudentApply, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tfStudentName, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(StudentTableSP, javax.swing.GroupLayout.PREFERRED_SIZE, 1045, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)))
                .addGap(30, 30, 30)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
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

        javax.swing.GroupLayout pStudentLayout = new javax.swing.GroupLayout(pStudent);
        pStudent.setLayout(pStudentLayout);
        pStudentLayout.setHorizontalGroup(
            pStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pStudentLayout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pStudentLayout.setVerticalGroup(
            pStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pStudentLayout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pLecturer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pStudent, javax.swing.GroupLayout.DEFAULT_SIZE, 1886, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pLecturer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bLecturerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLecturerActionPerformed
        // TODO add your handling code here:
        selectionID = "-1";
        
        pLecturer.setVisible(true);
        refreshLecturerTable("","");
        pHome.setVisible(false);
        pStudent.setVisible(false);
    }//GEN-LAST:event_bLecturerActionPerformed

    private void bStudentClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bStudentClearActionPerformed
        // TODO add your handling code here:
        selectionID = "-1";
        
        tfIntakeCode.setText("");
        tfStudentID.setText("");
        tfStudentName.setText("");
        refreshStudentTable("","","");
    }//GEN-LAST:event_bStudentClearActionPerformed

    private void bHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bHomeActionPerformed
        // TODO add your handling code here:
        selectionID = "-1";
        
        pHome.setVisible(true);
        pLecturer.setVisible(false);
        pStudent.setVisible(false);
    }//GEN-LAST:event_bHomeActionPerformed

    private void bStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bStudentActionPerformed
        // TODO add your handling code here:
        pStudent.setVisible(true);
        refreshStudentTable("","","");
        pLecturer.setVisible(false);
        pHome.setVisible(false);
    }//GEN-LAST:event_bStudentActionPerformed

    private void bLecturerApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLecturerApplyActionPerformed
        // TODO add your handling code here:
        String lecturerID = tfLecturerID.getText().toLowerCase();
        String lecturerName = tfLecturerName.getText().toLowerCase();
        refreshLecturerTable(lecturerID, lecturerName);
    }//GEN-LAST:event_bLecturerApplyActionPerformed

    private void bLecturerClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLecturerClearActionPerformed
        // TODO add your handling code here:
        tfLecturerID.setText("");
        tfLecturerName.setText("");
        refreshLecturerTable("","");
    }//GEN-LAST:event_bLecturerClearActionPerformed

    private void bStudentApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bStudentApplyActionPerformed
        // TODO add your handling code here:
        String studentID = tfStudentID.getText().toLowerCase();
        String studentName = tfStudentName.getText().toLowerCase();
        String intakeCode = tfIntakeCode.getText().toLowerCase();
        refreshStudentTable(studentID, studentName, intakeCode);
    }//GEN-LAST:event_bStudentApplyActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        
        AddLecturerDetails addLec = new AddLecturerDetails();
        addLec.setAdminPageInstance(this);
        
        addLec.setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        
        
        
        
        
        
        EditLecturerDetails eld = new EditLecturerDetails();
        if (selectionID == "-1") {
            //If no selected then notify user to select
            JOptionPane.showMessageDialog(null, "Please select a product to edit!");
        }
        else{
            
            //Set and pass record and this page to EditProduct
            eld.setAdminPageInstance(this);
            try {
                eld.setRecordData(selectionID);
            } catch (ParseException ex) {
                Logger.getLogger(AdminHomePage.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //Open EditProduct page
            eld.setVisible(true);
        }
        
        
        
    }//GEN-LAST:event_jButton8ActionPerformed

    private void tLecturerListMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tLecturerListMouseReleased
        // TODO add your handling code here:
        //The id of the select record
        selectionID = tLecturerList.getModel().getValueAt(tLecturerList.getSelectedRow(), 0).toString();
    }//GEN-LAST:event_tLecturerListMouseReleased

    private void AddStudentBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddStudentBtnActionPerformed
        // TODO add your handling code here:
        AddStudentDetails addStu = new AddStudentDetails();
        addStu.setAdminPageInstance(this);
        addStu.setVisible(true);
        
    }//GEN-LAST:event_AddStudentBtnActionPerformed

    private void tStudentListMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tStudentListMouseReleased
        // TODO add your handling code here:
        //The id of the select record
        selectionID = tStudentList.getModel().getValueAt(tStudentList.getSelectedRow(), 0).toString();
        
    }//GEN-LAST:event_tStudentListMouseReleased

    private void bEditStudentrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEditStudentrActionPerformed
        // TODO add your handling code here:
        EditStudentDetails editStu = new EditStudentDetails();
        if (selectionID == "-1") {
            //If no selected then notify user to select
            JOptionPane.showMessageDialog(null, "Please select a product to edit!");
        }
        else{
            
            //Set and pass record and this page to EditProduct
            editStu.setAdminPageInstance(this);
            try {
                editStu.setRecordData(selectionID);
            } catch (ParseException ex) {
                Logger.getLogger(AdminHomePage.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //Open EditProduct page
            editStu.setVisible(true);
        }
        
    }//GEN-LAST:event_bEditStudentrActionPerformed

    private void bImportLecturerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bImportLecturerActionPerformed
        // TODO add your handling code here:
        ImportLecCSV();
    }//GEN-LAST:event_bImportLecturerActionPerformed

    private void bImportStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bImportStudentActionPerformed
        // TODO add your handling code here:
        ImportStuCSV();
    }//GEN-LAST:event_bImportStudentActionPerformed

    private void adminLogoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminLogoutBtnActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "Logged Out Successfully!");
        this.dispose();
        LoginPage lp = new LoginPage();

        lp.setVisible(true);

    }//GEN-LAST:event_adminLogoutBtnActionPerformed

    private void btnExportStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportStudentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExportStudentActionPerformed

    private void btnExportLecturerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportLecturerActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.showOpenDialog(null);
        File file = chooser.getSelectedFile();
        String path = file.getAbsolutePath() + "//Report.csv";

        try {
            TableModel model = tLecturerList.getModel();
            FileWriter csv = new FileWriter(new File(path));

            // Write column names
            for (int i = 0; i < model.getColumnCount(); i++) {
                csv.write(model.getColumnName(i) + ",");
            }
            csv.write("\n");

            // Write row data
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    csv.write(model.getValueAt(i, j).toString() + ",");
                }
                csv.write("\n");
            }

            csv.close();
        } catch (Exception e) {
            e.getMessage();
        }
    }//GEN-LAST:event_btnExportLecturerActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        ImportStuCSV();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        ImportLecCSV();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        AddLecturerDetails addLec = new AddLecturerDetails();
        addLec.setAdminPageInstance(this);
        
        addLec.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        AddStudentDetails addStu = new AddStudentDetails();
        addStu.setAdminPageInstance(this);
        addStu.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void bStudent1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bStudent1ActionPerformed
        // TODO add your handling code here:
        
        refreshFeedbackTable("");
        jPanel1.setVisible(true);
        pLecturer.setVisible(false);
        pHome.setVisible(false);
        pStudent.setVisible(false);
    }//GEN-LAST:event_bStudent1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:\
        ViewFeedback vf = new ViewFeedback();
        try {
            String selectID = tFeedback.getModel().getValueAt(tFeedback.getSelectedRow(), 0).toString();
            vf.setRecordData(selectID);
            //Open EditProduct page
            vf.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please select a title to view!");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(AdminHomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminHomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminHomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminHomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminHomePage().setVisible(true);
            }
        });
    }
    
    private String selectionID = "-1";

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddStudentBtn;
    private javax.swing.JLabel IntakeCodeLB;
    private javax.swing.JLabel StudentDetailsLabel;
    private javax.swing.JLabel StudentIDLB;
    private javax.swing.JLabel StudentNameLB;
    private javax.swing.JScrollPane StudentTableSP;
    private javax.swing.JLabel TotalLB;
    private javax.swing.JTextField TotalStudentTF;
    private javax.swing.JButton adminLogoutBtn;
    private javax.swing.JButton bEditStudentr;
    private javax.swing.JButton bHome;
    private javax.swing.JButton bImportLecturer;
    private javax.swing.JButton bImportStudent;
    private javax.swing.JButton bLecturer;
    private javax.swing.JButton bLecturerApply;
    private javax.swing.JButton bLecturerClear;
    private javax.swing.JButton bStudent;
    private javax.swing.JButton bStudent1;
    private javax.swing.JButton bStudentApply;
    private javax.swing.JButton bStudentClear;
    private javax.swing.JButton btnExportLecturer;
    private javax.swing.JButton btnExportStudent;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pHome;
    private javax.swing.JPanel pLecturer;
    private javax.swing.JPanel pStudent;
    private javax.swing.JTable tFeedback;
    private javax.swing.JTable tLecturerList;
    private javax.swing.JTable tStudentList;
    private javax.swing.JTextField tfIntakeCode;
    private javax.swing.JTextField tfLecturerID;
    private javax.swing.JTextField tfLecturerName;
    private javax.swing.JTextField tfStudentID;
    private javax.swing.JTextField tfStudentName;
    // End of variables declaration//GEN-END:variables
}
