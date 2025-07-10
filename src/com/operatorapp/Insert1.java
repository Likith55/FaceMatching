package com.operatorapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Insert1 extends JFrame {
    JTextField nameField;
    JTextArea descArea;
    JButton submitBtn;

    // 👇 Keep reference to View window
    private ViewWitnessDescriptions viewRef;

    public Insert1(ViewWitnessDescriptions viewRef) {
        this.viewRef = viewRef;

        setTitle("Eyewitness Input");
        setSize(400, 300);
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(3, 1));
        nameField = new JTextField();
        descArea = new JTextArea();
        submitBtn = new JButton("Submit");

        form.add(new JLabel("Name:"));
        form.add(nameField);
        form.add(new JLabel("Description:"));
        form.add(new JScrollPane(descArea));
        form.add(submitBtn);

        add(form, BorderLayout.CENTER);

        submitBtn.addActionListener(e -> submit());

        setVisible(true);
    }

    private void submit() {
        String name = nameField.getText().trim();
        String desc = descArea.getText().trim();

        if (name.isEmpty() || desc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.");
            return;
        }

        try {
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/operator_db", "root", "Likith@123");

            String sql = "INSERT INTO witness_descriptions (name, description) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, desc);
            ps.executeUpdate();
            con.close();

            JOptionPane.showMessageDialog(this, "Submitted successfully!");

            // 👇 Now update the live view
            if (viewRef != null) {
                viewRef.refreshData();
            }

            nameField.setText("");
            descArea.setText("");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}
