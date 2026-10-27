package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;

/* JADX INFO: loaded from: classes.dex */
public final class f4 extends Single implements io.reactivex.rxjava3.internal.fuseable.d {
    public final ObservableSource c;
    public final ObservableSource e;
    public final io.reactivex.rxjava3.functions.d k;
    public final int l;

    public f4(ObservableSource observableSource, ObservableSource observableSource2, io.reactivex.rxjava3.functions.d dVar, int i) {
        this.c = observableSource;
        this.e = observableSource2;
        this.k = dVar;
        this.l = i;
    }

    @Override // io.reactivex.rxjava3.internal.fuseable.d
    public final Observable a() {
        return new d4(this.c, this.e, this.k, this.l);
    }

    @Override // io.reactivex.rxjava3.core.Single
    public final void subscribeActual(SingleObserver singleObserver) {
        ObservableSequenceEqualSingle$EqualCoordinator observableSequenceEqualSingle$EqualCoordinator = new ObservableSequenceEqualSingle$EqualCoordinator(singleObserver, this.l, this.c, this.e, this.k);
        singleObserver.onSubscribe(observableSequenceEqualSingle$EqualCoordinator);
        observableSequenceEqualSingle$EqualCoordinator.subscribe();
    }
}
