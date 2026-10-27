package com.google.firebase.perf.injection.modules;

import androidx.annotation.NonNull;
import com.google.android.datatransport.TransportFactory;
import com.google.firebase.FirebaseApp;
import com.google.firebase.inject.Provider;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.perf.config.ConfigResolver;
import com.google.firebase.perf.config.RemoteConfigManager;
import com.google.firebase.perf.session.SessionManager;
import com.google.firebase.remoteconfig.RemoteConfigComponent;

/* JADX INFO: loaded from: classes6.dex */
public class FirebasePerformanceModule {
    private final FirebaseApp firebaseApp;
    private final FirebaseInstallationsApi firebaseInstallations;
    private final Provider<RemoteConfigComponent> remoteConfigComponentProvider;
    private final Provider<TransportFactory> transportFactoryProvider;

    public FirebasePerformanceModule(@NonNull FirebaseApp firebaseApp, @NonNull FirebaseInstallationsApi firebaseInstallationsApi, @NonNull Provider<RemoteConfigComponent> provider, @NonNull Provider<TransportFactory> provider2) {
        this.firebaseApp = firebaseApp;
        this.firebaseInstallations = firebaseInstallationsApi;
        this.remoteConfigComponentProvider = provider;
        this.transportFactoryProvider = provider2;
    }

    FirebaseApp providesFirebaseApp() {
        return this.firebaseApp;
    }

    FirebaseInstallationsApi providesFirebaseInstallations() {
        return this.firebaseInstallations;
    }

    Provider<RemoteConfigComponent> providesRemoteConfigComponent() {
        return this.remoteConfigComponentProvider;
    }

    Provider<TransportFactory> providesTransportFactoryProvider() {
        return this.transportFactoryProvider;
    }

    RemoteConfigManager providesRemoteConfigManager() {
        return RemoteConfigManager.getInstance();
    }

    ConfigResolver providesConfigResolver() {
        return ConfigResolver.getInstance();
    }

    SessionManager providesSessionManager() {
        return SessionManager.getInstance();
    }
}
