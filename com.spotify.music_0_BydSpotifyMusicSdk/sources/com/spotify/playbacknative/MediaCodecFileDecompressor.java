package com.spotify.playbacknative;

import android.content.Context;
import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import androidx.annotation.Keep;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
@Keep
public class MediaCodecFileDecompressor {
    private MediaCodec mCodec;
    private boolean mEndOfInputStream;
    private final Context mInjectedContext;
    private ByteBuffer[] mInputBuffers;
    private MediaFormat mInputFormat;
    private final MediaCodecFactory mMediaCodecFactory;
    private MediaExtractor mMediaExtractor;
    private final MediaExtractorFactory mMediaExtractorFactory;
    private final NativeWrapper mNativeWrapper;
    private ByteBuffer[] mOutputBuffers;
    private MediaFormat mOutputFormat;
    private long nThis;

    public static class DefaultMediaCodecFactory implements MediaCodecFactory {
        private DefaultMediaCodecFactory() {
        }

        @Override // com.spotify.playbacknative.MediaCodecFileDecompressor.MediaCodecFactory
        public MediaCodec createDecoderByType(String str) {
            return MediaCodec.createDecoderByType(str);
        }
    }

    public static class DefaultMediaExtractorFactory implements MediaExtractorFactory {
        private DefaultMediaExtractorFactory() {
        }

        @Override // com.spotify.playbacknative.MediaCodecFileDecompressor.MediaExtractorFactory
        public MediaExtractor createExtractor() {
            return new MediaExtractor();
        }
    }

    public class DefaultNativeWrapper implements NativeWrapper {
        private DefaultNativeWrapper() {
        }

        @Override // com.spotify.playbacknative.MediaCodecFileDecompressor.NativeWrapper
        public void onOutputBuffer(ByteBuffer byteBuffer, int i, int i2, boolean z) {
            MediaCodecFileDecompressor.this.onOutputBuffer(byteBuffer, i, i2, z);
        }
    }

    public interface MediaCodecFactory {
        MediaCodec createDecoderByType(String str);
    }

    public interface MediaExtractorFactory {
        MediaExtractor createExtractor();
    }

    public interface NativeWrapper {
        void onOutputBuffer(ByteBuffer byteBuffer, int i, int i2, boolean z);
    }

    public MediaCodecFileDecompressor(Context context) {
        this.mInjectedContext = context;
        this.mNativeWrapper = new DefaultNativeWrapper();
        this.mMediaCodecFactory = new DefaultMediaCodecFactory();
        this.mMediaExtractorFactory = new DefaultMediaExtractorFactory();
    }

