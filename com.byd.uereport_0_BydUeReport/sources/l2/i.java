package l2;

import java.lang.reflect.Array;
import java.math.BigInteger;

/* JADX INFO: compiled from: RyuDouble.java */
/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[][] f6236a = (int[][]) Array.newInstance((Class<?>) int.class, 326, 4);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[][] f6237b = (int[][]) Array.newInstance((Class<?>) int.class, 291, 4);

    static {
        BigInteger bigInteger = BigInteger.ONE;
        BigInteger bigIntegerSubtract = bigInteger.shiftLeft(31).subtract(bigInteger);
        BigInteger bigIntegerSubtract2 = bigInteger.shiftLeft(31).subtract(bigInteger);
        int i8 = 0;
        while (i8 < 326) {
            BigInteger bigIntegerPow = BigInteger.valueOf(5L).pow(i8);
            int iBitLength = bigIntegerPow.bitLength();
            int i9 = i8 == 0 ? 1 : (int) ((((((long) i8) * 23219280) + 10000000) - 1) / 10000000);
            if (i9 != iBitLength) {
                throw new IllegalStateException(iBitLength + " != " + i9);
            }
            if (i8 < f6236a.length) {
                for (int i10 = 0; i10 < 4; i10++) {
                    f6236a[i8][i10] = bigIntegerPow.shiftRight((iBitLength - 121) + ((3 - i10) * 31)).and(bigIntegerSubtract).intValue();
                }
            }
            if (i8 < f6237b.length) {
                BigInteger bigInteger2 = BigInteger.ONE;
                BigInteger bigIntegerAdd = bigInteger2.shiftLeft(iBitLength + 121).divide(bigIntegerPow).add(bigInteger2);
                for (int i11 = 0; i11 < 4; i11++) {
                    if (i11 == 0) {
                        f6237b[i8][i11] = bigIntegerAdd.shiftRight((3 - i11) * 31).intValue();
                    } else {
                        f6237b[i8][i11] = bigIntegerAdd.shiftRight((3 - i11) * 31).and(bigIntegerSubtract2).intValue();
                    }
                }
            }
            i8++;
        }
    }

    public static int a(double d8, char[] cArr, int i8) {
        int i9;
        boolean z7;
        boolean z8;
        long j8;
        int i10;
        long j9;
        int i11;
        int i12;
        int i13;
        long j10;
        long j11;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        int i21;
        int i22;
        int i23;
        if (!Double.isNaN(d8)) {
            if (d8 == Double.POSITIVE_INFINITY) {
                int i24 = i8 + 1;
                cArr[i8] = 'I';
                int i25 = i24 + 1;
                cArr[i24] = 'n';
                int i26 = i25 + 1;
                cArr[i25] = 'f';
                int i27 = i26 + 1;
                cArr[i26] = 'i';
                int i28 = i27 + 1;
                cArr[i27] = 'n';
                int i29 = i28 + 1;
                cArr[i28] = 'i';
                int i30 = i29 + 1;
                cArr[i29] = 't';
                i18 = i30 + 1;
                cArr[i30] = 'y';
            } else if (d8 == Double.NEGATIVE_INFINITY) {
                int i31 = i8 + 1;
                cArr[i8] = '-';
                int i32 = i31 + 1;
                cArr[i31] = 'I';
                int i33 = i32 + 1;
                cArr[i32] = 'n';
                int i34 = i33 + 1;
                cArr[i33] = 'f';
                int i35 = i34 + 1;
                cArr[i34] = 'i';
                int i36 = i35 + 1;
                cArr[i35] = 'n';
                int i37 = i36 + 1;
                cArr[i36] = 'i';
                int i38 = i37 + 1;
                cArr[i37] = 't';
                i23 = i38 + 1;
                cArr[i38] = 'y';
            } else {
                long jDoubleToLongBits = Double.doubleToLongBits(d8);
                if (jDoubleToLongBits == 0) {
                    int i39 = i8 + 1;
                    cArr[i8] = '0';
                    int i40 = i39 + 1;
                    cArr[i39] = '.';
                    i23 = i40 + 1;
                    cArr[i40] = '0';
                } else if (jDoubleToLongBits == Long.MIN_VALUE) {
                    int i41 = i8 + 1;
                    cArr[i8] = '-';
                    int i42 = i41 + 1;
                    cArr[i41] = '0';
                    int i43 = i42 + 1;
                    cArr[i42] = '.';
                    i18 = i43 + 1;
                    cArr[i43] = '0';
                } else {
                    int i44 = (int) ((jDoubleToLongBits >>> 52) & 2047);
                    long j12 = jDoubleToLongBits & 4503599627370495L;
                    if (i44 == 0) {
                        i9 = -1074;
                    } else {
                        i9 = (i44 - 1023) - 52;
                        j12 |= 4503599627370496L;
                    }
                    boolean z9 = jDoubleToLongBits < 0;
                    boolean z10 = (j12 & 1) == 0;
                    long j13 = 4 * j12;
                    long j14 = j13 + 2;
                    int i45 = (j12 != 4503599627370496L || i44 <= 1) ? 1 : 0;
                    long j15 = (j13 - 1) - ((long) i45);
                    int i46 = i9 - 2;
                    int i47 = 3;
                    if (i46 >= 0) {
                        int iMax = Math.max(0, ((int) ((((long) i46) * 3010299) / 10000000)) - 1);
                        int i48 = ((((-i46) + iMax) + (((iMax == 0 ? 1 : (int) ((((((long) iMax) * 23219280) + 10000000) - 1) / 10000000)) + 122) - 1)) - 93) - 21;
                        if (i48 < 0) {
                            throw new IllegalArgumentException("" + i48);
                        }
                        int[] iArr = f6237b[iMax];
                        long j16 = j13 >>> 31;
                        long j17 = j13 & 2147483647L;
                        long j18 = ((long) iArr[0]) * j16;
                        z7 = z9;
                        z8 = z10;
                        long j19 = ((((((((((((j17 * ((long) iArr[3])) >>> 31) + (((long) iArr[2]) * j17)) + (j16 * ((long) iArr[3]))) >>> 31) + (((long) iArr[1]) * j17)) + (((long) iArr[2]) * j16)) >>> 31) + (((long) iArr[0]) * j17)) + (((long) iArr[1]) * j16)) >>> 21) + (j18 << 10)) >>> i48;
                        long j20 = j14 >>> 31;
                        long j21 = j14 & 2147483647L;
                        long j22 = ((long) iArr[0]) * j20;
                        long j23 = ((((((((((((j21 * ((long) iArr[3])) >>> 31) + (((long) iArr[2]) * j21)) + (j20 * ((long) iArr[3]))) >>> 31) + (((long) iArr[1]) * j21)) + (((long) iArr[2]) * j20)) >>> 31) + (((long) iArr[0]) * j21)) + (((long) iArr[1]) * j20)) >>> 21) + (j22 << 10)) >>> i48;
                        long j24 = j15 >>> 31;
                        long j25 = j15 & 2147483647L;
                        long j26 = ((long) iArr[0]) * j24;
                        j10 = j23;
                        j9 = ((((((((((((j25 * ((long) iArr[3])) >>> 31) + (((long) iArr[2]) * j25)) + (j24 * ((long) iArr[3]))) >>> 31) + (((long) iArr[1]) * j25)) + (((long) iArr[2]) * j24)) >>> 31) + (((long) iArr[0]) * j25)) + (((long) iArr[1]) * j24)) >>> 21) + (j26 << 10)) >>> i48;
                        if (iMax <= 21) {
                            long j27 = j13 % 5;
                            if (j27 == 0) {
                                if (j27 != 0) {
                                    i22 = 0;
                                } else if (j13 % 25 != 0) {
                                    i22 = 1;
                                } else if (j13 % 125 != 0) {
                                    i22 = 2;
                                } else if (j13 % 625 != 0) {
                                    i22 = 3;
                                } else {
                                    long j28 = j13 / 625;
                                    i22 = 4;
                                    for (long j29 = 0; j28 > j29 && j28 % 5 == j29; j29 = 0) {
                                        j28 /= 5;
                                        i22++;
                                    }
                                }
                                i12 = i22 >= iMax ? 1 : 0;
                                i19 = 0;
                                i13 = i19;
                                j8 = j19;
                                i11 = iMax;
                                i10 = 0;
                            } else {
                                if (z8) {
                                    if (j15 % 5 != 0) {
                                        i21 = 0;
                                    } else if (j15 % 25 != 0) {
                                        i21 = 1;
                                    } else if (j15 % 125 != 0) {
                                        i21 = 2;
                                    } else if (j15 % 625 != 0) {
                                        i21 = 3;
                                    } else {
                                        long j30 = j15 / 625;
                                        i21 = 4;
                                        for (long j31 = 0; j30 > j31 && j30 % 5 == j31; j31 = 0) {
                                            j30 /= 5;
                                            i21++;
                                        }
                                    }
                                    i19 = i21 >= iMax ? 1 : 0;
                                    i12 = 0;
                                    i13 = i19;
                                    j8 = j19;
                                    i11 = iMax;
                                    i10 = 0;
                                } else {
                                    if (j14 % 5 != 0) {
                                        i20 = 0;
                                    } else if (j14 % 25 != 0) {
                                        i20 = 1;
                                    } else if (j14 % 125 != 0) {
                                        i20 = 2;
                                    } else if (j14 % 625 != 0) {
                                        i20 = 3;
                                    } else {
                                        long j32 = j14 / 625;
                                        i20 = 4;
                                        for (long j33 = 0; j32 > j33 && j32 % 5 == j33; j33 = 0) {
                                            j32 /= 5;
                                            i20++;
                                        }
                                    }
                                    if (i20 >= iMax) {
                                        j10--;
                                    }
                                }
                                i12 = 0;
                                i13 = i19;
                                j8 = j19;
                                i11 = iMax;
                                i10 = 0;
                            }
                        } else {
                            i12 = 0;
                            i13 = i19;
                            j8 = j19;
                            i11 = iMax;
                            i10 = 0;
                        }
                    } else {
                        z7 = z9;
                        z8 = z10;
                        int i49 = -i46;
                        int iMax2 = Math.max(0, ((int) ((((long) i49) * 6989700) / 10000000)) - 1);
                        int i50 = i49 - iMax2;
                        int i51 = ((iMax2 - ((i50 == 0 ? 1 : (int) ((((((long) i50) * 23219280) + 10000000) - 1) / 10000000)) - 121)) - 93) - 21;
                        if (i51 < 0) {
                            throw new IllegalArgumentException("" + i51);
                        }
                        int[] iArr2 = f6236a[i50];
                        long j34 = j13 >>> 31;
                        long j35 = j13 & 2147483647L;
                        long j36 = ((long) iArr2[0]) * j34;
                        int i52 = i45;
                        long j37 = ((((((((((((j35 * ((long) iArr2[3])) >>> 31) + (((long) iArr2[2]) * j35)) + (j34 * ((long) iArr2[3]))) >>> 31) + (((long) iArr2[1]) * j35)) + (((long) iArr2[2]) * j34)) >>> 31) + (((long) iArr2[0]) * j35)) + (((long) iArr2[1]) * j34)) >>> 21) + (j36 << 10)) >>> i51;
                        long j38 = j14 >>> 31;
                        long j39 = j14 & 2147483647L;
                        long j40 = ((long) iArr2[0]) * j38;
                        j8 = j37;
                        long j41 = ((((((((((((j39 * ((long) iArr2[3])) >>> 31) + (((long) iArr2[2]) * j39)) + (j38 * ((long) iArr2[3]))) >>> 31) + (((long) iArr2[1]) * j39)) + (((long) iArr2[2]) * j38)) >>> 31) + (((long) iArr2[0]) * j39)) + (((long) iArr2[1]) * j38)) >>> 21) + (j40 << 10)) >>> i51;
                        long j42 = j15 >>> 31;
                        long j43 = j15 & 2147483647L;
                        i10 = 0;
                        long j44 = ((long) iArr2[0]) * j42;
                        j9 = ((((((((((((j43 * ((long) iArr2[3])) >>> 31) + (((long) iArr2[2]) * j43)) + (j42 * ((long) iArr2[3]))) >>> 31) + (((long) iArr2[1]) * j43)) + (((long) iArr2[2]) * j42)) >>> 31) + (((long) iArr2[0]) * j43)) + (((long) iArr2[1]) * j42)) >>> 21) + (j44 << 10)) >>> i51;
                        i11 = iMax2 + i46;
                        i12 = 1;
                        if (iMax2 > 1) {
                            if (iMax2 < 63) {
                                i12 = (j13 & ((1 << (iMax2 - 1)) - 1)) == 0 ? 1 : 0;
                                i13 = 0;
                            } else {
                                i12 = 0;
                                i13 = 0;
                            }
                            j10 = j41;
                        } else if (z8) {
                            j10 = j41;
                            i13 = i52 == 1 ? 1 : 0;
                        } else {
                            j10 = j41 - 1;
                            i13 = 0;
                        }
                    }
                    if (j10 >= 1000000000000000000L) {
                        i47 = 19;
                    } else if (j10 >= 100000000000000000L) {
                        i47 = 18;
                    } else if (j10 >= 10000000000000000L) {
                        i47 = 17;
                    } else if (j10 >= 1000000000000000L) {
                        i47 = 16;
                    } else if (j10 >= 100000000000000L) {
                        i47 = 15;
                    } else if (j10 >= 10000000000000L) {
                        i47 = 14;
                    } else if (j10 >= 1000000000000L) {
                        i47 = 13;
                    } else if (j10 >= 100000000000L) {
                        i47 = 12;
                    } else if (j10 >= 10000000000L) {
                        i47 = 11;
                    } else if (j10 >= 1000000000) {
                        i47 = 10;
                    } else if (j10 >= 100000000) {
                        i47 = 9;
                    } else if (j10 >= 10000000) {
                        i47 = 8;
                    } else if (j10 >= 1000000) {
                        i47 = 7;
                    } else if (j10 >= 100000) {
                        i47 = 6;
                    } else if (j10 >= 10000) {
                        i47 = 5;
                    } else if (j10 >= 1000) {
                        i47 = 4;
                    } else if (j10 < 100) {
                        i47 = j10 >= 10 ? 2 : 1;
                    }
                    int i53 = (i11 + i47) - 1;
                    int i54 = (i53 < -3 || i53 >= 7) ? 1 : i10;
                    if (i13 == 0 && i12 == 0) {
                        int i55 = i10;
                        i14 = i55;
                        while (true) {
                            long j45 = j10 / 10;
                            long j46 = j9 / 10;
                            if (j45 <= j46 || (j10 < 100 && i54 != 0)) {
                                break;
                            }
                            i55 = (int) (j8 % 10);
                            j8 /= 10;
                            i14++;
                            j10 = j45;
                            j9 = j46;
                        }
                        j11 = j8 + ((long) ((j8 == j9 || i55 >= 5) ? 1 : i10));
                    } else {
                        int i56 = i10;
                        int i57 = i56;
                        while (true) {
                            long j47 = j10 / 10;
                            long j48 = j9 / 10;
                            if (j47 <= j48 || (j10 < 100 && i54 != 0)) {
                                break;
                            }
                            i13 &= j9 % 10 == 0 ? 1 : i10;
                            i12 &= i56 == 0 ? 1 : i10;
                            i56 = (int) (j8 % 10);
                            j8 /= 10;
                            i57++;
                            j10 = j47;
                            j9 = j48;
                        }
                        if (i13 != 0 && z8) {
                            while (j9 % 10 == 0 && (j10 >= 100 || i54 == 0)) {
                                i12 &= i56 == 0 ? 1 : i10;
                                i56 = (int) (j8 % 10);
                                j10 /= 10;
                                j8 /= 10;
                                j9 /= 10;
                                i57++;
                            }
                        }
                        if (i12 != 0 && i56 == 5 && j8 % 2 == 0) {
                            i56 = 4;
                        }
                        j11 = j8 + ((long) (((j8 != j9 || (i13 != 0 && z8)) && i56 < 5) ? i10 : 1));
                        i14 = i57;
                    }
                    int i58 = i47 - i14;
                    if (z7) {
                        i15 = i8 + 1;
                        cArr[i8] = '-';
                    } else {
                        i15 = i8;
                    }
                    if (i54 == 0) {
                        char c8 = '0';
                        if (i53 < 0) {
                            int i59 = i15 + 1;
                            cArr[i15] = '0';
                            int i60 = i59 + 1;
                            cArr[i59] = '.';
                            int i61 = -1;
                            while (i61 > i53) {
                                cArr[i60] = c8;
                                i61--;
                                i60++;
                                c8 = '0';
                            }
                            i16 = i60;
                            while (i10 < i58) {
                                cArr[((i60 + i58) - i10) - 1] = (char) ((j11 % 10) + 48);
                                j11 /= 10;
                                i16++;
                                i10++;
                            }
                        } else {
                            int i62 = i53 + 1;
                            if (i62 >= i58) {
                                while (i10 < i58) {
                                    cArr[((i15 + i58) - i10) - 1] = (char) ((j11 % 10) + 48);
                                    j11 /= 10;
                                    i10++;
                                }
                                int i63 = i15 + i58;
                                while (i58 < i62) {
                                    cArr[i63] = '0';
                                    i58++;
                                    i63++;
                                }
                                int i64 = i63 + 1;
                                cArr[i63] = '.';
                                cArr[i64] = '0';
                                i16 = i64 + 1;
                            } else {
                                int i65 = i15 + 1;
                                while (i10 < i58) {
                                    if ((i58 - i10) - 1 == i53) {
                                        cArr[((i65 + i58) - i10) - 1] = '.';
                                        i65--;
                                    }
                                    cArr[((i65 + i58) - i10) - 1] = (char) ((j11 % 10) + 48);
                                    j11 /= 10;
                                    i10++;
                                }
                                i16 = i15 + i58 + 1;
                            }
                        }
                        return i16 - i8;
                    }
                    while (i10 < i58 - 1) {
                        int i66 = (int) (j11 % 10);
                        j11 /= 10;
                        cArr[(i15 + i58) - i10] = (char) (i66 + 48);
                        i10++;
                    }
                    cArr[i15] = (char) ((j11 % 10) + 48);
                    cArr[i15 + 1] = '.';
                    int i67 = i15 + i58 + 1;
                    if (i58 == 1) {
                        cArr[i67] = '0';
                        i67++;
                    }
                    int i68 = i67 + 1;
                    cArr[i67] = 'E';
                    if (i53 < 0) {
                        cArr[i68] = '-';
                        i53 = -i53;
                        i68++;
                    }
                    if (i53 >= 100) {
                        int i69 = i68 + 1;
                        i17 = 48;
                        cArr[i68] = (char) ((i53 / 100) + 48);
                        i53 %= 100;
                        i68 = i69 + 1;
                        cArr[i69] = (char) ((i53 / 10) + 48);
                    } else {
                        i17 = 48;
                        if (i53 >= 10) {
                            cArr[i68] = (char) ((i53 / 10) + 48);
                            i68++;
                        }
                    }
                    i18 = i68 + 1;
                    cArr[i68] = (char) ((i53 % 10) + i17);
                }
            }
            return i18 - i8;
        }
        int i70 = i8 + 1;
        cArr[i8] = 'N';
        int i71 = i70 + 1;
        cArr[i70] = 'a';
        i23 = i71 + 1;
        cArr[i71] = 'N';
        return i23 - i8;
    }

    public static String b(double d8) {
        char[] cArr = new char[24];
        return new String(cArr, 0, a(d8, cArr, 0));
    }
}
