package d.a;

import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: Scheduler.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final long f4513a = TimeUnit.MINUTES.toNanos(Long.getLong("rx2.scheduler.drift-tolerance", 15).longValue());

    /* JADX INFO: compiled from: Scheduler.java */
    static final class a implements Runnable, io.reactivex.disposables.b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Runnable f4514b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final b f4515c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        Thread f4516d;

        a(Runnable runnable, b bVar) {
            this.f4514b = runnable;
            this.f4515c = bVar;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            if (this.f4516d == Thread.currentThread()) {
                b bVar = this.f4515c;
                if (bVar instanceof io.reactivex.internal.schedulers.e) {
                    ((io.reactivex.internal.schedulers.e) bVar).f();
                    return;
                }
            }
            this.f4515c.dispose();
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.f4515c.isDisposed();
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f4516d = Thread.currentThread();
            try {
                this.f4514b.run();
            } finally {
                dispose();
                this.f4516d = null;
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
        a aVar = new a(d.a.o.a.o(runnable), bVarA);
        bVarA.c(aVar, j, timeUnit);
        return aVar;
    }
}
