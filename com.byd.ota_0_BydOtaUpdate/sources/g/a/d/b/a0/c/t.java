package g.a.d.b.a0.c;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f5426a = {-1, -1, -2, -1, -1, -1};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f5427b = {1, 0, 2, 0, 1, 0, -2, -1, -3, -1, -1, -1};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f5428c = {-1, -1, -3, -1, -2, -1, 1, 0, 2};

    public static void a(int[] iArr, int[] iArr2, int[] iArr3) {
        if (g.a.d.d.e.a(iArr, iArr2, iArr3) != 0 || (iArr3[5] == -1 && g.a.d.d.e.r(iArr3, f5426a))) {
            c(iArr3);
        }
    }

    public static void b(int[] iArr, int[] iArr2) {
        if (g.a.d.d.m.s(6, iArr, iArr2) != 0 || (iArr2[5] == -1 && g.a.d.d.e.r(iArr2, f5426a))) {
            c(iArr2);
        }
    }

    public static void c(int[] iArr) {
        long j = (((long) iArr[0]) & 4294967295L) + 1;
        iArr[0] = (int) j;
        long j2 = j >> 32;
        if (j2 != 0) {
            long j3 = j2 + (((long) iArr[1]) & 4294967295L);
            iArr[1] = (int) j3;
            j2 = j3 >> 32;
        }
        long j4 = j2 + (4294967295L & ((long) iArr[2])) + 1;
        iArr[2] = (int) j4;
        if ((j4 >> 32) != 0) {
            g.a.d.d.m.t(6, iArr, 3);
        }
    }

    public static int[] d(BigInteger bigInteger) {
        int[] iArrN = g.a.d.d.e.n(bigInteger);
        if (iArrN[5] == -1) {
            int[] iArr = f5426a;
            if (g.a.d.d.e.r(iArrN, iArr)) {
                g.a.d.d.e.G(iArr, iArrN);
            }
        }
        return iArrN;
    }

    public static void e(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] iArrI = g.a.d.d.e.i();
        g.a.d.d.e.x(iArr, iArr2, iArrI);
        h(iArrI, iArr3);
    }

    public static void f(int[] iArr, int[] iArr2, int[] iArr3) {
        if (g.a.d.d.e.B(iArr, iArr2, iArr3) != 0 || (iArr3[11] == -1 && g.a.d.d.m.q(12, iArr3, f5427b))) {
            int[] iArr4 = f5428c;
            if (g.a.d.d.m.e(iArr4.length, iArr4, iArr3) != 0) {
                g.a.d.d.m.t(12, iArr3, iArr4.length);
            }
        }
    }

    public static void g(int[] iArr, int[] iArr2) {
        if (g.a.d.d.e.u(iArr)) {
            g.a.d.d.e.J(iArr2);
        } else {
            g.a.d.d.e.F(f5426a, iArr, iArr2);
        }
    }

    public static void h(int[] iArr, int[] iArr2) {
        long j = ((long) iArr[6]) & 4294967295L;
        long j2 = ((long) iArr[7]) & 4294967295L;
        long j3 = ((long) iArr[8]) & 4294967295L;
        long j4 = ((long) iArr[9]) & 4294967295L;
        long j5 = (((long) iArr[10]) & 4294967295L) + j;
        long j6 = (((long) iArr[11]) & 4294967295L) + j2;
        long j7 = (((long) iArr[0]) & 4294967295L) + j5 + 0;
        int i2 = (int) j7;
        long j8 = (j7 >> 32) + (((long) iArr[1]) & 4294967295L) + j6;
        iArr2[1] = (int) j8;
        long j9 = j5 + j3;
        long j10 = j6 + j4;
        long j11 = (j8 >> 32) + (((long) iArr[2]) & 4294967295L) + j9;
        long j12 = j11 & 4294967295L;
        long j13 = (j11 >> 32) + (((long) iArr[3]) & 4294967295L) + j10;
        iArr2[3] = (int) j13;
        long j14 = (j13 >> 32) + (((long) iArr[4]) & 4294967295L) + (j9 - j);
        iArr2[4] = (int) j14;
        long j15 = (j14 >> 32) + (((long) iArr[5]) & 4294967295L) + (j10 - j2);
        iArr2[5] = (int) j15;
        long j16 = j15 >> 32;
        long j17 = j12 + j16;
        long j18 = j16 + (((long) i2) & 4294967295L);
        iArr2[0] = (int) j18;
        long j19 = j18 >> 32;
        if (j19 != 0) {
            long j20 = j19 + (4294967295L & ((long) iArr2[1]));
            iArr2[1] = (int) j20;
            j17 += j20 >> 32;
        }
        iArr2[2] = (int) j17;
        if (((j17 >> 32) == 0 || g.a.d.d.m.t(6, iArr2, 3) == 0) && !(iArr2[5] == -1 && g.a.d.d.e.r(iArr2, f5426a))) {
            return;
        }
        c(iArr2);
    }

    public static void i(int i2, int[] iArr) {
        long j;
        if (i2 != 0) {
            long j2 = ((long) i2) & 4294967295L;
            long j3 = (((long) iArr[0]) & 4294967295L) + j2 + 0;
            iArr[0] = (int) j3;
            long j4 = j3 >> 32;
            if (j4 != 0) {
                long j5 = j4 + (((long) iArr[1]) & 4294967295L);
                iArr[1] = (int) j5;
                j4 = j5 >> 32;
            }
            long j6 = j4 + (4294967295L & ((long) iArr[2])) + j2;
            iArr[2] = (int) j6;
            j = j6 >> 32;
        } else {
            j = 0;
        }
        if ((j == 0 || g.a.d.d.m.t(6, iArr, 3) == 0) && !(iArr[5] == -1 && g.a.d.d.e.r(iArr, f5426a))) {
            return;
        }
        c(iArr);
    }

    public static void j(int[] iArr, int[] iArr2) {
        int[] iArrI = g.a.d.d.e.i();
        g.a.d.d.e.D(iArr, iArrI);
        h(iArrI, iArr2);
    }

    public static void k(int[] iArr, int i2, int[] iArr2) {
        int[] iArrI = g.a.d.d.e.i();
        g.a.d.d.e.D(iArr, iArrI);
        while (true) {
            h(iArrI, iArr2);
            i2--;
            if (i2 <= 0) {
                return;
            } else {
                g.a.d.d.e.D(iArr2, iArrI);
            }
        }
    }

    public static void l(int[] iArr) {
        long j = (((long) iArr[0]) & 4294967295L) - 1;
        iArr[0] = (int) j;
        long j2 = j >> 32;
        if (j2 != 0) {
            long j3 = j2 + (((long) iArr[1]) & 4294967295L);
            iArr[1] = (int) j3;
            j2 = j3 >> 32;
        }
        long j4 = j2 + ((4294967295L & ((long) iArr[2])) - 1);
        iArr[2] = (int) j4;
        if ((j4 >> 32) != 0) {
            g.a.d.d.m.m(6, iArr, 3);
        }
    }

    public static void m(int[] iArr, int[] iArr2, int[] iArr3) {
        if (g.a.d.d.e.F(iArr, iArr2, iArr3) != 0) {
            l(iArr3);
        }
    }

    public static void n(int[] iArr, int[] iArr2) {
        if (g.a.d.d.m.E(6, iArr, 0, iArr2) != 0 || (iArr2[5] == -1 && g.a.d.d.e.r(iArr2, f5426a))) {
            c(iArr2);
        }
    }
}
