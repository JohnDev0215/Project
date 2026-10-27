package com.amazon.podcast.media.playback;

import Podcast.PlaybackInterface.v1_0.MediaMetadataElement;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import androidx.mediarouter.media.MediaItemMetadata;
import com.amazon.digitalmusicplayback.AudioFormat;
import com.amazon.digitalmusicplayback.MTSAttributes;
import com.amazon.music.casting.CastingDevice;
import com.amazon.music.casting.CastingException;
import com.amazon.music.casting.session.CastingConnectionCallback;
import com.amazon.music.casting.session.CastingSessionManager;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.OnFrameChangedListener;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import com.amazon.music.media.playback.OnPlaybackEventListener;
import com.amazon.music.media.playback.OnPlaybackMetricsListener;
import com.amazon.music.media.playback.OnPrebufferChangedListener;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.media.playback.PlayerPlaybackProvider;
import com.amazon.music.media.playback.SequencerPlaybackProvider;
import com.amazon.music.media.playback.config.PlaybackConfig;
import com.amazon.music.media.playback.impl.AudioFocusManager;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import com.amazon.podcast.Handlers;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.bootstrap.NetworkSettingsProvider;
import com.amazon.podcast.cast.Casting;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import com.amazon.podcast.mappers.Mappers;
import com.amazon.scrublib.TranscriptImplementation;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public class HarleyPodcastPlayer implements PodcastPlaybackController, OnPlayStateChangedListener, OnPlaybackEventListener {
    private static final String TAG = "HarleyPodcastPlayer";
    private ScheduledFuture bufferedPositionScheduledFuture;
    private PodcastPlayerCallback callback;
    private final Context context;
    private ScheduledFuture heartbeatPositionScheduledFuture;
    private boolean isDucking;
    private boolean isPreparing;
    private String mediaId;
    private final MediaMetadataCompat.Builder mediaMetadataBuilder;
    private final MediaSessionCompat mediaSessionCompat;
    private float playbackSpeed;
    private final PlaybackStateCompat.Builder playbackStateBuilder;
    private ScheduledFuture positionScheduledFuture;
    private TranscriptImplementation transcriptImplementation;
    private final Logger logger = LoggerFactory.getLogger(TAG);
    private PlayStatus previousState = null;
    private boolean isFinished = false;
    private boolean isRebufferTriggered = false;
    private Long bufferStartedAt = null;
    public OnPlaybackMetricsListener playbackMetricsListener = new OnPlaybackMetricsListener() { // from class: com.amazon.podcast.media.playback.HarleyPodcastPlayer.1
        @Override // com.amazon.music.media.playback.OnPlaybackMetricsListener
        public void streamingInitiated(int trackId, MTSAttributes mtsAttributes) {
            HarleyPodcastPlayer.this.logger.debug("streamingInitiated:  " + mtsAttributes.getInitialPlaybackDelay());
            PlaybackCache.get(HarleyPodcastPlayer.this.mediaId).updateInitialPlaybackDelayFromPlayer(Long.valueOf(mtsAttributes.getInitialPlaybackDelay()));
        }

        @Override // com.amazon.music.media.playback.OnPlaybackMetricsListener
        public void onTrackFinished(int trackId, MTSAttributes mtsAttributes) {
            HarleyPodcastPlayer.this.logger.debug("onTrackFinished");
        }

        @Override // com.amazon.music.media.playback.OnPlaybackMetricsListener
        public void streamingInitFailed(int trackId, MTSAttributes mtsAttributes) {
            HarleyPodcastPlayer.this.logger.debug("streamingInitFailed");
            String errorMessage = mtsAttributes.getErrorMessage();
            HarleyPodcastPlayer.this.logger.error("Playback Error: {}", errorMessage);
            Bundle bundle = new Bundle();
            bundle.putString("playbackError", errorMessage);
            HarleyPodcastPlayer.this.mediaSessionCompat.setPlaybackState(HarleyPodcastPlayer.this.playbackStateBuilder.setState(7, HarleyPodcastPlayer.this.playbackController.getPositionMillis(), HarleyPodcastPlayer.this.playbackSpeed).setExtras(bundle).build());
        }

        @Override // com.amazon.music.media.playback.OnPlaybackMetricsListener
        public void playbackTerminated(int trackId, MTSAttributes mtsAttributes) {
            HarleyPodcastPlayer.this.logger.debug("playbackTerminated");
        }

        @Override // com.amazon.music.media.playback.OnPlaybackMetricsListener
        public void streamingRequiredRebuffering(int trackId, float durationSeconds, MTSAttributes mtsAttributes) {
            HarleyPodcastPlayer.this.logger.debug("streamingRequiredRebuffering");
        }
    };
    private OnFrameChangedListener frameChangedListener = new OnFrameChangedListener() { // from class: com.amazon.podcast.media.playback.HarleyPodcastPlayer.2
        @Override // com.amazon.music.media.playback.OnFrameChangedListener
        public void frameReady(int i, int i1, AudioFormat audioFormat, ArrayList<ByteBuffer> arrayList) {
            arrayList.get(audioFormat.getChannelCount() - 1).order(ByteOrder.nativeOrder());
            int iRemaining = arrayList.get(audioFormat.getChannelCount() - 1).asFloatBuffer().remaining();
            final double[] dArr = new double[iRemaining];
            for (int i2 = 0; i2 < iRemaining; i2++) {
                dArr[i2] = (short) (r3.get(i2) * 32767.0f);
            }
            Handlers.INSTANCE.getBackground().post(new Runnable() { // from class: com.amazon.podcast.media.playback.HarleyPodcastPlayer.2.1
                @Override // java.lang.Runnable
                public void run() {
                    HarleyPodcastPlayer.this.transcriptImplementation = Podcast.getPlayback().getTranscriptImplementation();
                    if (HarleyPodcastPlayer.this.transcriptImplementation == null) {
                        return;
                    }
                    HarleyPodcastPlayer.this.transcriptImplementation.putData(dArr);
                }
            });
        }
    };
    private OnPrebufferChangedListener prebufferChangedListener = new OnPrebufferChangedListener() { // from class: com.amazon.podcast.media.playback.HarleyPodcastPlayer.3
        @Override // com.amazon.music.media.playback.OnPrebufferChangedListener
        public void prebufferChanged(final int trackID, final long prebufferStart, final long prebufferEnd) {
            HarleyPodcastPlayer.this.logger.debug("testHarley prebufferStart: " + prebufferStart);
            HarleyPodcastPlayer.this.logger.debug("testHarley prebufferEnd: " + prebufferEnd);
            if (prebufferEnd == 0) {
                HarleyPodcastPlayer.this.isRebufferTriggered = true;
            }
        }
    };
    private CastingConnectionCallback castingConnectionCallback = new CastingConnectionCallback() { // from class: com.amazon.podcast.media.playback.HarleyPodcastPlayer.5
        @Override // com.amazon.music.casting.session.CastingConnectionCallback
        public void onConnected(CastingDevice castingDevice) {
            HarleyPodcastPlayer.this.setVolume(0.0f);
        }

        @Override // com.amazon.music.casting.session.CastingConnectionCallback
        public void onDisconnected(TerminationReason reason) {
            HarleyPodcastPlayer.this.pause();
            HarleyPodcastPlayer.this.setVolume(1.0f);
        }

        @Override // com.amazon.music.casting.session.CastingConnectionCallback
        public void onError(CastingException e) {
            HarleyPodcastPlayer.this.playbackController.getVolumeProvider().onSetVolumeTo(1);
        }
    };
    private final AudioFocusManager.Listener audioFocusListener = new AudioFocusManager.Listener() { // from class: com.amazon.podcast.media.playback.HarleyPodcastPlayer.6
        @Override // com.amazon.music.media.playback.impl.AudioFocusManager.Listener
        public void onSystemDuck() {
            HarleyPodcastPlayer.this.logger.debug("Harley-onSystemDuck");
        }

        @Override // com.amazon.music.media.playback.impl.AudioFocusManager.Listener
        public void onSystemNormal() {
            HarleyPodcastPlayer.this.logger.debug("Harley-onSystemNormal");
            PlaybackCache.get(HarleyPodcastPlayer.this.mediaId).updatePlaybackRequestedAtTimstampMS(Long.valueOf(System.currentTimeMillis()));
        }

        @Override // com.amazon.music.media.playback.impl.AudioFocusManager.Listener
        public void onSystemPause() {
            HarleyPodcastPlayer.this.logger.debug("Harley-onSystemPause");
            HarleyPodcastPlayer.this.setPlaybackStatePaused();
        }

        @Override // com.amazon.music.media.playback.impl.AudioFocusManager.Listener
        public void onSystemStop() {
            HarleyPodcastPlayer.this.logger.debug("Harley-onSystemStop");
            HarleyPodcastPlayer.this.setPlaybackStatePaused();
        }

        @Override // com.amazon.music.media.playback.impl.AudioFocusManager.Listener
        public void onAudioFocusChanged(final boolean hasAudioFocus) {
            HarleyPodcastPlayer.this.logger.debug("Harley-onAudioFocusChanged: " + hasAudioFocus);
        }
    };
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private final ScheduledExecutorService heartbeatExecutorService = Executors.newSingleThreadScheduledExecutor();
    private PlaybackController playbackController = com.amazon.music.media.playback.Playback.getInstance().getPlaybackController(ControlSource.APP_UI);
    private final int seekModeTime = 0;

    @Override // com.amazon.podcast.media.playback.PodcastPlaybackController
    public int fastForwardOffsetMS() {
        return 0;
    }

    @Override // com.amazon.podcast.media.playback.PodcastPlaybackController
    public boolean isPlaying() {
        return false;
    }

    @Override // com.amazon.podcast.media.playback.PodcastPlaybackController
    public int rewindOffsetMS() {
        return 0;
    }

    @Override // com.amazon.podcast.media.playback.PodcastPlaybackController
    public void setVolume(final float volume) {
    }

    public HarleyPodcastPlayer(final Context context, final PodcastPlayerCallback callback, final MediaSessionCompat mediaSessionCompat, final float playbackSpeed, final PlaybackStateCompat.Builder playbackStateBuilder, final MediaMetadataCompat.Builder mediaMetadataBuilder) {
        this.callback = callback;
        this.context = context;
        this.mediaSessionCompat = mediaSessionCompat;
        this.playbackStateBuilder = playbackStateBuilder;
        this.mediaMetadataBuilder = mediaMetadataBuilder;
        this.playbackSpeed = playbackSpeed;
    }

    @Override // com.amazon.podcast.media.playback.PodcastPlaybackController
    public void prepareMedia(final String uriString, final Bundle extras, final String mediaId, final String audioStreamFormat) {
        this.isPreparing = true;
        this.isFinished = false;
        this.playbackController.addOnPlayStateChangedListener(this);
        this.playbackController.addOnPlaybackEventListener(this);
        com.amazon.music.media.playback.Playback.getInstance().getAudioFocusManager().addListener(this.audioFocusListener);
        this.mediaId = mediaId;
        boolean z = extras.getBoolean("isMediaDownloaded");
        final NetworkSettingsProvider networkSettingsProvider = Podcast.getNetworkSettingsProvider();
        boolean zIsStreamingRestricted = networkSettingsProvider.isStreamingRestricted(this.context);
        if (!z && zIsStreamingRestricted) {
            setPlaybackStateForStreamingRestricted();
            Handlers.INSTANCE.getForeground().post(new Runnable() { // from class: com.amazon.podcast.media.playback.HarleyPodcastPlayer.4
                @Override // java.lang.Runnable
                public void run() {
                    networkSettingsProvider.showStreamingRestricted(HarleyPodcastPlayer.this.context);
                }
            });
            return;
        }
        PodcastIndexedSequencer podcastIndexedSequencer = new PodcastIndexedSequencer();
        podcastIndexedSequencer.loadPodcastMediaItem(uriString);
        SequencerPlaybackProvider sequencerPlaybackProvider = setSequencerPlaybackProvider();
        if (sequencerPlaybackProvider == null) {
            this.logger.debug("No SequencerPlaybackProvider available. CANNOT start Podcast Playback");
        } else {
            sequencerPlaybackProvider.setSequencer(podcastIndexedSequencer, ControlSource.APP_UI);
        }
    }

    @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
    public void onPlayStateChanged() {
        PlaybackController playbackController;
        MediaItem currentMediaItem = this.playbackController.getCurrentMediaItem();
        if (currentMediaItem == null || currentMediaItem.getType() == MediaItem.Type.PODCAST_CONTENT) {
            PlayStatus playStatus = this.playbackController.getPlayStatus();
            this.logger.info("Harley-onPlayStateChanged: " + playStatus.toString());
            if (this.callback != null && (playbackController = this.playbackController) != null && playbackController.getDurationMillis() > 0) {
                this.callback.onMediaBuffered(this.playbackController.getDurationMillis());
            }
            int i = AnonymousClass7.$SwitchMap$com$amazon$music$media$playback$PlayStatus[playStatus.ordinal()];
            if (i == 1 || i == 2) {
                setPlaybackStateBuffering();
            } else if (i == 3) {
                Playback playback = Podcast.getPlayback();
                if (this.callback != null && playback.isRestoringPlayback()) {
                    this.callback.onMediaBuffered(this.playbackController.getDurationMillis());
                    this.callback.onPlaybackPositionChanged(this.playbackController.getPositionMillis());
                }
                setPlaybackStatePlaying();
            } else if (i == 4 && this.isPreparing) {
                setPlaybackStatePaused();
            }
            this.previousState = playStatus;
        }
    }

    /* JADX INFO: renamed from: com.amazon.podcast.media.playback.HarleyPodcastPlayer$7, reason: invalid class name */
    static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$media$playback$PlayStatus;

        static {
            int[] iArr = new int[PlayStatus.values().length];
            $SwitchMap$com$amazon$music$media$playback$PlayStatus = iArr;
            try {
                iArr[PlayStatus.PREPARING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlayStatus[PlayStatus.SEEKING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlayStatus[PlayStatus.RENDERING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlayStatus[PlayStatus.USER_PAUSED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlayStatus[PlayStatus.SYSTEM_PAUSED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    @Override // com.amazon.music.media.playback.OnPlaybackEventListener
    public void onPlaybackEvent(int i, ControlSource controlSource) {
        this.logger.info("Harley-onPlaybackEvent: " + i);
        if (11 == i) {
            setPlaybackStateFinished();
            return;
        }
        if (12 == i || 15 == i) {
            setPlaybackStatePaused();
            return;
        }
        if (8 == i || 5 == i) {
            long durationMillis = this.playbackController.getDurationMillis() - this.playbackController.getPositionMillis();
            this.logger.info("Harley-remainingDuration: " + durationMillis);
            if (durationMillis == 0) {
                setPlaybackStateFinished();
            }
        }
    }

    @Override // com.amazon.podcast.media.playback.PodcastPlaybackController
    public void playMedia(final String mediaId, final boolean isDownloaded) {
        if (Casting.isCasting()) {
            setVolume(0.0f);
        } else {
            setVolume(1.0f);
        }
        this.mediaId = mediaId;
        boolean zIsStreamingRestricted = Podcast.getNetworkSettingsProvider().isStreamingRestricted(this.context);
        if ((isDownloaded || !zIsStreamingRestricted) && this.playbackController != null) {
            PlaybackCache.get(mediaId).updatePlaybackRequestedAtTimstampMS(Long.valueOf(System.currentTimeMillis()));
            this.playbackController.play();
        }
    }

    @Override // com.amazon.podcast.media.playback.PodcastPlaybackController
    public void playMedia(final String mediaId, final boolean isDownloaded, final long playbackOffset) {
        PlaybackController playbackController;
        if (Casting.isCasting()) {
            setVolume(0.0f);
        } else {
            setVolume(1.0f);
        }
        this.mediaId = mediaId;
        boolean zIsStreamingRestricted = Podcast.getNetworkSettingsProvider().isStreamingRestricted(this.context);
        if ((isDownloaded || !zIsStreamingRestricted) && (playbackController = this.playbackController) != null) {
            playbackController.seek(playbackOffset, this.seekModeTime);
            PlaybackCache.get(mediaId).updatePlaybackRequestedAtTimstampMS(Long.valueOf(System.currentTimeMillis()));
            this.playbackController.play();
        }
    }

    @Override // com.amazon.podcast.media.playback.PodcastPlaybackController
    public void play() {
        if (this.playbackController == null) {
            return;
        }
        PlaybackCache.get(this.mediaId).updatePlaybackRequestedAtTimstampMS(Long.valueOf(System.currentTimeMillis()));
        this.playbackController.play();
    }

    @Override // com.amazon.podcast.media.playback.PodcastPlaybackController
    public void pause() {
        PlaybackController playbackController = this.playbackController;
        if (playbackController == null) {
            return;
        }
        playbackController.pause();
    }

    @Override // com.amazon.podcast.media.playback.PodcastPlaybackController
    public void fastForward() {
        PlaybackController playbackController = this.playbackController;
        if (playbackController == null) {
            return;
        }
        long durationMillis = playbackController.getDurationMillis();
        long positionMillis = this.playbackController.getPositionMillis() + 30000;
        if (positionMillis >= durationMillis) {
            return;
        }
        PlaybackCache.get(this.mediaId).updatePlaybackRequestedAtTimstampMS(Long.valueOf(System.currentTimeMillis()));
        this.playbackController.seek(positionMillis, this.seekModeTime);
        PodcastPlayerCallback podcastPlayerCallback = this.callback;
        if (podcastPlayerCallback == null) {
            return;
        }
        podcastPlayerCallback.onPlaybackPositionChanged(positionMillis);
    }

    @Override // com.amazon.podcast.media.playback.PodcastPlaybackController
    public void rewind() {
        PlaybackController playbackController = this.playbackController;
        if (playbackController == null) {
            return;
        }
        long positionMillis = playbackController.getPositionMillis() - 15000;
        if (positionMillis <= 0) {
            return;
        }
        PlaybackCache.get(this.mediaId).updatePlaybackRequestedAtTimstampMS(Long.valueOf(System.currentTimeMillis()));
        this.playbackController.seek(positionMillis, this.seekModeTime);
        PodcastPlayerCallback podcastPlayerCallback = this.callback;
        if (podcastPlayerCallback == null) {
            return;
        }
        podcastPlayerCallback.onPlaybackPositionChanged(positionMillis);
    }

    @Override // com.amazon.podcast.media.playback.PodcastPlaybackController
    public void setPlaybackSpeed(final float speed) {
        if (this.playbackController == null) {
            return;
        }
        this.playbackSpeed = speed;
        ScheduledFuture scheduledFuture = this.heartbeatPositionScheduledFuture;
        if (!(scheduledFuture == null)) {
            scheduledFuture.cancel(true);
        }
        PlayStatus playStatus = this.previousState;
        if (!(playStatus == null) && playStatus == PlayStatus.RENDERING) {
            this.heartbeatPositionScheduledFuture = scheduleHeartbeat();
        }
        this.playbackController.setPlaybackSpeed(speed);
    }

    @Override // com.amazon.podcast.media.playback.PodcastPlaybackController
    public void stop() {
        PlaybackController playbackController = this.playbackController;
        if (playbackController == null) {
            return;
        }
        playbackController.stop(ChangeReason.NEW_SOURCE);
        ScheduledFuture scheduledFuture = this.positionScheduledFuture;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
        ScheduledFuture scheduledFuture2 = this.heartbeatPositionScheduledFuture;
        if (!(scheduledFuture2 == null)) {
            scheduledFuture2.cancel(true);
        }
        Bundle bundle = new Bundle();
        bundle.putString("mediaId", this.mediaId);
        this.mediaSessionCompat.setPlaybackState(this.playbackStateBuilder.setState(1, this.playbackController.getPositionMillis(), this.playbackSpeed).setExtras(bundle).build());
        PodcastPlayerCallback podcastPlayerCallback = this.callback;
        if (podcastPlayerCallback == null) {
            return;
        }
        podcastPlayerCallback.onPlaybackPaused();
    }

    @Override // com.amazon.podcast.media.playback.PodcastPlaybackController
    public void clear() {
        if (this.playbackController == null) {
            return;
        }
        cancelScheduledFutures();
        if (this.playbackController.getPlayStatus().equals(PlayStatus.RENDERING) || this.playbackController.getPlayStatus().equals(PlayStatus.BUFFERING)) {
            stop();
        }
        PlaybackConfig playbackConfig = com.amazon.music.media.playback.Playback.getInstance().getPlaybackConfig();
        if (playbackConfig != null) {
            playbackConfig.setCustomKeyHandlingRequired(false);
        }
        this.mediaSessionCompat.setPlaybackState(this.playbackStateBuilder.setState(0, 0L, this.playbackSpeed).setExtras(null).build());
        this.playbackController.removeOnPlayStateChangedListener(this);
        this.playbackController.removeOnPlaybackEventListener(this);
        com.amazon.music.media.playback.Playback.getInstance().getAudioFocusManager().removeListener(this.audioFocusListener);
        this.playbackController = null;
        PodcastPlayerCallback podcastPlayerCallback = this.callback;
        if (podcastPlayerCallback == null) {
            return;
        }
        podcastPlayerCallback.onPlaybackCleared();
        if (Podcast.isFireOS()) {
            return;
        }
        CastingSessionManager.getInstance().unregisterListener(this.castingConnectionCallback);
    }

    @Override // com.amazon.podcast.media.playback.PodcastPlaybackController
    public void clearNotifications() {
        PodcastPlayerCallback podcastPlayerCallback = this.callback;
        if (podcastPlayerCallback == null) {
            return;
        }
        podcastPlayerCallback.onPlaybackRemovedFromLockScreen();
    }

    @Override // com.amazon.podcast.media.playback.PodcastPlaybackController
    public void seekTo(final long position) {
        PlaybackController playbackController = this.playbackController;
        if (playbackController == null || playbackController.getCurrentMediaItem() == null) {
            return;
        }
        PlaybackCache.get(this.mediaId).updatePlaybackRequestedAtTimstampMS(Long.valueOf(System.currentTimeMillis()));
        this.playbackController.seek(position, this.seekModeTime);
        PodcastPlayerCallback podcastPlayerCallback = this.callback;
        if (podcastPlayerCallback == null) {
            return;
        }
        podcastPlayerCallback.onPlaybackPositionChanged(position);
    }

    public void setPlaybackStateBuffering() {
        this.bufferStartedAt = Long.valueOf(System.currentTimeMillis());
        this.mediaSessionCompat.setPlaybackState(this.playbackStateBuilder.setState(6, this.playbackController.getPositionMillis(), this.playbackSpeed).setExtras(null).build());
        ScheduledFuture scheduledFuture = this.bufferedPositionScheduledFuture;
        if (!(scheduledFuture == null)) {
            scheduledFuture.cancel(true);
        }
        this.bufferedPositionScheduledFuture = this.executorService.scheduleAtFixedRate(new BufferedPositionRunnable(), 0L, 200L, TimeUnit.MILLISECONDS);
        ScheduledFuture scheduledFuture2 = this.heartbeatPositionScheduledFuture;
        if (scheduledFuture2 == null) {
            return;
        }
        scheduledFuture2.cancel(true);
    }

    public void setPlaybackStatePlaying() {
        Bundle bundle = new Bundle();
        PlayStatus playStatus = this.previousState;
        if (playStatus == PlayStatus.PREPARING || (playStatus == PlayStatus.SEEKING && this.isRebufferTriggered)) {
            if (this.isPreparing) {
                this.isPreparing = false;
                bundle.putString("streamingInitiated", "");
                PodcastPlayerCallback podcastPlayerCallback = this.callback;
                if (podcastPlayerCallback == null) {
                    return;
                } else {
                    podcastPlayerCallback.onMediaBuffered(this.playbackController.getDurationMillis());
                }
            } else {
                bundle.putString("streamingRebuffered", "");
                if (this.bufferStartedAt != null) {
                    bundle.putLong("rebufferDurationMS", Long.valueOf(System.currentTimeMillis() - this.bufferStartedAt.longValue()).longValue());
                    this.isRebufferTriggered = false;
                }
            }
        }
        ScheduledFuture scheduledFuture = this.positionScheduledFuture;
        if (!(scheduledFuture == null)) {
            scheduledFuture.cancel(true);
        }
        this.positionScheduledFuture = this.executorService.scheduleAtFixedRate(new PositionRunnable(), 0L, 200L, TimeUnit.MILLISECONDS);
        ScheduledFuture scheduledFuture2 = this.heartbeatPositionScheduledFuture;
        if (!(scheduledFuture2 == null)) {
            scheduledFuture2.cancel(true);
        }
        this.heartbeatPositionScheduledFuture = scheduleHeartbeat();
        this.mediaSessionCompat.setPlaybackState(this.playbackStateBuilder.setState(3, this.playbackController.getPositionMillis(), this.playbackSpeed).setExtras(bundle).build());
        PodcastPlayerCallback podcastPlayerCallback2 = this.callback;
        if (podcastPlayerCallback2 == null) {
            return;
        }
        podcastPlayerCallback2.onPlaybackStarted();
    }

    private final class PositionRunnable implements Runnable {
        private PositionRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (HarleyPodcastPlayer.this.callback == null) {
                    return;
                }
                HarleyPodcastPlayer.this.callback.onPlaybackPositionChanged(HarleyPodcastPlayer.this.playbackController.getPositionMillis());
            } catch (Exception e) {
                HarleyPodcastPlayer.this.logger.error("PositionRunnable: ", (Throwable) e);
            }
        }
    }

    private final class BufferedPositionRunnable implements Runnable {
        private BufferedPositionRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (HarleyPodcastPlayer.this.callback == null) {
                    return;
                }
                HarleyPodcastPlayer.this.callback.onBufferedPositionChanged(HarleyPodcastPlayer.this.playbackController.getAmountBuffered());
            } catch (Exception e) {
                HarleyPodcastPlayer.this.logger.error("BufferedPositionRunnable: ", (Throwable) e);
            }
        }
    }

    public void setPlaybackStatePaused() {
        cancelScheduledFutures();
        this.mediaSessionCompat.setPlaybackState(this.playbackStateBuilder.setState(2, this.playbackController.getPositionMillis(), this.playbackSpeed).setExtras(null).build());
        PodcastPlayerCallback podcastPlayerCallback = this.callback;
        if (podcastPlayerCallback == null) {
            return;
        }
        podcastPlayerCallback.onPlaybackPaused();
    }

    public void setPlaybackStateFinished() {
        if (this.isFinished) {
            return;
        }
        this.isFinished = true;
        cancelScheduledFutures();
        Bundle bundle = new Bundle();
        bundle.putString("playbackFinished", "");
        this.mediaSessionCompat.setPlaybackState(this.playbackStateBuilder.setState(2, this.playbackController.getPositionMillis(), this.playbackSpeed).setExtras(bundle).build());
    }

    public void setPlaybackStateForStreamingRestricted() {
        this.logger.error("Playback Error, streaming is restricted");
        Bundle bundle = new Bundle();
        bundle.putString("streamingRestricted", "");
        this.mediaSessionCompat.setPlaybackState(this.playbackStateBuilder.setState(7, 0L, this.playbackSpeed).setExtras(bundle).build());
    }

    public ScheduledFuture scheduleHeartbeat() {
        if (PodcastFeatureGating.HEARTBEAT.isEnabled()) {
            return this.heartbeatExecutorService.scheduleAtFixedRate(new HeartbeatPositionRunnable(), 0L, getHeartbeatPeriodMilliseconds(), TimeUnit.MILLISECONDS);
        }
        return null;
    }

    private final class HeartbeatPositionRunnable implements Runnable {
        private HeartbeatPositionRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (HarleyPodcastPlayer.this.callback == null) {
                    return;
                }
                HarleyPodcastPlayer.this.callback.onHeartbeatPositionChanged(HarleyPodcastPlayer.this.playbackController.getPositionMillis());
            } catch (Exception e) {
                HarleyPodcastPlayer.this.logger.error("HeartbeatPositionRunnable: ", (Throwable) e);
            }
        }
    }

    public long getHeartbeatPeriodMilliseconds() {
        if (this.playbackSpeed == 0.0f) {
            return 1000L;
        }
        return Math.round(1000.0f / r0);
    }

    @Override // com.amazon.podcast.media.playback.PodcastPlaybackController
    public void removeCallback() {
        this.callback = null;
    }

    @Override // com.amazon.podcast.media.playback.PodcastPlaybackController
    public void setMediaMetadata(Bundle extras) {
        MediaMetadataElement mediaMetadataElement;
        if (extras == null) {
            return;
        }
        try {
            String string = extras.getString("mediaMetadataElement", null);
            if (string == null || (mediaMetadataElement = (MediaMetadataElement) Mappers.mapper().readValue(string, MediaMetadataElement.class)) == null) {
                return;
            }
            this.mediaMetadataBuilder.putString("android.media.metadata.MEDIA_ID", mediaMetadataElement.getMediaId());
            this.mediaMetadataBuilder.putString("android.media.metadata.ALBUM_ART_URI", mediaMetadataElement.getArtwork());
            this.mediaMetadataBuilder.putString("android.media.metadata.DISPLAY_TITLE", mediaMetadataElement.getTitle());
            this.mediaMetadataBuilder.putString("android.media.metadata.DISPLAY_SUBTITLE", mediaMetadataElement.getSubtitle());
            this.mediaMetadataBuilder.putLong(MediaItemMetadata.KEY_DURATION, Podcast.getPlayback().getPlaybackDuration());
            this.mediaMetadataBuilder.putString("android.media.metadata.DISPLAY_ICON_URI", mediaMetadataElement.getLogo());
            this.mediaMetadataBuilder.putString(MediaItemMetadata.KEY_TITLE, mediaMetadataElement.getTitle());
            this.mediaMetadataBuilder.putString(MediaItemMetadata.KEY_ARTIST, mediaMetadataElement.getSubtitle());
            this.mediaSessionCompat.setMetadata(this.mediaMetadataBuilder.build());
        } catch (IOException e) {
            this.logger.error("Error reading MediaMetadataElement from extras bundle for the MediaSession", (Throwable) e);
        }
    }

    @Override // com.amazon.podcast.media.playback.PodcastPlaybackController
    public void updateMetadataKeyDuration() {
        this.mediaMetadataBuilder.putLong(MediaItemMetadata.KEY_DURATION, Podcast.getPlayback().getPlaybackDuration());
        this.mediaSessionCompat.setMetadata(this.mediaMetadataBuilder.build());
    }

    @Override // com.amazon.podcast.media.playback.PodcastPlaybackController
    public boolean isDucking() {
        return this.isDucking;
    }

    @Override // com.amazon.podcast.media.playback.PodcastPlaybackController
    public void duck() {
        this.isDucking = true;
        setVolume(0.3f);
    }

    @Override // com.amazon.podcast.media.playback.PodcastPlaybackController
    public void unDuck() {
        this.isDucking = false;
        setVolume(1.0f);
    }

    private void cancelScheduledFutures() {
        ScheduledFuture scheduledFuture = this.positionScheduledFuture;
        if (!(scheduledFuture == null)) {
            scheduledFuture.cancel(true);
        }
        ScheduledFuture scheduledFuture2 = this.heartbeatPositionScheduledFuture;
        if (!(scheduledFuture2 == null)) {
            scheduledFuture2.cancel(true);
        }
        ScheduledFuture scheduledFuture3 = this.bufferedPositionScheduledFuture;
        if (scheduledFuture3 == null) {
            return;
        }
        scheduledFuture3.cancel(true);
    }

    private SequencerPlaybackProvider setSequencerPlaybackProvider() {
        PlaybackConfig playbackConfig = com.amazon.music.media.playback.Playback.getInstance().getPlaybackConfig();
        playbackConfig.setCustomKeyHandlingRequired(true);
        PlayerPlaybackProvider defaultPlaybackProvider = playbackConfig.getDefaultPlaybackProvider(null, this.playbackMetricsListener, this.frameChangedListener, this.prebufferChangedListener);
        if (!(defaultPlaybackProvider instanceof SequencerPlaybackProvider)) {
            return null;
        }
        com.amazon.music.media.playback.Playback.getInstance().setPlaybackProvider(defaultPlaybackProvider);
        return (SequencerPlaybackProvider) defaultPlaybackProvider;
    }
}
