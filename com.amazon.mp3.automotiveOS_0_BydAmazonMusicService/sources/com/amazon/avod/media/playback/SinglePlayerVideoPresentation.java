package com.amazon.avod.media.playback;

import com.amazon.avod.media.diagnostics.DiagnosticsController;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.reporting.VideoPresentationEventReporter;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.qahooks.VerificationLogger;
import com.amazon.avod.util.DLog;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.Preconditions;
import java.io.File;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class SinglePlayerVideoPresentation implements VideoPresentation {
    private final DiagnosticsController mDiagController;
    protected final VideoPlayerLifecyleEventHandler mEventProxy;
    private final VideoPlayer mPlayer;
    private final VideoPresentationEventReporter mReporter;
    private final VideoSpecification mSpec;
    private final File mStoragePath;
    private final UserWatchSessionIdManager mUserWatchSessionIdManager;
    private final VerificationLogger mVerificationLogger;
    private final VideoOptions mVideoOptions;

    public SinglePlayerVideoPresentation(VideoSpecification videoSpecification, VideoOptions videoOptions, VideoPlayer videoPlayer, VideoPresentationEventReporter videoPresentationEventReporter, DiagnosticsController diagnosticsController, VideoPlayerLifecyleEventHandler videoPlayerLifecyleEventHandler, File file, VerificationLogger verificationLogger, UserWatchSessionIdManager userWatchSessionIdManager) {
        this.mSpec = (VideoSpecification) Preconditions.checkNotNull(videoSpecification, "mSpec parameter cannot be null.");
        this.mVideoOptions = (VideoOptions) Preconditions.checkNotNull(videoOptions, "mVideoOptions parameter cannot be null");
        this.mPlayer = (VideoPlayer) Preconditions.checkNotNull(videoPlayer, "mPlayer parameter cannot be null.");
        this.mReporter = (VideoPresentationEventReporter) Preconditions.checkNotNull(videoPresentationEventReporter, "mReporter parameter cannot be null.");
        this.mDiagController = (DiagnosticsController) Preconditions.checkNotNull(diagnosticsController, "mDiagController parameter cannot be null.");
        this.mVerificationLogger = (VerificationLogger) Preconditions.checkNotNull(verificationLogger, "verificationLogger parameter cannot be null.");
        this.mUserWatchSessionIdManager = (UserWatchSessionIdManager) Preconditions.checkNotNull(userWatchSessionIdManager, "userWatchSessionIdManager");
        VideoPlayerLifecyleEventHandler videoPlayerLifecyleEventHandler2 = (VideoPlayerLifecyleEventHandler) Preconditions.checkNotNull(videoPlayerLifecyleEventHandler, "mEventProxy parameter cannot be null.");
        this.mEventProxy = videoPlayerLifecyleEventHandler2;
        videoPlayerLifecyleEventHandler2.setPresentation(this);
        this.mStoragePath = file;
    }

    @Override // com.amazon.avod.media.playback.VideoPresentation
    @Nonnull
    public VideoPlayer getPlayer() {
        return this.mPlayer;
    }

    @Override // com.amazon.avod.media.playback.VideoPresentation
    public PlaybackEventReporter getReporter() {
        return this.mReporter.getPlaybackReporter();
    }

    @Override // com.amazon.avod.media.playback.VideoPresentation
    @Nonnull
    public VideoSpecification getSpecification() {
        return this.mSpec;
    }

    @Override // com.amazon.avod.media.playback.VideoPresentation
    @Nonnull
    public VideoOptions getVideoOptions() {
        return this.mVideoOptions;
    }

    @Override // com.amazon.avod.media.playback.VideoPresentation
    @Nullable
    public File getStoragePath() {
        return this.mStoragePath;
    }

    @Override // com.amazon.avod.media.playback.VideoPresentation
    @Nonnull
    public DiagnosticsController getDiagnosticsController() {
        return this.mDiagController;
    }

    @Override // com.amazon.avod.media.playback.VideoPresentation
    public void prepareAsync() throws MediaException {
        String url;
        VideoSpecification specification = getSpecification();
        if (specification.getMimeType().equals("video/aiv-asin")) {
            url = specification.getTitleId();
        } else {
            url = specification.getUrl();
        }
        this.mReporter.initialize(this, this.mUserWatchSessionIdManager.getOrCreate(url));
        this.mVerificationLogger.initialize(this);
        this.mPlayer.addListener(this.mEventProxy);
        DLog.logf("Preparing presentation for: %s", specification);
        this.mPlayer.prepareAsync(specification, this.mStoragePath);
    }

    @Override // com.amazon.avod.media.playback.VideoPresentation
    public void terminate(boolean z) {
        DLog.logf("Terminating presentation for: %s", getSpecification());
        this.mPlayer.terminate(z, null);
    }

    @Override // com.amazon.avod.media.playback.VideoPresentation
    public void setListener(@Nonnull VideoPresentationEventListener videoPresentationEventListener) {
        Preconditions.checkNotNull(videoPresentationEventListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.mEventProxy.addConcreteListener(videoPresentationEventListener);
    }

    @Override // com.amazon.avod.media.playback.VideoPresentation
    public void setRenderingSettings(@Nonnull VideoRenderingSettings videoRenderingSettings) {
        Preconditions.checkNotNull(videoRenderingSettings, "settings");
        this.mDiagController.setRenderingSettings(videoRenderingSettings);
        this.mPlayer.setRenderingSettings(videoRenderingSettings);
    }
}
