package h5;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

/* JADX INFO: renamed from: h5.F, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC1374F extends AtomicReference implements Runnable {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final T1.m f15537n = new T1.m(1);
    public static final T1.m o = new T1.m(1);
    public final Callable l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ G f15538m;

    public RunnableC1374F(G g7, Callable callable) {
        this.f15538m = g7;
        callable.getClass();
        this.l = callable;
    }

    public final void a(Thread thread) {
        Runnable runnable = (Runnable) get();
        w wVar = null;
        boolean z6 = false;
        int i7 = 0;
        while (true) {
            boolean z10 = runnable instanceof w;
            T1.m mVar = o;
            if (!z10 && runnable != mVar) {
                break;
            }
            if (z10) {
                wVar = (w) runnable;
            }
            i7++;
            if (i7 <= 1000) {
                Thread.yield();
            } else if (runnable == mVar || compareAndSet(runnable, mVar)) {
                z6 = Thread.interrupted() || z6;
                LockSupport.park(wVar);
            }
            runnable = (Runnable) get();
        }
        if (z6) {
            thread.interrupt();
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        Thread threadCurrentThread = Thread.currentThread();
        Object objCall = null;
        if (compareAndSet(null, threadCurrentThread)) {
            G g7 = this.f15538m;
            boolean zIsDone = g7.isDone();
            T1.m mVar = f15537n;
            if (!zIsDone) {
                try {
                    objCall = this.l.call();
                } catch (Throwable th) {
                    try {
                        if (th instanceof InterruptedException) {
                            Thread.currentThread().interrupt();
                        }
                        if (!compareAndSet(threadCurrentThread, mVar)) {
                            a(threadCurrentThread);
                        }
                        if (zIsDone) {
                            return;
                        }
                        g7.m(th);
                        return;
                    } finally {
                        if (!compareAndSet(threadCurrentThread, mVar)) {
                            a(threadCurrentThread);
                        }
                        if (!zIsDone) {
                            g7.l(null);
                        }
                    }
                }
            }
        }
    }

    @Override // java.util.concurrent.atomic.AtomicReference
    public final String toString() {
        String str;
        Runnable runnable = (Runnable) get();
        if (runnable == f15537n) {
            str = "running=[DONE]";
        } else if (runnable instanceof w) {
            str = "running=[INTERRUPTED]";
        } else if (runnable instanceof Thread) {
            str = "running=[RUNNING ON " + ((Thread) runnable).getName() + "]";
        } else {
            str = "running=[NOT STARTED YET]";
        }
        StringBuilder sbL = A7.k.l(str, ", ");
        sbL.append(this.l.toString());
        return sbL.toString();
    }
}
