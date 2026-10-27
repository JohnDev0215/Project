package com.amazon.music.media.playback.player;

import android.content.Context;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.media.VolumeProviderCompat;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaLink;
import com.amazon.music.media.playback.OnFrameChangedListener;
import com.amazon.music.media.playback.OnPlaybackMetricsListener;
import com.amazon.music.media.playback.OnPrebufferChangedListener;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.media.playback.PlaybackAttributes;
import com.amazon.music.media.playback.PlaybackException;
import com.amazon.music.media.playback.PlayerCapabilities;
import com.amazon.music.media.playback.audioeffects.AudioEffectsControl;
import com.amazon.music.media.playback.audioeffects.BasicAudioEffectsController;
import com.amazon.music.media.playback.concurrency.ConcurrencyProvider;
import com.amazon.music.media.playback.config.BitRateSelection;
import com.amazon.music.media.playback.config.PlaybackConfig;
import com.amazon.music.media.playback.impl.AudioFocusManager;
import com.amazon.music.media.playback.player.Player;
import com.amazon.music.media.playback.player.impl.PlayRequestStatusHandler;
import com.amazon.music.media.playback.player.impl.PlaybackSpeedHandler;
import com.amazon.music.media.playback.player.impl.PrimeVideoPlayer;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.media.playback.util.StrictMode;
import com.amazon.music.media.playback.util.StringUtils;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public abstract class AbstractPlayer implements Player {
    private static final long ALMOST_FINISHED_MILLIS = TimeUnit.SECONDS.toMillis(10);
    private AudioFocusManager audioFocusManager;
    private final boolean autoSendMetricsEvents;
    private Player.Callback callback;
    private ConcurrencyProvider concurrencyProvider;
    private PlayRequest currentRequest;
    private long currentRequestDuration;
    private boolean expectingPlaybackSent;
    private boolean honorAudioFocus;
    private boolean inDoPlay;
    private boolean isDucking;
    private boolean localPlayback;
    private String name;
    private PlaybackConfig playbackConfig;
    private boolean systemPaused;
    private boolean tempPause;
    private VolumeProviderCompat volumeProvider;
    protected final Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());
    private Player.State state = new Player.State();
    private float normalVolume = 1.0f;
    private boolean shouldNormalize = false;
    private boolean canSeek = true;
    private float duckVolume = 0.3f;
    private float currentVolume = 1.0f;
    private final PlayRequestStatusHandler playRequestStatusHandler = new PlayRequestStatusHandler();
    private final PlaybackSpeedHandler<PlayRequest> playbackSpeedHandler = new PlaybackSpeedHandler<PlayRequest>() { // from class: com.amazon.music.media.playback.player.AbstractPlayer.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amazon.music.media.playback.player.impl.PlaybackSpeedHandler
        public long getCurrentPositionMillis(PlayRequest playRequest) {
            return playRequest.getPositionTracker().getCurrentPosition();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amazon.music.media.playback.player.impl.PlaybackSpeedHandler
        public long getDurationMillis(PlayRequest playRequest) {
            return playRequest.getMediaItem().getDurationMillis();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amazon.music.media.playback.player.impl.PlaybackSpeedHandler
        public void seekToMillis(PlayRequest playRequest, long j, ChangeReason changeReason, ControlSource controlSource) {
            if (playRequest == AbstractPlayer.this.currentRequest) {
                AbstractPlayer.this.seek(j, changeReason, controlSource);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amazon.music.media.playback.player.impl.PlaybackSpeedHandler
        public void onRunSpeedChanged(PlayRequest playRequest, float f) {
            if (playRequest == AbstractPlayer.this.currentRequest) {
                PlayStatus playStatus = AbstractPlayer.this.getPlayStatus();
                if (f == 1.0f) {
                    f = playStatus == PlayStatus.RENDERING ? 1.0f : 0.0f;
                }
                playRequest.getPositionTracker().setRunSpeed(f);
                if (AbstractPlayer.this.callback != null) {
                    AbstractPlayer.this.callback.onPlayStatusChanged(playStatus, playStatus, null);
                }
            }
        }
    };
    private Runnable almostFinishedCallback = new Runnable() { // from class: com.amazon.music.media.playback.player.AbstractPlayer.2
        @Override // java.lang.Runnable
        public void run() {
            if (AbstractPlayer.this.callback != null) {
                AbstractPlayer.this.callback.onMediaItemAlmostFinished();
            }
        }
    };
    private final AudioFocusManager.Listener audioFocusListener = new AudioFocusManager.Listener() { // from class: com.amazon.music.media.playback.player.AbstractPlayer.3
        @Override // com.amazon.music.media.playback.impl.AudioFocusManager.Listener
        public void onAudioFocusChanged(boolean z) {
        }

        @Override // com.amazon.music.media.playback.impl.AudioFocusManager.Listener
        public void onSystemDuck() {
            if (AbstractPlayer.this.honorAudioFocus) {
                if (AbstractPlayer.this.systemPaused) {
                    AbstractPlayer.this.systemPaused = false;
                    AbstractPlayer.this.checkShouldPlay(ChangeReason.SYSTEM_RESUME, null, false, Clock.now());
                }
                AbstractPlayer.this.setDucking(true);
            }
        }

        @Override // com.amazon.music.media.playback.impl.AudioFocusManager.Listener
        public void onSystemNormal() {
            if (AbstractPlayer.this.honorAudioFocus) {
                if (AbstractPlayer.this.systemPaused) {
                    AbstractPlayer.this.systemPaused = false;
                    AbstractPlayer.this.checkShouldPlay(ChangeReason.SYSTEM_RESUME, null, false, Clock.now());
                }
                AbstractPlayer.this.setDucking(false);
            }
        }

        @Override // com.amazon.music.media.playback.impl.AudioFocusManager.Listener
        public void onSystemPause() {
            if (AbstractPlayer.this.honorAudioFocus) {
                AbstractPlayer abstractPlayer = AbstractPlayer.this;
                abstractPlayer.systemPaused = abstractPlayer.getPlayStatus() != PlayStatus.USER_PAUSED;
                if (AbstractPlayer.this.getPlayStatus().shouldBePlaying()) {
                    AbstractPlayer abstractPlayer2 = AbstractPlayer.this;
                    PlayStatus playStatus = PlayStatus.SYSTEM_PAUSED;
                    ChangeReason changeReason = ChangeReason.SYSTEM_PAUSE;
                    abstractPlayer2.setPlayStatus(playStatus, null, changeReason, null, Clock.now());
                    AbstractPlayer.this.doPause(false, changeReason, null);
                }
                AbstractPlayer.this.setDucking(false);
            }
        }

        @Override // com.amazon.music.media.playback.impl.AudioFocusManager.Listener
        public void onSystemStop() {
            if (AbstractPlayer.this.honorAudioFocus) {
                AbstractPlayer.this.systemPaused = false;
                boolean zShouldBePlaying = AbstractPlayer.this.getPlayStatus().shouldBePlaying();
                AbstractPlayer abstractPlayer = AbstractPlayer.this;
                PlayStatus playStatus = PlayStatus.USER_PAUSED;
                ChangeReason changeReason = ChangeReason.SYSTEM_STOP;
                abstractPlayer.setPlayStatus(playStatus, null, changeReason, null, Clock.now());
                if (zShouldBePlaying) {
                    AbstractPlayer.this.doPause(false, changeReason, null);
                }
                AbstractPlayer.this.setDucking(false);
            }
        }
    };
    private final PlaybackConfig.OnConfigurationChangedListener onConfigurationChangedListener = new PlaybackConfig.OnConfigurationChangedListener() { // from class: com.amazon.music.media.playback.player.AbstractPlayer.4
        @Override // com.amazon.music.media.playback.config.PlaybackConfig.OnConfigurationChangedListener
        public void onConfigurationChanged(PlaybackConfig playbackConfig) {
            AbstractPlayer.this.playbackConfig = playbackConfig;
            AbstractPlayer.this.onConfigurationChanged();
        }
    };

    @Override // com.amazon.music.media.playback.player.Player
    public void clearData() {
    }

    @Override // com.amazon.music.media.playback.player.Player, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    protected abstract void doPause(boolean z, ChangeReason changeReason, ControlSource controlSource);

    protected abstract void doPlay(ChangeReason changeReason, ControlSource controlSource, long j);

    @Override // com.amazon.music.media.playback.player.Player
    public float getMaxVolume() {
        return 1.0f;
    }

    @Override // com.amazon.music.media.playback.player.Player
    @Nullable
    public PlayerCapabilities getPlayerCapabilities() {
        return null;
    }

    @Override // com.amazon.music.media.playback.player.Player
    public boolean isStreaming() {
        return true;
    }

    protected abstract void onConnect();

    protected abstract void onCurrentVolumeChanged(float f);

    protected abstract void onDisconnect();

    protected abstract void onQueueChanged(int i, int i2, int i3, int i4, int i5, ChangeReason changeReason, ControlSource controlSource);

    @Override // com.amazon.music.media.playback.player.Player
    public void setOnFrameChangedListener(OnFrameChangedListener onFrameChangedListener) {
    }

    @Override // com.amazon.music.media.playback.player.Player
    public void setOnPrebufferChangedListener(OnPrebufferChangedListener onPrebufferChangedListener) {
    }

    @Override // com.amazon.music.media.playback.player.Player
    public void setPlaybackMetricsListener(OnPlaybackMetricsListener onPlaybackMetricsListener) {
    }

    @Override // com.amazon.music.media.playback.player.Player
    public void setPlaybackSpeed(double d) {
    }

    protected AbstractPlayer(@NonNull Context context, @Nullable String str, @Nullable VolumeProviderCompat volumeProviderCompat, boolean z, boolean z2, boolean z3) {
        this.name = str;
        this.volumeProvider = volumeProviderCompat;
        this.autoSendMetricsEvents = z3;
        setHonorAudioFocus(z);
        setLocalPlayback(z2);
    }

    protected void setLocalPlayback(boolean z) {
        this.localPlayback = z;
        AudioFocusManager audioFocusManager = this.audioFocusManager;
        if (audioFocusManager != null) {
            audioFocusManager.setLocalPlayback(z);
        }
    }

    protected void setHonorAudioFocus(boolean z) {
        this.honorAudioFocus = z;
    }

    protected void setPlayStatus(PlayStatus playStatus, PlaybackException playbackException) {
        setPlayStatus(playStatus, playbackException, null, null, Clock.now());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setPlayStatus(PlayStatus playStatus, PlaybackException playbackException, ChangeReason changeReason, ControlSource controlSource, long j) {
        Player.Callback callback;
        if (changeReason == null && playbackException != null) {
            changeReason = ChangeReason.ERROR;
        }
        this.logger.info("setPlayStatus(" + playStatus + ")", (Throwable) playbackException);
        PlayStatus playStatus2 = this.state.getPlayStatus();
        this.state.setPlayStatus(playStatus);
        if (playStatus.shouldBePlaying() && !playStatus2.shouldBePlaying()) {
            expectPlayback(j, changeReason, controlSource);
        } else if (!playStatus.shouldBePlaying() && playStatus2.shouldBePlaying() && !this.tempPause) {
            expectNoPlayback(j, changeReason, controlSource);
        }
        if ((playStatus2 != playStatus || playbackException != null) && (callback = this.callback) != null) {
            callback.onPlayStatusChanged(playStatus2, playStatus, playbackException);
        }
        AudioFocusManager audioFocusManager = this.audioFocusManager;
        if (audioFocusManager != null) {
            audioFocusManager.setAudioWillPlay(playStatus.willPlay());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setCurrentIndex(int i, PlaybackException playbackException, ChangeReason changeReason, long j) {
        int currentIndex = getCurrentIndex();
        this.state.setCurIndex(i);
        updateCurrentRequest(j, changeReason, null);
        Player.Callback callback = this.callback;
        if (callback != null) {
            callback.onIndexChanged(currentIndex, i, changeReason, playbackException);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void notifyMetadataChanged(MediaItem mediaItem) {
        Player.Callback callback = this.callback;
        if (callback != null) {
            callback.onMetadataChanged(mediaItem);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void notifyRepeatingMediaItem() {
        Player.Callback callback = this.callback;
        if (callback != null) {
            callback.onRepeatingMediaItem();
        }
    }

    @Override // com.amazon.music.media.playback.player.Player
    public final void connect(@NonNull Player.Callback callback, @NonNull PlaybackConfig playbackConfig, Player.State state, AudioFocusManager audioFocusManager) {
        PlayStatus playStatus;
        PlayStatus playStatus2;
        long jNow = Clock.now();
        this.audioFocusManager = audioFocusManager;
        Iterator<PlayRequest> it = state.getPlayRequests().iterator();
        while (it.hasNext()) {
            it.next().setAutoSend(this.autoSendMetricsEvents);
        }
        if (audioFocusManager != null) {
            audioFocusManager.setLocalPlayback(this.localPlayback);
            audioFocusManager.addListener(this.audioFocusListener);
        }
        if (this.callback != null) {
            if (this instanceof PrimeVideoPlayer) {
                this.logger.warn("PrimeVideoPlayer already connected!");
            } else {
                throw new IllegalStateException("Already connected!");
            }
        }
        this.callback = (Player.Callback) Validate.notNull(callback, "callback cannot be null", new Object[0]);
        this.playbackConfig = (PlaybackConfig) Validate.notNull(playbackConfig, "playback config cannot be null", new Object[0]);
        PlayStatus playStatus3 = getPlayStatus();
        this.state = state;
        if (!playStatus3.shouldBePlaying() || playStatus3 == (playStatus2 = PlayStatus.SWITCHING_PLAYERS)) {
            playStatus = playStatus3;
        } else {
            StrictMode.crashIfStrict("Invalid PlayStatus while switching Players: " + playStatus3 + ". It should not be a status where ");
            this.state.setPlayStatus(playStatus2);
            playStatus = playStatus2;
        }
        ChangeReason changeReason = ChangeReason.SWITCHING_PLAYERS;
        updateCurrentRequest(jNow, changeReason, null);
        this.playbackConfig.addOnConfigurationChangedListener(this.onConfigurationChangedListener);
        this.playbackSpeedHandler.setSpeeds(playbackConfig.getFastForwardSpeed(), playbackConfig.getRewindSpeed());
        onConnect();
        if (getNumPlayRequests() > 0) {
            onQueueChanged(0, getCurrentIndex(), 0, 0, getNumPlayRequests(), changeReason, null);
        }
        checkShouldPlay(changeReason, null, playStatus == PlayStatus.SWITCHING_PLAYERS, jNow);
        updateCurrentRequest(jNow, changeReason, null);
    }

    @Override // com.amazon.music.media.playback.player.Player
    public final Player.State disconnect() {
        long jNow = Clock.now();
        if (this.callback == null) {
            if (this instanceof PrimeVideoPlayer) {
                this.logger.warn("PrimeVideoPlayer already disconnected");
            } else {
                throw new IllegalStateException("Already disconnected");
            }
        }
        this.callback = null;
        AudioFocusManager audioFocusManager = this.audioFocusManager;
        if (audioFocusManager != null) {
            audioFocusManager.removeListener(this.audioFocusListener);
        }
        ChangeReason changeReason = ChangeReason.SWITCHING_PLAYERS;
        expectNoPlayback(jNow, changeReason, null);
        abandonAudioFocus();
        if (getPlayStatus().shouldBePlaying()) {
            setPlayStatus(PlayStatus.SWITCHING_PLAYERS, null, changeReason, null, jNow);
            doPause(false, changeReason, null);
        } else if (getPlayStatus() != PlayStatus.USER_PAUSED) {
            setPlayStatus(PlayStatus.SWITCHING_PLAYERS, null, changeReason, null, jNow);
        }
        int numPlayRequests = getNumPlayRequests();
        Player.State state = this.state;
        this.state = new Player.State();
        if (numPlayRequests > 0) {
            onQueueChanged(state.getCurIndex(), 0, 0, numPlayRequests, 0, changeReason, null);
        }
        onDisconnect();
        this.playbackConfig.removeOnConfigurationChangedListener(this.onConfigurationChangedListener);
        updateCurrentRequest(jNow, changeReason, null);
        this.audioFocusManager = null;
        return state;
    }

    @Override // com.amazon.music.media.playback.player.Player
    public final void setQueue(List<PlayRequest> list, int i, long j, boolean z, ChangeReason changeReason, ControlSource controlSource, long j2) {
        int i2;
        this.logger.info("setQueue(" + StringUtils.toString(list) + ", " + i + ", " + j + ", " + z + ", " + changeReason + ", " + controlSource + ")");
        if (list == null) {
            i2 = 0;
        } else {
            int size = list.size();
            Iterator<PlayRequest> it = list.iterator();
            while (it.hasNext()) {
                it.next().setAutoSend(this.autoSendMetricsEvents);
            }
            i2 = size;
        }
        if (controlSource != null) {
            this.state.setLastSource(controlSource);
        }
        int currentIndex = getCurrentIndex();
        int numPlayRequests = getNumPlayRequests();
        this.tempPause = getPlayStatus().shouldBePlaying();
        setPlayStatus(PlayStatus.USER_PAUSED, null, changeReason, controlSource, j2);
        if (!z && this.tempPause) {
            doPause(false, changeReason, controlSource);
        } else if (this instanceof PrimeVideoPlayer) {
            ((PrimeVideoPlayer) this).handleToggleMetrics(getCurrentPlayRequest(), changeReason);
        }
        this.state.getPlayRequests().clear();
        if (list != null) {
            this.state.getPlayRequests().addAll(list);
        }
        setCurrentIndex(i, null, changeReason, j2);
        onQueueChanged(currentIndex, i, 0, numPlayRequests, i2, changeReason, controlSource);
        if (j != 0) {
            seek(j, changeReason, controlSource);
        }
        this.tempPause = false;
        if (z) {
            this.systemPaused = false;
            checkShouldPlay(changeReason, controlSource, true, j2);
        }
        updateCurrentRequest(j2, changeReason, controlSource);
    }

    /* JADX WARN: Removed duplicated region for block: B:71:0x01cd  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01f2  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0205  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0282  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0286  */
    @Override // com.amazon.music.media.playback.player.Player
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void modifyQueue(int r25, int r26, java.util.List<com.amazon.music.media.playback.player.PlayRequest> r27, com.amazon.music.media.playback.ChangeReason r28, com.amazon.music.media.playback.ControlSource r29, long r30) {
        /*
            Method dump skipped, instruction units count: 662
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.media.playback.player.AbstractPlayer.modifyQueue(int, int, java.util.List, com.amazon.music.media.playback.ChangeReason, com.amazon.music.media.playback.ControlSource, long):void");
    }

    @Override // com.amazon.music.media.playback.player.Player
    public void setIndex(int i, boolean z, ChangeReason changeReason, ControlSource controlSource, long j) {
        this.logger.info("setIndex(" + i + ", " + z + ", " + changeReason + ", " + controlSource + ")");
        if (controlSource != null) {
            this.state.setLastSource(controlSource);
        }
        int currentIndex = getCurrentIndex();
        if (currentIndex == i) {
            checkShouldPlay(changeReason, controlSource, z, j);
            return;
        }
        PlayStatus playStatus = getPlayStatus();
        if (playStatus.shouldBePlaying()) {
            setPlayStatus(PlayStatus.USER_PAUSED, null, changeReason, controlSource, j);
            doPause(true, changeReason, controlSource);
        }
        setCurrentIndex(i, null, changeReason, j);
        onQueueChanged(currentIndex, i, 0, 0, 0, changeReason, controlSource);
        checkShouldPlay(changeReason, controlSource, z || playStatus.shouldBePlaying(), j);
        if (!playStatus.shouldBePlaying() || getPlayStatus().shouldBePlaying()) {
            return;
        }
        doPause(false, changeReason, controlSource);
    }

    private void expectPlayback(long j, ChangeReason changeReason, ControlSource controlSource) {
        PlayRequest playRequest = this.currentRequest;
        if (playRequest == null || this.expectingPlaybackSent) {
            if (playRequest == null && this.state.getExpectedStartTime() == Long.MIN_VALUE) {
                this.expectingPlaybackSent = false;
                this.state.expectPlayback(changeReason, controlSource, j);
                return;
            }
            return;
        }
        if (this.state.getExpectedStartTime() == Long.MIN_VALUE) {
            this.playRequestStatusHandler.expectPlayback(this.currentRequest, j, changeReason, controlSource);
        } else {
            this.playRequestStatusHandler.expectPlayback(this.currentRequest, this.state.getExpectedStartTime(), this.state.getExpectedStartReason(), this.state.getExpectedStartSource());
        }
        this.expectingPlaybackSent = true;
        this.state.expectNoPlayback();
    }

    private void expectNoPlayback(long j, ChangeReason changeReason, ControlSource controlSource) {
        PlayRequest playRequest = this.currentRequest;
        if (playRequest != null && this.expectingPlaybackSent) {
            this.playRequestStatusHandler.expectNoPlayback(playRequest, j, changeReason, controlSource);
        }
        this.expectingPlaybackSent = false;
        this.state.expectNoPlayback();
    }

    private synchronized void updateCurrentRequest(long j, ChangeReason changeReason, ControlSource controlSource) {
        PlayRequest playRequest = getPlayRequest(this.state.getCurIndex());
        PlayRequest playRequest2 = this.currentRequest;
        if (playRequest2 != playRequest) {
            if (playRequest2 != null) {
                expectNoPlayback(j, changeReason, controlSource);
                if (changeReason != ChangeReason.SWITCHING_PLAYERS && changeReason != ChangeReason.TOGGLE) {
                    terminate(this.currentRequest, changeReason, controlSource, j);
                }
                this.currentRequest.getPositionTracker().cancelAlert(this.almostFinishedCallback);
            }
            this.currentRequest = playRequest;
            this.currentRequestDuration = 0L;
            this.playbackSpeedHandler.setCurrentItem(playRequest);
        }
        updateCurrentRequestDuration();
        if (getPlayStatus().shouldBePlaying()) {
            expectPlayback(j, changeReason, controlSource);
        } else if (!this.tempPause) {
            expectNoPlayback(j, changeReason, controlSource);
        }
    }

    private void updateCurrentRequestDuration() {
        PlayRequest playRequest = this.currentRequest;
        if (playRequest != null) {
            long durationMillis = playRequest.getMediaItem().getDurationMillis();
            long j = this.currentRequestDuration;
            if (j != durationMillis) {
                long j2 = ALMOST_FINISHED_MILLIS;
                if (durationMillis > j2) {
                    this.currentRequest.getPositionTracker().alertAtPosition(durationMillis - j2, this.almostFinishedCallback);
                } else if (j > j2) {
                    this.currentRequest.getPositionTracker().cancelAlert(this.almostFinishedCallback);
                }
            }
        }
    }

    @Override // com.amazon.music.media.playback.player.Player
    public PlayStatus getPlayStatus() {
        return this.state.getPlayStatus();
    }

    @Override // com.amazon.music.media.playback.player.Player
    @NonNull
    public PlaybackAttributes getCurrentPlaybackAttributes() {
        return PlaybackAttributes.UNKNOWN;
    }

    @Override // com.amazon.music.media.playback.player.Player
    @NonNull
    public PlaybackAttributes getDynamicBestAvailablePlaybackAttributes() {
        return PlaybackAttributes.UNKNOWN;
    }

    @Override // com.amazon.music.media.playback.player.Player
    public void play(ChangeReason changeReason, ControlSource controlSource, long j) {
        this.logger.info("play(" + changeReason + ", " + controlSource + ")");
        if (controlSource != null) {
            this.state.setLastSource(controlSource);
        }
        if (!getPlayStatus().willPlay()) {
            normalSpeed();
        }
        checkShouldPlay(changeReason, controlSource, true, j);
    }

    @Override // com.amazon.music.media.playback.player.Player
    public void pause(ChangeReason changeReason, ControlSource controlSource) {
        this.logger.info("pause(" + changeReason + ", " + controlSource + ")");
        if (controlSource != null) {
            this.state.setLastSource(controlSource);
        }
        if (getPlayStatus().willPlay()) {
            normalSpeed();
        }
        if (getPlayStatus().shouldBePlaying()) {
            setPlayStatus(PlayStatus.USER_PAUSED, null, changeReason, controlSource, Clock.now());
            doPause(false, changeReason, controlSource);
            return;
        }
        PlayStatus playStatus = getPlayStatus();
        PlayStatus playStatus2 = PlayStatus.USER_PAUSED;
        if (playStatus != playStatus2) {
            setPlayStatus(playStatus2, null, changeReason, controlSource, Clock.now());
        }
    }

    protected void terminate(PlayRequest playRequest, ChangeReason changeReason, ControlSource controlSource, long j) {
        this.logger.info("terminate(" + playRequest + ", " + changeReason + ", " + controlSource + ", " + j + ")");
        this.playRequestStatusHandler.setPlayRequestStatus(playRequest, PlayRequestStatus.TERMINATED, getPositionMillis(), j, -1.0f, null, changeReason, controlSource);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void updatePlayStatus(ChangeReason changeReason, ControlSource controlSource) {
        PlayStatus playStatus;
        this.logger.info("updatePlayStatus() - currently " + getPlayStatus());
        PlayRequest currentPlayRequest = getCurrentPlayRequest();
        if (currentPlayRequest == null) {
            if (getPlayStatus().shouldBePlaying()) {
                setPlayStatus(PlayStatus.LOADING, null, changeReason, controlSource, Clock.now());
                return;
            }
            return;
        }
        int i = AnonymousClass5.$SwitchMap$com$amazon$music$media$playback$player$PlayRequestStatus[currentPlayRequest.getPlayRequestStatus().ordinal()];
        if (i == 1) {
            playStatus = PlayStatus.PREPARING;
        } else if (i == 2) {
            playStatus = PlayStatus.SEEKING;
        } else if (i == 3) {
            playStatus = PlayStatus.BUFFERING;
        } else if (i == 4) {
            playStatus = PlayStatus.RENDERING;
        } else {
            PlayStatus playStatus2 = getPlayStatus();
            playStatus = PlayStatus.SWITCHING_PLAYERS;
            if (playStatus2 != playStatus) {
                playStatus = PlayStatus.USER_PAUSED;
            }
        }
        PlayStatus playStatus3 = playStatus;
        if (playStatus3.shouldBePlaying() || getPlayStatus().shouldBePlaying() || this.inDoPlay) {
            this.logger.info("All is good, so status is " + playStatus3 + ".");
            setPlayStatus(playStatus3, null, changeReason, controlSource, Clock.now());
        }
    }

    /* JADX INFO: renamed from: com.amazon.music.media.playback.player.AbstractPlayer$5, reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$media$playback$player$PlayRequestStatus;

        static {
            int[] iArr = new int[PlayRequestStatus.values().length];
            $SwitchMap$com$amazon$music$media$playback$player$PlayRequestStatus = iArr;
            try {
                iArr[PlayRequestStatus.PREPARING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$player$PlayRequestStatus[PlayRequestStatus.SEEKING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$player$PlayRequestStatus[PlayRequestStatus.BUFFERING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$player$PlayRequestStatus[PlayRequestStatus.RENDERING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void notifyPlaybackAttributesChanged() {
        Player.Callback callback = this.callback;
        if (callback != null) {
            callback.onPlaybackAttributesChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setPlayRequestStatus(PlayRequest playRequest, PlayRequestStatus playRequestStatus, long j, long j2, float f, PlaybackException playbackException, ChangeReason changeReason, ControlSource controlSource) {
        this.logger.info("setPlayRequestStatus(" + playRequest + ", " + playRequestStatus + ", " + changeReason + ", error: " + playbackException + ")");
        this.playRequestStatusHandler.setPlayRequestStatus(playRequest, playRequestStatus, j, j2, f, playbackException, changeReason, controlSource);
    }

    protected void setMediaLink(PlayRequest playRequest, MediaLink mediaLink) {
        this.playRequestStatusHandler.setMediaLink(playRequest, mediaLink);
    }

    @Override // com.amazon.music.media.playback.player.Player
    public long getPositionMillis() {
        PlayRequest currentPlayRequest = getCurrentPlayRequest();
        if (currentPlayRequest == null) {
            return 0L;
        }
        if (this.playbackSpeedHandler.getPlaybackSpeed() != 1.0f) {
            return this.playbackSpeedHandler.getPositionMillis();
        }
        return currentPlayRequest.getPositionTracker().getCurrentPosition();
    }

    @Override // com.amazon.music.media.playback.player.Player
    public long getDurationMillis() {
        PlayRequest currentPlayRequest = getCurrentPlayRequest();
        if (currentPlayRequest == null) {
            return 0L;
        }
        return currentPlayRequest.getMediaItem().getDurationMillis();
    }

    @Override // com.amazon.music.media.playback.player.Player
    public boolean canSeek() {
        PlayRequest playRequest;
        return this.canSeek && (playRequest = this.currentRequest) != null && playRequest.getMediaItem().canSeek();
    }

    @Override // com.amazon.music.media.playback.player.Player
    public int getNumPlayRequests() {
        return this.state.getPlayRequests().size();
    }

    @Override // com.amazon.music.media.playback.player.Player
    public int getCurrentIndex() {
        return this.state.getCurIndex();
    }

    @Override // com.amazon.music.media.playback.player.Player
    public List<PlayRequest> getQueue() {
        return this.state.getPlayRequests();
    }

    @Override // com.amazon.music.media.playback.player.Player
    public PlayRequest getCurrentPlayRequest() {
        return this.currentRequest;
    }

    @Override // com.amazon.music.media.playback.player.Player
    public PlayRequest getPlayRequest(int i) {
        if (i < 0 || i >= this.state.getPlayRequests().size()) {
            return null;
        }
        return this.state.getPlayRequests().get(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized void removeAllPlayRequests() {
        this.callback.onClearQueue();
        this.state.getPlayRequests().clear();
        this.state.setCurIndex(0);
        this.currentRequest = null;
    }

    public int indexOf(PlayRequest playRequest) {
        return this.state.getPlayRequests().indexOf(playRequest);
    }

    @Override // com.amazon.music.media.playback.player.Player
    public void setNormalVolume(float f) {
        this.normalVolume = f;
        if (this.isDucking) {
            return;
        }
        setTargetVolume(f);
    }

    @Override // com.amazon.music.media.playback.player.Player
    public float getNormalVolume() {
        return this.normalVolume;
    }

    protected void setDucking(boolean z) {
        this.isDucking = z;
        setTargetVolume(z ? this.duckVolume : getNormalVolume());
    }

    public void setDuckVolume(float f) {
        this.duckVolume = f;
        if (this.isDucking) {
            setTargetVolume(f);
        }
    }

    public float getDuckVolume() {
        return this.duckVolume;
    }

    protected void setCurrentVolume(float f) {
        if (this.currentVolume != f) {
            this.currentVolume = f;
            onCurrentVolumeChanged(f);
        }
    }

    protected void setTargetVolume(float f) {
        setCurrentVolume(f);
    }

    public float getCurrentVolume() {
        return this.currentVolume;
    }

    @Override // com.amazon.music.media.playback.player.Player
    public ConcurrencyProvider getConcurrencyProvider() {
        return this.concurrencyProvider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkShouldPlay(ChangeReason changeReason, ControlSource controlSource, boolean z, long j) {
        this.logger.info("checkShouldPlay(" + changeReason + ", " + controlSource + ", " + z + ")");
        if (z) {
            PlayStatus playStatus = getPlayStatus();
            PlayStatus playStatus2 = PlayStatus.USER_PAUSED;
            if ((playStatus == playStatus2 || this.systemPaused) && !requestAudioFocus()) {
                this.logger.error("Unable to obtain audio focus");
                setPlayStatus(playStatus2, new PlaybackException(new PlaybackException.Config(PlaybackException.Recoverability.MEDIA_ITEM).metricsName("AudioFocus").playbackError(PlaybackException.PlaybackError.AudioFocus).description("Unable to get audio focus")));
                return;
            }
        }
        this.logger.info("checkShouldPlay() systemPaused = " + this.systemPaused + " getPlayStatus().shouldBePlaying() = " + getPlayStatus().shouldBePlaying() + " getPlayStatus() = " + getPlayStatus() + " startIfPaused = " + z + " getPlayStatus() = " + getPlayStatus());
        boolean z2 = false;
        if (!this.systemPaused) {
            if (!getPlayStatus().shouldBePlaying() || getPlayStatus() == PlayStatus.SWITCHING_PLAYERS) {
                if (z || getPlayStatus() != PlayStatus.USER_PAUSED) {
                    expectPlayback(j, changeReason, controlSource);
                    this.inDoPlay = true;
                    doPlay(changeReason, controlSource, j);
                    this.inDoPlay = false;
                    return;
                }
                return;
            }
            return;
        }
        PlayStatus playStatus3 = getPlayStatus();
        PlayStatus playStatus4 = PlayStatus.SYSTEM_PAUSED;
        if ((playStatus3 == playStatus4 && !this.systemPaused) || (getPlayStatus() == PlayStatus.USER_PAUSED && z)) {
            z2 = true;
        }
        if (z2 && this.systemPaused) {
            setPlayStatus(playStatus4, null, changeReason, controlSource, j);
        }
    }

    @Override // com.amazon.music.media.playback.player.Player
    public VolumeProviderCompat getVolumeProvider() {
        return this.volumeProvider;
    }

    private boolean requestAudioFocus() {
        if (!this.honorAudioFocus) {
            return true;
        }
        this.systemPaused = false;
        setDucking(false);
        AudioFocusManager audioFocusManager = this.audioFocusManager;
        return audioFocusManager != null && audioFocusManager.requestAudioFocus();
    }

    private void abandonAudioFocus() {
        if (this.honorAudioFocus) {
            this.systemPaused = false;
            setDucking(false);
            AudioFocusManager audioFocusManager = this.audioFocusManager;
            if (audioFocusManager != null) {
                audioFocusManager.abandonAudioFocus();
            }
        }
    }

    @Override // com.amazon.music.media.playback.player.Player
    public int getAudioFocusStatus() {
        AudioFocusManager audioFocusManager = this.audioFocusManager;
        if (audioFocusManager != null) {
            return audioFocusManager.getAudioFocusStatus();
        }
        return -1;
    }

    @Override // com.amazon.music.media.playback.player.Player
    @CallSuper
    public void setLoopMediaItem(boolean z) {
        this.state.setLoopMediaItem(z);
    }

    public final boolean isLoopMediaItem() {
        return this.state.isLoopMediaItem();
    }

    @Override // com.amazon.music.media.playback.player.Player
    public float getPlaybackSpeed() {
        if (getCurrentPlayRequest() == null) {
            return 1.0f;
        }
        return this.playbackSpeedHandler.getPlaybackSpeed();
    }

    @Override // com.amazon.music.media.playback.player.Player
    public void normalSpeed() {
        this.playbackSpeedHandler.normalSpeed();
    }

    @Override // com.amazon.music.media.playback.player.Player
    public void fastForward(ControlSource controlSource) {
        if (canFastForward()) {
            this.playbackSpeedHandler.fastForward(controlSource);
        }
    }

    @Override // com.amazon.music.media.playback.player.Player
    public void rewind(ControlSource controlSource) {
        if (canRewind()) {
            this.playbackSpeedHandler.rewind(controlSource);
        }
    }

    @Override // com.amazon.music.media.playback.player.Player
    public boolean canFastForward() {
        return getCurrentPlayRequest() != null;
    }

    @Override // com.amazon.music.media.playback.player.Player
    public boolean canRewind() {
        return getCurrentPlayRequest() != null && getPositionMillis() > 0;
    }

    @Override // com.amazon.music.media.playback.player.Player
    public String getName() {
        return this.name;
    }

    @CallSuper
    protected void onConfigurationChanged() {
        this.playbackSpeedHandler.setSpeeds(this.playbackConfig.getFastForwardSpeed(), this.playbackConfig.getRewindSpeed());
    }

    public BitRateSelection getBitRateSelection() {
        PlaybackConfig playbackConfig = this.playbackConfig;
        return playbackConfig == null ? BitRateSelection.AUTO : playbackConfig.getBitRateSelection();
    }

    protected BitRateSelection getBitRateSelectionForWifi() {
        PlaybackConfig playbackConfig = this.playbackConfig;
        return playbackConfig == null ? BitRateSelection.AUTO : playbackConfig.getBitRateSelectionForWifi();
    }

    protected BitRateSelection getBitRateSelectionForOTA() {
        PlaybackConfig playbackConfig = this.playbackConfig;
        return playbackConfig == null ? BitRateSelection.AUTO : playbackConfig.getBitRateSelectionForOTA();
    }

    protected boolean isSpatialEnabled() {
        PlaybackConfig playbackConfig = this.playbackConfig;
        if (playbackConfig == null) {
            return false;
        }
        return playbackConfig.isSpatialEnabled();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PlaybackConfig getPlaybackConfig() {
        return this.playbackConfig;
    }

    @Override // com.amazon.music.media.playback.player.Player
    public AudioEffectsControl getAudioEffectsController() {
        return BasicAudioEffectsController.EMPTY_AUDIO_EFFECTS_CONTROL;
    }
}
