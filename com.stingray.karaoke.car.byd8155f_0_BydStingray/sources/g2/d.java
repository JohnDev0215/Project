package G2;

import B2.AbstractC0062b;
import B2.B;
import B2.C0073m;
import B2.F;
import B2.L;
import B2.N;
import B2.r;
import B2.s;
import B2.v;
import B2.w;
import B2.x;
import P2.h;
import d5.I;
import e2.K;
import e2.M;
import h2.AbstractC1343A;
import h2.t;
import java.io.EOFException;
import java.io.InterruptedIOException;
import java.util.Arrays;
import u2.T;

/* JADX INFO: loaded from: classes.dex */
public final class d implements r {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public T f2656e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public L f2657f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public K f2659h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public x f2660i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f2661j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f2662k;
    public c l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f2663m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public long f2664n;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final byte[] f2654a = new byte[42];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final t f2655b = new t(0, new byte[32768]);
    public final boolean c = false;
    public final v d = new v();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f2658g = 0;

    @Override // B2.r
    public final void a() {
    }

    @Override // B2.r
    public final void b(B2.t tVar) {
        T t10 = (T) tVar;
        this.f2656e = t10;
        this.f2657f = t10.u(0, 1);
        t10.e();
    }

    @Override // B2.r
    public final int c(s sVar, v vVar) throws M, EOFException, InterruptedIOException {
        x xVar;
        F wVar;
        long j3;
        long j10;
        boolean z6;
        long j11;
        boolean zB;
        boolean z10 = true;
        int i7 = 0;
        int i10 = this.f2658g;
        K k10 = null;
        if (i10 == 0) {
            ((C0073m) sVar).f590q = 0;
            C0073m c0073m = (C0073m) sVar;
            long jL = c0073m.l();
            K kA = new B(0).a(c0073m, !this.c ? null : h.c);
            if (kA != null && kA.l.length != 0) {
                k10 = kA;
            }
            c0073m.i((int) (c0073m.l() - jL));
            this.f2659h = k10;
            this.f2658g = 1;
            return 0;
        }
        byte[] bArr = this.f2654a;
        if (i10 == 1) {
            ((C0073m) sVar).k(bArr, 0, bArr.length, false);
            ((C0073m) sVar).f590q = 0;
            this.f2658g = 2;
            return 0;
        }
        int i11 = 3;
        if (i10 == 2) {
            t tVar = new t(4);
            ((C0073m) sVar).c(tVar.f15241a, 0, 4, false);
            if (tVar.x() != 1716281667) {
                throw M.a(null, "Failed to read FLAC stream marker.");
            }
            this.f2658g = 3;
            return 0;
        }
        if (i10 == 3) {
            x xVar2 = this.f2660i;
            boolean z11 = false;
            while (!z11) {
                ((C0073m) sVar).f590q = 0;
                byte[] bArr2 = new byte[4];
                N n10 = new N(4, bArr2);
                C0073m c0073m2 = (C0073m) sVar;
                c0073m2.k(bArr2, 0, 4, false);
                boolean zH = n10.h();
                int i12 = n10.i(i);
                int i13 = n10.i(24) + 4;
                if (i12 == 0) {
                    byte[] bArr3 = new byte[38];
                    c0073m2.c(bArr3, 0, 38, false);
                    xVar2 = new x(4, bArr3);
                } else {
                    if (xVar2 == null) {
                        throw new IllegalArgumentException();
                    }
                    if (i12 == i11) {
                        t tVar2 = new t(i13);
                        c0073m2.c(tVar2.f15241a, 0, i13, false);
                        xVar = new x(xVar2.f603a, xVar2.f604b, xVar2.c, xVar2.d, xVar2.f605e, xVar2.f607g, xVar2.f608h, xVar2.f610j, AbstractC0062b.t(tVar2), xVar2.l);
                    } else {
                        K k11 = xVar2.l;
                        if (i12 == 4) {
                            t tVar3 = new t(i13);
                            c0073m2.c(tVar3.f15241a, 0, i13, false);
                            tVar3.I(4);
                            K kR = AbstractC0062b.r(Arrays.asList((String[]) AbstractC0062b.u(tVar3, false, false).f527m));
                            if (k11 != null) {
                                kR = k11.d(kR);
                            }
                            xVar = new x(xVar2.f603a, xVar2.f604b, xVar2.c, xVar2.d, xVar2.f605e, xVar2.f607g, xVar2.f608h, xVar2.f610j, xVar2.f611k, kR);
                        } else if (i12 == 6) {
                            t tVar4 = new t(i13);
                            c0073m2.c(tVar4.f15241a, 0, i13, false);
                            tVar4.I(4);
                            K k12 = new K(I.B(N2.a.a(tVar4)));
                            if (k11 != null) {
                                k12 = k11.d(k12);
                            }
                            xVar = new x(xVar2.f603a, xVar2.f604b, xVar2.c, xVar2.d, xVar2.f605e, xVar2.f607g, xVar2.f608h, xVar2.f610j, xVar2.f611k, k12);
                        } else {
                            c0073m2.i(i13);
                        }
                    }
                    xVar2 = xVar;
                }
                int i14 = AbstractC1343A.f15195a;
                this.f2660i = xVar2;
                z11 = zH;
                i11 = 3;
                i = 7;
            }
            this.f2660i.getClass();
            this.f2661j = Math.max(this.f2660i.c, 6);
            L l = this.f2657f;
            int i15 = AbstractC1343A.f15195a;
            l.d(this.f2660i.c(bArr, this.f2659h));
            this.f2658g = 4;
            return 0;
        }
        long j12 = 0;
        if (i10 == 4) {
            ((C0073m) sVar).f590q = 0;
            t tVar5 = new t(2);
            C0073m c0073m3 = (C0073m) sVar;
            c0073m3.k(tVar5.f15241a, 0, 2, false);
            int iB = tVar5.B();
            if ((iB >> 2) != 16382) {
                c0073m3.f590q = 0;
                throw M.a(null, "First frame does not start with sync code.");
            }
            c0073m3.f590q = 0;
            this.f2662k = iB;
            T t10 = this.f2656e;
            int i16 = AbstractC1343A.f15195a;
            long j13 = c0073m3.o;
            this.f2660i.getClass();
            x xVar3 = this.f2660i;
            if (xVar3.f611k != null) {
                wVar = new w(0, j13, xVar3);
            } else {
                long j14 = c0073m3.f588n;
                if (j14 == -1 || xVar3.f610j <= 0) {
                    wVar = new w(xVar3.b());
                } else {
                    int i17 = this.f2662k;
                    a aVar = new a(xVar3, i7);
                    b bVar = new b(xVar3, i17);
                    long jB = xVar3.b();
                    int i18 = xVar3.c;
                    int i19 = xVar3.d;
                    if (i19 > 0) {
                        j3 = j14;
                        j10 = ((((long) i19) + ((long) i18)) / 2) + 1;
                    } else {
                        j3 = j14;
                        int i20 = xVar3.f604b;
                        int i21 = xVar3.f603a;
                        j10 = (((((i21 != i20 || i21 <= 0) ? 4096L : i21) * ((long) xVar3.f607g)) * ((long) xVar3.f608h)) / 8) + 64;
                    }
                    c cVar = new c(aVar, bVar, jB, xVar3.f610j, j13, j3, j10, Math.max(6, i18));
                    this.l = cVar;
                    wVar = cVar.f2652a;
                }
            }
            t10.w(wVar);
            this.f2658g = 5;
            return 0;
        }
        if (i10 != 5) {
            throw new IllegalStateException();
        }
        this.f2657f.getClass();
        this.f2660i.getClass();
        c cVar2 = this.l;
        if (cVar2 != null && cVar2.c != null) {
            return cVar2.b((C0073m) sVar, vVar);
        }
        if (this.f2664n == -1) {
            x xVar4 = this.f2660i;
            ((C0073m) sVar).f590q = 0;
            C0073m c0073m4 = (C0073m) sVar;
            c0073m4.e(1, false);
            byte[] bArr4 = new byte[1];
            c0073m4.k(bArr4, 0, 1, false);
            boolean z12 = (bArr4[0] & 1) == 1;
            c0073m4.e(2, false);
            i = z12 ? 7 : 6;
            t tVar6 = new t(i);
            byte[] bArr5 = tVar6.f15241a;
            int i22 = 0;
            while (i22 < i) {
                int iQ = c0073m4.q(i22, i - i22, bArr5);
                if (iQ == -1) {
                    break;
                }
                i22 += iQ;
            }
            tVar6.G(i22);
            c0073m4.f590q = 0;
            try {
                long jC = tVar6.C();
                if (!z12) {
                    jC *= (long) xVar4.f604b;
                }
                j12 = jC;
            } catch (NumberFormatException unused) {
                z10 = false;
            }
            if (!z10) {
                throw M.a(null, null);
            }
            this.f2664n = j12;
            return 0;
        }
        t tVar7 = this.f2655b;
        int i23 = tVar7.c;
        if (i23 < 32768) {
            int iN = ((C0073m) sVar).n(tVar7.f15241a, i23, 32768 - i23);
            z6 = iN == -1;
            if (!z6) {
                tVar7.G(i23 + iN);
            } else if (tVar7.a() == 0) {
                long j15 = this.f2664n * 1000000;
                x xVar5 = this.f2660i;
                int i24 = AbstractC1343A.f15195a;
                this.f2657f.b(j15 / ((long) xVar5.f605e), 1, this.f2663m, 0, null);
                return -1;
            }
        } else {
            z6 = false;
        }
        int i25 = tVar7.f15242b;
        int i26 = this.f2663m;
        int i27 = this.f2661j;
        if (i26 < i27) {
            tVar7.I(Math.min(i27 - i26, tVar7.a()));
        }
        this.f2660i.getClass();
        int i28 = tVar7.f15242b;
        while (true) {
            int i29 = tVar7.c - 16;
            v vVar2 = this.d;
            if (i28 <= i29) {
                tVar7.H(i28);
                if (AbstractC0062b.b(tVar7, this.f2660i, this.f2662k, vVar2)) {
                    tVar7.H(i28);
                    j11 = vVar2.f600a;
                    break;
                }
                i28++;
            } else {
                if (z6) {
                    while (true) {
                        int i30 = tVar7.c;
                        if (i28 > i30 - this.f2661j) {
                            tVar7.H(i30);
                            break;
                        }
                        tVar7.H(i28);
                        try {
                            zB = AbstractC0062b.b(tVar7, this.f2660i, this.f2662k, vVar2);
                        } catch (IndexOutOfBoundsException unused2) {
                            zB = false;
                        }
                        if (tVar7.f15242b > tVar7.c) {
                            zB = false;
                        }
                        if (zB) {
                            tVar7.H(i28);
                            j11 = vVar2.f600a;
                            break;
                        }
                        i28++;
                    }
                } else {
                    tVar7.H(i28);
                }
                j11 = -1;
            }
        }
        int i31 = tVar7.f15242b - i25;
        tVar7.H(i25);
        this.f2657f.a(tVar7, i31, 0);
        int i32 = i31 + this.f2663m;
        this.f2663m = i32;
        if (j11 != -1) {
            long j16 = this.f2664n * 1000000;
            x xVar6 = this.f2660i;
            int i33 = AbstractC1343A.f15195a;
            this.f2657f.b(j16 / ((long) xVar6.f605e), 1, i32, 0, null);
            this.f2663m = 0;
            this.f2664n = j11;
        }
        if (tVar7.a() >= 16) {
            return 0;
        }
        int iA = tVar7.a();
        byte[] bArr6 = tVar7.f15241a;
        System.arraycopy(bArr6, tVar7.f15242b, bArr6, 0, iA);
        tVar7.H(0);
        tVar7.G(iA);
        return 0;
    }

    @Override // B2.r
    public final void e(long j3, long j10) {
        if (j3 == 0) {
            this.f2658g = 0;
        } else {
            c cVar = this.l;
            if (cVar != null) {
                cVar.d(j10);
            }
        }
        this.f2664n = j10 != 0 ? -1L : 0L;
        this.f2663m = 0;
        this.f2655b.E(0);
    }

    @Override // B2.r
    public final boolean f(s sVar) {
        C0073m c0073m = (C0073m) sVar;
        K kA = new B(0).a(c0073m, h.c);
        if (kA != null) {
            int length = kA.l.length;
        }
        t tVar = new t(4);
        c0073m.k(tVar.f15241a, 0, 4, false);
        return tVar.x() == 1716281667;
    }
}
