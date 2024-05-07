/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project_Management_System;

import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Owner
 */
public class AssignmentPanel extends JPanel{
    // Constructor accepting module code parameter
    public AssignmentPanel(String moduleCode) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Vertical layout
        displayAssignments(moduleCode);
    }

    // Method to display assignments related to the module code
    private void displayAssignments(String moduleCode) {
        try (BufferedReader br = new BufferedReader(new FileReader("src/Project_Management_System/database/assesment_assignment.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length >= 3 && parts[0].trim().equals(moduleCode)) { // Check if module code matches
                    String assignmentName = parts[2];
                    add(createAssignmentBox(assignmentName)); // Add assignment box to the panel
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading assessment_assignment file: " + e.getMessage());
        }
    }

    // Method to create an assignment box
    private JPanel createAssignmentBox(String assignmentName) {
        JPanel box = new JPanel();
        box.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        box.setPreferredSize(new Dimension(200, 50)); // Set preferred size as needed
        JLabel label = new JLabel(assignmentName);
        box.add(label);
        return box;
    }
    
}
