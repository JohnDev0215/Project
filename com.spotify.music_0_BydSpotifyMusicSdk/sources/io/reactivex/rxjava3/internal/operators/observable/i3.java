package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;

/* JADX INFO: loaded from: classes.dex */
public final class i3 extends Observable {
    public final int c;
    public final long e;

    public i3(int i, int i2) {
        this.c = i;
        this.e = ((long) i) + ((long) i2);
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        ObservableRange$RangeDisposable observableRange$RangeDisposable = new ObservableRange$RangeDisposable(observer, this.c, this.e);
        observer.onSubscribe(observableRange$RangeDisposable);
        if (observableRange$RangeDisposable.fused) {
            return;
        }
        Observer<? super Integer> observer2 = observableRange$RangeDisposable.downstream;
        long j = observableRange$RangeDisposable.end;
        for (long j2 = observableRange$RangeDisposable.index; j2 != j && observableRange$RangeDisposable.get() == 0; j2++) {
            observer2.onNext(Integer.valueOf((int) j2));
        }
        if (observableRange$RangeDisposable.get() == 0) {
            observableRange$RangeDisposable.lazySet(1);
            observer2.onComplete();
        }
    }
}
