package com.amazon.video.sdk.player;

import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: compiled from: ContentConfig.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000b8&X§\u0004¢\u0006\f\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u0004\u0018\u00010\u0011X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\u0015X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0012\u0010\u0018\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0005R\u0012\u0010\u001a\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u000fR\u0012\u0010\u001c\u001a\u00020\u001dX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001f¨\u0006 "}, d2 = {"Lcom/amazon/video/sdk/player/ContentConfig;", "", "doNotUseStoredAssets", "", "getDoNotUseStoredAssets", "()Z", "playbackEnvelope", "Lcom/amazon/video/sdk/player/PlaybackEnvelope;", "getPlaybackEnvelope", "()Lcom/amazon/video/sdk/player/PlaybackEnvelope;", "playbackToken", "", "getPlaybackToken$annotations", "()V", "getPlaybackToken", "()Ljava/lang/String;", "position", "", "getPosition", "()Ljava/lang/Long;", "sessionContext", "", "getSessionContext", "()Ljava/util/Map;", "showAds", "getShowAds", "titleId", "getTitleId", "videoType", "Lcom/amazon/video/sdk/player/VideoType;", "getVideoType", "()Lcom/amazon/video/sdk/player/VideoType;", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface ContentConfig {
    boolean getDoNotUseStoredAssets();

    PlaybackEnvelope getPlaybackEnvelope();

    String getPlaybackToken();

    Long getPosition();

    Map<String, String> getSessionContext();

    boolean getShowAds();

    String getTitleId();

    VideoType getVideoType();
}
