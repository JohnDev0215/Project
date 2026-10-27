package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class k3 implements Observer, Disposable {
    public final MaybeObserver c;
    public final io.reactivex.rxjava3.functions.c e;
    public boolean k;
    public Object l;
    public Disposable m;

    public k3(MaybeObserver maybeObserver, io.reactivex.rxjava3.functions.c cVar) {
        this.c = maybeObserver;
        this.e = cVar;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.m.dispose();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.m.isDisposed();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        if (this.k) {
            return;
        }
        this.k = true;
        Object obj = this.l;
        this.l = null;
        MaybeObserver maybeObserver = this.c;
        if (obj != null) {
            maybeObserver.onSuccess(obj);
        } else {
            maybeObserver.onComplete();
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        if (this.k) {
            RxJavaPlugins.b(th);
            return;
        }
        this.k = true;
        this.l = null;
        this.c.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        if (this.k) {
            return;
        }
        Object obj2 = this.l;
        if (obj2 == null) {
            this.l = obj;
            return;
        }
        try {
            Object objK = this.e.k(obj2, obj);
            Objects.requireNonNull(objK, "The reducer returned a null value");
            this.l = objK;
        } catch (Throwable th) {
            he1.R(th);
            this.m.dispose();
            onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.m, disposable)) {
            this.m = disposable;
            this.c.onSubscribe(this);
        }
    }
}
