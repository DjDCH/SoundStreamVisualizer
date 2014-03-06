package com.djdch.dev.soundstreamvisualizer.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JComponent;

public class MeterComponent extends JComponent {

    private final String name;

    private float data;

    public MeterComponent(String name) {
        this.name = name;
        data = 0.0f;

        setPreferredSize(new Dimension(150, 300));
        setFocusable(false);

        rebuild();
    }

    // TODO: If we don't need that, remove it.
    private void rebuild() {
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.BLACK);
        g.setFont(new Font("default", Font.BOLD, 15));
        g.drawString(name, 5, 20);
        g.drawString(String.format("%s", data), 5, 45);
    }

    public float getData() {
        return data;
    }

    public void setData(float data) {
        this.data = data;
        rebuild();
    }
}
