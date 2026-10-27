package com.spotify.connectivity.productstateesperanto;

import com.spotify.connectivity.flags.Flags;
import com.spotify.connectivity.flags.RxFlags;
import com.spotify.connectivity.productstate.OnDemandEnabled;
import com.spotify.connectivity.productstate.ProductStateUtil;
import com.spotify.connectivity.productstate.RxProductState;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.internal.operators.flowable.d0;
import io.reactivex.rxjava3.internal.operators.flowable.h;
import p.wx1;

/* JADX INFO: loaded from: classes.dex */
public final class ProductStateUtilModule {
    @OnDemandEnabled
    public static boolean provideIsOnDemandEnabled(Flags flags) {
        return ProductStateUtil.onDemandEnabled(flags);
    }

    @OnDemandEnabled
    public static Flowable<Boolean> provideOnDemandEnabledFlowable(RxFlags rxFlags) {
        Flowable<Flags> flowableFlags = rxFlags.flags();
        wx1 wx1Var = new wx1(2);
        flowableFlags.getClass();
        return new h(new d0(flowableFlags, wx1Var, 3));
    }

    @OnDemandEnabled
    public static Observable<Boolean> provideOnDemandEnabledObservable(RxProductState rxProductState) {
        return rxProductState.productState().map(new wx1(1)).distinctUntilChanged();
    }
}
