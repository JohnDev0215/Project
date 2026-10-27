package com.amazon.avod.media.ads.internal.adplaybackstatemachine;

import com.amazon.avod.media.ads.AdPlan;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext;
import com.amazon.avod.media.ads.internal.AdTransitioner;
import com.amazon.avod.media.ads.internal.AdUriProxy;
import com.amazon.avod.media.ads.internal.EmptyAdPlan;
import com.amazon.avod.media.ads.internal.config.AdsConfig;
import com.amazon.avod.media.ads.internal.state.AdBreakBufferContext;
import com.amazon.avod.media.ads.internal.state.AdPlanUpdateListener;
import com.amazon.avod.media.ads.internal.state.UserLaunchedPlaybackListener;
import com.amazon.avod.media.diagnostics.DiagnosticsController;
import com.amazon.avod.media.playback.PlaybackDataSource;
import com.amazon.avod.media.playback.VideoPlayer;
import com.amazon.avod.media.playback.VideoPlayerLifecyleEventHandler;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.reporting.VideoPresentationEventReporter;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.playback.session.PlaybackSessionContext;
import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class AdEnabledPlaybackSharedContext extends AdPlaybackStateMachineContext {
    private final Collection<AdPlanUpdateListener> mAdPlanUpdateListeners;
    private final AdsConfig mAdsConfig;
    private final PlaybackSessionContext mPlaybackSessionContext;
    private final VideoPresentationEventReporter mVideoPresentationEventReporter;

    public AdEnabledPlaybackSharedContext(@Nonnull AdUriProxy adUriProxy, @Nonnull AdTransitioner adTransitioner, @Nonnull VideoPlayer videoPlayer, @Nonnull DiagnosticsController diagnosticsController, @Nonnull VideoSpecification videoSpecification, @Nonnull VideoPresentationEventReporter videoPresentationEventReporter, @Nonnull PlaybackSessionContext playbackSessionContext, @Nonnull AdsConfig adsConfig) {
        super(adUriProxy, adTransitioner, videoPlayer, diagnosticsController, videoSpecification, new AdBreakBufferContext());
        this.mAdPlanUpdateListeners = new CopyOnWriteArraySet();
        this.mVideoPresentationEventReporter = (VideoPresentationEventReporter) Preconditions.checkNotNull(videoPresentationEventReporter, "videoPresentationEventReporter");
        this.mPlaybackSessionContext = (PlaybackSessionContext) Preconditions.checkNotNull(playbackSessionContext, "playbackSessionContext");
        this.mAdsConfig = (AdsConfig) Preconditions.checkNotNull(adsConfig, "adsConfig");
    }

    @Override // com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext
    @Nonnull
    public AdPlan getPlan() {
        return this.mPlaybackSessionContext.getAdPlan();
    }

    @Override // com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext
    @Nonnull
    public PlaybackSessionContext getPlaybackSessionContext() {
        return this.mPlaybackSessionContext;
    }

    @Override // com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext
    @Nonnull
    public String getOfferType() {
        String offerType = this.mPlaybackSessionContext.getVideoOptions().getOfferType();
        return offerType != null ? offerType : "NOT_PROVIDED";
    }

    @Override // com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext
    @Nonnull
    public PlaybackEventReporter getPrimaryEventReporter() {
        return this.mVideoPresentationEventReporter.getPlaybackReporter();
    }

    @Override // com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext
    @Nonnull
    public AdsConfig getAdsConfig() {
        return this.mAdsConfig;
    }

    @Override // com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext
    @Nonnull
    @Deprecated
    public VideoPlayerLifecyleEventHandler getPresentationEventListener() {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext
    @Nonnull
    @Deprecated
    public PlaybackDataSource getPrimaryPresentationPlaybackStatus() {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext
    @Deprecated
    public void setPrimaryPresentationPlaybackStatus(@Nonnull PlaybackDataSource playbackDataSource) {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext
    @Deprecated
    public boolean hasUserLaunchedPlayback() {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext
    @Deprecated
    public void notifyUserLaunchedPlayback() {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext
    @Deprecated
    public void setUserLaunchedPlaybackEventListener(UserLaunchedPlaybackListener userLaunchedPlaybackListener) {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext
    @Deprecated
    public void removeUserLaunchedPlaybackEventListener() {
        throw new UnsupportedOperationException();
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
        this.mPlaybackSessionContext.setAdPlan(adPlan);
        Iterator<AdPlanUpdateListener> it = this.mAdPlanUpdateListeners.iterator();
        while (it.hasNext()) {
            it.next().onPlanUpdated(adPlan);
        }
    }

    @Override // com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext
    public void updateTimeline() {
        Iterator<AdPlanUpdateListener> it = this.mAdPlanUpdateListeners.iterator();
        while (it.hasNext()) {
            it.next().onTimelineUpdated();
        }
    }
}
