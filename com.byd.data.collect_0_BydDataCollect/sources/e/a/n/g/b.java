package e.a.n.g;

import e.a.h;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ComputationScheduler.java */
/* JADX INFO: loaded from: classes.dex */
public final class b extends e.a.h {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final C0095b f4907b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final f f4908c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final int f4909d = d(Runtime.getRuntime().availableProcessors(), Integer.getInteger("rx2.computation-threads", 0).intValue());

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static final c f4910e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final ThreadFactory f4911f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final AtomicReference<C0095b> f4912g;

    /* JADX INFO: compiled from: ComputationScheduler.java */
    static final class a extends h.b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final e.a.n.a.d f4913b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final e.a.k.a f4914c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final e.a.n.a.d f4915d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final c f4916e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        volatile boolean f4917f;

        a(c cVar) {
            this.f4916e = cVar;
            e.a.n.a.d dVar = new e.a.n.a.d();
            this.f4913b = dVar;
            e.a.k.a aVar = new e.a.k.a();
            this.f4914c = aVar;
            e.a.n.a.d dVar2 = new e.a.n.a.d();
            this.f4915d = dVar2;
            dVar2.c(dVar);
            dVar2.c(aVar);
        }

        @Override // e.a.h.b
        public e.a.k.b b(Runnable runnable) {
            return this.f4917f ? e.a.n.a.c.INSTANCE : this.f4916e.e(runnable, 0L, TimeUnit.MILLISECONDS, this.f4913b);
        }

        @Override // e.a.h.b
        public e.a.k.b c(Runnable runnable, long j, TimeUnit timeUnit) {
            return this.f4917f ? e.a.n.a.c.INSTANCE : this.f4916e.e(runnable, j, timeUnit, this.f4914c);
        }

        @Override // e.a.k.b
        public void d() {
            if (this.f4917f) {
                return;
            }
            this.f4917f = true;
            this.f4915d.d();
        }
    }

    /* JADX INFO: renamed from: e.a.n.g.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ComputationScheduler.java */
    static final class C0095b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final int f4918a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c[] f4919b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        long f4920c;

        C0095b(int i2, ThreadFactory threadFactory) {
            this.f4918a = i2;
            this.f4919b = new c[i2];
            for (int i3 = 0; i3 < i2; i3++) {
                this.f4919b[i3] = new c(threadFactory);
            }
        }

        public c a() {
            int i2 = this.f4918a;
            if (i2 == 0) {
                return b.f4910e;
            }
            c[] cVarArr = this.f4919b;
            long j = this.f4920c;
            this.f4920c = 1 + j;
            return cVarArr[(int) (j % ((long) i2))];
        }

        public void b() {
            for (c cVar : this.f4919b) {
                cVar.d();
            }
        }
    }

    /* JADX INFO: compiled from: ComputationScheduler.java */
    static final class c extends e {
        c(ThreadFactory threadFactory) {
            super(threadFactory);
        }
    }

    static {
        c cVar = new c(new f("RxComputationShutdown"));
        f4910e = cVar;
        cVar.d();
        f fVar = new f("RxComputationThreadPool", Math.max(1, Math.min(10, Integer.getInteger("rx2.computation-priority", 5).intValue())), true);
        f4908c = fVar;
        C0095b c0095b = new C0095b(0, fVar);
        f4907b = c0095b;
        c0095b.b();
    }

    public b() {
        this(f4908c);
    }

    static int d(int i2, int i3) {
        return (i3 <= 0 || i3 > i2) ? i2 : i3;
    }

    @Override // e.a.h
    public h.b a() {
        return new a(this.f4912g.get().a());
    }

    @Override // e.a.h
    public e.a.k.b c(Runnable runnable, long j, TimeUnit timeUnit) {
        return this.f4912g.get().a().f(runnable, j, timeUnit);
    }

    public void e() {
        C0095b c0095b = new C0095b(f4909d, this.f4911f);
        if (this.f4912g.compareAndSet(f4907b, c0095b)) {
            return;
        }
        c0095b.b();
    }

    public b(ThreadFactory threadFactory) {
        this.f4911f = threadFactory;
        this.f4912g = new AtomicReference<>(f4907b);
        e();
    }
}
