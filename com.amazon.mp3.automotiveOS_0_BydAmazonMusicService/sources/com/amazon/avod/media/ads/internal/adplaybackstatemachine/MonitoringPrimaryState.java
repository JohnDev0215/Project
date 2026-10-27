package com.amazon.avod.media.ads.internal.adplaybackstatemachine;

import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.ads.AdBreak;
import com.amazon.avod.media.ads.AdPositionType;
import com.amazon.avod.media.ads.internal.AdBreakSelector;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext;
import com.amazon.avod.media.ads.internal.LiveAdTrackingManager;
import com.amazon.avod.media.ads.internal.state.AdBreakTrigger;
import com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackState;
import com.amazon.avod.media.ads.internal.state.AdEnabledPlayerStateType;
import com.amazon.avod.media.playback.VideoPlayer;
import com.amazon.avod.media.playback.monitoring.TimelineMonitor;
import com.amazon.avod.media.playback.monitoring.TimelineMonitoringTask;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggerType;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
class MonitoringPrimaryState extends AdEnabledPlaybackState {
    private final AdBreakSelector mBreakSelector;
    private final LiveAdTrackingManager mLiveAdTrackingManager;
    private TimelineMonitoringTask mMonitoringTask;
    private final TimelineMonitor mTimelineMonitor;

    public MonitoringPrimaryState(AdPlaybackStateMachineContext adPlaybackStateMachineContext, TimelineMonitor timelineMonitor, AdBreakSelector adBreakSelector, @Nonnull LiveAdTrackingManager liveAdTrackingManager) {
        super(AdEnabledPlayerStateType.MONITORING_FOR_AD_BREAK, adPlaybackStateMachineContext);
        this.mTimelineMonitor = timelineMonitor;
        this.mBreakSelector = adBreakSelector;
        this.mLiveAdTrackingManager = (LiveAdTrackingManager) Preconditions.checkNotNull(liveAdTrackingManager, "liveAdTrackingManager");
    }

    @Override // com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackState, com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public void enter(Trigger<PlayerTriggerType> trigger) {
        setNextBreak(TimeSpan.fromMilliseconds(getContext().getVideoPlayerProxy().getCurrentPosition()));
        this.mLiveAdTrackingManager.init();
    }

    private void setNextBreak(TimeSpan timeSpan) {
        final AdBreak adBreakSelectNextBreak = this.mBreakSelector.selectNextBreak(getContext().getPlan(), timeSpan);
        if (adBreakSelectNextBreak != null && adBreakSelectNextBreak.getAdPositionType() != AdPositionType.PRE_ROLL) {
            final VideoPlayer videoPlayerProxy = getContext().getVideoPlayerProxy();
            adBreakSelectNextBreak.preload(TimeSpan.fromMilliseconds(videoPlayerProxy.getCurrentPosition()), getContext().shouldStoreInPersistentCache(adBreakSelectNextBreak.getAdPositionType()), getConsumptionId());
            TimelineMonitoringTask timelineMonitoringTask = new TimelineMonitoringTask(new Runnable() { // from class: com.amazon.avod.media.ads.internal.adplaybackstatemachine.MonitoringPrimaryState.1
                @Override // java.lang.Runnable
                public void run() {
                    adBreakSelectNextBreak.setAdTriggeredTimeStamp(TimeSpan.fromMilliseconds(videoPlayerProxy.getCurrentPosition()));
                    MonitoringPrimaryState.this.doTrigger(new AdBreakTrigger(adBreakSelectNextBreak));
                    DLog.devf("Monitoring State triggering adBreak from position = %d", Long.valueOf(videoPlayerProxy.getCurrentPosition()));
                }
            }, adBreakSelectNextBreak.getStartTime());
            this.mMonitoringTask = timelineMonitoringTask;
            this.mTimelineMonitor.scheduleTask(videoPlayerProxy, timelineMonitoringTask);
        }
        if (getContext().getAdsConfig().isMidRollAggressiveCachingEnabled()) {
            aggressivelyLoadAllMidRolls(adBreakSelectNextBreak, timeSpan, getContext());
        }
    }

    public void aggressivelyLoadAllMidRolls(@Nullable AdBreak adBreak, @Nonnull TimeSpan timeSpan, @Nonnull AdPlaybackStateMachineContext adPlaybackStateMachineContext) {
        for (AdBreak adBreak2 : adPlaybackStateMachineContext.getPlan().getBreaks()) {
            if (adBreak != adBreak2 && adBreak2.getStartTime().greaterThan(timeSpan)) {
                adBreak2.preload(TimeSpan.fromMilliseconds(adPlaybackStateMachineContext.getPrimaryPlayer().getCurrentPosition()), false, getConsumptionId());
            }
        }
    }

    @Override // com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackState, com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public void exit(Trigger<PlayerTriggerType> trigger) {
        if (getContext().getAdsConfig().shouldDeinitOnPlaybackStop()) {
            return;
        }
        this.mLiveAdTrackingManager.deInit();
    }
}
