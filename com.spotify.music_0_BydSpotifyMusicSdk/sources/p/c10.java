package p;

import com.google.common.base.Optional;
import com.spotify.appstorage.userdirectory.UserDirectoryCachePaths;
import com.spotify.connectivity.AuthenticatedScopeConfiguration;
import com.spotify.connectivity.auth.LoginControllerDelegate;
import com.spotify.connectivity.auth.NativeLoginControllerConfiguration;
import com.spotify.connectivity.auth.NativeSession;
import com.spotify.connectivity.connectivityapi.ConnectivityApi;
import com.spotify.connectivity.connectivityservice.ConnectivityService;
import com.spotify.connectivity.connectivitysessionservice.ConnectivitySessionService;
import com.spotify.connectivity.eventsenderanalyticsdelegate.EventSenderAnalyticsDelegate;
import com.spotify.connectivity.sessionservice.DefaultSharedNativeSession;
import com.spotify.connectivity.sessionservice.SessionService;
import com.spotify.connectivity.sessionservice.TemporarySessionLoginFlowDependencies;
import com.spotify.core.corefullimpl.FullAuthenticatedScopeConfiguration;
import com.spotify.core.corelimitedimpl.LimitedAuthenticatedScopeConfiguration;
import com.spotify.cosmos.sharedcosmosrouterservice.SharedCosmosRouterService;
import io.reactivex.rxjava3.core.Observable;

/* JADX INFO: loaded from: classes.dex */
public final class c10 implements LoginControllerDelegate {
    public final /* synthetic */ d10 a;
    public final /* synthetic */ EventSenderAnalyticsDelegate b;
    public final /* synthetic */ NativeLoginControllerConfiguration c;
    public final /* synthetic */ AuthenticatedScopeConfiguration d;
    public final /* synthetic */ l10 e;
    public final /* synthetic */ FullAuthenticatedScopeConfiguration f;
    public final /* synthetic */ LimitedAuthenticatedScopeConfiguration g;

    public c10(d10 d10Var, EventSenderAnalyticsDelegate eventSenderAnalyticsDelegate, NativeLoginControllerConfiguration nativeLoginControllerConfiguration, AuthenticatedScopeConfiguration authenticatedScopeConfiguration, l10 l10Var, FullAuthenticatedScopeConfiguration fullAuthenticatedScopeConfiguration, LimitedAuthenticatedScopeConfiguration limitedAuthenticatedScopeConfiguration) {
        this.a = d10Var;
        this.b = eventSenderAnalyticsDelegate;
        this.c = nativeLoginControllerConfiguration;
        this.d = authenticatedScopeConfiguration;
        this.e = l10Var;
        this.f = fullAuthenticatedScopeConfiguration;
        this.g = limitedAuthenticatedScopeConfiguration;
    }

