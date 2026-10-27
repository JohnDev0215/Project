package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;

/* JADX INFO: loaded from: classes.dex */
public final class j3 extends Observable {
    public final long c;
    public final long e;

    public j3(long j, long j2) {
        this.c = j;
        this.e = j2;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        long j = this.c;
        ObservableRangeLong$RangeDisposable observableRangeLong$RangeDisposable = new ObservableRangeLong$RangeDisposable(observer, j, j + this.e);
        observer.onSubscribe(observableRangeLong$RangeDisposable);
        if (observableRangeLong$RangeDisposable.fused) {
            return;
        }
        Observer<? super Long> observer2 = observableRangeLong$RangeDisposable.downstream;
        long j2 = observableRangeLong$RangeDisposable.end;
        for (long j3 = observableRangeLong$RangeDisposable.index; j3 != j2 && observableRangeLong$RangeDisposable.get() == 0; j3++) {
            observer2.onNext(Long.valueOf(j3));
        }
        if (observableRangeLong$RangeDisposable.get() == 0) {
            observableRangeLong$RangeDisposable.lazySet(1);
            observer2.onComplete();
        }
    }
}
