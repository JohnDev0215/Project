package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.ArrayCompositeDisposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;

/* JADX INFO: loaded from: classes.dex */
public final class l4 implements Observer {
    public final ArrayCompositeDisposable c;
    public final m4 e;
    public final io.reactivex.rxjava3.observers.d k;
    public Disposable l;

    public l4(ArrayCompositeDisposable arrayCompositeDisposable, m4 m4Var, io.reactivex.rxjava3.observers.d dVar) {
        this.c = arrayCompositeDisposable;
        this.e = m4Var;
        this.k = dVar;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        this.e.l = true;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        this.c.dispose();
        this.k.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        this.l.dispose();
        this.e.l = true;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.l, disposable)) {
            this.l = disposable;
            this.c.a(1, disposable);
        }
    }
}
