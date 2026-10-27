package com.amazon.avod.media.playback.reporting.aloysius.state;

import com.amazon.avod.fsm.StateBase;
import com.amazon.avod.fsm.StateMachine;
import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusInteractionReporter;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class InteractionEventState extends StateBase<AloysiusInteractionReporter.Type, AloysiusInteractionReporter.Type> {
    private final AloysiusInteractionReporter.Type mType;

    InteractionEventState(@Nonnull InteractionEventStateMachine interactionEventStateMachine, AloysiusInteractionReporter.Type type) {
        super((StateMachine) Preconditions.checkNotNull(interactionEventStateMachine, "interactionEventStateMachine"));
        this.mType = type;
    }

    @Override // com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public final void enter(Trigger<AloysiusInteractionReporter.Type> trigger) {
        super.enter(trigger);
        if (trigger == null) {
            DLog.devf("Null trigger received, this is only expected during startup");
        } else {
            Preconditions.checkState(trigger instanceof InteractionEventTrigger, "Triggers into the InteractionEventStateMachine must be InteractionEventTriggers");
            logStateChange(trigger, "entering");
        }
    }

    @Override // com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public final void exit(Trigger<AloysiusInteractionReporter.Type> trigger) {
        super.exit(trigger);
        if (trigger == null) {
            DLog.devf("Null trigger received, this is only expected during shutdown");
        } else {
            Preconditions.checkState(trigger instanceof InteractionEventTrigger, "Triggers into the InteractionEventStateMachine must be InteractionEventTriggers");
            logStateChange(trigger, "exiting");
        }
    }

    @Override // com.amazon.avod.fsm.State
    public AloysiusInteractionReporter.Type getType() {
        return this.mType;
    }

    private void logStateChange(Trigger<AloysiusInteractionReporter.Type> trigger, String str) {
        DLog.logf("InteractionEventState: received a %s trigger and is %s %sState", trigger.getType(), str, this.mType);
    }
}
