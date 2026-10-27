package c.a.m.g;

import c.a.h;
import java.util.concurrent.Callable;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class e extends h.b implements c.a.j.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ScheduledExecutorService f1877b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    volatile boolean f1878c;

    public e(ThreadFactory threadFactory) {
        this.f1877b = i.a(threadFactory);
    }

    @Override // c.a.h.b
    public c.a.j.b b(Runnable runnable) {
        return c(runnable, 0L, null);
    }

    @Override // c.a.h.b
    public c.a.j.b c(Runnable runnable, long j, TimeUnit timeUnit) {
        return this.f1878c ? c.a.m.a.c.INSTANCE : d(runnable, j, timeUnit, null);
    }

    public h d(Runnable runnable, long j, TimeUnit timeUnit, c.a.m.a.a aVar) {
        h hVar = new h(c.a.n.a.o(runnable), aVar);
        if (aVar != null && !aVar.c(hVar)) {
            return hVar;
        }
        try {
            hVar.a(j <= 0 ? this.f1877b.submit((Callable) hVar) : this.f1877b.schedule((Callable) hVar, j, timeUnit));
        } catch (RejectedExecutionException e) {
            if (aVar != null) {
                aVar.b(hVar);
            }
            c.a.n.a.l(e);
        }
        return hVar;
    }

    @Override // c.a.j.b
    public void e() {
        if (this.f1878c) {
            return;
        }
        this.f1878c = true;
        this.f1877b.shutdownNow();
    }

    public c.a.j.b f(Runnable runnable, long j, TimeUnit timeUnit) {
        g gVar = new g(c.a.n.a.o(runnable));
        try {
            gVar.a(j <= 0 ? this.f1877b.submit(gVar) : this.f1877b.schedule(gVar, j, timeUnit));
            return gVar;
        } catch (RejectedExecutionException e) {
            c.a.n.a.l(e);
            return c.a.m.a.c.INSTANCE;
        }
    }

    @Override // c.a.j.b
    public boolean g() {
        return this.f1878c;
    }

    public void h() {
        if (this.f1878c) {
            return;
        }
        this.f1878c = true;
        this.f1877b.shutdown();
    }
}
