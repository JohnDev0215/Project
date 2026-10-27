package com.spotify.connectivity.connectiontype;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

/* JADX INFO: loaded from: classes.dex */
public interface ConnectivityManager {
    Completable forceOffline(boolean z);

    Completable setAllowSyncOverCellular(boolean z);

    Observable<OfflineState> state();
}
