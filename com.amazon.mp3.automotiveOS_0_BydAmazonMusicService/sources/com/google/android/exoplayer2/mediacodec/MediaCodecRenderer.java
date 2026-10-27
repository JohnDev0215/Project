package com.google.android.exoplayer2.mediacodec;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.exoplayer2.BaseRenderer;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.drm.DrmSession;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.FrameworkMediaCrypto;
import com.google.android.exoplayer2.mediacodec.MediaCodecUtil;
import com.google.android.exoplayer2.util.AmazonQuirks;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Logger;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.TraceUtil;
import com.google.android.exoplayer2.util.Util;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
@TargetApi(16)
public abstract class MediaCodecRenderer extends BaseRenderer {
    private static final byte[] ADAPTATION_WORKAROUND_BUFFER = Util.getBytesFromHexString("0000016742C00BDA259000000168CE0F13200000016588840DCE7118A0002FBF1C31C3275D78");
    private final DecoderInputBuffer buffer;
    private MediaCodec codec;
    private int codecAdaptationWorkaroundMode;
    private long codecHotswapDeadlineMs;
    private MediaCodecInfo codecInfo;
    protected String codecName;
    private boolean codecNeedsAdaptationWorkaroundBuffer;
    private boolean codecNeedsDiscardToSpsWorkaround;
    private boolean codecNeedsEosFlushWorkaround;
    private boolean codecNeedsEosOutputExceptionWorkaround;
    private boolean codecNeedsEosPropagationWorkaround;
    private boolean codecNeedsFlushWorkaround;
    private boolean codecNeedsMonoChannelCountWorkaround;
    private boolean codecReceivedBuffers;
    private boolean codecReceivedEos;
    private int codecReconfigurationState;
    private boolean codecReconfigured;
    private int codecReinitializationState;
    private final List<Long> decodeOnlyPresentationTimestamps;
    protected DecoderCounters decoderCounters;
    private DrmSession<FrameworkMediaCrypto> drmSession;
    private final DrmSessionManager<FrameworkMediaCrypto> drmSessionManager;
    private final DecoderInputBuffer flagsOnlyBuffer;
    private Format format;
    private final FormatHolder formatHolder;
    private ByteBuffer[] inputBuffers;
    private int inputIndex;
    private boolean inputStreamEnded;
    private final Logger log;
    private final MediaCodecSelector mediaCodecSelector;
    private final MediaCodec.BufferInfo outputBufferInfo;
    private ByteBuffer[] outputBuffers;
    private int outputIndex;
    private boolean outputStreamEnded;
    private DrmSession<FrameworkMediaCrypto> pendingDrmSession;
    private final boolean playClearSamplesWithoutKeys;
    private boolean shouldSkipAdaptationWorkaroundOutputBuffer;
    private boolean shouldSkipOutputBuffer;
    private boolean waitingForFirstSyncFrame;
    private boolean waitingForKeys;

    protected boolean canReconfigureCodec(MediaCodec mediaCodec, boolean z, Format format, Format format2) {
        return false;
    }

    protected abstract void configureCodec(MediaCodecInfo mediaCodecInfo, MediaCodec mediaCodec, Format format, MediaCrypto mediaCrypto) throws MediaCodecUtil.DecoderQueryException;

    protected long getDequeueOutputBufferTimeoutUs() {
        return 0L;
    }

    protected void onCodecInitialized(String str, long j, long j2) {
    }

    protected void onOutputFormatChanged(MediaCodec mediaCodec, MediaFormat mediaFormat) throws ExoPlaybackException {
    }

    protected void onProcessedOutputBuffer(long j) {
    }

    protected void onQueueInputBuffer(DecoderInputBuffer decoderInputBuffer) {
    }

    @Override // com.google.android.exoplayer2.BaseRenderer
    protected void onStarted() {
    }

    @Override // com.google.android.exoplayer2.BaseRenderer
    protected void onStopped() {
    }

    protected abstract boolean processOutputBuffer(long j, long j2, MediaCodec mediaCodec, ByteBuffer byteBuffer, int i, int i2, long j3, boolean z) throws ExoPlaybackException;

    protected void renderToEndOfStream() throws ExoPlaybackException {
    }

    protected boolean shouldInitCodec(MediaCodecInfo mediaCodecInfo) {
        return true;
    }

    protected abstract int supportsFormat(MediaCodecSelector mediaCodecSelector, Format format) throws MediaCodecUtil.DecoderQueryException;

    @Override // com.google.android.exoplayer2.BaseRenderer, com.google.android.exoplayer2.RendererCapabilities
    public final int supportsMixedMimeTypeAdaptation() {
        return 8;
    }

    public static class DecoderInitializationException extends Exception {
        public final String decoderName;
        public final String diagnosticInfo;
        public final String mimeType;
        public final boolean secureDecoderRequired;

        public DecoderInitializationException(Format format, Throwable th, boolean z, int i) {
            super("Decoder init failed: [" + i + "], " + format, th);
            this.mimeType = format.sampleMimeType;
            this.secureDecoderRequired = z;
            this.decoderName = null;
            this.diagnosticInfo = buildCustomDiagnosticInfo(i);
        }

