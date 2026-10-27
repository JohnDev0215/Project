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
    private final boolean f2991a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Executor f2992b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final Map<com.bumptech.glide.load.c, d> f2993c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final ReferenceQueue<n<?>> f2994d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private n.a f2995e;
    private volatile boolean f;
    private volatile c g;

    /* JADX INFO: renamed from: com.bumptech.glide.load.engine.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ActiveResources.java */
    class ThreadFactoryC0062a implements ThreadFactory {

        /* JADX INFO: renamed from: com.bumptech.glide.load.engine.a$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ActiveResources.java */
        class RunnableC0063a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ Runnable f2996b;

            RunnableC0063a(Runnable runnable) {
                this.f2996b = runnable;
            }

            @Override // java.lang.Runnable
            public void run() {
                Process.setThreadPriority(10);
                this.f2996b.run();
            }
        }

        ThreadFactoryC0062a() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(new RunnableC0063a(runnable), "glide-active-resources");
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
        final com.bumptech.glide.load.c f2999a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final boolean f3000b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        s<?> f3001c;

        d(com.bumptech.glide.load.c cVar, n<?> nVar, ReferenceQueue<? super n<?>> referenceQueue, boolean z) {
            super(nVar, referenceQueue);
            this.f2999a = (com.bumptech.glide.load.c) com.bumptech.glide.o.j.d(cVar);
            this.f3001c = (nVar.f() && z) ? (s) com.bumptech.glide.o.j.d(nVar.e()) : null;
            this.f3000b = nVar.f();
        }

        void a() {
            this.f3001c = null;
            clear();
        }
    }

    a(boolean z) {
        this(z, Executors.newSingleThreadExecutor(new ThreadFactoryC0062a()));
    }

    synchronized void a(com.bumptech.glide.load.c cVar, n<?> nVar) {
        d dVarPut = this.f2993c.put(cVar, new d(cVar, nVar, this.f2994d, this.f2991a));
        if (dVarPut != null) {
            dVarPut.a();
        }
    }

    void b() {
        while (!this.f) {
            try {
                c((d) this.f2994d.remove());
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
            this.f2993c.remove(dVar.f2999a);
            if (dVar.f3000b && (sVar = dVar.f3001c) != null) {
                this.f2995e.a(dVar.f2999a, new n<>(sVar, true, false, dVar.f2999a, this.f2995e));
            }
        }
    }

    synchronized void d(com.bumptech.glide.load.c cVar) {
        d dVarRemove = this.f2993c.remove(cVar);
        if (dVarRemove != null) {
            dVarRemove.a();
        }
    }

    synchronized n<?> e(com.bumptech.glide.load.c cVar) {
        d dVar = this.f2993c.get(cVar);
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
                this.f2995e = aVar;
            }
        }
    }

    a(boolean z, Executor executor) {
        this.f2993c = new HashMap();
        this.f2994d = new ReferenceQueue<>();
        this.f2991a = z;
        this.f2992b = executor;
        executor.execute(new b());
    }
}
