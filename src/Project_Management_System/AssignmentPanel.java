/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project_Management_System;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Owner
 */
public class AssignmentPanel extends JPanel{
    public AssignmentPanel(String moduleCode) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Set layout to vertical BoxLayout

        // Fetch assignment names for the given module code
        List<String> assignmentNames = fetchAssignmentNames(moduleCode);

        // Add assignment names to the panel
        for (String assignmentName : assignmentNames) {
            addAssignment(assignmentName);
        }
    }

    private List<String> fetchAssignmentNames(String moduleCode) {
        List<String> assignmentNames = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assesment_assignment.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length >= 3 && parts[1].trim().equals(moduleCode)) {
                    assignmentNames.add(parts[2]);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading assessment_assignment file: " + e.getMessage());
        }
        return assignmentNames;
    }

    private void addAssignment(String assignmentName) {
        JLabel assignmentLabel = new JLabel(assignmentName);
        assignmentLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // Align assignment label to the left
        add(assignmentLabel); // Add assignment label to the panel
    }
    
}
