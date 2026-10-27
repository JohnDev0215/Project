package androidx.leanback.util;

import java.util.ArrayList;
import kotlinx.coroutines.DebugKt;

/* JADX INFO: loaded from: classes3.dex */
public final class StateMachine {
    static final boolean DEBUG = false;
    public static final int STATUS_INVOKED = 1;
    public static final int STATUS_ZERO = 0;
    static final String TAG = "StateMachine";
    final ArrayList<State> mStates = new ArrayList<>();
    final ArrayList<State> mFinishedStates = new ArrayList<>();
    final ArrayList<State> mUnfinishedStates = new ArrayList<>();

    public static class Event {
        final String mName;

        public Event(String name) {
            this.mName = name;
        }
    }

    public static class Condition {
        final String mName;

        public Condition(String name) {
            this.mName = name;
        }

        public boolean canProceed() {
            return true;
        }
    }

    static class Transition {
        final Condition mCondition;
        final Event mEvent;
        final State mFromState;
        int mState;
        final State mToState;

        Transition(State fromState, State toState, Event event) {
            this.mState = 0;
            if (event == null) {
                throw new IllegalArgumentException();
            }
            this.mFromState = fromState;
            this.mToState = toState;
            this.mEvent = event;
            this.mCondition = null;
        }

        Transition(State fromState, State toState) {
            this.mState = 0;
            this.mFromState = fromState;
            this.mToState = toState;
            this.mEvent = null;
            this.mCondition = null;
        }

        Transition(State fromState, State toState, Condition condition) {
            this.mState = 0;
            if (condition == null) {
                throw new IllegalArgumentException();
            }
            this.mFromState = fromState;
            this.mToState = toState;
            this.mEvent = null;
            this.mCondition = condition;
        }

        public String toString() {
            String signalName;
            Event event = this.mEvent;
            if (event != null) {
                signalName = event.mName;
            } else {
                Condition condition = this.mCondition;
                if (condition != null) {
                    signalName = condition.mName;
                } else {
                    signalName = DebugKt.DEBUG_PROPERTY_VALUE_AUTO;
                }
            }
            return "[" + this.mFromState.mName + " -> " + this.mToState.mName + " <" + signalName + ">]";
        }
    }

    public static class State {
        final boolean mBranchEnd;
        final boolean mBranchStart;
        ArrayList<Transition> mIncomings;
        int mInvokedOutTransitions;
        final String mName;
        ArrayList<Transition> mOutgoings;
        int mStatus;

        public String toString() {
            return "[" + this.mName + " " + this.mStatus + "]";
        }

        public State(String name) {
            this(name, false, true);
        }

        public State(String name, boolean branchStart, boolean branchEnd) {
            this.mStatus = 0;
            this.mInvokedOutTransitions = 0;
            this.mName = name;
            this.mBranchStart = branchStart;
            this.mBranchEnd = branchEnd;
        }

        void addIncoming(Transition t) {
            if (this.mIncomings == null) {
                this.mIncomings = new ArrayList<>();
            }
            this.mIncomings.add(t);
        }

        void addOutgoing(Transition t) {
            if (this.mOutgoings == null) {
                this.mOutgoings = new ArrayList<>();
            }
            this.mOutgoings.add(t);
        }

        public void run() {
        }

        final boolean checkPreCondition() {
            ArrayList<Transition> arrayList = this.mIncomings;
            if (arrayList == null) {
                return true;
            }
            if (this.mBranchEnd) {
                for (Transition t : arrayList) {
                    if (t.mState != 1) {
                        return false;
                    }
                }
                return true;
            }
            for (Transition t2 : arrayList) {
                if (t2.mState == 1) {
                    return true;
                }
            }
            return false;
        }

        final boolean runIfNeeded() {
            if (this.mStatus != 1 && checkPreCondition()) {
                this.mStatus = 1;
                run();
                signalAutoTransitionsAfterRun();
                return true;
            }
            return false;
        }

        final void signalAutoTransitionsAfterRun() {
            ArrayList<Transition> arrayList = this.mOutgoings;
            if (arrayList != null) {
                for (Transition t : arrayList) {
                    if (t.mEvent == null && (t.mCondition == null || t.mCondition.canProceed())) {
                        this.mInvokedOutTransitions++;
                        t.mState = 1;
                        if (!this.mBranchStart) {
                            return;
                        }
                    }
                }
            }
        }

        public final int getStatus() {
            return this.mStatus;
        }
    }

    public void addState(State state) {
        if (!this.mStates.contains(state)) {
            this.mStates.add(state);
        }
    }

    public void addTransition(State fromState, State toState, Event event) {
        Transition transition = new Transition(fromState, toState, event);
        toState.addIncoming(transition);
        fromState.addOutgoing(transition);
    }

    public void addTransition(State fromState, State toState, Condition condition) {
        Transition transition = new Transition(fromState, toState, condition);
        toState.addIncoming(transition);
        fromState.addOutgoing(transition);
    }

    public void addTransition(State fromState, State toState) {
        Transition transition = new Transition(fromState, toState);
        toState.addIncoming(transition);
        fromState.addOutgoing(transition);
    }

    public void start() {
        this.mUnfinishedStates.addAll(this.mStates);
        runUnfinishedStates();
    }

    void runUnfinishedStates() {
        boolean changed;
        do {
            changed = false;
            for (int i = this.mUnfinishedStates.size() - 1; i >= 0; i--) {
                State state = this.mUnfinishedStates.get(i);
                if (state.runIfNeeded()) {
                    this.mUnfinishedStates.remove(i);
                    this.mFinishedStates.add(state);
                    changed = true;
                }
            }
        } while (changed);
    }

    public void fireEvent(Event event) {
        for (int i = 0; i < this.mFinishedStates.size(); i++) {
            State state = this.mFinishedStates.get(i);
            if (state.mOutgoings != null && (state.mBranchStart || state.mInvokedOutTransitions <= 0)) {
                for (Transition t : state.mOutgoings) {
                    if (t.mState != 1 && t.mEvent == event) {
                        t.mState = 1;
                        state.mInvokedOutTransitions++;
                        if (!state.mBranchStart) {
                            break;
                        }
                    }
                }
            }
        }
        runUnfinishedStates();
    }

    public void reset() {
        this.mUnfinishedStates.clear();
        this.mFinishedStates.clear();
        for (State state : this.mStates) {
            state.mStatus = 0;
            state.mInvokedOutTransitions = 0;
            if (state.mOutgoings != null) {
                for (Transition t : state.mOutgoings) {
                    t.mState = 0;
                }
            }
        }
    }
}
