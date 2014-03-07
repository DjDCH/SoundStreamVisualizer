package com.djdch.dev.soundstreamvisualizer.swing;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

import com.djdch.dev.soundstreamvisualizer.entity.SoundMetadata;
import com.djdch.dev.soundstreamvisualizer.runnable.SoundAnalyzer;

public class MetersPanel extends JPanel implements Observer {

    private final MeterComponent RMS;
    private final MeterComponent count;

    private SoundMetadata metadata;

    public MetersPanel() {
        metadata = new SoundMetadata();

        RMS = new MeterComponent("RMS");
        count = new MeterComponent("Count");

        setLayout(new GridLayout(1, 2));

        add(RMS);
        add(count);

//        rebuild();
    }

    public void rebuild() {
        RMS.setData(metadata.getRMS());
        count.setData((float) metadata.getCount());

        repaint();
    }

    public void reset() {
        metadata.reset();

        rebuild();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof SoundAnalyzer) {
            this.metadata = (SoundMetadata) arg;
        }
    }
}
