package io.reactivex.rxjava3.internal.schedulers;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.internal.disposables.SequentialDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import p.xf1;

/* JADX INFO: loaded from: classes.dex */
public final class g extends Scheduler.Worker implements Runnable {
    public final boolean c;
    public final boolean e;
    public final Executor k;
    public volatile boolean m;
    public final AtomicInteger n = new AtomicInteger();
    public final io.reactivex.rxjava3.disposables.a o = new io.reactivex.rxjava3.disposables.a();
    public final io.reactivex.rxjava3.internal.queue.a l = new io.reactivex.rxjava3.internal.queue.a();

    public g(Executor executor, boolean z, boolean z2) {
        this.k = executor;
        this.c = z;
        this.e = z2;
    }

    @Override // io.reactivex.rxjava3.core.Scheduler.Worker
    public final Disposable a(Runnable runnable) {
        Disposable executorScheduler$ExecutorWorker$BooleanRunnable;
        boolean z = this.m;
        EmptyDisposable emptyDisposable = EmptyDisposable.c;
        if (z) {
            return emptyDisposable;
        }
        Objects.requireNonNull(runnable, "run is null");
        if (this.c) {
            executorScheduler$ExecutorWorker$BooleanRunnable = new ExecutorScheduler$ExecutorWorker$InterruptibleRunnable(runnable, this.o);
            this.o.c(executorScheduler$ExecutorWorker$BooleanRunnable);
        } else {
            executorScheduler$ExecutorWorker$BooleanRunnable = new ExecutorScheduler$ExecutorWorker$BooleanRunnable(runnable);
        }
        this.l.offer(executorScheduler$ExecutorWorker$BooleanRunnable);
        if (this.n.getAndIncrement() == 0) {
            try {
                this.k.execute(this);
            } catch (RejectedExecutionException e) {
                this.m = true;
                this.l.clear();
                RxJavaPlugins.b(e);
                return emptyDisposable;
            }
        }
        return executorScheduler$ExecutorWorker$BooleanRunnable;
    }

    @Override // io.reactivex.rxjava3.core.Scheduler.Worker
    public final Disposable b(Runnable runnable, long j, TimeUnit timeUnit) {
        if (j <= 0) {
            return a(runnable);
        }
        boolean z = this.m;
        EmptyDisposable emptyDisposable = EmptyDisposable.c;
        if (z) {
            return emptyDisposable;
        }
        SequentialDisposable sequentialDisposable = new SequentialDisposable();
        SequentialDisposable sequentialDisposable2 = new SequentialDisposable(sequentialDisposable);
        Objects.requireNonNull(runnable, "run is null");
        ScheduledRunnable scheduledRunnable = new ScheduledRunnable(new xf1(this, sequentialDisposable2, runnable, 10), this.o);
        this.o.c(scheduledRunnable);
        Executor executor = this.k;
        if (executor instanceof ScheduledExecutorService) {
            try {
                scheduledRunnable.a(((ScheduledExecutorService) executor).schedule((Callable) scheduledRunnable, j, timeUnit));
            } catch (RejectedExecutionException e) {
                this.m = true;
                RxJavaPlugins.b(e);
                return emptyDisposable;
            }
        } else {
            scheduledRunnable.a(new e(h.a.d(scheduledRunnable, j, timeUnit)));
        }
        DisposableHelper.c(sequentialDisposable, scheduledRunnable);
        return sequentialDisposable2;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        if (this.m) {
            return;
        }
        this.m = true;
        this.o.dispose();
        if (this.n.getAndIncrement() == 0) {
            this.l.clear();
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.m;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.e) {
            io.reactivex.rxjava3.internal.queue.a aVar = this.l;
            if (this.m) {
                aVar.clear();
                return;
            }
            ((Runnable) aVar.poll()).run();
            if (this.m) {
                aVar.clear();
                return;
            } else {
                if (this.n.decrementAndGet() != 0) {
                    this.k.execute(this);
                    return;
                }
                return;
            }
        }
        io.reactivex.rxjava3.internal.queue.a aVar2 = this.l;
        int iAddAndGet = 1;
        while (!this.m) {
            do {
                Runnable runnable = (Runnable) aVar2.poll();
                if (runnable != null) {
                    runnable.run();
                } else if (this.m) {
                    aVar2.clear();
                    return;
                } else {
                    iAddAndGet = this.n.addAndGet(-iAddAndGet);
                    if (iAddAndGet == 0) {
                        return;
                    }
                }
            } while (!this.m);
            aVar2.clear();
            return;
        }
        aVar2.clear();
    }
}
