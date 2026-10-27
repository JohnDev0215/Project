package g.a.d.b.a0.c;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f5302a = {1, 0, 0, -1, -1, -1, -1};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f5303b = {1, 0, 0, -2, -1, -1, 0, 2, 0, 0, -2, -1, -1, -1};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f5304c = {-1, -1, -1, 1, 0, 0, -1, -3, -1, -1, 1};

    public static void a(int[] iArr, int[] iArr2, int[] iArr3) {
        if (g.a.d.d.f.a(iArr, iArr2, iArr3) != 0 || (iArr3[6] == -1 && g.a.d.d.f.j(iArr3, f5302a))) {
            c(iArr3);
        }
    }

    public static void b(int[] iArr, int[] iArr2) {
        if (g.a.d.d.m.s(7, iArr, iArr2) != 0 || (iArr2[6] == -1 && g.a.d.d.f.j(iArr2, f5302a))) {
            c(iArr2);
        }
    }

    public static void c(int[] iArr) {
        long j = (((long) iArr[0]) & 4294967295L) - 1;
        iArr[0] = (int) j;
        long j2 = j >> 32;
        if (j2 != 0) {
            long j3 = j2 + (((long) iArr[1]) & 4294967295L);
            iArr[1] = (int) j3;
            long j4 = (j3 >> 32) + (((long) iArr[2]) & 4294967295L);
            iArr[2] = (int) j4;
            j2 = j4 >> 32;
        }
        long j5 = j2 + (4294967295L & ((long) iArr[3])) + 1;
        iArr[3] = (int) j5;
        if ((j5 >> 32) != 0) {
            g.a.d.d.m.t(7, iArr, 4);
        }
    }

    public static int[] d(BigInteger bigInteger) {
        int[] iArrH = g.a.d.d.f.h(bigInteger);
        if (iArrH[6] == -1) {
            int[] iArr = f5302a;
            if (g.a.d.d.f.j(iArrH, iArr)) {
                g.a.d.d.f.t(iArr, iArrH);
            }
        }
        return iArrH;
    }

    public static void e(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] iArrF = g.a.d.d.f.f();
        g.a.d.d.f.m(iArr, iArr2, iArrF);
        h(iArrF, iArr3);
    }

    public static void f(int[] iArr, int[] iArr2, int[] iArr3) {
        if (g.a.d.d.f.q(iArr, iArr2, iArr3) != 0 || (iArr3[13] == -1 && g.a.d.d.m.q(14, iArr3, f5303b))) {
            int[] iArr4 = f5304c;
            if (g.a.d.d.m.e(iArr4.length, iArr4, iArr3) != 0) {
                g.a.d.d.m.t(14, iArr3, iArr4.length);
            }
        }
    }

    public static void g(int[] iArr, int[] iArr2) {
        if (g.a.d.d.f.l(iArr)) {
            g.a.d.d.f.v(iArr2);
        } else {
            g.a.d.d.f.s(f5302a, iArr, iArr2);
        }
    }

    public static void h(int[] iArr, int[] iArr2) {
        long j = ((long) iArr[10]) & 4294967295L;
        long j2 = ((long) iArr[11]) & 4294967295L;
        long j3 = ((long) iArr[12]) & 4294967295L;
        long j4 = ((long) iArr[13]) & 4294967295L;
        long j5 = ((((long) iArr[7]) & 4294967295L) + j2) - 1;
        long j6 = (((long) iArr[8]) & 4294967295L) + j3;
        long j7 = (((long) iArr[9]) & 4294967295L) + j4;
        long j8 = ((((long) iArr[0]) & 4294967295L) - j5) + 0;
        long j9 = j8 & 4294967295L;
        long j10 = (j8 >> 32) + ((((long) iArr[1]) & 4294967295L) - j6);
        iArr2[1] = (int) j10;
        long j11 = (j10 >> 32) + ((((long) iArr[2]) & 4294967295L) - j7);
        iArr2[2] = (int) j11;
        long j12 = (j11 >> 32) + (((((long) iArr[3]) & 4294967295L) + j5) - j);
        long j13 = j12 & 4294967295L;
        long j14 = (j12 >> 32) + (((((long) iArr[4]) & 4294967295L) + j6) - j2);
        iArr2[4] = (int) j14;
        long j15 = (j14 >> 32) + (((((long) iArr[5]) & 4294967295L) + j7) - j3);
        iArr2[5] = (int) j15;
        long j16 = (j15 >> 32) + (((((long) iArr[6]) & 4294967295L) + j) - j4);
        iArr2[6] = (int) j16;
        long j17 = (j16 >> 32) + 1;
        long j18 = j13 + j17;
        long j19 = j9 - j17;
        iArr2[0] = (int) j19;
        long j20 = j19 >> 32;
        if (j20 != 0) {
            long j21 = j20 + (((long) iArr2[1]) & 4294967295L);
            iArr2[1] = (int) j21;
            long j22 = (j21 >> 32) + (4294967295L & ((long) iArr2[2]));
            iArr2[2] = (int) j22;
            j18 += j22 >> 32;
        }
        iArr2[3] = (int) j18;
        if (((j18 >> 32) == 0 || g.a.d.d.m.t(7, iArr2, 4) == 0) && !(iArr2[6] == -1 && g.a.d.d.f.j(iArr2, f5302a))) {
            return;
        }
        c(iArr2);
    }

    public static void i(int i2, int[] iArr) {
        long j;
        if (i2 != 0) {
            long j2 = ((long) i2) & 4294967295L;
            long j3 = ((((long) iArr[0]) & 4294967295L) - j2) + 0;
            iArr[0] = (int) j3;
            long j4 = j3 >> 32;
            if (j4 != 0) {
                long j5 = j4 + (((long) iArr[1]) & 4294967295L);
                iArr[1] = (int) j5;
                long j6 = (j5 >> 32) + (((long) iArr[2]) & 4294967295L);
                iArr[2] = (int) j6;
                j4 = j6 >> 32;
            }
            long j7 = j4 + (4294967295L & ((long) iArr[3])) + j2;
            iArr[3] = (int) j7;
            j = j7 >> 32;
        } else {
            j = 0;
        }
        if ((j == 0 || g.a.d.d.m.t(7, iArr, 4) == 0) && !(iArr[6] == -1 && g.a.d.d.f.j(iArr, f5302a))) {
            return;
        }
        c(iArr);
    }

    public static void j(int[] iArr, int[] iArr2) {
        int[] iArrF = g.a.d.d.f.f();
        g.a.d.d.f.r(iArr, iArrF);
        h(iArrF, iArr2);
    }

    public static void k(int[] iArr, int i2, int[] iArr2) {
        int[] iArrF = g.a.d.d.f.f();
        g.a.d.d.f.r(iArr, iArrF);
        while (true) {
            h(iArrF, iArr2);
            i2--;
            if (i2 <= 0) {
                return;
            } else {
                g.a.d.d.f.r(iArr2, iArrF);
            }
        }
    }

    public static void l(int[] iArr) {
        long j = (((long) iArr[0]) & 4294967295L) + 1;
        iArr[0] = (int) j;
        long j2 = j >> 32;
        if (j2 != 0) {
            long j3 = j2 + (((long) iArr[1]) & 4294967295L);
            iArr[1] = (int) j3;
            long j4 = (j3 >> 32) + (((long) iArr[2]) & 4294967295L);
            iArr[2] = (int) j4;
            j2 = j4 >> 32;
        }
        long j5 = j2 + ((4294967295L & ((long) iArr[3])) - 1);
        iArr[3] = (int) j5;
        if ((j5 >> 32) != 0) {
            g.a.d.d.m.m(7, iArr, 4);
        }
    }

    public static void m(int[] iArr, int[] iArr2, int[] iArr3) {
        if (g.a.d.d.f.s(iArr, iArr2, iArr3) != 0) {
            l(iArr3);
        }
    }

    public static void n(int[] iArr, int[] iArr2) {
        if (g.a.d.d.m.E(7, iArr, 0, iArr2) != 0 || (iArr2[6] == -1 && g.a.d.d.f.j(iArr2, f5302a))) {
            c(iArr2);
        }
    }
}
