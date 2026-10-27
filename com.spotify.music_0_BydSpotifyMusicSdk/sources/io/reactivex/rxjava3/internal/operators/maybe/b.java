package io.reactivex.rxjava3.internal.operators.maybe;

import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.p;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class b implements SingleObserver, Disposable {
    public final MaybeObserver c;
    public final p e;
    public Disposable k;

    public b(MaybeObserver maybeObserver, p pVar) {
        this.c = maybeObserver;
        this.e = pVar;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        Disposable disposable = this.k;
        this.k = DisposableHelper.c;
        disposable.dispose();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.k.isDisposed();
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onError(Throwable th) {
        this.c.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.k, disposable)) {
            this.k = disposable;
            this.c.onSubscribe(this);
        }
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSuccess(Object obj) {
        MaybeObserver maybeObserver = this.c;
        try {
            if (this.e.test(obj)) {
                maybeObserver.onSuccess(obj);
            } else {
                maybeObserver.onComplete();
            }
        } catch (Throwable th) {
            he1.R(th);
            maybeObserver.onError(th);
        }
    }
}
