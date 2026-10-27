package com.amazon.avod.playback.player;

import com.amazon.avod.content.ContentSession;
import com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestCapturerInterface;
import com.amazon.avod.drm.DecryptionContextFactory;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.media.AudioFormat;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.profiling.MediaProfiler;
import com.amazon.avod.media.playback.VideoRenderingSettings;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusInteractionReporterInterface;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusPlaybackReporterInterface;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusPlayerHeuristicReporterInterface;
import com.amazon.avod.media.playback.support.RendererCapabilities;
import com.amazon.avod.media.playback.support.RendererPerformanceData;
import com.amazon.avod.media.playback.support.RendererScheme;
import com.amazon.avod.media.playback.support.RendererSchemeController;
import com.amazon.avod.media.playback.support.VideoRenderer;
import com.amazon.avod.playback.AVSyncDebugReporter;
import com.amazon.avod.playback.LifecycleProfiler;
import com.amazon.avod.playback.PlaybackException;
import com.amazon.avod.playback.PlaybackZoomLevel;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playback.player.actions.LaunchAction;
import com.amazon.avod.playback.player.actions.LoadAction;
import com.amazon.avod.playback.player.actions.PauseAction;
import com.amazon.avod.playback.player.actions.PlayAction;
import com.amazon.avod.playback.player.actions.SeekAction;
import com.amazon.avod.playback.player.states.PlaybackState;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.amazon.avod.playback.subtitles.SubtitlesEngine;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class VideoPlaybackEngine {
    private final boolean mIsOutputDisplayChangeSupported;
    private final VideoRenderer mRenderer;
    private final RendererScheme mRendererScheme;
    private final PlaybackStateMachine mStateMachine;
    private SubtitlesEngine mSubtitlesEngine;
    private final VideoPlaybackTimeline mTimeline;

    public VideoPlaybackEngine(PlaybackEventTransport playbackEventTransport, DecryptionContextFactory decryptionContextFactory, PlaybackConfig playbackConfig, VideoRenderer videoRenderer, MediaProfiler mediaProfiler, RendererScheme rendererScheme, AloysiusPlaybackReporterInterface aloysiusPlaybackReporterInterface, @Nonnull AloysiusInteractionReporterInterface aloysiusInteractionReporterInterface, @Nonnull AloysiusPlayerHeuristicReporterInterface aloysiusPlayerHeuristicReporterInterface, @Nonnull ManifestCapturerInterface manifestCapturerInterface, @Nonnull RendererSchemeController rendererSchemeController, @Nonnull AVSyncDebugReporter aVSyncDebugReporter) {
        this(playbackEventTransport, decryptionContextFactory, playbackConfig, videoRenderer, mediaProfiler, new VideoPlaybackTimeline(videoRenderer), rendererScheme, aloysiusPlaybackReporterInterface, aloysiusInteractionReporterInterface, aloysiusPlayerHeuristicReporterInterface, manifestCapturerInterface, rendererSchemeController, aVSyncDebugReporter);
    }

    VideoPlaybackEngine(PlaybackEventTransport playbackEventTransport, DecryptionContextFactory decryptionContextFactory, PlaybackConfig playbackConfig, VideoRenderer videoRenderer, MediaProfiler mediaProfiler, VideoPlaybackTimeline videoPlaybackTimeline, RendererScheme rendererScheme, AloysiusPlaybackReporterInterface aloysiusPlaybackReporterInterface, @Nonnull AloysiusInteractionReporterInterface aloysiusInteractionReporterInterface, @Nonnull AloysiusPlayerHeuristicReporterInterface aloysiusPlayerHeuristicReporterInterface, @Nonnull ManifestCapturerInterface manifestCapturerInterface, @Nonnull RendererSchemeController rendererSchemeController, @Nonnull AVSyncDebugReporter aVSyncDebugReporter) {
        this(new PlaybackStateMachine(playbackEventTransport, decryptionContextFactory, playbackConfig, videoRenderer, videoPlaybackTimeline, mediaProfiler, rendererScheme, aloysiusPlaybackReporterInterface, aloysiusInteractionReporterInterface, aloysiusPlayerHeuristicReporterInterface, manifestCapturerInterface, rendererSchemeController, aVSyncDebugReporter), videoRenderer, videoPlaybackTimeline, rendererScheme, playbackConfig.isOutputDisplayChangeSupported());
    }

    VideoPlaybackEngine(@Nonnull PlaybackStateMachine playbackStateMachine, @Nonnull VideoRenderer videoRenderer, @Nonnull VideoPlaybackTimeline videoPlaybackTimeline, @Nonnull RendererScheme rendererScheme, boolean z) {
        this.mStateMachine = (PlaybackStateMachine) Preconditions.checkNotNull(playbackStateMachine, "stateMachine");
        this.mRenderer = (VideoRenderer) Preconditions.checkNotNull(videoRenderer, "renderer");
        this.mTimeline = (VideoPlaybackTimeline) Preconditions.checkNotNull(videoPlaybackTimeline, "timeline");
        this.mRendererScheme = (RendererScheme) Preconditions.checkNotNull(rendererScheme, "rendererScheme");
        this.mIsOutputDisplayChangeSupported = z;
    }

    public void initialize(AudioFormat audioFormat) {
        this.mStateMachine.startProcessing(audioFormat);
    }

    public void prepareContent(VideoSpecification videoSpecification, ContentSession contentSession) {
        this.mStateMachine.enqueueAction(new LoadAction(videoSpecification, contentSession));
    }

    public void launchPlayback(VideoRenderingSettings videoRenderingSettings, @Nullable String str, @Nonnull DrmScheme drmScheme, @Nonnull RendererSchemeType rendererSchemeType, boolean z) {
        this.mStateMachine.enqueueAction(new LaunchAction(videoRenderingSettings, str, drmScheme, rendererSchemeType, z));
    }

    public void pause() {
        this.mStateMachine.enqueueAction(new PauseAction());
    }

    public void resume() {
        this.mStateMachine.enqueueAction(new PlayAction(getPlaybackState()));
    }

    public void seekTo(@Nonnull TimeSpan timeSpan, @Nonnull SeekAction.SeekCause seekCause) {
        this.mStateMachine.enqueueAction(new SeekAction(timeSpan, this.mStateMachine.getPlaybackState().getState(), seekCause));
    }

    public void close(boolean z, boolean z2) {
        this.mStateMachine.shutdown(z);
        if (z2) {
            this.mSubtitlesEngine.shutdown();
        }
    }

    public long getCurrentPositionInNanos() {
        return this.mTimeline.getCurrentPlayTimeInNanos();
    }

    public PlaybackState getPlaybackState() {
        return this.mStateMachine.getPlaybackState().getState();
    }

    public void setZoomLevel(PlaybackZoomLevel playbackZoomLevel, boolean z) throws PlaybackException {
        PlaybackState playbackState = getPlaybackState();
        if (playbackState == PlaybackState.Playing || playbackState == PlaybackState.Buffering || playbackState == PlaybackState.Seeking || playbackState == PlaybackState.Paused) {
            this.mRenderer.setZoomLevel(playbackZoomLevel, z);
        }
    }

    public void setSubtitlesEngine(@Nonnull SubtitlesEngine subtitlesEngine) {
        this.mSubtitlesEngine = (SubtitlesEngine) Preconditions.checkNotNull(subtitlesEngine, "subtitlesEngine");
        this.mStateMachine.getStateFactory().getStateContext().setSubtitlesEngine(subtitlesEngine);
    }

    @Nonnull
    public SubtitlesEngine getSubtitlesEngine() {
        Preconditions.checkState(this.mSubtitlesEngine != null, "Invoke setSubtitlesEngine() first!");
        return this.mSubtitlesEngine;
    }

    public void setLifecycleProfiler(@Nonnull LifecycleProfiler lifecycleProfiler) {
        Preconditions.checkNotNull(lifecycleProfiler, "lifecycleProfiler");
        this.mStateMachine.getStateFactory().getStateContext().setLifecycleProfiler(lifecycleProfiler);
    }

    public void setPlaybackSpeed(float f) {
        this.mRenderer.setPlaybackSpeed(f);
    }

    public void changeOutputDisplay(@Nonnull VideoRenderingSettings videoRenderingSettings) throws PlaybackException {
        PlaybackState playbackState = getPlaybackState();
        boolean z = this.mIsOutputDisplayChangeSupported;
        if (z && (playbackState == PlaybackState.Playing || playbackState == PlaybackState.Paused || playbackState == PlaybackState.Buffering || playbackState == PlaybackState.Seeking)) {
            this.mRenderer.configureOutputDisplay(videoRenderingSettings);
        } else {
            DLog.warnf("VideoPlaybackEngine: Ignoring changeOutputDisplay call, supported: %s playbackState: %s", Boolean.valueOf(z), playbackState);
        }
    }

    public void setVolume(float f) {
        Preconditions.checkArgument(f >= 0.0f, "volume cannot be negative.");
        this.mRenderer.setVolume(f);
    }

    @Nonnull
    public RendererPerformanceData getRendererPerformanceData() {
        return this.mRenderer.getRendererPerformanceData();
    }

    @Nonnull
    public RendererCapabilities getRendererCapabilities() {
        return this.mRenderer.getRendererCapabilities();
    }
}
