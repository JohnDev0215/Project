package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class o4 implements Observer, Disposable {
    public final Observer c;
    public final io.reactivex.rxjava3.functions.p e;
    public Disposable k;
    public boolean l;

    public o4(Observer observer, io.reactivex.rxjava3.functions.p pVar) {
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
        this.c.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        boolean z = this.l;
        Observer observer = this.c;
        if (z) {
            observer.onNext(obj);
            return;
        }
        try {
            if (this.e.test(obj)) {
                return;
            }
            this.l = true;
            observer.onNext(obj);
        } catch (Throwable th) {
            he1.R(th);
            this.k.dispose();
            observer.onError(th);
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
