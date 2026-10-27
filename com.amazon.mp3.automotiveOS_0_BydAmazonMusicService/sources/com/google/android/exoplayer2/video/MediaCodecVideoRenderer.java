package com.google.android.exoplayer2.video;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.Surface;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.FrameworkMediaCrypto;
import com.google.android.exoplayer2.mediacodec.MediaCodecInfo;
import com.google.android.exoplayer2.mediacodec.MediaCodecRenderer;
import com.google.android.exoplayer2.mediacodec.MediaCodecSelector;
import com.google.android.exoplayer2.mediacodec.MediaCodecUtil;
import com.google.android.exoplayer2.util.AmazonQuirks;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Logger;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.TraceUtil;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoRendererEventListener;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes6.dex */
@TargetApi(16)
public class MediaCodecVideoRenderer extends MediaCodecRenderer {
    private static final int[] STANDARD_LONG_EDGE_VIDEO_PX = {1920, 1600, 1440, 1280, 960, 854, 640, 540, 480};
    private final long allowedJoiningTimeMs;
    private CodecMaxValues codecMaxValues;
    private boolean codecNeedsSetOutputSurfaceWorkaround;
    private int consecutiveDroppedFrameCount;
    private final Context context;
    private int currentHeight;
    private float currentPixelWidthHeightRatio;
    private int currentUnappliedRotationDegrees;
    private int currentWidth;
    private final boolean deviceNeedsAutoFrcWorkaround;
    private long droppedFrameAccumulationStartTimeMs;
    private int droppedFrames;
    private Surface dummySurface;
    private final VideoRendererEventListener.EventDispatcher eventDispatcher;
    private final VideoFrameReleaseTimeHelper frameReleaseTimeHelper;
    private long joiningDeadlineMs;
    private final Logger log;
    private final int maxDroppedFramesToNotify;
    private long outputStreamOffsetUs;
    private int pendingOutputStreamOffsetCount;
    private final long[] pendingOutputStreamOffsetsUs;
    private float pendingPixelWidthHeightRatio;
    private int pendingRotationDegrees;
    private boolean renderedFirstFrame;
    private int reportedHeight;
    private float reportedPixelWidthHeightRatio;
    private int reportedUnappliedRotationDegrees;
    private int reportedWidth;
    private int scalingMode;
    private Format[] streamFormats;
    private Surface surface;
    private boolean tunneling;
    private int tunnelingAudioSessionId;
    OnFrameRenderedListenerV23 tunnelingOnFrameRenderedListener;

    private static boolean isBufferLate(long j) {
        return j < -30000;
    }

