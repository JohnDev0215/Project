package g.a.d.b.a0.c;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f5446a = {-6803, -2, -1, -1, -1, -1, -1};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f5447b = {46280809, 13606, 1, 0, 0, 0, 0, -13606, -3, -1, -1, -1, -1, -1};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f5448c = {-46280809, -13607, -2, -1, -1, -1, -1, 13605, 2};

    public static void a(int[] iArr, int[] iArr2, int[] iArr3) {
        if (g.a.d.d.f.a(iArr, iArr2, iArr3) != 0 || (iArr3[6] == -1 && g.a.d.d.f.j(iArr3, f5446a))) {
            g.a.d.d.m.b(7, 6803, iArr3);
        }
    }

    public static void b(int[] iArr, int[] iArr2) {
        if (g.a.d.d.m.s(7, iArr, iArr2) != 0 || (iArr2[6] == -1 && g.a.d.d.f.j(iArr2, f5446a))) {
            g.a.d.d.m.b(7, 6803, iArr2);
        }
    }

    public static int[] c(BigInteger bigInteger) {
        int[] iArrH = g.a.d.d.f.h(bigInteger);
        if (iArrH[6] == -1 && g.a.d.d.f.j(iArrH, f5446a)) {
            g.a.d.d.m.b(7, 6803, iArrH);
        }
        return iArrH;
    }

    public static void d(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] iArrF = g.a.d.d.f.f();
        g.a.d.d.f.m(iArr, iArr2, iArrF);
        g(iArrF, iArr3);
    }

    public static void e(int[] iArr, int[] iArr2, int[] iArr3) {
        if (g.a.d.d.f.q(iArr, iArr2, iArr3) != 0 || (iArr3[13] == -1 && g.a.d.d.m.q(14, iArr3, f5447b))) {
            int[] iArr4 = f5448c;
            if (g.a.d.d.m.e(iArr4.length, iArr4, iArr3) != 0) {
                g.a.d.d.m.t(14, iArr3, iArr4.length);
            }
        }
    }

    public static void f(int[] iArr, int[] iArr2) {
        if (g.a.d.d.f.l(iArr)) {
            g.a.d.d.f.v(iArr2);
        } else {
            g.a.d.d.f.s(f5446a, iArr, iArr2);
        }
    }

    public static void g(int[] iArr, int[] iArr2) {
        if (g.a.d.d.f.o(6803, g.a.d.d.f.n(6803, iArr, 7, iArr, 0, iArr2, 0), iArr2, 0) != 0 || (iArr2[6] == -1 && g.a.d.d.f.j(iArr2, f5446a))) {
            g.a.d.d.m.b(7, 6803, iArr2);
        }
    }

    public static void h(int i2, int[] iArr) {
        if ((i2 == 0 || g.a.d.d.f.p(6803, i2, iArr, 0) == 0) && !(iArr[6] == -1 && g.a.d.d.f.j(iArr, f5446a))) {
            return;
        }
        g.a.d.d.m.b(7, 6803, iArr);
    }

    public static void i(int[] iArr, int[] iArr2) {
        int[] iArrF = g.a.d.d.f.f();
        g.a.d.d.f.r(iArr, iArrF);
        g(iArrF, iArr2);
    }

    public static void j(int[] iArr, int i2, int[] iArr2) {
        int[] iArrF = g.a.d.d.f.f();
        g.a.d.d.f.r(iArr, iArrF);
        while (true) {
            g(iArrF, iArr2);
            i2--;
            if (i2 <= 0) {
                return;
            } else {
                g.a.d.d.f.r(iArr2, iArrF);
            }
        }
    }

    public static void k(int[] iArr, int[] iArr2, int[] iArr3) {
        if (g.a.d.d.f.s(iArr, iArr2, iArr3) != 0) {
            g.a.d.d.m.L(7, 6803, iArr3);
        }
    }

    public static void l(int[] iArr, int[] iArr2) {
        if (g.a.d.d.m.E(7, iArr, 0, iArr2) != 0 || (iArr2[6] == -1 && g.a.d.d.f.j(iArr2, f5446a))) {
            g.a.d.d.m.b(7, 6803, iArr2);
        }
    }
}
