package com.bumptech.glide.load.engine;

import android.os.Process;
import com.bumptech.glide.load.engine.n;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/* JADX INFO: compiled from: ActiveResources.java */
/* JADX INFO: loaded from: classes.dex */
final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f2758a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Executor f2759b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final Map<com.bumptech.glide.load.c, d> f2760c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final ReferenceQueue<n<?>> f2761d;
    private n.a e;
    private volatile boolean f;
    private volatile c g;

    /* JADX INFO: renamed from: com.bumptech.glide.load.engine.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ActiveResources.java */
    class ThreadFactoryC0086a implements ThreadFactory {

        /* JADX INFO: renamed from: com.bumptech.glide.load.engine.a$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ActiveResources.java */
        class RunnableC0087a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ Runnable f2762b;

            RunnableC0087a(Runnable runnable) {
                this.f2762b = runnable;
            }

            @Override // java.lang.Runnable
            public void run() {
                Process.setThreadPriority(10);
                this.f2762b.run();
            }
        }

        ThreadFactoryC0086a() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(new RunnableC0087a(runnable), "glide-active-resources");
        }
    }

    /* JADX INFO: compiled from: ActiveResources.java */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.b();
        }
    }

    /* JADX INFO: compiled from: ActiveResources.java */
    interface c {
        void a();
    }

    /* JADX INFO: compiled from: ActiveResources.java */
    static final class d extends WeakReference<n<?>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final com.bumptech.glide.load.c f2765a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final boolean f2766b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        s<?> f2767c;

        d(com.bumptech.glide.load.c cVar, n<?> nVar, ReferenceQueue<? super n<?>> referenceQueue, boolean z) {
            super(nVar, referenceQueue);
            this.f2765a = (com.bumptech.glide.load.c) com.bumptech.glide.m.j.d(cVar);
            this.f2767c = (nVar.f() && z) ? (s) com.bumptech.glide.m.j.d(nVar.e()) : null;
            this.f2766b = nVar.f();
        }

        void a() {
            this.f2767c = null;
            clear();
        }
    }

    a(boolean z) {
        this(z, Executors.newSingleThreadExecutor(new ThreadFactoryC0086a()));
    }

    synchronized void a(com.bumptech.glide.load.c cVar, n<?> nVar) {
        d dVarPut = this.f2760c.put(cVar, new d(cVar, nVar, this.f2761d, this.f2758a));
        if (dVarPut != null) {
            dVarPut.a();
        }
    }

    void b() {
        while (!this.f) {
            try {
                c((d) this.f2761d.remove());
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
        s<?> sVar;
        synchronized (this) {
            this.f2760c.remove(dVar.f2765a);
            if (dVar.f2766b && (sVar = dVar.f2767c) != null) {
                this.e.d(dVar.f2765a, new n<>(sVar, true, false, dVar.f2765a, this.e));
            }
        }
    }

    synchronized void d(com.bumptech.glide.load.c cVar) {
        d dVarRemove = this.f2760c.remove(cVar);
        if (dVarRemove != null) {
            dVarRemove.a();
        }
    }

    synchronized n<?> e(com.bumptech.glide.load.c cVar) {
        d dVar = this.f2760c.get(cVar);
        if (dVar == null) {
            return null;
        }
        n<?> nVar = dVar.get();
        if (nVar == null) {
            c(dVar);
        }
        return nVar;
    }

    void f(n.a aVar) {
        synchronized (aVar) {
            synchronized (this) {
                this.e = aVar;
            }
        }
    }

    a(boolean z, Executor executor) {
        this.f2760c = new HashMap();
        this.f2761d = new ReferenceQueue<>();
        this.f2758a = z;
        this.f2759b = executor;
        executor.execute(new b());
    }
}
