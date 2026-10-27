package h5;

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.locks.LockSupport;

/* JADX INFO: loaded from: classes.dex */
public final class G extends p implements RunnableFuture, h {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public volatile RunnableC1374F f15539s;

    public G(Callable callable) {
        this.f15539s = new RunnableC1374F(this, callable);
    }

    @Override // h5.p
    public final void c() {
        RunnableC1374F runnableC1374F;
        Object obj = this.l;
        if ((obj instanceof C1375a) && ((C1375a) obj).f15540a && (runnableC1374F = this.f15539s) != null) {
            T1.m mVar = RunnableC1374F.o;
            T1.m mVar2 = RunnableC1374F.f15537n;
            Runnable runnable = (Runnable) runnableC1374F.get();
            if (runnable instanceof Thread) {
                w wVar = new w(runnableC1374F);
                w.a(wVar, Thread.currentThread());
                if (runnableC1374F.compareAndSet(runnable, wVar)) {
                    try {
                        ((Thread) runnable).interrupt();
                    } finally {
                        if (((Runnable) runnableC1374F.getAndSet(mVar2)) == mVar) {
                            LockSupport.unpark((Thread) runnable);
                        }
                    }
                }
            }
        }
        this.f15539s = null;
    }

    @Override // h5.p, java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.l instanceof C1375a;
    }

    @Override // h5.p
    public final String j() {
        RunnableC1374F runnableC1374F = this.f15539s;
        if (runnableC1374F == null) {
            return super.j();
        }
        return "task=[" + runnableC1374F + "]";
    }

    @Override // java.util.concurrent.RunnableFuture, java.lang.Runnable
    public final void run() {
        RunnableC1374F runnableC1374F = this.f15539s;
        if (runnableC1374F != null) {
            runnableC1374F.run();
        }
        this.f15539s = null;
    }
}
