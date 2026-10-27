package c.a.m.g;

import c.a.h;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class k extends c.a.h {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final k f1895b = new k();

    static final class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Runnable f1896b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final c f1897c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final long f1898d;

        a(Runnable runnable, c cVar, long j) {
            this.f1896b = runnable;
            this.f1897c = cVar;
            this.f1898d = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f1897c.e) {
                return;
            }
            long jA = this.f1897c.a(TimeUnit.MILLISECONDS);
            long j = this.f1898d;
            if (j > jA) {
                long j2 = j - jA;
                if (j2 > 0) {
                    try {
                        Thread.sleep(j2);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        c.a.n.a.l(e);
                        return;
                    }
                }
            }
            if (this.f1897c.e) {
                return;
            }
            this.f1896b.run();
        }
    }

    static final class b implements Comparable<b> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Runnable f1899b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final long f1900c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final int f1901d;
        volatile boolean e;

        b(Runnable runnable, Long l, int i) {
            this.f1899b = runnable;
            this.f1900c = l.longValue();
            this.f1901d = i;
        }

        @Override // java.lang.Comparable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compareTo(b bVar) {
            int iB = c.a.m.b.b.b(this.f1900c, bVar.f1900c);
            return iB == 0 ? c.a.m.b.b.a(this.f1901d, bVar.f1901d) : iB;
        }
    }

    static final class c extends h.b implements c.a.j.b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final PriorityBlockingQueue<b> f1902b = new PriorityBlockingQueue<>();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final AtomicInteger f1903c = new AtomicInteger();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final AtomicInteger f1904d = new AtomicInteger();
        volatile boolean e;

        final class a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final b f1905b;

            a(b bVar) {
                this.f1905b = bVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.f1905b.e = true;
                c.this.f1902b.remove(this.f1905b);
            }
        }

        c() {
        }

        @Override // c.a.h.b
        public c.a.j.b b(Runnable runnable) {
            return d(runnable, a(TimeUnit.MILLISECONDS));
        }

        @Override // c.a.h.b
        public c.a.j.b c(Runnable runnable, long j, TimeUnit timeUnit) {
            long jA = a(TimeUnit.MILLISECONDS) + timeUnit.toMillis(j);
            return d(new a(runnable, this, jA), jA);
        }

        c.a.j.b d(Runnable runnable, long j) {
            if (this.e) {
                return c.a.m.a.c.INSTANCE;
            }
            b bVar = new b(runnable, Long.valueOf(j), this.f1904d.incrementAndGet());
            this.f1902b.add(bVar);
            if (this.f1903c.getAndIncrement() != 0) {
                return c.a.j.c.b(new a(bVar));
            }
            int iAddAndGet = 1;
            while (!this.e) {
                b bVarPoll = this.f1902b.poll();
                if (bVarPoll == null) {
                    iAddAndGet = this.f1903c.addAndGet(-iAddAndGet);
                    if (iAddAndGet == 0) {
                        return c.a.m.a.c.INSTANCE;
                    }
                } else if (!bVarPoll.e) {
                    bVarPoll.f1899b.run();
                }
            }
            this.f1902b.clear();
            return c.a.m.a.c.INSTANCE;
        }

        @Override // c.a.j.b
        public void e() {
            this.e = true;
        }

        @Override // c.a.j.b
        public boolean g() {
            return this.e;
        }
    }

    k() {
    }

    public static k d() {
        return f1895b;
    }

    @Override // c.a.h
    public h.b a() {
        return new c();
    }

    @Override // c.a.h
    public c.a.j.b b(Runnable runnable) {
        runnable.run();
        return c.a.m.a.c.INSTANCE;
    }

    @Override // c.a.h
    public c.a.j.b c(Runnable runnable, long j, TimeUnit timeUnit) {
        try {
            timeUnit.sleep(j);
            runnable.run();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            c.a.n.a.l(e);
        }
        return c.a.m.a.c.INSTANCE;
    }
}
