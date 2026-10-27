package io.reactivex.rxjava3.internal.schedulers;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.SequentialDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
final class ExecutorScheduler$DelayedRunnable extends AtomicReference<Runnable> implements Runnable, Disposable {
    private static final long serialVersionUID = -4101336210206799084L;
    final SequentialDisposable direct;
    final SequentialDisposable timed;

    public ExecutorScheduler$DelayedRunnable(Runnable runnable) {
        super(runnable);
        this.timed = new SequentialDisposable();
        this.direct = new SequentialDisposable();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        if (getAndSet(null) != null) {
            this.timed.dispose();
            this.direct.dispose();
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return get() == null;
    }

    @Override // java.lang.Runnable
    public final void run() {
        DisposableHelper disposableHelper = DisposableHelper.c;
        Runnable runnable = get();
        if (runnable != null) {
            try {
                try {
                    runnable.run();
                    lazySet(null);
                    this.timed.lazySet(disposableHelper);
                    this.direct.lazySet(disposableHelper);
                } catch (Throwable th) {
                    lazySet(null);
                    this.timed.lazySet(disposableHelper);
                    this.direct.lazySet(disposableHelper);
                    throw th;
                }
            } catch (Throwable th2) {
                RxJavaPlugins.b(th2);
                throw th2;
            }
        }
    }
}
