package p;

import com.spotify.connectivity.connectivityapi.ConnectivityApi;
import com.spotify.connectivity.connectivityservice.ConnectivityService;
import com.spotify.core.coreimpl.ApplicationScopeConfiguration;
import com.spotify.core.coreimpl.NativeApplicationScopeImpl;
import com.spotify.cosmos.sharedcosmosrouterservice.SharedCosmosRouterService;
import com.spotify.eventsender.api.EventSenderCoreBridge;

/* JADX INFO: loaded from: classes.dex */
public final class q10 implements q00, sb2 {
    public final r10 a;
    public final n10 b;
    public final ApplicationScopeConfiguration c;
    public final ConnectivityApi d;
    public final EventSenderCoreBridge e;
    public NativeApplicationScopeImpl f;

    public q10(u10 u10Var, o10 o10Var, ApplicationScopeConfiguration applicationScopeConfiguration, ConnectivityService connectivityService, SharedCosmosRouterService sharedCosmosRouterService, EventSenderCoreBridge eventSenderCoreBridge) {
        v03.j(eventSenderCoreBridge, "eventSenderCoreBridge");
        this.a = u10Var;
        this.b = o10Var;
        this.c = applicationScopeConfiguration;
        this.d = connectivityService;
        this.e = eventSenderCoreBridge;
        u10Var.a.runBlocking(new p10(this, sharedCosmosRouterService));
    }

    @Override // p.sb2
    public final void shutdown() {
        ((u10) this.a).a.runBlocking(new rv(18, this));
    }
}
