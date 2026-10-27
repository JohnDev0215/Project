package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class u2 implements Observer, Disposable {
    public final SingleObserver c;
    public final Object e;
    public Disposable k;
    public Object l;

    public u2(SingleObserver singleObserver, Object obj) {
        this.c = singleObserver;
        this.e = obj;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.k.dispose();
        this.k = DisposableHelper.c;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.k == DisposableHelper.c;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        this.k = DisposableHelper.c;
        Object obj = this.l;
        SingleObserver singleObserver = this.c;
        if (obj != null) {
            this.l = null;
            singleObserver.onSuccess(obj);
            return;
        }
        Object obj2 = this.e;
        if (obj2 != null) {
            singleObserver.onSuccess(obj2);
        } else {
            singleObserver.onError(new NoSuchElementException());
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        this.k = DisposableHelper.c;
        this.l = null;
        this.c.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        this.l = obj;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.k, disposable)) {
            this.k = disposable;
            this.c.onSubscribe(this);
        }
    }
}
