package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;

/* JADX INFO: loaded from: classes.dex */
public final class t extends a {
    public final int e;
    public final int k;
    public final io.reactivex.rxjava3.functions.q l;

    public t(ObservableSource observableSource, int i, int i2, io.reactivex.rxjava3.functions.q qVar) {
        super(observableSource);
        this.e = i;
        this.k = i2;
        this.l = qVar;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        ObservableSource observableSource = this.c;
        io.reactivex.rxjava3.functions.q qVar = this.l;
        int i = this.k;
        int i2 = this.e;
        if (i != i2) {
            observableSource.subscribe(new ObservableBuffer$BufferSkipObserver(observer, i2, i, qVar));
            return;
        }
        s sVar = new s(observer, i2, qVar);
        if (sVar.a()) {
            observableSource.subscribe(sVar);
        }
    }
}
