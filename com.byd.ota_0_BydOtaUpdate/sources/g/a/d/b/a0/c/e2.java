package g.a.d.b.a0.c;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class e2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final long[] f5328a = {878416384462358536L, 3513665537849438403L, -9076969306111048948L, 585610922974906400L, 34087042};

    public static void a(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr3[0] = jArr[0] ^ jArr2[0];
        jArr3[1] = jArr[1] ^ jArr2[1];
        jArr3[2] = jArr[2] ^ jArr2[2];
        jArr3[3] = jArr[3] ^ jArr2[3];
        jArr3[4] = jArr2[4] ^ jArr[4];
    }

    public static void b(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr3[0] = jArr[0] ^ jArr2[0];
        jArr3[1] = jArr[1] ^ jArr2[1];
        jArr3[2] = jArr[2] ^ jArr2[2];
        jArr3[3] = jArr[3] ^ jArr2[3];
        jArr3[4] = jArr[4] ^ jArr2[4];
        jArr3[5] = jArr[5] ^ jArr2[5];
        jArr3[6] = jArr[6] ^ jArr2[6];
        jArr3[7] = jArr[7] ^ jArr2[7];
        jArr3[8] = jArr2[8] ^ jArr[8];
    }

    public static void c(long[] jArr, long[] jArr2) {
        jArr2[0] = jArr[0] ^ 1;
        jArr2[1] = jArr[1];
        jArr2[2] = jArr[2];
        jArr2[3] = jArr[3];
        jArr2[4] = jArr[4];
    }

    public static long[] d(BigInteger bigInteger) {
        long[] jArrE = g.a.d.d.h.e(bigInteger);
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
        jArr[0] = j ^ (j2 << 57);
        jArr[1] = (j2 >>> 7) ^ (j3 << 50);
        jArr[2] = (j3 >>> 14) ^ (j4 << 43);
        jArr[3] = (j4 >>> 21) ^ (j5 << 36);
        jArr[4] = (j5 >>> 28) ^ (j6 << 29);
        jArr[5] = (j6 >>> 35) ^ (j7 << 22);
        jArr[6] = (j7 >>> 42) ^ (j8 << 15);
        jArr[7] = (j8 >>> 49) ^ (j9 << 8);
        jArr[8] = (j9 >>> 56) ^ (j10 << 1);
        jArr[9] = j10 >>> 63;
    }

    public static void f(long[] jArr, long[] jArr2) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = jArr[2];
        long j4 = jArr[3];
        long j5 = jArr[4];
        jArr2[0] = j & 144115188075855871L;
        jArr2[1] = ((j >>> 57) ^ (j2 << 7)) & 144115188075855871L;
        jArr2[2] = ((j2 >>> 50) ^ (j3 << 14)) & 144115188075855871L;
        jArr2[3] = ((j3 >>> 43) ^ (j4 << 21)) & 144115188075855871L;
        jArr2[4] = (j4 >>> 36) ^ (j5 << 28);
    }

    public static void g(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArr4 = new long[5];
        long[] jArr5 = new long[5];
        f(jArr, jArr4);
        f(jArr2, jArr5);
        long[] jArr6 = new long[26];
        h(jArr4[0], jArr5[0], jArr6, 0);
        h(jArr4[1], jArr5[1], jArr6, 2);
        h(jArr4[2], jArr5[2], jArr6, 4);
        h(jArr4[3], jArr5[3], jArr6, 6);
        h(jArr4[4], jArr5[4], jArr6, 8);
        long j = jArr4[0] ^ jArr4[1];
        long j2 = jArr5[0] ^ jArr5[1];
        long j3 = jArr4[0] ^ jArr4[2];
        long j4 = jArr5[0] ^ jArr5[2];
        long j5 = jArr4[2] ^ jArr4[4];
        long j6 = jArr5[2] ^ jArr5[4];
        long j7 = jArr4[3] ^ jArr4[4];
        long j8 = jArr5[3] ^ jArr5[4];
        h(j3 ^ jArr4[3], j4 ^ jArr5[3], jArr6, 18);
        h(j5 ^ jArr4[1], j6 ^ jArr5[1], jArr6, 20);
        long j9 = j ^ j7;
        long j10 = j2 ^ j8;
        long j11 = j9 ^ jArr4[2];
        long j12 = jArr5[2] ^ j10;
        h(j9, j10, jArr6, 22);
        h(j11, j12, jArr6, 24);
        h(j, j2, jArr6, 10);
        h(j3, j4, jArr6, 12);
        h(j5, j6, jArr6, 14);
        h(j7, j8, jArr6, 16);
        jArr3[0] = jArr6[0];
        jArr3[9] = jArr6[9];
        long j13 = jArr6[0] ^ jArr6[1];
        long j14 = jArr6[2] ^ j13;
        long j15 = jArr6[10] ^ j14;
        jArr3[1] = j15;
        long j16 = jArr6[3] ^ jArr6[4];
        long j17 = j14 ^ (j16 ^ (jArr6[11] ^ jArr6[12]));
        jArr3[2] = j17;
        long j18 = jArr6[5] ^ jArr6[6];
        long j19 = ((j13 ^ j16) ^ j18) ^ jArr6[8];
        long j20 = jArr6[13] ^ jArr6[14];
        jArr3[3] = (j19 ^ j20) ^ ((jArr6[18] ^ jArr6[22]) ^ jArr6[24]);
        long j21 = (jArr6[7] ^ jArr6[8]) ^ jArr6[9];
        long j22 = j21 ^ jArr6[17];
        jArr3[8] = j22;
        long j23 = (j21 ^ j18) ^ (jArr6[15] ^ jArr6[16]);
        jArr3[7] = j23;
        long j24 = j15 ^ j23;
        long j25 = (jArr6[19] ^ jArr6[20]) ^ (jArr6[25] ^ jArr6[24]);
        jArr3[4] = (j25 ^ (jArr6[18] ^ jArr6[23])) ^ j24;
        jArr3[5] = ((j17 ^ j22) ^ j25) ^ (jArr6[21] ^ jArr6[22]);
        jArr3[6] = ((((jArr6[9] ^ (j19 ^ jArr6[0])) ^ j20) ^ jArr6[21]) ^ jArr6[23]) ^ jArr6[25];
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

    public static void i(long[] jArr, long[] jArr2) {
        for (int i2 = 0; i2 < 4; i2++) {
            g.a.d.d.a.c(jArr[i2], jArr2, i2 << 1);
        }
        jArr2[8] = g.a.d.d.a.b((int) jArr[4]);
    }

    public static void j(long[] jArr, long[] jArr2) {
        if (g.a.d.d.h.g(jArr)) {
            throw new IllegalStateException();
        }
        long[] jArrB = g.a.d.d.h.b();
        long[] jArrB2 = g.a.d.d.h.b();
        p(jArr, jArrB);
        k(jArrB, jArr, jArrB);
        r(jArrB, 2, jArrB2);
        k(jArrB2, jArrB, jArrB2);
        r(jArrB2, 4, jArrB);
        k(jArrB, jArrB2, jArrB);
        r(jArrB, 8, jArrB2);
        k(jArrB2, jArrB, jArrB2);
        p(jArrB2, jArrB2);
        k(jArrB2, jArr, jArrB2);
        r(jArrB2, 17, jArrB);
        k(jArrB, jArrB2, jArrB);
        p(jArrB, jArrB);
        k(jArrB, jArr, jArrB);
        r(jArrB, 35, jArrB2);
        k(jArrB2, jArrB, jArrB2);
        r(jArrB2, 70, jArrB);
        k(jArrB, jArrB2, jArrB);
        p(jArrB, jArrB);
        k(jArrB, jArr, jArrB);
        r(jArrB, 141, jArrB2);
        k(jArrB2, jArrB, jArrB2);
        p(jArrB2, jArr2);
    }

    public static void k(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArrC = g.a.d.d.h.c();
        g(jArr, jArr2, jArrC);
        m(jArrC, jArr3);
    }

    public static void l(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArrC = g.a.d.d.h.c();
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
        long j9 = jArr[8];
        long j10 = j5 ^ ((((j9 >>> 27) ^ (j9 >>> 22)) ^ (j9 >>> 20)) ^ (j9 >>> 15));
        long j11 = j ^ ((((j6 << 37) ^ (j6 << 42)) ^ (j6 << 44)) ^ (j6 << 49));
        long j12 = (j2 ^ ((((j7 << 37) ^ (j7 << 42)) ^ (j7 << 44)) ^ (j7 << 49))) ^ ((((j6 >>> 27) ^ (j6 >>> 22)) ^ (j6 >>> 20)) ^ (j6 >>> 15));
        long j13 = j10 >>> 27;
        jArr2[0] = (((j11 ^ j13) ^ (j13 << 5)) ^ (j13 << 7)) ^ (j13 << 12);
        jArr2[1] = j12;
        jArr2[2] = (j3 ^ ((((j8 << 37) ^ (j8 << 42)) ^ (j8 << 44)) ^ (j8 << 49))) ^ ((((j7 >>> 27) ^ (j7 >>> 22)) ^ (j7 >>> 20)) ^ (j7 >>> 15));
        jArr2[3] = (j4 ^ ((((j9 << 37) ^ (j9 << 42)) ^ (j9 << 44)) ^ (j9 << 49))) ^ ((((j8 >>> 27) ^ (j8 >>> 22)) ^ (j8 >>> 20)) ^ (j8 >>> 15));
        jArr2[4] = 134217727 & j10;
    }

    public static void n(long[] jArr, int i2) {
        int i3 = i2 + 4;
        long j = jArr[i3];
        long j2 = j >>> 27;
        jArr[i2] = ((j2 << 12) ^ (((j2 << 5) ^ j2) ^ (j2 << 7))) ^ jArr[i2];
        jArr[i3] = j & 134217727;
    }

    public static void o(long[] jArr, long[] jArr2) {
        long[] jArrB = g.a.d.d.h.b();
        long jF = g.a.d.d.a.f(jArr[0]);
        long jF2 = g.a.d.d.a.f(jArr[1]);
        long j = (jF & 4294967295L) | (jF2 << 32);
        jArrB[0] = (jF >>> 32) | (jF2 & (-4294967296L));
        long jF3 = g.a.d.d.a.f(jArr[2]);
        long jF4 = g.a.d.d.a.f(jArr[3]);
        long j2 = (jF3 & 4294967295L) | (jF4 << 32);
        jArrB[1] = (jF3 >>> 32) | ((-4294967296L) & jF4);
        long jF5 = g.a.d.d.a.f(jArr[4]);
        jArrB[2] = jF5 >>> 32;
        k(jArrB, f5328a, jArr2);
        jArr2[0] = jArr2[0] ^ j;
        jArr2[1] = jArr2[1] ^ j2;
        jArr2[2] = jArr2[2] ^ (4294967295L & jF5);
    }

    public static void p(long[] jArr, long[] jArr2) {
        long[] jArrK = g.a.d.d.m.k(9);
        i(jArr, jArrK);
        m(jArrK, jArr2);
    }

    public static void q(long[] jArr, long[] jArr2) {
        long[] jArrK = g.a.d.d.m.k(9);
        i(jArr, jArrK);
        b(jArr2, jArrK, jArr2);
    }

    public static void r(long[] jArr, int i2, long[] jArr2) {
        long[] jArrK = g.a.d.d.m.k(9);
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
        return ((int) (jArr[0] ^ (jArr[4] >>> 15))) & 1;
    }
}
