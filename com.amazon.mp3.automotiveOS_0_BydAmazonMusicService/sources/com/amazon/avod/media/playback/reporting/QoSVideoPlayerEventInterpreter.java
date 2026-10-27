package com.amazon.avod.media.playback.reporting;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.error.MediaErrorCode;
import com.amazon.avod.media.playback.PlaybackDataSource;
import com.amazon.avod.media.playback.VideoAttributes;
import com.amazon.avod.media.playback.VideoPlayer;
import com.amazon.avod.media.playback.VideoPresentation;
import com.amazon.avod.media.playback.VideoPresentationEventListener;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.playback.BufferingAnalysis;
import com.amazon.avod.playback.PlaybackBufferEventType;
import com.amazon.avod.playback.PlaybackEventContext;
import com.amazon.avod.playback.PlaybackSessionBufferEventListener;
import com.amazon.avod.playback.PlaybackStateEventListener;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.qos.metadata.DeliveryType;
import com.amazon.avod.qos.model.internal.AudioAdaptationSetSwitchContext;
import com.amazon.avod.qos.reporter.PlaybackEventReporter;
import com.google.common.base.Preconditions;
import java.util.Locale;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class QoSVideoPlayerEventInterpreter implements PlaybackStateEventListener, VideoPresentationEventListener, PlaybackSessionBufferEventListener {
    private final PlaybackEventReporter mReporter;
    private VideoSpecification mVideoSpec;

    @Override // com.amazon.avod.playback.PlaybackSessionBufferEventListener
    public void onBufferProgress(float f) {
    }

    @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
    public void onCompletion(VideoPresentation videoPresentation) {
    }

    @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
    public void onStarted(VideoPresentation videoPresentation, PlaybackDataSource playbackDataSource) {
    }

    @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
    public void onTerminated() {
    }

    public QoSVideoPlayerEventInterpreter(PlaybackEventReporter playbackEventReporter) {
        this.mReporter = playbackEventReporter;
    }

    public void initialize(VideoPresentation videoPresentation) {
        ((VideoPresentation) Preconditions.checkNotNull(videoPresentation)).setListener(this);
        VideoSpecification specification = videoPresentation.getSpecification();
        this.mVideoSpec = specification;
        VideoAttributes attributes = specification.getAttributes();
        this.mReporter.onBitrateChange(attributes.getBitrate());
        this.mReporter.onResolutionChanged(attributes.getResolution());
        this.mReporter.onMediaPlayerStart(videoPresentation.getSpecification().getStartTime(), TimeSpan.now());
    }

    @Override // com.amazon.avod.playback.PlaybackStateEventListener
    public void onStart(PlaybackEventContext playbackEventContext) {
        TimeSpan timeSpanFromMilliseconds = TimeSpan.fromMilliseconds(playbackEventContext.getPlayHeadPositionInMillis());
        this.mReporter.onInitialPlaybackIndex(timeSpanFromMilliseconds.getTotalSeconds());
        this.mReporter.onPlaybackStart(timeSpanFromMilliseconds, TimeSpan.now(), this.mVideoSpec.getReportingTag(), null, playbackEventContext.getPlayerType().name(), null, new AudioAdaptationSetSwitchContext("", 0, "", ""));
    }

    @Override // com.amazon.avod.playback.PlaybackStateEventListener
    public void onStop(PlaybackEventContext playbackEventContext) {
        TimeSpan timeSpanNow = TimeSpan.now();
        this.mReporter.onPlaybackStop(timeSpanNow, playbackEventContext.getPlayerType().name(), DrmScheme.NONE.toString(), null);
        this.mReporter.reportSession(timeSpanNow);
    }

    @Override // com.amazon.avod.playback.PlaybackStateEventListener
    public void onResume(PlaybackEventContext playbackEventContext) {
        this.mReporter.onPlay(TimeSpan.fromMilliseconds(playbackEventContext.getPlayHeadPositionInMillis()), TimeSpan.now());
    }

    @Override // com.amazon.avod.playback.PlaybackStateEventListener
    public void onPause(PlaybackEventContext playbackEventContext) {
        this.mReporter.onPause(TimeSpan.fromMilliseconds(playbackEventContext.getPlayHeadPositionInMillis()), TimeSpan.now());
    }

    @Override // com.amazon.avod.playback.PlaybackStateEventListener
    public void onSeekStart(TimeSpan timeSpan, PlaybackEventContext playbackEventContext) {
        this.mReporter.reportSeekStart(TimeSpan.fromMilliseconds(playbackEventContext.getPlayHeadPositionInMillis()), TimeSpan.now());
    }

    @Override // com.amazon.avod.playback.PlaybackStateEventListener
    public void onSeekEnd(PlaybackEventContext playbackEventContext) {
        this.mReporter.reportSeekEnd(TimeSpan.fromMilliseconds(playbackEventContext.getPlayHeadPositionInMillis()), TimeSpan.now(), null);
    }

    @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
    public void onError(VideoPresentation videoPresentation, MediaErrorCode mediaErrorCode) {
        this.mReporter.onPlaybackError(TimeSpan.now(), 3, String.format(Locale.US, "Error %s", mediaErrorCode), null, null);
    }

    @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
    public void onPrepared(VideoPresentation videoPresentation, PlaybackDataSource playbackDataSource) {
        TimeSpan timeSpanFromMilliseconds = TimeSpan.fromMilliseconds(videoPresentation.getPlayer().getDuration());
        VideoSpecification specification = videoPresentation.getSpecification();
        this.mReporter.setContentMetadata(playbackDataSource == PlaybackDataSource.DOWNLOADED ? DeliveryType.DOWNLOAD : DeliveryType.STREAMING, timeSpanFromMilliseconds);
        this.mReporter.setInitialCdnConnection(null, null, specification.getUrl(), null);
        VideoPlayer player = videoPresentation.getPlayer();
        player.addListener((PlaybackStateEventListener) this);
        player.addListener((PlaybackSessionBufferEventListener) this);
    }

    @Override // com.amazon.avod.playback.PlaybackSessionBufferEventListener
    public void onBufferStart(PlaybackBufferEventType playbackBufferEventType, PlaybackEventContext playbackEventContext, @Nullable BufferingAnalysis bufferingAnalysis) {
        this.mReporter.reportBufferStart(TimeSpan.fromMilliseconds(playbackEventContext.getPlayHeadPositionInMillis()), TimeSpan.now(), bufferingAnalysis);
    }

    @Override // com.amazon.avod.playback.PlaybackSessionBufferEventListener
    public void onBufferEnd(PlaybackBufferEventType playbackBufferEventType, PlaybackEventContext playbackEventContext) {
        this.mReporter.reportBufferEnd(TimeSpan.fromMilliseconds(playbackEventContext.getPlayHeadPositionInMillis()), TimeSpan.now());
    }
}
