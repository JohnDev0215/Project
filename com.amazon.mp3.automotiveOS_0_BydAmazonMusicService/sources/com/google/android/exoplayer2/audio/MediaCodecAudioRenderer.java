package com.google.android.exoplayer2.audio;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Handler;
import android.view.Surface;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.audio.AudioRendererEventListener;
import com.google.android.exoplayer2.audio.AudioTrack;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.FrameworkMediaCrypto;
import com.google.android.exoplayer2.mediacodec.MediaCodecInfo;
import com.google.android.exoplayer2.mediacodec.MediaCodecRenderer;
import com.google.android.exoplayer2.mediacodec.MediaCodecSelector;
import com.google.android.exoplayer2.mediacodec.MediaCodecUtil;
import com.google.android.exoplayer2.util.AmazonQuirks;
import com.google.android.exoplayer2.util.Logger;
import com.google.android.exoplayer2.util.MediaClock;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import com.visualon.OSMPUtils.voMimeTypes;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes6.dex */
@TargetApi(16)
public class MediaCodecAudioRenderer extends MediaCodecRenderer implements MediaClock {
    private static final String TAG = "MediaCodecAudioRenderer";
    private boolean allowPositionDiscontinuity;
    private final AudioTrack audioTrack;
    private int channelCount;
    private boolean codecNeedsDiscardChannelsWorkaround;
    private long currentPositionUs;
    private final AudioRendererEventListener.EventDispatcher eventDispatcher;
    private final Logger log;
    private boolean passthroughEnabled;
    private MediaFormat passthroughMediaFormat;
    private int pcmEncoding;

    @Override // com.google.android.exoplayer2.BaseRenderer, com.google.android.exoplayer2.Renderer
    public MediaClock getMediaClock() {
        return this;
    }

    protected void onAudioSessionId(int i) {
    }

    protected void onAudioTrackPositionDiscontinuity() {
    }

    protected void onAudioTrackUnderrun(int i, long j, long j2) {
    }

    public MediaCodecAudioRenderer(MediaCodecSelector mediaCodecSelector) {
        this(mediaCodecSelector, null, true);
    }

    public MediaCodecAudioRenderer(MediaCodecSelector mediaCodecSelector, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, boolean z) {
        this(mediaCodecSelector, drmSessionManager, z, null, null);
    }

    public MediaCodecAudioRenderer(MediaCodecSelector mediaCodecSelector, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, boolean z, Handler handler, AudioRendererEventListener audioRendererEventListener) {
        this(mediaCodecSelector, drmSessionManager, z, handler, audioRendererEventListener, null, new AudioProcessor[0]);
    }

