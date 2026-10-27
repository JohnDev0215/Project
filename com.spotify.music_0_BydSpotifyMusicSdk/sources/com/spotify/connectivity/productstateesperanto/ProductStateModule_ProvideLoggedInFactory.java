package com.spotify.connectivity.productstateesperanto;

import com.spotify.connectivity.sessionstate.SessionState;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import p.ul0;
import p.vr2;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class ProductStateModule_ProvideLoggedInFactory implements ul0 {
    private final xy1 sessionStateProvider;

    public ProductStateModule_ProvideLoggedInFactory(xy1 xy1Var) {
        this.sessionStateProvider = xy1Var;
    }

    public static ProductStateModule_ProvideLoggedInFactory create(xy1 xy1Var) {
        return new ProductStateModule_ProvideLoggedInFactory(xy1Var);
    }

    public static Observable<Boolean> provideLoggedIn(Flowable<SessionState> flowable) {
        Observable<Boolean> observableProvideLoggedIn = ProductStateModule.provideLoggedIn(flowable);
        vr2.b(observableProvideLoggedIn);
        return observableProvideLoggedIn;
    }

    @Override // p.xy1
    public Observable<Boolean> get() {
        return provideLoggedIn((Flowable) this.sessionStateProvider.get());
    }
}
