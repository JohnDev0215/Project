package com.amazon.avod.media.playback.android;

import android.media.MediaPlayer;
import android.net.Uri;
import android.view.SurfaceHolder;
import android.view.ViewGroup;
import com.amazon.avod.content.urlvending.AudioTrackMetadata;
import com.amazon.avod.content.urlvending.FailoverMode;
import com.amazon.avod.content.urlvending.FailoverType;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.media.error.StandardErrorCode;
import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.framework.platform.DeviceConfiguration;
import com.amazon.avod.media.framework.playback.util.ZoomCalculator;
import com.amazon.avod.media.playback.MediaDefaultConfiguration;
import com.amazon.avod.media.playback.QOSCommunicationService;
import com.amazon.avod.media.playback.SurfaceHandlingMode;
import com.amazon.avod.media.playback.VideoPlayerBase;
import com.amazon.avod.media.playback.VideoRenderingSettings;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.android.AndroidVideoSurface;
import com.amazon.avod.media.playback.internal.GenericVideoPlayerState;
import com.amazon.avod.media.playback.internal.GenericVideoPlayerStateTracker;
import com.amazon.avod.media.playback.internal.PlaybackListenerProxy;
import com.amazon.avod.media.playback.reporting.aloysius.PlaybackMediaEventReporters;
import com.amazon.avod.media.playback.support.PlayerStatistics;
import com.amazon.avod.media.playback.util.VideoConfig;
import com.amazon.avod.media.playback.util.VideoRegion;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.playback.IllegalPlayerStateException;
import com.amazon.avod.playback.PlaybackBufferEventType;
import com.amazon.avod.playback.PlaybackEventContext;
import com.amazon.avod.playback.PlaybackException;
import com.amazon.avod.playback.PlaybackExperienceController;
import com.amazon.avod.playback.PlaybackZoomLevel;
import com.amazon.avod.playback.subtitles.SubtitlesListener;
import com.amazon.avod.playbackclient.displaymode.LayoutUpdater;
import com.amazon.avod.threading.ExecutorBuilder;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.Preconditions2;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.File;
import java.util.Collections;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AndroidVideoPlayer extends VideoPlayerBase {
    private final ExecutorService mCallbackExecutor;
    private final DeviceConfiguration mDeviceConfiguration;
    protected final PlaybackExperienceController mExperienceController;
    private final boolean mIsDynamicVideoSurfaceResizeEnabled;
    private final StatefulAndroidMediaPlayer mMediaPlayer;
    private final MediaPlayer.OnBufferingUpdateListener mOnBufferingUpdateListener;
    private ViewGroup mParentViewGroup;
    private VideoRegion mRegion;
    private TimeSpan mSeekToPosition;
    protected final MediaPlayer.OnVideoSizeChangedListener mSizeChangeListener;
    protected final GenericVideoPlayerStateTracker.StateChangeListener mStateChangeListener;
    private final AndroidVideoSurface mSurface;
    protected final AndroidVideoSurface.CreationListener mSurfaceCreationListener;
    private final Semaphore mSurfaceSemaphore;
    private VideoConfig mVideoConfig;
    private VideoSurfaceUpdater mVideoSurfaceUpdater;
    private final ZoomCalculator mZoomCalculator;
    private PlaybackZoomLevel mZoomLevel;

    public AndroidVideoPlayer(MediaSystemSharedContext mediaSystemSharedContext, @Nullable QOSCommunicationService qOSCommunicationService) {
        this(mediaSystemSharedContext, mediaSystemSharedContext.getDeviceConfiguration(), qOSCommunicationService);
    }

    public AndroidVideoPlayer(MediaSystemSharedContext mediaSystemSharedContext, DeviceConfiguration deviceConfiguration, @Nullable QOSCommunicationService qOSCommunicationService) {
        this(new PlaybackListenerProxy(), new StatefulAndroidMediaPlayer(mediaSystemSharedContext.getAppContext(), qOSCommunicationService), new AndroidVideoSurface(mediaSystemSharedContext.getAppContext()), ExecutorBuilder.newBuilderFor(AndroidVideoPlayer.class, new String[0]).withFixedThreadPoolSize(1).withProfilerTraceLevel(Profiler.TraceLevel.DEBUG).build(), new ZoomCalculator(deviceConfiguration), new Semaphore(1), deviceConfiguration, MediaDefaultConfiguration.getInstance());
    }

    AndroidVideoPlayer(PlaybackListenerProxy playbackListenerProxy, StatefulAndroidMediaPlayer statefulAndroidMediaPlayer, AndroidVideoSurface androidVideoSurface, ExecutorService executorService, ZoomCalculator zoomCalculator, Semaphore semaphore, @Nonnull DeviceConfiguration deviceConfiguration, @Nonnull MediaDefaultConfiguration mediaDefaultConfiguration) {
        super(playbackListenerProxy);
        this.mZoomLevel = PlaybackZoomLevel.NATIVE;
        this.mVideoConfig = null;
        this.mExperienceController = new PlaybackExperienceController() { // from class: com.amazon.avod.media.playback.android.AndroidVideoPlayer.1
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
            public boolean isVideoTrackRecreationSupported() {
                return true;
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            public void onSubtitlesCallbackComplete() {
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            public void registerSubtitleListener(SubtitlesListener subtitlesListener) {
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
                throw new UnsupportedOperationException("AndroidVideoPlayer: manuallyTriggerFailover() not implemented.");
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            @Nonnull
            public Optional<String> getAudioTrackId() {
                DLog.warnf("getAudioTrackId is unimplemented.");
                return Optional.absent();
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            public void setRestrictPlaybackToBufferedContent(boolean z) {
                DLog.warnf("setRestrictPlaybackToBufferedContent not implemented for AndroidVideoPlayer");
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            public void setZoomLevel(@Nonnull PlaybackZoomLevel playbackZoomLevel) {
                AndroidVideoPlayer.this.mZoomLevel = (PlaybackZoomLevel) Preconditions.checkNotNull(playbackZoomLevel, "zoomLevel");
                AndroidVideoPlayer.this.applyVideoDimensions();
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            public void scaleVolume(float f) {
                float fMax = Math.max(0.0f, Math.min(1.0f, f));
                DLog.logf("Scaling volume to %s", Float.valueOf(fMax));
                AndroidVideoPlayer.this.mMediaPlayer.setVolume(fMax, fMax);
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
                return AndroidVideoPlayer.this.mZoomLevel;
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            public void setPlaybackSpeed(float f) {
                DLog.warnf("setPlaybackSpeed not implemented in AndroidVideoPlayer");
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            @Nonnull
            public ImmutableList<AudioTrackMetadata> getAudioTrackMetadataList() {
                return ImmutableList.of();
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            @Nonnull
            public VideoConfig getVideoConfig() throws IllegalPlayerStateException {
                Preconditions.checkState(AndroidVideoPlayer.this.mVideoConfig != null, "This method must be called after preparation");
                return AndroidVideoPlayer.this.mVideoConfig;
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            public void setVideoPlayerInBackground(boolean z) {
                throw new UnsupportedOperationException("setVideoPlayerInBackground is not supported on the AndroidVideoPlayer. do so through the AdPlaybackStateMachine");
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            public void changeOutputDisplay(@Nonnull VideoRenderingSettings videoRenderingSettings) throws PlaybackException {
                Preconditions2.checkNotMainThread();
                AndroidVideoPlayer.this.mMediaPlayer.setDisplay(null);
                AndroidVideoPlayer.this.setRenderingSettings(videoRenderingSettings);
            }
        };
        GenericVideoPlayerStateTracker.StateChangeListener stateChangeListener = new GenericVideoPlayerStateTracker.StateChangeListener() { // from class: com.amazon.avod.media.playback.android.AndroidVideoPlayer.2
            @Override // com.amazon.avod.media.playback.internal.GenericVideoPlayerStateTracker.StateChangeListener
            public void onStateChange(GenericVideoPlayerState genericVideoPlayerState, GenericVideoPlayerState genericVideoPlayerState2) {
                PlaybackEventContext playbackEventContext = new PlaybackEventContext(AndroidVideoPlayer.this.getCurrentPosition(), PlaybackEventContext.PlayerType.ANDROID_PLAYER);
                switch (AnonymousClass6.$SwitchMap$com$amazon$avod$media$playback$internal$GenericVideoPlayerState[genericVideoPlayerState2.ordinal()]) {
                    case 1:
                        AndroidVideoPlayer.this.getEventProxy().onAdPlaybackLoaded();
                        break;
                    case 2:
                        AndroidVideoPlayer.this.getEventProxy().onAdPlaybackPlaying();
                        break;
                    case 3:
                    case 4:
                    case 5:
                        AndroidVideoPlayer.this.getEventProxy().onAdPlaybackPaused();
                        break;
                    case 6:
                        AndroidVideoPlayer.this.getEventProxy().onAdPlaybackCompleted();
                        break;
                }
                if (genericVideoPlayerState2 == GenericVideoPlayerState.COMPLETED) {
                    AndroidVideoPlayer.this.notifyCompleted();
                    return;
                }
                if (genericVideoPlayerState2 == GenericVideoPlayerState.PREPARING) {
                    AndroidVideoPlayer.this.getEventProxy().sendBufferStartEvent(PlaybackBufferEventType.INITIAL_LOADING, playbackEventContext, null);
                    return;
                }
                GenericVideoPlayerState genericVideoPlayerState3 = GenericVideoPlayerState.PREPARED;
                if (genericVideoPlayerState2 == genericVideoPlayerState3) {
                    try {
                        if (AndroidVideoPlayer.this.mSurfaceSemaphore.tryAcquire(5L, TimeUnit.SECONDS)) {
                            AndroidVideoPlayer.this.mSurfaceSemaphore.release();
                        } else {
                            DLog.warnf("Timed out while waiting for surface (%s seconds)", 5);
                        }
                    } catch (InterruptedException unused) {
                        DLog.warnf("Interrupted while waiting for surface");
                    }
                    AndroidVideoPlayer.this.applyVideoDimensions();
                    AndroidVideoPlayer androidVideoPlayer = AndroidVideoPlayer.this;
                    androidVideoPlayer.notifyPrepared(androidVideoPlayer.mMediaPlayer.getPlaybackStatus());
                    return;
                }
                if (genericVideoPlayerState2 == GenericVideoPlayerState.PLAYING) {
                    if (genericVideoPlayerState == genericVideoPlayerState3) {
                        AndroidVideoPlayer.this.getEventProxy().onStart(playbackEventContext);
                        AndroidVideoPlayer.this.getEventProxy().sendBufferEndEvent(PlaybackBufferEventType.INITIAL_LOADING, playbackEventContext);
                        AndroidVideoPlayer.this.getEventProxy().onStarted(AndroidVideoPlayer.this.mMediaPlayer.getPlaybackStatus());
                        return;
                    } else if (genericVideoPlayerState == GenericVideoPlayerState.PAUSED) {
                        AndroidVideoPlayer.this.getEventProxy().onResume(playbackEventContext);
                        return;
                    } else if (genericVideoPlayerState == GenericVideoPlayerState.BUFFERING) {
                        AndroidVideoPlayer.this.getEventProxy().sendBufferEndEvent(PlaybackBufferEventType.UNEXPECTED, playbackEventContext);
                        return;
                    } else {
                        if (genericVideoPlayerState == GenericVideoPlayerState.SEEKING) {
                            AndroidVideoPlayer.this.getEventProxy().onSeekEnd(playbackEventContext);
                            return;
                        }
                        return;
                    }
                }
                GenericVideoPlayerState genericVideoPlayerState4 = GenericVideoPlayerState.PAUSED_DURING_BUFFERING;
                if (genericVideoPlayerState2 == genericVideoPlayerState4) {
                    AndroidVideoPlayer.this.getEventProxy().onPause(playbackEventContext);
                    return;
                }
                if (genericVideoPlayerState2 == GenericVideoPlayerState.PAUSED) {
                    if (genericVideoPlayerState != genericVideoPlayerState4) {
                        AndroidVideoPlayer.this.getEventProxy().onPause(playbackEventContext);
                        return;
                    } else {
                        AndroidVideoPlayer.this.getEventProxy().sendBufferEndEvent(PlaybackBufferEventType.UNEXPECTED, playbackEventContext);
                        return;
                    }
                }
                if (genericVideoPlayerState2 == GenericVideoPlayerState.SEEKING) {
                    AndroidVideoPlayer.this.getEventProxy().onSeekStart(AndroidVideoPlayer.this.mSeekToPosition, playbackEventContext);
                    return;
                }
                if (genericVideoPlayerState2 == GenericVideoPlayerState.BUFFERING) {
                    if (genericVideoPlayerState == genericVideoPlayerState4) {
                        AndroidVideoPlayer.this.getEventProxy().onStart(playbackEventContext);
                    }
                    AndroidVideoPlayer.this.getEventProxy().sendBufferStartEvent(PlaybackBufferEventType.UNEXPECTED, playbackEventContext, null);
                } else if (genericVideoPlayerState2 == GenericVideoPlayerState.TERMINATED) {
                    AndroidVideoPlayer.this.getEventProxy().onStop(playbackEventContext);
                } else if (genericVideoPlayerState2 == GenericVideoPlayerState.ERRORED) {
                    AndroidVideoPlayer.this.notifyError(StandardErrorCode.ANDROID_MEDIAPLAYER_ERROR);
                }
            }
        };
        this.mStateChangeListener = stateChangeListener;
        MediaPlayer.OnVideoSizeChangedListener onVideoSizeChangedListener = new MediaPlayer.OnVideoSizeChangedListener() { // from class: com.amazon.avod.media.playback.android.AndroidVideoPlayer.3
            @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
                DLog.logf("onVideoSizeChanged invoked: w:%d, h:%d", Integer.valueOf(i), Integer.valueOf(i2));
                double d = i2 > 0 ? ((double) i) / ((double) i2) : -1.0d;
                VideoResolution videoResolution = new VideoResolution(i, i2, d);
                AndroidVideoPlayer.this.mVideoConfig = new VideoConfig("unkn", videoResolution, videoResolution, null, false, false, d);
                AndroidVideoPlayer.this.applyVideoDimensions();
            }
        };
        this.mSizeChangeListener = onVideoSizeChangedListener;
        this.mSurfaceCreationListener = new AndroidVideoSurface.CreationListener() { // from class: com.amazon.avod.media.playback.android.AndroidVideoPlayer.4
            @Override // com.amazon.avod.media.playback.android.AndroidVideoSurface.CreationListener
            public void onCreated(SurfaceHolder surfaceHolder) {
                AndroidVideoPlayer.this.mSurfaceSemaphore.release();
                AndroidVideoPlayer.this.mMediaPlayer.setDisplay(surfaceHolder);
            }

            @Override // com.amazon.avod.media.playback.android.AndroidVideoSurface.CreationListener
            public void onDestroyed(SurfaceHolder surfaceHolder) {
                AndroidVideoPlayer.this.mMediaPlayer.setDisplay(null);
            }
        };
        MediaPlayer.OnBufferingUpdateListener onBufferingUpdateListener = new MediaPlayer.OnBufferingUpdateListener() { // from class: com.amazon.avod.media.playback.android.AndroidVideoPlayer.5
            @Override // android.media.MediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
                if (i == 100) {
                    DLog.devf("Content file downloaded.");
                    AndroidVideoPlayer.this.getEventProxy().onContentDownloaded();
                }
            }
        };
        this.mOnBufferingUpdateListener = onBufferingUpdateListener;
        this.mMediaPlayer = statefulAndroidMediaPlayer;
        this.mSurface = androidVideoSurface;
        this.mZoomCalculator = zoomCalculator;
        this.mCallbackExecutor = executorService;
        this.mSurfaceSemaphore = semaphore;
        statefulAndroidMediaPlayer.setOnVideoSizeChangedListener(onVideoSizeChangedListener);
        statefulAndroidMediaPlayer.setStateChangeListener(new GenericVideoPlayerStateTracker.AsynchronousStateChangeListenerDelegate(executorService, stateChangeListener));
        statefulAndroidMediaPlayer.setOnBufferingUpdateListener(onBufferingUpdateListener);
        this.mRegion = zoomCalculator.getVideoRegionForScreenSize();
        this.mDeviceConfiguration = (DeviceConfiguration) Preconditions.checkNotNull(deviceConfiguration, "deviceConfiguration");
        Preconditions.checkNotNull(mediaDefaultConfiguration, "mediaDefaultConfiguration");
        this.mIsDynamicVideoSurfaceResizeEnabled = mediaDefaultConfiguration.isDynamicVideoSurfaceResizeEnabled();
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void prepareAsync(VideoSpecification videoSpecification, File file) throws Throwable {
        this.mMediaPlayer.prepareAsync(Uri.parse(videoSpecification.getUrl()));
    }

    @Override // com.amazon.avod.media.playback.VideoPlayerBase
    public void terminatePlayback(boolean z, @Nullable MediaException mediaException) {
        VideoSurfaceUpdater videoSurfaceUpdater;
        this.mMediaPlayer.stop();
        ViewGroup viewGroup = this.mParentViewGroup;
        if (viewGroup != null && (videoSurfaceUpdater = this.mVideoSurfaceUpdater) != null) {
            viewGroup.removeOnLayoutChangeListener(videoSurfaceUpdater);
        }
        this.mSurface.destroy();
        this.mCallbackExecutor.shutdown();
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public long getCurrentPosition() {
        return this.mMediaPlayer.getCurrentPosition();
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public long getCurrentAbsolutePosition() {
        return getCurrentPosition();
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public long getCurrentPositionUTC() {
        throw new UnsupportedOperationException("getCurrentPositionUTC is not supported in AndroidVideoPlayer");
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public long getDuration() {
        return this.mMediaPlayer.getDuration();
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    @Nonnull
    public PlayerStatistics getPlayerStatistics() {
        return PlayerStatistics.EMPTY_PLAYER_STATS;
    }

    @Override // com.amazon.avod.media.playback.VideoPlayerBase, com.amazon.avod.media.playback.VideoPlayer
    public int getBufferPercentage() {
        return this.mMediaPlayer.getBufferPercentage();
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void pause() {
        this.mMediaPlayer.pause();
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void start() {
        this.mMediaPlayer.start();
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void seekTo(long j) {
        Preconditions.checkArgument(j <= 2147483647L, "seek time for AndroidVideoPlayer is out of range");
        this.mSeekToPosition = TimeSpan.fromMilliseconds(j);
        this.mMediaPlayer.seekTo((int) j);
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void seekToUTC(long j) {
        throw new UnsupportedOperationException("seekToUTC is not supported in AndroidVideoPlayer");
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public PlaybackExperienceController getPlaybackExperienceController() {
        return this.mExperienceController;
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void setRenderingSettings(@Nonnull VideoRenderingSettings videoRenderingSettings) {
        Preconditions.checkNotNull(videoRenderingSettings, "settings");
        int i = AnonymousClass6.$SwitchMap$com$amazon$avod$media$playback$SurfaceHandlingMode[videoRenderingSettings.getSurfaceHandlingMode().ordinal()];
        if (i != 1) {
            if (i == 2) {
                this.mMediaPlayer.setSurface(videoRenderingSettings.getSurface());
                return;
            } else {
                if (i == 3) {
                    DLog.warnf("AndroidVideoPlayer: Audio only surface handling mode, starting without a playback surface");
                    return;
                }
                throw new IllegalStateException(String.format(Locale.US, "Please handle new surface handling mode %s", videoRenderingSettings.getSurfaceHandlingMode().name()));
            }
        }
        try {
            this.mSurfaceSemaphore.acquire();
        } catch (InterruptedException e) {
            DLog.exceptionf(e);
        }
        ViewGroup parentView = videoRenderingSettings.getParentView();
        this.mParentViewGroup = parentView;
        if (this.mIsDynamicVideoSurfaceResizeEnabled) {
            this.mZoomCalculator.setParentLayoutOverride(parentView);
            VideoSurfaceUpdater videoSurfaceUpdater = new VideoSurfaceUpdater();
            this.mVideoSurfaceUpdater = videoSurfaceUpdater;
            this.mParentViewGroup.addOnLayoutChangeListener(videoSurfaceUpdater);
        }
        this.mSurface.createAsync(this.mParentViewGroup, this.mSurfaceCreationListener, videoRenderingSettings.isMediaOverlay(), videoRenderingSettings.getSystemUIFlags());
    }

    private class VideoSurfaceUpdater extends LayoutUpdater {
        private VideoSurfaceUpdater() {
        }

        @Override // com.amazon.avod.playbackclient.displaymode.LayoutUpdater
        protected void update() {
            AndroidVideoPlayer.this.applyVideoDimensions();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void applyVideoDimensions() {
        if (this.mMediaPlayer.getState().isPlayback()) {
            try {
                int videoWidth = this.mMediaPlayer.getVideoWidth();
                int videoHeight = this.mMediaPlayer.getVideoHeight();
                DLog.logf("MediaPlayer content width=%d height=%d", Integer.valueOf(videoWidth), Integer.valueOf(videoHeight));
                this.mZoomCalculator.setVideoResolution(new VideoResolution(videoWidth, videoHeight));
                VideoRegion videoRegion = this.mZoomCalculator.getVideoRegion(this.mZoomLevel);
                int width = videoRegion.getWidth();
                int height = videoRegion.getHeight();
                DLog.logf("MediaPlayer zoomed to width=%d height=%d", Integer.valueOf(width), Integer.valueOf(height));
                this.mSurface.setDimensions(width, height);
            } catch (IllegalStateException e) {
                DLog.warnf("MediaPlayer threw unexpected IllegalStateException reading video dimensions: %s, unable to apply video dimensions to video surface", e);
            }
        }
    }

    /* JADX INFO: renamed from: com.amazon.avod.media.playback.android.AndroidVideoPlayer$6, reason: invalid class name */
    static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$media$playback$SurfaceHandlingMode;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$media$playback$internal$GenericVideoPlayerState;

        static {
            int[] iArr = new int[GenericVideoPlayerState.values().length];
            $SwitchMap$com$amazon$avod$media$playback$internal$GenericVideoPlayerState = iArr;
            try {
                iArr[GenericVideoPlayerState.PREPARED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$playback$internal$GenericVideoPlayerState[GenericVideoPlayerState.PLAYING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$playback$internal$GenericVideoPlayerState[GenericVideoPlayerState.PAUSED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$playback$internal$GenericVideoPlayerState[GenericVideoPlayerState.PAUSED_DURING_BUFFERING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$playback$internal$GenericVideoPlayerState[GenericVideoPlayerState.BUFFERING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$playback$internal$GenericVideoPlayerState[GenericVideoPlayerState.COMPLETED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            int[] iArr2 = new int[SurfaceHandlingMode.values().length];
            $SwitchMap$com$amazon$avod$media$playback$SurfaceHandlingMode = iArr2;
            try {
                iArr2[SurfaceHandlingMode.CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$playback$SurfaceHandlingMode[SurfaceHandlingMode.ATTACH.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$playback$SurfaceHandlingMode[SurfaceHandlingMode.AUDIO_ONLY.ordinal()] = 3;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }
}
