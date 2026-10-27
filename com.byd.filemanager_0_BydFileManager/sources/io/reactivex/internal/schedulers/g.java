package io.reactivex.internal.schedulers;

import d.a.j;
import io.reactivex.internal.disposables.EmptyDisposable;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: SingleScheduler.java */
/* JADX INFO: loaded from: classes.dex */
public final class g extends j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final RxThreadFactory f4619b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final ScheduledExecutorService f4620c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final ThreadFactory f4621d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final AtomicReference<ScheduledExecutorService> f4622e;

    /* JADX INFO: compiled from: SingleScheduler.java */
    static final class a extends j.b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final ScheduledExecutorService f4623b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final io.reactivex.disposables.a f4624c = new io.reactivex.disposables.a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        volatile boolean f4625d;

        a(ScheduledExecutorService scheduledExecutorService) {
            this.f4623b = scheduledExecutorService;
        }

        @Override // d.a.j.b
        public io.reactivex.disposables.b c(Runnable runnable, long j, TimeUnit timeUnit) {
            if (this.f4625d) {
                return EmptyDisposable.INSTANCE;
            }
            ScheduledRunnable scheduledRunnable = new ScheduledRunnable(d.a.o.a.o(runnable), this.f4624c);
            this.f4624c.c(scheduledRunnable);
            try {
                scheduledRunnable.setFuture(j <= 0 ? this.f4623b.submit((Callable) scheduledRunnable) : this.f4623b.schedule((Callable) scheduledRunnable, j, timeUnit));
                return scheduledRunnable;
            } catch (RejectedExecutionException e2) {
                dispose();
                d.a.o.a.l(e2);
                return EmptyDisposable.INSTANCE;
            }
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            if (this.f4625d) {
                return;
            }
            this.f4625d = true;
            this.f4624c.dispose();
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.f4625d;
        }
    }

    static {
        ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool = Executors.newScheduledThreadPool(0);
        f4620c = scheduledExecutorServiceNewScheduledThreadPool;
        scheduledExecutorServiceNewScheduledThreadPool.shutdown();
        f4619b = new RxThreadFactory("RxSingleScheduler", Math.max(1, Math.min(10, Integer.getInteger("rx2.single-priority", 5).intValue())), true);
    }

    public g() {
        this(f4619b);
    }

    static ScheduledExecutorService d(ThreadFactory threadFactory) {
        return f.a(threadFactory);
    }

    @Override // d.a.j
    public j.b a() {
        return new a(this.f4622e.get());
    }

    @Override // d.a.j
    public io.reactivex.disposables.b c(Runnable runnable, long j, TimeUnit timeUnit) {
        ScheduledDirectTask scheduledDirectTask = new ScheduledDirectTask(d.a.o.a.o(runnable));
        try {
            scheduledDirectTask.setFuture(j <= 0 ? this.f4622e.get().submit(scheduledDirectTask) : this.f4622e.get().schedule(scheduledDirectTask, j, timeUnit));
            return scheduledDirectTask;
        } catch (RejectedExecutionException e2) {
            d.a.o.a.l(e2);
            return EmptyDisposable.INSTANCE;
        }
    }

    public g(ThreadFactory threadFactory) {
        AtomicReference<ScheduledExecutorService> atomicReference = new AtomicReference<>();
        this.f4622e = atomicReference;
        this.f4621d = threadFactory;
        atomicReference.lazySet(d(threadFactory));
    }
}
