package com.spotify.cosmos.scheduling;

import com.spotify.cosmos.cosmosimpl.Scheduler;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class CoreThreadCosmosScheduler implements Scheduler {
    private final com.spotify.concurrency.async.Scheduler coreThread;

    public CoreThreadCosmosScheduler(com.spotify.concurrency.async.Scheduler scheduler) {
        v03.j(scheduler, "coreThread");
        this.coreThread = scheduler;
    }

    @Override // com.spotify.cosmos.cosmosimpl.Scheduler
    public boolean isOnSchedulerThread() {
        return this.coreThread.isCurrentThread();
    }

    @Override // com.spotify.cosmos.cosmosimpl.Scheduler
    public void post(Runnable runnable) {
        v03.j(runnable, "callback");
        this.coreThread.post(runnable);
    }
}
