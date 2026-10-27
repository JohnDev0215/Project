package org.chromium.media;

import android.media.AudioTrack;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.Surface;
import java.nio.ByteBuffer;
import org.chromium.base.Log;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.media.MediaCodecUtil;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("media")
class MediaCodecBridge {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String KEY_CROP_BOTTOM = "crop-bottom";
    private static final String KEY_CROP_LEFT = "crop-left";
    private static final String KEY_CROP_RIGHT = "crop-right";
    private static final String KEY_CROP_TOP = "crop-top";
    private static final long MAX_PRESENTATION_TIMESTAMP_SHIFT_US = 100000;
    private static final int MEDIA_CODEC_ABORT = 8;
    private static final int MEDIA_CODEC_DEQUEUE_INPUT_AGAIN_LATER = 1;
    private static final int MEDIA_CODEC_DEQUEUE_OUTPUT_AGAIN_LATER = 2;
    private static final int MEDIA_CODEC_ERROR = 9;
    private static final int MEDIA_CODEC_INPUT_END_OF_STREAM = 5;
    private static final int MEDIA_CODEC_NO_KEY = 7;
    private static final int MEDIA_CODEC_OK = 0;
    private static final int MEDIA_CODEC_OUTPUT_BUFFERS_CHANGED = 3;
    private static final int MEDIA_CODEC_OUTPUT_END_OF_STREAM = 6;
    private static final int MEDIA_CODEC_OUTPUT_FORMAT_CHANGED = 4;
    private static final int PCM16_BYTES_PER_SAMPLE = 2;
    private static final String TAG = "cr_media";
    private boolean mAdaptivePlaybackSupported;
    private AudioTrack mAudioTrack;
    private ByteBuffer[] mInputBuffers;
    private MediaCodec mMediaCodec;
    private String mMime;
    private ByteBuffer[] mOutputBuffers;
    private byte[] mPendingAudioBuffer = null;
    private long mLastPresentationTimeUs = 0;
    private boolean mFlushed = true;

    private int getAudioFormat(int i) {
        if (i == 1) {
            return 4;
        }
        if (i == 2) {
            return 12;
        }
        if (i == 4) {
            return 204;
        }
        if (i != 6) {
            return i != 8 ? 1 : 6396;
        }
        return 252;
    }

    private static class DequeueInputResult {
        private final int mIndex;
        private final int mStatus;

        private DequeueInputResult(int i, int i2) {
            this.mStatus = i;
            this.mIndex = i2;
        }

        private int status() {
            return this.mStatus;
        }

        private int index() {
            return this.mIndex;
        }
    }

    private static class DequeueOutputResult {
        private final int mFlags;
        private final int mIndex;
        private final int mNumBytes;
        private final int mOffset;
        private final long mPresentationTimeMicroseconds;
        private final int mStatus;

        private DequeueOutputResult(int i, int i2, int i3, int i4, long j, int i5) {
            this.mStatus = i;
            this.mIndex = i2;
            this.mFlags = i3;
            this.mOffset = i4;
            this.mPresentationTimeMicroseconds = j;
            this.mNumBytes = i5;
        }

        private int status() {
            return this.mStatus;
        }

        private int index() {
            return this.mIndex;
        }

        private int flags() {
            return this.mFlags;
        }

        private int offset() {
            return this.mOffset;
        }

        private long presentationTimeMicroseconds() {
            return this.mPresentationTimeMicroseconds;
        }

        private int numBytes() {
            return this.mNumBytes;
        }
    }

    private static class GetOutputFormatResult {
        private final MediaFormat mFormat;
        private final int mStatus;

        private GetOutputFormatResult(int i, MediaFormat mediaFormat) {
            this.mStatus = i;
            this.mFormat = mediaFormat;
        }

        private boolean formatHasCropValues() {
            return this.mFormat.containsKey(MediaCodecBridge.KEY_CROP_RIGHT) && this.mFormat.containsKey(MediaCodecBridge.KEY_CROP_LEFT) && this.mFormat.containsKey(MediaCodecBridge.KEY_CROP_BOTTOM) && this.mFormat.containsKey(MediaCodecBridge.KEY_CROP_TOP);
        }

        private int status() {
            return this.mStatus;
        }

