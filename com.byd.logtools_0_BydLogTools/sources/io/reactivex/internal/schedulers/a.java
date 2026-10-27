package io.reactivex.internal.schedulers;

import e.a.j;
import io.reactivex.internal.disposables.EmptyDisposable;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ComputationScheduler.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final b f5022b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final RxThreadFactory f5023c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final int f5024d = d(Runtime.getRuntime().availableProcessors(), Integer.getInteger("rx2.computation-threads", 0).intValue());

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static final c f5025e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final ThreadFactory f5026f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final AtomicReference<b> f5027g;

    /* JADX INFO: renamed from: io.reactivex.internal.schedulers.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ComputationScheduler.java */
    static final class C0091a extends j.b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final io.reactivex.internal.disposables.b f5028b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final io.reactivex.disposables.a f5029c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final io.reactivex.internal.disposables.b f5030d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final c f5031e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        volatile boolean f5032f;

        C0091a(c cVar) {
            this.f5031e = cVar;
            io.reactivex.internal.disposables.b bVar = new io.reactivex.internal.disposables.b();
            this.f5028b = bVar;
            io.reactivex.disposables.a aVar = new io.reactivex.disposables.a();
            this.f5029c = aVar;
            io.reactivex.internal.disposables.b bVar2 = new io.reactivex.internal.disposables.b();
            this.f5030d = bVar2;
            bVar2.c(bVar);
            bVar2.c(aVar);
        }

        @Override // e.a.j.b
        public io.reactivex.disposables.b b(Runnable runnable) {
            return this.f5032f ? EmptyDisposable.INSTANCE : this.f5031e.d(runnable, 0L, TimeUnit.MILLISECONDS, this.f5028b);
        }

        @Override // e.a.j.b
        public io.reactivex.disposables.b c(Runnable runnable, long j, TimeUnit timeUnit) {
            return this.f5032f ? EmptyDisposable.INSTANCE : this.f5031e.d(runnable, j, timeUnit, this.f5029c);
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            if (this.f5032f) {
                return;
            }
            this.f5032f = true;
            this.f5030d.dispose();
        }
    }

    /* JADX INFO: compiled from: ComputationScheduler.java */
    static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final int f5033a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c[] f5034b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        long f5035c;

        b(int i, ThreadFactory threadFactory) {
            this.f5033a = i;
            this.f5034b = new c[i];
            for (int i2 = 0; i2 < i; i2++) {
                this.f5034b[i2] = new c(threadFactory);
            }
        }

        public c a() {
            int i = this.f5033a;
            if (i == 0) {
                return a.f5025e;
            }
            c[] cVarArr = this.f5034b;
            long j = this.f5035c;
            this.f5035c = 1 + j;
            return cVarArr[(int) (j % ((long) i))];
        }

        public void b() {
            for (c cVar : this.f5034b) {
                cVar.dispose();
            }
        }
    }

    /* JADX INFO: compiled from: ComputationScheduler.java */
    static final class c extends d {
        c(ThreadFactory threadFactory) {
            super(threadFactory);
        }
    }

    static {
        c cVar = new c(new RxThreadFactory("RxComputationShutdown"));
        f5025e = cVar;
        cVar.dispose();
        RxThreadFactory rxThreadFactory = new RxThreadFactory("RxComputationThreadPool", Math.max(1, Math.min(10, Integer.getInteger("rx2.computation-priority", 5).intValue())), true);
        f5023c = rxThreadFactory;
        b bVar = new b(0, rxThreadFactory);
        f5022b = bVar;
        bVar.b();
    }

    public a() {
        this(f5023c);
    }

    static int d(int i, int i2) {
        return (i2 <= 0 || i2 > i) ? i : i2;
    }

    @Override // e.a.j
    public j.b a() {
        return new C0091a(this.f5027g.get().a());
    }

    @Override // e.a.j
    public io.reactivex.disposables.b c(Runnable runnable, long j, TimeUnit timeUnit) {
        return this.f5027g.get().a().e(runnable, j, timeUnit);
    }

    public void e() {
        b bVar = new b(f5024d, this.f5026f);
        if (this.f5027g.compareAndSet(f5022b, bVar)) {
            return;
        }
        bVar.b();
    }

    public a(ThreadFactory threadFactory) {
        this.f5026f = threadFactory;
        this.f5027g = new AtomicReference<>(f5022b);
        e();
    }
}
