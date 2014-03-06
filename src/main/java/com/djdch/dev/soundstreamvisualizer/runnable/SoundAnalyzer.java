package com.djdch.dev.soundstreamvisualizer.runnable;

import java.util.Observable;

import com.djdch.dev.soundstreamvisualizer.controller.ApplicationController;
import com.djdch.dev.soundstreamvisualizer.entity.SoundMetadata;
import com.djdch.dev.soundstreamvisualizer.util.SampleQueue;
import com.djdch.dev.soundstreamvisualizer.util.SoundTools;

public class SoundAnalyzer extends Observable implements Runnable {

    private final ApplicationController controller;
    private final SampleQueue queue;
    private final SoundMetadata metadata;

    private boolean running;

    public SoundAnalyzer(ApplicationController controller) {
        this.controller = controller;
        this.queue = controller.getQueue();

        metadata = new SoundMetadata();
    }

    @Override
    public void run() {
        int count = 0;

        running = true;

        while (running) {
            if (queue.isEmpty()) {
                metadata.setCount(count);

                notifyObservers(metadata); // Only executed if something has changed

                count = 0;

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    controller.failure(e);
                }
            } else {
                byte[] samples = queue.poolSamples();

                metadata.setRMS(SoundTools.calculateRMSLevel(samples));

                count++;
                setChanged();
//                notifyObservers(metadata);
            }
        }
    }

    public void stop() {
        running = false;
    }
}
