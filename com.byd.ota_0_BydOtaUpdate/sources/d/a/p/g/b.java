package d.a.p.g;

import d.a.j;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.schedulers.RxThreadFactory;
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
    public static final RxThreadFactory f3222b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final RxThreadFactory f3223c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final TimeUnit f3224d = TimeUnit.SECONDS;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final c f3225e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final a f3226f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final AtomicReference<a> f3227g = new AtomicReference<>(f3226f);

    /* JADX INFO: compiled from: IoScheduler.java */
    public static final class a implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final long f3228c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final ConcurrentLinkedQueue<c> f3229d;
        public final d.a.m.a s;
        public final ScheduledExecutorService x;
        public final Future<?> y;

        public a(long j, TimeUnit timeUnit) {
            ScheduledFuture<?> scheduledFutureScheduleWithFixedDelay;
            long nanos = timeUnit != null ? timeUnit.toNanos(j) : 0L;
            this.f3228c = nanos;
            this.f3229d = new ConcurrentLinkedQueue<>();
            this.s = new d.a.m.a();
            ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool = null;
            if (timeUnit != null) {
                scheduledExecutorServiceNewScheduledThreadPool = Executors.newScheduledThreadPool(1, b.f3223c);
                scheduledFutureScheduleWithFixedDelay = scheduledExecutorServiceNewScheduledThreadPool.scheduleWithFixedDelay(this, nanos, nanos, TimeUnit.NANOSECONDS);
            } else {
                scheduledFutureScheduleWithFixedDelay = null;
            }
            this.x = scheduledExecutorServiceNewScheduledThreadPool;
            this.y = scheduledFutureScheduleWithFixedDelay;
        }

        public void a() {
            if (this.f3229d.isEmpty()) {
                return;
            }
            long jC = c();
            for (c cVar : this.f3229d) {
                if (cVar.f() > jC) {
                    return;
                }
                if (this.f3229d.remove(cVar)) {
                    this.s.b(cVar);
                }
            }
        }

        public c b() {
            if (this.s.isDisposed()) {
                return b.f3225e;
            }
            while (!this.f3229d.isEmpty()) {
                c cVarPoll = this.f3229d.poll();
                if (cVarPoll != null) {
                    return cVarPoll;
                }
            }
            c cVar = new c(b.f3222b);
            this.s.c(cVar);
            return cVar;
        }

        public long c() {
            return System.nanoTime();
        }

        public void d(c cVar) {
            cVar.g(c() + this.f3228c);
            this.f3229d.offer(cVar);
        }

        public void e() {
            this.s.dispose();
            Future<?> future = this.y;
            if (future != null) {
                future.cancel(true);
            }
            ScheduledExecutorService scheduledExecutorService = this.x;
            if (scheduledExecutorService != null) {
                scheduledExecutorService.shutdownNow();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            a();
        }
    }

    /* JADX INFO: renamed from: d.a.p.g.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: IoScheduler.java */
    public static final class C0076b extends j.b {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final a f3231d;
        public final c s;
        public final AtomicBoolean x = new AtomicBoolean();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final d.a.m.a f3230c = new d.a.m.a();

        public C0076b(a aVar) {
            this.f3231d = aVar;
            this.s = aVar.b();
        }

        @Override // d.a.j.b
        public d.a.m.b c(Runnable runnable, long j, TimeUnit timeUnit) {
            return this.f3230c.isDisposed() ? EmptyDisposable.INSTANCE : this.s.d(runnable, j, timeUnit, this.f3230c);
        }

        @Override // d.a.m.b
        public void dispose() {
            if (this.x.compareAndSet(false, true)) {
                this.f3230c.dispose();
                this.f3231d.d(this.s);
            }
        }

        @Override // d.a.m.b
        public boolean isDisposed() {
            return this.x.get();
        }
    }

    /* JADX INFO: compiled from: IoScheduler.java */
    public static final class c extends d {
        public long s;

        public c(ThreadFactory threadFactory) {
            super(threadFactory);
            this.s = 0L;
        }

        public long f() {
            return this.s;
        }

        public void g(long j) {
            this.s = j;
        }
    }

    static {
        a aVar = new a(0L, null);
        f3226f = aVar;
        aVar.e();
        c cVar = new c(new RxThreadFactory("RxCachedThreadSchedulerShutdown"));
        f3225e = cVar;
        cVar.dispose();
        int iMax = Math.max(1, Math.min(10, Integer.getInteger("rx2.io-priority", 5).intValue()));
        f3222b = new RxThreadFactory("RxCachedThreadScheduler", iMax);
        f3223c = new RxThreadFactory("RxCachedWorkerPoolEvictor", iMax);
    }

    public b() {
        d();
    }

    @Override // d.a.j
    public j.b a() {
        return new C0076b(this.f3227g.get());
    }

    public void d() {
        a aVar = new a(60L, f3224d);
        if (this.f3227g.compareAndSet(f3226f, aVar)) {
            return;
        }
        aVar.e();
    }
}
