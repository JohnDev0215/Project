package com.spotify.connectivity.productstateesperanto;

import com.google.common.base.Optional;
import io.reactivex.rxjava3.core.ObservableTransformer;
import java.util.Map;
import p.gu;
import p.u4;
import p.ul0;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class AccumulatedProductStateClient_Factory implements ul0 {
    private final xy1 accumulatorProvider;
    private final xy1 coldStartupTimeKeeperProvider;
    private final xy1 productStateMethodsProvider;

    public AccumulatedProductStateClient_Factory(xy1 xy1Var, xy1 xy1Var2, xy1 xy1Var3) {
        this.productStateMethodsProvider = xy1Var;
        this.coldStartupTimeKeeperProvider = xy1Var2;
        this.accumulatorProvider = xy1Var3;
    }

    public static AccumulatedProductStateClient_Factory create(xy1 xy1Var, xy1 xy1Var2, xy1 xy1Var3) {
        return new AccumulatedProductStateClient_Factory(xy1Var, xy1Var2, xy1Var3);
    }

    public static AccumulatedProductStateClient newInstance(ProductStateMethods productStateMethods, gu guVar, ObservableTransformer<Optional<Map<String, String>>, Map<String, String>> observableTransformer) {
        return new AccumulatedProductStateClient(productStateMethods, guVar, observableTransformer);
    }

    @Override // p.xy1
    public AccumulatedProductStateClient get() {
        ProductStateMethods productStateMethods = (ProductStateMethods) this.productStateMethodsProvider.get();
        u4.z(this.coldStartupTimeKeeperProvider.get());
        return newInstance(productStateMethods, null, (ObservableTransformer) this.accumulatorProvider.get());
    }
}
