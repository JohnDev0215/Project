package io.reactivex.internal.schedulers;

import e.a.j;
import io.reactivex.internal.disposables.EmptyDisposable;
import java.util.concurrent.Callable;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: NewThreadWorker.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends j.b implements io.reactivex.disposables.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ScheduledExecutorService f5056b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    volatile boolean f5057c;

    public d(ThreadFactory threadFactory) {
        this.f5056b = e.a(threadFactory);
    }

    @Override // e.a.j.b
    public io.reactivex.disposables.b b(Runnable runnable) {
        return c(runnable, 0L, null);
    }

    @Override // e.a.j.b
    public io.reactivex.disposables.b c(Runnable runnable, long j, TimeUnit timeUnit) {
        return this.f5057c ? EmptyDisposable.INSTANCE : d(runnable, j, timeUnit, null);
    }

    public ScheduledRunnable d(Runnable runnable, long j, TimeUnit timeUnit, io.reactivex.internal.disposables.a aVar) {
        ScheduledRunnable scheduledRunnable = new ScheduledRunnable(e.a.o.a.m(runnable), aVar);
        if (aVar != null && !aVar.c(scheduledRunnable)) {
            return scheduledRunnable;
        }
        try {
            scheduledRunnable.setFuture(j <= 0 ? this.f5056b.submit((Callable) scheduledRunnable) : this.f5056b.schedule((Callable) scheduledRunnable, j, timeUnit));
        } catch (RejectedExecutionException e2) {
            if (aVar != null) {
                aVar.b(scheduledRunnable);
            }
            e.a.o.a.k(e2);
        }
        return scheduledRunnable;
    }

    @Override // io.reactivex.disposables.b
    public void dispose() {
        if (this.f5057c) {
            return;
        }
        this.f5057c = true;
        this.f5056b.shutdownNow();
    }

    public io.reactivex.disposables.b e(Runnable runnable, long j, TimeUnit timeUnit) {
        ScheduledDirectTask scheduledDirectTask = new ScheduledDirectTask(e.a.o.a.m(runnable));
        try {
            scheduledDirectTask.setFuture(j <= 0 ? this.f5056b.submit(scheduledDirectTask) : this.f5056b.schedule(scheduledDirectTask, j, timeUnit));
            return scheduledDirectTask;
        } catch (RejectedExecutionException e2) {
            e.a.o.a.k(e2);
            return EmptyDisposable.INSTANCE;
        }
    }

    public void f() {
        if (this.f5057c) {
            return;
        }
        this.f5057c = true;
        this.f5056b.shutdown();
    }
}
