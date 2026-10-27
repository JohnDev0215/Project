package com.amazon.avod.fsm.internal;

import com.amazon.avod.fsm.State;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class TransitionTable<S, T> {
    private final Map<TransitionTable<S, T>.Key, Transition<S, T>> mTransitions = Maps.newHashMap();

    private final class Key {
        private final State<S, T> mState;
        private final T mTrigger;

        public Key(@Nonnull State<S, T> state, @Nonnull T t) {
            this.mState = state;
            this.mTrigger = t;
        }

        public int hashCode() {
            return Objects.hashCode(this.mTrigger, this.mState);
        }

        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            if (obj.getClass() != Key.class) {
                return false;
            }
            Key key = (Key) obj;
            return Objects.equal(this.mState, key.mState) && Objects.equal(this.mTrigger, key.mTrigger);
        }

        public String toString() {
            return String.format(Locale.US, "[State:%s, Trigger:%s]", this.mState, this.mTrigger);
        }
    }

    public void addTransition(@Nonnull State<S, T> state, @Nonnull State<S, T> state2, @Nonnull T t) {
        Preconditions.checkNotNull(state, "fromState");
        Preconditions.checkNotNull(state2, "toState");
        Preconditions.checkNotNull(t, "trigger");
        TransitionTable<S, T>.Key key = new Key(state, t);
        Preconditions.checkState(!this.mTransitions.containsKey(key), "Transition already exists for %s", key);
        this.mTransitions.put(key, new Transition<>(state, state2, t));
    }

    public Transition<S, T> getTransition(@Nonnull State<S, T> state, @Nonnull T t) {
        Preconditions.checkNotNull(state, "fromState");
        Preconditions.checkNotNull(t, "trigger");
        return this.mTransitions.get(new Key(state, t));
    }
}
