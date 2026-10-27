package h5;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: renamed from: h5.D, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class ScheduledExecutorServiceC1372D extends C1369A implements ScheduledExecutorService {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ScheduledExecutorService f15536m;

    public ScheduledExecutorServiceC1372D(ScheduledExecutorService scheduledExecutorService) {
        super(scheduledExecutorService);
        this.f15536m = scheduledExecutorService;
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final ScheduledFuture schedule(Callable callable, long j3, TimeUnit timeUnit) {
        G g7 = new G(callable);
        return new ScheduledFutureC1370B(g7, this.f15536m.schedule(g7, j3, timeUnit));
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final ScheduledFuture scheduleAtFixedRate(Runnable runnable, long j3, long j10, TimeUnit timeUnit) {
        RunnableC1371C runnableC1371C = new RunnableC1371C(runnable);
        return new ScheduledFutureC1370B(runnableC1371C, this.f15536m.scheduleAtFixedRate(runnableC1371C, j3, j10, timeUnit));
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final ScheduledFuture scheduleWithFixedDelay(Runnable runnable, long j3, long j10, TimeUnit timeUnit) {
        RunnableC1371C runnableC1371C = new RunnableC1371C(runnable);
        return new ScheduledFutureC1370B(runnableC1371C, this.f15536m.scheduleWithFixedDelay(runnableC1371C, j3, j10, timeUnit));
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final ScheduledFuture schedule(Runnable runnable, long j3, TimeUnit timeUnit) {
        G g7 = new G(Executors.callable(runnable, null));
        return new ScheduledFutureC1370B(g7, this.f15536m.schedule(g7, j3, timeUnit));
    }
}
