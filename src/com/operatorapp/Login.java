package com.operatorapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener {
    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginBtn;

    public Login() {
        setTitle("🔐 Secure Login");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(240, 248, 255));

        JLabel title = new JLabel("🔒 Operator Login");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBounds(110, 20, 250, 30);
        add(title);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        userLabel.setBounds(50, 80, 100, 25);
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(150, 80, 180, 30);
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        passLabel.setBounds(50, 130, 100, 25);
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 130, 180, 30);
        add(passwordField);

        loginBtn = new JButton("Login");
        loginBtn.setBounds(150, 180, 100, 35);
        loginBtn.setBackground(new Color(0, 102, 204));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFocusPainted(false);
        loginBtn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        loginBtn.addActionListener(this);
        add(loginBtn);
    }

    public void actionPerformed(ActionEvent ae) {
        String user = usernameField.getText();
        String pass = new String(passwordField.getPassword());

        // You can validate with DB here
        if (user.equals("Likith") && pass.equals("1234")) {
            JOptionPane.showMessageDialog(this, "Login Successful!");
            new Operator();
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new Login().setVisible(true);
    }
}
