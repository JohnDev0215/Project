package com.spotify.connectivity.productstateesperanto;

import com.spotify.connectivity.product_state.esperanto.proto.ProductStateClient;
import p.ul0;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class ProductStateMethodsImpl_Factory implements ul0 {
    private final xy1 productStateClientProvider;

    public ProductStateMethodsImpl_Factory(xy1 xy1Var) {
        this.productStateClientProvider = xy1Var;
    }

    public static ProductStateMethodsImpl_Factory create(xy1 xy1Var) {
        return new ProductStateMethodsImpl_Factory(xy1Var);
    }

    public static ProductStateMethodsImpl newInstance(ProductStateClient productStateClient) {
        return new ProductStateMethodsImpl(productStateClient);
    }

    @Override // p.xy1
    public ProductStateMethodsImpl get() {
        return newInstance((ProductStateClient) this.productStateClientProvider.get());
    }
}
