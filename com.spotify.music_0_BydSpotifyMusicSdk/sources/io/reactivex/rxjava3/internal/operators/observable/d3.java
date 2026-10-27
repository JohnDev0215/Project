package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class d3 implements Observer, Disposable {
    public final Observer c;
    public final io.reactivex.rxjava3.functions.p e;
    public Disposable k;

    public d3(Observer observer, io.reactivex.rxjava3.functions.p pVar) {
        this.c = observer;
        this.e = pVar;
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
        Observer observer = this.c;
        try {
            if (this.e.test(th)) {
                observer.onComplete();
            } else {
                observer.onError(th);
            }
        } catch (Throwable th2) {
            he1.R(th2);
            observer.onError(new CompositeException(th, th2));
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        this.c.onNext(obj);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.k, disposable)) {
            this.k = disposable;
            this.c.onSubscribe(this);
        }
    }
}
