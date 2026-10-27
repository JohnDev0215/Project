package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class s implements SingleObserver {
    public final SingleObserver c;
    public final io.reactivex.rxjava3.functions.g e;
    public boolean k;

    public s(SingleObserver singleObserver, io.reactivex.rxjava3.functions.g gVar) {
        this.c = singleObserver;
        this.e = gVar;
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onError(Throwable th) {
        if (this.k) {
            RxJavaPlugins.b(th);
        } else {
            this.c.onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSubscribe(Disposable disposable) {
        SingleObserver singleObserver = this.c;
        try {
            this.e.accept(disposable);
            singleObserver.onSubscribe(disposable);
        } catch (Throwable th) {
            he1.R(th);
            this.k = true;
            disposable.dispose();
            singleObserver.onSubscribe(EmptyDisposable.c);
            singleObserver.onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSuccess(Object obj) {
        if (this.k) {
            return;
        }
        this.c.onSuccess(obj);
    }
}
