package h3;

import B2.C0073m;
import B2.N;
import android.util.SparseArray;
import h2.AbstractC1346c;
import io.sentry.C1489n1;
import java.io.EOFException;
import java.io.InterruptedIOException;
import u2.T;

/* JADX INFO: loaded from: classes.dex */
public final class C implements B2.r {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f15265e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f15266f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f15267g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f15268h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public G2.c f15269i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public T f15270j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f15271k;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final h2.y f15263a = new h2.y(0);
    public final h2.t c = new h2.t(4096);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final SparseArray f15264b = new SparseArray();
    public final C1349A d = new C1349A(0);

    @Override // B2.r
    public final void a() {
    }

    @Override // B2.r
    public final void b(B2.t tVar) {
        this.f15270j = (T) tVar;
    }

    @Override // B2.r
    public final int c(B2.s sVar, B2.v vVar) throws EOFException, InterruptedIOException {
        int i7;
        long j3;
        h2.t tVar;
        InterfaceC1358i kVar;
        long jC;
        long jC2;
        AbstractC1346c.k(this.f15270j);
        long j10 = ((C0073m) sVar).f588n;
        int i10 = (j10 > (-1L) ? 1 : (j10 == (-1L) ? 0 : -1));
        int i11 = 1;
        C1349A c1349a = this.d;
        if (i10 != 0 && !c1349a.d) {
            boolean z6 = c1349a.f15254f;
            h2.t tVar2 = c1349a.c;
            if (!z6) {
                C0073m c0073m = (C0073m) sVar;
                long j11 = c0073m.f588n;
                int iMin = (int) Math.min(20000L, j11);
                long j12 = j11 - ((long) iMin);
                if (c0073m.o != j12) {
                    vVar.f600a = j12;
                } else {
                    tVar2.E(iMin);
                    c0073m.f590q = 0;
                    c0073m.k(tVar2.f15241a, 0, iMin, false);
                    int i12 = tVar2.f15242b;
                    int i13 = tVar2.c - 4;
                    while (true) {
                        if (i13 < i12) {
                            jC2 = -9223372036854775807L;
                            break;
                        }
                        if (C1349A.b(i13, tVar2.f15241a) == 442) {
                            tVar2.H(i13 + 4);
                            jC2 = C1349A.c(tVar2);
                            if (jC2 != -9223372036854775807L) {
                                break;
                            }
                        }
                        i13--;
                    }
                    c1349a.f15256h = jC2;
                    c1349a.f15254f = true;
                    i11 = 0;
                }
            } else {
                if (c1349a.f15256h == -9223372036854775807L) {
                    c1349a.a((C0073m) sVar);
                    return 0;
                }
                if (c1349a.f15253e) {
                    long j13 = c1349a.f15255g;
                    if (j13 == -9223372036854775807L) {
                        c1349a.a((C0073m) sVar);
                        return 0;
                    }
                    h2.y yVar = c1349a.f15252b;
                    c1349a.f15257i = yVar.c(c1349a.f15256h) - yVar.b(j13);
                    c1349a.a((C0073m) sVar);
                    return 0;
                }
                C0073m c0073m2 = (C0073m) sVar;
                int iMin2 = (int) Math.min(20000L, c0073m2.f588n);
                long j14 = 0;
                if (c0073m2.o != j14) {
                    vVar.f600a = j14;
                } else {
                    tVar2.E(iMin2);
                    c0073m2.f590q = 0;
                    c0073m2.k(tVar2.f15241a, 0, iMin2, false);
                    int i14 = tVar2.f15242b;
                    int i15 = tVar2.c;
                    while (true) {
                        if (i14 >= i15 - 3) {
                            jC = -9223372036854775807L;
                            break;
                        }
                        if (C1349A.b(i14, tVar2.f15241a) == 442) {
                            tVar2.H(i14 + 4);
                            jC = C1349A.c(tVar2);
                            if (jC != -9223372036854775807L) {
                                break;
                            }
                        }
                        i14++;
                    }
                    c1349a.f15255g = jC;
                    c1349a.f15253e = true;
                    i11 = 0;
                }
            }
            return i11;
        }
        if (this.f15271k) {
            i7 = i10;
            j3 = j10;
        } else {
            this.f15271k = true;
            long j15 = c1349a.f15257i;
            if (j15 != -9223372036854775807L) {
                i7 = i10;
                j3 = j10;
                G2.c cVar = new G2.c(new C8.d(1), new C1489n1(c1349a.f15252b), j15, j15 + 1, 0L, j10, 188L, 1000);
                this.f15269i = cVar;
                this.f15270j.w(cVar.f2652a);
            } else {
                i7 = i10;
                j3 = j10;
                this.f15270j.w(new B2.w(j15));
            }
        }
        G2.c cVar2 = this.f15269i;
        if (cVar2 != null && cVar2.c != null) {
            return cVar2.b((C0073m) sVar, vVar);
        }
        C0073m c0073m3 = (C0073m) sVar;
        c0073m3.f590q = 0;
        long jL = i7 != 0 ? j3 - c0073m3.l() : -1L;
        if (jL != -1 && jL < 4) {
            return -1;
        }
        h2.t tVar3 = this.c;
        if (!c0073m3.k(tVar3.f15241a, 0, 4, true)) {
            return -1;
        }
        tVar3.H(0);
        int iH = tVar3.h();
        if (iH == 441) {
            return -1;
        }
        if (iH == 442) {
            c0073m3.k(tVar3.f15241a, 0, 10, false);
            tVar3.H(9);
            c0073m3.i((tVar3.v() & 7) + 14);
            return 0;
        }
        if (iH == 443) {
            c0073m3.k(tVar3.f15241a, 0, 2, false);
            tVar3.H(0);
            c0073m3.i(tVar3.B() + 6);
            return 0;
        }
        if (((iH & (-256)) >> 8) != 1) {
            c0073m3.i(1);
            return 0;
        }
        int i16 = iH & 255;
        SparseArray sparseArray = this.f15264b;
        B b10 = (B) sparseArray.get(i16);
        if (!this.f15265e) {
            if (b10 == null) {
                if (i16 == 189) {
                    kVar = new C1352c();
                    this.f15266f = true;
                    this.f15268h = c0073m3.o;
                } else if ((iH & 224) == 192) {
                    kVar = new u(null, 0);
                    this.f15266f = true;
                    this.f15268h = c0073m3.o;
                } else if ((iH & 240) == 224) {
                    kVar = new k(null);
                    this.f15267g = true;
                    this.f15268h = c0073m3.o;
                } else {
                    kVar = null;
                }
                if (kVar != null) {
                    kVar.f(this.f15270j, new H(i16, 256));
                    b10 = new B(kVar, this.f15263a);
                    sparseArray.put(i16, b10);
                }
            }
            if (c0073m3.o > ((this.f15266f && this.f15267g) ? this.f15268h + 8192 : 1048576L)) {
                this.f15265e = true;
                this.f15270j.e();
            }
        }
        c0073m3.k(tVar3.f15241a, 0, 2, false);
        tVar3.H(0);
        int iB = tVar3.B() + 6;
        if (b10 == null) {
            c0073m3.i(iB);
            return 0;
        }
        tVar3.E(iB);
        c0073m3.c(tVar3.f15241a, 0, iB, false);
        tVar3.H(6);
        N n10 = b10.c;
        tVar3.f(0, 3, n10.d);
        n10.r(0);
        n10.u(8);
        b10.d = n10.h();
        b10.f15260e = n10.h();
        n10.u(6);
        tVar3.f(0, n10.i(8), n10.d);
        n10.r(0);
        b10.f15262g = 0L;
        if (b10.d) {
            n10.u(4);
            long jI = ((long) n10.i(3)) << 30;
            n10.u(1);
            long jI2 = jI | ((long) (n10.i(15) << 15));
            n10.u(1);
            long jI3 = jI2 | ((long) n10.i(15));
            n10.u(1);
            boolean z10 = b10.f15261f;
            h2.y yVar2 = b10.f15259b;
            if (z10 || !b10.f15260e) {
                tVar = tVar3;
            } else {
                n10.u(4);
                long jI4 = ((long) n10.i(3)) << 30;
                n10.u(1);
                tVar = tVar3;
                long jI5 = jI4 | ((long) (n10.i(15) << 15));
                n10.u(1);
                long jI6 = jI5 | ((long) n10.i(15));
                n10.u(1);
                yVar2.b(jI6);
                b10.f15261f = true;
            }
            b10.f15262g = yVar2.b(jI3);
        } else {
            tVar = tVar3;
        }
        long j16 = b10.f15262g;
        InterfaceC1358i interfaceC1358i = b10.f15258a;
        interfaceC1358i.e(j16, 4);
        interfaceC1358i.c(tVar);
        interfaceC1358i.d(false);
        tVar.G(tVar.f15241a.length);
        return 0;
    }

