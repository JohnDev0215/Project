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
public final class c extends j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final RxThreadFactory f4598b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final RxThreadFactory f4599c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final TimeUnit f4600d = TimeUnit.SECONDS;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static final C0116c f4601e;
    static final a f;
    final ThreadFactory g;
    final AtomicReference<a> h;

    /* JADX INFO: compiled from: IoScheduler.java */
    static final class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final long f4602b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final ConcurrentLinkedQueue<C0116c> f4603c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final io.reactivex.disposables.a f4604d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final ScheduledExecutorService f4605e;
        private final Future<?> f;
        private final ThreadFactory g;

        a(long j, TimeUnit timeUnit, ThreadFactory threadFactory) {
            ScheduledFuture<?> scheduledFutureScheduleWithFixedDelay;
            long nanos = timeUnit != null ? timeUnit.toNanos(j) : 0L;
            this.f4602b = nanos;
            this.f4603c = new ConcurrentLinkedQueue<>();
            this.f4604d = new io.reactivex.disposables.a();
            this.g = threadFactory;
            ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool = null;
            if (timeUnit != null) {
                scheduledExecutorServiceNewScheduledThreadPool = Executors.newScheduledThreadPool(1, c.f4599c);
                scheduledFutureScheduleWithFixedDelay = scheduledExecutorServiceNewScheduledThreadPool.scheduleWithFixedDelay(this, nanos, nanos, TimeUnit.NANOSECONDS);
            } else {
                scheduledFutureScheduleWithFixedDelay = null;
            }
            this.f4605e = scheduledExecutorServiceNewScheduledThreadPool;
            this.f = scheduledFutureScheduleWithFixedDelay;
        }

        void a() {
            if (this.f4603c.isEmpty()) {
                return;
            }
            long jC = c();
            for (C0116c c0116c : this.f4603c) {
                if (c0116c.g() > jC) {
                    return;
                }
                if (this.f4603c.remove(c0116c)) {
                    this.f4604d.b(c0116c);
                }
            }
        }

        C0116c b() {
            if (this.f4604d.isDisposed()) {
                return c.f4601e;
            }
            while (!this.f4603c.isEmpty()) {
                C0116c c0116cPoll = this.f4603c.poll();
                if (c0116cPoll != null) {
                    return c0116cPoll;
                }
            }
            C0116c c0116c = new C0116c(this.g);
            this.f4604d.c(c0116c);
            return c0116c;
        }

        long c() {
            return System.nanoTime();
        }

        void d(C0116c c0116c) {
            c0116c.h(c() + this.f4602b);
            this.f4603c.offer(c0116c);
        }

        void e() {
            this.f4604d.dispose();
            Future<?> future = this.f;
            if (future != null) {
                future.cancel(true);
            }
            ScheduledExecutorService scheduledExecutorService = this.f4605e;
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
    static final class b extends j.b {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final a f4607c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final C0116c f4608d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final AtomicBoolean f4609e = new AtomicBoolean();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final io.reactivex.disposables.a f4606b = new io.reactivex.disposables.a();

        b(a aVar) {
            this.f4607c = aVar;
            this.f4608d = aVar.b();
        }

        @Override // d.a.j.b
        public io.reactivex.disposables.b c(Runnable runnable, long j, TimeUnit timeUnit) {
            return this.f4606b.isDisposed() ? EmptyDisposable.INSTANCE : this.f4608d.d(runnable, j, timeUnit, this.f4606b);
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            if (this.f4609e.compareAndSet(false, true)) {
                this.f4606b.dispose();
                this.f4607c.d(this.f4608d);
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.f4609e.get();
        }
    }

    /* JADX INFO: renamed from: io.reactivex.internal.schedulers.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: IoScheduler.java */
    static final class C0116c extends e {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private long f4610d;

        C0116c(ThreadFactory threadFactory) {
            super(threadFactory);
            this.f4610d = 0L;
        }

        public long g() {
            return this.f4610d;
        }

        public void h(long j) {
            this.f4610d = j;
        }
    }

    static {
        C0116c c0116c = new C0116c(new RxThreadFactory("RxCachedThreadSchedulerShutdown"));
        f4601e = c0116c;
        c0116c.dispose();
        int iMax = Math.max(1, Math.min(10, Integer.getInteger("rx2.io-priority", 5).intValue()));
        RxThreadFactory rxThreadFactory = new RxThreadFactory("RxCachedThreadScheduler", iMax);
        f4598b = rxThreadFactory;
        f4599c = new RxThreadFactory("RxCachedWorkerPoolEvictor", iMax);
        a aVar = new a(0L, null, rxThreadFactory);
        f = aVar;
        aVar.e();
    }

    public c() {
        this(f4598b);
    }

    @Override // d.a.j
    public j.b a() {
        return new b(this.h.get());
    }

    public void d() {
        a aVar = new a(60L, f4600d, this.g);
        if (this.h.compareAndSet(f, aVar)) {
            return;
        }
        aVar.e();
    }

    public c(ThreadFactory threadFactory) {
        this.g = threadFactory;
        this.h = new AtomicReference<>(f);
        d();
    }
}
