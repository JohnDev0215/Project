package io.reactivex.rxjava3.internal.schedulers;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class p extends Scheduler.Worker {
    public final ScheduledThreadPoolExecutor c;
    public volatile boolean e;

    public p(ThreadFactory threadFactory) {
        boolean z = r.a;
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, threadFactory);
        scheduledThreadPoolExecutor.setRemoveOnCancelPolicy(r.a);
        this.c = scheduledThreadPoolExecutor;
    }

    @Override // io.reactivex.rxjava3.core.Scheduler.Worker
    public final Disposable a(Runnable runnable) {
        return b(runnable, 0L, null);
    }

    @Override // io.reactivex.rxjava3.core.Scheduler.Worker
    public final Disposable b(Runnable runnable, long j, TimeUnit timeUnit) {
        return this.e ? EmptyDisposable.c : d(runnable, j, timeUnit, null);
    }

    public final ScheduledRunnable d(Runnable runnable, long j, TimeUnit timeUnit, io.reactivex.rxjava3.disposables.b bVar) {
        Objects.requireNonNull(runnable, "run is null");
        ScheduledRunnable scheduledRunnable = new ScheduledRunnable(runnable, bVar);
        if (bVar != null && !bVar.c(scheduledRunnable)) {
            return scheduledRunnable;
        }
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = this.c;
        try {
            scheduledRunnable.a(j <= 0 ? scheduledThreadPoolExecutor.submit((Callable) scheduledRunnable) : scheduledThreadPoolExecutor.schedule((Callable) scheduledRunnable, j, timeUnit));
        } catch (RejectedExecutionException e) {
            if (bVar != null) {
                bVar.a(scheduledRunnable);
            }
            RxJavaPlugins.b(e);
        }
        return scheduledRunnable;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        if (this.e) {
            return;
        }
        this.e = true;
        this.c.shutdownNow();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.e;
    }
}
