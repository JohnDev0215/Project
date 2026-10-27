package com.amazon.avod.media.playback.image;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.amazon.avod.content.urlvending.AudioTrackMetadata;
import com.amazon.avod.content.urlvending.FailoverMode;
import com.amazon.avod.content.urlvending.FailoverType;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.media.error.MediaErrorCode;
import com.amazon.avod.media.error.StandardErrorCode;
import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.media.framework.error.GenericMediaException;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.framework.platform.Handlers;
import com.amazon.avod.media.framework.playback.util.ZoomCalculator;
import com.amazon.avod.media.framework.util.PausableCountDownTimer;
import com.amazon.avod.media.framework.util.UrlUtils;
import com.amazon.avod.media.playback.PlaybackDataSource;
import com.amazon.avod.media.playback.VideoPlayerBase;
import com.amazon.avod.media.playback.VideoRenderingSettings;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.internal.GenericVideoPlayerState;
import com.amazon.avod.media.playback.internal.GenericVideoPlayerStateTracker;
import com.amazon.avod.media.playback.internal.PlaybackListenerProxy;
import com.amazon.avod.media.playback.reporting.aloysius.PlaybackMediaEventReporters;
import com.amazon.avod.media.playback.support.PlayerStatistics;
import com.amazon.avod.media.playback.util.VideoConfig;
import com.amazon.avod.media.playback.util.VideoRegion;
import com.amazon.avod.playback.IllegalPlayerStateException;
import com.amazon.avod.playback.PlaybackEventContext;
import com.amazon.avod.playback.PlaybackExperienceController;
import com.amazon.avod.playback.PlaybackZoomLevel;
import com.amazon.avod.playback.subtitles.SubtitlesListener;
import com.amazon.avod.util.DLog;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.io.Closeables;
import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class ImageVideoPlayer extends VideoPlayerBase {
    private final PausableCountDownTimer mCountdownTimer;
    private final ArrayDeque<Drawable> mDrawables;
    private TimeSpan mDuration;
    private final ExecutorService mExecutorService;
    private final PlaybackExperienceController mExperienceController;
    private final ImageView mImageView;
    private PlaybackDataSource mInitialDataSource;
    private MediaErrorCode mLastError;
    private VideoRenderingSettings mRenderingSettings;
    private TimeSpan mSeekToPosition;
    final GenericVideoPlayerStateTracker.StateChangeListener mStateChangeListener;
    private final GenericVideoPlayerStateTracker mStateTracker;
    private final Handler mUiHandler;
    private final ZoomCalculator mZoomCalculator;
    private PlaybackZoomLevel mZoomLevel;

    @Override // com.amazon.avod.media.playback.VideoPlayerBase, com.amazon.avod.media.playback.VideoPlayer
    public int getBufferPercentage() {
        return 100;
    }

    public ImageVideoPlayer(MediaSystemSharedContext mediaSystemSharedContext) {
        this(Handlers.getUIHandler(), new PlaybackListenerProxy(), new PausableCountDownTimer(), new ImageView(mediaSystemSharedContext.getAppContext()), mediaSystemSharedContext.getExecutorService(), new ZoomCalculator(mediaSystemSharedContext.getDeviceConfiguration()), new GenericVideoPlayerStateTracker());
    }

    ImageVideoPlayer(Handler handler, PlaybackListenerProxy playbackListenerProxy, PausableCountDownTimer pausableCountDownTimer, ImageView imageView, ExecutorService executorService, ZoomCalculator zoomCalculator, GenericVideoPlayerStateTracker genericVideoPlayerStateTracker) {
        super(playbackListenerProxy);
        this.mZoomLevel = PlaybackZoomLevel.NO_ZOOM;
        this.mLastError = null;
        GenericVideoPlayerStateTracker.StateChangeListener stateChangeListener = new GenericVideoPlayerStateTracker.StateChangeListener() { // from class: com.amazon.avod.media.playback.image.ImageVideoPlayer.3
            @Override // com.amazon.avod.media.playback.internal.GenericVideoPlayerStateTracker.StateChangeListener
            public void onStateChange(GenericVideoPlayerState genericVideoPlayerState, GenericVideoPlayerState genericVideoPlayerState2) {
                PlaybackEventContext playbackEventContext = new PlaybackEventContext(ImageVideoPlayer.this.getCurrentPosition(), PlaybackEventContext.PlayerType.IMAGE_PLAYER);
                switch (AnonymousClass5.$SwitchMap$com$amazon$avod$media$playback$internal$GenericVideoPlayerState[genericVideoPlayerState2.ordinal()]) {
                    case 1:
                    case 2:
                        return;
                    case 3:
                        if (genericVideoPlayerState == GenericVideoPlayerState.PREPARING) {
                            ImageVideoPlayer imageVideoPlayer = ImageVideoPlayer.this;
                            imageVideoPlayer.notifyPrepared(imageVideoPlayer.mInitialDataSource);
                            return;
                        }
                        return;
                    case 4:
                        if (genericVideoPlayerState == GenericVideoPlayerState.PREPARED) {
                            ImageVideoPlayer.this.getEventProxy().onStart(playbackEventContext);
                            return;
                        } else if (genericVideoPlayerState == GenericVideoPlayerState.SEEKING) {
                            ImageVideoPlayer.this.getEventProxy().onSeekEnd(playbackEventContext);
                            return;
                        } else {
                            if (genericVideoPlayerState == GenericVideoPlayerState.PAUSED) {
                                ImageVideoPlayer.this.getEventProxy().onResume(playbackEventContext);
                                return;
                            }
                            return;
                        }
                    case 5:
                        ImageVideoPlayer.this.getEventProxy().onPause(playbackEventContext);
                        return;
                    case 6:
                        throw new IllegalStateException("ImageVideoPlayer should never buffer.");
                    case 7:
                        ImageVideoPlayer.this.getEventProxy().onSeekStart(ImageVideoPlayer.this.mSeekToPosition, playbackEventContext);
                        return;
                    case 8:
                        Preconditions.checkState(ImageVideoPlayer.this.mLastError != null);
                        ImageVideoPlayer imageVideoPlayer2 = ImageVideoPlayer.this;
                        imageVideoPlayer2.notifyError(imageVideoPlayer2.mLastError);
                        return;
                    case 9:
                        ImageVideoPlayer.this.notifyCompleted();
                        return;
                    case 10:
                        ImageVideoPlayer.this.getEventProxy().onStop(playbackEventContext);
                        return;
                    default:
                        throw new IllegalStateException("ImageVideoPlayer transitioned to an unknown state.");
                }
            }
        };
        this.mStateChangeListener = stateChangeListener;
        this.mExperienceController = new PlaybackExperienceController() { // from class: com.amazon.avod.media.playback.image.ImageVideoPlayer.4
            @Override // com.amazon.avod.playback.PlaybackExperienceController
            public void deregisterSubtitleListener(SubtitlesListener subtitlesListener) {
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            @Nullable
            public PlaybackMediaEventReporters getAloysiusReporter() {
                return null;
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            public boolean isStreamingSubtitlesSupported() {
                return false;
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            public void onSubtitlesCallbackComplete() {
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            public void registerSubtitleListener(SubtitlesListener subtitlesListener) {
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            public void scaleVolume(float f) {
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            public void setFailoverZigZagSpeed(int i) {
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            public void startSubtitleDownload(String str) {
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            public void stopSubtitleDownload() {
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            public void manuallyTriggerFailover(@Nonnull FailoverType failoverType, @Nullable FailoverMode failoverMode) {
                throw new UnsupportedOperationException("ImageVideoPlayer: manuallyTriggerFailover() not implemented.");
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            @Nonnull
            public Optional<String> getAudioTrackId() {
                throw new UnsupportedOperationException("ImageVideoPlayer: getAudioTrackId not implemented.");
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            public void setRestrictPlaybackToBufferedContent(boolean z) {
                throw new UnsupportedOperationException("setRestrictPlaybackToBufferedContent not implemented for ImageVideoPlayer");
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            public void setZoomLevel(PlaybackZoomLevel playbackZoomLevel) {
                ImageVideoPlayer.this.mZoomLevel = playbackZoomLevel;
                ImageVideoPlayer.this.mImageView.post(new Runnable() { // from class: com.amazon.avod.media.playback.image.ImageVideoPlayer.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ImageVideoPlayer.this.applyVideoDimensions();
                    }
                });
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            public Set<String> getAvailableSubtitleLanguageCodes() {
                return Collections.emptySet();
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            public Set<String> getAvailableAudioLanguages() {
                return Collections.EMPTY_SET;
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            public Optional<String> getCurrentAudioLanguage() {
                return Optional.absent();
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            public long getLiveTimeWindowStartMillis() {
                throw new UnsupportedOperationException("getLiveTimeWindowStartMillis not supported");
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            public long getLiveTimeWindowEndMillis() {
                throw new UnsupportedOperationException("getLiveTimeWindowEndMillis not supported");
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            @Nonnull
            public PlaybackZoomLevel getZoomLevel() {
                return ImageVideoPlayer.this.mZoomLevel;
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            public void setPlaybackSpeed(float f) {
                DLog.warnf("setPlaybackSpeed not implemented in ImageVideoPlayer");
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            @Nonnull
            public ImmutableList<AudioTrackMetadata> getAudioTrackMetadataList() throws IllegalPlayerStateException {
                throw new IllegalPlayerStateException("getAudioTrackMetadataList not supported in ImageVideoPlayer");
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            @Nonnull
            public VideoConfig getVideoConfig() throws IllegalPlayerStateException {
                throw new UnsupportedOperationException("getVideoConfig() not supported for ImageVideoPlayer!");
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            public void setVideoPlayerInBackground(boolean z) {
                throw new UnsupportedOperationException("setVideoPlayerInBackground is not supported");
            }
        };
        this.mUiHandler = handler;
        this.mCountdownTimer = pausableCountDownTimer;
        this.mImageView = imageView;
        this.mExecutorService = executorService;
        this.mZoomCalculator = zoomCalculator;
        this.mDrawables = new ArrayDeque<>();
        this.mStateTracker = genericVideoPlayerStateTracker;
        genericVideoPlayerStateTracker.setListener(new GenericVideoPlayerStateTracker.AsynchronousStateChangeListenerDelegate(executorService, stateChangeListener));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void start() {
        Preconditions.checkNotNull(this.mRenderingSettings, "setRenderingSettings must be called before starting playback.");
        final ViewGroup parentView = this.mRenderingSettings.getParentView();
        this.mUiHandler.post(new Runnable() { // from class: com.amazon.avod.media.playback.image.ImageVideoPlayer.1
            @Override // java.lang.Runnable
            public void run() {
                Drawable drawable;
                synchronized (ImageVideoPlayer.this.mDrawables) {
                    drawable = (Drawable) ImageVideoPlayer.this.mDrawables.peek();
                    ImageVideoPlayer.this.mDrawables.clear();
                }
                if (drawable != null) {
                    ImageVideoPlayer.this.mImageView.setImageDrawable(drawable);
                }
                ImageVideoPlayer.this.applyVideoDimensions();
                if (ImageVideoPlayer.this.mImageView.getParent() == null) {
                    parentView.addView(ImageVideoPlayer.this.mImageView);
                }
                ImageVideoPlayer.this.mCountdownTimer.start(new TimerCompletionCallback());
            }
        });
        this.mStateTracker.changeState(GenericVideoPlayerState.PLAYING);
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void pause() {
        this.mCountdownTimer.pause();
        this.mStateTracker.changeState(GenericVideoPlayerState.PAUSED);
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void seekTo(long j) {
        Preconditions.checkArgument(this.mDuration.getTotalMilliseconds() >= j);
        this.mSeekToPosition = TimeSpan.fromMilliseconds(j);
        this.mStateTracker.changeState(GenericVideoPlayerState.SEEKING);
        this.mCountdownTimer.cancel();
        this.mCountdownTimer.set(this.mDuration.getTotalMilliseconds() - j);
        this.mStateTracker.changeState(GenericVideoPlayerState.PLAYING);
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void seekToUTC(long j) {
        throw new UnsupportedOperationException("seekToUTC is not supported in ImageVideoPlayer");
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public long getCurrentPosition() {
        return this.mDuration.getTotalMilliseconds() - this.mCountdownTimer.getRemainingTime();
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public long getCurrentAbsolutePosition() {
        return getCurrentPosition();
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public long getCurrentPositionUTC() {
        throw new UnsupportedOperationException("getCurrentPositionUTC is not supported in ImageVideoPlayer");
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public long getDuration() {
        return this.mDuration.getTotalMilliseconds();
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    @Nonnull
    public PlayerStatistics getPlayerStatistics() {
        return PlayerStatistics.EMPTY_PLAYER_STATS;
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public PlaybackExperienceController getPlaybackExperienceController() {
        return this.mExperienceController;
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void prepareAsync(VideoSpecification videoSpecification, File file) throws MediaException {
        this.mDuration = (TimeSpan) Preconditions.checkNotNull(videoSpecification.getDuration());
        this.mStateTracker.changeState(GenericVideoPlayerState.PREPARING);
        this.mCountdownTimer.set(this.mDuration.getTotalMilliseconds());
        try {
            URL url = new URL(videoSpecification.getUrl());
            this.mInitialDataSource = UrlUtils.isLocal(url) ? PlaybackDataSource.DOWNLOADED : PlaybackDataSource.STREAMING;
            this.mExecutorService.execute(new ImageDownloadRunnable(url));
        } catch (MalformedURLException e) {
            throw new GenericMediaException(e, StandardErrorCode.IMAGE_VIDEO_PLAYER_ERROR);
        }
    }

    @Override // com.amazon.avod.media.playback.VideoPlayerBase
    public void terminatePlayback(boolean z, @Nullable MediaException mediaException) {
        this.mCountdownTimer.cancel();
        this.mStateTracker.changeState(GenericVideoPlayerState.TERMINATED);
        VideoRenderingSettings videoRenderingSettings = this.mRenderingSettings;
        if (videoRenderingSettings != null) {
            final ViewGroup parentView = videoRenderingSettings.getParentView();
            parentView.post(new Runnable() { // from class: com.amazon.avod.media.playback.image.ImageVideoPlayer.2
                @Override // java.lang.Runnable
                public void run() {
                    parentView.removeView(ImageVideoPlayer.this.mImageView);
                }
            });
        }
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void setRenderingSettings(@Nonnull VideoRenderingSettings videoRenderingSettings) {
        this.mRenderingSettings = (VideoRenderingSettings) Preconditions.checkNotNull(videoRenderingSettings, "settings");
    }

    void applyVideoDimensions() {
        Drawable drawable = this.mImageView.getDrawable();
        if (drawable == null) {
            return;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (this.mZoomLevel != PlaybackZoomLevel.NO_ZOOM) {
            this.mZoomCalculator.setVideoResolution(new VideoResolution(intrinsicWidth, intrinsicHeight));
            VideoRegion videoRegion = this.mZoomCalculator.getVideoRegion(this.mZoomLevel);
            intrinsicWidth = videoRegion.getWidth();
            intrinsicHeight = videoRegion.getHeight();
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(intrinsicWidth, intrinsicHeight);
        layoutParams.addRule(13);
        this.mImageView.setLayoutParams(layoutParams);
    }

    void setLastError(MediaErrorCode mediaErrorCode) {
        this.mLastError = mediaErrorCode;
    }

    /* JADX INFO: renamed from: com.amazon.avod.media.playback.image.ImageVideoPlayer$5, reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$media$playback$internal$GenericVideoPlayerState;

        static {
            int[] iArr = new int[GenericVideoPlayerState.values().length];
            $SwitchMap$com$amazon$avod$media$playback$internal$GenericVideoPlayerState = iArr;
            try {
                iArr[GenericVideoPlayerState.UNINITIALIZED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$playback$internal$GenericVideoPlayerState[GenericVideoPlayerState.PREPARING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$playback$internal$GenericVideoPlayerState[GenericVideoPlayerState.PREPARED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$playback$internal$GenericVideoPlayerState[GenericVideoPlayerState.PLAYING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$playback$internal$GenericVideoPlayerState[GenericVideoPlayerState.PAUSED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$playback$internal$GenericVideoPlayerState[GenericVideoPlayerState.BUFFERING.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$playback$internal$GenericVideoPlayerState[GenericVideoPlayerState.SEEKING.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$playback$internal$GenericVideoPlayerState[GenericVideoPlayerState.ERRORED.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$playback$internal$GenericVideoPlayerState[GenericVideoPlayerState.COMPLETED.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$playback$internal$GenericVideoPlayerState[GenericVideoPlayerState.TERMINATED.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    private class ImageDownloadRunnable implements Runnable {
        private final URL mURL;

        public ImageDownloadRunnable(URL url) {
            this.mURL = url;
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            InputStream inputStream;
            Throwable th;
            InputStream inputStream2 = null;
            try {
                try {
                    URLConnection uRLConnection = (URLConnection) FirebasePerfUrlConnection.instrument(this.mURL.openConnection());
                    uRLConnection.connect();
                    inputStream = uRLConnection.getInputStream();
                    try {
                        Drawable drawableCreateFromStream = Drawable.createFromStream(inputStream, null);
                        synchronized (ImageVideoPlayer.this.mDrawables) {
                            ImageVideoPlayer.this.mDrawables.addFirst(drawableCreateFromStream);
                        }
                        ImageVideoPlayer.this.mStateTracker.changeState(GenericVideoPlayerState.PREPARED);
                        Closeables.closeQuietly(inputStream);
                    } catch (IOException unused) {
                        inputStream2 = inputStream;
                        ImageVideoPlayer.this.setLastError(StandardErrorCode.NETWORK_ERROR);
                        ImageVideoPlayer.this.mStateTracker.changeState(GenericVideoPlayerState.ERRORED);
                        Closeables.closeQuietly(inputStream2);
                    } catch (Throwable th2) {
                        th = th2;
                        Closeables.closeQuietly(inputStream);
                        throw th;
                    }
                } catch (Throwable th3) {
                    inputStream = inputStream2;
                    th = th3;
                }
            } catch (IOException unused2) {
            }
        }
    }

    private class TimerCompletionCallback implements Runnable {
        private TimerCompletionCallback() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ImageVideoPlayer.this.mStateTracker.changeState(GenericVideoPlayerState.COMPLETED);
        }
    }
}
