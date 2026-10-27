package com.amazon.avod.playback.renderer.visualon;

import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.media.framework.error.InvalidRendererTimestampException;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.framework.libraries.PlaybackNativeLibrariesLoader;
import com.amazon.avod.media.framework.platform.FileSystem;
import com.amazon.avod.media.framework.playback.util.ZoomCalculator;
import com.amazon.avod.media.playback.MediaDefaultConfiguration;
import com.amazon.avod.media.playback.SurfaceHandlingMode;
import com.amazon.avod.media.playback.VideoRenderingSettings;
import com.amazon.avod.media.playback.android.AndroidVideoSurface;
import com.amazon.avod.media.playback.support.MutableRendererPerformanceData;
import com.amazon.avod.media.playback.support.RendererCapabilities;
import com.amazon.avod.media.playback.support.RendererPerformanceData;
import com.amazon.avod.media.playback.support.RendererPerformanceEventListener;
import com.amazon.avod.media.playback.util.AudioConfig;
import com.amazon.avod.media.playback.util.VideoConfig;
import com.amazon.avod.media.playback.util.VideoRegion;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.playback.PlaybackException;
import com.amazon.avod.playback.PlaybackZoomLevel;
import com.amazon.avod.playback.player.PlaybackConfig;
import com.amazon.avod.playback.player.PlaybackPerformanceConfig;
import com.amazon.avod.playback.renderer.VideoRegionInterpolator;
import com.amazon.avod.playback.renderer.shared.NativeCodecTypes;
import com.amazon.avod.playback.renderer.shared.NativeRendererBase;
import com.amazon.avod.threading.ScheduledExecutorBuilder;
import com.amazon.avod.threading.Tickers;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableList;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
public final class VisualOnRenderer extends NativeRendererBase {
    private static final float MAX_GAIN = 2.0f;
    private static final int PERF_DATA_TIMER_START_DELAY_IN_MILLIS = 100;
    private static final int SURFACE_CREATION_TIMEOUT_MILLIS = 10000;
    private static final int VOLUME_SCALE = 100;
    private final Object mConfigureOutputDisplayMutex;
    private PlaybackZoomLevel mCurrentZoomLevel;
    private boolean mIsDisposed;
    private final boolean mIsDynamicVideoSurfaceResizeEnabled;
    private boolean mIsOutputDisplayConfigured;
    private boolean mIsPlaying;
    private final boolean mIsVideoSurfaceHotSwapSupported;
    private boolean mIsVideoTrackRecreationEnabled;
    private volatile PlaybackException mLastRendererException;
    private LayoutChangeListener mLayoutChangeListener;
    private final MediaSystemSharedContext mMediaSystemSharedContext;
    private final Object mMutex;
    private ViewGroup mParentViewGroup;
    private final Object mPerformanceDataLock;
    private final PlaybackPerformanceConfig mPlaybackPerformanceConfiguration;
    private final RendererCapabilities mRendererCapabilities;
    private final VisualOnRendererJni mRendererJni;
    private final MutableRendererPerformanceData mRendererPerformanceData;
    private final TimeSpan mRendererStatisticsUpdateInterval;
    private final VisualOnRendererType mRendererType;
    private final Stopwatch mStatisticsUpdateWatch;
    private AudioConfig mStoredAudioConfig;
    private VideoConfig mStoredVideoConfig;
    private final ScheduledExecutorService mSurfaceCallbackExecutor;
    private boolean mSurfaceDestroyed;
    private final AtomicReference<SurfaceView> mSurfaceViewReference;
    private Timer mTimer;
    private VideoRegion mVideoRegion;
    private AndroidVideoSurface mVideoSurface;

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public void setHdrStatus(boolean z) {
    }

