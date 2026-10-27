package p;

import com.spotify.automotive.httpwebgategson.TokenResponse;
import com.spotify.cosmos.cosmonaut.annotations.GET;
import com.spotify.cosmos.cosmonaut.annotations.Query;
import io.reactivex.rxjava3.core.Single;

/* JADX INFO: loaded from: classes.dex */
public interface gw2 {
    @GET("sp://auth/v2/token")
    Single<TokenResponse> a(@Query("renew") int i);
}
