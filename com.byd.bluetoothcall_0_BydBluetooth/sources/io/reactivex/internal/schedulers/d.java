package io.reactivex.internal.schedulers;

import d.a.j;
import io.reactivex.internal.disposables.EmptyDisposable;
import java.util.concurrent.Callable;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: NewThreadWorker.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends j.b implements io.reactivex.disposables.b {

    /* JADX INFO: renamed from: b */
    private final ScheduledExecutorService f4327b;

    /* JADX INFO: renamed from: c */
    volatile boolean f4328c;

    public d(ThreadFactory threadFactory) {
        this.f4327b = e.a(threadFactory);
    }

    @Override // d.a.j.b
    public io.reactivex.disposables.b b(Runnable runnable) {
        return c(runnable, 0L, null);
    }

    @Override // d.a.j.b
    public io.reactivex.disposables.b c(Runnable runnable, long j, TimeUnit timeUnit) {
        return this.f4328c ? EmptyDisposable.INSTANCE : d(runnable, j, timeUnit, null);
    }

    public ScheduledRunnable d(Runnable runnable, long j, TimeUnit timeUnit, io.reactivex.internal.disposables.a aVar) {
        ScheduledRunnable scheduledRunnable = new ScheduledRunnable(d.a.p.a.n(runnable), aVar);
        if (aVar != null && !aVar.c(scheduledRunnable)) {
            return scheduledRunnable;
        }
        try {
            scheduledRunnable.setFuture(j <= 0 ? this.f4327b.submit((Callable) scheduledRunnable) : this.f4327b.schedule((Callable) scheduledRunnable, j, timeUnit));
        } catch (RejectedExecutionException e2) {
            if (aVar != null) {
                aVar.b(scheduledRunnable);
            }
            d.a.p.a.l(e2);
        }
        return scheduledRunnable;
    }

    @Override // io.reactivex.disposables.b
    public void dispose() {
        if (this.f4328c) {
            return;
        }
        this.f4328c = true;
        this.f4327b.shutdownNow();
    }

    public io.reactivex.disposables.b e(Runnable runnable, long j, TimeUnit timeUnit) {
        ScheduledDirectTask scheduledDirectTask = new ScheduledDirectTask(d.a.p.a.n(runnable));
        try {
            scheduledDirectTask.setFuture(j <= 0 ? this.f4327b.submit(scheduledDirectTask) : this.f4327b.schedule(scheduledDirectTask, j, timeUnit));
            return scheduledDirectTask;
        } catch (RejectedExecutionException e2) {
            d.a.p.a.l(e2);
            return EmptyDisposable.INSTANCE;
        }
    }

    public void f() {
        if (this.f4328c) {
            return;
        }
        this.f4328c = true;
        this.f4327b.shutdown();
    }

    @Override // io.reactivex.disposables.b
    public boolean isDisposed() {
        return this.f4328c;
    }
}
