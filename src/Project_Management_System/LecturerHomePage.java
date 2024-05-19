/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Project_Management_System;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Owner
 */
public class LecturerHomePage extends javax.swing.JFrame {

    /**
     * Creates new form StudentHome
     */
    public LecturerHomePage() {
        initComponents();
         createAssessmentPanels();
    }
    
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
                
                jPanel1Layout.setHorizontalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(214, 214, 214)
                                .addComponent(jLabel1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(242, 242, 242)
                                .addComponent(jLabel2)))
                        .addContainerGap(268, Short.MAX_VALUE))
                );
                
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
                
                jPanel1Layout.setHorizontalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(214, 214, 214)
                                .addComponent(jLabel1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(242, 242, 242)
                                .addComponent(jLabel2)))
                        .addContainerGap(268, Short.MAX_VALUE))
                );
                
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
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        sidePanel = new javax.swing.JPanel();
        presentationButton = new javax.swing.JButton();
        superviseeButton = new javax.swing.JButton();
        profileButton = new javax.swing.JButton();
        reportMarksButton1 = new javax.swing.JButton();
        lecturerSchoolLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        mainTitleLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pAssessment = new javax.swing.JPanel();
        pInsideAssessment = new javax.swing.JPanel();
        ModuleLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        pAssignment = new javax.swing.JPanel();

        jLabel1.setText("Code");

        jLabel2.setText("Assesment Name");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(188, 188, 188)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(26, 26, 26)))
                .addContainerGap(171, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addContainerGap(44, Short.MAX_VALUE))
        );

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

        superviseeButton.setBackground(new java.awt.Color(255, 255, 204));
        superviseeButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        superviseeButton.setText("Supervisee");
        superviseeButton.setPreferredSize(new java.awt.Dimension(184, 40));
        superviseeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                superviseeButtonActionPerformed(evt);
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

        reportMarksButton1.setBackground(new java.awt.Color(255, 255, 204));
        reportMarksButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        reportMarksButton1.setText("Reports Marking");
        reportMarksButton1.setPreferredSize(new java.awt.Dimension(184, 40));
        reportMarksButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportMarksButton1ActionPerformed(evt);
            }
        });

        lecturerSchoolLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lecturerSchoolLabel.setIcon(new ImageIcon("src\\Project_Management_System\\logo\\University_Logo.png"));
        lecturerSchoolLabel.setMaximumSize(new java.awt.Dimension(100, 100));
        lecturerSchoolLabel.setPreferredSize(new java.awt.Dimension(100, 100));

        javax.swing.GroupLayout sidePanelLayout = new javax.swing.GroupLayout(sidePanel);
        sidePanel.setLayout(sidePanelLayout);
        sidePanelLayout.setHorizontalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(superviseeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(presentationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(profileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reportMarksButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(sidePanelLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(lecturerSchoolLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        sidePanelLayout.setVerticalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(lecturerSchoolLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(presentationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(superviseeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(reportMarksButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(profileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(380, 380, 380))
        );

        jPanel2.setBackground(new java.awt.Color(252, 247, 204));
        jPanel2.setPreferredSize(new java.awt.Dimension(1500, 780));

        mainTitleLabel.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        mainTitleLabel.setText("Lecturer Dashboard");
        mainTitleLabel.setPreferredSize(new java.awt.Dimension(200, 160));

        pAssessment.setLayout(new java.awt.GridLayout(0, 1));
        jScrollPane1.setViewportView(pAssessment);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(122, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(639, 639, 639))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(mainTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGap(100, 100, 100)
                .addGroup(pInsideAssessmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ModuleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 1127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        pInsideAssessmentLayout.setVerticalGroup(
            pInsideAssessmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pInsideAssessmentLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(ModuleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(131, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pInsideAssessment, javax.swing.GroupLayout.PREFERRED_SIZE, 1326, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 849, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pInsideAssessment, javax.swing.GroupLayout.DEFAULT_SIZE, 849, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(sidePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sidePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 861, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void presentationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_presentationButtonActionPerformed
        // TODO add your handling code here:
       
       
    }//GEN-LAST:event_presentationButtonActionPerformed

    private void superviseeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_superviseeButtonActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_superviseeButtonActionPerformed

    private void profileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileButtonActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_profileButtonActionPerformed

    private void reportMarksButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportMarksButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_reportMarksButton1ActionPerformed

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
                new LecturerHomePage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ModuleLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lecturerSchoolLabel;
    private javax.swing.JLabel mainTitleLabel;
    private javax.swing.JPanel pAssessment;
    private javax.swing.JPanel pAssignment;
    private javax.swing.JPanel pInsideAssessment;
    private javax.swing.JButton presentationButton;
    private javax.swing.JButton profileButton;
    private javax.swing.JButton reportMarksButton1;
    private javax.swing.JPanel sidePanel;
    private javax.swing.JButton superviseeButton;
    // End of variables declaration//GEN-END:variables
}
