package com.amazon.avod.playback.renderer.mediacodec;

import android.annotation.TargetApi;
import android.media.AudioManager;
import android.media.MediaFormat;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.ViewGroup;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.drm.DrmCryptoSession;
import com.amazon.avod.media.drm.DrmSession;
import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.media.framework.error.DrmLicensingException;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.framework.playback.util.ZoomCalculator;
import com.amazon.avod.media.playback.MediaDefaultConfiguration;
import com.amazon.avod.media.playback.SurfaceHandlingMode;
import com.amazon.avod.media.playback.VideoRenderingSettings;
import com.amazon.avod.media.playback.android.AndroidVideoSurface;
import com.amazon.avod.media.playback.avsync.MediaClock;
import com.amazon.avod.media.playback.avsync.TimeSource;
import com.amazon.avod.media.playback.pipeline.MediaPipeline;
import com.amazon.avod.media.playback.pipeline.MediaPipelineContext;
import com.amazon.avod.media.playback.pipeline.PipelineTaskType;
import com.amazon.avod.media.playback.source.MediaSource;
import com.amazon.avod.media.playback.support.Buffer;
import com.amazon.avod.media.playback.support.DrmFramework;
import com.amazon.avod.media.playback.support.MediaCodecDeviceCapabilityDetector;
import com.amazon.avod.media.playback.support.RendererCapabilities;
import com.amazon.avod.media.playback.support.RendererPerformanceData;
import com.amazon.avod.media.playback.support.RendererPerformanceEventListener;
import com.amazon.avod.media.playback.support.VideoRenderer;
import com.amazon.avod.media.playback.util.AudioConfig;
import com.amazon.avod.media.playback.util.AudioTrackAdapter;
import com.amazon.avod.media.playback.util.VideoConfig;
import com.amazon.avod.media.playback.util.VideoRegion;
import com.amazon.avod.media.playback.util.VideoTrackAdapter;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.playback.PlaybackException;
import com.amazon.avod.playback.PlaybackZoomLevel;
import com.amazon.avod.playback.capability.DeviceResources;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playback.drm.DrmSecurityLevel;
import com.amazon.avod.playback.player.PlaybackConfig;
import com.amazon.avod.playback.renderer.VideoRegionInterpolator;
import com.amazon.avod.playback.sampling.SampleHolder;
import com.amazon.avod.playback.sampling.SampleType;
import com.amazon.avod.playbackclient.displaymode.LayoutUpdater;
import com.amazon.avod.threading.ScheduledExecutorBuilder;
import com.amazon.avod.util.CastUtils;
import com.amazon.avod.util.DLog;
import com.google.android.exoplayer2.video.DummySurface;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.common.collect.UnmodifiableIterator;
import com.visualon.OSMPUtils.voMimeTypes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
@TargetApi(21)
public class MediaCodecRenderer implements VideoRenderer, VideoRegionInterpolator.VideoRegionChangeListener {
    private final BufferingMediaSourceAdapterFactory mBufferFactory;
    private final TimeSpan mBufferedTimeRequiredToContinue;
    private final TimeSpan mBufferedTimeRequiredToStart;
    private long mCachedLastMediaTimeUs;
    private final MediaCodecDeviceCapabilityDetector mCapabilityDetector;
    private TimeSpan mContentBufferLookAheadSize;
    private final MediaSystemSharedContext mContext;
    private PlaybackZoomLevel mCurrentZoomLevel;
    private DrmCryptoSession mDrmCryptoSession;
    private final DrmSession mDrmFramework;
    private Surface mDummySurface;
    private final ScheduledExecutorService mExecutor;
    private final MediaFormatFactory mFormatFactory;
    private final boolean mIsAsnycModeEnabled;
    private final boolean mIsBackgroundAudioPlaybackSupported;
    private boolean mIsDisposed;
    private final boolean mIsDummyVideoSurfaceEnabled;
    private final boolean mIsDynamicVideoSurfaceResizeEnabled;
    private final boolean mIsFrameDropSimulationEnabled;
    private volatile boolean mIsHdrEnabled;
    private boolean mIsOutputDisplayConfiguredOnce;
    private final boolean mIsRendererBufferStatsEnabled;
    private volatile boolean mIsRunning;
    private boolean mIsTunneledPlayback;
    private volatile boolean mIsVideoPipelineInactive;
    private final boolean mIsVideoSurfaceHotSwapSupported;
    private boolean mIsVideoTrackRecreationEnabled;
    private volatile PlaybackException mLastRendererException;
    private final MediaClock mMediaClock;
    private final MediaPipelineContext mMediaPipelineContext;
    private final Object mMutex;
    private final int mNumSimulatedFrameDrops;
    private ViewGroup mParentViewGroup;
    private final MediaPipelineFactory mPipelineFactory;
    private final Map<SampleType, List<Future<Void>>> mPipelineTaskFuturesMap;
    private ImmutableList<PipelineTask> mPipelineTasks;
    private final RendererCapabilities mRendererCapabilities;
    private int mSimulatedDroppedFrameAccumulatedCount;
    private final ScheduledExecutorService mSurfaceCallbackExecutor;
    private final TimeSpan mSurfaceCreationTimeout;
    private AndroidVideoSurface mSurfaceCreator;
    private final AtomicReference<Surface> mSurfaceReference;
    private final int mTickIntervalMillis;
    private final Map<SampleType, MediaTrackData> mTracks;
    private final TunneledPlaybackEvaluator mTunneledPlaybackEvaluator;
    private final boolean mUseBackgroundThreadForSurfaceCallbacks;
    private final Runnable mVideoBufferConsumer;
    private ScheduledExecutorService mVideoBufferConsumerExecutor;
    private ScheduledFuture mVideoBufferConsumerFuture;
    private VideoRegion mVideoRegion;
    private VideoRegionUpdater mVideoRegionUpdater;
    private Long mVideoSampleDurationMillis;
    private final ZoomCalculator mZoomCalculator;

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public void addListener(RendererPerformanceEventListener rendererPerformanceEventListener) {
    }

    private static final class MediaTrackData {
        private final MediaSource mBuffer;
        private final MediaFormat mFormat;
        private final MediaPipeline mPipeline;

        public MediaTrackData(@Nonnull MediaFormat mediaFormat, @Nonnull MediaSource mediaSource, @Nonnull MediaPipeline mediaPipeline) {
            this.mBuffer = mediaSource;
            this.mFormat = mediaFormat;
            this.mPipeline = mediaPipeline;
        }
    }