    public VisualOnRenderer(@Nonnull MediaSystemSharedContext mediaSystemSharedContext, @Nonnull VisualOnRendererType visualOnRendererType, @Nonnull FileSystem fileSystem, @Nonnull PlaybackNativeLibrariesLoader playbackNativeLibrariesLoader) {
        ExecutorService executorService = mediaSystemSharedContext.getExecutorService();
        PlaybackConfig playbackConfig = PlaybackConfig.INSTANCE;
        PlaybackPerformanceConfig playbackPerformanceConfig = PlaybackPerformanceConfig.INSTANCE;
        AndroidVideoSurface androidVideoSurface = new AndroidVideoSurface(mediaSystemSharedContext.getAppContext());
        VideoRegionInterpolator videoRegionInterpolator = new VideoRegionInterpolator();
        ZoomCalculator zoomCalculator = new ZoomCalculator(mediaSystemSharedContext.getDeviceConfiguration());
        VisualOnRendererJni visualOnRendererJni = new VisualOnRendererJni(mediaSystemSharedContext.getAppContext(), mediaSystemSharedContext.getDeviceConfiguration(), fileSystem, playbackNativeLibrariesLoader);
        MediaDefaultConfiguration mediaDefaultConfiguration = MediaDefaultConfiguration.getInstance();
        VisualOnRendererConfig visualOnRendererConfig = VisualOnRendererConfig.INSTANCE;
        this(executorService, playbackConfig, playbackPerformanceConfig, androidVideoSurface, videoRegionInterpolator, zoomCalculator, visualOnRendererJni, visualOnRendererType, mediaDefaultConfiguration, mediaSystemSharedContext, visualOnRendererConfig.isVisualOnVideoSurfaceHotSwapSupported(), visualOnRendererConfig.isVisualOnBackgroundAudioPlaybackSupported());
    }

