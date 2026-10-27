package com.amazon.avod.fsm;

import com.google.common.base.Preconditions;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public abstract class StateBase<S, T> implements State<S, T> {
    private final StateMachine<S, T> mOwingStateMachine;

    @Override // com.amazon.avod.fsm.State
    public void enter(Trigger<T> trigger) {
    }

    @Override // com.amazon.avod.fsm.State
    public void exit(Trigger<T> trigger) {
    }

    public StateBase(StateMachine<S, T> stateMachine) {
        this.mOwingStateMachine = (StateMachine) Preconditions.checkNotNull(stateMachine);
    }

    protected void doTrigger(Trigger<T> trigger) {
        this.mOwingStateMachine.doTrigger(trigger);
    }

    public String toString() {
        return String.format(Locale.US, "[State: %s]", getType());
    }
}
