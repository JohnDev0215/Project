package io.reactivex.rxjava3.internal.operators.completable;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
final class CompletableObserveOn$ObserveOnCompletableObserver extends AtomicReference<Disposable> implements CompletableObserver, Disposable, Runnable {
    private static final long serialVersionUID = 8571289934935992137L;
    final CompletableObserver downstream;
    Throwable error;
    final Scheduler scheduler;

    public CompletableObserveOn$ObserveOnCompletableObserver(CompletableObserver completableObserver, Scheduler scheduler) {
        this.downstream = completableObserver;
        this.scheduler = scheduler;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        DisposableHelper.a(this);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.b(get());
    }

    @Override // io.reactivex.rxjava3.core.CompletableObserver
    public final void onComplete() {
        DisposableHelper.c(this, this.scheduler.c(this));
    }

    @Override // io.reactivex.rxjava3.core.CompletableObserver
    public final void onError(Throwable th) {
        this.error = th;
        DisposableHelper.c(this, this.scheduler.c(this));
    }

    @Override // io.reactivex.rxjava3.core.CompletableObserver
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.f(this, disposable)) {
            this.downstream.onSubscribe(this);
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        Throwable th = this.error;
        if (th == null) {
            this.downstream.onComplete();
        } else {
            this.error = null;
            this.downstream.onError(th);
        }
    }
}
