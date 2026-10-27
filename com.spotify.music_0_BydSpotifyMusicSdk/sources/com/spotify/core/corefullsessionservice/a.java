package com.spotify.core.corefullsessionservice;

import com.spotify.appstorage.userdirectory.NativeUserDirectoryManager;
import com.spotify.appstorage.userdirectory.UserDirectoryApi;
import com.spotify.base.java.logging.Logger;
import com.spotify.concurrency.asyncimpl.NativeTimerManagerThreadImpl;
import com.spotify.connectivity.NativeApplicationScope;
import com.spotify.connectivity.NativeAuthenticatedScope;
import com.spotify.connectivity.auth.NativeSession;
import com.spotify.connectivity.auth.esperanto.proto.EsSessionEsperantoKt;
import com.spotify.connectivity.connectivityapi.ConnectivityApi;
import com.spotify.connectivity.connectivitysdkpolicyimpl.NativeConnectivityManager;
import com.spotify.connectivity.connectivityservice.ConnectivityService;
import com.spotify.connectivity.connectivitysessionapi.ConnectivitySessionApi;
import com.spotify.connectivity.connectivitysessionservice.ConnectivitySessionService;
import com.spotify.connectivity.sessionapi.SessionApi;
import com.spotify.connectivity.sessionapi.SessionClient;
import com.spotify.connectivity.sessionesperanto.SessionClientEsperanto;
import com.spotify.connectivity.sessionservice.SessionService;
import com.spotify.core.corefullimpl.FullAuthenticatedScopeConfiguration;
import com.spotify.core.corefullimpl.NativeFullAuthenticatedScopeImpl;
import com.spotify.core.coreimpl.NativeApplicationScopeImpl;
import com.spotify.cosmos.cosmosimpl.NativeRouter;
import com.spotify.cosmos.sharedcosmosrouterapi.SharedCosmosRouterApi;
import com.spotify.cosmos.sharedcosmosrouterservice.SharedCosmosRouterService;
import com.spotify.esperanto.esperantoimpl.SchedulingTransport;
import com.spotify.localfiles.localfiles.LocalFilesApi;
import com.spotify.localfiles.localfilesimpl.NativeLocalFilesDelegate;
import com.spotify.offline.NativeForegroundProvider;
import com.spotify.offline.NativeOfflinePluginRegistry;
import com.spotify.prefs.prefsimpl.NativePrefs;
import com.spotify.remoteconfig.NativeRemoteConfigImpl;
import com.spotify.shoreline.esperanto.java.impl.NativeShorelineLoggerImpl;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.internal.schedulers.i;
import io.reactivex.rxjava3.schedulers.e;
import java.util.concurrent.TimeUnit;
import kotlin.Result;
import kotlin.collections.EmptyList;
import p.b10;
import p.c32;
import p.gd2;
import p.hd2;
import p.lq2;
import p.n10;
import p.o10;
import p.ok2;
import p.py0;
import p.q00;
import p.q10;
import p.qo;
import p.r00;
import p.r10;
import p.s00;
import p.sb2;
import p.u10;
import p.u11;
import p.v03;
import p.wr2;
import p.xx2;

/* JADX INFO: loaded from: classes.dex */
public final class a implements r00, sb2 {
    public final r10 a;
    public final SharedCosmosRouterApi b;
    public final n10 c;
    public final c32 d;
    public final gd2 e;
    public final ConnectivityApi f;
    public final q00 g;
    public final ConnectivitySessionApi h;
    public final SessionApi i;
    public final u11 j;
    public final LocalFilesApi k;
    public final UserDirectoryApi l;
    public final FullAuthenticatedScopeConfiguration m;
    public final qo n;
    public final b o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Observable f49p;
    public final CoreFullSessionService$CoreThreadPolicy q;
    public NativeFullAuthenticatedScopeImpl r;
    public SessionClient s;

