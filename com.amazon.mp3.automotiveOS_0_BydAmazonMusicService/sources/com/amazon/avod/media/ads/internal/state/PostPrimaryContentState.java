package com.amazon.avod.media.ads.internal.state;

import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.ads.AdBreak;
import com.amazon.avod.media.ads.internal.AdBreakSelector;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggerType;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggers;
import com.amazon.avod.util.DLog;

/* JADX INFO: loaded from: classes2.dex */
public class PostPrimaryContentState extends AdEnabledPlaybackState {
    private final AdBreakSelector mBreakSelector;

    public PostPrimaryContentState(AdPlaybackStateMachineContext adPlaybackStateMachineContext, AdBreakSelector adBreakSelector) {
        super(AdEnabledPlayerStateType.POST_PRIMARY_STATE, adPlaybackStateMachineContext);
        this.mBreakSelector = adBreakSelector;
    }

    @Override // com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackState, com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public void enter(Trigger<PlayerTriggerType> trigger) {
        AdBreak adBreakSelectNextBreak = this.mBreakSelector.selectNextBreak(getContext().getPlan(), TimeSpan.fromMilliseconds(getContext().getPrimaryPlayer().getDuration()));
        if (adBreakSelectNextBreak != null) {
            DLog.logf("Primary content finished - found post-roll ad break at %s.", adBreakSelectNextBreak.getStartTime());
            doTrigger(new AdBreakTrigger(adBreakSelectNextBreak));
        } else {
            doTrigger(PlayerTriggers.COMPLETED);
        }
    }
}
