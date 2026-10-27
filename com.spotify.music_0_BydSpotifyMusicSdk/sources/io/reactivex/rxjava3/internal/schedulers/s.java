package io.reactivex.rxjava3.internal.schedulers;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class s extends Scheduler.Worker {
    public final ScheduledExecutorService c;
    public final io.reactivex.rxjava3.disposables.a e = new io.reactivex.rxjava3.disposables.a();
    public volatile boolean k;

    public s(ScheduledExecutorService scheduledExecutorService) {
        this.c = scheduledExecutorService;
    }

    @Override // io.reactivex.rxjava3.core.Scheduler.Worker
    public final Disposable b(Runnable runnable, long j, TimeUnit timeUnit) {
        boolean z = this.k;
        EmptyDisposable emptyDisposable = EmptyDisposable.c;
        if (z) {
            return emptyDisposable;
        }
        Objects.requireNonNull(runnable, "run is null");
        ScheduledRunnable scheduledRunnable = new ScheduledRunnable(runnable, this.e);
        this.e.c(scheduledRunnable);
        try {
            scheduledRunnable.a(j <= 0 ? this.c.submit((Callable) scheduledRunnable) : this.c.schedule((Callable) scheduledRunnable, j, timeUnit));
            return scheduledRunnable;
        } catch (RejectedExecutionException e) {
            dispose();
            RxJavaPlugins.b(e);
            return emptyDisposable;
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        if (this.k) {
            return;
        }
        this.k = true;
        this.e.dispose();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.k;
    }
}
