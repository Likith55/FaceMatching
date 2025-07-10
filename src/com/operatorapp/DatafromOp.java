package com.operatorapp;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class DatafromOp extends JFrame {
    JTable table;

    public DatafromOp() {
        setTitle("Data from Operator");
        setSize(600, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columnNames = {"ID", "Username", "Password"};
        Object[][] data = fetchData();

        if (data != null && data.length > 0) {
            table = new JTable(data, columnNames);
            JScrollPane scrollPane = new JScrollPane(table);
            add(scrollPane, BorderLayout.CENTER);
        } else {
            add(new JLabel("No user data found."), BorderLayout.CENTER);
        }
    }

    private Object[][] fetchData() {
        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM users");

            rs.last();
            int rowCount = rs.getRow();
            rs.beforeFirst();

            Object[][] data = new Object[rowCount][3];
            int i = 0;
            while (rs.next()) {
                data[i][0] = rs.getInt("id");
                data[i][1] = rs.getString("username");
                data[i][2] = rs.getString("password");
                i++;
            }

            return data;
        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
            return new Object[0][0];
        }
    }
}
