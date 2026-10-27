package com.amazon.music.platform.playback.data;

import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.platform.PlatformProviderUtilKt;
import com.amazon.music.platform.playback.queueseeds.QueueSeed;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaybackRequest.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001BS\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0014\u001a\u00020\u0002\u0012\u0006\u0010\u0018\u001a\u00020\u0002\u0012\u0006\u0010\u001a\u001a\u00020\u0007\u0012\u0006\u0010\u001e\u001a\u00020\u001d\u0012\u0014\u0010#\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\"¢\u0006\u0004\b'\u0010(J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u001d\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0010\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0014\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0018\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0015\u001a\u0004\b\u0019\u0010\u0017R\u0017\u0010\u001a\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001a\u0010\u001cR\u0017\u0010\u001e\u001a\u00020\u001d8\u0006¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!R%\u0010#\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\"8\u0006¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&¨\u0006)"}, d2 = {"Lcom/amazon/music/platform/playback/data/PlaybackRequest;", "", "", "toString", "", "hashCode", "other", "", "equals", "", "Lcom/amazon/music/platform/playback/queueseeds/QueueSeed;", "playbackSeed", "Ljava/util/List;", "getPlaybackSeed", "()Ljava/util/List;", "Lcom/amazon/music/platform/playback/data/PlaybackOptions;", "playbackOptions", "Lcom/amazon/music/platform/playback/data/PlaybackOptions;", "getPlaybackOptions", "()Lcom/amazon/music/platform/playback/data/PlaybackOptions;", Splash.PARAMS_SESSION_ID, "Ljava/lang/String;", "getSessionId", "()Ljava/lang/String;", "initiatedFrom", "getInitiatedFrom", "isUserInitiated", "Z", "()Z", "", "startTimeInMS", "D", "getStartTimeInMS", "()D", "", "metricsContext", "Ljava/util/Map;", "getMetricsContext", "()Ljava/util/Map;", "<init>", "(Ljava/util/List;Lcom/amazon/music/platform/playback/data/PlaybackOptions;Ljava/lang/String;Ljava/lang/String;ZDLjava/util/Map;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class PlaybackRequest {
    private final String initiatedFrom;
    private final boolean isUserInitiated;
    private final Map<String, String> metricsContext;
    private final PlaybackOptions playbackOptions;
    private final List<QueueSeed> playbackSeed;
    private final String sessionId;
    private final double startTimeInMS;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PlaybackRequest)) {
            return false;
        }
        PlaybackRequest playbackRequest = (PlaybackRequest) other;
        return Intrinsics.areEqual(this.playbackSeed, playbackRequest.playbackSeed) && Intrinsics.areEqual(this.playbackOptions, playbackRequest.playbackOptions) && Intrinsics.areEqual(this.sessionId, playbackRequest.sessionId) && Intrinsics.areEqual(this.initiatedFrom, playbackRequest.initiatedFrom) && this.isUserInitiated == playbackRequest.isUserInitiated && Intrinsics.areEqual((Object) Double.valueOf(this.startTimeInMS), (Object) Double.valueOf(playbackRequest.startTimeInMS)) && Intrinsics.areEqual(this.metricsContext, playbackRequest.metricsContext);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v7, types: [int] */
    public int hashCode() {
        int iHashCode = ((((((this.playbackSeed.hashCode() * 31) + this.playbackOptions.hashCode()) * 31) + this.sessionId.hashCode()) * 31) + this.initiatedFrom.hashCode()) * 31;
        boolean z = this.isUserInitiated;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        int iHashCode2 = (((iHashCode + r1) * 31) + Double.hashCode(this.startTimeInMS)) * 31;
        Map<String, String> map = this.metricsContext;
        return iHashCode2 + (map == null ? 0 : map.hashCode());
    }

    public String toString() {
        return "PlaybackRequest(playbackSeed=" + this.playbackSeed + ", playbackOptions=" + this.playbackOptions + ", sessionId=" + this.sessionId + ", initiatedFrom=" + this.initiatedFrom + ", isUserInitiated=" + this.isUserInitiated + ", startTimeInMS=" + this.startTimeInMS + ", metricsContext=" + this.metricsContext + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public PlaybackRequest(List<? extends QueueSeed> playbackSeed, PlaybackOptions playbackOptions, String sessionId, String initiatedFrom, boolean z, double d, Map<String, String> map) {
        Intrinsics.checkNotNullParameter(playbackSeed, "playbackSeed");
        Intrinsics.checkNotNullParameter(playbackOptions, "playbackOptions");
        Intrinsics.checkNotNullParameter(sessionId, "sessionId");
        Intrinsics.checkNotNullParameter(initiatedFrom, "initiatedFrom");
        this.playbackSeed = playbackSeed;
        this.playbackOptions = playbackOptions;
        this.sessionId = sessionId;
        this.initiatedFrom = initiatedFrom;
        this.isUserInitiated = z;
        this.startTimeInMS = d;
        this.metricsContext = map;
        PlatformProviderUtilKt.freeze(this);
    }

    public final List<QueueSeed> getPlaybackSeed() {
        return this.playbackSeed;
    }

    public final PlaybackOptions getPlaybackOptions() {
        return this.playbackOptions;
    }

    public final String getInitiatedFrom() {
        return this.initiatedFrom;
    }

    /* JADX INFO: renamed from: isUserInitiated, reason: from getter */
    public final boolean getIsUserInitiated() {
        return this.isUserInitiated;
    }

    public final double getStartTimeInMS() {
        return this.startTimeInMS;
    }
}
