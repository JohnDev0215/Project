package u2;

import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.media.MediaCodec;
import h2.AbstractC1343A;
import java.nio.ByteBuffer;
import java.util.Arrays;
import r2.C2285g;

/* JADX INFO: loaded from: classes.dex */
public final class X {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final x2.e f22761a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f22762b;
    public final h2.t c;
    public G.i0 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public G.i0 f22763e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public G.i0 f22764f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f22765g;

    public X(x2.e eVar) {
        this.f22761a = eVar;
        int i7 = eVar.f23726b;
        this.f22762b = i7;
        this.c = new h2.t(32);
        G.i0 i0Var = new G.i0(0L, i7);
        this.d = i0Var;
        this.f22763e = i0Var;
        this.f22764f = i0Var;
    }

    public static G.i0 c(G.i0 i0Var, long j3, ByteBuffer byteBuffer, int i7) {
        while (j3 >= i0Var.f2583m) {
            i0Var = (G.i0) i0Var.o;
        }
        while (i7 > 0) {
            int iMin = Math.min(i7, (int) (i0Var.f2583m - j3));
            x2.a aVar = (x2.a) i0Var.f2584n;
            byteBuffer.put(aVar.f23719a, ((int) (j3 - i0Var.l)) + aVar.f23720b, iMin);
            i7 -= iMin;
            j3 += (long) iMin;
            if (j3 == i0Var.f2583m) {
                i0Var = (G.i0) i0Var.o;
            }
        }
        return i0Var;
    }

    public static G.i0 d(G.i0 i0Var, long j3, byte[] bArr, int i7) {
        while (j3 >= i0Var.f2583m) {
            i0Var = (G.i0) i0Var.o;
        }
        int i10 = i7;
        while (i10 > 0) {
            int iMin = Math.min(i10, (int) (i0Var.f2583m - j3));
            x2.a aVar = (x2.a) i0Var.f2584n;
            System.arraycopy(aVar.f23719a, ((int) (j3 - i0Var.l)) + aVar.f23720b, bArr, i7 - i10, iMin);
            i10 -= iMin;
            j3 += (long) iMin;
            if (j3 == i0Var.f2583m) {
                i0Var = (G.i0) i0Var.o;
            }
        }
        return i0Var;
    }

