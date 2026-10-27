package spi;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicReference;
import spi.vc;

/* JADX INFO: loaded from: classes2.dex */
public abstract class f extends AtomicReference<Future<?>> implements q6 {
    public static final FutureTask<Void> DISPOSED;
    public static final FutureTask<Void> FINISHED;
    private static final long serialVersionUID = 1811839108042568751L;
    public final boolean interruptOnCancel;
    public final Runnable runnable;
    public Thread runner;

    static {
        vc.d dVar = vc.a;
        FINISHED = new FutureTask<>(dVar, null);
        DISPOSED = new FutureTask<>(dVar, null);
    }

    public f(Runnable runnable, boolean z) {
        this.runnable = runnable;
        this.interruptOnCancel = z;
    }

    private void cancelFuture(Future<?> future) {
        future.cancel(this.runner == Thread.currentThread() ? false : this.interruptOnCancel);
    }

    @Override // spi.q6
    public final void dispose() {
        FutureTask<Void> futureTask;
        Future<?> future = get();
        if (future == FINISHED || future == (futureTask = DISPOSED) || !compareAndSet(future, futureTask) || future == null) {
            return;
        }
        cancelFuture(future);
    }

    public Runnable getWrappedRunnable() {
        return this.runnable;
    }

    public final boolean isDisposed() {
        Future<?> future = get();
        return future == FINISHED || future == DISPOSED;
    }

    public final void setFuture(Future<?> future) {
        Future<?> future2;
        do {
            future2 = get();
            if (future2 == FINISHED) {
                return;
            }
            if (future2 == DISPOSED) {
                cancelFuture(future);
                return;
            }
        } while (!compareAndSet(future2, future));
    }

    @Override // java.util.concurrent.atomic.AtomicReference
    public String toString() {
        String string;
        Future<?> future = get();
        if (future == FINISHED) {
            string = "Finished";
        } else if (future == DISPOSED) {
            string = "Disposed";
        } else if (this.runner != null) {
            StringBuilder sbK = e.k("Running on ");
            sbK.append(this.runner);
            string = sbK.toString();
        } else {
            string = "Waiting";
        }
        return getClass().getSimpleName() + "[" + string + "]";
    }
}
