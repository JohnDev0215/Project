package com.amazon.avod.media.ads.internal.state;

import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggerType;

/* JADX INFO: loaded from: classes2.dex */
public class TransitionState extends AdEnabledPlaybackState {
    private final TimeSpan mDuration;
    private final TransitionType mTransitionType;

    public enum TransitionType {
        TRANSITION_IN,
        TRANSITION_OUT_FROM_PRIMARY,
        TRANSITION_OUT_FROM_CLIP
    }

    public TransitionState(AdPlaybackStateMachineContext adPlaybackStateMachineContext, TransitionType transitionType, TimeSpan timeSpan) {
        super(AdEnabledPlayerStateType.TRANSITION_STATE, adPlaybackStateMachineContext);
        this.mTransitionType = transitionType;
        this.mDuration = timeSpan;
    }

    @Override // com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackState, com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public void enter(Trigger<PlayerTriggerType> trigger) {
        TriggerTask triggerTask = new TriggerTask(trigger);
        AdPlaybackStateMachineContext context = getContext();
        boolean z = context.getAdEnabledRenderingSettings() == null;
        int i = AnonymousClass1.$SwitchMap$com$amazon$avod$media$ads$internal$state$TransitionState$TransitionType[this.mTransitionType.ordinal()];
        if (i == 1) {
            if (z) {
                context.getAdTransitioner().transitionIn(context.isPrerollPlaying() ? TimeSpan.ZERO : this.mDuration, null);
            }
            triggerTask.run();
        } else {
            if (i == 2) {
                if (context.isPrerollPlaying() || !z) {
                    triggerTask.run();
                    return;
                } else {
                    context.getAdTransitioner().transitionOut(this.mDuration, triggerTask);
                    return;
                }
            }
            if (i != 3) {
                throw new IllegalStateException("Unknown transition state.");
            }
            if (z) {
                context.getAdTransitioner().transitionOut(this.mDuration, triggerTask);
            } else {
                triggerTask.run();
            }
        }
    }

    /* JADX INFO: renamed from: com.amazon.avod.media.ads.internal.state.TransitionState$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$media$ads$internal$state$TransitionState$TransitionType;

        static {
            int[] iArr = new int[TransitionType.values().length];
            $SwitchMap$com$amazon$avod$media$ads$internal$state$TransitionState$TransitionType = iArr;
            try {
                iArr[TransitionType.TRANSITION_IN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$ads$internal$state$TransitionState$TransitionType[TransitionType.TRANSITION_OUT_FROM_PRIMARY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$ads$internal$state$TransitionState$TransitionType[TransitionType.TRANSITION_OUT_FROM_CLIP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override // com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackState, com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public void exit(Trigger<PlayerTriggerType> trigger) {
        AdPlaybackStateMachineContext context = getContext();
        if (this.mTransitionType == TransitionType.TRANSITION_OUT_FROM_PRIMARY) {
            context.hidePrimaryContent();
        }
    }

    private class TriggerTask implements Runnable {
        private final Trigger<PlayerTriggerType> mTrigger;

        public TriggerTask(Trigger<PlayerTriggerType> trigger) {
            this.mTrigger = trigger;
        }

        @Override // java.lang.Runnable
        public void run() {
            TransitionState.this.doTrigger(this.mTrigger);
        }
    }
}
