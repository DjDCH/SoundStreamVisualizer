package com.djdch.dev.soundstreamvisualizer.controller;

import com.djdch.dev.soundstreamvisualizer.runnable.MetersRefresher;
import com.djdch.dev.soundstreamvisualizer.runnable.SoundAnalyzer;
import com.djdch.dev.soundstreamvisualizer.runnable.SoundListener;
import com.djdch.dev.soundstreamvisualizer.swing.ApplicationFrame;
import com.djdch.dev.soundstreamvisualizer.util.SampleQueue;

public class ApplicationController {

    private final ApplicationFrame frame;
    private final SampleQueue queue;

    private SoundListener listener;
    private SoundAnalyzer analyzer;
    private MetersRefresher refresher;

    public ApplicationController(ApplicationFrame frame) {
        this.frame = frame;

        queue = new SampleQueue();
    }

    public void start() {
        listener = new SoundListener(this);
        Thread listenerThread = new Thread(listener);
        listenerThread.start();

        analyzer = new SoundAnalyzer(this);
        analyzer.addObserver(frame.getMetersPanel());
        Thread analyzerThread = new Thread(analyzer);
        analyzerThread.start();

        refresher = new MetersRefresher(this, this.frame);
        Thread refresherThread = new Thread(refresher);
        refresherThread.start();

//        Executors.newSingleThreadExecutor().submit(listener);
    }

    public void stop() {
        listener.stop();
        analyzer.stop();
        refresher.stop();

        analyzer.deleteObserver(frame.getMetersPanel());

        frame.getMetersPanel().reset();
    }

    public void failure(Exception e) {
        frame.failure(e.getMessage());
        stop();
        e.printStackTrace();
    }

    public SampleQueue getQueue() {
        return queue;
    }
}
