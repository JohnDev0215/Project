package com.spotify.connectivity.connectiontype;

import io.reactivex.rxjava3.core.Observable;
import p.ul0;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class RxConnectionStateImpl_Factory implements ul0 {
    private final xy1 connectionStateProvider;

    public RxConnectionStateImpl_Factory(xy1 xy1Var) {
        this.connectionStateProvider = xy1Var;
    }

    public static RxConnectionStateImpl_Factory create(xy1 xy1Var) {
        return new RxConnectionStateImpl_Factory(xy1Var);
    }

    public static RxConnectionStateImpl newInstance(Observable<ConnectionState> observable) {
        return new RxConnectionStateImpl(observable);
    }

    @Override // p.xy1
    public RxConnectionStateImpl get() {
        return newInstance((Observable) this.connectionStateProvider.get());
    }
}
