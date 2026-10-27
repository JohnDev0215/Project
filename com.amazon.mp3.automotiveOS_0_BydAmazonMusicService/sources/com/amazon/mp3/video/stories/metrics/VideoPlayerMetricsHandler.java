package com.amazon.mp3.video.stories.metrics;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.amazon.mp3.elf.ExplicitLanguageManager;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.video.stories.VideoType;
import com.amazon.mp3.video.stories.playback.PlaybackRequest;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.media.playback.util.StringUtils;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.MetricsManager;
import com.amazon.music.metrics.mts.MTSEvent;
import com.amazon.music.metrics.mts.event.EventTimeConverter;
import com.amazon.music.metrics.mts.event.types.ContentSubscriptionMode;
import com.amazon.music.metrics.mts.event.types.PlaybackEntityType;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.PlaybackSourceType;
import com.amazon.music.metrics.mts.event.types.ResourceType;
import com.amazon.music.metrics.mts.event.types.ResumeReason;
import com.amazon.music.metrics.mts.event.types.SelectionSourceType;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import com.amazon.music.metrics.util.RangeValidator;
import java.util.UUID;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VideoPlayerMetricsHandler.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0010H\u0002J\u0018\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0010H\u0002J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J>\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0006J6\u0010!\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u001fJV\u0010%\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u00062\u0006\u0010&\u001a\u00020\u00102\u0006\u0010'\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010(\u001a\u00020\u00062\u0006\u0010)\u001a\u00020*2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0006JF\u0010+\u001a\u00020\u00152\u0006\u0010,\u001a\u00020\u00102\u0006\u0010'\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020\u00062\u0006\u0010-\u001a\u00020\u00102\u0006\u0010.\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u001fJ.\u0010/\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010&\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0006R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lcom/amazon/mp3/video/stories/metrics/VideoPlayerMetricsHandler;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "metricsManager", "Lcom/amazon/music/metrics/MetricsManager;", "getMetricsManager", "()Lcom/amazon/music/metrics/MetricsManager;", "metricsManager$delegate", "Lkotlin/Lazy;", "videoPlaybackInstanceId", "Ljava/util/UUID;", "getDurationMilliseconds", "", "playbackDurationMilliseconds", "trackProgressMilliseconds", "getDurationSeconds", "sendMetricsEvent", "", NotificationCompat.CATEGORY_EVENT, "Lcom/amazon/music/metrics/mts/MTSEvent;", "sendVideoPlaybackCompletedEvent", "eventName", "playbackRequest", "Lcom/amazon/mp3/video/stories/playback/PlaybackRequest;", "trackProgressMilliSeconds", "trackLengthMilliseconds", "videoType", "Lcom/amazon/mp3/video/stories/VideoType;", "mediaPlayerName", "sendVideoPlaybackResumedEvent", "resumeReason", "Lcom/amazon/music/metrics/mts/event/types/ResumeReason;", "resumePlaybackDelayMilliseconds", "sendVideoPlaybackTerminated", "initialPlaybackDelayMilliseconds", "progressInMillis", "errorMessage", "terminationReason", "Lcom/amazon/music/metrics/mts/event/types/TerminationReason;", "sendVideoRebufferingRequiredEvent", "rebufferGapInMilliseconds", "transferSpeed", PageUriUtils.SOURCE_QUERY_PARAM, "sendVideoStreamingInitiatedEvent", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class VideoPlayerMetricsHandler {
    private final String TAG;
    private final Context context;

    /* JADX INFO: renamed from: metricsManager$delegate, reason: from kotlin metadata */
    private final Lazy metricsManager;
    private UUID videoPlaybackInstanceId;

    public VideoPlayerMetricsHandler(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.metricsManager = LazyKt__LazyJVMKt.lazy(new Function0<MetricsManager>() { // from class: com.amazon.mp3.video.stories.metrics.VideoPlayerMetricsHandler$metricsManager$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final MetricsManager invoke() {
                return MetricsHolder.getManager();
            }
        });
        this.TAG = "VideoStoryMetrics";
        UUID uuidRandomUUID = UUID.randomUUID();
        Intrinsics.checkNotNullExpressionValue(uuidRandomUUID, "randomUUID()");
        this.videoPlaybackInstanceId = uuidRandomUUID;
    }

    private final MetricsManager getMetricsManager() {
        Object value = this.metricsManager.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-metricsManager>(...)");
        return (MetricsManager) value;
    }

    public final void sendVideoPlaybackTerminated(String eventName, long initialPlaybackDelayMilliseconds, long progressInMillis, long playbackDurationMilliseconds, long trackLengthMilliseconds, String errorMessage, TerminationReason terminationReason, PlaybackRequest playbackRequest, VideoType videoType, String mediaPlayerName) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
        Intrinsics.checkNotNullParameter(terminationReason, "terminationReason");
        Intrinsics.checkNotNullParameter(playbackRequest, "playbackRequest");
        Intrinsics.checkNotNullParameter(videoType, "videoType");
        Intrinsics.checkNotNullParameter(mediaPlayerName, "mediaPlayerName");
        VideoMetricsEventsFactory videoMetricsEventsFactory = VideoMetricsEventsFactory.INSTANCE;
        String asin = playbackRequest.getAsin();
        String str = asin == null ? "" : asin;
        UUID uuid = this.videoPlaybackInstanceId;
        String string = videoType.toString();
        SelectionSourceType selectionSourceType = SelectionSourceType.SONGS;
        VideoMetricsUtil videoMetricsUtil = VideoMetricsUtil.INSTANCE;
        String metricsName = videoMetricsUtil.getNetworkProvider(this.context).getMetricsName();
        ContentSubscriptionMode contentSubscriptionMode = videoMetricsUtil.getContentSubscriptionMode();
        ResourceType resourceType = videoMetricsUtil.getResourceType();
        PlaybackSourceType playbackSourceType = PlaybackSourceType.STREAM;
        PlaybackEntityType playbackEntityType = PlaybackEntityType.VIDEO;
        boolean isShufflePlay = playbackRequest.getIsShufflePlay();
        boolean isAlexaInitiatedPlay = playbackRequest.getIsAlexaInitiatedPlay();
        String sourceCollectionID = playbackRequest.getSourceCollectionID();
        String str2 = sourceCollectionID == null ? "" : sourceCollectionID;
        boolean zIsExplicitFilterEnabled = ExplicitLanguageManager.isExplicitFilterEnabled();
        long durationSeconds = getDurationSeconds(playbackDurationMilliseconds, progressInMillis);
        PlaybackPageType playbackPageType = PlaybackPageType.AUTOMOTIVE_OS_BROWSE_HOME;
        Intrinsics.checkNotNullExpressionValue(metricsName, "metricsName");
        MTSEvent mTSEventCreateVideoPlaybackTerminatedEvent = videoMetricsEventsFactory.createVideoPlaybackTerminatedEvent(eventName, initialPlaybackDelayMilliseconds, str, uuid, playbackPageType, trackLengthMilliseconds, playbackDurationMilliseconds, durationSeconds, progressInMillis, terminationReason, errorMessage, mediaPlayerName, string, playbackSourceType, resourceType, playbackEntityType, selectionSourceType, contentSubscriptionMode, metricsName, isShufflePlay, isAlexaInitiatedPlay, str2, zIsExplicitFilterEnabled);
        mTSEventCreateVideoPlaybackTerminatedEvent.setMetricsContext(playbackRequest.getMetricsContext());
        sendMetricsEvent(mTSEventCreateVideoPlaybackTerminatedEvent);
    }

    public final void sendVideoStreamingInitiatedEvent(String eventName, PlaybackRequest playbackRequest, long initialPlaybackDelayMilliseconds, VideoType videoType, String mediaPlayerName) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        Intrinsics.checkNotNullParameter(playbackRequest, "playbackRequest");
        Intrinsics.checkNotNullParameter(videoType, "videoType");
        Intrinsics.checkNotNullParameter(mediaPlayerName, "mediaPlayerName");
        VideoMetricsEventsFactory videoMetricsEventsFactory = VideoMetricsEventsFactory.INSTANCE;
        String asin = playbackRequest.getAsin();
        if (asin == null) {
            asin = "";
        }
        UUID uuid = this.videoPlaybackInstanceId;
        String string = videoType.toString();
        SelectionSourceType selectionSourceType = SelectionSourceType.SONGS;
        VideoMetricsUtil videoMetricsUtil = VideoMetricsUtil.INSTANCE;
        String metricsName = videoMetricsUtil.getNetworkProvider(this.context).getMetricsName();
        ContentSubscriptionMode contentSubscriptionMode = videoMetricsUtil.getContentSubscriptionMode();
        ResourceType resourceType = videoMetricsUtil.getResourceType();
        PlaybackSourceType playbackSourceType = PlaybackSourceType.STREAM;
        PlaybackEntityType playbackEntityType = PlaybackEntityType.VIDEO;
        boolean isShufflePlay = playbackRequest.getIsShufflePlay();
        boolean isAlexaInitiatedPlay = playbackRequest.getIsAlexaInitiatedPlay();
        String sourceCollectionID = playbackRequest.getSourceCollectionID();
        String str = sourceCollectionID == null ? "" : sourceCollectionID;
        boolean zIsExplicitFilterEnabled = ExplicitLanguageManager.isExplicitFilterEnabled();
        Intrinsics.checkNotNullExpressionValue(metricsName, "metricsName");
        MTSEvent mTSEventCreateVideoPlaybackInitiatedEvent = videoMetricsEventsFactory.createVideoPlaybackInitiatedEvent(eventName, asin, uuid, initialPlaybackDelayMilliseconds, mediaPlayerName, string, selectionSourceType, zIsExplicitFilterEnabled, metricsName, contentSubscriptionMode, resourceType, playbackSourceType, playbackEntityType, isShufflePlay, isAlexaInitiatedPlay, str);
        mTSEventCreateVideoPlaybackInitiatedEvent.setMetricsContext(playbackRequest.getMetricsContext());
        sendMetricsEvent(mTSEventCreateVideoPlaybackInitiatedEvent);
    }

    public final void sendVideoPlaybackCompletedEvent(String eventName, PlaybackRequest playbackRequest, long trackProgressMilliSeconds, long playbackDurationMilliseconds, long trackLengthMilliseconds, VideoType videoType, String mediaPlayerName) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        Intrinsics.checkNotNullParameter(playbackRequest, "playbackRequest");
        Intrinsics.checkNotNullParameter(videoType, "videoType");
        Intrinsics.checkNotNullParameter(mediaPlayerName, "mediaPlayerName");
        VideoMetricsEventsFactory videoMetricsEventsFactory = VideoMetricsEventsFactory.INSTANCE;
        String asin = playbackRequest.getAsin();
        String str = asin == null ? "" : asin;
        UUID uuid = this.videoPlaybackInstanceId;
        String string = videoType.toString();
        SelectionSourceType selectionSourceType = SelectionSourceType.SONGS;
        VideoMetricsUtil videoMetricsUtil = VideoMetricsUtil.INSTANCE;
        String metricsName = videoMetricsUtil.getNetworkProvider(this.context).getMetricsName();
        ContentSubscriptionMode contentSubscriptionMode = videoMetricsUtil.getContentSubscriptionMode();
        ResourceType resourceType = videoMetricsUtil.getResourceType();
        PlaybackSourceType playbackSourceType = PlaybackSourceType.STREAM;
        PlaybackEntityType playbackEntityType = PlaybackEntityType.VIDEO;
        boolean isShufflePlay = playbackRequest.getIsShufflePlay();
        boolean isAlexaInitiatedPlay = playbackRequest.getIsAlexaInitiatedPlay();
        String sourceCollectionID = playbackRequest.getSourceCollectionID();
        String str2 = sourceCollectionID == null ? "" : sourceCollectionID;
        boolean zIsExplicitFilterEnabled = ExplicitLanguageManager.isExplicitFilterEnabled();
        long durationSeconds = getDurationSeconds(playbackDurationMilliseconds, trackProgressMilliSeconds);
        Intrinsics.checkNotNullExpressionValue(metricsName, "metricsName");
        MTSEvent mTSEventCreateVideoPlaybackCompletedEvent = videoMetricsEventsFactory.createVideoPlaybackCompletedEvent(eventName, uuid, str, playbackDurationMilliseconds, trackLengthMilliseconds, trackProgressMilliSeconds, durationSeconds, mediaPlayerName, string, contentSubscriptionMode, playbackEntityType, playbackSourceType, selectionSourceType, zIsExplicitFilterEnabled, resourceType, metricsName, str2, isAlexaInitiatedPlay, isShufflePlay);
        UUID uuidRandomUUID = UUID.randomUUID();
        Intrinsics.checkNotNullExpressionValue(uuidRandomUUID, "randomUUID()");
        this.videoPlaybackInstanceId = uuidRandomUUID;
        mTSEventCreateVideoPlaybackCompletedEvent.setMetricsContext(playbackRequest.getMetricsContext());
        sendMetricsEvent(mTSEventCreateVideoPlaybackCompletedEvent);
    }

    public final void sendVideoPlaybackResumedEvent(String eventName, PlaybackRequest playbackRequest, ResumeReason resumeReason, long resumePlaybackDelayMilliseconds, long trackProgressMilliSeconds, VideoType videoType) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        Intrinsics.checkNotNullParameter(playbackRequest, "playbackRequest");
        Intrinsics.checkNotNullParameter(resumeReason, "resumeReason");
        Intrinsics.checkNotNullParameter(videoType, "videoType");
        VideoMetricsEventsFactory videoMetricsEventsFactory = VideoMetricsEventsFactory.INSTANCE;
        UUID uuid = this.videoPlaybackInstanceId;
        String asin = playbackRequest.getAsin();
        if (asin == null) {
            asin = "";
        }
        MTSEvent mTSEventCreateVideoPlaybackResumedEvent = videoMetricsEventsFactory.createVideoPlaybackResumedEvent(eventName, asin, uuid, trackProgressMilliSeconds, resumePlaybackDelayMilliseconds, resumeReason, videoType.toString(), PlaybackSourceType.STREAM, PlaybackEntityType.VIDEO);
        mTSEventCreateVideoPlaybackResumedEvent.setMetricsContext(playbackRequest.getMetricsContext());
        sendMetricsEvent(mTSEventCreateVideoPlaybackResumedEvent);
    }

    private final void sendMetricsEvent(MTSEvent event) {
        String str = " - class: " + ((Object) event.getClass().getSimpleName()) + ", eventName: " + ((Object) event.getEventName());
        if (!StringUtils.shouldRedact()) {
            str = str + ", attributes: " + ((Object) StringUtils.toString(event.getEventAttributes()));
        }
        Log.debug(this.TAG, str);
        getMetricsManager().handleEvent(event);
    }

    private final long getDurationSeconds(long playbackDurationMilliseconds, long trackProgressMilliseconds) {
        return new EventTimeConverter().convertToRoundedSeconds(getDurationMilliseconds(playbackDurationMilliseconds, trackProgressMilliseconds));
    }

    private final long getDurationMilliseconds(long playbackDurationMilliseconds, long trackProgressMilliseconds) {
        return new RangeValidator().withinRange(playbackDurationMilliseconds, 0L, trackProgressMilliseconds) ? playbackDurationMilliseconds : trackProgressMilliseconds;
    }

    public final void sendVideoRebufferingRequiredEvent(long rebufferGapInMilliseconds, long progressInMillis, long trackLengthMilliseconds, PlaybackRequest playbackRequest, String mediaPlayerName, long transferSpeed, String source, VideoType videoType) {
        Intrinsics.checkNotNullParameter(playbackRequest, "playbackRequest");
        Intrinsics.checkNotNullParameter(mediaPlayerName, "mediaPlayerName");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(videoType, "videoType");
        VideoMetricsEventsFactory videoMetricsEventsFactory = VideoMetricsEventsFactory.INSTANCE;
        String asin = playbackRequest.getAsin();
        UUID uuid = this.videoPlaybackInstanceId;
        VideoMetricsUtil videoMetricsUtil = VideoMetricsUtil.INSTANCE;
        ContentSubscriptionMode contentSubscriptionMode = videoMetricsUtil.getContentSubscriptionMode();
        ResourceType resourceType = videoMetricsUtil.getResourceType();
        PlaybackSourceType playbackSourceType = PlaybackSourceType.STREAM;
        PlaybackEntityType playbackEntityType = PlaybackEntityType.VIDEO;
        String sourceCollectionID = playbackRequest.getSourceCollectionID();
        if (sourceCollectionID == null) {
            sourceCollectionID = "";
        }
        MTSEvent mTSEventCreateVideoStreamingRequiredRebufferingEvent = videoMetricsEventsFactory.createVideoStreamingRequiredRebufferingEvent(asin, uuid, mediaPlayerName, source, contentSubscriptionMode, resourceType, rebufferGapInMilliseconds, progressInMillis, trackLengthMilliseconds, playbackSourceType, playbackEntityType, sourceCollectionID, SelectionSourceType.SONGS, transferSpeed, playbackRequest.getIsShufflePlay(), videoType.toString(), ExplicitLanguageManager.isExplicitFilterEnabled());
        mTSEventCreateVideoStreamingRequiredRebufferingEvent.setMetricsContext(playbackRequest.getMetricsContext());
        sendMetricsEvent(mTSEventCreateVideoStreamingRequiredRebufferingEvent);
    }
}
