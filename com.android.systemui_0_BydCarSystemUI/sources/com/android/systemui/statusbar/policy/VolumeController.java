package com.android.systemui.statusbar.policy;

import android.media.AudioFocusInfo;
import android.os.Handler;
import android.util.SparseArray;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface VolumeController {

    public interface Callbacks {
        default void onAllMuteState(boolean isAllMute) {
        }

        default void onShowRequested(State state, boolean isFromMute, int flags) {
        }

        default void onStateChanged(boolean showUI, State state, boolean isFromMute, int flags) {
        }

        default void voiceBarVisibleChange(boolean show) {
        }
    }

    void addCallback(Callbacks callbacks, Handler handler);

    boolean getAllMute();

    List<AudioFocusInfo> getCurAudioFocusInfos();

    int getGroupVolume(int groupId);

    int getGroupVolume(int zoneId, int groupId);

    int getMediaVolume();

    int getNaviVolume();

    void getState();

    boolean getStreamMute(int stream);

    int getVoiceVolume();

    int getVolumeGroupCount();

    int getVolumeGroupIdForUsage(int usage);

    int getVolumeGroupIdForUsage(int zoneId, int usage);

    State getVolumeState();

    boolean isVolumeGroupMuted(int zoneId, int groupId);

    void removeCallback(Callbacks callbacks);

    void setAllMute(boolean mute);

    void setGroupVolume(int groupId, int index, int flags);

    void setGroupVolume(int zoneId, int groupId, int index, int flags);

    void setStreamMute(int stream, boolean mute);

    void setStreamVolume(int stream, int userLevel);

    void setVolumeGroupMute(int zoneId, int groupId, boolean mute, int flags);

    public static final class StreamState {
        public int level;
        public int levelMax;
        public int levelMin;
        public boolean muted;

        public StreamState copy() {
            StreamState streamState = new StreamState();
            streamState.level = this.level;
            streamState.levelMin = this.levelMin;
            streamState.levelMax = this.levelMax;
            streamState.muted = this.muted;
            return streamState;
        }
    }

    public static final class State {
        public static int NO_ACTIVE_STREAM = -1;
        public final SparseArray<StreamState> states = new SparseArray<>();
        public int activeStream = NO_ACTIVE_STREAM;

        public State copy() {
            State state = new State();
            for (int i = 0; i < this.states.size(); i++) {
                state.states.put(this.states.keyAt(i), this.states.valueAt(i).copy());
            }
            state.activeStream = this.activeStream;
            return state;
        }
    }
}
