package com.spotify.connectivity.productstateesperanto;

import com.google.common.base.Optional;
import com.spotify.connectivity.product_state.esperanto.proto.ProductStateClient;
import com.spotify.connectivity.product_state.esperanto.proto.ProductStateEsperantoKt;
import com.spotify.connectivity.productstate.RxProductState;
import com.spotify.connectivity.productstate.RxProductStateUpdater;
import com.spotify.connectivity.sessionstate.SessionState;
import com.spotify.cosmos.rxrouter.RxRouter;
import com.spotify.esperanto.esperantocosmos.CosmosTransport;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.internal.operators.flowable.d0;
import java.util.Map;
import java.util.Objects;
import p.sx;
import p.wx1;

/* JADX INFO: loaded from: classes.dex */
public interface ProductStateModule {
    static Observable<Boolean> provideLoggedIn(Flowable<SessionState> flowable) {
        wx1 wx1Var = new wx1(0);
        flowable.getClass();
        return new d0(flowable, wx1Var, 3).n();
    }

    static ProductStateClient provideProductStateClient(RxRouter rxRouter) {
        Objects.requireNonNull(rxRouter);
        return ProductStateEsperantoKt.createProductStateClient(new CosmosTransport(new sx(rxRouter, 1)));
    }

    static ProductStateMethods provideProductStateMethods(ProductStateClient productStateClient) {
        return new ProductStateMethodsImpl(productStateClient);
    }

    static RxProductState provideRxProductState(Observable<Map<String, String>> observable) {
        return new RxProductStateImpl(observable);
    }

    ObservableTransformer<Optional<Map<String, String>>, Map<String, String>> bindProductStateAccumulator(ProductStateAccumulator productStateAccumulator);

    RxProductStateUpdater bindRxProductStateUpdater(RxProductStateUpdaterImpl rxProductStateUpdaterImpl);
}
