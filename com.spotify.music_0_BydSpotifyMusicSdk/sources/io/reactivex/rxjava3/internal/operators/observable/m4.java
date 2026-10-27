package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.ArrayCompositeDisposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;

/* JADX INFO: loaded from: classes.dex */
public final class m4 implements Observer {
    public final Observer c;
    public final ArrayCompositeDisposable e;
    public Disposable k;
    public volatile boolean l;
    public boolean m;

    public m4(io.reactivex.rxjava3.observers.d dVar, ArrayCompositeDisposable arrayCompositeDisposable) {
        this.c = dVar;
        this.e = arrayCompositeDisposable;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        this.e.dispose();
        this.c.onComplete();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        this.e.dispose();
        this.c.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        if (this.m) {
            this.c.onNext(obj);
        } else if (this.l) {
            this.m = true;
            this.c.onNext(obj);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.k, disposable)) {
            this.k = disposable;
            this.e.a(0, disposable);
        }
    }
}
