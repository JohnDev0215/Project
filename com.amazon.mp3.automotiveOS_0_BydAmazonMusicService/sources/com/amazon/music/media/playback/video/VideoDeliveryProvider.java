package com.amazon.music.media.playback.video;

import androidx.core.app.NotificationCompat;
import com.amazon.dmvideodeliveryservice.GetPlaybackTokenRequest;
import com.amazon.dmvideodeliveryservice.GetPlaybackTokenResponse;
import com.android.volley.VolleyError;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.Validate;

/* JADX INFO: compiled from: VideoDeliveryProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/music/media/playback/video/VideoDeliveryProvider;", "", NotificationCompat.CATEGORY_SERVICE, "Lcom/amazon/music/media/playback/video/VideoDeliveryService;", "(Lcom/amazon/music/media/playback/video/VideoDeliveryService;)V", "getPlaybackToken", "Lcom/amazon/dmvideodeliveryservice/GetPlaybackTokenResponse;", "request", "Lcom/amazon/dmvideodeliveryservice/GetPlaybackTokenRequest;", "DMMPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class VideoDeliveryProvider {
    private final VideoDeliveryService service;

    public VideoDeliveryProvider(VideoDeliveryService service) {
        Intrinsics.checkNotNullParameter(service, "service");
        this.service = service;
        Validate.notNull(service, "service can't be null", new Object[0]);
    }

    public final GetPlaybackTokenResponse getPlaybackToken(GetPlaybackTokenRequest request) throws VolleyError {
        Intrinsics.checkNotNullParameter(request, "request");
        try {
            return this.service.getPlaybackToken(request);
        } catch (VolleyError e) {
            throw e;
        }
    }
}
