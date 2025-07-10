package com.operatorapp;

import java.io.*;
import java.sql.*;

public class ImageInsertHelper {
    public static void insertImage(String name, String filePath) {
        try {
            Connection con = DBConnection.getConnection(); // DBConnection already working

            String sql = "INSERT INTO images_blob (name, photo) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name); // e.g., "Criminal 1"

            File imgFile = new File(filePath); // File object
            FileInputStream fis = new FileInputStream(imgFile); // read image as stream
            ps.setBinaryStream(2, fis, (int) imgFile.length()); // insert as BLOB

            int status = ps.executeUpdate();
            if (status > 0) {
                System.out.println("✅ Image inserted into DB successfully.");
            }

            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
