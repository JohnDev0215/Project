package com.neusoft.na.onecore;

/* JADX INFO: loaded from: classes2.dex */
public class OneCore {
    private long mNativeInstance = 0;

    private native long getAndroidManagerPeer(long j);

    private native long getCommunicationTaskPeer(long j);

    private native long nativeStart(String str);

    private native void nativeStop(long j);

    static {
        System.loadLibrary("na_onecore");
    }

    public synchronized boolean start(String str) {
        if (this.mNativeInstance != 0) {
            return true;
        }
        long jNativeStart = nativeStart(str);
        this.mNativeInstance = jNativeStart;
        return jNativeStart != 0;
    }

    public synchronized boolean stop() {
        long j = this.mNativeInstance;
        if (j == 0) {
            return true;
        }
        nativeStop(j);
        this.mNativeInstance = 0L;
        return true;
    }

    public synchronized boolean isRunning() {
        return this.mNativeInstance != 0;
    }

    public <T> T getFeature(Class<T> cls) {
        long communicationTaskPeer;
        if (this.mNativeInstance == 0) {
            return null;
        }
        if ("com.neusoft.na.onecore.AndroidManager".equals(cls.getName())) {
            communicationTaskPeer = getAndroidManagerPeer(this.mNativeInstance);
        } else {
            communicationTaskPeer = "com.neusoft.na.onecore.CommunicationTask".equals(cls.getName()) ? getCommunicationTaskPeer(this.mNativeInstance) : 0L;
        }
        if (communicationTaskPeer == 0) {
            return null;
        }
        try {
            return cls.getDeclaredConstructor(Long.TYPE).newInstance(Long.valueOf(communicationTaskPeer));
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Feature not supported: " + cls.getName());
        }
    }
}
