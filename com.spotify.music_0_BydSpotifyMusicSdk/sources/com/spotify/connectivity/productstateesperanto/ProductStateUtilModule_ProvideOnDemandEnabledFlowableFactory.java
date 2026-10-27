package com.spotify.connectivity.productstateesperanto;

import com.spotify.connectivity.flags.RxFlags;
import io.reactivex.rxjava3.core.Flowable;
import p.ul0;
import p.vr2;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class ProductStateUtilModule_ProvideOnDemandEnabledFlowableFactory implements ul0 {
    private final xy1 rxFlagsProvider;

    public ProductStateUtilModule_ProvideOnDemandEnabledFlowableFactory(xy1 xy1Var) {
        this.rxFlagsProvider = xy1Var;
    }

    public static ProductStateUtilModule_ProvideOnDemandEnabledFlowableFactory create(xy1 xy1Var) {
        return new ProductStateUtilModule_ProvideOnDemandEnabledFlowableFactory(xy1Var);
    }

    public static Flowable<Boolean> provideOnDemandEnabledFlowable(RxFlags rxFlags) {
        Flowable<Boolean> flowableProvideOnDemandEnabledFlowable = ProductStateUtilModule.provideOnDemandEnabledFlowable(rxFlags);
        vr2.b(flowableProvideOnDemandEnabledFlowable);
        return flowableProvideOnDemandEnabledFlowable;
    }

    @Override // p.xy1
    public Flowable<Boolean> get() {
        return provideOnDemandEnabledFlowable((RxFlags) this.rxFlagsProvider.get());
    }
}
