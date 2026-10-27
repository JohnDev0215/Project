package g.a.d.b.a0.c;

import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f5343a = {Integer.MAX_VALUE, -1, -1, -1, -1};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f5344b = {1, TestOtaManagerImplCallback.ONLOCALDIAGSTATUSCHANGED, 0, 0, 0, -2, -2, -1, -1, -1};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f5345c = {-1, -1073741826, -1, -1, -1, 1, 1};

    public static void a(int[] iArr, int[] iArr2, int[] iArr3) {
        if (g.a.d.d.d.a(iArr, iArr2, iArr3) != 0 || (iArr3[4] == -1 && g.a.d.d.d.i(iArr3, f5343a))) {
            g.a.d.d.m.g(5, -2147483647, iArr3);
        }
    }

    public static void b(int[] iArr, int[] iArr2) {
        if (g.a.d.d.m.s(5, iArr, iArr2) != 0 || (iArr2[4] == -1 && g.a.d.d.d.i(iArr2, f5343a))) {
            g.a.d.d.m.g(5, -2147483647, iArr2);
        }
    }

    public static int[] c(BigInteger bigInteger) {
        int[] iArrG = g.a.d.d.d.g(bigInteger);
        if (iArrG[4] == -1) {
            int[] iArr = f5343a;
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
        if (g.a.d.d.d.p(iArr, iArr2, iArr3) != 0 || (iArr3[9] == -1 && g.a.d.d.m.q(10, iArr3, f5344b))) {
            int[] iArr4 = f5345c;
            if (g.a.d.d.m.e(iArr4.length, iArr4, iArr3) != 0) {
                g.a.d.d.m.t(10, iArr3, iArr4.length);
            }
        }
    }

    public static void f(int[] iArr, int[] iArr2) {
        if (g.a.d.d.d.k(iArr)) {
            g.a.d.d.d.v(iArr2);
        } else {
            g.a.d.d.d.s(f5343a, iArr, iArr2);
        }
    }

    public static void g(int[] iArr, int[] iArr2) {
        long j = ((long) iArr[5]) & 4294967295L;
        long j2 = ((long) iArr[6]) & 4294967295L;
        long j3 = ((long) iArr[7]) & 4294967295L;
        long j4 = ((long) iArr[8]) & 4294967295L;
        long j5 = ((long) iArr[9]) & 4294967295L;
        long j6 = (((long) iArr[0]) & 4294967295L) + j + (j << 31) + 0;
        iArr2[0] = (int) j6;
        long j7 = (j6 >>> 32) + (((long) iArr[1]) & 4294967295L) + j2 + (j2 << 31);
        iArr2[1] = (int) j7;
        long j8 = (j7 >>> 32) + (((long) iArr[2]) & 4294967295L) + j3 + (j3 << 31);
        iArr2[2] = (int) j8;
        long j9 = (j8 >>> 32) + (((long) iArr[3]) & 4294967295L) + j4 + (j4 << 31);
        iArr2[3] = (int) j9;
        long j10 = (j9 >>> 32) + (4294967295L & ((long) iArr[4])) + j5 + (j5 << 31);
        iArr2[4] = (int) j10;
        h((int) (j10 >>> 32), iArr2);
    }

    public static void h(int i2, int[] iArr) {
        if ((i2 == 0 || g.a.d.d.d.q(-2147483647, i2, iArr, 0) == 0) && !(iArr[4] == -1 && g.a.d.d.d.i(iArr, f5343a))) {
            return;
        }
        g.a.d.d.m.g(5, -2147483647, iArr);
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
            g.a.d.d.m.O(5, -2147483647, iArr3);
        }
    }

    public static void l(int[] iArr, int[] iArr2) {
        if (g.a.d.d.m.E(5, iArr, 0, iArr2) != 0 || (iArr2[4] == -1 && g.a.d.d.d.i(iArr2, f5343a))) {
            g.a.d.d.m.g(5, -2147483647, iArr2);
        }
    }
}
