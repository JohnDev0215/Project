package E0;

import android.media.LoudnessCodecController;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Trace;
import android.view.Surface;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import k2.InterfaceC1606g;
import k3.C1;
import k3.x1;
import l2.InterfaceC1735b;
import l3.C1764u;
import s3.C2393m;
import u.C2465e;
import z2.C2758d;

/* JADX INFO: loaded from: classes.dex */
public final class X implements InterfaceC1606g, s2.k {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f1595m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f1596n;
    public Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Object f1597p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Object f1598q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Object f1599r;

    public X(int i7) {
        this.l = i7;
        switch (i7) {
            case 2:
                break;
            default:
                this.o = new Object();
                this.f1597p = new C2465e(0);
                break;
        }
    }

    public static void n(X x8, MediaFormat mediaFormat, Surface surface, MediaCrypto mediaCrypto, int i7) {
        h3.y yVar;
        LoudnessCodecController loudnessCodecController;
        s2.f fVar = (s2.f) x8.f1597p;
        AbstractC1346c.j(fVar.c == null);
        HandlerThread handlerThread = fVar.f22080b;
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        MediaCodec mediaCodec = (MediaCodec) x8.o;
        mediaCodec.setCallback(fVar, handler);
        fVar.c = handler;
        Trace.beginSection("configureCodec");
        mediaCodec.configure(mediaFormat, surface, mediaCrypto, i7);
        Trace.endSection();
        ((s2.l) x8.f1598q).start();
        Trace.beginSection("startCodec");
        mediaCodec.start();
        Trace.endSection();
        if (AbstractC1343A.f15195a >= 35 && (yVar = (h3.y) x8.f1599r) != null && ((loudnessCodecController = (LoudnessCodecController) yVar.o) == null || loudnessCodecController.addMediaCodec(mediaCodec))) {
            AbstractC1346c.j(((HashSet) yVar.f15499m).add(mediaCodec));
        }
        x8.f1595m = 1;
    }

    public static String t(int i7, String str) {
        StringBuilder sb = new StringBuilder(str);
        if (i7 == 1) {
            sb.append("Audio");
        } else if (i7 == 2) {
            sb.append("Video");
        } else {
            sb.append("Unknown(");
            sb.append(i7);
            sb.append(")");
        }
        return sb.toString();
    }

    @Override // s2.k
    public void a() {
        ArrayList arrayList;
        h3.y yVar;
        h3.y yVar2;
        switch (this.l) {
            case 1:
                synchronized (this.o) {
                    try {
                        this.f1596n = true;
                        arrayList = new ArrayList(((C2465e) this.f1597p).values());
                        ((C2465e) this.f1597p).clear();
                        if (((A2.l) this.f1598q) != null) {
                            Handler handler = (Handler) this.f1599r;
                            handler.getClass();
                            handler.post((A2.l) this.f1598q);
                            this.f1598q = null;
                            this.f1599r = null;
                        }
                    } finally {
                    }
                    break;
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((x1) it.next()).n();
                }
                return;
            default:
                try {
                    if (this.f1595m == 1) {
                        ((s2.l) this.f1598q).shutdown();
                        s2.f fVar = (s2.f) this.f1597p;
                        synchronized (fVar.f22079a) {
                            fVar.f22088m = true;
                            fVar.f22080b.quit();
                            fVar.a();
                        }
                    }
                    this.f1595m = 2;
                    if (this.f1596n) {
                        return;
                    }
                    try {
                        int i7 = AbstractC1343A.f15195a;
                        if (i7 >= 30 && i7 < 33) {
                            ((MediaCodec) this.o).stop();
                            break;
                        }
                        if (i7 >= 35 && (yVar2 = (h3.y) this.f1599r) != null) {
                            yVar2.H((MediaCodec) this.o);
                        }
                        ((MediaCodec) this.o).release();
                        this.f1596n = true;
                        return;
                    } finally {
                    }
                } catch (Throwable th) {
                    if (!this.f1596n) {
                        try {
                            int i10 = AbstractC1343A.f15195a;
                            if (i10 >= 30 && i10 < 33) {
                                ((MediaCodec) this.o).stop();
                            }
                            if (i10 >= 35 && (yVar = (h3.y) this.f1599r) != null) {
                                yVar.H((MediaCodec) this.o);
                            }
                            ((MediaCodec) this.o).release();
                            this.f1596n = true;
                        } finally {
                        }
                        break;
                    }
                    throw th;
                }
                break;
        }
    }

