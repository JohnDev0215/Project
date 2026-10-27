package g.a.d.b.a0.c;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class r0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f5412a = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 511};

    public static void a(int[] iArr, int[] iArr2, int[] iArr3) {
        int iA = g.a.d.d.m.a(16, iArr, iArr2, iArr3) + iArr[16] + iArr2[16];
        if (iA > 511 || (iA == 511 && g.a.d.d.m.n(16, iArr3, f5412a))) {
            iA = (iA + g.a.d.d.m.r(16, iArr3)) & 511;
        }
        iArr3[16] = iA;
    }

    public static void b(int[] iArr, int[] iArr2) {
        int iS = g.a.d.d.m.s(16, iArr, iArr2) + iArr[16];
        if (iS > 511 || (iS == 511 && g.a.d.d.m.n(16, iArr2, f5412a))) {
            iS = (iS + g.a.d.d.m.r(16, iArr2)) & 511;
        }
        iArr2[16] = iS;
    }

    public static int[] c(BigInteger bigInteger) {
        int[] iArrO = g.a.d.d.m.o(521, bigInteger);
        if (g.a.d.d.m.n(17, iArrO, f5412a)) {
            g.a.d.d.m.Q(17, iArrO);
        }
        return iArrO;
    }

    public static void d(int[] iArr, int[] iArr2, int[] iArr3) {
        g.a.d.d.k.a(iArr, iArr2, iArr3);
        int i2 = iArr[16];
        int i3 = iArr2[16];
        iArr3[32] = g.a.d.d.m.x(16, i2, iArr2, i3, iArr, iArr3, 16) + (i2 * i3);
    }

    public static void e(int[] iArr, int[] iArr2) {
        g.a.d.d.k.b(iArr, iArr2);
        int i2 = iArr[16];
        iArr2[32] = g.a.d.d.m.y(16, i2 << 1, iArr, 0, iArr2, 16) + (i2 * i2);
    }

    public static void f(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] iArrJ = g.a.d.d.m.j(33);
        d(iArr, iArr2, iArrJ);
        h(iArrJ, iArr3);
    }

    public static void g(int[] iArr, int[] iArr2) {
        if (g.a.d.d.m.w(17, iArr)) {
            g.a.d.d.m.Q(17, iArr2);
        } else {
            g.a.d.d.m.K(17, f5412a, iArr, iArr2);
        }
    }

    public static void h(int[] iArr, int[] iArr2) {
        int i2 = iArr[32];
        int iB = (g.a.d.d.m.B(16, iArr, 16, 9, i2, iArr2, 0) >>> 23) + (i2 >>> 9) + g.a.d.d.m.e(16, iArr, iArr2);
        if (iB > 511 || (iB == 511 && g.a.d.d.m.n(16, iArr2, f5412a))) {
            iB = (iB + g.a.d.d.m.r(16, iArr2)) & 511;
        }
        iArr2[16] = iB;
    }

    public static void i(int[] iArr) {
        int i2 = iArr[16];
        int iG = g.a.d.d.m.g(16, i2 >>> 9, iArr) + (i2 & 511);
        if (iG > 511 || (iG == 511 && g.a.d.d.m.n(16, iArr, f5412a))) {
            iG = (iG + g.a.d.d.m.r(16, iArr)) & 511;
        }
        iArr[16] = iG;
    }

    public static void j(int[] iArr, int[] iArr2) {
        int[] iArrJ = g.a.d.d.m.j(33);
        e(iArr, iArrJ);
        h(iArrJ, iArr2);
    }

    public static void k(int[] iArr, int i2, int[] iArr2) {
        int[] iArrJ = g.a.d.d.m.j(33);
        e(iArr, iArrJ);
        while (true) {
            h(iArrJ, iArr2);
            i2--;
            if (i2 <= 0) {
                return;
            } else {
                e(iArr2, iArrJ);
            }
        }
    }

    public static void l(int[] iArr, int[] iArr2, int[] iArr3) {
        int iK = (g.a.d.d.m.K(16, iArr, iArr2, iArr3) + iArr[16]) - iArr2[16];
        if (iK < 0) {
            iK = (iK + g.a.d.d.m.l(16, iArr3)) & 511;
        }
        iArr3[16] = iK;
    }

    public static void m(int[] iArr, int[] iArr2) {
        int i2 = iArr[16];
        iArr2[16] = (g.a.d.d.m.E(16, iArr, i2 << 23, iArr2) | (i2 << 1)) & 511;
    }
}
