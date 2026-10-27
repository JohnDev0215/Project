package io.reactivex.rxjava3.internal.schedulers;

import io.reactivex.rxjava3.plugins.RxJavaPlugins;

/* JADX INFO: loaded from: classes7.dex */
public final class ScheduledDirectPeriodicTask extends AbstractDirectTask implements Runnable {
    @Override // io.reactivex.rxjava3.internal.schedulers.AbstractDirectTask, java.util.concurrent.atomic.AtomicReference
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public ScheduledDirectPeriodicTask(Runnable runnable, boolean interruptOnCancel) {
        super(runnable, interruptOnCancel);
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
            RxJavaPlugins.onError(th);
            throw th;
        }
    }
}
