package g.a.d.b.a0.c;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f5370a = {-21389, -2, -1, -1, -1};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f5371b = {457489321, 42778, 1, 0, 0, -42778, -3, -1, -1, -1};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f5372c = {-457489321, -42779, -2, -1, -1, 42777, 2};

    public static void a(int[] iArr, int[] iArr2, int[] iArr3) {
        if (g.a.d.d.d.a(iArr, iArr2, iArr3) != 0 || (iArr3[4] == -1 && g.a.d.d.d.i(iArr3, f5370a))) {
            g.a.d.d.m.b(5, 21389, iArr3);
        }
    }

    public static void b(int[] iArr, int[] iArr2) {
        if (g.a.d.d.m.s(5, iArr, iArr2) != 0 || (iArr2[4] == -1 && g.a.d.d.d.i(iArr2, f5370a))) {
            g.a.d.d.m.b(5, 21389, iArr2);
        }
    }

    public static int[] c(BigInteger bigInteger) {
        int[] iArrG = g.a.d.d.d.g(bigInteger);
        if (iArrG[4] == -1) {
            int[] iArr = f5370a;
            if (g.a.d.d.d.i(iArrG, iArr)) {
                g.a.d.d.d.t(iArr, iArrG);
            }
        }
        return iArrG;
    }

    public static void d(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] iArrE = g.a.d.d.d.e();
        g.a.d.d.d.l(iArr, iArr2, iArrE);
        g(iArrE, iArr3);
    }

    public static void e(int[] iArr, int[] iArr2, int[] iArr3) {
        if (g.a.d.d.d.p(iArr, iArr2, iArr3) != 0 || (iArr3[9] == -1 && g.a.d.d.m.q(10, iArr3, f5371b))) {
            int[] iArr4 = f5372c;
            if (g.a.d.d.m.e(iArr4.length, iArr4, iArr3) != 0) {
                g.a.d.d.m.t(10, iArr3, iArr4.length);
            }
        }
    }

    public static void f(int[] iArr, int[] iArr2) {
        if (g.a.d.d.d.k(iArr)) {
            g.a.d.d.d.v(iArr2);
        } else {
            g.a.d.d.d.s(f5370a, iArr, iArr2);
        }
    }

    public static void g(int[] iArr, int[] iArr2) {
        if (g.a.d.d.d.n(21389, g.a.d.d.d.m(21389, iArr, 5, iArr, 0, iArr2, 0), iArr2, 0) != 0 || (iArr2[4] == -1 && g.a.d.d.d.i(iArr2, f5370a))) {
            g.a.d.d.m.b(5, 21389, iArr2);
        }
    }

    public static void h(int i2, int[] iArr) {
        if ((i2 == 0 || g.a.d.d.d.o(21389, i2, iArr, 0) == 0) && !(iArr[4] == -1 && g.a.d.d.d.i(iArr, f5370a))) {
            return;
        }
        g.a.d.d.m.b(5, 21389, iArr);
    }

    public static void i(int[] iArr, int[] iArr2) {
        int[] iArrE = g.a.d.d.d.e();
        g.a.d.d.d.r(iArr, iArrE);
        g(iArrE, iArr2);
    }

    public static void j(int[] iArr, int i2, int[] iArr2) {
        int[] iArrE = g.a.d.d.d.e();
        g.a.d.d.d.r(iArr, iArrE);
        while (true) {
            g(iArrE, iArr2);
            i2--;
            if (i2 <= 0) {
                return;
            } else {
                g.a.d.d.d.r(iArr2, iArrE);
            }
        }
    }

    public static void k(int[] iArr, int[] iArr2, int[] iArr3) {
        if (g.a.d.d.d.s(iArr, iArr2, iArr3) != 0) {
            g.a.d.d.m.L(5, 21389, iArr3);
        }
    }

    public static void l(int[] iArr, int[] iArr2) {
        if (g.a.d.d.m.E(5, iArr, 0, iArr2) != 0 || (iArr2[4] == -1 && g.a.d.d.d.i(iArr2, f5370a))) {
            g.a.d.d.m.b(5, 21389, iArr2);
        }
    }
}
