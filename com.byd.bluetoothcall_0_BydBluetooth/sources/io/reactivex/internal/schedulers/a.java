package io.reactivex.internal.schedulers;

import d.a.j;
import io.reactivex.internal.disposables.EmptyDisposable;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ComputationScheduler.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final b f4301b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final RxThreadFactory f4302c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final int f4303d = d(Runtime.getRuntime().availableProcessors(), Integer.getInteger("rx2.computation-threads", 0).intValue());

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static final c f4304e;
    final ThreadFactory f;
    final AtomicReference<b> g;

    /* JADX INFO: renamed from: io.reactivex.internal.schedulers.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ComputationScheduler.java */
    static final class C0111a extends j.b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final io.reactivex.internal.disposables.b f4305b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final io.reactivex.disposables.a f4306c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final io.reactivex.internal.disposables.b f4307d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final c f4308e;
        volatile boolean f;

        C0111a(c cVar) {
            this.f4308e = cVar;
            io.reactivex.internal.disposables.b bVar = new io.reactivex.internal.disposables.b();
            this.f4305b = bVar;
            io.reactivex.disposables.a aVar = new io.reactivex.disposables.a();
            this.f4306c = aVar;
            io.reactivex.internal.disposables.b bVar2 = new io.reactivex.internal.disposables.b();
            this.f4307d = bVar2;
            bVar2.c(bVar);
            bVar2.c(aVar);
        }

        @Override // d.a.j.b
        public io.reactivex.disposables.b b(Runnable runnable) {
            return this.f ? EmptyDisposable.INSTANCE : this.f4308e.d(runnable, 0L, TimeUnit.MILLISECONDS, this.f4305b);
        }

        @Override // d.a.j.b
        public io.reactivex.disposables.b c(Runnable runnable, long j, TimeUnit timeUnit) {
            return this.f ? EmptyDisposable.INSTANCE : this.f4308e.d(runnable, j, timeUnit, this.f4306c);
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            if (this.f) {
                return;
            }
            this.f = true;
            this.f4307d.dispose();
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.f;
        }
    }

    /* JADX INFO: compiled from: ComputationScheduler.java */
    static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final int f4309a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c[] f4310b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        long f4311c;

        b(int i, ThreadFactory threadFactory) {
            this.f4309a = i;
            this.f4310b = new c[i];
            for (int i2 = 0; i2 < i; i2++) {
                this.f4310b[i2] = new c(threadFactory);
            }
        }

        public c a() {
            int i = this.f4309a;
            if (i == 0) {
                return a.f4304e;
            }
            c[] cVarArr = this.f4310b;
            long j = this.f4311c;
            this.f4311c = 1 + j;
            return cVarArr[(int) (j % ((long) i))];
        }

        public void b() {
            for (c cVar : this.f4310b) {
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
        f4304e = cVar;
        cVar.dispose();
        RxThreadFactory rxThreadFactory = new RxThreadFactory("RxComputationThreadPool", Math.max(1, Math.min(10, Integer.getInteger("rx2.computation-priority", 5).intValue())), true);
        f4302c = rxThreadFactory;
        b bVar = new b(0, rxThreadFactory);
        f4301b = bVar;
        bVar.b();
    }

    public a() {
        this(f4302c);
    }

    static int d(int i, int i2) {
        return (i2 <= 0 || i2 > i) ? i : i2;
    }

    @Override // d.a.j
    public j.b a() {
        return new C0111a(this.g.get().a());
    }

    @Override // d.a.j
    public io.reactivex.disposables.b c(Runnable runnable, long j, TimeUnit timeUnit) {
        return this.g.get().a().e(runnable, j, timeUnit);
    }

    public void e() {
        b bVar = new b(f4303d, this.f);
        if (this.g.compareAndSet(f4301b, bVar)) {
            return;
        }
        bVar.b();
    }

    public a(ThreadFactory threadFactory) {
        this.f = threadFactory;
        this.g = new AtomicReference<>(f4301b);
        e();
    }
}
