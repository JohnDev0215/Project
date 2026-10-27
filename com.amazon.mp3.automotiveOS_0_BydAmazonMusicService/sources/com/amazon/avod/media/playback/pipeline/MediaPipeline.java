package com.amazon.avod.media.playback.pipeline;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaFormat;
import android.os.Bundle;
import android.view.Surface;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.media.drm.DrmCryptoSession;
import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.media.framework.config.PlaybackZoomConfig;
import com.amazon.avod.media.framework.error.DrmLicensingException;
import com.amazon.avod.media.framework.playback.util.ZoomCalculator;
import com.amazon.avod.media.playback.MediaDefaultConfiguration;
import com.amazon.avod.media.playback.android.AndroidVideoSurface;
import com.amazon.avod.media.playback.avsync.MediaClock;
import com.amazon.avod.media.playback.avsync.TimeSource;
import com.amazon.avod.media.playback.render.MediaRenderer;
import com.amazon.avod.media.playback.source.MediaSource;
import com.amazon.avod.media.playback.support.MediaCodecDeviceCapabilityDetector;
import com.amazon.avod.media.playback.util.VideoConfig;
import com.amazon.avod.media.playback.util.VideoRegion;
import com.amazon.avod.playback.PlaybackException;
import com.amazon.avod.playback.renderer.shared.SampleMetadata;
import com.amazon.avod.threading.Tickers;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import java.nio.ByteBuffer;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
@TargetApi(21)
public abstract class MediaPipeline extends MediaPipelineStateManager {
    private final boolean mAreTunnelModePipelineStatsEnabled;
    final Integer mAudioSessionId;
    MediaFormat mCachedAudioOutputFormat;
    final MediaCodecDeviceCapabilityDetector mCapabilityDetector;
    MediaCodec mCodec;
    final MediaCodecFactory mCodecFactory;
    DrmCryptoSession mDrmCryptoSession;
    boolean mHasInputStreamEnded;
    final Bundle mHdrOffBundle;
    final Bundle mHdrOnBundle;
    ByteBuffer[] mInputBuffers;
    MediaFormat mInputFormat;
    final boolean mIsAudioPipeline;
    final boolean mIsAudioStallingOptimizationEnabled;
    boolean mIsFirstSamplePTSReported;
    final boolean mIsSamsungSmartFittingEnabled;
    private final boolean mIsTunneled;
    final int mMaxConcurrentSampleCount;
    final MediaClock mMediaClock;
    final MediaPipelineContext mMediaPipelineContext;
    final MediaSystemSharedContext mMediaSystemSharedContext;
    String mMimeType;
    final boolean mNewGetBufferApiEnabled;
    ByteBuffer[] mOutputBuffers;
    MediaFormat mPassthroughMediaFormat;
    MediaRenderer mRenderer;
    final MediaRendererFactory mRendererFactory;
    final boolean mShouldReportAVSyncStats;
    final boolean mShouldValidateVideoDecodingCadence;
    final MediaSource mSource;
    Surface mSurface;
    private volatile TunneledFrameRenderedListener mTunneledFrameRenderedListener;
    final VideoConfig mVideoConfig;
    final long mVideoDecodingCadenceLoggingThresholdInMillis;
    final long mVideoDecodingCadenceThresholdInMillis;
    final AndroidVideoSurface mVideoSurface;
    final boolean mWaitForAudioStartupEnabled;
    final ZoomCalculator mZoomCalculator;
    final ConditionalLoggingTimer mPipelineTimer = ConditionalLoggingTimer.create();
    final ConditionalLoggingTimer mFeedTaskTimer = ConditionalLoggingTimer.create();
    final ConditionalLoggingTimer mDrainTaskTimer = ConditionalLoggingTimer.create();
    final Object mMutex = new Object();
    final Stopwatch mVideoDecodeCadenceStopwatch = Stopwatch.createUnstarted(Tickers.androidTicker());
    int mAvailableOutputBufferIndex = -1;
    MediaCodec.BufferInfo mAvailableOutputBufferInfo = new MediaCodec.BufferInfo();
    final AtomicBoolean mHasOutputStreamEnded = new AtomicBoolean(false);
    long mLastDecodedPresentationTimeUs = -1;
    final AtomicInteger mLastDecodedSampleBitrate = new AtomicInteger(0);
    private int mTotalDroppedFrames = 0;

