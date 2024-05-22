/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Project_Management_System;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author Owner
 */
public class LecturerHomePage extends javax.swing.JFrame {
   
    private String lecturerId;
    /**
     * Creates new form StudentHome
     */
    public LecturerHomePage(String lecturerId) {
        this.lecturerId = lecturerId;
        initComponents();
        createAssessmentPanels();
        pHome.setVisible(true);
        jPanel2.setVisible(false);
        pInsideAssessment.setVisible(false);
        
        lecturerId = getLectureIdFromLogin();
        int totalSupervisees = countSupervisees(lecturerId);
        TotalSuperviseeLabel.setText("Total Supervisees: " + totalSupervisees);
        
        int totalAssessments = countAssessments(lecturerId);
        TotalAssessmentLabel.setText("Total Assessments: " + totalAssessments);
        
        int totalPendingRequests = countPendingRequests(lecturerId);
        TotalPresentationLabel.setText("Total Pending Presentation Requests: " + totalPendingRequests);
    
        int totalUngraded = countUngraded(lecturerId);
        TotalUngradedLB.setText("Total Ungraded Reports: " + totalUngraded);
        
        displayUpcomingAcceptedPresentations();
    } 
    private String getLectureIdFromLogin() {
        return lecturerId;
    }
    
    public int countSupervisees(String lectureIdToCount) {
        Map<String, String> assessmentToLecturerMap = new HashMap<>();
        int superviseeCount = 0;

        // Read assessment.txt and create a map of assessmentId to lecturerId
        try (BufferedReader reader = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assessment.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] record = line.split("\t");
                if (record.length >= 6) {
                    String assessmentId = record[0].trim();
                    String lecturerId = record[5].trim();
                    assessmentToLecturerMap.put(assessmentId, lecturerId);
                } else {
                    System.out.println("Invalid line format in assessment.txt: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading assessment.txt: " + e.getMessage());
        }

        // Read assessment_student.txt and count the supervisees for the given lecturerId
        try (BufferedReader reader = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assessment_student.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] record = line.split("\t");
                if (record.length >= 1) {
                    String assessmentId = record[0].trim();
                    if (assessmentToLecturerMap.getOrDefault(assessmentId, "").equalsIgnoreCase(lectureIdToCount)) {
                        superviseeCount++;
                    }
                } else {
                    System.out.println("Invalid line format in assessment_student.txt: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading assessment_student.txt: " + e.getMessage());
        }

        return superviseeCount;

    }
    
