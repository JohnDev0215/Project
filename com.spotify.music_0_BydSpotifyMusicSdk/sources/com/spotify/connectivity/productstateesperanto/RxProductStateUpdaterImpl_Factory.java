package com.spotify.connectivity.productstateesperanto;

import p.ul0;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class RxProductStateUpdaterImpl_Factory implements ul0 {
    private final xy1 productStateMethodsProvider;

    public RxProductStateUpdaterImpl_Factory(xy1 xy1Var) {
        this.productStateMethodsProvider = xy1Var;
    }

    public static RxProductStateUpdaterImpl_Factory create(xy1 xy1Var) {
        return new RxProductStateUpdaterImpl_Factory(xy1Var);
    }

    public static RxProductStateUpdaterImpl newInstance(ProductStateMethods productStateMethods) {
        return new RxProductStateUpdaterImpl(productStateMethods);
    }

    @Override // p.xy1
    public RxProductStateUpdaterImpl get() {
        return newInstance((ProductStateMethods) this.productStateMethodsProvider.get());
    }
}
