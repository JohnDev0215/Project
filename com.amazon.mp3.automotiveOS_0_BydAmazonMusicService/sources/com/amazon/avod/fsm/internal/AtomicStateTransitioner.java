package com.amazon.avod.fsm.internal;

import com.amazon.avod.fsm.State;
import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.TraceKey;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class AtomicStateTransitioner<S, T> {
    private final StateModel<S, T> mStateModel;
    private final Object mStateMutex = new Object();
    private final List<State<S, T>> mEnterStates = Lists.newLinkedList();
    private final List<State<S, T>> mExitStates = Lists.newLinkedList();
    private State<S, T> mCurrentState = null;

    public AtomicStateTransitioner(StateModel<S, T> stateModel) {
        this.mStateModel = (StateModel) Preconditions.checkNotNull(stateModel);
    }

    public void setState(State<S, T> state, Trigger<T> trigger) {
        synchronized (this.mStateMutex) {
            Preconditions.checkState(this.mEnterStates.isEmpty(), "Cannot invoke a transition while another transition is in progress.");
            Preconditions.checkState(this.mExitStates.isEmpty(), "Cannot invoke a transition while another transition is in progress.");
            this.mStateModel.getTransitionPath(this.mCurrentState, state, this.mExitStates, this.mEnterStates);
            while (!this.mExitStates.isEmpty()) {
                this.mCurrentState = this.mExitStates.remove(0);
                TraceKey traceKeyBeginTrace = Profiler.beginTrace(Profiler.TraceLevel.DEBUG, "%s:exit:%s:%s", getClass().getSimpleName(), this.mCurrentState.getClass().getSimpleName(), trigger);
                this.mCurrentState.exit(trigger);
                Profiler.endTrace(traceKeyBeginTrace);
            }
            this.mCurrentState = null;
            while (!this.mEnterStates.isEmpty()) {
                this.mCurrentState = this.mEnterStates.remove(0);
                TraceKey traceKeyBeginTrace2 = Profiler.beginTrace(Profiler.TraceLevel.DEBUG, "%s:enter:%s:%s", getClass().getSimpleName(), this.mCurrentState.getClass().getSimpleName(), trigger);
                this.mCurrentState.enter(trigger);
                Profiler.endTrace(traceKeyBeginTrace2);
            }
        }
    }

    public State<S, T> getState() {
        State<S, T> state;
        synchronized (this.mStateMutex) {
            state = this.mCurrentState;
        }
        return state;
    }
}
