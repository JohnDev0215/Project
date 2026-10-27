package a5;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import q4.d;

/* JADX INFO: compiled from: TrampolineScheduler.java */
/* JADX INFO: loaded from: classes.dex */
public final class m extends q4.d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final m f115b = new m();

    /* JADX INFO: compiled from: TrampolineScheduler.java */
    static final class a implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final Runnable f116e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private final c f117g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private final long f118h;

        a(Runnable runnable, c cVar, long j7) {
            this.f116e = runnable;
            this.f117g = cVar;
            this.f118h = j7;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f117g.f126i) {
                return;
            }
            long jA = this.f117g.a(TimeUnit.MILLISECONDS);
            long j7 = this.f118h;
            if (j7 > jA) {
                try {
                    Thread.sleep(j7 - jA);
                } catch (InterruptedException e7) {
                    Thread.currentThread().interrupt();
                    d5.a.k(e7);
                    return;
                }
            }
            if (this.f117g.f126i) {
                return;
            }
            this.f116e.run();
        }
    }

    /* JADX INFO: compiled from: TrampolineScheduler.java */
    static final class b implements Comparable<b> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final Runnable f119e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final long f120g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final int f121h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        volatile boolean f122i;

        b(Runnable runnable, Long l7, int i7) {
            this.f119e = runnable;
            this.f120g = l7.longValue();
            this.f121h = i7;
        }

        @Override // java.lang.Comparable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compareTo(b bVar) {
            int iB = x4.b.b(this.f120g, bVar.f120g);
            return iB == 0 ? x4.b.a(this.f121h, bVar.f121h) : iB;
        }
    }

    /* JADX INFO: compiled from: TrampolineScheduler.java */
    static final class c extends d.b {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final PriorityBlockingQueue<b> f123e = new PriorityBlockingQueue<>();

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private final AtomicInteger f124g = new AtomicInteger();

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final AtomicInteger f125h = new AtomicInteger();

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        volatile boolean f126i;

        /* JADX INFO: compiled from: TrampolineScheduler.java */
        final class a implements Runnable {

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            final b f127e;

            a(b bVar) {
                this.f127e = bVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.f127e.f122i = true;
                c.this.f123e.remove(this.f127e);
            }
        }

        c() {
        }

        @Override // q4.d.b
        public t4.b b(Runnable runnable, long j7, TimeUnit timeUnit) {
            long jA = a(TimeUnit.MILLISECONDS) + timeUnit.toMillis(j7);
            return c(new a(runnable, this, jA), jA);
        }

        t4.b c(Runnable runnable, long j7) {
            if (this.f126i) {
                return w4.c.INSTANCE;
            }
            b bVar = new b(runnable, Long.valueOf(j7), this.f125h.incrementAndGet());
            this.f123e.add(bVar);
            if (this.f124g.getAndIncrement() != 0) {
                return t4.c.b(new a(bVar));
            }
            int iAddAndGet = 1;
            while (!this.f126i) {
                b bVarPoll = this.f123e.poll();
                if (bVarPoll == null) {
                    iAddAndGet = this.f124g.addAndGet(-iAddAndGet);
                    if (iAddAndGet == 0) {
                        return w4.c.INSTANCE;
                    }
                } else if (!bVarPoll.f122i) {
                    bVarPoll.f119e.run();
                }
            }
            this.f123e.clear();
            return w4.c.INSTANCE;
        }

        @Override // t4.b
        public void dispose() {
            this.f126i = true;
        }

        @Override // t4.b
        public boolean isDisposed() {
            return this.f126i;
        }
    }

    m() {
    }

    public static m d() {
        return f115b;
    }

    @Override // q4.d
    public d.b a() {
        return new c();
    }

    @Override // q4.d
    public t4.b b(Runnable runnable) {
        d5.a.m(runnable).run();
        return w4.c.INSTANCE;
    }

    @Override // q4.d
    public t4.b c(Runnable runnable, long j7, TimeUnit timeUnit) {
        try {
            timeUnit.sleep(j7);
            d5.a.m(runnable).run();
        } catch (InterruptedException e7) {
            Thread.currentThread().interrupt();
            d5.a.k(e7);
        }
        return w4.c.INSTANCE;
    }
}
