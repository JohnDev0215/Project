package com.amazon.avod.media.ads.internal.state;

import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.VideoPresentation;
import com.amazon.avod.media.playback.state.StandardPlayerStateType;
import com.amazon.avod.media.playback.state.trigger.ErrorTrigger;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggerType;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.TraceKey;
import com.amazon.avod.util.CastUtils;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public class PreparingState extends AdEnabledPlaybackState {
    private final AsyncAdPlanRetriever mAdPlanTask;
    private TraceKey mStateTrace;

    public PreparingState(AdPlaybackStateMachineContext adPlaybackStateMachineContext, AsyncAdPlanRetriever asyncAdPlanRetriever) {
        super(StandardPlayerStateType.PREPARING, adPlaybackStateMachineContext);
        this.mAdPlanTask = asyncAdPlanRetriever;
    }

    @Override // com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackState, com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public void enter(Trigger<PlayerTriggerType> trigger) {
        this.mStateTrace = Profiler.beginTrace(Profiler.TraceLevel.CRITICAL, "%s", getClass().getSimpleName());
        PrepareTrigger prepareTrigger = (PrepareTrigger) CastUtils.castTo(trigger, PrepareTrigger.class);
        Preconditions.checkArgument(prepareTrigger != null, "Only PrepareTrigger triggers are handled.");
        VideoPresentation presentation = prepareTrigger.getPresentation();
        try {
            presentation.setListener(getContext().getStateMachine());
            presentation.prepareAsync();
            this.mAdPlanTask.prepareAdPlan();
        } catch (MediaException e) {
            DLog.exceptionf(e);
            doTrigger(new ErrorTrigger(e.getErrorCode().getExternalCode()));
        }
    }

    @Override // com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackState, com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public void exit(Trigger<PlayerTriggerType> trigger) {
        Profiler.endTrace(this.mStateTrace);
    }
}
