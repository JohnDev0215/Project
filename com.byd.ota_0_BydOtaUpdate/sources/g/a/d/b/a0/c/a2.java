package g.a.d.b.a0.c;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class a2 {
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
        jArr3[6] = jArr[6] ^ jArr2[6];
        jArr3[7] = jArr2[7] ^ jArr[7];
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
        jArr[0] = j ^ (j2 << 60);
        jArr[1] = (j2 >>> 4) ^ (j3 << 56);
        jArr[2] = (j3 >>> 8) ^ (j4 << 52);
        jArr[3] = (j4 >>> 12) ^ (j5 << 48);
        jArr[4] = (j5 >>> 16) ^ (j6 << 44);
        jArr[5] = (j6 >>> 20) ^ (j7 << 40);
        jArr[6] = (j7 >>> 24) ^ (j8 << 36);
        jArr[7] = j8 >>> 28;
    }

    public static void f(long[] jArr, long[] jArr2) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = jArr[2];
        long j4 = jArr[3];
        jArr2[0] = j & 1152921504606846975L;
        jArr2[1] = ((j >>> 60) ^ (j2 << 4)) & 1152921504606846975L;
        jArr2[2] = ((j2 >>> 56) ^ (j3 << 8)) & 1152921504606846975L;
        jArr2[3] = (j3 >>> 52) ^ (j4 << 12);
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
        int i4 = 54;
        do {
            int i5 = (int) (j >>> i4);
            long j5 = jArr2[i5 & 7] ^ (jArr2[(i5 >>> 3) & 7] << 3);
            j3 ^= j5 << i4;
            j4 ^= j5 >>> (-i4);
            i4 -= 6;
        } while (i4 > 0);
        jArr[i2] = jArr[i2] ^ (1152921504606846975L & j3);
        int i6 = i2 + 1;
        jArr[i6] = ((((((j & 585610922974906400L) & ((j2 << 4) >> 63)) >>> 5) ^ j4) << 4) ^ (j3 >>> 60)) ^ jArr[i6];
    }

    public static void i(long[] jArr, long[] jArr2) {
        g.a.d.d.a.c(jArr[0], jArr2, 0);
        g.a.d.d.a.c(jArr[1], jArr2, 2);
        g.a.d.d.a.c(jArr[2], jArr2, 4);
        long j = jArr[3];
        jArr2[6] = g.a.d.d.a.b((int) j);
        jArr2[7] = ((long) g.a.d.d.a.a((int) (j >>> 32))) & 4294967295L;
    }

    public static void j(long[] jArr, long[] jArr2) {
        if (g.a.d.d.g.w(jArr)) {
            throw new IllegalStateException();
        }
        long[] jArrI = g.a.d.d.g.i();
        long[] jArrI2 = g.a.d.d.g.i();
        p(jArr, jArrI);
        k(jArrI, jArr, jArrI);
        p(jArrI, jArrI);
        k(jArrI, jArr, jArrI);
        r(jArrI, 3, jArrI2);
        k(jArrI2, jArrI, jArrI2);
        p(jArrI2, jArrI2);
        k(jArrI2, jArr, jArrI2);
        r(jArrI2, 7, jArrI);
        k(jArrI, jArrI2, jArrI);
        r(jArrI, 14, jArrI2);
        k(jArrI2, jArrI, jArrI2);
        p(jArrI2, jArrI2);
        k(jArrI2, jArr, jArrI2);
        r(jArrI2, 29, jArrI);
        k(jArrI, jArrI2, jArrI);
        p(jArrI, jArrI);
        k(jArrI, jArr, jArrI);
        r(jArrI, 59, jArrI2);
        k(jArrI2, jArrI, jArrI2);
        p(jArrI2, jArrI2);
        k(jArrI2, jArr, jArrI2);
        r(jArrI2, 119, jArrI);
        k(jArrI, jArrI2, jArrI);
        p(jArrI, jArr2);
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
        long j8 = jArr[7];
        long j9 = j7 ^ (j8 >>> 17);
        long j10 = (j6 ^ (j8 << 47)) ^ (j9 >>> 17);
        long j11 = ((j5 ^ (j8 >>> 47)) ^ (j9 << 47)) ^ (j10 >>> 17);
        long j12 = j ^ (j11 << 17);
        long j13 = (j2 ^ (j10 << 17)) ^ (j11 >>> 47);
        long j14 = ((j3 ^ (j9 << 17)) ^ (j10 >>> 47)) ^ (j11 << 47);
        long j15 = (((j4 ^ (j8 << 17)) ^ (j9 >>> 47)) ^ (j10 << 47)) ^ (j11 >>> 17);
        long j16 = j15 >>> 47;
        jArr2[0] = j12 ^ j16;
        jArr2[1] = j13;
        jArr2[2] = (j16 << 30) ^ j14;
        jArr2[3] = 140737488355327L & j15;
    }

    public static void n(long[] jArr, int i2) {
        int i3 = i2 + 3;
        long j = jArr[i3];
        long j2 = j >>> 47;
        jArr[i2] = jArr[i2] ^ j2;
        int i4 = i2 + 2;
        jArr[i4] = (j2 << 30) ^ jArr[i4];
        jArr[i3] = j & 140737488355327L;
    }

    public static void o(long[] jArr, long[] jArr2) {
        int i2 = 0;
        long jF = g.a.d.d.a.f(jArr[0]);
        long jF2 = g.a.d.d.a.f(jArr[1]);
        long j = (jF & 4294967295L) | (jF2 << 32);
        long j2 = (jF >>> 32) | (jF2 & (-4294967296L));
        int i3 = 2;
        long jF3 = g.a.d.d.a.f(jArr[2]);
        long jF4 = g.a.d.d.a.f(jArr[3]);
        long j3 = (jF3 & 4294967295L) | (jF4 << 32);
        long j4 = (jF4 & (-4294967296L)) | (jF3 >>> 32);
        long j5 = j4 >>> 49;
        long j6 = (j2 >>> 49) | (j4 << 15);
        long j7 = j4 ^ (j2 << 15);
        long[] jArrK = g.a.d.d.g.k();
        int[] iArr = {39, 120};
        while (i2 < i3) {
            int i4 = iArr[i2] >>> 6;
            int i5 = iArr[i2] & 63;
            jArrK[i4] = jArrK[i4] ^ (j2 << i5);
            int i6 = i4 + 1;
            int[] iArr2 = iArr;
            int i7 = -i5;
            jArrK[i6] = jArrK[i6] ^ ((j7 << i5) | (j2 >>> i7));
            int i8 = i4 + 2;
            jArrK[i8] = jArrK[i8] ^ ((j6 << i5) | (j7 >>> i7));
            int i9 = i4 + 3;
            jArrK[i9] = jArrK[i9] ^ ((j5 << i5) | (j6 >>> i7));
            int i10 = i4 + 4;
            jArrK[i10] = jArrK[i10] ^ (j5 >>> i7);
            i2++;
            i3 = 2;
            iArr = iArr2;
        }
        m(jArrK, jArr2);
        jArr2[0] = jArr2[0] ^ j;
        jArr2[1] = jArr2[1] ^ j3;
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
        return ((int) ((jArr[0] ^ (jArr[1] >>> 17)) ^ (jArr[2] >>> 34))) & 1;
    }
}
