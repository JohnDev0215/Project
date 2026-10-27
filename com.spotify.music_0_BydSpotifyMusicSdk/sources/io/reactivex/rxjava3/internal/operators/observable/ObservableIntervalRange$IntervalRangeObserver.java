package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
final class ObservableIntervalRange$IntervalRangeObserver extends AtomicReference<Disposable> implements Disposable, Runnable {
    private static final long serialVersionUID = 1891866368734007884L;
    long count;
    final Observer<? super Long> downstream;
    final long end;

    public ObservableIntervalRange$IntervalRangeObserver(Observer observer, long j, long j2) {
        this.downstream = observer;
        this.count = j;
        this.end = j2;
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
        long j = this.count;
        this.downstream.onNext(Long.valueOf(j));
        if (j != this.end) {
            this.count = j + 1;
            return;
        }
        if (!isDisposed()) {
            this.downstream.onComplete();
        }
        DisposableHelper.a(this);
    }
}
