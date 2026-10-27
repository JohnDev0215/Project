package io.reactivex.rxjava3.internal.operators.maybe;

import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class i implements MaybeObserver, Disposable {
    public final SingleObserver c;
    public final Object e;
    public Disposable k;

    public i(SingleObserver singleObserver, Object obj) {
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
        return this.k.isDisposed();
    }

    @Override // io.reactivex.rxjava3.core.MaybeObserver
    public final void onComplete() {
        this.k = DisposableHelper.c;
        SingleObserver singleObserver = this.c;
        Object obj = this.e;
        if (obj != null) {
            singleObserver.onSuccess(obj);
        } else {
            singleObserver.onError(new NoSuchElementException("The MaybeSource is empty"));
        }
    }

    @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver
    public final void onError(Throwable th) {
        this.k = DisposableHelper.c;
        this.c.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.k, disposable)) {
            this.k = disposable;
            this.c.onSubscribe(this);
        }
    }

    @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver
    public final void onSuccess(Object obj) {
        this.k = DisposableHelper.c;
        this.c.onSuccess(obj);
    }
}
