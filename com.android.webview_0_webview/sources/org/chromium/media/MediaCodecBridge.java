package org.chromium.media;

import J.N;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.view.Surface;
import defpackage.AbstractC0708eH;
import defpackage.C0820gH;
import defpackage.C1240nt;
import defpackage.RunnableC0764fH;
import defpackage.Ts;
import defpackage.Z7;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.Queue;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class MediaCodecBridge {
    public static HandlerThread m;
    public static Handler n;
    public MediaCodec a;
    public ByteBuffer[] b;
    public int c;
    public boolean d;
    public Queue e;
    public GetOutputFormatResult f;
    public boolean g;
    public boolean h;
    public long i;
    public int j;
    public Queue k;
    public Queue l;

    /* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
    public class DequeueInputResult {
        public final int a;
        public final int b;

        public DequeueInputResult(int i, int i2, AbstractC0708eH abstractC0708eH) {
            this.a = i;
            this.b = i2;
        }

        private int index() {
            return this.b;
        }

        private int status() {
            return this.a;
        }
    }

    /* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
    public class DequeueOutputResult {
        public final int a;
        public final int b;
        public final int c;
        public final int d;
        public final long e;
        public final int f;

        public DequeueOutputResult(int i, int i2, int i3, int i4, long j, int i5, AbstractC0708eH abstractC0708eH) {
            this.a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
            this.e = j;
            this.f = i5;
        }

        private int flags() {
            return this.c;
        }

        private int index() {
            return this.b;
        }

        private int numBytes() {
            return this.f;
        }

        private int offset() {
            return this.d;
        }

        private long presentationTimeMicroseconds() {
            return this.e;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int status() {
            return this.a;
        }
    }

    /* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
    public class GetOutputFormatResult {
        public final int a;
        public final MediaFormat b;

        public GetOutputFormatResult(int i, MediaFormat mediaFormat, AbstractC0708eH abstractC0708eH) {
            this.a = i;
            this.b = mediaFormat;
        }

        private int channelCount() {
            return this.b.getInteger("channel-count");
        }

        private int height() {
            return a() ? (this.b.getInteger("crop-bottom") - this.b.getInteger("crop-top")) + 1 : this.b.getInteger("height");
        }

        private int sampleRate() {
            return this.b.getInteger("sample-rate");
        }

        private int status() {
            return this.a;
        }

        private int width() {
            return a() ? (this.b.getInteger("crop-right") - this.b.getInteger("crop-left")) + 1 : this.b.getInteger("width");
        }

        public final boolean a() {
            return this.b.containsKey("crop-right") && this.b.containsKey("crop-left") && this.b.containsKey("crop-bottom") && this.b.containsKey("crop-top");
        }
    }

    public MediaCodecBridge(MediaCodec mediaCodec, int i, boolean z) {
        this.a = mediaCodec;
        this.c = i;
        this.d = z;
        if (z) {
            this.g = false;
            this.e = new LinkedList();
            this.k = new LinkedList();
            this.l = new LinkedList();
            this.a.setCallback(new C0820gH(this, this), n);
            e();
        }
    }

    public static void createCallbackHandlerForTesting() {
        if (m != null) {
            return;
        }
        HandlerThread handlerThread = new HandlerThread("TestCallbackThread");
        m = handlerThread;
        handlerThread.start();
        n = new Handler(m.getLooper());
    }

    private DequeueInputResult dequeueInputBuffer(long j) {
        int i = 5;
        int i2 = -1;
        if (this.d) {
            synchronized (this) {
                if (this.g) {
                    return new DequeueInputResult(5, -1, null);
                }
                if (!this.h && !this.k.isEmpty()) {
                    return (DequeueInputResult) this.k.remove();
                }
                return new DequeueInputResult(1, -1, null);
            }
        }
        try {
            int iDequeueInputBuffer = this.a.dequeueInputBuffer(j);
            if (iDequeueInputBuffer >= 0) {
                i2 = iDequeueInputBuffer;
                i = 0;
            } else if (iDequeueInputBuffer == -1) {
                i = 1;
            } else {
                Ts.a("MediaCodecBridge", "Unexpected index_or_status: " + iDequeueInputBuffer, new Object[0]);
            }
        } catch (Exception e) {
            Ts.a("MediaCodecBridge", "Failed to dequeue input buffer", e);
        }
        return new DequeueInputResult(i, i2, null);
    }

    private DequeueOutputResult dequeueOutputBuffer(long j) {
        int i;
        int i2;
        int i3 = 3;
        if (this.d) {
            synchronized (this) {
                if (this.g) {
                    return new DequeueOutputResult(5, -1, 0, 0, 0L, 0, null);
                }
                if (this.l.isEmpty()) {
                    return new DequeueOutputResult(1, -1, 0, 0, 0L, 0, null);
                }
                if (((DequeueOutputResult) this.l.peek()).status() == 3) {
                    this.f = (GetOutputFormatResult) this.e.remove();
                }
                return (DequeueOutputResult) this.l.remove();
            }
        }
        MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
        int i4 = -1;
        try {
            int iB = b(bufferInfo, j);
            if (iB >= 0) {
                i4 = iB;
                i3 = 0;
            } else if (iB == -3) {
                this.b = this.a.getOutputBuffers();
                i3 = 2;
            } else if (iB == -2) {
                this.a.getOutputFormat();
            } else if (iB == -1) {
                i3 = 1;
            } else {
                Ts.a("MediaCodecBridge", "Unexpected index_or_status: " + iB, new Object[0]);
                i3 = 5;
            }
            i = i3;
            i2 = i4;
        } catch (IllegalStateException e) {
            Ts.a("MediaCodecBridge", "Failed to dequeue output buffer", e);
            i = 5;
            i2 = -1;
        }
        return new DequeueOutputResult(i, i2, bufferInfo.flags, bufferInfo.offset, bufferInfo.presentationTimeUs, bufferInfo.size, null);
    }

    private int flush() {
        try {
            this.a.flush();
            if (this.d) {
                e();
                if (!f()) {
                    return 5;
                }
            }
            return 0;
        } catch (Exception e) {
            Ts.a("MediaCodecBridge", "Failed to flush MediaCodec", e);
            return 5;
        }
    }

    private ByteBuffer getInputBuffer(int i) {
        try {
            return this.a.getInputBuffer(i);
        } catch (IllegalStateException e) {
            Ts.a("MediaCodecBridge", "Failed to get input buffer", e);
            return null;
        }
    }

    private String getName() {
        try {
            return this.a.getName();
        } catch (IllegalStateException e) {
            Ts.a("MediaCodecBridge", "Cannot get codec name", e);
            return "unknown";
        }
    }

    private GetOutputFormatResult getOutputFormat() {
        MediaFormat outputFormat;
        GetOutputFormatResult getOutputFormatResult;
        if (this.d && (getOutputFormatResult = this.f) != null) {
            return getOutputFormatResult;
        }
        int i = 0;
        try {
            outputFormat = this.a.getOutputFormat();
        } catch (IllegalStateException e) {
            Ts.a("MediaCodecBridge", "Failed to get output format", e);
            i = 5;
            outputFormat = null;
        }
        return new GetOutputFormatResult(i, outputFormat, null);
    }

    private int queueInputBuffer(int i, int i2, int i3, long j, int i4) {
        try {
            this.a.queueInputBuffer(i, i2, i3, j, i4);
            return 0;
        } catch (Exception e) {
            Ts.a("MediaCodecBridge", "Failed to queue input buffer", e);
            return 5;
        }
    }

    private int queueSecureInputBuffer(int i, int i2, byte[] bArr, byte[] bArr2, int[] iArr, int[] iArr2, int i3, int i4, int i5, int i6, long j) {
        int i7 = Build.VERSION.SDK_INT;
        try {
            int iG = g(i4);
            if (iG == -1) {
                return 5;
            }
            boolean z = iG == 2;
            if (z && !MediaCodecUtil.platformSupportsCbcsEncryption(i7)) {
                Ts.a("MediaCodecBridge", "Encryption scheme 'cbcs' not supported on this platform.", new Object[0]);
                return 5;
            }
            MediaCodec.CryptoInfo cryptoInfo = new MediaCodec.CryptoInfo();
            cryptoInfo.set(i3, iArr, iArr2, bArr2, bArr, iG);
            if (i5 != 0 && i6 != 0) {
                if (!z) {
                    Ts.a("MediaCodecBridge", "Pattern encryption only supported for 'cbcs' scheme (CBC mode).", new Object[0]);
                    return 5;
                }
                if (i7 >= 24) {
                    C1240nt.e(cryptoInfo, i5, i6);
                }
            }
            this.a.queueSecureInputBuffer(i, i2, cryptoInfo, j, 0);
            return 0;
        } catch (MediaCodec.CryptoException e) {
            if (e.getErrorCode() == 1) {
                return 4;
            }
            StringBuilder sbF = Z7.f("Failed to queue secure input buffer, CryptoException with error code ");
            sbF.append(e.getErrorCode());
            Ts.a("MediaCodecBridge", sbF.toString(), new Object[0]);
            return 5;
        } catch (IllegalArgumentException e2) {
            Ts.a("MediaCodecBridge", "Failed to queue secure input buffer, IllegalArgumentException " + e2, new Object[0]);
            return 5;
        } catch (IllegalStateException e3) {
            Ts.a("MediaCodecBridge", "Failed to queue secure input buffer, IllegalStateException " + e3, new Object[0]);
            return 5;
        }
    }

    private void requestKeyFrameSoon() {
        Bundle bundle = new Bundle();
        bundle.putInt("request-sync", 0);
        try {
            this.a.setParameters(bundle);
        } catch (IllegalStateException e) {
            Ts.a("MediaCodecBridge", "Failed to set MediaCodec parameters", e);
        }
    }

    private synchronized void setBuffersAvailableListener(long j) {
        this.i = j;
        if (!this.k.isEmpty() || !this.l.isEmpty() || this.g) {
            c();
        }
    }

    private boolean setSurface(Surface surface) {
        try {
            this.a.setOutputSurface(surface);
            return true;
        } catch (IllegalArgumentException | IllegalStateException e) {
            Ts.a("MediaCodecBridge", "Cannot set output surface", e);
            return false;
        }
    }

    private void setVideoBitrate(int i, int i2) {
        int i3 = this.c;
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
            Ts.a("MediaCodecBridge", "Failed to set MediaCodec parameters", e);
        }
    }

    private void stop() {
        try {
            this.a.stop();
            if (this.d) {
                e();
            }
        } catch (IllegalStateException e) {
            Ts.a("MediaCodecBridge", "Failed to stop MediaCodec", e);
        }
    }

    public boolean a(MediaFormat mediaFormat, Surface surface, MediaCrypto mediaCrypto, int i) {
        try {
            this.a.configure(mediaFormat, surface, mediaCrypto, i);
            return true;
        } catch (MediaCodec.CryptoException e) {
            Ts.a("MediaCodecBridge", "Cannot configure the video codec: DRM error", e);
            return false;
        } catch (IllegalArgumentException e2) {
            Ts.a("MediaCodecBridge", "Cannot configure the video codec, wrong format or surface", e2);
            return false;
        } catch (IllegalStateException e3) {
            Ts.a("MediaCodecBridge", "Cannot configure the video codec", e3);
            return false;
        } catch (Exception e4) {
            Ts.a("MediaCodecBridge", "Cannot configure the video codec", e4);
            return false;
        }
    }

    public int b(MediaCodec.BufferInfo bufferInfo, long j) {
        return this.a.dequeueOutputBuffer(bufferInfo, j);
    }

    public final synchronized void c() {
        if (this.i != 0) {
            N.MNqjfk23(this.i, this);
        }
    }

    public synchronized void d() {
        this.g = true;
        this.k.clear();
        this.l.clear();
        c();
    }

    public final synchronized void e() {
        this.e.clear();
        this.k.clear();
        this.l.clear();
        this.h = true;
        this.f = null;
        this.j++;
    }

    public boolean f() {
        try {
            if (this.d) {
                synchronized (this) {
                    if (this.g) {
                        return false;
                    }
                    (n == null ? new Handler(Looper.getMainLooper()) : n).post(new RunnableC0764fH(this, this.j));
                }
            }
            this.a.start();
            return true;
        } catch (IllegalArgumentException e) {
            Ts.a("MediaCodecBridge", "Cannot start the media codec", e);
            return false;
        } catch (IllegalStateException e2) {
            Ts.a("MediaCodecBridge", "Cannot start the media codec", e2);
            return false;
        }
    }

    public final int g(int i) {
        if (i == 0) {
            return 0;
        }
        int i2 = 1;
        if (i != 1) {
            i2 = 2;
            if (i != 2) {
                Ts.a("MediaCodecBridge", Z7.a("Unsupported cipher mode: ", i), new Object[0]);
                return -1;
            }
        }
        return i2;
    }

    public ByteBuffer getOutputBuffer(int i) {
        try {
            return this.a.getOutputBuffer(i);
        } catch (IllegalStateException e) {
            Ts.a("MediaCodecBridge", "Failed to get output buffer", e);
            return null;
        }
    }

    public void release() {
        if (this.d) {
            synchronized (this) {
                this.i = 0L;
            }
        }
        try {
            Ts.f("MediaCodecBridge", "Releasing: " + this.a.getName(), new Object[0]);
            this.a.release();
            Ts.f("MediaCodecBridge", "Codec released", new Object[0]);
        } catch (IllegalStateException e) {
            Ts.a("MediaCodecBridge", "Cannot release media codec", e);
        }
        this.a = null;
    }

    public void releaseOutputBuffer(int i, boolean z) {
        try {
            this.a.releaseOutputBuffer(i, z);
        } catch (IllegalStateException e) {
            Ts.a("MediaCodecBridge", "Failed to release output buffer", e);
        }
    }
}
