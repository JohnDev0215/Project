package com.spotify.connectivity.platformconnectiontype;

import io.reactivex.rxjava3.core.Scheduler;
import p.ul0;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class OfflineStateControllerCosmos_Factory implements ul0 {
    private final xy1 endpointProvider;
    private final xy1 mainSchedulerProvider;

    public OfflineStateControllerCosmos_Factory(xy1 xy1Var, xy1 xy1Var2) {
        this.endpointProvider = xy1Var;
        this.mainSchedulerProvider = xy1Var2;
    }

    public static OfflineStateControllerCosmos_Factory create(xy1 xy1Var, xy1 xy1Var2) {
        return new OfflineStateControllerCosmos_Factory(xy1Var, xy1Var2);
    }

    public static OfflineStateControllerCosmos newInstance(CoreConnectionState coreConnectionState, Scheduler scheduler) {
        return new OfflineStateControllerCosmos(coreConnectionState, scheduler);
    }

    @Override // p.xy1
    public OfflineStateControllerCosmos get() {
        return newInstance((CoreConnectionState) this.endpointProvider.get(), (Scheduler) this.mainSchedulerProvider.get());
    }
}
