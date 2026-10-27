package c.a;

import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public abstract class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final long f1795a = TimeUnit.MINUTES.toNanos(Long.getLong("rx2.scheduler.drift-tolerance", 15).longValue());

    static final class a implements Runnable, c.a.j.b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Runnable f1796b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final b f1797c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        Thread f1798d;

        a(Runnable runnable, b bVar) {
            this.f1796b = runnable;
            this.f1797c = bVar;
        }

        @Override // c.a.j.b
        public void e() {
            if (this.f1798d == Thread.currentThread()) {
                b bVar = this.f1797c;
                if (bVar instanceof c.a.m.g.e) {
                    ((c.a.m.g.e) bVar).h();
                    return;
                }
            }
            this.f1797c.e();
        }

        @Override // c.a.j.b
        public boolean g() {
            return this.f1797c.g();
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f1798d = Thread.currentThread();
            try {
                this.f1796b.run();
            } finally {
                e();
                this.f1798d = null;
            }
        }
    }

    public static abstract class b implements c.a.j.b {
        public long a(TimeUnit timeUnit) {
            return timeUnit.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        public c.a.j.b b(Runnable runnable) {
            return c(runnable, 0L, TimeUnit.NANOSECONDS);
        }

        public abstract c.a.j.b c(Runnable runnable, long j, TimeUnit timeUnit);
    }

    public abstract b a();

    public c.a.j.b b(Runnable runnable) {
        return c(runnable, 0L, TimeUnit.NANOSECONDS);
    }

    public c.a.j.b c(Runnable runnable, long j, TimeUnit timeUnit) {
        b bVarA = a();
        a aVar = new a(c.a.n.a.o(runnable), bVarA);
        bVarA.c(aVar, j, timeUnit);
        return aVar;
    }
}