    private void decodeInternal(boolean z) {
        int iDequeueInputBuffer;
        if (!this.mEndOfInputStream && (iDequeueInputBuffer = this.mCodec.dequeueInputBuffer(0L)) >= 0) {
            int sampleData = this.mMediaExtractor.readSampleData(this.mInputBuffers[iDequeueInputBuffer], 0);
            if (sampleData < 0) {
                this.mCodec.queueInputBuffer(iDequeueInputBuffer, 0, 0, 0L, 4);
                this.mEndOfInputStream = true;
            } else {
                this.mCodec.queueInputBuffer(iDequeueInputBuffer, 0, sampleData, this.mMediaExtractor.getSampleTime(), 0);
                this.mMediaExtractor.advance();
            }
        }
        MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
        int iDequeueOutputBuffer = this.mCodec.dequeueOutputBuffer(bufferInfo, 0L);
        if (iDequeueOutputBuffer >= 0) {
            if (z) {
                this.mNativeWrapper.onOutputBuffer(this.mOutputBuffers[iDequeueOutputBuffer], bufferInfo.offset, bufferInfo.size, (bufferInfo.flags & 4) == 4);
            } else {
                Logger.e("Got data before output format, that shouldn't happen!", new Object[0]);
            }
            this.mCodec.releaseOutputBuffer(iDequeueOutputBuffer, true);
            return;
        }
        if (iDequeueOutputBuffer == -3) {
            this.mOutputBuffers = this.mCodec.getOutputBuffers();
        } else if (iDequeueOutputBuffer == -2) {
            this.mOutputFormat = this.mCodec.getOutputFormat();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public native void onOutputBuffer(ByteBuffer byteBuffer, int i, int i2, boolean z);

    public void decode() {
        decodeInternal(true);
    }

    public void destroy() {
        MediaCodec mediaCodec = this.mCodec;
        if (mediaCodec != null) {
            mediaCodec.stop();
            this.mCodec.release();
        }
        MediaExtractor mediaExtractor = this.mMediaExtractor;
        if (mediaExtractor != null) {
            mediaExtractor.release();
        }
    }

    public int getChannelCount() {
        if (this.mOutputFormat.containsKey("channel-count")) {
            return this.mOutputFormat.getInteger("channel-count");
        }
        return -1;
    }

    public long getDurationUs() {
        if (this.mInputFormat.containsKey("durationUs")) {
            return this.mInputFormat.getLong("durationUs");
        }
        return -1L;
    }

    public String getMimeType() {
        return this.mInputFormat.containsKey("mime") ? this.mInputFormat.getString("mime") : "";
    }

    public int getSampleRate() {
        if (this.mOutputFormat.containsKey("sample-rate")) {
            return this.mOutputFormat.getInteger("sample-rate");
        }
        return -1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0053, code lost:
    
        r5.mMediaExtractor.selectTrack(r1);
        r6 = r5.mMediaCodecFactory.createDecoderByType(r2);
        r5.mCodec = r6;
        r6.configure(r5.mInputFormat, (android.view.Surface) null, (android.media.MediaCrypto) null, 0);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean open(java.lang.String r6) {
        /*
            r5 = this;
            r0 = 0
            com.spotify.playbacknative.MediaCodecFileDecompressor$MediaExtractorFactory r1 = r5.mMediaExtractorFactory     // Catch: java.io.IOException -> L9f
            android.media.MediaExtractor r1 = r1.createExtractor()     // Catch: java.io.IOException -> L9f
            r5.mMediaExtractor = r1     // Catch: java.io.IOException -> L9f
            android.net.Uri r1 = android.net.Uri.parse(r6)     // Catch: java.io.IOException -> L9f
            android.content.Context r2 = r5.mInjectedContext     // Catch: java.io.IOException -> L9f
            r3 = 0
            if (r2 == 0) goto L26
            java.lang.String r2 = "content"
            java.lang.String r4 = r1.getScheme()     // Catch: java.io.IOException -> L9f
            boolean r2 = r2.equals(r4)     // Catch: java.io.IOException -> L9f
            if (r2 == 0) goto L26
            android.media.MediaExtractor r6 = r5.mMediaExtractor     // Catch: java.io.IOException -> L9f
            android.content.Context r2 = r5.mInjectedContext     // Catch: java.io.IOException -> L9f
            r6.setDataSource(r2, r1, r3)     // Catch: java.io.IOException -> L9f
            goto L2b
        L26:
            android.media.MediaExtractor r1 = r5.mMediaExtractor     // Catch: java.io.IOException -> L9f
            r1.setDataSource(r6)     // Catch: java.io.IOException -> L9f
        L2b:
            android.media.MediaExtractor r6 = r5.mMediaExtractor     // Catch: java.io.IOException -> L9f
            int r6 = r6.getTrackCount()     // Catch: java.io.IOException -> L9f
            if (r6 != 0) goto L3a
            java.lang.String r1 = "No tracks found in media file"
            java.lang.Object[] r2 = new java.lang.Object[r0]     // Catch: java.io.IOException -> L9f
            com.spotify.playbacknative.Logger.w(r1, r2)     // Catch: java.io.IOException -> L9f
        L3a:
            r1 = r0
        L3b:
            if (r1 >= r6) goto L69
            android.media.MediaExtractor r2 = r5.mMediaExtractor     // Catch: java.io.IOException -> L9f
            android.media.MediaFormat r2 = r2.getTrackFormat(r1)     // Catch: java.io.IOException -> L9f
            r5.mInputFormat = r2     // Catch: java.io.IOException -> L9f
            java.lang.String r4 = "mime"
            java.lang.String r2 = r2.getString(r4)     // Catch: java.io.IOException -> L9f
            java.lang.String r4 = "audio/"
            boolean r4 = r2.startsWith(r4)     // Catch: java.io.IOException -> L9f
            if (r4 == 0) goto L66
            android.media.MediaExtractor r6 = r5.mMediaExtractor     // Catch: java.io.IOException -> L9f
            r6.selectTrack(r1)     // Catch: java.io.IOException -> L9f
            com.spotify.playbacknative.MediaCodecFileDecompressor$MediaCodecFactory r6 = r5.mMediaCodecFactory     // Catch: java.io.IOException -> L9f
            android.media.MediaCodec r6 = r6.createDecoderByType(r2)     // Catch: java.io.IOException -> L9f
            r5.mCodec = r6     // Catch: java.io.IOException -> L9f
            android.media.MediaFormat r1 = r5.mInputFormat     // Catch: java.io.IOException -> L9f
            r6.configure(r1, r3, r3, r0)     // Catch: java.io.IOException -> L9f
            goto L69
        L66:
            int r1 = r1 + 1
            goto L3b
        L69:
            android.media.MediaCodec r6 = r5.mCodec
            if (r6 != 0) goto L75
            java.lang.String r6 = "MediaCodec not found in media file"
            java.lang.Object[] r1 = new java.lang.Object[r0]
            com.spotify.playbacknative.Logger.e(r6, r1)
            return r0
        L75:
            r6.start()
            android.media.MediaCodec r6 = r5.mCodec
            java.nio.ByteBuffer[] r6 = r6.getInputBuffers()
            r5.mInputBuffers = r6
            android.media.MediaCodec r6 = r5.mCodec
            java.nio.ByteBuffer[] r6 = r6.getOutputBuffers()
            r5.mOutputBuffers = r6
            r6 = r0
        L89:
            android.media.MediaFormat r1 = r5.mOutputFormat
            r2 = 1
            if (r1 != 0) goto L9e
            r5.decodeInternal(r0)
            int r6 = r6 + r2
            r1 = 100
            if (r6 <= r1) goto L89
            java.lang.String r6 = "MediaFormat not found in media file"
            java.lang.Object[] r1 = new java.lang.Object[r0]
            com.spotify.playbacknative.Logger.e(r6, r1)
            return r0
        L9e:
            return r2
        L9f:
            java.lang.String r6 = "IOException when opening media file"
            java.lang.Object[] r1 = new java.lang.Object[r0]
            com.spotify.playbacknative.Logger.e(r6, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.spotify.playbacknative.MediaCodecFileDecompressor.open(java.lang.String):boolean");
    }

    public void seekToUs(long j) {
        this.mMediaExtractor.seekTo(j, 1);
        this.mCodec.flush();
        this.mEndOfInputStream = false;
    }

    public MediaCodecFileDecompressor(Context context, NativeWrapper nativeWrapper, MediaCodecFactory mediaCodecFactory, MediaExtractorFactory mediaExtractorFactory) {
        this.mInjectedContext = context;
        this.mNativeWrapper = nativeWrapper;
        this.mMediaCodecFactory = mediaCodecFactory;
        this.mMediaExtractorFactory = mediaExtractorFactory;
    }
}
