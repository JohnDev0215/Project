package rx.observables;

import rx.Observable;

/* JADX INFO: loaded from: classes8.dex */
public abstract class ConnectableObservable<T> extends Observable<T> {
    protected ConnectableObservable(Observable.OnSubscribe<T> onSubscribe) {
        super(onSubscribe);
    }
}
