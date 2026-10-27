package com.spotify.connectivity.platformconnectiontype;

import android.content.Context;
import io.reactivex.rxjava3.core.Scheduler;
import p.ul0;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class SpotifyConnectivityManagerImpl_Factory implements ul0 {
    private final xy1 connectivityUtilProvider;
    private final xy1 contextProvider;
    private final xy1 debounceSchedulerProvider;

    public SpotifyConnectivityManagerImpl_Factory(xy1 xy1Var, xy1 xy1Var2, xy1 xy1Var3) {
        this.contextProvider = xy1Var;
        this.connectivityUtilProvider = xy1Var2;
        this.debounceSchedulerProvider = xy1Var3;
    }

    public static SpotifyConnectivityManagerImpl_Factory create(xy1 xy1Var, xy1 xy1Var2, xy1 xy1Var3) {
        return new SpotifyConnectivityManagerImpl_Factory(xy1Var, xy1Var2, xy1Var3);
    }

    public static SpotifyConnectivityManagerImpl newInstance(Context context, ConnectivityUtil connectivityUtil, Scheduler scheduler) {
        return new SpotifyConnectivityManagerImpl(context, connectivityUtil, scheduler);
    }

    @Override // p.xy1
    public SpotifyConnectivityManagerImpl get() {
        return newInstance((Context) this.contextProvider.get(), (ConnectivityUtil) this.connectivityUtilProvider.get(), (Scheduler) this.debounceSchedulerProvider.get());
    }
}
