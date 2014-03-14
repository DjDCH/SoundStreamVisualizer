package com.djdch.dev.soundstreamvisualizer.entity;

public class SoundMetadata {

    private volatile float instantRMS;
    private volatile float smoothRMS;
    private volatile float count;
    private volatile boolean beat;
    private volatile boolean beat2;
    private volatile boolean beat3;

    public SoundMetadata() {
        reset();
    }

    public void reset() {
        instantRMS = 0.0f;
        smoothRMS = 0.0f;
        count = 0.0f;

        softReset();
    }

    public void softReset() {
        beat = false;
        beat2 = false;
        beat3 = false;
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

    public boolean isBeat() {
        return beat;
    }

    public void setBeat(boolean beat) {
        this.beat = beat;
    }

    public boolean isBeat2() {
        return beat2;
    }

    public void setBeat2(boolean beat2) {
        this.beat2 = beat2;
    }

    public boolean isBeat3() {
        return beat3;
    }

    public void setBeat3(boolean beat3) {
        this.beat3 = beat3;
    }
}
