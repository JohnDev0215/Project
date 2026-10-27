package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class l3 implements Observer, Disposable {
    public final SingleObserver c;
    public final io.reactivex.rxjava3.functions.c e;
    public Object k;
    public Disposable l;

    public l3(SingleObserver singleObserver, io.reactivex.rxjava3.functions.c cVar, Object obj) {
        this.c = singleObserver;
        this.k = obj;
        this.e = cVar;
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
        Object obj = this.k;
        if (obj != null) {
            this.k = null;
            this.c.onSuccess(obj);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        if (this.k == null) {
            RxJavaPlugins.b(th);
        } else {
            this.k = null;
            this.c.onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        Object obj2 = this.k;
        if (obj2 != null) {
            try {
                Object objK = this.e.k(obj2, obj);
                Objects.requireNonNull(objK, "The reducer returned a null value");
                this.k = objK;
            } catch (Throwable th) {
                he1.R(th);
                this.l.dispose();
                onError(th);
            }
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
