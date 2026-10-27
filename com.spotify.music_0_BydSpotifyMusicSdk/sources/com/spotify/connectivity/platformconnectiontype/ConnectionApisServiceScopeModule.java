package com.spotify.connectivity.platformconnectiontype;

import android.content.Context;
import com.google.common.base.Optional;
import com.spotify.connectivity.connectiontype.ConnectionApis;
import com.spotify.connectivity.connectiontype.ConnectionType;
import com.spotify.connectivity.connectiontype.InternetMonitor;
import com.spotify.connectivity.connectiontype.SpotifyConnectivityManager;
import io.reactivex.rxjava3.core.Observable;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public interface ConnectionApisServiceScopeModule {
    public static final Companion Companion = Companion.$$INSTANCE;

    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public final ConnectionApis provideConnectionApis(ConnectivityListener connectivityListener, FlightModeEnabledMonitor flightModeEnabledMonitor, MobileDataDisabledMonitor mobileDataDisabledMonitor, InternetMonitor internetMonitor, Optional<SpotifyConnectivityManager> optional) {
            v03.j(connectivityListener, "connectivityListener");
            v03.j(flightModeEnabledMonitor, "flightModeEnabledMonitor");
            v03.j(mobileDataDisabledMonitor, "mobileDataDisabledMonitor");
            v03.j(internetMonitor, "internetMonitor");
            v03.j(optional, "spotifyConnectivityManager");
            if (!optional.d()) {
                return new ConnectionApisImplLegacy(connectivityListener, flightModeEnabledMonitor, mobileDataDisabledMonitor, internetMonitor);
            }
            Object objC = optional.c();
            v03.i(objC, "spotifyConnectivityManager.get()");
            return new ConnectionApisImpl(flightModeEnabledMonitor, mobileDataDisabledMonitor, internetMonitor, (SpotifyConnectivityManager) objC);
        }

        public final Observable<ConnectionType> provideConnectionTypeObservable(ConnectionApis connectionApis) {
            v03.j(connectionApis, "connectionApis");
            return connectionApis.getConnectionTypeObservable();
        }

        public final FlightModeEnabledMonitor provideFlightModeEnabledMonitor(Context context) {
            v03.j(context, "context");
            return new FlightModeEnabledMonitorImpl(context);
        }
    }

    static ConnectionApis provideConnectionApis(ConnectivityListener connectivityListener, FlightModeEnabledMonitor flightModeEnabledMonitor, MobileDataDisabledMonitor mobileDataDisabledMonitor, InternetMonitor internetMonitor, Optional<SpotifyConnectivityManager> optional) {
        return Companion.provideConnectionApis(connectivityListener, flightModeEnabledMonitor, mobileDataDisabledMonitor, internetMonitor, optional);
    }

    static Observable<ConnectionType> provideConnectionTypeObservable(ConnectionApis connectionApis) {
        return Companion.provideConnectionTypeObservable(connectionApis);
    }

    static FlightModeEnabledMonitor provideFlightModeEnabledMonitor(Context context) {
        return Companion.provideFlightModeEnabledMonitor(context);
    }
}
