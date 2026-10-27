package io.reactivex.internal.schedulers;

import d.a.j;
import io.reactivex.internal.disposables.EmptyDisposable;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: TrampolineScheduler.java */
/* JADX INFO: loaded from: classes.dex */
public final class g extends j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final g f4342b = new g();

    /* JADX INFO: compiled from: TrampolineScheduler.java */
    static final class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Runnable f4343b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final c f4344c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final long f4345d;

        a(Runnable runnable, c cVar, long j) {
            this.f4343b = runnable;
            this.f4344c = cVar;
            this.f4345d = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f4344c.f4353e) {
                return;
            }
            long jA = this.f4344c.a(TimeUnit.MILLISECONDS);
            long j = this.f4345d;
            if (j > jA) {
                try {
                    Thread.sleep(j - jA);
                } catch (InterruptedException e2) {
                    Thread.currentThread().interrupt();
                    d.a.p.a.l(e2);
                    return;
                }
            }
            if (this.f4344c.f4353e) {
                return;
            }
            this.f4343b.run();
        }
    }

    /* JADX INFO: compiled from: TrampolineScheduler.java */
    static final class b implements Comparable<b> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Runnable f4346b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final long f4347c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final int f4348d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        volatile boolean f4349e;

        b(Runnable runnable, Long l, int i) {
            this.f4346b = runnable;
            this.f4347c = l.longValue();
            this.f4348d = i;
        }

        @Override // java.lang.Comparable
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public int compareTo(b bVar) {
            int iB = d.a.n.a.b.b(this.f4347c, bVar.f4347c);
            return iB == 0 ? d.a.n.a.b.a(this.f4348d, bVar.f4348d) : iB;
        }
    }

    /* JADX INFO: compiled from: TrampolineScheduler.java */
    static final class c extends j.b implements io.reactivex.disposables.b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final PriorityBlockingQueue<b> f4350b = new PriorityBlockingQueue<>();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final AtomicInteger f4351c = new AtomicInteger();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final AtomicInteger f4352d = new AtomicInteger();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        volatile boolean f4353e;

        /* JADX INFO: compiled from: TrampolineScheduler.java */
        final class a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final b f4354b;

            a(b bVar) {
                this.f4354b = bVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.f4354b.f4349e = true;
                c.this.f4350b.remove(this.f4354b);
            }
        }

        c() {
        }

        @Override // d.a.j.b
        public io.reactivex.disposables.b b(Runnable runnable) {
            return d(runnable, a(TimeUnit.MILLISECONDS));
        }

        @Override // d.a.j.b
        public io.reactivex.disposables.b c(Runnable runnable, long j, TimeUnit timeUnit) {
            long jA = a(TimeUnit.MILLISECONDS) + timeUnit.toMillis(j);
            return d(new a(runnable, this, jA), jA);
        }

        io.reactivex.disposables.b d(Runnable runnable, long j) {
            if (this.f4353e) {
                return EmptyDisposable.INSTANCE;
            }
            b bVar = new b(runnable, Long.valueOf(j), this.f4352d.incrementAndGet());
            this.f4350b.add(bVar);
            if (this.f4351c.getAndIncrement() != 0) {
                return io.reactivex.disposables.c.b(new a(bVar));
            }
            int iAddAndGet = 1;
            while (!this.f4353e) {
                b bVarPoll = this.f4350b.poll();
                if (bVarPoll == null) {
                    iAddAndGet = this.f4351c.addAndGet(-iAddAndGet);
                    if (iAddAndGet == 0) {
                        return EmptyDisposable.INSTANCE;
                    }
                } else if (!bVarPoll.f4349e) {
                    bVarPoll.f4346b.run();
                }
            }
            this.f4350b.clear();
            return EmptyDisposable.INSTANCE;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.f4353e = true;
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.f4353e;
        }
    }

    g() {
    }

    public static g d() {
        return f4342b;
    }

    @Override // d.a.j
    public j.b a() {
        return new c();
    }

    @Override // d.a.j
    public io.reactivex.disposables.b b(Runnable runnable) {
        d.a.p.a.n(runnable).run();
        return EmptyDisposable.INSTANCE;
    }

    @Override // d.a.j
    public io.reactivex.disposables.b c(Runnable runnable, long j, TimeUnit timeUnit) {
        try {
            timeUnit.sleep(j);
            d.a.p.a.n(runnable).run();
        } catch (InterruptedException e2) {
            Thread.currentThread().interrupt();
            d.a.p.a.l(e2);
        }
        return EmptyDisposable.INSTANCE;
    }
}
