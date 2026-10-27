package com.spotify.connectivity.productstateesperanto;

import com.spotify.connectivity.product_state.esperanto.proto.ProductStateClient;
import p.ul0;
import p.vr2;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class ProductStateModule_ProvideProductStateMethodsFactory implements ul0 {
    private final xy1 productStateClientProvider;

    public ProductStateModule_ProvideProductStateMethodsFactory(xy1 xy1Var) {
        this.productStateClientProvider = xy1Var;
    }

    public static ProductStateModule_ProvideProductStateMethodsFactory create(xy1 xy1Var) {
        return new ProductStateModule_ProvideProductStateMethodsFactory(xy1Var);
    }

    public static ProductStateMethods provideProductStateMethods(ProductStateClient productStateClient) {
        ProductStateMethods productStateMethodsProvideProductStateMethods = ProductStateModule.provideProductStateMethods(productStateClient);
        vr2.b(productStateMethodsProvideProductStateMethods);
        return productStateMethodsProvideProductStateMethods;
    }

    @Override // p.xy1
    public ProductStateMethods get() {
        return provideProductStateMethods((ProductStateClient) this.productStateClientProvider.get());
    }
}
