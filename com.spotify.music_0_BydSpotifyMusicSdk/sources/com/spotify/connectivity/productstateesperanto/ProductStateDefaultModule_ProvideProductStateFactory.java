package com.spotify.connectivity.productstateesperanto;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import java.util.Map;
import p.gu;
import p.u4;
import p.ul0;
import p.vr2;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class ProductStateDefaultModule_ProvideProductStateFactory implements ul0 {
    private final xy1 mColdStartupTimeKeeperProvider;
    private final xy1 mainThreadProvider;
    private final xy1 productStateClientProvider;

    public ProductStateDefaultModule_ProvideProductStateFactory(xy1 xy1Var, xy1 xy1Var2, xy1 xy1Var3) {
        this.productStateClientProvider = xy1Var;
        this.mainThreadProvider = xy1Var2;
        this.mColdStartupTimeKeeperProvider = xy1Var3;
    }

    public static ProductStateDefaultModule_ProvideProductStateFactory create(xy1 xy1Var, xy1 xy1Var2, xy1 xy1Var3) {
        return new ProductStateDefaultModule_ProvideProductStateFactory(xy1Var, xy1Var2, xy1Var3);
    }

    public static Observable<Map<String, String>> provideProductState(LoggedInProductStateClient loggedInProductStateClient, Scheduler scheduler, gu guVar) {
        Observable<Map<String, String>> observableProvideProductState = ProductStateDefaultModule.provideProductState(loggedInProductStateClient, scheduler, guVar);
        vr2.b(observableProvideProductState);
        return observableProvideProductState;
    }

    @Override // p.xy1
    public Observable<Map<String, String>> get() {
        LoggedInProductStateClient loggedInProductStateClient = (LoggedInProductStateClient) this.productStateClientProvider.get();
        Scheduler scheduler = (Scheduler) this.mainThreadProvider.get();
        u4.z(this.mColdStartupTimeKeeperProvider.get());
        return provideProductState(loggedInProductStateClient, scheduler, null);
    }
}
