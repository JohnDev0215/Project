package com.amazon.mp3.video.stories.metrics;

import com.amazon.mp3.AmazonApplication;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.mts.MTSEvent;
import com.amazon.music.metrics.mts.event.EventTimeConverter;
import com.amazon.music.metrics.mts.event.definition.playback.GenericPlaybackCompletedEvent;
import com.amazon.music.metrics.mts.event.definition.playback.GenericPlaybackInitiatedEvent;
import com.amazon.music.metrics.mts.event.definition.playback.GenericPlaybackResumedEvent;
import com.amazon.music.metrics.mts.event.definition.playback.GenericPlaybackTerminatedEvent;
import com.amazon.music.metrics.mts.event.definition.playback.TrackPlaybackInfo;
import com.amazon.music.metrics.mts.event.definition.playback.VideoStreamingRequiredRebufferingEvent;
import com.amazon.music.metrics.mts.event.types.BitRate;
import com.amazon.music.metrics.mts.event.types.CacheHitStatus;
import com.amazon.music.metrics.mts.event.types.ContentSubscriptionMode;
import com.amazon.music.metrics.mts.event.types.MediaPlayerType;
import com.amazon.music.metrics.mts.event.types.PlaybackEntityType;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.PlaybackSourceType;
import com.amazon.music.metrics.mts.event.types.ResourceType;
import com.amazon.music.metrics.mts.event.types.ResumeReason;
import com.amazon.music.metrics.mts.event.types.SelectionSourceInfo;
import com.amazon.music.metrics.mts.event.types.SelectionSourceType;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VideoMetricsEventsFactory.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b/\u00100J\u0086\u0001\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u0002J\u009e\u0001\u0010!\u001a\u00020\u001b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\rJ¾\u0001\u0010'\u001a\u00020\u001b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010#\u001a\u00020\"2\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u00072\u0006\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\rJN\u0010*\u001a\u00020\u001b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010)\u001a\u00020(2\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0016J\u0090\u0001\u0010.\u001a\u00020\u001b2\b\u0010\u0004\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010+\u001a\u00020\u00072\u0006\u0010,\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010-\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\r¨\u00061"}, d2 = {"Lcom/amazon/mp3/video/stories/metrics/VideoMetricsEventsFactory;", "", "", "eventName", "asin", "Ljava/util/UUID;", "videoPlaybackInstanceID", "", "initialPlaybackDelayMilliseconds", "mediaPlayerName", "videoType", "Lcom/amazon/music/metrics/mts/event/types/SelectionSourceType;", "selectionSourceType", "", "isExplicitLanguageFilterOn", PageUriUtils.SOURCE_QUERY_PARAM, "Lcom/amazon/music/metrics/mts/event/types/ContentSubscriptionMode;", "contentSubscriptionMode", "Lcom/amazon/music/metrics/mts/event/types/ResourceType;", "resourceType", "Lcom/amazon/music/metrics/mts/event/types/PlaybackSourceType;", "playbackSourceType", "Lcom/amazon/music/metrics/mts/event/types/PlaybackEntityType;", "playbackEntityType", "isShufflePlay", "isAlexaInitiatedPlay", "sourceCollectionID", "Lcom/amazon/music/metrics/mts/MTSEvent;", "createVideoPlaybackInitiatedEvent", "playbackDurationMilliseconds", "trackLengthMilliseconds", "trackProgressMilliseconds", "durationSeconds", "createVideoPlaybackCompletedEvent", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/TerminationReason;", "terminationReason", "errorMessage", "createVideoPlaybackTerminatedEvent", "Lcom/amazon/music/metrics/mts/event/types/ResumeReason;", "resumeReason", "createVideoPlaybackResumedEvent", "rebufferGapInMilliseconds", "progressInMillis", "transferSpeed", "createVideoStreamingRequiredRebufferingEvent", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class VideoMetricsEventsFactory {
    public static final VideoMetricsEventsFactory INSTANCE = new VideoMetricsEventsFactory();

    private VideoMetricsEventsFactory() {
    }

    public final MTSEvent createVideoPlaybackInitiatedEvent(String eventName, String asin, UUID videoPlaybackInstanceID, long initialPlaybackDelayMilliseconds, String mediaPlayerName, String videoType, SelectionSourceType selectionSourceType, boolean isExplicitLanguageFilterOn, String source, ContentSubscriptionMode contentSubscriptionMode, ResourceType resourceType, PlaybackSourceType playbackSourceType, PlaybackEntityType playbackEntityType, boolean isShufflePlay, boolean isAlexaInitiatedPlay, String sourceCollectionID) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        Intrinsics.checkNotNullParameter(asin, "asin");
        Intrinsics.checkNotNullParameter(videoPlaybackInstanceID, "videoPlaybackInstanceID");
        Intrinsics.checkNotNullParameter(mediaPlayerName, "mediaPlayerName");
        Intrinsics.checkNotNullParameter(videoType, "videoType");
        Intrinsics.checkNotNullParameter(selectionSourceType, "selectionSourceType");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(contentSubscriptionMode, "contentSubscriptionMode");
        Intrinsics.checkNotNullParameter(resourceType, "resourceType");
        Intrinsics.checkNotNullParameter(playbackSourceType, "playbackSourceType");
        Intrinsics.checkNotNullParameter(playbackEntityType, "playbackEntityType");
        Intrinsics.checkNotNullParameter(sourceCollectionID, "sourceCollectionID");
        GenericPlaybackInitiatedEvent genericPlaybackInitiatedEvent = new GenericPlaybackInitiatedEvent(null, new TrackPlaybackInfo(asin, null, contentSubscriptionMode.getAttribute(), selectionSourceType.getMetricsValue(), playbackEntityType.getMetricsValue()), new SelectionSourceInfo(selectionSourceType, sourceCollectionID), source, false, initialPlaybackDelayMilliseconds, isExplicitLanguageFilterOn, isShufflePlay, mediaPlayerName, resourceType, playbackSourceType, playbackEntityType);
        genericPlaybackInitiatedEvent.addAttribute("playbackInstanceId", videoPlaybackInstanceID.toString());
        genericPlaybackInitiatedEvent.addAttribute("videoType", videoType);
        genericPlaybackInitiatedEvent.addAttribute("initialPlaybackDelayMilliseconds", initialPlaybackDelayMilliseconds);
        genericPlaybackInitiatedEvent.addAttribute("mediaPlayerName", mediaPlayerName);
        genericPlaybackInitiatedEvent.addAttribute("isShufflePlay", isShufflePlay);
        genericPlaybackInitiatedEvent.addAttribute(PageUriUtils.SOURCE_QUERY_PARAM, source);
        genericPlaybackInitiatedEvent.addAttribute("contentSubscriptionMode", contentSubscriptionMode.getAttribute());
        genericPlaybackInitiatedEvent.addAttribute("resourceType", resourceType.getMetricsValue());
        genericPlaybackInitiatedEvent.addAttribute("selectionSourceType", selectionSourceType);
        genericPlaybackInitiatedEvent.addAttribute("isVoiceInitiated", String.valueOf(isAlexaInitiatedPlay));
        genericPlaybackInitiatedEvent.addAttribute("isExplicitLanguageFilterOn", String.valueOf(isExplicitLanguageFilterOn));
        genericPlaybackInitiatedEvent.addAttribute("selectionSourceId", sourceCollectionID);
        return genericPlaybackInitiatedEvent;
    }

    public final MTSEvent createVideoPlaybackCompletedEvent(String eventName, UUID videoPlaybackInstanceID, String asin, long playbackDurationMilliseconds, long trackLengthMilliseconds, long trackProgressMilliseconds, long durationSeconds, String mediaPlayerName, String videoType, ContentSubscriptionMode contentSubscriptionMode, PlaybackEntityType playbackEntityType, PlaybackSourceType playbackSourceType, SelectionSourceType selectionSourceType, boolean isExplicitLanguageFilterOn, ResourceType resourceType, String source, String sourceCollectionID, boolean isAlexaInitiatedPlay, boolean isShufflePlay) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        Intrinsics.checkNotNullParameter(videoPlaybackInstanceID, "videoPlaybackInstanceID");
        Intrinsics.checkNotNullParameter(asin, "asin");
        Intrinsics.checkNotNullParameter(mediaPlayerName, "mediaPlayerName");
        Intrinsics.checkNotNullParameter(videoType, "videoType");
        Intrinsics.checkNotNullParameter(contentSubscriptionMode, "contentSubscriptionMode");
        Intrinsics.checkNotNullParameter(playbackEntityType, "playbackEntityType");
        Intrinsics.checkNotNullParameter(playbackSourceType, "playbackSourceType");
        Intrinsics.checkNotNullParameter(selectionSourceType, "selectionSourceType");
        Intrinsics.checkNotNullParameter(resourceType, "resourceType");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(sourceCollectionID, "sourceCollectionID");
        GenericPlaybackCompletedEvent genericPlaybackCompletedEvent = new GenericPlaybackCompletedEvent(null, new TrackPlaybackInfo(asin, null, contentSubscriptionMode.getAttribute(), selectionSourceType.getMetricsValue(), playbackEntityType.getMetricsValue()), new SelectionSourceInfo(selectionSourceType, sourceCollectionID), playbackDurationMilliseconds, trackLengthMilliseconds, playbackSourceType.getMetricsValue(), mediaPlayerName, trackProgressMilliseconds, false, isExplicitLanguageFilterOn, isShufflePlay, resourceType, playbackSourceType, playbackEntityType);
        genericPlaybackCompletedEvent.addAttribute("videoType", videoType);
        genericPlaybackCompletedEvent.addAttribute("durationSeconds", durationSeconds);
        genericPlaybackCompletedEvent.addAttribute("playbackInstanceId", videoPlaybackInstanceID.toString());
        genericPlaybackCompletedEvent.addAttribute("mediaPlayerName", mediaPlayerName);
        genericPlaybackCompletedEvent.addAttribute("contentSubscriptionMode", contentSubscriptionMode.getAttribute());
        genericPlaybackCompletedEvent.addAttribute("selectionSourceType", selectionSourceType);
        genericPlaybackCompletedEvent.addAttribute("selectionSourceId", sourceCollectionID);
        genericPlaybackCompletedEvent.addAttribute("isVoiceInitiated", String.valueOf(isAlexaInitiatedPlay));
        genericPlaybackCompletedEvent.addAttribute("isExplicitLanguageFilterOn", String.valueOf(isExplicitLanguageFilterOn));
        genericPlaybackCompletedEvent.addAttribute("resourceType", resourceType.getMetricsValue());
        genericPlaybackCompletedEvent.addAttribute("isShufflePlay", isShufflePlay);
        genericPlaybackCompletedEvent.addAttribute(PageUriUtils.SOURCE_QUERY_PARAM, source);
        genericPlaybackCompletedEvent.addAttribute("trackProgress", new EventTimeConverter().convertToRoundedSeconds(trackProgressMilliseconds));
        return genericPlaybackCompletedEvent;
    }

    public final MTSEvent createVideoPlaybackTerminatedEvent(String eventName, long initialPlaybackDelayMilliseconds, String asin, UUID videoPlaybackInstanceID, PlaybackPageType pageType, long trackLengthMilliseconds, long playbackDurationMilliseconds, long durationSeconds, long trackProgressMilliseconds, TerminationReason terminationReason, String errorMessage, String mediaPlayerName, String videoType, PlaybackSourceType playbackSourceType, ResourceType resourceType, PlaybackEntityType playbackEntityType, SelectionSourceType selectionSourceType, ContentSubscriptionMode contentSubscriptionMode, String source, boolean isShufflePlay, boolean isAlexaInitiatedPlay, String sourceCollectionID, boolean isExplicitLanguageFilterOn) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        Intrinsics.checkNotNullParameter(asin, "asin");
        Intrinsics.checkNotNullParameter(videoPlaybackInstanceID, "videoPlaybackInstanceID");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        Intrinsics.checkNotNullParameter(terminationReason, "terminationReason");
        Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
        Intrinsics.checkNotNullParameter(mediaPlayerName, "mediaPlayerName");
        Intrinsics.checkNotNullParameter(videoType, "videoType");
        Intrinsics.checkNotNullParameter(playbackSourceType, "playbackSourceType");
        Intrinsics.checkNotNullParameter(resourceType, "resourceType");
        Intrinsics.checkNotNullParameter(playbackEntityType, "playbackEntityType");
        Intrinsics.checkNotNullParameter(selectionSourceType, "selectionSourceType");
        Intrinsics.checkNotNullParameter(contentSubscriptionMode, "contentSubscriptionMode");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(sourceCollectionID, "sourceCollectionID");
        GenericPlaybackTerminatedEvent genericPlaybackTerminatedEvent = new GenericPlaybackTerminatedEvent(null, new TrackPlaybackInfo(asin, null, contentSubscriptionMode.getAttribute(), selectionSourceType.getMetricsValue(), playbackEntityType.getMetricsValue()), new SelectionSourceInfo(selectionSourceType, sourceCollectionID), playbackSourceType.getMetricsValue(), isExplicitLanguageFilterOn, trackLengthMilliseconds, playbackDurationMilliseconds, playbackEntityType, playbackSourceType, errorMessage, terminationReason, resourceType, pageType, mediaPlayerName, false, isShufflePlay, trackProgressMilliseconds);
        genericPlaybackTerminatedEvent.addAttribute("videoType", videoType);
        genericPlaybackTerminatedEvent.addAttribute("durationSeconds", durationSeconds);
        genericPlaybackTerminatedEvent.addAttribute("playbackInstanceId", videoPlaybackInstanceID.toString());
        genericPlaybackTerminatedEvent.addAttribute("terminationReason", terminationReason.getMetricsValue());
        genericPlaybackTerminatedEvent.addAttribute("mediaPlayerName", mediaPlayerName);
        genericPlaybackTerminatedEvent.addAttribute("isShufflePlay", isShufflePlay);
        genericPlaybackTerminatedEvent.addAttribute("pageType", pageType);
        genericPlaybackTerminatedEvent.addAttribute("contentSubscriptionMode", contentSubscriptionMode.getAttribute());
        genericPlaybackTerminatedEvent.addAttribute("resourceType", resourceType.getMetricsValue());
        genericPlaybackTerminatedEvent.addAttribute("selectionSourceType", selectionSourceType);
        genericPlaybackTerminatedEvent.addAttribute("selectionSourceId", sourceCollectionID);
        genericPlaybackTerminatedEvent.addAttribute(PageUriUtils.SOURCE_QUERY_PARAM, source);
        genericPlaybackTerminatedEvent.addAttribute("isVoiceInitiated", String.valueOf(isAlexaInitiatedPlay));
        genericPlaybackTerminatedEvent.addAttribute("isExplicitLanguageFilterOn", String.valueOf(isExplicitLanguageFilterOn));
        genericPlaybackTerminatedEvent.addAttribute("initialPlaybackDelayMilliseconds", initialPlaybackDelayMilliseconds);
        if (terminationReason == TerminationReason.ERROR) {
            genericPlaybackTerminatedEvent.addAttribute("errorMessage", errorMessage);
        }
        genericPlaybackTerminatedEvent.addAttribute("trackProgress", new EventTimeConverter().convertToRoundedSeconds(trackProgressMilliseconds));
        return genericPlaybackTerminatedEvent;
    }

    public final MTSEvent createVideoPlaybackResumedEvent(String eventName, String asin, UUID videoPlaybackInstanceID, long trackProgressMilliseconds, long initialPlaybackDelayMilliseconds, ResumeReason resumeReason, String videoType, PlaybackSourceType playbackSourceType, PlaybackEntityType playbackEntityType) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        Intrinsics.checkNotNullParameter(asin, "asin");
        Intrinsics.checkNotNullParameter(videoPlaybackInstanceID, "videoPlaybackInstanceID");
        Intrinsics.checkNotNullParameter(resumeReason, "resumeReason");
        Intrinsics.checkNotNullParameter(videoType, "videoType");
        Intrinsics.checkNotNullParameter(playbackSourceType, "playbackSourceType");
        Intrinsics.checkNotNullParameter(playbackEntityType, "playbackEntityType");
        GenericPlaybackResumedEvent genericPlaybackResumedEvent = new GenericPlaybackResumedEvent(null, new TrackPlaybackInfo(asin, null, ContentSubscriptionMode.UNLIMITED.getAttribute(), playbackSourceType.getMetricsValue(), playbackEntityType.getMetricsValue()), resumeReason, initialPlaybackDelayMilliseconds, trackProgressMilliseconds, playbackSourceType, playbackEntityType);
        genericPlaybackResumedEvent.addAttribute("videoType", videoType);
        genericPlaybackResumedEvent.addAttribute("playbackInstanceId", videoPlaybackInstanceID.toString());
        genericPlaybackResumedEvent.addAttribute("initialPlaybackDelayMilliseconds", initialPlaybackDelayMilliseconds);
        genericPlaybackResumedEvent.addAttribute("resumeReason", resumeReason.getMetricsValue());
        genericPlaybackResumedEvent.addAttribute("trackProgress", new EventTimeConverter().convertToRoundedSeconds(trackProgressMilliseconds));
        return genericPlaybackResumedEvent;
    }

    public final MTSEvent createVideoStreamingRequiredRebufferingEvent(String asin, UUID videoPlaybackInstanceID, String mediaPlayerName, String source, ContentSubscriptionMode contentSubscriptionMode, ResourceType resourceType, long rebufferGapInMilliseconds, long progressInMillis, long trackLengthMilliseconds, PlaybackSourceType playbackSourceType, PlaybackEntityType playbackEntityType, String sourceCollectionID, SelectionSourceType selectionSourceType, long transferSpeed, boolean isShufflePlay, String videoType, boolean isExplicitLanguageFilterOn) {
        Intrinsics.checkNotNullParameter(videoPlaybackInstanceID, "videoPlaybackInstanceID");
        Intrinsics.checkNotNullParameter(mediaPlayerName, "mediaPlayerName");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(contentSubscriptionMode, "contentSubscriptionMode");
        Intrinsics.checkNotNullParameter(resourceType, "resourceType");
        Intrinsics.checkNotNullParameter(playbackSourceType, "playbackSourceType");
        Intrinsics.checkNotNullParameter(playbackEntityType, "playbackEntityType");
        Intrinsics.checkNotNullParameter(sourceCollectionID, "sourceCollectionID");
        Intrinsics.checkNotNullParameter(selectionSourceType, "selectionSourceType");
        Intrinsics.checkNotNullParameter(videoType, "videoType");
        VideoStreamingRequiredRebufferingEvent videoStreamingRequiredRebufferingEvent = new VideoStreamingRequiredRebufferingEvent(new TrackPlaybackInfo(asin, null, contentSubscriptionMode.getAttribute(), playbackSourceType.getMetricsValue(), playbackEntityType.getMetricsValue()), MediaPlayerType.LOCAL, source, progressInMillis, rebufferGapInMilliseconds, (int) ((progressInMillis / trackLengthMilliseconds) * 100), 0, 0, CacheHitStatus.UNKNOWN, BitRate.UNKNOWN, new SelectionSourceInfo(selectionSourceType, sourceCollectionID), transferSpeed);
        videoStreamingRequiredRebufferingEvent.addAttribute("playbackInstanceId", videoPlaybackInstanceID.toString());
        videoStreamingRequiredRebufferingEvent.addAttribute("mediaPlayerName", mediaPlayerName);
        videoStreamingRequiredRebufferingEvent.addAttribute(PageUriUtils.SOURCE_QUERY_PARAM, source);
        videoStreamingRequiredRebufferingEvent.addAttribute("platformName", AmazonApplication.getCapabilities().getPlatformName());
        videoStreamingRequiredRebufferingEvent.addAttribute("videoType", videoType);
        videoStreamingRequiredRebufferingEvent.addAttribute("isShufflePlay", isShufflePlay);
        videoStreamingRequiredRebufferingEvent.addAttribute("isExplicitLanguageFilterOn", String.valueOf(isExplicitLanguageFilterOn));
        videoStreamingRequiredRebufferingEvent.addAttribute("selectionSourceType", selectionSourceType);
        videoStreamingRequiredRebufferingEvent.addAttribute("playbackEntityType", playbackEntityType.getMetricsValue());
        videoStreamingRequiredRebufferingEvent.addAttribute("playbackSourceType", playbackSourceType.getMetricsValue());
        videoStreamingRequiredRebufferingEvent.addAttribute("resourceType", resourceType.getMetricsValue());
        return videoStreamingRequiredRebufferingEvent;
    }
}
