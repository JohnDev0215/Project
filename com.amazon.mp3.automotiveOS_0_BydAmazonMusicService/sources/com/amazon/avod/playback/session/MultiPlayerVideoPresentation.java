package com.amazon.avod.playback.session;

import com.amazon.avod.media.ads.AdEnabledVideoEventListener;
import com.amazon.avod.media.ads.AdEnabledVideoPresentation;
import com.amazon.avod.media.ads.AdPlan;
import com.amazon.avod.media.ads.internal.AdEnabledPlaybackManager;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachine;
import com.amazon.avod.media.ads.internal.state.AdPlanUpdateListener;
import com.amazon.avod.media.diagnostics.DiagnosticsController;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.UserWatchSessionIdManager;
import com.amazon.avod.media.playback.VideoOptions;
import com.amazon.avod.media.playback.VideoPlayer;
import com.amazon.avod.media.playback.VideoPlayerLifecyleEventHandler;
import com.amazon.avod.media.playback.VideoPlayerProxy;
import com.amazon.avod.media.playback.VideoPresentationEventListener;
import com.amazon.avod.media.playback.VideoRenderingSettings;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.reporting.VideoPresentationEventReporter;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.qahooks.VerificationLogger;
import com.amazon.avod.util.DLog;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.Preconditions;
import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class MultiPlayerVideoPresentation implements AdEnabledVideoPresentation {
    private final AdEnabledPlaybackManager mAdEnabledPlaybackManager;
    private final DiagnosticsController mDiagController;
    private final AtomicBoolean mIsTerminated = new AtomicBoolean(false);
    private final PlaybackSession mPlaybackSession;
    private final VideoPlayer mPrimaryVideoPlayer;
    private final File mStoragePath;
    private final UserWatchSessionIdManager mUserWatchSessionIdManager;
    private final VerificationLogger mVerificationLogger;
    private final VideoOptions mVideoOptions;
    private final VideoPlayerLifecyleEventHandler mVideoPlayerLifecyleEventHandler;
    private final VideoPlayerProxy mVideoPlayerProxy;
    private final VideoPresentationEventReporter mVideoPresentationEventReporter;
    private final VideoSpecification mVideoSpecification;

    public MultiPlayerVideoPresentation(@Nonnull PlaybackSession playbackSession, @Nonnull VideoSpecification videoSpecification, @Nonnull VideoPlayer videoPlayer, @Nonnull VideoPresentationEventReporter videoPresentationEventReporter, @Nonnull DiagnosticsController diagnosticsController, @Nonnull VideoPlayerLifecyleEventHandler videoPlayerLifecyleEventHandler, @Nonnull VerificationLogger verificationLogger, @Nullable File file, @Nonnull VideoPlayerProxy videoPlayerProxy, @Nonnull AdEnabledPlaybackManager adEnabledPlaybackManager, @Nonnull VideoOptions videoOptions, @Nonnull UserWatchSessionIdManager userWatchSessionIdManager) {
        this.mPlaybackSession = (PlaybackSession) Preconditions.checkNotNull(playbackSession, "playbackSession");
        this.mVideoSpecification = (VideoSpecification) Preconditions.checkNotNull(videoSpecification, "videoSpecification");
        this.mPrimaryVideoPlayer = (VideoPlayer) Preconditions.checkNotNull(videoPlayer, "primaryVideoPlayer");
        this.mVideoPresentationEventReporter = (VideoPresentationEventReporter) Preconditions.checkNotNull(videoPresentationEventReporter, "videoPresentationEventReporter");
        this.mDiagController = (DiagnosticsController) Preconditions.checkNotNull(diagnosticsController, "diagnosticsController");
        VideoPlayerLifecyleEventHandler videoPlayerLifecyleEventHandler2 = (VideoPlayerLifecyleEventHandler) Preconditions.checkNotNull(videoPlayerLifecyleEventHandler, "videoPlayerLifecyleEventHandler");
        this.mVideoPlayerLifecyleEventHandler = videoPlayerLifecyleEventHandler2;
        this.mVerificationLogger = (VerificationLogger) Preconditions.checkNotNull(verificationLogger, "verificationLogger");
        this.mStoragePath = file;
        this.mVideoPlayerProxy = (VideoPlayerProxy) Preconditions.checkNotNull(videoPlayerProxy, "videoPlayerProxy");
        this.mAdEnabledPlaybackManager = (AdEnabledPlaybackManager) Preconditions.checkNotNull(adEnabledPlaybackManager, "manager");
        this.mVideoOptions = (VideoOptions) Preconditions.checkNotNull(videoOptions, "videoOptions");
        this.mUserWatchSessionIdManager = (UserWatchSessionIdManager) Preconditions.checkNotNull(userWatchSessionIdManager, "userWatchSessionIdManager");
        videoPlayerLifecyleEventHandler2.setPresentation(this);
    }

    @Override // com.amazon.avod.media.ads.AdEnabledVideoPresentation
    @Nonnull
    public AdPlan getAdPlan() {
        return this.mPlaybackSession.getContext().getAdPlan();
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

    @Override // com.amazon.avod.media.ads.AdEnabledVideoPresentation
    public boolean addAdPlanUpdateListener(@Nonnull AdPlanUpdateListener adPlanUpdateListener) {
        Preconditions.checkNotNull(adPlanUpdateListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        return this.mAdEnabledPlaybackManager.addAdPlanUpdateListener(adPlanUpdateListener);
    }

    @Override // com.amazon.avod.media.ads.AdEnabledVideoPresentation
    public void setAdEventListener(@Nullable AdEnabledVideoEventListener adEnabledVideoEventListener) {
        this.mAdEnabledPlaybackManager.setAdEventListener(adEnabledVideoEventListener);
    }

    @Override // com.amazon.avod.media.playback.VideoPresentation
    @Nonnull
    public VideoPlayer getPlayer() {
        return this.mVideoPlayerProxy;
    }

    @Override // com.amazon.avod.media.playback.VideoPresentation
    @Nullable
    public PlaybackEventReporter getReporter() {
        return this.mVideoPresentationEventReporter.getPlaybackReporter();
    }

    @Override // com.amazon.avod.media.playback.VideoPresentation
    @Nonnull
    public VideoSpecification getSpecification() {
        return this.mVideoSpecification;
    }

    @Override // com.amazon.avod.media.playback.VideoPresentation
    @Nonnull
    public DiagnosticsController getDiagnosticsController() {
        return this.mDiagController;
    }

    @Override // com.amazon.avod.media.playback.VideoPresentation
    public void prepareAsync() throws MediaException {
        if (this.mIsTerminated.get()) {
            DLog.warnf("Ignoring prepareAsync call for %s", this);
            return;
        }
        DLog.logf("MultiPlayerVideoPresenation - calling prepareAsync on DAG path.");
        this.mVideoPresentationEventReporter.initialize(this, this.mUserWatchSessionIdManager.getOrCreate(this.mVideoSpecification.getTitleId()));
        this.mVerificationLogger.initialize(this);
        this.mPrimaryVideoPlayer.addListener(this.mVideoPlayerLifecyleEventHandler);
        this.mPlaybackSession.prepare(this.mVideoSpecification, this.mStoragePath, null, null);
        this.mPrimaryVideoPlayer.prepareAsync(this.mVideoSpecification, this.mStoragePath);
        setListener((AdPlaybackStateMachine) this.mAdEnabledPlaybackManager);
        this.mAdEnabledPlaybackManager.initialize();
    }

    @Override // com.amazon.avod.media.playback.VideoPresentation
    public void terminate(boolean z) {
        if (!this.mIsTerminated.compareAndSet(false, true)) {
            DLog.warnf("Ignoring rogue terminate call for %s", this);
            return;
        }
        this.mPlaybackSession.terminate(z, null);
        this.mPrimaryVideoPlayer.terminate(z, null);
        this.mAdEnabledPlaybackManager.shutdown(z);
        if (z) {
            this.mPlaybackSession.getResources().getTimelineMonitor().stopMonitoring(this.mPrimaryVideoPlayer);
        } else {
            this.mPlaybackSession.getResources().getTimelineMonitor().shutdown();
        }
    }

    @Override // com.amazon.avod.media.playback.VideoPresentation
    @Deprecated
    public void setListener(@Nonnull VideoPresentationEventListener videoPresentationEventListener) {
        this.mVideoPlayerLifecyleEventHandler.addConcreteListener((VideoPresentationEventListener) Preconditions.checkNotNull(videoPresentationEventListener, "videoPresentationEventListener"));
    }

    @Override // com.amazon.avod.media.playback.VideoPresentation
    public void setRenderingSettings(@Nonnull VideoRenderingSettings videoRenderingSettings) {
        if (this.mIsTerminated.get()) {
            DLog.warnf("Ignoring setRenderingSettings call for %s", this);
            return;
        }
        Preconditions.checkNotNull(videoRenderingSettings, "settings");
        this.mPlaybackSession.start(videoRenderingSettings);
        this.mDiagController.setRenderingSettings(videoRenderingSettings);
        this.mPrimaryVideoPlayer.setRenderingSettings(videoRenderingSettings);
        this.mAdEnabledPlaybackManager.setRenderingSettings(videoRenderingSettings);
    }
}
