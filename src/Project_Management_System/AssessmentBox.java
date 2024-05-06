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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AssessmentBox extends JPanel {
    private String assessmentID;
    private ActionListener actionListener;

  public AssessmentBox(String assessmentID, String assessmentName, String firstMarker) {
        this.assessmentID = assessmentID;
        setLayout(new GridLayout(3, 1)); // Three rows, one column
        setPreferredSize(new Dimension(150, 100)); // Adjust size as needed
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Create labels for assessment details with newline characters ("\n")
        JLabel idLabel = new JLabel( assessmentID);
        JLabel nameLabel = new JLabel( assessmentName);
        JLabel markerLabel = new JLabel("Lecturer: " + firstMarker);

        // Set labels' alignment
        idLabel.setHorizontalAlignment(JLabel.CENTER);
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        markerLabel.setHorizontalAlignment(JLabel.CENTER);

        // Add labels to the panel
        add(idLabel);
        add(nameLabel);
        add(markerLabel);


    // Add action listener to handle click events
    addActionListener(e -> {
        if (actionListener != null) {
            actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, assessmentID));
        }
    });
}


    public void addActionListener(ActionListener listener) {
        actionListener = listener;
    }
}