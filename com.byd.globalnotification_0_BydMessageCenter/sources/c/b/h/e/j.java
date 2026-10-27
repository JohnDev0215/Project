package c.b.h.e;

import c.b.c;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class j extends c.b.c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final j f2304b = new j();

    public static final class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Runnable f2305a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final c f2306b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final long f2307c;

        public a(Runnable runnable, c cVar, long j) {
            this.f2305a = runnable;
            this.f2306b = cVar;
            this.f2307c = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f2306b.f2315d) {
                return;
            }
            long jA = this.f2306b.a(TimeUnit.MILLISECONDS);
            long j = this.f2307c;
            if (j > jA) {
                try {
                    Thread.sleep(j - jA);
                } catch (InterruptedException e2) {
                    Thread.currentThread().interrupt();
                    c.b.j.a.c(e2);
                    return;
                }
            }
            if (this.f2306b.f2315d) {
                return;
            }
            this.f2305a.run();
        }
    }

    public static final class b implements Comparable<b> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Runnable f2308a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final long f2309b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f2310c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public volatile boolean f2311d;

        public b(Runnable runnable, Long l, int i) {
            this.f2308a = runnable;
            this.f2309b = l.longValue();
            this.f2310c = i;
        }

        @Override // java.lang.Comparable
        public int compareTo(b bVar) {
            b bVar2 = bVar;
            long j = this.f2309b;
            long j2 = bVar2.f2309b;
            int i = 1;
            int i2 = j < j2 ? -1 : j > j2 ? 1 : 0;
            if (i2 != 0) {
                return i2;
            }
            int i3 = this.f2310c;
            int i4 = bVar2.f2310c;
            if (i3 < i4) {
                i = -1;
            } else if (i3 <= i4) {
                i = 0;
            }
            return i;
        }
    }

    public static final class c extends c.b implements c.b.e.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final PriorityBlockingQueue<b> f2312a = new PriorityBlockingQueue<>();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final AtomicInteger f2313b = new AtomicInteger();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final AtomicInteger f2314c = new AtomicInteger();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public volatile boolean f2315d;

        public final class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final b f2316a;

            public a(b bVar) {
                this.f2316a = bVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.f2316a.f2311d = true;
                c.this.f2312a.remove(this.f2316a);
            }
        }

        @Override // c.b.c.b
        public c.b.e.b b(Runnable runnable, long j, TimeUnit timeUnit) {
            long millis = timeUnit.toMillis(j) + a(TimeUnit.MILLISECONDS);
            a aVar = new a(runnable, this, millis);
            c.b.h.a.c cVar = c.b.h.a.c.INSTANCE;
            if (this.f2315d) {
                return cVar;
            }
            b bVar = new b(aVar, Long.valueOf(millis), this.f2314c.incrementAndGet());
            this.f2312a.add(bVar);
            if (this.f2313b.getAndIncrement() != 0) {
                a aVar2 = new a(bVar);
                c.b.h.b.b.a(aVar2, "run is null");
                return new c.b.e.c(aVar2);
            }
            int iAddAndGet = 1;
            while (!this.f2315d) {
                b bVarPoll = this.f2312a.poll();
                if (bVarPoll == null) {
                    iAddAndGet = this.f2313b.addAndGet(-iAddAndGet);
                    if (iAddAndGet == 0) {
                        return cVar;
                    }
                } else if (!bVarPoll.f2311d) {
                    bVarPoll.f2308a.run();
                }
            }
            this.f2312a.clear();
            return cVar;
        }

        @Override // c.b.e.b
        public void d() {
            this.f2315d = true;
        }
    }

    @Override // c.b.c
    public c.b a() {
        return new c();
    }

    @Override // c.b.c
    public c.b.e.b b(Runnable runnable) {
        c.b.h.b.b.a(runnable, "run is null");
        runnable.run();
        return c.b.h.a.c.INSTANCE;
    }

    @Override // c.b.c
    public c.b.e.b c(Runnable runnable, long j, TimeUnit timeUnit) {
        try {
            timeUnit.sleep(j);
            c.b.h.b.b.a(runnable, "run is null");
            runnable.run();
        } catch (InterruptedException e2) {
            Thread.currentThread().interrupt();
            c.b.j.a.c(e2);
        }
        return c.b.h.a.c.INSTANCE;
    }
}