    private void displayUpcomingAcceptedPresentations() {
        List<PresentationInfo> acceptedPresentations = new ArrayList<>();
        Map<String, String> studentNames = new HashMap<>();
        Map<String, String> assessmentNames = new HashMap<>();
        String assessmentStudentFilePath = "src\\Project_Management_System\\database\\assessment_student.txt";
        String studentFilePath = "src\\Project_Management_System\\database\\student.txt";
        String assessmentFilePath = "src\\Project_Management_System\\database\\assessment.txt";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        // Read student.txt and create a map of studentId to studentName
        try (BufferedReader reader = new BufferedReader(new FileReader(studentFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] record = line.split("\t");
                if (record.length >= 2) {
                    String studentId = record[0].trim();
                    String studentName = record[1].trim();
                    studentNames.put(studentId, studentName);
                } else {
                    System.out.println("Invalid line format in student.txt: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading student.txt: " + e.getMessage());
        }

        // Read assessment.txt and create a map of assessmentId to assessmentName
        try (BufferedReader reader = new BufferedReader(new FileReader(assessmentFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] record = line.split("\t");
                if (record.length >= 2) {
                    String assessmentId = record[0].trim();
                    String assessmentName = record[1].trim();
                    assessmentNames.put(assessmentId, assessmentName);
                } else {
                    System.out.println("Invalid line format in assessment.txt: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading assessment.txt: " + e.getMessage());
        }

        // Read assessment_student.txt and extract relevant records
        try (BufferedReader reader = new BufferedReader(new FileReader(assessmentStudentFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] record = line.split("\t");
                if (record.length >= 11) {
                    String assessmentId = record[0].trim();
                    String studentId = record[1].trim();
                    String dateStr = record[8].trim();
                    String timeStr = record[9].trim();
                    String status = record[10].trim();

                    if (status.equalsIgnoreCase("ACCEPTED") && !dateStr.equals("NA") && !timeStr.equals("NA")) {
                        String dateTimeStr = dateStr + " " + timeStr;
                        LocalDateTime presentationDateTime = LocalDateTime.parse(dateTimeStr, dateFormatter);
                        acceptedPresentations.add(new PresentationInfo(assessmentId, studentId, presentationDateTime, studentNames.get(studentId), assessmentNames.get(assessmentId)));
                    }
                } else {
                    System.out.println("Invalid line format in assessment_student.txt: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading assessment_student.txt: " + e.getMessage());
        }

        LocalDateTime now = LocalDateTime.now();
        acceptedPresentations.removeIf(presentation -> presentation.getPresentationDateTime().isBefore(now));

        acceptedPresentations.sort(Comparator.comparing(PresentationInfo::getPresentationDateTime));

        StringBuilder upcomingPresentations = new StringBuilder("<html>");
        for (int i = 0; i < Math.min(3, acceptedPresentations.size()); i++) {
            PresentationInfo info = acceptedPresentations.get(i);
            upcomingPresentations.append(info.getStudentId())
                                 .append(" ")
                                 .append(info.getStudentName())
                                 .append(" ")
                                 .append(info.getAssessmentId())
                                 .append(" ")
                                 .append(info.getAssessmentName())
                                 .append(" ")
                                 .append(info.getPresentationDateTime().toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                                 .append(" ")
                                 .append(info.getPresentationDateTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")))
                                 .append("<br>");
        }
        upcomingPresentations.append("</html>");

        UpPresentationLB.setText(upcomingPresentations.toString());
    }

    public int countUngraded(String lectureIdToCount) {
        int ungradedCount = 0;
        String assessmentStudentFilePath = "src\\Project_Management_System\\database\\assessment_student.txt";
        String assessmentFilePath = "src\\Project_Management_System\\database\\assessment.txt";

        Map<String, String> firstMarkerMap = new HashMap<>();
        Map<String, String> secondMarkerMap = new HashMap<>();

        // Read assessment.txt and map assessmentId to markers
        try (BufferedReader reader = new BufferedReader(new FileReader(assessmentFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] record = line.split("\t");
                if (record.length >= 7) {
                    String assessmentId = record[0].trim();
                    String firstMarker = record[5].trim();
                    String secondMarker = record[6].trim();
                    firstMarkerMap.put(assessmentId, firstMarker);
                    secondMarkerMap.put(assessmentId, secondMarker);
                } else {
                    System.out.println("Invalid line format in assessment.txt: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading assessment.txt: " + e.getMessage());
        }

        // Read assessment_student.txt and count ungraded marks
        try (BufferedReader reader = new BufferedReader(new FileReader(assessmentStudentFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] record = line.split("\t");
                if (record.length >= 8) {
                    String assessmentId = record[0].trim();
                    String firstMarkerMark = record[5].trim();
                    String secondMarkerMark = record[7].trim();

                    if (firstMarkerMap.get(assessmentId).equals(lectureIdToCount) && "NA".equalsIgnoreCase(firstMarkerMark)) {
                        ungradedCount++;
                    }

                    if (secondMarkerMap.get(assessmentId).equals(lectureIdToCount) && "NA".equalsIgnoreCase(secondMarkerMark)) {
                        ungradedCount++;
                    }
                } else {
                    System.out.println("Invalid line format in assessment_student.txt: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading assessment_student.txt: " + e.getMessage());
        }

        return ungradedCount;
    }
    
    public int countAssessments(String lectureIdToCount) {
        int assessmentCount = 0;
        String filePath = "src\\Project_Management_System\\database\\assessment.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] record = line.split("\t");
                if (record.length >= 6) {
                    String lecturerId = record[5].trim();
                    if (lecturerId.equalsIgnoreCase(lectureIdToCount)) {
                        assessmentCount++;
                    }
                } else {
                    System.out.println("Invalid line format in assessment.txt: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading assessment.txt: " + e.getMessage());
        }
        return assessmentCount;
    }
    
    public int countPendingRequests(String lectureIdToCount) {
        Map<String, String> assessmentToLecturerMap = new HashMap<>();
        int pendingRequestCount = 0;

        // Read assessment.txt and create a map of assessmentId to lecturerId
        try (BufferedReader reader = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assessment.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] record = line.split("\t");
                if (record.length >= 6) {
                    String assessmentId = record[0].trim();
                    String lecturerId = record[5].trim();
                    assessmentToLecturerMap.put(assessmentId, lecturerId);
                } else {
                    System.out.println("Invalid line format in assessment.txt: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading assessment.txt: " + e.getMessage());
        }

        // Read assessment_student.txt and count the pending requests for the given lecturerId
        try (BufferedReader reader = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assessment_student.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] record = line.split("\t");
                if (record.length >= 11) {
                    String assessmentId = record[0].trim();
                    String status = record[10].trim();
                    if (assessmentToLecturerMap.getOrDefault(assessmentId, "").equalsIgnoreCase(lectureIdToCount) && status.equalsIgnoreCase("PENDING")) {
                        pendingRequestCount++;
                    }
                } else {
                    System.out.println("Invalid line format in assessment_student.txt: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading assessment_student.txt: " + e.getMessage());
        }

        return pendingRequestCount;
    }
        
    private void createAssignmentPanels(String assessmentID) {
        String line;
        
        try {BufferedReader br = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assessment_assignment.txt")); 
            while ((line = br.readLine()) != null) {
                String[] record = line.split("\t");
                
                System.out.println(record[1]);
                if(record[1].equals(assessmentID)){
                
                javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
                javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
                
                jLabel1.setText(record[2]);
                jLabel2.setText(record[3]);
                
                jPanel1Layout.setHorizontalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(214, 214, 214)
                                .addComponent(jLabel1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(242, 242, 242)
                                .addComponent(jLabel2)))
                        .addContainerGap(268, Short.MAX_VALUE))
                );
                
                jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    // TODO add your handling code here:
                    
                    }
                });
                
                
                pAssignment.add(jPanel1);
            }
            jScrollPane2.setViewportView(pAssignment);
            }
        } catch (Exception e) {
        e.getMessage();
    }
    }
 
    private void createAssessmentPanels() {
        String line;
        try {BufferedReader br = new BufferedReader(new FileReader("src\\Project_Management_System\\database\\assessment.txt")); 
            while ((line = br.readLine()) != null) {
                String[] record = line.split("\t");
                
                javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
                javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
                
                jLabel1.setText(record[0]);
                jLabel2.setText(record[1]);
                
                jPanel1Layout.setHorizontalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(214, 214, 214)
                                .addComponent(jLabel1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(242, 242, 242)
                                .addComponent(jLabel2)))
                        .addContainerGap(268, Short.MAX_VALUE))
                );
                
                jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    // TODO add your handling code here:
                    System.out.println(record[0]);
                    createAssignmentPanels(record[0]);
                    
                    pInsideAssessment.setVisible(true);
                    jPanel2.setVisible(false);
                    ModuleLabel.setText(record[1]);
                    
                    }
                });
                
                
                pAssessment.add(jPanel1);
            }
            jScrollPane1.setViewportView(pAssessment);
        } catch (Exception e) {
        e.getMessage();
    }
    }

