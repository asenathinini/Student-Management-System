/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

/**
 *
 * @author asena
 */

import StudentManagementSystem.StudentLogin;
import StudentManagementSystem.StudentLogin.LoginResult;
import javax.swing.*;
import java.awt.*;

public class LoginGUI extends JFrame {
    
     

    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginGUI() {

        setTitle("Student Login");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Layout
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Student Management System", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        panel.add(new JLabel()); // empty space
        panel.add(loginButton);

        add(panel, BorderLayout.CENTER);

        // Button action
        loginButton.addActionListener(e -> login());
    }

     private void login() {

        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        LoginResult result = StudentLogin.login(email, password);

        switch (result) {

            case SUCCESS:
                JOptionPane.showMessageDialog(this, "Login Successful!");
                dispose();
                new DashboardGui(email).setVisible(true);
                break;

            case STUDENT_NOT_REGISTERED:
                JOptionPane.showMessageDialog(this,
                        "Student not registered.\nPlease contact the university.",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE);
                break;

            case WRONG_PASSWORD:
                JOptionPane.showMessageDialog(this,
                        "Incorrect password.",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE);
                break;
        }
    }
}