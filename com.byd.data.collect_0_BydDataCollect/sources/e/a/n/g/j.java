package e.a.n.g;

import e.a.h;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: SingleScheduler.java */
/* JADX INFO: loaded from: classes.dex */
public final class j extends e.a.h {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final f f4949b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final ScheduledExecutorService f4950c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final ThreadFactory f4951d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final AtomicReference<ScheduledExecutorService> f4952e;

    /* JADX INFO: compiled from: SingleScheduler.java */
    static final class a extends h.b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final ScheduledExecutorService f4953b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final e.a.k.a f4954c = new e.a.k.a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        volatile boolean f4955d;

        a(ScheduledExecutorService scheduledExecutorService) {
            this.f4953b = scheduledExecutorService;
        }

        @Override // e.a.h.b
        public e.a.k.b c(Runnable runnable, long j, TimeUnit timeUnit) {
            if (this.f4955d) {
                return e.a.n.a.c.INSTANCE;
            }
            h hVar = new h(e.a.p.a.q(runnable), this.f4954c);
            this.f4954c.c(hVar);
            try {
                hVar.a(j <= 0 ? this.f4953b.submit((Callable) hVar) : this.f4953b.schedule((Callable) hVar, j, timeUnit));
                return hVar;
            } catch (RejectedExecutionException e2) {
                d();
                e.a.p.a.o(e2);
                return e.a.n.a.c.INSTANCE;
            }
        }

        @Override // e.a.k.b
        public void d() {
            if (this.f4955d) {
                return;
            }
            this.f4955d = true;
            this.f4954c.d();
        }
    }

    static {
        ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool = Executors.newScheduledThreadPool(0);
        f4950c = scheduledExecutorServiceNewScheduledThreadPool;
        scheduledExecutorServiceNewScheduledThreadPool.shutdown();
        f4949b = new f("RxSingleScheduler", Math.max(1, Math.min(10, Integer.getInteger("rx2.single-priority", 5).intValue())), true);
    }

    public j() {
        this(f4949b);
    }

    static ScheduledExecutorService d(ThreadFactory threadFactory) {
        return i.a(threadFactory);
    }

    @Override // e.a.h
    public h.b a() {
        return new a(this.f4952e.get());
    }

    @Override // e.a.h
    public e.a.k.b c(Runnable runnable, long j, TimeUnit timeUnit) {
        g gVar = new g(e.a.p.a.q(runnable));
        try {
            gVar.a(j <= 0 ? this.f4952e.get().submit(gVar) : this.f4952e.get().schedule(gVar, j, timeUnit));
            return gVar;
        } catch (RejectedExecutionException e2) {
            e.a.p.a.o(e2);
            return e.a.n.a.c.INSTANCE;
        }
    }

    public j(ThreadFactory threadFactory) {
        AtomicReference<ScheduledExecutorService> atomicReference = new AtomicReference<>();
        this.f4952e = atomicReference;
        this.f4951d = threadFactory;
        atomicReference.lazySet(d(threadFactory));
    }
}
