package com.amazon.music.media.playback.video;

import com.amazon.dmvideodeliveryservice.GetPlaybackTokenCall;
import com.amazon.dmvideodeliveryservice.GetPlaybackTokenRequest;
import com.amazon.dmvideodeliveryservice.GetPlaybackTokenRequestSerializer;
import com.amazon.dmvideodeliveryservice.GetPlaybackTokenResponse;
import com.amazon.dmvideodeliveryservice.GetPlaybackTokenResponseDeserializer;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.MalformedURLException;
import java.net.URL;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HttpVideoDeliveryService.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B-\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\u001a\u0010\r\u001a\u00020\u000e2\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u0013\u001a\u00020\u00142\u000e\u0010\u0015\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0016H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/amazon/music/media/playback/video/HttpVideoDeliveryService;", "Lcom/amazon/music/media/playback/video/VideoDeliveryService;", "requestInterceptor", "Lcom/amazon/hermes/RequestInterceptor;", "marketplaceId", "", "environment", "Lcom/amazon/music/media/playback/video/ServiceEnvironment;", "retryPolicy", "Lcom/android/volley/RetryPolicy;", "(Lcom/amazon/hermes/RequestInterceptor;Ljava/lang/String;Lcom/amazon/music/media/playback/video/ServiceEnvironment;Lcom/android/volley/RetryPolicy;)V", "playbackObjectMapper", "Lcom/fasterxml/jackson/databind/ObjectMapper;", "createUrl", "Ljava/net/URL;", "getPlaybackToken", "Lcom/amazon/dmvideodeliveryservice/GetPlaybackTokenResponse;", "request", "Lcom/amazon/dmvideodeliveryservice/GetPlaybackTokenRequest;", "setRetryPolicy", "", "coralCall", "Lcom/amazon/hermes/CoralCall;", "Companion", "DMMPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class HttpVideoDeliveryService implements VideoDeliveryService {
    private final ServiceEnvironment environment;
    private final String marketplaceId;
    private final ObjectMapper playbackObjectMapper;
    private final RequestInterceptor requestInterceptor;
    private RetryPolicy retryPolicy;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public HttpVideoDeliveryService(RequestInterceptor requestInterceptor, String str, ServiceEnvironment environment) {
        this(requestInterceptor, str, environment, null, 8, null);
        Intrinsics.checkNotNullParameter(requestInterceptor, "requestInterceptor");
        Intrinsics.checkNotNullParameter(environment, "environment");
    }

    @JvmOverloads
    public HttpVideoDeliveryService(RequestInterceptor requestInterceptor, String str, ServiceEnvironment environment, RetryPolicy retryPolicy) {
        Intrinsics.checkNotNullParameter(requestInterceptor, "requestInterceptor");
        Intrinsics.checkNotNullParameter(environment, "environment");
        this.requestInterceptor = requestInterceptor;
        this.marketplaceId = str;
        this.environment = environment;
        this.retryPolicy = retryPolicy;
        ObjectMapper objectMapper = new ObjectMapper();
        this.playbackObjectMapper = objectMapper;
        GetPlaybackTokenRequestSerializer.register(objectMapper);
        GetPlaybackTokenResponseDeserializer.register(objectMapper);
    }

    public /* synthetic */ HttpVideoDeliveryService(RequestInterceptor requestInterceptor, String str, ServiceEnvironment serviceEnvironment, RetryPolicy retryPolicy, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(requestInterceptor, str, serviceEnvironment, (i & 8) != 0 ? new DefaultRetryPolicy(20000, 1, 1.0f) : retryPolicy);
    }

    @Override // com.amazon.music.media.playback.video.VideoDeliveryService
    public GetPlaybackTokenResponse getPlaybackToken(GetPlaybackTokenRequest request) throws VolleyError {
        Intrinsics.checkNotNullParameter(request, "request");
        GetPlaybackTokenCall getPlaybackTokenCall = new GetPlaybackTokenCall(createUrl(this.marketplaceId, this.environment), request, this.requestInterceptor);
        setRetryPolicy(getPlaybackTokenCall);
        GetPlaybackTokenResponse getPlaybackTokenResponseExecute = getPlaybackTokenCall.execute(this.playbackObjectMapper);
        Intrinsics.checkNotNullExpressionValue(getPlaybackTokenResponseExecute, "call.execute(playbackObjectMapper)");
        return getPlaybackTokenResponseExecute;
    }

    private final void setRetryPolicy(CoralCall<?, ?> coralCall) {
        RetryPolicy retryPolicy = this.retryPolicy;
        if (retryPolicy != null) {
            coralCall.setRetryPolicy(retryPolicy);
        }
    }

    private final URL createUrl(String marketplaceId, ServiceEnvironment environment) {
        try {
            return new URL(environment.getUrl(marketplaceId));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
