package g.a.d.b.a0.c;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class q2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final long[] f5411a = {3161836309350906777L, -7642453882179322845L, -3821226941089661423L, 7312758566309945096L, -556661012383879292L, 8945041530681231562L, -4750851271514160027L, 6847946401097695794L, 541669439031730457L};

    public static void a(long[] jArr, int i2, long[] jArr2, int i3, long[] jArr3, int i4) {
        for (int i5 = 0; i5 < 9; i5++) {
            jArr3[i4 + i5] = jArr[i2 + i5] ^ jArr2[i3 + i5];
        }
    }

    public static void b(long[] jArr, long[] jArr2, long[] jArr3) {
        for (int i2 = 0; i2 < 9; i2++) {
            jArr3[i2] = jArr[i2] ^ jArr2[i2];
        }
    }

    public static void c(long[] jArr, int i2, long[] jArr2, int i3, long[] jArr3, int i4) {
        for (int i5 = 0; i5 < 9; i5++) {
            int i6 = i4 + i5;
            jArr3[i6] = jArr3[i6] ^ (jArr[i2 + i5] ^ jArr2[i3 + i5]);
        }
    }

    public static void d(long[] jArr, long[] jArr2, long[] jArr3) {
        for (int i2 = 0; i2 < 9; i2++) {
            jArr3[i2] = jArr3[i2] ^ (jArr[i2] ^ jArr2[i2]);
        }
    }

    public static void e(long[] jArr, long[] jArr2, long[] jArr3) {
        for (int i2 = 0; i2 < 18; i2++) {
            jArr3[i2] = jArr[i2] ^ jArr2[i2];
        }
    }

    public static void f(long[] jArr, long[] jArr2) {
        jArr2[0] = jArr[0] ^ 1;
        for (int i2 = 1; i2 < 9; i2++) {
            jArr2[i2] = jArr[i2];
        }
    }

    public static long[] g(BigInteger bigInteger) {
        long[] jArrE = g.a.d.d.l.e(bigInteger);
        r(jArrE, 0);
        return jArrE;
    }

    public static void h(long[] jArr, long[] jArr2, long[] jArr3) {
        i(jArr, p(jArr2), jArr3);
    }

    public static void i(long[] jArr, long[] jArr2, long[] jArr3) {
        for (int i2 = 56; i2 >= 0; i2 -= 8) {
            for (int i3 = 1; i3 < 9; i3 += 2) {
                int i4 = (int) (jArr[i3] >>> i2);
                c(jArr2, (i4 & 15) * 9, jArr2, (((i4 >>> 4) & 15) + 16) * 9, jArr3, i3 - 1);
            }
            g.a.d.d.m.I(16, jArr3, 0, 8, 0L);
        }
        for (int i5 = 56; i5 >= 0; i5 -= 8) {
            for (int i6 = 0; i6 < 9; i6 += 2) {
                int i7 = (int) (jArr[i6] >>> i5);
                c(jArr2, (i7 & 15) * 9, jArr2, (((i7 >>> 4) & 15) + 16) * 9, jArr3, i6);
            }
            if (i5 > 0) {
                g.a.d.d.m.I(18, jArr3, 0, 8, 0L);
            }
        }
    }

    public static void j(long[] jArr, long[] jArr2) {
        for (int i2 = 0; i2 < 9; i2++) {
            g.a.d.d.a.c(jArr[i2], jArr2, i2 << 1);
        }
    }

    public static void k(long[] jArr, long[] jArr2) {
        if (g.a.d.d.l.g(jArr)) {
            throw new IllegalStateException();
        }
        long[] jArrB = g.a.d.d.l.b();
        long[] jArrB2 = g.a.d.d.l.b();
        long[] jArrB3 = g.a.d.d.l.b();
        t(jArr, jArrB3);
        t(jArrB3, jArrB);
        t(jArrB, jArrB2);
        l(jArrB, jArrB2, jArrB);
        v(jArrB, 2, jArrB2);
        l(jArrB, jArrB2, jArrB);
        l(jArrB, jArrB3, jArrB);
        v(jArrB, 5, jArrB2);
        l(jArrB, jArrB2, jArrB);
        v(jArrB2, 5, jArrB2);
        l(jArrB, jArrB2, jArrB);
        v(jArrB, 15, jArrB2);
        l(jArrB, jArrB2, jArrB3);
        v(jArrB3, 30, jArrB);
        v(jArrB, 30, jArrB2);
        l(jArrB, jArrB2, jArrB);
        v(jArrB, 60, jArrB2);
        l(jArrB, jArrB2, jArrB);
        v(jArrB2, 60, jArrB2);
        l(jArrB, jArrB2, jArrB);
        v(jArrB, 180, jArrB2);
        l(jArrB, jArrB2, jArrB);
        v(jArrB2, 180, jArrB2);
        l(jArrB, jArrB2, jArrB);
        l(jArrB, jArrB3, jArr2);
    }

    public static void l(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArrC = g.a.d.d.l.c();
        h(jArr, jArr2, jArrC);
        q(jArrC, jArr3);
    }

    public static void m(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArrC = g.a.d.d.l.c();
        h(jArr, jArr2, jArrC);
        e(jArr3, jArrC, jArr3);
    }

    public static void n(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArrC = g.a.d.d.l.c();
        i(jArr, jArr2, jArrC);
        q(jArrC, jArr3);
    }

    public static void o(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArrC = g.a.d.d.l.c();
        i(jArr, jArr2, jArrC);
        e(jArr3, jArrC, jArr3);
    }

    public static long[] p(long[] jArr) {
        long[] jArr2 = new long[288];
        int i2 = 0;
        System.arraycopy(jArr, 0, jArr2, 9, 9);
        int i3 = 7;
        while (i3 > 0) {
            int i4 = i2 + 18;
            g.a.d.d.m.F(9, jArr2, i4 >>> 1, 0L, jArr2, i4);
            r(jArr2, i4);
            a(jArr2, 9, jArr2, i4, jArr2, i4 + 9);
            i3--;
            i2 = i4;
        }
        g.a.d.d.m.J(144, jArr2, 0, 4, 0L, jArr2, 144);
        return jArr2;
    }

    public static void q(long[] jArr, long[] jArr2) {
        long j = jArr[9];
        long j2 = jArr[17];
        long j3 = (((j ^ (j2 >>> 59)) ^ (j2 >>> 57)) ^ (j2 >>> 54)) ^ (j2 >>> 49);
        long j4 = (j2 << 15) ^ (((jArr[8] ^ (j2 << 5)) ^ (j2 << 7)) ^ (j2 << 10));
        for (int i2 = 16; i2 >= 10; i2--) {
            long j5 = jArr[i2];
            jArr2[i2 - 8] = (((j4 ^ (j5 >>> 59)) ^ (j5 >>> 57)) ^ (j5 >>> 54)) ^ (j5 >>> 49);
            j4 = (((jArr[i2 - 9] ^ (j5 << 5)) ^ (j5 << 7)) ^ (j5 << 10)) ^ (j5 << 15);
        }
        jArr2[1] = (((j4 ^ (j3 >>> 59)) ^ (j3 >>> 57)) ^ (j3 >>> 54)) ^ (j3 >>> 49);
        long j6 = (j3 << 15) ^ (((jArr[0] ^ (j3 << 5)) ^ (j3 << 7)) ^ (j3 << 10));
        long j7 = jArr2[8];
        long j8 = j7 >>> 59;
        jArr2[0] = (((j6 ^ j8) ^ (j8 << 2)) ^ (j8 << 5)) ^ (j8 << 10);
        jArr2[8] = 576460752303423487L & j7;
    }

    public static void r(long[] jArr, int i2) {
        int i3 = i2 + 8;
        long j = jArr[i3];
        long j2 = j >>> 59;
        jArr[i2] = ((j2 << 10) ^ (((j2 << 2) ^ j2) ^ (j2 << 5))) ^ jArr[i2];
        jArr[i3] = j & 576460752303423487L;
    }

    public static void s(long[] jArr, long[] jArr2) {
        long[] jArrB = g.a.d.d.l.b();
        long[] jArrB2 = g.a.d.d.l.b();
        int i2 = 0;
        for (int i3 = 0; i3 < 4; i3++) {
            int i4 = i2 + 1;
            long jF = g.a.d.d.a.f(jArr[i2]);
            i2 = i4 + 1;
            long jF2 = g.a.d.d.a.f(jArr[i4]);
            jArrB[i3] = (4294967295L & jF) | (jF2 << 32);
            jArrB2[i3] = (jF >>> 32) | ((-4294967296L) & jF2);
        }
        long jF3 = g.a.d.d.a.f(jArr[i2]);
        jArrB[4] = 4294967295L & jF3;
        jArrB2[4] = jF3 >>> 32;
        l(jArrB2, f5411a, jArr2);
        b(jArr2, jArrB, jArr2);
    }

    public static void t(long[] jArr, long[] jArr2) {
        long[] jArrC = g.a.d.d.l.c();
        j(jArr, jArrC);
        q(jArrC, jArr2);
    }

    public static void u(long[] jArr, long[] jArr2) {
        long[] jArrC = g.a.d.d.l.c();
        j(jArr, jArrC);
        e(jArr2, jArrC, jArr2);
    }

    public static void v(long[] jArr, int i2, long[] jArr2) {
        long[] jArrC = g.a.d.d.l.c();
        j(jArr, jArrC);
        while (true) {
            q(jArrC, jArr2);
            i2--;
            if (i2 <= 0) {
                return;
            } else {
                j(jArr2, jArrC);
            }
        }
    }

    public static int w(long[] jArr) {
        return ((int) ((jArr[0] ^ (jArr[8] >>> 49)) ^ (jArr[8] >>> 57))) & 1;
    }
}
