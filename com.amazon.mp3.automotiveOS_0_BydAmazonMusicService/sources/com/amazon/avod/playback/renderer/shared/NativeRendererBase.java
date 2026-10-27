package com.amazon.avod.playback.renderer.shared;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.error.InvalidRendererTimestampException;
import com.amazon.avod.media.framework.playback.util.ZoomCalculator;
import com.amazon.avod.media.playback.MediaDefaultConfiguration;
import com.amazon.avod.media.playback.VideoRenderingSettings;
import com.amazon.avod.media.playback.support.Buffer;
import com.amazon.avod.media.playback.support.RendererPerformanceData;
import com.amazon.avod.media.playback.support.RendererPerformanceEventListener;
import com.amazon.avod.media.playback.support.VideoRenderer;
import com.amazon.avod.media.playback.util.AudioConfig;
import com.amazon.avod.media.playback.util.AudioTrackAdapter;
import com.amazon.avod.media.playback.util.VideoConfig;
import com.amazon.avod.media.playback.util.VideoRegion;
import com.amazon.avod.media.playback.util.VideoTrackAdapter;
import com.amazon.avod.playback.PlaybackException;
import com.amazon.avod.playback.PlaybackZoomLevel;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playback.player.PlaybackConfig;
import com.amazon.avod.playback.renderer.StreamHandlerBase;
import com.amazon.avod.playback.renderer.VideoRegionInterpolator;
import com.amazon.avod.playback.sampling.SampleHolder;
import com.amazon.avod.playback.sampling.SampleType;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
public abstract class NativeRendererBase implements VideoRenderer {
    private static final double BUFFERING_FULLNESS_RATIO = 0.75d;
    private static final long CACHED_NEXT_AUDIO_PTS_NANOS_UNSET = 0;
    private static final String EMPTY_DEBUG_INFO = "";
    private StreamHandlerBase mAudioHandler;
    private final long mBufferedTimeRequiredToContinuePlaybackInNanoseconds;
    private final long mBufferedTimeRequiredToStartPlaybackInNanoseconds;
    private final ExecutorService mExecutor;
    private final boolean mIsRendererBufferStatsEnabled;
    private final MediaDefaultConfiguration mMediaDefaultConfiguration;
    private RendererPerformanceEventListener mPerformanceListener;
    private final PlaybackConfig mPlaybackConfiguration;
    private final VideoRegionInterpolator.VideoRegionChangeListener mRegionChangeListener;
    private final VideoRegionInterpolator mRegionInterpolator;
    protected final NativeRendererJniBase mRendererJni;
    private StreamHandlerBase mVideoHandler;
    protected VideoRegion mVideoRegion;
    protected final ZoomCalculator mZoomCalculator;
    protected final NativePlaybackStatus mPlaybackStatus = new NativePlaybackStatus();
    private boolean mIsInitialized = false;
    private boolean mIsNativeOwnershipAcquired = false;
    private final AtomicBoolean mIsFlushed = new AtomicBoolean(true);
    protected long mCachedNextAudioPtsNanos = 0;
    protected TimeSpan mContentBufferLookAheadSize = TimeSpan.MAX_VALUE;

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    @Nonnull
    public String getRendererDebugInfo() {
        return "";
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public void recreateAudioTrack() {
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public void recreateVideoTrack() {
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public void setVolume(float f) {
    }

    protected NativeRendererBase(@Nonnull ExecutorService executorService, @Nonnull PlaybackConfig playbackConfig, @Nonnull VideoRegionInterpolator videoRegionInterpolator, @Nonnull ZoomCalculator zoomCalculator, @Nonnull NativeRendererJniBase nativeRendererJniBase, @Nonnull MediaDefaultConfiguration mediaDefaultConfiguration) {
        VideoRegionInterpolator.VideoRegionChangeListener videoRegionChangeListener = new VideoRegionInterpolator.VideoRegionChangeListener() { // from class: com.amazon.avod.playback.renderer.shared.NativeRendererBase.1
            @Override // com.amazon.avod.playback.renderer.VideoRegionInterpolator.VideoRegionChangeListener
            public void onVideoRegionChanged(@Nonnull VideoRegion videoRegion) {
                if (NativeRendererBase.this.mIsInitialized) {
                    try {
                        NativeRendererBase.this.mRendererJni.setVideoRegion(videoRegion.getFromLeft(), videoRegion.getFromTop(), videoRegion.getWidth(), videoRegion.getHeight());
                    } catch (PlaybackException e) {
                        DLog.exceptionf(e, "Failed to resize video surface from region changed callback: %s", videoRegion);
                    }
                }
            }
        };
        this.mRegionChangeListener = videoRegionChangeListener;
        ZoomCalculator zoomCalculator2 = (ZoomCalculator) Preconditions.checkNotNull(zoomCalculator, "zoomCalculator");
        this.mZoomCalculator = zoomCalculator2;
        this.mRendererJni = (NativeRendererJniBase) Preconditions.checkNotNull(nativeRendererJniBase, "rendererJni");
        PlaybackConfig playbackConfig2 = (PlaybackConfig) Preconditions.checkNotNull(playbackConfig, "playbackConfig");
        this.mPlaybackConfiguration = playbackConfig2;
        VideoRegionInterpolator videoRegionInterpolator2 = (VideoRegionInterpolator) Preconditions.checkNotNull(videoRegionInterpolator, "regionInterpolator");
        this.mRegionInterpolator = videoRegionInterpolator2;
        this.mExecutor = (ExecutorService) Preconditions.checkNotNull(executorService, "executor");
        this.mMediaDefaultConfiguration = (MediaDefaultConfiguration) Preconditions.checkNotNull(mediaDefaultConfiguration, "mediaDefaultConfiguration");
        videoRegionInterpolator2.setListener(videoRegionChangeListener);
        this.mBufferedTimeRequiredToStartPlaybackInNanoseconds = playbackConfig2.getRequiredNativeBufferForStartPlayback().getTotalNanoSeconds();
        this.mBufferedTimeRequiredToContinuePlaybackInNanoseconds = playbackConfig2.getRequiredNativeBufferForContinuePlayback().getTotalNanoSeconds();
        this.mIsRendererBufferStatsEnabled = playbackConfig2.isRendererBufferStatsEnabled();
        this.mAudioHandler = new NativeStreamHandler(playbackConfig2);
        this.mVideoHandler = new NativeStreamHandler(playbackConfig2);
        this.mVideoRegion = zoomCalculator2.getVideoRegionForScreenSize();
    }

    protected int initialize(int i, int i2, String str, String str2, boolean z) throws PlaybackException {
        boolean z2 = this.mIsInitialized;
        if (!z2 || !this.mIsNativeOwnershipAcquired) {
            Preconditions.checkState(!z2);
            this.mIsNativeOwnershipAcquired = NativeRendererJniBase.acquireOwnership();
        }
        int iCreateRenderer = this.mRendererJni.createRenderer(i, i2, NativeCodecTypes.valueOf(str), NativeCodecTypes.valueOf(str2), z);
        this.mPlaybackStatus.init(this.mRendererJni.getStatisticsBuffer());
        this.mIsFlushed.set(true);
        this.mIsInitialized = true;
        this.mCachedNextAudioPtsNanos = 0L;
        return iCreateRenderer;
    }

    protected void configureStreamHandlers(VideoConfig videoConfig, AudioConfig audioConfig) throws PlaybackException {
        if (this.mMediaDefaultConfiguration.isOmxIlZeroMemCopyEnabled() && this.mMediaDefaultConfiguration.isMemStoreBasedRendererZeroMemCopyAllowed()) {
            this.mAudioHandler = new NativeStreamHandlerWithZeroMemoryCopy(this.mPlaybackConfiguration);
            this.mVideoHandler = new NativeStreamHandlerWithZeroMemoryCopy(this.mPlaybackConfiguration);
        }
        DLog.logf("Initializing AudioStreamHandler with AudioConfig=%s", audioConfig);
        this.mAudioHandler.initialize(SampleType.AUDIO_SAMPLE, new AudioTrackAdapter(), audioConfig, this.mRendererJni);
        DLog.logf("Initializing VideoStreamHandler with VideoConfig=%s", videoConfig);
        this.mVideoHandler.initialize(SampleType.VIDEO_SAMPLE, new VideoTrackAdapter(videoConfig.getFourCC()), videoConfig, this.mRendererJni);
        this.mZoomCalculator.setVideoResolution(videoConfig.getVideoResolution());
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public void configureDecryption(byte[] bArr, @Nullable byte[] bArr2, @Nullable byte[] bArr3, @Nullable String str, @Nonnull DrmScheme drmScheme) throws PlaybackException {
        Preconditions.checkNotNull(bArr, "encryptionData");
        Preconditions.checkNotNull(drmScheme, "drmScheme");
        DLog.devf("Setting PlayReady header: %s", new String(bArr, StandardCharsets.UTF_16LE));
        this.mRendererJni.setDrmHeader(bArr);
        this.mRendererJni.waitForSetDrmHeader();
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public void configureOutputDisplay(@Nonnull VideoRenderingSettings videoRenderingSettings) throws PlaybackException {
        Preconditions.checkNotNull(videoRenderingSettings, "settings");
        VideoRegion videoRegion = this.mZoomCalculator.getVideoRegion(videoRenderingSettings.getPlaybackZoomLevel());
        this.mVideoRegion = videoRegion;
        this.mRegionInterpolator.setInitialRegion(videoRegion);
        this.mRendererJni.setVideoRegion(this.mVideoRegion.getFromLeft(), this.mVideoRegion.getFromTop(), this.mVideoRegion.getWidth(), this.mVideoRegion.getHeight());
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    @Nullable
    public SampleType getRequiredNextSampleType() {
        SampleType sampleType = SampleType.AUDIO_SAMPLE;
        boolean zHasSpaceForMoreSamples = hasSpaceForMoreSamples(sampleType);
        SampleType sampleType2 = SampleType.VIDEO_SAMPLE;
        boolean zHasSpaceForMoreSamples2 = hasSpaceForMoreSamples(sampleType2);
        if (zHasSpaceForMoreSamples || zHasSpaceForMoreSamples2) {
            return !zHasSpaceForMoreSamples ? sampleType2 : (zHasSpaceForMoreSamples2 && this.mAudioHandler.getLastSubmittedDecodeTimeInNanos() >= this.mVideoHandler.getLastSubmittedDecodeTimeInNanos()) ? sampleType2 : sampleType;
        }
        return null;
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public void flush() throws PlaybackException {
        DLog.logf("Flushing renderer");
        this.mAudioHandler.flush();
        this.mVideoHandler.flush();
        this.mRendererJni.flushRenderer();
        this.mIsFlushed.set(true);
        this.mCachedNextAudioPtsNanos = 0L;
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public long getLastRenderedTimeStampInNanos() throws InvalidRendererTimestampException {
        if (this.mIsInitialized) {
            return this.mPlaybackStatus.getLastRenderedTimeStampInNanos();
        }
        return 0L;
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public long getLastTimeStampPassedToRendererInNanos(SampleType sampleType) {
        return getHandler(sampleType).getLastSubmittedDecodeTimeInNanos();
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public void pause() throws PlaybackException {
        this.mRendererJni.pauseRenderer();
        DLog.logf("Video renderer paused: queued audio samples = %s, video = %s", Long.valueOf(this.mPlaybackStatus.getNumberOfSamplesInQueue(SampleType.AUDIO_SAMPLE)), Long.valueOf(this.mPlaybackStatus.getNumberOfSamplesInQueue(SampleType.VIDEO_SAMPLE)));
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public void resume() throws PlaybackException {
        DLog.logf("Resuming renderer");
        this.mRendererJni.resumeRenderer();
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public void setZoomLevel(PlaybackZoomLevel playbackZoomLevel, boolean z) throws PlaybackException {
        if (this.mIsInitialized) {
            DLog.logf("Set zoom level to %s with ratio: %s", playbackZoomLevel.getZoomLevel(), Float.valueOf(playbackZoomLevel.getZoomRatio()));
            VideoRegion videoRegion = this.mZoomCalculator.getVideoRegion(playbackZoomLevel);
            this.mVideoRegion = videoRegion;
            DLog.logf("Updated video region to: %s", videoRegion.toString());
            this.mRegionInterpolator.setTarget(this.mVideoRegion, z);
            return;
        }
        DLog.warnf("Called before the native video renderer was initialized");
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public void dispose(boolean z) {
        if (this.mPlaybackConfiguration.isRendererReuseEnabled() && z) {
            return;
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.mExecutor.execute(new Runnable() { // from class: com.amazon.avod.playback.renderer.shared.NativeRendererBase$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$dispose$0(countDownLatch);
            }
        });
        try {
            if (!countDownLatch.await(this.mPlaybackConfiguration.getRendererTerminationTimeout().getTotalMilliseconds(), TimeUnit.MILLISECONDS)) {
                DLog.errorf("Renderer termination timed out.");
            }
        } catch (InterruptedException unused) {
            DLog.errorf("Renderer termination interrupted.");
        }
        if (this.mIsNativeOwnershipAcquired) {
            this.mIsNativeOwnershipAcquired = false;
            NativeRendererJniBase.releaseOwnership();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$dispose$0(CountDownLatch countDownLatch) {
        try {
            try {
                DLog.logf("Pausing native renderer");
                if (this.mIsInitialized) {
                    this.mRendererJni.pauseRenderer();
                }
            } catch (PlaybackException e) {
                DLog.exceptionf(e, "Pausing the native renderer", new Object[0]);
                if (this.mIsInitialized) {
                }
            }
        } finally {
            if (this.mIsInitialized) {
                this.mAudioHandler.flush();
                this.mVideoHandler.flush();
                this.mIsInitialized = false;
            }
            DLog.logf("Terminating native renderer");
            this.mRendererJni.terminateRenderer();
            countDownLatch.countDown();
        }
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public boolean haveStreamsReachedEnd() {
        return this.mPlaybackStatus.haveStreamsReachedEnd();
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public boolean needsMoreSamplesToStartPlaying() {
        return needsMoreSamples(Math.min(this.mBufferedTimeRequiredToStartPlaybackInNanoseconds, this.mContentBufferLookAheadSize.getTotalNanoSeconds()));
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public boolean needsMoreSamplesToContinuePlaying() {
        return needsMoreSamples(this.mBufferedTimeRequiredToContinuePlaybackInNanoseconds);
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public boolean hasSpaceForMoreSamples(SampleType sampleType) {
        StreamHandlerBase handler = getHandler(sampleType);
        return !handler.isDone() && ((double) this.mPlaybackStatus.getNumberOfBytesInQueue(sampleType)) / ((double) handler.getBufferSize()) < BUFFERING_FULLNESS_RATIO;
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public void submitSample(SampleHolder sampleHolder) throws PlaybackException {
        getHandler(sampleHolder.getType()).submitSample(sampleHolder);
        if (this.mIsFlushed.compareAndSet(true, false)) {
            this.mPlaybackStatus.setLastTimeStampPassedToRenderInNanos(SampleType.AUDIO_SAMPLE, sampleHolder.getDecodeTime());
            this.mPlaybackStatus.setLastTimeStampPassedToRenderInNanos(SampleType.VIDEO_SAMPLE, sampleHolder.getDecodeTime());
        }
        if (this.mCachedNextAudioPtsNanos == 0 && sampleHolder.getType() == SampleType.AUDIO_SAMPLE) {
            this.mCachedNextAudioPtsNanos = sampleHolder.getPresentationTime();
        }
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public int getBitrate(SampleType sampleType) {
        return this.mPlaybackStatus.getBitrate(sampleType);
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    @Nonnull
    public RendererPerformanceData getRendererPerformanceData() {
        return new RendererPerformanceData(this.mPlaybackStatus.getDeliveryFPS(SampleType.VIDEO_SAMPLE), getRendererBufferStats());
    }

    @Nonnull
    protected List<Buffer> getRendererBufferStats() {
        if (this.mIsRendererBufferStatsEnabled) {
            Buffer.Builder builder = new Buffer.Builder();
            builder.type(Buffer.Type.Video);
            TimeUnit timeUnit = TimeUnit.NANOSECONDS;
            SampleType sampleType = SampleType.VIDEO_SAMPLE;
            builder.timeAvailable(timeUnit.toMillis(Math.max(getBufferedTimeInNanos(sampleType), 0L)));
            builder.bytesAvailable(Math.max(this.mPlaybackStatus.getNumberOfBytesInQueue(sampleType), 0L));
            builder.bytesCapacity(Math.max(getHandler(sampleType).getBufferSize(), 0));
            Buffer.Builder builder2 = new Buffer.Builder();
            builder2.type(Buffer.Type.Audio);
            SampleType sampleType2 = SampleType.AUDIO_SAMPLE;
            builder2.timeAvailable(timeUnit.toMillis(Math.max(getBufferedTimeInNanos(sampleType2), 0L)));
            builder2.bytesAvailable(Math.max(this.mPlaybackStatus.getNumberOfBytesInQueue(sampleType2), 0L));
            builder2.bytesCapacity(Math.max(getHandler(sampleType2).getBufferSize(), 0));
            return ImmutableList.of(builder.build(), builder2.build());
        }
        return ImmutableList.of();
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public void addListener(@Nullable RendererPerformanceEventListener rendererPerformanceEventListener) {
        this.mPerformanceListener = rendererPerformanceEventListener;
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    @Nonnull
    public String getCodecInfo() {
        return getClass().toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public RendererPerformanceEventListener getPerformanceListener() {
        return this.mPerformanceListener;
    }

    private boolean needsMoreSamples(long j) {
        return needsMoreSamplesOfType(j, SampleType.AUDIO_SAMPLE) || needsMoreSamplesOfType(j, SampleType.VIDEO_SAMPLE);
    }

    private boolean needsMoreSamplesOfType(long j, SampleType sampleType) {
        return (hasEnoughBufferedTime(sampleType, j) ^ true) && hasSpaceForMoreSamples(sampleType);
    }

    private StreamHandlerBase getHandler(SampleType sampleType) {
        if (sampleType == SampleType.VIDEO_SAMPLE) {
            return this.mVideoHandler;
        }
        return this.mAudioHandler;
    }

    private boolean hasEnoughBufferedTime(SampleType sampleType, long j) {
        return getHandler(sampleType).getLastSubmittedDecodeTimeInNanos() - this.mPlaybackStatus.getLastTimeStampPassedToRendererInNanos(sampleType) >= j;
    }

    private long getBufferedTimeInNanos(@Nonnull SampleType sampleType) {
        return getHandler(sampleType).getLastSubmittedDecodeTimeInNanos() - this.mPlaybackStatus.getLastTimeStampPassedToRendererInNanos(sampleType);
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public boolean isOutOfSamples() {
        return (hasEnoughBufferedTime(SampleType.AUDIO_SAMPLE, this.mBufferedTimeRequiredToContinuePlaybackInNanoseconds) ^ true) || (hasEnoughBufferedTime(SampleType.VIDEO_SAMPLE, this.mBufferedTimeRequiredToContinuePlaybackInNanoseconds) ^ true);
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    public void setVideoRegion(@Nonnull VideoRegion videoRegion) {
        if (this.mIsInitialized) {
            this.mVideoRegion = videoRegion;
            this.mRegionInterpolator.setTarget(videoRegion, false);
        } else {
            DLog.warnf("Called before the native video renderer was initialized");
        }
    }

    @Override // com.amazon.avod.media.playback.support.VideoRenderer
    @Nonnull
    public VideoRegion getVideoRegion() {
        return this.mVideoRegion;
    }
}
