/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project_Management_System;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
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

    public AssignmentBox(String assignmentName, String description) {
        this.assignmentName = assignmentName;
        this.description = description;

        setLayout(new GridLayout(2, 1)); 
        setPreferredSize(new Dimension(150, 100)); // Adjust size as needed
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Create labels for assignment details
        JLabel nameLabel = new JLabel(assignmentName);
        JLabel desLabel = new JLabel(description);
        
        // Set labels' alignment
        nameLabel.setHorizontalAlignment(JLabel.LEFT);
        desLabel.setHorizontalAlignment(JLabel.LEFT);

        // Add label to the panel with defined constraints
        add(nameLabel);
        add(desLabel);

        // Add mouse listener to change cursor when hovering
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(Cursor.getDefaultCursor());
            }
        });
        
        addActionListener(e -> {
            if (actionListener != null) {
                actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, assignmentName));
            }
        });
    }

    public String getAssignmentName() {
        return assignmentName;
    }
    
    public void addActionListener(ActionListener listener) {
        actionListener = listener;
    }
}