    public MediaCodecRenderer(@Nonnull MediaSystemSharedContext mediaSystemSharedContext, @Nonnull DrmFramework drmFramework, @Nonnull MediaCodecDeviceCapabilityDetector mediaCodecDeviceCapabilityDetector) {
        AndroidVideoSurface androidVideoSurface = new AndroidVideoSurface(mediaSystemSharedContext.getAppContext());
        MediaClock mediaClock = new MediaClock();
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutorBuild = ScheduledExecutorBuilder.newBuilderFor(MediaCodecRenderer.class, new String[0]).withProfilerTraceLevel(Profiler.TraceLevel.VERBOSE).withFixedThreadPoolSize(MediaDefaultConfiguration.getInstance().isAsynchronousModeEnabled() ? 7 : 4).withThreadPriority(10).allowCoreThreadExpiry(100L, TimeUnit.MILLISECONDS).build();
        ZoomCalculator zoomCalculator = new ZoomCalculator(mediaSystemSharedContext.getDeviceConfiguration());
        DrmSession drmSession = (DrmSession) CastUtils.castTo(drmFramework, DrmSession.class);
        MediaFormatFactory mediaFormatFactory = new MediaFormatFactory(mediaCodecDeviceCapabilityDetector);
        BufferingMediaSourceAdapterFactory bufferingMediaSourceAdapterFactory = BufferingMediaSourceAdapterFactory.getInstance();
        MediaPipelineFactory mediaPipelineFactory = MediaPipelineFactory.getInstance();
        PlaybackConfig playbackConfig = PlaybackConfig.INSTANCE;
        this(androidVideoSurface, mediaClock, scheduledThreadPoolExecutorBuild, zoomCalculator, drmSession, mediaFormatFactory, bufferingMediaSourceAdapterFactory, mediaPipelineFactory, playbackConfig.getRequiredNativeBufferForStartPlayback(), playbackConfig.getRequiredNativeBufferForContinuePlayback(), playbackConfig.isRendererBufferStatsEnabled(), mediaSystemSharedContext, new MediaPipelineContext(), MediaDefaultConfiguration.getInstance(), playbackConfig.getPlayerSurfaceTerminateTimeout(), new TunneledPlaybackEvaluator(mediaCodecDeviceCapabilityDetector, DeviceResources.getInstance()), mediaCodecDeviceCapabilityDetector, MediaDefaultConfiguration.getInstance().isFrameDropSimulationEnabled(), MediaDefaultConfiguration.getInstance().getNumSimulatedFrameDrops(), MediaDefaultConfiguration.getInstance().isVideoSurfaceHotSwapSupported(), MediaDefaultConfiguration.getInstance().isBackgroundAudioPlaybackSupported(), MediaDefaultConfiguration.getInstance().isDummySurfaceEnabled(), MediaDefaultConfiguration.getInstance().useBackgroundThreadForSurfaceCallbacks());
    }

