package p;

import com.spotify.automotive.auth.model.LoginRequest;
import com.spotify.automotive.auth.model.LoginResponse;
import com.spotify.cosmos.cosmonaut.CosmosService;
import com.spotify.cosmos.cosmonaut.TypedResponse;
import com.spotify.cosmos.cosmonaut.annotations.Body;
import com.spotify.cosmos.cosmonaut.annotations.DELETE;
import com.spotify.cosmos.cosmonaut.annotations.POST;
import com.spotify.cosmos.cosmonaut.annotations.Query;
import com.spotify.cosmos.cosmonaut.annotations.SUB;
import com.spotify.cosmos.cosmos.Response;
import com.spotify.cosmos.queuingrouter.QueuingRemoteNativeRouter;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

/* JADX INFO: loaded from: classes.dex */
@CosmosService
public interface vb2 {
    @POST(QueuingRemoteNativeRouter.SP_SESSION_V1_URI)
    Single<TypedResponse<LoginResponse>> a(@Body LoginRequest loginRequest);

    @SUB(QueuingRemoteNativeRouter.SP_SESSION_V1_URI)
    Observable<Response> currentSession();

    @DELETE(QueuingRemoteNativeRouter.SP_SESSION_V1_URI)
    Completable logout(@Query("forgetCredentials") boolean z);

    @POST("sp://session/v1/bootstrap-completed")
    Single<TypedResponse<LoginResponse>> notifyBootstrapCompleted(@Body byte[] bArr);
}