    public a(u10 u10Var, SharedCosmosRouterService sharedCosmosRouterService, o10 o10Var, c32 c32Var, hd2 hd2Var, ConnectivityService connectivityService, q10 q10Var, ConnectivitySessionService connectivitySessionService, SessionService sessionService, u11 u11Var, b10 b10Var, wr2 wr2Var, FullAuthenticatedScopeConfiguration fullAuthenticatedScopeConfiguration, qo qoVar, b bVar, Observable observable) {
        v03.j(fullAuthenticatedScopeConfiguration, "fullAuthenticatedScopeConfiguration");
        this.a = u10Var;
        this.b = sharedCosmosRouterService;
        this.c = o10Var;
        this.d = c32Var;
        this.e = hd2Var;
        this.f = connectivityService;
        this.g = q10Var;
        this.h = connectivitySessionService;
        this.i = sessionService;
        this.j = u11Var;
        this.k = b10Var;
        this.l = wr2Var;
        this.m = fullAuthenticatedScopeConfiguration;
        this.n = qoVar;
        this.o = bVar;
        this.f49p = observable;
        Logger.a("CoreFullSessionService init called", new Object[0]);
        NativeTimerManagerThreadImpl nativeTimerManagerThreadImpl = u10Var.a;
        CoreFullSessionService$CoreThreadPolicy coreFullSessionService$CoreThreadPolicy = nativeTimerManagerThreadImpl.isCurrentThread() ? CoreFullSessionService$CoreThreadPolicy.e : CoreFullSessionService$CoreThreadPolicy.c;
        this.q = coreFullSessionService$CoreThreadPolicy;
        int iOrdinal = coreFullSessionService$CoreThreadPolicy.ordinal();
        if (iOrdinal == 0) {
            nativeTimerManagerThreadImpl.runBlocking(new s00(this, 0));
        } else {
            if (iOrdinal != 1) {
                return;
            }
            this.r = a();
        }
    }

    public final NativeFullAuthenticatedScopeImpl a() {
        NativeFullAuthenticatedScopeImpl.Companion companion = NativeFullAuthenticatedScopeImpl.Companion;
        u10 u10Var = (u10) this.a;
        NativeTimerManagerThreadImpl nativeTimerManagerThreadImpl = u10Var.a;
        SharedCosmosRouterApi sharedCosmosRouterApi = this.b;
        NativeRouter nativeRouter = sharedCosmosRouterApi.getNativeRouter();
        NativePrefs nativePrefs = ((o10) this.c).a;
        NativeRemoteConfigImpl nativeRemoteConfigImpl = this.d.a;
        NativeShorelineLoggerImpl nativeShorelineLoggerImpl = ((hd2) this.e).a;
        ConnectivityApi connectivityApi = this.f;
        NativeConnectivityManager nativeConnectivityManager = connectivityApi.getNativeConnectivityManager();
        NativeApplicationScope nativeConnectivityApplicationScope = connectivityApi.getNativeConnectivityApplicationScope();
        SessionApi sessionApi = this.i;
        NativeSession nativeSession = sessionApi.getNativeSession();
        NativeAuthenticatedScope authenticatedScope = this.h.getAuthenticatedScope();
        NativeApplicationScopeImpl nativeApplicationScopeImpl = ((q10) this.g).f;
        if (nativeApplicationScopeImpl == null) {
            v03.Q("nativeCoreApplicationScopeImpl");
            throw null;
        }
        NativeLocalFilesDelegate nativeLocalFilesDelegate = this.k.getNativeLocalFilesDelegate();
        NativeUserDirectoryManager nativeUserDirectoryManager = this.l.getNativeUserDirectoryManager();
        FullAuthenticatedScopeConfiguration fullAuthenticatedScopeConfiguration = this.m;
        this.n.getClass();
        NativeOfflinePluginRegistry nativeOfflinePluginRegistry = new NativeOfflinePluginRegistry(EmptyList.c);
        xx2 xx2Var = new xx2(2, this);
        Scheduler scheduler = e.a;
        Observable observableObserveOn = this.f49p.observeOn(new i(xx2Var, false, false));
        v03.i(observableObserveOn, "internal fun initInterna…henticatedScopeImpl\n    }");
        NativeFullAuthenticatedScopeImpl nativeFullAuthenticatedScopeImplCreate = companion.create(nativeTimerManagerThreadImpl, nativeRouter, nativePrefs, nativeRemoteConfigImpl, nativeShorelineLoggerImpl, nativeConnectivityManager, nativeConnectivityApplicationScope, nativeSession, authenticatedScope, nativeApplicationScopeImpl, nativeLocalFilesDelegate, nativeUserDirectoryManager, fullAuthenticatedScopeConfiguration, nativeOfflinePluginRegistry, new NativeForegroundProvider(observableObserveOn));
        this.j.getClass();
        v03.j(nativeFullAuthenticatedScopeImplCreate, "coreFullAuthenticatedScope");
        sharedCosmosRouterApi.onCoreSessionInitialized();
        new SessionClientEsperanto(EsSessionEsperantoKt.createSessionClient(new SchedulingTransport(u10Var.b, sessionApi.getNativeSession().getInternalTransportToNative())));
        Logger.a("CoreFullSessionService init completed", new Object[0]);
        return nativeFullAuthenticatedScopeImplCreate;
    }

