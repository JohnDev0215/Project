package a5;

import java.util.concurrent.Callable;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import q4.d;

/* JADX INFO: compiled from: NewThreadWorker.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends d.b {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final ScheduledExecutorService f92e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    volatile boolean f93g;

    public g(ThreadFactory threadFactory) {
        this.f92e = k.a(threadFactory);
    }

    @Override // q4.d.b
    public t4.b b(Runnable runnable, long j7, TimeUnit timeUnit) {
        return this.f93g ? w4.c.INSTANCE : c(runnable, j7, timeUnit, null);
    }

    public j c(Runnable runnable, long j7, TimeUnit timeUnit, w4.a aVar) {
        j jVar = new j(d5.a.m(runnable), aVar);
        if (aVar != null && !aVar.a(jVar)) {
            return jVar;
        }
        try {
            jVar.a(j7 <= 0 ? this.f92e.submit((Callable) jVar) : this.f92e.schedule((Callable) jVar, j7, timeUnit));
        } catch (RejectedExecutionException e7) {
            if (aVar != null) {
                aVar.c(jVar);
            }
            d5.a.k(e7);
        }
        return jVar;
    }

    public t4.b d(Runnable runnable, long j7, TimeUnit timeUnit) {
        i iVar = new i(d5.a.m(runnable));
        try {
            iVar.a(j7 <= 0 ? this.f92e.submit(iVar) : this.f92e.schedule(iVar, j7, timeUnit));
            return iVar;
        } catch (RejectedExecutionException e7) {
            d5.a.k(e7);
            return w4.c.INSTANCE;
        }
    }

    @Override // t4.b
    public void dispose() {
        if (this.f93g) {
            return;
        }
        this.f93g = true;
        this.f92e.shutdownNow();
    }

    public void e() {
        if (this.f93g) {
            return;
        }
        this.f93g = true;
        this.f92e.shutdown();
    }

    @Override // t4.b
    public boolean isDisposed() {
        return this.f93g;
    }
}
