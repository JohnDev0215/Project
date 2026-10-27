package com.amazon.music.media.playback;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.media.VolumeProviderCompat;
import java.util.EnumSet;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public interface PlaybackController {
    void addOnMetadataChangedListener(OnMetadataChangedListener onMetadataChangedListener);

    void addOnPlayStateChangedListener(OnPlayStateChangedListener onPlayStateChangedListener);

    void addOnPlaybackErrorListener(OnPlaybackErrorListener onPlaybackErrorListener);

    void addOnPlaybackEventListener(OnPlaybackEventListener onPlaybackEventListener);

    void autoResolveNextConcurrencyCheck();

    boolean canFastForward();

    boolean canPlay();

    boolean canRateCurrentItem();

    boolean canRateMediaItems();

    boolean canRestartItem();

    boolean canRewind();

    boolean canSeek();

    boolean canShuffle();

    boolean canSkipNext();

    boolean canSkipPrevious();

    boolean canSkipToIndex();

    void fastForward();

    long getAmountBuffered();

    int getAudioFocusStatus();

    EnumSet<RepeatMode> getAvailableRepeatModes();

    @NonNull
    PlaybackAttributes getBestAvailablePlaybackAttributes();

    ControlSource getControlSource();

    int getCurrentIndex();

    int getCurrentItemRating();

    MediaItem getCurrentMediaItem();

    @NonNull
    PlaybackAttributes getCurrentPlaybackAttributes();

    List<MediaItem> getCurrentQueue();

    long getDurationMillis();

    MediaCollectionInfo getMediaCollectionInfo();

    String getMediaPlayerName();

    PlayStatus getPlayStatus();

    Playback getPlayback();

    float getPlaybackSpeed();

    @Nullable
    PlayerCapabilities getPlayerCapabilities();

    long getPositionMillis();

    RepeatMode getRepeatMode();

    ActionAvailability getSkipNextAvailability();

    int getUnshuffledIndex(int i);

    VolumeProviderCompat getVolumeProvider();

    boolean isLive();

    boolean isShuffled();

    boolean isStopped();

    boolean isStreaming();

    void normalSpeed();

    void onUserInteraction();

    void pause();

    MediaItem peek(int i);

    void play();

    void play(long j);

    void previousOrRestart();

    void removeOnMetadataChangedListener(OnMetadataChangedListener onMetadataChangedListener);

    void removeOnPlayStateChangedListener(OnPlayStateChangedListener onPlayStateChangedListener);

    void removeOnPlaybackErrorListener(OnPlaybackErrorListener onPlaybackErrorListener);

    void removeOnPlaybackEventListener(OnPlaybackEventListener onPlaybackEventListener);

    void resolveMaxConcurrency(String str, boolean z, long j);

    void restartMediaItem();

    void rewind();

    void seek(long j, int i);

    void setCurrentItemRating(int i);

    void setPlaybackSpeed(double d);

    void setRepeatMode(RepeatMode repeatMode);

    void setShuffled(boolean z);

    boolean shouldPauseWhenTaskRemoved();

    void skip(int i);

    void skipNext();

    void skipPrevious();

    void skipToIndex(int i);

    void stop(ChangeReason changeReason);

    void togglePlaying();

    void toggleRepeatMode();

    int toggleThumbsDown();

    int toggleThumbsUp();
}
