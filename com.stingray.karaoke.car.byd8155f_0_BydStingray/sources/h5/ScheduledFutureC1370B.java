package h5;

import d5.AbstractC1095q;
import java.util.concurrent.Delayed;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: renamed from: h5.B, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class ScheduledFutureC1370B extends AbstractC1095q implements ScheduledFuture, y, Future {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final p f15533m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final ScheduledFuture f15534n;

    public ScheduledFutureC1370B(p pVar, ScheduledFuture scheduledFuture) {
        super(2);
        this.f15533m = pVar;
        this.f15534n = scheduledFuture;
    }

    @Override // h5.y
    public final void b(Runnable runnable, Executor executor) {
        this.f15533m.b(runnable, executor);
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z6) {
        boolean zY = y(z6);
        if (zY) {
            this.f15534n.cancel(z6);
        }
        return zY;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Delayed delayed) {
        return this.f15534n.compareTo(delayed);
    }

    @Override // java.util.concurrent.Future
    public final Object get() {
        return this.f15533m.get();
    }

    @Override // java.util.concurrent.Delayed
    public final long getDelay(TimeUnit timeUnit) {
        return this.f15534n.getDelay(timeUnit);
    }

    @Override // d5.AbstractC1095q
    public final Object h() {
        return this.f15533m;
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.f15533m.isCancelled();
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return this.f15533m.isDone();
    }

    public final boolean y(boolean z6) {
        return this.f15533m.cancel(z6);
    }

    @Override // java.util.concurrent.Future
    public final Object get(long j3, TimeUnit timeUnit) {
        return this.f15533m.get(j3, timeUnit);
    }
}
