package com.amazon.avod.media.ads.internal.state;

import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.media.ads.AdPlan;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext;
import com.amazon.avod.media.ads.internal.config.AdsConfig;
import com.amazon.avod.media.ads.internal.state.AsyncAdPlanRetriever;
import com.amazon.avod.media.playback.state.PlayerStateType;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggerType;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.SimpleCounterMetric;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public class WaitingForAdsState extends AdEnabledPlaybackState implements UserLaunchedPlaybackListener, AsyncAdPlanRetriever.AsyncAdPlanRetrieverListener {
    private final Object mAdPlanLock;
    private final AdsConfig mAdsConfig;
    private boolean mAlreadyNotified;
    private final String mOfferType;

    public WaitingForAdsState(@Nonnull PlayerStateType playerStateType, @Nonnull AdPlaybackStateMachineContext adPlaybackStateMachineContext, @Nonnull String str) {
        this(playerStateType, adPlaybackStateMachineContext, str, AdsConfig.getInstance());
    }

    WaitingForAdsState(PlayerStateType playerStateType, AdPlaybackStateMachineContext adPlaybackStateMachineContext, String str, AdsConfig adsConfig) {
        super(playerStateType, adPlaybackStateMachineContext);
        this.mAdPlanLock = new Object();
        this.mAlreadyNotified = false;
        this.mOfferType = (String) Preconditions.checkNotNull(str, "offerType");
        this.mAdsConfig = (AdsConfig) Preconditions.checkNotNull(adsConfig, "adsConfig");
    }

    @Override // com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackState, com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public void enter(Trigger<PlayerTriggerType> trigger) {
        getContext().setUserLaunchedPlaybackEventListener(this);
        skipAdsIfNecessary();
    }

    @Override // com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackState, com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public void exit(Trigger<PlayerTriggerType> trigger) {
        getContext().removeUserLaunchedPlaybackEventListener();
    }

    void skipAdsIfNecessary() {
        if (this.mAdsConfig.shouldSkipAdIfNotReady(this.mOfferType) && getContext().hasUserLaunchedPlayback()) {
            getContext().getPrimaryEventReporter().reportMetric("AdEvent", "AdPlanSkipped", null, null, null);
            Profiler.reportCounterMetric(new SimpleCounterMetric("AdPlanRetrieval", (ImmutableList<String>) ImmutableList.of("Metric", this.mOfferType, "Skipped")));
            DLog.logf("AdPlan retrieval not yet complete. Skipping ads playback for now.");
            doTrigger(AdEnabledPlayerTriggers.AD_PLAN_READY);
        }
    }

    @Override // com.amazon.avod.media.ads.internal.state.AsyncAdPlanRetriever.AsyncAdPlanRetrieverListener
    public void notifyRetrievalComplete(@Nonnull AdPlan adPlan) {
        Preconditions.checkNotNull(adPlan, "adPlan");
        synchronized (this.mAdPlanLock) {
            if (!getContext().getPlan().equals(adPlan)) {
                getContext().updatePlan(adPlan);
            }
            if (!this.mAlreadyNotified) {
                this.mAlreadyNotified = true;
                Profiler.reportCounterMetric(new SimpleCounterMetric("AdPlanRetrieval", (ImmutableList<String>) ImmutableList.of("Metric", this.mOfferType, "NonSkipped")));
                DLog.logf("AdPlan retrieval complete.");
                doTrigger(AdEnabledPlayerTriggers.AD_PLAN_READY);
            }
        }
    }

    @Override // com.amazon.avod.media.ads.internal.state.UserLaunchedPlaybackListener
    public void userLaunchedPlayback() {
        skipAdsIfNecessary();
    }
}