    public MediaCodecAudioRenderer(MediaCodecSelector mediaCodecSelector, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, boolean z, Handler handler, AudioRendererEventListener audioRendererEventListener, AudioCapabilities audioCapabilities, AudioProcessor... audioProcessorArr) {
        super(1, mediaCodecSelector, drmSessionManager, z);
        this.log = new Logger(Logger.Module.Audio, TAG);
        this.audioTrack = new AudioTrack(audioCapabilities, audioProcessorArr, new AudioTrackListener());
        this.eventDispatcher = new AudioRendererEventListener.EventDispatcher(handler, audioRendererEventListener);
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    protected int supportsFormat(MediaCodecSelector mediaCodecSelector, Format format) throws MediaCodecUtil.DecoderQueryException {
        int i;
        int i2;
        String str = format.sampleMimeType;
        boolean z = false;
        if (!MimeTypes.isAudio(str)) {
            return 0;
        }
        int i3 = Util.SDK_INT;
        int i4 = i3 >= 21 ? 32 : 0;
        if (allowPassthrough(str) && mediaCodecSelector.getPassthroughDecoderInfo() != null) {
            return i4 | 8 | 4;
        }
        MediaCodecInfo decoderInfo = mediaCodecSelector.getDecoderInfo(str, false);
        if (decoderInfo == null) {
            return 1;
        }
        if (i3 < 21 || (((i = format.sampleRate) == -1 || decoderInfo.isAudioSampleRateSupportedV21(i)) && ((i2 = format.channelCount) == -1 || decoderInfo.isAudioChannelCountSupportedV21(i2)))) {
            z = true;
        }
        return i4 | 8 | (z ? 4 : 3);
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    protected MediaCodecInfo getDecoderInfo(MediaCodecSelector mediaCodecSelector, Format format, boolean z) throws MediaCodecUtil.DecoderQueryException {
        MediaCodecInfo passthroughDecoderInfo;
        if (allowPassthrough(format.sampleMimeType) && AmazonQuirks.useDefaultPassthroughDecoder() && (passthroughDecoderInfo = mediaCodecSelector.getPassthroughDecoderInfo()) != null) {
            this.passthroughEnabled = true;
            return passthroughDecoderInfo;
        }
        this.passthroughEnabled = false;
        return super.getDecoderInfo(mediaCodecSelector, format, z);
    }

    protected boolean allowPassthrough(String str) {
        return this.audioTrack.isPassthroughSupported(str);
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    protected void configureCodec(MediaCodecInfo mediaCodecInfo, MediaCodec mediaCodec, Format format, MediaCrypto mediaCrypto) {
        this.log.setTAG(mediaCodecInfo.name + "-" + TAG);
        this.codecNeedsDiscardChannelsWorkaround = codecNeedsDiscardChannelsWorkaround(mediaCodecInfo.name);
        if (this.passthroughEnabled) {
            MediaFormat frameworkMediaFormatV16 = format.getFrameworkMediaFormatV16();
            this.passthroughMediaFormat = frameworkMediaFormatV16;
            frameworkMediaFormatV16.setString("mime", voMimeTypes.VOAUDIO_RAW);
            mediaCodec.configure(this.passthroughMediaFormat, (Surface) null, mediaCrypto, 0);
            this.passthroughMediaFormat.setString("mime", format.sampleMimeType);
            return;
        }
        mediaCodec.configure(format.getFrameworkMediaFormatV16(), (Surface) null, mediaCrypto, 0);
        this.passthroughMediaFormat = null;
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    protected void onCodecInitialized(String str, long j, long j2) {
        this.eventDispatcher.decoderInitialized(str, j, j2);
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    protected void onInputFormatChanged(Format format) throws ExoPlaybackException {
        super.onInputFormatChanged(format);
        this.eventDispatcher.inputFormatChanged(format);
        this.pcmEncoding = voMimeTypes.VOAUDIO_RAW.equals(format.sampleMimeType) ? format.pcmEncoding : 2;
        this.channelCount = format.channelCount;
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    protected void onOutputFormatChanged(MediaCodec mediaCodec, MediaFormat mediaFormat) throws ExoPlaybackException {
        String string;
        int[] iArr;
        int i;
        this.log.i("onOutputFormatChanged: outputFormat:" + mediaFormat + ", codec:" + mediaCodec);
        MediaFormat mediaFormat2 = this.passthroughMediaFormat;
        boolean z = mediaFormat2 != null;
        if (z) {
            mediaFormat = mediaFormat2;
        }
        if (AmazonQuirks.isAmazonDevice()) {
            string = mediaFormat.getString("mime");
        } else {
            string = z ? this.passthroughMediaFormat.getString("mime") : voMimeTypes.VOAUDIO_RAW;
        }
        String str = string;
        int integer = mediaFormat.getInteger("channel-count");
        int integer2 = mediaFormat.getInteger("sample-rate");
        if (this.codecNeedsDiscardChannelsWorkaround && integer == 6 && (i = this.channelCount) < 6) {
            iArr = new int[i];
            for (int i2 = 0; i2 < this.channelCount; i2++) {
                iArr[i2] = i2;
            }
        } else {
            iArr = null;
        }
        try {
            this.audioTrack.configure(str, integer, integer2, this.pcmEncoding, 0, iArr);
        } catch (AudioTrack.ConfigurationException e) {
            throw ExoPlaybackException.createForRenderer(e, getIndex());
        }
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.BaseRenderer
    protected void onEnabled(boolean z) throws ExoPlaybackException {
        super.onEnabled(z);
        this.eventDispatcher.enabled(this.decoderCounters);
        int i = getConfiguration().tunnelingAudioSessionId;
        if (i != 0) {
            this.audioTrack.enableTunnelingV21(i);
        } else {
            this.audioTrack.disableTunneling();
        }
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.BaseRenderer
    protected void onPositionReset(long j, boolean z) throws ExoPlaybackException {
        super.onPositionReset(j, z);
        this.audioTrack.reset();
        this.currentPositionUs = j;
        this.allowPositionDiscontinuity = true;
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.BaseRenderer
    protected void onStarted() {
        super.onStarted();
        this.audioTrack.play();
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.BaseRenderer
    protected void onStopped() {
        this.audioTrack.pause();
        super.onStopped();
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.BaseRenderer
    protected void onDisabled() {
        try {
            this.audioTrack.release();
            try {
                super.onDisabled();
            } finally {
            }
        } catch (Throwable th) {
            try {
                super.onDisabled();
                throw th;
            } finally {
            }
        }
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.Renderer
    public boolean isEnded() {
        boolean zIsEnded = super.isEnded();
        return !this.audioTrack.applyDolbyPassthroughQuirk() ? zIsEnded && this.audioTrack.isEnded() : zIsEnded;
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.Renderer
    public boolean isReady() {
        return this.audioTrack.hasPendingData() || super.isReady();
    }

    @Override // com.google.android.exoplayer2.util.MediaClock
    public long getPositionUs() {
        long currentPositionUs = this.audioTrack.getCurrentPositionUs(isEnded());
        if (currentPositionUs != Long.MIN_VALUE) {
            if (!this.allowPositionDiscontinuity) {
                currentPositionUs = Math.max(this.currentPositionUs, currentPositionUs);
            }
            this.currentPositionUs = currentPositionUs;
            this.allowPositionDiscontinuity = false;
        }
        return this.currentPositionUs;
    }

    @Override // com.google.android.exoplayer2.util.MediaClock
    public PlaybackParameters setPlaybackParameters(PlaybackParameters playbackParameters) {
        return this.audioTrack.setPlaybackParameters(playbackParameters);
    }

    @Override // com.google.android.exoplayer2.util.MediaClock
    public PlaybackParameters getPlaybackParameters() {
        return this.audioTrack.getPlaybackParameters();
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    protected boolean processOutputBuffer(long j, long j2, MediaCodec mediaCodec, ByteBuffer byteBuffer, int i, int i2, long j3, boolean z) throws ExoPlaybackException {
        if (this.log.allowDebug()) {
            this.log.d("processOutputBuffer: positionUs = " + j + " elapsedRealtimeUs =  " + j2 + " bufferIndex = " + i + " shouldSkip = " + z + " presentationTimeUs = " + j3);
        }
        if (this.passthroughEnabled && (i2 & 2) != 0) {
            mediaCodec.releaseOutputBuffer(i, false);
            return true;
        }
        if (z) {
            mediaCodec.releaseOutputBuffer(i, false);
            this.decoderCounters.skippedOutputBufferCount++;
            this.audioTrack.handleDiscontinuity();
            return true;
        }
        try {
            if (!this.audioTrack.handleBuffer(byteBuffer, j3)) {
                return false;
            }
            mediaCodec.releaseOutputBuffer(i, false);
            this.decoderCounters.renderedOutputBufferCount++;
            return true;
        } catch (AudioTrack.InitializationException | AudioTrack.WriteException e) {
            throw ExoPlaybackException.createForRenderer(e, getIndex());
        }
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    protected void renderToEndOfStream() throws ExoPlaybackException {
        try {
            this.audioTrack.playToEndOfStream();
        } catch (AudioTrack.WriteException e) {
            throw ExoPlaybackException.createForRenderer(e, getIndex());
        }
    }

    @Override // com.google.android.exoplayer2.BaseRenderer, com.google.android.exoplayer2.ExoPlayer.ExoPlayerComponent
    public void handleMessage(int i, Object obj) throws ExoPlaybackException {
        if (i == 2) {
            this.audioTrack.setVolume(((Float) obj).floatValue());
        } else if (i == 3) {
            this.audioTrack.setAudioAttributes((AudioAttributes) obj);
        } else {
            super.handleMessage(i, obj);
        }
    }

    private static boolean codecNeedsDiscardChannelsWorkaround(String str) {
        if (Util.SDK_INT < 24 && "OMX.SEC.aac.dec".equals(str) && "samsung".equals(Util.MANUFACTURER)) {
            String str2 = Util.DEVICE;
            if (str2.startsWith("zeroflte") || str2.startsWith("herolte") || str2.startsWith("heroqlte")) {
                return true;
            }
        }
        return false;
    }

    private final class AudioTrackListener implements AudioTrack.Listener {
        private AudioTrackListener() {
        }

        @Override // com.google.android.exoplayer2.audio.AudioTrack.Listener
        public void onAudioSessionId(int i) {
            MediaCodecAudioRenderer.this.eventDispatcher.audioSessionId(i);
            MediaCodecAudioRenderer.this.onAudioSessionId(i);
        }

        @Override // com.google.android.exoplayer2.audio.AudioTrack.Listener
        public void onPositionDiscontinuity() {
            MediaCodecAudioRenderer.this.onAudioTrackPositionDiscontinuity();
            MediaCodecAudioRenderer.this.allowPositionDiscontinuity = true;
        }

        @Override // com.google.android.exoplayer2.audio.AudioTrack.Listener
        public void onUnderrun(int i, long j, long j2) {
            MediaCodecAudioRenderer.this.eventDispatcher.audioTrackUnderrun(i, j, j2);
            MediaCodecAudioRenderer.this.onAudioTrackUnderrun(i, j, j2);
        }
    }
}
