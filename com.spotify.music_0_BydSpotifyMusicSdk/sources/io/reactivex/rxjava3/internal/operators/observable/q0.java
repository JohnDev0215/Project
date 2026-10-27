package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;

/* JADX INFO: loaded from: classes.dex */
public final class q0 extends Single implements io.reactivex.rxjava3.internal.fuseable.d {
    public final ObservableSource c;

    public q0(ObservableSource observableSource) {
        this.c = observableSource;
    }

    @Override // io.reactivex.rxjava3.internal.fuseable.d
    public final Observable a() {
        return new o0(this.c, 0);
    }

    @Override // io.reactivex.rxjava3.core.Single
    public final void subscribeActual(SingleObserver singleObserver) {
        this.c.subscribe(new p0(singleObserver));
    }
}
