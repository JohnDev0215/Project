package e.a;

import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: Scheduler.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final long f4844a = TimeUnit.MINUTES.toNanos(Long.getLong("rx2.scheduler.drift-tolerance", 15).longValue());

    /* JADX INFO: compiled from: Scheduler.java */
    static final class a implements Runnable, e.a.k.b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Runnable f4845b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final b f4846c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        Thread f4847d;

        a(Runnable runnable, b bVar) {
            this.f4845b = runnable;
            this.f4846c = bVar;
        }

        @Override // e.a.k.b
        public void d() {
            if (this.f4847d == Thread.currentThread()) {
                b bVar = this.f4846c;
                if (bVar instanceof e.a.n.g.e) {
                    ((e.a.n.g.e) bVar).g();
                    return;
                }
            }
            this.f4846c.d();
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f4847d = Thread.currentThread();
            try {
                this.f4845b.run();
            } finally {
                d();
                this.f4847d = null;
            }
        }
    }

    /* JADX INFO: compiled from: Scheduler.java */
    public static abstract class b implements e.a.k.b {
        public long a(TimeUnit timeUnit) {
            return timeUnit.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        public e.a.k.b b(Runnable runnable) {
            return c(runnable, 0L, TimeUnit.NANOSECONDS);
        }

        public abstract e.a.k.b c(Runnable runnable, long j, TimeUnit timeUnit);
    }

    public abstract b a();

    public e.a.k.b b(Runnable runnable) {
        return c(runnable, 0L, TimeUnit.NANOSECONDS);
    }

    public e.a.k.b c(Runnable runnable, long j, TimeUnit timeUnit) {
        b bVarA = a();
        a aVar = new a(e.a.p.a.q(runnable), bVarA);
        bVarA.c(aVar, j, timeUnit);
        return aVar;
    }
}
