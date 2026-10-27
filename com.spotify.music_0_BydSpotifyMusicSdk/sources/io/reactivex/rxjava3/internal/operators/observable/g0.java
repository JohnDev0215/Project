package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class g0 implements Observer, Disposable {
    public final SingleObserver c;
    public final io.reactivex.rxjava3.functions.b e;
    public final Object k;
    public Disposable l;
    public boolean m;

    public g0(SingleObserver singleObserver, Object obj, io.reactivex.rxjava3.functions.b bVar) {
        this.c = singleObserver;
        this.e = bVar;
        this.k = obj;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.l.dispose();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.l.isDisposed();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        if (this.m) {
            return;
        }
        this.m = true;
        this.c.onSuccess(this.k);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        if (this.m) {
            RxJavaPlugins.b(th);
        } else {
            this.m = true;
            this.c.onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        if (this.m) {
            return;
        }
        try {
            this.e.accept(this.k, obj);
        } catch (Throwable th) {
            he1.R(th);
            this.l.dispose();
            onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.l, disposable)) {
            this.l = disposable;
            this.c.onSubscribe(this);
        }
    }
}
