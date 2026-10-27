package a5;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import q4.d;

/* JADX INFO: compiled from: ExecutorScheduler.java */
/* JADX INFO: loaded from: classes.dex */
public final class d extends q4.d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final q4.d f51d = e5.a.c();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final boolean f52b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final Executor f53c;

    /* JADX INFO: compiled from: ExecutorScheduler.java */
    final class a implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final b f54e;

        a(b bVar) {
            this.f54e = bVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            b bVar = this.f54e;
            bVar.f57g.a(d.this.b(bVar));
        }
    }

    /* JADX INFO: compiled from: ExecutorScheduler.java */
    static final class b extends AtomicReference<Runnable> implements Runnable, t4.b {
        private static final long serialVersionUID = -4101336210206799084L;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final w4.e f56e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final w4.e f57g;

        b(Runnable runnable) {
            super(runnable);
            this.f56e = new w4.e();
            this.f57g = new w4.e();
        }

        @Override // t4.b
        public void dispose() {
            if (getAndSet(null) != null) {
                this.f56e.dispose();
                this.f57g.dispose();
            }
        }

        @Override // t4.b
        public boolean isDisposed() {
            return get() == null;
        }

        @Override // java.lang.Runnable
        public void run() {
            Runnable runnable = get();
            if (runnable != null) {
                try {
                    runnable.run();
                    lazySet(null);
                    w4.e eVar = this.f56e;
                    w4.b bVar = w4.b.DISPOSED;
                    eVar.lazySet(bVar);
                    this.f57g.lazySet(bVar);
                } catch (Throwable th) {
                    lazySet(null);
                    this.f56e.lazySet(w4.b.DISPOSED);
                    this.f57g.lazySet(w4.b.DISPOSED);
                    throw th;
                }
            }
        }
    }

    /* JADX INFO: compiled from: ExecutorScheduler.java */
    public static final class c extends d.b implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final boolean f58e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final Executor f59g;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        volatile boolean f61i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        final AtomicInteger f62j = new AtomicInteger();

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        final t4.a f63k = new t4.a();

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final z4.a<Runnable> f60h = new z4.a<>();

        /* JADX INFO: compiled from: ExecutorScheduler.java */
        static final class a extends AtomicBoolean implements Runnable, t4.b {
            private static final long serialVersionUID = -2421395018820541164L;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            final Runnable f64e;

            a(Runnable runnable) {
                this.f64e = runnable;
            }

            @Override // t4.b
            public void dispose() {
                lazySet(true);
            }

            @Override // t4.b
            public boolean isDisposed() {
                return get();
            }

            @Override // java.lang.Runnable
            public void run() {
                if (get()) {
                    return;
                }
                try {
                    this.f64e.run();
                } finally {
                    lazySet(true);
                }
            }
        }

        /* JADX INFO: compiled from: ExecutorScheduler.java */
        static final class b extends AtomicInteger implements Runnable, t4.b {
            private static final long serialVersionUID = -3603436687413320876L;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            final Runnable f65e;

            /* JADX INFO: renamed from: g, reason: collision with root package name */
            final w4.a f66g;

            /* JADX INFO: renamed from: h, reason: collision with root package name */
            volatile Thread f67h;

            b(Runnable runnable, w4.a aVar) {
                this.f65e = runnable;
                this.f66g = aVar;
            }

            void a() {
                w4.a aVar = this.f66g;
                if (aVar != null) {
                    aVar.b(this);
                }
            }

            @Override // t4.b
            public void dispose() {
                while (true) {
                    int i7 = get();
                    if (i7 >= 2) {
                        return;
                    }
                    if (i7 == 0) {
                        if (compareAndSet(0, 4)) {
                            a();
                            return;
                        }
                    } else if (compareAndSet(1, 3)) {
                        Thread thread = this.f67h;
                        if (thread != null) {
                            thread.interrupt();
                            this.f67h = null;
                        }
                        set(4);
                        a();
                        return;
                    }
                }
            }

            @Override // t4.b
            public boolean isDisposed() {
                return get() >= 2;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (get() == 0) {
                    this.f67h = Thread.currentThread();
                    if (!compareAndSet(0, 1)) {
                        this.f67h = null;
                        return;
                    }
                    try {
                        this.f65e.run();
                        this.f67h = null;
                        if (compareAndSet(1, 2)) {
                            a();
                            return;
                        }
                        while (get() == 3) {
                            Thread.yield();
                        }
                        Thread.interrupted();
                    } catch (Throwable th) {
                        this.f67h = null;
                        if (compareAndSet(1, 2)) {
                            a();
                        } else {
                            while (get() == 3) {
                                Thread.yield();
                            }
                            Thread.interrupted();
                        }
                        throw th;
                    }
                }
            }
        }

        /* JADX INFO: renamed from: a5.d$c$c, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ExecutorScheduler.java */
        final class RunnableC0002c implements Runnable {

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            private final w4.e f68e;

            /* JADX INFO: renamed from: g, reason: collision with root package name */
            private final Runnable f69g;

            RunnableC0002c(w4.e eVar, Runnable runnable) {
                this.f68e = eVar;
                this.f69g = runnable;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.f68e.a(c.this.c(this.f69g));
            }
        }

        public c(Executor executor, boolean z6) {
            this.f59g = executor;
            this.f58e = z6;
        }

        @Override // q4.d.b
        public t4.b b(Runnable runnable, long j7, TimeUnit timeUnit) {
            if (j7 <= 0) {
                return c(runnable);
            }
            if (this.f61i) {
                return w4.c.INSTANCE;
            }
            w4.e eVar = new w4.e();
            w4.e eVar2 = new w4.e(eVar);
            j jVar = new j(new RunnableC0002c(eVar2, d5.a.m(runnable)), this.f63k);
            this.f63k.a(jVar);
            Executor executor = this.f59g;
            if (executor instanceof ScheduledExecutorService) {
                try {
                    jVar.a(((ScheduledExecutorService) executor).schedule((Callable) jVar, j7, timeUnit));
                } catch (RejectedExecutionException e7) {
                    this.f61i = true;
                    d5.a.k(e7);
                    return w4.c.INSTANCE;
                }
            } else {
                jVar.a(new a5.c(d.f51d.c(jVar, j7, timeUnit)));
            }
            eVar.a(jVar);
            return eVar2;
        }

        public t4.b c(Runnable runnable) {
            t4.b aVar;
            if (this.f61i) {
                return w4.c.INSTANCE;
            }
            Runnable runnableM = d5.a.m(runnable);
            if (this.f58e) {
                aVar = new b(runnableM, this.f63k);
                this.f63k.a(aVar);
            } else {
                aVar = new a(runnableM);
            }
            this.f60h.f(aVar);
            if (this.f62j.getAndIncrement() == 0) {
                try {
                    this.f59g.execute(this);
                } catch (RejectedExecutionException e7) {
                    this.f61i = true;
                    this.f60h.a();
                    d5.a.k(e7);
                    return w4.c.INSTANCE;
                }
            }
            return aVar;
        }

        @Override // t4.b
        public void dispose() {
            if (this.f61i) {
                return;
            }
            this.f61i = true;
            this.f63k.dispose();
            if (this.f62j.getAndIncrement() == 0) {
                this.f60h.a();
            }
        }

        @Override // t4.b
        public boolean isDisposed() {
            return this.f61i;
        }

        @Override // java.lang.Runnable
        public void run() {
            z4.a<Runnable> aVar = this.f60h;
            int iAddAndGet = 1;
            while (!this.f61i) {
                do {
                    Runnable runnableG = aVar.g();
                    if (runnableG != null) {
                        runnableG.run();
                    } else if (this.f61i) {
                        aVar.a();
                        return;
                    } else {
                        iAddAndGet = this.f62j.addAndGet(-iAddAndGet);
                        if (iAddAndGet == 0) {
                            return;
                        }
                    }
                } while (!this.f61i);
                aVar.a();
                return;
            }
            aVar.a();
        }
    }

    public d(Executor executor, boolean z6) {
        this.f53c = executor;
        this.f52b = z6;
    }

    @Override // q4.d
    public d.b a() {
        return new c(this.f53c, this.f52b);
    }

    @Override // q4.d
    public t4.b b(Runnable runnable) {
        Runnable runnableM = d5.a.m(runnable);
        try {
            if (this.f53c instanceof ExecutorService) {
                i iVar = new i(runnableM);
                iVar.a(((ExecutorService) this.f53c).submit(iVar));
                return iVar;
            }
            if (this.f52b) {
                c.b bVar = new c.b(runnableM, null);
                this.f53c.execute(bVar);
                return bVar;
            }
            c.a aVar = new c.a(runnableM);
            this.f53c.execute(aVar);
            return aVar;
        } catch (RejectedExecutionException e7) {
            d5.a.k(e7);
            return w4.c.INSTANCE;
        }
    }

    @Override // q4.d
    public t4.b c(Runnable runnable, long j7, TimeUnit timeUnit) {
        Runnable runnableM = d5.a.m(runnable);
        if (!(this.f53c instanceof ScheduledExecutorService)) {
            b bVar = new b(runnableM);
            bVar.f56e.a(f51d.c(new a(bVar), j7, timeUnit));
            return bVar;
        }
        try {
            i iVar = new i(runnableM);
            iVar.a(((ScheduledExecutorService) this.f53c).schedule(iVar, j7, timeUnit));
            return iVar;
        } catch (RejectedExecutionException e7) {
            d5.a.k(e7);
            return w4.c.INSTANCE;
        }
    }
}
