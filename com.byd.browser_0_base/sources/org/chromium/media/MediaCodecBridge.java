package org.chromium.media;

import J.N;
import android.annotation.SuppressLint;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import android.view.Surface;
import defpackage.fa1;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class MediaCodecBridge {
    public static HandlerThread m;
    public static Handler n;
    public MediaCodec a;
    public final int b;
    public int c;
    public final boolean d;
    public LinkedList e;
    public MediaFormatWrapper f;
    public boolean g;
    public boolean h;
    public long i;
    public int j;
    public LinkedList k;
    public LinkedList l;

    public static class DequeueInputResult {
        public final int a;
        public final int b;

        public DequeueInputResult(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        @CalledByNative
        public final int index() {
            return this.b;
        }

        @CalledByNative
        public final int status() {
            return this.a;
        }
    }

    public static class DequeueOutputResult {
        public final int a;
        public final int b;
        public final int c;
        public final int d;
        public final long e;
        public final int f;

        public DequeueOutputResult(int i, int i2, int i3, int i4, long j, int i5) {
            this.a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
            this.e = j;
            this.f = i5;
        }

        @CalledByNative
        public final int flags() {
            return this.c;
        }

        @CalledByNative
        public final int index() {
            return this.b;
        }

        @CalledByNative
        public final int numBytes() {
            return this.f;
        }

        @CalledByNative
        public final int offset() {
            return this.d;
        }

        @CalledByNative
        public final long presentationTimeMicroseconds() {
            return this.e;
        }

        @CalledByNative
        public final int status() {
            return this.a;
        }
    }

    public static class MediaFormatWrapper {
        public final MediaFormat a;

        public MediaFormatWrapper(MediaFormat mediaFormat) {
            this.a = mediaFormat;
        }

        public final boolean a() {
            MediaFormat mediaFormat = this.a;
            return mediaFormat.containsKey("crop-right") && mediaFormat.containsKey("crop-left") && mediaFormat.containsKey("crop-bottom") && mediaFormat.containsKey("crop-top");
        }

        @CalledByNative
        public final int channelCount() {
            return this.a.getInteger("channel-count");
        }

        @CalledByNative
        public final int colorRange() {
            MediaFormat mediaFormat = this.a;
            if (mediaFormat.containsKey("color-range")) {
                return mediaFormat.getInteger("color-range");
            }
            return -1;
        }

        @CalledByNative
        public final int colorStandard() {
            MediaFormat mediaFormat = this.a;
            if (mediaFormat.containsKey("color-standard")) {
                return mediaFormat.getInteger("color-standard");
            }
            return -1;
        }

        @CalledByNative
        public final int colorTransfer() {
            MediaFormat mediaFormat = this.a;
            if (mediaFormat.containsKey("color-transfer")) {
                return mediaFormat.getInteger("color-transfer");
            }
            return -1;
        }

        @CalledByNative
        public final int height() {
            boolean zA = a();
            MediaFormat mediaFormat = this.a;
            return zA ? (mediaFormat.getInteger("crop-bottom") - mediaFormat.getInteger("crop-top")) + 1 : mediaFormat.getInteger("height");
        }

        @CalledByNative
        public final int sampleRate() {
            return this.a.getInteger("sample-rate");
        }

        @CalledByNative
        public final int stride() {
            MediaFormat mediaFormat = this.a;
            return !mediaFormat.containsKey("stride") ? width() : mediaFormat.getInteger("stride");
        }

        @CalledByNative
        public final int width() {
            boolean zA = a();
            MediaFormat mediaFormat = this.a;
            return zA ? (mediaFormat.getInteger("crop-right") - mediaFormat.getInteger("crop-left")) + 1 : mediaFormat.getInteger("width");
        }

        @CalledByNative
        public final int yPlaneHeight() {
            MediaFormat mediaFormat = this.a;
            return !mediaFormat.containsKey("slice-height") ? height() : mediaFormat.getInteger("slice-height");
        }
    }

    public class a implements Runnable {
        public final int e;

        public a(int i) {
            this.e = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            MediaCodecBridge mediaCodecBridge = MediaCodecBridge.this;
            int i = this.e;
            synchronized (mediaCodecBridge) {
                if (mediaCodecBridge.j != i) {
                    return;
                }
                mediaCodecBridge.h = false;
            }
        }
    }

    public class b extends MediaCodec.Callback {
        public final MediaCodecBridge a;

        public b(MediaCodecBridge mediaCodecBridge) {
            this.a = mediaCodecBridge;
        }

        @Override // android.media.MediaCodec.Callback
        public final void onError(MediaCodec mediaCodec, MediaCodec.CodecException codecException) {
            fa1.b("MediaCodecBridge", "MediaCodec.onError: %s", codecException.getDiagnosticInfo());
            MediaCodecBridge mediaCodecBridge = this.a;
            synchronized (mediaCodecBridge) {
                mediaCodecBridge.g = true;
                mediaCodecBridge.k.clear();
                mediaCodecBridge.l.clear();
                mediaCodecBridge.c();
            }
        }

        @Override // android.media.MediaCodec.Callback
        public final void onInputBufferAvailable(MediaCodec mediaCodec, int i) {
            MediaCodecBridge mediaCodecBridge = this.a;
            synchronized (mediaCodecBridge) {
                if (mediaCodecBridge.h) {
                    return;
                }
                mediaCodecBridge.k.add(new DequeueInputResult(0, i));
                mediaCodecBridge.c();
            }
        }

        @Override // android.media.MediaCodec.Callback
        public final void onOutputBufferAvailable(MediaCodec mediaCodec, int i, MediaCodec.BufferInfo bufferInfo) {
            MediaCodecBridge mediaCodecBridge = this.a;
            synchronized (mediaCodecBridge) {
                if (mediaCodecBridge.h) {
                    return;
                }
                mediaCodecBridge.l.add(new DequeueOutputResult(0, i, bufferInfo.flags, bufferInfo.offset, bufferInfo.presentationTimeUs, bufferInfo.size));
                mediaCodecBridge.c();
            }
        }

        @Override // android.media.MediaCodec.Callback
        public final void onOutputFormatChanged(MediaCodec mediaCodec, MediaFormat mediaFormat) {
            MediaCodecBridge mediaCodecBridge = this.a;
            synchronized (mediaCodecBridge) {
                mediaCodecBridge.l.add(new DequeueOutputResult(3, -1, 0, 0, 0L, 0));
                mediaCodecBridge.e.add(new MediaFormatWrapper(mediaFormat));
                mediaCodecBridge.c();
            }
        }
    }

    public MediaCodecBridge(MediaCodec mediaCodec, int i, boolean z) {
        this.a = mediaCodec;
        this.b = i;
        this.d = z;
        if (z) {
            this.g = false;
            this.e = new LinkedList();
            this.k = new LinkedList();
            this.l = new LinkedList();
            this.a.setCallback(new b(this), n);
            d();
        }
    }

    @CalledByNative
    public static void createCallbackHandlerForTesting() {
        if (m != null) {
            return;
        }
        HandlerThread handlerThread = new HandlerThread("TestCallbackThread");
        m = handlerThread;
        handlerThread.start();
        n = new Handler(m.getLooper());
    }

    public final boolean a(MediaFormat mediaFormat, Surface surface, MediaCrypto mediaCrypto, int i) {
        try {
            this.a.configure(mediaFormat, surface, mediaCrypto, i);
            this.c = mediaFormat.getInteger("max-input-size");
            if (i != 1) {
                return true;
            }
            MediaFormat inputFormat = this.a.getInputFormat();
            if (!((inputFormat.containsKey("stride") && inputFormat.containsKey("slice-height")) ? false : true)) {
                return true;
            }
            int integer = inputFormat.getInteger("width");
            int i2 = integer & (-16);
            int integer2 = inputFormat.getInteger("height");
            int i3 = integer2 & (-16);
            if (i3 != 0 && i2 != 0) {
                if (i2 == integer && i3 == integer2) {
                    return true;
                }
                this.a.reset();
                mediaFormat.setInteger("width", i2);
                mediaFormat.setInteger("height", i3);
                this.a.configure(mediaFormat, surface, mediaCrypto, i);
                return true;
            }
            Log.e("cr_MediaCodecBridge", "MediaCodec requires 16x16 alignment, which is not possible for: " + integer + "x" + integer2);
            return false;
        } catch (MediaCodec.CryptoException e) {
            Log.e("cr_MediaCodecBridge", "Cannot configure the video codec: DRM error", e);
            return false;
        } catch (IllegalArgumentException e2) {
            Log.e("cr_MediaCodecBridge", "Cannot configure the video codec, wrong format or surface", e2);
            return false;
        } catch (IllegalStateException e3) {
            Log.e("cr_MediaCodecBridge", "Cannot configure the video codec", e3);
            return false;
        } catch (Exception e4) {
            Log.e("cr_MediaCodecBridge", "Cannot configure the video codec", e4);
            return false;
        }
    }

    public int b(MediaCodec.BufferInfo bufferInfo, long j) {
        return this.a.dequeueOutputBuffer(bufferInfo, j);
    }

    public final synchronized void c() {
        long j = this.i;
        if (j != 0) {
            N.MNqjfk23(j, this);
        }
    }

    public final synchronized void d() {
        this.e.clear();
        this.k.clear();
        this.l.clear();
        this.h = true;
        this.f = null;
        this.j++;
    }

    @CalledByNative
    public final DequeueInputResult dequeueInputBuffer(long j) {
        int i = 1;
        int i2 = 5;
        int i3 = -1;
        if (this.d) {
            synchronized (this) {
                if (this.g) {
                    return new DequeueInputResult(5, -1);
                }
                if (!this.h && !this.k.isEmpty()) {
                    return (DequeueInputResult) this.k.remove();
                }
                return new DequeueInputResult(1, -1);
            }
        }
        try {
            int iDequeueInputBuffer = this.a.dequeueInputBuffer(j);
            if (iDequeueInputBuffer >= 0) {
                i = 0;
                i3 = iDequeueInputBuffer;
            } else if (iDequeueInputBuffer != -1) {
                fa1.b("MediaCodecBridge", "Unexpected index_or_status: %d", Integer.valueOf(iDequeueInputBuffer));
                i = 5;
            }
            i2 = i;
        } catch (Exception e) {
            Log.e("cr_MediaCodecBridge", "Failed to dequeue input buffer", e);
        }
        return new DequeueInputResult(i2, i3);
    }

    @CalledByNative
    public final DequeueOutputResult dequeueOutputBuffer(long j) {
        int i;
        int i2;
        int i3 = 3;
        if (this.d) {
            synchronized (this) {
                if (this.g) {
                    return new DequeueOutputResult(5, -1, 0, 0, 0L, 0);
                }
                if (this.l.isEmpty()) {
                    return new DequeueOutputResult(1, -1, 0, 0, 0L, 0);
                }
                if (((DequeueOutputResult) this.l.peek()).status() == 3) {
                    this.f = (MediaFormatWrapper) this.e.remove();
                }
                return (DequeueOutputResult) this.l.remove();
            }
        }
        MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
        int i4 = -1;
        try {
            int iB = b(bufferInfo, j);
            if (iB >= 0) {
                i3 = 0;
                i4 = iB;
            } else if (iB == -3) {
                i3 = 2;
            } else if (iB == -2) {
                this.a.getOutputFormat();
            } else if (iB == -1) {
                i3 = 1;
            } else {
                fa1.b("MediaCodecBridge", "Unexpected index_or_status: %d", Integer.valueOf(iB));
                i3 = 5;
            }
            i2 = i3;
            i = i4;
        } catch (IllegalStateException e) {
            Log.e("cr_MediaCodecBridge", "Failed to dequeue output buffer", e);
            i = -1;
            i2 = 5;
        }
        return new DequeueOutputResult(i2, i, bufferInfo.flags, bufferInfo.offset, bufferInfo.presentationTimeUs, bufferInfo.size);
    }

    public final boolean e() {
        try {
            if (this.d) {
                synchronized (this) {
                    if (this.g) {
                        return false;
                    }
                    Handler handler = n;
                    if (handler == null) {
                        handler = new Handler(Looper.getMainLooper());
                    }
                    handler.post(new a(this.j));
                }
            }
            this.a.start();
            return true;
        } catch (IllegalArgumentException e) {
            Log.e("cr_MediaCodecBridge", "Cannot start the media codec", e);
            return false;
        } catch (IllegalStateException e2) {
            Log.e("cr_MediaCodecBridge", "Cannot start the media codec", e2);
            return false;
        }
    }

    @CalledByNative
    public final int flush() {
        try {
            this.a.flush();
            if (!this.d) {
                return 0;
            }
            d();
            return !e() ? 5 : 0;
        } catch (Exception e) {
            Log.e("cr_MediaCodecBridge", "Failed to flush MediaCodec", e);
            return 5;
        }
    }

    @CalledByNative
    public final ByteBuffer getInputBuffer(int i) {
        if (this.d) {
            synchronized (this) {
                if (this.g) {
                    return null;
                }
            }
        }
        try {
            return this.a.getInputBuffer(i);
        } catch (IllegalStateException e) {
            Log.e("cr_MediaCodecBridge", "Failed to get input buffer", e);
            return null;
        }
    }

    @CalledByNative
    public final MediaFormatWrapper getInputFormat() {
        try {
            MediaFormat inputFormat = this.a.getInputFormat();
            if (inputFormat != null) {
                return new MediaFormatWrapper(inputFormat);
            }
            return null;
        } catch (IllegalStateException e) {
            Log.e("cr_MediaCodecBridge", "Failed to get input format", e);
            return null;
        }
    }

    @CalledByNative
    public final int getMaxInputSize() {
        return this.c;
    }

    @CalledByNative
    public final String getName() {
        try {
            return this.a.getName();
        } catch (IllegalStateException e) {
            Log.e("cr_MediaCodecBridge", "Cannot get codec name", e);
            return "unknown";
        }
    }

    @CalledByNative
    public ByteBuffer getOutputBuffer(int i) {
        try {
            return this.a.getOutputBuffer(i);
        } catch (IllegalStateException e) {
            Log.e("cr_MediaCodecBridge", "Failed to get output buffer", e);
            return null;
        }
    }

    @CalledByNative
    public final MediaFormatWrapper getOutputFormat() {
        MediaFormatWrapper mediaFormatWrapper;
        if (this.d && (mediaFormatWrapper = this.f) != null) {
            return mediaFormatWrapper;
        }
        try {
            MediaFormat outputFormat = this.a.getOutputFormat();
            if (outputFormat != null) {
                return new MediaFormatWrapper(outputFormat);
            }
            return null;
        } catch (IllegalStateException e) {
            Log.e("cr_MediaCodecBridge", "Failed to get output format", e);
            return null;
        }
    }

    @CalledByNative
    public final int queueInputBuffer(int i, int i2, int i3, long j, int i4) {
        try {
            this.a.queueInputBuffer(i, i2, i3, j, i4);
            return 0;
        } catch (Exception e) {
            Log.e("cr_MediaCodecBridge", "Failed to queue input buffer", e);
            return 5;
        }
    }

    @SuppressLint({"WrongConstant"})
    @CalledByNative
    public final int queueSecureInputBuffer(int i, int i2, byte[] bArr, byte[] bArr2, int[] iArr, int[] iArr2, int i3, int i4, int i5, int i6, long j) {
        int i7;
        if (i4 == 0) {
            i7 = 0;
        } else if (i4 == 1) {
            i7 = 1;
        } else if (i4 != 2) {
            try {
                fa1.b("MediaCodecBridge", "Unsupported cipher mode: %d", Integer.valueOf(i4));
                i7 = -1;
            } catch (MediaCodec.CryptoException e) {
                if (e.getErrorCode() == 1) {
                    return 4;
                }
                fa1.c("MediaCodecBridge", "Failed to queue secure input buffer. Error code %d", Integer.valueOf(e.getErrorCode()), e);
                return 5;
            } catch (IllegalArgumentException e2) {
                Log.e("cr_MediaCodecBridge", "Failed to queue secure input buffer.", e2);
                return 5;
            } catch (IllegalStateException e3) {
                Log.e("cr_MediaCodecBridge", "Failed to queue secure input buffer.", e3);
                return 5;
            }
        } else {
            i7 = 2;
        }
        if (i7 == -1) {
            return 5;
        }
        boolean z = i7 == 2;
        if (z && !MediaCodecUtil.platformSupportsCbcsEncryption(Build.VERSION.SDK_INT)) {
            Log.e("cr_MediaCodecBridge", "Encryption scheme 'cbcs' not supported on this platform.");
            return 5;
        }
        MediaCodec.CryptoInfo cryptoInfo = new MediaCodec.CryptoInfo();
        cryptoInfo.set(i3, iArr, iArr2, bArr2, bArr, i7);
        if (i5 != 0 && i6 != 0) {
            if (!z) {
                Log.e("cr_MediaCodecBridge", "Pattern encryption only supported for 'cbcs' scheme (CBC mode).");
                return 5;
            }
            cryptoInfo.setPattern(new MediaCodec.CryptoInfo.Pattern(i5, i6));
        }
        this.a.queueSecureInputBuffer(i, i2, cryptoInfo, j, 0);
        return 0;
    }

    @CalledByNative
    public void release() {
        if (this.d) {
            synchronized (this) {
                this.i = 0L;
            }
        }
        try {
            fa1.k("MediaCodecBridge", "Releasing: %s", this.a.getName());
            this.a.release();
            Log.w("cr_MediaCodecBridge", "Codec released");
        } catch (IllegalStateException e) {
            Log.e("cr_MediaCodecBridge", "Cannot release media codec", e);
        }
        this.a = null;
    }

    @CalledByNative
    public void releaseOutputBuffer(int i, boolean z) {
        try {
            this.a.releaseOutputBuffer(i, z);
        } catch (IllegalStateException e) {
            Log.e("cr_MediaCodecBridge", "Failed to release output buffer", e);
        }
    }

    @CalledByNative
    public final void requestKeyFrameSoon() {
        Bundle bundle = new Bundle();
        bundle.putInt("request-sync", 0);
        try {
            this.a.setParameters(bundle);
        } catch (IllegalStateException e) {
            Log.e("cr_MediaCodecBridge", "Failed to set MediaCodec parameters", e);
        }
    }

    @CalledByNative
    public final synchronized void setBuffersAvailableListener(long j) {
        this.i = j;
        if (!this.k.isEmpty() || !this.l.isEmpty() || this.g) {
            c();
        }
    }

    @CalledByNative
    public final boolean setSurface(Surface surface) {
        try {
            this.a.setOutputSurface(surface);
            return true;
        } catch (IllegalArgumentException | IllegalStateException e) {
            Log.e("cr_MediaCodecBridge", "Cannot set output surface", e);
            return false;
        }
    }

    @CalledByNative
    public final void setVideoBitrate(int i, int i2) {
        int i3 = this.b;
        if (i3 != 0) {
            if (i3 != 1) {
                i = 0;
            } else if (i2 != 0) {
                i = (i * 30) / i2;
            }
        }
        Bundle bundle = new Bundle();
        bundle.putInt("video-bitrate", i);
        try {
            this.a.setParameters(bundle);
        } catch (IllegalStateException e) {
            Log.e("cr_MediaCodecBridge", "Failed to set MediaCodec parameters", e);
        }
    }

    @CalledByNative
    public final void stop() {
        try {
            this.a.stop();
            if (this.d) {
                d();
            }
        } catch (IllegalStateException e) {
            Log.e("cr_MediaCodecBridge", "Failed to stop MediaCodec", e);
        }
    }
}
