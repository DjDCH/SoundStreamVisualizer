package com.djdch.dev.soundstreamvisualizer.swing;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

import com.djdch.dev.soundstreamvisualizer.controller.ApplicationController;

public class ApplicationFrame extends JFrame implements KeyEventDispatcher {

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

        JPanel gridPanel = new JPanel(new GridLayout(1, 2));
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

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this);

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

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        if (e.getID() == KeyEvent.KEY_PRESSED) {
//            System.out.println(String.format("%s %s %s", e.getKeyCode(), e.getKeyChar(), e.getKeyLocation()));

            if (e.getKeyCode() == KeyEvent.VK_A) {
                metersPanel.metadata.setBeat(true);
            } else if (e.getKeyCode() == KeyEvent.VK_S) {
                metersPanel.metadata.setBeat2(true);
            } else if (e.getKeyCode() == KeyEvent.VK_D) {
                metersPanel.metadata.setBeat3(true);
            }
        }

        return false;
    }
}
