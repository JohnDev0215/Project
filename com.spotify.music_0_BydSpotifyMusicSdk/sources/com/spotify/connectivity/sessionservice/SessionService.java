package com.spotify.connectivity.sessionservice;

import com.spotify.base.java.logging.Logger;
import com.spotify.concurrency.asyncimpl.NativeTimerManagerThreadImpl;
import com.spotify.connectivity.AnalyticsDelegate;
import com.spotify.connectivity.auth.NativeLoginControllerConfiguration;
import com.spotify.connectivity.auth.NativeSession;
import com.spotify.connectivity.auth.UnencryptedCredentials;
import com.spotify.connectivity.auth.esperanto.proto.EsSessionEsperantoKt;
import com.spotify.connectivity.connectivityapi.ConnectivityApi;
import com.spotify.connectivity.sessionapi.SessionApi;
import com.spotify.connectivity.sessionesperanto.SessionClientEsperanto;
import com.spotify.connectivity.sessionservice.TemporarySessionLoginFlowDependencies;
import com.spotify.esperanto.esperantoimpl.SchedulingTransport;
import p.a10;
import p.bf;
import p.fm1;
import p.q00;
import p.qe;
import p.r10;
import p.re;
import p.sb2;
import p.se;
import p.u10;
import p.u4;
import p.ut;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class SessionService implements SessionApi, sb2 {
    private final AnalyticsDelegate analyticsDelegate;
    private final se authDataApi;
    private final ut clientInfo;
    private final ConnectivityApi connectivityApi;
    private final q00 coreApi;
    private final r10 coreThreadingApi;
    private final NativeLoginControllerConfiguration loginControllerConfiguration;
    private final fm1 nativeLogin5OAuthClientApi;
    public NativeSession nativeSession;
    private final TemporarySessionLoginFlowDependencies temporarySessionLoginFlowDependencies;

    public SessionService(TemporarySessionLoginFlowDependencies temporarySessionLoginFlowDependencies, r10 r10Var, AnalyticsDelegate analyticsDelegate, ConnectivityApi connectivityApi, q00 q00Var, NativeLoginControllerConfiguration nativeLoginControllerConfiguration, ut utVar, fm1 fm1Var, se seVar) {
        v03.j(temporarySessionLoginFlowDependencies, "temporarySessionLoginFlowDependencies");
        v03.j(r10Var, "coreThreadingApi");
        v03.j(analyticsDelegate, "analyticsDelegate");
        v03.j(connectivityApi, "connectivityApi");
        v03.j(q00Var, "coreApi");
        v03.j(nativeLoginControllerConfiguration, "loginControllerConfiguration");
        v03.j(utVar, "clientInfo");
        v03.j(fm1Var, "nativeLogin5OAuthClientApi");
        v03.j(seVar, "authDataApi");
        this.temporarySessionLoginFlowDependencies = temporarySessionLoginFlowDependencies;
        this.coreThreadingApi = r10Var;
        this.analyticsDelegate = analyticsDelegate;
        this.connectivityApi = connectivityApi;
        this.coreApi = q00Var;
        this.loginControllerConfiguration = nativeLoginControllerConfiguration;
        this.clientInfo = utVar;
        this.nativeLogin5OAuthClientApi = fm1Var;
        this.authDataApi = seVar;
        if (temporarySessionLoginFlowDependencies instanceof TemporarySessionLoginFlowDependencies.LegacyFlow) {
            setNativeSession(((TemporarySessionLoginFlowDependencies.LegacyFlow) temporarySessionLoginFlowDependencies).getSharedNativeSession().acquire());
            return;
        }
        if (temporarySessionLoginFlowDependencies instanceof TemporarySessionLoginFlowDependencies.NewFlow) {
            u10 u10Var = (u10) r10Var;
            u10Var.a.runBlocking(new Runnable() { // from class: com.spotify.connectivity.sessionservice.SessionService.1
                @Override // java.lang.Runnable
                public final void run() {
                    ((a10) SessionService.this.authDataApi).getClass();
                    bf bfVar = bf.l;
                    String str = bfVar.c;
                    ((a10) SessionService.this.authDataApi).getClass();
                    re reVar = bfVar.k;
                    v03.g(reVar, "null cannot be cast to non-null type com.spotify.authentication.authclientapi.AuthBlob.StoredCredentials");
                    new UnencryptedCredentials(str, ((qe) reVar).e);
                    SessionService sessionService = SessionService.this;
                    NativeSession.Companion companion = NativeSession.Companion;
                    NativeLoginControllerConfiguration unused = sessionService.loginControllerConfiguration;
                    NativeTimerManagerThreadImpl nativeTimerManagerThreadImpl = ((u10) SessionService.this.coreThreadingApi).a;
                    SessionService.this.connectivityApi.getNativeConnectivityApplicationScope();
                    AnalyticsDelegate unused2 = SessionService.this.analyticsDelegate;
                    SessionService.this.connectivityApi.getNativeConnectivityPolicyProvider();
                    SessionService.this.connectivityApi.getNativeConnectionTypeProvider();
                    SessionService.this.connectivityApi.getNativeCredentialsStorage();
                    SessionService.this.nativeLogin5OAuthClientApi.getClass();
                    u4.r();
                    throw null;
                }
            });
            if (bf.l.e != null) {
                new SessionClientEsperanto(EsSessionEsperantoKt.createSessionClient(new SchedulingTransport(u10Var.b, getNativeSession().getInternalTransportToNative()))).writeProductStateToLegacyStorage(((TemporarySessionLoginFlowDependencies.NewFlow) temporarySessionLoginFlowDependencies).getProductState()).blockingGet();
            }
        }
    }

    private final void shutdownSession() {
        getNativeSession().blockingLogout();
        ((u10) this.coreThreadingApi).a.runBlocking(new Runnable() { // from class: com.spotify.connectivity.sessionservice.SessionService.shutdownSession.1
            @Override // java.lang.Runnable
            public final void run() {
                SessionService.this.getNativeSession().prepareForShutdown();
                SessionService.this.getNativeSession().destroy();
            }
        });
    }

    /* JADX INFO: renamed from: getApi, reason: merged with bridge method [inline-methods] */
    public SessionApi m192getApi() {
        return this;
    }

    @Override // com.spotify.connectivity.sessionapi.SessionApi
    public NativeSession getNativeSession() {
        NativeSession nativeSession = this.nativeSession;
        if (nativeSession != null) {
            return nativeSession;
        }
        v03.Q("nativeSession");
        throw null;
    }

    public void setNativeSession(NativeSession nativeSession) {
        v03.j(nativeSession, "<set-?>");
        this.nativeSession = nativeSession;
    }

    @Override // p.sb2
    public void shutdown() {
        Logger.a("SessionService shutdown called", new Object[0]);
        TemporarySessionLoginFlowDependencies temporarySessionLoginFlowDependencies = this.temporarySessionLoginFlowDependencies;
        if (temporarySessionLoginFlowDependencies instanceof TemporarySessionLoginFlowDependencies.LegacyFlow) {
            ((TemporarySessionLoginFlowDependencies.LegacyFlow) temporarySessionLoginFlowDependencies).getSharedNativeSession().release();
        } else if (temporarySessionLoginFlowDependencies instanceof TemporarySessionLoginFlowDependencies.NewFlow) {
            shutdownSession();
        }
        Logger.a("SessionService shutdown completed", new Object[0]);
    }
}
