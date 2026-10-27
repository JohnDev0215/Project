package com.spotify.connectivity;

/* JADX INFO: loaded from: classes.dex */
public interface Scheduler {

    public static final class NativeRunnable implements Runnable {
        private long nThis;

        private NativeRunnable() {
        }

        @Override // java.lang.Runnable
        public native void run();
    }

    void post(Runnable runnable);
}
