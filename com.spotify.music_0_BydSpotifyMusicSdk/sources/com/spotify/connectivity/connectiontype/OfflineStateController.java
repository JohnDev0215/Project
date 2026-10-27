package com.spotify.connectivity.connectiontype;

import io.reactivex.rxjava3.core.Observable;

/* JADX INFO: loaded from: classes.dex */
public interface OfflineStateController {
    Observable<OfflineState> observable();

    void setOfflineMode(boolean z);
}
