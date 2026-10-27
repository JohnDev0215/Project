package d.a.p.g;

import d.a.j;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.schedulers.RxThreadFactory;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ComputationScheduler.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends j {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final RxThreadFactory f3213c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final c f3215e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final AtomicReference<b> f3216f = new AtomicReference<>(f3212b);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final b f3212b = new b(0);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int f3214d = d(Runtime.getRuntime().availableProcessors(), Integer.getInteger("rx2.computation-threads", 0).intValue());

    /* JADX INFO: renamed from: d.a.p.g.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ComputationScheduler.java */
    public static final class C0075a extends j.b {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final d.a.p.a.b f3217c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final d.a.m.a f3218d;
        public final d.a.p.a.b s;
        public final c x;
        public volatile boolean y;

        public C0075a(c cVar) {
            this.x = cVar;
            d.a.p.a.b bVar = new d.a.p.a.b();
            this.f3217c = bVar;
            d.a.m.a aVar = new d.a.m.a();
            this.f3218d = aVar;
            d.a.p.a.b bVar2 = new d.a.p.a.b();
            this.s = bVar2;
            bVar2.c(bVar);
            bVar2.c(aVar);
        }

        @Override // d.a.j.b
        public d.a.m.b b(Runnable runnable) {
            return this.y ? EmptyDisposable.INSTANCE : this.x.d(runnable, 0L, null, this.f3217c);
        }

        @Override // d.a.j.b
        public d.a.m.b c(Runnable runnable, long j, TimeUnit timeUnit) {
            return this.y ? EmptyDisposable.INSTANCE : this.x.d(runnable, j, timeUnit, this.f3218d);
        }

        @Override // d.a.m.b
        public void dispose() {
            if (this.y) {
                return;
            }
            this.y = true;
            this.s.dispose();
        }

        @Override // d.a.m.b
        public boolean isDisposed() {
            return this.y;
        }
    }

    /* JADX INFO: compiled from: ComputationScheduler.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f3219a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final c[] f3220b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public long f3221c;

        public b(int i2) {
            this.f3219a = i2;
            this.f3220b = new c[i2];
            for (int i3 = 0; i3 < i2; i3++) {
                this.f3220b[i3] = new c(a.f3213c);
            }
        }

        public c a() {
            int i2 = this.f3219a;
            if (i2 == 0) {
                return a.f3215e;
            }
            c[] cVarArr = this.f3220b;
            long j = this.f3221c;
            this.f3221c = 1 + j;
            return cVarArr[(int) (j % ((long) i2))];
        }

        public void b() {
            for (c cVar : this.f3220b) {
                cVar.dispose();
            }
        }
    }

    /* JADX INFO: compiled from: ComputationScheduler.java */
    public static final class c extends d {
        public c(ThreadFactory threadFactory) {
            super(threadFactory);
        }
    }

    static {
        c cVar = new c(new RxThreadFactory("RxComputationShutdown"));
        f3215e = cVar;
        cVar.dispose();
        f3213c = new RxThreadFactory("RxComputationThreadPool", Math.max(1, Math.min(10, Integer.getInteger("rx2.computation-priority", 5).intValue())));
    }

    public a() {
        e();
    }

    public static int d(int i2, int i3) {
        return (i3 <= 0 || i3 > i2) ? i2 : i3;
    }

    @Override // d.a.j
    public j.b a() {
        return new C0075a(this.f3216f.get().a());
    }

    @Override // d.a.j
    public d.a.m.b c(Runnable runnable, long j, TimeUnit timeUnit) {
        return this.f3216f.get().a().e(runnable, j, timeUnit);
    }

    public void e() {
        b bVar = new b(f3214d);
        if (this.f3216f.compareAndSet(f3212b, bVar)) {
            return;
        }
        bVar.b();
    }
}
