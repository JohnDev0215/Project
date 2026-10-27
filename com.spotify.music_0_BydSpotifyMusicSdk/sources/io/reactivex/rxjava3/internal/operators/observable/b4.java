package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class b4 implements Observer, Disposable {
    public final Observer c;
    public final io.reactivex.rxjava3.functions.c e;
    public Object k;
    public Disposable l;
    public boolean m;

    public b4(Observer observer, io.reactivex.rxjava3.functions.c cVar, Object obj) {
        this.c = observer;
        this.e = cVar;
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
        try {
            Object objK = this.e.k(this.k, obj);
            Objects.requireNonNull(objK, "The accumulator returned a null value");
            this.k = objK;
            this.c.onNext(objK);
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
            Observer observer = this.c;
            observer.onSubscribe(this);
            observer.onNext(this.k);
        }
    }
}
