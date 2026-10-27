package io.reactivex.rxjava3.internal.schedulers;

import spi.f;
import spi.vn;

/* JADX INFO: loaded from: classes2.dex */
public final class ScheduledDirectPeriodicTask extends f implements Runnable {
    private static final long serialVersionUID = 1811839108042568751L;

    public ScheduledDirectPeriodicTask(Runnable runnable, boolean z) {
        super(runnable, z);
    }

    @Override // spi.f
    public /* bridge */ /* synthetic */ Runnable getWrappedRunnable() {
        return super.getWrappedRunnable();
    }

    @Override // java.lang.Runnable
    public void run() {
        this.runner = Thread.currentThread();
        try {
            this.runnable.run();
            this.runner = null;
        } catch (Throwable th) {
            dispose();
            this.runner = null;
            vn.a(th);
            throw th;
        }
    }

    @Override // spi.f, java.util.concurrent.atomic.AtomicReference
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }
}
