package com.amazon.mp3.video.stories.videostoryplayback;

import android.content.Context;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Handler;
import android.view.TextureView;
import androidx.lifecycle.CoroutineLiveDataKt;
import com.amazon.mp3.video.stories.TransferSpeed;
import com.amazon.mp3.video.stories.TransferSpeedManager;
import com.amazon.mp3.video.stories.videostoryplayback.VideoPlayer;
import com.amazon.music.find.utils.PageUriUtils;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.upstream.BandwidthMeter$EventListener;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: VideoPlayerImpl.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 q2\u00020\u00012\u00020\u00022\u00020\u0003:\u0002qrB\u000f\u0012\u0006\u0010B\u001a\u00020A¢\u0006\u0004\bo\u0010pJ\n\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002J \u0010\f\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\tH\u0002J\u0010\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0016J\b\u0010\u0010\u001a\u00020\u000bH\u0016J\u001c\u0010\u0015\u001a\u00020\u000b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u0016J\u0018\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u0018H\u0016J\u0010\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u001bH\u0016J\u0018\u0010!\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020\u001fH\u0016J\u0010\u0010#\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020\u001fH\u0016J\u0010\u0010&\u001a\u00020\u000b2\u0006\u0010%\u001a\u00020$H\u0016J\b\u0010'\u001a\u00020\u000bH\u0016J\u0010\u0010*\u001a\u00020\u000b2\u0006\u0010)\u001a\u00020(H\u0016J(\u0010/\u001a\u00020\u000b2\u0006\u0010+\u001a\u00020\u001f2\u0006\u0010,\u001a\u00020\u001f2\u0006\u0010-\u001a\u00020\u001f2\u0006\u0010.\u001a\u00020\u0006H\u0016J\b\u00100\u001a\u00020\u000bH\u0016J\b\u00101\u001a\u00020\u000bH\u0016J\b\u00102\u001a\u00020\u000bH\u0016J\b\u00103\u001a\u00020\u000bH\u0016J\u0010\u00105\u001a\u00020\u000b2\u0006\u00104\u001a\u00020\u001bH\u0016J\u0018\u00109\u001a\u00020\u000b2\u0006\u00106\u001a\u00020\u001f2\u0006\u00108\u001a\u000207H\u0016J\u0010\u0010;\u001a\u00020\u000b2\u0006\u0010:\u001a\u00020\u001fH\u0016J\u001f\u0010>\u001a\u00020\u000b\"\u0004\b\u0000\u0010<2\b\u0010=\u001a\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0004\b>\u0010?J\b\u0010@\u001a\u00020\u001bH\u0016R\u0014\u0010B\u001a\u00020A8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bB\u0010CR\u0018\u0010E\u001a\u00060DR\u00020\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bE\u0010FR\u0016\u0010H\u001a\u00020G8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bH\u0010IR\u0014\u0010J\u001a\u0002078\u0002X\u0082D¢\u0006\u0006\n\u0004\bJ\u0010KR\u001c\u0010N\u001a\n M*\u0004\u0018\u00010L0L8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bN\u0010OR\u0014\u0010Q\u001a\u00020P8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bQ\u0010RR\u0018\u0010S\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bS\u0010TR\u001c\u0010V\u001a\b\u0012\u0002\b\u0003\u0018\u00010U8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bV\u0010WR\u0018\u0010\n\u001a\u0004\u0018\u00010\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\n\u0010XR\u0016\u00106\u001a\u00020\u001f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b6\u0010YR\u0016\u0010Z\u001a\u00020\u001f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bZ\u0010YR\"\u0010[\u001a\u00020\u001f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b[\u0010Y\u001a\u0004\b\\\u0010]\"\u0004\b^\u0010_R\"\u0010`\u001a\u00020\u001b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b`\u0010a\u001a\u0004\b`\u0010b\"\u0004\bc\u0010dR\"\u0010e\u001a\u00020\u001b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\be\u0010a\u001a\u0004\be\u0010b\"\u0004\bf\u0010dR\"\u0010h\u001a\u00020g8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bh\u0010i\u001a\u0004\bj\u0010k\"\u0004\bl\u0010mR\u0018\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000e\u0010n¨\u0006s"}, d2 = {"Lcom/amazon/mp3/video/stories/videostoryplayback/VideoPlayerImpl;", "Lcom/google/android/exoplayer2/Player$EventListener;", "Lcom/google/android/exoplayer2/SimpleExoPlayer$VideoListener;", "Lcom/amazon/mp3/video/stories/videostoryplayback/VideoPlayer;", "Lcom/google/android/exoplayer2/SimpleExoPlayer;", "initializeExoPlayer", "", "videoWidth", "videoHeight", "Landroid/view/TextureView;", "textureView", "", "zoomToFit", "Lcom/amazon/mp3/video/stories/videostoryplayback/VideoPlayer$Callback;", "callback", "setCallback", "removeCallback", "Lcom/google/android/exoplayer2/Timeline;", "p0", "", "p1", "onTimelineChanged", "Lcom/google/android/exoplayer2/source/TrackGroupArray;", "trackGroupArray", "Lcom/google/android/exoplayer2/trackselection/TrackSelectionArray;", "trackSelectionArray", "onTracksChanged", "", "b", "onLoadingChanged", "playReady", "", "state", "onPlayerStateChanged", "i", "onRepeatModeChanged", "Lcom/google/android/exoplayer2/ExoPlaybackException;", "e", "onPlayerError", "onPositionDiscontinuity", "Lcom/google/android/exoplayer2/PlaybackParameters;", "playbackParameters", "onPlaybackParametersChanged", "width", "height", "unappliedRotationDegrees", "pixelWidthHeightRatio", "onVideoSizeChanged", "onRenderedFirstFrame", "shutdown", PageUriUtils.DO_PLAY_VALUE, "pause", "mute", "toggleMute", "queuePosition", "", "uri", "prepareQueueItem", "seekPos", "seekPosition", "T", "contentView", "setContentView", "(Ljava/lang/Object;)V", "isPlaying", "Landroid/content/Context;", "context", "Landroid/content/Context;", "Lcom/amazon/mp3/video/stories/videostoryplayback/VideoPlayerImpl$PositionRunnable;", "positionRunnable", "Lcom/amazon/mp3/video/stories/videostoryplayback/VideoPlayerImpl$PositionRunnable;", "Lcom/google/android/exoplayer2/upstream/DefaultHttpDataSourceFactory;", "dataSourceFactory", "Lcom/google/android/exoplayer2/upstream/DefaultHttpDataSourceFactory;", "USER_AGENT", "Ljava/lang/String;", "Ljava/util/concurrent/ScheduledExecutorService;", "kotlin.jvm.PlatformType", "executorService", "Ljava/util/concurrent/ScheduledExecutorService;", "Landroid/os/Handler;", "handler", "Landroid/os/Handler;", "exoPlayer", "Lcom/google/android/exoplayer2/SimpleExoPlayer;", "Ljava/util/concurrent/ScheduledFuture;", "contentLoadingFuture", "Ljava/util/concurrent/ScheduledFuture;", "Landroid/view/TextureView;", "I", "playerState", "duration", "getDuration", "()I", "setDuration", "(I)V", "isPlaybackFinished", "Z", "()Z", "setPlaybackFinished", "(Z)V", "isBuffering", "setBuffering", "Lcom/amazon/mp3/video/stories/TransferSpeedManager;", "transferSpeedManager", "Lcom/amazon/mp3/video/stories/TransferSpeedManager;", "getTransferSpeedManager", "()Lcom/amazon/mp3/video/stories/TransferSpeedManager;", "setTransferSpeedManager", "(Lcom/amazon/mp3/video/stories/TransferSpeedManager;)V", "Lcom/amazon/mp3/video/stories/videostoryplayback/VideoPlayer$Callback;", "<init>", "(Landroid/content/Context;)V", "Companion", "PositionRunnable", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class VideoPlayerImpl implements Player.EventListener, SimpleExoPlayer.VideoListener, VideoPlayer {
    private static final Logger logger = LoggerFactory.getLogger("VideoPlayer");
    private final String USER_AGENT;
    private VideoPlayer.Callback callback;
    private ScheduledFuture<?> contentLoadingFuture;
    private final Context context;
    private DefaultHttpDataSourceFactory dataSourceFactory;
    private int duration;
    private final ScheduledExecutorService executorService;
    private SimpleExoPlayer exoPlayer;
    private final Handler handler;
    private boolean isBuffering;
    private boolean isPlaybackFinished;
    private int playerState;
    private final PositionRunnable positionRunnable;
    private int queuePosition;
    private TextureView textureView;
    private TransferSpeedManager transferSpeedManager;

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onLoadingChanged(boolean b) {
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        Intrinsics.checkNotNullParameter(playbackParameters, "playbackParameters");
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
    public void onTimelineChanged(Timeline p0, Object p1) {
    }

    public VideoPlayerImpl(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.positionRunnable = new PositionRunnable(this);
        this.USER_AGENT = "Amazon Music";
        this.executorService = Executors.newSingleThreadScheduledExecutor();
        this.handler = new Handler();
        this.playerState = -1;
        this.isBuffering = true;
        this.transferSpeedManager = TransferSpeedManager.INSTANCE.get(context, this);
        this.dataSourceFactory = new DefaultHttpDataSourceFactory("Amazon Music", new DefaultBandwidthMeter(new Handler(), new BandwidthMeter$EventListener() { // from class: com.amazon.mp3.video.stories.videostoryplayback.VideoPlayerImpl$$ExternalSyntheticLambda4
            @Override // com.google.android.exoplayer2.upstream.BandwidthMeter$EventListener
            public final void onBandwidthSample(int i, long j, long j2) {
                VideoPlayerImpl.m1690_init_$lambda0(this.f$0, i, j, j2);
            }
        }));
        initializeExoPlayer();
    }

    public final int getDuration() {
        return this.duration;
    }

    public final void setDuration(int i) {
        this.duration = i;
    }

    /* JADX INFO: renamed from: isBuffering, reason: from getter */
    public final boolean getIsBuffering() {
        return this.isBuffering;
    }

    public final void setBuffering(boolean z) {
        this.isBuffering = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-0, reason: not valid java name */
    public static final void m1690_init_$lambda0(VideoPlayerImpl this$0, int i, long j, long j2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isBuffering) {
            this$0.transferSpeedManager.getTransferSpeeds().add(new TransferSpeed(j2, System.currentTimeMillis()));
        }
    }

    @Override // com.amazon.mp3.video.stories.videostoryplayback.VideoPlayer
    public void setCallback(VideoPlayer.Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.callback = callback;
    }

    public void removeCallback() {
        this.callback = null;
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
        Intrinsics.checkNotNullParameter(trackGroupArray, "trackGroupArray");
        Intrinsics.checkNotNullParameter(trackSelectionArray, "trackSelectionArray");
        SimpleExoPlayer simpleExoPlayer = this.exoPlayer;
        if (simpleExoPlayer == null) {
            return;
        }
        setDuration((int) simpleExoPlayer.getDuration());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onPlayerStateChanged$lambda-3, reason: not valid java name */
    public static final void m1691onPlayerStateChanged$lambda3(final VideoPlayerImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.handler.post(new Runnable() { // from class: com.amazon.mp3.video.stories.videostoryplayback.VideoPlayerImpl$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                VideoPlayerImpl.m1692onPlayerStateChanged$lambda3$lambda2(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onPlayerStateChanged$lambda-3$lambda-2, reason: not valid java name */
    public static final void m1692onPlayerStateChanged$lambda3$lambda2(VideoPlayerImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        VideoPlayer.Callback callback = this$0.callback;
        if (callback != null) {
            callback.onContentLoading();
        }
        this$0.contentLoadingFuture = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onPlayerStateChanged$lambda-4, reason: not valid java name */
    public static final void m1693onPlayerStateChanged$lambda4(VideoPlayerImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        VideoPlayer.Callback callback = this$0.callback;
        if (callback == null) {
            return;
        }
        callback.onContentReady();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onPlayerStateChanged$lambda-5, reason: not valid java name */
    public static final void m1694onPlayerStateChanged$lambda5(VideoPlayerImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        VideoPlayer.Callback callback = this$0.callback;
        if (callback == null) {
            return;
        }
        callback.onPlaybackStarted();
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onPlayerStateChanged(boolean playReady, int state) {
        if (state == 1 || state == 2) {
            if (this.contentLoadingFuture == null) {
                this.contentLoadingFuture = this.executorService.schedule(new Runnable() { // from class: com.amazon.mp3.video.stories.videostoryplayback.VideoPlayerImpl$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        VideoPlayerImpl.m1691onPlayerStateChanged$lambda3(this.f$0);
                    }
                }, 1000L, TimeUnit.MILLISECONDS);
            }
            this.playerState = state;
        } else if (state == 3) {
            this.isPlaybackFinished = false;
            ScheduledFuture<?> scheduledFuture = this.contentLoadingFuture;
            if (scheduledFuture != null) {
                if (scheduledFuture != null) {
                    scheduledFuture.cancel(true);
                }
                this.contentLoadingFuture = null;
            }
            if (this.playerState != 3) {
                this.handler.post(new Runnable() { // from class: com.amazon.mp3.video.stories.videostoryplayback.VideoPlayerImpl$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        VideoPlayerImpl.m1693onPlayerStateChanged$lambda4(this.f$0);
                    }
                });
            }
            this.playerState = state;
            if (playReady) {
                this.handler.postDelayed(this.positionRunnable, 200L);
                this.handler.post(new Runnable() { // from class: com.amazon.mp3.video.stories.videostoryplayback.VideoPlayerImpl$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        VideoPlayerImpl.m1694onPlayerStateChanged$lambda5(this.f$0);
                    }
                });
                return;
            }
        } else if (state == 4) {
            if (!this.isPlaybackFinished) {
                this.handler.post(new Runnable() { // from class: com.amazon.mp3.video.stories.videostoryplayback.VideoPlayerImpl$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        VideoPlayerImpl.m1695onPlayerStateChanged$lambda6(this.f$0);
                    }
                });
                this.isPlaybackFinished = true;
            }
            this.playerState = state;
        }
        this.handler.removeCallbacks(this.positionRunnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onPlayerStateChanged$lambda-6, reason: not valid java name */
    public static final void m1695onPlayerStateChanged$lambda6(VideoPlayerImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        VideoPlayer.Callback callback = this$0.callback;
        if (callback == null) {
            return;
        }
        callback.onPlaybackFinished();
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onPlayerError(ExoPlaybackException e) {
        Intrinsics.checkNotNullParameter(e, "e");
        logger.error("Error playing video", (Throwable) e);
        VideoPlayer.Callback callback = this.callback;
        if (callback != null) {
            callback.onPlayerError(e);
        }
        this.handler.removeCallbacks(this.positionRunnable);
    }

    @Override // com.google.android.exoplayer2.SimpleExoPlayer.VideoListener
    public void onVideoSizeChanged(int width, int height, int unappliedRotationDegrees, float pixelWidthHeightRatio) {
        TextureView textureView = this.textureView;
        if (textureView == null) {
            return;
        }
        zoomToFit(width, height, textureView);
    }

    public void shutdown() {
        SimpleExoPlayer simpleExoPlayer = this.exoPlayer;
        if (simpleExoPlayer != null) {
            if (simpleExoPlayer != null) {
                simpleExoPlayer.release();
            }
            this.exoPlayer = null;
        }
    }

    @Override // com.amazon.mp3.video.stories.videostoryplayback.VideoPlayer
    public void play() {
        SimpleExoPlayer simpleExoPlayer = this.exoPlayer;
        if (simpleExoPlayer == null) {
            return;
        }
        simpleExoPlayer.setPlayWhenReady(true);
    }

    @Override // com.amazon.mp3.video.stories.videostoryplayback.VideoPlayer
    public void pause() {
        SimpleExoPlayer simpleExoPlayer = this.exoPlayer;
        if (simpleExoPlayer == null) {
            return;
        }
        simpleExoPlayer.setPlayWhenReady(false);
    }

    @Override // com.amazon.mp3.video.stories.videostoryplayback.VideoPlayer
    public void toggleMute(boolean mute) {
        if (mute) {
            SimpleExoPlayer simpleExoPlayer = this.exoPlayer;
            if (simpleExoPlayer == null) {
                return;
            }
            simpleExoPlayer.setVolume(0.0f);
            return;
        }
        SimpleExoPlayer simpleExoPlayer2 = this.exoPlayer;
        if (simpleExoPlayer2 == null) {
            return;
        }
        simpleExoPlayer2.setVolume(1.0f);
    }

    @Override // com.amazon.mp3.video.stories.videostoryplayback.VideoPlayer
    public void prepareQueueItem(int queuePosition, String uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        initializeExoPlayer();
        this.queuePosition = queuePosition;
        Uri uri2 = Uri.parse(uri);
        DefaultHttpDataSourceFactory defaultHttpDataSourceFactory = this.dataSourceFactory;
        DashMediaSource dashMediaSource = new DashMediaSource(uri2, defaultHttpDataSourceFactory, new DefaultDashChunkSource.Factory(defaultHttpDataSourceFactory), this.handler, null);
        SimpleExoPlayer simpleExoPlayer = this.exoPlayer;
        if (simpleExoPlayer == null) {
            return;
        }
        simpleExoPlayer.prepare(dashMediaSource);
    }

    @Override // com.amazon.mp3.video.stories.videostoryplayback.VideoPlayer
    public void seekPosition(int seekPos) {
        SimpleExoPlayer simpleExoPlayer = this.exoPlayer;
        if (simpleExoPlayer == null) {
            return;
        }
        simpleExoPlayer.seekTo(seekPos);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amazon.mp3.video.stories.videostoryplayback.VideoPlayer
    public <T> void setContentView(T contentView) {
        SimpleExoPlayer simpleExoPlayer;
        if (!(contentView instanceof TextureView) || (simpleExoPlayer = this.exoPlayer) == null) {
            return;
        }
        TextureView textureView = (TextureView) contentView;
        this.textureView = textureView;
        simpleExoPlayer.setVideoTextureView(textureView);
    }

    public boolean isPlaying() {
        SimpleExoPlayer simpleExoPlayer = this.exoPlayer;
        if (simpleExoPlayer == null) {
            return false;
        }
        return simpleExoPlayer.getPlayWhenReady();
    }

    private final SimpleExoPlayer initializeExoPlayer() {
        if (this.exoPlayer == null) {
            SimpleExoPlayer simpleExoPlayerNewSimpleInstance = ExoPlayerFactory.newSimpleInstance(this.context, new DefaultTrackSelector(), new DefaultLoadControl(new DefaultAllocator(true, 65536), 15000, 30000, 625L, CoroutineLiveDataKt.DEFAULT_TIMEOUT));
            this.exoPlayer = simpleExoPlayerNewSimpleInstance;
            if (simpleExoPlayerNewSimpleInstance != null) {
                simpleExoPlayerNewSimpleInstance.setVideoListener(this);
                SimpleExoPlayer simpleExoPlayer = this.exoPlayer;
                if (simpleExoPlayer == null) {
                    throw new NullPointerException("null cannot be cast to non-null type com.google.android.exoplayer2.SimpleExoPlayer");
                }
                simpleExoPlayer.addListener(this);
            } else {
                throw new NullPointerException("null cannot be cast to non-null type com.google.android.exoplayer2.SimpleExoPlayer");
            }
        }
        return this.exoPlayer;
    }

    private final void zoomToFit(float videoWidth, float videoHeight, TextureView textureView) {
        float f;
        float width = textureView.getWidth();
        float height = textureView.getHeight();
        float f2 = width / height;
        float f3 = videoWidth / videoHeight;
        float f4 = 1.0f;
        if (f2 > f3) {
            f = f2 / f3;
        } else if (f3 > f2) {
            float f5 = f3 / f2;
            f = 1.0f;
            f4 = f5;
        } else {
            f = 1.0f;
        }
        Matrix matrix = new Matrix();
        float f6 = 2;
        matrix.setScale(f4, f, width / f6, height / f6);
        textureView.setTransform(matrix);
    }

    /* JADX INFO: compiled from: VideoPlayerImpl.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/amazon/mp3/video/stories/videostoryplayback/VideoPlayerImpl$PositionRunnable;", "Ljava/lang/Runnable;", "(Lcom/amazon/mp3/video/stories/videostoryplayback/VideoPlayerImpl;)V", "run", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private final class PositionRunnable implements Runnable {
        final /* synthetic */ VideoPlayerImpl this$0;

        public PositionRunnable(VideoPlayerImpl this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.this$0.exoPlayer == null) {
                return;
            }
            VideoPlayerImpl videoPlayerImpl = this.this$0;
            VideoPlayer.Callback callback = videoPlayerImpl.callback;
            if (callback != null) {
                int i = videoPlayerImpl.queuePosition;
                SimpleExoPlayer simpleExoPlayer = videoPlayerImpl.exoPlayer;
                callback.onPlaybackPositionChanged(i, simpleExoPlayer == null ? 0L : simpleExoPlayer.getCurrentPosition());
            }
            videoPlayerImpl.handler.postDelayed(this, 200L);
        }
    }
}
