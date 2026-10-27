package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.internal.util.ErrorMode;

/* JADX INFO: loaded from: classes.dex */
public final class k0 extends a {
    public final io.reactivex.rxjava3.functions.o e;
    public final ErrorMode k;
    public final int l;
    public final int m;

    public k0(ObservableSource observableSource, io.reactivex.rxjava3.functions.o oVar, ErrorMode errorMode, int i, int i2) {
        super(observableSource);
        this.e = oVar;
        this.k = errorMode;
        this.l = i;
        this.m = i2;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        this.c.subscribe(new ObservableConcatMapEager$ConcatMapEagerMainObserver(observer, this.e, this.l, this.m, this.k));
    }
}
