package io.reactivex.rxjava3.internal.schedulers;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.internal.disposables.SequentialDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class i extends Scheduler {
    public final boolean c;
    public final boolean d;
    public final Executor e;

    public i(Executor executor, boolean z, boolean z2) {
        this.e = executor;
        this.c = z;
        this.d = z2;
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    public final Scheduler.Worker b() {
        return new g(this.e, this.c, this.d);
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    public final Disposable c(Runnable runnable) {
        Executor executor = this.e;
        Objects.requireNonNull(runnable, "run is null");
        try {
            boolean z = executor instanceof ExecutorService;
            boolean z2 = this.c;
            if (z) {
                ScheduledDirectTask scheduledDirectTask = new ScheduledDirectTask(runnable, z2);
                scheduledDirectTask.a(((ExecutorService) executor).submit(scheduledDirectTask));
                return scheduledDirectTask;
            }
            if (z2) {
                ExecutorScheduler$ExecutorWorker$InterruptibleRunnable executorScheduler$ExecutorWorker$InterruptibleRunnable = new ExecutorScheduler$ExecutorWorker$InterruptibleRunnable(runnable, null);
                executor.execute(executorScheduler$ExecutorWorker$InterruptibleRunnable);
                return executorScheduler$ExecutorWorker$InterruptibleRunnable;
            }
            ExecutorScheduler$ExecutorWorker$BooleanRunnable executorScheduler$ExecutorWorker$BooleanRunnable = new ExecutorScheduler$ExecutorWorker$BooleanRunnable(runnable);
            executor.execute(executorScheduler$ExecutorWorker$BooleanRunnable);
            return executorScheduler$ExecutorWorker$BooleanRunnable;
        } catch (RejectedExecutionException e) {
            RxJavaPlugins.b(e);
            return EmptyDisposable.c;
        }
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    public final Disposable d(Runnable runnable, long j, TimeUnit timeUnit) {
        Objects.requireNonNull(runnable, "run is null");
        Executor executor = this.e;
        if (executor instanceof ScheduledExecutorService) {
            try {
                ScheduledDirectTask scheduledDirectTask = new ScheduledDirectTask(runnable, this.c);
                scheduledDirectTask.a(((ScheduledExecutorService) executor).schedule(scheduledDirectTask, j, timeUnit));
                return scheduledDirectTask;
            } catch (RejectedExecutionException e) {
                RxJavaPlugins.b(e);
                return EmptyDisposable.c;
            }
        }
        ExecutorScheduler$DelayedRunnable executorScheduler$DelayedRunnable = new ExecutorScheduler$DelayedRunnable(runnable);
        Disposable disposableD = h.a.d(new f(this, 0, executorScheduler$DelayedRunnable), j, timeUnit);
        SequentialDisposable sequentialDisposable = executorScheduler$DelayedRunnable.timed;
        sequentialDisposable.getClass();
        DisposableHelper.c(sequentialDisposable, disposableD);
        return executorScheduler$DelayedRunnable;
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    public final Disposable e(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        Executor executor = this.e;
        if (!(executor instanceof ScheduledExecutorService)) {
            return super.e(runnable, j, j2, timeUnit);
        }
        Objects.requireNonNull(runnable, "run is null");
        try {
            ScheduledDirectPeriodicTask scheduledDirectPeriodicTask = new ScheduledDirectPeriodicTask(runnable, this.c);
            scheduledDirectPeriodicTask.a(((ScheduledExecutorService) executor).scheduleAtFixedRate(scheduledDirectPeriodicTask, j, j2, timeUnit));
            return scheduledDirectPeriodicTask;
        } catch (RejectedExecutionException e) {
            RxJavaPlugins.b(e);
            return EmptyDisposable.c;
        }
    }
}
