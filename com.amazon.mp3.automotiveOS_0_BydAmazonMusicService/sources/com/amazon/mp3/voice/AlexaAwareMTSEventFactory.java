package com.amazon.mp3.voice;

import android.util.Log;
import androidx.annotation.Nullable;
import com.amazon.mp3.library.provider.source.nowplaying.AlexaMediaItem;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaLink;
import com.amazon.music.media.playback.PlaybackException;
import com.amazon.music.media.playback.config.PlaybackConfig;
import com.amazon.music.media.playback.metrics.MetricsInfo;
import com.amazon.music.media.playback.metrics.PlaybackEvent;
import com.amazon.music.media.playback.metrics.mts.DefaultMTSEventFactory;
import com.amazon.music.metrics.mts.MTSEvent;
import com.amazon.music.metrics.mts.event.exception.CirrusInvalidDataException;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import com.amazon.music.voice.VoiceManagerSingleton;
import com.amazon.music.voice.metrics.AlexaMetricsProvider;

/* JADX INFO: loaded from: classes4.dex */
public class AlexaAwareMTSEventFactory extends DefaultMTSEventFactory {
    private static final String TAG = "AlexaAwareMTSEventFactory";

    public AlexaAwareMTSEventFactory(final PlaybackConfig config) {
        super(config);
    }

    @Override // com.amazon.music.media.playback.metrics.mts.DefaultMTSEventFactory
    protected long getInitialPlaybackDelayMillis(final MetricsInfo metricsInfo, final MediaItem mediaItem, final long reportedIpdMillis, final long reportedStartTime) {
        AlexaMetricsProvider.AlexaMetrics alexaMetrics = getAlexaMetrics(mediaItem);
        if (alexaMetrics == null) {
            return reportedIpdMillis;
        }
        long initialPlaybackDelayMilliseconds = alexaMetrics.getInitialPlaybackDelayMilliseconds(reportedStartTime);
        Log.d(TAG, "PlayerReportedIPD is: " + reportedIpdMillis + " alexaIPD is: " + initialPlaybackDelayMilliseconds);
        return initialPlaybackDelayMilliseconds <= 0 ? reportedIpdMillis : initialPlaybackDelayMilliseconds;
    }

    @Override // com.amazon.music.media.playback.metrics.mts.DefaultMTSEventFactory, com.amazon.music.media.playback.metrics.mts.MTSEventFactory
    public MTSEvent createStreamingInitiatedEvent(MetricsInfo metricsInfo, @Nullable MediaLink mediaLink, long initialStartDelay, long initialStartTime, @Nullable ChangeReason changeReason) {
        MTSEvent mTSEventCreateStreamingInitiatedEvent = super.createStreamingInitiatedEvent(metricsInfo, mediaLink, initialStartDelay, initialStartTime, changeReason);
        setIsVoiceInitiated(mTSEventCreateStreamingInitiatedEvent, metricsInfo);
        setAlexaTtsPresent(mTSEventCreateStreamingInitiatedEvent, metricsInfo);
        return mTSEventCreateStreamingInitiatedEvent;
    }

    @Override // com.amazon.music.media.playback.metrics.mts.DefaultMTSEventFactory, com.amazon.music.media.playback.metrics.mts.MTSEventFactory
    public MTSEvent createTrackStreamedEvent(MetricsInfo metricsInfo, long maxPosition, ChangeReason changeReason) {
        return createTrackStreamedEvent(metricsInfo, maxPosition, getTerminationReason(changeReason));
    }

    @Override // com.amazon.music.media.playback.metrics.mts.DefaultMTSEventFactory, com.amazon.music.media.playback.metrics.mts.MTSEventFactory
    public MTSEvent createTrackStreamedEvent(MetricsInfo metricsInfo, long maxPosition, TerminationReason terminationReason) {
        MTSEvent mTSEventCreateTrackStreamedEvent = super.createTrackStreamedEvent(metricsInfo, maxPosition, terminationReason);
        setIsVoiceInitiated(mTSEventCreateTrackStreamedEvent, metricsInfo);
        setAlexaTtsPresent(mTSEventCreateTrackStreamedEvent, metricsInfo);
        return mTSEventCreateTrackStreamedEvent;
    }

