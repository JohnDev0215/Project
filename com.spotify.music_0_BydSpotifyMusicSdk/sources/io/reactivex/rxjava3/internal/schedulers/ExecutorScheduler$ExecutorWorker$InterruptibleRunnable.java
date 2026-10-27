package io.reactivex.rxjava3.internal.schedulers;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
final class ExecutorScheduler$ExecutorWorker$InterruptibleRunnable extends AtomicInteger implements Runnable, Disposable {
    private static final long serialVersionUID = -3603436687413320876L;
    final Runnable run;
    final io.reactivex.rxjava3.disposables.b tasks;
    volatile Thread thread;

    public ExecutorScheduler$ExecutorWorker$InterruptibleRunnable(Runnable runnable, io.reactivex.rxjava3.disposables.a aVar) {
        this.run = runnable;
        this.tasks = aVar;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        while (true) {
            int i = get();
            if (i >= 2) {
                return;
            }
            if (i == 0) {
                if (compareAndSet(0, 4)) {
                    io.reactivex.rxjava3.disposables.b bVar = this.tasks;
                    if (bVar != null) {
                        bVar.b(this);
                        return;
                    }
                    return;
                }
            } else if (compareAndSet(1, 3)) {
                Thread thread = this.thread;
                if (thread != null) {
                    thread.interrupt();
                    this.thread = null;
                }
                set(4);
                io.reactivex.rxjava3.disposables.b bVar2 = this.tasks;
                if (bVar2 != null) {
                    bVar2.b(this);
                    return;
                }
                return;
            }
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return get() >= 2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (get() == 0) {
            this.thread = Thread.currentThread();
            if (!compareAndSet(0, 1)) {
                this.thread = null;
                return;
            }
            try {
                this.run.run();
                this.thread = null;
                if (!compareAndSet(1, 2)) {
                    while (get() == 3) {
                        Thread.yield();
                    }
                    Thread.interrupted();
                } else {
                    io.reactivex.rxjava3.disposables.b bVar = this.tasks;
                    if (bVar != null) {
                        bVar.b(this);
                    }
                }
            } catch (Throwable th) {
                try {
                    RxJavaPlugins.b(th);
                    throw th;
                } catch (Throwable th2) {
                    this.thread = null;
                    if (compareAndSet(1, 2)) {
                        io.reactivex.rxjava3.disposables.b bVar2 = this.tasks;
                        if (bVar2 != null) {
                            bVar2.b(this);
                        }
                    } else {
                        while (get() == 3) {
                            Thread.yield();
                        }
                        Thread.interrupted();
                    }
                    throw th2;
                }
            }
        }
    }
}
