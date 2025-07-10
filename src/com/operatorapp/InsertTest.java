package com.operatorapp;

public class InsertTest {
    public static void main(String[] args) {
        // 🎯 Image names and paths
        String[] imageNames = {"Criminal Pic 1", "Criminal Pic 2", "Criminal Pic 3"};
        String[] imagePaths = {
            "C:/Users/likit/Desktop/criminals/pic1.jpg",
            "C:/Users/likit/Desktop/criminals/pic2.jpg",
            "C:/Users/likit/Desktop/criminals/pic3.jpg"
        };

        // 🔁 Insert each image
        for (int i = 0; i < imageNames.length; i++) {
            System.out.println("📥 Inserting: " + imageNames[i]);
            ImageInsertHelper.insertImage(imageNames[i], imagePaths[i]);
        }

        System.out.println("✅ All 3 images inserted successfully into the database!");
    }
}
