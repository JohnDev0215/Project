package io.reactivex.rxjava3.internal.operators.maybe;

import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;

/* JADX INFO: loaded from: classes.dex */
public final class h extends Observable {
    public final MaybeSource c;

    public h(MaybeSource maybeSource) {
        this.c = maybeSource;
    }

    public static MaybeObserver a(Observer observer) {
        return new MaybeToObservable$MaybeToObservableObserver(observer);
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        this.c.subscribe(new MaybeToObservable$MaybeToObservableObserver(observer));
    }
}
