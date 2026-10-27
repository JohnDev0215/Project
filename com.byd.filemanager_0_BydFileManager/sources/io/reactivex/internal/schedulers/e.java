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
public class e extends j.b implements io.reactivex.disposables.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ScheduledExecutorService f4613b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    volatile boolean f4614c;

    public e(ThreadFactory threadFactory) {
        this.f4613b = f.a(threadFactory);
    }

    @Override // d.a.j.b
    public io.reactivex.disposables.b b(Runnable runnable) {
        return c(runnable, 0L, null);
    }

    @Override // d.a.j.b
    public io.reactivex.disposables.b c(Runnable runnable, long j, TimeUnit timeUnit) {
        return this.f4614c ? EmptyDisposable.INSTANCE : d(runnable, j, timeUnit, null);
    }

    public ScheduledRunnable d(Runnable runnable, long j, TimeUnit timeUnit, io.reactivex.internal.disposables.a aVar) {
        ScheduledRunnable scheduledRunnable = new ScheduledRunnable(d.a.o.a.o(runnable), aVar);
        if (aVar != null && !aVar.c(scheduledRunnable)) {
            return scheduledRunnable;
        }
        try {
            scheduledRunnable.setFuture(j <= 0 ? this.f4613b.submit((Callable) scheduledRunnable) : this.f4613b.schedule((Callable) scheduledRunnable, j, timeUnit));
        } catch (RejectedExecutionException e2) {
            if (aVar != null) {
                aVar.b(scheduledRunnable);
            }
            d.a.o.a.l(e2);
        }
        return scheduledRunnable;
    }

    @Override // io.reactivex.disposables.b
    public void dispose() {
        if (this.f4614c) {
            return;
        }
        this.f4614c = true;
        this.f4613b.shutdownNow();
    }

    public io.reactivex.disposables.b e(Runnable runnable, long j, TimeUnit timeUnit) {
        ScheduledDirectTask scheduledDirectTask = new ScheduledDirectTask(d.a.o.a.o(runnable));
        try {
            scheduledDirectTask.setFuture(j <= 0 ? this.f4613b.submit(scheduledDirectTask) : this.f4613b.schedule(scheduledDirectTask, j, timeUnit));
            return scheduledDirectTask;
        } catch (RejectedExecutionException e2) {
            d.a.o.a.l(e2);
            return EmptyDisposable.INSTANCE;
        }
    }

    public void f() {
        if (this.f4614c) {
            return;
        }
        this.f4614c = true;
        this.f4613b.shutdown();
    }

    @Override // io.reactivex.disposables.b
    public boolean isDisposed() {
        return this.f4614c;
    }
}
