package s2;

import B2.P;
import B5.H;
import android.media.MediaCodec;
import android.os.Bundle;
import android.os.HandlerThread;
import h2.AbstractC1343A;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class e implements l {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final ArrayDeque f22073r = new ArrayDeque();

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final Object f22074s = new Object();
    public final MediaCodec l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final HandlerThread f22075m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public H f22076n;
    public final AtomicReference o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final P f22077p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f22078q;

    public e(MediaCodec mediaCodec, HandlerThread handlerThread) {
        P p10 = new P();
        this.l = mediaCodec;
        this.f22075m = handlerThread;
        this.f22077p = p10;
        this.o = new AtomicReference();
    }

    public static d a() {
        ArrayDeque arrayDeque = f22073r;
        synchronized (arrayDeque) {
            try {
                if (arrayDeque.isEmpty()) {
                    return new d();
                }
                return (d) arrayDeque.removeFirst();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // s2.l
    public final void b(int i7, m2.c cVar, long j3, int i10) {
        k();
        d dVarA = a();
        dVarA.f22070a = i7;
        dVarA.f22071b = 0;
        dVarA.d = j3;
        dVarA.f22072e = i10;
        int i11 = cVar.f18949f;
        MediaCodec.CryptoInfo cryptoInfo = dVarA.c;
        cryptoInfo.numSubSamples = i11;
        int[] iArr = cVar.d;
        int[] iArrCopyOf = cryptoInfo.numBytesOfClearData;
        if (iArr != null) {
            if (iArrCopyOf == null || iArrCopyOf.length < iArr.length) {
                iArrCopyOf = Arrays.copyOf(iArr, iArr.length);
            } else {
                System.arraycopy(iArr, 0, iArrCopyOf, 0, iArr.length);
            }
        }
        cryptoInfo.numBytesOfClearData = iArrCopyOf;
        int[] iArr2 = cVar.f18948e;
        int[] iArrCopyOf2 = cryptoInfo.numBytesOfEncryptedData;
        if (iArr2 != null) {
            if (iArrCopyOf2 == null || iArrCopyOf2.length < iArr2.length) {
                iArrCopyOf2 = Arrays.copyOf(iArr2, iArr2.length);
            } else {
                System.arraycopy(iArr2, 0, iArrCopyOf2, 0, iArr2.length);
            }
        }
        cryptoInfo.numBytesOfEncryptedData = iArrCopyOf2;
        byte[] bArr = cVar.f18947b;
        byte[] bArrCopyOf = cryptoInfo.key;
        if (bArr != null) {
            if (bArrCopyOf == null || bArrCopyOf.length < bArr.length) {
                bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
            } else {
                System.arraycopy(bArr, 0, bArrCopyOf, 0, bArr.length);
            }
        }
        bArrCopyOf.getClass();
        cryptoInfo.key = bArrCopyOf;
        byte[] bArr2 = cVar.f18946a;
        byte[] bArrCopyOf2 = cryptoInfo.iv;
        if (bArr2 != null) {
            if (bArrCopyOf2 == null || bArrCopyOf2.length < bArr2.length) {
                bArrCopyOf2 = Arrays.copyOf(bArr2, bArr2.length);
            } else {
                System.arraycopy(bArr2, 0, bArrCopyOf2, 0, bArr2.length);
            }
        }
        bArrCopyOf2.getClass();
        cryptoInfo.iv = bArrCopyOf2;
        cryptoInfo.mode = cVar.c;
        if (AbstractC1343A.f15195a >= 24) {
            cryptoInfo.setPattern(new MediaCodec.CryptoInfo.Pattern(cVar.f18950g, cVar.f18951h));
        }
        this.f22076n.obtainMessage(2, dVarA).sendToTarget();
    }

    @Override // s2.l
    public final void c(int i7, int i10, int i11, long j3) {
        k();
        d dVarA = a();
        dVarA.f22070a = i7;
        dVarA.f22071b = i10;
        dVarA.d = j3;
        dVarA.f22072e = i11;
        H h10 = this.f22076n;
        int i12 = AbstractC1343A.f15195a;
        h10.obtainMessage(1, dVarA).sendToTarget();
    }

    @Override // s2.l
    public final void d(Bundle bundle) {
        k();
        H h10 = this.f22076n;
        int i7 = AbstractC1343A.f15195a;
        h10.obtainMessage(4, bundle).sendToTarget();
    }

    @Override // s2.l
    public final void flush() {
        if (this.f22078q) {
            try {
                H h10 = this.f22076n;
                h10.getClass();
                h10.removeCallbacksAndMessages(null);
                P p10 = this.f22077p;
                synchronized (p10) {
                    p10.l = false;
                }
                H h11 = this.f22076n;
                h11.getClass();
                h11.obtainMessage(3).sendToTarget();
                p10.a();
            } catch (InterruptedException e10) {
                Thread.currentThread().interrupt();
                throw new IllegalStateException(e10);
            }
        }
    }

    @Override // s2.l
    public final void k() {
        RuntimeException runtimeException = (RuntimeException) this.o.getAndSet(null);
        if (runtimeException != null) {
            throw runtimeException;
        }
    }

    @Override // s2.l
    public final void shutdown() {
        if (this.f22078q) {
            flush();
            this.f22075m.quit();
        }
        this.f22078q = false;
    }

    @Override // s2.l
    public final void start() {
        if (this.f22078q) {
            return;
        }
        HandlerThread handlerThread = this.f22075m;
        handlerThread.start();
        this.f22076n = new H(this, handlerThread.getLooper(), 20);
        this.f22078q = true;
    }
}
