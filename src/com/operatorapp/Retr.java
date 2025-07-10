package com.operatorapp;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;

public class Retr extends JFrame {
    JPanel panel;

    public Retr() {
        setTitle("Images from Database (BLOB)");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        panel = new JPanel(new GridLayout(0, 2, 10, 10));
        JScrollPane scrollPane = new JScrollPane(panel);

        add(scrollPane, BorderLayout.CENTER);
        fetchImagesFromDB();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void fetchImagesFromDB() {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT name, photo FROM images_blob";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                byte[] imgData = rs.getBytes("photo");

                if (imgData != null && imgData.length > 0) {
                    ByteArrayInputStream bis = new ByteArrayInputStream(imgData);
                    BufferedImage bImage = ImageIO.read(bis);
                    ImageIcon icon = new ImageIcon(bImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH));

                    JLabel label = new JLabel(name, SwingConstants.CENTER);
                    JLabel imgLabel = new JLabel(icon);
                    imgLabel.setBorder(BorderFactory.createTitledBorder(name));
                    panel.add(imgLabel);
                }
            }

            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
}
