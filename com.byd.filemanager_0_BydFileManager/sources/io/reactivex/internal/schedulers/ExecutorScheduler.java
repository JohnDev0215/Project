package io.reactivex.internal.schedulers;

import d.a.j;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.queue.MpscLinkedQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class ExecutorScheduler extends j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final j f4575b = d.a.p.a.e();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final Executor f4576c;

    static final class DelayedRunnable extends AtomicReference<Runnable> implements Runnable, io.reactivex.disposables.b {
        private static final long serialVersionUID = -4101336210206799084L;
        final SequentialDisposable direct;
        final SequentialDisposable timed;

        DelayedRunnable(Runnable runnable) {
            super(runnable);
            this.timed = new SequentialDisposable();
            this.direct = new SequentialDisposable();
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            if (getAndSet(null) != null) {
                this.timed.dispose();
                this.direct.dispose();
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return get() == null;
        }

        @Override // java.lang.Runnable
        public void run() {
            Runnable runnable = get();
            if (runnable != null) {
                try {
                    runnable.run();
                    lazySet(null);
                    SequentialDisposable sequentialDisposable = this.timed;
                    DisposableHelper disposableHelper = DisposableHelper.DISPOSED;
                    sequentialDisposable.lazySet(disposableHelper);
                    this.direct.lazySet(disposableHelper);
                } catch (Throwable th) {
                    lazySet(null);
                    this.timed.lazySet(DisposableHelper.DISPOSED);
                    this.direct.lazySet(DisposableHelper.DISPOSED);
                    throw th;
                }
            }
        }
    }

    public static final class ExecutorWorker extends j.b implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Executor f4577b;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        volatile boolean f4579d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final AtomicInteger f4580e = new AtomicInteger();
        final io.reactivex.disposables.a f = new io.reactivex.disposables.a();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final MpscLinkedQueue<Runnable> f4578c = new MpscLinkedQueue<>();

        static final class BooleanRunnable extends AtomicBoolean implements Runnable, io.reactivex.disposables.b {
            private static final long serialVersionUID = -2421395018820541164L;
            final Runnable actual;

            BooleanRunnable(Runnable runnable) {
                this.actual = runnable;
            }

            @Override // io.reactivex.disposables.b
            public void dispose() {
                lazySet(true);
            }

            @Override // io.reactivex.disposables.b
            public boolean isDisposed() {
                return get();
            }

            @Override // java.lang.Runnable
            public void run() {
                if (get()) {
                    return;
                }
                try {
                    this.actual.run();
                } finally {
                    lazySet(true);
                }
            }
        }

        final class a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private final SequentialDisposable f4581b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            private final Runnable f4582c;

            a(SequentialDisposable sequentialDisposable, Runnable runnable) {
                this.f4581b = sequentialDisposable;
                this.f4582c = runnable;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.f4581b.replace(ExecutorWorker.this.b(this.f4582c));
            }
        }

        public ExecutorWorker(Executor executor) {
            this.f4577b = executor;
        }

        @Override // d.a.j.b
        public io.reactivex.disposables.b b(Runnable runnable) {
            if (this.f4579d) {
                return EmptyDisposable.INSTANCE;
            }
            BooleanRunnable booleanRunnable = new BooleanRunnable(d.a.o.a.o(runnable));
            this.f4578c.offer(booleanRunnable);
            if (this.f4580e.getAndIncrement() == 0) {
                try {
                    this.f4577b.execute(this);
                } catch (RejectedExecutionException e2) {
                    this.f4579d = true;
                    this.f4578c.clear();
                    d.a.o.a.l(e2);
                    return EmptyDisposable.INSTANCE;
                }
            }
            return booleanRunnable;
        }

        @Override // d.a.j.b
        public io.reactivex.disposables.b c(Runnable runnable, long j, TimeUnit timeUnit) {
            if (j <= 0) {
                return b(runnable);
            }
            if (this.f4579d) {
                return EmptyDisposable.INSTANCE;
            }
            SequentialDisposable sequentialDisposable = new SequentialDisposable();
            SequentialDisposable sequentialDisposable2 = new SequentialDisposable(sequentialDisposable);
            ScheduledRunnable scheduledRunnable = new ScheduledRunnable(new a(sequentialDisposable2, d.a.o.a.o(runnable)), this.f);
            this.f.c(scheduledRunnable);
            Executor executor = this.f4577b;
            if (executor instanceof ScheduledExecutorService) {
                try {
                    scheduledRunnable.setFuture(((ScheduledExecutorService) executor).schedule((Callable) scheduledRunnable, j, timeUnit));
                } catch (RejectedExecutionException e2) {
                    this.f4579d = true;
                    d.a.o.a.l(e2);
                    return EmptyDisposable.INSTANCE;
                }
            } else {
                scheduledRunnable.setFuture(new b(ExecutorScheduler.f4575b.c(scheduledRunnable, j, timeUnit)));
            }
            sequentialDisposable.replace(scheduledRunnable);
            return sequentialDisposable2;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            if (this.f4579d) {
                return;
            }
            this.f4579d = true;
            this.f.dispose();
            if (this.f4580e.getAndIncrement() == 0) {
                this.f4578c.clear();
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.f4579d;
        }

        @Override // java.lang.Runnable
        public void run() {
            MpscLinkedQueue<Runnable> mpscLinkedQueue = this.f4578c;
            int iAddAndGet = 1;
            while (!this.f4579d) {
                do {
                    Runnable runnablePoll = mpscLinkedQueue.poll();
                    if (runnablePoll != null) {
                        runnablePoll.run();
                    } else if (this.f4579d) {
                        mpscLinkedQueue.clear();
                        return;
                    } else {
                        iAddAndGet = this.f4580e.addAndGet(-iAddAndGet);
                        if (iAddAndGet == 0) {
                            return;
                        }
                    }
                } while (!this.f4579d);
                mpscLinkedQueue.clear();
                return;
            }
            mpscLinkedQueue.clear();
        }
    }

    final class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final DelayedRunnable f4584b;

        a(DelayedRunnable delayedRunnable) {
            this.f4584b = delayedRunnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            DelayedRunnable delayedRunnable = this.f4584b;
            delayedRunnable.direct.replace(ExecutorScheduler.this.b(delayedRunnable));
        }
    }

    public ExecutorScheduler(Executor executor) {
        this.f4576c = executor;
    }

    @Override // d.a.j
    public j.b a() {
        return new ExecutorWorker(this.f4576c);
    }

    @Override // d.a.j
    public io.reactivex.disposables.b b(Runnable runnable) {
        Runnable runnableO = d.a.o.a.o(runnable);
        try {
            if (this.f4576c instanceof ExecutorService) {
                ScheduledDirectTask scheduledDirectTask = new ScheduledDirectTask(runnableO);
                scheduledDirectTask.setFuture(((ExecutorService) this.f4576c).submit(scheduledDirectTask));
                return scheduledDirectTask;
            }
            ExecutorWorker.BooleanRunnable booleanRunnable = new ExecutorWorker.BooleanRunnable(runnableO);
            this.f4576c.execute(booleanRunnable);
            return booleanRunnable;
        } catch (RejectedExecutionException e2) {
            d.a.o.a.l(e2);
            return EmptyDisposable.INSTANCE;
        }
    }

    @Override // d.a.j
    public io.reactivex.disposables.b c(Runnable runnable, long j, TimeUnit timeUnit) {
        Runnable runnableO = d.a.o.a.o(runnable);
        if (!(this.f4576c instanceof ScheduledExecutorService)) {
            DelayedRunnable delayedRunnable = new DelayedRunnable(runnableO);
            delayedRunnable.timed.replace(f4575b.c(new a(delayedRunnable), j, timeUnit));
            return delayedRunnable;
        }
        try {
            ScheduledDirectTask scheduledDirectTask = new ScheduledDirectTask(runnableO);
            scheduledDirectTask.setFuture(((ScheduledExecutorService) this.f4576c).schedule(scheduledDirectTask, j, timeUnit));
            return scheduledDirectTask;
        } catch (RejectedExecutionException e2) {
            d.a.o.a.l(e2);
            return EmptyDisposable.INSTANCE;
        }
    }
}
