package com.amazon.video.sdk.player;

import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ContentConfig.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\b\u0086\b\u0018\u00002\u00020\u0001Bc\b\u0007\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0015\u0012\u0014\b\u0002\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u001a\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u001f\u0012\b\b\u0002\u0010$\u001a\u00020\b\u0012\b\b\u0002\u0010(\u001a\u00020\b¢\u0006\u0004\b*\u0010+J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u001a\u0010\n\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000e\u0010\u000b\u001a\u0004\b\u000f\u0010\rR\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0016\u001a\u00020\u00158\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R&\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u001a8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u001c\u0010 \u001a\u0004\u0018\u00010\u001f8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#R\u001a\u0010$\u001a\u00020\b8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'R\u001a\u0010(\u001a\u00020\b8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b(\u0010%\u001a\u0004\b)\u0010'¨\u0006,"}, d2 = {"Lcom/amazon/video/sdk/player/ContentConfigData;", "Lcom/amazon/video/sdk/player/ContentConfig;", "", "toString", "", "hashCode", "", "other", "", "equals", "playbackToken", "Ljava/lang/String;", "getPlaybackToken", "()Ljava/lang/String;", "titleId", "getTitleId", "", "position", "Ljava/lang/Long;", "getPosition", "()Ljava/lang/Long;", "Lcom/amazon/video/sdk/player/VideoType;", "videoType", "Lcom/amazon/video/sdk/player/VideoType;", "getVideoType", "()Lcom/amazon/video/sdk/player/VideoType;", "", "sessionContext", "Ljava/util/Map;", "getSessionContext", "()Ljava/util/Map;", "Lcom/amazon/video/sdk/player/PlaybackEnvelope;", "playbackEnvelope", "Lcom/amazon/video/sdk/player/PlaybackEnvelope;", "getPlaybackEnvelope", "()Lcom/amazon/video/sdk/player/PlaybackEnvelope;", "doNotUseStoredAssets", "Z", "getDoNotUseStoredAssets", "()Z", "showAds", "getShowAds", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Lcom/amazon/video/sdk/player/VideoType;Ljava/util/Map;Lcom/amazon/video/sdk/player/PlaybackEnvelope;ZZ)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class ContentConfigData implements ContentConfig {
    private final boolean doNotUseStoredAssets;
    private final PlaybackEnvelope playbackEnvelope;
    private final String playbackToken;
    private final Long position;
    private final Map<String, String> sessionContext;
    private final boolean showAds;
    private final String titleId;
    private final VideoType videoType;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ContentConfigData)) {
            return false;
        }
        ContentConfigData contentConfigData = (ContentConfigData) other;
        return Intrinsics.areEqual(getPlaybackToken(), contentConfigData.getPlaybackToken()) && Intrinsics.areEqual(getTitleId(), contentConfigData.getTitleId()) && Intrinsics.areEqual(getPosition(), contentConfigData.getPosition()) && getVideoType() == contentConfigData.getVideoType() && Intrinsics.areEqual(getSessionContext(), contentConfigData.getSessionContext()) && Intrinsics.areEqual(getPlaybackEnvelope(), contentConfigData.getPlaybackEnvelope()) && getDoNotUseStoredAssets() == contentConfigData.getDoNotUseStoredAssets() && getShowAds() == contentConfigData.getShowAds();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v12, types: [int] */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3, types: [int] */
    /* JADX WARN: Type inference failed for: r2v4 */
    public int hashCode() {
        int iHashCode = ((((((((((getPlaybackToken().hashCode() * 31) + getTitleId().hashCode()) * 31) + (getPosition() == null ? 0 : getPosition().hashCode())) * 31) + getVideoType().hashCode()) * 31) + getSessionContext().hashCode()) * 31) + (getPlaybackEnvelope() != null ? getPlaybackEnvelope().hashCode() : 0)) * 31;
        boolean doNotUseStoredAssets = getDoNotUseStoredAssets();
        ?? r1 = doNotUseStoredAssets;
        if (doNotUseStoredAssets) {
            r1 = 1;
        }
        int i = (iHashCode + r1) * 31;
        boolean showAds = getShowAds();
        return i + (showAds ? 1 : showAds);
    }

    public String toString() {
        return "ContentConfigData(playbackToken=" + getPlaybackToken() + ", titleId=" + getTitleId() + ", position=" + getPosition() + ", videoType=" + getVideoType() + ", sessionContext=" + getSessionContext() + ", playbackEnvelope=" + getPlaybackEnvelope() + ", doNotUseStoredAssets=" + getDoNotUseStoredAssets() + ", showAds=" + getShowAds() + ')';
    }

    @JvmOverloads
    public ContentConfigData(String playbackToken, String titleId, Long l, VideoType videoType, Map<String, String> sessionContext, PlaybackEnvelope playbackEnvelope, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(playbackToken, "playbackToken");
        Intrinsics.checkNotNullParameter(titleId, "titleId");
        Intrinsics.checkNotNullParameter(videoType, "videoType");
        Intrinsics.checkNotNullParameter(sessionContext, "sessionContext");
        this.playbackToken = playbackToken;
        this.titleId = titleId;
        this.position = l;
        this.videoType = videoType;
        this.sessionContext = sessionContext;
        this.playbackEnvelope = playbackEnvelope;
        this.doNotUseStoredAssets = z;
        this.showAds = z2;
    }

    @Override // com.amazon.video.sdk.player.ContentConfig
    public String getPlaybackToken() {
        return this.playbackToken;
    }

    @Override // com.amazon.video.sdk.player.ContentConfig
    public String getTitleId() {
        return this.titleId;
    }

    @Override // com.amazon.video.sdk.player.ContentConfig
    public Long getPosition() {
        return this.position;
    }

    public /* synthetic */ ContentConfigData(String str, String str2, Long l, VideoType videoType, Map map, PlaybackEnvelope playbackEnvelope, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, l, (i & 8) != 0 ? VideoType.VOD : videoType, (i & 16) != 0 ? MapsKt__MapsKt.emptyMap() : map, (i & 32) != 0 ? null : playbackEnvelope, (i & 64) != 0 ? false : z, (i & 128) != 0 ? true : z2);
    }

    @Override // com.amazon.video.sdk.player.ContentConfig
    public VideoType getVideoType() {
        return this.videoType;
    }

    @Override // com.amazon.video.sdk.player.ContentConfig
    public Map<String, String> getSessionContext() {
        return this.sessionContext;
    }

    @Override // com.amazon.video.sdk.player.ContentConfig
    public PlaybackEnvelope getPlaybackEnvelope() {
        return this.playbackEnvelope;
    }

    @Override // com.amazon.video.sdk.player.ContentConfig
    public boolean getDoNotUseStoredAssets() {
        return this.doNotUseStoredAssets;
    }

    @Override // com.amazon.video.sdk.player.ContentConfig
    public boolean getShowAds() {
        return this.showAds;
    }
}
