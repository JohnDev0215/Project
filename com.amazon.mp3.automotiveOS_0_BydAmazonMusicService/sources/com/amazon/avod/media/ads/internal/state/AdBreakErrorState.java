package com.amazon.avod.media.ads.internal.state;

import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggerType;
import com.amazon.avod.util.CastUtils;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public class AdBreakErrorState extends AdEnabledPlaybackState {
    public AdBreakErrorState(AdPlaybackStateMachineContext adPlaybackStateMachineContext) {
        super(AdEnabledPlayerStateType.CLIP_ERROR_STATE, adPlaybackStateMachineContext);
    }

    @Override // com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackState, com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public void enter(Trigger<PlayerTriggerType> trigger) {
        ClipErrorTrigger clipErrorTrigger = (ClipErrorTrigger) CastUtils.castTo(trigger, ClipErrorTrigger.class);
        Preconditions.checkArgument(clipErrorTrigger != null, "Only ClipErrorTrigger triggers are handled.");
        DLog.logf("Handle error %s for break %s due to clip %s", clipErrorTrigger.getError(), getContext().getCurrentAdBreak(), clipErrorTrigger.getClip());
        getContext().getListener().onAdBreakError(getContext().getCurrentAdBreak(), clipErrorTrigger.getError());
        doTrigger(AdEnabledPlayerTriggers.NO_MORE_ADS);
    }
}
