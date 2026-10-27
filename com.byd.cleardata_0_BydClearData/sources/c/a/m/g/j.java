package c.a.m.g;

import c.a.h;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class j extends c.a.h {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final f f1889b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final ScheduledExecutorService f1890c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final ThreadFactory f1891d;
    final AtomicReference<ScheduledExecutorService> e;

    static final class a extends h.b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final ScheduledExecutorService f1892b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final c.a.j.a f1893c = new c.a.j.a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        volatile boolean f1894d;

        a(ScheduledExecutorService scheduledExecutorService) {
            this.f1892b = scheduledExecutorService;
        }

        @Override // c.a.h.b
        public c.a.j.b c(Runnable runnable, long j, TimeUnit timeUnit) {
            if (this.f1894d) {
                return c.a.m.a.c.INSTANCE;
            }
            h hVar = new h(c.a.n.a.o(runnable), this.f1893c);
            this.f1893c.c(hVar);
            try {
                hVar.a(j <= 0 ? this.f1892b.submit((Callable) hVar) : this.f1892b.schedule((Callable) hVar, j, timeUnit));
                return hVar;
            } catch (RejectedExecutionException e) {
                e();
                c.a.n.a.l(e);
                return c.a.m.a.c.INSTANCE;
            }
        }

        @Override // c.a.j.b
        public void e() {
            if (this.f1894d) {
                return;
            }
            this.f1894d = true;
            this.f1893c.e();
        }

        @Override // c.a.j.b
        public boolean g() {
            return this.f1894d;
        }
    }

    static {
        ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool = Executors.newScheduledThreadPool(0);
        f1890c = scheduledExecutorServiceNewScheduledThreadPool;
        scheduledExecutorServiceNewScheduledThreadPool.shutdown();
        f1889b = new f("RxSingleScheduler", Math.max(1, Math.min(10, Integer.getInteger("rx2.single-priority", 5).intValue())), true);
    }

    public j() {
        this(f1889b);
    }

    public j(ThreadFactory threadFactory) {
        AtomicReference<ScheduledExecutorService> atomicReference = new AtomicReference<>();
        this.e = atomicReference;
        this.f1891d = threadFactory;
        atomicReference.lazySet(d(threadFactory));
    }

    static ScheduledExecutorService d(ThreadFactory threadFactory) {
        return i.a(threadFactory);
    }

    @Override // c.a.h
    public h.b a() {
        return new a(this.e.get());
    }

    @Override // c.a.h
    public c.a.j.b c(Runnable runnable, long j, TimeUnit timeUnit) {
        g gVar = new g(c.a.n.a.o(runnable));
        try {
            gVar.a(j <= 0 ? this.e.get().submit(gVar) : this.e.get().schedule(gVar, j, timeUnit));
            return gVar;
        } catch (RejectedExecutionException e) {
            c.a.n.a.l(e);
            return c.a.m.a.c.INSTANCE;
        }
    }
}
