package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;

/* JADX INFO: loaded from: classes.dex */
public final class t4 implements Observer, Disposable {
    public final Observer c;
    public Disposable e;
    public Object k;

    public t4(Observer observer) {
        this.c = observer;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.k = null;
        this.e.dispose();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.e.isDisposed();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        Object obj = this.k;
        Observer observer = this.c;
        if (obj != null) {
            this.k = null;
            observer.onNext(obj);
        }
        observer.onComplete();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        this.k = null;
        this.c.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        this.k = obj;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.e, disposable)) {
            this.e = disposable;
            this.c.onSubscribe(this);
        }
    }
}