    @Override // s2.k
    public void b(int i7, m2.c cVar, long j3, int i10) {
        ((s2.l) this.f1598q).b(i7, cVar, j3, i10);
    }

    @Override // s2.k
    public void c(int i7, int i10, int i11, long j3) {
        ((s2.l) this.f1598q).c(i7, i10, i11, j3);
    }

    @Override // s2.k
    public void d(Bundle bundle) {
        ((s2.l) this.f1598q).d(bundle);
    }

    @Override // s2.k
    public int e(MediaCodec.BufferInfo bufferInfo) {
        int i7;
        ((s2.l) this.f1598q).k();
        s2.f fVar = (s2.f) this.f1597p;
        synchronized (fVar.f22079a) {
            try {
                IllegalStateException illegalStateException = fVar.f22089n;
                if (illegalStateException != null) {
                    fVar.f22089n = null;
                    throw illegalStateException;
                }
                MediaCodec.CodecException codecException = fVar.f22086j;
                if (codecException != null) {
                    fVar.f22086j = null;
                    throw codecException;
                }
                MediaCodec.CryptoException cryptoException = fVar.f22087k;
                if (cryptoException != null) {
                    fVar.f22087k = null;
                    throw cryptoException;
                }
                i7 = -1;
                if (!(fVar.l > 0 || fVar.f22088m)) {
                    C2393m c2393m = fVar.f22081e;
                    int i10 = c2393m.f22321b;
                    int i11 = c2393m.c;
                    if (!(i10 == i11)) {
                        if (i10 == i11) {
                            throw new ArrayIndexOutOfBoundsException();
                        }
                        i7 = c2393m.f22320a[i10];
                        c2393m.f22321b = c2393m.d & (i10 + 1);
                        if (i7 >= 0) {
                            AbstractC1346c.k(fVar.f22084h);
                            MediaCodec.BufferInfo bufferInfo2 = (MediaCodec.BufferInfo) fVar.f22082f.remove();
                            bufferInfo.set(bufferInfo2.offset, bufferInfo2.size, bufferInfo2.presentationTimeUs, bufferInfo2.flags);
                        } else if (i7 == -2) {
                            fVar.f22084h = (MediaFormat) fVar.f22083g.remove();
                        }
                    }
                }
            } finally {
            }
        }
        return i7;
    }

    @Override // s2.k
    public void f(long j3, int i7) {
        ((MediaCodec) this.o).releaseOutputBuffer(i7, j3);
    }

    @Override // s2.k
    public void flush() {
        ((s2.l) this.f1598q).flush();
        ((MediaCodec) this.o).flush();
        s2.f fVar = (s2.f) this.f1597p;
        synchronized (fVar.f22079a) {
            fVar.l++;
            Handler handler = fVar.c;
            int i7 = AbstractC1343A.f15195a;
            handler.post(new p2.p(fVar, 3));
        }
        ((MediaCodec) this.o).start();
    }

    @Override // s2.k
    public void g(int i7) {
        ((MediaCodec) this.o).releaseOutputBuffer(i7, false);
    }

    @Override // s2.k
    public void h(C2758d c2758d, Handler handler) {
        ((MediaCodec) this.o).setOnFrameRenderedListener(new s2.b(this, c2758d, 0), handler);
    }

    @Override // s2.k
    public void i(int i7) {
        ((MediaCodec) this.o).setVideoScalingMode(i7);
    }