    MediaCodecRenderer(@Nonnull AndroidVideoSurface androidVideoSurface, @Nonnull MediaClock mediaClock, @Nonnull ScheduledExecutorService scheduledExecutorService, @Nonnull ZoomCalculator zoomCalculator, @Nonnull DrmSession drmSession, @Nonnull MediaFormatFactory mediaFormatFactory, @Nonnull BufferingMediaSourceAdapterFactory bufferingMediaSourceAdapterFactory, @Nonnull MediaPipelineFactory mediaPipelineFactory, @Nonnull TimeSpan timeSpan, @Nonnull TimeSpan timeSpan2, boolean z, @Nonnull MediaSystemSharedContext mediaSystemSharedContext, @Nonnull MediaPipelineContext mediaPipelineContext, @Nonnull MediaDefaultConfiguration mediaDefaultConfiguration, @Nonnull TimeSpan timeSpan3, @Nonnull TunneledPlaybackEvaluator tunneledPlaybackEvaluator, @Nonnull MediaCodecDeviceCapabilityDetector mediaCodecDeviceCapabilityDetector, boolean z2, int i, boolean z3, boolean z4, boolean z5, boolean z6) {
        this.mTracks = Maps.newEnumMap(SampleType.class);
        this.mContentBufferLookAheadSize = TimeSpan.MAX_VALUE;
        this.mDrmCryptoSession = null;
        this.mSimulatedDroppedFrameAccumulatedCount = 0;
        this.mPipelineTaskFuturesMap = Maps.newEnumMap(SampleType.class);
        this.mMutex = new Object();
        this.mCachedLastMediaTimeUs = 0L;
        this.mIsRunning = false;
        this.mIsHdrEnabled = false;
        this.mIsDisposed = false;
        this.mIsVideoPipelineInactive = false;
        this.mVideoSampleDurationMillis = null;
        this.mDummySurface = null;
        this.mIsOutputDisplayConfiguredOnce = false;
        this.mSurfaceCreator = (AndroidVideoSurface) Preconditions.checkNotNull(androidVideoSurface, "surfaceCreator");
        this.mMediaClock = (MediaClock) Preconditions.checkNotNull(mediaClock, "mediaClock");
        this.mExecutor = (ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService, "executor");
        ZoomCalculator zoomCalculator2 = (ZoomCalculator) Preconditions.checkNotNull(zoomCalculator, "zoomCalculator");
        this.mZoomCalculator = zoomCalculator2;
        this.mDrmFramework = (DrmSession) Preconditions.checkNotNull(drmSession, "drmFramework");
        this.mFormatFactory = (MediaFormatFactory) Preconditions.checkNotNull(mediaFormatFactory, "mediaFormatFactory");
        this.mBufferFactory = (BufferingMediaSourceAdapterFactory) Preconditions.checkNotNull(bufferingMediaSourceAdapterFactory, "bufferFactory");
        this.mPipelineFactory = (MediaPipelineFactory) Preconditions.checkNotNull(mediaPipelineFactory, "pipelineFactory");
        this.mBufferedTimeRequiredToStart = (TimeSpan) Preconditions.checkNotNull(timeSpan, "timeRequiredToStart");
        this.mBufferedTimeRequiredToContinue = (TimeSpan) Preconditions.checkNotNull(timeSpan2, "timeRequiredToContinue");
        this.mIsRendererBufferStatsEnabled = z;
        this.mContext = (MediaSystemSharedContext) Preconditions.checkNotNull(mediaSystemSharedContext, "context");
        this.mMediaPipelineContext = (MediaPipelineContext) Preconditions.checkNotNull(mediaPipelineContext, "mediaPipelineContext");
        Preconditions.checkNotNull(mediaDefaultConfiguration, "mediaDefaultConfiguration");
        Preconditions.checkState(mediaDefaultConfiguration.getTickIntervalMillis() > 0, "Tick interval cannot be non positive");
        this.mTickIntervalMillis = mediaDefaultConfiguration.getTickIntervalMillis();
        this.mIsAsnycModeEnabled = mediaDefaultConfiguration.isAsynchronousModeEnabled();
        this.mVideoRegion = zoomCalculator2.getVideoRegionForScreenSize();
        this.mCurrentZoomLevel = PlaybackZoomLevel.NATIVE;
        this.mSurfaceCreationTimeout = (TimeSpan) Preconditions.checkNotNull(timeSpan3, "surfaceCreationTimeout");
        this.mIsDynamicVideoSurfaceResizeEnabled = mediaDefaultConfiguration.isDynamicVideoSurfaceResizeEnabled();
        this.mTunneledPlaybackEvaluator = (TunneledPlaybackEvaluator) Preconditions.checkNotNull(tunneledPlaybackEvaluator, "tunneledPlaybackEvaluator");
        this.mCapabilityDetector = (MediaCodecDeviceCapabilityDetector) Preconditions.checkNotNull(mediaCodecDeviceCapabilityDetector, "capabilityDetector");
        this.mIsFrameDropSimulationEnabled = z2;
        this.mNumSimulatedFrameDrops = i;
        this.mIsVideoSurfaceHotSwapSupported = z3;
        this.mIsBackgroundAudioPlaybackSupported = z4;
        this.mIsDummyVideoSurfaceEnabled = z5;
        this.mUseBackgroundThreadForSurfaceCallbacks = z6;
        this.mRendererCapabilities = new RendererCapabilities(true, z3, z4, true);
        this.mSurfaceReference = new AtomicReference<>(null);
        this.mSurfaceCallbackExecutor = ScheduledExecutorBuilder.newBuilderFor(SurfaceCreationListener.class, new String[0]).withProfilerTraceLevel(Profiler.TraceLevel.VERBOSE).withFixedThreadPoolSize(1).allowCoreThreadExpiry(1L, TimeUnit.SECONDS).build();
        this.mVideoBufferConsumer = new Runnable() { // from class: com.amazon.avod.playback.renderer.mediacodec.MediaCodecRenderer.1
            @Override // java.lang.Runnable
            public void run() {
                if (MediaCodecRenderer.this.mIsDisposed) {
                    MediaCodecRenderer.this.mVideoBufferConsumerExecutor.shutdown();
                    MediaCodecRenderer.this.mVideoBufferConsumerExecutor = null;
                    return;
                }
                MediaTrackData mediaTrackData = (MediaTrackData) MediaCodecRenderer.this.mTracks.get(SampleType.VIDEO_SAMPLE);
                if (MediaCodecRenderer.this.mIsVideoPipelineInactive && MediaCodecRenderer.this.mIsRunning && mediaTrackData != null && mediaTrackData.mBuffer.hasNext()) {
                    mediaTrackData.mBuffer.advance();
                }
            }
        };
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public int initialize(String str, String str2, boolean z) {
        this.mLastRendererException = null;
        return 1;
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public int configureCodecs(@Nonnull VideoConfig videoConfig, @Nonnull AudioConfig audioConfig, boolean z, @Nonnull TimeSpan timeSpan) throws PlaybackException {
        this.mContentBufferLookAheadSize = (TimeSpan) Preconditions.checkNotNull(timeSpan, "mContentBufferLookAheadSize");
        this.mIsHdrEnabled = videoConfig.isHdrEnabled();
        MediaFormat mediaFormatNewVideoFormat = this.mFormatFactory.newVideoFormat(videoConfig);
        MediaFormat mediaFormatNewAudioFormat = this.mFormatFactory.newAudioFormat(audioConfig);
        boolean zEvaluateTunneledPlaybackCapability = this.mTunneledPlaybackEvaluator.evaluateTunneledPlaybackCapability(videoConfig, mediaFormatNewVideoFormat);
        this.mIsTunneledPlayback = zEvaluateTunneledPlaybackCapability;
        boolean zShouldSkipSendingAudioTrackHeaderAsStandaloneSample = this.mTunneledPlaybackEvaluator.shouldSkipSendingAudioTrackHeaderAsStandaloneSample(zEvaluateTunneledPlaybackCapability, mediaFormatNewAudioFormat);
        MediaSource mediaSourceNewMediaSource = this.mBufferFactory.newMediaSource(mediaFormatNewVideoFormat, false, new VideoTrackAdapter(videoConfig.getFourCC()));
        Integer numGenerateAudioSessionId = this.mIsTunneledPlayback ? generateAudioSessionId() : null;
        MediaPipeline mediaPipelineNewMediaPipeline = this.mPipelineFactory.newMediaPipeline(mediaSourceNewMediaSource, this.mContext, this.mMediaClock, this.mMediaPipelineContext, this.mSurfaceCreator, false, videoConfig, numGenerateAudioSessionId, this.mExecutor, this.mIsAsnycModeEnabled, this.mCapabilityDetector);
        this.mTracks.put(SampleType.VIDEO_SAMPLE, new MediaTrackData(mediaFormatNewVideoFormat, mediaSourceNewMediaSource, mediaPipelineNewMediaPipeline));
        MediaSource mediaSourceNewMediaSource2 = this.mBufferFactory.newMediaSource(mediaFormatNewAudioFormat, zShouldSkipSendingAudioTrackHeaderAsStandaloneSample, new AudioTrackAdapter());
        MediaPipeline mediaPipelineNewMediaPipeline2 = this.mPipelineFactory.newMediaPipeline(mediaSourceNewMediaSource2, this.mContext, this.mMediaClock, this.mMediaPipelineContext, this.mSurfaceCreator, true, videoConfig, numGenerateAudioSessionId, this.mExecutor, this.mIsAsnycModeEnabled, this.mCapabilityDetector);
        this.mTracks.put(SampleType.AUDIO_SAMPLE, new MediaTrackData(mediaFormatNewAudioFormat, mediaSourceNewMediaSource2, mediaPipelineNewMediaPipeline2));
        for (SampleType sampleType : SampleType.values()) {
            this.mPipelineTaskFuturesMap.put(sampleType, new ArrayList());
        }
        if (arePipelinesSynchronous()) {
            this.mPipelineTasks = constructMediaPipelineTasks(mediaPipelineNewMediaPipeline, mediaPipelineNewMediaPipeline2, this.mIsTunneledPlayback);
        }
        this.mZoomCalculator.setVideoResolution(videoConfig.getVideoResolution());
        return 1;
    }

    @Nullable
    private Integer generateAudioSessionId() {
        AudioManager audioManager = (AudioManager) this.mContext.getAppContext().getSystemService(voMimeTypes.VOBASE_TYPE_AUDIO);
        if (audioManager != null) {
            int iGenerateAudioSessionId = audioManager.generateAudioSessionId();
            if (iGenerateAudioSessionId != -1) {
                Integer numValueOf = Integer.valueOf(iGenerateAudioSessionId);
                DLog.logf("Configuring renderer for tunneled playback with audioSessionId %d", numValueOf);
                return numValueOf;
            }
            DLog.warnf("Configuring renderer for non-tunneled playback, failed to generate audio session id");
            return null;
        }
        DLog.warnf("Configuring renderer for non-tunneled playback,failed to get audio manager service");
        return null;
    }

    ImmutableList<PipelineTask> constructMediaPipelineTasks(@Nonnull MediaPipeline mediaPipeline, @Nonnull MediaPipeline mediaPipeline2, boolean z) {
        ImmutableList.Builder builder = new ImmutableList.Builder();
        PipelineTaskType pipelineTaskType = PipelineTaskType.DRAIN;
        builder.add(new PipelineTask(mediaPipeline2, pipelineTaskType));
        PipelineTaskType pipelineTaskType2 = PipelineTaskType.FEED;
        builder.add(new PipelineTask(mediaPipeline2, pipelineTaskType2));
        builder.add(new PipelineTask(mediaPipeline, pipelineTaskType2));
        if (z) {
            DLog.logf("TunnelMode has 3 pipelineTasks: audioFeed, audioDrain, videoFeed");
        } else {
            builder.add(new PipelineTask(mediaPipeline, pipelineTaskType));
        }
        return builder.build();
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public void setHdrStatus(boolean z) {
        MediaTrackData mediaTrackData = this.mTracks.get(SampleType.VIDEO_SAMPLE);
        if (!this.mIsHdrEnabled || mediaTrackData == null) {
            return;
        }
        mediaTrackData.mPipeline.setHdrStatus(z);
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    @Nonnull
    public String getCodecInfo() {
        JSONArray jSONArray = new JSONArray();
        try {
            for (MediaTrackData mediaTrackData : this.mTracks.values()) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(mediaTrackData.mPipeline.isAudioPipeline() ? voMimeTypes.VOBASE_TYPE_AUDIO : voMimeTypes.VOBASE_TYPE_VIDEO, mediaTrackData.mPipeline.getCodecInfo());
                jSONArray.put(jSONObject);
            }
            jSONArray.put(getDrmInfo());
        } catch (JSONException unused) {
            DLog.warnf("Unable to format JSON array at MediaCodecRenderer.getCodecInfo");
        }
        return jSONArray.toString();
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    @Nonnull
    public String getRendererDebugInfo() {
        return this.mMediaPipelineContext.getRendererDebugTracker().getRendererDebugInfo();
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public void setVolume(float f) {
        Preconditions.checkArgument(f >= 0.0f, "volume gain cannot be negative.");
        MediaTrackData mediaTrackData = this.mTracks.get(SampleType.AUDIO_SAMPLE);
        if (mediaTrackData != null) {
            mediaTrackData.mPipeline.setVolume(f);
        }
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public void setPlaybackSpeed(float f) {
        Preconditions.checkArgument(f > 0.0f, "Playback speed must be positive.");
        Iterator<MediaTrackData> it = this.mTracks.values().iterator();
        while (it.hasNext()) {
            it.next().mPipeline.setPlaybackSpeed(f);
        }
    }

    @Nonnull
    private JSONObject getDrmInfo() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(this.mDrmFramework.getCurrentDrmScheme().name(), this.mDrmFramework.getDrmSecurityLevel().name());
            jSONObject.put("drmInfo", jSONObject2);
        } catch (JSONException unused) {
            DLog.warnf("Unable to format JSON at MediaCodecRenderer.getDrmInfo");
        }
        return jSONObject;
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public void configureDecryption(byte[] bArr, @Nullable byte[] bArr2, @Nullable byte[] bArr3, @Nullable String str, @Nonnull DrmScheme drmScheme) throws PlaybackException {
        Preconditions.checkNotNull(bArr, "encryptionInitData");
        try {
            this.mDrmCryptoSession = this.mDrmFramework.openDrmCryptoSession(bArr, bArr2, bArr3, str, drmScheme);
        } catch (DrmLicensingException e) {
            throw new PlaybackException(e);
        }
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public void configureOutputDisplay(@Nonnull VideoRenderingSettings videoRenderingSettings) throws PlaybackException {
        synchronized (this.mMutex) {
            Preconditions.checkNotNull(videoRenderingSettings, "settings");
            this.mIsVideoTrackRecreationEnabled = videoRenderingSettings.isVideoTrackRecreationEnabled();
            createDummySurface();
            SurfaceHandlingMode surfaceHandlingMode = (SurfaceHandlingMode) Preconditions.checkNotNull(videoRenderingSettings.getSurfaceHandlingMode(), "mode");
            boolean zCleanupExistingSurface = cleanupExistingSurface(surfaceHandlingMode);
            int i = AnonymousClass2.$SwitchMap$com$amazon$avod$media$playback$SurfaceHandlingMode[surfaceHandlingMode.ordinal()];
            if (i != 1) {
                if (i == 2) {
                    Surface surface = (Surface) Preconditions.checkNotNull(videoRenderingSettings.getSurface(), "Nonnull surface must be provided in ATTACH mode");
                    if (!surface.isValid()) {
                        throw new PlaybackException(PlaybackException.PlaybackError.RENDERER_INITIALIZE_INVALID_SURFACE, "MediaCodecRenderer: Provided surface is not valid!");
                    }
                    this.mSurfaceReference.set(surface);
                    if (zCleanupExistingSurface || this.mIsOutputDisplayConfiguredOnce) {
                        restartVideoPipelineWithNewSurface(this.mSurfaceReference.get());
                    }
                } else if (i == 3) {
                    createNewSurface(videoRenderingSettings);
                    if (zCleanupExistingSurface || this.mIsOutputDisplayConfiguredOnce) {
                        restartVideoPipelineWithNewSurface(this.mSurfaceReference.get());
                    }
                } else {
                    throw new PlaybackException(String.format(Locale.US, "Unknown surface handling mode %s", surfaceHandlingMode));
                }
            }
            this.mIsOutputDisplayConfiguredOnce = true;
        }
    }

    /* JADX INFO: renamed from: com.amazon.avod.playback.renderer.mediacodec.MediaCodecRenderer$2, reason: invalid class name */
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
                $SwitchMap$com$amazon$avod$media$playback$SurfaceHandlingMode[SurfaceHandlingMode.ATTACH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$playback$SurfaceHandlingMode[SurfaceHandlingMode.CREATE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    void resetOutputDisplayConfigured() {
        this.mIsOutputDisplayConfiguredOnce = false;
        this.mSurfaceReference.set(null);
    }

    private void createDummySurface() {
        synchronized (this.mMutex) {
            if (this.mIsDummyVideoSurfaceEnabled && this.mDummySurface == null) {
                try {
                    Stopwatch stopwatchCreateStarted = Stopwatch.createStarted();
                    DummySurface dummySurfaceNewInstanceV17 = DummySurface.newInstanceV17(this.mContext.getAppContext(), this.mDrmFramework.getDrmSecurityLevel() == DrmSecurityLevel.LEVEL_1);
                    this.mDummySurface = dummySurfaceNewInstanceV17;
                    DLog.logf("MediaCodecRenderer created dummy surface %s took %s ms", dummySurfaceNewInstanceV17, Long.valueOf(stopwatchCreateStarted.elapsed(TimeUnit.MILLISECONDS)));
                } catch (Exception e) {
                    DLog.errorf("MediaCodecRenderer failed to create dummy surface %s", e);
                }
            }
        }
    }

    private boolean cleanupExistingSurface(@Nonnull SurfaceHandlingMode surfaceHandlingMode) {
        synchronized (this.mMutex) {
            if (surfaceHandlingMode != SurfaceHandlingMode.AUDIO_ONLY && this.mSurfaceReference.get() == null) {
                return false;
            }
            stopVideoPipeline();
            this.mSurfaceCreator.unregisterListener();
            this.mSurfaceCreator.destroy();
            this.mSurfaceReference.set(null);
            this.mSurfaceCreator = new AndroidVideoSurface(this.mContext.getAppContext());
            return true;
        }
    }

    private void createNewSurface(@Nonnull VideoRenderingSettings videoRenderingSettings) throws PlaybackException {
        synchronized (this.mMutex) {
            Preconditions.checkState(videoRenderingSettings.getSurfaceHandlingMode() == SurfaceHandlingMode.CREATE, "Only CREATE mode can be be used to create a surface");
            ViewGroup viewGroup = (ViewGroup) Preconditions.checkNotNull(videoRenderingSettings.getParentView(), "Nonnull parentView must be provided in CREATE mode");
            this.mParentViewGroup = viewGroup;
            if (this.mIsDynamicVideoSurfaceResizeEnabled) {
                this.mZoomCalculator.setParentLayoutOverride(viewGroup);
                VideoRegionUpdater videoRegionUpdater = new VideoRegionUpdater();
                this.mVideoRegionUpdater = videoRegionUpdater;
                this.mParentViewGroup.addOnLayoutChangeListener(videoRegionUpdater);
            }
            CountDownLatch countDownLatch = new CountDownLatch(1);
            this.mSurfaceCreator.createAsync(this.mParentViewGroup, new SurfaceCreationListener(countDownLatch, this.mSurfaceReference), videoRenderingSettings.isMediaOverlay(), videoRenderingSettings.getSystemUIFlags());
            try {
                if (!countDownLatch.await(this.mSurfaceCreationTimeout.getTotalMilliseconds(), TimeUnit.MILLISECONDS)) {
                    throw new PlaybackException(PlaybackException.PlaybackError.RENDERER_INITIALIZE_TIMEOUT, "MediaCodecRenderer: Creating SurfaceView timed out!");
                }
                Surface surface = this.mSurfaceReference.get();
                if (surface == null) {
                    throw new PlaybackException(PlaybackException.PlaybackError.RENDERER_INITIALIZE_FAILED, String.format("MediaCodecRenderer: Failed to create surface within %s seconds!", this.mSurfaceCreationTimeout));
                }
                if (!surface.isValid()) {
                    throw new PlaybackException(PlaybackException.PlaybackError.RENDERER_INITIALIZE_INVALID_SURFACE, "MediaCodecRenderer: Surface is not valid!");
                }
                PlaybackZoomLevel playbackZoomLevel = videoRenderingSettings.getPlaybackZoomLevel();
                this.mCurrentZoomLevel = playbackZoomLevel;
                onVideoRegionChanged(this.mZoomCalculator.getVideoRegion(playbackZoomLevel));
            } catch (InterruptedException unused) {
                throw new PlaybackException(PlaybackException.PlaybackError.RENDERER_INITIALIZE_FAILED, "MediaCodecRenderer: Create SurfaceView was interrupted!");
            }
        }
    }

    private class SurfaceCreationListener implements AndroidVideoSurface.CreationListener {
        private final CountDownLatch mSurfaceLatch;
        private final AtomicReference<Surface> mSurfaceReference;

        private SurfaceCreationListener(@Nonnull CountDownLatch countDownLatch, @Nonnull AtomicReference<Surface> atomicReference) {
            this.mSurfaceLatch = (CountDownLatch) Preconditions.checkNotNull(countDownLatch, "surfaceLatch");
            this.mSurfaceReference = (AtomicReference) Preconditions.checkNotNull(atomicReference, "surfaceReference");
        }

        @Override // com.amazon.avod.media.playback.android.AndroidVideoSurface.CreationListener
        public void onCreated(@Nonnull SurfaceHolder surfaceHolder) {
            if (this.mSurfaceLatch.getCount() == 0 && !MediaCodecRenderer.this.mIsDisposed && MediaCodecRenderer.this.mIsVideoTrackRecreationEnabled && MediaCodecRenderer.this.mIsVideoSurfaceHotSwapSupported) {
                DLog.logf("SurfaceCreationListener video surface recreated mid-flight %s", surfaceHolder);
                MediaCodecRenderer.this.restartVideoPipelineWithNewSurface(surfaceHolder.getSurface());
            } else {
                DLog.logf("SurfaceCreationListener video surface created %s", surfaceHolder);
                this.mSurfaceReference.set(surfaceHolder.getSurface());
                this.mSurfaceLatch.countDown();
            }
        }

        @Override // com.amazon.avod.media.playback.android.AndroidVideoSurface.CreationListener
        public void onDestroyed(@Nonnull SurfaceHolder surfaceHolder) {
            if (this.mSurfaceLatch.getCount() == 0 && !MediaCodecRenderer.this.mIsDisposed && MediaCodecRenderer.this.mIsVideoTrackRecreationEnabled && MediaCodecRenderer.this.mIsVideoSurfaceHotSwapSupported) {
                DLog.logf("SurfaceCreationListener video surface destroyed mid-flight %s", surfaceHolder);
                MediaCodecRenderer.this.stopVideoPipeline();
            } else {
                DLog.logf("SurfaceCreationListener video surface destroyed %s", surfaceHolder);
                this.mSurfaceReference.set(null);
                this.mSurfaceLatch.countDown();
            }
        }
    }

    public void restartVideoPipelineWithNewSurface(@Nonnull Surface surface) {
        if (this.mIsVideoTrackRecreationEnabled && this.mIsVideoSurfaceHotSwapSupported) {
            RestartVideoPipelineTask restartVideoPipelineTask = new RestartVideoPipelineTask(surface);
            if (this.mUseBackgroundThreadForSurfaceCallbacks) {
                this.mSurfaceCallbackExecutor.execute(restartVideoPipelineTask);
            } else {
                restartVideoPipelineTask.run();
            }
        }
    }

    private final class RestartVideoPipelineTask implements Runnable {
        private final Surface mSurface;

        private RestartVideoPipelineTask(@Nonnull Surface surface) {
            this.mSurface = (Surface) Preconditions.checkNotNull(surface, "surface");
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (MediaCodecRenderer.this.mMutex) {
                if (!MediaCodecRenderer.this.mIsDisposed && this.mSurface.isValid()) {
                    MediaTrackData mediaTrackData = (MediaTrackData) MediaCodecRenderer.this.mTracks.get(SampleType.VIDEO_SAMPLE);
                    if (MediaCodecRenderer.this.mDummySurface != null) {
                        try {
                            mediaTrackData.mPipeline.setOutputSurface(this.mSurface);
                            if (mediaTrackData.mPipeline.canConfigure()) {
                                mediaTrackData.mPipeline.notifySurfaceRecreated(mediaTrackData.mFormat, this.mSurface, MediaCodecRenderer.this.mDrmCryptoSession);
                            }
                        } catch (PlaybackException e) {
                            MediaCodecRenderer.this.mLastRendererException = e;
                        }
                    }
                    try {
                        mediaTrackData.mPipeline.notifySurfaceRecreated(mediaTrackData.mFormat, this.mSurface, MediaCodecRenderer.this.mDrmCryptoSession);
                        long audioTrackRestartPositionUs = MediaCodecRenderer.this.getAudioTrackRestartPositionUs();
                        MediaCodecRenderer.this.mLastRendererException = new PlaybackException(PlaybackException.PlaybackError.RENDERER_VIDEO_SURFACE_RECREATED, String.format("Video track recreated, seeking to the current audio track position %s Us", Long.valueOf(audioTrackRestartPositionUs)), audioTrackRestartPositionUs);
                        MediaCodecRenderer.this.mIsVideoPipelineInactive = false;
                    } catch (PlaybackException e2) {
                        MediaCodecRenderer.this.mLastRendererException = e2;
                    }
                    if (MediaCodecRenderer.this.mVideoBufferConsumerFuture != null) {
                        MediaCodecRenderer.this.mVideoBufferConsumerFuture.cancel(true);
                    }
                }
            }
        }
    }

    public void stopVideoPipeline() {
        if (this.mIsVideoTrackRecreationEnabled && this.mIsVideoSurfaceHotSwapSupported) {
            StopVideoPipelineTask stopVideoPipelineTask = new StopVideoPipelineTask();
            if (this.mUseBackgroundThreadForSurfaceCallbacks) {
                this.mSurfaceCallbackExecutor.execute(stopVideoPipelineTask);
            } else {
                stopVideoPipelineTask.run();
            }
        }
    }

    private final class StopVideoPipelineTask implements Runnable {
        private StopVideoPipelineTask() {
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (MediaCodecRenderer.this.mMutex) {
                if (MediaCodecRenderer.this.mIsDisposed) {
                    return;
                }
                Map map = MediaCodecRenderer.this.mTracks;
                SampleType sampleType = SampleType.VIDEO_SAMPLE;
                MediaTrackData mediaTrackData = (MediaTrackData) map.get(sampleType);
                if (MediaCodecRenderer.this.mDummySurface != null) {
                    try {
                        mediaTrackData.mPipeline.setOutputSurface(MediaCodecRenderer.this.mDummySurface);
                    } catch (PlaybackException e) {
                        MediaCodecRenderer.this.mLastRendererException = e;
                    }
                } else {
                    MediaCodecRenderer.this.mIsVideoPipelineInactive = true;
                    if (MediaCodecRenderer.this.arePipelinesSynchronous()) {
                        try {
                            Iterator it = ((List) MediaCodecRenderer.this.mPipelineTaskFuturesMap.get(sampleType)).iterator();
                            while (it.hasNext()) {
                                ((Future) it.next()).get();
                            }
                        } catch (InterruptedException | ExecutionException e2) {
                            DLog.warnf("Unexpected exception in onDestroyed() waiting for pipeline task to finish: %s", e2);
                        }
                    }
                    mediaTrackData.mPipeline.notifySurfaceDestroyed();
                    if (MediaCodecRenderer.this.mIsBackgroundAudioPlaybackSupported) {
                        if (MediaCodecRenderer.this.mVideoBufferConsumerExecutor == null) {
                            MediaCodecRenderer.this.mVideoBufferConsumerExecutor = ScheduledExecutorBuilder.newBuilderFor("VideoBufferConsumer", new String[0]).withProfilerTraceLevel(Profiler.TraceLevel.VERBOSE).withFixedThreadPoolSize(1).allowCoreThreadExpiry(1L, TimeUnit.SECONDS).build();
                        }
                        MediaCodecRenderer mediaCodecRenderer = MediaCodecRenderer.this;
                        mediaCodecRenderer.mVideoBufferConsumerFuture = mediaCodecRenderer.mVideoBufferConsumerExecutor.scheduleAtFixedRate(MediaCodecRenderer.this.mVideoBufferConsumer, 0L, MediaCodecRenderer.this.mVideoSampleDurationMillis.longValue(), TimeUnit.MILLISECONDS);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long getAudioTrackRestartPositionUs() {
        synchronized (this.mMutex) {
            if (this.mIsRunning) {
                return this.mMediaClock.getCurrentMediaTimeUs();
            }
            MediaTrackData mediaTrackData = this.mTracks.get(SampleType.AUDIO_SAMPLE);
            if (mediaTrackData != null && !mediaTrackData.mPipeline.isUnconfigured()) {
                return mediaTrackData.mPipeline.getNextBufferedPresentationTimeUs();
            }
            return this.mCachedLastMediaTimeUs;
        }
    }

    private class VideoRegionUpdater extends LayoutUpdater {
        private VideoRegionUpdater() {
        }

        @Override // com.amazon.avod.playbackclient.displaymode.LayoutUpdater
        protected void update() {
            MediaCodecRenderer.this.onVideoRegionChanged(MediaCodecRenderer.this.mZoomCalculator.getVideoRegion(MediaCodecRenderer.this.mCurrentZoomLevel));
        }
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public void resume() throws PlaybackException {
        Surface surface;
        synchronized (this.mMutex) {
            if (this.mIsRunning) {
                return;
            }
            MediaTrackData mediaTrackData = this.mTracks.get(SampleType.AUDIO_SAMPLE);
            if (mediaTrackData != null) {
                mediaTrackData.mPipeline.configure(mediaTrackData.mFormat, this.mSurfaceReference.get(), this.mDrmCryptoSession);
            }
            MediaTrackData mediaTrackData2 = this.mTracks.get(SampleType.VIDEO_SAMPLE);
            if (mediaTrackData2 != null && (surface = this.mSurfaceReference.get()) != null && surface.isValid()) {
                mediaTrackData2.mPipeline.configure(mediaTrackData2.mFormat, this.mSurfaceReference.get(), this.mDrmCryptoSession);
            }
            long jMin = Long.MAX_VALUE;
            if (mediaTrackData != null) {
                TimeSource timeSource = mediaTrackData.mPipeline.getTimeSource();
                if (timeSource != null && !this.mMediaClock.isStarted()) {
                    this.mMediaClock.setTimeSource(timeSource);
                }
                jMin = mediaTrackData.mPipeline.getNextBufferedPresentationTimeUs();
            } else {
                Iterator<MediaTrackData> it = this.mTracks.values().iterator();
                while (it.hasNext()) {
                    jMin = Math.min(jMin, it.next().mPipeline.getNextBufferedPresentationTimeUs());
                }
            }
            if (!this.mMediaClock.isStarted()) {
                this.mMediaClock.start(jMin);
            }
            if (mediaTrackData != null) {
                mediaTrackData.mPipeline.start();
            }
            if (mediaTrackData2 != null) {
                mediaTrackData2.mPipeline.start();
            }
            this.mIsRunning = true;
            if (arePipelinesSynchronous()) {
                Iterator<List<Future<Void>>> it2 = this.mPipelineTaskFuturesMap.values().iterator();
                while (it2.hasNext()) {
                    it2.next().clear();
                }
                UnmodifiableIterator<PipelineTask> it3 = this.mPipelineTasks.iterator();
                while (it3.hasNext()) {
                    PipelineTask next = it3.next();
                    this.mPipelineTaskFuturesMap.get(next.getSampleType()).add(this.mExecutor.submit(next));
                }
            }
        }
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public void pause() {
        synchronized (this.mMutex) {
            this.mCachedLastMediaTimeUs = this.mMediaClock.pause();
            this.mIsRunning = false;
            if (arePipelinesSynchronous()) {
                try {
                    Iterator<List<Future<Void>>> it = this.mPipelineTaskFuturesMap.values().iterator();
                    while (it.hasNext()) {
                        Iterator<Future<Void>> it2 = it.next().iterator();
                        while (it2.hasNext()) {
                            it2.next().get();
                        }
                    }
                } catch (InterruptedException | ExecutionException e) {
                    DLog.warnf("Unexpected exception waiting for pipeline task to finish: %s", e);
                }
            }
            Iterator<MediaTrackData> it3 = this.mTracks.values().iterator();
            while (it3.hasNext()) {
                it3.next().mPipeline.stop();
            }
        }
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public boolean haveStreamsReachedEnd() {
        return this.mTracks.get(SampleType.AUDIO_SAMPLE).mPipeline.hasRenderedEndOfStream() && (this.mIsTunneledPlayback || this.mIsVideoPipelineInactive || this.mTracks.get(SampleType.VIDEO_SAMPLE).mPipeline.hasRenderedEndOfStream());
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public void flush() throws PlaybackException {
        synchronized (this.mMutex) {
            this.mMediaClock.stop();
            for (MediaTrackData mediaTrackData : this.mTracks.values()) {
                mediaTrackData.mPipeline.flush();
                mediaTrackData.mBuffer.flush();
            }
        }
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public long getLastRenderedTimeStampInNanos() {
        return TimeUnit.MICROSECONDS.toNanos(this.mIsRunning ? this.mMediaClock.getCurrentMediaTimeUs() : this.mCachedLastMediaTimeUs);
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public void dispose(boolean z) {
        VideoRegionUpdater videoRegionUpdater;
        synchronized (this.mMutex) {
            pause();
            for (MediaTrackData mediaTrackData : this.mTracks.values()) {
                mediaTrackData.mPipeline.release();
                mediaTrackData.mBuffer.dispose();
            }
            this.mTracks.clear();
            DrmCryptoSession drmCryptoSession = this.mDrmCryptoSession;
            if (drmCryptoSession != null) {
                drmCryptoSession.release();
            }
            ViewGroup viewGroup = this.mParentViewGroup;
            if (viewGroup != null && (videoRegionUpdater = this.mVideoRegionUpdater) != null) {
                viewGroup.removeOnLayoutChangeListener(videoRegionUpdater);
            }
            this.mIsDisposed = true;
            this.mSurfaceCreator.destroy();
            Surface surface = this.mDummySurface;
            if (surface != null) {
                DLog.logf("MediaCodecRenderer release dummy surface %s", surface);
                this.mDummySurface.release();
            }
            this.mExecutor.shutdown();
        }
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public void submitSample(SampleHolder sampleHolder) throws PlaybackException {
        MediaTrackData mediaTrackData = this.mTracks.get(sampleHolder.getType());
        if (mediaTrackData == null) {
            return;
        }
        mediaTrackData.mBuffer.submitSample(sampleHolder);
        if (sampleHolder.getType() == SampleType.VIDEO_SAMPLE && this.mVideoSampleDurationMillis == null) {
            this.mVideoSampleDurationMillis = Long.valueOf(TimeUnit.NANOSECONDS.toMillis(sampleHolder.getDuration()));
        }
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public long getLastTimeStampPassedToRendererInNanos(SampleType sampleType) {
        MediaTrackData mediaTrackData = this.mTracks.get(sampleType);
        if (mediaTrackData == null) {
            return 0L;
        }
        return Math.max(mediaTrackData.mBuffer.getLastSubmittedTimeInNanos(), 0L);
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    @Nullable
    public SampleType getRequiredNextSampleType() {
        SampleType sampleType = SampleType.AUDIO_SAMPLE;
        boolean zHasSpaceForMoreSamples = hasSpaceForMoreSamples(sampleType);
        SampleType sampleType2 = SampleType.VIDEO_SAMPLE;
        boolean zHasSpaceForMoreSamples2 = hasSpaceForMoreSamples(sampleType2);
        if (zHasSpaceForMoreSamples || zHasSpaceForMoreSamples2) {
            return !zHasSpaceForMoreSamples ? sampleType2 : (zHasSpaceForMoreSamples2 && getLastTimeStampPassedToRendererInNanos(sampleType) > getLastTimeStampPassedToRendererInNanos(sampleType2)) ? sampleType2 : sampleType;
        }
        return null;
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public boolean needsMoreSamplesToStartPlaying() {
        return hasLessThanNeeded(Math.min(this.mBufferedTimeRequiredToStart.getTotalNanoSeconds(), this.mContentBufferLookAheadSize.getTotalNanoSeconds()));
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public boolean needsMoreSamplesToContinuePlaying() {
        return hasLessThanNeeded(this.mBufferedTimeRequiredToContinue.getTotalNanoSeconds());
    }

    private boolean hasLessThanNeeded(long j) {
        return hasSpaceForMoreAndLessThanNeededSamples(SampleType.AUDIO_SAMPLE, j) || hasSpaceForMoreAndLessThanNeededSamples(SampleType.VIDEO_SAMPLE, j);
    }

    private boolean hasSpaceForMoreAndLessThanNeededSamples(SampleType sampleType, long j) {
        return hasSpaceForMoreSamples(sampleType) && this.mTracks.get(sampleType).mBuffer.getBufferedTimeUs() < TimeUnit.NANOSECONDS.toMicros(j);
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public boolean hasSpaceForMoreSamples(SampleType sampleType) {
        MediaTrackData mediaTrackData = this.mTracks.get(sampleType);
        if (mediaTrackData == null) {
            return false;
        }
        MediaSource mediaSource = mediaTrackData.mBuffer;
        return !mediaSource.hasReadEndOfStream() && mediaSource.hasCapacityForMoreSamples();
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public int getBitrate(SampleType sampleType) {
        MediaTrackData mediaTrackData = this.mTracks.get(sampleType);
        if (mediaTrackData == null) {
            return 0;
        }
        if (mediaTrackData.mPipeline.isAudioPipeline() || !this.mIsVideoPipelineInactive) {
            return mediaTrackData.mPipeline.getLastDecodedSampleBitrate();
        }
        return 0;
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public void setZoomLevel(PlaybackZoomLevel playbackZoomLevel, boolean z) throws PlaybackException {
        DLog.logf("Set zoom level to %s with ratio: %s", playbackZoomLevel.getZoomLevel(), Float.valueOf(playbackZoomLevel.getZoomRatio()));
        onVideoRegionChanged(this.mZoomCalculator.getVideoRegion(playbackZoomLevel));
        this.mCurrentZoomLevel = playbackZoomLevel;
    }

    @Override // com.amazon.avod.playback.renderer.VideoRegionInterpolator.VideoRegionChangeListener
    public void onVideoRegionChanged(@Nonnull VideoRegion videoRegion) {
        int width = videoRegion.getWidth();
        int height = videoRegion.getHeight();
        this.mSurfaceCreator.setDimensions(width, height);
        this.mVideoRegion = videoRegion;
        DLog.logf("VideoRegion width=%d height=%d", Integer.valueOf(width), Integer.valueOf(height));
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    @Nonnull
    public RendererPerformanceData getRendererPerformanceData() {
        MediaTrackData mediaTrackData = this.mTracks.get(SampleType.VIDEO_SAMPLE);
        if (mediaTrackData != null) {
            MediaPipeline mediaPipeline = mediaTrackData.mPipeline;
            if (!mediaPipeline.isUnconfigured() && !this.mIsVideoPipelineInactive) {
                try {
                    if (this.mIsFrameDropSimulationEnabled) {
                        this.mSimulatedDroppedFrameAccumulatedCount += this.mNumSimulatedFrameDrops;
                    }
                    return new RendererPerformanceData(mediaPipeline.getLastFpsSample(), this.mSimulatedDroppedFrameAccumulatedCount + mediaPipeline.getTotalDroppedFrames(), mediaPipeline.getLastDecodedSampleBitrate(), getRendererBufferStats());
                } catch (IllegalStateException e) {
                    DLog.warnf("Failed to read renderer performance data: %s", e);
                    return RendererPerformanceData.EMPTY_PERF_DATA;
                }
            }
            return RendererPerformanceData.EMPTY_PERF_DATA;
        }
        return RendererPerformanceData.EMPTY_PERF_DATA;
    }

    @Nonnull
    private List<Buffer> getRendererBufferStats() {
        if (this.mIsRendererBufferStatsEnabled) {
            Buffer.Builder builder = new Buffer.Builder();
            builder.type(Buffer.Type.Video);
            MediaSource mediaSource = this.mTracks.get(SampleType.VIDEO_SAMPLE).mBuffer;
            TimeUnit timeUnit = TimeUnit.MICROSECONDS;
            builder.timeAvailable(timeUnit.toMillis(Math.max(mediaSource.getBufferedTimeUs(), 0L)));
            builder.timeCapacity(timeUnit.toMillis(Math.max(mediaSource.getCapacityUs(), 0L)));
            builder.bytesAvailable(Math.max(mediaSource.getBufferedBytes(), 0L));
            builder.bytesCapacity(Math.max(mediaSource.getCapacityBytes(), 0L));
            Buffer.Builder builder2 = new Buffer.Builder();
            builder2.type(Buffer.Type.Audio);
            MediaSource mediaSource2 = this.mTracks.get(SampleType.AUDIO_SAMPLE).mBuffer;
            builder2.timeAvailable(timeUnit.toMillis(Math.max(mediaSource2.getBufferedTimeUs(), 0L)));
            builder2.timeCapacity(timeUnit.toMillis(Math.max(mediaSource2.getCapacityUs(), 0L)));
            builder2.bytesAvailable(Math.max(mediaSource2.getBufferedBytes(), 0L));
            builder2.bytesCapacity(Math.max(mediaSource2.getCapacityBytes(), 0L));
            return ImmutableList.of(builder.build(), builder2.build());
        }
        return ImmutableList.of();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean arePipelinesSynchronous() {
        return (this.mTracks.isEmpty() || this.mTracks.get(SampleType.AUDIO_SAMPLE).mPipeline.isAsynchronous() || this.mTracks.get(SampleType.VIDEO_SAMPLE).mPipeline.isAsynchronous()) ? false : true;
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public void checkRendererError(boolean z) throws MediaException {
        if (!arePipelinesSynchronous()) {
            this.mTracks.get(SampleType.AUDIO_SAMPLE).mPipeline.checkPipelineError();
            this.mTracks.get(SampleType.VIDEO_SAMPLE).mPipeline.checkPipelineError();
        }
        if (this.mLastRendererException != null) {
            throw this.mLastRendererException;
        }
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public boolean isOutOfSamples() {
        long totalMicroseconds = this.mBufferedTimeRequiredToContinue.getTotalMicroseconds();
        return this.mTracks.get(SampleType.AUDIO_SAMPLE).mBuffer.getBufferedTimeUs() < totalMicroseconds || this.mTracks.get(SampleType.VIDEO_SAMPLE).mBuffer.getBufferedTimeUs() < totalMicroseconds;
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public void recreateAudioTrack() throws PlaybackException {
        this.mLastRendererException = null;
        this.mTracks.get(SampleType.AUDIO_SAMPLE).mPipeline.recreateRenderer();
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public void recreateVideoTrack() {
        DLog.logf("MediaCodecRenderer.recreateVideoTrack() reactivating video pipeline.");
        this.mLastRendererException = null;
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public void setVideoRegion(@Nonnull VideoRegion videoRegion) {
        this.mSurfaceCreator.setVideoRegion(videoRegion);
        this.mVideoRegion = videoRegion;
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    @Nonnull
    public VideoRegion getVideoRegion() {
        return this.mVideoRegion;
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    @Nonnull
    public RendererCapabilities getRendererCapabilities() {
        return this.mRendererCapabilities;
    }

    final class PipelineTask implements Callable<Void> {
        private final MediaPipeline mMediaPipeline;
        private final PipelineTaskType mPipelineTaskType;

        PipelineTask(@Nonnull MediaPipeline mediaPipeline, @Nonnull PipelineTaskType pipelineTaskType) {
            this.mMediaPipeline = (MediaPipeline) Preconditions.checkNotNull(mediaPipeline, "mediaPipeline");
            this.mPipelineTaskType = (PipelineTaskType) Preconditions.checkNotNull(pipelineTaskType, "pipelineTaskType");
        }

        @Override // java.util.concurrent.Callable
        public Void call() {
            Thread.currentThread().setName(getTaskName());
            while (MediaCodecRenderer.this.mIsRunning && (this.mMediaPipeline.isAudioPipeline() || !MediaCodecRenderer.this.mIsVideoPipelineInactive)) {
                try {
                    this.mMediaPipeline.executePipelineTask(this.mPipelineTaskType);
                    Thread.sleep(MediaCodecRenderer.this.mTickIntervalMillis);
                } catch (PlaybackException e) {
                    MediaCodecRenderer.this.mLastRendererException = e;
                } catch (InterruptedException e2) {
                    DLog.warnf("PipelineTask: %s interrupted: %s", getTaskName(), e2);
                }
            }
            return null;
        }

        @Nonnull
        String getTaskName() {
            Object[] objArr = new Object[2];
            objArr[0] = this.mMediaPipeline.isAudioPipeline() ? "A" : "V";
            objArr[1] = this.mPipelineTaskType.toString();
            return String.format("MCR:%s:%s", objArr);
        }

        @Nonnull
        public SampleType getSampleType() {
            return this.mMediaPipeline.isAudioPipeline() ? SampleType.AUDIO_SAMPLE : SampleType.VIDEO_SAMPLE;
        }
    }
}
