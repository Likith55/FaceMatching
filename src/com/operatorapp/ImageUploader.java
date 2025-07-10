package com.operatorapp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ImageUploader extends JFrame implements ActionListener {
    Label l, l3;
    JLabel l2;
    Font f;
    Button b3, b4, b5, b8, b9, b10, bUpload;  // added bUpload
    Container c1;

    public ImageUploader() {
        c1 = this.getContentPane();
        c1.setLayout(new FlowLayout());

        setBackground(Color.LIGHT_GRAY);
        f = new Font("Arial", Font.BOLD, 20);
        setFont(f);

        ImageIcon i = new ImageIcon("criminal1.jpg");  // optional image file

        l = new Label("         Welcome  Chandu!!!!!!!!        ");
        l2 = new JLabel("     Hi,                 ", i, JLabel.LEADING);
        l3 = new Label("           This is Operator                                           ", Label.CENTER);

        // Buttons
        b3 = new Button("   Identifying of Images                   ");
        b4 = new Button("   Images from Data Base      ");
        b5 = new Button("   Images from Eyewitnesses   ");
        b8 = new Button("   Drawing of Images          ");
        b9 = new Button("   Matching of Images          ");
        b10 = new Button("   Data to Administrator       ");
        bUpload = new Button("   Upload Image   ");  // New upload image button

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        l.setForeground(new Color(100, 100, 100));
        l.setFont(new Font("Arial", Font.BOLD, 30));
        l2.setForeground(new Color(100, 100, 100));
        l2.setFont(new Font("Arial", Font.BOLD, 20));
        l3.setForeground(new Color(0, 64, 64));
        l3.setFont(new Font("Arial", Font.BOLD, 20));

        Color btnColor = new Color(0, 64, 64);
        Button[] buttons = {b3, b4, b5, b8, b9, b10, bUpload}; // include upload here

        for (Button b : buttons) {
            b.setForeground(Color.WHITE);
            b.setBackground(btnColor);
            b.setFont(new Font("Arial", Font.BOLD, 15));
            b.addActionListener(this);
        }

        c1.add(l);
        c1.add(l2);
        c1.add(l3);
        for (Button b : buttons) {
            c1.add(b);
        }

        setSize(500, 600);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b5) {
            new Insert1(null).setVisible(true);
        } else if (ae.getSource() == b3) {
            new faceIdMain1().setVisible(true);
        } else if (ae.getSource() == b8) {
            new Draw().setVisible(true);
        } else if (ae.getSource() == b9) {
            new MatchingImg().setVisible(true);
        } else if (ae.getSource() == b10) {
            new DatafromOp().setVisible(true);
        } else if (ae.getSource() == b4) {
            new Retr().setVisible(true);
        } else if (ae.getSource() == bUpload) {
            new ImageUploader().setVisible(true);  // Upload image action
        }
    }

    public static void main(String[] args) {
        new ImageUploader();
    }
}
