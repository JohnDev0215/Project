package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;

/* JADX INFO: loaded from: classes.dex */
public final class j4 extends a {
    public final /* synthetic */ int e;
    public final int k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ j4(ObservableSource observableSource, int i, int i2) {
        super(observableSource);
        this.e = i2;
        this.k = i;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        int i = this.e;
        ObservableSource observableSource = this.c;
        int i2 = this.k;
        switch (i) {
            case 0:
                observableSource.subscribe(new ObservableSkipLast$SkipLastObserver(observer, i2));
                break;
            default:
                observableSource.subscribe(new ObservableTakeLast$TakeLastObserver(observer, i2));
                break;
        }
    }
}
