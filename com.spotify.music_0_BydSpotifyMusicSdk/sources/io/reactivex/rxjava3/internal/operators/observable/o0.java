package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;

/* JADX INFO: loaded from: classes.dex */
public final class o0 extends a {
    public final /* synthetic */ int e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ o0(ObservableSource observableSource, int i) {
        super(observableSource);
        this.e = i;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        int i = this.e;
        ObservableSource observableSource = this.c;
        switch (i) {
            case 0:
                observableSource.subscribe(new n0(observer));
                break;
            case 1:
                d1 d1Var = new d1();
                d1Var.c = observer;
                observableSource.subscribe(d1Var);
                break;
            case 2:
                observableSource.subscribe(new d2(observer));
                break;
            case 3:
                observableSource.subscribe(new e2(observer));
                break;
            case 4:
                observableSource.subscribe(new x2(observer));
                break;
            case 5:
                observableSource.subscribe(new io.reactivex.rxjava3.observers.d(observer));
                break;
            default:
                observableSource.subscribe(new t4(observer));
                break;
        }
    }
}
