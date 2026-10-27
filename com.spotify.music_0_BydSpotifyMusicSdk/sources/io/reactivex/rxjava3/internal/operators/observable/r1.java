package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;

/* JADX INFO: loaded from: classes.dex */
public final class r1 extends Completable implements io.reactivex.rxjava3.internal.fuseable.d {
    public final ObservableSource c;
    public final io.reactivex.rxjava3.functions.o e;
    public final boolean k;

    public r1(ObservableSource observableSource, io.reactivex.rxjava3.functions.o oVar, boolean z) {
        this.c = observableSource;
        this.e = oVar;
        this.k = z;
    }

    @Override // io.reactivex.rxjava3.internal.fuseable.d
    public final Observable a() {
        return new q1(this.c, this.e, this.k, 0);
    }

    @Override // io.reactivex.rxjava3.core.Completable
    public final void d(CompletableObserver completableObserver) {
        this.c.subscribe(new ObservableFlatMapCompletableCompletable$FlatMapCompletableMainObserver(completableObserver, this.e, this.k));
    }
}
