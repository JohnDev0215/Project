package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class r implements SingleObserver, Disposable {
    public final SingleObserver c;
    public final io.reactivex.rxjava3.functions.g e;
    public final io.reactivex.rxjava3.functions.a k;
    public Disposable l;

    public r(SingleObserver singleObserver, io.reactivex.rxjava3.functions.g gVar, io.reactivex.rxjava3.functions.a aVar) {
        this.c = singleObserver;
        this.e = gVar;
        this.k = aVar;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        try {
            this.k.run();
        } catch (Throwable th) {
            he1.R(th);
            RxJavaPlugins.b(th);
        }
        this.l.dispose();
        this.l = DisposableHelper.c;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.l.isDisposed();
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onError(Throwable th) {
        Disposable disposable = this.l;
        DisposableHelper disposableHelper = DisposableHelper.c;
        if (disposable == disposableHelper) {
            RxJavaPlugins.b(th);
        } else {
            this.l = disposableHelper;
            this.c.onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSubscribe(Disposable disposable) {
        SingleObserver singleObserver = this.c;
        try {
            this.e.accept(disposable);
            if (DisposableHelper.g(this.l, disposable)) {
                this.l = disposable;
                singleObserver.onSubscribe(this);
            }
        } catch (Throwable th) {
            he1.R(th);
            disposable.dispose();
            this.l = DisposableHelper.c;
            singleObserver.onSubscribe(EmptyDisposable.c);
            singleObserver.onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSuccess(Object obj) {
        Disposable disposable = this.l;
        DisposableHelper disposableHelper = DisposableHelper.c;
        if (disposable != disposableHelper) {
            this.l = disposableHelper;
            this.c.onSuccess(obj);
        }
    }
}
