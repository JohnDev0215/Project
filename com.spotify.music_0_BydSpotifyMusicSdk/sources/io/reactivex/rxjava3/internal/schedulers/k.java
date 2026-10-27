package io.reactivex.rxjava3.internal.schedulers;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class k implements Runnable {
    public final long c;
    public final ConcurrentLinkedQueue e;
    public final io.reactivex.rxjava3.disposables.a k;
    public final ScheduledExecutorService l;
    public final ScheduledFuture m;
    public final ThreadFactory n;

    public k(long j, TimeUnit timeUnit, ThreadFactory threadFactory) {
        ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool;
        ScheduledFuture<?> scheduledFutureScheduleWithFixedDelay;
        long nanos = timeUnit != null ? timeUnit.toNanos(j) : 0L;
        this.c = nanos;
        this.e = new ConcurrentLinkedQueue();
        this.k = new io.reactivex.rxjava3.disposables.a();
        this.n = threadFactory;
        if (timeUnit != null) {
            scheduledExecutorServiceNewScheduledThreadPool = Executors.newScheduledThreadPool(1, n.e);
            scheduledFutureScheduleWithFixedDelay = scheduledExecutorServiceNewScheduledThreadPool.scheduleWithFixedDelay(this, nanos, nanos, TimeUnit.NANOSECONDS);
        } else {
            scheduledExecutorServiceNewScheduledThreadPool = null;
            scheduledFutureScheduleWithFixedDelay = null;
        }
        this.l = scheduledExecutorServiceNewScheduledThreadPool;
        this.m = scheduledFutureScheduleWithFixedDelay;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ConcurrentLinkedQueue<m> concurrentLinkedQueue = this.e;
        if (concurrentLinkedQueue.isEmpty()) {
            return;
        }
        long jNanoTime = System.nanoTime();
        for (m mVar : concurrentLinkedQueue) {
            if (mVar.k > jNanoTime) {
                return;
            }
            if (concurrentLinkedQueue.remove(mVar)) {
                this.k.a(mVar);
            }
        }
    }
}
