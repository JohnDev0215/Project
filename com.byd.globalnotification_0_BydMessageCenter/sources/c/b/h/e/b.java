package c.b.h.e;

import c.b.c;
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
public final class b extends c.b.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final e f2262d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final e f2263e;
    public static final c h;
    public static boolean i;
    public static final a j;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ThreadFactory f2265b = f2262d;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final AtomicReference<a> f2266c = new AtomicReference<>(j);
    public static final TimeUnit g = TimeUnit.SECONDS;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final long f2264f = Long.getLong("rx2.io-keep-alive-time", 60).longValue();

    public static final class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final long f2267a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final ConcurrentLinkedQueue<c> f2268b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final c.b.e.a f2269c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final ScheduledExecutorService f2270d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final Future<?> f2271e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final ThreadFactory f2272f;

        public a(long j, TimeUnit timeUnit, ThreadFactory threadFactory) {
            ScheduledFuture<?> scheduledFutureScheduleWithFixedDelay;
            this.f2267a = timeUnit != null ? timeUnit.toNanos(j) : 0L;
            this.f2268b = new ConcurrentLinkedQueue<>();
            this.f2269c = new c.b.e.a();
            this.f2272f = threadFactory;
            ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool = null;
            if (timeUnit != null) {
                scheduledExecutorServiceNewScheduledThreadPool = Executors.newScheduledThreadPool(1, b.f2263e);
                long j2 = this.f2267a;
                scheduledFutureScheduleWithFixedDelay = scheduledExecutorServiceNewScheduledThreadPool.scheduleWithFixedDelay(this, j2, j2, TimeUnit.NANOSECONDS);
            } else {
                scheduledFutureScheduleWithFixedDelay = null;
            }
            this.f2270d = scheduledExecutorServiceNewScheduledThreadPool;
            this.f2271e = scheduledFutureScheduleWithFixedDelay;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f2268b.isEmpty()) {
                return;
            }
            long jNanoTime = System.nanoTime();
            for (c cVar : this.f2268b) {
                if (cVar.f2277c > jNanoTime) {
                    return;
                }
                if (this.f2268b.remove(cVar) && this.f2269c.b(cVar)) {
                    cVar.d();
                }
            }
        }
    }

    /* JADX INFO: renamed from: c.b.h.e.b$b, reason: collision with other inner class name */
    public static final class RunnableC0053b extends c.b implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final a f2274b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final c f2275c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final AtomicBoolean f2276d = new AtomicBoolean();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final c.b.e.a f2273a = new c.b.e.a();

        public RunnableC0053b(a aVar) {
            c cVar;
            c cVar2;
            this.f2274b = aVar;
            if (aVar.f2269c.f2218b) {
                cVar2 = b.h;
            } else {
                while (true) {
                    if (aVar.f2268b.isEmpty()) {
                        cVar = new c(aVar.f2272f);
                        aVar.f2269c.a(cVar);
                        break;
                    } else {
                        cVar = aVar.f2268b.poll();
                        if (cVar != null) {
                            break;
                        }
                    }
                }
                cVar2 = cVar;
            }
            this.f2275c = cVar2;
        }

        @Override // c.b.c.b
        public c.b.e.b b(Runnable runnable, long j, TimeUnit timeUnit) {
            return this.f2273a.f2218b ? c.b.h.a.c.INSTANCE : this.f2275c.c(runnable, j, timeUnit, this.f2273a);
        }

        @Override // c.b.e.b
        public void d() {
            if (this.f2276d.compareAndSet(false, true)) {
                this.f2273a.d();
                if (b.i) {
                    this.f2275c.c(this, 0L, TimeUnit.NANOSECONDS, null);
                    return;
                }
                a aVar = this.f2274b;
                c cVar = this.f2275c;
                if (aVar == null) {
                    throw null;
                }
                cVar.f2277c = System.nanoTime() + aVar.f2267a;
                aVar.f2268b.offer(cVar);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            a aVar = this.f2274b;
            c cVar = this.f2275c;
            if (aVar == null) {
                throw null;
            }
            cVar.f2277c = System.nanoTime() + aVar.f2267a;
            aVar.f2268b.offer(cVar);
        }
    }

    public static final class c extends d {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public long f2277c;

        public c(ThreadFactory threadFactory) {
            super(threadFactory);
            this.f2277c = 0L;
        }
    }

    static {
        c cVar = new c(new e("RxCachedThreadSchedulerShutdown"));
        h = cVar;
        cVar.d();
        int iMax = Math.max(1, Math.min(10, Integer.getInteger("rx2.io-priority", 5).intValue()));
        f2262d = new e("RxCachedThreadScheduler", iMax);
        f2263e = new e("RxCachedWorkerPoolEvictor", iMax);
        i = Boolean.getBoolean("rx2.io-scheduled-release");
        a aVar = new a(0L, null, f2262d);
        j = aVar;
        aVar.f2269c.d();
        Future<?> future = aVar.f2271e;
        if (future != null) {
            future.cancel(true);
        }
        ScheduledExecutorService scheduledExecutorService = aVar.f2270d;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdownNow();
        }
    }

    public b() {
        a aVar = new a(f2264f, g, this.f2265b);
        if (this.f2266c.compareAndSet(j, aVar)) {
            return;
        }
        aVar.f2269c.d();
        Future<?> future = aVar.f2271e;
        if (future != null) {
            future.cancel(true);
        }
        ScheduledExecutorService scheduledExecutorService = aVar.f2270d;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdownNow();
        }
    }

    @Override // c.b.c
    public c.b a() {
        return new RunnableC0053b(this.f2266c.get());
    }
}
