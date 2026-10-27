package com.amazon.music.media.playback.impl;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.media.VolumeProviderCompat;
import com.amazon.music.media.playback.ActionAvailability;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.GhostListeningException;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaCollectionType;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.NoPlayableContentException;
import com.amazon.music.media.playback.OnFrameChangedListener;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import com.amazon.music.media.playback.OnPlaybackMetricsListener;
import com.amazon.music.media.playback.OnPrebufferChangedListener;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.media.playback.PlaybackAttributes;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.media.playback.PlaybackException;
import com.amazon.music.media.playback.PlayerCapabilities;
import com.amazon.music.media.playback.PlayerPlaybackProvider;
import com.amazon.music.media.playback.RepeatMode;
import com.amazon.music.media.playback.SequencerPlaybackProvider;
import com.amazon.music.media.playback.TooMuchSilenceException;
import com.amazon.music.media.playback.access.MediaAccessInfo;
import com.amazon.music.media.playback.access.MediaAccessPolicy;
import com.amazon.music.media.playback.concurrency.ConcurrencyException;
import com.amazon.music.media.playback.concurrency.ConcurrencyProvider;
import com.amazon.music.media.playback.config.PlaybackConfig;
import com.amazon.music.media.playback.config.PlaybackPersistenceProvider;
import com.amazon.music.media.playback.config.SequencerPersistenceProvider;
import com.amazon.music.media.playback.impl.AudioFocusManager;
import com.amazon.music.media.playback.impl.ConcurrencyManager;
import com.amazon.music.media.playback.metrics.MetricsInfo;
import com.amazon.music.media.playback.metrics.mts.MTSMetricsSender;
import com.amazon.music.media.playback.player.NoOpPlayer;
import com.amazon.music.media.playback.player.PlayRequest;
import com.amazon.music.media.playback.player.PlayRequestStatus;
import com.amazon.music.media.playback.player.Player;
import com.amazon.music.media.playback.player.PositionTracker;
import com.amazon.music.media.playback.ratings.DelegatingRatingsProvider;
import com.amazon.music.media.playback.ratings.RatingsProvider;
import com.amazon.music.media.playback.sequencer.NoOpSequencer;
import com.amazon.music.media.playback.sequencer.Sequencer;
import com.amazon.music.media.playback.sequencer.SequencerListener;
import com.amazon.music.media.playback.sequencer.impl.FilteredSequencer;
import com.amazon.music.media.playback.sequencer.impl.PlaceholderSequencer;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.media.playback.util.ObjectUtils;
import com.amazon.music.media.playback.util.StrictMode;
import com.amazon.music.media.playback.util.StringUtils;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class DefaultPlaybackProvider extends AbstractPlaybackProvider implements SequencerPlaybackProvider, PlayerPlaybackProvider {
    private static final long PREFER_PREVIOUS_VS_RESTART_MILLIS = TimeUnit.SECONDS.toMillis(3);
    private static final Logger logger = LoggerFactory.getLogger("DefaultPlaybackProvider");
    private int additionalPreloadCount;
    private final AudioFocusManager.Listener audioFocusListener;
    private ConcurrencyManager.OnConcurrencyExceptionListener concurrencyExceptionListener;
    private ConcurrencyManager concurrencyManager;
    private final PlaybackConfig.OnConfigurationChangedListener configListener;
    private int consecutiveErrors;
    private PlaybackException contentException;
    private Context context;
    private ChangeReason currentChangeReason;
    private PlaybackController defaultPlaybackController;
    private long desiredPlayTime;
    private FilteredSequencer filteredSequencer;
    private PositionTracker ghostListeningTracker;
    private boolean isNewSequencer;
    private long lastUserInteractionTime;
    private SequencerPlaybackProvider.MediaItemVerifier mediaItemVerifier;
    private String mricToken;
    private SequencerPersistenceProvider persistenceProvider;
    private float playbackSpeedMultiplier;
    private Player player;
    private final Player.Callback playerCallback;
    private PlaybackException playerException;
    private final RatingsProvider.OnRatingsChangedListener ratingsListener;
    private DelegatingRatingsProvider ratingsProvider;
    private Sequencer sequencer;
    private PlaybackException sequencerException;
    private SequencerListener sequencerListener;
    private int sequencerNum;
    private final Runnable silenceRunner;
    private PositionTracker silenceTracker;
    private long startPositionMillis;
    private boolean suppressCallbacks;

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected boolean shouldPauseWhenTaskRemoved() {
        return true;
    }

    static /* synthetic */ int access$808(DefaultPlaybackProvider defaultPlaybackProvider) {
        int i = defaultPlaybackProvider.consecutiveErrors;
        defaultPlaybackProvider.consecutiveErrors = i + 1;
        return i;
    }

    public DefaultPlaybackProvider(PlaybackConfig playbackConfig) {
        super(playbackConfig);
        this.consecutiveErrors = 0;
        this.playbackSpeedMultiplier = 1.0f;
        this.silenceTracker = new PositionTracker();
        this.ghostListeningTracker = new PositionTracker();
        this.lastUserInteractionTime = Clock.now();
        PlaybackConfig.OnConfigurationChangedListener onConfigurationChangedListener = new PlaybackConfig.OnConfigurationChangedListener() { // from class: com.amazon.music.media.playback.impl.DefaultPlaybackProvider.1
            @Override // com.amazon.music.media.playback.config.PlaybackConfig.OnConfigurationChangedListener
            public void onConfigurationChanged(PlaybackConfig playbackConfig2) {
                DefaultPlaybackProvider.this.silenceTracker.alertAtPosition(playbackConfig2.getMaxSilenceMillis(), DefaultPlaybackProvider.this.silenceRunner);
                DefaultPlaybackProvider.this.concurrencyManager.setConcurrencyProvider(DefaultPlaybackProvider.this.getConcurrencyProvider());
                DefaultPlaybackProvider.this.concurrencyManager.setConcurrencyResolutionPolicy(playbackConfig2.getConcurrencyResolutionPolicy());
                StringUtils.setShouldRedact(playbackConfig2.isRedactEnabled());
                DefaultPlaybackProvider.this.updateMediaAccessPolicy();
            }
        };
        this.configListener = onConfigurationChangedListener;
        Runnable runnable = new Runnable() { // from class: com.amazon.music.media.playback.impl.DefaultPlaybackProvider.2
            @Override // java.lang.Runnable
            public void run() {
                if (DefaultPlaybackProvider.this.getPlayStatus().willPlay()) {
                    DefaultPlaybackProvider.this.suppressCallbacks = true;
                    try {
                        DefaultPlaybackProvider.this.pause(ChangeReason.TOO_MUCH_SILENCE, null);
                        DefaultPlaybackProvider.this.notifyPlaybackError(new TooMuchSilenceException());
                    } finally {
                        DefaultPlaybackProvider.this.suppressCallbacks = false;
                        DefaultPlaybackProvider.this.notifyPlayStateChanged();
                    }
                }
            }
        };
        this.silenceRunner = runnable;
        this.playerCallback = new Player.Callback() { // from class: com.amazon.music.media.playback.impl.DefaultPlaybackProvider.3
            @Override // com.amazon.music.media.playback.player.Player.Callback
            public void onClearQueue() {
            }

            @Override // com.amazon.music.media.playback.player.Player.Callback
            public void onPlayStatusChanged(PlayStatus playStatus, PlayStatus playStatus2, PlaybackException playbackException) {
                if (playbackException != null && playStatus.willPlay() && !playStatus2.willPlay()) {
                    DefaultPlaybackProvider.this.playerException = playbackException;
                    if (playbackException.getRecoverability().canRecoverCollection() && !playbackException.getRecoverability().canRecoverMediaItem() && DefaultPlaybackProvider.this.consecutiveErrors < 5 && DefaultPlaybackProvider.this.sequencer.canSkipNext()) {
                        DefaultPlaybackProvider.access$808(DefaultPlaybackProvider.this);
                        DefaultPlaybackProvider defaultPlaybackProvider = DefaultPlaybackProvider.this;
                        ChangeReason changeReason = ChangeReason.ERROR;
                        ControlSource controlSource = ControlSource.NONE;
                        defaultPlaybackProvider.skip(1, changeReason, controlSource);
                        DefaultPlaybackProvider.this.play(changeReason, controlSource, Clock.now());
                        return;
                    }
                } else if (playStatus2 == PlayStatus.RENDERING) {
                    DefaultPlaybackProvider.this.consecutiveErrors = 0;
                }
                DefaultPlaybackProvider.this.onCallback(playbackException, 0, null);
                DefaultPlaybackProvider.this.checkTrackers();
                PlayStatus playStatus3 = PlayStatus.SYSTEM_PAUSED;
                if (playStatus == playStatus3 && playStatus2.shouldBePlaying()) {
                    DefaultPlaybackProvider.this.notifyPlaybackEvent(16, null);
                } else if (playStatus2 == playStatus3 && playStatus.shouldBePlaying()) {
                    DefaultPlaybackProvider.this.notifyPlaybackEvent(15, null);
                }
            }

            @Override // com.amazon.music.media.playback.player.Player.Callback
            public void onIndexChanged(int i, int i2, ChangeReason changeReason, PlaybackException playbackException) {
                DefaultPlaybackProvider.this.checkGhostListeningThresholdExceeded();
                if (!DefaultPlaybackProvider.this.suppressCallbacks && playbackException == null && DefaultPlaybackProvider.this.getPlayStatus().willPlay()) {
                    DefaultPlaybackProvider.this.autoRecover();
                }
                DefaultPlaybackProvider.this.onCallback(playbackException, i2 - i, changeReason);
            }

            @Override // com.amazon.music.media.playback.player.Player.Callback
            public void onMediaItemAlmostFinished() {
                DefaultPlaybackProvider.logger.info("onMediaItemAlmostFinished()");
                if (DefaultPlaybackProvider.this.suppressCallbacks || !DefaultPlaybackProvider.this.autoRecover()) {
                    return;
                }
                DefaultPlaybackProvider.this.onCallback(null, 0, null);
            }

            @Override // com.amazon.music.media.playback.OnMetadataChangedListener
            public void onMetadataChanged(MediaItem mediaItem) {
                DefaultPlaybackProvider.this.notifyMetadataChanged(mediaItem);
            }

            @Override // com.amazon.music.media.playback.OnMetadataChangedListener
            public void onMediaCollectionInfoChanged(MediaCollectionInfo mediaCollectionInfo) {
                DefaultPlaybackProvider.this.notifyMediaCollectionInfoChanged(mediaCollectionInfo);
            }

            @Override // com.amazon.music.media.playback.player.Player.Callback
            public void onPlaybackAttributesChanged() {
                DefaultPlaybackProvider.this.notifyPlayStateChanged();
            }

            @Override // com.amazon.music.media.playback.player.Player.Callback
            public void onRepeatingMediaItem() {
                if (DefaultPlaybackProvider.this.getPlaybackConfig().isResetPositionOnRepeat()) {
                    DefaultPlaybackProvider.this.doSeek(0L, ChangeReason.RESTART, ControlSource.NONE);
                }
                DefaultPlaybackProvider.this.notifyPlaybackEvent(27, null);
            }
        };
        this.sequencerListener = new SequencerListener() { // from class: com.amazon.music.media.playback.impl.DefaultPlaybackProvider.4
            @Override // com.amazon.music.media.playback.sequencer.SequencerListener
            public void onErrorFetchingMediaItems(Sequencer sequencer, PlaybackException playbackException) {
                if (playbackException != null) {
                    DefaultPlaybackProvider.this.sequencerException = playbackException;
                }
                DefaultPlaybackProvider.this.onCallback(null, 0, null);
            }

            @Override // com.amazon.music.media.playback.sequencer.SequencerListener
            public void onSequencerInfoChanged(Sequencer sequencer) {
                DefaultPlaybackProvider.this.onCallback(null, 0, null);
            }

            @Override // com.amazon.music.media.playback.OnMetadataChangedListener
            public void onMetadataChanged(MediaItem mediaItem) {
                DefaultPlaybackProvider.this.notifyMetadataChanged(mediaItem);
            }

            @Override // com.amazon.music.media.playback.OnMetadataChangedListener
            public void onMediaCollectionInfoChanged(MediaCollectionInfo mediaCollectionInfo) {
                DefaultPlaybackProvider.this.notifyMediaCollectionInfoChanged(mediaCollectionInfo);
            }
        };
        this.audioFocusListener = new AudioFocusManager.Listener() { // from class: com.amazon.music.media.playback.impl.DefaultPlaybackProvider.5
            @Override // com.amazon.music.media.playback.impl.AudioFocusManager.Listener
            public void onSystemDuck() {
            }

            @Override // com.amazon.music.media.playback.impl.AudioFocusManager.Listener
            public void onSystemNormal() {
            }

            @Override // com.amazon.music.media.playback.impl.AudioFocusManager.Listener
            public void onSystemPause() {
            }

            @Override // com.amazon.music.media.playback.impl.AudioFocusManager.Listener
            public void onSystemStop() {
            }

            @Override // com.amazon.music.media.playback.impl.AudioFocusManager.Listener
            public void onAudioFocusChanged(boolean z) {
                DefaultPlaybackProvider.this.notifyPlayStateChanged();
            }
        };
        this.ratingsListener = new RatingsProvider.OnRatingsChangedListener() { // from class: com.amazon.music.media.playback.impl.DefaultPlaybackProvider.6
            @Override // com.amazon.music.media.playback.ratings.RatingsProvider.OnRatingsChangedListener
            public void onRatingsChanged(@NonNull MediaItem mediaItem, int i, int i2) {
                MediaItem currentMediaItem = DefaultPlaybackProvider.this.getCurrentMediaItem();
                if (currentMediaItem != null && ObjectUtils.equal(currentMediaItem.getMediaItemId(), mediaItem.getMediaItemId())) {
                    DefaultPlaybackProvider.this.notifyPlayStateChanged();
                }
                if (i == -1 || i2 == -1) {
                    return;
                }
                if (i2 == 1) {
                    DefaultPlaybackProvider.this.notifyPlaybackEvent(20, null);
                    return;
                }
                if (i2 == 2) {
                    DefaultPlaybackProvider.this.notifyPlaybackEvent(21, null);
                } else if (i == 1) {
                    DefaultPlaybackProvider.this.notifyPlaybackEvent(22, null);
                } else if (i == 2) {
                    DefaultPlaybackProvider.this.notifyPlaybackEvent(23, null);
                }
            }

            @Override // com.amazon.music.media.playback.ratings.RatingsProvider.OnRatingsChangedListener
            public void onAllRatingsChanged() {
                if (DefaultPlaybackProvider.this.getCurrentMediaItem() != null) {
                    DefaultPlaybackProvider.this.notifyPlayStateChanged();
                }
            }
        };
        this.concurrencyExceptionListener = new ConcurrencyManager.OnConcurrencyExceptionListener() { // from class: com.amazon.music.media.playback.impl.DefaultPlaybackProvider.7
            @Override // com.amazon.music.media.playback.impl.ConcurrencyManager.OnConcurrencyExceptionListener
            public void onConcurrencyException(ConcurrencyException concurrencyException) {
                DefaultPlaybackProvider.this.pause(ChangeReason.RESOLVING_CONCURRENCY, ControlSource.NONE);
                DefaultPlaybackProvider.this.notifyPlaybackError(concurrencyException);
            }
        };
        this.ratingsProvider = new DelegatingRatingsProvider(null);
        this.defaultPlaybackController = playbackConfig.getPlayback().getPlaybackController(ControlSource.NONE);
        this.silenceTracker.alertAtPosition(getPlaybackConfig().getMaxSilenceMillis(), runnable);
        this.concurrencyManager = new ConcurrencyManager(this.concurrencyExceptionListener);
        this.player = new NoOpPlayer(this.context);
        onConfigurationChangedListener.onConfigurationChanged(playbackConfig);
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected void open() throws Throwable {
        PlaybackConfig playbackConfig = getPlaybackConfig();
        this.context = playbackConfig.getContext();
        if (!playbackConfig.isUnitTestMode()) {
            this.additionalPreloadCount = 2;
        } else {
            this.additionalPreloadCount = 0;
        }
        this.player.connect(this.playerCallback, getPlaybackConfig(), new Player.State(), getAudioFocusManager());
        PlaybackPersistenceProvider playbackPersistenceProvider = getPlaybackConfig().getPlaybackPersistenceProvider();
        this.persistenceProvider = playbackPersistenceProvider instanceof SequencerPersistenceProvider ? (SequencerPersistenceProvider) playbackPersistenceProvider : null;
        if (this.sequencer == null) {
            setSequencer(new NoOpSequencer(), 0L, null, false, null, ControlSource.NONE, 0L);
        }
        this.ratingsProvider.addOnRatingsChangedListener(this.ratingsListener);
        getPlaybackConfig().addOnConfigurationChangedListener(this.configListener);
        getAudioFocusManager().addListener(this.audioFocusListener);
        getConnection().addOnPlayStateChangedListener(new OnPlayStateChangedListener() { // from class: com.amazon.music.media.playback.impl.DefaultPlaybackProvider.8
            private int index;
            private MediaItem mediaItem;
            private RepeatMode repeatMode;
            private Sequencer sequencer;
            private boolean shuffled;

            @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
            public void onPlayStateChanged() {
                DefaultPlaybackProvider.this.concurrencyManager.updatePlayStatus(DefaultPlaybackProvider.this.getPlayStatus(), DefaultPlaybackProvider.this.getPlayingMediaItem(), DefaultPlaybackProvider.this.getPositionMillis(), null);
                if (DefaultPlaybackProvider.this.sequencer instanceof PlaceholderSequencer) {
                    return;
                }
                Sequencer sequencer = DefaultPlaybackProvider.this.sequencer;
                boolean zIsShuffled = sequencer.isShuffled();
                RepeatMode repeatMode = sequencer.getRepeatMode();
                int currentIndex = sequencer.getCurrentIndex();
                MediaItem currentMediaItem = sequencer.getCurrentMediaItem();
                if (sequencer == this.sequencer && zIsShuffled == this.shuffled && repeatMode == this.repeatMode && currentIndex == this.index && currentMediaItem == this.mediaItem) {
                    return;
                }
                this.sequencer = sequencer;
                this.shuffled = zIsShuffled;
                this.repeatMode = repeatMode;
                this.index = currentIndex;
                this.mediaItem = currentMediaItem;
                if (DefaultPlaybackProvider.this.persistenceProvider != null) {
                    DefaultPlaybackProvider.this.persistenceProvider.saveSequencerState(this.sequencer);
                    DefaultPlaybackProvider.this.persistenceProvider.savePosition(DefaultPlaybackProvider.this.getPositionMillis());
                }
            }
        });
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected void close() {
        this.persistenceProvider = null;
        getAudioFocusManager().removeListener(this.audioFocusListener);
        this.player.disconnect();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateMediaAccessPolicy() {
        MediaAccessPolicy mediaAccessPolicy = getMediaAccessPolicy();
        FilteredSequencer filteredSequencer = this.filteredSequencer;
        if (filteredSequencer == null || filteredSequencer.getMediaAccessPolicy() == mediaAccessPolicy) {
            return;
        }
        this.currentChangeReason = ChangeReason.UNAUTHORIZED;
        try {
            MediaItem playingMediaItem = getPlayingMediaItem();
            if (playingMediaItem != null) {
                MediaAccessInfo mediaAccessInfo = playingMediaItem.getMediaAccessInfo();
                if (!mediaAccessPolicy.getMediaAccess(mediaAccessInfo).canPlay() && mediaAccessPolicy.getMediaAccess(MediaAccessInfo.forContentAccessTypes(false, mediaAccessInfo.isCastable(), mediaAccessInfo.isAvailableOffline(), mediaAccessInfo.isAllowedOnline(), mediaAccessInfo.getContentAccessTypes())).canPlay()) {
                    this.currentChangeReason = ChangeReason.EXPLICIT_FILTER_ENABLED;
                }
            }
            this.filteredSequencer.setMediaAccessPolicy(mediaAccessPolicy);
        } finally {
            this.currentChangeReason = null;
        }
    }

    @VisibleForTesting(otherwise = 2)
    void onCallback(PlaybackException playbackException, int i, ChangeReason changeReason) {
        Logger logger2 = logger;
        Object[] objArr = new Object[3];
        objArr[0] = playbackException == null ? "null" : playbackException.getMetricsName();
        objArr[1] = Integer.valueOf(i);
        objArr[2] = changeReason != null ? changeReason.name() : "null";
        logger2.info(String.format("onCallback(%s, %d, %s)", objArr));
        if (this.suppressCallbacks) {
            if (playbackException != null) {
                notifyPlaybackError(playbackException);
                return;
            }
            return;
        }
        this.suppressCallbacks = true;
        if (i != 0) {
            try {
                notifyPlaybackEvent(11, null);
                this.filteredSequencer.skip(i, changeReason);
            } finally {
                this.suppressCallbacks = false;
                if (playbackException != null) {
                    notifyPlaybackError(playbackException);
                }
                notifyPlayStateChanged();
            }
        }
        coordinateState(this.currentChangeReason, null);
    }

    private boolean clearErrors(boolean z) {
        boolean z2 = (this.playerException == null && this.sequencerException == null) ? false : true;
        if (z2) {
            this.playerException = null;
            this.sequencerException = null;
        }
        if (z) {
            this.consecutiveErrors = 0;
        }
        return z2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean autoRecover() {
        PlaybackException playbackException = this.playerException;
        boolean z = false;
        boolean z2 = (playbackException == null && this.sequencerException == null) ? false : true;
        if (!z2) {
            return z2;
        }
        if (playbackException != null && (!playbackException.canAutoRetry() || !this.playerException.getRecoverability().canRecoverCollection())) {
            z2 = false;
        }
        PlaybackException playbackException2 = this.sequencerException;
        if (playbackException2 == null || (playbackException2.canAutoRetry() && this.sequencerException.getRecoverability().canRecoverCollection())) {
            z = z2;
        }
        if (z) {
            this.playerException = null;
            this.sequencerException = null;
        }
        return z;
    }

    private MediaAccessPolicy getMediaAccessPolicy() {
        return getPlaybackConfig().getMediaAccessPolicy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkTrackers() {
        MediaItem playingMediaItem = getPlayingMediaItem();
        if (getPlayStatus() == PlayStatus.RENDERING && playingMediaItem != null) {
            this.silenceTracker.pause(0L);
            if (getMediaAccessPolicy().getMediaAccess(playingMediaItem.getMediaAccessInfo()).canGhostListen()) {
                this.ghostListeningTracker.pause();
                return;
            } else {
                this.ghostListeningTracker.start();
                return;
            }
        }
        if (getPlayStatus().userExpectsPlayback()) {
            this.silenceTracker.start();
            this.ghostListeningTracker.pause();
        } else {
            this.silenceTracker.pause();
            this.ghostListeningTracker.pause();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkGhostListeningThresholdExceeded() {
        if (this.ghostListeningTracker.getCurrentPosition() < getPlaybackConfig().getGhostListeningTimeoutMillis() || !getPlayStatus().willPlay()) {
            return;
        }
        triggerGhostListeningException();
    }

    private void triggerGhostListeningException() {
        this.suppressCallbacks = true;
        try {
            PlayRequest currentPlayRequest = this.player.getCurrentPlayRequest();
            if (currentPlayRequest != null) {
                MetricsInfo metricsInfo = currentPlayRequest.getMetricsHandler().getMetricsInfo();
                TimeUnit timeUnit = TimeUnit.MILLISECONDS;
                long seconds = timeUnit.toSeconds(getPlaybackConfig().getGhostListeningTimeoutMillis());
                long seconds2 = timeUnit.toSeconds(Clock.now() - this.lastUserInteractionTime);
                this.player.pause(ChangeReason.GHOST_LISTENING, null);
                getPlaybackConfig().getMTSMetricsSender().sendGhostListeningInterruptedEvent(metricsInfo, seconds, seconds2);
                notifyPlaybackError(new GhostListeningException());
            }
        } finally {
            this.suppressCallbacks = false;
            notifyPlayStateChanged();
        }
    }

    private void logColdStart() {
        MediaCollectionType type;
        if (this.isNewSequencer) {
            if (getPlayStatus().willPlay() && this.desiredPlayTime == 0) {
                this.desiredPlayTime = Clock.now();
            }
            MediaItem playingMediaItem = getPlayingMediaItem();
            if (this.desiredPlayTime == 0 || playingMediaItem == null) {
                return;
            }
            this.isNewSequencer = false;
            this.sequencerNum++;
            long elapsedTimeMillis = this.sequencer.getMetricsTimer("EXT_LOAD_TIME").getElapsedTimeMillis();
            long elapsedTimeMillis2 = this.sequencer.getMetricsTimer("TIME_SINCE_READY").getElapsedTimeMillis();
            long elapsedTimeMillis3 = this.sequencer.getMetricsTimer("TIME_SINCE_CREATION").getElapsedTimeMillis();
            long jNow = Clock.now() - this.desiredPlayTime;
            long j = jNow - elapsedTimeMillis3;
            long jMax = Math.max(0L, elapsedTimeMillis + Math.min(0L, j));
            long jMax2 = Math.max(0L, ((jNow - elapsedTimeMillis2) - jMax) - j);
            long jMin = Math.min(jNow, elapsedTimeMillis2);
            logger.debug("cold start - perceivedDelay: " + jNow + " (creationDelay: " + j + ", external: " + jMax + ", sequencerOverhead: " + jMax2 + ", otherOverhead: " + jMin + ")");
            MediaCollectionInfo mediaCollectionInfo = this.sequencer.getMediaCollectionInfo();
            String metricsName = (mediaCollectionInfo == null || (type = mediaCollectionInfo.getType()) == null) ? "" : type.getMetricsName();
            MediaItemId mediaItemId = playingMediaItem.getMediaItemId(MediaItemId.Type.ASIN);
            if (mediaItemId == null) {
                mediaItemId = playingMediaItem.getMediaItemId();
            }
            getPlaybackConfig().getMTSMetricsSender().sendMetricsEvent(FlexEvent.builder("coldStart").withFlexStr1(Integer.toString(this.sequencerNum)).withFlexStr2(mediaItemId != null ? mediaItemId.getId() : "").withFlexStr3(metricsName).withFlexStr4(this.sequencer.getClass().getSimpleName()).withFlexNum1(Float.valueOf(jNow)).withFlexNum2(Float.valueOf(jMax)).withFlexNum3(Float.valueOf(j + jMax2)).withFlexNum4(Float.valueOf(jMin)).build());
        }
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected void notifyPlayStateChanged() {
        logColdStart();
        checkTrackers();
        super.notifyPlayStateChanged();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected void notifyPlaybackError(PlaybackException playbackException) {
        checkTrackers();
        super.notifyPlaybackError(playbackException);
    }

    @Override // com.amazon.music.media.playback.SequencerPlaybackProvider
    public void setSequencer(Sequencer sequencer, ControlSource controlSource) throws Throwable {
        setSequencer(sequencer, 0L, null, false, ChangeReason.NEW_SOURCE, controlSource, Clock.now());
    }

    @Override // com.amazon.music.media.playback.SequencerPlaybackProvider
    public void setSequencerAndPosition(Sequencer sequencer, long j, SequencerPlaybackProvider.MediaItemVerifier mediaItemVerifier, ControlSource controlSource) throws Throwable {
        setSequencer(sequencer, j, mediaItemVerifier, false, ChangeReason.NEW_SOURCE, controlSource, Clock.now());
    }

    @Override // com.amazon.music.media.playback.SequencerPlaybackProvider
    public void startSequencer(Sequencer sequencer, ControlSource controlSource, long j) throws Throwable {
        this.persistenceProvider.markRestored();
        setSequencer(sequencer, 0L, null, true, ChangeReason.NEW_SOURCE, controlSource, j);
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    public void stop(ChangeReason changeReason, ControlSource controlSource) throws Throwable {
        setSequencer(new NoOpSequencer(), 0L, null, false, changeReason, controlSource, Clock.now());
    }

    private void setSequencer(Sequencer sequencer, long j, SequencerPlaybackProvider.MediaItemVerifier mediaItemVerifier, boolean z, ChangeReason changeReason, ControlSource controlSource, long j2) throws Throwable {
        ChangeReason changeReason2;
        logger.info("setSequencer(" + sequencer + ", " + j + ", " + mediaItemVerifier + ", " + z + ", " + changeReason + ", " + controlSource + ")");
        Validate.notNull(sequencer);
        this.startPositionMillis = j;
        this.mediaItemVerifier = mediaItemVerifier;
        if (this.sequencer == sequencer) {
            return;
        }
        this.isNewSequencer = true;
        this.desiredPlayTime = z ? j2 : 0L;
        this.concurrencyManager.onQualifyingInteraction();
        Sequencer sequencer2 = this.sequencer;
        if (sequencer2 != null) {
            sequencer2.removeSequencerListener(this.sequencerListener);
        }
        this.sequencer = sequencer == null ? new NoOpSequencer() : sequencer;
        FilteredSequencer filteredSequencer = this.filteredSequencer;
        if (filteredSequencer != null) {
            filteredSequencer.removeSequencerListener(this.sequencerListener);
        }
        FilteredSequencer filteredSequencer2 = new FilteredSequencer(this.sequencer, getMediaAccessPolicy());
        this.filteredSequencer = filteredSequencer2;
        filteredSequencer2.addSequencerListener(this.sequencerListener);
        if (z) {
            notifyPlaybackEvent(18, controlSource);
        } else {
            notifyPlaybackEvent(17, controlSource);
        }
        this.ratingsProvider.setSource(this.sequencer.getRatingsProvider());
        this.suppressCallbacks = true;
        try {
            clearErrors(true);
            this.currentChangeReason = changeReason;
            changeReason2 = null;
        } catch (Throwable th) {
            th = th;
            changeReason2 = null;
        }
        try {
            this.player.setQueue(Collections.emptyList(), 0, 0L, z, changeReason, controlSource, j2);
            this.currentChangeReason = null;
            coordinateState(changeReason, controlSource, j2);
            this.suppressCallbacks = false;
            this.currentChangeReason = null;
            notifyPlayStateChanged();
            notifyMediaCollectionInfoChanged(sequencer.getMediaCollectionInfo());
        } catch (Throwable th2) {
            th = th2;
            this.suppressCallbacks = false;
            this.currentChangeReason = changeReason2;
            notifyPlayStateChanged();
            notifyMediaCollectionInfoChanged(sequencer.getMediaCollectionInfo());
            throw th;
        }
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected void play(ControlSource controlSource, long j) {
        Logger logger2 = logger;
        logger2.info("play: " + controlSource);
        if (this.sequencer instanceof NoOpSequencer) {
            logger2.info("NoOpSequencer, so ignoring.");
            return;
        }
        if (!this.player.getPlayStatus().willPlay()) {
            notifyPlaybackEvent(14, controlSource);
        }
        this.concurrencyManager.onQualifyingInteraction();
        play(ChangeReason.PLAY_PAUSE, controlSource, j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void play(ChangeReason changeReason, ControlSource controlSource, long j) {
        logger.info("play: " + changeReason + ", " + controlSource.getName());
        if (this.desiredPlayTime == 0) {
            this.desiredPlayTime = j;
        }
        this.suppressCallbacks = true;
        try {
            clearErrors(false);
            this.player.play(changeReason, controlSource, j);
            coordinateState(changeReason, controlSource, j);
        } finally {
            this.suppressCallbacks = false;
            notifyPlayStateChanged();
        }
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected void pause(ControlSource controlSource) {
        Logger logger2 = logger;
        logger2.info("pause: " + controlSource);
        if (this.sequencer instanceof NoOpSequencer) {
            logger2.info("NoOpSequencer, so ignoring.");
            return;
        }
        if (this.player.getPlayStatus().willPlay()) {
            notifyPlaybackEvent(12, controlSource);
        }
        pause(ChangeReason.PLAY_PAUSE, controlSource);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pause(ChangeReason changeReason, ControlSource controlSource) {
        logger.info("pause(" + changeReason + ", " + controlSource + ")");
        this.suppressCallbacks = true;
        try {
            clearErrors(false);
            this.player.pause(changeReason, controlSource);
            coordinateState(changeReason, controlSource);
        } finally {
            this.suppressCallbacks = false;
            notifyPlayStateChanged();
        }
    }

    private boolean shouldLoop() {
        if (this.sequencer.getRepeatMode() == RepeatMode.REPEAT_ONE) {
            return true;
        }
        if (this.sequencer.getRepeatMode() != RepeatMode.REPEAT_ALL) {
            return false;
        }
        MediaItem mediaItemPeek = this.filteredSequencer.peek(1);
        return mediaItemPeek != null && mediaItemPeek.equals(this.filteredSequencer.getCurrentMediaItem());
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected boolean canPlay() {
        return !this.filteredSequencer.isAfterEnd();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected PlayStatus getPlayStatus() {
        return this.player.getPlayStatus();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected PlaybackAttributes getCurrentPlaybackAttributes() {
        return this.player.getCurrentPlaybackAttributes();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected PlaybackAttributes getDynamicBestAvailablePlaybackAttributes() {
        return this.player.getDynamicBestAvailablePlaybackAttributes();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected boolean isStreaming() {
        return this.player.isStreaming();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected PlayerCapabilities getPlayerCapabilities() {
        return this.player.getPlayerCapabilities();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected void skip(int i, ChangeReason changeReason, ControlSource controlSource) {
        Logger logger2 = logger;
        logger2.info("skip: " + i + " from: " + controlSource);
        Sequencer sequencer = this.sequencer;
        if (sequencer instanceof NoOpSequencer) {
            logger2.info("NoOpSequencer, so ignoring.");
            return;
        }
        this.startPositionMillis = 0L;
        if ((sequencer.getRepeatMode() == RepeatMode.REPEAT_ONE) && (changeReason == ChangeReason.NEXT || changeReason == ChangeReason.PREVIOUS)) {
            i = 0;
        } else {
            if (Math.abs(i) > 1 && !canSkipToIndex()) {
                return;
            }
            if (i < 0 && !canSkipPrevious()) {
                if (changeReason == ChangeReason.INDEX_REQUESTED) {
                    return;
                }
                i = 0;
            } else if (i > 0) {
                if (changeReason == ChangeReason.ERROR) {
                    if (!this.sequencer.canSkipNext()) {
                        return;
                    }
                } else if (!canSkipNext()) {
                    return;
                }
            }
        }
        if (i == 0) {
            restartMediaItem(controlSource);
            return;
        }
        if (i <= 0 || this.sequencer.peek(1) != null || this.sequencer.getRepeatMode() == RepeatMode.REPEAT_ALL || this.sequencer.isFetchingUpcomingMediaItems() || this.sequencer.canFetchUpcomingMediaItems()) {
            int i2 = AnonymousClass9.$SwitchMap$com$amazon$music$media$playback$ChangeReason[changeReason.ordinal()];
            if (i2 == 1) {
                notifyPlaybackEvent(1, controlSource);
            } else if (i2 == 2) {
                notifyPlaybackEvent(2, controlSource);
            } else if (i2 == 3) {
                notifyPlaybackEvent(3, controlSource);
            }
            this.concurrencyManager.onQualifyingInteraction();
            this.suppressCallbacks = true;
            try {
                this.filteredSequencer.skip(i, changeReason);
                advancePlayer(i, changeReason, controlSource);
                coordinateState(changeReason, controlSource);
            } finally {
                this.suppressCallbacks = false;
                notifyPlayStateChanged();
            }
        }
    }

    /* JADX INFO: renamed from: com.amazon.music.media.playback.impl.DefaultPlaybackProvider$9, reason: invalid class name */
    static /* synthetic */ class AnonymousClass9 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$media$playback$ChangeReason;

        static {
            int[] iArr = new int[ChangeReason.values().length];
            $SwitchMap$com$amazon$music$media$playback$ChangeReason = iArr;
            try {
                iArr[ChangeReason.NEXT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$ChangeReason[ChangeReason.PREVIOUS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$ChangeReason[ChangeReason.INDEX_REQUESTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected void skipToIndex(int i, ControlSource controlSource) {
        Logger logger2 = logger;
        logger2.info("skipToIndex: " + i + " from: " + controlSource);
        this.startPositionMillis = 0L;
        Sequencer sequencer = this.sequencer;
        if (sequencer instanceof NoOpSequencer) {
            logger2.info("NoOpSequencer, so ignoring.");
            return;
        }
        if (sequencer.isIndexBased()) {
            if (i < 0 || (this.sequencer.getNumMediaItems() >= 0 && i >= this.sequencer.getNumMediaItems())) {
                logger2.warn("Attempting to skipToIndex out of range.");
                return;
            }
            if (i == this.sequencer.getCurrentIndex()) {
                return;
            }
            MediaItem mediaItem = this.sequencer.getMediaItem(i);
            if (mediaItem != null && !this.filteredSequencer.shouldInclude(mediaItem)) {
                logger2.warn("Attempting to skipToIndex to a MediaItem the user cannot play.");
                return;
            }
            notifyPlaybackEvent(3, controlSource);
            this.concurrencyManager.onQualifyingInteraction();
            this.suppressCallbacks = true;
            try {
                int iFromSourceSkips = this.filteredSequencer.fromSourceSkips(i - this.sequencer.getCurrentIndex());
                Sequencer sequencer2 = this.sequencer;
                ChangeReason changeReason = ChangeReason.INDEX_REQUESTED;
                sequencer2.setCurrentIndex(i, changeReason);
                if (iFromSourceSkips > 0) {
                    advancePlayer(iFromSourceSkips, changeReason, controlSource);
                }
                coordinateState(changeReason, controlSource);
                return;
            } finally {
                this.suppressCallbacks = false;
                notifyPlayStateChanged();
            }
        }
        StrictMode.crashIfStrict("Cannot skip to index of non-index-based Sequencer: " + this.sequencer);
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected float getPlaybackSpeed() {
        return this.player.getPlaybackSpeed();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected void normalSpeed(ControlSource controlSource) {
        float playbackSpeed = getPlaybackSpeed();
        Logger logger2 = logger;
        logger2.info("normalSpeed(" + controlSource + ") - curSpeed: " + playbackSpeed);
        if (playbackSpeed != 1.0f) {
            logger2.info("normalSpeed from: " + controlSource);
            notifyPlaybackEvent(10, controlSource);
        }
        this.player.normalSpeed();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected void fastForward(ControlSource controlSource) {
        float playbackSpeed = getPlaybackSpeed();
        MediaItem playingMediaItem = getPlayingMediaItem();
        if (playbackSpeed > 1.0f || !canFastForward() || playingMediaItem == null) {
            return;
        }
        logger.info("fastForward from: " + controlSource);
        notifyPlaybackEvent(8, controlSource);
        this.player.fastForward(controlSource);
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected void rewind(ControlSource controlSource) {
        float playbackSpeed = getPlaybackSpeed();
        MediaItem playingMediaItem = getPlayingMediaItem();
        if (playbackSpeed < 0.0f || !canRewind() || playingMediaItem == null) {
            return;
        }
        logger.info("rewind from: " + controlSource);
        notifyPlaybackEvent(9, controlSource);
        this.player.rewind(controlSource);
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected boolean canFastForward() {
        return this.player.canFastForward();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected boolean canRewind() {
        return this.player.canRewind();
    }

    private void advancePlayer(int i, ChangeReason changeReason, ControlSource controlSource) {
        int currentIndex = this.player.getCurrentIndex();
        int numPlayRequests = this.player.getNumPlayRequests();
        if (i > 0 && i < numPlayRequests - currentIndex) {
            this.player.setIndex(currentIndex + i, getPlayStatus().shouldBePlaying(), changeReason, controlSource, Clock.now());
        } else {
            this.player.setQueue(Collections.emptyList(), 0, 0L, getPlayStatus().willPlay(), changeReason, controlSource, Clock.now());
        }
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected void restartMediaItem(ControlSource controlSource) {
        Logger logger2 = logger;
        logger2.info("restartMediaItem: " + controlSource);
        this.startPositionMillis = 0L;
        if (this.sequencer instanceof NoOpSequencer) {
            logger2.info("NoOpSequencer, so ignoring.");
        } else if (canRestartItem()) {
            notifyPlaybackEvent(4, controlSource);
            doSeek(0L, ChangeReason.RESTART, controlSource);
        }
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected ActionAvailability getSkipNextAvailability() {
        if (this.sequencer.canSkipNext() && getPlayingMediaItem() != null && canPlay()) {
            return ActionAvailability.AVAILABLE;
        }
        return this.sequencer.getSkipNextAvailability();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected boolean canSkipNext() {
        return ActionAvailability.AVAILABLE == getSkipNextAvailability();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected boolean canSkipPrevious() {
        return this.sequencer.canSkipPrevious() && canPlay() && (!this.filteredSequencer.isAtBeginning() || getRepeatMode() == RepeatMode.REPEAT_ALL);
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected boolean canSkipToIndex() {
        return this.sequencer.canSkipToIndex() && canPlay();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected boolean canRestartItem() {
        return this.player.getCurrentPlayRequest() != null && this.player.canSeek();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected boolean canSeek() {
        return this.player.getCurrentPlayRequest() != null && this.player.canSeek();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected boolean prefersRestartOverPrevious() {
        boolean zCanSkipPrevious = canSkipPrevious();
        if (!canRestartItem()) {
            return false;
        }
        if (!zCanSkipPrevious) {
            return true;
        }
        PlayRequest currentPlayRequest = this.player.getCurrentPlayRequest();
        return currentPlayRequest != null && currentPlayRequest.getPositionTracker().getCurrentPosition() > PREFER_PREVIOUS_VS_RESTART_MILLIS;
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected void setShuffled(boolean z, boolean z2, ControlSource controlSource) {
        logger.info("setShuffled: " + z + " from " + controlSource);
        if (!z2) {
            this.startPositionMillis = 0L;
        }
        if (this.sequencer.isShuffled() == z || !this.sequencer.canShuffle()) {
            return;
        }
        notifyPlaybackEvent(z ? 24 : 25, controlSource);
        this.sequencer.setShuffled(z, z2);
        coordinateState(ChangeReason.SHUFFLE, controlSource);
        notifyPlayStateChanged();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected void setPlaybackSpeed(double d) {
        this.player.setPlaybackSpeed(d);
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected boolean canShuffle() {
        return this.sequencer.canShuffle();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected boolean isShuffled() {
        return this.sequencer.isShuffled();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected void setRepeatMode(RepeatMode repeatMode, ControlSource controlSource) {
        logger.info("setRepeatMode: " + repeatMode + " from " + controlSource);
        if (this.sequencer.getRepeatMode() == repeatMode || !this.sequencer.getAvailableRepeatModes().contains(repeatMode)) {
            return;
        }
        notifyPlaybackEvent(26, controlSource);
        this.sequencer.setRepeatMode(repeatMode);
        coordinateState(ChangeReason.REPEAT_MODE, controlSource);
        notifyPlayStateChanged();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected RepeatMode getRepeatMode() {
        return this.sequencer.getRepeatMode();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected EnumSet<RepeatMode> getAvailableRepeatModes() {
        return this.sequencer.getAvailableRepeatModes();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected void seek(long j, ChangeReason changeReason, ControlSource controlSource) {
        logger.info("seek: " + j + " from " + controlSource);
        if (canSeek()) {
            notifyPlaybackEvent(changeReason == ChangeReason.LYRICS_SEEK ? 7 : getPlayback().getPlaybackController(controlSource).getPositionMillis() < j ? 5 : 6, controlSource);
            doSeek(j, changeReason, controlSource);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doSeek(long j, ChangeReason changeReason, ControlSource controlSource) {
        if (this.player.getCurrentPlayRequest() == null || !this.player.canSeek()) {
            return;
        }
        this.concurrencyManager.onQualifyingInteraction();
        this.player.seek(j, changeReason, controlSource);
        notifyPlayStateChanged();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected void onUserInteraction(ControlSource controlSource) {
        logger.info("onUserInteraction from " + controlSource);
        this.silenceTracker.setPosition(0L);
        this.ghostListeningTracker.setPosition(0L);
        this.consecutiveErrors = 0;
        this.lastUserInteractionTime = Clock.now();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected void autoResolveNextConcurrencyCheck() {
        logger.info("autoResolveNextConcurrencyCheck");
        this.concurrencyManager.autoResolveNextConcurrencyCheck();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected void resolveMaxConcurrency(String str, boolean z, ControlSource controlSource, long j) {
        logger.info("resolveMaxConcurrency: " + controlSource);
        this.suppressCallbacks = true;
        try {
            clearErrors(true);
            this.concurrencyManager.updatePlayStatus(z ? PlayStatus.PREPARING : getPlayStatus(), getPlayingMediaItem(), getPositionMillis(), str);
            if (z) {
                this.player.play(ChangeReason.RESOLVING_CONCURRENCY, controlSource, j);
            }
            coordinateState(ChangeReason.RESOLVING_CONCURRENCY, controlSource, j);
        } finally {
            this.suppressCallbacks = false;
            notifyPlayStateChanged();
        }
    }

    @Override // com.amazon.music.media.playback.PlayerPlaybackProvider
    public Player getPlayer() {
        return this.player;
    }

    @Override // com.amazon.music.media.playback.PlayerPlaybackProvider
    public void setPlayer(Player player, ControlSource controlSource) {
        Player player2;
        boolean z = true;
        this.suppressCallbacks = true;
        if (player == null) {
            try {
                player = getPlaybackConfig().getLocalPlayer(this.mricToken);
                if (player != null) {
                    StrictMode.validationFailed(null, "To use the local Player, get it from the PlaybackConfig instead of passing in null.");
                } else {
                    StrictMode.validationFailed(null, "player cannot be null. Note that the PlaybackConfig local Player is also null and that could be the root cause.");
                    player = this.player;
                    if (!(player instanceof NoOpPlayer)) {
                        player = new NoOpPlayer(this.context);
                    }
                }
            } finally {
                this.suppressCallbacks = false;
            }
        }
        if (this.player == player) {
            return;
        }
        notifyPlaybackEvent(19, controlSource);
        Logger logger2 = logger;
        logger2.info("switching players from " + this.player + " to " + player);
        if (getConnection() == null) {
            z = false;
        }
        Player.State state = (!z || (player2 = this.player) == null) ? new Player.State() : player2.disconnect();
        logger2.info("intermediate state: " + state.toString());
        this.player = player;
        this.concurrencyManager.setConcurrencyProvider(getConcurrencyProvider());
        if (z) {
            this.player.connect(this.playerCallback, getPlaybackConfig(), state, getAudioFocusManager());
        }
        logger2.info("done switching players");
        notifyPlayStateChanged();
    }

    @Override // com.amazon.music.media.playback.PlayerPlaybackProvider
    public void setMricToken(@Nullable String str) {
        this.mricToken = str;
    }

    @Override // com.amazon.music.media.playback.PlayerPlaybackProvider
    public void setPlaybackMetricsListener(OnPlaybackMetricsListener onPlaybackMetricsListener) {
        this.player.setPlaybackMetricsListener(onPlaybackMetricsListener);
    }

    @Override // com.amazon.music.media.playback.PlayerPlaybackProvider
    public void setOnFrameChangedListener(OnFrameChangedListener onFrameChangedListener) {
        this.player.setOnFrameChangedListener(onFrameChangedListener);
    }

    @Override // com.amazon.music.media.playback.PlayerPlaybackProvider
    public void setOnPrebufferChangedListener(OnPrebufferChangedListener onPrebufferChangedListener) {
        this.player.setOnPrebufferChangedListener(onPrebufferChangedListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ConcurrencyProvider getConcurrencyProvider() {
        Player player = this.player;
        ConcurrencyProvider concurrencyProvider = player == null ? null : player.getConcurrencyProvider();
        return concurrencyProvider == null ? getPlaybackConfig().getConcurrencyProvider() : concurrencyProvider;
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected boolean isLive() {
        return this.sequencer.isLive();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected int getCurrentIndex() {
        return this.sequencer.getCurrentIndex();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected int getUnshuffledIndex(int i) {
        return this.sequencer.getUnshuffledIndex(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MediaItem getPlayingMediaItem() {
        PlayRequest currentPlayRequest = getPlayer().getCurrentPlayRequest();
        if (currentPlayRequest == null) {
            return null;
        }
        return currentPlayRequest.getMediaItem();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected MediaItem getCurrentMediaItem() {
        return this.sequencer.getCurrentMediaItem();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected long getAmountBuffered() {
        return this.player.getCurrentPlayRequest().getAmountBuffered();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected long getPositionMillis() {
        if (this.player.getCurrentPlayRequest() == null) {
            return 0L;
        }
        return this.player.getPositionMillis();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected long getDurationMillis() {
        if (this.player.getCurrentPlayRequest() == null) {
            return 0L;
        }
        return this.player.getDurationMillis();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected MediaItem peek(int i) {
        return this.sequencer.peek(i);
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected List<MediaItem> getCurrentQueue() {
        ArrayList arrayList = new ArrayList();
        List<MediaItem> previousMediaItems = this.sequencer.getPreviousMediaItems(-1);
        if (!ObjectUtils.isNullOrEmpty(previousMediaItems)) {
            arrayList.addAll(previousMediaItems);
        }
        MediaItem currentMediaItem = this.sequencer.getCurrentMediaItem();
        if (currentMediaItem != null) {
            arrayList.add(currentMediaItem);
        }
        List<MediaItem> upcomingMediaItems = this.sequencer.getUpcomingMediaItems(getPlaybackConfig().getPreviewCount());
        if (!ObjectUtils.isNullOrEmpty(upcomingMediaItems)) {
            arrayList.addAll(upcomingMediaItems);
        }
        return arrayList;
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected VolumeProviderCompat getVolumeProvider() {
        return this.player.getVolumeProvider();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected boolean canRateMediaItems() {
        return this.sequencer.getRatingsProvider().canRateMediaItems();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected String getMediaPlayerName() {
        return this.player.getName();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected MediaCollectionInfo getMediaCollectionInfo() {
        return this.sequencer.getMediaCollectionInfo();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected boolean isStopped() {
        return this.sequencer instanceof NoOpSequencer;
    }

    @Override // com.amazon.music.media.playback.SequencerPlaybackProvider
    public Sequencer getSequencer() {
        return this.sequencer;
    }

    private int getPreloadCount() {
        int iMin;
        if (isShuffled() && this.sequencer.canFetchUpcomingMediaItems()) {
            return 1;
        }
        int upcomingPreviewSize = this.sequencer.getUpcomingPreviewSize();
        if (upcomingPreviewSize < 0) {
            iMin = getPlaybackConfig().getPreviewCount();
        } else {
            iMin = Math.min(upcomingPreviewSize, getPlaybackConfig().getPreviewCount());
        }
        return Math.max(iMin, getPlaybackConfig().getPrefetchCount()) + this.additionalPreloadCount;
    }

    private void coordinateState(ChangeReason changeReason, ControlSource controlSource) {
        coordinateState(changeReason, controlSource, Clock.now());
    }

    private void coordinateState(ChangeReason changeReason, ControlSource controlSource, long j) {
        SequencerPlaybackProvider.MediaItemVerifier mediaItemVerifier;
        boolean z;
        boolean z2;
        Logger logger2 = logger;
        logger2.info("coordinateState(" + changeReason + ", " + controlSource + ")");
        if (changeReason == ChangeReason.NEW_SOURCE || changeReason == ChangeReason.SOURCE_MODIFIED) {
            this.contentException = null;
        }
        this.player.setLoopMediaItem(shouldLoop());
        MediaItem currentMediaItem = this.filteredSequencer.getCurrentMediaItem();
        if (currentMediaItem == null) {
            logger2.info("no current item in Sequencer");
            boolean zCanFetchCurrentMediaItem = this.filteredSequencer.canFetchCurrentMediaItem();
            boolean z3 = getPlayStatus().willPlay() && zCanFetchCurrentMediaItem && this.sequencerException == null;
            boolean z4 = !zCanFetchCurrentMediaItem && this.sequencerException == null;
            boolean z5 = getPlayStatus().willPlay() && this.sequencerException != null;
            if (getPlayStatus().willPlay() && !canPlay() && this.sequencerException == null) {
                this.contentException = new NoPlayableContentException(getCurrentMediaItem());
            }
            PlaybackException playbackException = this.contentException;
            if (playbackException != null) {
                notifyPlaybackError(playbackException);
            }
            if (z4) {
                RepeatMode repeatMode = getRepeatMode();
                if (repeatMode == RepeatMode.REPEAT_NONE) {
                    notifyPlaybackEvent(28, controlSource);
                }
                this.filteredSequencer.goToBeginning(changeReason);
                boolean z6 = this.filteredSequencer.getCurrentMediaItem() != null || this.filteredSequencer.canFetchCurrentMediaItem();
                z = repeatMode == RepeatMode.REPEAT_ALL && z3;
                z2 = z6;
            } else {
                z = z3;
                z2 = false;
            }
            this.player.setQueue(Collections.emptyList(), 0, 0L, z, changeReason, controlSource, j);
            if (z2) {
                coordinateState(changeReason, controlSource, j);
                return;
            }
            if (!this.filteredSequencer.isAfterEnd() && this.sequencerException == null) {
                if (this.filteredSequencer.canFetchCurrentMediaItem() && !this.filteredSequencer.isFetchingCurrentMediaItem()) {
                    this.filteredSequencer.fetchCurrentMediaItem();
                }
                if (this.filteredSequencer.canFetchUpcomingMediaItems() && !this.filteredSequencer.isFetchingUpcomingMediaItems()) {
                    this.filteredSequencer.fetchUpcomingMediaItems(getPreloadCount());
                }
            }
            if (z5) {
                notifyPlaybackError(this.sequencerException);
                return;
            }
            return;
        }
        PlayRequest currentPlayRequest = this.player.getCurrentPlayRequest();
        MTSMetricsSender mTSMetricsSender = getPlaybackConfig().getMTSMetricsSender();
        if (currentPlayRequest == null || !currentPlayRequest.getMediaItem().equals(currentMediaItem)) {
            logger2.info("curRequest differs - resetting player queue to single item");
            PlayRequest playRequest = new PlayRequest(currentMediaItem, PlayRequestStatus.NOT_STARTED, mTSMetricsSender);
            this.playerException = null;
            if (this.startPositionMillis != 0 && (mediaItemVerifier = this.mediaItemVerifier) != null && !mediaItemVerifier.isCorrectMediaItem(currentMediaItem)) {
                this.startPositionMillis = 0L;
            }
            this.mediaItemVerifier = null;
            this.player.setQueue(Collections.singletonList(playRequest), 0, this.startPositionMillis, getPlayStatus().willPlay(), changeReason, controlSource, j);
            this.startPositionMillis = 0L;
        }
        List<MediaItem> upcomingMediaItems = this.filteredSequencer.getUpcomingMediaItems(getPreloadCount());
        int size = upcomingMediaItems.size();
        if ((size < getPreloadCount() && this.filteredSequencer.canFetchUpcomingMediaItems()) && !this.filteredSequencer.isFetchingUpcomingMediaItems() && this.sequencerException == null) {
            this.filteredSequencer.fetchUpcomingMediaItems(getPreloadCount() - size);
        }
        if (size == 0) {
            return;
        }
        int currentIndex = this.player.getCurrentIndex() + 1;
        int numPlayRequests = this.player.getNumPlayRequests();
        int i = numPlayRequests - currentIndex;
        int i2 = 0;
        while (i2 < size && i2 < i && this.player.getPlayRequest(currentIndex + i2).getMediaItem().equals(upcomingMediaItems.get(i2))) {
            i2++;
        }
        if (i2 == size && i2 == i) {
            logger.info("player is up to date with " + i2 + " items");
            if (!this.filteredSequencer.isFetchingPreviousMediaItems() && peek(-1) == null && this.filteredSequencer.canFetchPreviousMediaItems()) {
                this.filteredSequencer.fetchPreviousMediaItems(1);
                return;
            }
            return;
        }
        int i3 = 0;
        while (true) {
            int i4 = i2 + i3;
            if (i4 >= size || i4 >= i || !this.player.getPlayRequest((numPlayRequests - 1) - i3).getMediaItem().equals(upcomingMediaItems.get((size - 1) - i3))) {
                break;
            } else {
                i3++;
            }
        }
        ArrayList arrayList = new ArrayList((size - i2) - i3);
        for (int i5 = i2; i5 < size - i3; i5++) {
            arrayList.add(new PlayRequest(upcomingMediaItems.get(i5), PlayRequestStatus.NOT_STARTED, mTSMetricsSender));
        }
        this.player.modifyQueue(currentIndex + i2, (i - i2) - i3, arrayList, changeReason, controlSource, j);
    }
}
