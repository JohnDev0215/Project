package com.spotify.connectivity.platformconnectiontype;

import com.spotify.connectivity.connectiontype.OfflineState;
import com.spotify.cosmos.cosmonaut.CosmosService;
import com.spotify.cosmos.cosmonaut.annotations.Body;
import com.spotify.cosmos.cosmonaut.annotations.PUT;
import com.spotify.cosmos.cosmonaut.annotations.SUB;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

/* JADX INFO: loaded from: classes.dex */
@CosmosService
public interface CoreConnectionState {
    public static final String OFFLINE_CONNECTION_URI = "sp://connectivity-manager/v1/connection";

    @SUB(OFFLINE_CONNECTION_URI)
    Observable<OfflineStateCosmos> connection();

    @PUT(OFFLINE_CONNECTION_URI)
    Single<OfflineStateCosmos> set(@Body OfflineState offlineState);

    @PUT("sp://connectivity-manager/v1/use-platform-connection-state-for-is-online")
    Completable setUsePlatformConnectionStateForIsOnline();
}
