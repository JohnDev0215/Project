package d.a.p.g;

import d.a.j;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.schedulers.ScheduledRunnable;
import java.util.concurrent.Callable;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: NewThreadWorker.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends j.b implements d.a.m.b {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ScheduledExecutorService f3234c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public volatile boolean f3235d;

    public d(ThreadFactory threadFactory) {
        this.f3234c = e.a(threadFactory);
    }

    @Override // d.a.j.b
    public d.a.m.b b(Runnable runnable) {
        return c(runnable, 0L, null);
    }

    @Override // d.a.j.b
    public d.a.m.b c(Runnable runnable, long j, TimeUnit timeUnit) {
        return this.f3235d ? EmptyDisposable.INSTANCE : d(runnable, j, timeUnit, null);
    }

    public ScheduledRunnable d(Runnable runnable, long j, TimeUnit timeUnit, d.a.p.a.a aVar) {
        ScheduledRunnable scheduledRunnable = new ScheduledRunnable(d.a.r.a.q(runnable), aVar);
        if (aVar != null && !aVar.c(scheduledRunnable)) {
            return scheduledRunnable;
        }
        try {
            scheduledRunnable.setFuture(j <= 0 ? this.f3234c.submit((Callable) scheduledRunnable) : this.f3234c.schedule((Callable) scheduledRunnable, j, timeUnit));
        } catch (RejectedExecutionException e2) {
            aVar.b(scheduledRunnable);
            d.a.r.a.o(e2);
        }
        return scheduledRunnable;
    }

    @Override // d.a.m.b
    public void dispose() {
        if (this.f3235d) {
            return;
        }
        this.f3235d = true;
        this.f3234c.shutdownNow();
    }

    public d.a.m.b e(Runnable runnable, long j, TimeUnit timeUnit) {
        Runnable runnableQ = d.a.r.a.q(runnable);
        try {
            return d.a.m.c.a(j <= 0 ? this.f3234c.submit(runnableQ) : this.f3234c.schedule(runnableQ, j, timeUnit));
        } catch (RejectedExecutionException e2) {
            d.a.r.a.o(e2);
            return EmptyDisposable.INSTANCE;
        }
    }

    @Override // d.a.m.b
    public boolean isDisposed() {
        return this.f3235d;
    }
}
