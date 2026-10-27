package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;

/* JADX INFO: loaded from: classes.dex */
public final class g4 implements Observer, Disposable {
    public final MaybeObserver c;
    public Disposable e;
    public Object k;
    public boolean l;

    public g4(MaybeObserver maybeObserver) {
        this.c = maybeObserver;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.e.dispose();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.e.isDisposed();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        if (this.l) {
            return;
        }
        this.l = true;
        Object obj = this.k;
        this.k = null;
        MaybeObserver maybeObserver = this.c;
        if (obj == null) {
            maybeObserver.onComplete();
        } else {
            maybeObserver.onSuccess(obj);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        if (this.l) {
            RxJavaPlugins.b(th);
        } else {
            this.l = true;
            this.c.onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        if (this.l) {
            return;
        }
        if (this.k == null) {
            this.k = obj;
            return;
        }
        this.l = true;
        this.e.dispose();
        this.c.onError(new IllegalArgumentException("Sequence contains more than one element!"));
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.e, disposable)) {
            this.e = disposable;
            this.c.onSubscribe(this);
        }
    }
}
