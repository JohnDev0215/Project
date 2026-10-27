package e.a.n.g;

import e.a.h;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: TrampolineScheduler.java */
/* JADX INFO: loaded from: classes.dex */
public final class k extends e.a.h {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final k f4956b = new k();

    /* JADX INFO: compiled from: TrampolineScheduler.java */
    static final class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Runnable f4957b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final c f4958c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final long f4959d;

        a(Runnable runnable, c cVar, long j) {
            this.f4957b = runnable;
            this.f4958c = cVar;
            this.f4959d = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f4958c.f4967e) {
                return;
            }
            long jA = this.f4958c.a(TimeUnit.MILLISECONDS);
            long j = this.f4959d;
            if (j > jA) {
                long j2 = j - jA;
                if (j2 > 0) {
                    try {
                        Thread.sleep(j2);
                    } catch (InterruptedException e2) {
                        Thread.currentThread().interrupt();
                        e.a.p.a.o(e2);
                        return;
                    }
                }
            }
            if (this.f4958c.f4967e) {
                return;
            }
            this.f4957b.run();
        }
    }

    /* JADX INFO: compiled from: TrampolineScheduler.java */
    static final class b implements Comparable<b> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Runnable f4960b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final long f4961c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final int f4962d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        volatile boolean f4963e;

        b(Runnable runnable, Long l, int i2) {
            this.f4960b = runnable;
            this.f4961c = l.longValue();
            this.f4962d = i2;
        }

        @Override // java.lang.Comparable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compareTo(b bVar) {
            int iB = e.a.n.b.b.b(this.f4961c, bVar.f4961c);
            return iB == 0 ? e.a.n.b.b.a(this.f4962d, bVar.f4962d) : iB;
        }
    }

    /* JADX INFO: compiled from: TrampolineScheduler.java */
    static final class c extends h.b implements e.a.k.b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final PriorityBlockingQueue<b> f4964b = new PriorityBlockingQueue<>();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final AtomicInteger f4965c = new AtomicInteger();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final AtomicInteger f4966d = new AtomicInteger();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        volatile boolean f4967e;

        /* JADX INFO: compiled from: TrampolineScheduler.java */
        final class a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final b f4968b;

            a(b bVar) {
                this.f4968b = bVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.f4968b.f4963e = true;
                c.this.f4964b.remove(this.f4968b);
            }
        }

        c() {
        }

        @Override // e.a.h.b
        public e.a.k.b b(Runnable runnable) {
            return e(runnable, a(TimeUnit.MILLISECONDS));
        }

        @Override // e.a.h.b
        public e.a.k.b c(Runnable runnable, long j, TimeUnit timeUnit) {
            long jA = a(TimeUnit.MILLISECONDS) + timeUnit.toMillis(j);
            return e(new a(runnable, this, jA), jA);
        }

        @Override // e.a.k.b
        public void d() {
            this.f4967e = true;
        }

        e.a.k.b e(Runnable runnable, long j) {
            if (this.f4967e) {
                return e.a.n.a.c.INSTANCE;
            }
            b bVar = new b(runnable, Long.valueOf(j), this.f4966d.incrementAndGet());
            this.f4964b.add(bVar);
            if (this.f4965c.getAndIncrement() != 0) {
                return e.a.k.c.b(new a(bVar));
            }
            int iAddAndGet = 1;
            while (!this.f4967e) {
                b bVarPoll = this.f4964b.poll();
                if (bVarPoll == null) {
                    iAddAndGet = this.f4965c.addAndGet(-iAddAndGet);
                    if (iAddAndGet == 0) {
                        return e.a.n.a.c.INSTANCE;
                    }
                } else if (!bVarPoll.f4963e) {
                    bVarPoll.f4960b.run();
                }
            }
            this.f4964b.clear();
            return e.a.n.a.c.INSTANCE;
        }
    }

    k() {
    }

    public static k d() {
        return f4956b;
    }

    @Override // e.a.h
    public h.b a() {
        return new c();
    }

    @Override // e.a.h
    public e.a.k.b b(Runnable runnable) {
        runnable.run();
        return e.a.n.a.c.INSTANCE;
    }

    @Override // e.a.h
    public e.a.k.b c(Runnable runnable, long j, TimeUnit timeUnit) {
        try {
            timeUnit.sleep(j);
            runnable.run();
        } catch (InterruptedException e2) {
            Thread.currentThread().interrupt();
            e.a.p.a.o(e2);
        }
        return e.a.n.a.c.INSTANCE;
    }
}
