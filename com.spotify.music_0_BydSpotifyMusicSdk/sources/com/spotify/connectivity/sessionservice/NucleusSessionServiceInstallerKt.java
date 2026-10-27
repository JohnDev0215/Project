package com.spotify.connectivity.sessionservice;

import com.spotify.connectivity.AnalyticsDelegate;
import com.spotify.connectivity.auth.NativeLoginControllerConfiguration;
import com.spotify.connectivity.connectivityapi.ConnectivityApi;
import p.a51;
import p.fm1;
import p.py0;
import p.q00;
import p.r10;
import p.sb2;
import p.se;
import p.ut;
import p.v03;
import p.xz;

/* JADX INFO: loaded from: classes.dex */
public final class NucleusSessionServiceInstallerKt {
    public static final a51 installSessionService(xz xzVar, final py0 py0Var, final py0 py0Var2, final py0 py0Var3, final py0 py0Var4, final py0 py0Var5, final py0 py0Var6, final py0 py0Var7, final py0 py0Var8, final py0 py0Var9) {
        v03.j(xzVar, "contextRuntime");
        v03.j(py0Var, "temporarySessionLoginFlowDependencies");
        v03.j(py0Var2, "coreThreadingApi");
        v03.j(py0Var3, "analyticsDelegate");
        v03.j(py0Var4, "connectivityApi");
        v03.j(py0Var5, "coreApi");
        v03.j(py0Var6, "loginControllerConfiguration");
        v03.j(py0Var7, "clientInfo");
        v03.j(py0Var8, "nativeLogin5OAuthClientApi");
        v03.j(py0Var9, "authDataApi");
        new py0() { // from class: com.spotify.connectivity.sessionservice.NucleusSessionServiceInstallerKt.installSessionService.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // p.py0
            public final sb2 invoke() {
                return new SessionService((TemporarySessionLoginFlowDependencies) py0Var.invoke(), (r10) py0Var2.invoke(), (AnalyticsDelegate) py0Var3.invoke(), (ConnectivityApi) py0Var4.invoke(), (q00) py0Var5.invoke(), (NativeLoginControllerConfiguration) py0Var6.invoke(), (ut) py0Var7.invoke(), (fm1) py0Var8.invoke(), (se) py0Var9.invoke());
            }
        };
        xzVar.a();
        return null;
    }
}
