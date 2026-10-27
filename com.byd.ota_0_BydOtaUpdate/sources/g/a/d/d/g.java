package g.a.d.d;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public abstract class g {
    public static int A(int i2, long j, int[] iArr, int i3) {
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
        return m.u(8, iArr, i3, 4);
    }

    public static int B(int i2, int i3, int[] iArr, int i4) {
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
        return m.u(8, iArr, i4, 3);
    }

    public static int C(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = 4294967295L;
        long j2 = ((long) iArr2[0]) & 4294967295L;
        long j3 = ((long) iArr2[1]) & 4294967295L;
        long j4 = ((long) iArr2[2]) & 4294967295L;
        long j5 = ((long) iArr2[3]) & 4294967295L;
        long j6 = ((long) iArr2[4]) & 4294967295L;
        long j7 = ((long) iArr2[5]) & 4294967295L;
        long j8 = ((long) iArr2[6]) & 4294967295L;
        long j9 = ((long) iArr2[7]) & 4294967295L;
        long j10 = 0;
        int i2 = 0;
        while (i2 < 8) {
            long j11 = j9;
            long j12 = ((long) iArr[i2]) & j;
            int i3 = i2 + 0;
            long j13 = j7;
            long j14 = (j12 * j2) + (((long) iArr3[i3]) & j) + 0;
            iArr3[i3] = (int) j14;
            int i4 = i2 + 1;
            long j15 = j3;
            long j16 = (j14 >>> 32) + (j12 * j3) + (((long) iArr3[i4]) & j);
            iArr3[i4] = (int) j16;
            int i5 = i2 + 2;
            long j17 = (j16 >>> 32) + (j12 * j4) + (((long) iArr3[i5]) & j);
            iArr3[i5] = (int) j17;
            int i6 = i2 + 3;
            long j18 = (j17 >>> 32) + (j12 * j5) + (((long) iArr3[i6]) & j);
            iArr3[i6] = (int) j18;
            int i7 = i2 + 4;
            long j19 = (j18 >>> 32) + (j12 * j6) + (((long) iArr3[i7]) & j);
            iArr3[i7] = (int) j19;
            int i8 = i2 + 5;
            long j20 = (j19 >>> 32) + (j12 * j13) + (((long) iArr3[i8]) & j);
            iArr3[i8] = (int) j20;
            int i9 = i2 + 6;
            long j21 = (j20 >>> 32) + (j12 * j8) + (((long) iArr3[i9]) & j);
            iArr3[i9] = (int) j21;
            int i10 = i2 + 7;
            long j22 = (j21 >>> 32) + (j12 * j11) + (((long) iArr3[i10]) & j);
            iArr3[i10] = (int) j22;
            int i11 = i2 + 8;
            long j23 = (j22 >>> 32) + j10 + (((long) iArr3[i11]) & j);
            iArr3[i11] = (int) j23;
            j10 = j23 >>> 32;
            i2 = i4;
            j9 = j11;
            j7 = j13;
            j3 = j15;
            j = 4294967295L;
        }
        return (int) j10;
    }

    public static int D(int i2, int[] iArr, int[] iArr2) {
        long j = ((long) i2) & 4294967295L;
        long j2 = ((((long) iArr2[0]) & 4294967295L) * j) + (((long) iArr[0]) & 4294967295L) + 0;
        iArr2[0] = (int) j2;
        long j3 = (j2 >>> 32) + ((((long) iArr2[1]) & 4294967295L) * j) + (((long) iArr[1]) & 4294967295L);
        iArr2[1] = (int) j3;
        long j4 = (j3 >>> 32) + ((((long) iArr2[2]) & 4294967295L) * j) + (((long) iArr[2]) & 4294967295L);
        iArr2[2] = (int) j4;
        long j5 = (j4 >>> 32) + ((((long) iArr2[3]) & 4294967295L) * j) + (((long) iArr[3]) & 4294967295L);
        iArr2[3] = (int) j5;
        long j6 = (j5 >>> 32) + ((((long) iArr2[4]) & 4294967295L) * j) + (((long) iArr[4]) & 4294967295L);
        iArr2[4] = (int) j6;
        long j7 = (j6 >>> 32) + ((((long) iArr2[5]) & 4294967295L) * j) + (((long) iArr[5]) & 4294967295L);
        iArr2[5] = (int) j7;
        long j8 = (j7 >>> 32) + ((((long) iArr2[6]) & 4294967295L) * j) + (((long) iArr[6]) & 4294967295L);
        iArr2[6] = (int) j8;
        long j9 = (j8 >>> 32) + (j * (((long) iArr2[7]) & 4294967295L)) + (4294967295L & ((long) iArr[7]));
        iArr2[7] = (int) j9;
        return (int) (j9 >>> 32);
    }

    public static void E(int[] iArr, int i2, int[] iArr2, int i3) {
        long j = ((long) iArr[i2 + 0]) & 4294967295L;
        int i4 = 0;
        int i5 = 16;
        int i6 = 7;
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
                long j36 = (j28 & 4294967295L) + (j33 * j);
                int i25 = (int) j36;
                iArr2[i16] = i22 | (i25 << 1);
                int i26 = i25 >>> 31;
                long j37 = (j29 & 4294967295L) + (j36 >>> 32) + (j33 * j5);
                long j38 = j32 + (j37 >>> 32) + (j33 * j9);
                long j39 = (j31 & 4294967295L) + (j38 >>> 32) + (j33 * j15);
                long j40 = (j34 & 4294967295L) + (j39 >>> 32) + (j33 * j23);
                long j41 = j39 & 4294967295L;
                long j42 = j35 + (j40 >>> 32);
                long j43 = j40 & 4294967295L;
                long j44 = ((long) iArr[i2 + 6]) & 4294967295L;
                int i27 = i3 + 11;
                long j45 = (((long) iArr2[i27]) & 4294967295L) + (j42 >>> 32);
                long j46 = j42 & 4294967295L;
                int i28 = i3 + 12;
                long j47 = (((long) iArr2[i28]) & 4294967295L) + (j45 >>> 32);
                long j48 = (j37 & 4294967295L) + (j44 * j);
                int i29 = (int) j48;
                iArr2[i17] = i26 | (i29 << 1);
                int i30 = i29 >>> 31;
                long j49 = (j38 & 4294967295L) + (j48 >>> 32) + (j44 * j5);
                long j50 = j41 + (j49 >>> 32) + (j44 * j9);
                long j51 = j43 + (j50 >>> 32) + (j44 * j15);
                long j52 = j50 & 4294967295L;
                long j53 = j46 + (j51 >>> 32) + (j44 * j23);
                long j54 = (j45 & 4294967295L) + (j53 >>> 32) + (j44 * j33);
                long j55 = j47 + (j54 >>> 32);
                long j56 = j54 & 4294967295L;
                long j57 = ((long) iArr[i2 + 7]) & 4294967295L;
                int i31 = i3 + 13;
                long j58 = (((long) iArr2[i31]) & 4294967295L) + (j55 >>> 32);
                long j59 = j55 & 4294967295L;
                int i32 = i3 + 14;
                long j60 = (((long) iArr2[i32]) & 4294967295L) + (j58 >>> 32);
                long j61 = (j49 & 4294967295L) + (j * j57);
                int i33 = (int) j61;
                iArr2[i19] = (i33 << 1) | i30;
                long j62 = j52 + (j61 >>> 32) + (j57 * j5);
                long j63 = (j51 & 4294967295L) + (j62 >>> 32) + (j57 * j9);
                long j64 = (j53 & 4294967295L) + (j63 >>> 32) + (j57 * j15);
                long j65 = j56 + (j64 >>> 32) + (j57 * j23);
                long j66 = j59 + (j65 >>> 32) + (j57 * j33);
                long j67 = (j58 & 4294967295L) + (j66 >>> 32) + (j57 * j44);
                long j68 = j60 + (j67 >>> 32);
                int i34 = (int) j62;
                iArr2[i20] = (i33 >>> 31) | (i34 << 1);
                int i35 = i34 >>> 31;
                int i36 = (int) j63;
                iArr2[i23] = i35 | (i36 << 1);
                int i37 = i36 >>> 31;
                int i38 = (int) j64;
                iArr2[i24] = i37 | (i38 << 1);
                int i39 = i38 >>> 31;
                int i40 = (int) j65;
                iArr2[i27] = i39 | (i40 << 1);
                int i41 = i40 >>> 31;
                int i42 = (int) j66;
                iArr2[i28] = i41 | (i42 << 1);
                int i43 = i42 >>> 31;
                int i44 = (int) j67;
                iArr2[i31] = i43 | (i44 << 1);
                int i45 = i44 >>> 31;
                int i46 = (int) j68;
                iArr2[i32] = i45 | (i46 << 1);
                int i47 = i46 >>> 31;
                int i48 = i3 + 15;
                iArr2[i48] = i47 | ((iArr2[i48] + ((int) (j68 >>> 32))) << 1);
                return;
            }
            i6 = i7;
        }
    }

    public static void F(int[] iArr, int[] iArr2) {
        long j = ((long) iArr[0]) & 4294967295L;
        int i2 = 16;
        int i3 = 0;
        int i4 = 7;
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
                int i12 = i11 >>> 31;
                long j30 = j24 + (j29 >>> 32) + (j25 * j6);
                long j31 = j27 + (j30 >>> 32) + (j25 * j10);
                long j32 = (j26 & 4294967295L) + (j31 >>> 32) + (j25 * j16);
                long j33 = j28 + (j32 >>> 32);
                long j34 = j32 & 4294967295L;
                long j35 = ((long) iArr[5]) & 4294967295L;
                long j36 = (((long) iArr2[9]) & 4294967295L) + (j33 >>> 32);
                long j37 = j33 & 4294967295L;
                long j38 = (((long) iArr2[10]) & 4294967295L) + (j36 >>> 32);
                long j39 = (j30 & 4294967295L) + (j35 * j);
                int i13 = (int) j39;
                iArr2[5] = (i13 << 1) | i12;
                long j40 = (j31 & 4294967295L) + (j39 >>> 32) + (j35 * j6);
                long j41 = j34 + (j40 >>> 32) + (j35 * j10);
                long j42 = j37 + (j41 >>> 32) + (j35 * j16);
                long j43 = (j36 & 4294967295L) + (j42 >>> 32) + (j35 * j25);
                long j44 = j38 + (j43 >>> 32);
                long j45 = ((long) iArr[6]) & 4294967295L;
                long j46 = (((long) iArr2[11]) & 4294967295L) + (j44 >>> 32);
                long j47 = j44 & 4294967295L;
                long j48 = (((long) iArr2[12]) & 4294967295L) + (j46 >>> 32);
                long j49 = (j40 & 4294967295L) + (j45 * j);
                int i14 = (int) j49;
                iArr2[6] = (i14 << 1) | (i13 >>> 31);
                long j50 = (j41 & 4294967295L) + (j49 >>> 32) + (j45 * j6);
                long j51 = (j42 & 4294967295L) + (j50 >>> 32) + (j45 * j10);
                long j52 = (j43 & 4294967295L) + (j51 >>> 32) + (j45 * j16);
                long j53 = j47 + (j52 >>> 32) + (j45 * j25);
                long j54 = (j46 & 4294967295L) + (j53 >>> 32) + (j45 * j35);
                long j55 = j48 + (j54 >>> 32);
                long j56 = j54 & 4294967295L;
                long j57 = ((long) iArr[7]) & 4294967295L;
                long j58 = (((long) iArr2[13]) & 4294967295L) + (j55 >>> 32);
                long j59 = j55 & 4294967295L;
                long j60 = (((long) iArr2[14]) & 4294967295L) + (j58 >>> 32);
                long j61 = (j50 & 4294967295L) + (j * j57);
                int i15 = (int) j61;
                iArr2[7] = (i14 >>> 31) | (i15 << 1);
                int i16 = i15 >>> 31;
                long j62 = (j51 & 4294967295L) + (j61 >>> 32) + (j6 * j57);
                long j63 = (j52 & 4294967295L) + (j62 >>> 32) + (j57 * j10);
                long j64 = (j53 & 4294967295L) + (j63 >>> 32) + (j57 * j16);
                long j65 = j56 + (j64 >>> 32) + (j57 * j25);
                long j66 = j59 + (j65 >>> 32) + (j57 * j35);
                long j67 = (j58 & 4294967295L) + (j66 >>> 32) + (j57 * j45);
                long j68 = j60 + (j67 >>> 32);
                int i17 = (int) j62;
                iArr2[8] = i16 | (i17 << 1);
                int i18 = i17 >>> 31;
                int i19 = (int) j63;
                iArr2[9] = i18 | (i19 << 1);
                int i20 = i19 >>> 31;
                int i21 = (int) j64;
                iArr2[10] = i20 | (i21 << 1);
                int i22 = i21 >>> 31;
                int i23 = (int) j65;
                iArr2[11] = i22 | (i23 << 1);
                int i24 = i23 >>> 31;
                int i25 = (int) j66;
                iArr2[12] = i24 | (i25 << 1);
                int i26 = i25 >>> 31;
                int i27 = (int) j67;
                iArr2[13] = i26 | (i27 << 1);
                int i28 = i27 >>> 31;
                int i29 = (int) j68;
                iArr2[14] = i28 | (i29 << 1);
                iArr2[15] = (i29 >>> 31) | ((iArr2[15] + ((int) (j68 >>> 32))) << 1);
                return;
            }
            i4 = i5;
            i3 = i7;
        }
    }

    public static int G(int[] iArr, int i2, int[] iArr2, int i3, int[] iArr3, int i4) {
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
        long j7 = (j6 >> 32) + ((((long) iArr[i2 + 6]) & 4294967295L) - (((long) iArr2[i3 + 6]) & 4294967295L));
        iArr3[i4 + 6] = (int) j7;
        long j8 = (j7 >> 32) + ((((long) iArr[i2 + 7]) & 4294967295L) - (((long) iArr2[i3 + 7]) & 4294967295L));
        iArr3[i4 + 7] = (int) j8;
        return (int) (j8 >> 32);
    }

    public static int H(int[] iArr, int[] iArr2, int[] iArr3) {
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
        long j7 = (j6 >> 32) + ((((long) iArr[6]) & 4294967295L) - (((long) iArr2[6]) & 4294967295L));
        iArr3[6] = (int) j7;
        long j8 = (j7 >> 32) + ((((long) iArr[7]) & 4294967295L) - (((long) iArr2[7]) & 4294967295L));
        iArr3[7] = (int) j8;
        return (int) (j8 >> 32);
    }

    public static int I(int[] iArr, int[] iArr2) {
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
        long j6 = (j5 >> 32) + ((((long) iArr2[5]) & 4294967295L) - (((long) iArr[5]) & 4294967295L));
        iArr2[5] = (int) j6;
        long j7 = (j6 >> 32) + ((((long) iArr2[6]) & 4294967295L) - (((long) iArr[6]) & 4294967295L));
        iArr2[6] = (int) j7;
        long j8 = (j7 >> 32) + ((((long) iArr2[7]) & 4294967295L) - (4294967295L & ((long) iArr[7])));
        iArr2[7] = (int) j8;
        return (int) (j8 >> 32);
    }

    public static BigInteger J(int[] iArr) {
        byte[] bArr = new byte[32];
        for (int i2 = 0; i2 < 8; i2++) {
            int i3 = iArr[i2];
            if (i3 != 0) {
                g.a.f.f.d(i3, bArr, (7 - i2) << 2);
            }
        }
        return new BigInteger(1, bArr);
    }

    public static BigInteger K(long[] jArr) {
        byte[] bArr = new byte[32];
        for (int i2 = 0; i2 < 4; i2++) {
            long j = jArr[i2];
            if (j != 0) {
                g.a.f.f.q(j, bArr, (3 - i2) << 3);
            }
        }
        return new BigInteger(1, bArr);
    }

    public static void L(int[] iArr) {
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        iArr[3] = 0;
        iArr[4] = 0;
        iArr[5] = 0;
        iArr[6] = 0;
        iArr[7] = 0;
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
        long j7 = (j6 >>> 32) + (((long) iArr[6]) & 4294967295L) + (((long) iArr2[6]) & 4294967295L);
        iArr3[6] = (int) j7;
        long j8 = (j7 >>> 32) + (((long) iArr[7]) & 4294967295L) + (((long) iArr2[7]) & 4294967295L);
        iArr3[7] = (int) j8;
        return (int) (j8 >>> 32);
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
        long j7 = (j6 >>> 32) + (((long) iArr[6]) & 4294967295L) + (((long) iArr2[6]) & 4294967295L) + (((long) iArr3[6]) & 4294967295L);
        iArr3[6] = (int) j7;
        long j8 = (j7 >>> 32) + (((long) iArr[7]) & 4294967295L) + (((long) iArr2[7]) & 4294967295L) + (((long) iArr3[7]) & 4294967295L);
        iArr3[7] = (int) j8;
        return (int) (j8 >>> 32);
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
        long j6 = (j5 >>> 32) + (((long) iArr[i2 + 5]) & 4294967295L) + (((long) iArr2[i10]) & 4294967295L);
        iArr2[i10] = (int) j6;
        int i11 = i3 + 6;
        long j7 = (j6 >>> 32) + (((long) iArr[i2 + 6]) & 4294967295L) + (((long) iArr2[i11]) & 4294967295L);
        iArr2[i11] = (int) j7;
        int i12 = i3 + 7;
        long j8 = (j7 >>> 32) + (((long) iArr[i2 + 7]) & 4294967295L) + (4294967295L & ((long) iArr2[i12]));
        iArr2[i12] = (int) j8;
        return (int) (j8 >>> 32);
    }

    public static int d(int[] iArr, int[] iArr2) {
        long j = (((long) iArr[0]) & 4294967295L) + (((long) iArr2[0]) & 4294967295L) + 0;
        iArr2[0] = (int) j;
        long j2 = (j >>> 32) + (((long) iArr[1]) & 4294967295L) + (((long) iArr2[1]) & 4294967295L);
        iArr2[1] = (int) j2;
        long j3 = (j2 >>> 32) + (((long) iArr[2]) & 4294967295L) + (((long) iArr2[2]) & 4294967295L);
        iArr2[2] = (int) j3;
        long j4 = (j3 >>> 32) + (((long) iArr[3]) & 4294967295L) + (((long) iArr2[3]) & 4294967295L);
        iArr2[3] = (int) j4;
        long j5 = (j4 >>> 32) + (((long) iArr[4]) & 4294967295L) + (((long) iArr2[4]) & 4294967295L);
        iArr2[4] = (int) j5;
        long j6 = (j5 >>> 32) + (((long) iArr[5]) & 4294967295L) + (((long) iArr2[5]) & 4294967295L);
        iArr2[5] = (int) j6;
        long j7 = (j6 >>> 32) + (((long) iArr[6]) & 4294967295L) + (((long) iArr2[6]) & 4294967295L);
        iArr2[6] = (int) j7;
        long j8 = (j7 >>> 32) + (((long) iArr[7]) & 4294967295L) + (4294967295L & ((long) iArr2[7]));
        iArr2[7] = (int) j8;
        return (int) (j8 >>> 32);
    }

    public static int e(int[] iArr, int i2, int[] iArr2, int i3) {
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
        long j6 = (j5 >>> 32) + (((long) iArr[i19]) & 4294967295L) + (((long) iArr2[i20]) & 4294967295L);
        int i21 = (int) j6;
        iArr[i19] = i21;
        iArr2[i20] = i21;
        int i22 = i2 + 6;
        int i23 = i3 + 6;
        long j7 = (j6 >>> 32) + (((long) iArr[i22]) & 4294967295L) + (((long) iArr2[i23]) & 4294967295L);
        int i24 = (int) j7;
        iArr[i22] = i24;
        iArr2[i23] = i24;
        int i25 = i2 + 7;
        int i26 = i3 + 7;
        long j8 = (j7 >>> 32) + (((long) iArr[i25]) & 4294967295L) + (4294967295L & ((long) iArr2[i26]));
        int i27 = (int) j8;
        iArr[i25] = i27;
        iArr2[i26] = i27;
        return (int) (j8 >>> 32);
    }

    public static void f(int[] iArr, int i2, int[] iArr2, int i3) {
        iArr2[i3 + 0] = iArr[i2 + 0];
        iArr2[i3 + 1] = iArr[i2 + 1];
        iArr2[i3 + 2] = iArr[i2 + 2];
        iArr2[i3 + 3] = iArr[i2 + 3];
        iArr2[i3 + 4] = iArr[i2 + 4];
        iArr2[i3 + 5] = iArr[i2 + 5];
        iArr2[i3 + 6] = iArr[i2 + 6];
        iArr2[i3 + 7] = iArr[i2 + 7];
    }

    public static void g(long[] jArr, int i2, long[] jArr2, int i3) {
        jArr2[i3 + 0] = jArr[i2 + 0];
        jArr2[i3 + 1] = jArr[i2 + 1];
        jArr2[i3 + 2] = jArr[i2 + 2];
        jArr2[i3 + 3] = jArr[i2 + 3];
    }

    public static int[] h() {
        return new int[8];
    }

    public static long[] i() {
        return new long[4];
    }

    public static int[] j() {
        return new int[16];
    }

    public static long[] k() {
        return new long[8];
    }

    public static boolean l(int[] iArr, int i2, int[] iArr2, int i3, int[] iArr3, int i4) {
        boolean zR = r(iArr, i2, iArr2, i3);
        if (zR) {
            G(iArr, i2, iArr2, i3, iArr3, i4);
        } else {
            G(iArr2, i3, iArr, i2, iArr3, i4);
        }
        return zR;
    }

    public static boolean m(int[] iArr, int[] iArr2) {
        for (int i2 = 7; i2 >= 0; i2--) {
            if (iArr[i2] != iArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public static boolean n(long[] jArr, long[] jArr2) {
        for (int i2 = 3; i2 >= 0; i2--) {
            if (jArr[i2] != jArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public static int[] o(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 256) {
            throw new IllegalArgumentException();
        }
        int[] iArrH = h();
        int i2 = 0;
        while (bigInteger.signum() != 0) {
            iArrH[i2] = bigInteger.intValue();
            bigInteger = bigInteger.shiftRight(32);
            i2++;
        }
        return iArrH;
    }

    public static long[] p(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 256) {
            throw new IllegalArgumentException();
        }
        long[] jArrI = i();
        int i2 = 0;
        while (bigInteger.signum() != 0) {
            jArrI[i2] = bigInteger.longValue();
            bigInteger = bigInteger.shiftRight(64);
            i2++;
        }
        return jArrI;
    }

    public static int q(int[] iArr, int i2) {
        int i3;
        if (i2 == 0) {
            i3 = iArr[0];
        } else {
            if ((i2 & 255) != i2) {
                return 0;
            }
            i3 = iArr[i2 >>> 5] >>> (i2 & 31);
        }
        return i3 & 1;
    }

    public static boolean r(int[] iArr, int i2, int[] iArr2, int i3) {
        for (int i4 = 7; i4 >= 0; i4--) {
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

    public static boolean s(int[] iArr, int[] iArr2) {
        for (int i2 = 7; i2 >= 0; i2--) {
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

    public static boolean t(int[] iArr) {
        if (iArr[0] != 1) {
            return false;
        }
        for (int i2 = 1; i2 < 8; i2++) {
            if (iArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean u(long[] jArr) {
        if (jArr[0] != 1) {
            return false;
        }
        for (int i2 = 1; i2 < 4; i2++) {
            if (jArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean v(int[] iArr) {
        for (int i2 = 0; i2 < 8; i2++) {
            if (iArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean w(long[] jArr) {
        for (int i2 = 0; i2 < 4; i2++) {
            if (jArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static void x(int[] iArr, int i2, int[] iArr2, int i3, int[] iArr3, int i4) {
        long j = ((long) iArr2[i3 + 0]) & 4294967295L;
        long j2 = ((long) iArr2[i3 + 1]) & 4294967295L;
        long j3 = ((long) iArr2[i3 + 2]) & 4294967295L;
        long j4 = ((long) iArr2[i3 + 3]) & 4294967295L;
        long j5 = ((long) iArr2[i3 + 4]) & 4294967295L;
        long j6 = ((long) iArr2[i3 + 5]) & 4294967295L;
        long j7 = ((long) iArr2[i3 + 6]) & 4294967295L;
        long j8 = ((long) iArr2[i3 + 7]) & 4294967295L;
        long j9 = ((long) iArr[i2 + 0]) & 4294967295L;
        long j10 = (j9 * j) + 0;
        iArr3[i4 + 0] = (int) j10;
        long j11 = (j10 >>> 32) + (j9 * j2);
        iArr3[i4 + 1] = (int) j11;
        long j12 = (j11 >>> 32) + (j9 * j3);
        iArr3[i4 + 2] = (int) j12;
        long j13 = (j12 >>> 32) + (j9 * j4);
        iArr3[i4 + 3] = (int) j13;
        long j14 = (j13 >>> 32) + (j9 * j5);
        iArr3[i4 + 4] = (int) j14;
        long j15 = (j14 >>> 32) + (j9 * j6);
        iArr3[i4 + 5] = (int) j15;
        long j16 = (j15 >>> 32) + (j9 * j7);
        iArr3[i4 + 6] = (int) j16;
        long j17 = j8;
        long j18 = (j16 >>> 32) + (j9 * j17);
        iArr3[i4 + 7] = (int) j18;
        iArr3[i4 + 8] = (int) (j18 >>> 32);
        int i5 = 1;
        int i6 = i4;
        int i7 = 1;
        while (i7 < 8) {
            i6 += i5;
            long j19 = ((long) iArr[i2 + i7]) & 4294967295L;
            int i8 = i6 + 0;
            long j20 = (j19 * j) + (((long) iArr3[i8]) & 4294967295L) + 0;
            iArr3[i8] = (int) j20;
            int i9 = i6 + 1;
            long j21 = j17;
            long j22 = (j20 >>> 32) + (j19 * j2) + (((long) iArr3[i9]) & 4294967295L);
            iArr3[i9] = (int) j22;
            int i10 = i6 + 2;
            long j23 = j3;
            long j24 = (j22 >>> 32) + (j19 * j3) + (((long) iArr3[i10]) & 4294967295L);
            iArr3[i10] = (int) j24;
            int i11 = i6 + 3;
            long j25 = (j24 >>> 32) + (j19 * j4) + (((long) iArr3[i11]) & 4294967295L);
            iArr3[i11] = (int) j25;
            int i12 = i6 + 4;
            long j26 = (j25 >>> 32) + (j19 * j5) + (((long) iArr3[i12]) & 4294967295L);
            iArr3[i12] = (int) j26;
            int i13 = i6 + 5;
            long j27 = (j26 >>> 32) + (j19 * j6) + (((long) iArr3[i13]) & 4294967295L);
            iArr3[i13] = (int) j27;
            int i14 = i6 + 6;
            long j28 = (j27 >>> 32) + (j19 * j7) + (((long) iArr3[i14]) & 4294967295L);
            iArr3[i14] = (int) j28;
            int i15 = i6 + 7;
            long j29 = (j28 >>> 32) + (j19 * j21) + (((long) iArr3[i15]) & 4294967295L);
            iArr3[i15] = (int) j29;
            iArr3[i6 + 8] = (int) (j29 >>> 32);
            i7++;
            j3 = j23;
            j17 = j21;
            j4 = j4;
            i5 = 1;
        }
    }

    public static void y(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = ((long) iArr2[0]) & 4294967295L;
        long j2 = ((long) iArr2[1]) & 4294967295L;
        long j3 = ((long) iArr2[2]) & 4294967295L;
        long j4 = ((long) iArr2[3]) & 4294967295L;
        long j5 = ((long) iArr2[4]) & 4294967295L;
        long j6 = ((long) iArr2[5]) & 4294967295L;
        long j7 = ((long) iArr2[6]) & 4294967295L;
        long j8 = ((long) iArr2[7]) & 4294967295L;
        long j9 = ((long) iArr[0]) & 4294967295L;
        long j10 = (j9 * j) + 0;
        iArr3[0] = (int) j10;
        long j11 = (j10 >>> 32) + (j9 * j2);
        iArr3[1] = (int) j11;
        long j12 = (j11 >>> 32) + (j9 * j3);
        iArr3[2] = (int) j12;
        long j13 = (j12 >>> 32) + (j9 * j4);
        iArr3[3] = (int) j13;
        long j14 = (j13 >>> 32) + (j9 * j5);
        iArr3[4] = (int) j14;
        long j15 = (j14 >>> 32) + (j9 * j6);
        iArr3[5] = (int) j15;
        long j16 = (j15 >>> 32) + (j9 * j7);
        iArr3[6] = (int) j16;
        long j17 = (j16 >>> 32) + (j9 * j8);
        iArr3[7] = (int) j17;
        iArr3[8] = (int) (j17 >>> 32);
        int i2 = 1;
        for (int i3 = 8; i2 < i3; i3 = 8) {
            long j18 = ((long) iArr[i2]) & 4294967295L;
            int i4 = i2 + 0;
            long j19 = (j18 * j) + (((long) iArr3[i4]) & 4294967295L) + 0;
            iArr3[i4] = (int) j19;
            int i5 = i2 + 1;
            long j20 = j2;
            long j21 = (j19 >>> 32) + (j18 * j2) + (((long) iArr3[i5]) & 4294967295L);
            iArr3[i5] = (int) j21;
            int i6 = i2 + 2;
            long j22 = j6;
            long j23 = (j21 >>> 32) + (j18 * j3) + (((long) iArr3[i6]) & 4294967295L);
            iArr3[i6] = (int) j23;
            int i7 = i2 + 3;
            long j24 = (j23 >>> 32) + (j18 * j4) + (((long) iArr3[i7]) & 4294967295L);
            iArr3[i7] = (int) j24;
            int i8 = i2 + 4;
            long j25 = (j24 >>> 32) + (j18 * j5) + (((long) iArr3[i8]) & 4294967295L);
            iArr3[i8] = (int) j25;
            int i9 = i2 + 5;
            long j26 = (j25 >>> 32) + (j18 * j22) + (((long) iArr3[i9]) & 4294967295L);
            iArr3[i9] = (int) j26;
            int i10 = i2 + 6;
            long j27 = (j26 >>> 32) + (j18 * j7) + (((long) iArr3[i10]) & 4294967295L);
            iArr3[i10] = (int) j27;
            int i11 = i2 + 7;
            long j28 = (j27 >>> 32) + (j18 * j8) + (((long) iArr3[i11]) & 4294967295L);
            iArr3[i11] = (int) j28;
            iArr3[i2 + 8] = (int) (j28 >>> 32);
            i2 = i5;
            j = j;
            j2 = j20;
            j6 = j22;
        }
    }

    public static long z(int i2, int[] iArr, int i3, int[] iArr2, int i4, int[] iArr3, int i5) {
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
        long j14 = (j12 >>> 32) + (j * j13) + j11 + (((long) iArr2[i4 + 5]) & 4294967295L);
        iArr3[i5 + 5] = (int) j14;
        long j15 = ((long) iArr[i3 + 6]) & 4294967295L;
        long j16 = (j14 >>> 32) + (j * j15) + j13 + (((long) iArr2[i4 + 6]) & 4294967295L);
        iArr3[i5 + 6] = (int) j16;
        long j17 = ((long) iArr[i3 + 7]) & 4294967295L;
        long j18 = (j16 >>> 32) + (j * j17) + j15 + (4294967295L & ((long) iArr2[i4 + 7]));
        iArr3[i5 + 7] = (int) j18;
        return (j18 >>> 32) + j17;
    }
}
