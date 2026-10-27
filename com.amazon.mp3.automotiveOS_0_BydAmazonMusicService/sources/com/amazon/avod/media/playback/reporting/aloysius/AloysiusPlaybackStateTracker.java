package com.amazon.avod.media.playback.reporting.aloysius;

import androidx.annotation.VisibleForTesting;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.ArrayTable;
import com.google.common.collect.Table;
import java.util.Arrays;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AloysiusPlaybackStateTracker {
    private static final AloysiusPlaybackStateTracker mInstance = new AloysiusPlaybackStateTracker();
    private static final Table<PlaybackState, ActionType, PlaybackState> mTransitionTable = ArrayTable.create(Arrays.asList(PlaybackState.values()), Arrays.asList(ActionType.values()));
    private PlaybackState mPlayerCurrentState = PlaybackState.Uninitialized;

    public enum ActionType {
        AdBegin,
        AdExit,
        AdPause,
        AdResume,
        MainStart,
        MainResume,
        MainPause,
        MainStop,
        IntentOfPlay
    }

    public enum PlaybackState {
        Uninitialized,
        AdPlaying,
        AdPaused,
        MainPlaying,
        MainPaused,
        PlayerOpen
    }

    public enum TransitioningTo {
        AdPlayer,
        MainPlayer
    }

    static {
        PlaybackState playbackState = PlaybackState.Uninitialized;
        ActionType actionType = ActionType.MainStart;
        PlaybackState playbackState2 = PlaybackState.MainPlaying;
        addTransition(playbackState, actionType, playbackState2);
        ActionType actionType2 = ActionType.AdBegin;
        PlaybackState playbackState3 = PlaybackState.AdPlaying;
        addTransition(playbackState, actionType2, playbackState3);
        ActionType actionType3 = ActionType.IntentOfPlay;
        PlaybackState playbackState4 = PlaybackState.PlayerOpen;
        addTransition(playbackState, actionType3, playbackState4);
        addTransition(playbackState4, actionType, playbackState2);
        addTransition(playbackState4, actionType2, playbackState3);
        ActionType actionType4 = ActionType.MainResume;
        addTransition(playbackState3, actionType4, playbackState2);
        addTransition(playbackState3, actionType, playbackState2);
        ActionType actionType5 = ActionType.AdPause;
        PlaybackState playbackState5 = PlaybackState.AdPaused;
        addTransition(playbackState3, actionType5, playbackState5);
        ActionType actionType6 = ActionType.AdResume;
        addTransition(playbackState5, actionType6, playbackState3);
        addTransition(playbackState5, actionType4, playbackState2);
        addTransition(playbackState5, actionType, playbackState2);
        addTransition(playbackState2, actionType2, playbackState3);
        ActionType actionType7 = ActionType.MainPause;
        PlaybackState playbackState6 = PlaybackState.MainPaused;
        addTransition(playbackState2, actionType7, playbackState6);
        addTransition(playbackState6, actionType4, playbackState2);
        addTransition(playbackState6, actionType2, playbackState3);
        addTransition(playbackState6, actionType6, playbackState3);
        addTransition(playbackState6, actionType, playbackState2);
        for (PlaybackState playbackState7 : PlaybackState.values()) {
            PlaybackState playbackState8 = PlaybackState.Uninitialized;
            if (playbackState7 != playbackState8) {
                addTransition(playbackState7, ActionType.MainStop, playbackState8);
            }
        }
    }

    @Nonnull
    public PlaybackState getPlayerCurrentState() {
        return this.mPlayerCurrentState;
    }

    @VisibleForTesting
    AloysiusPlaybackStateTracker() {
    }

    public static AloysiusPlaybackStateTracker getInstance() {
        return mInstance;
    }

    private static void addTransition(@Nonnull PlaybackState playbackState, @Nonnull ActionType actionType, @Nonnull PlaybackState playbackState2) {
        Preconditions.checkNotNull(playbackState, "originalState");
        Preconditions.checkNotNull(actionType, "actionType");
        Preconditions.checkNotNull(playbackState2, "newState");
        Table<PlaybackState, ActionType, PlaybackState> table = mTransitionTable;
        synchronized (table) {
            table.put(playbackState, actionType, playbackState2);
        }
    }

    @Nullable
    static PlaybackState getNextState(@Nonnull PlaybackState playbackState, @Nonnull ActionType actionType) {
        Preconditions.checkNotNull(playbackState, "state");
        Preconditions.checkNotNull(actionType, "actionType");
        Table<PlaybackState, ActionType, PlaybackState> table = mTransitionTable;
        synchronized (table) {
            if (!table.contains(playbackState, actionType)) {
                DLog.warnf("In state: %s missing action for: %s", playbackState, actionType);
                return null;
            }
            return table.get(playbackState, actionType);
        }
    }

    @Nullable
    public TransitioningTo trigger(@Nonnull ActionType actionType) {
        PlaybackState nextState = getNextState(this.mPlayerCurrentState, (ActionType) Preconditions.checkNotNull(actionType, "actionType"));
        DLog.devf("current state = %s action = %s, new state = %s", this.mPlayerCurrentState.toString(), actionType.toString(), String.valueOf(nextState));
        if (nextState == null) {
            return null;
        }
        this.mPlayerCurrentState = nextState;
        return (nextState == PlaybackState.AdPlaying || nextState == PlaybackState.AdPaused) ? TransitioningTo.AdPlayer : TransitioningTo.MainPlayer;
    }
}
