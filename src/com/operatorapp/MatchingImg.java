package com.operatorapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class MatchingImg extends JFrame implements ActionListener {
    JLabel img1Label, img2Label, resultLabel;
    JButton selectImg1Btn, selectImg2Btn, matchBtn;

    File imgFile1 = null;
    File imgFile2 = null;

    public MatchingImg() {
        setTitle("Match Two Images");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout());
        selectImg1Btn = new JButton("Select Image 1");
        selectImg2Btn = new JButton("Select Image 2");
        matchBtn = new JButton("Match Images");

        selectImg1Btn.addActionListener(this);
        selectImg2Btn.addActionListener(this);
        matchBtn.addActionListener(this);

        topPanel.add(selectImg1Btn);
        topPanel.add(selectImg2Btn);
        topPanel.add(matchBtn);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        img1Label = new JLabel("Image 1", SwingConstants.CENTER);
        img2Label = new JLabel("Image 2", SwingConstants.CENTER);
        centerPanel.add(img1Label);
        centerPanel.add(img2Label);

        resultLabel = new JLabel("Result: ", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 18));

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(resultLabel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == selectImg1Btn) {
            imgFile1 = chooseAndDisplayImage(img1Label);
        } else if (e.getSource() == selectImg2Btn) {
            imgFile2 = chooseAndDisplayImage(img2Label);
        } else if (e.getSource() == matchBtn) {
            if (imgFile1 != null && imgFile2 != null) {
                // Fake match result (could use real image comparison later)
                resultLabel.setText("Result: Match Found (95% Similar)");
            } else {
                resultLabel.setText("Result: Please select both images first.");
            }
        }
    }

    private File chooseAndDisplayImage(JLabel label) {
        JFileChooser chooser = new JFileChooser();
        int option = chooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            ImageIcon icon = new ImageIcon(file.getAbsolutePath());
            Image img = icon.getImage().getScaledInstance(300, 250, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(img));
            label.setText(null);
            return file;
        }
        return null;
    }
}
