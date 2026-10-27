package p;

import com.spotify.appstorage.userdirectory.NativeUserDirectoryManager;
import com.spotify.appstorage.userdirectory.UserDirectoryApi;
import com.spotify.concurrency.asyncimpl.NativeTimerManagerThreadImpl;
import com.spotify.connectivity.NativeApplicationScope;
import com.spotify.connectivity.NativeAuthenticatedScope;
import com.spotify.connectivity.auth.NativeSession;
import com.spotify.connectivity.connectivityapi.ConnectivityApi;
import com.spotify.connectivity.connectivitysdkpolicyimpl.NativeConnectivityManager;
import com.spotify.connectivity.connectivityservice.ConnectivityService;
import com.spotify.connectivity.connectivitysessionapi.ConnectivitySessionApi;
import com.spotify.connectivity.connectivitysessionservice.ConnectivitySessionService;
import com.spotify.connectivity.sessionapi.SessionApi;
import com.spotify.connectivity.sessionservice.SessionService;
import com.spotify.core.coreimpl.NativeApplicationScopeImpl;
import com.spotify.core.corelimitedimpl.LimitedAuthenticatedScopeConfiguration;
import com.spotify.core.corelimitedimpl.NativeLimitedAuthenticatedScopeImpl;
import com.spotify.core.corelimitedsessionservice.CoreLimitedSessionService$CoreThreadPolicy;
import com.spotify.cosmos.cosmosimpl.NativeRouter;
import com.spotify.cosmos.sharedcosmosrouterapi.SharedCosmosRouterApi;
import com.spotify.cosmos.sharedcosmosrouterservice.SharedCosmosRouterService;
import com.spotify.offline.NativeForegroundProvider;
import com.spotify.prefs.prefsimpl.NativePrefs;
import com.spotify.remoteconfig.NativeRemoteConfigImpl;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;

/* JADX INFO: loaded from: classes.dex */
public final class w00 implements sb2 {
    public final r10 a;
    public final SharedCosmosRouterApi b;
    public final n10 c;
    public final c32 d;
    public final ConnectivityApi e;
    public final q00 f;
    public final ConnectivitySessionApi g;
    public final SessionApi h;
    public final UserDirectoryApi i;
    public final LimitedAuthenticatedScopeConfiguration j;
    public final Observable k;
    public final CoreLimitedSessionService$CoreThreadPolicy l;
    public NativeLimitedAuthenticatedScopeImpl m;

    public w00(u10 u10Var, SharedCosmosRouterService sharedCosmosRouterService, o10 o10Var, c32 c32Var, ConnectivityService connectivityService, q10 q10Var, ConnectivitySessionService connectivitySessionService, SessionService sessionService, wr2 wr2Var, LimitedAuthenticatedScopeConfiguration limitedAuthenticatedScopeConfiguration, Observable observable) {
        v03.j(limitedAuthenticatedScopeConfiguration, "limitedAuthenticatedScopeConfiguration");
        this.a = u10Var;
        this.b = sharedCosmosRouterService;
        this.c = o10Var;
        this.d = c32Var;
        this.e = connectivityService;
        this.f = q10Var;
        this.g = connectivitySessionService;
        this.h = sessionService;
        this.i = wr2Var;
        this.j = limitedAuthenticatedScopeConfiguration;
        this.k = observable;
        NativeTimerManagerThreadImpl nativeTimerManagerThreadImpl = u10Var.a;
        CoreLimitedSessionService$CoreThreadPolicy coreLimitedSessionService$CoreThreadPolicy = nativeTimerManagerThreadImpl.isCurrentThread() ? CoreLimitedSessionService$CoreThreadPolicy.e : CoreLimitedSessionService$CoreThreadPolicy.c;
        this.l = coreLimitedSessionService$CoreThreadPolicy;
        int iOrdinal = coreLimitedSessionService$CoreThreadPolicy.ordinal();
        if (iOrdinal == 0) {
            nativeTimerManagerThreadImpl.runBlocking(new v00(this, 0));
        } else {
            if (iOrdinal != 1) {
                return;
            }
            this.m = a();
        }
    }

