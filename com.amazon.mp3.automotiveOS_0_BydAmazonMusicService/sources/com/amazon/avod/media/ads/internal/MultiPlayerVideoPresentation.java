package com.amazon.avod.media.ads.internal;

import com.amazon.avod.media.ads.AdEnabledVideoEventListener;
import com.amazon.avod.media.ads.AdEnabledVideoPresentation;
import com.amazon.avod.media.ads.AdPlan;
import com.amazon.avod.media.ads.internal.state.AdPlanUpdateListener;
import com.amazon.avod.media.diagnostics.DiagnosticsController;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.VideoOptions;
import com.amazon.avod.media.playback.VideoPlayer;
import com.amazon.avod.media.playback.VideoPresentation;
import com.amazon.avod.media.playback.VideoPresentationEventListener;
import com.amazon.avod.media.playback.VideoRenderingSettings;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.monitoring.TimelineMonitor;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.google.common.base.Preconditions;
import java.io.File;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public class MultiPlayerVideoPresentation implements AdEnabledVideoPresentation {
    private final AdEnabledVideoPlayer mAdPlayer;
    private final AdEnabledPlaybackManager mPlaybackManager;
    private final VideoPresentation mPrimaryPresentation;
    private final TimelineMonitor mTimelineMonitor;
    private final VideoOptions mVideoOptions;

    public MultiPlayerVideoPresentation(@Nonnull VideoPresentation videoPresentation, @Nonnull AdEnabledVideoPlayer adEnabledVideoPlayer, @Nonnull AdEnabledPlaybackManager adEnabledPlaybackManager, @Nonnull TimelineMonitor timelineMonitor, @Nonnull VideoOptions videoOptions) {
        this.mPrimaryPresentation = (VideoPresentation) Preconditions.checkNotNull(videoPresentation, "primaryPresentation");
        this.mPlaybackManager = (AdEnabledPlaybackManager) Preconditions.checkNotNull(adEnabledPlaybackManager, "manager");
        this.mAdPlayer = (AdEnabledVideoPlayer) Preconditions.checkNotNull(adEnabledVideoPlayer, "adPlayer");
        this.mTimelineMonitor = (TimelineMonitor) Preconditions.checkNotNull(timelineMonitor, "timelineMonitor");
        this.mVideoOptions = (VideoOptions) Preconditions.checkNotNull(videoOptions, "videoOptions");
    }

    @Override // com.amazon.avod.media.playback.VideoPresentation
    public void prepareAsync() throws MediaException {
        this.mPlaybackManager.initialize();
    }

    @Override // com.amazon.avod.media.playback.VideoPresentation
    public void terminate(boolean z) {
        this.mPlaybackManager.shutdown(z);
        if (z) {
            this.mTimelineMonitor.stopMonitoring(this.mPrimaryPresentation.getPlayer());
        } else {
            this.mTimelineMonitor.shutdown();
        }
    }

    @Override // com.amazon.avod.media.playback.VideoPresentation
    public void setListener(@Nonnull VideoPresentationEventListener videoPresentationEventListener) {
        this.mPlaybackManager.setPresentationEventListener(this, videoPresentationEventListener);
    }

    @Override // com.amazon.avod.media.playback.VideoPresentation
    @Nonnull
    public VideoPlayer getPlayer() {
        return this.mAdPlayer;
    }

    @Override // com.amazon.avod.media.playback.VideoPresentation
    @Nullable
    public PlaybackEventReporter getReporter() {
        return this.mPrimaryPresentation.getReporter();
    }

    @Override // com.amazon.avod.media.playback.VideoPresentation
    @Nonnull
    public VideoSpecification getSpecification() {
        return this.mPrimaryPresentation.getSpecification();
    }

    @Override // com.amazon.avod.media.playback.VideoPresentation
    @Nonnull
    public VideoOptions getVideoOptions() {
        return this.mVideoOptions;
    }

    @Override // com.amazon.avod.media.playback.VideoPresentation
    @Nullable
    public File getStoragePath() {
        return this.mPrimaryPresentation.getStoragePath();
    }

    @Override // com.amazon.avod.media.playback.VideoPresentation
    @Nonnull
    public DiagnosticsController getDiagnosticsController() {
        return this.mPrimaryPresentation.getDiagnosticsController();
    }

    @Override // com.amazon.avod.media.playback.VideoPresentation
    public void setRenderingSettings(@Nonnull VideoRenderingSettings videoRenderingSettings) {
        Preconditions.checkNotNull(videoRenderingSettings, "settings");
        this.mPlaybackManager.setRenderingSettings(videoRenderingSettings);
    }

    @Override // com.amazon.avod.media.ads.AdEnabledVideoPresentation
    @Nonnull
    public AdPlan getAdPlan() {
        return this.mPlaybackManager.getPlan();
    }

    @Override // com.amazon.avod.media.ads.AdEnabledVideoPresentation
    public boolean addAdPlanUpdateListener(@Nonnull AdPlanUpdateListener adPlanUpdateListener) {
        return this.mPlaybackManager.addAdPlanUpdateListener(adPlanUpdateListener);
    }

    @Override // com.amazon.avod.media.ads.AdEnabledVideoPresentation
    public void setAdEventListener(@Nullable AdEnabledVideoEventListener adEnabledVideoEventListener) {
        this.mPlaybackManager.setAdEventListener(adEnabledVideoEventListener);
    }
}
