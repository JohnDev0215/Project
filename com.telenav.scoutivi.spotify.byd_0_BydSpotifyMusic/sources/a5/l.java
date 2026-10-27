package a5;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import q4.d;

/* JADX INFO: compiled from: SingleScheduler.java */
/* JADX INFO: loaded from: classes.dex */
public final class l extends q4.d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final h f108d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static final ScheduledExecutorService f109e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final ThreadFactory f110b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final AtomicReference<ScheduledExecutorService> f111c;

    /* JADX INFO: compiled from: SingleScheduler.java */
    static final class a extends d.b {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final ScheduledExecutorService f112e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final t4.a f113g = new t4.a();

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        volatile boolean f114h;

        a(ScheduledExecutorService scheduledExecutorService) {
            this.f112e = scheduledExecutorService;
        }

        @Override // q4.d.b
        public t4.b b(Runnable runnable, long j7, TimeUnit timeUnit) {
            if (this.f114h) {
                return w4.c.INSTANCE;
            }
            j jVar = new j(d5.a.m(runnable), this.f113g);
            this.f113g.a(jVar);
            try {
                jVar.a(j7 <= 0 ? this.f112e.submit((Callable) jVar) : this.f112e.schedule((Callable) jVar, j7, timeUnit));
                return jVar;
            } catch (RejectedExecutionException e7) {
                dispose();
                d5.a.k(e7);
                return w4.c.INSTANCE;
            }
        }

        @Override // t4.b
        public void dispose() {
            if (this.f114h) {
                return;
            }
            this.f114h = true;
            this.f113g.dispose();
        }

        @Override // t4.b
        public boolean isDisposed() {
            return this.f114h;
        }
    }

    static {
        ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool = Executors.newScheduledThreadPool(0);
        f109e = scheduledExecutorServiceNewScheduledThreadPool;
        scheduledExecutorServiceNewScheduledThreadPool.shutdown();
        f108d = new h("RxSingleScheduler", Math.max(1, Math.min(10, Integer.getInteger("rx2.single-priority", 5).intValue())), true);
    }

    public l() {
        this(f108d);
    }

    static ScheduledExecutorService d(ThreadFactory threadFactory) {
        return k.a(threadFactory);
    }

    @Override // q4.d
    public d.b a() {
        return new a(this.f111c.get());
    }

    @Override // q4.d
    public t4.b c(Runnable runnable, long j7, TimeUnit timeUnit) {
        i iVar = new i(d5.a.m(runnable));
        try {
            iVar.a(j7 <= 0 ? this.f111c.get().submit(iVar) : this.f111c.get().schedule(iVar, j7, timeUnit));
            return iVar;
        } catch (RejectedExecutionException e7) {
            d5.a.k(e7);
            return w4.c.INSTANCE;
        }
    }

    public l(ThreadFactory threadFactory) {
        AtomicReference<ScheduledExecutorService> atomicReference = new AtomicReference<>();
        this.f111c = atomicReference;
        this.f110b = threadFactory;
        atomicReference.lazySet(d(threadFactory));
    }
}
