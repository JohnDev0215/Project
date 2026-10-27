package e.a.n.g;

import e.a.h;
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
public final class c extends e.a.h {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final f f4921b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final f f4922c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final TimeUnit f4923d = TimeUnit.SECONDS;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static final C0096c f4924e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    static final a f4925f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final ThreadFactory f4926g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    final AtomicReference<a> f4927h;

    /* JADX INFO: compiled from: IoScheduler.java */
    static final class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final long f4928b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final ConcurrentLinkedQueue<C0096c> f4929c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final e.a.k.a f4930d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final ScheduledExecutorService f4931e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private final Future<?> f4932f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private final ThreadFactory f4933g;

        a(long j, TimeUnit timeUnit, ThreadFactory threadFactory) {
            ScheduledFuture<?> scheduledFutureScheduleWithFixedDelay;
            long nanos = timeUnit != null ? timeUnit.toNanos(j) : 0L;
            this.f4928b = nanos;
            this.f4929c = new ConcurrentLinkedQueue<>();
            this.f4930d = new e.a.k.a();
            this.f4933g = threadFactory;
            ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool = null;
            if (timeUnit != null) {
                scheduledExecutorServiceNewScheduledThreadPool = Executors.newScheduledThreadPool(1, c.f4922c);
                scheduledFutureScheduleWithFixedDelay = scheduledExecutorServiceNewScheduledThreadPool.scheduleWithFixedDelay(this, nanos, nanos, TimeUnit.NANOSECONDS);
            } else {
                scheduledFutureScheduleWithFixedDelay = null;
            }
            this.f4931e = scheduledExecutorServiceNewScheduledThreadPool;
            this.f4932f = scheduledFutureScheduleWithFixedDelay;
        }

        void a() {
            if (this.f4929c.isEmpty()) {
                return;
            }
            long jC = c();
            for (C0096c c0096c : this.f4929c) {
                if (c0096c.h() > jC) {
                    return;
                }
                if (this.f4929c.remove(c0096c)) {
                    this.f4930d.b(c0096c);
                }
            }
        }

        C0096c b() {
            if (this.f4930d.f()) {
                return c.f4924e;
            }
            while (!this.f4929c.isEmpty()) {
                C0096c c0096cPoll = this.f4929c.poll();
                if (c0096cPoll != null) {
                    return c0096cPoll;
                }
            }
            C0096c c0096c = new C0096c(this.f4933g);
            this.f4930d.c(c0096c);
            return c0096c;
        }

        long c() {
            return System.nanoTime();
        }

        void d(C0096c c0096c) {
            c0096c.i(c() + this.f4928b);
            this.f4929c.offer(c0096c);
        }

        void e() {
            this.f4930d.d();
            Future<?> future = this.f4932f;
            if (future != null) {
                future.cancel(true);
            }
            ScheduledExecutorService scheduledExecutorService = this.f4931e;
            if (scheduledExecutorService != null) {
                scheduledExecutorService.shutdownNow();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            a();
        }
    }

    /* JADX INFO: compiled from: IoScheduler.java */
    static final class b extends h.b {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final a f4935c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final C0096c f4936d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final AtomicBoolean f4937e = new AtomicBoolean();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final e.a.k.a f4934b = new e.a.k.a();

        b(a aVar) {
            this.f4935c = aVar;
            this.f4936d = aVar.b();
        }

        @Override // e.a.h.b
        public e.a.k.b c(Runnable runnable, long j, TimeUnit timeUnit) {
            return this.f4934b.f() ? e.a.n.a.c.INSTANCE : this.f4936d.e(runnable, j, timeUnit, this.f4934b);
        }

        @Override // e.a.k.b
        public void d() {
            if (this.f4937e.compareAndSet(false, true)) {
                this.f4934b.d();
                this.f4935c.d(this.f4936d);
            }
        }
    }

    /* JADX INFO: renamed from: e.a.n.g.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: IoScheduler.java */
    static final class C0096c extends e {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private long f4938d;

        C0096c(ThreadFactory threadFactory) {
            super(threadFactory);
            this.f4938d = 0L;
        }

        public long h() {
            return this.f4938d;
        }

        public void i(long j) {
            this.f4938d = j;
        }
    }

    static {
        C0096c c0096c = new C0096c(new f("RxCachedThreadSchedulerShutdown"));
        f4924e = c0096c;
        c0096c.d();
        int iMax = Math.max(1, Math.min(10, Integer.getInteger("rx2.io-priority", 5).intValue()));
        f fVar = new f("RxCachedThreadScheduler", iMax);
        f4921b = fVar;
        f4922c = new f("RxCachedWorkerPoolEvictor", iMax);
        a aVar = new a(0L, null, fVar);
        f4925f = aVar;
        aVar.e();
    }

    public c() {
        this(f4921b);
    }

    @Override // e.a.h
    public h.b a() {
        return new b(this.f4927h.get());
    }

    public void d() {
        a aVar = new a(60L, f4923d, this.f4926g);
        if (this.f4927h.compareAndSet(f4925f, aVar)) {
            return;
        }
        aVar.e();
    }

    public c(ThreadFactory threadFactory) {
        this.f4926g = threadFactory;
        this.f4927h = new AtomicReference<>(f4925f);
        d();
    }
}
