package com.djdch.dev.soundstreamvisualizer.entity;

public class SoundMetadata {

    private volatile float instantRMS;
    private volatile float smoothRMS;
    private volatile float count;

    public SoundMetadata() {
        reset();
    }

    public void reset() {
        instantRMS = 0.0f;
        smoothRMS = 0.0f;
        count = 0.0f;
    }

    public float getInstantRMS() {
        return instantRMS;
    }

    public void setInstantRMS(float instantRMS) {
        this.instantRMS = instantRMS;
    }

    public float getSmoothRMS() {
        return smoothRMS;
    }

    public void setSmoothRMS(float smoothRMS) {
        this.smoothRMS = smoothRMS;
    }

    public float getCount() {
        return count;
    }

    public void setCount(float count) {
        this.count = count;
    }
}
