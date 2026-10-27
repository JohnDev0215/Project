package com.spotify.connectivity.connectivityservice;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.spotify.base.java.logging.Logger;
import com.spotify.concurrency.asyncimpl.NativeTimerManagerThreadImpl;
import com.spotify.connectivity.AnalyticsDelegate;
import com.spotify.connectivity.ApplicationScopeConfiguration;
import com.spotify.connectivity.ConnectivitySdk;
import com.spotify.connectivity.MobileDeviceInfo;
import com.spotify.connectivity.NativeApplicationScope;
import com.spotify.connectivity.NativeConnectionTypeProvider;
import com.spotify.connectivity.NativeConnectivityPolicyProvider;
import com.spotify.connectivity.TimerManagerThreadScheduler;
import com.spotify.connectivity.auth.CredentialsStorage;
import com.spotify.connectivity.auth.LoginControllerDelegate;
import com.spotify.connectivity.auth.NativeCredentialsStorage;
import com.spotify.connectivity.auth.NativeLoginController;
import com.spotify.connectivity.auth.NativeSession;
import com.spotify.connectivity.connectiontype.ConnectionType;
import com.spotify.connectivity.connectivityapi.ConnectivityApi;
import com.spotify.connectivity.connectivityapi.StoredCredentials;
import com.spotify.connectivity.connectivitysdkcredentialsstorage.PrefsCredentialsStorage;
import com.spotify.connectivity.connectivitysdkpolicyimpl.DefaultConnectionTypeProvider;
import com.spotify.connectivity.connectivitysdkpolicyimpl.DefaultConnectivityPolicyProvider;
import com.spotify.connectivity.connectivitysdkpolicyimpl.NativeConnectivityManager;
import com.spotify.connectivity.httpconnection.HttpConnectionFactoryImpl;
import com.spotify.core.http.NativeHttpConnection;
import com.spotify.cosmos.sharedcosmosrouterapi.SharedCosmosRouterApi;
import com.spotify.prefs.prefsimpl.NativePrefs;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.g;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import okhttp3.OkHttpClient;
import p.f60;
import p.l91;
import p.lx1;
import p.n10;
import p.n70;
import p.o10;
import p.py0;
import p.r10;
import p.r91;
import p.s91;
import p.sb2;
import p.u10;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectivityService implements ConnectivityApi, sb2 {
    private final AnalyticsDelegate analyticsDelegate;
    private final Disposable connectionTypeDisposable;
    private final Observable<ConnectionType> connectionTypeObservable;
    private final ApplicationScopeConfiguration connectivityApplicationScopeConfiguration;
    private final ServiceConnectivityObserver connectivityObserver;
    private final Context context;
    private final n10 corePreferencesApi;
    private final r10 coreThreadingApi;
    private InternalLoginControllerDelegate loginControllerDelegate;
    private final Handler mainLooperHandler;
    public NativeConnectionTypeProvider nativeConnectionTypeProvider;
    public NativeApplicationScope nativeConnectivityApplicationScope;
    public NativeConnectivityManager nativeConnectivityManager;
    public NativeConnectivityPolicyProvider nativeConnectivityPolicyProvider;
    public NativeCredentialsStorage nativeCredentialsStorage;
    public NativeLoginController nativeLoginController;
    private final OkHttpClient okHttpClient;
    private py0 preShutdownHook;
    private final l91 processLifecycle;
    private final r91 reconnectObserver;
    private volatile boolean shouldTryReconnectNow;

    public static final class InternalLoginControllerDelegate implements LoginControllerDelegate {
        private final List<LoginControllerDelegate> delegates;
        private final NativeLoginController nativeLoginController;
        private NativeSession nativeSession;

        public InternalLoginControllerDelegate(NativeLoginController nativeLoginController) {
            v03.j(nativeLoginController, "nativeLoginController");
            this.nativeLoginController = nativeLoginController;
            this.delegates = new ArrayList();
        }

        public final void addLoginControllerDelegate(LoginControllerDelegate loginControllerDelegate) {
            v03.j(loginControllerDelegate, "delegate");
            synchronized (this) {
                this.delegates.add(loginControllerDelegate);
            }
        }

        public final NativeSession adoptNativeSession() {
            f60.g();
            NativeSession nativeSession = this.nativeSession;
            if (nativeSession == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            this.nativeSession = null;
            return nativeSession;
        }

        public final void maybeDestroyNativeSession$src_main_java_com_spotify_connectivity_connectivityservice_connectivityservice_kt() {
            f60.g();
            NativeSession nativeSession = this.nativeSession;
            if (nativeSession != null) {
                nativeSession.destroy();
            }
            this.nativeSession = null;
        }

        @Override // com.spotify.connectivity.auth.LoginControllerDelegate
        public void onLogin() {
            Logger.e("InternalLoginControllerDelegate::onLogin", new Object[0]);
            f60.g();
            NativeSession nativeSessionStealNativeSession = this.nativeLoginController.stealNativeSession();
            if (nativeSessionStealNativeSession == null) {
                throw new IllegalStateException("stealNativeSession must never return null".toString());
            }
            this.nativeSession = nativeSessionStealNativeSession;
            synchronized (this) {
                Iterator<T> it = this.delegates.iterator();
                while (it.hasNext()) {
                    ((LoginControllerDelegate) it.next()).onLogin();
                }
            }
        }

        @Override // com.spotify.connectivity.auth.LoginControllerDelegate
        public void onLogout() {
            Logger.e("InternalLoginControllerDelegate::onLogout", new Object[0]);
            synchronized (this) {
                Iterator<T> it = this.delegates.iterator();
                while (it.hasNext()) {
                    ((LoginControllerDelegate) it.next()).onLogout();
                }
            }
            maybeDestroyNativeSession$src_main_java_com_spotify_connectivity_connectivityservice_connectivityservice_kt();
        }
    }

    public ConnectivityService(AnalyticsDelegate analyticsDelegate, r10 r10Var, n10 n10Var, ApplicationScopeConfiguration applicationScopeConfiguration, MobileDeviceInfo mobileDeviceInfo, final SharedCosmosRouterApi sharedCosmosRouterApi, Context context, OkHttpClient okHttpClient, Observable<ConnectionType> observable) {
        v03.j(analyticsDelegate, "analyticsDelegate");
        v03.j(r10Var, "coreThreadingApi");
        v03.j(n10Var, "corePreferencesApi");
        v03.j(applicationScopeConfiguration, "connectivityApplicationScopeConfiguration");
        v03.j(mobileDeviceInfo, "mobileDeviceInfo");
        v03.j(sharedCosmosRouterApi, "sharedCosmosRouterApi");
        v03.j(context, "context");
        v03.j(okHttpClient, "okHttpClient");
        v03.j(observable, "connectionTypeObservable");
        this.analyticsDelegate = analyticsDelegate;
        this.coreThreadingApi = r10Var;
        this.corePreferencesApi = n10Var;
        this.connectivityApplicationScopeConfiguration = applicationScopeConfiguration;
        this.context = context;
        this.okHttpClient = okHttpClient;
        this.connectionTypeObservable = observable;
        this.shouldTryReconnectNow = true;
        NativeHttpConnection.Companion.initialize(new HttpConnectionFactoryImpl(okHttpClient));
        ConnectivitySdk.Companion.setMobileDeviceInfo(mobileDeviceInfo);
        ((u10) r10Var).a.runBlocking(new Runnable() { // from class: com.spotify.connectivity.connectivityservice.ConnectivityService.1
            @Override // java.lang.Runnable
            public final void run() {
                NativeConnectivityManager nativeConnectivityManagerCreate = NativeConnectivityManager.Companion.create(sharedCosmosRouterApi.getNativeRouter(), new TimerManagerThreadScheduler(((u10) this.coreThreadingApi).a), this.analyticsDelegate, true);
                PrefsCredentialsStorage.Companion companion = PrefsCredentialsStorage.Companion;
                NativePrefs nativePrefs = ((o10) this.corePreferencesApi).a;
                String deviceId = this.connectivityApplicationScopeConfiguration.getDeviceId();
                if (deviceId == null) {
                    throw new IllegalStateException("Required value was null.".toString());
                }
                NativeCredentialsStorage nativeCredentialsStorageCreate = companion.create(nativePrefs, deviceId);
                NativeConnectionTypeProvider nativeConnectionTypeProviderCreate = DefaultConnectionTypeProvider.Companion.create(nativeConnectivityManagerCreate);
                NativeConnectivityPolicyProvider nativeConnectivityPolicyProviderCreate = DefaultConnectivityPolicyProvider.Companion.create(nativeConnectivityManagerCreate);
                NativeApplicationScope nativeApplicationScopeCreate = NativeApplicationScope.Companion.create(((u10) this.coreThreadingApi).a, sharedCosmosRouterApi.getNativeRouter(), this.analyticsDelegate, nativeConnectionTypeProviderCreate, nativeConnectivityPolicyProviderCreate, nativeCredentialsStorageCreate, this.connectivityApplicationScopeConfiguration);
                NativeLoginController nativeLoginControllerCreateLoginController = nativeApplicationScopeCreate.createLoginController(((u10) this.coreThreadingApi).a, sharedCosmosRouterApi.getNativeRouter(), this.context);
                InternalLoginControllerDelegate internalLoginControllerDelegate = new InternalLoginControllerDelegate(nativeLoginControllerCreateLoginController);
                nativeLoginControllerCreateLoginController.setDelegate(internalLoginControllerDelegate);
                this.setNativeConnectivityManager(nativeConnectivityManagerCreate);
                this.setNativeCredentialsStorage(nativeCredentialsStorageCreate);
                this.setNativeConnectionTypeProvider(nativeConnectionTypeProviderCreate);
                this.setNativeConnectivityPolicyProvider(nativeConnectivityPolicyProviderCreate);
                this.setNativeConnectivityApplicationScope(nativeApplicationScopeCreate);
                this.setNativeLoginController(nativeLoginControllerCreateLoginController);
                this.loginControllerDelegate = internalLoginControllerDelegate;
            }
        });
        this.connectivityObserver = new ServiceConnectivityObserver(getNativeLoginController(), getNativeConnectivityManager(), context);
        Disposable disposableSubscribe = observable.subscribe(new g() { // from class: com.spotify.connectivity.connectivityservice.ConnectivityService.2
            @Override // io.reactivex.rxjava3.functions.g
            public final void accept(ConnectionType connectionType) {
                ServiceConnectivityObserver serviceConnectivityObserver = ConnectivityService.this.connectivityObserver;
                v03.i(connectionType, "it");
                serviceConnectivityObserver.setConnectivityType(connectionType);
            }
        });
        v03.i(disposableSubscribe, "connectionTypeObservable…ctivityType(it)\n        }");
        this.connectionTypeDisposable = disposableSubscribe;
        lx1 lx1Var = lx1.q;
        this.processLifecycle = lx1.q.n;
        Handler handler = new Handler(Looper.getMainLooper());
        this.mainLooperHandler = handler;
        this.reconnectObserver = new n70() { // from class: com.spotify.connectivity.connectivityservice.ConnectivityService.3
            @Override // p.n70
            public void onCreate(s91 s91Var) {
                v03.j(s91Var, "owner");
            }

            @Override // p.n70
            public void onDestroy(s91 s91Var) {
                v03.j(s91Var, "owner");
            }

            @Override // p.n70
            public void onPause(s91 s91Var) {
                v03.j(s91Var, "owner");
            }

            @Override // p.n70
            public void onResume(s91 s91Var) {
                v03.j(s91Var, "owner");
            }

            @Override // p.n70
            public void onStart(s91 s91Var) {
                v03.j(s91Var, "owner");
                if (ConnectivityService.this.shouldTryReconnectNow) {
                    NativeTimerManagerThreadImpl nativeTimerManagerThreadImpl = ((u10) ConnectivityService.this.coreThreadingApi).a;
                    final ConnectivityService connectivityService = ConnectivityService.this;
                    nativeTimerManagerThreadImpl.post(new Runnable() { // from class: com.spotify.connectivity.connectivityservice.ConnectivityService$3$onStart$1
                        @Override // java.lang.Runnable
                        public final void run() {
                            if (connectivityService.shouldTryReconnectNow) {
                                connectivityService.getNativeLoginController().tryReconnectNow(false);
                            }
                        }
                    });
                }
            }

            @Override // p.n70
            public void onStop(s91 s91Var) {
                v03.j(s91Var, "owner");
            }
        };
        handler.post(new Runnable() { // from class: com.spotify.connectivity.connectivityservice.ConnectivityService.4
            @Override // java.lang.Runnable
            public final void run() {
                ConnectivityService.this.processLifecycle.a(ConnectivityService.this.reconnectObserver);
            }
        });
    }

    @Override // com.spotify.connectivity.connectivityapi.ConnectivityApi
    public void addLoginControllerDelegate(LoginControllerDelegate loginControllerDelegate) {
        v03.j(loginControllerDelegate, "delegate");
        InternalLoginControllerDelegate internalLoginControllerDelegate = this.loginControllerDelegate;
        if (internalLoginControllerDelegate != null) {
            internalLoginControllerDelegate.addLoginControllerDelegate(loginControllerDelegate);
        } else {
            v03.Q("loginControllerDelegate");
            throw null;
        }
    }

    @Override // com.spotify.connectivity.connectivityapi.ConnectivityApi
    public NativeSession adoptNativeSession() {
        InternalLoginControllerDelegate internalLoginControllerDelegate = this.loginControllerDelegate;
        if (internalLoginControllerDelegate != null) {
            return internalLoginControllerDelegate.adoptNativeSession();
        }
        v03.Q("loginControllerDelegate");
        throw null;
    }

    @Override // com.spotify.connectivity.connectivityapi.ConnectivityApi
    public void blockingLogout() {
        getNativeLoginController().blockingLogout();
    }

    /* JADX INFO: renamed from: getApi, reason: merged with bridge method [inline-methods] */
    public ConnectivityApi m141getApi() {
        return this;
    }

    @Override // com.spotify.connectivity.connectivityapi.ConnectivityApi
    public NativeConnectionTypeProvider getNativeConnectionTypeProvider() {
        NativeConnectionTypeProvider nativeConnectionTypeProvider = this.nativeConnectionTypeProvider;
        if (nativeConnectionTypeProvider != null) {
            return nativeConnectionTypeProvider;
        }
        v03.Q("nativeConnectionTypeProvider");
        throw null;
    }

    @Override // com.spotify.connectivity.connectivityapi.ConnectivityApi
    public NativeApplicationScope getNativeConnectivityApplicationScope() {
        NativeApplicationScope nativeApplicationScope = this.nativeConnectivityApplicationScope;
        if (nativeApplicationScope != null) {
            return nativeApplicationScope;
        }
        v03.Q("nativeConnectivityApplicationScope");
        throw null;
    }

    @Override // com.spotify.connectivity.connectivityapi.ConnectivityApi
    public NativeConnectivityManager getNativeConnectivityManager() {
        NativeConnectivityManager nativeConnectivityManager = this.nativeConnectivityManager;
        if (nativeConnectivityManager != null) {
            return nativeConnectivityManager;
        }
        v03.Q("nativeConnectivityManager");
        throw null;
    }

    @Override // com.spotify.connectivity.connectivityapi.ConnectivityApi
    public NativeConnectivityPolicyProvider getNativeConnectivityPolicyProvider() {
        NativeConnectivityPolicyProvider nativeConnectivityPolicyProvider = this.nativeConnectivityPolicyProvider;
        if (nativeConnectivityPolicyProvider != null) {
            return nativeConnectivityPolicyProvider;
        }
        v03.Q("nativeConnectivityPolicyProvider");
        throw null;
    }

    @Override // com.spotify.connectivity.connectivityapi.ConnectivityApi
    public NativeCredentialsStorage getNativeCredentialsStorage() {
        NativeCredentialsStorage nativeCredentialsStorage = this.nativeCredentialsStorage;
        if (nativeCredentialsStorage != null) {
            return nativeCredentialsStorage;
        }
        v03.Q("nativeCredentialsStorage");
        throw null;
    }

    @Override // com.spotify.connectivity.connectivityapi.ConnectivityApi
    public NativeLoginController getNativeLoginController() {
        NativeLoginController nativeLoginController = this.nativeLoginController;
        if (nativeLoginController != null) {
            return nativeLoginController;
        }
        v03.Q("nativeLoginController");
        throw null;
    }

    public final OkHttpClient getOkHttpClient() {
        return this.okHttpClient;
    }

    public void setNativeConnectionTypeProvider(NativeConnectionTypeProvider nativeConnectionTypeProvider) {
        v03.j(nativeConnectionTypeProvider, "<set-?>");
        this.nativeConnectionTypeProvider = nativeConnectionTypeProvider;
    }

    public void setNativeConnectivityApplicationScope(NativeApplicationScope nativeApplicationScope) {
        v03.j(nativeApplicationScope, "<set-?>");
        this.nativeConnectivityApplicationScope = nativeApplicationScope;
    }

    public void setNativeConnectivityManager(NativeConnectivityManager nativeConnectivityManager) {
        v03.j(nativeConnectivityManager, "<set-?>");
        this.nativeConnectivityManager = nativeConnectivityManager;
    }

    public void setNativeConnectivityPolicyProvider(NativeConnectivityPolicyProvider nativeConnectivityPolicyProvider) {
        v03.j(nativeConnectivityPolicyProvider, "<set-?>");
        this.nativeConnectivityPolicyProvider = nativeConnectivityPolicyProvider;
    }

    public void setNativeCredentialsStorage(NativeCredentialsStorage nativeCredentialsStorage) {
        v03.j(nativeCredentialsStorage, "<set-?>");
        this.nativeCredentialsStorage = nativeCredentialsStorage;
    }

    public void setNativeLoginController(NativeLoginController nativeLoginController) {
        v03.j(nativeLoginController, "<set-?>");
        this.nativeLoginController = nativeLoginController;
    }

    @Override // com.spotify.connectivity.connectivityapi.ConnectivityApi
    public void setPreShutdownHook(py0 py0Var) {
        v03.j(py0Var, "hook");
        this.preShutdownHook = py0Var;
    }

    @Override // p.sb2
    public void shutdown() {
        this.shouldTryReconnectNow = false;
        this.mainLooperHandler.post(new Runnable() { // from class: com.spotify.connectivity.connectivityservice.ConnectivityService.shutdown.1
            @Override // java.lang.Runnable
            public final void run() {
                ConnectivityService.this.processLifecycle.c(ConnectivityService.this.reconnectObserver);
            }
        });
        this.connectivityObserver.destroy();
        this.connectionTypeDisposable.dispose();
        py0 py0Var = this.preShutdownHook;
        if (py0Var != null) {
            py0Var.invoke();
        }
        ((u10) this.coreThreadingApi).a.runBlocking(new Runnable() { // from class: com.spotify.connectivity.connectivityservice.ConnectivityService.shutdown.2
            @Override // java.lang.Runnable
            public final void run() {
                InternalLoginControllerDelegate internalLoginControllerDelegate = ConnectivityService.this.loginControllerDelegate;
                if (internalLoginControllerDelegate == null) {
                    v03.Q("loginControllerDelegate");
                    throw null;
                }
                internalLoginControllerDelegate.maybeDestroyNativeSession$src_main_java_com_spotify_connectivity_connectivityservice_connectivityservice_kt();
                ConnectivityService.this.getNativeLoginController().prepareForShutdown();
                ConnectivityService.this.getNativeConnectivityApplicationScope().prepareForShutdown();
                ConnectivityService.this.getNativeLoginController().destroy();
                ConnectivityService.this.getNativeConnectivityApplicationScope().destroy();
                ConnectivityService.this.getNativeConnectivityPolicyProvider().destroy();
                ConnectivityService.this.getNativeConnectionTypeProvider().destroy();
                ConnectivityService.this.getNativeCredentialsStorage().destroy();
                ConnectivityService.this.getNativeConnectivityManager().destroy();
            }
        });
    }

    @Override // com.spotify.connectivity.connectivityapi.ConnectivityApi
    public Single<StoredCredentials> storedCredentials() {
        Single<StoredCredentials> singleCreate = Single.create(new SingleOnSubscribe() { // from class: com.spotify.connectivity.connectivityservice.ConnectivityService.storedCredentials.1
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(final SingleEmitter<StoredCredentials> singleEmitter) {
                NativeTimerManagerThreadImpl nativeTimerManagerThreadImpl = ((u10) ConnectivityService.this.coreThreadingApi).a;
                final ConnectivityService connectivityService = ConnectivityService.this;
                nativeTimerManagerThreadImpl.runBlocking(new Runnable() { // from class: com.spotify.connectivity.connectivityservice.ConnectivityService.storedCredentials.1.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        CredentialsStorage.StoredCredentialsAndUsername storedCredentials = connectivityService.getNativeCredentialsStorage().getStoredCredentials();
                        if (storedCredentials == null) {
                            singleEmitter.onSuccess(StoredCredentials.NonAuthenticated.INSTANCE);
                        } else {
                            singleEmitter.onSuccess(new StoredCredentials.Authenticated(storedCredentials.getCanonicalUsername()));
                        }
                    }
                });
            }
        });
        v03.i(singleCreate, "override fun storedCrede…        }\n        }\n    }");
        return singleCreate;
    }
}
