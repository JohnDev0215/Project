package com.spotify.cosmos.cosmosimpl;

/* JADX INFO: loaded from: classes.dex */
public interface Scheduler {
    boolean isOnSchedulerThread();

    void post(Runnable runnable);
}