        public DecoderInitializationException(Format format, Throwable th, boolean z, String str) {
            super("Decoder init failed: " + str + ", " + format, th);
            this.mimeType = format.sampleMimeType;
            this.secureDecoderRequired = z;
            this.decoderName = str;
            this.diagnosticInfo = Util.SDK_INT >= 21 ? getDiagnosticInfoV21(th) : null;
        }

        @TargetApi(21)
        private static String getDiagnosticInfoV21(Throwable th) {
            if (th instanceof MediaCodec.CodecException) {
                return ((MediaCodec.CodecException) th).getDiagnosticInfo();
            }
            return null;
        }

        private static String buildCustomDiagnosticInfo(int i) {
            return "com.google.android.exoplayer.MediaCodecTrackRenderer_" + (i < 0 ? "neg_" : "") + Math.abs(i);
        }
    }

    public MediaCodecRenderer(int i, MediaCodecSelector mediaCodecSelector, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, boolean z) {
        super(i);
        this.log = new Logger(Logger.Module.AudioVideoCommon, "MediaCodecRenderer");
        this.codecName = "CodecNameUnknown";
        Assertions.checkState(Util.SDK_INT >= 16);
        this.mediaCodecSelector = (MediaCodecSelector) Assertions.checkNotNull(mediaCodecSelector);
        this.drmSessionManager = drmSessionManager;
        this.playClearSamplesWithoutKeys = z;
        this.buffer = new DecoderInputBuffer(0);
        this.flagsOnlyBuffer = DecoderInputBuffer.newFlagsOnlyInstance();
        this.formatHolder = new FormatHolder();
        this.decodeOnlyPresentationTimestamps = new ArrayList();
        this.outputBufferInfo = new MediaCodec.BufferInfo();
        this.codecReconfigurationState = 0;
        this.codecReinitializationState = 0;
    }

    @Override // com.google.android.exoplayer2.RendererCapabilities
    public final int supportsFormat(Format format) throws ExoPlaybackException {
        try {
            int iSupportsFormat = supportsFormat(this.mediaCodecSelector, format);
            return (iSupportsFormat & 7) > 2 ? !isDrmSchemeSupported(this.drmSessionManager, format.drmInitData) ? (iSupportsFormat & (-8)) | 2 : iSupportsFormat : iSupportsFormat;
        } catch (MediaCodecUtil.DecoderQueryException e) {
            throw ExoPlaybackException.createForRenderer(e, getIndex());
        }
    }

    protected MediaCodecInfo getDecoderInfo(MediaCodecSelector mediaCodecSelector, Format format, boolean z) throws MediaCodecUtil.DecoderQueryException {
        return mediaCodecSelector.getDecoderInfo(format.sampleMimeType, z);
    }

