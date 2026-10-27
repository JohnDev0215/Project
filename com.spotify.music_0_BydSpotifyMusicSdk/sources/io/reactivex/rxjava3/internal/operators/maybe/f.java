package io.reactivex.rxjava3.internal.operators.maybe;

import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.functions.p;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class f implements MaybeObserver, SingleObserver, Disposable {
    public final MaybeObserver c;
    public final p e;
    public Disposable k;

    public f(MaybeObserver maybeObserver, p pVar) {
        this.c = maybeObserver;
        this.e = pVar;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.k.dispose();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.k.isDisposed();
    }

    @Override // io.reactivex.rxjava3.core.MaybeObserver
    public final void onComplete() {
        this.c.onComplete();
    }

    @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver
    public final void onError(Throwable th) {
        MaybeObserver maybeObserver = this.c;
        try {
            if (this.e.test(th)) {
                maybeObserver.onComplete();
            } else {
                maybeObserver.onError(th);
            }
        } catch (Throwable th2) {
            he1.R(th2);
            maybeObserver.onError(new CompositeException(th, th2));
        }
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
        this.c.onSuccess(obj);
    }
}
