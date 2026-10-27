package com.spotify.connectivity.productstateesperanto;

import io.reactivex.rxjava3.core.Observable;
import java.util.Map;
import p.ul0;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class RxProductStateImpl_Factory implements ul0 {
    private final xy1 productStateProvider;

    public RxProductStateImpl_Factory(xy1 xy1Var) {
        this.productStateProvider = xy1Var;
    }

    public static RxProductStateImpl_Factory create(xy1 xy1Var) {
        return new RxProductStateImpl_Factory(xy1Var);
    }

    public static RxProductStateImpl newInstance(Observable<Map<String, String>> observable) {
        return new RxProductStateImpl(observable);
    }

    @Override // p.xy1
    public RxProductStateImpl get() {
        return newInstance((Observable) this.productStateProvider.get());
    }
}