    public final NativeLimitedAuthenticatedScopeImpl a() {
        NativeLimitedAuthenticatedScopeImpl.Companion companion = NativeLimitedAuthenticatedScopeImpl.Companion;
        NativeTimerManagerThreadImpl nativeTimerManagerThreadImpl = ((u10) this.a).a;
        SharedCosmosRouterApi sharedCosmosRouterApi = this.b;
        NativeRouter nativeRouter = sharedCosmosRouterApi.getNativeRouter();
        NativePrefs nativePrefs = ((o10) this.c).a;
        NativeRemoteConfigImpl nativeRemoteConfigImpl = this.d.a;
        ConnectivityApi connectivityApi = this.e;
        NativeConnectivityManager nativeConnectivityManager = connectivityApi.getNativeConnectivityManager();
        NativeApplicationScope nativeConnectivityApplicationScope = connectivityApi.getNativeConnectivityApplicationScope();
        NativeSession nativeSession = this.h.getNativeSession();
        NativeAuthenticatedScope authenticatedScope = this.g.getAuthenticatedScope();
        NativeApplicationScopeImpl nativeApplicationScopeImpl = ((q10) this.f).f;
        if (nativeApplicationScopeImpl == null) {
            v03.Q("nativeCoreApplicationScopeImpl");
            throw null;
        }
        NativeUserDirectoryManager nativeUserDirectoryManager = this.i.getNativeUserDirectoryManager();
        LimitedAuthenticatedScopeConfiguration limitedAuthenticatedScopeConfiguration = this.j;
        xx2 xx2Var = new xx2(3, this);
        Scheduler scheduler = io.reactivex.rxjava3.schedulers.e.a;
        Observable observableObserveOn = this.k.observeOn(new io.reactivex.rxjava3.internal.schedulers.i(xx2Var, false, false));
        v03.i(observableObserveOn, "internal fun initInterna…henticatedScopeImpl\n    }");
        NativeLimitedAuthenticatedScopeImpl nativeLimitedAuthenticatedScopeImplCreate = companion.create(nativeTimerManagerThreadImpl, nativeRouter, nativePrefs, nativeRemoteConfigImpl, nativeConnectivityManager, nativeConnectivityApplicationScope, nativeSession, authenticatedScope, nativeApplicationScopeImpl, nativeUserDirectoryManager, limitedAuthenticatedScopeConfiguration, new NativeForegroundProvider(observableObserveOn));
        sharedCosmosRouterApi.onCoreSessionInitialized();
        return nativeLimitedAuthenticatedScopeImplCreate;
    }

    public final void b() {
        NativeLimitedAuthenticatedScopeImpl nativeLimitedAuthenticatedScopeImpl = this.m;
        if (nativeLimitedAuthenticatedScopeImpl == null) {
            v03.Q("authenticatedScopeImpl");
            throw null;
        }
        nativeLimitedAuthenticatedScopeImpl.prepareForShutdown();
        NativeLimitedAuthenticatedScopeImpl nativeLimitedAuthenticatedScopeImpl2 = this.m;
        if (nativeLimitedAuthenticatedScopeImpl2 == null) {
            v03.Q("authenticatedScopeImpl");
            throw null;
        }
        nativeLimitedAuthenticatedScopeImpl2.flushCaches();
        NativeLimitedAuthenticatedScopeImpl nativeLimitedAuthenticatedScopeImpl3 = this.m;
        if (nativeLimitedAuthenticatedScopeImpl3 != null) {
            nativeLimitedAuthenticatedScopeImpl3.destroy();
        } else {
            v03.Q("authenticatedScopeImpl");
            throw null;
        }
    }

    @Override // p.sb2
    public final void shutdown() {
        int iOrdinal = this.l.ordinal();
        if (iOrdinal == 0) {
            ((u10) this.a).a.runBlocking(new v00(this, 1));
        } else {
            if (iOrdinal != 1) {
                return;
            }
            b();
        }
    }
}