    @Override // B2.r
    public final void e(long j3, long j10) {
        long j11;
        h2.y yVar = this.f15263a;
        synchronized (yVar) {
            j11 = yVar.f15250b;
        }
        boolean z6 = j11 == -9223372036854775807L;
        if (!z6) {
            long jD = yVar.d();
            z6 = (jD == -9223372036854775807L || jD == 0 || jD == j10) ? false : true;
        }
        if (z6) {
            yVar.e(j10);
        }
        G2.c cVar = this.f15269i;
        if (cVar != null) {
            cVar.d(j10);
        }
        int i7 = 0;
        while (true) {
            SparseArray sparseArray = this.f15264b;
            if (i7 >= sparseArray.size()) {
                return;
            }
            B b10 = (B) sparseArray.valueAt(i7);
            b10.f15261f = false;
            b10.f15258a.b();
            i7++;
        }
    }

    @Override // B2.r
    public final boolean f(B2.s sVar) throws EOFException, InterruptedIOException {
        byte[] bArr = new byte[14];
        C0073m c0073m = (C0073m) sVar;
        c0073m.k(bArr, 0, 14, false);
        if (442 != (((bArr[0] & 255) << 24) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8) | (bArr[3] & 255)) || (bArr[4] & 196) != 68 || (bArr[6] & 4) != 4 || (bArr[8] & 4) != 4 || (bArr[9] & 1) != 1 || (bArr[12] & 3) != 3) {
            return false;
        }
        c0073m.e(bArr[13] & 7, false);
        c0073m.k(bArr, 0, 3, false);
        return 1 == ((((bArr[0] & 255) << 16) | ((bArr[1] & 255) << 8)) | (bArr[2] & 255));
    }
}
