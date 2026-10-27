package e.a.n.g;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: AbstractDirectTask.java */
/* JADX INFO: loaded from: classes.dex */
abstract class a extends AtomicReference<Future<?>> implements e.a.k.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected static final FutureTask<Void> f4905b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected static final FutureTask<Void> f4906c;
    private static final long serialVersionUID = 1811839108042568751L;
    protected final Runnable runnable;
    protected Thread runner;

    static {
        Runnable runnable = e.a.n.b.a.f4870b;
        f4905b = new FutureTask<>(runnable, null);
        f4906c = new FutureTask<>(runnable, null);
    }

    a(Runnable runnable) {
        this.runnable = runnable;
    }

    public final void a(Future<?> future) {
        Future<?> future2;
        do {
            future2 = get();
            if (future2 == f4905b) {
                return;
            }
            if (future2 == f4906c) {
                future.cancel(this.runner != Thread.currentThread());
                return;
            }
        } while (!compareAndSet(future2, future));
    }

    @Override // e.a.k.b
    public final void d() {
        FutureTask<Void> futureTask;
        Future<?> future = get();
        if (future == f4905b || future == (futureTask = f4906c) || !compareAndSet(future, futureTask) || future == null) {
            return;
        }
        future.cancel(this.runner != Thread.currentThread());
    }
}
