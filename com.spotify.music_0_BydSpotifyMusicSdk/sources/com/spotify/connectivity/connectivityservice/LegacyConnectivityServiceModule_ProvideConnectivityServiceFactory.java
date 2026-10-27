package com.spotify.connectivity.connectivityservice;

import android.app.Application;
import com.spotify.connectivity.ApplicationScopeConfiguration;
import com.spotify.connectivity.MobileDeviceInfo;
import com.spotify.connectivity.connectiontype.ConnectionType;
import com.spotify.cosmos.sharedcosmosrouterapi.SharedCosmosRouterApi;
import com.spotify.eventsender.api.EventSenderCoreBridge;
import io.reactivex.rxjava3.core.Observable;
import okhttp3.OkHttpClient;
import p.em1;
import p.n10;
import p.r10;
import p.u4;
import p.ul0;
import p.vr2;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class LegacyConnectivityServiceModule_ProvideConnectivityServiceFactory implements ul0 {
    private final xy1 applicationProvider;
    private final xy1 connectionTypeObservableProvider;
    private final xy1 connectivityApplicationScopeConfigurationProvider;
    private final xy1 corePreferencesApiProvider;
    private final xy1 coreThreadingApiProvider;
    private final xy1 eventSenderCoreBridgeProvider;
    private final xy1 mobileDeviceInfoProvider;
    private final xy1 nativeLibraryProvider;
    private final xy1 okHttpClientProvider;
    private final xy1 sharedCosmosRouterApiProvider;

    public LegacyConnectivityServiceModule_ProvideConnectivityServiceFactory(xy1 xy1Var, xy1 xy1Var2, xy1 xy1Var3, xy1 xy1Var4, xy1 xy1Var5, xy1 xy1Var6, xy1 xy1Var7, xy1 xy1Var8, xy1 xy1Var9, xy1 xy1Var10) {
        this.applicationProvider = xy1Var;
        this.nativeLibraryProvider = xy1Var2;
        this.eventSenderCoreBridgeProvider = xy1Var3;
        this.okHttpClientProvider = xy1Var4;
        this.coreThreadingApiProvider = xy1Var5;
        this.corePreferencesApiProvider = xy1Var6;
        this.sharedCosmosRouterApiProvider = xy1Var7;
        this.mobileDeviceInfoProvider = xy1Var8;
        this.connectionTypeObservableProvider = xy1Var9;
        this.connectivityApplicationScopeConfigurationProvider = xy1Var10;
    }

    public static LegacyConnectivityServiceModule_ProvideConnectivityServiceFactory create(xy1 xy1Var, xy1 xy1Var2, xy1 xy1Var3, xy1 xy1Var4, xy1 xy1Var5, xy1 xy1Var6, xy1 xy1Var7, xy1 xy1Var8, xy1 xy1Var9, xy1 xy1Var10) {
        return new LegacyConnectivityServiceModule_ProvideConnectivityServiceFactory(xy1Var, xy1Var2, xy1Var3, xy1Var4, xy1Var5, xy1Var6, xy1Var7, xy1Var8, xy1Var9, xy1Var10);
    }

    public static ConnectivityService provideConnectivityService(Application application, em1 em1Var, EventSenderCoreBridge eventSenderCoreBridge, OkHttpClient okHttpClient, r10 r10Var, n10 n10Var, SharedCosmosRouterApi sharedCosmosRouterApi, MobileDeviceInfo mobileDeviceInfo, Observable<ConnectionType> observable, ApplicationScopeConfiguration applicationScopeConfiguration) {
        ConnectivityService connectivityServiceProvideConnectivityService = LegacyConnectivityServiceModule.INSTANCE.provideConnectivityService(application, em1Var, eventSenderCoreBridge, okHttpClient, r10Var, n10Var, sharedCosmosRouterApi, mobileDeviceInfo, observable, applicationScopeConfiguration);
        vr2.b(connectivityServiceProvideConnectivityService);
        return connectivityServiceProvideConnectivityService;
    }

    @Override // p.xy1
    public ConnectivityService get() {
        Application application = (Application) this.applicationProvider.get();
        u4.z(this.nativeLibraryProvider.get());
        return provideConnectivityService(application, null, (EventSenderCoreBridge) this.eventSenderCoreBridgeProvider.get(), (OkHttpClient) this.okHttpClientProvider.get(), (r10) this.coreThreadingApiProvider.get(), (n10) this.corePreferencesApiProvider.get(), (SharedCosmosRouterApi) this.sharedCosmosRouterApiProvider.get(), (MobileDeviceInfo) this.mobileDeviceInfoProvider.get(), (Observable) this.connectionTypeObservableProvider.get(), (ApplicationScopeConfiguration) this.connectivityApplicationScopeConfigurationProvider.get());
    }
}
