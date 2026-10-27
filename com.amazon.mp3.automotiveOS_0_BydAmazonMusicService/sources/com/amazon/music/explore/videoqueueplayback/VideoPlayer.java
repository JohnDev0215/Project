package com.amazon.music.explore.videoqueueplayback;

import android.content.Context;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Handler;
import android.view.TextureView;
import androidx.lifecycle.CoroutineLiveDataKt;
import com.amazon.music.explore.videoqueueplayback.VideoQueuePlayerManager;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
class VideoPlayer implements Player.EventListener, SimpleExoPlayer.VideoListener {
    private static final int CONTENT_LOADING_DELAY_MILLIS = 1000;
    private static final int PLAYBACK_DELAY_MILLIS = 200;
    private static final Logger logger = LoggerFactory.getLogger("VideoPlayer");
    private final VideoQueuePlayerManager.Callback callback;
    private ScheduledFuture contentLoadingFuture;
    private final Context context;
    public int duration;
    private SimpleExoPlayer exoPlayer;
    public boolean isPlaybackFinished;
    private int queuePosition;
    private TextureView textureView;
    private final PositionRunnable positionRunnable = new PositionRunnable();
    private final DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("Amazon Music");
    private final ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private final Handler handler = new Handler();

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onPositionDiscontinuity() {
    }

    @Override // com.google.android.exoplayer2.SimpleExoPlayer.VideoListener
    public void onRenderedFirstFrame() {
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onRepeatModeChanged(int i) {
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onTimelineChanged(Timeline timeline, Object obj) {
    }

    VideoPlayer(Context context, VideoQueuePlayerManager.Callback callback) {
        this.context = context;
        this.callback = callback;
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
        logger.info("on tracks changed");
        if (isInitialized()) {
            this.duration = (int) this.exoPlayer.getDuration();
        }
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onLoadingChanged(boolean z) {
        logger.info("on loading changed");
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onPlayerStateChanged(boolean z, int i) {
        Logger logger2 = logger;
        logger2.debug("onPlayerStateChanged, playReady " + z);
        logger2.debug("state " + i);
        if (i == 2) {
            if (this.contentLoadingFuture == null) {
                this.contentLoadingFuture = this.executorService.schedule(new Runnable() { // from class: com.amazon.music.explore.videoqueueplayback.VideoPlayer$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onPlayerStateChanged$1();
                    }
                }, 1000L, TimeUnit.MILLISECONDS);
            }
        } else if (i == 3) {
            this.isPlaybackFinished = false;
            ScheduledFuture scheduledFuture = this.contentLoadingFuture;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(true);
                this.contentLoadingFuture = null;
            }
            Handler handler = this.handler;
            final VideoQueuePlayerManager.Callback callback = this.callback;
            Objects.requireNonNull(callback);
            handler.post(new Runnable() { // from class: com.amazon.music.explore.videoqueueplayback.VideoPlayer$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    callback.onContentReady();
                }
            });
            if (z) {
                this.handler.postDelayed(this.positionRunnable, 200L);
                Handler handler2 = this.handler;
                final VideoQueuePlayerManager.Callback callback2 = this.callback;
                Objects.requireNonNull(callback2);
                handler2.post(new Runnable() { // from class: com.amazon.music.explore.videoqueueplayback.VideoPlayer$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        callback2.onPlaybackStarted();
                    }
                });
                return;
            }
        } else if (i == 4 && !this.isPlaybackFinished) {
            Handler handler3 = this.handler;
            final VideoQueuePlayerManager.Callback callback3 = this.callback;
            Objects.requireNonNull(callback3);
            handler3.post(new Runnable() { // from class: com.amazon.music.explore.videoqueueplayback.VideoPlayer$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    callback3.onPlaybackFinished();
                }
            });
            this.isPlaybackFinished = true;
        }
        this.handler.removeCallbacks(this.positionRunnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onPlayerStateChanged$1() {
        this.handler.post(new Runnable() { // from class: com.amazon.music.explore.videoqueueplayback.VideoPlayer$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onPlayerStateChanged$0();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onPlayerStateChanged$0() {
        this.callback.onContentLoading();
        this.contentLoadingFuture = null;
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onPlayerError(ExoPlaybackException exoPlaybackException) {
        logger.error("Error playing video", (Throwable) exoPlaybackException);
        this.handler.removeCallbacks(this.positionRunnable);
    }

    @Override // com.google.android.exoplayer2.SimpleExoPlayer.VideoListener
    public void onVideoSizeChanged(int i, int i2, int i3, float f) {
        TextureView textureView = this.textureView;
        if (textureView == null) {
            return;
        }
        zoomToFit(i, i2, textureView);
    }

    public boolean isInitialized() {
        return this.exoPlayer != null;
    }

    public void shutdown() {
        if (isInitialized()) {
            this.exoPlayer.release();
            this.exoPlayer = null;
        }
    }

    public void play() {
        if (isInitialized()) {
            this.exoPlayer.setPlayWhenReady(true);
        }
    }

    public void pause() {
        if (isInitialized()) {
            this.exoPlayer.setPlayWhenReady(false);
        }
    }

    public void mute() {
        if (isInitialized()) {
            this.exoPlayer.setVolume(0.0f);
        }
    }

    public int getDuration() {
        return this.duration;
    }

    void prepareQueueItem(int i, String str) {
        logger.info("preparing media with uri {}", str);
        initializeExoPlayer();
        this.queuePosition = i;
        this.exoPlayer.prepare(new ExtractorMediaSource(Uri.parse(str), this.dataSourceFactory, this.extractorsFactory, this.handler, null));
    }

    void setTextureView(TextureView textureView) {
        logger.info("setting surface view");
        if (isInitialized()) {
            this.textureView = textureView;
            this.exoPlayer.setVideoTextureView(textureView);
        }
    }

    private SimpleExoPlayer initializeExoPlayer() {
        if (!isInitialized()) {
            SimpleExoPlayer simpleExoPlayerNewSimpleInstance = ExoPlayerFactory.newSimpleInstance(this.context, new DefaultTrackSelector(), new DefaultLoadControl(new DefaultAllocator(true, 65536), 15000, 30000, 625L, CoroutineLiveDataKt.DEFAULT_TIMEOUT));
            this.exoPlayer = simpleExoPlayerNewSimpleInstance;
            simpleExoPlayerNewSimpleInstance.setVideoListener(this);
            this.exoPlayer.addListener(this);
        }
        return this.exoPlayer;
    }

    private void zoomToFit(float f, float f2, TextureView textureView) {
        float f3;
        float width = textureView.getWidth();
        float height = textureView.getHeight();
        float f4 = width / height;
        float f5 = f / f2;
        float f6 = 1.0f;
        if (f4 > f5) {
            f3 = f4 / f5;
        } else if (f5 > f4) {
            float f7 = f5 / f4;
            f3 = 1.0f;
            f6 = f7;
        } else {
            f3 = 1.0f;
        }
        Matrix matrix = new Matrix();
        matrix.setScale(f6, f3, width / 2.0f, height / 2.0f);
        textureView.setTransform(matrix);
    }

    private class PositionRunnable implements Runnable {
        private PositionRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (VideoPlayer.this.exoPlayer != null) {
                VideoPlayer.this.callback.onPlaybackPositionChanged(VideoPlayer.this.queuePosition, VideoPlayer.this.exoPlayer.getCurrentPosition());
                VideoPlayer.this.handler.postDelayed(this, 200L);
            }
        }
    }
}
