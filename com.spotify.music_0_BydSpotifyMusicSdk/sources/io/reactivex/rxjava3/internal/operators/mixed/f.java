package io.reactivex.rxjava3.internal.operators.mixed;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.functions.o;
import io.reactivex.rxjava3.internal.util.ErrorMode;
import p.vr2;

/* JADX INFO: loaded from: classes.dex */
public final class f extends Observable {
    public final /* synthetic */ int c;
    public final o e;
    public final ErrorMode k;
    public final int l;
    public final ObservableSource m;

    public /* synthetic */ f(ObservableSource observableSource, o oVar, ErrorMode errorMode, int i, int i2) {
        this.c = i2;
        this.m = observableSource;
        this.e = oVar;
        this.k = errorMode;
        this.l = i;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        int i = this.c;
        ErrorMode errorMode = this.k;
        int i2 = this.l;
        o oVar = this.e;
        ObservableSource observableSource = this.m;
        switch (i) {
            case 0:
                Observable observable = (Observable) observableSource;
                if (!vr2.v(observable, oVar, observer)) {
                    observable.subscribe(new ObservableConcatMapMaybe$ConcatMapMaybeMainObserver(observer, oVar, i2, errorMode));
                }
                break;
            default:
                if (!vr2.w(observableSource, oVar, observer)) {
                    observableSource.subscribe(new ObservableConcatMapSingle$ConcatMapSingleMainObserver(observer, oVar, i2, errorMode));
                }
                break;
        }
    }
}
