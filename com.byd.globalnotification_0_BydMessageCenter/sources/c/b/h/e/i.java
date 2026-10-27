package c.b.h.e;

import c.b.c;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class i extends c.b.c {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final e f2298c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final ScheduledExecutorService f2299d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final AtomicReference<ScheduledExecutorService> f2300b;

    public static final class a extends c.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ScheduledExecutorService f2301a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final c.b.e.a f2302b = new c.b.e.a();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public volatile boolean f2303c;

        public a(ScheduledExecutorService scheduledExecutorService) {
            this.f2301a = scheduledExecutorService;
        }

        @Override // c.b.c.b
        public c.b.e.b b(Runnable runnable, long j, TimeUnit timeUnit) {
            c.b.h.a.c cVar = c.b.h.a.c.INSTANCE;
            if (this.f2303c) {
                return cVar;
            }
            c.b.h.b.b.a(runnable, "run is null");
            g gVar = new g(runnable, this.f2302b);
            this.f2302b.a(gVar);
            try {
                gVar.a(j <= 0 ? this.f2301a.submit((Callable) gVar) : this.f2301a.schedule((Callable) gVar, j, timeUnit));
                return gVar;
            } catch (RejectedExecutionException e2) {
                d();
                c.b.j.a.c(e2);
                return cVar;
            }
        }

        @Override // c.b.e.b
        public void d() {
            if (this.f2303c) {
                return;
            }
            this.f2303c = true;
            this.f2302b.d();
        }
    }

    static {
        ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool = Executors.newScheduledThreadPool(0);
        f2299d = scheduledExecutorServiceNewScheduledThreadPool;
        scheduledExecutorServiceNewScheduledThreadPool.shutdown();
        f2298c = new e("RxSingleScheduler", Math.max(1, Math.min(10, Integer.getInteger("rx2.single-priority", 5).intValue())), true);
    }

    public i() {
        e eVar = f2298c;
        AtomicReference<ScheduledExecutorService> atomicReference = new AtomicReference<>();
        this.f2300b = atomicReference;
        atomicReference.lazySet(h.a(eVar));
    }

    @Override // c.b.c
    public c.b a() {
        return new a(this.f2300b.get());
    }

    @Override // c.b.c
    public c.b.e.b c(Runnable runnable, long j, TimeUnit timeUnit) {
        c.b.h.b.b.a(runnable, "run is null");
        f fVar = new f(runnable);
        try {
            fVar.a(j <= 0 ? this.f2300b.get().submit(fVar) : this.f2300b.get().schedule(fVar, j, timeUnit));
            return fVar;
        } catch (RejectedExecutionException e2) {
            c.b.j.a.c(e2);
            return c.b.h.a.c.INSTANCE;
        }
    }
}
