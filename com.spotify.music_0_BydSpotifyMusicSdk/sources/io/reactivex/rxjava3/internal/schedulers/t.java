package io.reactivex.rxjava3.internal.schedulers;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class t extends Scheduler {
    public static final RxThreadFactory d;
    public final AtomicReference c;

    static {
        Executors.newScheduledThreadPool(0).shutdown();
        d = new RxThreadFactory(Math.max(1, Math.min(10, Integer.getInteger("rx3.single-priority", 5).intValue())), "RxSingleScheduler", true);
    }

    public t() {
        AtomicReference atomicReference = new AtomicReference();
        this.c = atomicReference;
        boolean z = r.a;
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, d);
        scheduledThreadPoolExecutor.setRemoveOnCancelPolicy(r.a);
        atomicReference.lazySet(scheduledThreadPoolExecutor);
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    public final Scheduler.Worker b() {
        return new s((ScheduledExecutorService) this.c.get());
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    public final Disposable d(Runnable runnable, long j, TimeUnit timeUnit) {
        Objects.requireNonNull(runnable, "run is null");
        ScheduledDirectTask scheduledDirectTask = new ScheduledDirectTask(runnable, true);
        AtomicReference atomicReference = this.c;
        try {
            scheduledDirectTask.a(j <= 0 ? ((ScheduledExecutorService) atomicReference.get()).submit(scheduledDirectTask) : ((ScheduledExecutorService) atomicReference.get()).schedule(scheduledDirectTask, j, timeUnit));
            return scheduledDirectTask;
        } catch (RejectedExecutionException e) {
            RxJavaPlugins.b(e);
            return EmptyDisposable.c;
        }
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    public final Disposable e(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        Objects.requireNonNull(runnable, "run is null");
        EmptyDisposable emptyDisposable = EmptyDisposable.c;
        AtomicReference atomicReference = this.c;
        if (j2 > 0) {
            ScheduledDirectPeriodicTask scheduledDirectPeriodicTask = new ScheduledDirectPeriodicTask(runnable, true);
            try {
                scheduledDirectPeriodicTask.a(((ScheduledExecutorService) atomicReference.get()).scheduleAtFixedRate(scheduledDirectPeriodicTask, j, j2, timeUnit));
                return scheduledDirectPeriodicTask;
            } catch (RejectedExecutionException e) {
                RxJavaPlugins.b(e);
                return emptyDisposable;
            }
        }
        ScheduledExecutorService scheduledExecutorService = (ScheduledExecutorService) atomicReference.get();
        j jVar = new j(runnable, scheduledExecutorService);
        try {
            jVar.a(j <= 0 ? scheduledExecutorService.submit(jVar) : scheduledExecutorService.schedule(jVar, j, timeUnit));
            return jVar;
        } catch (RejectedExecutionException e2) {
            RxJavaPlugins.b(e2);
            return emptyDisposable;
        }
    }
}
