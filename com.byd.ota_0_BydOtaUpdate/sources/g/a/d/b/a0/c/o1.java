package g.a.d.b.a0.c;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class o1 {
    public static void a(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr3[0] = jArr[0] ^ jArr2[0];
        jArr3[1] = jArr[1] ^ jArr2[1];
        jArr3[2] = jArr[2] ^ jArr2[2];
        jArr3[3] = jArr2[3] ^ jArr[3];
    }

    public static void b(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr3[0] = jArr[0] ^ jArr2[0];
        jArr3[1] = jArr[1] ^ jArr2[1];
        jArr3[2] = jArr[2] ^ jArr2[2];
        jArr3[3] = jArr[3] ^ jArr2[3];
        jArr3[4] = jArr[4] ^ jArr2[4];
        jArr3[5] = jArr[5] ^ jArr2[5];
        jArr3[6] = jArr2[6] ^ jArr[6];
    }

    public static void c(long[] jArr, long[] jArr2) {
        jArr2[0] = jArr[0] ^ 1;
        jArr2[1] = jArr[1];
        jArr2[2] = jArr[2];
        jArr2[3] = jArr[3];
    }

    public static long[] d(BigInteger bigInteger) {
        long[] jArrP = g.a.d.d.g.p(bigInteger);
        n(jArrP, 0);
        return jArrP;
    }

    public static void e(long[] jArr) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = jArr[2];
        long j4 = jArr[3];
        long j5 = jArr[4];
        long j6 = jArr[5];
        long j7 = jArr[6];
        long j8 = jArr[7];
        jArr[0] = j ^ (j2 << 49);
        jArr[1] = (j2 >>> 15) ^ (j3 << 34);
        jArr[2] = (j3 >>> 30) ^ (j4 << 19);
        jArr[3] = ((j4 >>> 45) ^ (j5 << 4)) ^ (j6 << 53);
        jArr[4] = ((j5 >>> 60) ^ (j7 << 38)) ^ (j6 >>> 11);
        jArr[5] = (j7 >>> 26) ^ (j8 << 23);
        jArr[6] = j8 >>> 41;
        jArr[7] = 0;
    }

    public static void f(long[] jArr, long[] jArr2) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = jArr[2];
        long j4 = jArr[3];
        jArr2[0] = j & 562949953421311L;
        jArr2[1] = ((j >>> 49) ^ (j2 << 15)) & 562949953421311L;
        jArr2[2] = ((j2 >>> 34) ^ (j3 << 30)) & 562949953421311L;
        jArr2[3] = (j3 >>> 19) ^ (j4 << 45);
    }

    public static void g(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArr4 = new long[4];
        long[] jArr5 = new long[4];
        f(jArr, jArr4);
        f(jArr2, jArr5);
        h(jArr4[0], jArr5[0], jArr3, 0);
        h(jArr4[1], jArr5[1], jArr3, 1);
        h(jArr4[2], jArr5[2], jArr3, 2);
        h(jArr4[3], jArr5[3], jArr3, 3);
        for (int i2 = 5; i2 > 0; i2--) {
            jArr3[i2] = jArr3[i2] ^ jArr3[i2 - 1];
        }
        h(jArr4[0] ^ jArr4[1], jArr5[0] ^ jArr5[1], jArr3, 1);
        h(jArr4[2] ^ jArr4[3], jArr5[2] ^ jArr5[3], jArr3, 3);
        for (int i3 = 7; i3 > 1; i3--) {
            jArr3[i3] = jArr3[i3] ^ jArr3[i3 - 2];
        }
        long j = jArr4[0] ^ jArr4[2];
        long j2 = jArr4[1] ^ jArr4[3];
        long j3 = jArr5[0] ^ jArr5[2];
        long j4 = jArr5[1] ^ jArr5[3];
        h(j ^ j2, j3 ^ j4, jArr3, 3);
        long[] jArr6 = new long[3];
        h(j, j3, jArr6, 0);
        h(j2, j4, jArr6, 1);
        long j5 = jArr6[0];
        long j6 = jArr6[1];
        long j7 = jArr6[2];
        jArr3[2] = jArr3[2] ^ j5;
        jArr3[3] = (j5 ^ j6) ^ jArr3[3];
        jArr3[4] = jArr3[4] ^ (j7 ^ j6);
        jArr3[5] = jArr3[5] ^ j7;
        e(jArr3);
    }

    public static void h(long j, long j2, long[] jArr, int i2) {
        long[] jArr2 = new long[8];
        jArr2[1] = j2;
        jArr2[2] = jArr2[1] << 1;
        jArr2[3] = jArr2[2] ^ j2;
        jArr2[4] = jArr2[2] << 1;
        jArr2[5] = jArr2[4] ^ j2;
        jArr2[6] = jArr2[3] << 1;
        jArr2[7] = jArr2[6] ^ j2;
        int i3 = (int) j;
        long j3 = (jArr2[(i3 >>> 3) & 7] << 3) ^ jArr2[i3 & 7];
        long j4 = 0;
        int i4 = 36;
        do {
            int i5 = (int) (j >>> i4);
            long j5 = (((jArr2[i5 & 7] ^ (jArr2[(i5 >>> 3) & 7] << 3)) ^ (jArr2[(i5 >>> 6) & 7] << 6)) ^ (jArr2[(i5 >>> 9) & 7] << 9)) ^ (jArr2[(i5 >>> 12) & 7] << 12);
            j3 ^= j5 << i4;
            j4 ^= j5 >>> (-i4);
            i4 -= 15;
        } while (i4 > 0);
        jArr[i2] = jArr[i2] ^ (562949953421311L & j3);
        int i6 = i2 + 1;
        jArr[i6] = jArr[i6] ^ ((j3 >>> 49) ^ (j4 << 15));
    }

    public static void i(long[] jArr, long[] jArr2) {
        g.a.d.d.a.c(jArr[0], jArr2, 0);
        g.a.d.d.a.c(jArr[1], jArr2, 2);
        g.a.d.d.a.c(jArr[2], jArr2, 4);
        jArr2[6] = jArr[3] & 1;
    }

    public static void j(long[] jArr, long[] jArr2) {
        if (g.a.d.d.g.w(jArr)) {
            throw new IllegalStateException();
        }
        long[] jArrI = g.a.d.d.g.i();
        long[] jArrI2 = g.a.d.d.g.i();
        p(jArr, jArrI);
        r(jArrI, 1, jArrI2);
        k(jArrI, jArrI2, jArrI);
        r(jArrI2, 1, jArrI2);
        k(jArrI, jArrI2, jArrI);
        r(jArrI, 3, jArrI2);
        k(jArrI, jArrI2, jArrI);
        r(jArrI, 6, jArrI2);
        k(jArrI, jArrI2, jArrI);
        r(jArrI, 12, jArrI2);
        k(jArrI, jArrI2, jArrI);
        r(jArrI, 24, jArrI2);
        k(jArrI, jArrI2, jArrI);
        r(jArrI, 48, jArrI2);
        k(jArrI, jArrI2, jArrI);
        r(jArrI, 96, jArrI2);
        k(jArrI, jArrI2, jArr2);
    }

    public static void k(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArrK = g.a.d.d.g.k();
        g(jArr, jArr2, jArrK);
        m(jArrK, jArr3);
    }

    public static void l(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArrK = g.a.d.d.g.k();
        g(jArr, jArr2, jArrK);
        b(jArr3, jArrK, jArr3);
    }

    public static void m(long[] jArr, long[] jArr2) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = jArr[2];
        long j4 = jArr[3];
        long j5 = jArr[4];
        long j6 = jArr[5];
        long j7 = jArr[6];
        long j8 = j5 ^ (j7 >>> 50);
        long j9 = (j4 ^ ((j7 >>> 1) ^ (j7 << 14))) ^ (j6 >>> 50);
        long j10 = j ^ (j8 << 63);
        long j11 = (j2 ^ (j6 << 63)) ^ ((j8 >>> 1) ^ (j8 << 14));
        long j12 = ((j3 ^ (j7 << 63)) ^ ((j6 >>> 1) ^ (j6 << 14))) ^ (j8 >>> 50);
        long j13 = j9 >>> 1;
        jArr2[0] = (j10 ^ j13) ^ (j13 << 15);
        jArr2[1] = (j13 >>> 49) ^ j11;
        jArr2[2] = j12;
        jArr2[3] = 1 & j9;
    }

    public static void n(long[] jArr, int i2) {
        int i3 = i2 + 3;
        long j = jArr[i3];
        long j2 = j >>> 1;
        jArr[i2] = jArr[i2] ^ ((j2 << 15) ^ j2);
        int i4 = i2 + 1;
        jArr[i4] = (j2 >>> 49) ^ jArr[i4];
        jArr[i3] = j & 1;
    }

    public static void o(long[] jArr, long[] jArr2) {
        long jF = g.a.d.d.a.f(jArr[0]);
        long jF2 = g.a.d.d.a.f(jArr[1]);
        long j = (jF & 4294967295L) | (jF2 << 32);
        long j2 = (jF >>> 32) | (jF2 & (-4294967296L));
        long jF3 = g.a.d.d.a.f(jArr[2]);
        long j3 = (jF3 & 4294967295L) ^ (jArr[3] << 32);
        long j4 = jF3 >>> 32;
        jArr2[0] = j ^ (j2 << 8);
        jArr2[1] = ((j3 ^ (j4 << 8)) ^ (j2 >>> 56)) ^ (j2 << 33);
        jArr2[2] = (j2 >>> 31) ^ ((j4 >>> 56) ^ (j4 << 33));
        jArr2[3] = j4 >>> 31;
    }

    public static void p(long[] jArr, long[] jArr2) {
        long[] jArrK = g.a.d.d.g.k();
        i(jArr, jArrK);
        m(jArrK, jArr2);
    }

    public static void q(long[] jArr, long[] jArr2) {
        long[] jArrK = g.a.d.d.g.k();
        i(jArr, jArrK);
        b(jArr2, jArrK, jArr2);
    }

    public static void r(long[] jArr, int i2, long[] jArr2) {
        long[] jArrK = g.a.d.d.g.k();
        i(jArr, jArrK);
        while (true) {
            m(jArrK, jArr2);
            i2--;
            if (i2 <= 0) {
                return;
            } else {
                i(jArr2, jArrK);
            }
        }
    }

    public static int s(long[] jArr) {
        return ((int) jArr[0]) & 1;
    }
}
