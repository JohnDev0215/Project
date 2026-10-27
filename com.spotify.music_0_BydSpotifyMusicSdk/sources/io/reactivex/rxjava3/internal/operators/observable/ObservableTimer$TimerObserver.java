package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
final class ObservableTimer$TimerObserver extends AtomicReference<Disposable> implements Disposable, Runnable {
    private static final long serialVersionUID = -2809475196591179431L;
    final Observer<? super Long> downstream;

    public ObservableTimer$TimerObserver(Observer observer) {
        this.downstream = observer;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        DisposableHelper.a(this);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return get() == DisposableHelper.c;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (isDisposed()) {
            return;
        }
        this.downstream.onNext(0L);
        lazySet(EmptyDisposable.c);
        this.downstream.onComplete();
    }
}
