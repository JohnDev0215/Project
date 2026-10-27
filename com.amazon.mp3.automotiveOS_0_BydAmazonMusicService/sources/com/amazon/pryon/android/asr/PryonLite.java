package com.amazon.pryon.android.asr;

/* JADX INFO: loaded from: classes5.dex */
public class PryonLite {
    private static boolean available;
    private PryonLiteCallbacks callbacks;

    public static class Config {
        public int detectThreshold;
        public boolean lowLatency;
        public byte[] model;
        public boolean useVad;
    }

    public native void destroy();

    public native int getSamplesPerFrame();

    public native int initialize(Config config);

    public native int pushAudio(short[] sArr);

    public native int setDetectionThreshold(int i);

    static {
        try {
            System.loadLibrary("pryon-lite-jni");
            available = true;
        } catch (UnsatisfiedLinkError unused) {
            available = false;
        }
    }

    public static boolean isAvailable() {
        return available;
    }

    public PryonLite(PryonLiteCallbacks pryonLiteCallbacks) {
        this.callbacks = pryonLiteCallbacks;
    }

    public void onWakeWordDetected(String str, long j, long j2) {
        this.callbacks.wakeWordDetected(str, j, j2);
    }

    public void onVADStateChanged(int i) {
        this.callbacks.vadStateChanged(i);
    }
}
