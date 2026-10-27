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
public final class f extends j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final RxThreadFactory f4335b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final ScheduledExecutorService f4336c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final ThreadFactory f4337d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final AtomicReference<ScheduledExecutorService> f4338e;

    /* JADX INFO: compiled from: SingleScheduler.java */
    static final class a extends j.b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final ScheduledExecutorService f4339b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final io.reactivex.disposables.a f4340c = new io.reactivex.disposables.a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        volatile boolean f4341d;

        a(ScheduledExecutorService scheduledExecutorService) {
            this.f4339b = scheduledExecutorService;
        }

        @Override // d.a.j.b
        public io.reactivex.disposables.b c(Runnable runnable, long j, TimeUnit timeUnit) {
            if (this.f4341d) {
                return EmptyDisposable.INSTANCE;
            }
            ScheduledRunnable scheduledRunnable = new ScheduledRunnable(d.a.p.a.n(runnable), this.f4340c);
            this.f4340c.c(scheduledRunnable);
            try {
                scheduledRunnable.setFuture(j <= 0 ? this.f4339b.submit((Callable) scheduledRunnable) : this.f4339b.schedule((Callable) scheduledRunnable, j, timeUnit));
                return scheduledRunnable;
            } catch (RejectedExecutionException e2) {
                dispose();
                d.a.p.a.l(e2);
                return EmptyDisposable.INSTANCE;
            }
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            if (this.f4341d) {
                return;
            }
            this.f4341d = true;
            this.f4340c.dispose();
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.f4341d;
        }
    }

    static {
        ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool = Executors.newScheduledThreadPool(0);
        f4336c = scheduledExecutorServiceNewScheduledThreadPool;
        scheduledExecutorServiceNewScheduledThreadPool.shutdown();
        f4335b = new RxThreadFactory("RxSingleScheduler", Math.max(1, Math.min(10, Integer.getInteger("rx2.single-priority", 5).intValue())), true);
    }

    public f() {
        this(f4335b);
    }

    static ScheduledExecutorService d(ThreadFactory threadFactory) {
        return e.a(threadFactory);
    }

    @Override // d.a.j
    public j.b a() {
        return new a(this.f4338e.get());
    }

    @Override // d.a.j
    public io.reactivex.disposables.b c(Runnable runnable, long j, TimeUnit timeUnit) {
        ScheduledDirectTask scheduledDirectTask = new ScheduledDirectTask(d.a.p.a.n(runnable));
        try {
            scheduledDirectTask.setFuture(j <= 0 ? this.f4338e.get().submit(scheduledDirectTask) : this.f4338e.get().schedule(scheduledDirectTask, j, timeUnit));
            return scheduledDirectTask;
        } catch (RejectedExecutionException e2) {
            d.a.p.a.l(e2);
            return EmptyDisposable.INSTANCE;
        }
    }

    public f(ThreadFactory threadFactory) {
        AtomicReference<ScheduledExecutorService> atomicReference = new AtomicReference<>();
        this.f4338e = atomicReference;
        this.f4337d = threadFactory;
        atomicReference.lazySet(d(threadFactory));
    }
}
