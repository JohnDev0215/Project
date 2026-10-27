package g.a.d.b.a0.c;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f5398a = {-4553, -2, -1, -1, -1, -1};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f5399b = {20729809, 9106, 1, 0, 0, 0, -9106, -3, -1, -1, -1, -1};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f5400c = {-20729809, -9107, -2, -1, -1, -1, 9105, 2};

    public static void a(int[] iArr, int[] iArr2, int[] iArr3) {
        if (g.a.d.d.e.a(iArr, iArr2, iArr3) != 0 || (iArr3[5] == -1 && g.a.d.d.e.r(iArr3, f5398a))) {
            g.a.d.d.m.b(6, 4553, iArr3);
        }
    }

    public static void b(int[] iArr, int[] iArr2) {
        if (g.a.d.d.m.s(6, iArr, iArr2) != 0 || (iArr2[5] == -1 && g.a.d.d.e.r(iArr2, f5398a))) {
            g.a.d.d.m.b(6, 4553, iArr2);
        }
    }

    public static int[] c(BigInteger bigInteger) {
        int[] iArrN = g.a.d.d.e.n(bigInteger);
        if (iArrN[5] == -1) {
            int[] iArr = f5398a;
            if (g.a.d.d.e.r(iArrN, iArr)) {
                g.a.d.d.e.G(iArr, iArrN);
            }
        }
        return iArrN;
    }

    public static void d(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] iArrI = g.a.d.d.e.i();
        g.a.d.d.e.x(iArr, iArr2, iArrI);
        g(iArrI, iArr3);
    }

    public static void e(int[] iArr, int[] iArr2, int[] iArr3) {
        if (g.a.d.d.e.B(iArr, iArr2, iArr3) != 0 || (iArr3[11] == -1 && g.a.d.d.m.q(12, iArr3, f5399b))) {
            int[] iArr4 = f5400c;
            if (g.a.d.d.m.e(iArr4.length, iArr4, iArr3) != 0) {
                g.a.d.d.m.t(12, iArr3, iArr4.length);
            }
        }
    }

    public static void f(int[] iArr, int[] iArr2) {
        if (g.a.d.d.e.u(iArr)) {
            g.a.d.d.e.J(iArr2);
        } else {
            g.a.d.d.e.F(f5398a, iArr, iArr2);
        }
    }

    public static void g(int[] iArr, int[] iArr2) {
        if (g.a.d.d.e.z(4553, g.a.d.d.e.y(4553, iArr, 6, iArr, 0, iArr2, 0), iArr2, 0) != 0 || (iArr2[5] == -1 && g.a.d.d.e.r(iArr2, f5398a))) {
            g.a.d.d.m.b(6, 4553, iArr2);
        }
    }

    public static void h(int i2, int[] iArr) {
        if ((i2 == 0 || g.a.d.d.e.A(4553, i2, iArr, 0) == 0) && !(iArr[5] == -1 && g.a.d.d.e.r(iArr, f5398a))) {
            return;
        }
        g.a.d.d.m.b(6, 4553, iArr);
    }

    public static void i(int[] iArr, int[] iArr2) {
        int[] iArrI = g.a.d.d.e.i();
        g.a.d.d.e.D(iArr, iArrI);
        g(iArrI, iArr2);
    }

    public static void j(int[] iArr, int i2, int[] iArr2) {
        int[] iArrI = g.a.d.d.e.i();
        g.a.d.d.e.D(iArr, iArrI);
        while (true) {
            g(iArrI, iArr2);
            i2--;
            if (i2 <= 0) {
                return;
            } else {
                g.a.d.d.e.D(iArr2, iArrI);
            }
        }
    }

    public static void k(int[] iArr, int[] iArr2, int[] iArr3) {
        if (g.a.d.d.e.F(iArr, iArr2, iArr3) != 0) {
            g.a.d.d.m.L(6, 4553, iArr3);
        }
    }

    public static void l(int[] iArr, int[] iArr2) {
        if (g.a.d.d.m.E(6, iArr, 0, iArr2) != 0 || (iArr2[5] == -1 && g.a.d.d.e.r(iArr2, f5398a))) {
            g.a.d.d.m.b(6, 4553, iArr2);
        }
    }
}
