package com.spotify.cosmos.cosmosimpl;

/* JADX INFO: loaded from: classes.dex */
public final class NativeRunnable implements Runnable {
    private long nThis;

    private NativeRunnable() {
    }

    public final native void destroy();

    public final native void internalRun();

    @Override // java.lang.Runnable
    public void run() {
        internalRun();
        destroy();
    }
}
