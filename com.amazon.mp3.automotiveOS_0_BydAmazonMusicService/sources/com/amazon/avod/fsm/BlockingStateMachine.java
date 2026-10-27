package com.amazon.avod.fsm;

import android.annotation.SuppressLint;
import com.amazon.avod.fsm.internal.AtomicStateTransitioner;
import com.amazon.avod.fsm.internal.StateMachineBase;
import com.amazon.avod.fsm.internal.StateModel;
import com.amazon.avod.fsm.internal.Transition;
import com.amazon.avod.fsm.internal.TransitionTable;
import com.amazon.avod.util.DLog;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class BlockingStateMachine<S, T> extends StateMachineBase<S, T> {
    private final String mName;
    private final AtomicStateTransitioner<S, T> mTransitioner;

    public boolean doVerboseLogging() {
        return false;
    }

    public BlockingStateMachine(@Nonnull String str) {
        this.mName = (String) Preconditions.checkNotNull(str, "name");
        this.mTransitioner = new AtomicStateTransitioner<>(this.mStateModel);
    }

    public BlockingStateMachine() {
        this.mName = getClass().getSimpleName();
        this.mTransitioner = new AtomicStateTransitioner<>(this.mStateModel);
    }

    @SuppressLint({"VisibleForTests"})
    BlockingStateMachine(AtomicStateTransitioner<S, T> atomicStateTransitioner, TransitionTable<S, T> transitionTable, StateModel<S, T> stateModel, String str) {
        super(transitionTable, stateModel);
        this.mTransitioner = (AtomicStateTransitioner) Preconditions.checkNotNull(atomicStateTransitioner);
        this.mName = str == null ? "Nameless" : str;
    }

    public void start(State<S, T> state) {
        synchronized (this.mTransitioner) {
            this.mTransitioner.setState(state, null);
        }
    }

    public void shutdown() {
        synchronized (this.mTransitioner) {
            this.mTransitioner.setState(null, null);
        }
    }

    @Override // com.amazon.avod.fsm.StateMachine
    public boolean doTrigger(Trigger<T> trigger) {
        synchronized (this.mTransitioner) {
            if (this.mTransitioner.getState() == null) {
                return false;
            }
            Transition<S, T> transition = getTransition(this.mTransitioner.getState(), trigger.getType());
            if (transition == null) {
                return false;
            }
            try {
                if (doVerboseLogging()) {
                    DLog.logf("BSM %s: %s->%s->%s", getClass().getSimpleName(), transition.getFromState().getType(), transition.getTrigger(), transition.getToState().getType());
                }
                this.mTransitioner.setState(transition.getToState(), trigger);
                return true;
            } catch (Exception e) {
                throw new IllegalStateException("Exception thrown while transitioning states. This is a fatal error.", e);
            }
        }
    }

    public State<S, T> getCurrentState() {
        State<S, T> state;
        synchronized (this.mTransitioner) {
            state = this.mTransitioner.getState();
        }
        return state;
    }

    public String toString() {
        return MoreObjects.toStringHelper(getClass()).add("name", this.mName).toString();
    }
}
