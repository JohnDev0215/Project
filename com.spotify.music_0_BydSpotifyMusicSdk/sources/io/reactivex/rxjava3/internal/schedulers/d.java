package io.reactivex.rxjava3.internal.schedulers;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class d extends Scheduler {
    public static final b d;
    public static final RxThreadFactory e;
    public static final int f;
    public static final c g;
    public final AtomicReference c;

    static {
        int iAvailableProcessors = Runtime.getRuntime().availableProcessors();
        int iIntValue = Integer.getInteger("rx3.computation-threads", 0).intValue();
        if (iIntValue > 0 && iIntValue <= iAvailableProcessors) {
            iAvailableProcessors = iIntValue;
        }
        f = iAvailableProcessors;
        c cVar = new c(new RxThreadFactory("RxComputationShutdown"));
        g = cVar;
        cVar.dispose();
        RxThreadFactory rxThreadFactory = new RxThreadFactory(Math.max(1, Math.min(10, Integer.getInteger("rx3.computation-priority", 5).intValue())), "RxComputationThreadPool", true);
        e = rxThreadFactory;
        b bVar = new b(0, rxThreadFactory);
        d = bVar;
        for (c cVar2 : bVar.b) {
            cVar2.dispose();
        }
    }

    public d() {
        AtomicReference atomicReference;
        b bVar = d;
        this.c = new AtomicReference(bVar);
        b bVar2 = new b(f, e);
        do {
            atomicReference = this.c;
            if (atomicReference.compareAndSet(bVar, bVar2)) {
                return;
            }
        } while (atomicReference.get() == bVar);
        for (c cVar : bVar2.b) {
            cVar.dispose();
        }
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    public final Scheduler.Worker b() {
        return new a(((b) this.c.get()).a());
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    public final Disposable d(Runnable runnable, long j, TimeUnit timeUnit) {
        c cVarA = ((b) this.c.get()).a();
        cVarA.getClass();
        Objects.requireNonNull(runnable, "run is null");
        ScheduledDirectTask scheduledDirectTask = new ScheduledDirectTask(runnable, true);
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = cVarA.c;
        try {
            scheduledDirectTask.a(j <= 0 ? scheduledThreadPoolExecutor.submit(scheduledDirectTask) : scheduledThreadPoolExecutor.schedule(scheduledDirectTask, j, timeUnit));
            return scheduledDirectTask;
        } catch (RejectedExecutionException e2) {
            RxJavaPlugins.b(e2);
            return EmptyDisposable.c;
        }
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    public final Disposable e(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        c cVarA = ((b) this.c.get()).a();
        cVarA.getClass();
        Objects.requireNonNull(runnable, "run is null");
        EmptyDisposable emptyDisposable = EmptyDisposable.c;
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = cVarA.c;
        if (j2 <= 0) {
            j jVar = new j(runnable, scheduledThreadPoolExecutor);
            try {
                jVar.a(j <= 0 ? scheduledThreadPoolExecutor.submit(jVar) : scheduledThreadPoolExecutor.schedule(jVar, j, timeUnit));
                return jVar;
            } catch (RejectedExecutionException e2) {
                RxJavaPlugins.b(e2);
                return emptyDisposable;
            }
        }
        ScheduledDirectPeriodicTask scheduledDirectPeriodicTask = new ScheduledDirectPeriodicTask(runnable, true);
        try {
            scheduledDirectPeriodicTask.a(scheduledThreadPoolExecutor.scheduleAtFixedRate(scheduledDirectPeriodicTask, j, j2, timeUnit));
            return scheduledDirectPeriodicTask;
        } catch (RejectedExecutionException e3) {
            RxJavaPlugins.b(e3);
            return emptyDisposable;
        }
    }
}
