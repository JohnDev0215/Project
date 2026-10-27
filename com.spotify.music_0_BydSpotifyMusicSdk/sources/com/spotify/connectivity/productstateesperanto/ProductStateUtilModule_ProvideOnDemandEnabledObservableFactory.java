package com.spotify.connectivity.productstateesperanto;

import com.spotify.connectivity.productstate.RxProductState;
import io.reactivex.rxjava3.core.Observable;
import p.ul0;
import p.vr2;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class ProductStateUtilModule_ProvideOnDemandEnabledObservableFactory implements ul0 {
    private final xy1 rxProductStateProvider;

    public ProductStateUtilModule_ProvideOnDemandEnabledObservableFactory(xy1 xy1Var) {
        this.rxProductStateProvider = xy1Var;
    }

    public static ProductStateUtilModule_ProvideOnDemandEnabledObservableFactory create(xy1 xy1Var) {
        return new ProductStateUtilModule_ProvideOnDemandEnabledObservableFactory(xy1Var);
    }

    public static Observable<Boolean> provideOnDemandEnabledObservable(RxProductState rxProductState) {
        Observable<Boolean> observableProvideOnDemandEnabledObservable = ProductStateUtilModule.provideOnDemandEnabledObservable(rxProductState);
        vr2.b(observableProvideOnDemandEnabledObservable);
        return observableProvideOnDemandEnabledObservable;
    }

    @Override // p.xy1
    public Observable<Boolean> get() {
        return provideOnDemandEnabledObservable((RxProductState) this.rxProductStateProvider.get());
    }
}
