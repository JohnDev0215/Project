package c.b.h.e;

import c.b.c;
import java.util.concurrent.Callable;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class d extends c.b implements c.b.e.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ScheduledExecutorService f2280a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile boolean f2281b;

    public d(ThreadFactory threadFactory) {
        this.f2280a = h.a(threadFactory);
    }

    @Override // c.b.c.b
    public c.b.e.b b(Runnable runnable, long j, TimeUnit timeUnit) {
        return this.f2281b ? c.b.h.a.c.INSTANCE : c(runnable, j, timeUnit, null);
    }

    public g c(Runnable runnable, long j, TimeUnit timeUnit, c.b.h.a.a aVar) {
        c.b.h.b.b.a(runnable, "run is null");
        g gVar = new g(runnable, aVar);
        if (aVar != null && !aVar.a(gVar)) {
            return gVar;
        }
        try {
            gVar.a(j <= 0 ? this.f2280a.submit((Callable) gVar) : this.f2280a.schedule((Callable) gVar, j, timeUnit));
        } catch (RejectedExecutionException e2) {
            if (aVar != null) {
                aVar.c(gVar);
            }
            c.b.j.a.c(e2);
        }
        return gVar;
    }

    @Override // c.b.e.b
    public void d() {
        if (this.f2281b) {
            return;
        }
        this.f2281b = true;
        this.f2280a.shutdownNow();
    }
}
