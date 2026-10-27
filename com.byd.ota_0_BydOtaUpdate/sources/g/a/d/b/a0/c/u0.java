package g.a.d.b.a0.c;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class u0 {
    public static void a(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr3[0] = jArr[0] ^ jArr2[0];
        jArr3[1] = jArr2[1] ^ jArr[1];
    }

    public static void b(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr3[0] = jArr[0] ^ jArr2[0];
        jArr3[1] = jArr[1] ^ jArr2[1];
        jArr3[2] = jArr[2] ^ jArr2[2];
        jArr3[3] = jArr2[3] ^ jArr[3];
    }

    public static void c(long[] jArr, long[] jArr2) {
        jArr2[0] = jArr[0] ^ 1;
        jArr2[1] = jArr[1];
    }

    public static long[] d(BigInteger bigInteger) {
        long[] jArrL = g.a.d.d.c.l(bigInteger);
        l(jArrL, 0);
        return jArrL;
    }

    public static void e(long[] jArr, long[] jArr2, long[] jArr3) {
        long j = jArr[0];
        long j2 = ((jArr[1] << 7) ^ (j >>> 57)) & 144115188075855871L;
        long j3 = j & 144115188075855871L;
        long j4 = jArr2[0];
        long j5 = ((jArr2[1] << 7) ^ (j4 >>> 57)) & 144115188075855871L;
        long j6 = 144115188075855871L & j4;
        long[] jArr4 = new long[6];
        f(j3, j6, jArr4, 0);
        f(j2, j5, jArr4, 2);
        f(j3 ^ j2, j6 ^ j5, jArr4, 4);
        long j7 = jArr4[1] ^ jArr4[2];
        long j8 = jArr4[0];
        long j9 = jArr4[3];
        long j10 = (jArr4[4] ^ j8) ^ j7;
        long j11 = j7 ^ (jArr4[5] ^ j9);
        jArr3[0] = j8 ^ (j10 << 57);
        jArr3[1] = (j10 >>> 7) ^ (j11 << 50);
        jArr3[2] = (j11 >>> 14) ^ (j9 << 43);
        jArr3[3] = j9 >>> 21;
    }

    public static void f(long j, long j2, long[] jArr, int i2) {
        long[] jArr2 = new long[8];
        jArr2[1] = j2;
        jArr2[2] = jArr2[1] << 1;
        jArr2[3] = jArr2[2] ^ j2;
        jArr2[4] = jArr2[2] << 1;
        jArr2[5] = jArr2[4] ^ j2;
        jArr2[6] = jArr2[3] << 1;
        jArr2[7] = jArr2[6] ^ j2;
        long j3 = jArr2[((int) j) & 7];
        long j4 = 0;
        int i3 = 48;
        do {
            int i4 = (int) (j >>> i3);
            long j5 = (jArr2[i4 & 7] ^ (jArr2[(i4 >>> 3) & 7] << 3)) ^ (jArr2[(i4 >>> 6) & 7] << 6);
            j3 ^= j5 << i3;
            j4 ^= j5 >>> (-i3);
            i3 -= 9;
        } while (i3 > 0);
        jArr[i2] = 144115188075855871L & j3;
        jArr[i2 + 1] = (((((j & 72198606942111744L) & ((j2 << 7) >> 63)) >>> 8) ^ j4) << 7) ^ (j3 >>> 57);
    }

    public static void g(long[] jArr, long[] jArr2) {
        g.a.d.d.a.c(jArr[0], jArr2, 0);
        g.a.d.d.a.c(jArr[1], jArr2, 2);
    }

    public static void h(long[] jArr, long[] jArr2) {
        if (g.a.d.d.c.r(jArr)) {
            throw new IllegalStateException();
        }
        long[] jArrF = g.a.d.d.c.f();
        long[] jArrF2 = g.a.d.d.c.f();
        n(jArr, jArrF);
        i(jArrF, jArr, jArrF);
        n(jArrF, jArrF);
        i(jArrF, jArr, jArrF);
        p(jArrF, 3, jArrF2);
        i(jArrF2, jArrF, jArrF2);
        n(jArrF2, jArrF2);
        i(jArrF2, jArr, jArrF2);
        p(jArrF2, 7, jArrF);
        i(jArrF, jArrF2, jArrF);
        p(jArrF, 14, jArrF2);
        i(jArrF2, jArrF, jArrF2);
        p(jArrF2, 28, jArrF);
        i(jArrF, jArrF2, jArrF);
        p(jArrF, 56, jArrF2);
        i(jArrF2, jArrF, jArrF2);
        n(jArrF2, jArr2);
    }

    public static void i(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArrH = g.a.d.d.c.h();
        e(jArr, jArr2, jArrH);
        k(jArrH, jArr3);
    }

    public static void j(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArrH = g.a.d.d.c.h();
        e(jArr, jArr2, jArrH);
        b(jArr3, jArrH, jArr3);
    }

    public static void k(long[] jArr, long[] jArr2) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = jArr[2];
        long j4 = jArr[3];
        long j5 = j3 ^ ((j4 >>> 40) ^ (j4 >>> 49));
        long j6 = j ^ ((j5 << 15) ^ (j5 << 24));
        long j7 = (j2 ^ ((j4 << 15) ^ (j4 << 24))) ^ ((j5 >>> 40) ^ (j5 >>> 49));
        long j8 = j7 >>> 49;
        jArr2[0] = (j6 ^ j8) ^ (j8 << 9);
        jArr2[1] = 562949953421311L & j7;
    }

    public static void l(long[] jArr, int i2) {
        int i3 = i2 + 1;
        long j = jArr[i3];
        long j2 = j >>> 49;
        jArr[i2] = (j2 ^ (j2 << 9)) ^ jArr[i2];
        jArr[i3] = j & 562949953421311L;
    }

    public static void m(long[] jArr, long[] jArr2) {
        long jF = g.a.d.d.a.f(jArr[0]);
        long jF2 = g.a.d.d.a.f(jArr[1]);
        long j = (4294967295L & jF) | (jF2 << 32);
        long j2 = (jF >>> 32) | (jF2 & (-4294967296L));
        jArr2[0] = ((j2 << 57) ^ j) ^ (j2 << 5);
        jArr2[1] = (j2 >>> 59) ^ (j2 >>> 7);
    }

    public static void n(long[] jArr, long[] jArr2) {
        long[] jArrH = g.a.d.d.c.h();
        g(jArr, jArrH);
        k(jArrH, jArr2);
    }

    public static void o(long[] jArr, long[] jArr2) {
        long[] jArrH = g.a.d.d.c.h();
        g(jArr, jArrH);
        b(jArr2, jArrH, jArr2);
    }

    public static void p(long[] jArr, int i2, long[] jArr2) {
        long[] jArrH = g.a.d.d.c.h();
        g(jArr, jArrH);
        while (true) {
            k(jArrH, jArr2);
            i2--;
            if (i2 <= 0) {
                return;
            } else {
                g(jArr2, jArrH);
            }
        }
    }

    public static int q(long[] jArr) {
        return ((int) jArr[0]) & 1;
    }
}
