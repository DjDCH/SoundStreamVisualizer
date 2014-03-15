package com.djdch.dev.soundstreamvisualizer.swing;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

import com.djdch.dev.soundstreamvisualizer.entity.SoundMetadata;

public class MetersPanel extends JPanel implements Observer {

    private final LevelComponent left;
    private final LevelComponent right;
    private final LevelComponent mix;

    private final BeatComponent kick;
    private final BeatComponent snare;
    private final BeatComponent hat;

    private final BeatComponent beat;

    private final MultiLevelComponent fft;

//    private final LevelComponent instantRMS;
//    private final LevelComponent smoothRMS;
//    private final LevelComponent count;
//    private final BeatComponent beat2;
//    private final BeatComponent beat3;
//    private final PitchComponent pitch;


    public SoundMetadata metadata; // FIXME: Currently public because of the keyEvent; switch back to private

    public MetersPanel() {
        metadata = new SoundMetadata();

        left = new LevelComponent("Left", 0.0f, 1.0f);
        right = new LevelComponent("Right", 0.0f, 1.0f);
        mix = new LevelComponent("Mix", 0.0f, 1.0f);

        kick = new BeatComponent("Kick");
        snare = new BeatComponent("Snare");
        hat = new BeatComponent("Hat");

        beat = new BeatComponent("Beat");

        fft = new MultiLevelComponent("FFT", 27);

//        instantRMS = new LevelComponent("Instant RMS", 0.0f, 164.0f);
//        smoothRMS = new LevelComponent("Smooth RMS", 0.0f, 164.0f);
//        beat2 = new BeatComponent("Beat");
//        beat3 = new BeatComponent("Beat");
//        pitch = new PitchComponent("Pitch", 0.0f, 164.0f);
//        count = new LevelComponent("Count", 0.0f, 60000.0f);

//        setLayout(new GridLayout(1, 8));
        setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel(new GridLayout(1, 7));

        gridPanel.add(left);
        gridPanel.add(mix);
        gridPanel.add(right);
        gridPanel.add(kick);
        gridPanel.add(snare);
        gridPanel.add(hat);
//        add(instantRMS);
//        add(smoothRMS);
        gridPanel.add(beat);
//        add(beat2);
//        add(beat3);
//        add(pitch);
//        add(count);
        add(gridPanel, BorderLayout.NORTH);
        add(fft, BorderLayout.CENTER);
    }

    public void rebuild() {
        left.setRawData(metadata.getLeft());
        right.setRawData(metadata.getRight());
        mix.setRawData(metadata.getMix());
        kick.setEnabled(metadata.isKick());
        snare.setEnabled(metadata.isSnare());
        hat.setEnabled(metadata.isHat());
//        instantRMS.setRawData(metadata.getInstantRMS());
//        smoothRMS.setRawData(metadata.getSmoothRMS());
        beat.setEnabled(metadata.isBeat());
//        beat2.setEnabled(metadata.isBeat2());
//        beat3.setEnabled(metadata.isBeat3());
//        pitch.setRawData(metadata.getSmoothRMS());
//        count.setRawData(metadata.getCount());

        for (int i = 0; i < fft.getCount(); i++) {
            fft.setRawData(i, metadata.getFFT().getAvg(i));
        }
        fft.rebuild();

        metadata.softReset();
    }

    public void reset() {
        metadata.reset();

        rebuild();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof SoundMetadata) {
            this.metadata = (SoundMetadata) arg;
        }
    }
}
