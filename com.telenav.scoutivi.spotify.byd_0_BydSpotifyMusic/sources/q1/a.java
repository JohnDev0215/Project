package q1;

import android.os.Process;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import q1.p;

/* JADX INFO: compiled from: ActiveResources.java */
/* JADX INFO: loaded from: classes.dex */
final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f8937a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Executor f8938b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final Map<n1.f, d> f8939c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final ReferenceQueue<p<?>> f8940d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private p.a f8941e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private volatile boolean f8942f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private volatile c f8943g;

    /* JADX INFO: renamed from: q1.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ActiveResources.java */
    class ThreadFactoryC0137a implements ThreadFactory {

        /* JADX INFO: renamed from: q1.a$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ActiveResources.java */
        class RunnableC0138a implements Runnable {

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            final /* synthetic */ Runnable f8944e;

            RunnableC0138a(Runnable runnable) {
                this.f8944e = runnable;
            }

            @Override // java.lang.Runnable
            public void run() {
                Process.setThreadPriority(10);
                this.f8944e.run();
            }
        }

        ThreadFactoryC0137a() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(new RunnableC0138a(runnable), "glide-active-resources");
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
    static final class d extends WeakReference<p<?>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final n1.f f8947a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final boolean f8948b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        v<?> f8949c;

        d(n1.f fVar, p<?> pVar, ReferenceQueue<? super p<?>> referenceQueue, boolean z6) {
            super(pVar, referenceQueue);
            this.f8947a = (n1.f) k2.j.d(fVar);
            this.f8949c = (pVar.f() && z6) ? (v) k2.j.d(pVar.d()) : null;
            this.f8948b = pVar.f();
        }

        void a() {
            this.f8949c = null;
            clear();
        }
    }

    a(boolean z6) {
        this(z6, Executors.newSingleThreadExecutor(new ThreadFactoryC0137a()));
    }

    synchronized void a(n1.f fVar, p<?> pVar) {
        d dVarPut = this.f8939c.put(fVar, new d(fVar, pVar, this.f8940d, this.f8937a));
        if (dVarPut != null) {
            dVarPut.a();
        }
    }

    void b() {
        while (!this.f8942f) {
            try {
                c((d) this.f8940d.remove());
                c cVar = this.f8943g;
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
            this.f8939c.remove(dVar.f8947a);
            if (dVar.f8948b && (vVar = dVar.f8949c) != null) {
                this.f8941e.b(dVar.f8947a, new p<>(vVar, true, false, dVar.f8947a, this.f8941e));
            }
        }
    }

    synchronized void d(n1.f fVar) {
        d dVarRemove = this.f8939c.remove(fVar);
        if (dVarRemove != null) {
            dVarRemove.a();
        }
    }

    synchronized p<?> e(n1.f fVar) {
        d dVar = this.f8939c.get(fVar);
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
                this.f8941e = aVar;
            }
        }
    }

    a(boolean z6, Executor executor) {
        this.f8939c = new HashMap();
        this.f8940d = new ReferenceQueue<>();
        this.f8937a = z6;
        this.f8938b = executor;
        executor.execute(new b());
    }
}
