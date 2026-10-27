package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class p1 extends a {
    public final io.reactivex.rxjava3.functions.o e;
    public final boolean k;
    public final int l;
    public final int m;

    public p1(ObservableSource observableSource, io.reactivex.rxjava3.functions.o oVar, boolean z, int i, int i2) {
        super(observableSource);
        this.e = oVar;
        this.k = z;
        this.l = i;
        this.m = i2;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        io.reactivex.rxjava3.functions.o oVar = this.e;
        ObservableSource observableSource = this.c;
        if (he1.X(observableSource, observer, oVar)) {
            return;
        }
        observableSource.subscribe(new ObservableFlatMap$MergeObserver(this.l, this.m, observer, this.e, this.k));
    }
}
