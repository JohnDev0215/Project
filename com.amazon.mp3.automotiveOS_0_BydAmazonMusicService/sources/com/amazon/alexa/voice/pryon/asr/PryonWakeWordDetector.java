package com.amazon.alexa.voice.pryon.asr;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.amazon.pryon.android.asr.PryonLite;
import com.amazon.pryon.android.asr.PryonLiteCallbacks;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public class PryonWakeWordDetector implements PryonLiteCallbacks {
    private static final String TAG = "PryonWakeWordDetector";
    private final Handler handler = new Handler(Looper.getMainLooper());
    private PryonLite pryon;
    private PryonLiteCallbacks pryonLiteCallbacks;
    private PryonTransferThread transferThread;
    private final boolean useAcousticEchoCanceler;
    private final boolean useNoiseSuppressor;

    public PryonWakeWordDetector(PryonLite.Config config, boolean z, boolean z2) throws UnsupportedOperationException {
        this.useAcousticEchoCanceler = z;
        this.useNoiseSuppressor = z2;
        init(config);
    }

    private void init(PryonLite.Config config) throws UnsupportedOperationException {
        if (!PryonLite.isAvailable()) {
            this.pryon = null;
            throw new UnsupportedOperationException("Pryon ASR is not available on this platform.");
        }
        PryonLite pryonLite = new PryonLite(this);
        this.pryon = pryonLite;
        int iInitialize = pryonLite.initialize(config);
        if (iInitialize == 0) {
            return;
        }
        release();
        throw new IllegalStateException("Failed to init PryonWakeWordDetector. ErrorCode " + iInitialize);
    }

    @Override // com.amazon.pryon.android.asr.PryonLiteCallbacks
    public void wakeWordDetected(String str, long j, long j2) {
        notifyWakeWordDetected(str, j, j2);
    }

    @Override // com.amazon.pryon.android.asr.PryonLiteCallbacks
    public void vadStateChanged(int i) {
        notifyVoiceActivityDetectorCallback(i);
    }

    private void notifyWakeWordDetected(final String str, final long j, final long j2) {
        this.handler.post(new Runnable() { // from class: com.amazon.alexa.voice.pryon.asr.PryonWakeWordDetector.1
            @Override // java.lang.Runnable
            public void run() {
                if (PryonWakeWordDetector.this.pryonLiteCallbacks == null) {
                    return;
                }
                PryonWakeWordDetector.this.pryonLiteCallbacks.wakeWordDetected(str, j, j2);
            }
        });
    }

    private void notifyVoiceActivityDetectorCallback(final int i) {
        this.handler.post(new Runnable() { // from class: com.amazon.alexa.voice.pryon.asr.PryonWakeWordDetector.2
            @Override // java.lang.Runnable
            public void run() {
                if (PryonWakeWordDetector.this.pryonLiteCallbacks == null) {
                    return;
                }
                PryonWakeWordDetector.this.pryonLiteCallbacks.vadStateChanged(i);
            }
        });
    }

    private boolean isPryonAvailable() {
        return PryonLite.isAvailable() && this.pryon != null;
    }

    public void start() {
        if (!isPryonAvailable() || isActive()) {
            return;
        }
        PryonTransferThread pryonTransferThread = new PryonTransferThread(this.pryon, this.useAcousticEchoCanceler, this.useNoiseSuppressor);
        this.transferThread = pryonTransferThread;
        pryonTransferThread.start();
    }

    public void stop() {
        if (isPryonAvailable() && isActive()) {
            this.transferThread.terminate();
            try {
                try {
                    this.transferThread.join();
                } catch (InterruptedException unused) {
                    this.transferThread.interrupt();
                }
            } finally {
                this.transferThread = null;
            }
        }
    }

    public void release() {
        stop();
        PryonLite pryonLite = this.pryon;
        if (pryonLite == null) {
            return;
        }
        pryonLite.destroy();
        this.pryon = null;
    }

    public boolean isActive() {
        return this.transferThread != null;
    }

    public void setPryonLiteCallbacks(PryonLiteCallbacks pryonLiteCallbacks) {
        this.pryonLiteCallbacks = pryonLiteCallbacks;
    }

    public void removePryonLiteCallbacks() {
        this.pryonLiteCallbacks = null;
    }

    public int setSensitivityThreshold(int i) {
        PryonLite pryonLite;
        if (!PryonLite.isAvailable() || (pryonLite = this.pryon) == null) {
            return -1;
        }
        return pryonLite.setDetectionThreshold(i);
    }

    protected static class PryonTransferThread extends Thread {
        private final PryonLite pryon;
        private volatile boolean running = true;
        private final boolean useAcousticEchoCanceler;
        private final boolean useNoiseSuppressor;

        public PryonTransferThread(PryonLite pryonLite, boolean z, boolean z2) {
            this.pryon = pryonLite;
            this.useAcousticEchoCanceler = z;
            this.useNoiseSuppressor = z2;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Log.i(PryonWakeWordDetector.TAG, "Beginning transfer of audio buffer to Pryon native component.  useAEC: " + this.useAcousticEchoCanceler + " useNS: " + this.useNoiseSuppressor);
            try {
                AudioCapturer audioCapturer = new AudioCapturer(this.pryon.getSamplesPerFrame(), this.useAcousticEchoCanceler, this.useNoiseSuppressor);
                while (this.running) {
                    try {
                        try {
                            int iPushAudio = this.pryon.pushAudio(audioCapturer.read());
                            if (iPushAudio != 0) {
                                throw new IOException("Failed to push audio samples to Pryon. Error code = " + iPushAudio);
                            }
                        } catch (IOException e) {
                            Log.e(PryonWakeWordDetector.TAG, "Failed to transfer audio to Wake Word engine.", e);
                        }
                        Log.i(PryonWakeWordDetector.TAG, "Transfer of audio buffer to Pryon is ending.");
                    } finally {
                        audioCapturer.release();
                    }
                }
                Log.i(PryonWakeWordDetector.TAG, "Transfer of audio buffer to Pryon is ending.");
            } catch (AudioRecordException e2) {
                Log.e(PryonWakeWordDetector.TAG, "Error Initializing AudioCapture, wake word detection wont start", e2);
            }
        }

        public void terminate() {
            this.running = false;
        }
    }
}