    public static G.i0 e(G.i0 i0Var, m2.g gVar, C2285g c2285g, h2.t tVar) {
        int iB;
        if (gVar.e(TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED)) {
            long j3 = c2285g.f21788b;
            tVar.E(1);
            G.i0 i0VarD = d(i0Var, j3, tVar.f15241a, 1);
            long j10 = j3 + 1;
            byte b10 = tVar.f15241a[0];
            boolean z6 = (b10 & 128) != 0;
            int i7 = b10 & 127;
            m2.c cVar = gVar.o;
            byte[] bArr = cVar.f18946a;
            if (bArr == null) {
                cVar.f18946a = new byte[16];
            } else {
                Arrays.fill(bArr, (byte) 0);
            }
            i0Var = d(i0VarD, j10, cVar.f18946a, i7);
            long j11 = j10 + ((long) i7);
            if (z6) {
                tVar.E(2);
                i0Var = d(i0Var, j11, tVar.f15241a, 2);
                j11 += 2;
                iB = tVar.B();
            } else {
                iB = 1;
            }
            int[] iArr = cVar.d;
            if (iArr == null || iArr.length < iB) {
                iArr = new int[iB];
            }
            int[] iArr2 = cVar.f18948e;
            if (iArr2 == null || iArr2.length < iB) {
                iArr2 = new int[iB];
            }
            if (z6) {
                int i10 = iB * 6;
                tVar.E(i10);
                i0Var = d(i0Var, j11, tVar.f15241a, i10);
                j11 += (long) i10;
                tVar.H(0);
                for (int i11 = 0; i11 < iB; i11++) {
                    iArr[i11] = tVar.B();
                    iArr2[i11] = tVar.z();
                }
            } else {
                iArr[0] = 0;
                iArr2[0] = c2285g.f21787a - ((int) (j11 - c2285g.f21788b));
            }
            B2.K k10 = (B2.K) c2285g.c;
            int i12 = AbstractC1343A.f15195a;
            byte[] bArr2 = k10.f518b;
            byte[] bArr3 = cVar.f18946a;
            cVar.f18949f = iB;
            cVar.d = iArr;
            cVar.f18948e = iArr2;
            cVar.f18947b = bArr2;
            cVar.f18946a = bArr3;
            int i13 = k10.f517a;
            cVar.c = i13;
            int i14 = k10.c;
            cVar.f18950g = i14;
            int i15 = k10.d;
            cVar.f18951h = i15;
            MediaCodec.CryptoInfo cryptoInfo = cVar.f18952i;
            cryptoInfo.numSubSamples = iB;
            cryptoInfo.numBytesOfClearData = iArr;
            cryptoInfo.numBytesOfEncryptedData = iArr2;
            cryptoInfo.key = bArr2;
            cryptoInfo.iv = bArr3;
            cryptoInfo.mode = i13;
            if (AbstractC1343A.f15195a >= 24) {
                m2.b bVar = cVar.f18953j;
                bVar.getClass();
                MediaCodec.CryptoInfo.Pattern pattern = (MediaCodec.CryptoInfo.Pattern) bVar.f18945m;
                pattern.set(i14, i15);
                ((MediaCodec.CryptoInfo) bVar.l).setPattern(pattern);
            }
            long j12 = c2285g.f21788b;
            int i16 = (int) (j11 - j12);
            c2285g.f21788b = j12 + ((long) i16);
            c2285g.f21787a -= i16;
        }
        if (!gVar.e(268435456)) {
            gVar.j(c2285g.f21787a);
            return c(i0Var, c2285g.f21788b, gVar.f18955p, c2285g.f21787a);
        }
        tVar.E(4);
        G.i0 i0VarD2 = d(i0Var, c2285g.f21788b, tVar.f15241a, 4);
        int iZ = tVar.z();
        c2285g.f21788b += 4;
        c2285g.f21787a -= 4;
        gVar.j(iZ);
        G.i0 i0VarC = c(i0VarD2, c2285g.f21788b, gVar.f18955p, iZ);
        c2285g.f21788b += (long) iZ;
        int i17 = c2285g.f21787a - iZ;
        c2285g.f21787a = i17;
        ByteBuffer byteBuffer = gVar.f18958s;
        if (byteBuffer == null || byteBuffer.capacity() < i17) {
            gVar.f18958s = ByteBuffer.allocate(i17);
        } else {
            gVar.f18958s.clear();
        }
        return c(i0VarC, c2285g.f21788b, gVar.f18958s, c2285g.f21787a);
    }

    public final void a(long j3) {
        G.i0 i0Var;
        if (j3 == -1) {
            return;
        }
        while (true) {
            i0Var = this.d;
            if (j3 < i0Var.f2583m) {
                break;
            }
            x2.e eVar = this.f22761a;
            x2.a aVar = (x2.a) i0Var.f2584n;
            synchronized (eVar) {
                x2.a[] aVarArr = eVar.f23728f;
                int i7 = eVar.f23727e;
                eVar.f23727e = i7 + 1;
                aVarArr[i7] = aVar;
                eVar.d--;
                eVar.notifyAll();
            }
            G.i0 i0Var2 = this.d;
            i0Var2.f2584n = null;
            G.i0 i0Var3 = (G.i0) i0Var2.o;
            i0Var2.o = null;
            this.d = i0Var3;
        }
        if (this.f22763e.l < i0Var.l) {
            this.f22763e = i0Var;
        }
    }

    public final int b(int i7) {
        x2.a aVar;
        G.i0 i0Var = this.f22764f;
        if (((x2.a) i0Var.f2584n) == null) {
            x2.e eVar = this.f22761a;
            synchronized (eVar) {
                try {
                    int i10 = eVar.d + 1;
                    eVar.d = i10;
                    int i11 = eVar.f23727e;
                    if (i11 > 0) {
                        x2.a[] aVarArr = eVar.f23728f;
                        int i12 = i11 - 1;
                        eVar.f23727e = i12;
                        aVar = aVarArr[i12];
                        aVar.getClass();
                        eVar.f23728f[eVar.f23727e] = null;
                    } else {
                        x2.a aVar2 = new x2.a(0, new byte[eVar.f23726b]);
                        x2.a[] aVarArr2 = eVar.f23728f;
                        if (i10 > aVarArr2.length) {
                            eVar.f23728f = (x2.a[]) Arrays.copyOf(aVarArr2, aVarArr2.length * 2);
                        }
                        aVar = aVar2;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            G.i0 i0Var2 = new G.i0(this.f22764f.f2583m, this.f22762b);
            i0Var.f2584n = aVar;
            i0Var.o = i0Var2;
        }
        return Math.min(i7, (int) (this.f22764f.f2583m - this.f22765g));
    }
}
