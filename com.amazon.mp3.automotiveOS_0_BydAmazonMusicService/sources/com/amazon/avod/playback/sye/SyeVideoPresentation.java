package com.amazon.avod.playback.sye;

import com.amazon.avod.media.diagnostics.DiagnosticsController;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.VideoOptions;
import com.amazon.avod.media.playback.VideoPlayer;
import com.amazon.avod.media.playback.VideoPlayerLifecyleEventHandler;
import com.amazon.avod.media.playback.VideoPresentation;
import com.amazon.avod.media.playback.VideoPresentationEventListener;
import com.amazon.avod.media.playback.VideoRenderingSettings;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.google.common.base.Preconditions;
import java.io.File;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
class SyeVideoPresentation implements VideoPresentation {
    private final DiagnosticsController mDiagnosticsController;
    private final VideoPlayerLifecyleEventHandler mEventProxy;
    private final ScheduledExecutorService mExecutor;
    private final PlaybackEventReporter mReporter;
    private final VideoOptions mVideoOptions;
    private final VideoPlayer mVideoPlayer;
    private final VideoSpecification mVideoSpecification;

    @Override // com.amazon.avod.media.playback.VideoPresentation
    @Nullable
    public File getStoragePath() {
        return null;
    }

    SyeVideoPresentation(@Nonnull VideoSpecification videoSpecification, @Nonnull VideoOptions videoOptions, @Nonnull VideoPlayer videoPlayer, @Nonnull PlaybackEventReporter playbackEventReporter, @Nonnull VideoPlayerLifecyleEventHandler videoPlayerLifecyleEventHandler, @Nonnull DiagnosticsController diagnosticsController, @Nonnull ScheduledExecutorService scheduledExecutorService) {
        this.mVideoSpecification = (VideoSpecification) Preconditions.checkNotNull(videoSpecification, "videoSpecification");
        this.mVideoOptions = (VideoOptions) Preconditions.checkNotNull(videoOptions, "videoOptions");
        this.mVideoPlayer = (VideoPlayer) Preconditions.checkNotNull(videoPlayer, "videoPlayer");
        this.mReporter = (PlaybackEventReporter) Preconditions.checkNotNull(playbackEventReporter, "reporter");
        this.mEventProxy = (VideoPlayerLifecyleEventHandler) Preconditions.checkNotNull(videoPlayerLifecyleEventHandler, "eventProxy");
        this.mDiagnosticsController = (DiagnosticsController) Preconditions.checkNotNull(diagnosticsController, "diagnosticsController");
        this.mExecutor = (ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService, "scheduledExecutorService");
    }

    @Override // com.amazon.avod.media.playback.VideoPresentation
    @Nonnull
    public VideoOptions getVideoOptions() {
        return this.mVideoOptions;
    }

    @Override // com.amazon.avod.media.playback.VideoPresentation
    @Nonnull
    public VideoPlayer getPlayer() {
        return this.mVideoPlayer;
    }

    @Override // com.amazon.avod.media.playback.VideoPresentation
    @Nullable
    public PlaybackEventReporter getReporter() {
        return this.mReporter;
    }

    @Override // com.amazon.avod.media.playback.VideoPresentation
    @Nonnull
    public VideoSpecification getSpecification() {
        return this.mVideoSpecification;
    }

    @Override // com.amazon.avod.media.playback.VideoPresentation
    @Nonnull
    public DiagnosticsController getDiagnosticsController() {
        return this.mDiagnosticsController;
    }

    @Override // com.amazon.avod.media.playback.VideoPresentation
    public void prepareAsync() throws MediaException {
        this.mVideoPlayer.prepareAsync(this.mVideoSpecification, null);
    }

    @Override // com.amazon.avod.media.playback.VideoPresentation
    public void terminate(boolean z) {
        this.mExecutor.shutdown();
        this.mVideoPlayer.terminate(false, null);
    }

    @Override // com.amazon.avod.media.playback.VideoPresentation
    public void setListener(@Nonnull VideoPresentationEventListener videoPresentationEventListener) {
        this.mEventProxy.addConcreteListener(videoPresentationEventListener);
    }

    @Override // com.amazon.avod.media.playback.VideoPresentation
    public void setRenderingSettings(@Nonnull VideoRenderingSettings videoRenderingSettings) {
        this.mVideoPlayer.setRenderingSettings(videoRenderingSettings);
        this.mDiagnosticsController.setRenderingSettings(videoRenderingSettings);
    }
}
