package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;

/* JADX INFO: loaded from: classes.dex */
public final class n1 extends Single implements io.reactivex.rxjava3.internal.fuseable.d {
    public final ObservableSource c;
    public final long e;
    public final Object k;

    public n1(ObservableSource observableSource, long j, Object obj) {
        this.c = observableSource;
        this.e = j;
        this.k = obj;
    }

    @Override // io.reactivex.rxjava3.internal.fuseable.d
    public final Observable a() {
        return new j1(this.c, this.e, this.k, true);
    }

    @Override // io.reactivex.rxjava3.core.Single
    public final void subscribeActual(SingleObserver singleObserver) {
        this.c.subscribe(new m1(singleObserver, this.e, this.k));
    }
}
