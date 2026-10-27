package com.amazon.music.media.playback.impl;

import androidx.annotation.NonNull;
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
import com.amazon.music.media.playback.PlaybackException;
import com.amazon.music.media.playback.PlaybackProvider;
import com.amazon.music.media.playback.PlayerCapabilities;
import com.amazon.music.media.playback.RepeatMode;
import com.amazon.music.media.playback.config.PlaybackConfig;
import com.amazon.music.media.playback.metrics.mts.MTSMetricsSender;
import com.amazon.music.media.playback.util.ListenerSet;
import com.amazon.music.media.playback.util.StrictMode;
import com.amazon.music.media.playback.util.ThreadUtils;
import java.util.EnumSet;
import java.util.List;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public abstract class AbstractPlaybackProvider implements PlaybackProvider {
    private static final Logger logger = LoggerFactory.getLogger("AbstractPlaybackProvider");
    private Connection connection;
    private Playback playback;
    private PlaybackConfig playbackConfig;

    protected abstract void autoResolveNextConcurrencyCheck();

    protected abstract boolean canFastForward();

    protected abstract boolean canPlay();

    protected abstract boolean canRateMediaItems();

    protected abstract boolean canRestartItem();

    protected abstract boolean canRewind();

    protected abstract boolean canSeek();

    protected abstract boolean canShuffle();

    protected abstract boolean canSkipNext();

    protected abstract boolean canSkipPrevious();

    protected abstract boolean canSkipToIndex();

    protected abstract void close();

    protected abstract void fastForward(ControlSource controlSource);

    protected abstract long getAmountBuffered();

    protected abstract EnumSet<RepeatMode> getAvailableRepeatModes();

    protected abstract int getCurrentIndex();

    protected abstract MediaItem getCurrentMediaItem();

    protected abstract PlaybackAttributes getCurrentPlaybackAttributes();

    protected abstract List<MediaItem> getCurrentQueue();

    protected abstract long getDurationMillis();

    protected abstract MediaCollectionInfo getMediaCollectionInfo();

    protected abstract String getMediaPlayerName();

    protected abstract PlayStatus getPlayStatus();

    protected abstract float getPlaybackSpeed();

    protected abstract PlayerCapabilities getPlayerCapabilities();

    protected abstract long getPositionMillis();

    protected abstract RepeatMode getRepeatMode();

    protected abstract ActionAvailability getSkipNextAvailability();

    protected abstract int getUnshuffledIndex(int i);

    protected abstract VolumeProviderCompat getVolumeProvider();

    protected abstract boolean isLive();

    protected abstract boolean isShuffled();

    protected abstract boolean isStopped();

    protected abstract boolean isStreaming();

    protected abstract void normalSpeed(ControlSource controlSource);

    protected abstract void onUserInteraction(ControlSource controlSource);

    protected abstract void open();

    protected abstract void pause(ControlSource controlSource);

    protected abstract MediaItem peek(int i);

    protected abstract void play(ControlSource controlSource, long j);

    protected abstract boolean prefersRestartOverPrevious();

    protected abstract void resolveMaxConcurrency(String str, boolean z, ControlSource controlSource, long j);

    protected abstract void restartMediaItem(ControlSource controlSource);

    protected abstract void rewind(ControlSource controlSource);

    protected abstract void seek(long j, ChangeReason changeReason, ControlSource controlSource);

    protected abstract void setPlaybackSpeed(double d);

    protected abstract void setRepeatMode(RepeatMode repeatMode, ControlSource controlSource);

    protected abstract void setShuffled(boolean z, boolean z2, ControlSource controlSource);

    protected abstract boolean shouldPauseWhenTaskRemoved();

    protected abstract void skip(int i, ChangeReason changeReason, ControlSource controlSource);

    protected abstract void skipToIndex(int i, ControlSource controlSource);

    protected abstract void stop(ChangeReason changeReason, ControlSource controlSource);

    protected class Connection implements PlaybackProvider.Connection {
        private final ListenerSet<OnPlaybackErrorListener> errorListeners;
        private final ListenerSet<OnPlaybackEventListener> eventListeners;
        private final ListenerSet<OnMetadataChangedListener> metadataListeners;
        private final ListenerSet<OnPlayStateChangedListener> stateListeners;

        public Connection() {
            if (!AbstractPlaybackProvider.this.getPlaybackConfig().isUnitTestMode()) {
                if (!ThreadUtils.isOnMainThread()) {
                    throw new IllegalThreadStateException("Can only create " + getClass().getSimpleName() + " from the main thread.");
                }
                ListenerSet.DispatchStrategy dispatchStrategy = ListenerSet.DispatchStrategy.MAIN_THREAD;
                this.errorListeners = new ListenerSet<>(OnPlaybackErrorListener.class, dispatchStrategy, ListenerSet.DuplicateStrategy.ALLOW_DUPLICATES);
                ListenerSet.DuplicateStrategy duplicateStrategy = ListenerSet.DuplicateStrategy.REMOVE_DUPLICATES;
                this.stateListeners = new ListenerSet<>(OnPlayStateChangedListener.class, dispatchStrategy, duplicateStrategy);
                this.eventListeners = new ListenerSet<>(OnPlaybackEventListener.class, dispatchStrategy, duplicateStrategy);
                this.metadataListeners = new ListenerSet<>(OnMetadataChangedListener.class, dispatchStrategy, duplicateStrategy);
                return;
            }
            ListenerSet.DispatchStrategy dispatchStrategy2 = ListenerSet.DispatchStrategy.SYNCHRONOUS;
            this.errorListeners = new ListenerSet<>(OnPlaybackErrorListener.class, dispatchStrategy2, ListenerSet.DuplicateStrategy.ALLOW_DUPLICATES);
            ListenerSet.DuplicateStrategy duplicateStrategy2 = ListenerSet.DuplicateStrategy.REMOVE_DUPLICATES;
            this.stateListeners = new ListenerSet<>(OnPlayStateChangedListener.class, dispatchStrategy2, duplicateStrategy2);
            this.eventListeners = new ListenerSet<>(OnPlaybackEventListener.class, dispatchStrategy2, duplicateStrategy2);
            this.metadataListeners = new ListenerSet<>(OnMetadataChangedListener.class, dispatchStrategy2, duplicateStrategy2);
        }

        private void checkCurrent() {
            if (AbstractPlaybackProvider.this.connection != this) {
                StrictMode.crashIfStrict("Closed.");
            }
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            checkCurrent();
            if (AbstractPlaybackProvider.this.getPlayStatus().willPlay()) {
                StrictMode.crashIfStrict("close() called while PlayStatus is " + getPlayStatus() + " - need to stop playback first.");
            }
            AbstractPlaybackProvider.this.close();
            AbstractPlaybackProvider.this.connection = null;
            AbstractPlaybackProvider.this.playback = null;
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void stop(ChangeReason changeReason, ControlSource controlSource) {
            checkCurrent();
            AbstractPlaybackProvider.this.stop(changeReason, controlSource);
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void onUserInteraction(ControlSource controlSource) {
            checkCurrent();
            AbstractPlaybackProvider.this.onUserInteraction(controlSource);
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void play(ControlSource controlSource, long j) {
            checkCurrent();
            AbstractPlaybackProvider.this.play(controlSource, j);
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public PlaybackAttributes getCurrentPlaybackAttributes() {
            checkCurrent();
            return AbstractPlaybackProvider.this.getCurrentPlaybackAttributes();
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public PlaybackAttributes getDynamicBestAvailablePlaybackAttributes() {
            checkCurrent();
            return AbstractPlaybackProvider.this.getDynamicBestAvailablePlaybackAttributes();
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public boolean isStreaming() {
            checkCurrent();
            return AbstractPlaybackProvider.this.isStreaming();
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public PlayerCapabilities getPlayerCapabilities() {
            checkCurrent();
            return AbstractPlaybackProvider.this.getPlayerCapabilities();
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void pause(ControlSource controlSource) {
            checkCurrent();
            AbstractPlaybackProvider.this.pause(controlSource);
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public boolean canPlay() {
            checkCurrent();
            return AbstractPlaybackProvider.this.canPlay();
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public PlayStatus getPlayStatus() {
            checkCurrent();
            return AbstractPlaybackProvider.this.getPlayStatus();
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void skip(int i, ChangeReason changeReason, ControlSource controlSource) {
            checkCurrent();
            AbstractPlaybackProvider.this.skip(i, changeReason, controlSource);
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void skipToIndex(int i, ControlSource controlSource) {
            checkCurrent();
            AbstractPlaybackProvider.this.skipToIndex(i, controlSource);
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public float getPlaybackSpeed() {
            checkCurrent();
            return AbstractPlaybackProvider.this.getPlaybackSpeed();
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void normalSpeed(ControlSource controlSource) {
            checkCurrent();
            AbstractPlaybackProvider.this.normalSpeed(controlSource);
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void fastForward(ControlSource controlSource) {
            checkCurrent();
            AbstractPlaybackProvider.this.fastForward(controlSource);
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void rewind(ControlSource controlSource) {
            checkCurrent();
            AbstractPlaybackProvider.this.rewind(controlSource);
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public boolean canFastForward() {
            checkCurrent();
            return AbstractPlaybackProvider.this.canFastForward();
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public boolean canRewind() {
            checkCurrent();
            return AbstractPlaybackProvider.this.canRewind();
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void restartMediaItem(ControlSource controlSource) {
            checkCurrent();
            AbstractPlaybackProvider.this.restartMediaItem(controlSource);
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public boolean canSkipPrevious() {
            checkCurrent();
            return AbstractPlaybackProvider.this.canSkipPrevious();
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public boolean canRestartItem() {
            checkCurrent();
            return AbstractPlaybackProvider.this.canRestartItem();
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public boolean prefersRestartOverPrevious() {
            checkCurrent();
            return AbstractPlaybackProvider.this.prefersRestartOverPrevious();
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public ActionAvailability getSkipNextAvailability() {
            checkCurrent();
            return AbstractPlaybackProvider.this.getSkipNextAvailability();
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public boolean canSkipNext() {
            checkCurrent();
            return AbstractPlaybackProvider.this.canSkipNext();
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public boolean canSkipToIndex() {
            checkCurrent();
            return AbstractPlaybackProvider.this.canSkipToIndex();
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public boolean canShuffle() {
            checkCurrent();
            return AbstractPlaybackProvider.this.canShuffle();
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public boolean isShuffled() {
            checkCurrent();
            return AbstractPlaybackProvider.this.isShuffled();
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void setShuffled(boolean z, boolean z2, ControlSource controlSource) {
            checkCurrent();
            AbstractPlaybackProvider.this.setShuffled(z, z2, controlSource);
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public RepeatMode getRepeatMode() {
            checkCurrent();
            return AbstractPlaybackProvider.this.getRepeatMode();
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void setRepeatMode(RepeatMode repeatMode, ControlSource controlSource) {
            checkCurrent();
            AbstractPlaybackProvider.this.setRepeatMode(repeatMode, controlSource);
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public EnumSet<RepeatMode> getAvailableRepeatModes() {
            checkCurrent();
            return AbstractPlaybackProvider.this.getAvailableRepeatModes();
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public boolean canSeek() {
            checkCurrent();
            return AbstractPlaybackProvider.this.canSeek();
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void seek(long j, ChangeReason changeReason, ControlSource controlSource) {
            checkCurrent();
            AbstractPlaybackProvider.this.seek(j, changeReason, controlSource);
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void autoResolveNextConcurrencyCheck() {
            checkCurrent();
            AbstractPlaybackProvider.this.autoResolveNextConcurrencyCheck();
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void resolveMaxConcurrency(String str, boolean z, ControlSource controlSource, long j) {
            checkCurrent();
            AbstractPlaybackProvider.this.resolveMaxConcurrency(str, z, controlSource, j);
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void addOnPlayStateChangedListener(OnPlayStateChangedListener onPlayStateChangedListener) {
            this.stateListeners.add(onPlayStateChangedListener);
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void removeOnPlayStateChangedListener(OnPlayStateChangedListener onPlayStateChangedListener) {
            this.stateListeners.remove(onPlayStateChangedListener);
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void addOnMetadataChangedListener(OnMetadataChangedListener onMetadataChangedListener) {
            this.metadataListeners.add(onMetadataChangedListener);
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void removeOnMetadataChangedListener(OnMetadataChangedListener onMetadataChangedListener) {
            this.metadataListeners.remove(onMetadataChangedListener);
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void addOnPlaybackErrorListener(OnPlaybackErrorListener onPlaybackErrorListener) {
            this.errorListeners.add(onPlaybackErrorListener);
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void removeOnPlaybackErrorListener(OnPlaybackErrorListener onPlaybackErrorListener) {
            this.errorListeners.remove(onPlaybackErrorListener);
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void addOnPlaybackEventListener(OnPlaybackEventListener onPlaybackEventListener) {
            this.eventListeners.add(onPlaybackEventListener);
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void removeOnPlaybackEventListener(OnPlaybackEventListener onPlaybackEventListener) {
            this.eventListeners.remove(onPlaybackEventListener);
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public MediaItem getCurrentMediaItem() {
            checkCurrent();
            return AbstractPlaybackProvider.this.getCurrentMediaItem();
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public long getAmountBuffered() {
            checkCurrent();
            return AbstractPlaybackProvider.this.getAmountBuffered();
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public long getPositionMillis() {
            checkCurrent();
            return AbstractPlaybackProvider.this.getPositionMillis();
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public long getDurationMillis() {
            checkCurrent();
            return AbstractPlaybackProvider.this.getDurationMillis();
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public MediaItem peek(int i) {
            checkCurrent();
            return AbstractPlaybackProvider.this.peek(i);
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public List<MediaItem> getCurrentQueue() {
            checkCurrent();
            return AbstractPlaybackProvider.this.getCurrentQueue();
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public int getAudioFocusStatus() {
            checkCurrent();
            return AbstractPlaybackProvider.this.getAudioFocusStatus();
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public boolean isLive() {
            checkCurrent();
            return AbstractPlaybackProvider.this.isLive();
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public int getCurrentIndex() {
            checkCurrent();
            return AbstractPlaybackProvider.this.getCurrentIndex();
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public int getUnshuffledIndex(int i) {
            checkCurrent();
            return AbstractPlaybackProvider.this.getUnshuffledIndex(i);
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public VolumeProviderCompat getVolumeProvider() {
            checkCurrent();
            return AbstractPlaybackProvider.this.getVolumeProvider();
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public boolean canRateMediaItems() {
            checkCurrent();
            return AbstractPlaybackProvider.this.canRateMediaItems();
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public String getMediaPlayerName() {
            return AbstractPlaybackProvider.this.getMediaPlayerName();
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public MediaCollectionInfo getMediaCollectionInfo() {
            return AbstractPlaybackProvider.this.getMediaCollectionInfo();
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public boolean isStopped() {
            return AbstractPlaybackProvider.this.isStopped();
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public boolean shouldPauseWhenTaskRemoved() {
            return AbstractPlaybackProvider.this.shouldPauseWhenTaskRemoved();
        }

        @Override // com.amazon.music.media.playback.PlaybackProvider.Connection
        public void setPlaybackSpeed(double d) {
            checkCurrent();
            AbstractPlaybackProvider.this.setPlaybackSpeed(d);
        }
    }

    protected AbstractPlaybackProvider(PlaybackConfig playbackConfig) {
        this.playbackConfig = playbackConfig;
    }

    @Override // com.amazon.music.media.playback.PlaybackProvider
    public final Connection openConnection(@NonNull Playback playback) {
        if (this.connection != null) {
            throw new IllegalStateException("Already open.");
        }
        Validate.notNull(playback);
        this.playback = playback;
        this.playbackConfig = playback.getPlaybackConfig();
        this.connection = new Connection();
        open();
        return this.connection;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void notifyPlayStateChanged() {
        Connection connection = this.connection;
        if (connection != null) {
            ((OnPlayStateChangedListener) connection.stateListeners.proxy()).onPlayStateChanged();
        }
    }

    protected void notifyPlaybackError(PlaybackException playbackException) {
        if (this.connection != null) {
            logger.error("notifyPlaybackError", (Throwable) playbackException);
            MTSMetricsSender mTSMetricsSender = getPlaybackConfig().getMTSMetricsSender();
            if (mTSMetricsSender != null) {
                mTSMetricsSender.sendPlaybackException(playbackException);
            }
            ((OnPlaybackErrorListener) this.connection.errorListeners.proxy()).onPlaybackError(playbackException);
            notifyPlaybackEvent(13, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void notifyPlaybackEvent(int i, ControlSource controlSource) {
        if (this.connection != null) {
            logger.info("notifyPlaybackEvent(" + getEventName(i) + ", " + controlSource + ")");
            OnPlaybackEventListener onPlaybackEventListener = (OnPlaybackEventListener) this.connection.eventListeners.proxy();
            if (controlSource == null) {
                controlSource = ControlSource.NONE;
            }
            onPlaybackEventListener.onPlaybackEvent(i, controlSource);
        }
    }

    protected void notifyMetadataChanged(MediaItem mediaItem) {
        Connection connection = this.connection;
        if (connection != null) {
            ((OnMetadataChangedListener) connection.metadataListeners.proxy()).onMetadataChanged(mediaItem);
        }
    }

    protected void notifyMediaCollectionInfoChanged(MediaCollectionInfo mediaCollectionInfo) {
        Connection connection = this.connection;
        if (connection != null) {
            ((OnMetadataChangedListener) connection.metadataListeners.proxy()).onMediaCollectionInfoChanged(mediaCollectionInfo);
        }
    }

    protected Playback getPlayback() {
        return this.playback;
    }

    protected PlaybackConfig getPlaybackConfig() {
        return this.playbackConfig;
    }

    protected AudioFocusManager getAudioFocusManager() {
        Playback playback = this.playback;
        if (playback == null) {
            return null;
        }
        return playback.getAudioFocusManager();
    }

    protected Connection getConnection() {
        return this.connection;
    }

    public int getAudioFocusStatus() {
        AudioFocusManager audioFocusManager = getAudioFocusManager();
        if (audioFocusManager == null) {
            return -1;
        }
        return audioFocusManager.getAudioFocusStatus();
    }

    private static String getEventName(int i) {
        switch (i) {
            case 0:
                return "EVENT_NONE";
            case 1:
                return "EVENT_SKIP_NEXT";
            case 2:
                return "EVENT_SKIP_PREVIOUS";
            case 3:
                return "EVENT_SKIP_TO_INDEX";
            case 4:
                return "EVENT_RESTART";
            case 5:
                return "EVENT_SEEK_TIME_FORWARD";
            case 6:
                return "EVENT_SEEK_TIME_BACKWARD";
            case 7:
                return "EVENT_SEEK_LYRICS";
            case 8:
                return "EVENT_FAST_FORWARD";
            case 9:
                return "EVENT_REWIND";
            case 10:
                return "EVENT_NORMAL_SPEED";
            case 11:
                return "EVENT_TRACK_END";
            case 12:
                return "EVENT_PAUSE";
            case 13:
                return "EVENT_ERROR";
            case 14:
                return "EVENT_RESUME";
            case 15:
                return "EVENT_SYSTEM_PAUSE";
            case 16:
                return "EVENT_SYSTEM_RESUME";
            case 17:
                return "EVENT_SET_SEQUENCER";
            case 18:
                return "EVENT_START_SEQUENCER";
            case 19:
                return "EVENT_SET_PLAYER";
            case 20:
                return "EVENT_THUMBS_UP";
            case 21:
                return "EVENT_THUMBS_DOWN";
            case 22:
                return "EVENT_REVERT_THUMBS_UP";
            case 23:
                return "EVENT_REVERT_THUMBS_DOWN";
            case 24:
                return "EVENT_SHUFFLE_ENABLED";
            case 25:
                return "EVENT_SHUFFLE_DISABLED";
            case 26:
                return "EVENT_REPEAT_MODE_CHANGED";
            default:
                return "UNKNOWN<" + i + ">";
        }
    }

    protected PlaybackAttributes getDynamicBestAvailablePlaybackAttributes() {
        return PlaybackAttributes.UNKNOWN;
    }
}
