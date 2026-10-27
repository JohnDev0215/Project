package com.amazon.avod.media.ads.internal.state;

import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.ads.AdBreak;
import com.amazon.avod.media.ads.internal.AdBreakSelector;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggerType;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggers;
import com.amazon.avod.util.DLog;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public class CheckPrerollState extends AdEnabledPlaybackState {
    protected final AdBreakSelector mBreakSelector;

    public CheckPrerollState(AdPlaybackStateMachineContext adPlaybackStateMachineContext, AdBreakSelector adBreakSelector) {
        super(AdEnabledPlayerStateType.CHECK_PREROLL, adPlaybackStateMachineContext);
        this.mBreakSelector = adBreakSelector;
    }

    @Override // com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackState, com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public void enter(Trigger<PlayerTriggerType> trigger) {
        TimeSpan startTime = getContext().getVideoSpecification().getStartTime();
        AdBreak adBreakSelectNextBreak = this.mBreakSelector.selectNextBreak(getContext().getPlan(), startTime);
        if (adBreakSelectNextBreak != null && adBreakSelectNextBreak.getStartTime().equals(TimeSpan.ZERO)) {
            doTrigger(new AdBreakTrigger(adBreakSelectNextBreak));
        } else {
            doTrigger(PlayerTriggers.PLAY);
        }
        reportPrerollStatus(PrerollStatus.getPrerollStatus(startTime, adBreakSelectNextBreak, getContext()));
    }

    protected void reportPrerollStatus(@Nonnull PrerollStatus prerollStatus) {
        DLog.logf("CheckPrerollState - Preroll: %s", prerollStatus.getReportingName());
        getContext().getPrimaryEventReporter().reportMetric("AdEvent", "AdPreroll", null, prerollStatus.getReportingName(), null);
    }
}
