package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class p implements SingleObserver, Disposable {
    public final SingleObserver c;
    public final io.reactivex.rxjava3.functions.a e;
    public Disposable k;

    public p(SingleObserver singleObserver, io.reactivex.rxjava3.functions.a aVar) {
        this.c = singleObserver;
        this.e = aVar;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.k.dispose();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.k.isDisposed();
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onError(Throwable th) {
        this.c.onError(th);
        try {
            this.e.run();
        } catch (Throwable th2) {
            he1.R(th2);
            RxJavaPlugins.b(th2);
        }
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.k, disposable)) {
            this.k = disposable;
            this.c.onSubscribe(this);
        }
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSuccess(Object obj) {
        this.c.onSuccess(obj);
        try {
            this.e.run();
        } catch (Throwable th) {
            he1.R(th);
            RxJavaPlugins.b(th);
        }
    }
}
