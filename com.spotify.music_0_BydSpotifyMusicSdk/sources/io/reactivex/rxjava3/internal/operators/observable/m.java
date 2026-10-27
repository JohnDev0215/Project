package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;

/* JADX INFO: loaded from: classes.dex */
public final class m extends Single implements io.reactivex.rxjava3.internal.fuseable.d {
    public final /* synthetic */ int c;
    public final ObservableSource e;
    public final io.reactivex.rxjava3.functions.p k;

    public /* synthetic */ m(ObservableSource observableSource, io.reactivex.rxjava3.functions.p pVar, int i) {
        this.c = i;
        this.e = observableSource;
        this.k = pVar;
    }

    @Override // io.reactivex.rxjava3.internal.fuseable.d
    public final Observable a() {
        int i = this.c;
        ObservableSource observableSource = this.e;
        io.reactivex.rxjava3.functions.p pVar = this.k;
        switch (i) {
            case 0:
                return new k(observableSource, pVar, 0);
            default:
                return new k(observableSource, pVar, 1);
        }
    }

    @Override // io.reactivex.rxjava3.core.Single
    public final void subscribeActual(SingleObserver singleObserver) {
        int i = this.c;
        ObservableSource observableSource = this.e;
        io.reactivex.rxjava3.functions.p pVar = this.k;
        switch (i) {
            case 0:
                observableSource.subscribe(new l(singleObserver, pVar));
                break;
            default:
                observableSource.subscribe(new q(singleObserver, pVar));
                break;
        }
    }
}