    @Override // s2.k
    public MediaFormat j() {
        MediaFormat mediaFormat;
        s2.f fVar = (s2.f) this.f1597p;
        synchronized (fVar.f22079a) {
            try {
                mediaFormat = fVar.f22084h;
                if (mediaFormat == null) {
                    throw new IllegalStateException();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return mediaFormat;
    }

    @Override // s2.k
    public void k() {
        ((MediaCodec) this.o).detachOutputSurface();
    }

    @Override // s2.k
    public ByteBuffer l(int i7) {
        return ((MediaCodec) this.o).getInputBuffer(i7);
    }

    @Override // s2.k
    public void m(Surface surface) {
        ((MediaCodec) this.o).setOutputSurface(surface);
    }

    @Override // s2.k
    public ByteBuffer o(int i7) {
        return ((MediaCodec) this.o).getOutputBuffer(i7);
    }

    public boolean p(int i7, int i10) {
        V.d dVar = (V.d) this.f1597p;
        int i11 = this.f1595m;
        g0.m mVar = (g0.m) dVar.l[i7 + i11];
        g0.m mVar2 = (g0.m) ((V.d) this.f1598q).l[i11 + i10];
        Z z6 = a0.f1607a;
        return A7.m.a(mVar, mVar2) || g0.a.a(mVar, mVar2);
    }

    @Override // k2.InterfaceC1606g
    public k2.h q() {
        M.t tVar = (M.t) this.f1599r;
        l2.d dVar = null;
        k2.h hVarQ = tVar != null ? tVar.q() : null;
        int i7 = this.f1595m;
        InterfaceC1735b interfaceC1735b = (InterfaceC1735b) this.o;
        interfaceC1735b.getClass();
        if (!this.f1596n && hVarQ != null) {
            e7.f fVar = (e7.f) this.f1598q;
            if (fVar != null) {
                InterfaceC1735b interfaceC1735b2 = (InterfaceC1735b) fVar.f14480m;
                interfaceC1735b2.getClass();
                dVar = new l2.d(interfaceC1735b2);
            } else {
                dVar = new l2.d(interfaceC1735b);
            }
        }
        return new l2.e(interfaceC1735b, hVarQ, ((k2.r) this.f1597p).q(), dVar, i7);
    }

    @Override // s2.k
    public int r() {
        int i7;
        ((s2.l) this.f1598q).k();
        s2.f fVar = (s2.f) this.f1597p;
        synchronized (fVar.f22079a) {
            try {
                IllegalStateException illegalStateException = fVar.f22089n;
                if (illegalStateException != null) {
                    fVar.f22089n = null;
                    throw illegalStateException;
                }
                MediaCodec.CodecException codecException = fVar.f22086j;
                if (codecException != null) {
                    fVar.f22086j = null;
                    throw codecException;
                }
                MediaCodec.CryptoException cryptoException = fVar.f22087k;
                if (cryptoException != null) {
                    fVar.f22087k = null;
                    throw cryptoException;
                }
                i7 = -1;
                if (!(fVar.l > 0 || fVar.f22088m)) {
                    C2393m c2393m = fVar.d;
                    int i10 = c2393m.f22321b;
                    int i11 = c2393m.c;
                    if (!(i10 == i11)) {
                        if (i10 == i11) {
                            throw new ArrayIndexOutOfBoundsException();
                        }
                        i7 = c2393m.f22320a[i10];
                        c2393m.f22321b = (i10 + 1) & c2393m.d;
                    }
                }
            } finally {
            }
        }
        return i7;
    }

    @Override // s2.k
    public boolean s(C1764u c1764u) {
        s2.f fVar = (s2.f) this.f1597p;
        synchronized (fVar.f22079a) {
            fVar.o = c1764u;
        }
        return true;
    }

    public int u() {
        int i7;
        synchronized (this.o) {
            i7 = this.f1595m;
            this.f1595m = i7 + 1;
        }
        return i7;
    }

    public void v(int i7, Object obj) {
        synchronized (this.o) {
            try {
                x1 x1Var = (x1) ((C2465e) this.f1597p).remove(Integer.valueOf(i7));
                if (x1Var != null) {
                    if (C1.class == obj.getClass()) {
                        x1Var.l(obj);
                    } else {
                        AbstractC1346c.z("SequencedFutureManager", "Type mismatch, expected " + C1.class + ", but was " + obj.getClass());
                    }
                }
                if (((A2.l) this.f1598q) != null && ((C2465e) this.f1597p).isEmpty()) {
                    a();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public X(MediaCodec mediaCodec, HandlerThread handlerThread, s2.l lVar, h3.y yVar) {
        this.l = 3;
        this.o = mediaCodec;
        this.f1597p = new s2.f(handlerThread);
        this.f1598q = lVar;
        this.f1599r = yVar;
        this.f1595m = 0;
    }

    public X(Y y10, g0.n nVar, int i7, V.d dVar, V.d dVar2, boolean z6) {
        this.l = 0;
        this.f1599r = y10;
        this.o = nVar;
        this.f1595m = i7;
        this.f1597p = dVar;
        this.f1598q = dVar2;
        this.f1596n = z6;
    }
}
