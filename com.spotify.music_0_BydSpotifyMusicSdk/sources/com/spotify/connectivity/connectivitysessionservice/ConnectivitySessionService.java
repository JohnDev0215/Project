package com.spotify.connectivity.connectivitysessionservice;

import com.google.common.base.Optional;
import com.spotify.authentication.authtriggerserviceapi.AuthLogoutReason;
import com.spotify.base.java.logging.Logger;
import com.spotify.connectivity.AnalyticsDelegate;
import com.spotify.connectivity.AuthenticatedScopeConfiguration;
import com.spotify.connectivity.NativeAuthenticatedScope;
import com.spotify.connectivity.RCSSnapshotIdProvider;
import com.spotify.connectivity.auth.LoginControllerAnalyticsDelegate;
import com.spotify.connectivity.connectivityapi.ConnectivityApi;
import com.spotify.connectivity.connectivitysessionapi.ConnectivitySessionApi;
import com.spotify.connectivity.logoutanalyticsdelegate.AuthAnalyticsDelegate;
import com.spotify.connectivity.logoutanalyticsdelegate.LogoutEvent;
import com.spotify.connectivity.pubsub.PubSubClient;
import com.spotify.connectivity.sessionapi.SessionApi;
import com.spotify.cosmos.sharedcosmosrouterapi.SharedCosmosRouterApi;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.a42;
import p.r10;
import p.sb2;
import p.u10;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectivitySessionService implements ConnectivitySessionApi, sb2 {
    private final AnalyticsDelegate analyticsDelegate;
    private final AuthAnalyticsDelegate authAnalyticsDelegate;
    public NativeAuthenticatedScope authenticatedScope;
    private final AuthenticatedScopeConfiguration authenticatedScopeConfiguration;
    private final ConnectivityApi connectivityApi;
    private final CoreThreadPolicy coreThreadPolicy;
    private final r10 coreThreadingApi;
    private final InternalLoginControllerAnalyticsDelegate loginControllerAnalyticsDelegate;
    private final PubSubClient pubSubClient;
    private final ConnectivitySessionService$rcsSnapshotIdProvider$1 rcsSnapshotIdProvider;
    private final SessionApi sessionApi;
    private final SharedCosmosRouterApi sharedCosmosRouterApi;

    public enum CoreThreadPolicy {
        RUN_ON_CORE_THREAD,
        DO_NOT_RUN_ON_CORE_THREAD
    }

    public static final class InternalLoginControllerAnalyticsDelegate implements LoginControllerAnalyticsDelegate {
        private final AuthAnalyticsDelegate authAnalyticsDelegate;

        public InternalLoginControllerAnalyticsDelegate(AuthAnalyticsDelegate authAnalyticsDelegate) {
            this.authAnalyticsDelegate = authAnalyticsDelegate;
        }

        @Override // com.spotify.connectivity.auth.LoginControllerAnalyticsDelegate
        public void onBeforeLogout() {
            AuthAnalyticsDelegate authAnalyticsDelegate = this.authAnalyticsDelegate;
            if (authAnalyticsDelegate != null) {
                authAnalyticsDelegate.logEvent(new LogoutEvent.WillLogout(AuthLogoutReason.c, false, 2, null));
            }
        }

        @Override // com.spotify.connectivity.auth.LoginControllerAnalyticsDelegate
        public void onBeforeLogoutForced() {
            AuthAnalyticsDelegate authAnalyticsDelegate = this.authAnalyticsDelegate;
            if (authAnalyticsDelegate != null) {
                authAnalyticsDelegate.logEvent(new LogoutEvent.WillLogout(AuthLogoutReason.e, false, 2, null));
            }
        }

        @Override // com.spotify.connectivity.auth.LoginControllerAnalyticsDelegate
        public void onBeforeLogoutRemote() {
            AuthAnalyticsDelegate authAnalyticsDelegate = this.authAnalyticsDelegate;
            if (authAnalyticsDelegate != null) {
                authAnalyticsDelegate.logEvent(new LogoutEvent.WillLogout(AuthLogoutReason.k, false, 2, null));
            }
        }

        @Override // com.spotify.connectivity.auth.LoginControllerAnalyticsDelegate
        public void onLogoutDone() {
            AuthAnalyticsDelegate authAnalyticsDelegate = this.authAnalyticsDelegate;
            if (authAnalyticsDelegate != null) {
                authAnalyticsDelegate.logEvent(LogoutEvent.DidLogout.INSTANCE);
            }
        }

        @Override // com.spotify.connectivity.auth.LoginControllerAnalyticsDelegate
        public void onOfflineUserRemoved() {
            AuthAnalyticsDelegate authAnalyticsDelegate = this.authAnalyticsDelegate;
            if (authAnalyticsDelegate != null) {
                authAnalyticsDelegate.logEvent(LogoutEvent.OfflineUserRemoved.INSTANCE);
            }
        }

        @Override // com.spotify.connectivity.auth.LoginControllerAnalyticsDelegate
        public void onStoredCredentialsRemoved() {
            AuthAnalyticsDelegate authAnalyticsDelegate = this.authAnalyticsDelegate;
            if (authAnalyticsDelegate != null) {
                authAnalyticsDelegate.logEvent(LogoutEvent.StoredCredentialsRemoved.INSTANCE);
            }
        }
    }

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CoreThreadPolicy.values().length];
            try {
                iArr[CoreThreadPolicy.RUN_ON_CORE_THREAD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CoreThreadPolicy.DO_NOT_RUN_ON_CORE_THREAD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [com.spotify.connectivity.connectivitysessionservice.ConnectivitySessionService$rcsSnapshotIdProvider$1] */
    public ConnectivitySessionService(r10 r10Var, SharedCosmosRouterApi sharedCosmosRouterApi, ConnectivityApi connectivityApi, AnalyticsDelegate analyticsDelegate, AuthenticatedScopeConfiguration authenticatedScopeConfiguration, SessionApi sessionApi, AuthAnalyticsDelegate authAnalyticsDelegate, PubSubClient pubSubClient, final Optional<a42> optional) {
        v03.j(r10Var, "coreThreadingApi");
        v03.j(sharedCosmosRouterApi, "sharedCosmosRouterApi");
        v03.j(connectivityApi, "connectivityApi");
        v03.j(analyticsDelegate, "analyticsDelegate");
        v03.j(authenticatedScopeConfiguration, "authenticatedScopeConfiguration");
        v03.j(sessionApi, "sessionApi");
        v03.j(optional, "snapshotIdResolver");
        this.coreThreadingApi = r10Var;
        this.sharedCosmosRouterApi = sharedCosmosRouterApi;
        this.connectivityApi = connectivityApi;
        this.analyticsDelegate = analyticsDelegate;
        this.authenticatedScopeConfiguration = authenticatedScopeConfiguration;
        this.sessionApi = sessionApi;
        this.authAnalyticsDelegate = authAnalyticsDelegate;
        this.pubSubClient = pubSubClient;
        this.rcsSnapshotIdProvider = new RCSSnapshotIdProvider() { // from class: com.spotify.connectivity.connectivitysessionservice.ConnectivitySessionService$rcsSnapshotIdProvider$1
            @Override // com.spotify.connectivity.RCSSnapshotIdProvider
            public String getSnapshotId() {
                Long snapshotId;
                String string;
                a42 a42Var = (a42) optional.h();
                return (a42Var == null || (snapshotId = a42Var.getSnapshotId()) == null || (string = snapshotId.toString()) == null) ? "" : string;
            }
        };
        CoreThreadPolicy coreThreadPolicy = ((u10) r10Var).a.isCurrentThread() ? CoreThreadPolicy.DO_NOT_RUN_ON_CORE_THREAD : CoreThreadPolicy.RUN_ON_CORE_THREAD;
        this.coreThreadPolicy = coreThreadPolicy;
        int i = WhenMappings.$EnumSwitchMapping$0[coreThreadPolicy.ordinal()];
        if (i == 1) {
            ((u10) r10Var).a.runBlocking(new Runnable() { // from class: com.spotify.connectivity.connectivitysessionservice.ConnectivitySessionService.1
                @Override // java.lang.Runnable
                public final void run() {
                    ConnectivitySessionService connectivitySessionService = ConnectivitySessionService.this;
                    connectivitySessionService.setAuthenticatedScope(connectivitySessionService.initInternal$src_main_java_com_spotify_connectivity_connectivitysessionservice_connectivitysessionservice_kt());
                }
            });
        } else if (i == 2) {
            setAuthenticatedScope(initInternal$src_main_java_com_spotify_connectivity_connectivitysessionservice_connectivitysessionservice_kt());
        }
        InternalLoginControllerAnalyticsDelegate internalLoginControllerAnalyticsDelegate = new InternalLoginControllerAnalyticsDelegate(authAnalyticsDelegate);
        this.loginControllerAnalyticsDelegate = internalLoginControllerAnalyticsDelegate;
        connectivityApi.getNativeLoginController().setAnalyticsDelegate(internalLoginControllerAnalyticsDelegate);
        if (pubSubClient != null) {
            pubSubClient.onSessionLogin();
        }
    }

    /* JADX INFO: renamed from: getApi, reason: merged with bridge method [inline-methods] */
    public ConnectivitySessionApi m142getApi() {
        return this;
    }

    @Override // com.spotify.connectivity.connectivitysessionapi.ConnectivitySessionApi
    public NativeAuthenticatedScope getAuthenticatedScope() {
        NativeAuthenticatedScope nativeAuthenticatedScope = this.authenticatedScope;
        if (nativeAuthenticatedScope != null) {
            return nativeAuthenticatedScope;
        }
        v03.Q("authenticatedScope");
        throw null;
    }

    public final NativeAuthenticatedScope initInternal$src_main_java_com_spotify_connectivity_connectivitysessionservice_connectivitysessionservice_kt() {
        return NativeAuthenticatedScope.Companion.create(((u10) this.coreThreadingApi).a, this.sharedCosmosRouterApi.getNativeRouter(), this.connectivityApi.getNativeConnectivityApplicationScope(), this.sessionApi.getNativeSession(), this.analyticsDelegate, this.rcsSnapshotIdProvider, this.authenticatedScopeConfiguration);
    }

    public void setAuthenticatedScope(NativeAuthenticatedScope nativeAuthenticatedScope) {
        v03.j(nativeAuthenticatedScope, "<set-?>");
        this.authenticatedScope = nativeAuthenticatedScope;
    }

    @Override // p.sb2
    public void shutdown() {
        Logger.a("ConnectivitySessionService shutdown called", new Object[0]);
        PubSubClient pubSubClient = this.pubSubClient;
        if (pubSubClient != null) {
            pubSubClient.onSessionLogout();
        }
        int i = WhenMappings.$EnumSwitchMapping$0[this.coreThreadPolicy.ordinal()];
        if (i == 1) {
            ((u10) this.coreThreadingApi).a.runBlocking(new Runnable() { // from class: com.spotify.connectivity.connectivitysessionservice.ConnectivitySessionService.shutdown.1
                @Override // java.lang.Runnable
                public final void run() {
                    ConnectivitySessionService.this.shutdownInternal$src_main_java_com_spotify_connectivity_connectivitysessionservice_connectivitysessionservice_kt();
                }
            });
        } else {
            if (i != 2) {
                return;
            }
            shutdownInternal$src_main_java_com_spotify_connectivity_connectivitysessionservice_connectivitysessionservice_kt();
        }
    }

    public final void shutdownInternal$src_main_java_com_spotify_connectivity_connectivitysessionservice_connectivitysessionservice_kt() {
        getAuthenticatedScope().prepareForShutdown();
        getAuthenticatedScope().destroy();
        Logger.a("ConnectivitySessionService shutdown completed", new Object[0]);
    }

    public /* synthetic */ ConnectivitySessionService(r10 r10Var, SharedCosmosRouterApi sharedCosmosRouterApi, ConnectivityApi connectivityApi, AnalyticsDelegate analyticsDelegate, AuthenticatedScopeConfiguration authenticatedScopeConfiguration, SessionApi sessionApi, AuthAnalyticsDelegate authAnalyticsDelegate, PubSubClient pubSubClient, Optional optional, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(r10Var, sharedCosmosRouterApi, connectivityApi, analyticsDelegate, authenticatedScopeConfiguration, sessionApi, (i & 64) != 0 ? null : authAnalyticsDelegate, (i & 128) != 0 ? null : pubSubClient, optional);
    }
}
