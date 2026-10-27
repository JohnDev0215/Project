package io.reactivex.internal.schedulers;

import e.a.j;
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
public final class f extends j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final RxThreadFactory f5062b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final ScheduledExecutorService f5063c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final ThreadFactory f5064d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final AtomicReference<ScheduledExecutorService> f5065e;

    /* JADX INFO: compiled from: SingleScheduler.java */
    static final class a extends j.b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final ScheduledExecutorService f5066b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final io.reactivex.disposables.a f5067c = new io.reactivex.disposables.a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        volatile boolean f5068d;

        a(ScheduledExecutorService scheduledExecutorService) {
            this.f5066b = scheduledExecutorService;
        }

        @Override // e.a.j.b
        public io.reactivex.disposables.b c(Runnable runnable, long j, TimeUnit timeUnit) {
            if (this.f5068d) {
                return EmptyDisposable.INSTANCE;
            }
            ScheduledRunnable scheduledRunnable = new ScheduledRunnable(e.a.o.a.m(runnable), this.f5067c);
            this.f5067c.c(scheduledRunnable);
            try {
                scheduledRunnable.setFuture(j <= 0 ? this.f5066b.submit((Callable) scheduledRunnable) : this.f5066b.schedule((Callable) scheduledRunnable, j, timeUnit));
                return scheduledRunnable;
            } catch (RejectedExecutionException e2) {
                dispose();
                e.a.o.a.k(e2);
                return EmptyDisposable.INSTANCE;
            }
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            if (this.f5068d) {
                return;
            }
            this.f5068d = true;
            this.f5067c.dispose();
        }
    }

    static {
        ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool = Executors.newScheduledThreadPool(0);
        f5063c = scheduledExecutorServiceNewScheduledThreadPool;
        scheduledExecutorServiceNewScheduledThreadPool.shutdown();
        f5062b = new RxThreadFactory("RxSingleScheduler", Math.max(1, Math.min(10, Integer.getInteger("rx2.single-priority", 5).intValue())), true);
    }

    public f() {
        this(f5062b);
    }

    static ScheduledExecutorService d(ThreadFactory threadFactory) {
        return e.a(threadFactory);
    }

    @Override // e.a.j
    public j.b a() {
        return new a(this.f5065e.get());
    }

    @Override // e.a.j
    public io.reactivex.disposables.b c(Runnable runnable, long j, TimeUnit timeUnit) {
        ScheduledDirectTask scheduledDirectTask = new ScheduledDirectTask(e.a.o.a.m(runnable));
        try {
            scheduledDirectTask.setFuture(j <= 0 ? this.f5065e.get().submit(scheduledDirectTask) : this.f5065e.get().schedule(scheduledDirectTask, j, timeUnit));
            return scheduledDirectTask;
        } catch (RejectedExecutionException e2) {
            e.a.o.a.k(e2);
            return EmptyDisposable.INSTANCE;
        }
    }

    public f(ThreadFactory threadFactory) {
        AtomicReference<ScheduledExecutorService> atomicReference = new AtomicReference<>();
        this.f5065e = atomicReference;
        this.f5064d = threadFactory;
        atomicReference.lazySet(d(threadFactory));
    }
}
