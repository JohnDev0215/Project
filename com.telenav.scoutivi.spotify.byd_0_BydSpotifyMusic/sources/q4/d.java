package q4;

import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: Scheduler.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final long f9165a = TimeUnit.MINUTES.toNanos(Long.getLong("rx2.scheduler.drift-tolerance", 15).longValue());

    /* JADX INFO: compiled from: Scheduler.java */
    static final class a implements t4.b, Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final Runnable f9166e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final b f9167g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        Thread f9168h;

        a(Runnable runnable, b bVar) {
            this.f9166e = runnable;
            this.f9167g = bVar;
        }

        @Override // t4.b
        public void dispose() {
            if (this.f9168h == Thread.currentThread()) {
                b bVar = this.f9167g;
                if (bVar instanceof a5.g) {
                    ((a5.g) bVar).e();
                    return;
                }
            }
            this.f9167g.dispose();
        }

        @Override // t4.b
        public boolean isDisposed() {
            return this.f9167g.isDisposed();
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f9168h = Thread.currentThread();
            try {
                this.f9166e.run();
            } finally {
                dispose();
                this.f9168h = null;
            }
        }
    }

    /* JADX INFO: compiled from: Scheduler.java */
    public static abstract class b implements t4.b {
        public long a(TimeUnit timeUnit) {
            return timeUnit.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        public abstract t4.b b(Runnable runnable, long j7, TimeUnit timeUnit);
    }

    public abstract b a();

    public t4.b b(Runnable runnable) {
        return c(runnable, 0L, TimeUnit.NANOSECONDS);
    }

    public t4.b c(Runnable runnable, long j7, TimeUnit timeUnit) {
        b bVarA = a();
        a aVar = new a(d5.a.m(runnable), bVarA);
        bVarA.b(aVar, j7, timeUnit);
        return aVar;
    }
}
