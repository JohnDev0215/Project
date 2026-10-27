package g.a.d.b.a0.c;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class g1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final long[] f5339a = {-5270498306774157648L, 5270498306774195053L, 19634136210L};

    public static void a(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr3[0] = jArr[0] ^ jArr2[0];
        jArr3[1] = jArr[1] ^ jArr2[1];
        jArr3[2] = jArr2[2] ^ jArr[2];
    }

    public static void b(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr3[0] = jArr[0] ^ jArr2[0];
        jArr3[1] = jArr[1] ^ jArr2[1];
        jArr3[2] = jArr[2] ^ jArr2[2];
        jArr3[3] = jArr[3] ^ jArr2[3];
        jArr3[4] = jArr[4] ^ jArr2[4];
        jArr3[5] = jArr2[5] ^ jArr[5];
    }

    public static void c(long[] jArr, long[] jArr2) {
        jArr2[0] = jArr[0] ^ 1;
        jArr2[1] = jArr[1];
        jArr2[2] = jArr[2];
    }

    public static long[] d(BigInteger bigInteger) {
        long[] jArrO = g.a.d.d.e.o(bigInteger);
        m(jArrO, 0);
        return jArrO;
    }

    public static void e(long[] jArr) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = jArr[2];
        long j4 = jArr[3];
        long j5 = jArr[4];
        long j6 = jArr[5];
        jArr[0] = j ^ (j2 << 55);
        jArr[1] = (j2 >>> 9) ^ (j3 << 46);
        jArr[2] = (j3 >>> 18) ^ (j4 << 37);
        jArr[3] = (j4 >>> 27) ^ (j5 << 28);
        jArr[4] = (j5 >>> 36) ^ (j6 << 19);
        jArr[5] = j6 >>> 45;
    }

    public static void f(long[] jArr, long[] jArr2, long[] jArr3) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = (jArr[2] << 18) ^ (j2 >>> 46);
        long j4 = ((j2 << 9) ^ (j >>> 55)) & 36028797018963967L;
        long j5 = j & 36028797018963967L;
        long j6 = jArr2[0];
        long j7 = jArr2[1];
        long j8 = (j7 >>> 46) ^ (jArr2[2] << 18);
        long j9 = ((j6 >>> 55) ^ (j7 << 9)) & 36028797018963967L;
        long j10 = j6 & 36028797018963967L;
        long[] jArr4 = new long[10];
        g(j5, j10, jArr4, 0);
        g(j3, j8, jArr4, 2);
        long j11 = (j5 ^ j4) ^ j3;
        long j12 = (j10 ^ j9) ^ j8;
        g(j11, j12, jArr4, 4);
        long j13 = (j4 << 1) ^ (j3 << 2);
        long j14 = (j9 << 1) ^ (j8 << 2);
        g(j5 ^ j13, j10 ^ j14, jArr4, 6);
        g(j11 ^ j13, j12 ^ j14, jArr4, 8);
        long j15 = jArr4[6] ^ jArr4[8];
        long j16 = jArr4[7] ^ jArr4[9];
        long j17 = (j15 << 1) ^ jArr4[6];
        long j18 = (j15 ^ (j16 << 1)) ^ jArr4[7];
        long j19 = jArr4[0];
        long j20 = (jArr4[1] ^ jArr4[0]) ^ jArr4[4];
        long j21 = jArr4[1] ^ jArr4[5];
        long j22 = ((j17 ^ j19) ^ (jArr4[2] << 4)) ^ (jArr4[2] << 1);
        long j23 = (((j18 ^ j20) ^ (jArr4[3] << 4)) ^ (jArr4[3] << 1)) ^ (j22 >>> 55);
        long j24 = (j21 ^ j16) ^ (j23 >>> 55);
        long j25 = j23 & 36028797018963967L;
        long j26 = ((j22 & 36028797018963967L) >>> 1) ^ ((j25 & 1) << 54);
        long j27 = j26 ^ (j26 << 1);
        long j28 = j27 ^ (j27 << 2);
        long j29 = j28 ^ (j28 << 4);
        long j30 = j29 ^ (j29 << 8);
        long j31 = j30 ^ (j30 << 16);
        long j32 = (j31 ^ (j31 << 32)) & 36028797018963967L;
        long j33 = ((j25 >>> 1) ^ ((j24 & 1) << 54)) ^ (j32 >>> 54);
        long j34 = j33 ^ (j33 << 1);
        long j35 = j34 ^ (j34 << 2);
        long j36 = j35 ^ (j35 << 4);
        long j37 = j36 ^ (j36 << 8);
        long j38 = j37 ^ (j37 << 16);
        long j39 = (j38 ^ (j38 << 32)) & 36028797018963967L;
        long j40 = (j24 >>> 1) ^ (j39 >>> 54);
        long j41 = j40 ^ (j40 << 1);
        long j42 = j41 ^ (j41 << 2);
        long j43 = j42 ^ (j42 << 4);
        long j44 = j43 ^ (j43 << 8);
        long j45 = j44 ^ (j44 << 16);
        long j46 = j45 ^ (j45 << 32);
        jArr3[0] = j19;
        jArr3[1] = (j20 ^ j32) ^ jArr4[2];
        jArr3[2] = ((j21 ^ j39) ^ j32) ^ jArr4[3];
        jArr3[3] = j46 ^ j39;
        jArr3[4] = jArr4[2] ^ j46;
        jArr3[5] = jArr4[3];
        e(jArr3);
    }

    public static void g(long j, long j2, long[] jArr, int i2) {
        long[] jArr2 = new long[8];
        jArr2[1] = j2;
        jArr2[2] = jArr2[1] << 1;
        jArr2[3] = jArr2[2] ^ j2;
        jArr2[4] = jArr2[2] << 1;
        jArr2[5] = jArr2[4] ^ j2;
        jArr2[6] = jArr2[3] << 1;
        jArr2[7] = jArr2[6] ^ j2;
        long j3 = jArr2[((int) j) & 3];
        long j4 = 0;
        int i3 = 47;
        do {
            int i4 = (int) (j >>> i3);
            long j5 = (jArr2[i4 & 7] ^ (jArr2[(i4 >>> 3) & 7] << 3)) ^ (jArr2[(i4 >>> 6) & 7] << 6);
            j3 ^= j5 << i3;
            j4 ^= j5 >>> (-i3);
            i3 -= 9;
        } while (i3 > 0);
        jArr[i2] = 36028797018963967L & j3;
        jArr[i2 + 1] = (j3 >>> 55) ^ (j4 << 9);
    }

    public static void h(long[] jArr, long[] jArr2) {
        g.a.d.d.a.c(jArr[0], jArr2, 0);
        g.a.d.d.a.c(jArr[1], jArr2, 2);
        long j = jArr[2];
        jArr2[4] = g.a.d.d.a.b((int) j);
        jArr2[5] = ((long) g.a.d.d.a.e((int) (j >>> 32))) & 4294967295L;
    }

    public static void i(long[] jArr, long[] jArr2) {
        if (g.a.d.d.e.v(jArr)) {
            throw new IllegalStateException();
        }
        long[] jArrH = g.a.d.d.e.h();
        long[] jArrH2 = g.a.d.d.e.h();
        o(jArr, jArrH);
        q(jArrH, 1, jArrH2);
        j(jArrH, jArrH2, jArrH);
        q(jArrH2, 1, jArrH2);
        j(jArrH, jArrH2, jArrH);
        q(jArrH, 3, jArrH2);
        j(jArrH, jArrH2, jArrH);
        q(jArrH2, 3, jArrH2);
        j(jArrH, jArrH2, jArrH);
        q(jArrH, 9, jArrH2);
        j(jArrH, jArrH2, jArrH);
        q(jArrH2, 9, jArrH2);
        j(jArrH, jArrH2, jArrH);
        q(jArrH, 27, jArrH2);
        j(jArrH, jArrH2, jArrH);
        q(jArrH2, 27, jArrH2);
        j(jArrH, jArrH2, jArrH);
        q(jArrH, 81, jArrH2);
        j(jArrH, jArrH2, jArr2);
    }

    public static void j(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArrJ = g.a.d.d.e.j();
        f(jArr, jArr2, jArrJ);
        l(jArrJ, jArr3);
    }

    public static void k(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArrJ = g.a.d.d.e.j();
        f(jArr, jArr2, jArrJ);
        b(jArr3, jArrJ, jArr3);
    }

    public static void l(long[] jArr, long[] jArr2) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = jArr[2];
        long j4 = jArr[3];
        long j5 = jArr[4];
        long j6 = jArr[5];
        long j7 = j4 ^ ((((j6 >>> 35) ^ (j6 >>> 32)) ^ (j6 >>> 29)) ^ (j6 >>> 28));
        long j8 = (j3 ^ ((((j6 << 29) ^ (j6 << 32)) ^ (j6 << 35)) ^ (j6 << 36))) ^ ((j5 >>> 28) ^ (((j5 >>> 35) ^ (j5 >>> 32)) ^ (j5 >>> 29)));
        long j9 = j ^ ((((j7 << 29) ^ (j7 << 32)) ^ (j7 << 35)) ^ (j7 << 36));
        long j10 = (j2 ^ ((((j5 << 29) ^ (j5 << 32)) ^ (j5 << 35)) ^ (j5 << 36))) ^ ((j7 >>> 28) ^ (((j7 >>> 35) ^ (j7 >>> 32)) ^ (j7 >>> 29)));
        long j11 = j8 >>> 35;
        jArr2[0] = (((j9 ^ j11) ^ (j11 << 3)) ^ (j11 << 6)) ^ (j11 << 7);
        jArr2[1] = j10;
        jArr2[2] = 34359738367L & j8;
    }

    public static void m(long[] jArr, int i2) {
        int i3 = i2 + 2;
        long j = jArr[i3];
        long j2 = j >>> 35;
        jArr[i2] = ((j2 << 7) ^ (((j2 << 3) ^ j2) ^ (j2 << 6))) ^ jArr[i2];
        jArr[i3] = j & 34359738367L;
    }

    public static void n(long[] jArr, long[] jArr2) {
        long[] jArrH = g.a.d.d.e.h();
        long jF = g.a.d.d.a.f(jArr[0]);
        long jF2 = g.a.d.d.a.f(jArr[1]);
        long j = (jF & 4294967295L) | (jF2 << 32);
        jArrH[0] = (jF >>> 32) | (jF2 & (-4294967296L));
        long jF3 = g.a.d.d.a.f(jArr[2]);
        jArrH[1] = jF3 >>> 32;
        j(jArrH, f5339a, jArr2);
        jArr2[0] = jArr2[0] ^ j;
        jArr2[1] = jArr2[1] ^ (jF3 & 4294967295L);
    }

    public static void o(long[] jArr, long[] jArr2) {
        long[] jArrJ = g.a.d.d.e.j();
        h(jArr, jArrJ);
        l(jArrJ, jArr2);
    }

    public static void p(long[] jArr, long[] jArr2) {
        long[] jArrJ = g.a.d.d.e.j();
        h(jArr, jArrJ);
        b(jArr2, jArrJ, jArr2);
    }

    public static void q(long[] jArr, int i2, long[] jArr2) {
        long[] jArrJ = g.a.d.d.e.j();
        h(jArr, jArrJ);
        while (true) {
            l(jArrJ, jArr2);
            i2--;
            if (i2 <= 0) {
                return;
            } else {
                h(jArr2, jArrJ);
            }
        }
    }

    public static int r(long[] jArr) {
        return ((int) (jArr[0] ^ (jArr[2] >>> 29))) & 1;
    }
}
