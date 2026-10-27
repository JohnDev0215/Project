package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class n implements Disposable {
    public final Observer c;
    public final ObservableAmb$AmbInnerObserver[] e;
    public final AtomicInteger k = new AtomicInteger();

    public n(Observer observer, int i) {
        this.c = observer;
        this.e = new ObservableAmb$AmbInnerObserver[i];
    }

    public final boolean a(int i) {
        AtomicInteger atomicInteger = this.k;
        int i2 = 0;
        if (atomicInteger.get() != 0 || !atomicInteger.compareAndSet(0, i)) {
            return false;
        }
        ObservableAmb$AmbInnerObserver[] observableAmb$AmbInnerObserverArr = this.e;
        int length = observableAmb$AmbInnerObserverArr.length;
        while (i2 < length) {
            int i3 = i2 + 1;
            if (i3 != i) {
                ObservableAmb$AmbInnerObserver observableAmb$AmbInnerObserver = observableAmb$AmbInnerObserverArr[i2];
                observableAmb$AmbInnerObserver.getClass();
                DisposableHelper.a(observableAmb$AmbInnerObserver);
            }
            i2 = i3;
        }
        return true;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        AtomicInteger atomicInteger = this.k;
        if (atomicInteger.get() != -1) {
            atomicInteger.lazySet(-1);
            for (ObservableAmb$AmbInnerObserver observableAmb$AmbInnerObserver : this.e) {
                observableAmb$AmbInnerObserver.getClass();
                DisposableHelper.a(observableAmb$AmbInnerObserver);
            }
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.k.get() == -1;
    }

    public void subscribe(ObservableSource<Object>[] observableSourceArr) {
        Observer observer;
        ObservableAmb$AmbInnerObserver[] observableAmb$AmbInnerObserverArr = this.e;
        int length = observableAmb$AmbInnerObserverArr.length;
        int i = 0;
        while (true) {
            observer = this.c;
            if (i >= length) {
                break;
            }
            int i2 = i + 1;
            observableAmb$AmbInnerObserverArr[i] = new ObservableAmb$AmbInnerObserver(this, i2, observer);
            i = i2;
        }
        AtomicInteger atomicInteger = this.k;
        atomicInteger.lazySet(0);
        observer.onSubscribe(this);
        for (int i3 = 0; i3 < length && atomicInteger.get() == 0; i3++) {
            observableSourceArr[i3].subscribe(observableAmb$AmbInnerObserverArr[i3]);
        }
    }
}
