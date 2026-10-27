package com.amazon.podcast.media.playback;

import Podcast.PlaybackInterface.v1_0.MediaMetadataElement;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import androidx.mediarouter.media.MediaItemMetadata;
import com.amazon.music.casting.CastingDevice;
import com.amazon.music.casting.CastingException;
import com.amazon.music.casting.session.CastingConnectionCallback;
import com.amazon.music.casting.session.CastingSessionManager;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import com.amazon.podcast.Handlers;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.bootstrap.NetworkSettingsProvider;
import com.amazon.podcast.cast.Casting;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import com.amazon.podcast.mappers.Mappers;
import com.amazon.podcast.transcription.PodcastAudioProcessor;
import com.amazon.podcast.transcription.TranscriptProvider;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.audio.AudioProcessor;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
final class PodcastPlayer implements Player.EventListener, PodcastPlaybackController {
    private ScheduledFuture bufferedPositionScheduledFuture;
    private PodcastPlayerCallback callback;
    private final Context context;
    private SimpleExoPlayer exoPlayer;
    private ScheduledFuture heartbeatPositionScheduledFuture;
    private boolean isDucking;
    private boolean isPreparing;
    private String mediaId;
    private final MediaMetadataCompat.Builder mediaMetadataBuilder;
    private final MediaSessionCompat mediaSessionCompat;
    private float playbackSpeed;
    private final PlaybackStateCompat.Builder playbackStateBuilder;
    private ScheduledFuture positionScheduledFuture;
    private Integer previousState;
    private final Logger logger = LoggerFactory.getLogger("PodcastPlayer");
    private Long bufferStartedAt = null;
    private CastingConnectionCallback castingConnectionCallback = new CastingConnectionCallback() { // from class: com.amazon.podcast.media.playback.PodcastPlayer.1
        @Override // com.amazon.music.casting.session.CastingConnectionCallback
        public void onConnected(CastingDevice castingDevice) {
            PodcastPlayer.this.setVolume(0.0f);
        }

        @Override // com.amazon.music.casting.session.CastingConnectionCallback
        public void onDisconnected(TerminationReason terminationReason) {
            PodcastPlayer.this.pause();
            PodcastPlayer.this.setVolume(1.0f);
        }

        @Override // com.amazon.music.casting.session.CastingConnectionCallback
        public void onError(CastingException e) {
            PodcastPlayer.this.exoPlayer.setVolume(1.0f);
        }
    };
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private final ScheduledExecutorService heartbeatExecutorService = Executors.newSingleThreadScheduledExecutor();
    private final Handler handler = new Handler();

