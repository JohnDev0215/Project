package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.internal.disposables.ArrayCompositeDisposable;

/* JADX INFO: loaded from: classes.dex */
public final class n4 extends a {
    public final /* synthetic */ int e;
    public final ObservableSource k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ n4(ObservableSource observableSource, ObservableSource observableSource2, int i) {
        super(observableSource);
        this.e = i;
        this.k = observableSource2;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        int i = this.e;
        ObservableSource observableSource = this.c;
        ObservableSource observableSource2 = this.k;
        switch (i) {
            case 0:
                io.reactivex.rxjava3.observers.d dVar = new io.reactivex.rxjava3.observers.d(observer);
                ArrayCompositeDisposable arrayCompositeDisposable = new ArrayCompositeDisposable(2);
                dVar.onSubscribe(arrayCompositeDisposable);
                m4 m4Var = new m4(dVar, arrayCompositeDisposable);
                observableSource2.subscribe(new l4(arrayCompositeDisposable, m4Var, dVar));
                observableSource.subscribe(m4Var);
                break;
            default:
                b1 b1Var = new b1(observableSource2, observer);
                observer.onSubscribe(b1Var.k);
                observableSource.subscribe(b1Var);
                break;
        }
    }
}
