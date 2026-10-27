package a5;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import q4.d;

/* JADX INFO: compiled from: IoScheduler.java */
/* JADX INFO: loaded from: classes.dex */
public final class e extends q4.d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final h f71d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static final h f72e;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    static final c f75h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    static final a f76i;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final ThreadFactory f77b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final AtomicReference<a> f78c;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final TimeUnit f74g = TimeUnit.SECONDS;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final long f73f = Long.getLong("rx2.io-keep-alive-time", 60).longValue();

    /* JADX INFO: compiled from: IoScheduler.java */
    static final class a implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final long f79e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private final ConcurrentLinkedQueue<c> f80g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final t4.a f81h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private final ScheduledExecutorService f82i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private final Future<?> f83j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        private final ThreadFactory f84k;

        a(long j7, TimeUnit timeUnit, ThreadFactory threadFactory) {
            ScheduledFuture<?> scheduledFutureScheduleWithFixedDelay;
            long nanos = timeUnit != null ? timeUnit.toNanos(j7) : 0L;
            this.f79e = nanos;
            this.f80g = new ConcurrentLinkedQueue<>();
            this.f81h = new t4.a();
            this.f84k = threadFactory;
            ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool = null;
            if (timeUnit != null) {
                scheduledExecutorServiceNewScheduledThreadPool = Executors.newScheduledThreadPool(1, e.f72e);
                scheduledFutureScheduleWithFixedDelay = scheduledExecutorServiceNewScheduledThreadPool.scheduleWithFixedDelay(this, nanos, nanos, TimeUnit.NANOSECONDS);
            } else {
                scheduledFutureScheduleWithFixedDelay = null;
            }
            this.f82i = scheduledExecutorServiceNewScheduledThreadPool;
            this.f83j = scheduledFutureScheduleWithFixedDelay;
        }

        void a() {
            if (this.f80g.isEmpty()) {
                return;
            }
            long jC = c();
            for (c cVar : this.f80g) {
                if (cVar.f() > jC) {
                    return;
                }
                if (this.f80g.remove(cVar)) {
                    this.f81h.c(cVar);
                }
            }
        }

        c b() {
            if (this.f81h.isDisposed()) {
                return e.f75h;
            }
            while (!this.f80g.isEmpty()) {
                c cVarPoll = this.f80g.poll();
                if (cVarPoll != null) {
                    return cVarPoll;
                }
            }
            c cVar = new c(this.f84k);
            this.f81h.a(cVar);
            return cVar;
        }

        long c() {
            return System.nanoTime();
        }

        void d(c cVar) {
            cVar.g(c() + this.f79e);
            this.f80g.offer(cVar);
        }

        void e() {
            this.f81h.dispose();
            Future<?> future = this.f83j;
            if (future != null) {
                future.cancel(true);
            }
            ScheduledExecutorService scheduledExecutorService = this.f82i;
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
    static final class b extends d.b {

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private final a f86g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private final c f87h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        final AtomicBoolean f88i = new AtomicBoolean();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final t4.a f85e = new t4.a();

        b(a aVar) {
            this.f86g = aVar;
            this.f87h = aVar.b();
        }

        @Override // q4.d.b
        public t4.b b(Runnable runnable, long j7, TimeUnit timeUnit) {
            return this.f85e.isDisposed() ? w4.c.INSTANCE : this.f87h.c(runnable, j7, timeUnit, this.f85e);
        }

        @Override // t4.b
        public void dispose() {
            if (this.f88i.compareAndSet(false, true)) {
                this.f85e.dispose();
                this.f86g.d(this.f87h);
            }
        }

        @Override // t4.b
        public boolean isDisposed() {
            return this.f88i.get();
        }
    }

    /* JADX INFO: compiled from: IoScheduler.java */
    static final class c extends g {

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private long f89h;

        c(ThreadFactory threadFactory) {
            super(threadFactory);
            this.f89h = 0L;
        }

        public long f() {
            return this.f89h;
        }

        public void g(long j7) {
            this.f89h = j7;
        }
    }

    static {
        c cVar = new c(new h("RxCachedThreadSchedulerShutdown"));
        f75h = cVar;
        cVar.dispose();
        int iMax = Math.max(1, Math.min(10, Integer.getInteger("rx2.io-priority", 5).intValue()));
        h hVar = new h("RxCachedThreadScheduler", iMax);
        f71d = hVar;
        f72e = new h("RxCachedWorkerPoolEvictor", iMax);
        a aVar = new a(0L, null, hVar);
        f76i = aVar;
        aVar.e();
    }

    public e() {
        this(f71d);
    }

    @Override // q4.d
    public d.b a() {
        return new b(this.f78c.get());
    }

    public void d() {
        a aVar = new a(f73f, f74g, this.f77b);
        if (this.f78c.compareAndSet(f76i, aVar)) {
            return;
        }
        aVar.e();
    }

    public e(ThreadFactory threadFactory) {
        this.f77b = threadFactory;
        this.f78c = new AtomicReference<>(f76i);
        d();
    }
}
