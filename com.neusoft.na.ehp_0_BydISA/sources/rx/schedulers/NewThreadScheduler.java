package rx.schedulers;

import rx.Scheduler;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class NewThreadScheduler extends Scheduler {
    @Override // rx.Scheduler
    public Scheduler.Worker createWorker() {
        return null;
    }

    private NewThreadScheduler() {
        throw new IllegalStateException("No instances!");
    }
}
