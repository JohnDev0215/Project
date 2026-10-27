package com.amazon.mp3.voice;

import com.amazon.mp3.cloudqueue.sequencer.PlaybackActionCapabilityQueryable;
import com.amazon.mp3.library.provider.source.nowplaying.AlexaMediaItem;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.config.PlaybackConfig;
import com.amazon.music.media.playback.metrics.MetricsInfo;
import com.amazon.music.media.playback.metrics.PlaybackEvent;
import com.amazon.music.media.playback.metrics.mts.MTSMetricsSender;
import com.amazon.music.metrics.MetricsManager;
import com.amazon.music.metrics.mts.MTSEvent;
import com.amazon.music.metrics.mts.event.definition.playback.StreamingInitiatedEvent;
import com.amazon.music.metrics.mts.event.definition.playback.StreamingTerminatedEvent;

/* JADX INFO: loaded from: classes4.dex */
public class AlexaAwareMTSMetricsSender extends MTSMetricsSender {
    private final AlexaStreamingTerminatedEventHandler mAlexaStreamingTerminatedEventHandler;
    private MTSMetricsSender mDefaultSender;

    public AlexaAwareMTSMetricsSender(final PlaybackConfig config, final MetricsManager metricsManager, final MTSMetricsSender sender) {
        super(config, metricsManager);
        this.mAlexaStreamingTerminatedEventHandler = new AlexaStreamingTerminatedEventHandler(this);
        this.mDefaultSender = sender;
    }

    @Override // com.amazon.music.media.playback.metrics.mts.MTSMetricsSender
    public void sendMetricsEvent(final MTSEvent event, final MetricsInfo metricsInfo) {
        if (metricsInfo != null) {
            MediaItem mediaItem = (MediaItem) metricsInfo.get(PlaybackEvent.PROPERTY_MEDIA_ITEM);
            if (mediaItem instanceof AlexaMediaItem) {
                if (event instanceof StreamingTerminatedEvent) {
                    this.mAlexaStreamingTerminatedEventHandler.submit((StreamingTerminatedEvent) event);
                    return;
                } else if (event instanceof StreamingInitiatedEvent) {
                    this.mAlexaStreamingTerminatedEventHandler.updateTerminationReason((AlexaMediaItem) mediaItem);
                }
            }
        }
        this.mDefaultSender.sendMetricsEvent(event, metricsInfo != null && (metricsInfo.get(PlaybackEvent.PROPERTY_MEDIA_ITEM) instanceof PlaybackActionCapabilityQueryable) && (event instanceof StreamingTerminatedEvent));
    }
}
