package com.operatorapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.sql.*;

public class faceIdMain1 extends JFrame {
    JLabel imageLabel, resultLabel;
    JButton uploadButton;

    public faceIdMain1() {
        setTitle("Image Identifier");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        imageLabel = new JLabel("No image selected", SwingConstants.CENTER);
        resultLabel = new JLabel("", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));

        uploadButton = new JButton("Upload Image to Identify");
        uploadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chooseAndMatchImage();
            }
        });

        add(uploadButton, BorderLayout.NORTH);
        add(imageLabel, BorderLayout.CENTER);
        add(resultLabel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void chooseAndMatchImage() {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();

            // Show image
            ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath());
            Image img = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(img));
            imageLabel.setText("");

            // Match image with DB
            boolean match = isImageInDatabase(selectedFile);
            if (match) {
                resultLabel.setText("✅ Match Found in Database!");
            } else {
                resultLabel.setText("❌ No Match Found.");
            }
        }
    }

    private boolean isImageInDatabase(File file) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT photo FROM images_blob");
            ResultSet rs = ps.executeQuery();

            // Convert file to byte array
            byte[] uploadedImageBytes = Files.readAllBytes(file.toPath());

            while (rs.next()) {
                byte[] dbImageBytes = rs.getBytes("photo");

                // Compare the two byte arrays
                if (java.util.Arrays.equals(uploadedImageBytes, dbImageBytes)) {
                    return true;
                }
            }

            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void main(String[] args) {
        new faceIdMain1();
    }
}
