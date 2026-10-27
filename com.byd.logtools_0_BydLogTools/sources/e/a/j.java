package e.a;

import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: Scheduler.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final long f4910a = TimeUnit.MINUTES.toNanos(Long.getLong("rx2.scheduler.drift-tolerance", 15).longValue());

    /* JADX INFO: compiled from: Scheduler.java */
    static final class a implements io.reactivex.disposables.b, Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Runnable f4911b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final b f4912c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        Thread f4913d;

        a(Runnable runnable, b bVar) {
            this.f4911b = runnable;
            this.f4912c = bVar;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            if (this.f4913d == Thread.currentThread()) {
                b bVar = this.f4912c;
                if (bVar instanceof io.reactivex.internal.schedulers.d) {
                    ((io.reactivex.internal.schedulers.d) bVar).f();
                    return;
                }
            }
            this.f4912c.dispose();
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f4913d = Thread.currentThread();
            try {
                this.f4911b.run();
            } finally {
                dispose();
                this.f4913d = null;
            }
        }
    }

    /* JADX INFO: compiled from: Scheduler.java */
    public static abstract class b implements io.reactivex.disposables.b {
        public long a(TimeUnit timeUnit) {
            return timeUnit.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        public io.reactivex.disposables.b b(Runnable runnable) {
            return c(runnable, 0L, TimeUnit.NANOSECONDS);
        }

        public abstract io.reactivex.disposables.b c(Runnable runnable, long j, TimeUnit timeUnit);
    }

    public abstract b a();

    public io.reactivex.disposables.b b(Runnable runnable) {
        return c(runnable, 0L, TimeUnit.NANOSECONDS);
    }

    public io.reactivex.disposables.b c(Runnable runnable, long j, TimeUnit timeUnit) {
        b bVarA = a();
        a aVar = new a(e.a.o.a.m(runnable), bVarA);
        bVarA.c(aVar, j, timeUnit);
        return aVar;
    }
}
