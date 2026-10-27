package com.amazon.avod.media.ads.internal.state;

import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.ads.AdBreak;
import com.amazon.avod.media.ads.AdPositionType;
import com.amazon.avod.media.ads.internal.AdBreakSelector;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext;
import com.amazon.avod.media.ads.internal.LiveAdTrackingManager;
import com.amazon.avod.media.playback.VideoPlayer;
import com.amazon.avod.media.playback.monitoring.TimelineMonitor;
import com.amazon.avod.media.playback.monitoring.TimelineMonitoringTask;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggerType;
import com.amazon.avod.playback.smoothstream.ServerInsertedManifestTimelineManager;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
class ServerInsertedMonitoringPrimaryState extends AdEnabledPlaybackState {
    private final AdBreakSelector mBreakSelector;
    private final LiveAdTrackingManager mLiveAdTrackingManager;
    private TimelineMonitoringTask mMonitoringTask;
    private final ServerInsertedManifestTimelineManager mServerInsertedManifestTimelineManager;
    private final TimelineMonitor mTimelineMonitor;

    public ServerInsertedMonitoringPrimaryState(AdPlaybackStateMachineContext adPlaybackStateMachineContext, TimelineMonitor timelineMonitor, AdBreakSelector adBreakSelector, @Nonnull LiveAdTrackingManager liveAdTrackingManager, @Nonnull ServerInsertedManifestTimelineManager serverInsertedManifestTimelineManager) {
        super(AdEnabledPlayerStateType.MONITORING_FOR_AD_BREAK, adPlaybackStateMachineContext);
        this.mTimelineMonitor = timelineMonitor;
        this.mBreakSelector = adBreakSelector;
        this.mLiveAdTrackingManager = (LiveAdTrackingManager) Preconditions.checkNotNull(liveAdTrackingManager, "liveAdTrackingManager");
        this.mServerInsertedManifestTimelineManager = (ServerInsertedManifestTimelineManager) Preconditions.checkNotNull(serverInsertedManifestTimelineManager, "serverInsertedManifestTimelineManager");
    }

    @Override // com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackState, com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public void enter(Trigger<PlayerTriggerType> trigger) {
        setNextBreak(TimeSpan.fromMilliseconds(getContext().getVideoPlayerProxy().getCurrentPosition()));
        this.mLiveAdTrackingManager.init();
    }

    private void setNextBreak(TimeSpan timeSpan) {
        final AdBreak adBreakSelectNextBreak = this.mBreakSelector.selectNextBreak(getContext().getPlan(), timeSpan);
        final VideoPlayer videoPlayerProxy = getContext().getVideoPlayerProxy();
        if (adBreakSelectNextBreak == null || adBreakSelectNextBreak.getAdPositionType() == AdPositionType.PRE_ROLL || adBreakSelectNextBreak.isAdBreakScheduled()) {
            return;
        }
        adBreakSelectNextBreak.preload(TimeSpan.fromMilliseconds(getContext().getPrimaryPlayer().getCurrentPosition()), getContext().shouldStoreInPersistentCache(adBreakSelectNextBreak.getAdPositionType()), getConsumptionId());
        DLog.logf("ServerInsertedPlaybackSession: Next ad break relative start time: %s", adBreakSelectNextBreak.getRelativeStartTime());
        Runnable runnable = new Runnable() { // from class: com.amazon.avod.media.ads.internal.state.ServerInsertedMonitoringPrimaryState.1
            @Override // java.lang.Runnable
            public void run() {
                adBreakSelectNextBreak.setAdTriggeredTimeStamp(TimeSpan.fromMilliseconds(videoPlayerProxy.getCurrentPosition()));
                ServerInsertedMonitoringPrimaryState.this.doTrigger(new AdBreakTrigger(adBreakSelectNextBreak));
                DLog.logf("ServerInsertedPlaybackSession: Monitoring State triggering adBreak from position: %s", TimeSpan.fromMilliseconds(videoPlayerProxy.getCurrentPosition()));
            }
        };
        TimeSpan startTime = adBreakSelectNextBreak.getStartTime();
        DLog.logf("ServerInsertedPlaybackSession: scheduling next AdBreak. Selected AdBreaks absoluteAdBreakStartTime: %s, current primaryContentTime: %s.", startTime, timeSpan);
        this.mMonitoringTask = new TimelineMonitoringTask(runnable, startTime, true, false);
        adBreakSelectNextBreak.adBreakScheduled();
        this.mTimelineMonitor.scheduleTask(getContext().getPrimaryPlayer(), this.mMonitoringTask);
    }

    @Override // com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackState, com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public void exit(Trigger<PlayerTriggerType> trigger) {
        if (getContext().getAdsConfig().shouldDeinitOnPlaybackStop()) {
            return;
        }
        this.mLiveAdTrackingManager.deInit();
    }
}
