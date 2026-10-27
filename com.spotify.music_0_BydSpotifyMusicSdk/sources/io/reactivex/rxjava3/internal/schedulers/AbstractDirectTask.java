package io.reactivex.rxjava3.internal.schedulers;

import io.reactivex.rxjava3.disposables.Disposable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicReference;
import p.mc0;

/* JADX INFO: loaded from: classes.dex */
abstract class AbstractDirectTask extends AtomicReference<Future<?>> implements Disposable {
    public static final FutureTask c;
    public static final FutureTask e;
    private static final long serialVersionUID = 1811839108042568751L;
    protected final boolean interruptOnCancel;
    protected final Runnable runnable;
    protected Thread runner;

    static {
        mc0 mc0Var = io.reactivex.rxjava3.internal.functions.k.b;
        c = new FutureTask(mc0Var, null);
        e = new FutureTask(mc0Var, null);
    }

    public AbstractDirectTask(Runnable runnable, boolean z) {
        this.runnable = runnable;
        this.interruptOnCancel = z;
    }

    public final void a(Future future) {
        Future<?> future2;
        do {
            future2 = get();
            if (future2 == c) {
                return;
            }
            if (future2 == e) {
                if (this.runner == Thread.currentThread()) {
                    future.cancel(false);
                    return;
                } else {
                    future.cancel(this.interruptOnCancel);
                    return;
                }
            }
        } while (!compareAndSet(future2, future));
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public final void dispose() {
        FutureTask futureTask;
        Future<?> future = get();
        if (future == c || future == (futureTask = e) || !compareAndSet(future, futureTask) || future == null) {
            return;
        }
        if (this.runner == Thread.currentThread()) {
            future.cancel(false);
        } else {
            future.cancel(this.interruptOnCancel);
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public final boolean isDisposed() {
        Future<?> future = get();
        return future == c || future == e;
    }

    @Override // java.util.concurrent.atomic.AtomicReference
    public String toString() {
        String str;
        Future<?> future = get();
        if (future == c) {
            str = "Finished";
        } else if (future == e) {
            str = "Disposed";
        } else if (this.runner != null) {
            str = "Running on " + this.runner;
        } else {
            str = "Waiting";
        }
        return getClass().getSimpleName() + "[" + str + "]";
    }
}
