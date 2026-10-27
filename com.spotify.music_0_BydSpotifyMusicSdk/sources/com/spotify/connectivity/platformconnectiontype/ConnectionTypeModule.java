package com.spotify.connectivity.platformconnectiontype;

import android.app.Application;
import android.content.Context;
import com.google.common.base.Optional;
import com.spotify.connectivity.connectiontype.InternetMonitor;
import com.spotify.connectivity.connectiontype.OfflineStateController;
import com.spotify.connectivity.connectiontype.SpotifyConnectivityManager;
import com.spotify.connectivity.connectiontypeflags.NetCapabilitiesValidatedDisabled;
import com.spotify.connectivity.connectiontypeflags.ShouldUseSingleThread;
import io.reactivex.rxjava3.core.Scheduler;

/* JADX INFO: loaded from: classes.dex */
public interface ConnectionTypeModule {
    static ConnectivityListener provideConnectivityListener(Application application, ConnectivityUtil connectivityUtil, PlatformConnectionTypeProperties platformConnectionTypeProperties) {
        return ConnectivityListenerSingletonFactory.createConnectivityListener(application, connectivityUtil, platformConnectionTypeProperties);
    }

    static ConnectivityUtil provideConnectivityUtil(PlatformConnectionTypeProperties platformConnectionTypeProperties) {
        return new ConnectivityUtilImpl(platformConnectionTypeProperties);
    }

    static InternetMonitor provideInternetMonitor(ConnectivityListener connectivityListener, FlightModeEnabledMonitor flightModeEnabledMonitor, MobileDataDisabledMonitor mobileDataDisabledMonitor) {
        return new InternetMonitorImpl(new ConnectivityMonitorImpl(connectivityListener), flightModeEnabledMonitor, mobileDataDisabledMonitor);
    }

    static OfflineStateController provideOfflineStateController(CoreConnectionState coreConnectionState, Scheduler scheduler) {
        return new OfflineStateControllerCosmos(coreConnectionState, scheduler);
    }

    static PlatformConnectionTypeProperties providePlatformConnectionTypeProperties(@NetCapabilitiesValidatedDisabled final boolean z, @ShouldUseSingleThread final boolean z2) {
        return new PlatformConnectionTypeProperties() { // from class: com.spotify.connectivity.platformconnectiontype.ConnectionTypeModule.1
            @Override // com.spotify.connectivity.platformconnectiontype.PlatformConnectionTypeProperties
            public boolean netCapabilitiesValidatedDisregardedEnabled() {
                return z;
            }

            @Override // com.spotify.connectivity.platformconnectiontype.PlatformConnectionTypeProperties
            public boolean shouldUseSingleThread() {
                return z2;
            }
        };
    }

    static Optional<SpotifyConnectivityManager> provideSpotifyConnectivityManager(Context context, ConnectivityUtil connectivityUtil, Scheduler scheduler) {
        return Optional.e(new SpotifyConnectivityManagerImpl(context, connectivityUtil, scheduler));
    }
}
