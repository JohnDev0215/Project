package p;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import com.google.common.base.Optional;
import com.spotify.concurrency.asyncimpl.NativeTimerManagerThreadImpl;
import com.spotify.connectivity.ApplicationScopeConfiguration;
import com.spotify.connectivity.AuthenticatedScopeConfiguration;
import com.spotify.connectivity.MobileDeviceInfo;
import com.spotify.connectivity.WebgateUserAgentPlatform;
import com.spotify.connectivity.auth.NativeLoginControllerConfiguration;
import com.spotify.connectivity.connectivityservice.ConnectivityService;
import com.spotify.connectivity.connectivitysessionservice.ConnectivitySessionService;
import com.spotify.connectivity.eventsenderanalyticsdelegate.EventSenderAnalyticsDelegate;
import com.spotify.connectivity.sessionapi.SharedNativeSession;
import com.spotify.connectivity.sessionservice.SessionService;
import com.spotify.core.corefullimpl.FullAuthenticatedScopeConfiguration;
import com.spotify.core.corelimitedimpl.LimitedAuthenticatedScopeConfiguration;
import com.spotify.cosmos.cosmosimpl.NativeRouter;
import com.spotify.cosmos.queuingrouter.QueuingRemoteNativeRouter;
import com.spotify.cosmos.queuingrouter.QueuingResolveCallback;
import com.spotify.cosmos.servicebasedrouter.RemoteNativeRxRouter;
import com.spotify.cosmos.sharedcosmosrouterservice.SharedCosmosRouterService;
import com.spotify.eventsender.api.EventSenderCoreBridge;
import com.spotify.logging.logging.Logging;
import com.spotify.playbacknative.AudioDriver;
import io.reactivex.rxjava3.core.Observable;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import kotlin.text.Regex;
import okhttp3.OkHttpClient;

/* JADX INFO: loaded from: classes.dex */
public final class d10 implements y00 {
    public final Context a;
    public final ut b;
    public final m10 c;
    public final zy2 d;
    public final EventSenderCoreBridge e;
    public final MobileDeviceInfo f;
    public final a42 g;
    public final OkHttpClient h;
    public final Observable i;
    public final boolean j = true;
    public final k10 k;
    public u10 l;
    public o10 m;
    public SharedCosmosRouterService n;
    public ConnectivityService o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public SessionService f113p;
    public q10 q;
    public SharedNativeSession r;
    public ConnectivitySessionService s;
    public c32 t;
    public hd2 u;
    public wr2 v;
    public sb2 w;

    public d10(Application application, ut utVar, m10 m10Var, zy2 zy2Var, EventSenderCoreBridge eventSenderCoreBridge, MobileDeviceInfo mobileDeviceInfo, a42 a42Var, OkHttpClient okHttpClient, Observable observable, k10 k10Var) {
        this.a = application;
        this.b = utVar;
        this.c = m10Var;
        this.d = zy2Var;
        this.e = eventSenderCoreBridge;
        this.f = mobileDeviceInfo;
        this.g = a42Var;
        this.h = okHttpClient;
        this.i = observable;
        this.k = k10Var;
    }

    public static final void a(d10 d10Var) {
        u10 u10Var = d10Var.l;
        v03.h(u10Var);
        if (!u10Var.a.isCurrentThread()) {
            throw new IllegalStateException("destroySessionServices should always be called on the core thread");
        }
        if (d10Var.s != null) {
            sb2 sb2Var = d10Var.w;
            v03.h(sb2Var);
            sb2Var.shutdown();
            d10Var.w = null;
            wr2 wr2Var = d10Var.v;
            v03.h(wr2Var);
            wr2Var.shutdown();
            d10Var.v = null;
            ConnectivitySessionService connectivitySessionService = d10Var.s;
            v03.h(connectivitySessionService);
            connectivitySessionService.shutdown();
            d10Var.s = null;
            SessionService sessionService = d10Var.f113p;
            v03.h(sessionService);
            sessionService.shutdown();
            d10Var.f113p = null;
            SharedNativeSession sharedNativeSession = d10Var.r;
            if (sharedNativeSession != null) {
                sharedNativeSession.release();
            }
            d10Var.r = null;
        }
    }

