package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class s1 implements Observer, Disposable {
    public final Observer c;
    public final io.reactivex.rxjava3.functions.o e;
    public Disposable k;

    public s1(Observer observer, io.reactivex.rxjava3.functions.o oVar) {
        this.c = observer;
        this.e = oVar;
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

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        Disposable disposable = this.k;
        DisposableHelper disposableHelper = DisposableHelper.c;
        if (disposable == disposableHelper) {
            return;
        }
        this.k = disposableHelper;
        this.c.onComplete();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        Disposable disposable = this.k;
        DisposableHelper disposableHelper = DisposableHelper.c;
        if (disposable == disposableHelper) {
            RxJavaPlugins.b(th);
        } else {
            this.k = disposableHelper;
            this.c.onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        if (this.k == DisposableHelper.c) {
            return;
        }
        try {
            for (Object obj2 : (Iterable) this.e.apply(obj)) {
                try {
                    try {
                        Objects.requireNonNull(obj2, "The iterator returned a null value");
                        this.c.onNext(obj2);
                    } catch (Throwable th) {
                        he1.R(th);
                        this.k.dispose();
                        onError(th);
                        return;
                    }
                } catch (Throwable th2) {
                    he1.R(th2);
                    this.k.dispose();
                    onError(th2);
                    return;
                }
            }
        } catch (Throwable th3) {
            he1.R(th3);
            this.k.dispose();
            onError(th3);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.k, disposable)) {
            this.k = disposable;
            this.c.onSubscribe(this);
        }
    }
}
