package com.amazon.music.media.playback.impl;

import UpsellInterface.v1_0.PlaybackMode;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.media.VolumeProviderCompat;
import com.amazon.music.media.playback.ActionAvailability;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.OnMetadataChangedListener;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import com.amazon.music.media.playback.OnPlaybackErrorListener;
import com.amazon.music.media.playback.OnPlaybackEventListener;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackAttributes;
import com.amazon.music.media.playback.PlaybackProvider;
import com.amazon.music.media.playback.PlayerCapabilities;
import com.amazon.music.media.playback.RepeatMode;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class NullPlaybackProvider implements PlaybackProvider {
    public static final NullPlaybackProvider INSTANCE = new NullPlaybackProvider();
    private final PlaybackProvider.Connection connection = new PlaybackProvider.Connection() { // from class: com.amazon.music.media.playback.impl.NullPlaybackProvider.1
        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void addOnMetadataChangedListener(OnMetadataChangedListener onMetadataChangedListener) {
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void addOnPlayStateChangedListener(OnPlayStateChangedListener onPlayStateChangedListener) {
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void addOnPlaybackErrorListener(OnPlaybackErrorListener onPlaybackErrorListener) {
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void addOnPlaybackEventListener(OnPlaybackEventListener onPlaybackEventListener) {
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void autoResolveNextConcurrencyCheck() {
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public boolean canFastForward() {
            return false;
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public boolean canPlay() {
            return false;
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public boolean canRateMediaItems() {
            return false;
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public boolean canRestartItem() {
            return false;
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public boolean canRewind() {
            return false;
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public boolean canSeek() {
            return false;
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public boolean canShuffle() {
            return false;
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public boolean canSkipNext() {
            return false;
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public boolean canSkipPrevious() {
            return false;
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public boolean canSkipToIndex() {
            return false;
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void fastForward(ControlSource controlSource) {
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public long getAmountBuffered() {
            return 0L;
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public int getAudioFocusStatus() {
            return -1;
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public int getCurrentIndex() {
            return 0;
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public MediaItem getCurrentMediaItem() {
            return null;
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public long getDurationMillis() {
            return 0L;
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public MediaCollectionInfo getMediaCollectionInfo() {
            return null;
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public String getMediaPlayerName() {
            return PlaybackMode.NONE;
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public float getPlaybackSpeed() {
            return 1.0f;
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        @Nullable
        public PlayerCapabilities getPlayerCapabilities() {
            return null;
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public long getPositionMillis() {
            return 0L;
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public int getUnshuffledIndex(int i) {
            return 0;
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public VolumeProviderCompat getVolumeProvider() {
            return null;
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public boolean isLive() {
            return false;
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public boolean isShuffled() {
            return false;
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public boolean isStopped() {
            return true;
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public boolean isStreaming() {
            return true;
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void normalSpeed(ControlSource controlSource) {
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void onUserInteraction(ControlSource controlSource) {
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void pause(ControlSource controlSource) {
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public MediaItem peek(int i) {
            return null;
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void play(ControlSource controlSource, long j) {
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public boolean prefersRestartOverPrevious() {
            return false;
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void removeOnMetadataChangedListener(OnMetadataChangedListener onMetadataChangedListener) {
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void removeOnPlayStateChangedListener(OnPlayStateChangedListener onPlayStateChangedListener) {
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void removeOnPlaybackErrorListener(OnPlaybackErrorListener onPlaybackErrorListener) {
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void removeOnPlaybackEventListener(OnPlaybackEventListener onPlaybackEventListener) {
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void resolveMaxConcurrency(String str, boolean z, ControlSource controlSource, long j) {
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void restartMediaItem(ControlSource controlSource) {
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void rewind(ControlSource controlSource) {
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void seek(long j, ChangeReason changeReason, ControlSource controlSource) {
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void setPlaybackSpeed(double d) {
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void setRepeatMode(RepeatMode repeatMode, ControlSource controlSource) {
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void setShuffled(boolean z, boolean z2, ControlSource controlSource) {
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public boolean shouldPauseWhenTaskRemoved() {
            return true;
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void skip(int i, ChangeReason changeReason, ControlSource controlSource) {
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void skipToIndex(int i, ControlSource controlSource) {
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void stop(ChangeReason changeReason, ControlSource controlSource) {
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        @NonNull
        public PlaybackAttributes getCurrentPlaybackAttributes() {
            return PlaybackAttributes.UNKNOWN;
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        @NonNull
        public PlaybackAttributes getDynamicBestAvailablePlaybackAttributes() {
            return PlaybackAttributes.UNKNOWN;
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public PlayStatus getPlayStatus() {
            return PlayStatus.USER_PAUSED;
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public ActionAvailability getSkipNextAvailability() {
            return ActionAvailability.UNAVAILABLE;
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public RepeatMode getRepeatMode() {
            return RepeatMode.REPEAT_NONE;
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public EnumSet<RepeatMode> getAvailableRepeatModes() {
            return RepeatMode.REPEAT_NONE_ONLY;
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public List<MediaItem> getCurrentQueue() {
            return Collections.emptyList();
        }
    };

    private NullPlaybackProvider() {
    }

    @Override // com.amazon.music.media.playback.PlaybackProvider
    public PlaybackProvider.Connection openConnection(@NonNull Playback playback) {
        return this.connection;
    }
}
