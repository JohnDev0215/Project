package com.amazon.alexa.voice;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Handler;
import androidx.mediarouter.media.MediaRouteProviderProtocol;
import com.amazon.alexa.voice.core.AudioPlayer;
import com.amazon.alexa.voice.core.AudioSource;
import com.amazon.alexa.voice.core.Logger;
import com.amazon.alexa.voice.core.internal.audio.StaticAudioDataSource;
import com.amazon.alexa.voice.core.internal.util.Preconditions;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Renderer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.audio.MediaCodecAudioRenderer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.mediacodec.MediaCodecSelector;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.util.Util;
import com.visualon.OSMPUtils.voMimeTypes;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class ExoAudioPlayer implements AudioPlayer, Player.EventListener {
    private AudioManager.OnAudioFocusChangeListener audioFocusChangeListener;
    private final AudioManager audioManager;
    private final MediaCodecAudioRenderer audioRenderer;
    private boolean buffering;
    private final List<AudioPlayer.Callback> callbacks;
    private final Context context;
    private ObjectAnimator duckingAudioAnimator;
    private final Handler handler;
    private boolean paused;
    private ExoPlayer player;
    private boolean playing;
    private boolean ready;
    private boolean released;
    private float volume;
    private boolean wasPlaying;

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public float getMaximumVolume() {
        return 1.0f;
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public float getMinimumVolume() {
        return 0.05f;
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onLoadingChanged(boolean z) {
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onPositionDiscontinuity() {
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onRepeatModeChanged(int i) {
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onTimelineChanged(Timeline timeline, Object obj) {
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
    }

    public ExoAudioPlayer(Context context) {
        Preconditions.notNull(context, "context == null");
        Preconditions.mainThread("Audio player has to be created only on main thread");
        this.context = context;
        this.handler = new Handler();
        this.audioManager = (AudioManager) context.getSystemService(voMimeTypes.VOBASE_TYPE_AUDIO);
        this.callbacks = new ArrayList();
        this.volume = 1.0f;
        this.audioRenderer = new MediaCodecAudioRenderer(MediaCodecSelector.DEFAULT);
        createExoPlayerInstance();
    }

    private void setupAudioFocusListener() {
        if (this.audioFocusChangeListener != null) {
            return;
        }
        this.audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() { // from class: com.amazon.alexa.voice.ExoAudioPlayer.1
            @Override // android.media.AudioManager.OnAudioFocusChangeListener
            public void onAudioFocusChange(int i) {
                if (i == -1 || i == -2) {
                    ExoAudioPlayer exoAudioPlayer = ExoAudioPlayer.this;
                    exoAudioPlayer.wasPlaying = exoAudioPlayer.isPlaying();
                    ExoAudioPlayer exoAudioPlayer2 = ExoAudioPlayer.this;
                    exoAudioPlayer2.fadeVolume(exoAudioPlayer2.getMinimumVolume(), new Runnable() { // from class: com.amazon.alexa.voice.ExoAudioPlayer.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            ExoAudioPlayer.this.pause();
                        }
                    });
                    return;
                }
                if (i == -3) {
                    ExoAudioPlayer exoAudioPlayer3 = ExoAudioPlayer.this;
                    exoAudioPlayer3.fadeVolume(exoAudioPlayer3.getMinimumVolume());
                } else if (i == 1) {
                    ExoAudioPlayer exoAudioPlayer4 = ExoAudioPlayer.this;
                    exoAudioPlayer4.fadeVolume(exoAudioPlayer4.getMaximumVolume());
                    if (ExoAudioPlayer.this.isPlaying() || !ExoAudioPlayer.this.wasPlaying) {
                        return;
                    }
                    ExoAudioPlayer.this.player.setPlayWhenReady(true);
                }
            }
        };
        if (requestAudioFocus()) {
            return;
        }
        this.audioFocusChangeListener = null;
    }

    private boolean requestAudioFocus() {
        int iRequestAudioFocus = this.audioManager.requestAudioFocus(this.audioFocusChangeListener, 3, 1);
        Logger.info("Requesting Audio focus. Result code %d", Integer.valueOf(iRequestAudioFocus));
        return iRequestAudioFocus == 1;
    }

    private void abandonAudioFocus() {
        if (this.audioFocusChangeListener == null) {
            return;
        }
        fadeVolume(getMaximumVolume());
        this.audioManager.abandonAudioFocus(this.audioFocusChangeListener);
        this.audioFocusChangeListener = null;
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public void fadeVolume(float f) {
        fadeVolume(f, null);
    }

    public void fadeVolume(float f, final Runnable runnable) {
        ObjectAnimator objectAnimator = this.duckingAudioAnimator;
        if (objectAnimator != null) {
            objectAnimator.removeAllListeners();
            this.duckingAudioAnimator.cancel();
        }
        if (this.volume == f) {
            if (runnable != null) {
                runnable.run();
                return;
            }
            return;
        }
        if (this.duckingAudioAnimator == null) {
            ObjectAnimator objectAnimator2 = new ObjectAnimator();
            this.duckingAudioAnimator = objectAnimator2;
            objectAnimator2.setTarget(this);
            this.duckingAudioAnimator.setPropertyName(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME);
            this.duckingAudioAnimator.setDuration(750L);
        }
        this.duckingAudioAnimator.setFloatValues(f);
        this.duckingAudioAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.amazon.alexa.voice.ExoAudioPlayer.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                Runnable runnable2 = runnable;
                if (runnable2 != null) {
                    runnable2.run();
                }
            }
        });
        this.duckingAudioAnimator.start();
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onPlayerStateChanged(boolean z, int i) {
        if (i == 1) {
            abandonAudioFocus();
            setPlaying(false, false, z);
            setReady(false);
        } else if (i == 4) {
            abandonAudioFocus();
            setPlaying(false, false, z);
        } else if (i == 3) {
            setupAudioFocusListener();
            setReady(true);
            setPlaying(true, false, z);
        } else if (i == 2) {
            setPlaying(false, true, z);
        }
    }

    private void setReady(boolean z) {
        if (this.ready == z) {
            return;
        }
        this.ready = z;
        if (z) {
            Logger.debug("Player is ready");
            for (int size = this.callbacks.size() - 1; size >= 0; size--) {
                this.callbacks.get(size).onPlaybackReady();
            }
            return;
        }
        Logger.debug("Player is idle");
        for (int size2 = this.callbacks.size() - 1; size2 >= 0; size2--) {
            this.callbacks.get(size2).onPlaybackIdle();
        }
    }

    private void setPlaying(boolean z, boolean z2, boolean z3) {
        boolean z4 = this.playing;
        if (z4 == z && this.buffering == z2 && this.paused == (!z3)) {
            return;
        }
        this.playing = z;
        this.buffering = z2;
        boolean z5 = !z3;
        this.paused = z5;
        if (z2) {
            Logger.debug("Player is buffering...");
            for (int size = this.callbacks.size() - 1; size >= 0; size--) {
                this.callbacks.get(size).onPlayerBuffering();
            }
            return;
        }
        if (!z) {
            if (z4) {
                Logger.debug("Player ended playback");
                for (int size2 = this.callbacks.size() - 1; size2 >= 0; size2--) {
                    this.callbacks.get(size2).onPlaybackEnded();
                }
                return;
            }
            return;
        }
        if (z5) {
            Logger.debug("Player is paused");
            for (int size3 = this.callbacks.size() - 1; size3 >= 0; size3--) {
                this.callbacks.get(size3).onPlaybackPaused();
            }
            return;
        }
        Logger.debug("Player is playing");
        for (int size4 = this.callbacks.size() - 1; size4 >= 0; size4--) {
            this.callbacks.get(size4).onPlaybackStarted();
        }
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onPlayerError(ExoPlaybackException exoPlaybackException) {
        abandonAudioFocus();
        setPlaying(false, false, this.player.getPlayWhenReady());
        Logger.debug("Player error has occurred");
        for (int size = this.callbacks.size() - 1; size >= 0; size--) {
            this.callbacks.get(size).onPlaybackError(exoPlaybackException);
        }
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public void prepare(AudioSource audioSource) {
        Preconditions.notNull(audioSource, "audioSource == null");
        stop();
        this.player.prepare(new ExtractorMediaSource(Uri.EMPTY, new StaticAudioDataSource.Factory(audioSource), new DefaultExtractorsFactory(), this.handler, null));
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public void prepare(String str) {
        Preconditions.notNull(str, "url == null");
        stop();
        DefaultExtractorsFactory defaultExtractorsFactory = new DefaultExtractorsFactory();
        this.player.prepare(new DynamicMediaSource(this.context, Uri.parse(str), Util.getUserAgent(this.context, "Alexa Mobile Voice"), defaultExtractorsFactory, this.handler));
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public long getDuration() {
        return this.player.getDuration();
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public long getCurrentPosition() {
        return this.player.getCurrentPosition();
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public void seek(long j) {
        this.player.seekTo(j);
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public boolean isPlaying() {
        return this.player.getPlayWhenReady() && this.player.getPlaybackState() == 3;
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public void play() {
        if (requestAudioFocus()) {
            fadeVolume(getMaximumVolume());
            this.player.setPlayWhenReady(true);
        }
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public void setVolume(float f) {
        if (this.volume == f) {
            return;
        }
        this.volume = f;
        this.player.sendMessages(new ExoPlayer.ExoPlayerMessage(this.audioRenderer, 2, Float.valueOf(f)));
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public float getVolume() {
        return this.volume;
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public boolean isPaused() {
        return this.player.getPlaybackState() == 3 && !this.player.getPlayWhenReady();
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public boolean isEnded() {
        return this.player.getPlaybackState() == 4;
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public boolean isBuffering() {
        return this.buffering;
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public boolean isIdle() {
        return this.player.getPlaybackState() == 1;
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public void stop() {
        this.player.stop();
        reset();
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public void pause() {
        this.player.setPlayWhenReady(false);
    }

    public void release() {
        if (this.released) {
            return;
        }
        this.released = true;
        this.player.removeListener(this);
        this.player.release();
    }

    private void reset() {
        release();
        createExoPlayerInstance();
        this.released = false;
    }

    private void createExoPlayerInstance() {
        ExoPlayer exoPlayerNewInstance = ExoPlayerFactory.newInstance(new Renderer[]{this.audioRenderer}, new DefaultTrackSelector());
        this.player = exoPlayerNewInstance;
        exoPlayerNewInstance.addListener(this);
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public void addCallback(AudioPlayer.Callback callback) {
        this.callbacks.add(callback);
    }
}