    VisualOnRenderer(@Nonnull ExecutorService executorService, @Nonnull PlaybackConfig playbackConfig, @Nonnull PlaybackPerformanceConfig playbackPerformanceConfig, @Nonnull AndroidVideoSurface androidVideoSurface, @Nonnull VideoRegionInterpolator videoRegionInterpolator, @Nonnull ZoomCalculator zoomCalculator, @Nonnull VisualOnRendererJni visualOnRendererJni, @Nonnull VisualOnRendererType visualOnRendererType, @Nonnull MediaDefaultConfiguration mediaDefaultConfiguration, @Nonnull MediaSystemSharedContext mediaSystemSharedContext, boolean z, boolean z2) {
        super(executorService, playbackConfig, videoRegionInterpolator, zoomCalculator, visualOnRendererJni, mediaDefaultConfiguration);
        this.mStatisticsUpdateWatch = Stopwatch.createUnstarted(Tickers.androidTicker());
        this.mPerformanceDataLock = new Object();
        this.mMutex = new Object();
        this.mConfigureOutputDisplayMutex = new Object();
        this.mRendererPerformanceData = new MutableRendererPerformanceData();
        this.mIsDisposed = false;
        this.mIsOutputDisplayConfigured = false;
        this.mSurfaceDestroyed = false;
        this.mIsPlaying = false;
        this.mVideoSurface = androidVideoSurface;
        this.mRendererType = visualOnRendererType;
        this.mRendererJni = visualOnRendererJni;
        this.mPlaybackPerformanceConfiguration = playbackPerformanceConfig;
        this.mRendererStatisticsUpdateInterval = playbackConfig.getRendererUpdateStatisticsInterval();
        this.mIsDynamicVideoSurfaceResizeEnabled = mediaDefaultConfiguration.isDynamicVideoSurfaceResizeEnabled();
        this.mIsVideoSurfaceHotSwapSupported = z;
        this.mRendererCapabilities = new RendererCapabilities(true, z, z2, true);
        this.mMediaSystemSharedContext = (MediaSystemSharedContext) Preconditions.checkNotNull(mediaSystemSharedContext, "mediaSystemSharedContext");
        this.mCurrentZoomLevel = PlaybackZoomLevel.NATIVE;
        this.mVideoRegion = this.mZoomCalculator.getVideoRegionForScreenSize();
        this.mSurfaceViewReference = new AtomicReference<>(null);
        this.mSurfaceCallbackExecutor = ScheduledExecutorBuilder.newBuilderFor(SurfaceCreationListener.class, new String[0]).withProfilerTraceLevel(Profiler.TraceLevel.VERBOSE).withFixedThreadPoolSize(1).allowCoreThreadExpiry(1L, TimeUnit.SECONDS).build();
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public int initialize(String str, String str2, boolean z) throws PlaybackException {
        this.mRendererJni.initialize();
        super.initialize(0, 0, str, str2, z);
        return 1;
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public int configureCodecs(@Nonnull VideoConfig videoConfig, @Nonnull AudioConfig audioConfig, boolean z, @Nonnull TimeSpan timeSpan) throws PlaybackException {
        super.configureStreamHandlers(videoConfig, audioConfig);
        this.mRendererJni.configureDrm();
        this.mStoredAudioConfig = audioConfig;
        this.mStoredVideoConfig = videoConfig;
        this.mContentBufferLookAheadSize = (TimeSpan) Preconditions.checkNotNull(timeSpan, "mContentBufferLookAheadSize");
        return 1;
    }

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererBase, com.amazon.avod.media.playback.support.VideoRenderer
    public void configureOutputDisplay(@Nonnull VideoRenderingSettings videoRenderingSettings) throws PlaybackException {
        synchronized (this.mConfigureOutputDisplayMutex) {
            Preconditions.checkNotNull(videoRenderingSettings, "settings");
            this.mIsVideoTrackRecreationEnabled = videoRenderingSettings.isVideoTrackRecreationEnabled();
            boolean zCleanupExistingSurface = cleanupExistingSurface();
            SurfaceHandlingMode surfaceHandlingMode = (SurfaceHandlingMode) Preconditions.checkNotNull(videoRenderingSettings.getSurfaceHandlingMode(), "mode");
            int i = AnonymousClass2.$SwitchMap$com$amazon$avod$media$playback$SurfaceHandlingMode[surfaceHandlingMode.ordinal()];
            if (i != 1) {
                if (i == 2) {
                    createNewSurface(videoRenderingSettings);
                    if (zCleanupExistingSurface || this.mIsOutputDisplayConfigured) {
                        restartVideoPipelineWithNewSurface();
                    } else {
                        configureNativeRenderer(this.mSurfaceViewReference.get());
                    }
                } else {
                    throw new PlaybackException(String.format(Locale.US, "Unsupported surface handling mode %s", surfaceHandlingMode));
                }
            } else if (!zCleanupExistingSurface && !this.mIsOutputDisplayConfigured) {
                configureNativeRenderer(null);
            }
            this.mIsOutputDisplayConfigured = true;
        }
    }

    /* JADX INFO: renamed from: com.amazon.avod.playback.renderer.visualon.VisualOnRenderer$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$media$playback$SurfaceHandlingMode;

        static {
            int[] iArr = new int[SurfaceHandlingMode.values().length];
            $SwitchMap$com$amazon$avod$media$playback$SurfaceHandlingMode = iArr;
            try {
                iArr[SurfaceHandlingMode.AUDIO_ONLY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$playback$SurfaceHandlingMode[SurfaceHandlingMode.CREATE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$playback$SurfaceHandlingMode[SurfaceHandlingMode.ATTACH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private boolean cleanupExistingSurface() {
        if (this.mIsOutputDisplayConfigured) {
            stopVideoPipeline();
        }
        if (this.mSurfaceViewReference.get() == null) {
            return false;
        }
        this.mSurfaceViewReference.set(null);
        this.mVideoSurface.unregisterListener();
        this.mVideoSurface.destroy();
        this.mVideoSurface = new AndroidVideoSurface(this.mMediaSystemSharedContext.getAppContext());
        return true;
    }

    private void createNewSurface(@Nonnull VideoRenderingSettings videoRenderingSettings) throws PlaybackException {
        Preconditions.checkState(videoRenderingSettings.getSurfaceHandlingMode() == SurfaceHandlingMode.CREATE, "Only CREATE mode can be be used to create a surface");
        CountDownLatch countDownLatch = new CountDownLatch(1);
        SurfaceCreationListener surfaceCreationListener = new SurfaceCreationListener(countDownLatch, this.mSurfaceViewReference);
        ViewGroup viewGroup = (ViewGroup) Preconditions.checkNotNull(videoRenderingSettings.getParentView(), "parentViewGroup");
        this.mParentViewGroup = viewGroup;
        if (this.mIsDynamicVideoSurfaceResizeEnabled) {
            this.mZoomCalculator.setParentLayoutOverride(viewGroup);
            LayoutChangeListener layoutChangeListener = new LayoutChangeListener();
            this.mLayoutChangeListener = layoutChangeListener;
            this.mParentViewGroup.addOnLayoutChangeListener(layoutChangeListener);
        }
        this.mVideoSurface.createAsync(videoRenderingSettings.getParentView(), surfaceCreationListener, videoRenderingSettings.isMediaOverlay(), videoRenderingSettings.getSystemUIFlags());
        try {
            if (!countDownLatch.await(10000L, TimeUnit.MILLISECONDS)) {
                throw new PlaybackException(PlaybackException.PlaybackError.RENDERER_INITIALIZE_TIMEOUT, "VisualOnRenderer: Create android SurfaceView time out");
            }
            SurfaceView surfaceView = this.mSurfaceViewReference.get();
            if (surfaceView == null) {
                throw new PlaybackException(PlaybackException.PlaybackError.RENDERER_INITIALIZE_NULL_SURFACE, "VisualOnRenderer: Unable to create android SurfaceView. This can be caused by the view inflation failure (out of memory) or when the user terminates the playback before the surface creation finished.");
            }
            if (!surfaceView.getHolder().getSurface().isValid()) {
                throw new PlaybackException(PlaybackException.PlaybackError.RENDERER_INITIALIZE_INVALID_SURFACE, "VisualOnRenderer: Surface is not valid!");
            }
            VideoRegion videoRegion = this.mZoomCalculator.getVideoRegion(videoRenderingSettings.getPlaybackZoomLevel());
            this.mCurrentZoomLevel = videoRenderingSettings.getPlaybackZoomLevel();
            resizeVideoSurfaceToVideoRegion(videoRegion);
        } catch (InterruptedException unused) {
            throw new PlaybackException(PlaybackException.PlaybackError.RENDERER_INITIALIZE_FAILED, "VisualOnRenderer: Create android SurfaceView was interrupted");
        }
    }

    private void configureNativeRenderer(@Nullable SurfaceView surfaceView) throws PlaybackException {
        this.mStatisticsUpdateWatch.reset().start();
        int iValueOf = NativeCodecTypes.valueOf(this.mStoredAudioConfig.getFourCC());
        this.mRendererJni.configureRenderer(NativeCodecTypes.valueOf(this.mStoredVideoConfig.getFourCC()), iValueOf, this.mStoredAudioConfig.getSampleRate(), this.mStoredAudioConfig.getChannels(), this.mStoredAudioConfig.getBitRate(), surfaceView, this.mRendererType);
        scheduleStatisticsUpdateTask();
    }

    private void scheduleStatisticsUpdateTask() {
        Timer timer = this.mTimer;
        if (timer != null) {
            timer.cancel();
        }
        this.mTimer = new Timer();
        this.mTimer.schedule(new TimerTask() { // from class: com.amazon.avod.playback.renderer.visualon.VisualOnRenderer.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                synchronized (VisualOnRenderer.this.mPerformanceDataLock) {
                    if (VisualOnRenderer.this.mSurfaceDestroyed) {
                        return;
                    }
                    VisualOnRenderer.this.mRendererJni.getPerformanceData(VisualOnRenderer.this.mRendererPerformanceData);
                    RendererPerformanceEventListener performanceListener = VisualOnRenderer.this.getPerformanceListener();
                    if (performanceListener != null && VisualOnRenderer.this.mRendererPerformanceData.isLowPerformanceDetected()) {
                        performanceListener.onLowPerformanceReported(new RendererPerformanceData(VisualOnRenderer.this.mRendererPerformanceData, ImmutableList.of()));
                    }
                }
            }
        }, 100L, this.mPlaybackPerformanceConfiguration.getPerformanceSamplingFrequency().getTotalMilliseconds());
    }

    private class SurfaceCreationListener implements AndroidVideoSurface.CreationListener {
        private final CountDownLatch mSurfaceCreateLatch;
        private final AtomicReference<SurfaceView> mSurfaceViewReference;

        private SurfaceCreationListener(@Nonnull CountDownLatch countDownLatch, @Nonnull AtomicReference<SurfaceView> atomicReference) {
            this.mSurfaceCreateLatch = (CountDownLatch) Preconditions.checkNotNull(countDownLatch, "surfaceCreateLatch");
            this.mSurfaceViewReference = (AtomicReference) Preconditions.checkNotNull(atomicReference, "surfaceReference");
        }

        @Override // com.amazon.avod.media.playback.android.AndroidVideoSurface.CreationListener
        public void onCreated(@Nonnull SurfaceHolder surfaceHolder) {
            if (this.mSurfaceCreateLatch.getCount() == 0 && !VisualOnRenderer.this.mIsDisposed && VisualOnRenderer.this.mIsVideoTrackRecreationEnabled && VisualOnRenderer.this.mIsVideoSurfaceHotSwapSupported) {
                DLog.logf("SurfaceCreationListener video surface recreated mid-flight %s", surfaceHolder);
                ScheduledExecutorService scheduledExecutorService = VisualOnRenderer.this.mSurfaceCallbackExecutor;
                final VisualOnRenderer visualOnRenderer = VisualOnRenderer.this;
                scheduledExecutorService.execute(new Runnable() { // from class: com.amazon.avod.playback.renderer.visualon.VisualOnRenderer$SurfaceCreationListener$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        visualOnRenderer.restartVideoPipelineWithNewSurface();
                    }
                });
                return;
            }
            DLog.logf("SurfaceCreationListener video surface created %s", surfaceHolder);
            this.mSurfaceViewReference.set(VisualOnRenderer.this.mVideoSurface.getSurfaceView());
            this.mSurfaceCreateLatch.countDown();
        }

        @Override // com.amazon.avod.media.playback.android.AndroidVideoSurface.CreationListener
        public void onDestroyed(@Nonnull SurfaceHolder surfaceHolder) {
            if (this.mSurfaceCreateLatch.getCount() == 0 && !VisualOnRenderer.this.mIsDisposed && VisualOnRenderer.this.mIsVideoTrackRecreationEnabled && VisualOnRenderer.this.mIsVideoSurfaceHotSwapSupported) {
                DLog.logf("SurfaceCreationListener video surface destroyed mid-flight %s", surfaceHolder);
                ScheduledExecutorService scheduledExecutorService = VisualOnRenderer.this.mSurfaceCallbackExecutor;
                final VisualOnRenderer visualOnRenderer = VisualOnRenderer.this;
                scheduledExecutorService.execute(new Runnable() { // from class: com.amazon.avod.playback.renderer.visualon.VisualOnRenderer$SurfaceCreationListener$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        visualOnRenderer.stopVideoPipeline();
                    }
                });
                return;
            }
            DLog.logf("SurfaceCreationListener video surface destroyed %s", surfaceHolder);
            this.mSurfaceViewReference.set(null);
            this.mSurfaceCreateLatch.countDown();
        }
    }

    public void restartVideoPipelineWithNewSurface() {
        long micros;
        if (this.mIsVideoTrackRecreationEnabled && this.mIsVideoSurfaceHotSwapSupported) {
            synchronized (this.mMutex) {
                if (this.mIsDisposed) {
                    return;
                }
                this.mRendererJni.notifySurfaceRecreated(this.mSurfaceViewReference.get());
                this.mSurfaceDestroyed = false;
                this.mRendererPerformanceData.reset();
                scheduleStatisticsUpdateTask();
                try {
                    micros = TimeUnit.NANOSECONDS.toMicros(getLastRenderedTimeStampInNanos());
                } catch (InvalidRendererTimestampException e) {
                    DLog.warnf("Failed to get the last rendered timestamp: %s. Using last cached presentation time.", e);
                    micros = TimeUnit.NANOSECONDS.toMicros(this.mCachedNextAudioPtsNanos);
                }
                this.mLastRendererException = new PlaybackException(PlaybackException.PlaybackError.RENDERER_VIDEO_SURFACE_RECREATED, String.format("Video surface recreated, seeking to the current audio track position %s Us", Long.valueOf(micros)), micros);
                if (this.mIsPlaying) {
                    try {
                        resume();
                    } catch (PlaybackException e2) {
                        DLog.warnf("Failed to resume the native playback engine: %s", e2);
                    }
                }
            }
        }
    }

    public void stopVideoPipeline() {
        if (this.mIsVideoTrackRecreationEnabled && this.mIsVideoSurfaceHotSwapSupported) {
            synchronized (this.mMutex) {
                if (this.mIsDisposed) {
                    return;
                }
                this.mRendererJni.notifySurfaceDestroyed();
                this.mSurfaceDestroyed = true;
            }
        }
    }

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererBase, com.amazon.avod.media.playback.support.VideoRenderer
    public void resume() throws PlaybackException {
        synchronized (this.mMutex) {
            this.mIsPlaying = true;
            super.resume();
        }
    }

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererBase, com.amazon.avod.media.playback.support.VideoRenderer
    public void pause() throws PlaybackException {
        synchronized (this.mMutex) {
            this.mIsPlaying = false;
            super.pause();
        }
    }

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererBase, com.amazon.avod.media.playback.support.VideoRenderer
    public void recreateVideoTrack() {
        DLog.logf("VisualOnRenderer.recreateVideoTrack() reactivating video track.");
        this.mLastRendererException = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resizeVideoSurfaceToVideoRegion(@Nonnull VideoRegion videoRegion) {
        this.mVideoRegion = (VideoRegion) Preconditions.checkNotNull(videoRegion, "region");
        this.mVideoSurface.setDimensions(videoRegion.getWidth(), videoRegion.getHeight());
        DLog.logf("Resized video surface to video region: %s", this.mVideoRegion.toString());
    }

    private class LayoutChangeListener implements View.OnLayoutChangeListener {
        private LayoutChangeListener() {
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            int width = view.getWidth();
            int i9 = i7 - i5;
            int height = view.getHeight();
            int i10 = i8 - i6;
            if (width == i9 && height == i10) {
                return;
            }
            VideoRegion videoRegion = ((NativeRendererBase) VisualOnRenderer.this).mZoomCalculator.getVideoRegion(VisualOnRenderer.this.mCurrentZoomLevel);
            DLog.logf("Video surface parent layout size changed to (w=%s h=%s), Resizing video surface to (w=%s h=%s).", Integer.valueOf(width), Integer.valueOf(height), Integer.valueOf(videoRegion.getWidth()), Integer.valueOf(videoRegion.getHeight()));
            VisualOnRenderer.this.resizeVideoSurfaceToVideoRegion(videoRegion);
        }
    }

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererBase, com.amazon.avod.media.playback.support.VideoRenderer
    public void setZoomLevel(PlaybackZoomLevel playbackZoomLevel, boolean z) throws PlaybackException {
        DLog.logf("Set zoom level to %s with ratio: %s", playbackZoomLevel.getZoomLevel(), Float.valueOf(playbackZoomLevel.getZoomRatio()));
        resizeVideoSurfaceToVideoRegion(this.mZoomCalculator.getVideoRegion(playbackZoomLevel));
        this.mCurrentZoomLevel = playbackZoomLevel;
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public void setPlaybackSpeed(float f) {
        Preconditions.checkArgument(f >= 0.5f && ((double) f) <= 2.0d, "Playback speed for VisualOn renderer must be within [0.5 - 2.0].");
        this.mRendererJni.setPlaybackSpeed(f);
    }

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererBase, com.amazon.avod.media.playback.support.VideoRenderer
    @Nonnull
    public RendererPerformanceData getRendererPerformanceData() {
        RendererPerformanceData rendererPerformanceData;
        synchronized (this.mPerformanceDataLock) {
            if (this.mStatisticsUpdateWatch.elapsed(TimeUnit.SECONDS) > this.mRendererStatisticsUpdateInterval.getTotalSeconds() && !this.mSurfaceDestroyed) {
                this.mStatisticsUpdateWatch.reset();
                this.mRendererJni.getPerformanceData(this.mRendererPerformanceData);
                this.mStatisticsUpdateWatch.start();
            }
            rendererPerformanceData = new RendererPerformanceData(this.mRendererPerformanceData, getRendererBufferStats());
        }
        return rendererPerformanceData;
    }

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererBase, com.amazon.avod.media.playback.support.VideoRenderer
    public void dispose(boolean z) {
        LayoutChangeListener layoutChangeListener;
        synchronized (this.mMutex) {
            Timer timer = this.mTimer;
            if (timer != null) {
                timer.cancel();
            }
            super.dispose(false);
            ViewGroup viewGroup = this.mParentViewGroup;
            if (viewGroup != null && (layoutChangeListener = this.mLayoutChangeListener) != null) {
                viewGroup.removeOnLayoutChangeListener(layoutChangeListener);
            }
            this.mIsDisposed = true;
            this.mSurfaceViewReference.set(null);
            this.mVideoSurface.destroy();
            this.mStatisticsUpdateWatch.reset();
        }
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public void checkRendererError(boolean z) throws MediaException {
        int iCheckRendererError;
        if (this.mLastRendererException != null) {
            throw this.mLastRendererException;
        }
        if (z && (iCheckRendererError = this.mPlaybackStatus.checkRendererError()) != 0) {
            throw new VisualOnRendererException("Error returned on NativePlaybackStatus", iCheckRendererError);
        }
    }

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererBase, com.amazon.avod.media.playback.support.VideoRenderer
    public void setVideoRegion(@Nonnull VideoRegion videoRegion) {
        this.mVideoRegion = (VideoRegion) Preconditions.checkNotNull(videoRegion, "videoRegion");
        this.mVideoSurface.setVideoRegion(videoRegion);
        DLog.logf("Resized video surface to absolute video region: %s", this.mVideoRegion.toString());
    }

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererBase, com.amazon.avod.media.playback.support.VideoRenderer
    @Nonnull
    public VideoRegion getVideoRegion() {
        return this.mVideoRegion;
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    @Nonnull
    public RendererCapabilities getRendererCapabilities() {
        return this.mRendererCapabilities;
    }

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererBase, com.amazon.avod.media.playback.support.VideoRenderer
    public void setVolume(float f) {
        Preconditions.checkArgument(f >= 0.0f, "volume gain cannot be negative.");
        Preconditions.checkArgument(f <= MAX_GAIN, "volume gain exceeds maximum");
        this.mRendererJni.setParam(VisualOnRendererJni.VOLUME_PARAM, Integer.valueOf((int) (f * 100.0f)));
    }
}
