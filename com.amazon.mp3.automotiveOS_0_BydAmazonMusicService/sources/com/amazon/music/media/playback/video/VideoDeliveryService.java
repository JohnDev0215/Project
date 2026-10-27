package com.amazon.music.media.playback.video;

import com.amazon.dmvideodeliveryservice.GetPlaybackTokenRequest;
import com.amazon.dmvideodeliveryservice.GetPlaybackTokenResponse;
import com.android.volley.VolleyError;
import kotlin.Metadata;

/* JADX INFO: compiled from: VideoDeliveryService.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/amazon/music/media/playback/video/VideoDeliveryService;", "", "getPlaybackToken", "Lcom/amazon/dmvideodeliveryservice/GetPlaybackTokenResponse;", "request", "Lcom/amazon/dmvideodeliveryservice/GetPlaybackTokenRequest;", "DMMPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface VideoDeliveryService {
    GetPlaybackTokenResponse getPlaybackToken(GetPlaybackTokenRequest request) throws VolleyError;
}
