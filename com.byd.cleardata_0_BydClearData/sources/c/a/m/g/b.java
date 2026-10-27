package c.a.m.g;

import c.a.h;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class b extends c.a.h {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final C0060b f1856b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final f f1857c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final int f1858d = d(Runtime.getRuntime().availableProcessors(), Integer.getInteger("rx2.computation-threads", 0).intValue());
    static final c e;
    final ThreadFactory f;
    final AtomicReference<C0060b> g;

    static final class a extends h.b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final c.a.m.a.d f1859b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final c.a.j.a f1860c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final c.a.m.a.d f1861d;
        private final c e;
        volatile boolean f;

        a(c cVar) {
            this.e = cVar;
            c.a.m.a.d dVar = new c.a.m.a.d();
            this.f1859b = dVar;
            c.a.j.a aVar = new c.a.j.a();
            this.f1860c = aVar;
            c.a.m.a.d dVar2 = new c.a.m.a.d();
            this.f1861d = dVar2;
            dVar2.c(dVar);
            dVar2.c(aVar);
        }

        @Override // c.a.h.b
        public c.a.j.b b(Runnable runnable) {
            return this.f ? c.a.m.a.c.INSTANCE : this.e.d(runnable, 0L, TimeUnit.MILLISECONDS, this.f1859b);
        }

        @Override // c.a.h.b
        public c.a.j.b c(Runnable runnable, long j, TimeUnit timeUnit) {
            return this.f ? c.a.m.a.c.INSTANCE : this.e.d(runnable, j, timeUnit, this.f1860c);
        }

        @Override // c.a.j.b
        public void e() {
            if (this.f) {
                return;
            }
            this.f = true;
            this.f1861d.e();
        }

        @Override // c.a.j.b
        public boolean g() {
            return this.f;
        }
    }

    /* JADX INFO: renamed from: c.a.m.g.b$b, reason: collision with other inner class name */
    static final class C0060b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final int f1862a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c[] f1863b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        long f1864c;

        C0060b(int i, ThreadFactory threadFactory) {
            this.f1862a = i;
            this.f1863b = new c[i];
            for (int i2 = 0; i2 < i; i2++) {
                this.f1863b[i2] = new c(threadFactory);
            }
        }

        public c a() {
            int i = this.f1862a;
            if (i == 0) {
                return b.e;
            }
            c[] cVarArr = this.f1863b;
            long j = this.f1864c;
            this.f1864c = 1 + j;
            return cVarArr[(int) (j % ((long) i))];
        }

        public void b() {
            for (c cVar : this.f1863b) {
                cVar.e();
            }
        }
    }

    static final class c extends e {
        c(ThreadFactory threadFactory) {
            super(threadFactory);
        }
    }

    static {
        c cVar = new c(new f("RxComputationShutdown"));
        e = cVar;
        cVar.e();
        f fVar = new f("RxComputationThreadPool", Math.max(1, Math.min(10, Integer.getInteger("rx2.computation-priority", 5).intValue())), true);
        f1857c = fVar;
        C0060b c0060b = new C0060b(0, fVar);
        f1856b = c0060b;
        c0060b.b();
    }

    public b() {
        this(f1857c);
    }

    public b(ThreadFactory threadFactory) {
        this.f = threadFactory;
        this.g = new AtomicReference<>(f1856b);
        e();
    }

    static int d(int i, int i2) {
        return (i2 <= 0 || i2 > i) ? i : i2;
    }

    @Override // c.a.h
    public h.b a() {
        return new a(this.g.get().a());
    }

    @Override // c.a.h
    public c.a.j.b c(Runnable runnable, long j, TimeUnit timeUnit) {
        return this.g.get().a().f(runnable, j, timeUnit);
    }

    public void e() {
        C0060b c0060b = new C0060b(f1858d, this.f);
        if (this.g.compareAndSet(f1856b, c0060b)) {
            return;
        }
        c0060b.b();
    }
}
