package com.operatorapp;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ViewWitnessDescriptions extends JFrame {
    JTextArea area;

    public ViewWitnessDescriptions() {
        setTitle("Submitted Descriptions");
        setSize(500, 400);
        setLayout(new BorderLayout());

        area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(area);
        add(scrollPane, BorderLayout.CENTER);

        refreshData(); // Load data on startup
    }

    // 🔄 Public method to refresh the data from DB
    public void refreshData() {
        try {
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/operator_db", "root", "Likith@123");

            String sql = "SELECT name, description FROM witness_descriptions";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append("👤 Name: ").append(rs.getString("name")).append("\n");
                sb.append("📝 Description: ").append(rs.getString("description")).append("\n\n");
            }

            if (sb.length() == 0) {
                sb.append("⚠️ No descriptions submitted yet.");
            }

            area.setText(sb.toString());
            con.close();

        } catch (Exception e) {
            area.setText("Error fetching witness data: " + e.getMessage());
        }
    }
}
