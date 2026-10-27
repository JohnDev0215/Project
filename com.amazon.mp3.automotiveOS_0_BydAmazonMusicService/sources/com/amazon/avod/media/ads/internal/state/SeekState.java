package com.amazon.avod.media.ads.internal.state;

import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.ads.AdBreak;
import com.amazon.avod.media.ads.internal.AdBreakSelector;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext;
import com.amazon.avod.media.playback.state.StandardPlayerStateType;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggerType;
import com.amazon.avod.media.playback.state.trigger.SeekTrigger;
import com.amazon.avod.util.CastUtils;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public class SeekState extends AdEnabledPlaybackState {
    private final AdBreakSelector mBreakSelector;
    private TimeSpan mSeekTarget;

    public SeekState(AdPlaybackStateMachineContext adPlaybackStateMachineContext, AdBreakSelector adBreakSelector) {
        super(StandardPlayerStateType.SEEKING, adPlaybackStateMachineContext);
        this.mBreakSelector = adBreakSelector;
    }

    @Override // com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackState, com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public void enter(Trigger<PlayerTriggerType> trigger) {
        SeekTrigger seekTrigger = (SeekTrigger) CastUtils.castTo(trigger, SeekTrigger.class);
        Preconditions.checkArgument(seekTrigger != null, "Only SeekTrigger triggers are handled.");
        this.mSeekTarget = seekTrigger.getSeekTarget();
        TimeSpan timeSpanFromMilliseconds = TimeSpan.fromMilliseconds(getContext().getPrimaryPlayer().getCurrentPosition());
        AdBreak adBreakSelectPriorBreakIfUnPlayed = this.mBreakSelector.selectPriorBreakIfUnPlayed(getContext().getPlan(), this.mSeekTarget, timeSpanFromMilliseconds);
        if (adBreakSelectPriorBreakIfUnPlayed != null && this.mSeekTarget.greaterThanEquals(adBreakSelectPriorBreakIfUnPlayed.getStartTime())) {
            DLog.logf("Seeking past %s will get ad break %s - will force to ad state.", this.mSeekTarget, adBreakSelectPriorBreakIfUnPlayed.getStartTime());
            DLog.devf("adBreak triggered at Seeking state position = %d", Long.valueOf(timeSpanFromMilliseconds.getTotalMilliseconds()));
            adBreakSelectPriorBreakIfUnPlayed.setAdTriggeredTimeStamp(timeSpanFromMilliseconds);
            doTrigger(new AdBreakTrigger(adBreakSelectPriorBreakIfUnPlayed));
            return;
        }
        doTrigger(new MonitorTrigger(seekTrigger.getSeekTarget()));
    }

    @Override // com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackState, com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public void exit(Trigger<PlayerTriggerType> trigger) {
        getContext().getPrimaryPlayer().seekTo(this.mSeekTarget.getTotalMilliseconds());
    }
}