    @Override // com.amazon.music.media.playback.metrics.mts.DefaultMTSEventFactory, com.amazon.music.media.playback.metrics.mts.MTSEventFactory
    public MTSEvent createStreamingInitiationFailedEvent(MetricsInfo metricsInfo, @Nullable MediaLink mediaLink, ChangeReason changeReason, PlaybackException error) {
        MTSEvent mTSEventCreateStreamingInitiationFailedEvent = super.createStreamingInitiationFailedEvent(metricsInfo, mediaLink, changeReason, error);
        setIsVoiceInitiated(mTSEventCreateStreamingInitiationFailedEvent, metricsInfo);
        setAlexaTtsPresent(mTSEventCreateStreamingInitiationFailedEvent, metricsInfo);
        return mTSEventCreateStreamingInitiationFailedEvent;
    }

    @Override // com.amazon.music.media.playback.metrics.mts.DefaultMTSEventFactory, com.amazon.music.media.playback.metrics.mts.MTSEventFactory
    public MTSEvent createGhostListeningInterruptedEvent(MetricsInfo metricsInfo, long thresholdHitSeconds, long secondsSinceLastActivity) {
        MTSEvent mTSEventCreateGhostListeningInterruptedEvent = super.createGhostListeningInterruptedEvent(metricsInfo, thresholdHitSeconds, secondsSinceLastActivity);
        setIsVoiceInitiated(mTSEventCreateGhostListeningInterruptedEvent, metricsInfo);
        setAlexaTtsPresent(mTSEventCreateGhostListeningInterruptedEvent, metricsInfo);
        return mTSEventCreateGhostListeningInterruptedEvent;
    }

    @Override // com.amazon.music.media.playback.metrics.mts.DefaultMTSEventFactory, com.amazon.music.media.playback.metrics.mts.MTSEventFactory
    public MTSEvent createStreamingTerminatedEvent(final MetricsInfo metricsInfo, @Nullable final MediaLink mediaLink, final long initialStartDelay, final long initialStartTime, final long duration, final int bufferingCount, final long transferSpeedBytesPerSecond, final float amountCached, TerminationReason terminationReason, final PlaybackException error, final long trackProgress) {
        MTSEvent mTSEventCreateStreamingTerminatedEvent = super.createStreamingTerminatedEvent(metricsInfo, mediaLink, initialStartDelay, initialStartTime, duration, bufferingCount, transferSpeedBytesPerSecond, amountCached, terminationReason, error, trackProgress);
        addAlexaTtsIpd(mTSEventCreateStreamingTerminatedEvent, metricsInfo);
        setIsVoiceInitiated(mTSEventCreateStreamingTerminatedEvent, metricsInfo);
        setAlexaTtsPresent(mTSEventCreateStreamingTerminatedEvent, metricsInfo);
        drainAlexaMetricsProviderCache(metricsInfo);
        return mTSEventCreateStreamingTerminatedEvent;
    }

    @Override // com.amazon.music.media.playback.metrics.mts.DefaultMTSEventFactory
    protected MTSEvent createLocalPlaybackTerminatedEvent(final MediaItem mediaItem, final MetricsInfo metricsInfo, @Nullable final MediaLink mediaLink, final long initialStartDelay, final long duration, final TerminationReason terminationReason) throws CirrusInvalidDataException {
        MTSEvent mTSEventCreateLocalPlaybackTerminatedEvent = super.createLocalPlaybackTerminatedEvent(mediaItem, metricsInfo, mediaLink, initialStartDelay, duration, terminationReason);
        addAlexaTtsIpd(mTSEventCreateLocalPlaybackTerminatedEvent, metricsInfo);
        setAlexaTtsPresent(mTSEventCreateLocalPlaybackTerminatedEvent, metricsInfo);
        drainAlexaMetricsProviderCache(metricsInfo);
        return mTSEventCreateLocalPlaybackTerminatedEvent;
    }

    @Override // com.amazon.music.media.playback.metrics.mts.DefaultMTSEventFactory, com.amazon.music.media.playback.metrics.mts.MTSEventFactory
    public MTSEvent createStreamingRequiredRebufferingEvent(final MetricsInfo metricsInfo, final MediaLink mediaLink, final long bufferingDuration, final long position, final long transferSpeedBytesPerSecond, final float amountCached) {
        MTSEvent mTSEventCreateStreamingRequiredRebufferingEvent = super.createStreamingRequiredRebufferingEvent(metricsInfo, mediaLink, bufferingDuration, position, transferSpeedBytesPerSecond, amountCached);
        addAlexaTtsIpd(mTSEventCreateStreamingRequiredRebufferingEvent, metricsInfo);
        setIsVoiceInitiated(mTSEventCreateStreamingRequiredRebufferingEvent, metricsInfo);
        setAlexaTtsPresent(mTSEventCreateStreamingRequiredRebufferingEvent, metricsInfo);
        return mTSEventCreateStreamingRequiredRebufferingEvent;
    }

