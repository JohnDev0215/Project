package com.amazon.mp3.ondemandplaylists.service;

import com.amazon.mp3.ondemandplaylists.api.model.GetOnDemandPlaylistsResponse;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazonaws.mobileconnectors.apigateway.annotation.Operation;
import com.amazonaws.mobileconnectors.apigateway.annotation.Parameter;
import com.amazonaws.mobileconnectors.apigateway.annotation.Service;
import com.google.firebase.perf.FirebasePerformance;
import kotlin.Metadata;

/* JADX INFO: compiled from: UsherServiceAPIGatewayClient.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J^\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0001\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u00022\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00022\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u00022\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u00022\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u00022\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0002H'¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/ondemandplaylists/service/UsherServiceAPIGatewayClient;", "", "", "customerId", Splash.PARAMS_DEVICE_TYPE, Splash.PARAMS_DEVICE_ID, "token", Splash.PARAMS_MUSIC_TERRITORY, "eligibilityTier", "userAgent", "Lcom/amazon/mp3/ondemandplaylists/api/model/GetOnDemandPlaylistsResponse;", "getOnDemandPlaylists", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
@Service(endpoint = "")
public interface UsherServiceAPIGatewayClient {
    @Operation(method = FirebasePerformance.HttpMethod.POST, path = "/getOnDemandPlaylists")
    GetOnDemandPlaylistsResponse getOnDemandPlaylists(@Parameter(location = "header", name = "x-amz-customer-id") String customerId, @Parameter(location = "header", name = "x-amz-device-type") String deviceType, @Parameter(location = "header", name = "x-amz-device-id") String deviceId, @Parameter(location = "header", name = "x-amz-access-token") String token, @Parameter(location = "header", name = "x-amz-music-territory") String musicTerritory, @Parameter(location = "header", name = "x-amz-eligibility-tier") String eligibilityTier, @Parameter(location = "header", name = "x-amz-user-agent") String userAgent);
}
