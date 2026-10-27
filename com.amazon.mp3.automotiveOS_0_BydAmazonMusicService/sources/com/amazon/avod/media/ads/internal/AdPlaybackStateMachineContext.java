package com.amazon.avod.media.ads.internal;

import com.amazon.avod.media.ads.AdBreak;
import com.amazon.avod.media.ads.AdEnabledVideoEventListener;
import com.amazon.avod.media.ads.AdPlan;
import com.amazon.avod.media.ads.AdPositionType;
import com.amazon.avod.media.ads.internal.config.AdsConfig;
import com.amazon.avod.media.ads.internal.state.AdBreakBufferContext;
import com.amazon.avod.media.ads.internal.state.AdPlanUpdateListener;
import com.amazon.avod.media.ads.internal.state.UserLaunchedPlaybackListener;
import com.amazon.avod.media.diagnostics.DiagnosticsController;
import com.amazon.avod.media.playback.PlaybackDataSource;
import com.amazon.avod.media.playback.VideoPlayer;
import com.amazon.avod.media.playback.VideoPlayerLifecyleEventHandler;
import com.amazon.avod.media.playback.VideoPlayerProxy;
import com.amazon.avod.media.playback.VideoPresentation;
import com.amazon.avod.media.playback.VideoRenderingSettings;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.playback.PlaybackZoomLevel;
import com.amazon.avod.playback.session.PlaybackSessionContext;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AdPlaybackStateMachineContext {
    private final AdBreakBufferContext mAdBreakBufferContext;
    private VideoRenderingSettings mAdEnabledRenderingSettings;
    private final AdTransitioner mAdTransitioner;
    private AdManagerBasedAdClip mCurrentAdClip;
    private AdBreak mCurrentBreak;
    private final DiagnosticsController mPrimaryDiagnosticsController;
    private final VideoPlayer mPrimaryVideoPlayer;
    private VideoRenderingSettings mRenderingSettings;
    private AdPlaybackStateMachine mStateMachine;
    private final AdUriProxy mUriProxy;
    private VideoPlayerProxy mVideoPlayerProxy;
    private VideoSpecification mVideoSpecification;
    private AdEnabledVideoEventListener mEventListener = NoopAdEnabledVideoEventListener.INSTANCE;
    private final Collection<AdPlanUpdateListener> mAdPlanUpdateListeners = new CopyOnWriteArraySet();
    private boolean mIsPrimaryContentComplete = false;
    private boolean mIsPrimaryContentStarted = false;
    private PlaybackZoomLevel mPrimaryContentZoomLevel = null;

    @Nonnull
    public abstract AdsConfig getAdsConfig();

    @Nonnull
    public abstract String getOfferType();

    @Nonnull
    public abstract AdPlan getPlan();

    @Nonnull
    public abstract PlaybackSessionContext getPlaybackSessionContext();

    @Nonnull
    @Deprecated
    public abstract VideoPlayerLifecyleEventHandler getPresentationEventListener();

    @Nonnull
    public abstract PlaybackEventReporter getPrimaryEventReporter();

    @Nonnull
    @Deprecated
    public abstract PlaybackDataSource getPrimaryPresentationPlaybackStatus();

    @Deprecated
    public abstract boolean hasUserLaunchedPlayback();

    @Deprecated
    public abstract void notifyUserLaunchedPlayback();

    @Deprecated
    public abstract void removeUserLaunchedPlaybackEventListener();

    @Deprecated
    public abstract void setPrimaryPresentationPlaybackStatus(@Nonnull PlaybackDataSource playbackDataSource);

    @Deprecated
    public abstract void setUserLaunchedPlaybackEventListener(UserLaunchedPlaybackListener userLaunchedPlaybackListener);

    @Deprecated
    public abstract void updatePlan(@Nonnull AdPlan adPlan);

    public abstract void updateTimeline();

    public AdPlaybackStateMachineContext(@Nonnull AdUriProxy adUriProxy, @Nonnull AdTransitioner adTransitioner, @Nonnull VideoPlayer videoPlayer, @Nonnull DiagnosticsController diagnosticsController, @Nonnull VideoSpecification videoSpecification, @Nonnull AdBreakBufferContext adBreakBufferContext) {
        this.mUriProxy = (AdUriProxy) Preconditions.checkNotNull(adUriProxy, "uriProxy");
        this.mAdTransitioner = (AdTransitioner) Preconditions.checkNotNull(adTransitioner, "adTransitioner");
        this.mPrimaryVideoPlayer = (VideoPlayer) Preconditions.checkNotNull(videoPlayer, "primaryVideoPlayer");
        this.mPrimaryDiagnosticsController = (DiagnosticsController) Preconditions.checkNotNull(diagnosticsController, "primaryDiagnosticsController");
        this.mVideoSpecification = (VideoSpecification) Preconditions.checkNotNull(videoSpecification, "videoSpecification");
        this.mAdBreakBufferContext = (AdBreakBufferContext) Preconditions.checkNotNull(adBreakBufferContext, "adBreakBufferContext");
    }

    public void initialize(@Nonnull AdPlaybackStateMachine adPlaybackStateMachine, @Nonnull VideoPlayerProxy videoPlayerProxy) {
        this.mStateMachine = (AdPlaybackStateMachine) Preconditions.checkNotNull(adPlaybackStateMachine, "stateMachine");
        this.mVideoPlayerProxy = (VideoPlayerProxy) Preconditions.checkNotNull(videoPlayerProxy, "videoPlayerProxy");
    }

    public AdBreakBufferContext getAdBreakBufferContext() {
        return this.mAdBreakBufferContext;
    }

    @Nonnull
    public AdPlaybackStateMachine getStateMachine() {
        return this.mStateMachine;
    }

    @Nonnull
    public AdEnabledVideoEventListener getListener() {
        AdEnabledVideoEventListener adEnabledVideoEventListener = this.mEventListener;
        return adEnabledVideoEventListener != null ? adEnabledVideoEventListener : NoopAdEnabledVideoEventListener.INSTANCE;
    }

    public void setListener(@Nullable AdEnabledVideoEventListener adEnabledVideoEventListener) {
        this.mEventListener = adEnabledVideoEventListener;
    }

    @Nullable
    public AdBreak getCurrentAdBreak() {
        return this.mCurrentBreak;
    }

    public void setCurrentAdBreak(@Nullable AdBreak adBreak) {
        if (this.mCurrentBreak != null) {
            getListener().onEndAdBreak(this.mCurrentBreak);
        }
        this.mCurrentBreak = adBreak;
        if (adBreak != null) {
            getListener().onBeginAdBreak(this.mCurrentBreak);
        }
    }

    public void setCurrentAdClip(@Nullable AdManagerBasedAdClip adManagerBasedAdClip) {
        if (this.mCurrentAdClip != null) {
            getListener().onEndAdClip(this.mCurrentAdClip);
        }
        this.mCurrentAdClip = adManagerBasedAdClip;
        if (adManagerBasedAdClip != null) {
            getListener().onBeginAdClip(this.mCurrentAdClip);
        }
    }

    @Nullable
    public AdManagerBasedAdClip getCurrentAdClip() {
        return this.mCurrentAdClip;
    }

    @Nonnull
    public VideoPlayer getPrimaryPlayer() {
        return this.mPrimaryVideoPlayer;
    }

    @Nonnull
    public VideoPlayer getVideoPlayerProxy() {
        return this.mVideoPlayerProxy;
    }

    @Nullable
    public VideoRenderingSettings getRenderingSettings() {
        return this.mRenderingSettings;
    }

    public void setRenderingSettings(VideoRenderingSettings videoRenderingSettings) {
        this.mRenderingSettings = videoRenderingSettings;
        AdManagerBasedAdClip adManagerBasedAdClip = this.mCurrentAdClip;
        if (adManagerBasedAdClip == null || this.mAdEnabledRenderingSettings != null) {
            return;
        }
        VideoPresentation presentation = adManagerBasedAdClip.getPresentation();
        if (presentation != null) {
            presentation.setRenderingSettings(this.mRenderingSettings);
        } else {
            DLog.logf("Ignoring the setRenderingSettings call as current ad clip VideoPresentation is null");
        }
    }

    public VideoRenderingSettings getAdEnabledRenderingSettings() {
        return this.mAdEnabledRenderingSettings;
    }

    public AdUriProxy getUriProxy() {
        return this.mUriProxy;
    }

    public boolean isPrimaryContentComplete() {
        return this.mIsPrimaryContentComplete;
    }

    public void setPrimaryContentComplete(boolean z) {
        this.mIsPrimaryContentComplete = z;
    }

    @Nonnull
    public AdTransitioner getAdTransitioner() {
        return this.mAdTransitioner;
    }

    public void hidePrimaryContent() {
        PlaybackZoomLevel zoomLevel = getPrimaryPlayer().getPlaybackExperienceController().getZoomLevel();
        PlaybackZoomLevel playbackZoomLevel = PlaybackZoomLevel.INVISIBLE;
        if (zoomLevel != playbackZoomLevel) {
            this.mPrimaryContentZoomLevel = zoomLevel;
            getPrimaryPlayer().getPlaybackExperienceController().setZoomLevel(playbackZoomLevel);
        }
    }

    public void showPrimaryContent() {
        if (this.mPrimaryContentZoomLevel != null) {
            getPrimaryPlayer().getPlaybackExperienceController().setZoomLevel(this.mPrimaryContentZoomLevel);
        }
    }

    public boolean isPrerollPlaying() {
        AdBreak currentAdBreak = getCurrentAdBreak();
        return currentAdBreak != null && currentAdBreak.getAdPositionType() == AdPositionType.PRE_ROLL;
    }

    public boolean shouldStoreInPersistentCache(AdPositionType adPositionType) {
        return adPositionType == AdPositionType.PRE_ROLL && (!getPlan().isDraper() ? !getAdsConfig().isPersistentCacheForAvodPreRollsEnabled() : !getAdsConfig().isPersistentCacheForDraperPreRollsEnabled());
    }

    public boolean addAdPlanUpdateListener(AdPlanUpdateListener adPlanUpdateListener) {
        if (!this.mAdPlanUpdateListeners.add(adPlanUpdateListener)) {
            return false;
        }
        if (getPlan() == EmptyAdPlan.INSTANCE) {
            return true;
        }
        adPlanUpdateListener.onPlanUpdated(getPlan());
        return true;
    }

    @Nonnull
    public DiagnosticsController getPrimaryDiagnosticsController() {
        return this.mPrimaryDiagnosticsController;
    }

    @Nonnull
    public VideoSpecification getVideoSpecification() {
        return this.mVideoSpecification;
    }

    public void setVideoSpecification(@Nonnull VideoSpecification videoSpecification) {
        this.mVideoSpecification = (VideoSpecification) Preconditions.checkNotNull(videoSpecification, "videoSpecification cannot be null");
    }

    public boolean isPrimaryContentStarted() {
        return this.mIsPrimaryContentStarted;
    }

    public void setPrimaryContentStarted(boolean z) {
        this.mIsPrimaryContentStarted = z;
    }
}
