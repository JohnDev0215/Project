package g.a.d.d;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public abstract class e {
    public static int A(int i2, int i3, int[] iArr, int i4) {
        long j = ((long) i2) & 4294967295L;
        long j2 = ((long) i3) & 4294967295L;
        int i5 = i4 + 0;
        long j3 = (j * j2) + (((long) iArr[i5]) & 4294967295L) + 0;
        iArr[i5] = (int) j3;
        int i6 = i4 + 1;
        long j4 = (j3 >>> 32) + j2 + (((long) iArr[i6]) & 4294967295L);
        iArr[i6] = (int) j4;
        long j5 = j4 >>> 32;
        int i7 = i4 + 2;
        long j6 = j5 + (((long) iArr[i7]) & 4294967295L);
        iArr[i7] = (int) j6;
        if ((j6 >>> 32) == 0) {
            return 0;
        }
        return m.u(6, iArr, i4, 3);
    }

    public static int B(int[] iArr, int[] iArr2, int[] iArr3) {
        int i2 = 0;
        long j = 4294967295L;
        long j2 = ((long) iArr2[0]) & 4294967295L;
        long j3 = ((long) iArr2[1]) & 4294967295L;
        long j4 = ((long) iArr2[2]) & 4294967295L;
        long j5 = ((long) iArr2[3]) & 4294967295L;
        long j6 = ((long) iArr2[4]) & 4294967295L;
        long j7 = ((long) iArr2[5]) & 4294967295L;
        long j8 = 0;
        while (i2 < 6) {
            long j9 = j7;
            long j10 = ((long) iArr[i2]) & j;
            int i3 = i2 + 0;
            long j11 = j2;
            long j12 = (j10 * j2) + (((long) iArr3[i3]) & j) + 0;
            iArr3[i3] = (int) j12;
            int i4 = i2 + 1;
            long j13 = (j12 >>> 32) + (j10 * j3) + (((long) iArr3[i4]) & 4294967295L);
            iArr3[i4] = (int) j13;
            int i5 = i2 + 2;
            long j14 = (j13 >>> 32) + (j10 * j4) + (((long) iArr3[i5]) & 4294967295L);
            iArr3[i5] = (int) j14;
            int i6 = i2 + 3;
            long j15 = (j14 >>> 32) + (j10 * j5) + (((long) iArr3[i6]) & 4294967295L);
            iArr3[i6] = (int) j15;
            int i7 = i2 + 4;
            long j16 = (j15 >>> 32) + (j10 * j6) + (((long) iArr3[i7]) & 4294967295L);
            iArr3[i7] = (int) j16;
            int i8 = i2 + 5;
            long j17 = (j16 >>> 32) + (j10 * j9) + (((long) iArr3[i8]) & 4294967295L);
            iArr3[i8] = (int) j17;
            int i9 = i2 + 6;
            long j18 = (j17 >>> 32) + j8 + (((long) iArr3[i9]) & 4294967295L);
            iArr3[i9] = (int) j18;
            j8 = j18 >>> 32;
            i2 = i4;
            j = 4294967295L;
            j7 = j9;
            j2 = j11;
            j3 = j3;
            j4 = j4;
        }
        return (int) j8;
    }

    public static void C(int[] iArr, int i2, int[] iArr2, int i3) {
        long j = ((long) iArr[i2 + 0]) & 4294967295L;
        int i4 = 0;
        int i5 = 12;
        int i6 = 5;
        while (true) {
            int i7 = i6 - 1;
            long j2 = ((long) iArr[i2 + i6]) & 4294967295L;
            long j3 = j2 * j2;
            int i8 = i5 - 1;
            iArr2[i3 + i8] = (i4 << 31) | ((int) (j3 >>> 33));
            i5 = i8 - 1;
            iArr2[i3 + i5] = (int) (j3 >>> 1);
            i4 = (int) j3;
            if (i7 <= 0) {
                long j4 = j * j;
                iArr2[i3 + 0] = (int) j4;
                long j5 = ((long) iArr[i2 + 1]) & 4294967295L;
                int i9 = i3 + 2;
                long j6 = ((long) iArr2[i9]) & 4294967295L;
                long j7 = ((((long) (i4 << 31)) & 4294967295L) | (j4 >>> 33)) + (j5 * j);
                int i10 = (int) j7;
                iArr2[i3 + 1] = (i10 << 1) | (((int) (j4 >>> 32)) & 1);
                int i11 = i10 >>> 31;
                long j8 = j6 + (j7 >>> 32);
                long j9 = ((long) iArr[i2 + 2]) & 4294967295L;
                int i12 = i3 + 3;
                long j10 = ((long) iArr2[i12]) & 4294967295L;
                int i13 = i3 + 4;
                long j11 = ((long) iArr2[i13]) & 4294967295L;
                long j12 = j8 + (j9 * j);
                int i14 = (int) j12;
                iArr2[i9] = (i14 << 1) | i11;
                int i15 = i14 >>> 31;
                long j13 = j10 + (j12 >>> 32) + (j9 * j5);
                long j14 = j11 + (j13 >>> 32);
                long j15 = ((long) iArr[i2 + 3]) & 4294967295L;
                int i16 = i3 + 5;
                long j16 = (((long) iArr2[i16]) & 4294967295L) + (j14 >>> 32);
                int i17 = i3 + 6;
                long j17 = (((long) iArr2[i17]) & 4294967295L) + (j16 >>> 32);
                long j18 = (j13 & 4294967295L) + (j15 * j);
                int i18 = (int) j18;
                iArr2[i12] = (i18 << 1) | i15;
                long j19 = (j14 & 4294967295L) + (j18 >>> 32) + (j15 * j5);
                long j20 = (j16 & 4294967295L) + (j19 >>> 32) + (j15 * j9);
                long j21 = j17 + (j20 >>> 32);
                long j22 = j20 & 4294967295L;
                long j23 = ((long) iArr[i2 + 4]) & 4294967295L;
                int i19 = i3 + 7;
                long j24 = (((long) iArr2[i19]) & 4294967295L) + (j21 >>> 32);
                long j25 = j21 & 4294967295L;
                int i20 = i3 + 8;
                long j26 = (((long) iArr2[i20]) & 4294967295L) + (j24 >>> 32);
                long j27 = (j19 & 4294967295L) + (j23 * j);
                int i21 = (int) j27;
                iArr2[i13] = (i18 >>> 31) | (i21 << 1);
                int i22 = i21 >>> 31;
                long j28 = j22 + (j27 >>> 32) + (j23 * j5);
                long j29 = j25 + (j28 >>> 32) + (j23 * j9);
                long j30 = (j24 & 4294967295L) + (j29 >>> 32) + (j23 * j15);
                long j31 = j26 + (j30 >>> 32);
                long j32 = j30 & 4294967295L;
                long j33 = ((long) iArr[i2 + 5]) & 4294967295L;
                int i23 = i3 + 9;
                long j34 = (((long) iArr2[i23]) & 4294967295L) + (j31 >>> 32);
                int i24 = i3 + 10;
                long j35 = (((long) iArr2[i24]) & 4294967295L) + (j34 >>> 32);
                long j36 = (j28 & 4294967295L) + (j * j33);
                int i25 = (int) j36;
                iArr2[i16] = (i25 << 1) | i22;
                long j37 = (j29 & 4294967295L) + (j36 >>> 32) + (j33 * j5);
                long j38 = j32 + (j37 >>> 32) + (j33 * j9);
                long j39 = (j31 & 4294967295L) + (j38 >>> 32) + (j33 * j15);
                long j40 = (j34 & 4294967295L) + (j39 >>> 32) + (j33 * j23);
                long j41 = j35 + (j40 >>> 32);
                int i26 = (int) j37;
                iArr2[i17] = (i25 >>> 31) | (i26 << 1);
                int i27 = (int) j38;
                iArr2[i19] = (i26 >>> 31) | (i27 << 1);
                int i28 = i27 >>> 31;
                int i29 = (int) j39;
                iArr2[i20] = i28 | (i29 << 1);
                int i30 = i29 >>> 31;
                int i31 = (int) j40;
                iArr2[i23] = i30 | (i31 << 1);
                int i32 = i31 >>> 31;
                int i33 = (int) j41;
                iArr2[i24] = i32 | (i33 << 1);
                int i34 = i33 >>> 31;
                int i35 = i3 + 11;
                iArr2[i35] = i34 | ((iArr2[i35] + ((int) (j41 >>> 32))) << 1);
                return;
            }
            i6 = i7;
        }
    }

    public static void D(int[] iArr, int[] iArr2) {
        long j = ((long) iArr[0]) & 4294967295L;
        int i2 = 12;
        int i3 = 0;
        int i4 = 5;
        while (true) {
            int i5 = i4 - 1;
            long j2 = ((long) iArr[i4]) & 4294967295L;
            long j3 = j2 * j2;
            int i6 = i2 - 1;
            iArr2[i6] = (i3 << 31) | ((int) (j3 >>> 33));
            i2 = i6 - 1;
            iArr2[i2] = (int) (j3 >>> 1);
            int i7 = (int) j3;
            if (i5 <= 0) {
                long j4 = j * j;
                long j5 = (((long) (i7 << 31)) & 4294967295L) | (j4 >>> 33);
                iArr2[0] = (int) j4;
                long j6 = ((long) iArr[1]) & 4294967295L;
                long j7 = ((long) iArr2[2]) & 4294967295L;
                long j8 = j5 + (j6 * j);
                int i8 = (int) j8;
                iArr2[1] = (i8 << 1) | (((int) (j4 >>> 32)) & 1);
                long j9 = j7 + (j8 >>> 32);
                long j10 = ((long) iArr[2]) & 4294967295L;
                long j11 = ((long) iArr2[3]) & 4294967295L;
                long j12 = ((long) iArr2[4]) & 4294967295L;
                long j13 = j9 + (j10 * j);
                int i9 = (int) j13;
                iArr2[2] = (i9 << 1) | (i8 >>> 31);
                long j14 = j11 + (j13 >>> 32) + (j10 * j6);
                long j15 = j12 + (j14 >>> 32);
                long j16 = ((long) iArr[3]) & 4294967295L;
                long j17 = (((long) iArr2[5]) & 4294967295L) + (j15 >>> 32);
                long j18 = j15 & 4294967295L;
                long j19 = (((long) iArr2[6]) & 4294967295L) + (j17 >>> 32);
                long j20 = (j14 & 4294967295L) + (j16 * j);
                int i10 = (int) j20;
                iArr2[3] = (i10 << 1) | (i9 >>> 31);
                long j21 = j18 + (j20 >>> 32) + (j16 * j6);
                long j22 = (j17 & 4294967295L) + (j21 >>> 32) + (j16 * j10);
                long j23 = j19 + (j22 >>> 32);
                long j24 = j22 & 4294967295L;
                long j25 = ((long) iArr[4]) & 4294967295L;
                long j26 = (((long) iArr2[7]) & 4294967295L) + (j23 >>> 32);
                long j27 = j23 & 4294967295L;
                long j28 = (((long) iArr2[8]) & 4294967295L) + (j26 >>> 32);
                long j29 = (j21 & 4294967295L) + (j25 * j);
                int i11 = (int) j29;
                iArr2[4] = (i10 >>> 31) | (i11 << 1);
                long j30 = j24 + (j29 >>> 32) + (j25 * j6);
                long j31 = j27 + (j30 >>> 32) + (j25 * j10);
                long j32 = (j26 & 4294967295L) + (j31 >>> 32) + (j25 * j16);
                long j33 = j28 + (j32 >>> 32);
                long j34 = ((long) iArr[5]) & 4294967295L;
                long j35 = (((long) iArr2[9]) & 4294967295L) + (j33 >>> 32);
                long j36 = j33 & 4294967295L;
                long j37 = (((long) iArr2[10]) & 4294967295L) + (j35 >>> 32);
                long j38 = (j30 & 4294967295L) + (j * j34);
                int i12 = (int) j38;
                iArr2[5] = (i11 >>> 31) | (i12 << 1);
                int i13 = i12 >>> 31;
                long j39 = (j31 & 4294967295L) + (j38 >>> 32) + (j6 * j34);
                long j40 = (j32 & 4294967295L) + (j39 >>> 32) + (j34 * j10);
                long j41 = j36 + (j40 >>> 32) + (j34 * j16);
                long j42 = (j35 & 4294967295L) + (j41 >>> 32) + (j34 * j25);
                long j43 = j37 + (j42 >>> 32);
                int i14 = (int) j39;
                iArr2[6] = i13 | (i14 << 1);
                int i15 = (int) j40;
                iArr2[7] = (i14 >>> 31) | (i15 << 1);
                int i16 = i15 >>> 31;
                int i17 = (int) j41;
                iArr2[8] = i16 | (i17 << 1);
                int i18 = i17 >>> 31;
                int i19 = (int) j42;
                iArr2[9] = i18 | (i19 << 1);
                int i20 = i19 >>> 31;
                int i21 = (int) j43;
                iArr2[10] = i20 | (i21 << 1);
                iArr2[11] = (i21 >>> 31) | ((iArr2[11] + ((int) (j43 >>> 32))) << 1);
                return;
            }
            i4 = i5;
            i3 = i7;
        }
    }

    public static int E(int[] iArr, int i2, int[] iArr2, int i3, int[] iArr3, int i4) {
        long j = ((((long) iArr[i2 + 0]) & 4294967295L) - (((long) iArr2[i3 + 0]) & 4294967295L)) + 0;
        iArr3[i4 + 0] = (int) j;
        long j2 = (j >> 32) + ((((long) iArr[i2 + 1]) & 4294967295L) - (((long) iArr2[i3 + 1]) & 4294967295L));
        iArr3[i4 + 1] = (int) j2;
        long j3 = (j2 >> 32) + ((((long) iArr[i2 + 2]) & 4294967295L) - (((long) iArr2[i3 + 2]) & 4294967295L));
        iArr3[i4 + 2] = (int) j3;
        long j4 = (j3 >> 32) + ((((long) iArr[i2 + 3]) & 4294967295L) - (((long) iArr2[i3 + 3]) & 4294967295L));
        iArr3[i4 + 3] = (int) j4;
        long j5 = (j4 >> 32) + ((((long) iArr[i2 + 4]) & 4294967295L) - (((long) iArr2[i3 + 4]) & 4294967295L));
        iArr3[i4 + 4] = (int) j5;
        long j6 = (j5 >> 32) + ((((long) iArr[i2 + 5]) & 4294967295L) - (((long) iArr2[i3 + 5]) & 4294967295L));
        iArr3[i4 + 5] = (int) j6;
        return (int) (j6 >> 32);
    }

    public static int F(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = ((((long) iArr[0]) & 4294967295L) - (((long) iArr2[0]) & 4294967295L)) + 0;
        iArr3[0] = (int) j;
        long j2 = (j >> 32) + ((((long) iArr[1]) & 4294967295L) - (((long) iArr2[1]) & 4294967295L));
        iArr3[1] = (int) j2;
        long j3 = (j2 >> 32) + ((((long) iArr[2]) & 4294967295L) - (((long) iArr2[2]) & 4294967295L));
        iArr3[2] = (int) j3;
        long j4 = (j3 >> 32) + ((((long) iArr[3]) & 4294967295L) - (((long) iArr2[3]) & 4294967295L));
        iArr3[3] = (int) j4;
        long j5 = (j4 >> 32) + ((((long) iArr[4]) & 4294967295L) - (((long) iArr2[4]) & 4294967295L));
        iArr3[4] = (int) j5;
        long j6 = (j5 >> 32) + ((((long) iArr[5]) & 4294967295L) - (((long) iArr2[5]) & 4294967295L));
        iArr3[5] = (int) j6;
        return (int) (j6 >> 32);
    }

    public static int G(int[] iArr, int[] iArr2) {
        long j = ((((long) iArr2[0]) & 4294967295L) - (((long) iArr[0]) & 4294967295L)) + 0;
        iArr2[0] = (int) j;
        long j2 = (j >> 32) + ((((long) iArr2[1]) & 4294967295L) - (((long) iArr[1]) & 4294967295L));
        iArr2[1] = (int) j2;
        long j3 = (j2 >> 32) + ((((long) iArr2[2]) & 4294967295L) - (((long) iArr[2]) & 4294967295L));
        iArr2[2] = (int) j3;
        long j4 = (j3 >> 32) + ((((long) iArr2[3]) & 4294967295L) - (((long) iArr[3]) & 4294967295L));
        iArr2[3] = (int) j4;
        long j5 = (j4 >> 32) + ((((long) iArr2[4]) & 4294967295L) - (((long) iArr[4]) & 4294967295L));
        iArr2[4] = (int) j5;
        long j6 = (j5 >> 32) + ((((long) iArr2[5]) & 4294967295L) - (4294967295L & ((long) iArr[5])));
        iArr2[5] = (int) j6;
        return (int) (j6 >> 32);
    }

    public static BigInteger H(int[] iArr) {
        byte[] bArr = new byte[24];
        for (int i2 = 0; i2 < 6; i2++) {
            int i3 = iArr[i2];
            if (i3 != 0) {
                g.a.f.f.d(i3, bArr, (5 - i2) << 2);
            }
        }
        return new BigInteger(1, bArr);
    }

    public static BigInteger I(long[] jArr) {
        byte[] bArr = new byte[24];
        for (int i2 = 0; i2 < 3; i2++) {
            long j = jArr[i2];
            if (j != 0) {
                g.a.f.f.q(j, bArr, (2 - i2) << 3);
            }
        }
        return new BigInteger(1, bArr);
    }

    public static void J(int[] iArr) {
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        iArr[3] = 0;
        iArr[4] = 0;
        iArr[5] = 0;
    }

    public static int a(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = (((long) iArr[0]) & 4294967295L) + (((long) iArr2[0]) & 4294967295L) + 0;
        iArr3[0] = (int) j;
        long j2 = (j >>> 32) + (((long) iArr[1]) & 4294967295L) + (((long) iArr2[1]) & 4294967295L);
        iArr3[1] = (int) j2;
        long j3 = (j2 >>> 32) + (((long) iArr[2]) & 4294967295L) + (((long) iArr2[2]) & 4294967295L);
        iArr3[2] = (int) j3;
        long j4 = (j3 >>> 32) + (((long) iArr[3]) & 4294967295L) + (((long) iArr2[3]) & 4294967295L);
        iArr3[3] = (int) j4;
        long j5 = (j4 >>> 32) + (((long) iArr[4]) & 4294967295L) + (((long) iArr2[4]) & 4294967295L);
        iArr3[4] = (int) j5;
        long j6 = (j5 >>> 32) + (((long) iArr[5]) & 4294967295L) + (((long) iArr2[5]) & 4294967295L);
        iArr3[5] = (int) j6;
        return (int) (j6 >>> 32);
    }

    public static int b(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = (((long) iArr[0]) & 4294967295L) + (((long) iArr2[0]) & 4294967295L) + (((long) iArr3[0]) & 4294967295L) + 0;
        iArr3[0] = (int) j;
        long j2 = (j >>> 32) + (((long) iArr[1]) & 4294967295L) + (((long) iArr2[1]) & 4294967295L) + (((long) iArr3[1]) & 4294967295L);
        iArr3[1] = (int) j2;
        long j3 = (j2 >>> 32) + (((long) iArr[2]) & 4294967295L) + (((long) iArr2[2]) & 4294967295L) + (((long) iArr3[2]) & 4294967295L);
        iArr3[2] = (int) j3;
        long j4 = (j3 >>> 32) + (((long) iArr[3]) & 4294967295L) + (((long) iArr2[3]) & 4294967295L) + (((long) iArr3[3]) & 4294967295L);
        iArr3[3] = (int) j4;
        long j5 = (j4 >>> 32) + (((long) iArr[4]) & 4294967295L) + (((long) iArr2[4]) & 4294967295L) + (((long) iArr3[4]) & 4294967295L);
        iArr3[4] = (int) j5;
        long j6 = (j5 >>> 32) + (((long) iArr[5]) & 4294967295L) + (((long) iArr2[5]) & 4294967295L) + (((long) iArr3[5]) & 4294967295L);
        iArr3[5] = (int) j6;
        return (int) (j6 >>> 32);
    }

    public static int c(int[] iArr, int i2, int[] iArr2, int i3, int i4) {
        int i5 = i3 + 0;
        long j = (((long) i4) & 4294967295L) + (((long) iArr[i2 + 0]) & 4294967295L) + (((long) iArr2[i5]) & 4294967295L);
        iArr2[i5] = (int) j;
        int i6 = i3 + 1;
        long j2 = (j >>> 32) + (((long) iArr[i2 + 1]) & 4294967295L) + (((long) iArr2[i6]) & 4294967295L);
        iArr2[i6] = (int) j2;
        int i7 = i3 + 2;
        long j3 = (j2 >>> 32) + (((long) iArr[i2 + 2]) & 4294967295L) + (((long) iArr2[i7]) & 4294967295L);
        iArr2[i7] = (int) j3;
        int i8 = i3 + 3;
        long j4 = (j3 >>> 32) + (((long) iArr[i2 + 3]) & 4294967295L) + (((long) iArr2[i8]) & 4294967295L);
        iArr2[i8] = (int) j4;
        int i9 = i3 + 4;
        long j5 = (j4 >>> 32) + (((long) iArr[i2 + 4]) & 4294967295L) + (((long) iArr2[i9]) & 4294967295L);
        iArr2[i9] = (int) j5;
        int i10 = i3 + 5;
        long j6 = (j5 >>> 32) + (((long) iArr[i2 + 5]) & 4294967295L) + (4294967295L & ((long) iArr2[i10]));
        iArr2[i10] = (int) j6;
        return (int) (j6 >>> 32);
    }

    public static int d(int[] iArr, int i2, int[] iArr2, int i3) {
        int i4 = i2 + 0;
        int i5 = i3 + 0;
        long j = (((long) iArr[i4]) & 4294967295L) + (((long) iArr2[i5]) & 4294967295L) + 0;
        int i6 = (int) j;
        iArr[i4] = i6;
        iArr2[i5] = i6;
        int i7 = i2 + 1;
        int i8 = i3 + 1;
        long j2 = (j >>> 32) + (((long) iArr[i7]) & 4294967295L) + (((long) iArr2[i8]) & 4294967295L);
        int i9 = (int) j2;
        iArr[i7] = i9;
        iArr2[i8] = i9;
        int i10 = i2 + 2;
        int i11 = i3 + 2;
        long j3 = (j2 >>> 32) + (((long) iArr[i10]) & 4294967295L) + (((long) iArr2[i11]) & 4294967295L);
        int i12 = (int) j3;
        iArr[i10] = i12;
        iArr2[i11] = i12;
        int i13 = i2 + 3;
        int i14 = i3 + 3;
        long j4 = (j3 >>> 32) + (((long) iArr[i13]) & 4294967295L) + (((long) iArr2[i14]) & 4294967295L);
        int i15 = (int) j4;
        iArr[i13] = i15;
        iArr2[i14] = i15;
        int i16 = i2 + 4;
        int i17 = i3 + 4;
        long j5 = (j4 >>> 32) + (((long) iArr[i16]) & 4294967295L) + (((long) iArr2[i17]) & 4294967295L);
        int i18 = (int) j5;
        iArr[i16] = i18;
        iArr2[i17] = i18;
        int i19 = i2 + 5;
        int i20 = i3 + 5;
        long j6 = (j5 >>> 32) + (((long) iArr[i19]) & 4294967295L) + (4294967295L & ((long) iArr2[i20]));
        int i21 = (int) j6;
        iArr[i19] = i21;
        iArr2[i20] = i21;
        return (int) (j6 >>> 32);
    }

    public static void e(int[] iArr, int i2, int[] iArr2, int i3) {
        iArr2[i3 + 0] = iArr[i2 + 0];
        iArr2[i3 + 1] = iArr[i2 + 1];
        iArr2[i3 + 2] = iArr[i2 + 2];
        iArr2[i3 + 3] = iArr[i2 + 3];
        iArr2[i3 + 4] = iArr[i2 + 4];
        iArr2[i3 + 5] = iArr[i2 + 5];
    }

    public static void f(long[] jArr, int i2, long[] jArr2, int i3) {
        jArr2[i3 + 0] = jArr[i2 + 0];
        jArr2[i3 + 1] = jArr[i2 + 1];
        jArr2[i3 + 2] = jArr[i2 + 2];
    }

    public static int[] g() {
        return new int[6];
    }

    public static long[] h() {
        return new long[3];
    }

    public static int[] i() {
        return new int[12];
    }

    public static long[] j() {
        return new long[6];
    }

    public static boolean k(int[] iArr, int i2, int[] iArr2, int i3, int[] iArr3, int i4) {
        boolean zQ = q(iArr, i2, iArr2, i3);
        if (zQ) {
            E(iArr, i2, iArr2, i3, iArr3, i4);
        } else {
            E(iArr2, i3, iArr, i2, iArr3, i4);
        }
        return zQ;
    }

    public static boolean l(int[] iArr, int[] iArr2) {
        for (int i2 = 5; i2 >= 0; i2--) {
            if (iArr[i2] != iArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public static boolean m(long[] jArr, long[] jArr2) {
        for (int i2 = 2; i2 >= 0; i2--) {
            if (jArr[i2] != jArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public static int[] n(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 192) {
            throw new IllegalArgumentException();
        }
        int[] iArrG = g();
        int i2 = 0;
        while (bigInteger.signum() != 0) {
            iArrG[i2] = bigInteger.intValue();
            bigInteger = bigInteger.shiftRight(32);
            i2++;
        }
        return iArrG;
    }

    public static long[] o(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 192) {
            throw new IllegalArgumentException();
        }
        long[] jArrH = h();
        int i2 = 0;
        while (bigInteger.signum() != 0) {
            jArrH[i2] = bigInteger.longValue();
            bigInteger = bigInteger.shiftRight(64);
            i2++;
        }
        return jArrH;
    }

    public static int p(int[] iArr, int i2) {
        int i3;
        if (i2 == 0) {
            i3 = iArr[0];
        } else {
            int i4 = i2 >> 5;
            if (i4 < 0 || i4 >= 6) {
                return 0;
            }
            i3 = iArr[i4] >>> (i2 & 31);
        }
        return i3 & 1;
    }

    public static boolean q(int[] iArr, int i2, int[] iArr2, int i3) {
        for (int i4 = 5; i4 >= 0; i4--) {
            int i5 = iArr[i2 + i4] ^ Integer.MIN_VALUE;
            int i6 = Integer.MIN_VALUE ^ iArr2[i3 + i4];
            if (i5 < i6) {
                return false;
            }
            if (i5 > i6) {
                return true;
            }
        }
        return true;
    }

    public static boolean r(int[] iArr, int[] iArr2) {
        for (int i2 = 5; i2 >= 0; i2--) {
            int i3 = iArr[i2] ^ Integer.MIN_VALUE;
            int i4 = Integer.MIN_VALUE ^ iArr2[i2];
            if (i3 < i4) {
                return false;
            }
            if (i3 > i4) {
                return true;
            }
        }
        return true;
    }

    public static boolean s(int[] iArr) {
        if (iArr[0] != 1) {
            return false;
        }
        for (int i2 = 1; i2 < 6; i2++) {
            if (iArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean t(long[] jArr) {
        if (jArr[0] != 1) {
            return false;
        }
        for (int i2 = 1; i2 < 3; i2++) {
            if (jArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean u(int[] iArr) {
        for (int i2 = 0; i2 < 6; i2++) {
            if (iArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean v(long[] jArr) {
        for (int i2 = 0; i2 < 3; i2++) {
            if (jArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static void w(int[] iArr, int i2, int[] iArr2, int i3, int[] iArr3, int i4) {
        long j = ((long) iArr2[i3 + 0]) & 4294967295L;
        long j2 = ((long) iArr2[i3 + 1]) & 4294967295L;
        long j3 = ((long) iArr2[i3 + 2]) & 4294967295L;
        long j4 = ((long) iArr2[i3 + 3]) & 4294967295L;
        long j5 = ((long) iArr2[i3 + 4]) & 4294967295L;
        long j6 = ((long) iArr2[i3 + 5]) & 4294967295L;
        long j7 = ((long) iArr[i2 + 0]) & 4294967295L;
        long j8 = (j7 * j) + 0;
        iArr3[i4 + 0] = (int) j8;
        long j9 = (j8 >>> 32) + (j7 * j2);
        iArr3[i4 + 1] = (int) j9;
        long j10 = (j9 >>> 32) + (j7 * j3);
        iArr3[i4 + 2] = (int) j10;
        long j11 = (j10 >>> 32) + (j7 * j4);
        iArr3[i4 + 3] = (int) j11;
        long j12 = (j11 >>> 32) + (j7 * j5);
        iArr3[i4 + 4] = (int) j12;
        long j13 = (j12 >>> 32) + (j7 * j6);
        iArr3[i4 + 5] = (int) j13;
        iArr3[i4 + 6] = (int) (j13 >>> 32);
        int i5 = 1;
        int i6 = i4;
        int i7 = 1;
        while (i7 < 6) {
            i6 += i5;
            long j14 = ((long) iArr[i2 + i7]) & 4294967295L;
            int i8 = i6 + 0;
            long j15 = (j14 * j) + (((long) iArr3[i8]) & 4294967295L) + 0;
            iArr3[i8] = (int) j15;
            int i9 = i6 + 1;
            long j16 = j6;
            long j17 = (j15 >>> 32) + (j14 * j2) + (((long) iArr3[i9]) & 4294967295L);
            iArr3[i9] = (int) j17;
            int i10 = i6 + 2;
            long j18 = (j17 >>> 32) + (j14 * j3) + (((long) iArr3[i10]) & 4294967295L);
            iArr3[i10] = (int) j18;
            int i11 = i6 + 3;
            long j19 = (j18 >>> 32) + (j14 * j4) + (((long) iArr3[i11]) & 4294967295L);
            iArr3[i11] = (int) j19;
            int i12 = i6 + 4;
            long j20 = (j19 >>> 32) + (j14 * j5) + (((long) iArr3[i12]) & 4294967295L);
            iArr3[i12] = (int) j20;
            int i13 = i6 + 5;
            long j21 = (j20 >>> 32) + (j14 * j16) + (((long) iArr3[i13]) & 4294967295L);
            iArr3[i13] = (int) j21;
            iArr3[i6 + 6] = (int) (j21 >>> 32);
            i7++;
            j3 = j3;
            j6 = j16;
            i5 = 1;
        }
    }

    public static void x(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = ((long) iArr2[0]) & 4294967295L;
        long j2 = ((long) iArr2[1]) & 4294967295L;
        long j3 = ((long) iArr2[2]) & 4294967295L;
        long j4 = ((long) iArr2[3]) & 4294967295L;
        long j5 = ((long) iArr2[4]) & 4294967295L;
        long j6 = ((long) iArr2[5]) & 4294967295L;
        long j7 = ((long) iArr[0]) & 4294967295L;
        long j8 = (j7 * j) + 0;
        iArr3[0] = (int) j8;
        long j9 = (j8 >>> 32) + (j7 * j2);
        iArr3[1] = (int) j9;
        long j10 = (j9 >>> 32) + (j7 * j3);
        iArr3[2] = (int) j10;
        long j11 = (j10 >>> 32) + (j7 * j4);
        iArr3[3] = (int) j11;
        long j12 = (j11 >>> 32) + (j7 * j5);
        iArr3[4] = (int) j12;
        long j13 = (j12 >>> 32) + (j7 * j6);
        iArr3[5] = (int) j13;
        iArr3[6] = (int) (j13 >>> 32);
        int i2 = 1;
        for (int i3 = 6; i2 < i3; i3 = 6) {
            long j14 = ((long) iArr[i2]) & 4294967295L;
            int i4 = i2 + 0;
            long j15 = (j14 * j) + (((long) iArr3[i4]) & 4294967295L) + 0;
            iArr3[i4] = (int) j15;
            int i5 = i2 + 1;
            long j16 = j2;
            long j17 = (j15 >>> 32) + (j14 * j2) + (((long) iArr3[i5]) & 4294967295L);
            iArr3[i5] = (int) j17;
            int i6 = i2 + 2;
            long j18 = j6;
            long j19 = (j17 >>> 32) + (j14 * j3) + (((long) iArr3[i6]) & 4294967295L);
            iArr3[i6] = (int) j19;
            int i7 = i2 + 3;
            long j20 = (j19 >>> 32) + (j14 * j4) + (((long) iArr3[i7]) & 4294967295L);
            iArr3[i7] = (int) j20;
            int i8 = i2 + 4;
            long j21 = (j20 >>> 32) + (j14 * j5) + (((long) iArr3[i8]) & 4294967295L);
            iArr3[i8] = (int) j21;
            int i9 = i2 + 5;
            long j22 = (j21 >>> 32) + (j14 * j18) + (((long) iArr3[i9]) & 4294967295L);
            iArr3[i9] = (int) j22;
            iArr3[i2 + 6] = (int) (j22 >>> 32);
            i2 = i5;
            j = j;
            j2 = j16;
            j6 = j18;
        }
    }

    public static long y(int i2, int[] iArr, int i3, int[] iArr2, int i4, int[] iArr3, int i5) {
        long j = ((long) i2) & 4294967295L;
        long j2 = ((long) iArr[i3 + 0]) & 4294967295L;
        long j3 = (j * j2) + (((long) iArr2[i4 + 0]) & 4294967295L) + 0;
        iArr3[i5 + 0] = (int) j3;
        long j4 = ((long) iArr[i3 + 1]) & 4294967295L;
        long j5 = (j3 >>> 32) + (j * j4) + j2 + (((long) iArr2[i4 + 1]) & 4294967295L);
        iArr3[i5 + 1] = (int) j5;
        long j6 = j5 >>> 32;
        long j7 = ((long) iArr[i3 + 2]) & 4294967295L;
        long j8 = j6 + (j * j7) + j4 + (((long) iArr2[i4 + 2]) & 4294967295L);
        iArr3[i5 + 2] = (int) j8;
        long j9 = ((long) iArr[i3 + 3]) & 4294967295L;
        long j10 = (j8 >>> 32) + (j * j9) + j7 + (((long) iArr2[i4 + 3]) & 4294967295L);
        iArr3[i5 + 3] = (int) j10;
        long j11 = ((long) iArr[i3 + 4]) & 4294967295L;
        long j12 = (j10 >>> 32) + (j * j11) + j9 + (((long) iArr2[i4 + 4]) & 4294967295L);
        iArr3[i5 + 4] = (int) j12;
        long j13 = ((long) iArr[i3 + 5]) & 4294967295L;
        long j14 = (j12 >>> 32) + (j * j13) + j11 + (4294967295L & ((long) iArr2[i4 + 5]));
        iArr3[i5 + 5] = (int) j14;
        return (j14 >>> 32) + j13;
    }

    public static int z(int i2, long j, int[] iArr, int i3) {
        long j2 = ((long) i2) & 4294967295L;
        long j3 = j & 4294967295L;
        int i4 = i3 + 0;
        long j4 = (j2 * j3) + (((long) iArr[i4]) & 4294967295L) + 0;
        iArr[i4] = (int) j4;
        long j5 = j >>> 32;
        long j6 = (j2 * j5) + j3;
        int i5 = i3 + 1;
        long j7 = (j4 >>> 32) + j6 + (((long) iArr[i5]) & 4294967295L);
        iArr[i5] = (int) j7;
        int i6 = i3 + 2;
        long j8 = (j7 >>> 32) + j5 + (((long) iArr[i6]) & 4294967295L);
        iArr[i6] = (int) j8;
        int i7 = i3 + 3;
        long j9 = (j8 >>> 32) + (4294967295L & ((long) iArr[i7]));
        iArr[i7] = (int) j9;
        if ((j9 >>> 32) == 0) {
            return 0;
        }
        return m.u(6, iArr, i3, 4);
    }
}
