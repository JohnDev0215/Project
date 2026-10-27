package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;

/* JADX INFO: loaded from: classes.dex */
public final class m0 extends Observable {
    public final SingleSource c;

    public m0(SingleSource singleSource) {
        this.c = singleSource;
    }

    public static SingleObserver a(Observer observer) {
        return new SingleToObservable$SingleToObservableObserver(observer);
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        this.c.subscribe(new SingleToObservable$SingleToObservableObserver(observer));
    }
}
