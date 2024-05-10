/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project_Management_System;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Owner
 */
public class AssignmentBox extends JPanel {
    private String assignmentName;
    private String description;
    private ActionListener actionListener;

    public AssignmentBox(String assignmentName, String description, int panelWidth) {
        this.assignmentName = assignmentName;
        this.description = description;

         // Set a fixed size for the AssignmentBox panel
         
        setLayout(new GridLayout(2, 1)); 
        setPreferredSize(new Dimension(panelWidth, 100));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        // Create labels for assignment details
        JLabel nameLabel = new JLabel(assignmentName );
        JLabel desLabel = new JLabel(description);
        
        // Set labels' alignment
        nameLabel.setHorizontalAlignment(JLabel.LEFT);
        desLabel.setHorizontalAlignment(JLabel.LEFT);

        // Set labels' color
        nameLabel.setForeground(Color.BLACK);
        desLabel.setForeground(Color.BLACK);
        
        // Add label to the panel with defined constraints
        add(nameLabel);
        add(desLabel);

        // Add mouse listener to change cursor when hovering
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Add mouse listener for click events
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (actionListener != null) {
                    actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, assignmentName));
                }
            }
        });
    }

    public String getAssignmentName() {
        return assignmentName;
    }
    
    public void addActionListener(ActionListener listener) {
        this.actionListener = listener;
    }
}
