package com.djdch.dev.soundstreamvisualizer.entity;

public class SoundMetadata {

    private volatile float RMS;
    private volatile int count;

    public SoundMetadata() {
        RMS = 0.0f;
        count = 0;
    }

    public float getRMS() {
        return RMS;
    }

    public void setRMS(float RMS) {
        this.RMS = RMS;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
