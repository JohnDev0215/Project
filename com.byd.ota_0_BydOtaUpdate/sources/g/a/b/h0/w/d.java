package g.a.b.h0.w;

import g.a.f.f;

/* JADX INFO: loaded from: classes.dex */
public abstract class d {
    public static void a(long[] jArr, byte[] bArr) {
        f.r(jArr, bArr, 0);
    }

    public static void b(byte[] bArr, long[] jArr) {
        f.c(bArr, 0, jArr);
    }

    public static long[] c(byte[] bArr) {
        long[] jArr = new long[2];
        f.c(bArr, 0, jArr);
        return jArr;
    }

    public static void d(long[] jArr, long[] jArr2) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = j >> 63;
        jArr2[0] = ((j ^ ((-2233785415175766016L) & j3)) << 1) | (j2 >>> 63);
        jArr2[1] = (j2 << 1) | (-j3);
    }

    public static void e(byte[] bArr, byte[] bArr2) {
        long[] jArrC = c(bArr);
        f(jArrC, c(bArr2));
        a(jArrC, bArr);
    }

    public static void f(long[] jArr, long[] jArr2) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = jArr2[0];
        long j4 = jArr2[1];
        long j5 = 0;
        long j6 = 0;
        long j7 = 0;
        for (int i2 = 0; i2 < 64; i2++) {
            long j8 = j >> 63;
            j <<= 1;
            j5 ^= j3 & j8;
            long j9 = j7 ^ (j4 & j8);
            long j10 = j2 >> 63;
            j2 <<= 1;
            j7 = j9 ^ (j3 & j10);
            j6 ^= j4 & j10;
            long j11 = (j4 << 63) >> 8;
            j4 = (j4 >>> 1) | (j3 << 63);
            j3 = (j3 >>> 1) ^ (j11 & (-2233785415175766016L));
        }
        jArr[0] = ((((j6 >>> 1) ^ j6) ^ (j6 >>> 2)) ^ (j6 >>> 7)) ^ j5;
        jArr[1] = (((j6 << 63) ^ (j6 << 62)) ^ (j6 << 57)) ^ j7;
    }

    public static void g(long[] jArr, long[] jArr2) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = j2 << 57;
        jArr2[0] = (j3 >>> 7) ^ ((((j >>> 7) ^ j3) ^ (j3 >>> 1)) ^ (j3 >>> 2));
        jArr2[1] = (j << 57) | (j2 >>> 7);
    }

    public static long[] h() {
        return new long[]{Long.MIN_VALUE, 0};
    }

    public static void i(long[] jArr, long[] jArr2) {
        long[] jArr3 = new long[4];
        g.a.d.d.a.d(jArr[0], jArr3, 0);
        g.a.d.d.a.d(jArr[1], jArr3, 2);
        long j = jArr3[0];
        long j2 = jArr3[1];
        long j3 = jArr3[2];
        long j4 = jArr3[3];
        long j5 = j3 ^ ((j4 << 57) ^ ((j4 << 63) ^ (j4 << 62)));
        jArr2[0] = j ^ ((((j5 >>> 1) ^ j5) ^ (j5 >>> 2)) ^ (j5 >>> 7));
        jArr2[1] = (j2 ^ ((((j4 >>> 1) ^ j4) ^ (j4 >>> 2)) ^ (j4 >>> 7))) ^ ((j5 << 57) ^ ((j5 << 63) ^ (j5 << 62)));
    }

    public static void j(byte[] bArr, int i2, byte[] bArr2, int i3, int i4) {
        while (true) {
            i4--;
            if (i4 < 0) {
                return;
            }
            int i5 = i2 + i4;
            bArr[i5] = (byte) (bArr[i5] ^ bArr2[i3 + i4]);
        }
    }

    public static void k(byte[] bArr, int i2, byte[] bArr2, int i3, byte[] bArr3, int i4) {
        int i5 = 0;
        do {
            bArr3[i4 + i5] = (byte) (bArr[i2 + i5] ^ bArr2[i3 + i5]);
            int i6 = i5 + 1;
            bArr3[i4 + i6] = (byte) (bArr[i2 + i6] ^ bArr2[i3 + i6]);
            int i7 = i6 + 1;
            bArr3[i4 + i7] = (byte) (bArr[i2 + i7] ^ bArr2[i3 + i7]);
            int i8 = i7 + 1;
            bArr3[i4 + i8] = (byte) (bArr[i2 + i8] ^ bArr2[i3 + i8]);
            i5 = i8 + 1;
        } while (i5 < 16);
    }

    public static void l(byte[] bArr, byte[] bArr2) {
        int i2 = 0;
        do {
            bArr[i2] = (byte) (bArr[i2] ^ bArr2[i2]);
            int i3 = i2 + 1;
            bArr[i3] = (byte) (bArr[i3] ^ bArr2[i3]);
            int i4 = i3 + 1;
            bArr[i4] = (byte) (bArr[i4] ^ bArr2[i4]);
            int i5 = i4 + 1;
            bArr[i5] = (byte) (bArr[i5] ^ bArr2[i5]);
            i2 = i5 + 1;
        } while (i2 < 16);
    }

    public static void m(byte[] bArr, byte[] bArr2, int i2) {
        int i3 = 0;
        do {
            bArr[i3] = (byte) (bArr[i3] ^ bArr2[i2 + i3]);
            int i4 = i3 + 1;
            bArr[i4] = (byte) (bArr[i4] ^ bArr2[i2 + i4]);
            int i5 = i4 + 1;
            bArr[i5] = (byte) (bArr[i5] ^ bArr2[i2 + i5]);
            int i6 = i5 + 1;
            bArr[i6] = (byte) (bArr[i6] ^ bArr2[i2 + i6]);
            i3 = i6 + 1;
        } while (i3 < 16);
    }

    public static void n(byte[] bArr, byte[] bArr2, int i2, int i3) {
        while (true) {
            i3--;
            if (i3 < 0) {
                return;
            } else {
                bArr[i3] = (byte) (bArr[i3] ^ bArr2[i2 + i3]);
            }
        }
    }

    public static void o(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr3[0] = jArr[0] ^ jArr2[0];
        jArr3[1] = jArr2[1] ^ jArr[1];
    }
}
