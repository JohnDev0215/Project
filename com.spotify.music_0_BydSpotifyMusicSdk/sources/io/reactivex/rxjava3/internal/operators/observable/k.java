package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;

/* JADX INFO: loaded from: classes.dex */
public final class k extends a {
    public final /* synthetic */ int e;
    public final io.reactivex.rxjava3.functions.p k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ k(ObservableSource observableSource, io.reactivex.rxjava3.functions.p pVar, int i) {
        super(observableSource);
        this.e = i;
        this.k = pVar;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        int i = this.e;
        ObservableSource observableSource = this.c;
        io.reactivex.rxjava3.functions.p pVar = this.k;
        switch (i) {
            case 0:
                observableSource.subscribe(new j(observer, pVar));
                break;
            case 1:
                observableSource.subscribe(new p(observer, pVar));
                break;
            case 2:
                observableSource.subscribe(new g1(observer, pVar, 1));
                break;
            case 3:
                observableSource.subscribe(new d3(observer, pVar));
                break;
            case 4:
                observableSource.subscribe(new o4(observer, pVar));
                break;
            case 5:
                observableSource.subscribe(new w4(observer, pVar));
                break;
            default:
                observableSource.subscribe(new x4(observer, pVar));
                break;
        }
    }
}
