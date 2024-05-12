/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project_Management_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author User
 */
public class ContentBoxStudentOverallResult extends JPanel{
    private String subID;
    private String studentID;
    private String studentName;
    private int marks1;
    private int marks2;
    private double average;
    private String grade;
    private ActionListener actionListener;
    
      public ContentBoxStudentOverallResult(String subID, String studentID, String assignmentFilePath) {
        this.subID = subID;
        this.studentID = studentID;

        // Fetch student name based on studentID
        this.studentName = fetchStudentName(studentID);

        // Fetch assignment data based on studentID and subID
        fetchAssignmentData(subID, studentID);

        // Calculate average of marks from both markers
        this.average = (marks1 + marks2) / 2.0;

        // Calculate grade based on average marks
        this.grade = calculateGrade(average);

        setLayout(new GridLayout(4, 1)); // Four rows, one column
        setPreferredSize(new Dimension(150, 150)); // Adjust size as needed
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Create labels for assessment details with newline characters ("\n")
        JLabel idLabel = new JLabel("Subject ID: " + subID);
        JLabel nameLabel = new JLabel("Student Name: " + studentName);
        JLabel marksLabel = new JLabel("Average Marks: " + average);
        JLabel gradeLabel = new JLabel("Grade: " + grade);

        // Set labels' alignment
        idLabel.setHorizontalAlignment(JLabel.CENTER);
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        marksLabel.setHorizontalAlignment(JLabel.CENTER);
        gradeLabel.setHorizontalAlignment(JLabel.CENTER);

        // Add labels to the panel
        add(idLabel);
        add(nameLabel);
        add(marksLabel);
        add(gradeLabel);

        // Add action listener to handle click events
        addActionListener(e -> {
            if (actionListener != null) {
                actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, subID));
            }
    });
}
    private void fetchAssignmentData(String subID, String studentID) {
        String filePath = "assignment_studentSubmission.txt"; // Adjust file path as needed
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length >= 4 && parts[0].equals(studentID) && parts[1].equals(subID)) {
                    marks1 = Integer.parseInt(parts[2]);
                    marks2 = Integer.parseInt(parts[3]);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private String fetchStudentName(String studentID) {
    String studentName = "";
    String filePath = "C:\\Users\\User\\Documents\\NetBeansProjects\\G23_GA_CT038-3-2-OODJ\\src\\Project_Management_System\\database\\student.txt"; // Adjust file path as needed
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\t");
            if (parts.length > 1 && parts[0].equals(studentID)) {
                studentName = parts[1]; // Student name is in the second part
                break;
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return studentName;
}
        
    public void addActionListener(ActionListener listener) {
        actionListener = listener;
    }
}
