package com.amazon.music.media.playback.metrics.mts;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaLink;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackException;
import com.amazon.music.media.playback.PlaybackProvider;
import com.amazon.music.media.playback.SequencerPlaybackProvider;
import com.amazon.music.media.playback.access.MediaAccessInfo;
import com.amazon.music.media.playback.config.PlaybackConfig;
import com.amazon.music.media.playback.metrics.MetricsInfo;
import com.amazon.music.media.playback.metrics.PlaybackEvent;
import com.amazon.music.media.playback.metrics.PlaybackMetricsHandler;
import com.amazon.music.media.playback.sequencer.Sequencer;
import com.amazon.music.media.playback.util.StrictMode;
import com.amazon.music.media.playback.util.StringUtils;
import com.amazon.music.metrics.MetricsManager;
import com.amazon.music.metrics.mts.MTSEvent;
import com.amazon.music.metrics.mts.RealTimeMTSEvent;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class MTSMetricsSender implements PlaybackMetricsHandler.Sender {
    private final PlaybackConfig config;
    private final Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());
    private final MetricsManager metricsManager;

    public MTSMetricsSender(@NonNull PlaybackConfig playbackConfig, @NonNull MetricsManager metricsManager) {
        this.config = (PlaybackConfig) Validate.notNull(playbackConfig);
        this.metricsManager = (MetricsManager) Validate.notNull(metricsManager);
    }

    public void sendMetricsEvent(MTSEvent mTSEvent) {
        sendMetricsEvent(mTSEvent, false);
    }

    public void sendMetricsEvent(MTSEvent mTSEvent, boolean z) {
        String str = " - class: " + mTSEvent.getClass().getSimpleName() + ", eventName: " + mTSEvent.getEventName() + ", realTime: " + z;
        if (!StringUtils.shouldRedact()) {
            str = str + ", attributes: " + StringUtils.toString(mTSEvent.getEventAttributes());
        }
        if (this.metricsManager == null) {
            StrictMode.crashIfStrict("Cannot send metrics event because MetricsManager was not set on SessionInfo" + str);
            return;
        }
        this.logger.debug("sendMetricsEvent" + str);
        if (z) {
            this.metricsManager.handleEvent(new RealTimeMTSEvent(mTSEvent));
        } else {
            this.metricsManager.handleEvent(mTSEvent);
        }
    }

    public void sendMetricsEvent(MTSEvent mTSEvent, MetricsInfo metricsInfo) {
        sendMetricsEvent(mTSEvent);
    }

    public void sendPlaybackException(PlaybackException playbackException) {
        String name;
        int currentIndex;
        Sequencer sequencer;
        PlaybackProvider playbackProvider = Playback.getInstance().getPlaybackProvider();
        int numMediaItems = -1;
        int i = 0;
        if (!(playbackProvider instanceof SequencerPlaybackProvider) || (sequencer = ((SequencerPlaybackProvider) playbackProvider).getSequencer()) == null) {
            name = null;
            currentIndex = -1;
        } else {
            name = sequencer.getClass().getName();
            numMediaItems = sequencer.getNumMediaItems();
            currentIndex = sequencer.getCurrentIndex();
            MediaItem mediaItem = sequencer.getMediaItem(numMediaItems - 1);
            if (mediaItem != null) {
                MediaAccessInfo mediaAccessInfo = mediaItem.getMediaAccessInfo();
                i = mediaAccessInfo.isExplicit() ? 3 : 1;
                if (mediaAccessInfo.isAvailableOffline()) {
                    i += 4;
                }
                if (mediaAccessInfo.isAllowedOnline()) {
                    i += 8;
                }
                if (mediaAccessInfo.isCastable()) {
                    i += 16;
                }
            }
        }
        String mediaPlayerName = Playback.getInstance().getPlaybackController(ControlSource.NONE).getMediaPlayerName();
        String str = mediaPlayerName + " - " + name;
        float fOrdinal = playbackException.getRecoverability().ordinal();
        if (playbackException.getMediaItem() != null) {
            fOrdinal += 0.25f;
        }
        if (playbackException.canAutoRetry()) {
            fOrdinal += 0.5f;
        }
        sendMetricsEvent(FlexEvent.builder("PlaybackError").withFlexStr1(playbackException.getMetricsName()).withFlexStr2(playbackException.getClass().getSimpleName()).withFlexStr3(String.valueOf((!"PV_PLAYER".equals(mediaPlayerName) || playbackException.getMessage() == null) ? playbackException.getPlaybackError() : playbackException.getMessage())).withFlexStr4(str).withFlexNum1(Float.valueOf(fOrdinal)).withFlexNum2(Float.valueOf(numMediaItems)).withFlexNum3(Float.valueOf(currentIndex)).withFlexNum4(Float.valueOf(i)).build());
    }

    @Override // com.amazon.music.media.playback.metrics.PlaybackMetricsHandler.Sender
    public void sendStreamingInitiationFailed(MetricsInfo metricsInfo, @Nullable MediaLink mediaLink, ChangeReason changeReason, PlaybackException playbackException) {
        MediaItem mediaItem = (MediaItem) metricsInfo.get(PlaybackEvent.PROPERTY_MEDIA_ITEM);
        StringBuilder sb = new StringBuilder();
        sb.append("sendStreamingInitiationFailed(startReason: ");
        sb.append(changeReason);
        if (!StringUtils.shouldRedact()) {
            sb.append("metricsInfo: ");
            sb.append(metricsInfo);
            sb.append(", mediaItem: ");
            sb.append(mediaItem);
            sb.append(", mediaLink: ");
            sb.append(mediaLink);
        }
        sb.append(")");
        this.logger.warn(sb.toString(), (Throwable) playbackException);
        MTSEvent mTSEventCreateStreamingInitiationFailedEvent = this.config.getMTSEventFactory().createStreamingInitiationFailedEvent(metricsInfo, mediaLink, changeReason, playbackException);
        if (mTSEventCreateStreamingInitiationFailedEvent != null) {
            sendMetricsEvent(mTSEventCreateStreamingInitiationFailedEvent);
        }
    }

    @Override // com.amazon.music.media.playback.metrics.PlaybackMetricsHandler.Sender
    public void sendStreamingInitiated(MetricsInfo metricsInfo, @Nullable MediaLink mediaLink, long j, long j2, ChangeReason changeReason) {
        MediaItem mediaItem = (MediaItem) metricsInfo.get(PlaybackEvent.PROPERTY_MEDIA_ITEM);
        this.logger.debug("sendStreamingInitiated(item: " + mediaItem + ", link: " + mediaLink + ", initialStartDelay: " + j + "reason: " + changeReason + ")");
        MTSEvent mTSEventCreateStreamingInitiatedEvent = this.config.getMTSEventFactory().createStreamingInitiatedEvent(metricsInfo, mediaLink, j, j2, changeReason);
        if (mTSEventCreateStreamingInitiatedEvent != null) {
            sendMetricsEvent(mTSEventCreateStreamingInitiatedEvent);
        }
    }

    @Override // com.amazon.music.media.playback.metrics.PlaybackMetricsHandler.Sender
    public void sendStreamingTerminated(MetricsInfo metricsInfo, MediaLink mediaLink, long j, long j2, long j3, int i, long j4, float f, ChangeReason changeReason, long j5) {
        MediaItem mediaItem = (MediaItem) metricsInfo.get(PlaybackEvent.PROPERTY_MEDIA_ITEM);
        this.logger.debug("sendStreamingTerminated(item: " + mediaItem.getName() + ", reason: " + changeReason + ", initialStartDelay: " + StringUtils.getDurationString(j) + ", duration: " + StringUtils.getDurationString(j3) + ", bufferingCount: " + i + ", transferSpeed: " + j4 + ", amountCached: " + Math.round(100.0f * f) + "%, link: " + mediaLink + ")");
        MTSEvent mTSEventCreateStreamingTerminatedEvent = this.config.getMTSEventFactory().createStreamingTerminatedEvent(metricsInfo, mediaLink, j, j2, j3, i, j4, f, changeReason, (PlaybackException) null, j5);
        if (mTSEventCreateStreamingTerminatedEvent != null) {
            sendMetricsEvent(mTSEventCreateStreamingTerminatedEvent);
        }
    }

    @Override // com.amazon.music.media.playback.metrics.PlaybackMetricsHandler.Sender
    public void sendTrackStreamed(MetricsInfo metricsInfo, long j, ChangeReason changeReason) {
        MediaItem mediaItem = (MediaItem) metricsInfo.get(PlaybackEvent.PROPERTY_MEDIA_ITEM);
        this.logger.debug("sendTrackStreamed(item: " + mediaItem.getName() + ", maxPosition: " + StringUtils.getDurationString(j) + ", " + changeReason + ")");
        MTSEvent mTSEventCreateTrackStreamedEvent = this.config.getMTSEventFactory().createTrackStreamedEvent(metricsInfo, j, changeReason);
        if (mTSEventCreateTrackStreamedEvent != null) {
            sendMetricsEvent(mTSEventCreateTrackStreamedEvent);
        }
    }

    @Override // com.amazon.music.media.playback.metrics.PlaybackMetricsHandler.Sender
    public void sendStreamingRequiredRebuffering(MetricsInfo metricsInfo, MediaLink mediaLink, long j, long j2, long j3, float f) {
        MediaItem mediaItem = (MediaItem) metricsInfo.get(PlaybackEvent.PROPERTY_MEDIA_ITEM);
        this.logger.debug("sendStreamingRequiredRebuffering(item: " + mediaItem + ", mediaLink: " + mediaLink + ", bufferingDuration: " + StringUtils.getDurationString(j) + ", position: " + StringUtils.getDurationString(j2) + ", transferSpeedBytesPerSecond: " + j3 + ", amountCached: " + f + ")");
        MTSEvent mTSEventCreateStreamingRequiredRebufferingEvent = this.config.getMTSEventFactory().createStreamingRequiredRebufferingEvent(metricsInfo, mediaLink, j, j2, j3, f);
        if (mTSEventCreateStreamingRequiredRebufferingEvent != null) {
            sendMetricsEvent(mTSEventCreateStreamingRequiredRebufferingEvent);
        }
    }

    public void sendGhostListeningInterruptedEvent(MetricsInfo metricsInfo, long j, long j2) {
        MediaItem mediaItem = (MediaItem) metricsInfo.get(PlaybackEvent.PROPERTY_MEDIA_ITEM);
        Logger logger = this.logger;
        StringBuilder sb = new StringBuilder();
        sb.append("sendGhostListeningInterruptedEvent(item: ");
        sb.append(mediaItem.getName());
        sb.append(", thresholdHitSeconds: ");
        TimeUnit timeUnit = TimeUnit.SECONDS;
        sb.append(StringUtils.getDurationString(j, timeUnit));
        sb.append(", secondsSinceLastActivity: ");
        sb.append(StringUtils.getDurationString(j2, timeUnit));
        sb.append(")");
        logger.debug(sb.toString());
        MTSEvent mTSEventCreateGhostListeningInterruptedEvent = this.config.getMTSEventFactory().createGhostListeningInterruptedEvent(metricsInfo, j, j2);
        if (mTSEventCreateGhostListeningInterruptedEvent != null) {
            sendMetricsEvent(mTSEventCreateGhostListeningInterruptedEvent);
        }
    }

    public void sendSubDeviceConnectedEvent(String str, boolean z) {
        sendMetricsEvent(this.config.getMTSEventFactory().createSubDeviceConnectedEvent(str, z));
    }

    public void sendSubDeviceDisconnectedEvent(String str, boolean z) {
        sendMetricsEvent(this.config.getMTSEventFactory().createSubDeviceDisconnectedEvent(str, z));
    }
}
