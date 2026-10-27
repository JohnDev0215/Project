package io.reactivex.internal.schedulers;

import d.a.j;
import io.reactivex.internal.disposables.EmptyDisposable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: IoScheduler.java */
/* JADX INFO: loaded from: classes.dex */
public final class b extends j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final RxThreadFactory f4312b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final RxThreadFactory f4313c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final TimeUnit f4314d = TimeUnit.SECONDS;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static final c f4315e;
    static final a f;
    final ThreadFactory g;
    final AtomicReference<a> h;

    /* JADX INFO: compiled from: IoScheduler.java */
    static final class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final long f4316b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final ConcurrentLinkedQueue<c> f4317c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final io.reactivex.disposables.a f4318d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final ScheduledExecutorService f4319e;
        private final Future<?> f;
        private final ThreadFactory g;

        a(long j, TimeUnit timeUnit, ThreadFactory threadFactory) {
            ScheduledFuture<?> scheduledFutureScheduleWithFixedDelay;
            long nanos = timeUnit != null ? timeUnit.toNanos(j) : 0L;
            this.f4316b = nanos;
            this.f4317c = new ConcurrentLinkedQueue<>();
            this.f4318d = new io.reactivex.disposables.a();
            this.g = threadFactory;
            ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool = null;
            if (timeUnit != null) {
                scheduledExecutorServiceNewScheduledThreadPool = Executors.newScheduledThreadPool(1, b.f4313c);
                scheduledFutureScheduleWithFixedDelay = scheduledExecutorServiceNewScheduledThreadPool.scheduleWithFixedDelay(this, nanos, nanos, TimeUnit.NANOSECONDS);
            } else {
                scheduledFutureScheduleWithFixedDelay = null;
            }
            this.f4319e = scheduledExecutorServiceNewScheduledThreadPool;
            this.f = scheduledFutureScheduleWithFixedDelay;
        }

        void a() {
            if (this.f4317c.isEmpty()) {
                return;
            }
            long jC = c();
            for (c cVar : this.f4317c) {
                if (cVar.g() > jC) {
                    return;
                }
                if (this.f4317c.remove(cVar)) {
                    this.f4318d.b(cVar);
                }
            }
        }

        c b() {
            if (this.f4318d.isDisposed()) {
                return b.f4315e;
            }
            while (!this.f4317c.isEmpty()) {
                c cVarPoll = this.f4317c.poll();
                if (cVarPoll != null) {
                    return cVarPoll;
                }
            }
            c cVar = new c(this.g);
            this.f4318d.c(cVar);
            return cVar;
        }

        long c() {
            return System.nanoTime();
        }

        void d(c cVar) {
            cVar.h(c() + this.f4316b);
            this.f4317c.offer(cVar);
        }

        void e() {
            this.f4318d.dispose();
            Future<?> future = this.f;
            if (future != null) {
                future.cancel(true);
            }
            ScheduledExecutorService scheduledExecutorService = this.f4319e;
            if (scheduledExecutorService != null) {
                scheduledExecutorService.shutdownNow();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            a();
        }
    }

    /* JADX INFO: renamed from: io.reactivex.internal.schedulers.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: IoScheduler.java */
    static final class C0112b extends j.b {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final a f4321c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final c f4322d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final AtomicBoolean f4323e = new AtomicBoolean();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final io.reactivex.disposables.a f4320b = new io.reactivex.disposables.a();

        C0112b(a aVar) {
            this.f4321c = aVar;
            this.f4322d = aVar.b();
        }

        @Override // d.a.j.b
        public io.reactivex.disposables.b c(Runnable runnable, long j, TimeUnit timeUnit) {
            return this.f4320b.isDisposed() ? EmptyDisposable.INSTANCE : this.f4322d.d(runnable, j, timeUnit, this.f4320b);
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            if (this.f4323e.compareAndSet(false, true)) {
                this.f4320b.dispose();
                this.f4321c.d(this.f4322d);
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.f4323e.get();
        }
    }

    /* JADX INFO: compiled from: IoScheduler.java */
    static final class c extends d {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private long f4324d;

        c(ThreadFactory threadFactory) {
            super(threadFactory);
            this.f4324d = 0L;
        }

        public long g() {
            return this.f4324d;
        }

        public void h(long j) {
            this.f4324d = j;
        }
    }

    static {
        c cVar = new c(new RxThreadFactory("RxCachedThreadSchedulerShutdown"));
        f4315e = cVar;
        cVar.dispose();
        int iMax = Math.max(1, Math.min(10, Integer.getInteger("rx2.io-priority", 5).intValue()));
        RxThreadFactory rxThreadFactory = new RxThreadFactory("RxCachedThreadScheduler", iMax);
        f4312b = rxThreadFactory;
        f4313c = new RxThreadFactory("RxCachedWorkerPoolEvictor", iMax);
        a aVar = new a(0L, null, rxThreadFactory);
        f = aVar;
        aVar.e();
    }

    public b() {
        this(f4312b);
    }

    @Override // d.a.j
    public j.b a() {
        return new C0112b(this.h.get());
    }

    public void d() {
        a aVar = new a(60L, f4314d, this.g);
        if (this.h.compareAndSet(f, aVar)) {
            return;
        }
        aVar.e();
    }

    public b(ThreadFactory threadFactory) {
        this.g = threadFactory;
        this.h = new AtomicReference<>(f);
        d();
    }
}
