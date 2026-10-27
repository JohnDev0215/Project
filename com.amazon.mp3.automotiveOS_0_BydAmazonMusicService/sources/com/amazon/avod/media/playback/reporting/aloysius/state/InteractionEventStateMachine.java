package com.amazon.avod.media.playback.reporting.aloysius.state;

import com.amazon.avod.fsm.BlockingStateMachine;
import com.amazon.avod.fsm.State;
import com.amazon.avod.fsm.StateBuilder;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusInteractionReporter;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.Iterator;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class InteractionEventStateMachine extends BlockingStateMachine<AloysiusInteractionReporter.Type, AloysiusInteractionReporter.Type> {

    static class SingletonHolder {
        private static volatile InteractionEventStateMachine sInstance = new InteractionEventStateMachine();

        SingletonHolder() {
        }
    }

    @Nonnull
    public static InteractionEventStateMachine getInstance() {
        return SingletonHolder.sInstance;
    }

    InteractionEventStateMachine() {
        super("InteractionEventStateMachine");
        setupStateMachine();
    }

    @Override // com.amazon.avod.fsm.BlockingStateMachine
    public void start(@Nonnull State<AloysiusInteractionReporter.Type, AloysiusInteractionReporter.Type> state) {
        Preconditions.checkNotNull(state, "initialState");
        super.start(state);
        DLog.logf("IESM has been started and is entering %s state", state.getType());
    }

    private void setupStateMachine() {
        InteractionEventState interactionEventState = new InteractionEventState(this, AloysiusInteractionReporter.Type.Stop);
        AloysiusInteractionReporter.Type type = AloysiusInteractionReporter.Type.Play;
        InteractionEventState interactionEventState2 = new InteractionEventState(this, type);
        InteractionEventState interactionEventState3 = new InteractionEventState(this, AloysiusInteractionReporter.Type.Pause);
        InteractionEventState interactionEventState4 = new InteractionEventState(this, AloysiusInteractionReporter.Type.Next);
        InteractionEventState interactionEventState5 = new InteractionEventState(this, AloysiusInteractionReporter.Type.Previous);
        InteractionEventState interactionEventState6 = new InteractionEventState(this, AloysiusInteractionReporter.Type.TimedTextOn);
        InteractionEventState interactionEventState7 = new InteractionEventState(this, AloysiusInteractionReporter.Type.TimedTextOff);
        InteractionEventState interactionEventState8 = new InteractionEventState(this, AloysiusInteractionReporter.Type.FullscreenOn);
        InteractionEventState interactionEventState9 = new InteractionEventState(this, AloysiusInteractionReporter.Type.FullscreenOff);
        InteractionEventState interactionEventState10 = new InteractionEventState(this, AloysiusInteractionReporter.Type.SeekForward);
        InteractionEventState interactionEventState11 = new InteractionEventState(this, AloysiusInteractionReporter.Type.SeekBackward);
        InteractionEventState interactionEventState12 = new InteractionEventState(this, AloysiusInteractionReporter.Type.ShowControls);
        InteractionEventState interactionEventState13 = new InteractionEventState(this, AloysiusInteractionReporter.Type.TrackChange);
        InteractionEventState interactionEventState14 = new InteractionEventState(this, AloysiusInteractionReporter.Type.Navigation);
        ImmutableList<InteractionEventState> immutableListOf = ImmutableList.of(interactionEventState2, interactionEventState3, interactionEventState4, interactionEventState5, interactionEventState6, interactionEventState7, interactionEventState8, interactionEventState9);
        ImmutableList immutableListOf2 = ImmutableList.of(interactionEventState10, interactionEventState11, interactionEventState12, interactionEventState13, interactionEventState14);
        ImmutableList<InteractionEventState> immutableListOf3 = ImmutableList.of(interactionEventState, interactionEventState2, interactionEventState3, interactionEventState4, interactionEventState5, interactionEventState6, interactionEventState7, interactionEventState8, interactionEventState9, interactionEventState10, interactionEventState11, interactionEventState12, interactionEventState13, interactionEventState14);
        setupState(interactionEventState).registerTransition(type, interactionEventState2);
        for (InteractionEventState interactionEventState15 : immutableListOf) {
            StateBuilder<AloysiusInteractionReporter.Type, AloysiusInteractionReporter.Type> stateBuilder = setupState(interactionEventState15);
            for (InteractionEventState interactionEventState16 : immutableListOf3) {
                if (interactionEventState15.getType() != interactionEventState16.getType()) {
                    stateBuilder.registerTransition(interactionEventState16.getType(), interactionEventState16);
                }
            }
        }
        Iterator<E> it = immutableListOf2.iterator();
        while (it.hasNext()) {
            StateBuilder<AloysiusInteractionReporter.Type, AloysiusInteractionReporter.Type> stateBuilder2 = setupState((InteractionEventState) it.next());
            for (InteractionEventState interactionEventState17 : immutableListOf3) {
                stateBuilder2.registerTransition(interactionEventState17.getType(), interactionEventState17);
            }
        }
        start(interactionEventState);
    }
}
