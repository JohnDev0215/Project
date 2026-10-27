package com.amazon.avod.fsm.internal;

import com.amazon.avod.fsm.State;
import com.amazon.avod.fsm.StateBuilder;
import com.amazon.avod.fsm.StateMachine;
import com.google.common.base.Preconditions;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class StateMachineBase<S, T> implements StateMachine<S, T> {
    protected final StateModel<S, T> mStateModel;
    protected final TransitionTable<S, T> mTransitionTable;

    protected void handleException(Exception exc, State<S, T> state) {
    }

    public StateMachineBase() {
        this(new TransitionTable(), new StateModel());
    }

    public StateMachineBase(TransitionTable<S, T> transitionTable, StateModel<S, T> stateModel) {
        this.mTransitionTable = (TransitionTable) Preconditions.checkNotNull(transitionTable);
        this.mStateModel = (StateModel) Preconditions.checkNotNull(stateModel);
    }

    public StateBuilder<S, T> setupState(State<S, T> state) {
        this.mStateModel.addState(state);
        return new ConcreteStateBuilder(state, this.mTransitionTable);
    }

    public StateBuilder<S, T> setupState(State<S, T> state, State<S, T> state2) {
        this.mStateModel.addState(state2, state);
        return new ConcreteStateBuilder(state, this.mTransitionTable);
    }

    protected Transition<S, T> getTransition(State<S, T> state, T t) {
        List<State<S, T>> ancestors = this.mStateModel.getAncestors(state);
        ancestors.add(0, state);
        Iterator<State<S, T>> it = ancestors.iterator();
        while (it.hasNext()) {
            Transition<S, T> transition = this.mTransitionTable.getTransition(it.next(), t);
            if (transition != null) {
                return transition;
            }
        }
        return null;
    }
}
