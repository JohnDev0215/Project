package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.internal.util.ErrorMode;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class j0 extends a {
    public final /* synthetic */ int e = 1;
    public final io.reactivex.rxjava3.functions.o k;
    public final int l;
    public final Object m;

    public j0(ObservableSource observableSource, io.reactivex.rxjava3.functions.o oVar, int i, ErrorMode errorMode) {
        super(observableSource);
        this.k = oVar;
        this.m = errorMode;
        this.l = Math.max(8, i);
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        int i = this.e;
        ObservableSource observableSource = this.c;
        int i2 = this.l;
        io.reactivex.rxjava3.functions.o oVar = this.k;
        Object obj = this.m;
        switch (i) {
            case 0:
                if (!he1.X(observableSource, observer, oVar)) {
                    ErrorMode errorMode = (ErrorMode) obj;
                    if (errorMode != ErrorMode.c) {
                        observableSource.subscribe(new ObservableConcatMap$ConcatMapDelayErrorObserver(observer, oVar, i2, errorMode == ErrorMode.k));
                    } else {
                        observableSource.subscribe(new ObservableConcatMap$SourceObserver(new io.reactivex.rxjava3.observers.d(observer), oVar, i2));
                    }
                    break;
                }
                break;
            default:
                observableSource.subscribe(new ObservableWindowBoundarySelector$WindowBoundaryMainObserver(observer, (ObservableSource) obj, oVar, i2));
                break;
        }
    }

    public j0(ObservableSource observableSource, ObservableSource observableSource2, io.reactivex.rxjava3.functions.o oVar, int i) {
        super(observableSource);
        this.m = observableSource2;
        this.k = oVar;
        this.l = i;
    }
}
