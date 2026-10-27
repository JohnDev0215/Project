package com.amazon.avod.fsm.internal;

import com.amazon.avod.fsm.State;
import com.amazon.avod.fsm.StateBuilder;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public class ConcreteStateBuilder<S, T> implements StateBuilder<S, T> {
    private final State<S, T> mState;
    private final TransitionTable<S, T> mTransitionTable;

    public ConcreteStateBuilder(State<S, T> state, TransitionTable<S, T> transitionTable) {
        this.mState = (State) Preconditions.checkNotNull(state);
        this.mTransitionTable = (TransitionTable) Preconditions.checkNotNull(transitionTable);
    }

    @Override // com.amazon.avod.fsm.StateBuilder
    public StateBuilder<S, T> registerTransition(T t, State<S, T> state) {
        Preconditions.checkNotNull(t);
        Preconditions.checkNotNull(state);
        this.mTransitionTable.addTransition(this.mState, state, t);
        return this;
    }
}
