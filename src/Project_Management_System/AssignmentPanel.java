/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project_Management_System;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Owner
 */
public class AssignmentPanel extends JPanel {
    private ActionListener actionListener;

    public AssignmentPanel(String moduleCode) {
        setLayout(new GridLayout(0, 1)); // Use a vertical layout with variable rows

        // Fetch assignments for the given module code
        List<SimpleEntry<String, String>> assignments = fetchAssignments(moduleCode);

        // Create and add AssignmentBox instances to the panel
        for (SimpleEntry<String, String> assignment : assignments) {
            addAssignmentBox(assignment.getKey(), assignment.getValue());
        }
        
        // Add action listener to AssignmentBox instances
        addActionListenerToAssignmentBoxes();
    }

private void addActionListenerToAssignmentBoxes() {
        // Add action listener to AssignmentBox instances
        for (Component component : getComponents()) {
            if (component instanceof AssignmentBox) {
                ((AssignmentBox) component).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // When an assignment box is clicked, trigger the action listener
                        if (actionListener != null) {
                            actionListener.actionPerformed(e);
                        }
                    }
                });
            }
        }
    }

    private List<SimpleEntry<String, String>> fetchAssignments(String moduleCode) {
        List<SimpleEntry<String, String>> assignments = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assessment_assignment.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length >= 4 && parts[1].trim().equals(moduleCode)) {
                    assignments.add(new SimpleEntry<>(parts[2], parts[3])); // Add assignment name and description pair
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading assessment_assignment file: " + e.getMessage());
        }
        return assignments;
    }
    
    public void addActionListener(ActionListener listener) {
        actionListener = listener;
    }

    private void addAssignmentBox(String assignmentName, String description) {

        // Create an instance of AssignmentBox
        AssignmentBox assignmentBox = new AssignmentBox(assignmentName, description);
       
        
        
        // Add action listener to handle click events
        assignmentBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // When an assignment box is clicked, trigger the action listener
                if (actionListener != null) {
                    actionListener.actionPerformed(new ActionEvent(assignmentBox, ActionEvent.ACTION_PERFORMED, assignmentName));
                }
            }
        });

        // Add the AssignmentBox to the panel
        add(assignmentBox);
    }
    
    public JScrollPane createScrollPane() {
        JScrollPane scrollPane = new JScrollPane(this); // Create JScrollPane with this AssignmentPanel
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Show vertical scroll bar always
        return scrollPane;
    }
}