    private void drainAlexaMetricsProviderCache(final MetricsInfo metricsInfo) {
        if (!VoiceManagerSingleton.isInitialized() || metricsInfo == null) {
            return;
        }
        MediaItem mediaItem = (MediaItem) metricsInfo.get(PlaybackEvent.PROPERTY_MEDIA_ITEM);
        AlexaMetricsProvider alexaMetricsProvider = VoiceManagerSingleton.getInstance().getAlexaMetricsProvider();
        if (mediaItem == null || !(mediaItem instanceof AlexaMediaItem) || alexaMetricsProvider == null) {
            return;
        }
        AlexaMediaItem alexaMediaItem = (AlexaMediaItem) mediaItem;
        alexaMetricsProvider.drainMetrics(alexaMediaItem.getStreamInfo().getStreamUrl(), alexaMediaItem.getStreamInfo().getStreamToken());
    }

    private void setIsVoiceInitiated(final MTSEvent mtsEvent, final MetricsInfo metricsInfo) {
        MediaItem mediaItem;
        if (mtsEvent == null || metricsInfo == null || (mediaItem = (MediaItem) metricsInfo.get(PlaybackEvent.PROPERTY_MEDIA_ITEM)) == null || !mediaItem.isVoiceInitiated()) {
            return;
        }
        mtsEvent.addAttribute("isVoiceInitiated", Boolean.toString(true));
    }

    private void addAlexaTtsIpd(final MTSEvent mtsEvent, final MetricsInfo metricsInfo) {
        if (mtsEvent == null || metricsInfo == null) {
            Log.e(TAG, "Null mtsEvent or metricsInfo when trying to tag on AlexaTtsIpd.");
            return;
        }
        AlexaMetricsProvider.AlexaMetrics alexaMetrics = getAlexaMetrics((MediaItem) metricsInfo.get(PlaybackEvent.PROPERTY_MEDIA_ITEM));
        if (alexaMetrics != null) {
            long j = alexaMetrics.alexaTtsIpdMilliseconds;
            if (j > 0) {
                mtsEvent.addAttribute("alexaTtsIpdMilliseconds", j);
            }
        }
    }

    private void setAlexaTtsPresent(final MTSEvent mtsEvent, final MetricsInfo metricsInfo) {
        boolean z;
        if (mtsEvent == null || metricsInfo == null) {
            Log.e(TAG, "Null mtsEvent or metricsInfo when trying to tag on AlexaTtsPresent");
            return;
        }
        AlexaMetricsProvider.AlexaMetrics alexaMetrics = getAlexaMetrics((MediaItem) metricsInfo.get(PlaybackEvent.PROPERTY_MEDIA_ITEM));
        if (alexaMetrics == null || !(z = alexaMetrics.ttsPresent)) {
            return;
        }
        mtsEvent.addAttribute("isAlexaTtsPlayed", z);
    }

    private AlexaMetricsProvider.AlexaMetrics getAlexaMetrics(final MediaItem mediaItem) {
        boolean z;
        if (mediaItem == null || !((((z = mediaItem instanceof AlexaMediaItem)) || mediaItem.isVoiceInitiated()) && VoiceManagerSingleton.isInitialized())) {
            return null;
        }
        AlexaMetricsProvider alexaMetricsProvider = VoiceManagerSingleton.getInstance().getAlexaMetricsProvider();
        if (alexaMetricsProvider == null) {
            Log.e(TAG, "Alexa metrics provider is null.");
            return null;
        }
        if (z) {
            AlexaMediaItem alexaMediaItem = (AlexaMediaItem) mediaItem;
            return alexaMetricsProvider.getMetricsByMpqsUrl(alexaMediaItem.getStreamInfo().getStreamUrl(), alexaMediaItem.getStreamInfo().getStreamToken());
        }
        return alexaMetricsProvider.getMetricsByMediaContentId(mediaItem.getMediaItemId().getId());
    }
}
