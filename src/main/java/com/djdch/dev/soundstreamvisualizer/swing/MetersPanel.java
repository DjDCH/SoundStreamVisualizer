package com.djdch.dev.soundstreamvisualizer.swing;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

import com.djdch.dev.soundstreamvisualizer.entity.SoundMetadata;
import com.djdch.dev.soundstreamvisualizer.runnable.SoundAnalyzer;

public class MetersPanel extends JPanel implements Observer {

    private final LevelComponent instantRMS;
    private final LevelComponent smoothRMS;
    private final LevelComponent count;
    private final BeatComponent beat;
    private final BeatComponent beat2;
    private final BeatComponent beat3;
    private final PitchComponent pitch;

    public SoundMetadata metadata; // FIXME: Currently public because of the keyEvent; switch back to private

    public MetersPanel() {
        metadata = new SoundMetadata();

        instantRMS = new LevelComponent("Instant RMS", 0.0f, 164.0f);
        smoothRMS = new LevelComponent("Smooth RMS", 0.0f, 164.0f);
        beat = new BeatComponent("Beat");
        beat2 = new BeatComponent("Beat");
        beat3 = new BeatComponent("Beat");
        pitch = new PitchComponent("Pitch", 0.0f, 164.0f);
        count = new LevelComponent("Count", 0.0f, 60000.0f);

        setLayout(new GridLayout(1, 6));

        add(instantRMS);
        add(smoothRMS);
        add(beat);
        add(beat2);
        add(beat3);
        add(pitch);
//        add(count);
    }

    public void rebuild() {
        instantRMS.setRawData(metadata.getInstantRMS());
        smoothRMS.setRawData(metadata.getSmoothRMS());
        beat.setEnabled(metadata.isBeat());
        beat2.setEnabled(metadata.isBeat2());
        beat3.setEnabled(metadata.isBeat3());
        pitch.setRawData(metadata.getSmoothRMS());
//        count.setRawData(metadata.getCount());

        metadata.softReset();
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
