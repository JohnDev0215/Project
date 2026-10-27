package com.amazon.mp3.ondemandplaylists.service;

import android.util.Log;
import androidx.annotation.WorkerThread;
import com.amazon.mp3.ondemandplaylists.api.model.GetOnDemandPlaylistsResponse;
import com.amazon.mp3.ondemandplaylists.metrics.GetOnDemandPlaylistsMetricsUtil;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.data.OAuthInfo;
import com.amazon.music.platform.providers.AuthenticationProvider;
import com.amazonaws.mobileconnectors.apigateway.ApiClientFactory;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: UsherServiceClient.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015BU\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\fJ\n\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0007R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/ondemandplaylists/service/UsherServiceClient;", "", "customerId", "", Splash.PARAMS_DEVICE_ID, Splash.PARAMS_DEVICE_TYPE, Splash.PARAMS_MUSIC_TERRITORY, "eligibilityTier", "region", "authProvider", "Lcom/amazon/music/platform/providers/AuthenticationProvider;", "userAgent", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/amazon/music/platform/providers/AuthenticationProvider;Ljava/lang/String;)V", "apiClient", "Lcom/amazon/mp3/ondemandplaylists/service/UsherServiceAPIGatewayClient;", "apiClientFactory", "Lcom/amazonaws/mobileconnectors/apigateway/ApiClientFactory;", "usherEndpointFactory", "Lcom/amazon/mp3/ondemandplaylists/service/UsherEndpointFactory;", "getOnDemandPlaylists", "Lcom/amazon/mp3/ondemandplaylists/api/model/GetOnDemandPlaylistsResponse;", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class UsherServiceClient {
    private static final String TAG = Reflection.getOrCreateKotlinClass(UsherServiceClient.class).getSimpleName();
    private final UsherServiceAPIGatewayClient apiClient;
    private final ApiClientFactory apiClientFactory;
    private final AuthenticationProvider authProvider;
    private final String customerId;
    private final String deviceId;
    private final String deviceType;
    private final String eligibilityTier;
    private final String musicTerritory;
    private final String region;
    private final String userAgent;
    private final UsherEndpointFactory usherEndpointFactory;

    public UsherServiceClient(String str, String str2, String str3, String str4, String str5, String str6, AuthenticationProvider authProvider, String str7) {
        Intrinsics.checkNotNullParameter(authProvider, "authProvider");
        ApiClientFactory apiClientFactory = new ApiClientFactory();
        this.apiClientFactory = apiClientFactory;
        UsherEndpointFactory usherEndpointFactory = new UsherEndpointFactory();
        this.usherEndpointFactory = usherEndpointFactory;
        Object objBuild = apiClientFactory.endpoint(usherEndpointFactory.getUsherServiceEndpoint(str6)).build(UsherServiceAPIGatewayClient.class);
        Intrinsics.checkNotNullExpressionValue(objBuild, "apiClientFactory.endpoin…atewayClient::class.java)");
        this.apiClient = (UsherServiceAPIGatewayClient) objBuild;
        this.customerId = str;
        this.deviceId = str2;
        this.deviceType = str3;
        this.musicTerritory = str4;
        this.eligibilityTier = str5;
        this.region = str6;
        this.authProvider = authProvider;
        this.userAgent = str7;
    }

    @WorkerThread
    public final GetOnDemandPlaylistsResponse getOnDemandPlaylists() {
        Outcome<OAuthInfo> oAuthInfoBlocking = this.authProvider.getOAuthInfoBlocking();
        if (oAuthInfoBlocking instanceof Outcome.Success) {
            return this.apiClient.getOnDemandPlaylists(this.customerId, this.deviceType, this.deviceId, ((OAuthInfo) ((Outcome.Success) oAuthInfoBlocking).getValue()).getAccessToken(), this.musicTerritory, this.eligibilityTier, this.userAgent);
        }
        Log.e(TAG, "Failed to authenticate customerId: " + ((Object) this.customerId) + " and deviceId: " + ((Object) this.deviceId) + " in musicTerritory: " + ((Object) this.musicTerritory));
        GetOnDemandPlaylistsMetricsUtil.trackFailureEvent$default(GetOnDemandPlaylistsMetricsUtil.INSTANCE, GetOnDemandPlaylistsMetricsUtil.EventType.EVENT_AUTH, null, 2, null);
        return null;
    }
}
