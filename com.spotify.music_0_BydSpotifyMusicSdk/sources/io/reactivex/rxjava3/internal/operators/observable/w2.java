package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.Objects;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class w2 implements Observer, Disposable {
    public final Observer c;
    public final io.reactivex.rxjava3.functions.o e;
    public final io.reactivex.rxjava3.functions.o k;
    public final io.reactivex.rxjava3.functions.q l;
    public Disposable m;

    public w2(Observer observer, io.reactivex.rxjava3.functions.o oVar, io.reactivex.rxjava3.functions.o oVar2, io.reactivex.rxjava3.functions.q qVar) {
        this.c = observer;
        this.e = oVar;
        this.k = oVar2;
        this.l = qVar;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.m.dispose();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.m.isDisposed();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        Observer observer = this.c;
        try {
            Object obj = this.l.get();
            Objects.requireNonNull(obj, "The onComplete ObservableSource returned is null");
            observer.onNext((ObservableSource) obj);
            observer.onComplete();
        } catch (Throwable th) {
            he1.R(th);
            observer.onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        Observer observer = this.c;
        try {
            Object objApply = this.k.apply(th);
            Objects.requireNonNull(objApply, "The onError ObservableSource returned is null");
            observer.onNext((ObservableSource) objApply);
            observer.onComplete();
        } catch (Throwable th2) {
            he1.R(th2);
            observer.onError(new CompositeException(th, th2));
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        Observer observer = this.c;
        try {
            Object objApply = this.e.apply(obj);
            Objects.requireNonNull(objApply, "The onNext ObservableSource returned is null");
            observer.onNext((ObservableSource) objApply);
        } catch (Throwable th) {
            he1.R(th);
            observer.onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.m, disposable)) {
            this.m = disposable;
            this.c.onSubscribe(this);
        }
    }
}
