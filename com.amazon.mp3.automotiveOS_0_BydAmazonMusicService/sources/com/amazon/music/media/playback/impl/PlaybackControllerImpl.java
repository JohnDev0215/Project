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
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.media.playback.PlaybackProvider;
import com.amazon.music.media.playback.PlayerCapabilities;
import com.amazon.music.media.playback.RepeatMode;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.media.playback.util.ListenerSet;
import com.amazon.music.media.playback.util.PlayerUtil;
import com.amazon.music.media.playback.util.StringUtils;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.video.sdk.player.PlaybackState;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class PlaybackControllerImpl implements PlaybackController {
    private static final Logger logger = LoggerFactory.getLogger(PlaybackControllerImpl.class.getSimpleName());
    private final IOnPlaybackCommandListener commandListener;
    private PlaybackProvider.Connection connection;
    private final ControlSource controlSource;
    private final ListenerSet<OnPlaybackErrorListener> errorListeners;
    private final ListenerSet<OnPlaybackEventListener> eventListeners;
    private final Lock lock;
    private final ListenerSet<OnMetadataChangedListener> metadataListeners;
    private final Playback playback;
    private final ListenerSet<OnPlayStateChangedListener> stateListeners;

    public PlaybackControllerImpl(@NonNull Playback playback, @NonNull IOnPlaybackCommandListener iOnPlaybackCommandListener, @NonNull ControlSource controlSource, @NonNull PlaybackProvider.Connection connection, @NonNull Lock lock) {
        ListenerSet.DispatchStrategy dispatchStrategy = ListenerSet.DispatchStrategy.SYNCHRONOUS;
        ListenerSet.DuplicateStrategy duplicateStrategy = ListenerSet.DuplicateStrategy.ALLOW_DUPLICATES;
        this.errorListeners = new ListenerSet<>(OnPlaybackErrorListener.class, dispatchStrategy, duplicateStrategy);
        this.stateListeners = new ListenerSet<>(OnPlayStateChangedListener.class, dispatchStrategy, duplicateStrategy);
        this.metadataListeners = new ListenerSet<>(OnMetadataChangedListener.class, dispatchStrategy, duplicateStrategy);
        this.eventListeners = new ListenerSet<>(OnPlaybackEventListener.class, dispatchStrategy, duplicateStrategy);
        this.playback = (Playback) Validate.notNull(playback);
        this.commandListener = (IOnPlaybackCommandListener) Validate.notNull(iOnPlaybackCommandListener);
        this.controlSource = (ControlSource) Validate.notNull(controlSource);
        this.connection = (PlaybackProvider.Connection) Validate.notNull(connection);
        this.lock = (Lock) Validate.notNull(lock);
        moveListeners(null, this.connection);
    }

    private void moveListeners(PlaybackProvider.Connection connection, PlaybackProvider.Connection connection2) {
        if (connection != null) {
            connection.removeOnMetadataChangedListener(this.metadataListeners.proxy());
            connection.removeOnPlaybackErrorListener(this.errorListeners.proxy());
            connection.removeOnPlaybackEventListener(this.eventListeners.proxy());
            connection.removeOnPlayStateChangedListener(this.stateListeners.proxy());
        }
        if (connection2 != null) {
            connection2.addOnMetadataChangedListener(this.metadataListeners.proxy());
            connection2.addOnPlaybackErrorListener(this.errorListeners.proxy());
            connection2.addOnPlaybackEventListener(this.eventListeners.proxy());
            connection2.addOnPlayStateChangedListener(this.stateListeners.proxy());
        }
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public ControlSource getControlSource() {
        return this.controlSource;
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public Playback getPlayback() {
        return this.playback;
    }

    public void setConnection(@NonNull PlaybackProvider.Connection connection) {
        moveListeners(this.connection, connection);
        this.connection = (PlaybackProvider.Connection) Validate.notNull(connection, "connection is null", new Object[0]);
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void play() {
        play(Clock.now());
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void play(final long j) {
        ThreadUtils.callOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.impl.PlaybackControllerImpl.1
            @Override // java.lang.Runnable
            public void run() {
                PlaybackControllerImpl.this.connection.onUserInteraction(PlaybackControllerImpl.this.controlSource);
                PlaybackControllerImpl.this.commandListener.onPlay(PlaybackControllerImpl.this, j);
                PlaybackControllerImpl.this.connection.play(PlaybackControllerImpl.this.controlSource, j);
            }
        });
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void pause() {
        ThreadUtils.callOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.impl.PlaybackControllerImpl.2
            @Override // java.lang.Runnable
            public void run() {
                PlaybackControllerImpl.this.connection.onUserInteraction(PlaybackControllerImpl.this.controlSource);
                PlaybackControllerImpl.this.commandListener.onPause(PlaybackControllerImpl.this);
                PlaybackControllerImpl.this.connection.pause(PlaybackControllerImpl.this.controlSource);
            }
        });
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void stop(final ChangeReason changeReason) {
        logger.info("stop(" + changeReason + ") " + StringUtils.getStackTrace(12));
        ThreadUtils.callOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.impl.PlaybackControllerImpl.3
            @Override // java.lang.Runnable
            public void run() {
                PlaybackControllerImpl.this.connection.onUserInteraction(PlaybackControllerImpl.this.controlSource);
                PlaybackControllerImpl.this.commandListener.onStop(PlaybackControllerImpl.this, changeReason);
                PlaybackControllerImpl.this.connection.stop(changeReason, PlaybackControllerImpl.this.controlSource);
            }
        });
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void togglePlaying() {
        ThreadUtils.callOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.impl.PlaybackControllerImpl.4
            @Override // java.lang.Runnable
            public void run() {
                PlaybackControllerImpl.this.connection.onUserInteraction(PlaybackControllerImpl.this.controlSource);
                if (PlaybackControllerImpl.this.getPlayStatus() != PlayStatus.USER_PAUSED) {
                    PlaybackControllerImpl.this.commandListener.onPause(PlaybackControllerImpl.this);
                    PlaybackControllerImpl.this.connection.pause(PlaybackControllerImpl.this.controlSource);
                } else {
                    long jNow = Clock.now();
                    PlaybackControllerImpl.this.commandListener.onPlay(PlaybackControllerImpl.this, jNow);
                    PlaybackControllerImpl.this.connection.play(PlaybackControllerImpl.this.controlSource, jNow);
                }
            }
        });
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public boolean canPlay() {
        this.lock.lock();
        try {
            return this.connection.canPlay();
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public PlaybackAttributes getCurrentPlaybackAttributes() {
        this.lock.lock();
        try {
            return this.connection.getCurrentPlaybackAttributes();
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public PlaybackAttributes getBestAvailablePlaybackAttributes() {
        PlaybackAttributes dynamicBestAvailablePlaybackAttributes = getDynamicBestAvailablePlaybackAttributes();
        PlaybackAttributes playbackAttributes = PlaybackAttributes.UNKNOWN;
        if (dynamicBestAvailablePlaybackAttributes != playbackAttributes) {
            return dynamicBestAvailablePlaybackAttributes;
        }
        MediaItem currentMediaItem = getCurrentMediaItem();
        return currentMediaItem == null ? playbackAttributes : currentMediaItem.getBestAvailablePlaybackAttributes();
    }

    private PlaybackAttributes getDynamicBestAvailablePlaybackAttributes() {
        this.lock.lock();
        try {
            return this.connection.getDynamicBestAvailablePlaybackAttributes();
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public boolean isStreaming() {
        this.lock.lock();
        try {
            return this.connection.isStreaming();
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public PlayerCapabilities getPlayerCapabilities() {
        this.lock.lock();
        try {
            return this.connection.getPlayerCapabilities();
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public PlayStatus getPlayStatus() {
        this.lock.lock();
        try {
            return this.connection.getPlayStatus();
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void skipNext() {
        skip(1, ChangeReason.NEXT, true);
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void skipPrevious() {
        skip(-1, ChangeReason.PREVIOUS, true);
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void skip(int i) {
        skip(i, ChangeReason.INDEX_REQUESTED, false);
    }

    private void skip(final int i, final ChangeReason changeReason, final boolean z) {
        ThreadUtils.callOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.impl.PlaybackControllerImpl.5
            @Override // java.lang.Runnable
            public void run() {
                PlaybackControllerImpl.this.connection.onUserInteraction(PlaybackControllerImpl.this.controlSource);
                PlaybackControllerImpl.this.commandListener.onSkip(PlaybackControllerImpl.this, i, changeReason);
                PlaybackControllerImpl.this.connection.skip(i, changeReason, PlaybackControllerImpl.this.controlSource);
                if (z) {
                    PlaybackControllerImpl.this.commandListener.onPlay(PlaybackControllerImpl.this, Clock.now());
                    PlaybackControllerImpl.this.connection.play(PlaybackControllerImpl.this.controlSource, Clock.now());
                }
            }
        });
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void skipToIndex(final int i) {
        ThreadUtils.callOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.impl.PlaybackControllerImpl.6
            @Override // java.lang.Runnable
            public void run() {
                PlaybackControllerImpl.this.connection.onUserInteraction(PlaybackControllerImpl.this.controlSource);
                PlaybackControllerImpl.this.commandListener.onSkipToIndex(PlaybackControllerImpl.this, i);
                PlaybackControllerImpl.this.connection.skipToIndex(i, PlaybackControllerImpl.this.controlSource);
            }
        });
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public float getPlaybackSpeed() {
        this.lock.lock();
        try {
            return this.connection.getPlaybackSpeed();
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public long getAmountBuffered() {
        this.lock.lock();
        try {
            return this.connection.getAmountBuffered();
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void normalSpeed() {
        ThreadUtils.callOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.impl.PlaybackControllerImpl.8
            @Override // java.lang.Runnable
            public void run() {
                PlaybackControllerImpl.this.connection.onUserInteraction(PlaybackControllerImpl.this.controlSource);
                PlaybackControllerImpl.this.commandListener.onNormalSpeed(PlaybackControllerImpl.this);
                PlaybackControllerImpl.this.connection.normalSpeed(PlaybackControllerImpl.this.controlSource);
            }
        });
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void fastForward() {
        ThreadUtils.callOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.impl.PlaybackControllerImpl.9
            @Override // java.lang.Runnable
            public void run() {
                PlaybackControllerImpl.this.connection.onUserInteraction(PlaybackControllerImpl.this.controlSource);
                PlaybackControllerImpl.this.commandListener.onFastForward(PlaybackControllerImpl.this);
                PlaybackControllerImpl.this.connection.fastForward(PlaybackControllerImpl.this.controlSource);
            }
        });
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void rewind() {
        ThreadUtils.callOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.impl.PlaybackControllerImpl.10
            @Override // java.lang.Runnable
            public void run() {
                PlaybackControllerImpl.this.connection.onUserInteraction(PlaybackControllerImpl.this.controlSource);
                PlaybackControllerImpl.this.commandListener.onRewind(PlaybackControllerImpl.this);
                PlaybackControllerImpl.this.connection.rewind(PlaybackControllerImpl.this.controlSource);
            }
        });
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public boolean canFastForward() {
        return this.connection.canFastForward();
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public boolean canRewind() {
        return this.connection.canRewind();
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public int getCurrentIndex() {
        this.lock.lock();
        try {
            return this.connection.getCurrentIndex();
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public int getUnshuffledIndex(int i) {
        this.lock.lock();
        try {
            return this.connection.getUnshuffledIndex(i);
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void restartMediaItem() {
        ThreadUtils.callOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.impl.PlaybackControllerImpl.11
            @Override // java.lang.Runnable
            public void run() {
                PlaybackControllerImpl.this.connection.onUserInteraction(PlaybackControllerImpl.this.controlSource);
                PlaybackControllerImpl.this.commandListener.onRestartMediaItem(PlaybackControllerImpl.this);
                PlaybackControllerImpl.this.connection.restartMediaItem(PlaybackControllerImpl.this.controlSource);
            }
        });
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void previousOrRestart() {
        ThreadUtils.callOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.impl.PlaybackControllerImpl.12
            @Override // java.lang.Runnable
            public void run() {
                PlaybackControllerImpl.this.connection.onUserInteraction(PlaybackControllerImpl.this.controlSource);
                boolean zCanSkipPrevious = PlaybackControllerImpl.this.connection.canSkipPrevious();
                boolean zCanRestartItem = PlaybackControllerImpl.this.connection.canRestartItem();
                boolean z = false;
                boolean z2 = true;
                if (zCanSkipPrevious && !zCanRestartItem) {
                    z2 = false;
                    z = true;
                } else if (zCanSkipPrevious || !zCanRestartItem) {
                    if (zCanSkipPrevious) {
                        boolean zPrefersRestartOverPrevious = PlaybackControllerImpl.this.connection.prefersRestartOverPrevious();
                        z2 = zPrefersRestartOverPrevious;
                        z = !zPrefersRestartOverPrevious;
                    } else {
                        z2 = false;
                    }
                }
                if (!z) {
                    if (z2) {
                        PlaybackControllerImpl.this.commandListener.onRestartMediaItem(PlaybackControllerImpl.this);
                        PlaybackControllerImpl.this.connection.restartMediaItem(PlaybackControllerImpl.this.controlSource);
                        return;
                    }
                    return;
                }
                IOnPlaybackCommandListener iOnPlaybackCommandListener = PlaybackControllerImpl.this.commandListener;
                PlaybackControllerImpl playbackControllerImpl = PlaybackControllerImpl.this;
                ChangeReason changeReason = ChangeReason.PREVIOUS;
                iOnPlaybackCommandListener.onSkip(playbackControllerImpl, -1, changeReason);
                PlaybackControllerImpl.this.connection.skip(-1, changeReason, PlaybackControllerImpl.this.controlSource);
                PlaybackControllerImpl.this.commandListener.onPlay(PlaybackControllerImpl.this, Clock.now());
                PlaybackControllerImpl.this.connection.play(PlaybackControllerImpl.this.controlSource, Clock.now());
            }
        });
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public boolean canSkipNext() {
        this.lock.lock();
        try {
            return this.connection.canSkipNext();
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public ActionAvailability getSkipNextAvailability() {
        this.lock.lock();
        try {
            return this.connection.getSkipNextAvailability();
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public boolean canSkipPrevious() {
        this.lock.lock();
        try {
            return this.connection.canSkipPrevious();
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public boolean canSkipToIndex() {
        this.lock.lock();
        try {
            return this.connection.canSkipToIndex();
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public boolean canRestartItem() {
        this.lock.lock();
        try {
            return this.connection.canRestartItem();
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public boolean canShuffle() {
        this.lock.lock();
        try {
            return this.connection.canShuffle();
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public boolean isShuffled() {
        this.lock.lock();
        try {
            return this.connection.isShuffled();
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void setShuffled(final boolean z) {
        ThreadUtils.callOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.impl.PlaybackControllerImpl.13
            @Override // java.lang.Runnable
            public void run() {
                PlaybackControllerImpl.this.connection.onUserInteraction(PlaybackControllerImpl.this.controlSource);
                PlaybackControllerImpl.this.commandListener.onSetShuffled(PlaybackControllerImpl.this, z);
                PlaybackControllerImpl.this.connection.setShuffled(z, true, PlaybackControllerImpl.this.controlSource);
            }
        });
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void setPlaybackSpeed(final double d) {
        ThreadUtils.callOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.impl.PlaybackControllerImpl.14
            @Override // java.lang.Runnable
            public void run() {
                PlaybackControllerImpl.this.connection.onUserInteraction(PlaybackControllerImpl.this.controlSource);
                PlaybackControllerImpl.this.commandListener.onSetPlaybackSpeed(PlaybackControllerImpl.this, d);
                PlaybackControllerImpl.this.connection.setPlaybackSpeed(d);
            }
        });
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public RepeatMode getRepeatMode() {
        this.lock.lock();
        try {
            return this.connection.getRepeatMode();
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void setRepeatMode(final RepeatMode repeatMode) {
        ThreadUtils.callOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.impl.PlaybackControllerImpl.15
            @Override // java.lang.Runnable
            public void run() {
                PlaybackControllerImpl.this.connection.onUserInteraction(PlaybackControllerImpl.this.controlSource);
                PlaybackControllerImpl.this.commandListener.onSetRepeatMode(PlaybackControllerImpl.this, repeatMode);
                PlaybackControllerImpl.this.connection.setRepeatMode(repeatMode, PlaybackControllerImpl.this.controlSource);
            }
        });
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public EnumSet<RepeatMode> getAvailableRepeatModes() {
        this.lock.lock();
        try {
            return this.connection.getAvailableRepeatModes();
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void toggleRepeatMode() {
        ThreadUtils.callOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.impl.PlaybackControllerImpl.16
            @Override // java.lang.Runnable
            public void run() {
                RepeatMode nextRepeatMode = RepeatMode.getNextRepeatMode(PlaybackControllerImpl.this.getRepeatMode(), PlaybackControllerImpl.this.getAvailableRepeatModes());
                PlaybackControllerImpl.this.commandListener.onSetRepeatMode(PlaybackControllerImpl.this, nextRepeatMode);
                PlaybackControllerImpl.this.connection.setRepeatMode(nextRepeatMode, PlaybackControllerImpl.this.controlSource);
            }
        });
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public boolean canSeek() {
        this.lock.lock();
        try {
            return this.connection.canSeek();
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void seek(final long j, final int i) {
        ThreadUtils.callOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.impl.PlaybackControllerImpl.17
            @Override // java.lang.Runnable
            public void run() {
                PlaybackControllerImpl.this.connection.onUserInteraction(PlaybackControllerImpl.this.controlSource);
                PlaybackControllerImpl.this.commandListener.onSeek(PlaybackControllerImpl.this, j, i);
                PlaybackControllerImpl.this.connection.seek(j, i == 1 ? ChangeReason.LYRICS_SEEK : ChangeReason.TIME_SEEK, PlaybackControllerImpl.this.controlSource);
            }
        });
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void onUserInteraction() {
        ThreadUtils.callOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.impl.PlaybackControllerImpl.18
            @Override // java.lang.Runnable
            public void run() {
                PlaybackControllerImpl.this.connection.onUserInteraction(PlaybackControllerImpl.this.controlSource);
            }
        });
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void autoResolveNextConcurrencyCheck() {
        ThreadUtils.callOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.impl.PlaybackControllerImpl.19
            @Override // java.lang.Runnable
            public void run() {
                PlaybackControllerImpl.this.connection.autoResolveNextConcurrencyCheck();
            }
        });
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void resolveMaxConcurrency(final String str, final boolean z, final long j) {
        ThreadUtils.callOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.impl.PlaybackControllerImpl.20
            @Override // java.lang.Runnable
            public void run() {
                PlayerUtil playerUtil = PlayerUtil.INSTANCE;
                if (playerUtil.isVideoMediaItem(PlaybackControllerImpl.this.getCurrentMediaItem())) {
                    PlaybackControllerImpl.this.playback.getPlaybackController(PlaybackControllerImpl.this.getControlSource()).play();
                    playerUtil.updateStreamingStatus(PlaybackControllerImpl.this.playback.getPlaybackConfig(), PlaybackState.Playing, str, true);
                } else {
                    PlaybackControllerImpl.this.connection.onUserInteraction(PlaybackControllerImpl.this.controlSource);
                    PlaybackControllerImpl.this.commandListener.onResolveMaxConcurrency(PlaybackControllerImpl.this, str, z, j);
                    PlaybackControllerImpl.this.connection.resolveMaxConcurrency(str, z, PlaybackControllerImpl.this.controlSource, j);
                }
            }
        });
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void addOnPlayStateChangedListener(OnPlayStateChangedListener onPlayStateChangedListener) {
        this.stateListeners.add(onPlayStateChangedListener);
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void removeOnPlayStateChangedListener(OnPlayStateChangedListener onPlayStateChangedListener) {
        this.stateListeners.remove(onPlayStateChangedListener);
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void addOnMetadataChangedListener(OnMetadataChangedListener onMetadataChangedListener) {
        this.metadataListeners.add(onMetadataChangedListener);
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void removeOnMetadataChangedListener(OnMetadataChangedListener onMetadataChangedListener) {
        this.metadataListeners.remove(onMetadataChangedListener);
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void addOnPlaybackErrorListener(OnPlaybackErrorListener onPlaybackErrorListener) {
        this.errorListeners.add(onPlaybackErrorListener);
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void removeOnPlaybackErrorListener(OnPlaybackErrorListener onPlaybackErrorListener) {
        this.errorListeners.remove(onPlaybackErrorListener);
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void addOnPlaybackEventListener(OnPlaybackEventListener onPlaybackEventListener) {
        this.eventListeners.add(onPlaybackEventListener);
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void removeOnPlaybackEventListener(OnPlaybackEventListener onPlaybackEventListener) {
        this.eventListeners.remove(onPlaybackEventListener);
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public MediaItem getCurrentMediaItem() {
        this.lock.lock();
        try {
            return this.connection.getCurrentMediaItem();
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public long getPositionMillis() {
        this.lock.lock();
        try {
            return this.connection.getPositionMillis();
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public long getDurationMillis() {
        this.lock.lock();
        try {
            return this.connection.getDurationMillis();
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public MediaItem peek(int i) {
        this.lock.lock();
        try {
            return this.connection.peek(i);
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public List<MediaItem> getCurrentQueue() {
        this.lock.lock();
        try {
            return this.connection.getCurrentQueue();
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public int getAudioFocusStatus() {
        this.lock.lock();
        try {
            return this.connection.getAudioFocusStatus();
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public boolean isLive() {
        this.lock.lock();
        try {
            return this.connection.isLive();
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public boolean canRateMediaItems() {
        this.lock.lock();
        try {
            return this.connection.canRateMediaItems();
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public boolean canRateCurrentItem() {
        MediaItem currentMediaItem = getCurrentMediaItem();
        return currentMediaItem != null && currentMediaItem.canRate();
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public int getCurrentItemRating() {
        MediaItem currentMediaItem = getCurrentMediaItem();
        if (currentMediaItem == null) {
            return 0;
        }
        return currentMediaItem.getRating();
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public void setCurrentItemRating(final int i) {
        ThreadUtils.callOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.impl.PlaybackControllerImpl.21
            @Override // java.lang.Runnable
            public void run() {
                MediaItem currentMediaItem = PlaybackControllerImpl.this.getCurrentMediaItem();
                if (currentMediaItem != null) {
                    long positionMillis = PlaybackControllerImpl.this.connection.getPositionMillis();
                    int rating = currentMediaItem.getRating();
                    int i2 = i;
                    if (i2 == 2 && rating != 2) {
                        PlaybackControllerImpl playbackControllerImpl = PlaybackControllerImpl.this;
                        playbackControllerImpl.onThumbsDown(currentMediaItem, positionMillis, playbackControllerImpl.getPlayback().getPlaybackConfig().getThumbsDownSkipDelayMillis());
                        return;
                    }
                    if (i2 == 1 && rating != 1) {
                        PlaybackControllerImpl.this.onThumbsUp(currentMediaItem, positionMillis);
                        return;
                    }
                    if (i2 == 0 && rating == 2) {
                        PlaybackControllerImpl.this.onRevertThumbsDown(currentMediaItem, positionMillis);
                    } else if (i2 == 0 && rating == 1) {
                        PlaybackControllerImpl.this.onRevertThumbsUp(currentMediaItem, positionMillis);
                    } else {
                        currentMediaItem.setRating(i2, positionMillis);
                    }
                }
            }
        });
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public int toggleThumbsUp() {
        return toggleRating(true, -1L);
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public int toggleThumbsDown() {
        return toggleRating(false, getPlayback().getPlaybackConfig().getThumbsDownSkipDelayMillis());
    }

    private int toggleRating(final boolean z, final long j) {
        try {
            return ((Integer) ThreadUtils.callOnMainThread(new Callable<Integer>() { // from class: com.amazon.music.media.playback.impl.PlaybackControllerImpl.22
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                public Integer call() {
                    MediaItem currentMediaItem = PlaybackControllerImpl.this.getCurrentMediaItem();
                    if (currentMediaItem != null && currentMediaItem.canRate()) {
                        long positionMillis = PlaybackControllerImpl.this.connection.getPositionMillis();
                        int rating = currentMediaItem.getRating();
                        boolean z2 = z;
                        if (z2 && rating == 1) {
                            PlaybackControllerImpl.this.onRevertThumbsUp(currentMediaItem, positionMillis);
                        } else if (z2) {
                            PlaybackControllerImpl.this.onThumbsUp(currentMediaItem, positionMillis);
                        } else if (rating == 2) {
                            PlaybackControllerImpl.this.onRevertThumbsDown(currentMediaItem, positionMillis);
                        } else {
                            PlaybackControllerImpl.this.onThumbsDown(currentMediaItem, positionMillis, j);
                        }
                        return Integer.valueOf(currentMediaItem.getRating());
                    }
                    return -1;
                }
            })).intValue();
        } catch (Exception e) {
            logger.error("There was an error while trying to toggle the rating.", (Throwable) e);
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onThumbsDown(final MediaItem mediaItem, long j, long j2) {
        if (j2 > 0) {
            ThreadUtils.postOnMainThreadDelayed(new Runnable() { // from class: com.amazon.music.media.playback.impl.PlaybackControllerImpl.23
                @Override // java.lang.Runnable
                public void run() {
                    MediaItem currentMediaItem = PlaybackControllerImpl.this.getCurrentMediaItem();
                    MediaItem mediaItem2 = mediaItem;
                    if (currentMediaItem == mediaItem2 && mediaItem2.getRating() == 2) {
                        IOnPlaybackCommandListener iOnPlaybackCommandListener = PlaybackControllerImpl.this.commandListener;
                        PlaybackControllerImpl playbackControllerImpl = PlaybackControllerImpl.this;
                        ChangeReason changeReason = ChangeReason.THUMBS_DOWN;
                        iOnPlaybackCommandListener.onSkip(playbackControllerImpl, 1, changeReason);
                        PlaybackControllerImpl.this.connection.skip(1, changeReason, PlaybackControllerImpl.this.controlSource);
                    }
                }
            }, j2);
        }
        this.commandListener.onThumbsDown(this);
        mediaItem.setRating(2, j);
        if (j2 == 0) {
            IOnPlaybackCommandListener iOnPlaybackCommandListener = this.commandListener;
            ChangeReason changeReason = ChangeReason.THUMBS_DOWN;
            iOnPlaybackCommandListener.onSkip(this, 1, changeReason);
            this.connection.skip(1, changeReason, this.controlSource);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onRevertThumbsDown(MediaItem mediaItem, long j) {
        this.commandListener.onRevertThumbsDown(this);
        mediaItem.setRating(0, j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onThumbsUp(MediaItem mediaItem, long j) {
        this.commandListener.onThumbsUp(this);
        mediaItem.setRating(1, j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onRevertThumbsUp(MediaItem mediaItem, long j) {
        this.commandListener.onRevertThumbsUp(this);
        mediaItem.setRating(0, j);
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public VolumeProviderCompat getVolumeProvider() {
        this.lock.lock();
        try {
            return this.connection.getVolumeProvider();
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public String getMediaPlayerName() {
        this.lock.lock();
        try {
            return this.connection.getMediaPlayerName();
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public MediaCollectionInfo getMediaCollectionInfo() {
        this.lock.lock();
        try {
            return this.connection.getMediaCollectionInfo();
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public boolean isStopped() {
        this.lock.lock();
        try {
            return this.connection.isStopped();
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.amazon.music.media.playback.PlaybackController
    public boolean shouldPauseWhenTaskRemoved() {
        return this.connection.shouldPauseWhenTaskRemoved();
    }

    public String toString() {
        return "PlaybackControllerImpl{controlSource=" + this.controlSource.getName() + '}';
    }
}
