package a5;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import q4.d;

/* JADX INFO: compiled from: ComputationScheduler.java */
/* JADX INFO: loaded from: classes.dex */
public final class b extends q4.d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final C0001b f36d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static final h f37e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    static final int f38f = d(Runtime.getRuntime().availableProcessors(), Integer.getInteger("rx2.computation-threads", 0).intValue());

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    static final c f39g;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final ThreadFactory f40b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final AtomicReference<C0001b> f41c;

    /* JADX INFO: compiled from: ComputationScheduler.java */
    static final class a extends d.b {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final w4.d f42e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private final t4.a f43g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private final w4.d f44h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private final c f45i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        volatile boolean f46j;

        a(c cVar) {
            this.f45i = cVar;
            w4.d dVar = new w4.d();
            this.f42e = dVar;
            t4.a aVar = new t4.a();
            this.f43g = aVar;
            w4.d dVar2 = new w4.d();
            this.f44h = dVar2;
            dVar2.a(dVar);
            dVar2.a(aVar);
        }

        @Override // q4.d.b
        public t4.b b(Runnable runnable, long j7, TimeUnit timeUnit) {
            return this.f46j ? w4.c.INSTANCE : this.f45i.c(runnable, j7, timeUnit, this.f43g);
        }

        @Override // t4.b
        public void dispose() {
            if (this.f46j) {
                return;
            }
            this.f46j = true;
            this.f44h.dispose();
        }

        @Override // t4.b
        public boolean isDisposed() {
            return this.f46j;
        }
    }

    /* JADX INFO: renamed from: a5.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ComputationScheduler.java */
    static final class C0001b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final int f47a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c[] f48b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        long f49c;

        C0001b(int i7, ThreadFactory threadFactory) {
            this.f47a = i7;
            this.f48b = new c[i7];
            for (int i8 = 0; i8 < i7; i8++) {
                this.f48b[i8] = new c(threadFactory);
            }
        }

        public c a() {
            int i7 = this.f47a;
            if (i7 == 0) {
                return b.f39g;
            }
            c[] cVarArr = this.f48b;
            long j7 = this.f49c;
            this.f49c = 1 + j7;
            return cVarArr[(int) (j7 % ((long) i7))];
        }

        public void b() {
            for (c cVar : this.f48b) {
                cVar.dispose();
            }
        }
    }

    /* JADX INFO: compiled from: ComputationScheduler.java */
    static final class c extends g {
        c(ThreadFactory threadFactory) {
            super(threadFactory);
        }
    }

    static {
        c cVar = new c(new h("RxComputationShutdown"));
        f39g = cVar;
        cVar.dispose();
        h hVar = new h("RxComputationThreadPool", Math.max(1, Math.min(10, Integer.getInteger("rx2.computation-priority", 5).intValue())), true);
        f37e = hVar;
        C0001b c0001b = new C0001b(0, hVar);
        f36d = c0001b;
        c0001b.b();
    }

    public b() {
        this(f37e);
    }

    static int d(int i7, int i8) {
        return (i8 <= 0 || i8 > i7) ? i7 : i8;
    }

    @Override // q4.d
    public d.b a() {
        return new a(this.f41c.get().a());
    }

    @Override // q4.d
    public t4.b c(Runnable runnable, long j7, TimeUnit timeUnit) {
        return this.f41c.get().a().d(runnable, j7, timeUnit);
    }

    public void e() {
        C0001b c0001b = new C0001b(f38f, this.f40b);
        if (this.f41c.compareAndSet(f36d, c0001b)) {
            return;
        }
        c0001b.b();
    }

    public b(ThreadFactory threadFactory) {
        this.f40b = threadFactory;
        this.f41c = new AtomicReference<>(f36d);
        e();
    }
}
