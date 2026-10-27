package com.amazon.music.voice.playbackstate;

import com.amazon.alexa.voice.core.AudioPlayer;

/* JADX INFO: loaded from: classes4.dex */
public interface PlayerStateProvider {

    public enum FavoriteState {
        NOT_RATED,
        FAVORITED,
        UNFAVORITED
    }

    public enum RepeatMode {
        REPEATED,
        ONE_REPEATED,
        NOT_REPEATED
    }

    public enum ShuffleMode {
        SHUFFLED,
        NOT_SHUFFLED
    }

    void add(AudioPlayer.Callback callback);

    FavoriteState getFavoriteState();

    MediaMetadata getMedia();

    String getPlaybackSessionId();

    PlayerState getPlayerState();

    RepeatMode getRepeatMode();

    ShuffleMode getShuffleMode();

    int getTrackPositionMs();

    public enum PlayerState {
        STOPPED,
        PAUSED,
        BUFFER_UNDERRUN,
        PLAYING,
        FINISHED;

        @Override // java.lang.Enum
        public String toString() {
            return name();
        }
    }
}
