package com.djdch.dev.soundstreamvisualizer.runnable;

import java.util.Observable;

import javax.sound.sampled.AudioFormat;

import org.apache.commons.lang3.ArrayUtils;

import com.djdch.dev.soundstreamvisualizer.controller.ApplicationController;
import com.djdch.dev.soundstreamvisualizer.entity.SoundMetadata;
import com.djdch.dev.soundstreamvisualizer.util.SSVAudioFormat;
import com.djdch.dev.soundstreamvisualizer.util.SampleQueue;
import com.djdch.dev.soundstreamvisualizer.util.SoundTools;

public class SoundAnalyzer extends Observable implements Runnable {

    private static final int SAMPLES_ARRAY_SIZE = 5000;
    private static final int SAMPLES_QUEUE_BUFFER = 16000; // Was 35000, for 44100Hz
    private static final int SAMPLES_QUEUE_EXTRA = 50; // Was 300, for 44100Hz // XXX: Ideally, we want this value as lower as possible, but the system do not keep up below 350
    private static final int SAMPLES_QUEUE_SIZE = SAMPLES_QUEUE_BUFFER + SAMPLES_QUEUE_EXTRA;

    private final ApplicationController controller;
    private final SampleQueue queue;
    private final SoundMetadata metadata;
    private final AudioFormat format;

    private boolean running;

    public SoundAnalyzer(ApplicationController controller) {
        this.controller = controller;
        this.queue = controller.getQueue();

        metadata = new SoundMetadata();

        format = SSVAudioFormat.getFormat();
    }

    @Override
    public void run() {
        final int samplesArraySize = format.getFrameSize() * SAMPLES_ARRAY_SIZE;
        byte[] samplesArray = new byte[samplesArraySize];

        final int samplesQueueBufferSize = format.getFrameSize() * SAMPLES_QUEUE_BUFFER;
        final int samplesQueueExtraSize = format.getFrameSize() * SAMPLES_QUEUE_EXTRA;
        final int samplesQueueSize = format.getFrameSize() * SAMPLES_QUEUE_SIZE;
        byte[] samplesQueue = new byte[samplesQueueSize];

        int iArray = 0;
        int iBuffer = 0;
//        int countBuffer = 0;

        setChanged();
        notifyObservers(metadata);

        running = true;

        while (running) {
            if (queue.isEmpty()) {
                try {
//                    System.out.println("waiting");
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    controller.failure(e);
                }
            } else {
                byte[] samples = queue.poolSamples();

                for (byte sample : samples) {
                    samplesArray[iArray++] = sample;
                    samplesQueue[iBuffer++] = sample;
                }
            }

            if (iArray >= samplesArraySize) {
                metadata.setInstantRMS(SoundTools.volumeRMS(samplesArray));

                samplesArray = new byte[samplesArraySize];
                iArray = 0;
            }

            if (iBuffer >= samplesQueueSize) {
                byte newSamplesArray[] = ArrayUtils.subarray(samplesQueue, samplesQueueExtraSize - 1, samplesQueueSize - 1);

                metadata.setSmoothRMS(SoundTools.volumeRMS(newSamplesArray));

                samplesQueue = ArrayUtils.addAll(newSamplesArray, new byte[samplesQueueExtraSize]);
                iBuffer = samplesQueueBufferSize;
            }

//            metadata.setCount(0);
        }
    }

    public void stop() {
        running = false;
    }
}