    public final void b() {
        NativeTimerManagerThreadImpl nativeTimerManagerThreadImpl;
        if (this.q == null) {
            lc.c("Attempted to destroy un-started Core");
            return;
        }
        zy2 zy2Var = this.d;
        if (zy2Var.b == null) {
            throw new IllegalStateException("Not started".toString());
        }
        Handler handler = zy2Var.c;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        HandlerThread handlerThread = zy2Var.d;
        if (handlerThread != null) {
            handlerThread.quit();
        }
        zy2Var.a.onNext(Optional.a());
        zy2Var.b = null;
        u10 u10Var = this.l;
        if (u10Var != null && (nativeTimerManagerThreadImpl = u10Var.a) != null) {
            nativeTimerManagerThreadImpl.runBlocking(new rv(20, this));
        }
        q10 q10Var = this.q;
        if (q10Var != null) {
            q10Var.shutdown();
        }
        this.q = null;
        ConnectivityService connectivityService = this.o;
        if (connectivityService != null) {
            connectivityService.shutdown();
        }
        this.o = null;
        hd2 hd2Var = this.u;
        if (hd2Var != null) {
            hd2Var.shutdown();
        }
        this.u = null;
        c32 c32Var = this.t;
        if (c32Var != null) {
            c32Var.shutdown();
        }
        this.t = null;
        SharedCosmosRouterService sharedCosmosRouterService = this.n;
        if (sharedCosmosRouterService != null) {
            sharedCosmosRouterService.shutdown();
        }
        this.n = null;
        o10 o10Var = this.m;
        if (o10Var != null) {
            o10Var.shutdown();
        }
        this.m = null;
        u10 u10Var2 = this.l;
        if (u10Var2 != null) {
            u10Var2.shutdown();
        }
        this.l = null;
        Logging.Companion.deinitLogging();
    }

