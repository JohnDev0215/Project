package d.a;

import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: Scheduler.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final long f3163a = TimeUnit.MINUTES.toNanos(Long.getLong("rx2.scheduler.drift-tolerance", 15).longValue());

    /* JADX INFO: compiled from: Scheduler.java */
    public class a implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Runnable f3164c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ b f3165d;

        public a(Runnable runnable, b bVar) {
            this.f3164c = runnable;
            this.f3165d = bVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f3164c.run();
            } finally {
                this.f3165d.dispose();
            }
        }
    }

    /* JADX INFO: compiled from: Scheduler.java */
    public static abstract class b implements d.a.m.b {
        public long a(TimeUnit timeUnit) {
            return timeUnit.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        public d.a.m.b b(Runnable runnable) {
            return c(runnable, 0L, TimeUnit.NANOSECONDS);
        }

        public abstract d.a.m.b c(Runnable runnable, long j, TimeUnit timeUnit);
    }

    public abstract b a();

    public d.a.m.b b(Runnable runnable) {
        return c(runnable, 0L, TimeUnit.NANOSECONDS);
    }

    public d.a.m.b c(Runnable runnable, long j, TimeUnit timeUnit) {
        b bVarA = a();
        bVarA.c(new a(d.a.r.a.q(runnable), bVarA), j, timeUnit);
        return bVarA;
    }
}