    protected final void maybeInitCodec() throws ExoPlaybackException {
        Format format;
        boolean zRequiresSecureDecoderComponent;
        MediaCrypto wrappedMediaCrypto;
        if (this.codec != null || (format = this.format) == null) {
            return;
        }
        this.drmSession = this.pendingDrmSession;
        String str = format.sampleMimeType;
        this.log.i("mimeType = " + str);
        DrmSession<FrameworkMediaCrypto> drmSession = this.drmSession;
        if (drmSession != null) {
            FrameworkMediaCrypto frameworkMediaCrypto = (FrameworkMediaCrypto) drmSession.getMediaCrypto();
            if (frameworkMediaCrypto == null) {
                this.drmSession.getError();
                return;
            } else if (AmazonQuirks.waitForDRMKeysBeforeInitCodec() && this.drmSession.getState() != 4) {
                this.log.i("DRM Keys not available yet...deferring codec init");
                return;
            } else {
                wrappedMediaCrypto = frameworkMediaCrypto.getWrappedMediaCrypto();
                zRequiresSecureDecoderComponent = frameworkMediaCrypto.requiresSecureDecoderComponent(str);
            }
        } else {
            zRequiresSecureDecoderComponent = false;
            wrappedMediaCrypto = null;
        }
        if (this.codecInfo == null) {
            this.log.i("requiresSecureDecoder = " + zRequiresSecureDecoderComponent);
            try {
                MediaCodecInfo decoderInfo = getDecoderInfo(this.mediaCodecSelector, this.format, zRequiresSecureDecoderComponent);
                this.codecInfo = decoderInfo;
                if (decoderInfo == null && zRequiresSecureDecoderComponent) {
                    MediaCodecInfo decoderInfo2 = getDecoderInfo(this.mediaCodecSelector, this.format, false);
                    this.codecInfo = decoderInfo2;
                    if (decoderInfo2 != null) {
                        Log.w("MediaCodecRenderer", "Drm session requires secure decoder for " + str + ", but no secure decoder available. Trying to proceed with " + this.codecInfo.name + ".");
                    }
                }
            } catch (MediaCodecUtil.DecoderQueryException e) {
                throwDecoderInitError(new DecoderInitializationException(this.format, e, zRequiresSecureDecoderComponent, -49998));
            }
            if (this.codecInfo == null) {
                throwDecoderInitError(new DecoderInitializationException(this.format, (Throwable) null, zRequiresSecureDecoderComponent, -49999));
            }
        }
        if (shouldInitCodec(this.codecInfo)) {
            String str2 = this.codecInfo.name;
            this.codecName = str2;
            this.codecAdaptationWorkaroundMode = codecAdaptationWorkaroundMode(str2);
            this.codecNeedsDiscardToSpsWorkaround = codecNeedsDiscardToSpsWorkaround(this.codecName, this.format);
            this.codecNeedsFlushWorkaround = codecNeedsFlushWorkaround(this.codecName);
            this.codecNeedsEosPropagationWorkaround = codecNeedsEosPropagationWorkaround(this.codecName);
            this.codecNeedsEosFlushWorkaround = codecNeedsEosFlushWorkaround(this.codecName);
            this.codecNeedsEosOutputExceptionWorkaround = codecNeedsEosOutputExceptionWorkaround(this.codecName);
            this.codecNeedsMonoChannelCountWorkaround = codecNeedsMonoChannelCountWorkaround(this.codecName, this.format);
            try {
                long jElapsedRealtime = SystemClock.elapsedRealtime();
                TraceUtil.beginSection("createCodec:" + this.codecName);
                this.codec = MediaCodec.createByCodecName(this.codecName);
                TraceUtil.endSection();
                TraceUtil.beginSection("configureCodec");
                configureCodec(this.codecInfo, this.codec, this.format, wrappedMediaCrypto);
                TraceUtil.endSection();
                TraceUtil.beginSection("startCodec");
                this.codec.start();
                TraceUtil.endSection();
                long jElapsedRealtime2 = SystemClock.elapsedRealtime();
                onCodecInitialized(this.codecName, jElapsedRealtime2, jElapsedRealtime2 - jElapsedRealtime);
                this.inputBuffers = this.codec.getInputBuffers();
                this.outputBuffers = this.codec.getOutputBuffers();
            } catch (Exception e2) {
                throwDecoderInitError(new DecoderInitializationException(this.format, e2, zRequiresSecureDecoderComponent, this.codecName));
            }
            this.log.setTAG(this.codecName + "-MediaCodecRenderer");
            if (MimeTypes.isAudio(str)) {
                this.log.setModule(Logger.Module.Audio);
            } else {
                this.log.setModule(Logger.Module.Video);
            }
            Logger logger = this.log;
            StringBuilder sb = new StringBuilder();
            sb.append("Created Decoder ");
            sb.append(this.codecInfo.adaptive ? " and Codec is Adaptive " : " and Codec is NOT Adaptive");
            logger.i(sb.toString());
            this.codecHotswapDeadlineMs = getState() == 2 ? SystemClock.elapsedRealtime() + 1000 : -9223372036854775807L;
            this.inputIndex = -1;
            this.outputIndex = -1;
            this.waitingForFirstSyncFrame = true;
            this.decoderCounters.decoderInitCount++;
        }
    }

    private void throwDecoderInitError(DecoderInitializationException decoderInitializationException) throws ExoPlaybackException {
        throw ExoPlaybackException.createForRenderer(decoderInitializationException, getIndex());
    }

    protected final MediaCodec getCodec() {
        return this.codec;
    }

    protected final MediaCodecInfo getCodecInfo() {
        return this.codecInfo;
    }

    @Override // com.google.android.exoplayer2.BaseRenderer
    protected void onEnabled(boolean z) throws ExoPlaybackException {
        this.decoderCounters = new DecoderCounters();
    }

    @Override // com.google.android.exoplayer2.BaseRenderer
    protected void onPositionReset(long j, boolean z) throws ExoPlaybackException {
        this.log.i("onPositionReset: " + j + ", joining: " + z);
        this.inputStreamEnded = false;
        this.outputStreamEnded = false;
        if (this.codec != null) {
            flushCodec();
        }
    }

    @Override // com.google.android.exoplayer2.BaseRenderer
    protected void onDisabled() {
        this.format = null;
        try {
            releaseCodec();
            try {
                DrmSession<FrameworkMediaCrypto> drmSession = this.drmSession;
                if (drmSession != null) {
                    this.drmSessionManager.releaseSession(drmSession);
                }
                try {
                    DrmSession<FrameworkMediaCrypto> drmSession2 = this.pendingDrmSession;
                    if (drmSession2 != null && drmSession2 != this.drmSession) {
                        this.drmSessionManager.releaseSession(drmSession2);
                    }
                } finally {
                }
            } catch (Throwable th) {
                try {
                    DrmSession<FrameworkMediaCrypto> drmSession3 = this.pendingDrmSession;
                    if (drmSession3 != null && drmSession3 != this.drmSession) {
                        this.drmSessionManager.releaseSession(drmSession3);
                    }
                    throw th;
                } finally {
                }
            }
        } catch (Throwable th2) {
            try {
                if (this.drmSession != null) {
                    this.drmSessionManager.releaseSession(this.drmSession);
                }
                try {
                    DrmSession<FrameworkMediaCrypto> drmSession4 = this.pendingDrmSession;
                    if (drmSession4 != null && drmSession4 != this.drmSession) {
                        this.drmSessionManager.releaseSession(drmSession4);
                    }
                    throw th2;
                } finally {
                }
            } catch (Throwable th3) {
                try {
                    DrmSession<FrameworkMediaCrypto> drmSession5 = this.pendingDrmSession;
                    if (drmSession5 != null && drmSession5 != this.drmSession) {
                        this.drmSessionManager.releaseSession(drmSession5);
                    }
                    throw th3;
                } finally {
                }
            }
        }
    }

