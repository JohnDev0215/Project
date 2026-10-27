package com.spotify.connectivity.platformconnectiontype;

import com.spotify.connectivity.connectiontype.OfflineStateController;
import io.reactivex.rxjava3.core.Scheduler;
import p.ul0;
import p.vr2;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectionTypeModule_ProvideOfflineStateControllerFactory implements ul0 {
    private final xy1 endpointProvider;
    private final xy1 mainSchedulerProvider;

    public ConnectionTypeModule_ProvideOfflineStateControllerFactory(xy1 xy1Var, xy1 xy1Var2) {
        this.endpointProvider = xy1Var;
        this.mainSchedulerProvider = xy1Var2;
    }

    public static ConnectionTypeModule_ProvideOfflineStateControllerFactory create(xy1 xy1Var, xy1 xy1Var2) {
        return new ConnectionTypeModule_ProvideOfflineStateControllerFactory(xy1Var, xy1Var2);
    }

    public static OfflineStateController provideOfflineStateController(CoreConnectionState coreConnectionState, Scheduler scheduler) {
        OfflineStateController offlineStateControllerProvideOfflineStateController = ConnectionTypeModule.provideOfflineStateController(coreConnectionState, scheduler);
        vr2.b(offlineStateControllerProvideOfflineStateController);
        return offlineStateControllerProvideOfflineStateController;
    }

    @Override // p.xy1
    public OfflineStateController get() {
        return provideOfflineStateController((CoreConnectionState) this.endpointProvider.get(), (Scheduler) this.mainSchedulerProvider.get());
    }
}
