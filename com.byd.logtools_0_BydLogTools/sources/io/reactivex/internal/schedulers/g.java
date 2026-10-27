package io.reactivex.internal.schedulers;

import e.a.j;
import io.reactivex.internal.disposables.EmptyDisposable;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: TrampolineScheduler.java */
/* JADX INFO: loaded from: classes.dex */
public final class g extends j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final g f5069b = new g();

    /* JADX INFO: compiled from: TrampolineScheduler.java */
    static final class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Runnable f5070b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final c f5071c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final long f5072d;

        a(Runnable runnable, c cVar, long j) {
            this.f5070b = runnable;
            this.f5071c = cVar;
            this.f5072d = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f5071c.f5080e) {
                return;
            }
            long jA = this.f5071c.a(TimeUnit.MILLISECONDS);
            long j = this.f5072d;
            if (j > jA) {
                try {
                    Thread.sleep(j - jA);
                } catch (InterruptedException e2) {
                    Thread.currentThread().interrupt();
                    e.a.o.a.k(e2);
                    return;
                }
            }
            if (this.f5071c.f5080e) {
                return;
            }
            this.f5070b.run();
        }
    }

    /* JADX INFO: compiled from: TrampolineScheduler.java */
    static final class b implements Comparable<b> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Runnable f5073b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final long f5074c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final int f5075d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        volatile boolean f5076e;

        b(Runnable runnable, Long l, int i) {
            this.f5073b = runnable;
            this.f5074c = l.longValue();
            this.f5075d = i;
        }

        @Override // java.lang.Comparable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compareTo(b bVar) {
            int iB = e.a.n.a.b.b(this.f5074c, bVar.f5074c);
            return iB == 0 ? e.a.n.a.b.a(this.f5075d, bVar.f5075d) : iB;
        }
    }

    /* JADX INFO: compiled from: TrampolineScheduler.java */
    static final class c extends j.b implements io.reactivex.disposables.b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final PriorityBlockingQueue<b> f5077b = new PriorityBlockingQueue<>();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final AtomicInteger f5078c = new AtomicInteger();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final AtomicInteger f5079d = new AtomicInteger();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        volatile boolean f5080e;

        /* JADX INFO: compiled from: TrampolineScheduler.java */
        final class a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final b f5081b;

            a(b bVar) {
                this.f5081b = bVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.f5081b.f5076e = true;
                c.this.f5077b.remove(this.f5081b);
            }
        }

        c() {
        }

        @Override // e.a.j.b
        public io.reactivex.disposables.b b(Runnable runnable) {
            return d(runnable, a(TimeUnit.MILLISECONDS));
        }

        @Override // e.a.j.b
        public io.reactivex.disposables.b c(Runnable runnable, long j, TimeUnit timeUnit) {
            long jA = a(TimeUnit.MILLISECONDS) + timeUnit.toMillis(j);
            return d(new a(runnable, this, jA), jA);
        }

        io.reactivex.disposables.b d(Runnable runnable, long j) {
            if (this.f5080e) {
                return EmptyDisposable.INSTANCE;
            }
            b bVar = new b(runnable, Long.valueOf(j), this.f5079d.incrementAndGet());
            this.f5077b.add(bVar);
            if (this.f5078c.getAndIncrement() != 0) {
                return io.reactivex.disposables.c.b(new a(bVar));
            }
            int iAddAndGet = 1;
            while (!this.f5080e) {
                b bVarPoll = this.f5077b.poll();
                if (bVarPoll == null) {
                    iAddAndGet = this.f5078c.addAndGet(-iAddAndGet);
                    if (iAddAndGet == 0) {
                        return EmptyDisposable.INSTANCE;
                    }
                } else if (!bVarPoll.f5076e) {
                    bVarPoll.f5073b.run();
                }
            }
            this.f5077b.clear();
            return EmptyDisposable.INSTANCE;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.f5080e = true;
        }
    }

    g() {
    }

    public static g d() {
        return f5069b;
    }

    @Override // e.a.j
    public j.b a() {
        return new c();
    }

    @Override // e.a.j
    public io.reactivex.disposables.b b(Runnable runnable) {
        e.a.o.a.m(runnable).run();
        return EmptyDisposable.INSTANCE;
    }

    @Override // e.a.j
    public io.reactivex.disposables.b c(Runnable runnable, long j, TimeUnit timeUnit) {
        try {
            timeUnit.sleep(j);
            e.a.o.a.m(runnable).run();
        } catch (InterruptedException e2) {
            Thread.currentThread().interrupt();
            e.a.o.a.k(e2);
        }
        return EmptyDisposable.INSTANCE;
    }
}
