package com.djdch.dev.soundstreamvisualizer.runnable;

import com.djdch.dev.soundstreamvisualizer.controller.ApplicationController;
import com.djdch.dev.soundstreamvisualizer.swing.ApplicationFrame;

public class MetersRefresher implements Runnable {

    private final ApplicationController controller;
    private final ApplicationFrame frame;

    private boolean running;

    public MetersRefresher(ApplicationController controller, ApplicationFrame frame) {
        this.controller = controller;
        this.frame = frame;
    }

    @Override
    public void run() {
        running = true;

        while (running) {
            frame.getMetersPanel().rebuild();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                controller.failure(e);
            }
        }
    }

    public void stop() {
        running = false;
    }
}
