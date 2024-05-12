/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Project_Management_System;

import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Owner
 */
public class StudentOverallResult1 extends javax.swing.JFrame {

    /**
     * Creates new form StudentHome
     */
    private Map<String, String> assessmentData = new HashMap<>();
    private Map<String, String[]> subjectToAssessmentMap = new HashMap<>();
    
    public StudentOverallResult1() {
        initComponents();
         loadData();
        displayResults();
        
    
    }
    
  private void loadData() {
    // Load data from assignment_studentSubmission.txt
    try (BufferedReader br = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assignment_studentSubmission.txt"))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\t");
            String subjectId = parts[0];
            String studentId = parts[1];
            int mark1 = Integer.parseInt(parts[3]);
            int mark2 = Integer.parseInt(parts[4]);
            double average = (mark1 + mark2) / 2.0;
            String[] assessmentInfo = subjectToAssessmentMap.get(subjectId);
            if (assessmentInfo != null) { // Check if assessmentInfo is not null
                String assessmentId = assessmentInfo[1]; // Get assessment ID
                assessmentData.put(studentId + "_" + assessmentId, Double.toString(average));
            } else {
                // Handle case where subjectId is not found in subjectToAssessmentMap
                System.err.println("Subject ID not found: " + subjectId);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    // Load subject to assessment mapping from assessment_assignment.txt
    try (BufferedReader br = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assessment_assignment.txt"))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\t");
            String subjectId = parts[0];
            String assessmentId = parts[1];
            subjectToAssessmentMap.putIfAbsent(subjectId, new String[2]);
            subjectToAssessmentMap.get(subjectId)[1] = assessmentId;
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    
    private void displayResults() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(subjectToAssessmentMap.size(), 5));

        DecimalFormat df = new DecimalFormat("#.##");

        for (Map.Entry<String, String[]> entry : subjectToAssessmentMap.entrySet()) {
            String subjectId = entry.getKey();
            String[] assessmentInfo = entry.getValue();
            String assessmentId = assessmentInfo[1];
            String assessmentName = getAssessmentName(assessmentId);
            double average = calculateAverage(subjectId, assessmentId);
            String grade = calculateGrade(average);

            JLabel labelSubjectId = new JLabel(subjectId);
            JLabel labelAssessmentId = new JLabel(assessmentId);
            JLabel labelAssessmentName = new JLabel(assessmentName);
            JLabel labelAverage = new JLabel(df.format(average));
            JLabel labelGrade = new JLabel(grade);

            panel.add(labelSubjectId);
            panel.add(labelAssessmentId);
            panel.add(labelAssessmentName);
            panel.add(labelAverage);
            panel.add(labelGrade);
        }

        ResultScrollPane.setViewportView(panel);
    }
    
    private String getAssessmentName(String assessmentId) {
        // Implement logic to retrieve assessment name from assessment_assignment.txt based on assessmentId
        return "Assessment Name"; // Placeholder
    }

    private double calculateAverage(String subjectId, String assessmentId) {
        double total = 0.0;
        int count = 0;

        for (Map.Entry<String, String> entry : assessmentData.entrySet()) {
            String key = entry.getKey();
            if (key.endsWith("_" + assessmentId)) {
                total += Double.parseDouble(entry.getValue());
                count++;
            }
        }

        return count == 0 ? 0.0 : total / count;
    }