    protected void releaseCodec() {
        this.log.i("releaseCodec");
        this.codecHotswapDeadlineMs = -9223372036854775807L;
        this.inputIndex = -1;
        this.outputIndex = -1;
        this.waitingForKeys = false;
        this.shouldSkipOutputBuffer = false;
        this.decodeOnlyPresentationTimestamps.clear();
        this.inputBuffers = null;
        this.outputBuffers = null;
        this.codecInfo = null;
        this.codecReconfigured = false;
        this.codecReceivedBuffers = false;
        this.codecNeedsDiscardToSpsWorkaround = false;
        this.codecNeedsFlushWorkaround = false;
        this.codecAdaptationWorkaroundMode = 0;
        this.codecNeedsEosPropagationWorkaround = false;
        this.codecNeedsEosFlushWorkaround = false;
        this.codecNeedsMonoChannelCountWorkaround = false;
        this.codecNeedsAdaptationWorkaroundBuffer = false;
        this.shouldSkipAdaptationWorkaroundOutputBuffer = false;
        this.codecReceivedEos = false;
        this.codecReconfigurationState = 0;
        this.codecReinitializationState = 0;
        this.buffer.data = null;
        MediaCodec mediaCodec = this.codec;
        if (mediaCodec != null) {
            this.decoderCounters.decoderReleaseCount++;
            try {
                mediaCodec.stop();
                try {
                    this.codec.release();
                    this.codec = null;
                    DrmSession<FrameworkMediaCrypto> drmSession = this.drmSession;
                    if (drmSession == null || this.pendingDrmSession == drmSession) {
                        return;
                    }
                    try {
                        this.drmSessionManager.releaseSession(drmSession);
                    } finally {
                    }
                } catch (Throwable th) {
                    this.codec = null;
                    DrmSession<FrameworkMediaCrypto> drmSession2 = this.drmSession;
                    if (drmSession2 != null && this.pendingDrmSession != drmSession2) {
                        try {
                            this.drmSessionManager.releaseSession(drmSession2);
                        } finally {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                try {
                    this.codec.release();
                    this.codec = null;
                    DrmSession<FrameworkMediaCrypto> drmSession3 = this.drmSession;
                    if (drmSession3 != null && this.pendingDrmSession != drmSession3) {
                        try {
                            this.drmSessionManager.releaseSession(drmSession3);
                        } finally {
                        }
                    }
                    throw th2;
                } catch (Throwable th3) {
                    this.codec = null;
                    DrmSession<FrameworkMediaCrypto> drmSession4 = this.drmSession;
                    if (drmSession4 != null && this.pendingDrmSession != drmSession4) {
                        try {
                            this.drmSessionManager.releaseSession(drmSession4);
                        } finally {
                        }
                    }
                    throw th3;
                }
            }
        }
    }

    @Override // com.google.android.exoplayer2.Renderer
    public void render(long j, long j2) throws ExoPlaybackException {
        if (this.outputStreamEnded) {
            renderToEndOfStream();
            return;
        }
        if (this.log.allowVerbose()) {
            this.log.v("render: positionUs = " + j + " elapsedRealtimeUs = " + j2);
        }
        if (this.format == null) {
            this.flagsOnlyBuffer.clear();
            int source = readSource(this.formatHolder, this.flagsOnlyBuffer, true);
            if (source != -5) {
                if (source == -4) {
                    Assertions.checkState(this.flagsOnlyBuffer.isEndOfStream());
                    this.inputStreamEnded = true;
                    processEndOfStream();
                    return;
                }
                return;
            }
            onInputFormatChanged(this.formatHolder.format);
        }
        maybeInitCodec();
        if (this.codec != null) {
            TraceUtil.beginSection("drainAndFeed");
            while (drainOutputBuffer(j, j2)) {
            }
            while (feedInputBuffer()) {
            }
            TraceUtil.endSection();
        } else {
            skipSource(j);
            this.flagsOnlyBuffer.clear();
            int source2 = readSource(this.formatHolder, this.flagsOnlyBuffer, false);
            if (source2 == -5) {
                onInputFormatChanged(this.formatHolder.format);
            } else if (source2 == -4) {
                Assertions.checkState(this.flagsOnlyBuffer.isEndOfStream());
                this.inputStreamEnded = true;
                processEndOfStream();
            }
        }
        this.decoderCounters.ensureUpdated();
    }

    protected void flushCodec() throws ExoPlaybackException {
        this.codecHotswapDeadlineMs = -9223372036854775807L;
        this.inputIndex = -1;
        this.outputIndex = -1;
        this.waitingForFirstSyncFrame = true;
        this.waitingForKeys = false;
        this.shouldSkipOutputBuffer = false;
        this.decodeOnlyPresentationTimestamps.clear();
        this.codecNeedsAdaptationWorkaroundBuffer = false;
        this.shouldSkipAdaptationWorkaroundOutputBuffer = false;
        if (this.codecNeedsFlushWorkaround || ((this.codecNeedsEosFlushWorkaround && this.codecReceivedEos) || this.codecReinitializationState != 0)) {
            releaseCodec();
            maybeInitCodec();
        } else {
            this.codec.flush();
            this.codecReceivedBuffers = false;
        }
        if (!this.codecReconfigured || this.format == null) {
            return;
        }
        this.codecReconfigurationState = 1;
    }

    private boolean feedInputBuffer() throws ExoPlaybackException {
        int iPosition;
        int source;
        MediaCodec mediaCodec = this.codec;
        if (mediaCodec == null || this.codecReinitializationState == 2 || this.inputStreamEnded) {
            return false;
        }
        if (this.inputIndex < 0) {
            int iDequeueInputBuffer = mediaCodec.dequeueInputBuffer(0L);
            this.inputIndex = iDequeueInputBuffer;
            if (iDequeueInputBuffer < 0) {
                if (this.log.allowVerbose()) {
                    this.log.v("dequeueInputBuffer returned " + this.inputIndex + "... returning false");
                }
                return false;
            }
            if (this.log.allowDebug()) {
                this.log.d("dequeueInputBuffer returned " + this.inputIndex);
            }
            DecoderInputBuffer decoderInputBuffer = this.buffer;
            decoderInputBuffer.data = this.inputBuffers[this.inputIndex];
            decoderInputBuffer.clear();
        }
        if (this.codecReinitializationState == 1) {
            if (!this.codecNeedsEosPropagationWorkaround) {
                this.codecReceivedEos = true;
                this.codec.queueInputBuffer(this.inputIndex, 0, 0, 0L, 4);
                this.inputIndex = -1;
            }
            this.codecReinitializationState = 2;
            return false;
        }
        if (this.codecNeedsAdaptationWorkaroundBuffer) {
            this.codecNeedsAdaptationWorkaroundBuffer = false;
            ByteBuffer byteBuffer = this.buffer.data;
            byte[] bArr = ADAPTATION_WORKAROUND_BUFFER;
            byteBuffer.put(bArr);
            this.codec.queueInputBuffer(this.inputIndex, 0, bArr.length, 0L, 0);
            this.inputIndex = -1;
            this.codecReceivedBuffers = true;
            return true;
        }
        if (this.waitingForKeys) {
            this.log.i("We've already read an encrypted sample into sampleHolder, and are waiting for keys");
            source = -4;
            iPosition = 0;
        } else {
            if (this.codecReconfigurationState == 1) {
                this.log.i("Appending reconfiguration data at start of the buffer");
                for (int i = 0; i < this.format.initializationData.size(); i++) {
                    this.buffer.data.put(this.format.initializationData.get(i));
                }
                this.codecReconfigurationState = 2;
            }
            iPosition = this.buffer.data.position();
            source = readSource(this.formatHolder, this.buffer, false);
        }
        if (source == -3) {
            return false;
        }
        if (source == -5) {
            this.log.i("Source returned SampleSource.FORMAT_READ");
            if (this.codecReconfigurationState == 2) {
                this.log.i("We received two formats in a row.");
                this.buffer.clear();
                this.codecReconfigurationState = 1;
            }
            onInputFormatChanged(this.formatHolder.format);
            return true;
        }
        if (this.buffer.isEndOfStream()) {
            this.log.i("Reached end of buffer");
            if (this.codecReconfigurationState == 2) {
                this.buffer.clear();
                this.codecReconfigurationState = 1;
            }
            this.inputStreamEnded = true;
            if (!this.codecReceivedBuffers) {
                processEndOfStream();
                return false;
            }
            try {
                if (!this.codecNeedsEosPropagationWorkaround) {
                    this.codecReceivedEos = true;
                    if (this.log.allowDebug()) {
                        this.log.d("queueInputBuffer: inputIndex = " + this.inputIndex + "flag = BUFFER_FLAG_END_OF_STREAM");
                    }
                    this.codec.queueInputBuffer(this.inputIndex, 0, 0, 0L, 4);
                    this.inputIndex = -1;
                }
                return false;
            } catch (MediaCodec.CryptoException e) {
                throw ExoPlaybackException.createForRenderer(e, getIndex());
            }
        }
        if (this.waitingForFirstSyncFrame && !this.buffer.isKeyFrame()) {
            this.buffer.clear();
            if (this.codecReconfigurationState == 2) {
                this.codecReconfigurationState = 1;
            }
            return true;
        }
        this.waitingForFirstSyncFrame = false;
        boolean zIsEncrypted = this.buffer.isEncrypted();
        boolean zShouldWaitForKeys = shouldWaitForKeys(zIsEncrypted);
        this.waitingForKeys = zShouldWaitForKeys;
        if (zShouldWaitForKeys) {
            this.log.i("Waiting For Keys!!!");
            return false;
        }
        if (this.codecNeedsDiscardToSpsWorkaround && !zIsEncrypted) {
            NalUnitUtil.discardToSps(this.buffer.data);
            if (this.buffer.data.position() == 0) {
                return true;
            }
            this.codecNeedsDiscardToSpsWorkaround = false;
        }
        try {
            DecoderInputBuffer decoderInputBuffer2 = this.buffer;
            long j = decoderInputBuffer2.timeUs;
            if (decoderInputBuffer2.isDecodeOnly()) {
                this.decodeOnlyPresentationTimestamps.add(Long.valueOf(j));
            }
            this.buffer.flip();
            onQueueInputBuffer(this.buffer);
            if (zIsEncrypted) {
                MediaCodec.CryptoInfo frameworkCryptoInfo = getFrameworkCryptoInfo(this.buffer, iPosition);
                if (this.log.allowDebug()) {
                    this.log.d("queueSecureInputBuffer: inputIndex = " + this.inputIndex + " presentationTimeUs = " + j);
                }
                this.codec.queueSecureInputBuffer(this.inputIndex, 0, frameworkCryptoInfo, j, 0);
            } else {
                if (this.log.allowDebug()) {
                    this.log.d("queueInputBuffer: inputIndex = " + this.inputIndex + "bufferSize = " + this.buffer.data.limit() + "presentationTimeUs = " + j);
                }
                this.codec.queueInputBuffer(this.inputIndex, 0, this.buffer.data.limit(), j, 0);
            }
            this.inputIndex = -1;
            this.codecReceivedBuffers = true;
            this.codecReconfigurationState = 0;
            this.decoderCounters.inputBufferCount++;
            return true;
        } catch (MediaCodec.CryptoException e2) {
            throw ExoPlaybackException.createForRenderer(e2, getIndex());
        }
    }

    private static MediaCodec.CryptoInfo getFrameworkCryptoInfo(DecoderInputBuffer decoderInputBuffer, int i) {
        MediaCodec.CryptoInfo frameworkCryptoInfoV16 = decoderInputBuffer.cryptoInfo.getFrameworkCryptoInfoV16();
        if (i == 0) {
            return frameworkCryptoInfoV16;
        }
        if (frameworkCryptoInfoV16.numBytesOfClearData == null) {
            frameworkCryptoInfoV16.numBytesOfClearData = new int[1];
        }
        int[] iArr = frameworkCryptoInfoV16.numBytesOfClearData;
        iArr[0] = iArr[0] + i;
        return frameworkCryptoInfoV16;
    }

    private boolean shouldWaitForKeys(boolean z) throws ExoPlaybackException {
        DrmSession<FrameworkMediaCrypto> drmSession = this.drmSession;
        if (drmSession == null || (!z && this.playClearSamplesWithoutKeys)) {
            return false;
        }
        int state = drmSession.getState();
        if (state != 1) {
            return state != 4;
        }
        this.drmSession.getError();
        throw ExoPlaybackException.createForRenderer(null, getIndex());
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0093  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onInputFormatChanged(com.google.android.exoplayer2.Format r5) throws com.google.android.exoplayer2.ExoPlaybackException {
        /*
            r4 = this;
            com.google.android.exoplayer2.util.Logger r0 = r4.log
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "onInputFormatChanged: format = "
            r1.append(r2)
            com.google.android.exoplayer2.FormatHolder r2 = r4.formatHolder
            com.google.android.exoplayer2.Format r2 = r2.format
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.i(r1)
            com.google.android.exoplayer2.Format r0 = r4.format
            r4.format = r5
            com.google.android.exoplayer2.drm.DrmInitData r5 = r5.drmInitData
            r1 = 0
            if (r0 != 0) goto L25
            r2 = r1
            goto L27
        L25:
            com.google.android.exoplayer2.drm.DrmInitData r2 = r0.drmInitData
        L27:
            boolean r5 = com.google.android.exoplayer2.util.Util.areEqual(r5, r2)
            r2 = 1
            r5 = r5 ^ r2
            if (r5 == 0) goto L63
            com.google.android.exoplayer2.Format r5 = r4.format
            com.google.android.exoplayer2.drm.DrmInitData r5 = r5.drmInitData
            if (r5 == 0) goto L61
            com.google.android.exoplayer2.drm.DrmSessionManager<com.google.android.exoplayer2.drm.FrameworkMediaCrypto> r5 = r4.drmSessionManager
            if (r5 == 0) goto L51
            android.os.Looper r1 = android.os.Looper.myLooper()
            com.google.android.exoplayer2.Format r3 = r4.format
            com.google.android.exoplayer2.drm.DrmInitData r3 = r3.drmInitData
            com.google.android.exoplayer2.drm.DrmSession r5 = r5.acquireSession(r1, r3)
            r4.pendingDrmSession = r5
            com.google.android.exoplayer2.drm.DrmSession<com.google.android.exoplayer2.drm.FrameworkMediaCrypto> r1 = r4.drmSession
            if (r5 != r1) goto L63
            com.google.android.exoplayer2.drm.DrmSessionManager<com.google.android.exoplayer2.drm.FrameworkMediaCrypto> r1 = r4.drmSessionManager
            r1.releaseSession(r5)
            goto L63
        L51:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "Media requires a DrmSessionManager"
            r5.<init>(r0)
            int r0 = r4.getIndex()
            com.google.android.exoplayer2.ExoPlaybackException r5 = com.google.android.exoplayer2.ExoPlaybackException.createForRenderer(r5, r0)
            throw r5
        L61:
            r4.pendingDrmSession = r1
        L63:
            com.google.android.exoplayer2.drm.DrmSession<com.google.android.exoplayer2.drm.FrameworkMediaCrypto> r5 = r4.pendingDrmSession
            com.google.android.exoplayer2.drm.DrmSession<com.google.android.exoplayer2.drm.FrameworkMediaCrypto> r1 = r4.drmSession
            if (r5 != r1) goto L97
            android.media.MediaCodec r5 = r4.codec
            if (r5 == 0) goto L97
            com.google.android.exoplayer2.mediacodec.MediaCodecInfo r1 = r4.codecInfo
            boolean r1 = r1.adaptive
            com.google.android.exoplayer2.Format r3 = r4.format
            boolean r5 = r4.canReconfigureCodec(r5, r1, r0, r3)
            if (r5 == 0) goto L97
            r4.codecReconfigured = r2
            r4.codecReconfigurationState = r2
            int r5 = r4.codecAdaptationWorkaroundMode
            r1 = 2
            if (r5 == r1) goto L94
            if (r5 != r2) goto L93
            com.google.android.exoplayer2.Format r5 = r4.format
            int r1 = r5.width
            int r3 = r0.width
            if (r1 != r3) goto L93
            int r5 = r5.height
            int r0 = r0.height
            if (r5 != r0) goto L93
            goto L94
        L93:
            r2 = 0
        L94:
            r4.codecNeedsAdaptationWorkaroundBuffer = r2
            goto Lab
        L97:
            boolean r5 = r4.codecReceivedBuffers
            if (r5 == 0) goto L9e
            r4.codecReinitializationState = r2
            goto Lab
        L9e:
            com.google.android.exoplayer2.util.Logger r5 = r4.log
            java.lang.String r0 = "releasing and reiniting codec"
            r5.i(r0)
            r4.releaseCodec()
            r4.maybeInitCodec()
        Lab:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.mediacodec.MediaCodecRenderer.onInputFormatChanged(com.google.android.exoplayer2.Format):void");
    }

    @Override // com.google.android.exoplayer2.Renderer
    public boolean isEnded() {
        return this.outputStreamEnded;
    }

    @Override // com.google.android.exoplayer2.Renderer
    public boolean isReady() {
        return (this.format == null || this.waitingForKeys || (!isSourceReady() && this.outputIndex < 0 && (this.codecHotswapDeadlineMs == -9223372036854775807L || SystemClock.elapsedRealtime() >= this.codecHotswapDeadlineMs))) ? false : true;
    }

    private boolean drainOutputBuffer(long j, long j2) throws ExoPlaybackException {
        boolean zProcessOutputBuffer;
        if (this.log.allowVerbose()) {
            this.log.v("drainOutputBuffer: positionUs = " + j + " elapsedRealtimeUs = " + j2);
        }
        if (this.outputIndex < 0) {
            if (this.codecNeedsEosOutputExceptionWorkaround && this.codecReceivedEos) {
                try {
                    this.outputIndex = this.codec.dequeueOutputBuffer(this.outputBufferInfo, getDequeueOutputBufferTimeoutUs());
                } catch (IllegalStateException unused) {
                    processEndOfStream();
                    if (this.outputStreamEnded) {
                        releaseCodec();
                    }
                    return false;
                }
            } else {
                this.outputIndex = this.codec.dequeueOutputBuffer(this.outputBufferInfo, getDequeueOutputBufferTimeoutUs());
            }
            int i = this.outputIndex;
            if (i < 0) {
                if (i == -2) {
                    this.log.i("INFO_OUTPUT_FORMAT_CHANGED");
                    processOutputFormat();
                    return true;
                }
                if (i == -3) {
                    this.log.i("INFO_OUTPUT_BUFFERS_CHANGED");
                    processOutputBuffersChanged();
                    return true;
                }
                if (this.codecNeedsEosPropagationWorkaround && (this.inputStreamEnded || this.codecReinitializationState == 2)) {
                    this.log.i("dequeueOutputBuffer: processEndOfStream will be called while codecNeedsEosPropagationWorkaround is set.");
                    processEndOfStream();
                }
                return false;
            }
            if (this.shouldSkipAdaptationWorkaroundOutputBuffer) {
                this.shouldSkipAdaptationWorkaroundOutputBuffer = false;
                this.codec.releaseOutputBuffer(i, false);
                this.outputIndex = -1;
                return true;
            }
            MediaCodec.BufferInfo bufferInfo = this.outputBufferInfo;
            if ((bufferInfo.flags & 4) != 0) {
                processEndOfStream();
                this.outputIndex = -1;
                return false;
            }
            ByteBuffer byteBuffer = this.outputBuffers[i];
            if (byteBuffer != null) {
                byteBuffer.position(bufferInfo.offset);
                MediaCodec.BufferInfo bufferInfo2 = this.outputBufferInfo;
                byteBuffer.limit(bufferInfo2.offset + bufferInfo2.size);
            }
            this.shouldSkipOutputBuffer = shouldSkipOutputBuffer(this.outputBufferInfo.presentationTimeUs);
        }
        if (this.codecNeedsEosOutputExceptionWorkaround && this.codecReceivedEos) {
            try {
                MediaCodec mediaCodec = this.codec;
                ByteBuffer[] byteBufferArr = this.outputBuffers;
                int i2 = this.outputIndex;
                ByteBuffer byteBuffer2 = byteBufferArr[i2];
                MediaCodec.BufferInfo bufferInfo3 = this.outputBufferInfo;
                zProcessOutputBuffer = processOutputBuffer(j, j2, mediaCodec, byteBuffer2, i2, bufferInfo3.flags, bufferInfo3.presentationTimeUs, this.shouldSkipOutputBuffer);
            } catch (IllegalStateException unused2) {
                processEndOfStream();
                if (this.outputStreamEnded) {
                    releaseCodec();
                }
                return false;
            }
        } else {
            MediaCodec mediaCodec2 = this.codec;
            ByteBuffer[] byteBufferArr2 = this.outputBuffers;
            int i3 = this.outputIndex;
            ByteBuffer byteBuffer3 = byteBufferArr2[i3];
            MediaCodec.BufferInfo bufferInfo4 = this.outputBufferInfo;
            zProcessOutputBuffer = processOutputBuffer(j, j2, mediaCodec2, byteBuffer3, i3, bufferInfo4.flags, bufferInfo4.presentationTimeUs, this.shouldSkipOutputBuffer);
        }
        if (!zProcessOutputBuffer) {
            return false;
        }
        onProcessedOutputBuffer(this.outputBufferInfo.presentationTimeUs);
        this.outputIndex = -1;
        return true;
    }

    private void processOutputFormat() throws ExoPlaybackException {
        MediaFormat outputFormat = this.codec.getOutputFormat();
        if (this.codecAdaptationWorkaroundMode != 0 && outputFormat.getInteger("width") == 32 && outputFormat.getInteger("height") == 32) {
            this.shouldSkipAdaptationWorkaroundOutputBuffer = true;
            return;
        }
        if (this.codecNeedsMonoChannelCountWorkaround) {
            outputFormat.setInteger("channel-count", 1);
        }
        onOutputFormatChanged(this.codec, outputFormat);
    }

    private void processOutputBuffersChanged() {
        this.outputBuffers = this.codec.getOutputBuffers();
    }

    private void processEndOfStream() throws ExoPlaybackException {
        if (this.codecReinitializationState == 2) {
            releaseCodec();
            maybeInitCodec();
        } else {
            this.outputStreamEnded = true;
            renderToEndOfStream();
        }
    }

    private boolean shouldSkipOutputBuffer(long j) {
        int size = this.decodeOnlyPresentationTimestamps.size();
        for (int i = 0; i < size; i++) {
            if (this.decodeOnlyPresentationTimestamps.get(i).longValue() == j) {
                this.decodeOnlyPresentationTimestamps.remove(i);
                return true;
            }
        }
        return false;
    }

    private static boolean isDrmSchemeSupported(DrmSessionManager drmSessionManager, DrmInitData drmInitData) {
        if (drmInitData == null) {
            return true;
        }
        if (drmSessionManager == null) {
            return false;
        }
        return drmSessionManager.canAcquireSession(drmInitData);
    }

    private static boolean codecNeedsFlushWorkaround(String str) {
        int i = Util.SDK_INT;
        return i < 18 || (i == 18 && ("OMX.SEC.avc.dec".equals(str) || "OMX.SEC.avc.dec.secure".equals(str))) || (i == 19 && Util.MODEL.startsWith("SM-G800") && ("OMX.Exynos.avc.dec".equals(str) || "OMX.Exynos.avc.dec.secure".equals(str)));
    }

    private int codecAdaptationWorkaroundMode(String str) {
        int i = Util.SDK_INT;
        if (i <= 24 && "OMX.Exynos.avc.dec.secure".equals(str)) {
            String str2 = Util.MODEL;
            if (str2.startsWith("SM-T585") || str2.startsWith("SM-A520")) {
                return 2;
            }
        }
        if (i >= 24) {
            return 0;
        }
        if (!"OMX.Nvidia.h264.decode".equals(str) && !"OMX.Nvidia.h264.decode.secure".equals(str)) {
            return 0;
        }
        String str3 = Util.DEVICE;
        return ("flounder".equals(str3) || "flounder_lte".equals(str3) || "grouper".equals(str3) || "tilapia".equals(str3)) ? 1 : 0;
    }

    private static boolean codecNeedsDiscardToSpsWorkaround(String str, Format format) {
        return Util.SDK_INT < 21 && format.initializationData.isEmpty() && "OMX.MTK.VIDEO.DECODER.AVC".equals(str);
    }

    private static boolean codecNeedsEosPropagationWorkaround(String str) {
        return AmazonQuirks.codecNeedsEosPropagationWorkaround(str) || (Util.SDK_INT <= 17 && ("OMX.rk.video_decoder.avc".equals(str) || "OMX.allwinner.video.decoder.avc".equals(str)));
    }

    private static boolean codecNeedsEosFlushWorkaround(String str) {
        int i = Util.SDK_INT;
        return (i <= 23 && "OMX.google.vorbis.decoder".equals(str)) || (i <= 19 && "hb2000".equals(Util.DEVICE) && ("OMX.amlogic.avc.decoder.awesome".equals(str) || "OMX.amlogic.avc.decoder.awesome.secure".equals(str)));
    }

    private static boolean codecNeedsEosOutputExceptionWorkaround(String str) {
        return Util.SDK_INT == 21 && "OMX.google.aac.decoder".equals(str);
    }

    private static boolean codecNeedsMonoChannelCountWorkaround(String str, Format format) {
        return Util.SDK_INT <= 18 && format.channelCount == 1 && "OMX.MTK.AUDIO.DECODER.MP3".equals(str);
    }
}
