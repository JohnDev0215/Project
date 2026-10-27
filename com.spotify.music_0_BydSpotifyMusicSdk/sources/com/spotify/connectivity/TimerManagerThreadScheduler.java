package com.spotify.connectivity;

import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class TimerManagerThreadScheduler implements Scheduler {
    private final com.spotify.concurrency.async.Scheduler timerManagerThread;

    public TimerManagerThreadScheduler(com.spotify.concurrency.async.Scheduler scheduler) {
        v03.j(scheduler, "timerManagerThread");
        this.timerManagerThread = scheduler;
    }

    @Override // com.spotify.connectivity.Scheduler
    public void post(Runnable runnable) {
        v03.j(runnable, "callback");
        this.timerManagerThread.post(runnable);
    }
}
