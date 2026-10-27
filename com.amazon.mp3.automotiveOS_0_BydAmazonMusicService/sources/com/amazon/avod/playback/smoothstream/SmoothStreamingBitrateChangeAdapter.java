package com.amazon.avod.playback.smoothstream;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.media.playback.reporting.VideoPresentationEventReporter;
import com.amazon.avod.playback.event.AudioQualityChangeEvent;
import com.amazon.avod.playback.event.VideoQualityChangeEvent;
import com.amazon.avod.playback.event.playback.BitrateChangeEvent;
import com.amazon.avod.playback.sampling.SampleType;
import com.amazon.avod.qos.metadata.QOSEventName;
import com.google.common.base.Preconditions;
import com.google.common.eventbus.Subscribe;
import java.util.Arrays;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SmoothStreamingBitrateChangeAdapter {
    private final SmoothStreamingPlaybackConfig mConfig;
    private ContentSessionContext mContext;
    private final PlaybackEventTransport mEventTransport;
    private final VideoPresentationEventReporter mPlaybackEventReporter;

    public SmoothStreamingBitrateChangeAdapter(PlaybackEventTransport playbackEventTransport, @Nonnull VideoPresentationEventReporter videoPresentationEventReporter, @Nonnull SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig) {
        this.mEventTransport = playbackEventTransport;
        this.mPlaybackEventReporter = (VideoPresentationEventReporter) Preconditions.checkNotNull(videoPresentationEventReporter, "playbackEventReporter");
        this.mConfig = (SmoothStreamingPlaybackConfig) Preconditions.checkNotNull(smoothStreamingPlaybackConfig, "config");
    }

    public void initialize(ContentSessionContext contentSessionContext) {
        this.mContext = contentSessionContext;
        this.mEventTransport.registerEventBusHandler(this);
    }

    @Subscribe
    public void handleBitrateChange(BitrateChangeEvent bitrateChangeEvent) {
        if (bitrateChangeEvent.getSampleType() == SampleType.VIDEO_SAMPLE) {
            handleVideoBitrateChange(bitrateChangeEvent);
        } else if (bitrateChangeEvent.getSampleType() == SampleType.AUDIO_SAMPLE) {
            handleAudioBitrateChange(bitrateChangeEvent);
        }
    }

    private void handleVideoBitrateChange(BitrateChangeEvent bitrateChangeEvent) {
        com.amazon.avod.content.smoothstream.manifest.StreamIndex videoStream = this.mContext.getStreamSelections().getVideoStream();
        com.amazon.avod.content.smoothstream.manifest.VideoQualityLevel videoQualityLevel = (com.amazon.avod.content.smoothstream.manifest.VideoQualityLevel) videoStream.getQualityLevelLessThanEqual(this.mContext.getState().getConsumptionHead(videoStream.getIndex()), bitrateChangeEvent.getNewBitrate());
        VideoResolution videoResolution = new VideoResolution(videoQualityLevel.getMaxWidth(), videoQualityLevel.getMaxHeight());
        VideoResolution[] availableResolutions = new VideoResolution[1];
        if (this.mContext.isDownload()) {
            availableResolutions[0] = videoResolution;
        } else {
            availableResolutions = this.mContext.getAvailableResolutions();
        }
        if (!Arrays.asList(availableResolutions).contains(videoResolution) && this.mConfig.shouldReportInvalidResolutionSwitch()) {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format(Locale.US, "Current:%s Available:", videoResolution));
            for (VideoResolution videoResolution2 : availableResolutions) {
                sb.append(String.format(Locale.US, "%s ", videoResolution2));
            }
            String string = sb.toString();
            if (this.mPlaybackEventReporter.getPlaybackReporter() != null) {
                this.mPlaybackEventReporter.getPlaybackReporter().reportMetric(QOSEventName.Information.toString(), "InvalidCurrentResolution", null, string, null);
            }
        }
        this.mEventTransport.postEvent(new VideoQualityChangeEvent(bitrateChangeEvent.getEventTimeStamp(), videoQualityLevel.getBitrate(), videoResolution, availableResolutions));
    }

    private void handleAudioBitrateChange(BitrateChangeEvent bitrateChangeEvent) {
        com.amazon.avod.content.smoothstream.manifest.StreamIndex audioStream = this.mContext.getStreamSelections().getAudioStream();
        com.amazon.avod.content.smoothstream.manifest.AudioQualityLevel audioQualityLevel = (com.amazon.avod.content.smoothstream.manifest.AudioQualityLevel) audioStream.getQualityLevelLessThanEqual(this.mContext.getState().getConsumptionHead(audioStream.getIndex()), bitrateChangeEvent.getNewBitrate());
        this.mEventTransport.postEvent(new AudioQualityChangeEvent(bitrateChangeEvent.getEventTimeStamp(), audioQualityLevel.getBitrate(), audioQualityLevel.getAudioFormat()));
    }
}
