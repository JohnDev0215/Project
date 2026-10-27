package com.amazon.music.playbacknotification;

/* JADX INFO: loaded from: classes4.dex */
public interface PlaybackNotificationInterface {
    void addTrackToLibrary();

    boolean canRate();

    boolean canSkipNext();

    boolean canSkipPrevious();

    PlaybackNotificationError getPlaybackError();

    boolean hasNext();

    boolean hasPlaybackError();

    boolean isMediaInLibrary();

    boolean isPlaying();

    boolean isThumbedDown();

    boolean isThumbedUp();

    void next();

    boolean nowPlayingHasSongs();

    void pause();

    void play();

    void prev();

    void stopForeground();

    void thumbsDown();

    void thumbsUp();
}
