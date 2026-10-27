package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class a4 implements Observer, Disposable {
    public final Observer c;
    public final io.reactivex.rxjava3.functions.c e;
    public Disposable k;
    public Object l;
    public boolean m;

    public a4(Observer observer, io.reactivex.rxjava3.functions.c cVar) {
        this.c = observer;
        this.e = cVar;
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
        if (this.m) {
            return;
        }
        this.m = true;
        this.c.onComplete();
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
        Object obj2 = this.l;
        Observer observer = this.c;
        if (obj2 == null) {
            this.l = obj;
            observer.onNext(obj);
            return;
        }
        try {
            Object objK = this.e.k(obj2, obj);
            Objects.requireNonNull(objK, "The value returned by the accumulator is null");
            this.l = objK;
            observer.onNext(objK);
        } catch (Throwable th) {
            he1.R(th);
            this.k.dispose();
            onError(th);
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
