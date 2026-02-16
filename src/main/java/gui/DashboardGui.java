/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

/**
 *
 * @author asena
 */

import StudentManagementSystem.*;

import javax.swing.*;
import java.awt.*;

public class DashboardGui extends JFrame {

    private final String email;
    private JTextArea textArea;
    
    public DashboardGui(String email) {

        setTitle("Student Dashboard");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JLabel title = new JLabel("Student Dashboard", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        JTextArea detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        detailsArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        // Get student details from database
        String studentDetails = StudentDAO.getStudentDetails(email);
        detailsArea.setText(studentDetails);

        add(new JScrollPane(detailsArea), BorderLayout.CENTER);

        JButton logoutButton = new JButton("Logout");
        add(logoutButton, BorderLayout.SOUTH);

        logoutButton.addActionListener(e -> {
            dispose();
            new LoginGUI().setVisible(true);
        });
        this.email = email;
    }
    
    private void loadStudentData() {

    StudentDashboard dashboard = new StudentDashboard(email);

    if (!dashboard.studentExists()) {
        JOptionPane.showMessageDialog(this,
                "Student record not found.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        return;
    }

    textArea.setText(dashboard.getFullSummary());
}

}
