package com.spotify.connectivity.connectiontype;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import p.c00;
import p.dv0;

/* JADX INFO: loaded from: classes.dex */
public interface InternetMonitor {
    Observable<InternetState> getInternetState();

    dv0 getInternetStateFlow();

    Observable<Boolean> getPermanentOfflineState();

    dv0 getPermanentOfflineStateFlow();

    Object isPermanentOffline(c00<? super Boolean> c00Var);

    boolean isPermanentOffline(ConnectionType connectionType, boolean z, boolean z2);

    Single<Boolean> isPermanentlyOffline();
}
