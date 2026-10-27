package a5;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: AbstractDirectTask.java */
/* JADX INFO: loaded from: classes.dex */
abstract class a extends AtomicReference<Future<?>> implements t4.b {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    protected static final FutureTask<Void> f32h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    protected static final FutureTask<Void> f33i;
    private static final long serialVersionUID = 1811839108042568751L;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected final Runnable f34e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    protected Thread f35g;

    static {
        Runnable runnable = x4.a.f9841b;
        f32h = new FutureTask<>(runnable, null);
        f33i = new FutureTask<>(runnable, null);
    }

    a(Runnable runnable) {
        this.f34e = runnable;
    }

    public final void a(Future<?> future) {
        Future<?> future2;
        do {
            future2 = get();
            if (future2 == f32h) {
                return;
            }
            if (future2 == f33i) {
                future.cancel(this.f35g != Thread.currentThread());
                return;
            }
        } while (!compareAndSet(future2, future));
    }

    @Override // t4.b
    public final void dispose() {
        FutureTask<Void> futureTask;
        Future<?> future = get();
        if (future == f32h || future == (futureTask = f33i) || !compareAndSet(future, futureTask) || future == null) {
            return;
        }
        future.cancel(this.f35g != Thread.currentThread());
    }

    @Override // t4.b
    public final boolean isDisposed() {
        Future<?> future = get();
        return future == f32h || future == f33i;
    }
}
