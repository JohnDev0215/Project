package io.reactivex.rxjava3.core;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.internal.disposables.SequentialDisposable;
import io.reactivex.rxjava3.internal.schedulers.p;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public abstract class Scheduler {
    public static final boolean a = Boolean.getBoolean("rx3.scheduler.use-nanotime");
    public static final long b;

    public static final class DisposeTask implements Disposable, Runnable {
        public final Runnable c;
        public final Worker e;
        public Thread k;

        public DisposeTask(Runnable runnable, Worker worker) {
            this.c = runnable;
            this.e = worker;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            if (this.k == Thread.currentThread()) {
                Worker worker = this.e;
                if (worker instanceof p) {
                    p pVar = (p) worker;
                    if (pVar.e) {
                        return;
                    }
                    pVar.e = true;
                    pVar.c.shutdown();
                    return;
                }
            }
            this.e.dispose();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.e.isDisposed();
        }

        @Override // java.lang.Runnable
        public final void run() {
            this.k = Thread.currentThread();
            try {
                this.c.run();
            } finally {
            }
        }
    }

    public static final class PeriodicDirectTask implements Disposable, Runnable {
        public final Runnable c;
        public final Worker e;
        public volatile boolean k;

        public PeriodicDirectTask(Runnable runnable, Worker worker) {
            this.c = runnable;
            this.e = worker;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.k = true;
            this.e.dispose();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.k;
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (this.k) {
                return;
            }
            try {
                this.c.run();
            } catch (Throwable th) {
                dispose();
                RxJavaPlugins.b(th);
                throw th;
            }
        }
    }

    public static abstract class Worker implements Disposable {

        public final class PeriodicTask implements Runnable {
            public final Runnable c;
            public final SequentialDisposable e;
            public final long k;
            public long l;
            public long m;
            public long n;

            public PeriodicTask(long j, Runnable runnable, long j2, SequentialDisposable sequentialDisposable, long j3) {
                this.c = runnable;
                this.e = sequentialDisposable;
                this.k = j3;
                this.m = j2;
                this.n = j;
            }

            @Override // java.lang.Runnable
            public final void run() {
                long j;
                this.c.run();
                SequentialDisposable sequentialDisposable = this.e;
                if (sequentialDisposable.isDisposed()) {
                    return;
                }
                TimeUnit timeUnit = TimeUnit.NANOSECONDS;
                Worker worker = Worker.this;
                worker.getClass();
                long jA = Scheduler.a(timeUnit);
                long j2 = Scheduler.b;
                long j3 = jA + j2;
                long j4 = this.m;
                long j5 = this.k;
                if (j3 < j4 || jA >= j4 + j5 + j2) {
                    j = jA + j5;
                    long j6 = this.l + 1;
                    this.l = j6;
                    this.n = j - (j5 * j6);
                } else {
                    long j7 = this.n;
                    long j8 = this.l + 1;
                    this.l = j8;
                    j = (j8 * j5) + j7;
                }
                this.m = jA;
                Disposable disposableB = worker.b(this, j - jA, timeUnit);
                sequentialDisposable.getClass();
                DisposableHelper.c(sequentialDisposable, disposableB);
            }
        }

        public Disposable a(Runnable runnable) {
            return b(runnable, 0L, TimeUnit.NANOSECONDS);
        }

        public abstract Disposable b(Runnable runnable, long j, TimeUnit timeUnit);

        public final Disposable c(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
            SequentialDisposable sequentialDisposable = new SequentialDisposable();
            SequentialDisposable sequentialDisposable2 = new SequentialDisposable(sequentialDisposable);
            Objects.requireNonNull(runnable, "run is null");
            long nanos = timeUnit.toNanos(j2);
            long jA = Scheduler.a(TimeUnit.NANOSECONDS);
            Disposable disposableB = b(new PeriodicTask(timeUnit.toNanos(j) + jA, runnable, jA, sequentialDisposable2, nanos), j, timeUnit);
            if (disposableB == EmptyDisposable.c) {
                return disposableB;
            }
            DisposableHelper.c(sequentialDisposable, disposableB);
            return sequentialDisposable2;
        }
    }

    static {
        long jLongValue = Long.getLong("rx3.scheduler.drift-tolerance", 15L).longValue();
        String property = System.getProperty("rx3.scheduler.drift-tolerance-unit", "minutes");
        b = "seconds".equalsIgnoreCase(property) ? TimeUnit.SECONDS.toNanos(jLongValue) : "milliseconds".equalsIgnoreCase(property) ? TimeUnit.MILLISECONDS.toNanos(jLongValue) : TimeUnit.MINUTES.toNanos(jLongValue);
    }

    public static long a(TimeUnit timeUnit) {
        return !a ? timeUnit.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS) : timeUnit.convert(System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    public abstract Worker b();

    public Disposable c(Runnable runnable) {
        return d(runnable, 0L, TimeUnit.NANOSECONDS);
    }

    public Disposable d(Runnable runnable, long j, TimeUnit timeUnit) {
        Worker workerB = b();
        Objects.requireNonNull(runnable, "run is null");
        DisposeTask disposeTask = new DisposeTask(runnable, workerB);
        workerB.b(disposeTask, j, timeUnit);
        return disposeTask;
    }

    public Disposable e(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        Worker workerB = b();
        Objects.requireNonNull(runnable, "run is null");
        PeriodicDirectTask periodicDirectTask = new PeriodicDirectTask(runnable, workerB);
        Disposable disposableC = workerB.c(periodicDirectTask, j, j2, timeUnit);
        return disposableC == EmptyDisposable.c ? disposableC : periodicDirectTask;
    }
}
