package com.amazon.avod.media.ads.internal.state;

import androidx.annotation.VisibleForTesting;
import com.amazon.avod.media.ads.AdPlan;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext;
import com.amazon.avod.media.ads.internal.AdTransitioner;
import com.amazon.avod.media.ads.internal.AdUriProxy;
import com.amazon.avod.media.ads.internal.EmptyAdPlan;
import com.amazon.avod.media.ads.internal.config.AdsConfig;
import com.amazon.avod.media.diagnostics.DiagnosticsController;
import com.amazon.avod.media.playback.PlaybackDataSource;
import com.amazon.avod.media.playback.VideoPlayer;
import com.amazon.avod.media.playback.VideoPlayerLifecyleEventHandler;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.playback.session.PlaybackSessionContext;
import com.amazon.avod.util.Preconditions2;
import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
class AdEnabledPlaybackSharedContext extends AdPlaybackStateMachineContext {
    private final Collection<AdPlanUpdateListener> mAdPlanUpdateListeners;
    private final AdsConfig mAdsConfig;
    private final ExecutorService mExecutorService;
    private final Object mLockUserLaunchedPlaybackLock;
    private final String mOfferType;
    private final AtomicReference<AdPlan> mPlan;
    private PlaybackDataSource mPlaybackDataSource;
    private final PlaybackSessionContext mPlaybackSessionContext;
    private final VideoPlayerLifecyleEventHandler mPresentationEventListener;
    private final VideoPlayer mPrimaryVideoPlayer;
    private boolean mUserLaunchedPlayback;
    private UserLaunchedPlaybackListener userLaunchedPlaybackListener;

    @Override // com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext
    public void updateTimeline() {
    }

    public AdEnabledPlaybackSharedContext(@Nonnull VideoPlayerLifecyleEventHandler videoPlayerLifecyleEventHandler, @Nonnull AdUriProxy adUriProxy, @Nonnull AdTransitioner adTransitioner, @Nonnull ExecutorService executorService, @Nonnull VideoPlayer videoPlayer, @Nonnull DiagnosticsController diagnosticsController, @Nonnull VideoSpecification videoSpecification, @Nonnull String str, @Nonnull PlaybackSessionContext playbackSessionContext) {
        this(videoPlayerLifecyleEventHandler, adUriProxy, adTransitioner, executorService, videoPlayer, diagnosticsController, videoSpecification, str, AdsConfig.getInstance(), playbackSessionContext);
    }

    @VisibleForTesting
    AdEnabledPlaybackSharedContext(@Nonnull VideoPlayerLifecyleEventHandler videoPlayerLifecyleEventHandler, @Nonnull AdUriProxy adUriProxy, @Nonnull AdTransitioner adTransitioner, @Nonnull ExecutorService executorService, @Nonnull VideoPlayer videoPlayer, @Nonnull DiagnosticsController diagnosticsController, @Nonnull VideoSpecification videoSpecification, @Nonnull String str, @Nonnull AdsConfig adsConfig, @Nonnull PlaybackSessionContext playbackSessionContext) {
        super(adUriProxy, adTransitioner, videoPlayer, diagnosticsController, videoSpecification, new AdBreakBufferContext());
        this.mPlan = new AtomicReference<>(EmptyAdPlan.INSTANCE);
        this.mLockUserLaunchedPlaybackLock = new Object();
        this.mAdPlanUpdateListeners = new CopyOnWriteArraySet();
        this.mPlaybackDataSource = null;
        this.mUserLaunchedPlayback = false;
        this.mPresentationEventListener = (VideoPlayerLifecyleEventHandler) Preconditions.checkNotNull(videoPlayerLifecyleEventHandler, "presentationEventListener");
        this.mExecutorService = (ExecutorService) Preconditions.checkNotNull(executorService, "executorService");
        this.mPrimaryVideoPlayer = (VideoPlayer) Preconditions.checkNotNull(videoPlayer, "primaryVideoPlayer");
        this.mOfferType = (String) Preconditions.checkNotNull(str, "offerType");
        this.mAdsConfig = (AdsConfig) Preconditions.checkNotNull(adsConfig, "adsConfig");
        this.mPlaybackSessionContext = playbackSessionContext;
    }

    @Override // com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext
    @Nonnull
    public AdPlan getPlan() {
        return this.mPlan.get();
    }

    @Override // com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext
    @Nonnull
    public PlaybackSessionContext getPlaybackSessionContext() {
        return this.mPlaybackSessionContext;
    }

    @Override // com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext
    @Nonnull
    public String getOfferType() {
        return this.mOfferType;
    }

    @Override // com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext
    @Nonnull
    public PlaybackEventReporter getPrimaryEventReporter() {
        return getStateMachine().getPrimaryPlayerEventReporter();
    }

    @Override // com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext
    @Nonnull
    public AdsConfig getAdsConfig() {
        return this.mAdsConfig;
    }

    @Override // com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext
    @Nonnull
    public VideoPlayerLifecyleEventHandler getPresentationEventListener() {
        return this.mPresentationEventListener;
    }

    @Override // com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext
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

    @Override // com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext
    public void updatePlan(@Nonnull AdPlan adPlan) {
        Preconditions.checkNotNull(adPlan, "AdPlan");
        this.mPlan.set(adPlan);
        Iterator<AdPlanUpdateListener> it = this.mAdPlanUpdateListeners.iterator();
        while (it.hasNext()) {
            it.next().onPlanUpdated(adPlan);
        }
    }

    @Override // com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext
    @Nonnull
    public PlaybackDataSource getPrimaryPresentationPlaybackStatus() {
        Preconditions2.checkStateWeakly(this.mPlaybackDataSource != null, "PlaybackInitializationInformation should have been set before accessing it");
        if (this.mPlaybackDataSource == null) {
            this.mPlaybackDataSource = PlaybackDataSource.STREAMING;
        }
        return this.mPlaybackDataSource;
    }

    @Override // com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext
    public void setPrimaryPresentationPlaybackStatus(@Nonnull PlaybackDataSource playbackDataSource) {
        this.mPlaybackDataSource = (PlaybackDataSource) Preconditions.checkNotNull(playbackDataSource, "playbackDataSource");
    }

    @Override // com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext
    public boolean hasUserLaunchedPlayback() {
        return this.mUserLaunchedPlayback;
    }

    @Override // com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext
    public void notifyUserLaunchedPlayback() {
        this.mUserLaunchedPlayback = true;
        this.mPrimaryVideoPlayer.notifyUserLaunchedPlayback();
        synchronized (this.mLockUserLaunchedPlaybackLock) {
            UserLaunchedPlaybackListener userLaunchedPlaybackListener = this.userLaunchedPlaybackListener;
            if (userLaunchedPlaybackListener != null) {
                userLaunchedPlaybackListener.userLaunchedPlayback();
            }
        }
    }

    @Override // com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext
    public void setUserLaunchedPlaybackEventListener(UserLaunchedPlaybackListener userLaunchedPlaybackListener) {
        synchronized (this.mLockUserLaunchedPlaybackLock) {
            this.userLaunchedPlaybackListener = userLaunchedPlaybackListener;
        }
    }

    @Override // com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext
    public void removeUserLaunchedPlaybackEventListener() {
        synchronized (this.mLockUserLaunchedPlaybackLock) {
            this.userLaunchedPlaybackListener = null;
        }
    }
}
