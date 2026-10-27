package com.amazon.avod.fsm.internal;

import com.amazon.avod.fsm.State;
import com.google.common.base.Preconditions;
import java.util.Locale;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public class Transition<S, T> {
    private final State<S, T> mFromState;
    private final State<S, T> mToState;
    private final T mTrigger;

    public Transition(State<S, T> state, State<S, T> state2, T t) {
        this.mFromState = (State) Preconditions.checkNotNull(state);
        this.mToState = (State) Preconditions.checkNotNull(state2);
        this.mTrigger = (T) Preconditions.checkNotNull(t);
    }

    public State<S, T> getFromState() {
        return this.mFromState;
    }

    public State<S, T> getToState() {
        return this.mToState;
    }

    public T getTrigger() {
        return this.mTrigger;
    }

    public String toString() {
        return String.format(Locale.US, "[from:%s, to:%s, trigger:%s]", getFromState(), getToState(), getTrigger());
    }
}
