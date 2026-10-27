package io.reactivex.internal.schedulers;

import e.a.j;
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
    static final RxThreadFactory f5036b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final RxThreadFactory f5037c;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    static final c f5040f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    static final a f5041g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    final ThreadFactory f5042h;
    final AtomicReference<a> i;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final TimeUnit f5039e = TimeUnit.SECONDS;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final long f5038d = Long.getLong("rx2.io-keep-alive-time", 60).longValue();

    /* JADX INFO: compiled from: IoScheduler.java */
    static final class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final long f5043b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final ConcurrentLinkedQueue<c> f5044c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final io.reactivex.disposables.a f5045d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final ScheduledExecutorService f5046e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private final Future<?> f5047f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private final ThreadFactory f5048g;

        a(long j, TimeUnit timeUnit, ThreadFactory threadFactory) {
            ScheduledFuture<?> scheduledFutureScheduleWithFixedDelay;
            long nanos = timeUnit != null ? timeUnit.toNanos(j) : 0L;
            this.f5043b = nanos;
            this.f5044c = new ConcurrentLinkedQueue<>();
            this.f5045d = new io.reactivex.disposables.a();
            this.f5048g = threadFactory;
            ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool = null;
            if (timeUnit != null) {
                scheduledExecutorServiceNewScheduledThreadPool = Executors.newScheduledThreadPool(1, b.f5037c);
                scheduledFutureScheduleWithFixedDelay = scheduledExecutorServiceNewScheduledThreadPool.scheduleWithFixedDelay(this, nanos, nanos, TimeUnit.NANOSECONDS);
            } else {
                scheduledFutureScheduleWithFixedDelay = null;
            }
            this.f5046e = scheduledExecutorServiceNewScheduledThreadPool;
            this.f5047f = scheduledFutureScheduleWithFixedDelay;
        }

        void a() {
            if (this.f5044c.isEmpty()) {
                return;
            }
            long jC = c();
            for (c cVar : this.f5044c) {
                if (cVar.g() > jC) {
                    return;
                }
                if (this.f5044c.remove(cVar)) {
                    this.f5045d.b(cVar);
                }
            }
        }

        c b() {
            if (this.f5045d.e()) {
                return b.f5040f;
            }
            while (!this.f5044c.isEmpty()) {
                c cVarPoll = this.f5044c.poll();
                if (cVarPoll != null) {
                    return cVarPoll;
                }
            }
            c cVar = new c(this.f5048g);
            this.f5045d.c(cVar);
            return cVar;
        }

        long c() {
            return System.nanoTime();
        }

        void d(c cVar) {
            cVar.h(c() + this.f5043b);
            this.f5044c.offer(cVar);
        }

        void e() {
            this.f5045d.dispose();
            Future<?> future = this.f5047f;
            if (future != null) {
                future.cancel(true);
            }
            ScheduledExecutorService scheduledExecutorService = this.f5046e;
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
    static final class C0092b extends j.b {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final a f5050c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final c f5051d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final AtomicBoolean f5052e = new AtomicBoolean();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final io.reactivex.disposables.a f5049b = new io.reactivex.disposables.a();

        C0092b(a aVar) {
            this.f5050c = aVar;
            this.f5051d = aVar.b();
        }

        @Override // e.a.j.b
        public io.reactivex.disposables.b c(Runnable runnable, long j, TimeUnit timeUnit) {
            return this.f5049b.e() ? EmptyDisposable.INSTANCE : this.f5051d.d(runnable, j, timeUnit, this.f5049b);
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            if (this.f5052e.compareAndSet(false, true)) {
                this.f5049b.dispose();
                this.f5050c.d(this.f5051d);
            }
        }
    }

    /* JADX INFO: compiled from: IoScheduler.java */
    static final class c extends d {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private long f5053d;

        c(ThreadFactory threadFactory) {
            super(threadFactory);
            this.f5053d = 0L;
        }

        public long g() {
            return this.f5053d;
        }

        public void h(long j) {
            this.f5053d = j;
        }
    }

    static {
        c cVar = new c(new RxThreadFactory("RxCachedThreadSchedulerShutdown"));
        f5040f = cVar;
        cVar.dispose();
        int iMax = Math.max(1, Math.min(10, Integer.getInteger("rx2.io-priority", 5).intValue()));
        RxThreadFactory rxThreadFactory = new RxThreadFactory("RxCachedThreadScheduler", iMax);
        f5036b = rxThreadFactory;
        f5037c = new RxThreadFactory("RxCachedWorkerPoolEvictor", iMax);
        a aVar = new a(0L, null, rxThreadFactory);
        f5041g = aVar;
        aVar.e();
    }

    public b() {
        this(f5036b);
    }

    @Override // e.a.j
    public j.b a() {
        return new C0092b(this.i.get());
    }

    public void d() {
        a aVar = new a(f5038d, f5039e, this.f5042h);
        if (this.i.compareAndSet(f5041g, aVar)) {
            return;
        }
        aVar.e();
    }

    public b(ThreadFactory threadFactory) {
        this.f5042h = threadFactory;
        this.i = new AtomicReference<>(f5041g);
        d();
    }
}
