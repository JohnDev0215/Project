package io.reactivex.rxjava3.internal.schedulers;

import io.reactivex.rxjava3.core.Scheduler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class n extends Scheduler {
    public static final RxThreadFactory d;
    public static final RxThreadFactory e;
    public static final m h;
    public static final boolean i;
    public static final k j;
    public final AtomicReference c;
    public static final TimeUnit g = TimeUnit.SECONDS;
    public static final long f = Long.getLong("rx3.io-keep-alive-time", 60).longValue();

    static {
        m mVar = new m(new RxThreadFactory("RxCachedThreadSchedulerShutdown"));
        h = mVar;
        mVar.dispose();
        int iMax = Math.max(1, Math.min(10, Integer.getInteger("rx3.io-priority", 5).intValue()));
        RxThreadFactory rxThreadFactory = new RxThreadFactory(iMax, "RxCachedThreadScheduler", false);
        d = rxThreadFactory;
        e = new RxThreadFactory(iMax, "RxCachedWorkerPoolEvictor", false);
        i = Boolean.getBoolean("rx3.io-scheduled-release");
        k kVar = new k(0L, null, rxThreadFactory);
        j = kVar;
        kVar.k.dispose();
        ScheduledFuture scheduledFuture = kVar.m;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
        ScheduledExecutorService scheduledExecutorService = kVar.l;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdownNow();
        }
    }

    public n() {
        AtomicReference atomicReference;
        k kVar = j;
        this.c = new AtomicReference(kVar);
        k kVar2 = new k(f, g, d);
        do {
            atomicReference = this.c;
            if (atomicReference.compareAndSet(kVar, kVar2)) {
                return;
            }
        } while (atomicReference.get() == kVar);
        kVar2.k.dispose();
        ScheduledFuture scheduledFuture = kVar2.m;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
        ScheduledExecutorService scheduledExecutorService = kVar2.l;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdownNow();
        }
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    public final Scheduler.Worker b() {
        return new l((k) this.c.get());
    }
}
