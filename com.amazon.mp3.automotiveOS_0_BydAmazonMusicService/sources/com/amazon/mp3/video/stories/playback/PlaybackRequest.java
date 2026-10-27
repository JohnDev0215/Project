package com.amazon.mp3.video.stories.playback;

import ExternalActionInterface.v1_0.VideoStoryContent;
import Touch.PlaybackTemplateInterface.v1_0.PlaybackTemplate;
import com.amazon.mp3.video.stories.VideoPlaybackState;
import com.amazon.mp3.video.stories.VideoResourceType;
import com.amazon.mp3.video.stories.VideoType;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.mts.event.types.ResumeReason;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaybackRequest.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\b\u0018\u00002\u00020\u0001B¹\u0001\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\u0006\u0010\u0017\u001a\u00020\u0004\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u001f\u001a\u00020\u0007\u0012\u0006\u0010\"\u001a\u00020\u0007\u0012\u0006\u0010#\u001a\u00020\u0007\u0012\u0006\u0010%\u001a\u00020$\u0012\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020)\u0012\b\u0010.\u001a\u0004\u0018\u00010\u0002\u0012\b\u00100\u001a\u0004\u0018\u00010\u0002\u0012\b\u00102\u001a\u0004\u0018\u00010\u0002\u0012\b\u00104\u001a\u0004\u0018\u00010\u0002\u0012\b\u00106\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u00109\u001a\u000208\u0012\b\u0010>\u001a\u0004\u0018\u00010=\u0012\b\u0010E\u001a\u0004\u0018\u00010D\u0012\b\u0010L\u001a\u0004\u0018\u00010K¢\u0006\u0004\bR\u0010SJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R$\u0010\n\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR$\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0017\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0019\u0010\u001b\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\u001f\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b\u001f\u0010!R\u0017\u0010\"\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\"\u0010 \u001a\u0004\b\"\u0010!R\u0017\u0010#\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b#\u0010 \u001a\u0004\b#\u0010!R\u0017\u0010%\u001a\u00020$8\u0006¢\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(R#\u0010*\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020)8\u0006¢\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-R\u0019\u0010.\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b.\u0010\u001c\u001a\u0004\b/\u0010\u001eR\u0019\u00100\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b0\u0010\u001c\u001a\u0004\b1\u0010\u001eR\u0019\u00102\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b2\u0010\u001c\u001a\u0004\b3\u0010\u001eR\u0019\u00104\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b4\u0010\u001c\u001a\u0004\b5\u0010\u001eR\u0019\u00106\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b6\u0010\u001c\u001a\u0004\b7\u0010\u001eR\u0017\u00109\u001a\u0002088\u0006¢\u0006\f\n\u0004\b9\u0010:\u001a\u0004\b;\u0010<R$\u0010>\u001a\u0004\u0018\u00010=8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b>\u0010?\u001a\u0004\b@\u0010A\"\u0004\bB\u0010CR$\u0010E\u001a\u0004\u0018\u00010D8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bE\u0010F\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JR$\u0010L\u001a\u0004\u0018\u00010K8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bL\u0010M\u001a\u0004\bN\u0010O\"\u0004\bP\u0010Q¨\u0006T"}, d2 = {"Lcom/amazon/mp3/video/stories/playback/PlaybackRequest;", "", "", "toString", "", "hashCode", "other", "", "equals", "LTouch/PlaybackTemplateInterface/v1_0/PlaybackTemplate;", "videoItem", "LTouch/PlaybackTemplateInterface/v1_0/PlaybackTemplate;", "getVideoItem", "()LTouch/PlaybackTemplateInterface/v1_0/PlaybackTemplate;", "setVideoItem", "(LTouch/PlaybackTemplateInterface/v1_0/PlaybackTemplate;)V", "LExternalActionInterface/v1_0/VideoStoryContent;", "content", "LExternalActionInterface/v1_0/VideoStoryContent;", "getContent", "()LExternalActionInterface/v1_0/VideoStoryContent;", "setContent", "(LExternalActionInterface/v1_0/VideoStoryContent;)V", "startingIndex", "I", "getStartingIndex", "()I", "blockRef", "Ljava/lang/String;", "getBlockRef", "()Ljava/lang/String;", "isDirectedPlay", "Z", "()Z", "isShufflePlay", "isAlexaInitiatedPlay", "Lcom/amazon/mp3/video/stories/VideoResourceType;", "resourceType", "Lcom/amazon/mp3/video/stories/VideoResourceType;", "getResourceType", "()Lcom/amazon/mp3/video/stories/VideoResourceType;", "", "metricsContext", "Ljava/util/Map;", "getMetricsContext", "()Ljava/util/Map;", "asin", "getAsin", "sourceCollectionID", "getSourceCollectionID", ContextMappingConstants.ENTITY_ID, "getEntityId", ContextMappingConstants.ENTITY_ID_TYPE, "getEntityIdType", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "getEntityType", "Lcom/amazon/mp3/video/stories/VideoType;", "videoType", "Lcom/amazon/mp3/video/stories/VideoType;", "getVideoType", "()Lcom/amazon/mp3/video/stories/VideoType;", "Lcom/amazon/music/metrics/mts/event/types/TerminationReason;", "terminationReason", "Lcom/amazon/music/metrics/mts/event/types/TerminationReason;", "getTerminationReason", "()Lcom/amazon/music/metrics/mts/event/types/TerminationReason;", "setTerminationReason", "(Lcom/amazon/music/metrics/mts/event/types/TerminationReason;)V", "Lcom/amazon/music/metrics/mts/event/types/ResumeReason;", "resumeReason", "Lcom/amazon/music/metrics/mts/event/types/ResumeReason;", "getResumeReason", "()Lcom/amazon/music/metrics/mts/event/types/ResumeReason;", "setResumeReason", "(Lcom/amazon/music/metrics/mts/event/types/ResumeReason;)V", "Lcom/amazon/mp3/video/stories/VideoPlaybackState;", "playbackStatus", "Lcom/amazon/mp3/video/stories/VideoPlaybackState;", "getPlaybackStatus", "()Lcom/amazon/mp3/video/stories/VideoPlaybackState;", "setPlaybackStatus", "(Lcom/amazon/mp3/video/stories/VideoPlaybackState;)V", "<init>", "(LTouch/PlaybackTemplateInterface/v1_0/PlaybackTemplate;LExternalActionInterface/v1_0/VideoStoryContent;ILjava/lang/String;ZZZLcom/amazon/mp3/video/stories/VideoResourceType;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/amazon/mp3/video/stories/VideoType;Lcom/amazon/music/metrics/mts/event/types/TerminationReason;Lcom/amazon/music/metrics/mts/event/types/ResumeReason;Lcom/amazon/mp3/video/stories/VideoPlaybackState;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class PlaybackRequest {
    private final String asin;
    private final String blockRef;
    private VideoStoryContent content;
    private final String entityId;
    private final String entityIdType;
    private final String entityType;
    private final boolean isAlexaInitiatedPlay;
    private final boolean isDirectedPlay;
    private final boolean isShufflePlay;
    private final Map<String, String> metricsContext;
    private VideoPlaybackState playbackStatus;
    private final VideoResourceType resourceType;
    private ResumeReason resumeReason;
    private final String sourceCollectionID;
    private final int startingIndex;
    private TerminationReason terminationReason;
    private PlaybackTemplate videoItem;
    private final VideoType videoType;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PlaybackRequest)) {
            return false;
        }
        PlaybackRequest playbackRequest = (PlaybackRequest) other;
        return Intrinsics.areEqual(this.videoItem, playbackRequest.videoItem) && Intrinsics.areEqual(this.content, playbackRequest.content) && this.startingIndex == playbackRequest.startingIndex && Intrinsics.areEqual(this.blockRef, playbackRequest.blockRef) && this.isDirectedPlay == playbackRequest.isDirectedPlay && this.isShufflePlay == playbackRequest.isShufflePlay && this.isAlexaInitiatedPlay == playbackRequest.isAlexaInitiatedPlay && this.resourceType == playbackRequest.resourceType && Intrinsics.areEqual(this.metricsContext, playbackRequest.metricsContext) && Intrinsics.areEqual(this.asin, playbackRequest.asin) && Intrinsics.areEqual(this.sourceCollectionID, playbackRequest.sourceCollectionID) && Intrinsics.areEqual(this.entityId, playbackRequest.entityId) && Intrinsics.areEqual(this.entityIdType, playbackRequest.entityIdType) && Intrinsics.areEqual(this.entityType, playbackRequest.entityType) && this.videoType == playbackRequest.videoType && this.terminationReason == playbackRequest.terminationReason && this.resumeReason == playbackRequest.resumeReason && this.playbackStatus == playbackRequest.playbackStatus;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v11, types: [int] */
    /* JADX WARN: Type inference failed for: r2v48 */
    /* JADX WARN: Type inference failed for: r2v49 */
    /* JADX WARN: Type inference failed for: r2v52 */
    /* JADX WARN: Type inference failed for: r2v53 */
    /* JADX WARN: Type inference failed for: r2v9, types: [int] */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [int] */
    /* JADX WARN: Type inference failed for: r3v2 */
    public int hashCode() {
        PlaybackTemplate playbackTemplate = this.videoItem;
        int iHashCode = (playbackTemplate == null ? 0 : playbackTemplate.hashCode()) * 31;
        VideoStoryContent videoStoryContent = this.content;
        int iHashCode2 = (((iHashCode + (videoStoryContent == null ? 0 : videoStoryContent.hashCode())) * 31) + Integer.hashCode(this.startingIndex)) * 31;
        String str = this.blockRef;
        int iHashCode3 = (iHashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        boolean z = this.isDirectedPlay;
        ?? r2 = z;
        if (z) {
            r2 = 1;
        }
        int i = (iHashCode3 + r2) * 31;
        boolean z2 = this.isShufflePlay;
        ?? r22 = z2;
        if (z2) {
            r22 = 1;
        }
        int i2 = (i + r22) * 31;
        boolean z3 = this.isAlexaInitiatedPlay;
        int iHashCode4 = (((((i2 + (z3 ? 1 : z3)) * 31) + this.resourceType.hashCode()) * 31) + this.metricsContext.hashCode()) * 31;
        String str2 = this.asin;
        int iHashCode5 = (iHashCode4 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.sourceCollectionID;
        int iHashCode6 = (iHashCode5 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.entityId;
        int iHashCode7 = (iHashCode6 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.entityIdType;
        int iHashCode8 = (iHashCode7 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.entityType;
        int iHashCode9 = (((iHashCode8 + (str6 == null ? 0 : str6.hashCode())) * 31) + this.videoType.hashCode()) * 31;
        TerminationReason terminationReason = this.terminationReason;
        int iHashCode10 = (iHashCode9 + (terminationReason == null ? 0 : terminationReason.hashCode())) * 31;
        ResumeReason resumeReason = this.resumeReason;
        int iHashCode11 = (iHashCode10 + (resumeReason == null ? 0 : resumeReason.hashCode())) * 31;
        VideoPlaybackState videoPlaybackState = this.playbackStatus;
        return iHashCode11 + (videoPlaybackState != null ? videoPlaybackState.hashCode() : 0);
    }

    public String toString() {
        return "PlaybackRequest(videoItem=" + this.videoItem + ", content=" + this.content + ", startingIndex=" + this.startingIndex + ", blockRef=" + ((Object) this.blockRef) + ", isDirectedPlay=" + this.isDirectedPlay + ", isShufflePlay=" + this.isShufflePlay + ", isAlexaInitiatedPlay=" + this.isAlexaInitiatedPlay + ", resourceType=" + this.resourceType + ", metricsContext=" + this.metricsContext + ", asin=" + ((Object) this.asin) + ", sourceCollectionID=" + ((Object) this.sourceCollectionID) + ", entityId=" + ((Object) this.entityId) + ", entityIdType=" + ((Object) this.entityIdType) + ", entityType=" + ((Object) this.entityType) + ", videoType=" + this.videoType + ", terminationReason=" + this.terminationReason + ", resumeReason=" + this.resumeReason + ", playbackStatus=" + this.playbackStatus + ')';
    }

    public PlaybackRequest(PlaybackTemplate playbackTemplate, VideoStoryContent videoStoryContent, int i, String str, boolean z, boolean z2, boolean z3, VideoResourceType resourceType, Map<String, String> metricsContext, String str2, String str3, String str4, String str5, String str6, VideoType videoType, TerminationReason terminationReason, ResumeReason resumeReason, VideoPlaybackState videoPlaybackState) {
        Intrinsics.checkNotNullParameter(resourceType, "resourceType");
        Intrinsics.checkNotNullParameter(metricsContext, "metricsContext");
        Intrinsics.checkNotNullParameter(videoType, "videoType");
        this.videoItem = playbackTemplate;
        this.content = videoStoryContent;
        this.startingIndex = i;
        this.blockRef = str;
        this.isDirectedPlay = z;
        this.isShufflePlay = z2;
        this.isAlexaInitiatedPlay = z3;
        this.resourceType = resourceType;
        this.metricsContext = metricsContext;
        this.asin = str2;
        this.sourceCollectionID = str3;
        this.entityId = str4;
        this.entityIdType = str5;
        this.entityType = str6;
        this.videoType = videoType;
        this.terminationReason = terminationReason;
        this.resumeReason = resumeReason;
        this.playbackStatus = videoPlaybackState;
    }

    public final VideoStoryContent getContent() {
        return this.content;
    }

    public final int getStartingIndex() {
        return this.startingIndex;
    }

    /* JADX INFO: renamed from: isShufflePlay, reason: from getter */
    public final boolean getIsShufflePlay() {
        return this.isShufflePlay;
    }

    /* JADX INFO: renamed from: isAlexaInitiatedPlay, reason: from getter */
    public final boolean getIsAlexaInitiatedPlay() {
        return this.isAlexaInitiatedPlay;
    }

    public final Map<String, String> getMetricsContext() {
        return this.metricsContext;
    }

    public final String getAsin() {
        return this.asin;
    }

    public final String getSourceCollectionID() {
        return this.sourceCollectionID;
    }

    public final TerminationReason getTerminationReason() {
        return this.terminationReason;
    }

    public final void setTerminationReason(TerminationReason terminationReason) {
        this.terminationReason = terminationReason;
    }

    public final ResumeReason getResumeReason() {
        return this.resumeReason;
    }

    public final void setResumeReason(ResumeReason resumeReason) {
        this.resumeReason = resumeReason;
    }

    public final VideoPlaybackState getPlaybackStatus() {
        return this.playbackStatus;
    }

    public final void setPlaybackStatus(VideoPlaybackState videoPlaybackState) {
        this.playbackStatus = videoPlaybackState;
    }
}
