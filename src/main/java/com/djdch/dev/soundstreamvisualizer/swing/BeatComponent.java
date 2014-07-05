package com.djdch.dev.soundstreamvisualizer.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JComponent;

public class BeatComponent extends JComponent {

    private static final int PREFERRED_WIDTH = 120;
    private static final int PREFERRED_HEIGHT = 300;

    private String name;
    private float data;
    private float rawData;
    private float min;
    private float max;
    private boolean enabled;

    public BeatComponent(String name) {
        this(name, 0.0f, 1.0f);
    }

    public BeatComponent(String name, float min, float max) {
        this.name = name;
        this.min = min;
        this.max = max;
        data = 0.0f;
        enabled = false;

        setPreferredSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));
        setFocusable(false);

        rebuild();
    }

    private void rebuild() {
        if (data > min) {
            data -= 0.05f;
        }

        if (data < min) {
            data = min;
        }

        if (enabled) {
            data = max;
            enabled = false;
        }

        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(new Color(1.0f, 0.0f, 0.0f, data));
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.BLACK);
        g.setFont(new Font("default", Font.BOLD, 15));
        g.drawString(name, 5, 20);
        g.drawString(String.format("%s", data), 5, getHeight()-10);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getData() {
        return data;
    }

    public void setData(float data) {
        this.data = data;
        rebuild();
    }

    public float getRawData() {
        return rawData;
    }

    public void setRawData(float rawData) {
        this.rawData = rawData;
        rebuild();
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
        rebuild();
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
        rebuild();
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        rebuild();
    }
}
