package com.djdch.dev.soundstreamvisualizer.swing;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.djdch.dev.soundstreamvisualizer.controller.ApplicationController;

public class ApplicationFrame extends JFrame {

    public static final String APPLICATION_NAME = "SoundStreamVisualizer";

    private final ApplicationController controller;
    private final MetersPanel metersPanel;

    final JButton start = new JButton("Start");
    final JButton stop = new JButton("Stop");

    public ApplicationFrame() {
        super(APPLICATION_NAME);
//        setSize(PREFERRED_WIDTH, PREFERRED_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        controller = new ApplicationController(this);

        JPanel gridPanel = new JPanel(new GridLayout(1, 4));
        metersPanel = new MetersPanel();

        start.setEnabled(true);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start.setEnabled(false);
                stop.setEnabled(true);
                controller.start();
            }
        });
        gridPanel.add(start);

        stop.setEnabled(false);
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start.setEnabled(true);
                stop.setEnabled(false);
                controller.stop();
            }
        });
        gridPanel.add(stop);

        Container content = getContentPane();
        content.add(gridPanel, BorderLayout.NORTH);
        content.add(metersPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null); // Centered window
    }

    public void failure(String message) {
        start.setEnabled(false);
        stop.setEnabled(true);

        if (message == null) {
            message = "Something bad happened.";
        }

        JOptionPane.showMessageDialog(this, message, "Internal error", JOptionPane.ERROR_MESSAGE);
    }

    public MetersPanel getMetersPanel() {
        return metersPanel;
    }
}
