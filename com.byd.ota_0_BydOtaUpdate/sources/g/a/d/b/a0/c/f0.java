package g.a.d.b.a0.c;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class f0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f5329a = {-977, -2, -1, -1, -1, -1, -1, -1};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f5330b = {954529, 1954, 1, 0, 0, 0, 0, 0, -1954, -3, -1, -1, -1, -1, -1, -1};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f5331c = {-954529, -1955, -2, -1, -1, -1, -1, -1, 1953, 2};

    public static void a(int[] iArr, int[] iArr2, int[] iArr3) {
        if (g.a.d.d.g.a(iArr, iArr2, iArr3) != 0 || (iArr3[7] == -1 && g.a.d.d.g.s(iArr3, f5329a))) {
            g.a.d.d.m.b(8, 977, iArr3);
        }
    }

    public static void b(int[] iArr, int[] iArr2) {
        if (g.a.d.d.m.s(8, iArr, iArr2) != 0 || (iArr2[7] == -1 && g.a.d.d.g.s(iArr2, f5329a))) {
            g.a.d.d.m.b(8, 977, iArr2);
        }
    }

    public static int[] c(BigInteger bigInteger) {
        int[] iArrO = g.a.d.d.g.o(bigInteger);
        if (iArrO[7] == -1) {
            int[] iArr = f5329a;
            if (g.a.d.d.g.s(iArrO, iArr)) {
                g.a.d.d.g.I(iArr, iArrO);
            }
        }
        return iArrO;
    }

    public static void d(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] iArrJ = g.a.d.d.g.j();
        g.a.d.d.g.y(iArr, iArr2, iArrJ);
        g(iArrJ, iArr3);
    }

    public static void e(int[] iArr, int[] iArr2, int[] iArr3) {
        if (g.a.d.d.g.C(iArr, iArr2, iArr3) != 0 || (iArr3[15] == -1 && g.a.d.d.m.q(16, iArr3, f5330b))) {
            int[] iArr4 = f5331c;
            if (g.a.d.d.m.e(iArr4.length, iArr4, iArr3) != 0) {
                g.a.d.d.m.t(16, iArr3, iArr4.length);
            }
        }
    }

    public static void f(int[] iArr, int[] iArr2) {
        if (g.a.d.d.g.v(iArr)) {
            g.a.d.d.g.L(iArr2);
        } else {
            g.a.d.d.g.H(f5329a, iArr, iArr2);
        }
    }

    public static void g(int[] iArr, int[] iArr2) {
        if (g.a.d.d.g.A(977, g.a.d.d.g.z(977, iArr, 8, iArr, 0, iArr2, 0), iArr2, 0) != 0 || (iArr2[7] == -1 && g.a.d.d.g.s(iArr2, f5329a))) {
            g.a.d.d.m.b(8, 977, iArr2);
        }
    }

    public static void h(int i2, int[] iArr) {
        if ((i2 == 0 || g.a.d.d.g.B(977, i2, iArr, 0) == 0) && !(iArr[7] == -1 && g.a.d.d.g.s(iArr, f5329a))) {
            return;
        }
        g.a.d.d.m.b(8, 977, iArr);
    }

    public static void i(int[] iArr, int[] iArr2) {
        int[] iArrJ = g.a.d.d.g.j();
        g.a.d.d.g.F(iArr, iArrJ);
        g(iArrJ, iArr2);
    }

    public static void j(int[] iArr, int i2, int[] iArr2) {
        int[] iArrJ = g.a.d.d.g.j();
        g.a.d.d.g.F(iArr, iArrJ);
        while (true) {
            g(iArrJ, iArr2);
            i2--;
            if (i2 <= 0) {
                return;
            } else {
                g.a.d.d.g.F(iArr2, iArrJ);
            }
        }
    }

    public static void k(int[] iArr, int[] iArr2, int[] iArr3) {
        if (g.a.d.d.g.H(iArr, iArr2, iArr3) != 0) {
            g.a.d.d.m.L(8, 977, iArr3);
        }
    }

    public static void l(int[] iArr, int[] iArr2) {
        if (g.a.d.d.m.E(8, iArr, 0, iArr2) != 0 || (iArr2[7] == -1 && g.a.d.d.g.s(iArr2, f5329a))) {
            g.a.d.d.m.b(8, 977, iArr2);
        }
    }
}
