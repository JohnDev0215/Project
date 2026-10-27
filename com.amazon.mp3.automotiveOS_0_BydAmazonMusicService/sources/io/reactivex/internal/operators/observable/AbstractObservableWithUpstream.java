package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;

/* JADX INFO: loaded from: classes7.dex */
abstract class AbstractObservableWithUpstream<T, U> extends Observable<U> {
    protected final ObservableSource<T> source;

    AbstractObservableWithUpstream(ObservableSource<T> observableSource) {
        this.source = observableSource;
    }
}
