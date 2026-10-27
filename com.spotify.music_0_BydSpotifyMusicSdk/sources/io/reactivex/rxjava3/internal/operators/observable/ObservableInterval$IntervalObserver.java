package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
final class ObservableInterval$IntervalObserver extends AtomicReference<Disposable> implements Disposable, Runnable {
    private static final long serialVersionUID = 346773832286157679L;
    long count;
    final Observer<? super Long> downstream;

    public ObservableInterval$IntervalObserver(Observer observer) {
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
        if (get() != DisposableHelper.c) {
            Observer<? super Long> observer = this.downstream;
            long j = this.count;
            this.count = 1 + j;
            observer.onNext(Long.valueOf(j));
        }
    }
}