    private String calculateGrade(double average) {
    if (average >= 80) {
        return "A+";
    } else if (average >= 75) {
        return "A";
    } else if (average >= 70) {
        return "B+";
    } else if (average >= 65) {
        return "B";
    } else if (average >= 60) {
        return "C+";
    } else if (average >= 55) {
        return "C";
    } else if (average >= 50) {
        return "C-";
    } else if (average >= 40) {
        return "D";
    } else if (average >= 30) {
        return "F+";
    } else if (average >= 20) {
        return "F";
    } else {
        return "F-";
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

        MainPanelYellow = new javax.swing.JPanel();
        SidePanelBlue = new javax.swing.JPanel();
        PresentationSlotButton = new javax.swing.JButton();
        ResultButton = new javax.swing.JButton();
        ProfileButton = new javax.swing.JButton();
        ResultLabel = new javax.swing.JLabel();
        ResultsPanel = new javax.swing.JPanel();
        ResultScrollPane = new javax.swing.JScrollPane();
        SearchBackgroundPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        SearchTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        MainPanelYellow.setBackground(new java.awt.Color(252, 247, 204));
        MainPanelYellow.setPreferredSize(new java.awt.Dimension(1500, 780));

        SidePanelBlue.setBackground(new java.awt.Color(1, 51, 80));
        SidePanelBlue.setPreferredSize(new java.awt.Dimension(244, 284));

        PresentationSlotButton.setBackground(new java.awt.Color(255, 255, 204));
        PresentationSlotButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        PresentationSlotButton.setText("Presentation Slot");
        PresentationSlotButton.setPreferredSize(new java.awt.Dimension(184, 40));
        PresentationSlotButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PresentationSlotButtonActionPerformed(evt);
            }
        });

        ResultButton.setBackground(new java.awt.Color(255, 255, 204));
        ResultButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ResultButton.setText("Result");
        ResultButton.setPreferredSize(new java.awt.Dimension(184, 40));
        ResultButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResultButtonActionPerformed(evt);
            }
        });

        ProfileButton.setBackground(new java.awt.Color(255, 255, 204));
        ProfileButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ProfileButton.setText("Profile");
        ProfileButton.setPreferredSize(new java.awt.Dimension(184, 40));
        ProfileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProfileButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SidePanelBlueLayout = new javax.swing.GroupLayout(SidePanelBlue);
        SidePanelBlue.setLayout(SidePanelBlueLayout);
        SidePanelBlueLayout.setHorizontalGroup(
            SidePanelBlueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SidePanelBlueLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(SidePanelBlueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ResultButton, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PresentationSlotButton, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ProfileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        SidePanelBlueLayout.setVerticalGroup(
            SidePanelBlueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SidePanelBlueLayout.createSequentialGroup()
                .addContainerGap(234, Short.MAX_VALUE)
                .addComponent(PresentationSlotButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(ResultButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(ProfileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(393, 393, 393))
        );

        ResultLabel.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        ResultLabel.setText("Result");
        ResultLabel.setPreferredSize(new java.awt.Dimension(200, 160));

        ResultsPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout ResultsPanelLayout = new javax.swing.GroupLayout(ResultsPanel);
        ResultsPanel.setLayout(ResultsPanelLayout);
        ResultsPanelLayout.setHorizontalGroup(
            ResultsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ResultScrollPane)
        );
        ResultsPanelLayout.setVerticalGroup(
            ResultsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ResultScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
        );

        SearchBackgroundPanel.setBackground(new java.awt.Color(1, 51, 80));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Search:");

        javax.swing.GroupLayout SearchBackgroundPanelLayout = new javax.swing.GroupLayout(SearchBackgroundPanel);
        SearchBackgroundPanel.setLayout(SearchBackgroundPanelLayout);
        SearchBackgroundPanelLayout.setHorizontalGroup(
            SearchBackgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SearchBackgroundPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(SearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(530, Short.MAX_VALUE))
        );
        SearchBackgroundPanelLayout.setVerticalGroup(
            SearchBackgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SearchBackgroundPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(SearchBackgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(SearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(544, 544, 544))
        );

        javax.swing.GroupLayout MainPanelYellowLayout = new javax.swing.GroupLayout(MainPanelYellow);
        MainPanelYellow.setLayout(MainPanelYellowLayout);
        MainPanelYellowLayout.setHorizontalGroup(
            MainPanelYellowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelYellowLayout.createSequentialGroup()
                .addComponent(SidePanelBlue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(MainPanelYellowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MainPanelYellowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(ResultsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(SearchBackgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(ResultLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(213, Short.MAX_VALUE))
        );
        MainPanelYellowLayout.setVerticalGroup(
            MainPanelYellowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelYellowLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(ResultLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SearchBackgroundPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ResultsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(SidePanelBlue, javax.swing.GroupLayout.DEFAULT_SIZE, 861, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(MainPanelYellow, javax.swing.GroupLayout.PREFERRED_SIZE, 1570, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanelYellow, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 861, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PresentationSlotButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PresentationSlotButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PresentationSlotButtonActionPerformed

    private void ResultButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResultButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ResultButtonActionPerformed

    private void ProfileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProfileButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ProfileButtonActionPerformed

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
            java.util.logging.Logger.getLogger(StudentOverallResult1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentOverallResult1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentOverallResult1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentOverallResult1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new StudentOverallResult1().setVisible(true);
            }
        });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MainPanelYellow;
    private javax.swing.JButton PresentationSlotButton;
    private javax.swing.JButton ProfileButton;
    private javax.swing.JButton ResultButton;
    private javax.swing.JLabel ResultLabel;
    private javax.swing.JScrollPane ResultScrollPane;
    private javax.swing.JPanel ResultsPanel;
    private javax.swing.JPanel SearchBackgroundPanel;
    private javax.swing.JTextField SearchTextField;
    private javax.swing.JPanel SidePanelBlue;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables

   
}

