package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;

/* JADX INFO: loaded from: classes.dex */
public final class l implements SingleObserver, Disposable {
    public SingleObserver c;
    public Disposable e;

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.c = null;
        this.e.dispose();
        this.e = DisposableHelper.c;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.e.isDisposed();
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onError(Throwable th) {
        this.e = DisposableHelper.c;
        SingleObserver singleObserver = this.c;
        if (singleObserver != null) {
            this.c = null;
            singleObserver.onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.e, disposable)) {
            this.e = disposable;
            this.c.onSubscribe(this);
        }
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSuccess(Object obj) {
        this.e = DisposableHelper.c;
        SingleObserver singleObserver = this.c;
        if (singleObserver != null) {
            this.c = null;
            singleObserver.onSuccess(obj);
        }
    }
}
