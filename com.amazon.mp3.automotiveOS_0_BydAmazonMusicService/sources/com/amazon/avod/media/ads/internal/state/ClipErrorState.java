package com.amazon.avod.media.ads.internal.state;

import com.amazon.avod.ads.api.AdInfoErrorCode;
import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.media.ads.AdError;
import com.amazon.avod.media.ads.internal.AdManagerBasedAdClip;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggerType;
import com.amazon.avod.util.CastUtils;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public class ClipErrorState extends AdEnabledPlaybackState {
    public ClipErrorState(AdPlaybackStateMachineContext adPlaybackStateMachineContext) {
        super(AdEnabledPlayerStateType.CLIP_ERROR_STATE, adPlaybackStateMachineContext);
    }

    @Override // com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackState, com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public void enter(Trigger<PlayerTriggerType> trigger) {
        ClipErrorTrigger clipErrorTrigger = (ClipErrorTrigger) CastUtils.castTo(trigger, ClipErrorTrigger.class);
        Preconditions.checkArgument(clipErrorTrigger != null, "Only ClipErrorTrigger triggers are handled.");
        AdError error = clipErrorTrigger.getError();
        AdManagerBasedAdClip clip = clipErrorTrigger.getClip();
        DLog.logf("Handle error %s for clip %s", error, clip);
        clip.getPresentation().getReporter().reportError(error.toString(), clipErrorTrigger.getMessage(), null);
        getContext().getListener().onAdClipError(clip, error);
        clip.sendError(AdInfoErrorCode.GENERAL_LINEAR_ERROR);
        if (clipErrorTrigger.getError() == AdError.EXCESSIVE_BUFFERING) {
            doTrigger(trigger);
        } else {
            doTrigger(AdEnabledPlayerTriggers.PLAY_AD);
        }
    }
}
