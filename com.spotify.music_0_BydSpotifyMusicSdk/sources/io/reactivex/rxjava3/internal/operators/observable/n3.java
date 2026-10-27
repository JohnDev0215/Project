package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.internal.disposables.SequentialDisposable;

/* JADX INFO: loaded from: classes.dex */
public final class n3 extends a {
    public final /* synthetic */ int e;
    public final long k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ n3(ObservableSource observableSource, long j, int i) {
        super(observableSource);
        this.e = i;
        this.k = j;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        int i = this.e;
        ObservableSource observableSource = this.c;
        long j = this.k;
        switch (i) {
            case 0:
                SequentialDisposable sequentialDisposable = new SequentialDisposable();
                observer.onSubscribe(sequentialDisposable);
                new ObservableRepeat$RepeatObserver(observer, j != Long.MAX_VALUE ? j - 1 : Long.MAX_VALUE, sequentialDisposable, this.c).a();
                break;
            case 1:
                observableSource.subscribe(new i4(observer, j));
                break;
            default:
                observableSource.subscribe(new s4(observer, j));
                break;
        }
    }
}
