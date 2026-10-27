package com.spotify.connectivity.productstateesperanto;

import p.ul0;

/* JADX INFO: loaded from: classes.dex */
public final class ProductStateAccumulator_Factory implements ul0 {

    public static final class InstanceHolder {
        private static final ProductStateAccumulator_Factory INSTANCE = new ProductStateAccumulator_Factory();

        private InstanceHolder() {
        }
    }

    public static ProductStateAccumulator_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static ProductStateAccumulator newInstance() {
        return new ProductStateAccumulator();
    }

    @Override // p.xy1
    public ProductStateAccumulator get() {
        return newInstance();
    }
}
