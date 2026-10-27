package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;

/* JADX INFO: loaded from: classes.dex */
public final class j1 extends a {
    public final long e;
    public final Object k;
    public final boolean l;

    public j1(ObservableSource observableSource, long j, Object obj, boolean z) {
        super(observableSource);
        this.e = j;
        this.k = obj;
        this.l = z;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        this.c.subscribe(new i1(observer, this.e, this.k, this.l));
    }
}
