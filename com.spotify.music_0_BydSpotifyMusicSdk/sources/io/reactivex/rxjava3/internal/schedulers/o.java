package io.reactivex.rxjava3.internal.schedulers;

import io.reactivex.rxjava3.core.Scheduler;
import java.util.concurrent.ThreadFactory;

/* JADX INFO: loaded from: classes.dex */
public final class o extends Scheduler {
    public static final RxThreadFactory d = new RxThreadFactory(Math.max(1, Math.min(10, Integer.getInteger("rx3.newthread-priority", 5).intValue())), "RxNewThreadScheduler", false);
    public final ThreadFactory c = d;

    @Override // io.reactivex.rxjava3.core.Scheduler
    public final Scheduler.Worker b() {
        return new p(this.c);
    }
}
