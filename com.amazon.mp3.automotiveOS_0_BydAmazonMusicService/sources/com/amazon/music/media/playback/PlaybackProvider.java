package com.amazon.music.media.playback;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.media.VolumeProviderCompat;
import java.io.Closeable;
import java.util.EnumSet;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public interface PlaybackProvider {

    public interface Connection extends Closeable {
        void addOnMetadataChangedListener(OnMetadataChangedListener onMetadataChangedListener);

        void addOnPlayStateChangedListener(OnPlayStateChangedListener onPlayStateChangedListener);

        void addOnPlaybackErrorListener(OnPlaybackErrorListener onPlaybackErrorListener);

        void addOnPlaybackEventListener(OnPlaybackEventListener onPlaybackEventListener);

        void autoResolveNextConcurrencyCheck();

        boolean canFastForward();

        boolean canPlay();

        boolean canRateMediaItems();

        boolean canRestartItem();

        boolean canRewind();

        boolean canSeek();

        boolean canShuffle();

        boolean canSkipNext();

        boolean canSkipPrevious();

        boolean canSkipToIndex();

        @Override // java.io.Closeable, java.lang.AutoCloseable
        void close();

        void fastForward(ControlSource controlSource);

        long getAmountBuffered();

        int getAudioFocusStatus();

        EnumSet<RepeatMode> getAvailableRepeatModes();

        int getCurrentIndex();

        MediaItem getCurrentMediaItem();

        @NonNull
        PlaybackAttributes getCurrentPlaybackAttributes();

        List<MediaItem> getCurrentQueue();

        long getDurationMillis();

        @NonNull
        PlaybackAttributes getDynamicBestAvailablePlaybackAttributes();

        MediaCollectionInfo getMediaCollectionInfo();

        String getMediaPlayerName();

        PlayStatus getPlayStatus();

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

        void normalSpeed(ControlSource controlSource);

        void onUserInteraction(ControlSource controlSource);

        void pause(ControlSource controlSource);

        MediaItem peek(int i);

        void play(ControlSource controlSource, long j);

        boolean prefersRestartOverPrevious();

        void removeOnMetadataChangedListener(OnMetadataChangedListener onMetadataChangedListener);

        void removeOnPlayStateChangedListener(OnPlayStateChangedListener onPlayStateChangedListener);

        void removeOnPlaybackErrorListener(OnPlaybackErrorListener onPlaybackErrorListener);

        void removeOnPlaybackEventListener(OnPlaybackEventListener onPlaybackEventListener);

        void resolveMaxConcurrency(String str, boolean z, ControlSource controlSource, long j);

        void restartMediaItem(ControlSource controlSource);

        void rewind(ControlSource controlSource);

        void seek(long j, ChangeReason changeReason, ControlSource controlSource);

        void setPlaybackSpeed(double d);

        void setRepeatMode(RepeatMode repeatMode, ControlSource controlSource);

        void setShuffled(boolean z, boolean z2, ControlSource controlSource);

        boolean shouldPauseWhenTaskRemoved();

        void skip(int i, ChangeReason changeReason, ControlSource controlSource);

        void skipToIndex(int i, ControlSource controlSource);

        void stop(ChangeReason changeReason, ControlSource controlSource);
    }

    Connection openConnection(@NonNull Playback playback);
}