    public abstract void checkPipelineError() throws PlaybackException;

    public abstract void configureCallbacks();

    public abstract void executePipelineTask(@Nonnull PipelineTaskType pipelineTaskType) throws PlaybackException;

    public abstract boolean isAsynchronous();

    MediaPipeline(@Nonnull MediaSource mediaSource, @Nonnull MediaCodecFactory mediaCodecFactory, @Nonnull MediaRendererFactory mediaRendererFactory, @Nonnull MediaSystemSharedContext mediaSystemSharedContext, @Nonnull MediaClock mediaClock, @Nonnull MediaPipelineContext mediaPipelineContext, @Nonnull ZoomCalculator zoomCalculator, @Nonnull AndroidVideoSurface androidVideoSurface, @Nullable Integer num, boolean z, @Nonnull VideoConfig videoConfig, @Nonnull MediaDefaultConfiguration mediaDefaultConfiguration, @Nonnull MediaCodecDeviceCapabilityDetector mediaCodecDeviceCapabilityDetector) {
        this.mSource = (MediaSource) Preconditions.checkNotNull(mediaSource, "mediaSource");
        this.mCodecFactory = (MediaCodecFactory) Preconditions.checkNotNull(mediaCodecFactory, "codecFactory");
        this.mRendererFactory = (MediaRendererFactory) Preconditions.checkNotNull(mediaRendererFactory, "rendererFactory");
        this.mMediaSystemSharedContext = (MediaSystemSharedContext) Preconditions.checkNotNull(mediaSystemSharedContext, "mediaSystemSharedContext");
        this.mMediaClock = (MediaClock) Preconditions.checkNotNull(mediaClock, "mediaClock");
        this.mMediaPipelineContext = (MediaPipelineContext) Preconditions.checkNotNull(mediaPipelineContext, "mediaPipelineContext");
        this.mZoomCalculator = (ZoomCalculator) Preconditions.checkNotNull(zoomCalculator, "zoomCalculator");
        this.mVideoSurface = (AndroidVideoSurface) Preconditions.checkNotNull(androidVideoSurface, "videoSurface");
        this.mIsAudioPipeline = z;
        this.mVideoConfig = (VideoConfig) Preconditions.checkNotNull(videoConfig, "videoConfig");
        this.mCapabilityDetector = (MediaCodecDeviceCapabilityDetector) Preconditions.checkNotNull(mediaCodecDeviceCapabilityDetector, "capabilityDetector");
        this.mAudioSessionId = num;
        this.mIsAudioStallingOptimizationEnabled = mediaDefaultConfiguration.isAudioStallingOptimizationEnabled();
        this.mNewGetBufferApiEnabled = mediaDefaultConfiguration.isNewGetBufferApiEnabled();
        this.mMaxConcurrentSampleCount = mediaDefaultConfiguration.getMaxConcurrentSampleCount();
        this.mWaitForAudioStartupEnabled = mediaDefaultConfiguration.waitForAudioStartupEnabled();
        this.mIsSamsungSmartFittingEnabled = mediaDefaultConfiguration.isSamsungSmartFittingEnabled();
        this.mShouldValidateVideoDecodingCadence = mediaDefaultConfiguration.shouldValidateVideoDecodingCadence();
        this.mVideoDecodingCadenceThresholdInMillis = mediaDefaultConfiguration.getVideoDecodingCadenceThresholdInMillis();
        this.mVideoDecodingCadenceLoggingThresholdInMillis = mediaDefaultConfiguration.getVideoDecodingCadenceLoggingThresholdInMillis();
        this.mShouldReportAVSyncStats = mediaDefaultConfiguration.shouldReportAVSyncStats();
        Bundle bundle = new Bundle();
        this.mHdrOnBundle = bundle;
        bundle.putInt("HDR-ENABLE", 1);
        Bundle bundle2 = new Bundle();
        this.mHdrOffBundle = bundle2;
        bundle2.putInt("HDR-ENABLE", 0);
        this.mCachedAudioOutputFormat = null;
        this.mIsTunneled = num != null;
        this.mAreTunnelModePipelineStatsEnabled = mediaDefaultConfiguration.areTunnelModePipelineStatsEnabled();
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x0116 A[Catch: all -> 0x013f, TryCatch #2 {, blocks: (B:4:0x0003, B:6:0x000c, B:7:0x001d, B:9:0x001f, B:11:0x0058, B:12:0x0072, B:14:0x0077, B:16:0x007d, B:21:0x009d, B:24:0x00a4, B:25:0x00b2, B:26:0x00b3, B:28:0x00e8, B:30:0x00ee, B:31:0x00fe, B:33:0x0102, B:34:0x010a, B:39:0x010f, B:41:0x0116, B:46:0x0126, B:47:0x013e), top: B:51:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0123  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void configure(@javax.annotation.Nonnull android.media.MediaFormat r10, @javax.annotation.Nullable android.view.Surface r11, @javax.annotation.Nullable com.amazon.avod.media.drm.DrmCryptoSession r12) throws com.amazon.avod.playback.PlaybackException {
        /*
            Method dump skipped, instruction units count: 322
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.media.playback.pipeline.MediaPipeline.configure(android.media.MediaFormat, android.view.Surface, com.amazon.avod.media.drm.DrmCryptoSession):void");
    }

    private void configureTunnelPlaybackFeature(@Nonnull MediaFormat mediaFormat) {
        Integer num;
        if (this.mIsAudioPipeline || (num = this.mAudioSessionId) == null) {
            return;
        }
        DLog.logf("Configuring video pipeline for tunneled playback, audioSessionId %d", num);
        mediaFormat.setFeatureEnabled("tunneled-playback", true);
        mediaFormat.setInteger("audio-session-id", this.mAudioSessionId.intValue());
    }

    @Override // com.amazon.avod.media.playback.pipeline.AbstractMediaComponent
    public void start() {
        synchronized (this.mMutex) {
            if (!canStart()) {
                DLog.warnf("MediaPipeline %s ignoring start() in %s state.", this.mMimeType, getState());
                return;
            }
            super.start();
            this.mHasInputStreamEnded = false;
            this.mHasOutputStreamEnded.set(false);
            this.mIsFirstSamplePTSReported = false;
            if (this.mAreTunnelModePipelineStatsEnabled && this.mIsTunneled && !this.mIsAudioPipeline && this.mTunneledFrameRenderedListener == null && this.mVideoConfig.getMaxFrameRate() != 0.0f) {
                this.mTunneledFrameRenderedListener = new TunneledFrameRenderedListener(this.mCodec, this.mVideoConfig);
            }
            if (this.mRenderer.isIdle()) {
                this.mRenderer.start();
            }
        }
    }

    public void setHdrStatus(boolean z) {
        synchronized (this.mMutex) {
            MediaCodec mediaCodec = this.mCodec;
            if (mediaCodec != null) {
                mediaCodec.setParameters(z ? this.mHdrOnBundle : this.mHdrOffBundle);
            }
        }
    }

    public void setVolume(float f) {
        Preconditions.checkArgument(f >= 0.0f, "gain must be non-negative.");
        if (!this.mIsAudioPipeline) {
            throw new UnsupportedOperationException("This operation is not supported for non-audio media pipeline.");
        }
        synchronized (this.mMutex) {
            MediaRenderer mediaRenderer = this.mRenderer;
            if (mediaRenderer != null) {
                mediaRenderer.setVolume(f);
            }
        }
    }

    public void setPlaybackSpeed(float f) {
        Preconditions.checkArgument(f > 0.0f, "Playback speed must be positive.");
        synchronized (this.mMutex) {
            MediaRenderer mediaRenderer = this.mRenderer;
            if (mediaRenderer != null) {
                mediaRenderer.setPlaybackSpeed(f);
            }
        }
    }

    @Override // com.amazon.avod.media.playback.pipeline.AbstractMediaComponent
    public void release() {
        synchronized (this.mMutex) {
            if (!canRelease()) {
                DLog.warnf("MediaPipeline %s ignoring release() in %s state.", this.mMimeType, getState());
                return;
            }
            super.release();
            MediaRenderer mediaRenderer = this.mRenderer;
            if (mediaRenderer != null && !mediaRenderer.isUnconfigured()) {
                this.mRenderer.release();
                this.mRenderer = null;
            }
            if (this.mCodec != null) {
                this.mPipelineTimer.start();
                try {
                    this.mCodec.stop();
                } catch (IllegalStateException e) {
                    DLog.warnf("MediaCodec for %s (state = %s) threw unexpected IllegalStateException calling stop(): %s", this.mMimeType, getState(), e.getMessage());
                }
                this.mPipelineTimer.stop("MediaCodec.stop");
                this.mPipelineTimer.start();
                try {
                    this.mCodecFactory.releaseCodec(this.mCodec);
                } catch (IllegalStateException e2) {
                    DLog.warnf("MediaCodec for %s (state = %s) threw unexpected IllegalStateException calling release(): %s", this.mMimeType, getState(), e2.getMessage());
                }
                this.mPipelineTimer.stop("MediaCodec.release");
                this.mCodec = null;
            }
            this.mPassthroughMediaFormat = null;
            clearAvailableOutputBufferInfo();
        }
    }

    public boolean hasRenderedEndOfStream() {
        return this.mHasOutputStreamEnded.get();
    }

    @Nullable
    public TimeSource getTimeSource() {
        TimeSource timeSource;
        synchronized (this.mMutex) {
            Preconditions.checkState(!isUnconfigured(), "Cannot getTimeSource() while in UNCONFIGURED state!");
            timeSource = this.mRenderer.getTimeSource();
        }
        return timeSource;
    }

    public long getNextBufferedPresentationTimeUs() {
        synchronized (this.mMutex) {
            Preconditions.checkState(!isUnconfigured(), "Cannot getLastRenderedTimeUs() while in UNCONFIGURED state!");
            long j = this.mLastDecodedPresentationTimeUs;
            if (j != -1) {
                return j;
            }
            return this.mSource.hasNext() ? this.mSource.getSampleTimeUs() : 0L;
        }
    }

    public int getLastDecodedSampleBitrate() {
        synchronized (this.mMutex) {
            if (isUnconfigured()) {
                DLog.warnf("Cannot getLastDecodedSampleBitrate() while in UNCONFIGURED state, returning 0.");
                return 0;
            }
            return this.mLastDecodedSampleBitrate.get();
        }
    }

    public float getLastFpsSample() {
        synchronized (this.mMutex) {
            if (isUnconfigured()) {
                DLog.warnf("Cannot getLastFpsSample() while in UNCONFIGURED state, returning 0.");
                return 0.0f;
            }
            if (this.mTunneledFrameRenderedListener == null) {
                return this.mRenderer.getLastFpsSample();
            }
            return this.mTunneledFrameRenderedListener.mLastFpsSample;
        }
    }

    public int getTotalDroppedFrames() {
        synchronized (this.mMutex) {
            if (isUnconfigured()) {
                DLog.warnf("Cannot getTotalDroppedFrames() while in UNCONFIGURED state, returning 0.");
                return 0;
            }
            if (this.mTunneledFrameRenderedListener != null) {
                return this.mTotalDroppedFrames;
            }
            return this.mRenderer.getTotalDroppedFrames();
        }
    }

    public void flush() throws PlaybackException {
        synchronized (this.mMutex) {
            if (!canFlush()) {
                DLog.warnf("MediaPipeline %s ignoring flush() in %s state", this.mMimeType, getState());
                return;
            }
            clearAvailableOutputBufferInfo();
            this.mLastDecodedPresentationTimeUs = -1L;
            this.mLastDecodedSampleBitrate.set(0);
            this.mTunneledFrameRenderedListener = null;
            if (this.mRenderer.isIdle()) {
                this.mRenderer.flush();
            }
            this.mPipelineTimer.start();
            try {
                this.mCodec.flush();
            } catch (IllegalStateException e) {
                DLog.warnf("MediaCodec for %s (state = %s) threw unexpected IllegalStateException calling flush(): %s", this.mMimeType, getState(), e.getMessage());
            }
            this.mPipelineTimer.stop("MediaCodec.flush", 20L);
            this.mMediaPipelineContext.getRendererDebugTracker().clearPTS();
            this.mIsFirstSamplePTSReported = false;
        }
    }

    static boolean shouldReconfigureRenderer(boolean z, @Nullable MediaFormat mediaFormat, @Nullable MediaFormat mediaFormat2) {
        return (z && mediaFormat != null && mediaFormat.containsKey("channel-count") && mediaFormat.containsKey("sample-rate") && mediaFormat.containsKey("mime") && mediaFormat2 != null && mediaFormat2.containsKey("channel-count") && mediaFormat2.containsKey("sample-rate") && mediaFormat2.containsKey("mime") && mediaFormat.getInteger("channel-count") == mediaFormat2.getInteger("channel-count") && mediaFormat.getInteger("sample-rate") == mediaFormat2.getInteger("sample-rate") && mediaFormat.getString("mime").equals(mediaFormat2.getString("mime"))) ? false : true;
    }

    void clearAvailableOutputBufferInfo() {
        this.mAvailableOutputBufferIndex = -1;
        this.mAvailableOutputBufferInfo.set(0, 0, 0L, 0);
    }

    @Nonnull
    public JSONObject getCodecInfo() {
        JSONObject jSONObject = new JSONObject();
        MediaCodec mediaCodec = this.mCodec;
        if (mediaCodec != null) {
            try {
                boolean z = true;
                jSONObject.put(mediaCodec.getName(), !this.mCodec.getName().startsWith("OMX.google."));
                if (this.mAudioSessionId == null) {
                    z = false;
                }
                jSONObject.put("tunnel.playback", z);
            } catch (IllegalStateException | JSONException unused) {
                DLog.warnf("Unable to format JSON object at MediaPipeline.getCodecInfo");
            }
        }
        return jSONObject;
    }

    public boolean isAudioPipeline() {
        return this.mIsAudioPipeline;
    }

    public void recreateRenderer() throws PlaybackException {
        synchronized (this.mMutex) {
            this.mRenderer.recreate();
        }
    }

    public void notifySurfaceRecreated(@Nonnull MediaFormat mediaFormat, @Nonnull Surface surface, @Nullable DrmCryptoSession drmCryptoSession) throws PlaybackException {
        synchronized (this.mMutex) {
            Preconditions.checkState(!this.mIsAudioPipeline, "Only video pipeline can handle notifySurfaceRecreated().");
            Preconditions.checkNotNull(mediaFormat, "inputFormat");
            Preconditions.checkNotNull(surface, "surface");
            DLog.warnf("MediaPipeline %s notifySurfaceRecreated() recreating pipeline with new surface.", mediaFormat);
            configure(mediaFormat, surface, drmCryptoSession);
            start();
        }
    }

    public void notifySurfaceDestroyed() {
        synchronized (this.mMutex) {
            Preconditions.checkState(!this.mIsAudioPipeline, "Only video pipeline can handle notifySurfaceDestroyed().");
            DLog.warnf("MediaPipeline %s notifySurfaceDestroyed() deactivating pipeline.", this.mInputFormat);
            stop();
            release();
        }
    }

    @TargetApi(23)
    public void setOutputSurface(@Nonnull Surface surface) throws PlaybackException {
        synchronized (this.mMutex) {
            Preconditions.checkState(!this.mIsAudioPipeline, "Only video pipeline can handle setOutputSurface().");
            Preconditions.checkNotNull(surface, "surface");
            if (!canSetOutputSurface()) {
                DLog.warnf("MediaPipeline %s ignoring setOutputSurface() in %s state.", this.mMimeType, getState());
                return;
            }
            try {
                DLog.logf("MediaPipeline %s setOutputSurface() switching output surface to %s", this.mInputFormat, surface);
                this.mCodec.setOutputSurface(surface);
            } catch (Exception e) {
                String str = String.format(Locale.US, "MediaPipeline %s setOutputSurface() failed state %s surface %s error %s", this.mMimeType, getState(), surface, e);
                DLog.warnf(str);
                throw new PlaybackException(PlaybackException.PlaybackError.RENDERER_VIDEO_SURFACE_SWITCH_FAILED, str, e);
            }
        }
    }

    boolean shouldUseNewGetBufferApi() {
        return this.mNewGetBufferApiEnabled;
    }

    private void handleSamsungSmartFittingCallback(@Nonnull MediaFormat mediaFormat) {
        int i;
        int i2;
        Preconditions.checkNotNull(mediaFormat, "mediaFormat");
        int integer = mediaFormat.getInteger("width");
        int integer2 = mediaFormat.getInteger("height");
        double displayAspectRatio = this.mVideoConfig.getDisplayAspectRatio();
        if (displayAspectRatio == -1.0d) {
            DLog.logf("Cannot read the DAR from the manifest, falling back to 16:9");
            displayAspectRatio = 1.0d;
        }
        double d = (((double) integer2) / ((double) integer)) * displayAspectRatio;
        int integer3 = mediaFormat.getInteger("smartfit-w");
        int integer4 = mediaFormat.getInteger("smartfit-h");
        if (d >= 1.0d) {
            i2 = (int) (((double) integer3) * d);
            i = integer4;
        } else {
            i = (int) (((double) integer4) * d);
            i2 = integer3;
        }
        this.mZoomCalculator.setVideoResolution(new VideoResolution(i2, i));
        VideoRegion videoRegion = this.mZoomCalculator.getVideoRegion(PlaybackZoomConfig.getInstance().getPlaybackZoomLevel());
        DLog.logf("Resizing Video: PixelRect %s, SmartRect %s, DAR (%.2f), SAR (%.2f), SarAdjustedSmartRect %s, VideoSurface %s, MediaFormat %s", getRectInfoString(integer, integer2), getRectInfoString(integer3, integer4), Double.valueOf(displayAspectRatio), Double.valueOf(d), getRectInfoString(i2, i), getRectInfoString(videoRegion.getWidth(), videoRegion.getHeight()), mediaFormat);
        this.mVideoSurface.setDimensions(videoRegion.getWidth(), videoRegion.getHeight());
    }

    private String getRectInfoString(int i, int i2) {
        return String.format("(W: %s H: %s R: %.2f)", Integer.valueOf(i), Integer.valueOf(i2), Float.valueOf(i / i2));
    }

    void feedInputBuffer(int i) throws PlaybackException {
        PlaybackException.PlaybackError playbackError;
        Preconditions.checkState(i >= 0);
        ByteBuffer inputBuffer = (shouldUseNewGetBufferApi() || isAsynchronous()) ? this.mCodec.getInputBuffer(i) : this.mInputBuffers[i];
        if (inputBuffer == null) {
            DLog.warnf("MediaCodec for %s feed input buffer was unexpectedly null, index: %s", this.mMimeType, Integer.valueOf(i));
            return;
        }
        SampleMetadata sampleData = this.mSource.readSampleData(inputBuffer);
        int size = sampleData.getSize();
        MediaCodec.CryptoInfo cryptoInfoDecryptSample = null;
        DrmCryptoSession drmCryptoSession = this.mDrmCryptoSession;
        if (drmCryptoSession != null) {
            try {
                cryptoInfoDecryptSample = drmCryptoSession.decryptSample(inputBuffer, sampleData);
            } catch (DrmLicensingException e) {
                throw new PlaybackException(PlaybackException.PlaybackError.RENDERER_DECRYPTION_FAILURE, String.format("Decryption failed because of the following DrmLicensingException: %s", e));
            }
        }
        MediaCodec.CryptoInfo cryptoInfo = cryptoInfoDecryptSample;
        this.mFeedTaskTimer.start();
        if (cryptoInfo == null) {
            this.mCodec.queueInputBuffer(i, 0, size, sampleData.getPresentationTimeUs(), sampleData.getFlags());
        } else {
            try {
                this.mCodec.queueSecureInputBuffer(i, 0, cryptoInfo, sampleData.getPresentationTimeUs(), sampleData.getFlags());
            } catch (MediaCodec.CryptoException e2) {
                String str = String.format("Decryption failed because of the following CryptoException errorCode:%d, full:%s", Integer.valueOf(e2.getErrorCode()), e2);
                int errorCode = e2.getErrorCode();
                if (errorCode == 1) {
                    playbackError = PlaybackException.PlaybackError.RENDERER_DECRYPTION_FAILURE_MISSING_KEY;
                } else if (errorCode == 2) {
                    playbackError = PlaybackException.PlaybackError.RENDERER_DECRYPTION_FAILURE_EXPIRED_KEY;
                } else if (errorCode == 4) {
                    playbackError = PlaybackException.PlaybackError.RENDERER_DECRYPTION_FAILURE_INSUFFICIENT_OUTPUT_PROTECTION;
                } else if (errorCode == 6) {
                    playbackError = PlaybackException.PlaybackError.RENDERER_DECRYPTION_FAILURE_UNSUPPORTED_OPERATION;
                } else {
                    playbackError = PlaybackException.PlaybackError.RENDERER_DECRYPTION_FAILURE;
                }
                throw new PlaybackException(playbackError, str);
            }
        }
        this.mFeedTaskTimer.stop(cryptoInfo == null ? "MediaCodec.queueInputBuffer" : "MediaCodec.queueSecureInputBuffer", 20L);
        this.mLastDecodedSampleBitrate.set(sampleData.getSampleBitrate());
        if (!this.mIsAudioPipeline) {
            this.mVideoDecodeCadenceStopwatch.reset().start();
        }
        if (this.mShouldReportAVSyncStats && !this.mIsFirstSamplePTSReported) {
            this.mMediaPipelineContext.getRendererDebugTracker().updateFirstSampleInStreamPTS(this.mIsAudioPipeline, sampleData.getPresentationTimeUs());
            this.mIsFirstSamplePTSReported = true;
        }
        this.mSource.advance();
    }

    void feedEndOfStreamFlag(int i) {
        Preconditions.checkState(i >= 0);
        this.mFeedTaskTimer.start();
        this.mCodec.queueInputBuffer(i, 0, 0, 0L, 4);
        this.mFeedTaskTimer.stop("MediaCodec.queueInputBuffer", 20L);
        DLog.logf("Pipeline for %s read end of stream from source", this.mInputFormat);
        this.mHasInputStreamEnded = true;
    }

    void handleOutputFormatChanged(@Nonnull MediaFormat mediaFormat) throws PlaybackException {
        Preconditions.checkNotNull(mediaFormat);
        MediaFormat mediaFormat2 = this.mPassthroughMediaFormat;
        if (mediaFormat2 != null) {
            mediaFormat = mediaFormat2;
        }
        DLog.logf("MediaCodec for %s indicates output format changed to %s", this.mInputFormat, mediaFormat);
        if (this.mRenderer.isRunning() && shouldReconfigureRenderer(this.mIsAudioPipeline, this.mCachedAudioOutputFormat, mediaFormat)) {
            this.mRenderer.stop();
            this.mRenderer.release();
        }
        if (this.mRenderer.isUnconfigured()) {
            this.mRenderer.configure(mediaFormat, this.mAudioSessionId);
            this.mRenderer.start();
            if (this.mIsAudioPipeline) {
                this.mCachedAudioOutputFormat = mediaFormat;
            }
        }
        if (!this.mIsAudioPipeline && this.mIsSamsungSmartFittingEnabled && mediaFormat.containsKey("smartfit-w") && mediaFormat.containsKey("smartfit-h")) {
            handleSamsungSmartFittingCallback(mediaFormat);
        }
    }

    void validateVideoDecodingCadence() throws PlaybackException {
        long jElapsed = this.mVideoDecodeCadenceStopwatch.elapsed(TimeUnit.MILLISECONDS);
        if (jElapsed > this.mVideoDecodingCadenceLoggingThresholdInMillis) {
            DLog.warnf(String.format("MediaPipeline lastDecodedSamplePts %s ms, threshold %s ms breached %s ms since last video sample was submitted %s", Long.valueOf(TimeUnit.MICROSECONDS.toMillis(this.mLastDecodedPresentationTimeUs)), Long.valueOf(this.mVideoDecodingCadenceLoggingThresholdInMillis), Long.valueOf(jElapsed), this.mInputFormat));
        }
        if (jElapsed <= this.mVideoDecodingCadenceThresholdInMillis) {
            return;
        }
        String str = String.format("MediaPipeline triggering player restart lastDecodedSamplePts %s ms, threshold %s ms breached %s ms since last video sample was submitted %s", Long.valueOf(TimeUnit.MICROSECONDS.toMillis(this.mLastDecodedPresentationTimeUs)), Long.valueOf(this.mVideoDecodingCadenceThresholdInMillis), Long.valueOf(jElapsed), this.mInputFormat);
        DLog.errorf(str);
        throw new PlaybackException(PlaybackException.PlaybackError.RENDERER_DECODER_STALLED, str);
    }

    @TargetApi(23)
    private final class TunneledFrameRenderedListener implements MediaCodec.OnFrameRenderedListener {
        private long mFpsSampleStartUs;
        private final double mIdealRenderedPresentationTimeDeltaMs;
        private float mLastFpsSample;
        private long mLastRenderedPresentationTimeMs;
        private int mRenderedFramesInCurrentSample;
        private final TimeSource mTimeSource;

        private TunneledFrameRenderedListener(@Nonnull MediaCodec mediaCodec, @Nonnull VideoConfig videoConfig) {
            this.mRenderedFramesInCurrentSample = 0;
            this.mFpsSampleStartUs = 0L;
            this.mLastFpsSample = -1.0f;
            this.mLastRenderedPresentationTimeMs = -1L;
            this.mTimeSource = TimeSource.DEFAULT_INSTANCE;
            Preconditions.checkNotNull(mediaCodec, "codec");
            Preconditions.checkNotNull(videoConfig, "videoConfig");
            Preconditions.checkState(videoConfig.getMaxFrameRate() != 0.0f, "maxFrameRate unavailable");
            mediaCodec.setOnFrameRenderedListener(this, null);
            this.mIdealRenderedPresentationTimeDeltaMs = TimeUnit.SECONDS.toMillis(1L) / videoConfig.getMaxFrameRate();
        }

        @Override // android.media.MediaCodec.OnFrameRenderedListener
        public void onFrameRendered(@Nonnull MediaCodec mediaCodec, long j, long j2) {
            if (this != MediaPipeline.this.mTunneledFrameRenderedListener) {
                return;
            }
            TimeUnit timeUnit = TimeUnit.MICROSECONDS;
            long millis = timeUnit.toMillis(j);
            if (millis == 0) {
                return;
            }
            long j3 = this.mLastRenderedPresentationTimeMs;
            long j4 = millis - j3;
            if (j3 != -1 && Math.abs(j4 - this.mIdealRenderedPresentationTimeDeltaMs) > 5.0d) {
                int iAbs = (int) (Math.abs(j4 - 5) / this.mIdealRenderedPresentationTimeDeltaMs);
                MediaPipeline.this.mTotalDroppedFrames += iAbs;
                DLog.logf("TunneledFrameRenderedListener detected: %s dropped frame/s, total %s, LastPtsMs %s, CurrentPtsMs %s, DeltaMs %s, IdealDeltaMs %s", Integer.valueOf(iAbs), Integer.valueOf(MediaPipeline.this.mTotalDroppedFrames), Long.valueOf(this.mLastRenderedPresentationTimeMs), Long.valueOf(millis), Long.valueOf(j4), Double.valueOf(this.mIdealRenderedPresentationTimeDeltaMs));
            }
            this.mLastRenderedPresentationTimeMs = millis;
            this.mRenderedFramesInCurrentSample++;
            long currentRealTimeUs = this.mTimeSource.getCurrentRealTimeUs();
            long millis2 = timeUnit.toMillis(currentRealTimeUs - this.mFpsSampleStartUs);
            if (millis2 >= 1000) {
                this.mLastFpsSample = (this.mRenderedFramesInCurrentSample * TimeUnit.SECONDS.toMillis(1L)) / millis2;
                this.mRenderedFramesInCurrentSample = 0;
                this.mFpsSampleStartUs = currentRealTimeUs;
            }
        }
    }
}
