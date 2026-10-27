package com.spotify.concurrency.async;

/* JADX INFO: loaded from: classes.dex */
public interface Scheduler {
    boolean isCurrentThread();

    void kill(int i);

    void post(Runnable runnable);

    void runBlocking(Runnable runnable);
}
