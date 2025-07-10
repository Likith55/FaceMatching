package com.operatorapp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Operator extends JFrame implements ActionListener {
    JLabel welcomeLabel, descLabel, imageLabel;
    Font font;
    JButton bIdentify, bDatabase, bEyewitness, bDraw, bView;
    JPanel buttonPanel;

    ViewWitnessDescriptions viewWindow = new ViewWitnessDescriptions();

    public Operator() {
        setTitle("🧠 Operator Control Panel");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        font = new Font("Segoe UI", Font.BOLD, 20);

        // Header
        JPanel header = new JPanel();
        header.setBackground(new Color(0, 102, 153));
        welcomeLabel = new JLabel("👋 Welcome, Operator LIKITH");
        welcomeLabel.setFont(font);
        welcomeLabel.setForeground(Color.WHITE);
        header.add(welcomeLabel);
        add(header, BorderLayout.NORTH);

        // Description label
        descLabel = new JLabel("Select an action to continue:");
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        descLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(descLabel, BorderLayout.CENTER);

        // Buttons panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        buttonPanel.setBackground(new Color(240, 248, 255));

        bIdentify = createStyledButton("🔍 Identify Image");
        bDatabase = createStyledButton("🗃️ View Database");
        bEyewitness = createStyledButton("📝 Eyewitness Description");
        bDraw = createStyledButton("🎨 Draw Suspect");
        bView = createStyledButton("📋 View All Descriptions");

        buttonPanel.add(bIdentify);
        buttonPanel.add(bDatabase);
        buttonPanel.add(bEyewitness);
        buttonPanel.add(bDraw);
        buttonPanel.add(bView);

        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(new Color(0, 64, 128));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btn.setFocusPainted(false);
        btn.addActionListener(this);
        return btn;
    }

    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == bIdentify) {
            new faceIdMain1().setVisible(true);
        } else if (src == bDatabase) {
            new Retr().setVisible(true);
        } else if (src == bEyewitness) {
            new Insert1(viewWindow).setVisible(true);
        } else if (src == bDraw) {
            new Draw().setVisible(true);
        } else if (src == bView) {
            viewWindow.refreshData();
            viewWindow.setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Operator();
    }
}
