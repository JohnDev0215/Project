package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.SequentialDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class e3 implements Observer {
    public final Observer c;
    public final io.reactivex.rxjava3.functions.o e;
    public final SequentialDisposable k = new SequentialDisposable();
    public boolean l;
    public boolean m;

    public e3(Observer observer, io.reactivex.rxjava3.functions.o oVar) {
        this.c = observer;
        this.e = oVar;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        if (this.m) {
            return;
        }
        this.m = true;
        this.l = true;
        this.c.onComplete();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        boolean z = this.l;
        Observer observer = this.c;
        if (z) {
            if (this.m) {
                RxJavaPlugins.b(th);
                return;
            } else {
                observer.onError(th);
                return;
            }
        }
        this.l = true;
        try {
            ObservableSource observableSource = (ObservableSource) this.e.apply(th);
            if (observableSource != null) {
                observableSource.subscribe(this);
                return;
            }
            NullPointerException nullPointerException = new NullPointerException("Observable is null");
            nullPointerException.initCause(th);
            observer.onError(nullPointerException);
        } catch (Throwable th2) {
            he1.R(th2);
            observer.onError(new CompositeException(th, th2));
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        if (this.m) {
            return;
        }
        this.c.onNext(obj);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        SequentialDisposable sequentialDisposable = this.k;
        sequentialDisposable.getClass();
        DisposableHelper.c(sequentialDisposable, disposable);
    }
}