    public MediaCodecVideoRenderer(Context context, MediaCodecSelector mediaCodecSelector, long j, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, boolean z, Handler handler, VideoRendererEventListener videoRendererEventListener, int i) {
        super(2, mediaCodecSelector, drmSessionManager, z);
        this.log = new Logger(Logger.Module.Video, "MediaCodecVideoRenderer");
        this.allowedJoiningTimeMs = j;
        this.maxDroppedFramesToNotify = i;
        this.context = context.getApplicationContext();
        if (AmazonQuirks.isSnappingToVsyncDisabled()) {
            this.frameReleaseTimeHelper = new VideoFrameReleaseTimeHelper();
        } else {
            this.frameReleaseTimeHelper = new VideoFrameReleaseTimeHelper(context);
        }
        this.eventDispatcher = new VideoRendererEventListener.EventDispatcher(handler, videoRendererEventListener);
        this.deviceNeedsAutoFrcWorkaround = deviceNeedsAutoFrcWorkaround();
        this.pendingOutputStreamOffsetsUs = new long[10];
        this.outputStreamOffsetUs = -9223372036854775807L;
        this.joiningDeadlineMs = -9223372036854775807L;
        this.currentWidth = -1;
        this.currentHeight = -1;
        this.currentPixelWidthHeightRatio = -1.0f;
        this.pendingPixelWidthHeightRatio = -1.0f;
        this.scalingMode = 1;
        clearReportedVideoSize();
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    protected int supportsFormat(MediaCodecSelector mediaCodecSelector, Format format) throws MediaCodecUtil.DecoderQueryException {
        boolean z;
        int i;
        int i2;
        String str = format.sampleMimeType;
        if (!MimeTypes.isVideo(str)) {
            return 0;
        }
        DrmInitData drmInitData = format.drmInitData;
        if (drmInitData != null) {
            z = false;
            for (int i3 = 0; i3 < drmInitData.schemeDataCount; i3++) {
                z |= drmInitData.get(i3).requiresSecureDecryption;
            }
        } else {
            z = false;
        }
        MediaCodecInfo decoderInfo = mediaCodecSelector.getDecoderInfo(str, z);
        if (decoderInfo == null) {
            return 1;
        }
        boolean zIsCodecSupported = decoderInfo.isCodecSupported(format.codecs);
        if (zIsCodecSupported && (i = format.width) > 0 && (i2 = format.height) > 0) {
            if (Util.SDK_INT >= 21) {
                zIsCodecSupported = decoderInfo.isVideoSizeAndRateSupportedV21(i, i2, format.frameRate);
            } else {
                boolean z2 = i * i2 <= MediaCodecUtil.maxH264DecodableFrameSize();
                if (!z2) {
                    Log.d("MediaCodecVideoRenderer", "FalseCheck [legacyFrameSize, " + format.width + "x" + format.height + "] [" + Util.DEVICE_DEBUG_INFO + "]");
                }
                zIsCodecSupported = z2;
            }
        }
        return (zIsCodecSupported ? 4 : 3) | (decoderInfo.adaptive ? 16 : 8) | (decoderInfo.tunneling ? 32 : 0);
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.BaseRenderer
    protected void onEnabled(boolean z) throws ExoPlaybackException {
        super.onEnabled(z);
        int i = getConfiguration().tunnelingAudioSessionId;
        this.tunnelingAudioSessionId = i;
        this.tunneling = i != 0;
        this.eventDispatcher.enabled(this.decoderCounters);
        this.frameReleaseTimeHelper.enable();
    }

    @Override // com.google.android.exoplayer2.BaseRenderer
    protected void onStreamChanged(Format[] formatArr, long j) throws ExoPlaybackException {
        this.streamFormats = formatArr;
        if (this.outputStreamOffsetUs == -9223372036854775807L) {
            this.outputStreamOffsetUs = j;
        } else {
            int i = this.pendingOutputStreamOffsetCount;
            if (i == this.pendingOutputStreamOffsetsUs.length) {
                Log.w("MediaCodecVideoRenderer", "Too many stream changes, so dropping offset: " + this.pendingOutputStreamOffsetsUs[this.pendingOutputStreamOffsetCount - 1]);
            } else {
                this.pendingOutputStreamOffsetCount = i + 1;
            }
            this.pendingOutputStreamOffsetsUs[this.pendingOutputStreamOffsetCount - 1] = j;
        }
        super.onStreamChanged(formatArr, j);
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.BaseRenderer
    protected void onPositionReset(long j, boolean z) throws ExoPlaybackException {
        super.onPositionReset(j, z);
        clearRenderedFirstFrame();
        this.consecutiveDroppedFrameCount = 0;
        int i = this.pendingOutputStreamOffsetCount;
        if (i != 0) {
            this.outputStreamOffsetUs = this.pendingOutputStreamOffsetsUs[i - 1];
            this.pendingOutputStreamOffsetCount = 0;
        }
        if (z) {
            setJoiningDeadlineMs();
        } else {
            this.joiningDeadlineMs = -9223372036854775807L;
        }
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.Renderer
    public boolean isReady() {
        Surface surface;
        if (super.isReady() && (this.renderedFirstFrame || (((surface = this.dummySurface) != null && this.surface == surface) || getCodec() == null || this.tunneling))) {
            this.joiningDeadlineMs = -9223372036854775807L;
            return true;
        }
        if (this.joiningDeadlineMs == -9223372036854775807L) {
            return false;
        }
        if (SystemClock.elapsedRealtime() < this.joiningDeadlineMs) {
            return true;
        }
        this.joiningDeadlineMs = -9223372036854775807L;
        return false;
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.BaseRenderer
    protected void onStarted() {
        super.onStarted();
        this.droppedFrames = 0;
        this.droppedFrameAccumulationStartTimeMs = SystemClock.elapsedRealtime();
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.BaseRenderer
    protected void onStopped() {
        this.joiningDeadlineMs = -9223372036854775807L;
        maybeNotifyDroppedFrames();
        super.onStopped();
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.BaseRenderer
    protected void onDisabled() {
        this.currentWidth = -1;
        this.currentHeight = -1;
        this.currentPixelWidthHeightRatio = -1.0f;
        this.pendingPixelWidthHeightRatio = -1.0f;
        this.outputStreamOffsetUs = -9223372036854775807L;
        this.pendingOutputStreamOffsetCount = 0;
        clearReportedVideoSize();
        clearRenderedFirstFrame();
        this.frameReleaseTimeHelper.disable();
        this.tunnelingOnFrameRenderedListener = null;
        this.tunneling = false;
        try {
            super.onDisabled();
        } finally {
            this.decoderCounters.ensureUpdated();
            this.eventDispatcher.disabled(this.decoderCounters);
        }
    }

    @Override // com.google.android.exoplayer2.BaseRenderer, com.google.android.exoplayer2.ExoPlayer.ExoPlayerComponent
    public void handleMessage(int i, Object obj) throws ExoPlaybackException {
        if (i == 1) {
            setSurface((Surface) obj);
            return;
        }
        if (i == 4) {
            this.scalingMode = ((Integer) obj).intValue();
            MediaCodec codec = getCodec();
            if (codec != null) {
                setVideoScalingMode(codec, this.scalingMode);
                return;
            }
            return;
        }
        super.handleMessage(i, obj);
    }

    private void setSurface(Surface surface) throws ExoPlaybackException {
        if (surface == null) {
            Surface surface2 = this.dummySurface;
            if (surface2 != null) {
                surface = surface2;
            } else {
                MediaCodecInfo codecInfo = getCodecInfo();
                if (codecInfo != null && shouldUseDummySurface(codecInfo.secure)) {
                    surface = DummySurface.newInstanceV17(this.context, codecInfo.secure);
                    this.dummySurface = surface;
                }
            }
        }
        if (this.surface != surface) {
            this.surface = surface;
            int state = getState();
            if (state == 1 || state == 2) {
                MediaCodec codec = getCodec();
                if (Util.SDK_INT >= 23 && codec != null && surface != null && !this.codecNeedsSetOutputSurfaceWorkaround) {
                    setOutputSurfaceV23(codec, surface);
                } else {
                    releaseCodec();
                    maybeInitCodec();
                }
            }
            if (surface != null && surface != this.dummySurface) {
                maybeRenotifyVideoSizeChanged();
                clearRenderedFirstFrame();
                if (state == 2) {
                    setJoiningDeadlineMs();
                    return;
                }
                return;
            }
            clearReportedVideoSize();
            clearRenderedFirstFrame();
            return;
        }
        if (surface == null || surface == this.dummySurface) {
            return;
        }
        maybeRenotifyVideoSizeChanged();
        maybeRenotifyRenderedFirstFrame();
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    protected boolean shouldInitCodec(MediaCodecInfo mediaCodecInfo) {
        return this.surface != null || shouldUseDummySurface(mediaCodecInfo.secure);
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    protected void configureCodec(MediaCodecInfo mediaCodecInfo, MediaCodec mediaCodec, Format format, MediaCrypto mediaCrypto) throws MediaCodecUtil.DecoderQueryException {
        CodecMaxValues codecMaxValues = getCodecMaxValues(mediaCodecInfo, format, this.streamFormats);
        this.codecMaxValues = codecMaxValues;
        MediaFormat mediaFormat = getMediaFormat(format, codecMaxValues, this.deviceNeedsAutoFrcWorkaround, this.codecName, this.tunnelingAudioSessionId);
        if (this.surface == null) {
            Assertions.checkState(shouldUseDummySurface(mediaCodecInfo.secure));
            if (this.dummySurface == null) {
                this.dummySurface = DummySurface.newInstanceV17(this.context, mediaCodecInfo.secure);
            }
            this.surface = this.dummySurface;
        }
        this.log.setTAG(this.codecName + "-MediaCodecVideoRenderer");
        this.log.i("configureCodec: codecName = " + mediaCodec + " deviceNeedsAutoFrcWorkaround = " + this.deviceNeedsAutoFrcWorkaround + " format = " + format + " surface = " + this.surface + " crypto = " + mediaCrypto);
        mediaCodec.configure(mediaFormat, this.surface, mediaCrypto, 0);
        if (Util.SDK_INT < 23 || !this.tunneling) {
            return;
        }
        this.tunnelingOnFrameRenderedListener = new OnFrameRenderedListenerV23(mediaCodec);
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    protected void releaseCodec() {
        try {
            super.releaseCodec();
            Surface surface = this.dummySurface;
            if (surface != null) {
                if (this.surface == surface) {
                    this.surface = null;
                }
                surface.release();
                this.dummySurface = null;
            }
        } catch (Throwable th) {
            if (this.dummySurface != null) {
                Surface surface2 = this.surface;
                Surface surface3 = this.dummySurface;
                if (surface2 == surface3) {
                    this.surface = null;
                }
                surface3.release();
                this.dummySurface = null;
            }
            throw th;
        }
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    protected void onCodecInitialized(String str, long j, long j2) {
        this.eventDispatcher.decoderInitialized(str, j, j2);
        this.codecNeedsSetOutputSurfaceWorkaround = codecNeedsSetOutputSurfaceWorkaround(str);
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    protected void onInputFormatChanged(Format format) throws ExoPlaybackException {
        super.onInputFormatChanged(format);
        this.log.i("onInputFormatChanged: format = " + format);
        this.eventDispatcher.inputFormatChanged(format);
        this.pendingPixelWidthHeightRatio = getPixelWidthHeightRatio(format);
        this.pendingRotationDegrees = getRotationDegrees(format);
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    protected void onQueueInputBuffer(DecoderInputBuffer decoderInputBuffer) {
        if (Util.SDK_INT >= 23 || !this.tunneling) {
            return;
        }
        maybeNotifyRenderedFirstFrame();
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    protected void onOutputFormatChanged(MediaCodec mediaCodec, MediaFormat mediaFormat) {
        int integer;
        int integer2;
        this.log.i("onOutputFormatChanged: outputFormat:" + mediaFormat + ", codec:" + mediaCodec);
        boolean z = mediaFormat.containsKey("crop-right") && mediaFormat.containsKey("crop-left") && mediaFormat.containsKey("crop-bottom") && mediaFormat.containsKey("crop-top");
        if (z) {
            integer = (mediaFormat.getInteger("crop-right") - mediaFormat.getInteger("crop-left")) + 1;
        } else {
            integer = mediaFormat.getInteger("width");
        }
        this.currentWidth = integer;
        if (z) {
            integer2 = (mediaFormat.getInteger("crop-bottom") - mediaFormat.getInteger("crop-top")) + 1;
        } else {
            integer2 = mediaFormat.getInteger("height");
        }
        this.currentHeight = integer2;
        this.currentPixelWidthHeightRatio = this.pendingPixelWidthHeightRatio;
        this.log.i("hasCrop = " + z + " currentWidth = " + this.currentWidth + " currentHeight = " + this.currentHeight + " currentPixelWidthHeightRatio = " + this.currentPixelWidthHeightRatio);
        if (Util.SDK_INT >= 21) {
            int i = this.pendingRotationDegrees;
            if (i == 90 || i == 270) {
                int i2 = this.currentWidth;
                this.currentWidth = this.currentHeight;
                this.currentHeight = i2;
                this.currentPixelWidthHeightRatio = 1.0f / this.currentPixelWidthHeightRatio;
            }
        } else {
            this.currentUnappliedRotationDegrees = this.pendingRotationDegrees;
        }
        setVideoScalingMode(mediaCodec, this.scalingMode);
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    protected boolean canReconfigureCodec(MediaCodec mediaCodec, boolean z, Format format, Format format2) {
        if (areAdaptationCompatible(z, format, format2)) {
            int i = format2.width;
            CodecMaxValues codecMaxValues = this.codecMaxValues;
            if (i <= codecMaxValues.width && format2.height <= codecMaxValues.height && getMaxInputSize(format2) <= this.codecMaxValues.inputSize) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    protected boolean processOutputBuffer(long j, long j2, MediaCodec mediaCodec, ByteBuffer byteBuffer, int i, int i2, long j3, boolean z) {
        while (true) {
            int i3 = this.pendingOutputStreamOffsetCount;
            if (i3 == 0) {
                break;
            }
            long[] jArr = this.pendingOutputStreamOffsetsUs;
            long j4 = jArr[0];
            if (j3 < j4) {
                break;
            }
            this.outputStreamOffsetUs = j4;
            int i4 = i3 - 1;
            this.pendingOutputStreamOffsetCount = i4;
            System.arraycopy(jArr, 1, jArr, 0, i4);
        }
        long j5 = j3 - this.outputStreamOffsetUs;
        if (this.log.allowDebug()) {
            this.log.d("processOutputBuffer: positionUs = " + j + " elapsedRealtimeUs = " + j2 + " bufferIndex = " + i + " shouldSkip = " + z + " presentationTimeUs = " + j3);
        }
        if (z) {
            skipOutputBuffer(mediaCodec, i, j5);
            return true;
        }
        long j6 = j3 - j;
        if (this.surface == this.dummySurface) {
            if (!isBufferLate(j6)) {
                return false;
            }
            skipOutputBuffer(mediaCodec, i, j5);
            return true;
        }
        if (!this.renderedFirstFrame) {
            if (Util.SDK_INT >= 21) {
                renderOutputBufferV21(mediaCodec, i, j5, System.nanoTime());
            } else {
                renderOutputBuffer(mediaCodec, i, j5);
            }
            return true;
        }
        if (getState() != 2) {
            return false;
        }
        long jElapsedRealtime = j6 - ((SystemClock.elapsedRealtime() * 1000) - j2);
        long jNanoTime = System.nanoTime();
        long jAdjustReleaseTime = this.frameReleaseTimeHelper.adjustReleaseTime(j3, (jElapsedRealtime * 1000) + jNanoTime);
        long j7 = (jAdjustReleaseTime - jNanoTime) / 1000;
        if (shouldDropOutputBuffer(j7, j2)) {
            dropOutputBuffer(mediaCodec, i, j5);
            return true;
        }
        if (Util.SDK_INT >= 21) {
            if (j7 >= 50000) {
                return false;
            }
            renderOutputBufferV21(mediaCodec, i, j5, jAdjustReleaseTime);
            return true;
        }
        if (j7 >= 30000) {
            return false;
        }
        if (j7 > 11000) {
            try {
                Thread.sleep((j7 - 10000) / 1000);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        }
        renderOutputBuffer(mediaCodec, i, j5);
        return true;
    }

    protected boolean shouldDropOutputBuffer(long j, long j2) {
        return isBufferLate(j);
    }

    protected void skipOutputBuffer(MediaCodec mediaCodec, int i, long j) {
        this.log.i("skipOutputBuffer: bufferIndex = " + i + " PTS = " + j);
        TraceUtil.beginSection("skipVideoBuffer");
        mediaCodec.releaseOutputBuffer(i, false);
        TraceUtil.endSection();
        DecoderCounters decoderCounters = this.decoderCounters;
        decoderCounters.skippedOutputBufferCount = decoderCounters.skippedOutputBufferCount + 1;
    }

    protected void dropOutputBuffer(MediaCodec mediaCodec, int i, long j) {
        this.log.i("dropOutputBuffer: bufferIndex = " + i + " PTS = " + j);
        TraceUtil.beginSection("dropVideoBuffer");
        mediaCodec.releaseOutputBuffer(i, false);
        TraceUtil.endSection();
        DecoderCounters decoderCounters = this.decoderCounters;
        decoderCounters.droppedOutputBufferCount = decoderCounters.droppedOutputBufferCount + 1;
        this.droppedFrames++;
        int i2 = this.consecutiveDroppedFrameCount + 1;
        this.consecutiveDroppedFrameCount = i2;
        decoderCounters.maxConsecutiveDroppedOutputBufferCount = Math.max(i2, decoderCounters.maxConsecutiveDroppedOutputBufferCount);
        if (this.droppedFrames == this.maxDroppedFramesToNotify) {
            maybeNotifyDroppedFrames();
        }
    }

    protected void renderOutputBuffer(MediaCodec mediaCodec, int i, long j) {
        if (this.log.allowDebug()) {
            this.log.d("renderOutputBuffer: " + i + " PTS = " + j);
        }
        maybeNotifyVideoSizeChanged();
        TraceUtil.beginSection("releaseOutputBuffer");
        mediaCodec.releaseOutputBuffer(i, true);
        TraceUtil.endSection();
        this.decoderCounters.renderedOutputBufferCount++;
        this.consecutiveDroppedFrameCount = 0;
        maybeNotifyRenderedFirstFrame();
    }

    @TargetApi(21)
    protected void renderOutputBufferV21(MediaCodec mediaCodec, int i, long j, long j2) {
        if (this.log.allowDebug()) {
            this.log.d("renderOutputBufferV21: bufferIndex = " + i + " PTS = " + j + " releaseTimeNs = " + j2);
        }
        maybeNotifyVideoSizeChanged();
        TraceUtil.beginSection("releaseOutputBuffer");
        mediaCodec.releaseOutputBuffer(i, j2);
        TraceUtil.endSection();
        this.decoderCounters.renderedOutputBufferCount++;
        this.consecutiveDroppedFrameCount = 0;
        maybeNotifyRenderedFirstFrame();
    }

    private boolean shouldUseDummySurface(boolean z) {
        return Util.SDK_INT >= 23 && !this.tunneling && (!z || DummySurface.isSecureSupported(this.context));
    }

    private void setJoiningDeadlineMs() {
        this.joiningDeadlineMs = this.allowedJoiningTimeMs > 0 ? SystemClock.elapsedRealtime() + this.allowedJoiningTimeMs : -9223372036854775807L;
    }

    private void clearRenderedFirstFrame() {
        MediaCodec codec;
        this.renderedFirstFrame = false;
        if (Util.SDK_INT < 23 || !this.tunneling || (codec = getCodec()) == null) {
            return;
        }
        this.tunnelingOnFrameRenderedListener = new OnFrameRenderedListenerV23(codec);
    }

    void maybeNotifyRenderedFirstFrame() {
        if (this.renderedFirstFrame) {
            return;
        }
        this.renderedFirstFrame = true;
        this.eventDispatcher.renderedFirstFrame(this.surface);
    }

    private void maybeRenotifyRenderedFirstFrame() {
        if (this.renderedFirstFrame) {
            this.eventDispatcher.renderedFirstFrame(this.surface);
        }
    }

    private void clearReportedVideoSize() {
        this.reportedWidth = -1;
        this.reportedHeight = -1;
        this.reportedPixelWidthHeightRatio = -1.0f;
        this.reportedUnappliedRotationDegrees = -1;
    }

    private void maybeNotifyVideoSizeChanged() {
        int i = this.currentWidth;
        if (i == -1 && this.currentHeight == -1) {
            return;
        }
        if (this.reportedWidth == i && this.reportedHeight == this.currentHeight && this.reportedUnappliedRotationDegrees == this.currentUnappliedRotationDegrees && this.reportedPixelWidthHeightRatio == this.currentPixelWidthHeightRatio) {
            return;
        }
        this.eventDispatcher.videoSizeChanged(i, this.currentHeight, this.currentUnappliedRotationDegrees, this.currentPixelWidthHeightRatio);
        this.reportedWidth = this.currentWidth;
        this.reportedHeight = this.currentHeight;
        this.reportedUnappliedRotationDegrees = this.currentUnappliedRotationDegrees;
        this.reportedPixelWidthHeightRatio = this.currentPixelWidthHeightRatio;
    }

    private void maybeRenotifyVideoSizeChanged() {
        int i = this.reportedWidth;
        if (i == -1 && this.reportedHeight == -1) {
            return;
        }
        this.eventDispatcher.videoSizeChanged(i, this.reportedHeight, this.reportedUnappliedRotationDegrees, this.reportedPixelWidthHeightRatio);
    }

    private void maybeNotifyDroppedFrames() {
        if (this.droppedFrames > 0) {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            this.eventDispatcher.droppedFrames(this.droppedFrames, jElapsedRealtime - this.droppedFrameAccumulationStartTimeMs);
            this.droppedFrames = 0;
            this.droppedFrameAccumulationStartTimeMs = jElapsedRealtime;
        }
    }

    @TargetApi(23)
    private static void setOutputSurfaceV23(MediaCodec mediaCodec, Surface surface) {
        mediaCodec.setOutputSurface(surface);
    }

    @TargetApi(21)
    private static void configureTunnelingV21(MediaFormat mediaFormat, int i) {
        mediaFormat.setFeatureEnabled("tunneled-playback", true);
        mediaFormat.setInteger("audio-session-id", i);
    }

    protected CodecMaxValues getCodecMaxValues(MediaCodecInfo mediaCodecInfo, Format format, Format[] formatArr) throws MediaCodecUtil.DecoderQueryException {
        int iMax = format.width;
        int iMax2 = format.height;
        int maxInputSize = getMaxInputSize(format);
        if (formatArr.length == 1) {
            return new CodecMaxValues(iMax, iMax2, maxInputSize);
        }
        boolean z = false;
        for (Format format2 : formatArr) {
            if (areAdaptationCompatible(mediaCodecInfo.adaptive, format, format2)) {
                int i = format2.width;
                z |= i == -1 || format2.height == -1;
                iMax = Math.max(iMax, i);
                iMax2 = Math.max(iMax2, format2.height);
                maxInputSize = Math.max(maxInputSize, getMaxInputSize(format2));
            }
        }
        if (z) {
            Log.w("MediaCodecVideoRenderer", "Resolutions unknown. Codec max resolution: " + iMax + "x" + iMax2);
            Point codecMaxSize = getCodecMaxSize(mediaCodecInfo, format);
            if (codecMaxSize != null) {
                iMax = Math.max(iMax, codecMaxSize.x);
                iMax2 = Math.max(iMax2, codecMaxSize.y);
                maxInputSize = Math.max(maxInputSize, getMaxInputSize(format.sampleMimeType, iMax, iMax2));
                Log.w("MediaCodecVideoRenderer", "Codec max resolution adjusted to: " + iMax + "x" + iMax2);
            }
        }
        return new CodecMaxValues(iMax, iMax2, maxInputSize);
    }

    @SuppressLint({"InlinedApi"})
    protected MediaFormat getMediaFormat(Format format, CodecMaxValues codecMaxValues, boolean z, String str, int i) {
        MediaFormat frameworkMediaFormatV16 = format.getFrameworkMediaFormatV16();
        frameworkMediaFormatV16.setInteger("max-width", codecMaxValues.width);
        frameworkMediaFormatV16.setInteger("max-height", codecMaxValues.height);
        int i2 = codecMaxValues.inputSize;
        if (i2 != -1 && AmazonQuirks.isMaxInputSizeSupported(str, i2)) {
            frameworkMediaFormatV16.setInteger("max-input-size", codecMaxValues.inputSize);
        }
        if (z) {
            frameworkMediaFormatV16.setInteger("auto-frc", 0);
        }
        if (i != 0) {
            configureTunnelingV21(frameworkMediaFormatV16, i);
        }
        return frameworkMediaFormatV16;
    }

    private static Point getCodecMaxSize(MediaCodecInfo mediaCodecInfo, Format format) throws MediaCodecUtil.DecoderQueryException {
        int i = format.height;
        int i2 = format.width;
        boolean z = i > i2;
        int i3 = z ? i : i2;
        if (z) {
            i = i2;
        }
        float f = i / i3;
        for (int i4 : STANDARD_LONG_EDGE_VIDEO_PX) {
            int i5 = (int) (i4 * f);
            if (i4 <= i3 || i5 <= i) {
                break;
            }
            if (Util.SDK_INT >= 21) {
                int i6 = z ? i5 : i4;
                if (!z) {
                    i4 = i5;
                }
                Point pointAlignVideoSizeV21 = mediaCodecInfo.alignVideoSizeV21(i6, i4);
                if (mediaCodecInfo.isVideoSizeAndRateSupportedV21(pointAlignVideoSizeV21.x, pointAlignVideoSizeV21.y, format.frameRate)) {
                    return pointAlignVideoSizeV21;
                }
            } else {
                int iCeilDivide = Util.ceilDivide(i4, 16) * 16;
                int iCeilDivide2 = Util.ceilDivide(i5, 16) * 16;
                if (iCeilDivide * iCeilDivide2 <= MediaCodecUtil.maxH264DecodableFrameSize()) {
                    int i7 = z ? iCeilDivide2 : iCeilDivide;
                    if (!z) {
                        iCeilDivide = iCeilDivide2;
                    }
                    return new Point(i7, iCeilDivide);
                }
            }
        }
        return null;
    }

    private static int getMaxInputSize(Format format) {
        if (format.maxInputSize != -1) {
            int size = format.initializationData.size();
            int length = 0;
            for (int i = 0; i < size; i++) {
                length += format.initializationData.get(i).length;
            }
            return format.maxInputSize + length;
        }
        return getMaxInputSize(format.sampleMimeType, format.width, format.height);
    }

    private static int getMaxInputSize(String str, int i, int i2) {
        int i3;
        int iCeilDivide;
        if (i == -1 || i2 == -1) {
            return -1;
        }
        str.hashCode();
        i3 = 4;
        switch (str) {
            case "video/3gpp":
            case "video/mp4v-es":
            case "video/x-vnd.on2.vp8":
                iCeilDivide = i * i2;
                i3 = 2;
                break;
            case "video/hevc":
            case "video/x-vnd.on2.vp9":
                iCeilDivide = i * i2;
                break;
            case "video/avc":
                if (!"BRAVIA 4K 2015".equals(Util.MODEL)) {
                    iCeilDivide = Util.ceilDivide(i, 16) * Util.ceilDivide(i2, 16) * 16 * 16;
                    i3 = 2;
                    break;
                }
                break;
        }
        return -1;
    }

    private static void setVideoScalingMode(MediaCodec mediaCodec, int i) {
        mediaCodec.setVideoScalingMode(i);
    }

    private static boolean deviceNeedsAutoFrcWorkaround() {
        return Util.SDK_INT <= 22 && "foster".equals(Util.DEVICE) && "NVIDIA".equals(Util.MANUFACTURER);
    }

    private static boolean codecNeedsSetOutputSurfaceWorkaround(String str) {
        String str2 = Util.DEVICE;
        return (("deb".equals(str2) || "flo".equals(str2)) && "OMX.qcom.video.decoder.avc".equals(str)) || ("tcl_eu".equals(str2) && "OMX.MTK.VIDEO.DECODER.AVC".equals(str)) || AmazonQuirks.codecNeedsSetOutputSurfaceWorkaround();
    }

    private static boolean areAdaptationCompatible(boolean z, Format format, Format format2) {
        return format.sampleMimeType.equals(format2.sampleMimeType) && getRotationDegrees(format) == getRotationDegrees(format2) && (z || (format.width == format2.width && format.height == format2.height));
    }

    private static float getPixelWidthHeightRatio(Format format) {
        float f = format.pixelWidthHeightRatio;
        if (f == -1.0f) {
            return 1.0f;
        }
        return f;
    }

    private static int getRotationDegrees(Format format) {
        int i = format.rotationDegrees;
        if (i == -1) {
            return 0;
        }
        return i;
    }

    protected static final class CodecMaxValues {
        public final int height;
        public final int inputSize;
        public final int width;

        public CodecMaxValues(int i, int i2, int i3) {
            this.width = i;
            this.height = i2;
            this.inputSize = i3;
        }
    }

    @TargetApi(23)
    private final class OnFrameRenderedListenerV23 implements MediaCodec.OnFrameRenderedListener {
        private OnFrameRenderedListenerV23(MediaCodec mediaCodec) {
            mediaCodec.setOnFrameRenderedListener(this, new Handler());
        }

        @Override // android.media.MediaCodec.OnFrameRenderedListener
        public void onFrameRendered(MediaCodec mediaCodec, long j, long j2) {
            MediaCodecVideoRenderer mediaCodecVideoRenderer = MediaCodecVideoRenderer.this;
            if (this != mediaCodecVideoRenderer.tunnelingOnFrameRenderedListener) {
                return;
            }
            mediaCodecVideoRenderer.maybeNotifyRenderedFirstFrame();
        }
    }
}
