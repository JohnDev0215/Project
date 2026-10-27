package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;

/* JADX INFO: loaded from: classes7.dex */
abstract class AbstractObservableWithUpstream<T, U> extends Observable<U> {
    protected final ObservableSource<T> source;

    AbstractObservableWithUpstream(ObservableSource<T> source) {
        this.source = source;
    }
}
