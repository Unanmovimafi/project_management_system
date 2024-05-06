/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project_Management_System;

/**
 *
 * @author User
 */
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// no use for now

public class AssessmentPanel extends JPanel {
    private ArrayList<Assessment> assessments;

    public AssessmentPanel(ArrayList<Assessment> assessments) {
        this.assessments = assessments;
        setLayout(new GridLayout(0, 1, 10, 10)); // Adjust the layout as needed

        for (Assessment assessment : assessments) {
            add(createAssessmentPanel(assessment));
        }
    }

    private JPanel createAssessmentPanel(Assessment assessment) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEtchedBorder()); // Add border for visibility

        JLabel nameLabel = new JLabel( assessment.getID());
        JLabel typeLabel = new JLabel(assessment.getName());
        JLabel datesLabel = new JLabel( assessment.getlecturer());

        panel.add(nameLabel, BorderLayout.NORTH);
        panel.add(typeLabel, BorderLayout.CENTER);
        panel.add(datesLabel, BorderLayout.SOUTH);

        return panel;
    }
}
