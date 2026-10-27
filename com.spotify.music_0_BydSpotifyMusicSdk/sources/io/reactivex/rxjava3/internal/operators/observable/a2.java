package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;

/* JADX INFO: loaded from: classes.dex */
public final class a2 extends a {
    public final io.reactivex.rxjava3.functions.o e;
    public final io.reactivex.rxjava3.functions.o k;
    public final int l;
    public final boolean m;

    public a2(ObservableSource observableSource, io.reactivex.rxjava3.functions.o oVar, io.reactivex.rxjava3.functions.o oVar2, int i, boolean z) {
        super(observableSource);
        this.e = oVar;
        this.k = oVar2;
        this.l = i;
        this.m = z;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        this.c.subscribe(new ObservableGroupBy$GroupByObserver(observer, this.e, this.k, this.l, this.m));
    }
}
