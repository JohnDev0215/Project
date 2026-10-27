package com.amazon.avod.media.ads.internal.adplaybackstatemachine;

import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.ads.AdBreak;
import com.amazon.avod.media.ads.internal.AdBreakSelector;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext;
import com.amazon.avod.media.ads.internal.state.AdBreakTrigger;
import com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackState;
import com.amazon.avod.media.ads.internal.state.AdEnabledPlayerStateType;
import com.amazon.avod.media.ads.internal.state.PrerollStatus;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggerType;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggers;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class CheckPrerollState extends AdEnabledPlaybackState {
    protected final AdBreakSelector mBreakSelector;

    public CheckPrerollState(@Nonnull AdPlaybackStateMachineContext adPlaybackStateMachineContext, @Nonnull AdBreakSelector adBreakSelector) {
        super(AdEnabledPlayerStateType.CHECK_PREROLL, adPlaybackStateMachineContext);
        this.mBreakSelector = (AdBreakSelector) Preconditions.checkNotNull(adBreakSelector, "breakSelector");
    }

    @Override // com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackState, com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public void enter(Trigger<PlayerTriggerType> trigger) {
        TimeSpan startTime = getContext().getVideoSpecification().getStartTime();
        AdBreak adBreakSelectNextBreak = this.mBreakSelector.selectNextBreak(getContext().getPlan(), startTime);
        PrerollStatus prerollStatus = PrerollStatus.getPrerollStatus(startTime, adBreakSelectNextBreak, getContext());
        if (prerollStatus.isAvailableForPlayback()) {
            doTrigger(new AdBreakTrigger(adBreakSelectNextBreak));
        } else {
            doTrigger(PlayerTriggers.PLAY);
        }
        reportPrerollStatus(prerollStatus);
    }

    protected void reportPrerollStatus(@Nonnull PrerollStatus prerollStatus) {
        DLog.logf("CheckPrerollState - Preroll: %s", prerollStatus.getReportingName());
        getContext().getPrimaryEventReporter().reportMetric("AdEvent", "AdPreroll", null, prerollStatus.getReportingName(), null);
    }
}
