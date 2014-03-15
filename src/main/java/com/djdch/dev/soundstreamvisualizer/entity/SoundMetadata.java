package com.djdch.dev.soundstreamvisualizer.entity;

import ddf.minim.analysis.FFT;

public class SoundMetadata {

    private volatile float left;
    private volatile float right;
    private volatile float mix;

    private volatile boolean kick;
    private volatile boolean snare;
    private volatile boolean hat;

    private volatile boolean beat;

    private FFT fft;

    private volatile float instantRMS;
    private volatile float smoothRMS;
    private volatile float count;
    private volatile boolean beat2;
    private volatile boolean beat3;

    public SoundMetadata() {
        reset();
    }

    public void reset() {
        left = 0.0f;
        right = 0.0f;
        mix = 0.0f;

        instantRMS = 0.0f;
        smoothRMS = 0.0f;
        count = 0.0f;

        fft = new FFT(1024, 44100); // FIXME: Get these values from somewhere
        fft.logAverages(60, 3);
//        fft.logAverages(11, 1); // FIXME: Get these values from somewhere

//        System.out.println(fft.avgSize());

        softReset();
    }

    public void softReset() {
        kick = false;
        snare = false;
        hat = false;

        beat = false;
        beat2 = false;
        beat3 = false;
    }

    public float getLeft() {
        return left;
    }

    public void setLeft(float left) {
        this.left = left;
    }

    public float getRight() {
        return right;
    }

    public void setRight(float right) {
        this.right = right;
    }

    public float getMix() {
        return mix;
    }

    public void setMix(float mix) {
        this.mix = mix;
    }

    public boolean isKick() {
        return kick;
    }

    public void setKick(boolean kick) {
        this.kick = kick;
    }

    public boolean isSnare() {
        return snare;
    }

    public void setSnare(boolean snare) {
        this.snare = snare;
    }

    public boolean isHat() {
        return hat;
    }

    public void setHat(boolean hat) {
        this.hat = hat;
    }

    public FFT getFFT() {
        return fft;
    }

    public void setFFT(FFT fft) {
        this.fft = fft;
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
