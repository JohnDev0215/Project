package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class r4 extends a {
    public final io.reactivex.rxjava3.functions.o e;
    public final int k;
    public final boolean l;

    public r4(ObservableSource observableSource, io.reactivex.rxjava3.functions.o oVar, int i, boolean z) {
        super(observableSource);
        this.e = oVar;
        this.k = i;
        this.l = z;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        ObservableSource observableSource = this.c;
        io.reactivex.rxjava3.functions.o oVar = this.e;
        if (he1.X(observableSource, observer, oVar)) {
            return;
        }
        observableSource.subscribe(new ObservableSwitchMap$SwitchMapObserver(observer, oVar, this.k, this.l));
    }
}
