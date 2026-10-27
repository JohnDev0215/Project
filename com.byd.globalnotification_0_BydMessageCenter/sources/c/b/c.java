package c.b;

import c.b.h.e.d;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f2202a = Boolean.getBoolean("rx2.scheduler.use-nanotime");

    public static final class a implements c.b.e.b, Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Runnable f2203a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final b f2204b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Thread f2205c;

        public a(Runnable runnable, b bVar) {
            this.f2203a = runnable;
            this.f2204b = bVar;
        }

        @Override // c.b.e.b
        public void d() {
            if (this.f2205c == Thread.currentThread()) {
                b bVar = this.f2204b;
                if (bVar instanceof d) {
                    d dVar = (d) bVar;
                    if (dVar.f2281b) {
                        return;
                    }
                    dVar.f2281b = true;
                    dVar.f2280a.shutdown();
                    return;
                }
            }
            this.f2204b.d();
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f2205c = Thread.currentThread();
            try {
                this.f2203a.run();
            } finally {
                d();
                this.f2205c = null;
            }
        }
    }

    public static abstract class b implements c.b.e.b {
        public long a(TimeUnit timeUnit) {
            long jNanoTime;
            TimeUnit timeUnit2;
            if (c.f2202a) {
                jNanoTime = System.nanoTime();
                timeUnit2 = TimeUnit.NANOSECONDS;
            } else {
                jNanoTime = System.currentTimeMillis();
                timeUnit2 = TimeUnit.MILLISECONDS;
            }
            return timeUnit.convert(jNanoTime, timeUnit2);
        }

        public abstract c.b.e.b b(Runnable runnable, long j, TimeUnit timeUnit);
    }

    static {
        TimeUnit.MINUTES.toNanos(Long.getLong("rx2.scheduler.drift-tolerance", 15L).longValue());
    }

    public abstract b a();

    public c.b.e.b b(Runnable runnable) {
        return c(runnable, 0L, TimeUnit.NANOSECONDS);
    }

    public c.b.e.b c(Runnable runnable, long j, TimeUnit timeUnit) {
        b bVarA = a();
        c.b.h.b.b.a(runnable, "run is null");
        a aVar = new a(runnable, bVarA);
        bVarA.b(aVar, j, timeUnit);
        return aVar;
    }
}