    class PresentationInfo {
        private String assessmentId;
        private String studentId;
        private LocalDateTime presentationDateTime;
        private String studentName;
        private String assessmentName;

        public PresentationInfo(String assessmentId, String studentId, LocalDateTime presentationDateTime, String studentName, String assessmentName) {
            this.assessmentId = assessmentId;
            this.studentId = studentId;
            this.presentationDateTime = presentationDateTime;
            this.studentName = studentName;
            this.assessmentName = assessmentName;
        }

        public String getAssessmentId() {
            return assessmentId;
        }

        public String getStudentId() {
            return studentId;
        }

        public LocalDateTime getPresentationDateTime() {
            return presentationDateTime;
        }

        public String getStudentName() {
            return studentName;
        }

        public String getAssessmentName() {
            return assessmentName;
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        sidePanel = new javax.swing.JPanel();
        presentationButton = new javax.swing.JButton();
        superviseeButton = new javax.swing.JButton();
        profileButton = new javax.swing.JButton();
        reportMarksButton1 = new javax.swing.JButton();
        lecturerSchoolLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        mainTitleLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pAssessment = new javax.swing.JPanel();
        pInsideAssessment = new javax.swing.JPanel();
        ModuleLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        pAssignment = new javax.swing.JPanel();
        pHome = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        TotalPresentationLabel2 = new javax.swing.JLabel();
        TotalUngradedLB = new javax.swing.JLabel();
        UpPresentationLB = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        TotalPresentationLabel = new javax.swing.JLabel();
        TotalSuperviseeLabel = new javax.swing.JLabel();
        TotalAssessmentLabel = new javax.swing.JLabel();

        jLabel1.setText("Code");

        jLabel2.setText("Assesment Name");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(188, 188, 188)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(26, 26, 26)))
                .addContainerGap(171, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        sidePanel.setBackground(new java.awt.Color(1, 51, 80));
        sidePanel.setPreferredSize(new java.awt.Dimension(244, 284));

        presentationButton.setBackground(new java.awt.Color(255, 255, 204));
        presentationButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        presentationButton.setText("Presentation Slot");
        presentationButton.setPreferredSize(new java.awt.Dimension(184, 40));
        presentationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                presentationButtonActionPerformed(evt);
            }
        });

        superviseeButton.setBackground(new java.awt.Color(255, 255, 204));
        superviseeButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        superviseeButton.setText("Supervisee");
        superviseeButton.setPreferredSize(new java.awt.Dimension(184, 40));
        superviseeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                superviseeButtonActionPerformed(evt);
            }
        });

        profileButton.setBackground(new java.awt.Color(255, 255, 204));
        profileButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        profileButton.setText("Profile");
        profileButton.setPreferredSize(new java.awt.Dimension(184, 40));
        profileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileButtonActionPerformed(evt);
            }
        });

        reportMarksButton1.setBackground(new java.awt.Color(255, 255, 204));
        reportMarksButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        reportMarksButton1.setText("Reports Marking");
        reportMarksButton1.setPreferredSize(new java.awt.Dimension(184, 40));
        reportMarksButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportMarksButton1ActionPerformed(evt);
            }
        });

        lecturerSchoolLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lecturerSchoolLabel.setIcon(new ImageIcon("src\\Project_Management_System\\logo\\University_Logo.png"));
        lecturerSchoolLabel.setMaximumSize(new java.awt.Dimension(100, 100));
        lecturerSchoolLabel.setPreferredSize(new java.awt.Dimension(100, 100));

        javax.swing.GroupLayout sidePanelLayout = new javax.swing.GroupLayout(sidePanel);
        sidePanel.setLayout(sidePanelLayout);
        sidePanelLayout.setHorizontalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(superviseeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(presentationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(profileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reportMarksButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(sidePanelLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(lecturerSchoolLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        sidePanelLayout.setVerticalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(lecturerSchoolLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(presentationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(superviseeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(reportMarksButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(profileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(380, 380, 380))
        );

        jPanel2.setBackground(new java.awt.Color(252, 247, 204));
        jPanel2.setPreferredSize(new java.awt.Dimension(1500, 780));

        mainTitleLabel.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        mainTitleLabel.setText("Lecturer Dashboard");
        mainTitleLabel.setPreferredSize(new java.awt.Dimension(200, 160));

        pAssessment.setLayout(new java.awt.GridLayout(0, 1));
        jScrollPane1.setViewportView(pAssessment);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(122, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(639, 639, 639))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(mainTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(144, Short.MAX_VALUE))
        );

        pInsideAssessment.setBackground(new java.awt.Color(252, 247, 204));
        pInsideAssessment.setPreferredSize(new java.awt.Dimension(1500, 780));

        ModuleLabel.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        ModuleLabel.setPreferredSize(new java.awt.Dimension(200, 160));

        pAssignment.setLayout(new java.awt.GridLayout(0, 1));
        jScrollPane2.setViewportView(pAssignment);

        javax.swing.GroupLayout pInsideAssessmentLayout = new javax.swing.GroupLayout(pInsideAssessment);
        pInsideAssessment.setLayout(pInsideAssessmentLayout);
        pInsideAssessmentLayout.setHorizontalGroup(
            pInsideAssessmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pInsideAssessmentLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(pInsideAssessmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ModuleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 1127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        pInsideAssessmentLayout.setVerticalGroup(
            pInsideAssessmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pInsideAssessmentLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(ModuleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        pHome.setBackground(new java.awt.Color(252, 247, 204));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel3.setText("Lecture Dashboard");

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton3.setText("jButton3");

        jButton4.setText("jButton4");

        jButton5.setText("jButton5");

        jButton6.setText("jButton6");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Quick Access");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setForeground(new java.awt.Color(255, 255, 255));

        TotalPresentationLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TotalPresentationLabel2.setText("Upcoming Presentation:");

        TotalUngradedLB.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TotalUngradedLB.setText("Total Ungraded Reports:");

        UpPresentationLB.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        UpPresentationLB.setText("jLabel5");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TotalPresentationLabel2)
                    .addComponent(UpPresentationLB, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                    .addComponent(TotalUngradedLB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(TotalPresentationLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(UpPresentationLB, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TotalUngradedLB)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.setForeground(new java.awt.Color(255, 255, 255));

        TotalPresentationLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TotalPresentationLabel.setText("Total Pending Presentation Requests:");

        TotalSuperviseeLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TotalSuperviseeLabel.setText("Total Supervisees:");

        TotalAssessmentLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TotalAssessmentLabel.setText("Total Assessment:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TotalSuperviseeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TotalPresentationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                    .addComponent(TotalAssessmentLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(TotalPresentationLabel)
                .addGap(44, 44, 44)
                .addComponent(TotalSuperviseeLabel)
                .addGap(48, 48, 48)
                .addComponent(TotalAssessmentLabel)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(78, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pHomeLayout = new javax.swing.GroupLayout(pHome);
        pHome.setLayout(pHomeLayout);
        pHomeLayout.setHorizontalGroup(
            pHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pHomeLayout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addGroup(pHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(714, Short.MAX_VALUE))
        );
        pHomeLayout.setVerticalGroup(
            pHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pHomeLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel3)
                .addGap(62, 62, 62)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(163, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pInsideAssessment, javax.swing.GroupLayout.PREFERRED_SIZE, 1326, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addComponent(pHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 849, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pInsideAssessment, javax.swing.GroupLayout.DEFAULT_SIZE, 849, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(sidePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sidePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 861, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void presentationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_presentationButtonActionPerformed
        // TODO add your handling code here:
       
       
    }//GEN-LAST:event_presentationButtonActionPerformed

    private void superviseeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_superviseeButtonActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_superviseeButtonActionPerformed

    private void profileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileButtonActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_profileButtonActionPerformed

    private void reportMarksButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportMarksButton1ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_reportMarksButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(LecturerHomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LecturerHomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LecturerHomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LecturerHomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                String lecturerId = "lectureId";
                new LecturerHomePage(lecturerId).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ModuleLabel;
    private javax.swing.JLabel TotalAssessmentLabel;
    private javax.swing.JLabel TotalPresentationLabel;
    private javax.swing.JLabel TotalPresentationLabel2;
    private javax.swing.JLabel TotalSuperviseeLabel;
    private javax.swing.JLabel TotalUngradedLB;
    private javax.swing.JLabel UpPresentationLB;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lecturerSchoolLabel;
    private javax.swing.JLabel mainTitleLabel;
    private javax.swing.JPanel pAssessment;
    private javax.swing.JPanel pAssignment;
    private javax.swing.JPanel pHome;
    private javax.swing.JPanel pInsideAssessment;
    private javax.swing.JButton presentationButton;
    private javax.swing.JButton profileButton;
    private javax.swing.JButton reportMarksButton1;
    private javax.swing.JPanel sidePanel;
    private javax.swing.JButton superviseeButton;
    // End of variables declaration//GEN-END:variables
}