        private int width() {
            return formatHasCropValues() ? (this.mFormat.getInteger(MediaCodecBridge.KEY_CROP_RIGHT) - this.mFormat.getInteger(MediaCodecBridge.KEY_CROP_LEFT)) + 1 : this.mFormat.getInteger("width");
        }

        private int height() {
            return formatHasCropValues() ? (this.mFormat.getInteger(MediaCodecBridge.KEY_CROP_BOTTOM) - this.mFormat.getInteger(MediaCodecBridge.KEY_CROP_TOP)) + 1 : this.mFormat.getInteger("height");
        }

        private int sampleRate() {
            return this.mFormat.getInteger("sample-rate");
        }

        private int channelCount() {
            return this.mFormat.getInteger("channel-count");
        }
    }

    private MediaCodecBridge(MediaCodec mediaCodec, String str, boolean z) {
        this.mMediaCodec = mediaCodec;
        this.mMime = str;
        this.mAdaptivePlaybackSupported = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static MediaCodecBridge create(String str, boolean z, int i, boolean z2) {
        MediaCodecUtil.CodecCreationInfo codecCreationInfo = new MediaCodecUtil.CodecCreationInfo();
        try {
            if (i == 1) {
                codecCreationInfo.mediaCodec = MediaCodec.createEncoderByType(str);
                codecCreationInfo.supportsAdaptivePlayback = false;
                codecCreationInfo = codecCreationInfo;
                z = z;
            } else {
                MediaCodecUtil.CodecCreationInfo codecCreationInfoCreateDecoder = MediaCodecUtil.createDecoder(str, z, z2);
                codecCreationInfo = codecCreationInfoCreateDecoder;
                z = codecCreationInfoCreateDecoder;
            }
        } catch (Exception e) {
            Log.e(TAG, "Failed to create MediaCodec: %s, isSecure: %s, direction: %d", str, Boolean.valueOf(z), Integer.valueOf(i), e);
        }
        if (codecCreationInfo.mediaCodec == null) {
            return null;
        }
        return new MediaCodecBridge(codecCreationInfo.mediaCodec, str, codecCreationInfo.supportsAdaptivePlayback);
    }

    private void release() {
        try {
            Log.w(TAG, "calling MediaCodec.release() on " + this.mMediaCodec.getName(), new Object[0]);
            this.mMediaCodec.release();
        } catch (IllegalStateException e) {
            Log.e(TAG, "Cannot release media codec", e);
        }
        this.mMediaCodec = null;
        AudioTrack audioTrack = this.mAudioTrack;
        if (audioTrack != null) {
            audioTrack.release();
        }
        this.mPendingAudioBuffer = null;
    }

    private boolean start() {
        try {
            this.mMediaCodec.start();
            return true;
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Cannot start the media codec", e);
            return false;
        } catch (IllegalStateException e2) {
            Log.e(TAG, "Cannot start the media codec", e2);
            return false;
        }
    }

    private DequeueInputResult dequeueInputBuffer(long j) {
        int i = 1;
        int i2 = 9;
        int i3 = -1;
        try {
            int iDequeueInputBuffer = this.mMediaCodec.dequeueInputBuffer(j);
            if (iDequeueInputBuffer >= 0) {
                i3 = iDequeueInputBuffer;
                i = 0;
            } else if (iDequeueInputBuffer != -1) {
                Log.e(TAG, "Unexpected index_or_status: " + iDequeueInputBuffer, new Object[0]);
                i = 9;
            }
            i2 = i;
        } catch (Exception e) {
            Log.e(TAG, "Failed to dequeue input buffer", e);
        }
        return new DequeueInputResult(i2, i3);
    }

    private int flush() {
        try {
            this.mFlushed = true;
            AudioTrack audioTrack = this.mAudioTrack;
            if (audioTrack != null) {
                audioTrack.pause();
                this.mAudioTrack.flush();
                this.mPendingAudioBuffer = null;
            }
            this.mMediaCodec.flush();
            return 0;
        } catch (IllegalStateException e) {
            Log.e(TAG, "Failed to flush MediaCodec", e);
            return 9;
        }
    }

    private void stop() {
        this.mMediaCodec.stop();
        AudioTrack audioTrack = this.mAudioTrack;
        if (audioTrack != null) {
            audioTrack.pause();
        }
    }

    private GetOutputFormatResult getOutputFormat() {
        MediaFormat outputFormat;
        int i = 0;
        try {
            outputFormat = this.mMediaCodec.getOutputFormat();
        } catch (IllegalStateException e) {
            Log.e(TAG, "Failed to get output format", e);
            i = 9;
            outputFormat = null;
        }
        return new GetOutputFormatResult(i, outputFormat);
    }

    private ByteBuffer getInputBuffer(int i) {
        try {
            return this.mMediaCodec.getInputBuffer(i);
        } catch (IllegalStateException e) {
            Log.e(TAG, "Failed to get input buffer", e);
            return null;
        }
    }

    private ByteBuffer getOutputBuffer(int i) {
        try {
            return this.mMediaCodec.getOutputBuffer(i);
        } catch (IllegalStateException e) {
            Log.e(TAG, "Failed to get output buffer", e);
            return null;
        }
    }

    private int queueInputBuffer(int i, int i2, int i3, long j, int i4) {
        resetLastPresentationTimeIfNeeded(j);
        try {
            this.mMediaCodec.queueInputBuffer(i, i2, i3, j, i4);
            return 0;
        } catch (Exception e) {
            Log.e(TAG, "Failed to queue input buffer", e);
            return 9;
        }
    }

    private void setVideoBitrate(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("video-bitrate", i);
        this.mMediaCodec.setParameters(bundle);
    }

    private void requestKeyFrameSoon() {
        Bundle bundle = new Bundle();
        bundle.putInt("request-sync", 0);
        this.mMediaCodec.setParameters(bundle);
    }

    private int queueSecureInputBuffer(int i, int i2, byte[] bArr, byte[] bArr2, int[] iArr, int[] iArr2, int i3, long j) {
        resetLastPresentationTimeIfNeeded(j);
        try {
            MediaCodec.CryptoInfo cryptoInfo = new MediaCodec.CryptoInfo();
            cryptoInfo.set(i3, iArr, iArr2, bArr2, bArr, 1);
            this.mMediaCodec.queueSecureInputBuffer(i, i2, cryptoInfo, j, 0);
            return 0;
        } catch (MediaCodec.CryptoException e) {
            if (e.getErrorCode() == 1) {
                Log.d(TAG, "Failed to queue secure input buffer: CryptoException.ERROR_NO_KEY");
                return 7;
            }
            Log.e(TAG, "Failed to queue secure input buffer, CryptoException with error code " + e.getErrorCode(), new Object[0]);
            return 9;
        } catch (IllegalStateException e2) {
            Log.e(TAG, "Failed to queue secure input buffer, IllegalStateException " + e2, new Object[0]);
            return 9;
        }
    }

    private void releaseOutputBuffer(int i, boolean z) {
        try {
            this.mMediaCodec.releaseOutputBuffer(i, z);
        } catch (IllegalStateException e) {
            Log.e(TAG, "Failed to release output buffer", e);
        }
    }

    private DequeueOutputResult dequeueOutputBuffer(long j) {
        int i;
        int iDequeueOutputBuffer;
        MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
        int i2 = 0;
        int i3 = -1;
        try {
            iDequeueOutputBuffer = this.mMediaCodec.dequeueOutputBuffer(bufferInfo, j);
            long j2 = bufferInfo.presentationTimeUs;
            long j3 = this.mLastPresentationTimeUs;
            if (j2 < j3) {
                bufferInfo.presentationTimeUs = j3;
            }
            this.mLastPresentationTimeUs = bufferInfo.presentationTimeUs;
        } catch (IllegalStateException e) {
            Log.e(TAG, "Failed to dequeue output buffer", e);
            i = 9;
        }
        if (iDequeueOutputBuffer >= 0) {
            i3 = iDequeueOutputBuffer;
        } else if (iDequeueOutputBuffer == -3) {
            this.mOutputBuffers = this.mMediaCodec.getOutputBuffers();
            i2 = 3;
        } else {
            if (iDequeueOutputBuffer == -2) {
                MediaFormat outputFormat = this.mMediaCodec.getOutputFormat();
                i2 = (this.mAudioTrack == null || !outputFormat.containsKey("sample-rate") || this.mAudioTrack.setPlaybackRate(outputFormat.getInteger("sample-rate")) == 0) ? 4 : 9;
            } else if (iDequeueOutputBuffer == -1) {
                i2 = 2;
            } else {
                Log.e(TAG, "Unexpected index_or_status: " + iDequeueOutputBuffer, new Object[0]);
            }
            return new DequeueOutputResult(i, i3, bufferInfo.flags, bufferInfo.offset, bufferInfo.presentationTimeUs, bufferInfo.size);
        }
        i = i2;
        return new DequeueOutputResult(i, i3, bufferInfo.flags, bufferInfo.offset, bufferInfo.presentationTimeUs, bufferInfo.size);
    }

    private boolean configureVideo(MediaFormat mediaFormat, Surface surface, MediaCrypto mediaCrypto, int i, boolean z) {
        if (!z) {
            try {
                this.mAdaptivePlaybackSupported = false;
            } catch (MediaCodec.CryptoException e) {
                Log.e(TAG, "Cannot configure the video codec: DRM error", e);
                return false;
            } catch (IllegalArgumentException e2) {
                Log.e(TAG, "Cannot configure the video codec, wrong format or surface", e2);
                return false;
            } catch (IllegalStateException e3) {
                Log.e(TAG, "Cannot configure the video codec", e3);
                return false;
            } catch (Exception e4) {
                Log.e(TAG, "Cannot configure the video codec", e4);
                return false;
            }
        }
        if (this.mAdaptivePlaybackSupported) {
            mediaFormat.setInteger("max-width", mediaFormat.getInteger("width"));
            mediaFormat.setInteger("max-height", mediaFormat.getInteger("height"));
        }
        maybeSetMaxInputSize(mediaFormat);
        this.mMediaCodec.configure(mediaFormat, surface, mediaCrypto, i);
        return true;
    }

    private static MediaFormat createAudioFormat(String str, int i, int i2) {
        return MediaFormat.createAudioFormat(str, i, i2);
    }

    private static MediaFormat createVideoDecoderFormat(String str, int i, int i2) {
        return MediaFormat.createVideoFormat(str, i, i2);
    }

    private void maybeSetMaxInputSize(MediaFormat mediaFormat) {
        int integer;
        int integer2;
        int i;
        int i2;
        if (mediaFormat.containsKey("max-input-size")) {
        }
        integer = mediaFormat.getInteger("height");
        if (this.mAdaptivePlaybackSupported && mediaFormat.containsKey("max-height")) {
            integer = Math.max(integer, mediaFormat.getInteger("max-height"));
        }
        integer2 = mediaFormat.getInteger("width");
        if (this.mAdaptivePlaybackSupported && mediaFormat.containsKey("max-width")) {
            integer2 = Math.max(integer, mediaFormat.getInteger("max-width"));
        }
        String string = mediaFormat.getString("mime");
        string.hashCode();
        switch (string) {
            case "video/hevc":
            case "video/x-vnd.on2.vp9":
                i = integer2 * integer;
                i2 = 4;
                mediaFormat.setInteger("max-input-size", (i * 3) / (i2 * 2));
                break;
            case "video/avc":
                if (!"BRAVIA 4K 2015".equals(Build.MODEL)) {
                    i = ((integer2 + 15) / 16) * ((integer + 15) / 16) * 16 * 16;
                    i2 = 2;
                    mediaFormat.setInteger("max-input-size", (i * 3) / (i2 * 2));
                    break;
                }
                break;
            case "video/x-vnd.on2.vp8":
                i = integer2 * integer;
                i2 = 2;
                mediaFormat.setInteger("max-input-size", (i * 3) / (i2 * 2));
                break;
        }
    }

    private static MediaFormat createVideoEncoderFormat(String str, int i, int i2, int i3, int i4, int i5, int i6) {
        MediaFormat mediaFormatCreateVideoFormat = MediaFormat.createVideoFormat(str, i, i2);
        mediaFormatCreateVideoFormat.setInteger("bitrate", i3);
        mediaFormatCreateVideoFormat.setInteger("frame-rate", i4);
        mediaFormatCreateVideoFormat.setInteger("i-frame-interval", i5);
        mediaFormatCreateVideoFormat.setInteger("color-format", i6);
        return mediaFormatCreateVideoFormat;
    }

    private boolean isAdaptivePlaybackSupported(int i, int i2) {
        return this.mAdaptivePlaybackSupported;
    }

    private static void setCodecSpecificData(MediaFormat mediaFormat, int i, byte[] bArr) {
        String str = i != 0 ? i != 1 ? i != 2 ? null : "csd-2" : "csd-1" : "csd-0";
        if (str != null) {
            mediaFormat.setByteBuffer(str, ByteBuffer.wrap(bArr));
        }
    }

    private static void setFrameHasADTSHeader(MediaFormat mediaFormat) {
        mediaFormat.setInteger("is-adts", 1);
    }

    private boolean configureAudio(MediaFormat mediaFormat, MediaCrypto mediaCrypto, int i, boolean z) {
        try {
            this.mMediaCodec.configure(mediaFormat, (Surface) null, mediaCrypto, i);
            if (z) {
                if (!createAudioTrack(mediaFormat.getInteger("sample-rate"), mediaFormat.getInteger("channel-count"))) {
                    return false;
                }
            }
            return true;
        } catch (MediaCodec.CryptoException e) {
            Log.e(TAG, "Cannot configure the audio codec: DRM error", e);
            return false;
        } catch (IllegalArgumentException e2) {
            Log.e(TAG, "Cannot configure the audio codec", e2);
            return false;
        } catch (IllegalStateException e3) {
            Log.e(TAG, "Cannot configure the audio codec", e3);
            return false;
        } catch (Exception e4) {
            Log.e(TAG, "Cannot configure the audio codec", e4);
            return false;
        }
    }

    private boolean createAudioTrack(int i, int i2) {
        Log.v(TAG, "createAudioTrack: sampleRate:" + i + " channelCount:" + i2);
        int audioFormat = getAudioFormat(i2);
        int minBufferSize = ((int) (((double) ((AudioTrack.getMinBufferSize(i, audioFormat, 2) / 2) / i2)) * 1.5d)) * 2 * i2;
        AudioTrack audioTrack = this.mAudioTrack;
        if (audioTrack != null) {
            audioTrack.release();
        }
        AudioTrack audioTrack2 = new AudioTrack(3, i, audioFormat, 2, minBufferSize, 1);
        this.mAudioTrack = audioTrack2;
        if (audioTrack2.getState() != 0) {
            return true;
        }
        Log.e(TAG, "Cannot create AudioTrack", new Object[0]);
        this.mAudioTrack = null;
        return false;
    }

    private long playOutputBuffer(byte[] bArr, boolean z) {
        AudioTrack audioTrack = this.mAudioTrack;
        if (audioTrack == null) {
            return 0L;
        }
        if (z) {
            this.mPendingAudioBuffer = bArr;
            return 0L;
        }
        if (3 != audioTrack.getPlayState()) {
            this.mAudioTrack.play();
        }
        byte[] bArr2 = this.mPendingAudioBuffer;
        if (bArr2 != null) {
            int iWrite = this.mAudioTrack.write(bArr2, 0, bArr2.length);
            if (this.mPendingAudioBuffer.length != iWrite) {
                Log.i(TAG, "Failed to send all data to audio output, expected size: " + this.mPendingAudioBuffer.length + ", actual size: " + iWrite, new Object[0]);
            }
            this.mPendingAudioBuffer = null;
        }
        int iWrite2 = this.mAudioTrack.write(bArr, 0, bArr.length);
        if (bArr.length != iWrite2) {
            Log.i(TAG, "Failed to send all data to audio output, expected size: " + bArr.length + ", actual size: " + iWrite2, new Object[0]);
        }
        return ((long) this.mAudioTrack.getPlaybackHeadPosition()) & 4294967295L;
    }

    private void setVolume(double d) {
        AudioTrack audioTrack = this.mAudioTrack;
        if (audioTrack != null) {
            float f = (float) d;
            audioTrack.setStereoVolume(f, f);
        }
    }

    private void resetLastPresentationTimeIfNeeded(long j) {
        if (this.mFlushed) {
            this.mLastPresentationTimeUs = Math.max(j - MAX_PRESENTATION_TIMESTAMP_SHIFT_US, 0L);
            this.mFlushed = false;
        }
    }
}
