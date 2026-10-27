package com.spotify.connectivity.platformconnectiontype;

import android.content.Context;
import com.google.common.base.Optional;
import com.spotify.connectivity.connectiontype.SpotifyConnectivityManager;
import io.reactivex.rxjava3.core.Scheduler;
import p.ul0;
import p.vr2;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectionTypeModuleNoRcProps_ProvideSpotifyConnectivityManagerFactory implements ul0 {
    private final xy1 connectivityUtilProvider;
    private final xy1 contextProvider;
    private final xy1 debounceSchedulerProvider;

    public ConnectionTypeModuleNoRcProps_ProvideSpotifyConnectivityManagerFactory(xy1 xy1Var, xy1 xy1Var2, xy1 xy1Var3) {
        this.contextProvider = xy1Var;
        this.connectivityUtilProvider = xy1Var2;
        this.debounceSchedulerProvider = xy1Var3;
    }

    public static ConnectionTypeModuleNoRcProps_ProvideSpotifyConnectivityManagerFactory create(xy1 xy1Var, xy1 xy1Var2, xy1 xy1Var3) {
        return new ConnectionTypeModuleNoRcProps_ProvideSpotifyConnectivityManagerFactory(xy1Var, xy1Var2, xy1Var3);
    }

    public static Optional<SpotifyConnectivityManager> provideSpotifyConnectivityManager(Context context, ConnectivityUtil connectivityUtil, Scheduler scheduler) {
        Optional<SpotifyConnectivityManager> optionalProvideSpotifyConnectivityManager = ConnectionTypeModuleNoRcProps.provideSpotifyConnectivityManager(context, connectivityUtil, scheduler);
        vr2.b(optionalProvideSpotifyConnectivityManager);
        return optionalProvideSpotifyConnectivityManager;
    }

    @Override // p.xy1
    public Optional<SpotifyConnectivityManager> get() {
        return provideSpotifyConnectivityManager((Context) this.contextProvider.get(), (ConnectivityUtil) this.connectivityUtilProvider.get(), (Scheduler) this.debounceSchedulerProvider.get());
    }
}
