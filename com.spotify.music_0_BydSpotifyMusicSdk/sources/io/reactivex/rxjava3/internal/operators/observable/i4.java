package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;

/* JADX INFO: loaded from: classes.dex */
public final class i4 implements Observer, Disposable {
    public final Observer c;
    public long e;
    public Disposable k;

    public i4(Observer observer, long j) {
        this.c = observer;
        this.e = j;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.k.dispose();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.k.isDisposed();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        this.c.onComplete();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        this.c.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        long j = this.e;
        if (j != 0) {
            this.e = j - 1;
        } else {
            this.c.onNext(obj);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.k, disposable)) {
            this.k = disposable;
            this.c.onSubscribe(this);
        }
    }
}
