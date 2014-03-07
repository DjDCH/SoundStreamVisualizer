package com.djdch.dev.soundstreamvisualizer.swing;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

import com.djdch.dev.soundstreamvisualizer.entity.SoundMetadata;
import com.djdch.dev.soundstreamvisualizer.runnable.SoundAnalyzer;

public class MetersPanel extends JPanel implements Observer {

    private final MeterComponent instantRMS;
    private final MeterComponent smoothRMS;
    private final MeterComponent count;

    private SoundMetadata metadata;

    public MetersPanel() {
        metadata = new SoundMetadata();

        instantRMS = new MeterComponent("Instant RMS", 0.0f, 164.0f);
        smoothRMS = new MeterComponent("Smooth RMS", 0.0f, 164.0f);
        count = new MeterComponent("Count", 0.0f, 60000.0f);

        setLayout(new GridLayout(1, 3));

        add(instantRMS);
        add(smoothRMS);
        add(count);

//        rebuild();
    }

    public void rebuild() {
        instantRMS.setRawData(metadata.getInstantRMS());
        smoothRMS.setRawData(metadata.getSmoothRMS());
        count.setRawData(metadata.getCount());

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
