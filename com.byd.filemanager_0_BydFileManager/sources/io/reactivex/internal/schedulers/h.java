package io.reactivex.internal.schedulers;

import d.a.j;
import io.reactivex.internal.disposables.EmptyDisposable;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: TrampolineScheduler.java */
/* JADX INFO: loaded from: classes.dex */
public final class h extends j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final h f4626b = new h();

    /* JADX INFO: compiled from: TrampolineScheduler.java */
    static final class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Runnable f4627b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final c f4628c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final long f4629d;

        a(Runnable runnable, c cVar, long j) {
            this.f4627b = runnable;
            this.f4628c = cVar;
            this.f4629d = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f4628c.f4637e) {
                return;
            }
            long jA = this.f4628c.a(TimeUnit.MILLISECONDS);
            long j = this.f4629d;
            if (j > jA) {
                long j2 = j - jA;
                if (j2 > 0) {
                    try {
                        Thread.sleep(j2);
                    } catch (InterruptedException e2) {
                        Thread.currentThread().interrupt();
                        d.a.o.a.l(e2);
                        return;
                    }
                }
            }
            if (this.f4628c.f4637e) {
                return;
            }
            this.f4627b.run();
        }
    }

    /* JADX INFO: compiled from: TrampolineScheduler.java */
    static final class b implements Comparable<b> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Runnable f4630b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final long f4631c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final int f4632d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        volatile boolean f4633e;

        b(Runnable runnable, Long l, int i) {
            this.f4630b = runnable;
            this.f4631c = l.longValue();
            this.f4632d = i;
        }

        @Override // java.lang.Comparable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compareTo(b bVar) {
            int iB = d.a.n.a.b.b(this.f4631c, bVar.f4631c);
            return iB == 0 ? d.a.n.a.b.a(this.f4632d, bVar.f4632d) : iB;
        }
    }

    /* JADX INFO: compiled from: TrampolineScheduler.java */
    static final class c extends j.b implements io.reactivex.disposables.b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final PriorityBlockingQueue<b> f4634b = new PriorityBlockingQueue<>();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final AtomicInteger f4635c = new AtomicInteger();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final AtomicInteger f4636d = new AtomicInteger();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        volatile boolean f4637e;

        /* JADX INFO: compiled from: TrampolineScheduler.java */
        final class a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final b f4638b;

            a(b bVar) {
                this.f4638b = bVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.f4638b.f4633e = true;
                c.this.f4634b.remove(this.f4638b);
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
            if (this.f4637e) {
                return EmptyDisposable.INSTANCE;
            }
            b bVar = new b(runnable, Long.valueOf(j), this.f4636d.incrementAndGet());
            this.f4634b.add(bVar);
            if (this.f4635c.getAndIncrement() != 0) {
                return io.reactivex.disposables.c.b(new a(bVar));
            }
            int iAddAndGet = 1;
            while (!this.f4637e) {
                b bVarPoll = this.f4634b.poll();
                if (bVarPoll == null) {
                    iAddAndGet = this.f4635c.addAndGet(-iAddAndGet);
                    if (iAddAndGet == 0) {
                        return EmptyDisposable.INSTANCE;
                    }
                } else if (!bVarPoll.f4633e) {
                    bVarPoll.f4630b.run();
                }
            }
            this.f4634b.clear();
            return EmptyDisposable.INSTANCE;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.f4637e = true;
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.f4637e;
        }
    }

    h() {
    }

    public static h d() {
        return f4626b;
    }

    @Override // d.a.j
    public j.b a() {
        return new c();
    }

    @Override // d.a.j
    public io.reactivex.disposables.b b(Runnable runnable) {
        runnable.run();
        return EmptyDisposable.INSTANCE;
    }

    @Override // d.a.j
    public io.reactivex.disposables.b c(Runnable runnable, long j, TimeUnit timeUnit) {
        try {
            timeUnit.sleep(j);
            runnable.run();
        } catch (InterruptedException e2) {
            Thread.currentThread().interrupt();
            d.a.o.a.l(e2);
        }
        return EmptyDisposable.INSTANCE;
    }
}
