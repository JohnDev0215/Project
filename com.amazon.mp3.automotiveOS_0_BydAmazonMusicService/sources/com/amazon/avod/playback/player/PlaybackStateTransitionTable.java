package com.amazon.avod.playback.player;

import com.amazon.avod.playback.player.actions.ActionType;
import com.amazon.avod.playback.player.states.PlaybackState;
import com.amazon.avod.util.DLog;
import com.google.common.collect.ArrayTable;
import com.google.common.collect.Table;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackStateTransitionTable {
    private static final Table<PlaybackState, ActionType, PlaybackState> mTransitionTable = ArrayTable.create(Arrays.asList(PlaybackState.values()), Arrays.asList(ActionType.values()));

    static {
        PlaybackState playbackState = PlaybackState.Uninitialized;
        ActionType actionType = ActionType.Initialize;
        PlaybackState playbackState2 = PlaybackState.Initializing;
        addTransition(playbackState, actionType, playbackState2);
        ActionType actionType2 = ActionType.InitialLoad;
        PlaybackState playbackState3 = PlaybackState.Loading;
        addTransition(playbackState2, actionType2, playbackState3);
        ActionType actionType3 = ActionType.LaunchPlayback;
        PlaybackState playbackState4 = PlaybackState.Launching;
        addTransition(playbackState3, actionType3, playbackState4);
        ActionType actionType4 = ActionType.Play;
        PlaybackState playbackState5 = PlaybackState.Playing;
        addTransition(playbackState4, actionType4, playbackState5);
        PlaybackState playbackState6 = PlaybackState.Buffering;
        addTransition(playbackState6, actionType4, playbackState5);
        ActionType actionType5 = ActionType.Seek;
        PlaybackState playbackState7 = PlaybackState.Seeking;
        addTransition(playbackState6, actionType5, playbackState7);
        ActionType actionType6 = ActionType.Pause;
        PlaybackState playbackState8 = PlaybackState.Paused;
        addTransition(playbackState6, actionType6, playbackState8);
        addTransition(playbackState8, actionType4, playbackState5);
        addTransition(playbackState8, actionType5, playbackState7);
        addTransition(playbackState5, ActionType.Buffer, playbackState6);
        addTransition(playbackState5, actionType6, playbackState8);
        addTransition(playbackState5, actionType5, playbackState7);
        addTransition(playbackState5, ActionType.Shutdown, PlaybackState.Shutdown);
        addTransition(playbackState7, actionType6, playbackState8);
        addTransition(playbackState7, actionType4, playbackState5);
        addTransition(playbackState7, actionType5, playbackState7);
        for (PlaybackState playbackState9 : PlaybackState.values()) {
            PlaybackState playbackState10 = PlaybackState.Shutdown;
            if (playbackState9 != playbackState10) {
                addTransition(playbackState9, ActionType.Shutdown, playbackState10);
            }
        }
    }

    public static void addTransition(PlaybackState playbackState, ActionType actionType, PlaybackState playbackState2) {
        Table<PlaybackState, ActionType, PlaybackState> table = mTransitionTable;
        synchronized (table) {
            table.put(playbackState, actionType, playbackState2);
        }
    }

    public static PlaybackState getNextState(PlaybackState playbackState, ActionType actionType) {
        Table<PlaybackState, ActionType, PlaybackState> table = mTransitionTable;
        synchronized (table) {
            if (table.get(playbackState, actionType) == null) {
                DLog.warnf("In state: %s missing action for: %s", playbackState, actionType);
                return null;
            }
            return table.get(playbackState, actionType);
        }
    }
}
