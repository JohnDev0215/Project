package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class m1 implements Observer, Disposable {
    public final SingleObserver c;
    public final long e;
    public final Object k;
    public Disposable l;
    public long m;
    public boolean n;

    public m1(SingleObserver singleObserver, long j, Object obj) {
        this.c = singleObserver;
        this.e = j;
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
        if (this.n) {
            return;
        }
        this.n = true;
        SingleObserver singleObserver = this.c;
        Object obj = this.k;
        if (obj != null) {
            singleObserver.onSuccess(obj);
        } else {
            singleObserver.onError(new NoSuchElementException());
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        if (this.n) {
            RxJavaPlugins.b(th);
        } else {
            this.n = true;
            this.c.onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        if (this.n) {
            return;
        }
        long j = this.m;
        if (j != this.e) {
            this.m = j + 1;
            return;
        }
        this.n = true;
        this.l.dispose();
        this.c.onSuccess(obj);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.l, disposable)) {
            this.l = disposable;
            this.c.onSubscribe(this);
        }
    }
}
