package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
final class SingleObserveOn$ObserveOnSingleObserver<T> extends AtomicReference<Disposable> implements SingleObserver<T>, Disposable, Runnable {
    private static final long serialVersionUID = 3528003840217436037L;
    final SingleObserver<? super T> downstream;
    Throwable error;
    final Scheduler scheduler;
    T value;

    public SingleObserveOn$ObserveOnSingleObserver(SingleObserver singleObserver, Scheduler scheduler) {
        this.downstream = singleObserver;
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

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onError(Throwable th) {
        this.error = th;
        DisposableHelper.c(this, this.scheduler.c(this));
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.f(this, disposable)) {
            this.downstream.onSubscribe(this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSuccess(Object obj) {
        this.value = obj;
        DisposableHelper.c(this, this.scheduler.c(this));
    }

    @Override // java.lang.Runnable
    public final void run() {
        Throwable th = this.error;
        if (th != null) {
            this.downstream.onError(th);
        } else {
            this.downstream.onSuccess(this.value);
        }
    }
}
