package c.a.m.g;

import c.a.h;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class c extends c.a.h {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final f f1865b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final f f1866c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final TimeUnit f1867d = TimeUnit.SECONDS;
    static final C0061c e;
    static final a f;
    final ThreadFactory g;
    final AtomicReference<a> h;

    static final class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final long f1868b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final ConcurrentLinkedQueue<C0061c> f1869c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final c.a.j.a f1870d;
        private final ScheduledExecutorService e;
        private final Future<?> f;
        private final ThreadFactory g;

        a(long j, TimeUnit timeUnit, ThreadFactory threadFactory) {
            ScheduledFuture<?> scheduledFutureScheduleWithFixedDelay;
            long nanos = timeUnit != null ? timeUnit.toNanos(j) : 0L;
            this.f1868b = nanos;
            this.f1869c = new ConcurrentLinkedQueue<>();
            this.f1870d = new c.a.j.a();
            this.g = threadFactory;
            ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool = null;
            if (timeUnit != null) {
                scheduledExecutorServiceNewScheduledThreadPool = Executors.newScheduledThreadPool(1, c.f1866c);
                scheduledFutureScheduleWithFixedDelay = scheduledExecutorServiceNewScheduledThreadPool.scheduleWithFixedDelay(this, nanos, nanos, TimeUnit.NANOSECONDS);
            } else {
                scheduledFutureScheduleWithFixedDelay = null;
            }
            this.e = scheduledExecutorServiceNewScheduledThreadPool;
            this.f = scheduledFutureScheduleWithFixedDelay;
        }

        void a() {
            if (this.f1869c.isEmpty()) {
                return;
            }
            long jC = c();
            for (C0061c c0061c : this.f1869c) {
                if (c0061c.i() > jC) {
                    return;
                }
                if (this.f1869c.remove(c0061c)) {
                    this.f1870d.b(c0061c);
                }
            }
        }

        C0061c b() {
            if (this.f1870d.g()) {
                return c.e;
            }
            while (!this.f1869c.isEmpty()) {
                C0061c c0061cPoll = this.f1869c.poll();
                if (c0061cPoll != null) {
                    return c0061cPoll;
                }
            }
            C0061c c0061c = new C0061c(this.g);
            this.f1870d.c(c0061c);
            return c0061c;
        }

        long c() {
            return System.nanoTime();
        }

        void d(C0061c c0061c) {
            c0061c.j(c() + this.f1868b);
            this.f1869c.offer(c0061c);
        }

        void e() {
            this.f1870d.e();
            Future<?> future = this.f;
            if (future != null) {
                future.cancel(true);
            }
            ScheduledExecutorService scheduledExecutorService = this.e;
            if (scheduledExecutorService != null) {
                scheduledExecutorService.shutdownNow();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            a();
        }
    }

    static final class b extends h.b {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final a f1872c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final C0061c f1873d;
        final AtomicBoolean e = new AtomicBoolean();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final c.a.j.a f1871b = new c.a.j.a();

        b(a aVar) {
            this.f1872c = aVar;
            this.f1873d = aVar.b();
        }

        @Override // c.a.h.b
        public c.a.j.b c(Runnable runnable, long j, TimeUnit timeUnit) {
            return this.f1871b.g() ? c.a.m.a.c.INSTANCE : this.f1873d.d(runnable, j, timeUnit, this.f1871b);
        }

        @Override // c.a.j.b
        public void e() {
            if (this.e.compareAndSet(false, true)) {
                this.f1871b.e();
                this.f1872c.d(this.f1873d);
            }
        }

        @Override // c.a.j.b
        public boolean g() {
            return this.e.get();
        }
    }

    /* JADX INFO: renamed from: c.a.m.g.c$c, reason: collision with other inner class name */
    static final class C0061c extends e {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private long f1874d;

        C0061c(ThreadFactory threadFactory) {
            super(threadFactory);
            this.f1874d = 0L;
        }

        public long i() {
            return this.f1874d;
        }

        public void j(long j) {
            this.f1874d = j;
        }
    }

    static {
        C0061c c0061c = new C0061c(new f("RxCachedThreadSchedulerShutdown"));
        e = c0061c;
        c0061c.e();
        int iMax = Math.max(1, Math.min(10, Integer.getInteger("rx2.io-priority", 5).intValue()));
        f fVar = new f("RxCachedThreadScheduler", iMax);
        f1865b = fVar;
        f1866c = new f("RxCachedWorkerPoolEvictor", iMax);
        a aVar = new a(0L, null, fVar);
        f = aVar;
        aVar.e();
    }

    public c() {
        this(f1865b);
    }

    public c(ThreadFactory threadFactory) {
        this.g = threadFactory;
        this.h = new AtomicReference<>(f);
        d();
    }

    @Override // c.a.h
    public h.b a() {
        return new b(this.h.get());
    }

    public void d() {
        a aVar = new a(60L, f1867d, this.g);
        if (this.h.compareAndSet(f, aVar)) {
            return;
        }
        aVar.e();
    }
}