    @Override // com.spotify.connectivity.auth.LoginControllerDelegate
    public final void onLogin() {
        sb2 w00Var;
        d10 d10Var = this.a;
        u10 u10Var = d10Var.l;
        v03.h(u10Var);
        if (!u10Var.a.isCurrentThread()) {
            throw new IllegalStateException("onLogin should always be called on the core thread");
        }
        ConnectivityService connectivityService = d10Var.o;
        v03.h(connectivityService);
        NativeSession nativeSessionAdoptNativeSession = connectivityService.adoptNativeSession();
        u10 u10Var2 = d10Var.l;
        v03.h(u10Var2);
        DefaultSharedNativeSession defaultSharedNativeSession = new DefaultSharedNativeSession(nativeSessionAdoptNativeSession, u10Var2);
        d10Var.r = defaultSharedNativeSession;
        TemporarySessionLoginFlowDependencies.LegacyFlow legacyFlow = new TemporarySessionLoginFlowDependencies.LegacyFlow(defaultSharedNativeSession);
        u10 u10Var3 = d10Var.l;
        v03.h(u10Var3);
        EventSenderAnalyticsDelegate eventSenderAnalyticsDelegate = this.b;
        ConnectivityService connectivityService2 = d10Var.o;
        v03.h(connectivityService2);
        ConnectivityApi connectivityApiM141getApi = connectivityService2.m141getApi();
        q10 q10Var = d10Var.q;
        v03.h(q10Var);
        d10Var.f113p = new SessionService(legacyFlow, u10Var3, eventSenderAnalyticsDelegate, connectivityApiM141getApi, q10Var, this.c, d10Var.b, new z00(), new a10());
        u10 u10Var4 = d10Var.l;
        v03.h(u10Var4);
        SharedCosmosRouterService sharedCosmosRouterService = d10Var.n;
        v03.h(sharedCosmosRouterService);
        ConnectivityService connectivityService3 = d10Var.o;
        v03.h(connectivityService3);
        EventSenderAnalyticsDelegate eventSenderAnalyticsDelegate2 = this.b;
        AuthenticatedScopeConfiguration authenticatedScopeConfiguration = this.d;
        SessionService sessionService = d10Var.f113p;
        v03.h(sessionService);
        Optional optionalA = Optional.a();
        v03.i(optionalA, "absent()");
        d10Var.s = new ConnectivitySessionService(u10Var4, sharedCosmosRouterService, connectivityService3, eventSenderAnalyticsDelegate2, authenticatedScopeConfiguration, sessionService, null, null, optionalA);
        SessionService sessionService2 = d10Var.f113p;
        v03.h(sessionService2);
        r60 r60Var = (r60) this.e;
        String absolutePath = r60Var.a().getAbsolutePath();
        v03.i(absolutePath, "corePaths.cacheDir.absolutePath");
        String absolutePath2 = r60Var.b().getAbsolutePath();
        v03.i(absolutePath2, "corePaths.settingsDir.absolutePath");
        d10Var.v = new wr2(sessionService2, new UserDirectoryCachePaths(absolutePath, absolutePath2));
        if (d10Var.j) {
            d10Var.u = new hd2(new u11());
            u10 u10Var5 = d10Var.l;
            v03.h(u10Var5);
            SharedCosmosRouterService sharedCosmosRouterService2 = d10Var.n;
            v03.h(sharedCosmosRouterService2);
            o10 o10Var = d10Var.m;
            v03.h(o10Var);
            c32 c32Var = d10Var.t;
            v03.h(c32Var);
            hd2 hd2Var = d10Var.u;
            v03.h(hd2Var);
            ConnectivityService connectivityService4 = d10Var.o;
            v03.h(connectivityService4);
            q10 q10Var2 = d10Var.q;
            v03.h(q10Var2);
            ConnectivitySessionService connectivitySessionService = d10Var.s;
            v03.h(connectivitySessionService);
            SessionService sessionService3 = d10Var.f113p;
            v03.h(sessionService3);
            u11 u11Var = new u11();
            b10 b10Var = new b10();
            wr2 wr2Var = d10Var.v;
            v03.h(wr2Var);
            FullAuthenticatedScopeConfiguration fullAuthenticatedScopeConfiguration = this.f;
            qo qoVar = new qo();
            com.spotify.core.corefullsessionservice.b bVar = new com.spotify.core.corefullsessionservice.b(false);
            Observable observableEmpty = Observable.empty();
            v03.i(observableEmpty, "empty()");
            w00Var = new com.spotify.core.corefullsessionservice.a(u10Var5, sharedCosmosRouterService2, o10Var, c32Var, hd2Var, connectivityService4, q10Var2, connectivitySessionService, sessionService3, u11Var, b10Var, wr2Var, fullAuthenticatedScopeConfiguration, qoVar, bVar, observableEmpty);
        } else {
            u10 u10Var6 = d10Var.l;
            v03.h(u10Var6);
            SharedCosmosRouterService sharedCosmosRouterService3 = d10Var.n;
            v03.h(sharedCosmosRouterService3);
            o10 o10Var2 = d10Var.m;
            v03.h(o10Var2);
            c32 c32Var2 = d10Var.t;
            v03.h(c32Var2);
            ConnectivityService connectivityService5 = d10Var.o;
            v03.h(connectivityService5);
            q10 q10Var3 = d10Var.q;
            v03.h(q10Var3);
            ConnectivitySessionService connectivitySessionService2 = d10Var.s;
            v03.h(connectivitySessionService2);
            SessionService sessionService4 = d10Var.f113p;
            v03.h(sessionService4);
            wr2 wr2Var2 = d10Var.v;
            v03.h(wr2Var2);
            LimitedAuthenticatedScopeConfiguration limitedAuthenticatedScopeConfiguration = this.g;
            Observable observableEmpty2 = Observable.empty();
            v03.i(observableEmpty2, "empty()");
            w00Var = new w00(u10Var6, sharedCosmosRouterService3, o10Var2, c32Var2, connectivityService5, q10Var3, connectivitySessionService2, sessionService4, wr2Var2, limitedAuthenticatedScopeConfiguration, observableEmpty2);
        }
        d10Var.w = w00Var;
    }

    @Override // com.spotify.connectivity.auth.LoginControllerDelegate
    public final void onLogout() {
        d10.a(this.a);
    }
}
