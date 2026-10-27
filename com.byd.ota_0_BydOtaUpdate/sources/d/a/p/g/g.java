package d.a.p.g;

import d.a.j;
import io.reactivex.internal.disposables.EmptyDisposable;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: TrampolineScheduler.java */
/* JADX INFO: loaded from: classes.dex */
public final class g extends j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final g f3245b = new g();

    /* JADX INFO: compiled from: TrampolineScheduler.java */
    public static final class a implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Runnable f3246c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final c f3247d;
        public final long s;

        public a(Runnable runnable, c cVar, long j) {
            this.f3246c = runnable;
            this.f3247d = cVar;
            this.s = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f3247d.x) {
                return;
            }
            long jA = this.f3247d.a(TimeUnit.MILLISECONDS);
            long j = this.s;
            if (j > jA) {
                long j2 = j - jA;
                if (j2 > 0) {
                    try {
                        Thread.sleep(j2);
                    } catch (InterruptedException e2) {
                        Thread.currentThread().interrupt();
                        d.a.r.a.o(e2);
                        return;
                    }
                }
            }
            if (this.f3247d.x) {
                return;
            }
            this.f3246c.run();
        }
    }

    /* JADX INFO: compiled from: TrampolineScheduler.java */
    public static final class b implements Comparable<b> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Runnable f3248c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final long f3249d;
        public final int s;
        public volatile boolean x;

        public b(Runnable runnable, Long l, int i2) {
            this.f3248c = runnable;
            this.f3249d = l.longValue();
            this.s = i2;
        }

        @Override // java.lang.Comparable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compareTo(b bVar) {
            int iB = d.a.p.b.b.b(this.f3249d, bVar.f3249d);
            return iB == 0 ? d.a.p.b.b.a(this.s, bVar.s) : iB;
        }
    }

    /* JADX INFO: compiled from: TrampolineScheduler.java */
    public static final class c extends j.b implements d.a.m.b {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final PriorityBlockingQueue<b> f3250c = new PriorityBlockingQueue<>();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final AtomicInteger f3251d = new AtomicInteger();
        public final AtomicInteger s = new AtomicInteger();
        public volatile boolean x;

        /* JADX INFO: compiled from: TrampolineScheduler.java */
        public class a implements Runnable {

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public final /* synthetic */ b f3252c;

            public a(b bVar) {
                this.f3252c = bVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.f3252c.x = true;
                c.this.f3250c.remove(this.f3252c);
            }
        }

        @Override // d.a.j.b
        public d.a.m.b b(Runnable runnable) {
            return d(runnable, a(TimeUnit.MILLISECONDS));
        }

        @Override // d.a.j.b
        public d.a.m.b c(Runnable runnable, long j, TimeUnit timeUnit) {
            long jA = a(TimeUnit.MILLISECONDS) + timeUnit.toMillis(j);
            return d(new a(runnable, this, jA), jA);
        }

        public d.a.m.b d(Runnable runnable, long j) {
            if (this.x) {
                return EmptyDisposable.INSTANCE;
            }
            b bVar = new b(runnable, Long.valueOf(j), this.s.incrementAndGet());
            this.f3250c.add(bVar);
            if (this.f3251d.getAndIncrement() != 0) {
                return d.a.m.c.c(new a(bVar));
            }
            int iAddAndGet = 1;
            while (true) {
                b bVarPoll = this.f3250c.poll();
                if (bVarPoll == null) {
                    iAddAndGet = this.f3251d.addAndGet(-iAddAndGet);
                    if (iAddAndGet == 0) {
                        return EmptyDisposable.INSTANCE;
                    }
                } else if (!bVarPoll.x) {
                    bVarPoll.f3248c.run();
                }
            }
        }

        @Override // d.a.m.b
        public void dispose() {
            this.x = true;
        }

        @Override // d.a.m.b
        public boolean isDisposed() {
            return this.x;
        }
    }

    public static g d() {
        return f3245b;
    }

    @Override // d.a.j
    public j.b a() {
        return new c();
    }

    @Override // d.a.j
    public d.a.m.b b(Runnable runnable) {
        runnable.run();
        return EmptyDisposable.INSTANCE;
    }

    @Override // d.a.j
    public d.a.m.b c(Runnable runnable, long j, TimeUnit timeUnit) {
        try {
            timeUnit.sleep(j);
            runnable.run();
        } catch (InterruptedException e2) {
            Thread.currentThread().interrupt();
            d.a.r.a.o(e2);
        }
        return EmptyDisposable.INSTANCE;
    }
}
