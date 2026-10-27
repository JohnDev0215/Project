package io.reactivex.rxjava3.internal.schedulers;

import java.util.concurrent.Callable;
import spi.f;
import spi.vn;

/* JADX INFO: loaded from: classes2.dex */
public final class ScheduledDirectTask extends f implements Callable<Void> {
    private static final long serialVersionUID = 1811839108042568751L;

    public ScheduledDirectTask(Runnable runnable, boolean z) {
        super(runnable, z);
    }

    @Override // java.util.concurrent.Callable
    public Void call() {
        this.runner = Thread.currentThread();
        try {
            try {
                this.runnable.run();
                return null;
            } finally {
                lazySet(f.FINISHED);
                this.runner = null;
            }
        } catch (Throwable th) {
            vn.a(th);
            throw th;
        }
    }

    @Override // spi.f
    public /* bridge */ /* synthetic */ Runnable getWrappedRunnable() {
        return super.getWrappedRunnable();
    }

    @Override // spi.f, java.util.concurrent.atomic.AtomicReference
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }
}
