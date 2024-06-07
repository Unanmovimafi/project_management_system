/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Project_Management_System;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author User
 */
public class EditAssessment extends javax.swing.JFrame {
    
    int count = -1;
    private String selectedAssessID;
    
    private String[] Assessrecord;
    
    private ProjectManagerHomePage ProjectManagerHomePage;
    
    
    public void setPMPageInstance(ProjectManagerHomePage PMhomePage) {
        this.ProjectManagerHomePage = PMhomePage;
    }
    
    public String[] getAssessRecord(int line_num) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assessment.txt"))) {
            String line;
            int count = -1;
            while ((line = reader.readLine()) != null) {
                String[] lineArray = line.trim().split("\t");
                count = count + 1;
                if (count == line_num) {
                    // reutrn the line of the line_num in text file
                    return lineArray;
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return null; // Patient record not found
    }
    
    public List<String> getAllAssessRecord() {
        try {
            
            List<String> lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assessment.txt"));
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
    public List<String> getAllAssessStudentRecord() {
        try {
            
            List<String> lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assessment.txt"));
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
    
    public void setRecordData(String ID) throws ParseException {
        
        this.selectedAssessID = ID;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        
        try (BufferedReader reader = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assessment.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lineArray = line.split("\t");
                    count = count + 1;
                if (selectedAssessID.equals(lineArray[0])) {
                    break;
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        
        Assessrecord = getAssessRecord(count);
        //Get the value from text field
        
        tfID.setText(Assessrecord[0]);
        tfName.setText(Assessrecord[1]);
        cbType.setSelectedItem(Assessrecord[2]);
        tfDescription.setText(Assessrecord[3]);
        dcDueDate.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(Assessrecord[4]));
        
        tfSupervisor.setText(Assessrecord[5]);
        tfSecondMarker.setText(Assessrecord[6]);
        
        try (BufferedReader reader = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\lecturer.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lineArray = line.split("\t");
                if (Assessrecord[5].equals(lineArray[0])) {
                    tfSupervisorName.setText(lineArray[1]);
                }
                
                if (Assessrecord[6].equals(lineArray[0])) {
                    tfSecondMarkerName.setText(lineArray[1]);
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        
    }
    
    public void refreshSupervisorListTable(String IDOrNameOfLecturer) {
        DefaultTableModel model = (DefaultTableModel)tSupervisorList.getModel();
        model.setRowCount(0);
        String line;
        
        try {
            BufferedReader br = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\lecturer.txt"));
            while ((line = br.readLine()) != null) {
                String[] record = line.split("\t");
                if (record[0].toLowerCase().startsWith(IDOrNameOfLecturer) || record[1].toLowerCase().startsWith(IDOrNameOfLecturer) ) {
                    String [] newRecord = new String[2];
                    //Skip IC, Password, Address
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
    
    
    public void refreshSecondMarkerListTable(String IDOrNameOfLecturer) {
        DefaultTableModel model = (DefaultTableModel)tSecondMarkerList.getModel();
        model.setRowCount(0);
        String line;
        
        try {
            BufferedReader br = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\lecturer.txt"));
            while ((line = br.readLine()) != null) {
                String[] record = line.split("\t");
                if (record[0].toLowerCase().startsWith(IDOrNameOfLecturer) || record[1].toLowerCase().startsWith(IDOrNameOfLecturer) ) {
                    String [] newRecord = new String[2];
                    //Skip IC, Password, Address
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
    
    
    
    

    /**
     * Creates new form EditLecturerDetails
     */
    public EditAssessment() {
        initComponents();
        refreshSupervisorListTable("");
        refreshSecondMarkerListTable("");
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        tfDescription = new javax.swing.JTextField();
        bSave = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tfID = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tfSupervisor = new javax.swing.JTextField();
        tfSecondMarker = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tSupervisorList = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tSecondMarkerList = new javax.swing.JTable();
        tfSearchSupervisor = new javax.swing.JTextField();
        tfSearchSecondMarker = new javax.swing.JTextField();
        tfSupervisorName = new javax.swing.JTextField();
        tfSecondMarkerName = new javax.swing.JTextField();
        cbType = new javax.swing.JComboBox<>();
        dcDueDate = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        deleteAssessmentBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Name:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Description:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Due Date:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Second Marker:");

        bSave.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bSave.setText("SAVE");
        bSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSaveActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("ID:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Type:");

        tfID.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Supervisor:");

        tfSupervisor.setEditable(false);

        tfSecondMarker.setEditable(false);

        tSupervisorList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tSupervisorList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tSupervisorListMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tSupervisorList);

        tSecondMarkerList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tSecondMarkerList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tSecondMarkerListMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tSecondMarkerList);

        tfSearchSupervisor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfSearchSupervisorKeyReleased(evt);
            }
        });

        tfSearchSecondMarker.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfSearchSecondMarkerKeyReleased(evt);
            }
        });

        tfSupervisorName.setEditable(false);

        tfSecondMarkerName.setEditable(false);

        cbType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "INTERNSHIP", "INVESTIGATION REPORTS", "CP1", "CP2", "RMCP", "FYP" }));

        dcDueDate.setDateFormatString("dd/MM/yyyy");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Search:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Search:");

        deleteAssessmentBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        deleteAssessmentBtn.setText("DELETE");
        deleteAssessmentBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteAssessmentBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel3)
                                            .addGap(57, 57, 57))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel8)
                                            .addGap(18, 18, 18))))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfID, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(dcDueDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(tfSecondMarker, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                                        .addComponent(tfSupervisor, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(tfSecondMarkerName, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                                        .addComponent(tfSupervisorName)))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfSearchSecondMarker, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(tfSearchSupervisor, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(70, 70, 70))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(263, 263, 263)
                        .addComponent(bSave)
                        .addGap(51, 51, 51)
                        .addComponent(deleteAssessmentBtn)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tfID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfSearchSupervisor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(72, 72, 72)
                                .addComponent(jLabel3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(dcDueDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfSearchSecondMarker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(9, 9, 9)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(tfSupervisor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfSupervisorName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(tfSecondMarker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfSecondMarkerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bSave)
                            .addComponent(deleteAssessmentBtn)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(76, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveActionPerformed
        // TODO add your handling code here:
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if (tfID.getText().isEmpty() || tfName.getText().isEmpty() || tfDescription.getText().isEmpty() || sdf.format(dcDueDate.getDate()).isEmpty() || tfSupervisor.getText().isEmpty() || tfSecondMarker.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter all fields.");
        } else {
            //Get the value from each text field
            String newID = tfID.getText();
            String name = tfName.getText();
            String type = cbType.getSelectedItem().toString();
            String description = tfDescription.getText();
            String dueDate = sdf.format(dcDueDate.getDate());
            String supervisor = tfSupervisor.getText();
            String secondMarker = tfSecondMarker.getText();

            try {
                List<String> lines = getAllAssessRecord();
                StringBuilder record = new StringBuilder();
                //Write the information to the text file
                record.append(newID + "\t" + name + "\t" + type + "\t" + description + "\t" + dueDate + "\t" + supervisor + "\t" + secondMarker);
                
                lines.set(count, record.toString());
                BufferedWriter writer = new BufferedWriter(new FileWriter("src\\Project_Management_System\\database\\assessment.txt"));
                for (String updatedLine : lines) {
                    writer.write(updatedLine);
                    writer.newLine();
                }
                writer.close();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

            JOptionPane.showMessageDialog(null, "Successfully Save!");
            this.dispose();
            ProjectManagerHomePage.refreshAssessTable("","");
        }
    }//GEN-LAST:event_bSaveActionPerformed

    private void tfSearchSupervisorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSearchSupervisorKeyReleased
        // TODO add your handling code here:
        String nameID = tfSearchSupervisor.getText().toLowerCase();
        refreshSupervisorListTable(nameID);
    }//GEN-LAST:event_tfSearchSupervisorKeyReleased

    private void tfSearchSecondMarkerKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSearchSecondMarkerKeyReleased
        // TODO add your handling code here:
        String nameID = tfSearchSecondMarker.getText().toLowerCase();
        refreshSecondMarkerListTable(nameID);
    }//GEN-LAST:event_tfSearchSecondMarkerKeyReleased

    private void tSupervisorListMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tSupervisorListMouseReleased
        // TODO add your handling code here:
        String selectionID = tSupervisorList.getModel().getValueAt(tSupervisorList.getSelectedRow(), 0).toString();
        String selectionName = tSupervisorList.getModel().getValueAt(tSupervisorList.getSelectedRow(), 1).toString();
        
        if (tfSecondMarker.getText().equals(selectionID))
        {
            JOptionPane.showMessageDialog(null, "Supervisor and Second Marker cannot be the same person!");
        } else {
            tfSupervisor.setText(selectionID);
            tfSupervisorName.setText(selectionName);
        }
    }//GEN-LAST:event_tSupervisorListMouseReleased

    private void tSecondMarkerListMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tSecondMarkerListMouseReleased
        // TODO add your handling code here:
        String selectionID = tSecondMarkerList.getModel().getValueAt(tSecondMarkerList.getSelectedRow(), 0).toString();
        String selectionName = tSecondMarkerList.getModel().getValueAt(tSecondMarkerList.getSelectedRow(), 1).toString();
        
        if (tfSupervisor.getText().equals(selectionID))
        {
            JOptionPane.showMessageDialog(null, "Supervisor and Second Marker cannot be the same person!");
        } else {
        tfSecondMarker.setText(selectionID);
        tfSecondMarkerName.setText(selectionName);
        }
    }//GEN-LAST:event_tSecondMarkerListMouseReleased

    private void deleteAssessmentBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteAssessmentBtnActionPerformed
        // TODO add your handling code here:
        List<String> lines = getAllAssessRecord();
        List<String> lines2 = getAllAssessStudentRecord();
        //Write the information to the text file
        lines.remove(count);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src\\Project_Management_System\\database\\assessment.txt"));
            for (String updatedLine : lines) {
                writer.write(updatedLine);
                writer.newLine();
            }

            writer.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        try {
            //Get the new stock number
            BufferedReader br = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assessment_student.txt"));
            String line;
            ArrayList<String[]> newRecord = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] record = line.split("\t");
                if (record[0].equals(tfID.getText())) {
                    continue;
                } else {
                    newRecord.add(record);
                }

            }
            br.close();

            //Write the new lines to the text file
            BufferedWriter writer = new BufferedWriter(new FileWriter("src\\Project_Management_System\\database\\assessment_student.txt"));
            for (String[] record : newRecord) {
                String lineToWrite = String.join("\t", record);
                writer.write(lineToWrite);
                writer.newLine();
            }

            writer.close();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        JOptionPane.showMessageDialog(null, "Successfully Deleted!");

        this.dispose();

        ProjectManagerHomePage.refreshAssessTable("", "");
    }//GEN-LAST:event_deleteAssessmentBtnActionPerformed

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
            java.util.logging.Logger.getLogger(EditAssessment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditAssessment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditAssessment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditAssessment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new EditAssessment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bSave;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbType;
    private com.toedter.calendar.JDateChooser dcDueDate;
    private javax.swing.JButton deleteAssessmentBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tSecondMarkerList;
    private javax.swing.JTable tSupervisorList;
    private javax.swing.JTextField tfDescription;
    private javax.swing.JTextField tfID;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfSearchSecondMarker;
    private javax.swing.JTextField tfSearchSupervisor;
    private javax.swing.JTextField tfSecondMarker;
    private javax.swing.JTextField tfSecondMarkerName;
    private javax.swing.JTextField tfSupervisor;
    private javax.swing.JTextField tfSupervisorName;
    // End of variables declaration//GEN-END:variables
}
