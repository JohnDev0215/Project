package com.spotify.connectivity.productstateesperanto;

import com.spotify.connectivity.productstate.RxProductState;
import io.reactivex.rxjava3.core.Observable;
import java.util.Map;
import p.ul0;
import p.vr2;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class ProductStateModule_ProvideRxProductStateFactory implements ul0 {
    private final xy1 productStateProvider;

    public ProductStateModule_ProvideRxProductStateFactory(xy1 xy1Var) {
        this.productStateProvider = xy1Var;
    }

    public static ProductStateModule_ProvideRxProductStateFactory create(xy1 xy1Var) {
        return new ProductStateModule_ProvideRxProductStateFactory(xy1Var);
    }

    public static RxProductState provideRxProductState(Observable<Map<String, String>> observable) {
        RxProductState rxProductStateProvideRxProductState = ProductStateModule.provideRxProductState(observable);
        vr2.b(rxProductStateProvideRxProductState);
        return rxProductStateProvideRxProductState;
    }

    @Override // p.xy1
    public RxProductState get() {
        return provideRxProductState((Observable) this.productStateProvider.get());
    }
}
