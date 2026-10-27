package com.amazon.avod.media.ads.internal.state;

import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.ads.AdBreak;
import com.amazon.avod.media.ads.internal.AdBreakSelector;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext;
import com.amazon.avod.media.ads.internal.LiveAdTrackingManager;
import com.amazon.avod.media.playback.VideoPlayer;
import com.amazon.avod.media.playback.monitoring.TimelineMonitor;
import com.amazon.avod.media.playback.monitoring.TimelineMonitoringTask;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggerType;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
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
        setNextBreak(TimeSpan.fromMilliseconds(getContext().getPrimaryPlayer().getCurrentPosition()));
        this.mLiveAdTrackingManager.init();
    }

    private void setNextBreak(TimeSpan timeSpan) {
        final AdBreak adBreakSelectNextBreak = this.mBreakSelector.selectNextBreak(getContext().getPlan(), timeSpan);
        final VideoPlayer primaryPlayer = getContext().getPrimaryPlayer();
        if (adBreakSelectNextBreak != null) {
            adBreakSelectNextBreak.preload(TimeSpan.fromMilliseconds(getContext().getPrimaryPlayer().getCurrentPosition()), getContext().shouldStoreInPersistentCache(adBreakSelectNextBreak.getAdPositionType()), getConsumptionId());
            this.mMonitoringTask = new TimelineMonitoringTask(new Runnable() { // from class: com.amazon.avod.media.ads.internal.state.MonitoringPrimaryState.1
                @Override // java.lang.Runnable
                public void run() {
                    adBreakSelectNextBreak.setAdTriggeredTimeStamp(TimeSpan.fromMilliseconds(primaryPlayer.getCurrentPosition()));
                    MonitoringPrimaryState.this.doTrigger(new AdBreakTrigger(adBreakSelectNextBreak));
                    DLog.devf("Monitoring State triggering adBreak from position = %d", Long.valueOf(primaryPlayer.getCurrentPosition()));
                }
            }, adBreakSelectNextBreak.getStartTime());
            this.mTimelineMonitor.scheduleTask(getContext().getPrimaryPlayer(), this.mMonitoringTask);
        }
        if (getContext().getAdsConfig().isMidRollAggressiveCachingEnabled()) {
            aggressivelyLoadAllMidRolls(adBreakSelectNextBreak, timeSpan, getContext());
        }
    }

    public void aggressivelyLoadAllMidRolls(@Nullable AdBreak adBreak, @Nonnull TimeSpan timeSpan, @Nonnull AdPlaybackStateMachineContext adPlaybackStateMachineContext) {
        Preconditions.checkNotNull(adPlaybackStateMachineContext, "context");
        for (AdBreak adBreak2 : adPlaybackStateMachineContext.getPlan().getBreaks()) {
            if (!adBreak2.equals(adBreak) && adBreak2.getStartTime().greaterThan(timeSpan)) {
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
