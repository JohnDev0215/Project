package com.spotify.playerlimited.playerimpl.services;

import com.spotify.cosmos.cosmonaut.CosmosService;
import com.spotify.cosmos.cosmonaut.TypedResponse;
import com.spotify.cosmos.cosmonaut.annotations.Body;
import com.spotify.cosmos.cosmonaut.annotations.DELETE;
import com.spotify.cosmos.cosmonaut.annotations.HeaderMap;
import com.spotify.cosmos.cosmonaut.annotations.POST;
import com.spotify.cosmos.cosmonaut.annotations.Query;
import com.spotify.cosmos.cosmonaut.annotations.SUB;
import com.spotify.cosmos.cosmos.Response;
import com.spotify.cosmos.queuingrouter.QueuingRemoteNativeRouter;
import com.spotify.playerlimited.player.models.LoginRequest;
import com.spotify.playerlimited.player.models.LoginResponse;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@CosmosService
public interface SessionEndpoint {
    @SUB(QueuingRemoteNativeRouter.SP_SESSION_V1_URI)
    Observable<Response> currentSession();

    @SUB(QueuingRemoteNativeRouter.SP_SESSION_V1_URI)
    Observable<Response> currentSession(@HeaderMap Map<?, ?> map);

    @POST(QueuingRemoteNativeRouter.SP_SESSION_V1_URI)
    Single<TypedResponse<LoginResponse>> login(@Body LoginRequest loginRequest);

    @DELETE(QueuingRemoteNativeRouter.SP_SESSION_V1_URI)
    Completable logout();

    @DELETE(QueuingRemoteNativeRouter.SP_SESSION_V1_URI)
    Completable logout(@Query("forgetCredentials") boolean z);

    @POST("sp://session/v1/bootstrap-completed")
    Single<TypedResponse<LoginResponse>> notifyBootstrapCompleted(@Body byte[] bArr);

    @POST("sp://session/v1/update-product-state")
    Single<Response> updateProductState(@Body Map<String, String> map);
}
