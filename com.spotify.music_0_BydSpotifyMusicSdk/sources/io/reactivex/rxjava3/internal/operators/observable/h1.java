package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class h1 implements Observer, Disposable {
    public final Observer c;
    public final io.reactivex.rxjava3.functions.g e;
    public final io.reactivex.rxjava3.functions.g k;
    public final io.reactivex.rxjava3.functions.a l;
    public final io.reactivex.rxjava3.functions.a m;
    public Disposable n;
    public boolean o;

    public h1(Observer observer, io.reactivex.rxjava3.functions.g gVar, io.reactivex.rxjava3.functions.g gVar2, io.reactivex.rxjava3.functions.a aVar, io.reactivex.rxjava3.functions.a aVar2) {
        this.c = observer;
        this.e = gVar;
        this.k = gVar2;
        this.l = aVar;
        this.m = aVar2;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.n.dispose();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.n.isDisposed();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        if (this.o) {
            return;
        }
        try {
            this.l.run();
            this.o = true;
            this.c.onComplete();
            try {
                this.m.run();
            } catch (Throwable th) {
                he1.R(th);
                RxJavaPlugins.b(th);
            }
        } catch (Throwable th2) {
            he1.R(th2);
            onError(th2);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        if (this.o) {
            RxJavaPlugins.b(th);
            return;
        }
        this.o = true;
        try {
            this.k.accept(th);
        } catch (Throwable th2) {
            he1.R(th2);
            th = new CompositeException(th, th2);
        }
        this.c.onError(th);
        try {
            this.m.run();
        } catch (Throwable th3) {
            he1.R(th3);
            RxJavaPlugins.b(th3);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        if (this.o) {
            return;
        }
        try {
            this.e.accept(obj);
            this.c.onNext(obj);
        } catch (Throwable th) {
            he1.R(th);
            this.n.dispose();
            onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.n, disposable)) {
            this.n = disposable;
            this.c.onSubscribe(this);
        }
    }
}
