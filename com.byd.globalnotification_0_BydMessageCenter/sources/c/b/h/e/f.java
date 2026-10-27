package c.b.h.e;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicReference;

/* JADX WARN: Incorrect class signature: super class is equals to this class */
/* JADX INFO: loaded from: classes.dex */
public final class f extends AtomicReference implements Callable<Void>, c.b.e.b {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final FutureTask<Void> f2285c = new FutureTask<>(c.b.h.b.a.f2231a, null);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final FutureTask<Void> f2286d = new FutureTask<>(c.b.h.b.a.f2231a, null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Runnable f2287a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Thread f2288b;

    public f(Runnable runnable) {
        this.f2287a = runnable;
    }

    public final void a(Future future) {
        Future future2;
        do {
            future2 = (Future) get();
            if (future2 == f2285c) {
                return;
            }
            if (future2 == f2286d) {
                future.cancel(this.f2288b != Thread.currentThread());
                return;
            }
        } while (!compareAndSet(future2, future));
    }

    @Override // java.util.concurrent.Callable
    public Void call() {
        this.f2288b = Thread.currentThread();
        try {
            this.f2287a.run();
            return null;
        } finally {
            lazySet(f2285c);
            this.f2288b = null;
        }
    }

    @Override // c.b.e.b
    public final void d() {
        FutureTask<Void> futureTask;
        Future future = (Future) get();
        if (future == f2285c || future == (futureTask = f2286d) || !compareAndSet(future, futureTask) || future == null) {
            return;
        }
        future.cancel(this.f2288b != Thread.currentThread());
    }
}