    public final void b() {
        int iOrdinal = this.q.ordinal();
        if (iOrdinal == 0) {
            ((u10) this.a).a.runBlocking(new s00(this, 1));
        } else {
            if (iOrdinal != 1) {
                return;
            }
            c();
        }
    }

    public final void c() {
        this.j.getClass();
        NativeFullAuthenticatedScopeImpl nativeFullAuthenticatedScopeImpl = this.r;
        if (nativeFullAuthenticatedScopeImpl == null) {
            v03.Q("authenticatedScopeImpl");
            throw null;
        }
        nativeFullAuthenticatedScopeImpl.prepareForShutdown();
        NativeFullAuthenticatedScopeImpl nativeFullAuthenticatedScopeImpl2 = this.r;
        if (nativeFullAuthenticatedScopeImpl2 == null) {
            v03.Q("authenticatedScopeImpl");
            throw null;
        }
        nativeFullAuthenticatedScopeImpl2.flushCaches();
        NativeFullAuthenticatedScopeImpl nativeFullAuthenticatedScopeImpl3 = this.r;
        if (nativeFullAuthenticatedScopeImpl3 == null) {
            v03.Q("authenticatedScopeImpl");
            throw null;
        }
        nativeFullAuthenticatedScopeImpl3.destroy();
        Logger.a("CoreFullSessionService shutdown completed", new Object[0]);
    }

    @Override // p.sb2
    public final void shutdown() throws InterruptedException {
        Logger.a("CoreFullSessionService shutdown called", new Object[0]);
        if (!this.o.a()) {
            b();
            return;
        }
        Logger.a("CoreFullSessionService performing shutdown with watchdog...", new Object[0]);
        py0 py0Var = new py0() { // from class: com.spotify.core.corefullsessionservice.CoreFullSessionService$shutdown$1
            {
                super(0);
            }

            @Override // p.py0
            public final Object invoke() {
                this.this$0.b();
                return lq2.a;
            }
        };
        final py0 py0Var2 = new py0() { // from class: com.spotify.core.corefullsessionservice.CoreFullSessionService$shutdown$2
            final /* synthetic */ long $timeoutSeconds = 4;

            {
                super(0);
            }

            @Override // p.py0
            public final Object invoke() {
                Logger.a("CoreFullSessionService shutdown timeout (" + this.$timeoutSeconds + " seconds)!", new Object[0]);
                Logger.a("CoreFullSessionService killing core thread!", new Object[0]);
                ((u10) this.this$0.a).a.kill(11);
                return lq2.a;
            }
        };
        ok2 ok2Var = new ok2(new py0() { // from class: com.spotify.core.corefullsessionservice.CoreFullSessionService$executeWithWatchdog$watchdogThread$1
            final /* synthetic */ long $timeoutSeconds = 4;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // p.py0
            public final Object invoke() {
                Object objB;
                Object obj = lq2.a;
                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(this.$timeoutSeconds));
                    objB = obj;
                } catch (Throwable th) {
                    objB = kotlin.a.b(th);
                }
                py0 py0Var3 = py0Var2;
                if (!(objB instanceof Result.Failure)) {
                    py0Var3.invoke();
                }
                return obj;
            }
        });
        ok2Var.start();
        py0Var.invoke();
        ok2Var.interrupt();
        ok2Var.join();
    }
}
