package e.a.n.g;

import e.a.h;
import java.util.concurrent.Callable;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: NewThreadWorker.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends h.b implements e.a.k.b {

    /* JADX INFO: renamed from: b */
    private final ScheduledExecutorService f4941b;

    /* JADX INFO: renamed from: c */
    volatile boolean f4942c;

    public e(ThreadFactory threadFactory) {
        this.f4941b = i.a(threadFactory);
    }

    @Override // e.a.h.b
    public e.a.k.b b(Runnable runnable) {
        return c(runnable, 0L, null);
    }

    @Override // e.a.h.b
    public e.a.k.b c(Runnable runnable, long j, TimeUnit timeUnit) {
        return this.f4942c ? e.a.n.a.c.INSTANCE : e(runnable, j, timeUnit, null);
    }

    @Override // e.a.k.b
    public void d() {
        if (this.f4942c) {
            return;
        }
        this.f4942c = true;
        this.f4941b.shutdownNow();
    }

    public h e(Runnable runnable, long j, TimeUnit timeUnit, e.a.n.a.a aVar) {
        h hVar = new h(e.a.p.a.q(runnable), aVar);
        if (aVar != null && !aVar.c(hVar)) {
            return hVar;
        }
        try {
            hVar.a(j <= 0 ? this.f4941b.submit((Callable) hVar) : this.f4941b.schedule((Callable) hVar, j, timeUnit));
        } catch (RejectedExecutionException e2) {
            if (aVar != null) {
                aVar.b(hVar);
            }
            e.a.p.a.o(e2);
        }
        return hVar;
    }

    public e.a.k.b f(Runnable runnable, long j, TimeUnit timeUnit) {
        g gVar = new g(e.a.p.a.q(runnable));
        try {
            gVar.a(j <= 0 ? this.f4941b.submit(gVar) : this.f4941b.schedule(gVar, j, timeUnit));
            return gVar;
        } catch (RejectedExecutionException e2) {
            e.a.p.a.o(e2);
            return e.a.n.a.c.INSTANCE;
        }
    }

    public void g() {
        if (this.f4942c) {
            return;
        }
        this.f4942c = true;
        this.f4941b.shutdown();
    }
}
