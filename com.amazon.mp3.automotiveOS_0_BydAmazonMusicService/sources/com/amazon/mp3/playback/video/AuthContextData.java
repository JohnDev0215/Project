package com.amazon.mp3.playback.video;

import android.content.Context;
import com.amazon.mp3.platform.providers.AuthenticationProviderImpl;
import com.amazon.mp3.util.Log;
import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.data.OAuthInfo;
import com.amazon.video.sdk.AuthContext;
import com.amazonaws.http.HttpHeader;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AuthContextData.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J(\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\bH\u0016J\b\u0010\r\u001a\u00020\tH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\b8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u000f"}, d2 = {"Lcom/amazon/mp3/playback/video/AuthContextData;", "Lcom/amazon/video/sdk/AuthContext;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "authenticationProvider", "Lcom/amazon/mp3/platform/providers/AuthenticationProviderImpl;", "headers", "", "", "getHeaders", "()Ljava/util/Map;", "sessionContext", "getMapAccessToken", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AuthContextData implements AuthContext {
    private static final String TAG = AuthContextData.class.getSimpleName();
    private final AuthenticationProviderImpl authenticationProvider;

    public AuthContextData(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.authenticationProvider = new AuthenticationProviderImpl(context, null, 2, null);
    }

    public final Map<String, String> getHeaders() {
        return MapsKt__MapsKt.hashMapOf(TuplesKt.to(HttpHeader.AUTHORIZATION, getMapAccessToken()));
    }

    private final String getMapAccessToken() {
        String accessToken;
        Outcome<OAuthInfo> oAuthInfoBlocking = this.authenticationProvider.getOAuthInfoBlocking();
        if (oAuthInfoBlocking instanceof Outcome.Success) {
            accessToken = ((OAuthInfo) ((Outcome.Success) oAuthInfoBlocking).getValue()).getAccessToken();
        } else {
            if (oAuthInfoBlocking instanceof Outcome.Failure) {
                Log.error(TAG, "Failed to fetch access token from MAP");
            }
            accessToken = "";
        }
        return Intrinsics.stringPlus("Bearer ", accessToken);
    }

    @Override // com.amazon.video.sdk.AuthContext
    public Map<String, String> getHeaders(Map<String, String> sessionContext) {
        Intrinsics.checkNotNullParameter(sessionContext, "sessionContext");
        return getHeaders();
    }
}
