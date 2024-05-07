/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project_Management_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author User
 */
public class ContentBoxStudentOverallResult extends JPanel{
    private String subID;
    private ActionListener actionListener;
    
      public ContentBoxStudentOverallResult(String subID, String studentID, String grade) {
        this.subID = subID;
        setLayout(new GridLayout(3, 1)); // Three rows, one column
        setPreferredSize(new Dimension(150, 100)); // Adjust size as needed
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Create labels for assessment details with newline characters ("\n")
        JLabel idLabel = new JLabel( subID);
        JLabel nameLabel = new JLabel( subID);
        JLabel markerLabel = new JLabel("Subject: " + grade);

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
            actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, subID));
        }
    });
}


    public void addActionListener(ActionListener listener) {
        actionListener = listener;
    }
}
