package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class h4 implements Observer, Disposable {
    public final SingleObserver c;
    public final Object e;
    public Disposable k;
    public Object l;
    public boolean m;

    public h4(SingleObserver singleObserver, Object obj) {
        this.c = singleObserver;
        this.e = obj;
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
        Object obj = this.l;
        this.l = null;
        if (obj == null) {
            obj = this.e;
        }
        SingleObserver singleObserver = this.c;
        if (obj != null) {
            singleObserver.onSuccess(obj);
        } else {
            singleObserver.onError(new NoSuchElementException());
        }
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
        if (this.l == null) {
            this.l = obj;
            return;
        }
        this.m = true;
        this.k.dispose();
        this.c.onError(new IllegalArgumentException("Sequence contains more than one element!"));
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.k, disposable)) {
            this.k = disposable;
            this.c.onSubscribe(this);
        }
    }
}
