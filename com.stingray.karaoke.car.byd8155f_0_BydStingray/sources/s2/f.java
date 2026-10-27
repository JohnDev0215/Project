package s2;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.HandlerThread;
import java.util.ArrayDeque;
import l3.C1764u;
import n2.C1924y;
import s3.C2393m;

/* JADX INFO: loaded from: classes.dex */
public final class f extends MediaCodec.Callback {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final HandlerThread f22080b;
    public Handler c;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public MediaFormat f22084h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public MediaFormat f22085i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public MediaCodec.CodecException f22086j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public MediaCodec.CryptoException f22087k;
    public long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f22088m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public IllegalStateException f22089n;
    public C1764u o;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f22079a = new Object();
    public final C2393m d = new C2393m();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C2393m f22081e = new C2393m();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ArrayDeque f22082f = new ArrayDeque();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ArrayDeque f22083g = new ArrayDeque();

    public f(HandlerThread handlerThread) {
        this.f22080b = handlerThread;
    }

    public final void a() {
        ArrayDeque arrayDeque = this.f22083g;
        if (!arrayDeque.isEmpty()) {
            this.f22085i = (MediaFormat) arrayDeque.getLast();
        }
        C2393m c2393m = this.d;
        c2393m.c = c2393m.f22321b;
        C2393m c2393m2 = this.f22081e;
        c2393m2.c = c2393m2.f22321b;
        this.f22082f.clear();
        arrayDeque.clear();
    }

    @Override // android.media.MediaCodec.Callback
    public final void onCryptoError(MediaCodec mediaCodec, MediaCodec.CryptoException cryptoException) {
        synchronized (this.f22079a) {
            this.f22087k = cryptoException;
        }
    }

    @Override // android.media.MediaCodec.Callback
    public final void onError(MediaCodec mediaCodec, MediaCodec.CodecException codecException) {
        synchronized (this.f22079a) {
            this.f22086j = codecException;
        }
    }

    @Override // android.media.MediaCodec.Callback
    public final void onInputBufferAvailable(MediaCodec mediaCodec, int i7) {
        C1924y c1924y;
        synchronized (this.f22079a) {
            this.d.a(i7);
            C1764u c1764u = this.o;
            if (c1764u != null && (c1924y = ((q) c1764u.f18608m).f22132Q) != null) {
                c1924y.a();
            }
        }
    }

    @Override // android.media.MediaCodec.Callback
    public final void onOutputBufferAvailable(MediaCodec mediaCodec, int i7, MediaCodec.BufferInfo bufferInfo) {
        C1924y c1924y;
        synchronized (this.f22079a) {
            try {
                MediaFormat mediaFormat = this.f22085i;
                if (mediaFormat != null) {
                    this.f22081e.a(-2);
                    this.f22083g.add(mediaFormat);
                    this.f22085i = null;
                }
                this.f22081e.a(i7);
                this.f22082f.add(bufferInfo);
                C1764u c1764u = this.o;
                if (c1764u != null && (c1924y = ((q) c1764u.f18608m).f22132Q) != null) {
                    c1924y.a();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // android.media.MediaCodec.Callback
    public final void onOutputFormatChanged(MediaCodec mediaCodec, MediaFormat mediaFormat) {
        synchronized (this.f22079a) {
            this.f22081e.a(-2);
            this.f22083g.add(mediaFormat);
            this.f22085i = null;
        }
    }
}