    public final void c() {
        List list;
        if (this.q != null) {
            lc.c("Attempted to re-start Core without destroying it first");
            return;
        }
        r60 r60Var = (r60) this.c;
        r60Var.getClass();
        l10 l10Var = (l10) Optional.e(r60Var).c();
        Logging.Companion.initLogging(true);
        this.l = new u10();
        this.m = new o10();
        NativeRouter nativeRouter = new NativeRouter();
        QueuingRemoteNativeRouter queuingRemoteNativeRouter = new QueuingRemoteNativeRouter(nativeRouter, new QueuingResolveCallback(nativeRouter));
        u10 u10Var = this.l;
        v03.h(u10Var);
        this.n = new SharedCosmosRouterService(u10Var, queuingRemoteNativeRouter);
        v03.i(l10Var, "corePaths");
        FullAuthenticatedScopeConfiguration fullAuthenticatedScopeConfiguration = new FullAuthenticatedScopeConfiguration(null, null, null, null, null, null, null, null, null, null, 0, null, null, 0, null, null, 0, null, false, 524287, null);
        r60 r60Var2 = (r60) l10Var;
        fullAuthenticatedScopeConfiguration.setCachePath(r60Var2.a().getAbsolutePath());
        Context context = r60Var2.a;
        fullAuthenticatedScopeConfiguration.setVolatileCachePath(zb0.i(context.getCacheDir(), "core-volatile").getAbsolutePath());
        fullAuthenticatedScopeConfiguration.setSettingsPath(r60Var2.b().getAbsolutePath());
        ut utVar = this.b;
        fullAuthenticatedScopeConfiguration.setDeviceId(((n) utVar).a);
        int i = kk.a;
        fullAuthenticatedScopeConfiguration.setVersionNumber(i);
        fullAuthenticatedScopeConfiguration.setVersionName("4.4.1.0");
        utVar.getClass();
        fullAuthenticatedScopeConfiguration.setClientId("1fd85409eb8e441fb783f60caf2fdaab");
        fullAuthenticatedScopeConfiguration.setNativeBundleId("full");
        MobileDeviceInfo mobileDeviceInfo = this.f;
        fullAuthenticatedScopeConfiguration.setDeviceType(mobileDeviceInfo.getDeviceType());
        fullAuthenticatedScopeConfiguration.setDeviceName(mobileDeviceInfo.getName());
        fullAuthenticatedScopeConfiguration.setModel(mobileDeviceInfo.getModel());
        fullAuthenticatedScopeConfiguration.setDescription(mobileDeviceInfo.getModel());
        fullAuthenticatedScopeConfiguration.setDescriptionShort(mobileDeviceInfo.getModel());
        fullAuthenticatedScopeConfiguration.setBrand(mobileDeviceInfo.getBrand());
        WebgateUserAgentPlatform.Companion companion = WebgateUserAgentPlatform.Companion;
        fullAuthenticatedScopeConfiguration.setDeviceModelName(companion.android());
        fullAuthenticatedScopeConfiguration.setCanPlay(true);
        k10 k10Var = this.k;
        fullAuthenticatedScopeConfiguration.setSupportedContentTypes((k10Var == null || (list = k10Var.a) == null) ? null : (String[]) list.toArray(new String[0]));
        LimitedAuthenticatedScopeConfiguration limitedAuthenticatedScopeConfiguration = new LimitedAuthenticatedScopeConfiguration(null, null, null, null, null, null, null, null, 0, null, null, 0, null, null, 0, null, AudioDriver.SPOTIFY_MAX_VOLUME, null);
        limitedAuthenticatedScopeConfiguration.setCachePath(r60Var2.a().getAbsolutePath());
        limitedAuthenticatedScopeConfiguration.setVolatileCachePath(zb0.i(context.getCacheDir(), "core-volatile").getAbsolutePath());
        limitedAuthenticatedScopeConfiguration.setSettingsPath(r60Var2.b().getAbsolutePath());
        n nVar = (n) utVar;
        String str = nVar.a;
        limitedAuthenticatedScopeConfiguration.setDeviceId(str);
        limitedAuthenticatedScopeConfiguration.setVersionNumber(i);
        limitedAuthenticatedScopeConfiguration.setVersionName("4.4.1.0");
        limitedAuthenticatedScopeConfiguration.setClientId("1fd85409eb8e441fb783f60caf2fdaab");
        limitedAuthenticatedScopeConfiguration.setNativeBundleId("limited");
        limitedAuthenticatedScopeConfiguration.setDeviceType(mobileDeviceInfo.getDeviceType());
        limitedAuthenticatedScopeConfiguration.setDeviceName(mobileDeviceInfo.getName());
        limitedAuthenticatedScopeConfiguration.setDescription(mobileDeviceInfo.getModel());
        limitedAuthenticatedScopeConfiguration.setDescriptionShort(mobileDeviceInfo.getModel());
        limitedAuthenticatedScopeConfiguration.setDeviceModelName(companion.android());
        ApplicationScopeConfiguration applicationScopeConfiguration = new ApplicationScopeConfiguration();
        applicationScopeConfiguration.setClientId("1fd85409eb8e441fb783f60caf2fdaab");
        applicationScopeConfiguration.setCachePath(r60Var2.a().getAbsolutePath());
        applicationScopeConfiguration.setDeviceId(str);
        String str2 = Build.MODEL;
        applicationScopeConfiguration.setDeviceHardwareModel(str2);
        applicationScopeConfiguration.setClientRevision(i);
        applicationScopeConfiguration.setClientVersionLong("4.4.1.0");
        Context context2 = this.a;
        v03.j(context2, "context");
        String strA = d7.a("_", new ac1(new cc1(yw.a(context2.getResources().getConfiguration()))).b(0));
        v03.j(strA, "locale");
        applicationScopeConfiguration.setAccesspointLanguage(new Regex("_+").b(strA, "-"));
        Locale locale = Locale.ENGLISH;
        String strA2 = nVar.a();
        int i2 = Build.VERSION.SDK_INT;
        String str3 = String.format(locale, "Spotify/%s %s/%d (%s)", Arrays.copyOf(new Object[]{strA2, str2, Integer.valueOf(i2), str2}, 4));
        v03.i(str3, "format(locale, format, *args)");
        applicationScopeConfiguration.setDefaultHTTPUserAgent(str3);
        applicationScopeConfiguration.setEnableClientToken(false);
        applicationScopeConfiguration.setEncryptedPersistedClientToken(null);
        NativeLoginControllerConfiguration nativeLoginControllerConfiguration = new NativeLoginControllerConfiguration();
        nativeLoginControllerConfiguration.setClientID(applicationScopeConfiguration.getClientId());
        nativeLoginControllerConfiguration.setCachePath(applicationScopeConfiguration.getCachePath());
        nativeLoginControllerConfiguration.setDeviceId(applicationScopeConfiguration.getDeviceId());
        nativeLoginControllerConfiguration.setDeviceHardwareModel(applicationScopeConfiguration.getDeviceHardwareModel());
        nativeLoginControllerConfiguration.setRevision(applicationScopeConfiguration.getClientRevision());
        nativeLoginControllerConfiguration.setVersionLong(applicationScopeConfiguration.getClientVersionLong());
        nativeLoginControllerConfiguration.setAccesspointLanguage(applicationScopeConfiguration.getAccesspointLanguage());
        nativeLoginControllerConfiguration.setTracingEnabled(applicationScopeConfiguration.getEnablePerformanceTracing());
        nativeLoginControllerConfiguration.setReconnectPolicy(applicationScopeConfiguration.getReconnectPolicy());
        nativeLoginControllerConfiguration.setProtocolOsOverride(applicationScopeConfiguration.getProtocolOsOverride());
        nativeLoginControllerConfiguration.setContentAccessRefreshToken(applicationScopeConfiguration.getContentAccessRefreshToken());
        AuthenticatedScopeConfiguration authenticatedScopeConfiguration = new AuthenticatedScopeConfiguration();
        authenticatedScopeConfiguration.setClientVersionLong("4.4.1.0");
        authenticatedScopeConfiguration.setCachePath(r60Var2.a().getAbsolutePath());
        authenticatedScopeConfiguration.setClientId("1fd85409eb8e441fb783f60caf2fdaab");
        authenticatedScopeConfiguration.setDeviceId(str);
        String strA3 = d7.a("_", new ac1(new cc1(yw.a(context2.getResources().getConfiguration()))).b(0));
        v03.j(strA3, "locale");
        new Regex("_+").b(strA3, "-");
        authenticatedScopeConfiguration.setWebgateUserAgentParameterAppVersion(nVar.a());
        authenticatedScopeConfiguration.setWebgateUserAgentParameterAppPlatform(companion.android());
        authenticatedScopeConfiguration.setWebgateUserAgentParameterOsVersion(String.valueOf(i2));
        authenticatedScopeConfiguration.setWebgateUserAgentParameterExtraInformation(str2);
        if (k10Var != null) {
            authenticatedScopeConfiguration.setDealerPingTime(30000);
        }
        com.spotify.core.coreimpl.ApplicationScopeConfiguration applicationScopeConfiguration2 = new com.spotify.core.coreimpl.ApplicationScopeConfiguration(null, null, false, null, 15, null);
        applicationScopeConfiguration2.setCachePath(r60Var2.a().getAbsolutePath());
        applicationScopeConfiguration2.setSettingsPath(r60Var2.b().getAbsolutePath());
        EventSenderAnalyticsDelegate eventSenderAnalyticsDelegate = new EventSenderAnalyticsDelegate(this.e);
        this.t = new c32(this.g);
        u10 u10Var2 = this.l;
        v03.h(u10Var2);
        o10 o10Var = this.m;
        v03.h(o10Var);
        MobileDeviceInfo mobileDeviceInfo2 = this.f;
        SharedCosmosRouterService sharedCosmosRouterService = this.n;
        v03.h(sharedCosmosRouterService);
        this.o = new ConnectivityService(eventSenderAnalyticsDelegate, u10Var2, o10Var, applicationScopeConfiguration, mobileDeviceInfo2, sharedCosmosRouterService, this.a, this.h, this.i);
        u10 u10Var3 = this.l;
        v03.h(u10Var3);
        o10 o10Var2 = this.m;
        v03.h(o10Var2);
        ConnectivityService connectivityService = this.o;
        v03.h(connectivityService);
        SharedCosmosRouterService sharedCosmosRouterService2 = this.n;
        v03.h(sharedCosmosRouterService2);
        this.q = new q10(u10Var3, o10Var2, applicationScopeConfiguration2, connectivityService, sharedCosmosRouterService2, this.e);
        ConnectivityService connectivityService2 = this.o;
        v03.h(connectivityService2);
        connectivityService2.addLoginControllerDelegate(new c10(this, eventSenderAnalyticsDelegate, nativeLoginControllerConfiguration, authenticatedScopeConfiguration, l10Var, fullAuthenticatedScopeConfiguration, limitedAuthenticatedScopeConfiguration));
        SharedCosmosRouterService sharedCosmosRouterService3 = this.n;
        v03.h(sharedCosmosRouterService3);
        sharedCosmosRouterService3.getLegacyQueuingRemoteNativeRouter().onNativeRouterInitialized();
        SharedCosmosRouterService sharedCosmosRouterService4 = this.n;
        v03.h(sharedCosmosRouterService4);
        QueuingRemoteNativeRouter legacyQueuingRemoteNativeRouter = sharedCosmosRouterService4.getLegacyQueuingRemoteNativeRouter();
        zy2 zy2Var = this.d;
        zy2Var.getClass();
        v03.j(legacyQueuingRemoteNativeRouter, "nativeRouter");
        if (zy2Var.b != null) {
            throw new IllegalStateException("Already started".toString());
        }
        HandlerThread handlerThread = new HandlerThread("core", 10);
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        RemoteNativeRxRouter remoteNativeRxRouter = new RemoteNativeRxRouter(legacyQueuingRemoteNativeRouter, handler);
        zy2Var.b = remoteNativeRxRouter;
        zy2Var.a.onNext(Optional.e(remoteNativeRxRouter));
        zy2Var.c = handler;
        zy2Var.d = handlerThread;
    }
}
