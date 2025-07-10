package com.operatorapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Draw extends JFrame {
    private DrawCanvas canvas;
    private JButton clearBtn;

    public Draw() {
        setTitle("Draw a Suspect Sketch");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        canvas = new DrawCanvas();
        add(canvas, BorderLayout.CENTER);

        clearBtn = new JButton("Clear Drawing");
        clearBtn.addActionListener(e -> canvas.clear());

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(clearBtn);

        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Custom canvas panel
    class DrawCanvas extends JPanel {
        private Image image;
        private Graphics2D g2;
        private int prevX, prevY;

        public DrawCanvas() {
            setDoubleBuffered(false);
            setBackground(Color.WHITE);

            addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    prevX = e.getX();
                    prevY = e.getY();
                }
            });

            addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseDragged(MouseEvent e) {
                    int currX = e.getX();
                    int currY = e.getY();

                    if (g2 != null) {
                        g2.drawLine(prevX, prevY, currX, currY);
                        repaint();
                        prevX = currX;
                        prevY = currY;
                    }
                }
            });
        }

        protected void paintComponent(Graphics g) {
            if (image == null) {
                image = createImage(getSize().width, getSize().height);
                g2 = (Graphics2D) image.getGraphics();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                clear();
            }

            g.drawImage(image, 0, 0, null);
        }

        public void clear() {
            if (g2 != null) {
                g2.setPaint(Color.WHITE);
                g2.fillRect(0, 0, getSize().width, getSize().height);
                g2.setPaint(Color.BLACK);
                repaint();
            }
        }
    }
}
