package com.neusoft.na.pos;

/* JADX INFO: loaded from: classes2.dex */
public class Positioning {
    private long mNativeInstance = 0;

    private native long nativeStart(String str);

    private native void nativeStop(long j);

    static {
        System.loadLibrary("na_pos_jni");
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
}
