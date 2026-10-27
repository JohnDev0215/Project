package c.a.m.g;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
abstract class a extends AtomicReference<Future<?>> implements c.a.j.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected static final FutureTask<Void> f1853b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected static final FutureTask<Void> f1854c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected final Runnable f1855d;
    protected Thread e;

    static {
        Runnable runnable = c.a.m.b.a.f1824b;
        f1853b = new FutureTask<>(runnable, null);
        f1854c = new FutureTask<>(runnable, null);
    }

    a(Runnable runnable) {
        this.f1855d = runnable;
    }

    public final void a(Future<?> future) {
        Future<?> future2;
        do {
            future2 = get();
            if (future2 == f1853b) {
                return;
            }
            if (future2 == f1854c) {
                future.cancel(this.e != Thread.currentThread());
                return;
            }
        } while (!compareAndSet(future2, future));
    }

    @Override // c.a.j.b
    public final void e() {
        FutureTask<Void> futureTask;
        Future<?> future = get();
        if (future == f1853b || future == (futureTask = f1854c) || !compareAndSet(future, futureTask) || future == null) {
            return;
        }
        future.cancel(this.e != Thread.currentThread());
    }

    @Override // c.a.j.b
    public final boolean g() {
        Future<?> future = get();
        return future == f1853b || future == f1854c;
    }
}
