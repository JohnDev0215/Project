package com.bumptech.glide.load.n;

import android.os.Process;
import com.bumptech.glide.load.n.p;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/* JADX INFO: loaded from: classes.dex */
final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f2020a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Executor f2021b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final Map<com.bumptech.glide.load.g, d> f2022c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final ReferenceQueue<p<?>> f2023d;
    private p.a e;
    private volatile boolean f;
    private volatile c g;

    /* JADX INFO: renamed from: com.bumptech.glide.load.n.a$a, reason: collision with other inner class name */
    class ThreadFactoryC0065a implements ThreadFactory {

        /* JADX INFO: renamed from: com.bumptech.glide.load.n.a$a$a, reason: collision with other inner class name */
        class RunnableC0066a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ Runnable f2024b;

            RunnableC0066a(Runnable runnable) {
                this.f2024b = runnable;
            }

            @Override // java.lang.Runnable
            public void run() {
                Process.setThreadPriority(10);
                this.f2024b.run();
            }
        }

        ThreadFactoryC0065a() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(new RunnableC0066a(runnable), "glide-active-resources");
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.b();
        }
    }

    interface c {
        void a();
    }

    static final class d extends WeakReference<p<?>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final com.bumptech.glide.load.g f2027a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final boolean f2028b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        v<?> f2029c;

        d(com.bumptech.glide.load.g gVar, p<?> pVar, ReferenceQueue<? super p<?>> referenceQueue, boolean z) {
            super(pVar, referenceQueue);
            this.f2027a = (com.bumptech.glide.load.g) com.bumptech.glide.t.j.d(gVar);
            this.f2029c = (pVar.g() && z) ? (v) com.bumptech.glide.t.j.d(pVar.e()) : null;
            this.f2028b = pVar.g();
        }

        void a() {
            this.f2029c = null;
            clear();
        }
    }

    a(boolean z) {
        this(z, Executors.newSingleThreadExecutor(new ThreadFactoryC0065a()));
    }

    a(boolean z, Executor executor) {
        this.f2022c = new HashMap();
        this.f2023d = new ReferenceQueue<>();
        this.f2020a = z;
        this.f2021b = executor;
        executor.execute(new b());
    }

    synchronized void a(com.bumptech.glide.load.g gVar, p<?> pVar) {
        d dVarPut = this.f2022c.put(gVar, new d(gVar, pVar, this.f2023d, this.f2020a));
        if (dVarPut != null) {
            dVarPut.a();
        }
    }

    void b() {
        while (!this.f) {
            try {
                c((d) this.f2023d.remove());
                c cVar = this.g;
                if (cVar != null) {
                    cVar.a();
                }
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        }
    }

    void c(d dVar) {
        v<?> vVar;
        synchronized (this) {
            this.f2022c.remove(dVar.f2027a);
            if (dVar.f2028b && (vVar = dVar.f2029c) != null) {
                this.e.a(dVar.f2027a, new p<>(vVar, true, false, dVar.f2027a, this.e));
            }
        }
    }

    synchronized void d(com.bumptech.glide.load.g gVar) {
        d dVarRemove = this.f2022c.remove(gVar);
        if (dVarRemove != null) {
            dVarRemove.a();
        }
    }

    synchronized p<?> e(com.bumptech.glide.load.g gVar) {
        d dVar = this.f2022c.get(gVar);
        if (dVar == null) {
            return null;
        }
        p<?> pVar = dVar.get();
        if (pVar == null) {
            c(dVar);
        }
        return pVar;
    }

    void f(p.a aVar) {
        synchronized (aVar) {
            synchronized (this) {
                this.e = aVar;
            }
        }
    }
}
