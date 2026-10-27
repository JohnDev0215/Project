package com.amazon.music.media.playback.config;

import androidx.core.app.NotificationCompat;
import com.amazon.video.sdk.player.PlaybackState;
import kotlin.Metadata;

/* JADX INFO: compiled from: MediaStreamingStatusProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001JB\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\r\u001a\u00020\u000eH&¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/media/playback/config/MediaStreamingStatusProvider;", "", "updateCurrentStreamingStatus", "", "trackId", "", "trackIdType", NotificationCompat.CATEGORY_STATUS, "Lcom/amazon/video/sdk/player/PlaybackState;", "contentDuration", "", "currentPlaytime", "oldDeviceName", "forceUpdate", "", "DMMPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface MediaStreamingStatusProvider {
    void updateCurrentStreamingStatus(String trackId, String trackIdType, PlaybackState status, long contentDuration, long currentPlaytime, String oldDeviceName, boolean forceUpdate);
}