    @Override // com.amazon.podcast.media.playback.PodcastPlaybackController
    public int fastForwardOffsetMS() {
        return 30000;
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onLoadingChanged(final boolean b) {
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onPositionDiscontinuity() {
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onTracksChanged(final TrackGroupArray trackGroupArray, final TrackSelectionArray trackSelectionArray) {
    }

    @Override // com.amazon.podcast.media.playback.PodcastPlaybackController
    public void playMedia(final String mediaId, final boolean isDownloaded, final long playOffset) {
    }

    @Override // com.amazon.podcast.media.playback.PodcastPlaybackController
    public int rewindOffsetMS() {
        return 15000;
    }

    public PodcastPlayer(final Context context, final PodcastPlayerCallback callback, final MediaSessionCompat mediaSessionCompat, final float playbackSpeed, final PlaybackStateCompat.Builder playbackStateBuilder, final MediaMetadataCompat.Builder mediaMetadataBuilder) {
        this.context = context;
        this.callback = callback;
        this.mediaSessionCompat = mediaSessionCompat;
        this.playbackSpeed = playbackSpeed;
        this.playbackStateBuilder = playbackStateBuilder;
        this.mediaMetadataBuilder = mediaMetadataBuilder;
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

    @Override // com.amazon.podcast.media.playback.PodcastPlaybackController
    public void prepareMedia(final String uriString, final Bundle extras, final String mediaId, final String audioStreamFormat) {
        this.mediaId = mediaId;
        this.isPreparing = true;
        this.playbackStateBuilder.setActions(extras.getLong("playbackActions"));
        if (this.exoPlayer == null) {
            DefaultRenderersFactory defaultRenderersFactory = new DefaultRenderersFactory(this.context) { // from class: com.amazon.podcast.media.playback.PodcastPlayer.2
                @Override // com.google.android.exoplayer2.DefaultRenderersFactory
                protected AudioProcessor[] buildAudioProcessors() {
                    return new AudioProcessor[]{new PodcastAudioProcessor()};
                }
            };
            DefaultLoadControl defaultLoadControl = new DefaultLoadControl(new DefaultAllocator(true, 65536), 40000, 240000, 1000L, 1000L);
            if (TranscriptProvider.isTranscriptSupported()) {
                this.exoPlayer = ExoPlayerFactory.newSimpleInstance(defaultRenderersFactory, new DefaultTrackSelector(), defaultLoadControl);
            } else {
                this.exoPlayer = ExoPlayerFactory.newSimpleInstance(this.context, new DefaultTrackSelector(), defaultLoadControl);
            }
            this.exoPlayer.addListener(this);
        }
        boolean z = extras.getBoolean("isMediaDownloaded");
        final NetworkSettingsProvider networkSettingsProvider = Podcast.getNetworkSettingsProvider();
        boolean zIsStreamingRestricted = networkSettingsProvider.isStreamingRestricted(this.context);
        if (!z && zIsStreamingRestricted) {
            setPlaybackStateForStreamingRestricted();
            Handlers.INSTANCE.getForeground().post(new Runnable() { // from class: com.amazon.podcast.media.playback.PodcastPlayer.3
                @Override // java.lang.Runnable
                public void run() {
                    networkSettingsProvider.showStreamingRestricted(PodcastPlayer.this.context);
                }
            });
            return;
        }
        this.exoPlayer.prepare(new MediaSourceProviderImpl(this.context, this.handler).getMediaSource(Uri.parse(uriString), audioStreamFormat));
        this.exoPlayer.setPlayWhenReady(false);
        if (Podcast.isFireOS()) {
            return;
        }
        CastingSessionManager.getInstance().registerListener(this.castingConnectionCallback);
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
        if (isDownloaded || !zIsStreamingRestricted) {
            PlaybackCache.get(mediaId).updatePlaybackRequestedAtTimstampMS(Long.valueOf(System.currentTimeMillis()));
            this.exoPlayer.setPlayWhenReady(true);
        }
    }

    @Override // com.amazon.podcast.media.playback.PodcastPlaybackController
    public void play() {
        if (this.exoPlayer == null) {
            return;
        }
        PlaybackCache.get(this.mediaId).updatePlaybackRequestedAtTimstampMS(Long.valueOf(System.currentTimeMillis()));
        this.exoPlayer.setPlayWhenReady(true);
    }

    @Override // com.amazon.podcast.media.playback.PodcastPlaybackController
    public void pause() {
        SimpleExoPlayer simpleExoPlayer = this.exoPlayer;
        if (simpleExoPlayer == null) {
            return;
        }
        simpleExoPlayer.setPlayWhenReady(false);
    }

    @Override // com.amazon.podcast.media.playback.PodcastPlaybackController
    public void setVolume(float volume) {
        SimpleExoPlayer simpleExoPlayer = this.exoPlayer;
        if (simpleExoPlayer == null) {
            return;
        }
        simpleExoPlayer.setVolume(volume);
    }

    @Override // com.amazon.podcast.media.playback.PodcastPlaybackController
    public void fastForward() {
        SimpleExoPlayer simpleExoPlayer = this.exoPlayer;
        if (simpleExoPlayer == null) {
            return;
        }
        long duration = simpleExoPlayer.getDuration();
        long currentPosition = this.exoPlayer.getCurrentPosition() + 30000;
        if (currentPosition >= duration) {
            return;
        }
        seekTo(currentPosition);
    }

    @Override // com.amazon.podcast.media.playback.PodcastPlaybackController
    public void rewind() {
        SimpleExoPlayer simpleExoPlayer = this.exoPlayer;
        if (simpleExoPlayer == null) {
            return;
        }
        long currentPosition = simpleExoPlayer.getCurrentPosition() - 15000;
        if (currentPosition <= 0) {
            return;
        }
        seekTo(currentPosition);
    }

    @Override // com.amazon.podcast.media.playback.PodcastPlaybackController
    public void setPlaybackSpeed(final float speed) {
        PlaybackParameters playbackParameters;
        SimpleExoPlayer simpleExoPlayer = this.exoPlayer;
        if (simpleExoPlayer == null) {
            return;
        }
        if (simpleExoPlayer.getPlaybackParameters() == null) {
            playbackParameters = PlaybackParameters.DEFAULT;
        } else {
            playbackParameters = this.exoPlayer.getPlaybackParameters();
        }
        this.playbackSpeed = speed;
        ScheduledFuture scheduledFuture = this.heartbeatPositionScheduledFuture;
        if (!(scheduledFuture == null)) {
            scheduledFuture.cancel(true);
        }
        Integer num = this.previousState;
        if (!(num == null) && num.intValue() == 3) {
            this.heartbeatPositionScheduledFuture = scheduleHeartbeat();
        }
        this.exoPlayer.setPlaybackParameters(new PlaybackParameters(speed, playbackParameters.pitch));
    }

    @Override // com.amazon.podcast.media.playback.PodcastPlaybackController
    public void stop() {
        SimpleExoPlayer simpleExoPlayer = this.exoPlayer;
        if (simpleExoPlayer == null) {
            return;
        }
        simpleExoPlayer.stop();
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
        this.mediaSessionCompat.setPlaybackState(this.playbackStateBuilder.setState(1, this.exoPlayer.getCurrentPosition(), this.playbackSpeed).setExtras(bundle).build());
        PodcastPlayerCallback podcastPlayerCallback = this.callback;
        if (podcastPlayerCallback == null) {
            return;
        }
        podcastPlayerCallback.onPlaybackPaused();
    }

    @Override // com.amazon.podcast.media.playback.PodcastPlaybackController
    public void clear() {
        if (this.exoPlayer == null) {
            return;
        }
        cancelScheduledFutures();
        int playbackState = this.exoPlayer.getPlaybackState();
        if (3 == playbackState || 2 == playbackState) {
            stop();
        }
        this.mediaSessionCompat.setPlaybackState(this.playbackStateBuilder.setState(0, 0L, this.playbackSpeed).setExtras(null).build());
        this.exoPlayer.removeListener(this);
        this.exoPlayer.release();
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
    public boolean isPlaying() {
        SimpleExoPlayer simpleExoPlayer = this.exoPlayer;
        if (simpleExoPlayer == null) {
            return false;
        }
        return simpleExoPlayer.getPlayWhenReady();
    }

    @Override // com.amazon.podcast.media.playback.PodcastPlaybackController
    public void seekTo(final long position) {
        if (this.exoPlayer == null) {
            return;
        }
        PlaybackCache.get(this.mediaId).updatePlaybackRequestedAtTimstampMS(Long.valueOf(System.currentTimeMillis()));
        this.exoPlayer.seekTo(position);
        PodcastPlayerCallback podcastPlayerCallback = this.callback;
        if (podcastPlayerCallback == null) {
            return;
        }
        podcastPlayerCallback.onPlaybackPositionChanged(position);
    }

    @Override // com.amazon.podcast.media.playback.PodcastPlaybackController
    public void setMediaMetadata(final Bundle extras) {
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

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onPlayerStateChanged(final boolean playReady, final int state) {
        this.logger.debug("onPlayerStateChanged: state: {}, playReady: {} ", Integer.valueOf(state), Boolean.valueOf(playReady));
        if (state == 2) {
            setPlaybackStateBuffering();
        } else if (state == 3) {
            Playback playback = Podcast.getPlayback();
            if (this.callback != null && playback.isRestoringPlayback()) {
                this.callback.onMediaBuffered(this.exoPlayer.getDuration());
                this.callback.onPlaybackPositionChanged(this.exoPlayer.getCurrentPosition());
            }
            if (playReady) {
                setPlaybackStatePlaying();
            } else {
                setPlaybackStatePaused();
            }
        } else if (state == 4) {
            setPlaybackStateFinished();
        }
        this.previousState = Integer.valueOf(state);
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onRepeatModeChanged(int i) {
        this.logger.debug("onRepeatModeChanged: mode: {}", Integer.valueOf(i));
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onTimelineChanged(final Timeline timeline, final Object o) {
        this.logger.debug("onTimelineChanged");
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onPlayerError(final ExoPlaybackException e) {
        String errorMessage = getErrorMessage(e);
        this.logger.error("Playback Error: {}", errorMessage, e);
        Bundle bundle = new Bundle();
        bundle.putString("playbackError", errorMessage);
        this.mediaSessionCompat.setPlaybackState(this.playbackStateBuilder.setState(7, this.exoPlayer.getCurrentPosition(), this.playbackSpeed).setExtras(bundle).build());
    }

    private String getErrorMessage(final ExoPlaybackException e) {
        String message = e.getMessage();
        if (StringUtils.isNotBlank(message)) {
            return message;
        }
        if (e.getCause() != null && e.getCause().getCause() != null) {
            String localizedMessage = e.getCause().getCause().getLocalizedMessage();
            if (StringUtils.isNotBlank(localizedMessage) && localizedMessage.contains("Cleartext")) {
                return localizedMessage;
            }
        }
        if (e.type == 0) {
            String message2 = e.getSourceException().getMessage();
            if (StringUtils.isNotBlank(message2)) {
                return message2;
            }
        }
        if (e.type == 1) {
            String message3 = e.getRendererException().getMessage();
            if (StringUtils.isNotBlank(message3)) {
                return message3;
            }
        }
        if (e.type == 2) {
            String message4 = e.getUnexpectedException().getMessage();
            if (StringUtils.isNotBlank(message4)) {
                return message4;
            }
        }
        return e.getStackTrace().toString();
    }

    public void setPlaybackStateBuffering() {
        this.bufferStartedAt = Long.valueOf(System.currentTimeMillis());
        this.mediaSessionCompat.setPlaybackState(this.playbackStateBuilder.setState(6, this.exoPlayer.getCurrentPosition(), this.playbackSpeed).setExtras(null).build());
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
        if (this.previousState.intValue() == 2) {
            if (this.isPreparing) {
                this.isPreparing = false;
                bundle.putString("streamingInitiated", "");
                PodcastPlayerCallback podcastPlayerCallback = this.callback;
                if (podcastPlayerCallback == null) {
                    return;
                } else {
                    podcastPlayerCallback.onMediaBuffered(this.exoPlayer.getDuration());
                }
            } else {
                bundle.putString("streamingRebuffered", "");
                if (this.bufferStartedAt != null) {
                    bundle.putLong("rebufferDurationMS", Long.valueOf(System.currentTimeMillis() - this.bufferStartedAt.longValue()).longValue());
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
        this.mediaSessionCompat.setPlaybackState(this.playbackStateBuilder.setState(3, this.exoPlayer.getCurrentPosition(), this.playbackSpeed).setExtras(bundle).build());
        PodcastPlayerCallback podcastPlayerCallback2 = this.callback;
        if (podcastPlayerCallback2 == null) {
            return;
        }
        podcastPlayerCallback2.onPlaybackStarted();
    }

    public void setPlaybackStatePaused() {
        cancelScheduledFutures();
        this.mediaSessionCompat.setPlaybackState(this.playbackStateBuilder.setState(2, this.exoPlayer.getCurrentPosition(), this.playbackSpeed).setExtras(null).build());
        PodcastPlayerCallback podcastPlayerCallback = this.callback;
        if (podcastPlayerCallback == null) {
            return;
        }
        podcastPlayerCallback.onPlaybackPaused();
    }

    public void setPlaybackStateFinished() {
        cancelScheduledFutures();
        Bundle bundle = new Bundle();
        bundle.putString("playbackFinished", "");
        this.mediaSessionCompat.setPlaybackState(this.playbackStateBuilder.setState(2, this.exoPlayer.getCurrentPosition(), this.playbackSpeed).setExtras(bundle).build());
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

    public long getHeartbeatPeriodMilliseconds() {
        if (this.playbackSpeed == 0.0f) {
            return 1000L;
        }
        return Math.round(1000.0f / r0);
    }

    private final class PositionRunnable implements Runnable {
        private PositionRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (PodcastPlayer.this.callback == null) {
                    return;
                }
                PodcastPlayer.this.callback.onPlaybackPositionChanged(PodcastPlayer.this.exoPlayer.getCurrentPosition());
            } catch (Exception e) {
                PodcastPlayer.this.logger.error("PositionRunnable: ", (Throwable) e);
            }
        }
    }

    private final class BufferedPositionRunnable implements Runnable {
        private BufferedPositionRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (PodcastPlayer.this.callback == null) {
                    return;
                }
                PodcastPlayer.this.callback.onBufferedPositionChanged(PodcastPlayer.this.exoPlayer.getBufferedPosition());
            } catch (Exception e) {
                PodcastPlayer.this.logger.error("BufferedPositionRunnable: ", (Throwable) e);
            }
        }
    }

    private final class HeartbeatPositionRunnable implements Runnable {
        private HeartbeatPositionRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (PodcastPlayer.this.callback == null) {
                    return;
                }
                PodcastPlayer.this.callback.onHeartbeatPositionChanged(PodcastPlayer.this.exoPlayer.getCurrentPosition());
            } catch (Exception e) {
                PodcastPlayer.this.logger.error("HeartbeatPositionRunnable: ", (Throwable) e);
            }
        }
    }

    @Override // com.amazon.podcast.media.playback.PodcastPlaybackController
    public void removeCallback() {
        this.callback = null;
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
}
