package com.spotify.connectivity.platformconnectiontype;

import android.app.Application;
import android.content.Context;
import com.google.common.base.Optional;
import com.spotify.connectivity.connectiontype.InternetMonitor;
import com.spotify.connectivity.connectiontype.SpotifyConnectivityManager;
import io.reactivex.rxjava3.core.Scheduler;

/* JADX INFO: loaded from: classes.dex */
public interface ConnectionTypeModuleNoRcProps {
    static ConnectivityListener provideConnectivityListener(Application application, ConnectivityUtil connectivityUtil, PlatformConnectionTypeProperties platformConnectionTypeProperties) {
        return ConnectivityListenerSingletonFactory.createConnectivityListener(application, connectivityUtil, platformConnectionTypeProperties);
    }

    static ConnectivityUtil provideConnectivityUtil(PlatformConnectionTypeProperties platformConnectionTypeProperties) {
        return new ConnectivityUtilImpl(platformConnectionTypeProperties);
    }

    static InternetMonitor provideInternetMonitor(ConnectivityListener connectivityListener, FlightModeEnabledMonitor flightModeEnabledMonitor, MobileDataDisabledMonitor mobileDataDisabledMonitor) {
        return new InternetMonitorImpl(new ConnectivityMonitorImpl(connectivityListener), flightModeEnabledMonitor, mobileDataDisabledMonitor);
    }

    static PlatformConnectionTypeProperties providePlatformConnectionTypeProperties() {
        return new PlatformConnectionTypeProperties() { // from class: com.spotify.connectivity.platformconnectiontype.ConnectionTypeModuleNoRcProps.1
            @Override // com.spotify.connectivity.platformconnectiontype.PlatformConnectionTypeProperties
            public boolean netCapabilitiesValidatedDisregardedEnabled() {
                return false;
            }

            @Override // com.spotify.connectivity.platformconnectiontype.PlatformConnectionTypeProperties
            public boolean shouldUseSingleThread() {
                return false;
            }
        };
    }

    static Optional<SpotifyConnectivityManager> provideSpotifyConnectivityManager(Context context, ConnectivityUtil connectivityUtil, Scheduler scheduler) {
        return Optional.e(new SpotifyConnectivityManagerImpl(context, connectivityUtil, scheduler));
    }
}
