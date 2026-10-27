package com.spotify.connectivity.connectivityservice;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import com.spotify.connectivity.ApplicationScopeConfiguration;
import com.spotify.connectivity.MobileDeviceInfo;
import com.spotify.connectivity.connectiontype.ConnectionType;
import com.spotify.connectivity.eventsenderanalyticsdelegate.EventSenderAnalyticsDelegate;
import com.spotify.cosmos.sharedcosmosrouterapi.SharedCosmosRouterApi;
import com.spotify.eventsender.api.EventSenderCoreBridge;
import io.reactivex.rxjava3.core.Observable;
import java.util.Arrays;
import java.util.Locale;
import okhttp3.OkHttpClient;
import p.em1;
import p.f60;
import p.kk;
import p.n;
import p.n10;
import p.r10;
import p.ut;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class LegacyConnectivityServiceModule {
    public static final LegacyConnectivityServiceModule INSTANCE = new LegacyConnectivityServiceModule();

    private LegacyConnectivityServiceModule() {
    }

    public final ApplicationScopeConfiguration provideConnectivityApplicationScopeConfiguration(ut utVar, String str, String str2) {
        v03.j(utVar, "clientInfo");
        v03.j(str, "cachePath");
        v03.j(str2, "language");
        ApplicationScopeConfiguration applicationScopeConfiguration = new ApplicationScopeConfiguration();
        applicationScopeConfiguration.setClientId("1fd85409eb8e441fb783f60caf2fdaab");
        applicationScopeConfiguration.setCachePath(str);
        n nVar = (n) utVar;
        applicationScopeConfiguration.setDeviceId(nVar.a);
        String str3 = Build.MODEL;
        applicationScopeConfiguration.setDeviceHardwareModel(str3);
        applicationScopeConfiguration.setClientRevision(kk.a);
        applicationScopeConfiguration.setClientVersionLong("4.4.1.0");
        applicationScopeConfiguration.setAccesspointLanguage(str2);
        String str4 = String.format(Locale.ROOT, "Spotify/%s %s/%d (%s)", Arrays.copyOf(new Object[]{nVar.a(), str3, Integer.valueOf(Build.VERSION.SDK_INT), str3}, 4));
        v03.i(str4, "format(locale, format, *args)");
        applicationScopeConfiguration.setDefaultHTTPUserAgent(str4);
        return applicationScopeConfiguration;
    }

    public final ConnectivityService provideConnectivityService(Application application, em1 em1Var, EventSenderCoreBridge eventSenderCoreBridge, OkHttpClient okHttpClient, r10 r10Var, n10 n10Var, SharedCosmosRouterApi sharedCosmosRouterApi, MobileDeviceInfo mobileDeviceInfo, Observable<ConnectionType> observable, ApplicationScopeConfiguration applicationScopeConfiguration) {
        v03.j(application, "application");
        v03.j(em1Var, "nativeLibrary");
        v03.j(eventSenderCoreBridge, "eventSenderCoreBridge");
        v03.j(okHttpClient, "okHttpClient");
        v03.j(r10Var, "coreThreadingApi");
        v03.j(n10Var, "corePreferencesApi");
        v03.j(sharedCosmosRouterApi, "sharedCosmosRouterApi");
        v03.j(mobileDeviceInfo, "mobileDeviceInfo");
        v03.j(observable, "connectionTypeObservable");
        v03.j(applicationScopeConfiguration, "connectivityApplicationScopeConfiguration");
        f60.h();
        em1Var.a();
        EventSenderAnalyticsDelegate eventSenderAnalyticsDelegate = new EventSenderAnalyticsDelegate(eventSenderCoreBridge);
        Context applicationContext = application.getApplicationContext();
        v03.i(applicationContext, "application.applicationContext");
        return new ConnectivityService(eventSenderAnalyticsDelegate, r10Var, n10Var, applicationScopeConfiguration, mobileDeviceInfo, sharedCosmosRouterApi, applicationContext, okHttpClient, observable);
    }
}
