package io.reactivex.rxjava3.internal.observers;

import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class m implements SingleObserver {
    public final SingleObserver c;
    public boolean e;

    public m(SingleObserver singleObserver) {
        this.c = singleObserver;
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onError(Throwable th) {
        if (this.e) {
            RxJavaPlugins.b(th);
            return;
        }
        try {
            this.c.onError(th);
        } catch (Throwable th2) {
            he1.R(th2);
            RxJavaPlugins.b(new CompositeException(th, th2));
        }
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSubscribe(Disposable disposable) {
        try {
            this.c.onSubscribe(disposable);
        } catch (Throwable th) {
            he1.R(th);
            this.e = true;
            disposable.dispose();
            RxJavaPlugins.b(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSuccess(Object obj) {
        if (this.e) {
            return;
        }
        try {
            this.c.onSuccess(obj);
        } catch (Throwable th) {
            he1.R(th);
            RxJavaPlugins.b(th);
        }
    }
}
