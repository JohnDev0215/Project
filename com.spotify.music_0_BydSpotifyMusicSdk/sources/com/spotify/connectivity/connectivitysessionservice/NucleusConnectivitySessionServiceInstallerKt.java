package com.spotify.connectivity.connectivitysessionservice;

import com.google.common.base.Optional;
import com.spotify.connectivity.AnalyticsDelegate;
import com.spotify.connectivity.AuthenticatedScopeConfiguration;
import com.spotify.connectivity.connectivityapi.ConnectivityApi;
import com.spotify.connectivity.logoutanalyticsdelegate.AuthAnalyticsDelegate;
import com.spotify.connectivity.pubsub.PubSubClient;
import com.spotify.connectivity.sessionapi.SessionApi;
import com.spotify.cosmos.sharedcosmosrouterapi.SharedCosmosRouterApi;
import p.a51;
import p.py0;
import p.r10;
import p.sb2;
import p.v03;
import p.xz;

/* JADX INFO: loaded from: classes.dex */
public final class NucleusConnectivitySessionServiceInstallerKt {
    public static final a51 installConnectivitySessionService(xz xzVar, final py0 py0Var, final py0 py0Var2, final py0 py0Var3, final py0 py0Var4, final py0 py0Var5, final py0 py0Var6, final py0 py0Var7, final py0 py0Var8, final py0 py0Var9) {
        v03.j(xzVar, "contextRuntime");
        v03.j(py0Var, "coreThreadingApi");
        v03.j(py0Var2, "sharedCosmosRouterApi");
        v03.j(py0Var3, "connectivityApi");
        v03.j(py0Var4, "analyticsDelegate");
        v03.j(py0Var5, "authenticatedScopeConfiguration");
        v03.j(py0Var6, "sessionApi");
        v03.j(py0Var7, "authAnalyticsDelegate");
        v03.j(py0Var8, "pubSubClient");
        v03.j(py0Var9, "snapshotIdResolver");
        new py0() { // from class: com.spotify.connectivity.connectivitysessionservice.NucleusConnectivitySessionServiceInstallerKt.installConnectivitySessionService.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // p.py0
            public final sb2 invoke() {
                return new ConnectivitySessionService((r10) py0Var.invoke(), (SharedCosmosRouterApi) py0Var2.invoke(), (ConnectivityApi) py0Var3.invoke(), (AnalyticsDelegate) py0Var4.invoke(), (AuthenticatedScopeConfiguration) py0Var5.invoke(), (SessionApi) py0Var6.invoke(), (AuthAnalyticsDelegate) py0Var7.invoke(), (PubSubClient) py0Var8.invoke(), (Optional) py0Var9.invoke());
            }
        };
        xzVar.a();
        return null;
    }
}
