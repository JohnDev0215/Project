package c.b.h.e;

import c.b.c;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class a extends c.b.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final b f2249d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final e f2250e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final int f2251f;
    public static final c g;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ThreadFactory f2252b = f2250e;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final AtomicReference<b> f2253c = new AtomicReference<>(f2249d);

    /* JADX INFO: renamed from: c.b.h.e.a$a, reason: collision with other inner class name */
    public static final class C0052a extends c.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final c.b.h.a.d f2254a = new c.b.h.a.d();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final c.b.e.a f2255b = new c.b.e.a();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final c.b.h.a.d f2256c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final c f2257d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public volatile boolean f2258e;

        public C0052a(c cVar) {
            this.f2257d = cVar;
            c.b.h.a.d dVar = new c.b.h.a.d();
            this.f2256c = dVar;
            dVar.a(this.f2254a);
            this.f2256c.a(this.f2255b);
        }

        @Override // c.b.c.b
        public c.b.e.b b(Runnable runnable, long j, TimeUnit timeUnit) {
            return this.f2258e ? c.b.h.a.c.INSTANCE : this.f2257d.c(runnable, j, timeUnit, this.f2255b);
        }

        @Override // c.b.e.b
        public void d() {
            if (this.f2258e) {
                return;
            }
            this.f2258e = true;
            this.f2256c.d();
        }
    }

    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f2259a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final c[] f2260b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public long f2261c;

        public b(int i, ThreadFactory threadFactory) {
            this.f2259a = i;
            this.f2260b = new c[i];
            for (int i2 = 0; i2 < i; i2++) {
                this.f2260b[i2] = new c(threadFactory);
            }
        }

        public c a() {
            int i = this.f2259a;
            if (i == 0) {
                return a.g;
            }
            c[] cVarArr = this.f2260b;
            long j = this.f2261c;
            this.f2261c = 1 + j;
            return cVarArr[(int) (j % ((long) i))];
        }
    }

    public static final class c extends d {
        public c(ThreadFactory threadFactory) {
            super(threadFactory);
        }
    }

    static {
        int iAvailableProcessors = Runtime.getRuntime().availableProcessors();
        int iIntValue = Integer.getInteger("rx2.computation-threads", 0).intValue();
        if (iIntValue > 0 && iIntValue <= iAvailableProcessors) {
            iAvailableProcessors = iIntValue;
        }
        f2251f = iAvailableProcessors;
        c cVar = new c(new e("RxComputationShutdown"));
        g = cVar;
        cVar.d();
        e eVar = new e("RxComputationThreadPool", Math.max(1, Math.min(10, Integer.getInteger("rx2.computation-priority", 5).intValue())), true);
        f2250e = eVar;
        b bVar = new b(0, eVar);
        f2249d = bVar;
        for (c cVar2 : bVar.f2260b) {
            cVar2.d();
        }
    }

    public a() {
        b bVar = new b(f2251f, this.f2252b);
        if (this.f2253c.compareAndSet(f2249d, bVar)) {
            return;
        }
        for (c cVar : bVar.f2260b) {
            cVar.d();
        }
    }

    @Override // c.b.c
    public c.b a() {
        return new C0052a(this.f2253c.get().a());
    }

    @Override // c.b.c
    public c.b.e.b c(Runnable runnable, long j, TimeUnit timeUnit) {
        c cVarA = this.f2253c.get().a();
        if (cVarA == null) {
            throw null;
        }
        c.b.h.b.b.a(runnable, "run is null");
        f fVar = new f(runnable);
        try {
            fVar.a(j <= 0 ? cVarA.f2280a.submit(fVar) : cVarA.f2280a.schedule(fVar, j, timeUnit));
            return fVar;
        } catch (RejectedExecutionException e2) {
            c.b.j.a.c(e2);
            return c.b.h.a.c.INSTANCE;
        }
    }
}
