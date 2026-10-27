package g.a.d.b.a0.c;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class k2 {
    public static void a(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr3[0] = jArr[0] ^ jArr2[0];
        jArr3[1] = jArr[1] ^ jArr2[1];
        jArr3[2] = jArr[2] ^ jArr2[2];
        jArr3[3] = jArr[3] ^ jArr2[3];
        jArr3[4] = jArr[4] ^ jArr2[4];
        jArr3[5] = jArr[5] ^ jArr2[5];
        jArr3[6] = jArr2[6] ^ jArr[6];
    }

    public static void b(long[] jArr, long[] jArr2, long[] jArr3) {
        for (int i2 = 0; i2 < 13; i2++) {
            jArr3[i2] = jArr[i2] ^ jArr2[i2];
        }
    }

    public static void c(long[] jArr, long[] jArr2) {
        jArr2[0] = jArr[0] ^ 1;
        jArr2[1] = jArr[1];
        jArr2[2] = jArr[2];
        jArr2[3] = jArr[3];
        jArr2[4] = jArr[4];
        jArr2[5] = jArr[5];
        jArr2[6] = jArr[6];
    }

    public static long[] d(BigInteger bigInteger) {
        long[] jArrE = g.a.d.d.j.e(bigInteger);
        n(jArrE, 0);
        return jArrE;
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
        long j9 = jArr[8];
        long j10 = jArr[9];
        long j11 = jArr[10];
        long j12 = jArr[11];
        long j13 = jArr[12];
        long j14 = jArr[13];
        jArr[0] = j ^ (j2 << 59);
        jArr[1] = (j2 >>> 5) ^ (j3 << 54);
        jArr[2] = (j3 >>> 10) ^ (j4 << 49);
        jArr[3] = (j4 >>> 15) ^ (j5 << 44);
        jArr[4] = (j5 >>> 20) ^ (j6 << 39);
        jArr[5] = (j6 >>> 25) ^ (j7 << 34);
        jArr[6] = (j7 >>> 30) ^ (j8 << 29);
        jArr[7] = (j8 >>> 35) ^ (j9 << 24);
        jArr[8] = (j9 >>> 40) ^ (j10 << 19);
        jArr[9] = (j10 >>> 45) ^ (j11 << 14);
        jArr[10] = (j11 >>> 50) ^ (j12 << 9);
        jArr[11] = ((j12 >>> 55) ^ (j13 << 4)) ^ (j14 << 63);
        jArr[12] = (j13 >>> 60) ^ (j14 >>> 1);
        jArr[13] = 0;
    }

    public static void f(long[] jArr, long[] jArr2) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = jArr[2];
        long j4 = jArr[3];
        long j5 = jArr[4];
        long j6 = jArr[5];
        long j7 = jArr[6];
        jArr2[0] = j & 576460752303423487L;
        jArr2[1] = ((j >>> 59) ^ (j2 << 5)) & 576460752303423487L;
        jArr2[2] = ((j2 >>> 54) ^ (j3 << 10)) & 576460752303423487L;
        jArr2[3] = ((j3 >>> 49) ^ (j4 << 15)) & 576460752303423487L;
        jArr2[4] = ((j4 >>> 44) ^ (j5 << 20)) & 576460752303423487L;
        jArr2[5] = ((j5 >>> 39) ^ (j6 << 25)) & 576460752303423487L;
        jArr2[6] = (j6 >>> 34) ^ (j7 << 30);
    }

    public static void g(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArr4 = new long[7];
        long[] jArr5 = new long[7];
        f(jArr, jArr4);
        f(jArr2, jArr5);
        for (int i2 = 0; i2 < 7; i2++) {
            h(jArr4, jArr5[i2], jArr3, i2);
        }
        e(jArr3);
    }

    public static void h(long[] jArr, long j, long[] jArr2, int i2) {
        long[] jArr3 = new long[8];
        jArr3[1] = j;
        jArr3[2] = jArr3[1] << 1;
        jArr3[3] = jArr3[2] ^ j;
        jArr3[4] = jArr3[2] << 1;
        jArr3[5] = jArr3[4] ^ j;
        jArr3[6] = jArr3[3] << 1;
        jArr3[7] = jArr3[6] ^ j;
        for (int i3 = 0; i3 < 7; i3++) {
            long j2 = jArr[i3];
            int i4 = (int) j2;
            long j3 = 0;
            long j4 = jArr3[i4 & 7] ^ (jArr3[(i4 >>> 3) & 7] << 3);
            int i5 = 54;
            do {
                int i6 = (int) (j2 >>> i5);
                long j5 = jArr3[i6 & 7] ^ (jArr3[(i6 >>> 3) & 7] << 3);
                j4 ^= j5 << i5;
                j3 ^= j5 >>> (-i5);
                i5 -= 6;
            } while (i5 > 0);
            int i7 = i2 + i3;
            jArr2[i7] = jArr2[i7] ^ (576460752303423487L & j4);
            int i8 = i7 + 1;
            jArr2[i8] = jArr2[i8] ^ ((j3 << 5) ^ (j4 >>> 59));
        }
    }

    public static void i(long[] jArr, long[] jArr2) {
        for (int i2 = 0; i2 < 6; i2++) {
            g.a.d.d.a.c(jArr[i2], jArr2, i2 << 1);
        }
        jArr2[12] = g.a.d.d.a.b((int) jArr[6]);
    }

    public static void j(long[] jArr, long[] jArr2) {
        if (g.a.d.d.j.g(jArr)) {
            throw new IllegalStateException();
        }
        long[] jArrB = g.a.d.d.j.b();
        long[] jArrB2 = g.a.d.d.j.b();
        long[] jArrB3 = g.a.d.d.j.b();
        p(jArr, jArrB);
        r(jArrB, 1, jArrB2);
        k(jArrB, jArrB2, jArrB);
        r(jArrB2, 1, jArrB2);
        k(jArrB, jArrB2, jArrB);
        r(jArrB, 3, jArrB2);
        k(jArrB, jArrB2, jArrB);
        r(jArrB, 6, jArrB2);
        k(jArrB, jArrB2, jArrB);
        r(jArrB, 12, jArrB2);
        k(jArrB, jArrB2, jArrB3);
        r(jArrB3, 24, jArrB);
        r(jArrB, 24, jArrB2);
        k(jArrB, jArrB2, jArrB);
        r(jArrB, 48, jArrB2);
        k(jArrB, jArrB2, jArrB);
        r(jArrB, 96, jArrB2);
        k(jArrB, jArrB2, jArrB);
        r(jArrB, 192, jArrB2);
        k(jArrB, jArrB2, jArrB);
        k(jArrB, jArrB3, jArr2);
    }

    public static void k(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArrC = g.a.d.d.j.c();
        g(jArr, jArr2, jArrC);
        m(jArrC, jArr3);
    }

    public static void l(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArrC = g.a.d.d.j.c();
        g(jArr, jArr2, jArrC);
        b(jArr3, jArrC, jArr3);
    }

    public static void m(long[] jArr, long[] jArr2) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = jArr[2];
        long j4 = jArr[3];
        long j5 = jArr[4];
        long j6 = jArr[5];
        long j7 = jArr[6];
        long j8 = jArr[7];
        long j9 = jArr[12];
        long j10 = j6 ^ (j9 << 39);
        long j11 = j7 ^ ((j9 >>> 25) ^ (j9 << 62));
        long j12 = j8 ^ (j9 >>> 2);
        long j13 = jArr[11];
        long j14 = j5 ^ (j13 << 39);
        long j15 = j10 ^ ((j13 >>> 25) ^ (j13 << 62));
        long j16 = j11 ^ (j13 >>> 2);
        long j17 = jArr[10];
        long j18 = j4 ^ (j17 << 39);
        long j19 = j14 ^ ((j17 >>> 25) ^ (j17 << 62));
        long j20 = j15 ^ (j17 >>> 2);
        long j21 = jArr[9];
        long j22 = j3 ^ (j21 << 39);
        long j23 = j18 ^ ((j21 >>> 25) ^ (j21 << 62));
        long j24 = j19 ^ (j21 >>> 2);
        long j25 = jArr[8];
        long j26 = j ^ (j12 << 39);
        long j27 = (j2 ^ (j25 << 39)) ^ ((j12 >>> 25) ^ (j12 << 62));
        long j28 = (j22 ^ ((j25 >>> 25) ^ (j25 << 62))) ^ (j12 >>> 2);
        long j29 = j16 >>> 25;
        jArr2[0] = j26 ^ j29;
        jArr2[1] = (j29 << 23) ^ j27;
        jArr2[2] = j28;
        jArr2[3] = j23 ^ (j25 >>> 2);
        jArr2[4] = j24;
        jArr2[5] = j20;
        jArr2[6] = j16 & 33554431;
    }

    public static void n(long[] jArr, int i2) {
        int i3 = i2 + 6;
        long j = jArr[i3];
        long j2 = j >>> 25;
        jArr[i2] = jArr[i2] ^ j2;
        int i4 = i2 + 1;
        jArr[i4] = (j2 << 23) ^ jArr[i4];
        jArr[i3] = j & 33554431;
    }

    public static void o(long[] jArr, long[] jArr2) {
        long jF = g.a.d.d.a.f(jArr[0]);
        long jF2 = g.a.d.d.a.f(jArr[1]);
        long j = (jF & 4294967295L) | (jF2 << 32);
        long j2 = (jF >>> 32) | (jF2 & (-4294967296L));
        long jF3 = g.a.d.d.a.f(jArr[2]);
        long jF4 = g.a.d.d.a.f(jArr[3]);
        long j3 = (jF3 & 4294967295L) | (jF4 << 32);
        long j4 = (jF3 >>> 32) | (jF4 & (-4294967296L));
        long jF5 = g.a.d.d.a.f(jArr[4]);
        long jF6 = g.a.d.d.a.f(jArr[5]);
        long j5 = (jF5 >>> 32) | (jF6 & (-4294967296L));
        long jF7 = g.a.d.d.a.f(jArr[6]);
        long j6 = jF7 & 4294967295L;
        long j7 = jF7 >>> 32;
        jArr2[0] = j ^ (j2 << 44);
        jArr2[1] = (j3 ^ (j4 << 44)) ^ (j2 >>> 20);
        jArr2[2] = (((jF5 & 4294967295L) | (jF6 << 32)) ^ (j5 << 44)) ^ (j4 >>> 20);
        jArr2[3] = (((j7 << 44) ^ j6) ^ (j5 >>> 20)) ^ (j2 << 13);
        jArr2[4] = (j2 >>> 51) ^ ((j7 >>> 20) ^ (j4 << 13));
        jArr2[5] = (j5 << 13) ^ (j4 >>> 51);
        jArr2[6] = (j7 << 13) ^ (j5 >>> 51);
    }

    public static void p(long[] jArr, long[] jArr2) {
        long[] jArrK = g.a.d.d.m.k(13);
        i(jArr, jArrK);
        m(jArrK, jArr2);
    }

    public static void q(long[] jArr, long[] jArr2) {
        long[] jArrK = g.a.d.d.m.k(13);
        i(jArr, jArrK);
        b(jArr2, jArrK, jArr2);
    }

    public static void r(long[] jArr, int i2, long[] jArr2) {
        long[] jArrK = g.a.d.d.m.k(13);
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
