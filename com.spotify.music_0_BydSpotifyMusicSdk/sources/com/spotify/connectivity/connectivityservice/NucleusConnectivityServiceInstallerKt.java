package com.spotify.connectivity.connectivityservice;

import android.content.Context;
import com.spotify.connectivity.AnalyticsDelegate;
import com.spotify.connectivity.ApplicationScopeConfiguration;
import com.spotify.connectivity.MobileDeviceInfo;
import com.spotify.cosmos.sharedcosmosrouterapi.SharedCosmosRouterApi;
import io.reactivex.rxjava3.core.Observable;
import okhttp3.OkHttpClient;
import p.a51;
import p.n10;
import p.py0;
import p.r10;
import p.sb2;
import p.v03;
import p.xz;

/* JADX INFO: loaded from: classes.dex */
public final class NucleusConnectivityServiceInstallerKt {
    public static final a51 installConnectivityService(xz xzVar, final py0 py0Var, final py0 py0Var2, final py0 py0Var3, final py0 py0Var4, final py0 py0Var5, final py0 py0Var6, final py0 py0Var7, final py0 py0Var8, final py0 py0Var9) {
        v03.j(xzVar, "contextRuntime");
        v03.j(py0Var, "analyticsDelegate");
        v03.j(py0Var2, "coreThreadingApi");
        v03.j(py0Var3, "corePreferencesApi");
        v03.j(py0Var4, "connectivityApplicationScopeConfiguration");
        v03.j(py0Var5, "mobileDeviceInfo");
        v03.j(py0Var6, "sharedCosmosRouterApi");
        v03.j(py0Var7, "context");
        v03.j(py0Var8, "okHttpClient");
        v03.j(py0Var9, "connectionTypeObservable");
        new py0() { // from class: com.spotify.connectivity.connectivityservice.NucleusConnectivityServiceInstallerKt.installConnectivityService.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // p.py0
            public final sb2 invoke() {
                return new ConnectivityService((AnalyticsDelegate) py0Var.invoke(), (r10) py0Var2.invoke(), (n10) py0Var3.invoke(), (ApplicationScopeConfiguration) py0Var4.invoke(), (MobileDeviceInfo) py0Var5.invoke(), (SharedCosmosRouterApi) py0Var6.invoke(), (Context) py0Var7.invoke(), (OkHttpClient) py0Var8.invoke(), (Observable) py0Var9.invoke());
            }
        };
        xzVar.a();
        return null;
    }
}
