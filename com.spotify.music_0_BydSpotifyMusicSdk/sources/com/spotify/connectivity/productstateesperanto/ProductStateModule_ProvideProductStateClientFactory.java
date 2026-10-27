package com.spotify.connectivity.productstateesperanto;

import com.spotify.connectivity.product_state.esperanto.proto.ProductStateClient;
import com.spotify.cosmos.rxrouter.RxRouter;
import p.ul0;
import p.vr2;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class ProductStateModule_ProvideProductStateClientFactory implements ul0 {
    private final xy1 rxRouterProvider;

    public ProductStateModule_ProvideProductStateClientFactory(xy1 xy1Var) {
        this.rxRouterProvider = xy1Var;
    }

    public static ProductStateModule_ProvideProductStateClientFactory create(xy1 xy1Var) {
        return new ProductStateModule_ProvideProductStateClientFactory(xy1Var);
    }

    public static ProductStateClient provideProductStateClient(RxRouter rxRouter) {
        ProductStateClient productStateClientProvideProductStateClient = ProductStateModule.provideProductStateClient(rxRouter);
        vr2.b(productStateClientProvideProductStateClient);
        return productStateClientProvideProductStateClient;
    }

    @Override // p.xy1
    public ProductStateClient get() {
        return provideProductStateClient((RxRouter) this.rxRouterProvider.get());
    }
}
