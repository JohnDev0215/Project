package g.a.d.b.a0.a;

import g.a.d.d.g;
import g.a.d.d.m;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f5277a = {-19, -1, -1, -1, -1, -1, -1, Integer.MAX_VALUE};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f5278b = {361, 0, 0, 0, 0, 0, 0, 0, -19, -1, -1, -1, -1, -1, -1, 1073741823};

    public static void a(int[] iArr, int[] iArr2, int[] iArr3) {
        g.a(iArr, iArr2, iArr3);
        if (g.s(iArr3, f5277a)) {
            m(iArr3);
        }
    }

    public static void b(int[] iArr, int[] iArr2) {
        m.s(8, iArr, iArr2);
        if (g.s(iArr2, f5277a)) {
            m(iArr2);
        }
    }

    public static int c(int[] iArr) {
        long j = (((long) iArr[0]) & 4294967295L) - 19;
        iArr[0] = (int) j;
        long jM = j >> 32;
        if (jM != 0) {
            jM = m.m(7, iArr, 1);
        }
        long j2 = jM + (4294967295L & ((long) iArr[7])) + 2147483648L;
        iArr[7] = (int) j2;
        return (int) (j2 >> 32);
    }

    public static int[] d(BigInteger bigInteger) {
        int[] iArrO = g.o(bigInteger);
        while (true) {
            int[] iArr = f5277a;
            if (!g.s(iArrO, iArr)) {
                return iArrO;
            }
            g.I(iArr, iArrO);
        }
    }

    public static void e(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] iArrJ = g.j();
        g.y(iArr, iArr2, iArrJ);
        h(iArrJ, iArr3);
    }

    public static void f(int[] iArr, int[] iArr2, int[] iArr3) {
        g.C(iArr, iArr2, iArr3);
        if (m.q(16, iArr3, f5278b)) {
            l(iArr3);
        }
    }

    public static void g(int[] iArr, int[] iArr2) {
        if (g.v(iArr)) {
            g.L(iArr2);
        } else {
            g.H(f5277a, iArr, iArr2);
        }
    }

    public static void h(int[] iArr, int[] iArr2) {
        int i2 = iArr[7];
        m.D(8, iArr, 8, i2, iArr2, 0);
        int iD = g.D(19, iArr, iArr2) << 1;
        int i3 = iArr2[7];
        iArr2[7] = (i3 & Integer.MAX_VALUE) + m.g(7, (iD + ((i3 >>> 31) - (i2 >>> 31))) * 19, iArr2);
        if (g.s(iArr2, f5277a)) {
            m(iArr2);
        }
    }

    public static void i(int i2, int[] iArr) {
        int i3 = iArr[7];
        iArr[7] = (i3 & Integer.MAX_VALUE) + m.g(7, ((i2 << 1) | (i3 >>> 31)) * 19, iArr);
        if (g.s(iArr, f5277a)) {
            m(iArr);
        }
    }

    public static void j(int[] iArr, int[] iArr2) {
        int[] iArrJ = g.j();
        g.F(iArr, iArrJ);
        h(iArrJ, iArr2);
    }

    public static void k(int[] iArr, int i2, int[] iArr2) {
        int[] iArrJ = g.j();
        g.F(iArr, iArrJ);
        while (true) {
            h(iArrJ, iArr2);
            i2--;
            if (i2 <= 0) {
                return;
            } else {
                g.F(iArr2, iArrJ);
            }
        }
    }

    public static int l(int[] iArr) {
        long j = ((long) iArr[0]) & 4294967295L;
        int[] iArr2 = f5278b;
        long j2 = j - (((long) iArr2[0]) & 4294967295L);
        iArr[0] = (int) j2;
        long jM = j2 >> 32;
        if (jM != 0) {
            jM = m.m(8, iArr, 1);
        }
        long j3 = jM + (((long) iArr[8]) & 4294967295L) + 19;
        iArr[8] = (int) j3;
        long jT = j3 >> 32;
        if (jT != 0) {
            jT = m.t(15, iArr, 9);
        }
        long j4 = jT + ((((long) iArr[15]) & 4294967295L) - (4294967295L & ((long) (iArr2[15] + 1))));
        iArr[15] = (int) j4;
        return (int) (j4 >> 32);
    }

    public static int m(int[] iArr) {
        long j = (((long) iArr[0]) & 4294967295L) + 19;
        iArr[0] = (int) j;
        long jT = j >> 32;
        if (jT != 0) {
            jT = m.t(7, iArr, 1);
        }
        long j2 = jT + ((4294967295L & ((long) iArr[7])) - 2147483648L);
        iArr[7] = (int) j2;
        return (int) (j2 >> 32);
    }

    public static void n(int[] iArr, int[] iArr2, int[] iArr3) {
        if (g.H(iArr, iArr2, iArr3) != 0) {
            c(iArr3);
        }
    }

    public static void o(int[] iArr, int[] iArr2) {
        m.E(8, iArr, 0, iArr2);
        if (g.s(iArr2, f5277a)) {
            m(iArr2);
        }
    }
}
