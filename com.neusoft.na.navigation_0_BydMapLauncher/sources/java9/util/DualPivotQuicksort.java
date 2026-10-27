package java9.util;

import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.ShortCompanionObject;

/* JADX INFO: loaded from: classes3.dex */
final class DualPivotQuicksort {
    private static final int COUNTING_SORT_THRESHOLD_FOR_BYTE = 29;
    private static final int COUNTING_SORT_THRESHOLD_FOR_SHORT_OR_CHAR = 3200;
    private static final int INSERTION_SORT_THRESHOLD = 47;
    private static final int MAX_RUN_COUNT = 67;
    private static final int NUM_BYTE_VALUES = 256;
    private static final int NUM_CHAR_VALUES = 65536;
    private static final int NUM_SHORT_VALUES = 65536;
    private static final int QUICKSORT_THRESHOLD = 286;

    private DualPivotQuicksort() {
    }

    static void sort(int[] iArr, int i, int i2, int[] iArr2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int[] iArr3 = iArr;
        int[] iArr4 = iArr2;
        int i8 = 1;
        if (i2 - i < 286) {
            sort(iArr3, i, i2, true);
            return;
        }
        int[] iArr5 = new int[68];
        int i9 = 0;
        iArr5[0] = i;
        int i10 = i;
        int i11 = 0;
        while (i10 < i2) {
            while (i10 < i2) {
                int i12 = i10 + 1;
                if (iArr3[i10] != iArr3[i12]) {
                    break;
                } else {
                    i10 = i12;
                }
            }
            if (i10 == i2) {
                break;
            }
            int i13 = iArr3[i10];
            int i14 = iArr3[i10 + 1];
            if (i13 < i14) {
                do {
                    i10++;
                    if (i10 > i2) {
                        break;
                    }
                } while (iArr3[i10 - 1] <= iArr3[i10]);
            } else if (i13 > i14) {
                do {
                    i10++;
                    if (i10 > i2) {
                        break;
                    }
                } while (iArr3[i10 - 1] >= iArr3[i10]);
                int i15 = iArr5[i11] - 1;
                int i16 = i10;
                while (true) {
                    i15++;
                    i16--;
                    if (i15 >= i16) {
                        break;
                    }
                    int i17 = iArr3[i15];
                    iArr3[i15] = iArr3[i16];
                    iArr3[i16] = i17;
                }
            }
            int i18 = iArr5[i11];
            if (i18 > i && iArr3[i18] >= iArr3[i18 - 1]) {
                i11--;
            }
            i11++;
            if (i11 != 67) {
                iArr5[i11] = i10;
            } else {
                sort(iArr3, i, i2, true);
                return;
            }
        }
        if (i11 == 0) {
            return;
        }
        if (i11 != 1 || iArr5[i11] <= i2) {
            int i19 = i2 + 1;
            if (iArr5[i11] < i19) {
                i11++;
                iArr5[i11] = i19;
            }
            byte b = 0;
            int i20 = 1;
            while (true) {
                i20 <<= 1;
                if (i20 >= i11) {
                    break;
                } else {
                    b = (byte) (b ^ 1);
                }
            }
            int i21 = i19 - i;
            if (iArr4 == null || i4 < i21 || i3 + i21 > iArr4.length) {
                iArr4 = new int[i21];
                i5 = 0;
            } else {
                i5 = i3;
            }
            if (b == 0) {
                System.arraycopy(iArr3, i, iArr4, i5, i21);
                i7 = i5 - i;
                i6 = 0;
                int[] iArr6 = iArr4;
                iArr4 = iArr3;
                iArr3 = iArr6;
            } else {
                i6 = i5 - i;
                i7 = 0;
            }
            while (i11 > i8) {
                int i22 = i9;
                for (int i23 = 2; i23 <= i11; i23 += 2) {
                    int i24 = iArr5[i23];
                    int i25 = iArr5[i23 - 1];
                    int i26 = iArr5[i23 - 2];
                    int i27 = i25;
                    int i28 = i26;
                    while (i26 < i24) {
                        if (i27 >= i24 || (i28 < i25 && iArr3[i28 + i7] <= iArr3[i27 + i7])) {
                            iArr4[i26 + i6] = iArr3[i28 + i7];
                            i28++;
                        } else {
                            iArr4[i26 + i6] = iArr3[i27 + i7];
                            i27++;
                        }
                        i26++;
                    }
                    i22++;
                    iArr5[i22] = i24;
                }
                if ((i11 & 1) != 0) {
                    int i29 = iArr5[i11 - 1];
                    int i30 = i19;
                    while (true) {
                        i30--;
                        if (i30 < i29) {
                            break;
                        } else {
                            iArr4[i30 + i6] = iArr3[i30 + i7];
                        }
                    }
                    i22++;
                    iArr5[i22] = i19;
                }
                i11 = i22;
                i9 = 0;
                i8 = 1;
                int[] iArr7 = iArr4;
                iArr4 = iArr3;
                iArr3 = iArr7;
                int i31 = i7;
                i7 = i6;
                i6 = i31;
            }
        }
    }

    private static void sort(int[] iArr, int i, int i2, boolean z) {
        int i3;
        int i4;
        int i5;
        int i6 = (i2 - i) + 1;
        if (i6 < 47) {
            if (z) {
                int i7 = i;
                while (i7 < i2) {
                    int i8 = i7 + 1;
                    int i9 = iArr[i8];
                    while (true) {
                        int i10 = iArr[i7];
                        if (i9 < i10) {
                            iArr[i7 + 1] = i10;
                            int i11 = i7 - 1;
                            if (i7 == i) {
                                i7 = i11;
                                break;
                            }
                            i7 = i11;
                        }
                    }
                    iArr[i7 + 1] = i9;
                    i7 = i8;
                }
                return;
            }
            while (i < i2) {
                i++;
                if (iArr[i] < iArr[i - 1]) {
                    while (true) {
                        int i12 = i + 1;
                        if (i12 > i2) {
                            break;
                        }
                        int i13 = iArr[i];
                        int i14 = iArr[i12];
                        if (i13 < i14) {
                            i14 = i13;
                            i13 = i14;
                        }
                        while (true) {
                            i--;
                            int i15 = iArr[i];
                            if (i13 >= i15) {
                                break;
                            } else {
                                iArr[i + 2] = i15;
                            }
                        }
                        int i16 = i + 1;
                        iArr[i16 + 1] = i13;
                        while (true) {
                            i16--;
                            int i17 = iArr[i16];
                            if (i14 < i17) {
                                iArr[i16 + 1] = i17;
                            }
                        }
                        iArr[i16 + 1] = i14;
                        i = i12 + 1;
                    }
                    int i18 = iArr[i2];
                    while (true) {
                        i2--;
                        int i19 = iArr[i2];
                        if (i18 < i19) {
                            iArr[i2 + 1] = i19;
                        } else {
                            iArr[i2 + 1] = i18;
                            return;
                        }
                    }
                }
            }
            return;
        }
        int i20 = (i6 >> 3) + (i6 >> 6) + 1;
        int i21 = (i + i2) >>> 1;
        int i22 = i21 - i20;
        int i23 = i22 - i20;
        int i24 = i21 + i20;
        int i25 = i20 + i24;
        int i26 = iArr[i22];
        int i27 = iArr[i23];
        if (i26 < i27) {
            iArr[i22] = i27;
            iArr[i23] = i26;
        }
        int i28 = iArr[i21];
        int i29 = iArr[i22];
        if (i28 < i29) {
            iArr[i21] = i29;
            iArr[i22] = i28;
            int i30 = iArr[i23];
            if (i28 < i30) {
                iArr[i22] = i30;
                iArr[i23] = i28;
            }
        }
        int i31 = iArr[i24];
        int i32 = iArr[i21];
        if (i31 < i32) {
            iArr[i24] = i32;
            iArr[i21] = i31;
            int i33 = iArr[i22];
            if (i31 < i33) {
                iArr[i21] = i33;
                iArr[i22] = i31;
                int i34 = iArr[i23];
                if (i31 < i34) {
                    iArr[i22] = i34;
                    iArr[i23] = i31;
                }
            }
        }
        int i35 = iArr[i25];
        int i36 = iArr[i24];
        if (i35 < i36) {
            iArr[i25] = i36;
            iArr[i24] = i35;
            int i37 = iArr[i21];
            if (i35 < i37) {
                iArr[i24] = i37;
                iArr[i21] = i35;
                int i38 = iArr[i22];
                if (i35 < i38) {
                    iArr[i21] = i38;
                    iArr[i22] = i35;
                    int i39 = iArr[i23];
                    if (i35 < i39) {
                        iArr[i22] = i39;
                        iArr[i23] = i35;
                    }
                }
            }
        }
        int i40 = iArr[i23];
        int i41 = iArr[i22];
        if (i40 != i41 && i41 != (i4 = iArr[i21]) && i4 != (i5 = iArr[i24]) && i5 != iArr[i25]) {
            iArr[i22] = iArr[i];
            iArr[i24] = iArr[i2];
            int i42 = i;
            do {
                i42++;
            } while (iArr[i42] < i41);
            int i43 = i2;
            do {
                i43--;
            } while (iArr[i43] > i5);
            int i44 = i42 - 1;
            loop9: while (true) {
                i44++;
                if (i44 > i43) {
                    break;
                }
                int i45 = iArr[i44];
                if (i45 < i41) {
                    iArr[i44] = iArr[i42];
                    iArr[i42] = i45;
                    i42++;
                } else if (i45 > i5) {
                    while (true) {
                        int i46 = iArr[i43];
                        if (i46 > i5) {
                            int i47 = i43 - 1;
                            if (i43 == i44) {
                                i43 = i47;
                                break loop9;
                            }
                            i43 = i47;
                        } else {
                            if (i46 < i41) {
                                iArr[i44] = iArr[i42];
                                iArr[i42] = iArr[i43];
                                i42++;
                            } else {
                                iArr[i44] = i46;
                            }
                            iArr[i43] = i45;
                            i43--;
                        }
                    }
                } else {
                    continue;
                }
            }
            int i48 = i42 - 1;
            iArr[i] = iArr[i48];
            iArr[i48] = i41;
            int i49 = i43 + 1;
            iArr[i2] = iArr[i49];
            iArr[i49] = i5;
            sort(iArr, i, i42 - 2, z);
            sort(iArr, i43 + 2, i2, false);
            if (i42 < i23 && i25 < i43) {
                while (iArr[i42] == i41) {
                    i42++;
                }
                while (iArr[i43] == i5) {
                    i43--;
                }
                int i50 = i42 - 1;
                loop13: while (true) {
                    i50++;
                    if (i50 > i43) {
                        break;
                    }
                    int i51 = iArr[i50];
                    if (i51 == i41) {
                        iArr[i50] = iArr[i42];
                        iArr[i42] = i51;
                        i42++;
                    } else if (i51 == i5) {
                        while (true) {
                            int i52 = iArr[i43];
                            if (i52 == i5) {
                                int i53 = i43 - 1;
                                if (i43 == i50) {
                                    i43 = i53;
                                    break loop13;
                                }
                                i43 = i53;
                            } else {
                                if (i52 == i41) {
                                    iArr[i50] = iArr[i42];
                                    iArr[i42] = i41;
                                    i42++;
                                } else {
                                    iArr[i50] = i52;
                                }
                                iArr[i43] = i51;
                                i43--;
                            }
                        }
                    } else {
                        continue;
                    }
                }
            }
            sort(iArr, i42, i43, false);
            return;
        }
        int i54 = iArr[i21];
        int i55 = i;
        int i56 = i55;
        int i57 = i2;
        while (i55 <= i57) {
            int i58 = iArr[i55];
            if (i58 != i54) {
                if (i58 < i54) {
                    iArr[i55] = iArr[i56];
                    iArr[i56] = i58;
                    i56++;
                } else {
                    while (true) {
                        i3 = iArr[i57];
                        if (i3 <= i54) {
                            break;
                        } else {
                            i57--;
                        }
                    }
                    if (i3 < i54) {
                        iArr[i55] = iArr[i56];
                        iArr[i56] = iArr[i57];
                        i56++;
                    } else {
                        iArr[i55] = i54;
                    }
                    iArr[i57] = i58;
                    i57--;
                }
            }
            i55++;
        }
        sort(iArr, i, i56 - 1, z);
        sort(iArr, i57 + 1, i2, false);
    }

    static void sort(long[] jArr, int i, int i2, long[] jArr2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        long[] jArr3 = jArr;
        long[] jArr4 = jArr2;
        if (i2 - i < 286) {
            sort(jArr3, i, i2, true);
            return;
        }
        int[] iArr = new int[68];
        iArr[0] = i;
        int i8 = i;
        int i9 = 0;
        while (i8 < i2) {
            while (i8 < i2) {
                int i10 = i8 + 1;
                if (jArr3[i8] != jArr3[i10]) {
                    break;
                } else {
                    i8 = i10;
                }
            }
            if (i8 == i2) {
                break;
            }
            long j = jArr3[i8];
            long j2 = jArr3[i8 + 1];
            if (j < j2) {
                do {
                    i8++;
                    if (i8 > i2) {
                        break;
                    }
                } while (jArr3[i8 - 1] <= jArr3[i8]);
            } else if (j > j2) {
                do {
                    i8++;
                    if (i8 > i2) {
                        break;
                    }
                } while (jArr3[i8 - 1] >= jArr3[i8]);
                int i11 = iArr[i9] - 1;
                int i12 = i8;
                while (true) {
                    i11++;
                    i12--;
                    if (i11 >= i12) {
                        break;
                    }
                    long j3 = jArr3[i11];
                    jArr3[i11] = jArr3[i12];
                    jArr3[i12] = j3;
                }
            }
            int i13 = iArr[i9];
            if (i13 > i && jArr3[i13] >= jArr3[i13 - 1]) {
                i9--;
            }
            i9++;
            if (i9 != 67) {
                iArr[i9] = i8;
            } else {
                sort(jArr3, i, i2, true);
                return;
            }
        }
        if (i9 == 0) {
            return;
        }
        if (i9 != 1 || iArr[i9] <= i2) {
            int i14 = i2 + 1;
            if (iArr[i9] < i14) {
                i9++;
                iArr[i9] = i14;
            }
            byte b = 0;
            int i15 = 1;
            while (true) {
                i15 <<= 1;
                if (i15 >= i9) {
                    break;
                } else {
                    b = (byte) (b ^ 1);
                }
            }
            int i16 = i14 - i;
            if (jArr4 == null || i4 < i16 || i3 + i16 > jArr4.length) {
                jArr4 = new long[i16];
                i5 = 0;
            } else {
                i5 = i3;
            }
            if (b == 0) {
                System.arraycopy(jArr3, i, jArr4, i5, i16);
                i7 = i5 - i;
                i6 = 0;
                long[] jArr5 = jArr4;
                jArr4 = jArr3;
                jArr3 = jArr5;
            } else {
                i6 = i5 - i;
                i7 = 0;
            }
            while (i9 > 1) {
                int i17 = 0;
                for (int i18 = 2; i18 <= i9; i18 += 2) {
                    int i19 = iArr[i18];
                    int i20 = iArr[i18 - 1];
                    int i21 = iArr[i18 - 2];
                    int i22 = i20;
                    int i23 = i21;
                    while (i21 < i19) {
                        if (i22 >= i19 || (i23 < i20 && jArr3[i23 + i7] <= jArr3[i22 + i7])) {
                            jArr4[i21 + i6] = jArr3[i23 + i7];
                            i23++;
                        } else {
                            jArr4[i21 + i6] = jArr3[i22 + i7];
                            i22++;
                        }
                        i21++;
                    }
                    i17++;
                    iArr[i17] = i19;
                }
                if ((i9 & 1) != 0) {
                    int i24 = iArr[i9 - 1];
                    int i25 = i14;
                    while (true) {
                        i25--;
                        if (i25 < i24) {
                            break;
                        } else {
                            jArr4[i25 + i6] = jArr3[i25 + i7];
                        }
                    }
                    i17++;
                    iArr[i17] = i14;
                }
                i9 = i17;
                long[] jArr6 = jArr4;
                jArr4 = jArr3;
                jArr3 = jArr6;
                int i26 = i7;
                i7 = i6;
                i6 = i26;
            }
        }
    }

    private static void sort(long[] jArr, int i, int i2, boolean z) {
        long j;
        int i3 = i;
        int i4 = i2;
        int i5 = (i4 - i3) + 1;
        if (i5 < 47) {
            if (z) {
                int i6 = i3;
                while (i6 < i4) {
                    int i7 = i6 + 1;
                    long j2 = jArr[i7];
                    while (true) {
                        long j3 = jArr[i6];
                        if (j2 < j3) {
                            jArr[i6 + 1] = j3;
                            int i8 = i6 - 1;
                            if (i6 == i3) {
                                i6 = i8;
                                break;
                            }
                            i6 = i8;
                        }
                    }
                    jArr[i6 + 1] = j2;
                    i6 = i7;
                }
                return;
            }
            while (i3 < i4) {
                i3++;
                if (jArr[i3] < jArr[i3 - 1]) {
                    while (true) {
                        int i9 = i3 + 1;
                        if (i9 > i4) {
                            break;
                        }
                        long j4 = jArr[i3];
                        long j5 = jArr[i9];
                        if (j4 < j5) {
                            j4 = j5;
                            j5 = j4;
                        }
                        while (true) {
                            i3--;
                            long j6 = jArr[i3];
                            if (j4 >= j6) {
                                break;
                            } else {
                                jArr[i3 + 2] = j6;
                            }
                        }
                        int i10 = i3 + 1;
                        jArr[i10 + 1] = j4;
                        while (true) {
                            i10--;
                            long j7 = jArr[i10];
                            if (j5 < j7) {
                                jArr[i10 + 1] = j7;
                            }
                        }
                        jArr[i10 + 1] = j5;
                        i3 = i9 + 1;
                    }
                    long j8 = jArr[i4];
                    while (true) {
                        i4--;
                        long j9 = jArr[i4];
                        if (j8 < j9) {
                            jArr[i4 + 1] = j9;
                        } else {
                            jArr[i4 + 1] = j8;
                            return;
                        }
                    }
                }
            }
            return;
        }
        int i11 = (i5 >> 3) + (i5 >> 6) + 1;
        int i12 = (i3 + i4) >>> 1;
        int i13 = i12 - i11;
        int i14 = i13 - i11;
        int i15 = i12 + i11;
        int i16 = i11 + i15;
        long j10 = jArr[i13];
        long j11 = jArr[i14];
        if (j10 < j11) {
            jArr[i13] = j11;
            jArr[i14] = j10;
        }
        long j12 = jArr[i12];
        long j13 = jArr[i13];
        if (j12 < j13) {
            jArr[i12] = j13;
            jArr[i13] = j12;
            long j14 = jArr[i14];
            if (j12 < j14) {
                jArr[i13] = j14;
                jArr[i14] = j12;
            }
        }
        long j15 = jArr[i15];
        long j16 = jArr[i12];
        if (j15 < j16) {
            jArr[i15] = j16;
            jArr[i12] = j15;
            long j17 = jArr[i13];
            if (j15 < j17) {
                jArr[i12] = j17;
                jArr[i13] = j15;
                long j18 = jArr[i14];
                if (j15 < j18) {
                    jArr[i13] = j18;
                    jArr[i14] = j15;
                }
            }
        }
        long j19 = jArr[i16];
        long j20 = jArr[i15];
        if (j19 < j20) {
            jArr[i16] = j20;
            jArr[i15] = j19;
            long j21 = jArr[i12];
            if (j19 < j21) {
                jArr[i15] = j21;
                jArr[i12] = j19;
                long j22 = jArr[i13];
                if (j19 < j22) {
                    jArr[i12] = j22;
                    jArr[i13] = j19;
                    long j23 = jArr[i14];
                    if (j19 < j23) {
                        jArr[i13] = j23;
                        jArr[i14] = j19;
                    }
                }
            }
        }
        long j24 = jArr[i14];
        long j25 = jArr[i13];
        if (j24 != j25) {
            long j26 = jArr[i12];
            if (j25 != j26) {
                long j27 = jArr[i15];
                if (j26 != j27 && j27 != jArr[i16]) {
                    jArr[i13] = jArr[i3];
                    jArr[i15] = jArr[i4];
                    int i17 = i3;
                    do {
                        i17++;
                    } while (jArr[i17] < j25);
                    int i18 = i4;
                    do {
                        i18--;
                    } while (jArr[i18] > j27);
                    int i19 = i17 - 1;
                    loop9: while (true) {
                        i19++;
                        if (i19 > i18) {
                            break;
                        }
                        long j28 = jArr[i19];
                        if (j28 < j25) {
                            jArr[i19] = jArr[i17];
                            jArr[i17] = j28;
                            i17++;
                        } else if (j28 > j27) {
                            while (true) {
                                long j29 = jArr[i18];
                                if (j29 > j27) {
                                    int i20 = i18 - 1;
                                    if (i18 == i19) {
                                        i18 = i20;
                                        break loop9;
                                    }
                                    i18 = i20;
                                } else {
                                    if (j29 < j25) {
                                        jArr[i19] = jArr[i17];
                                        jArr[i17] = jArr[i18];
                                        i17++;
                                    } else {
                                        jArr[i19] = j29;
                                    }
                                    jArr[i18] = j28;
                                    i18--;
                                }
                            }
                        } else {
                            continue;
                        }
                    }
                    int i21 = i17 - 1;
                    jArr[i3] = jArr[i21];
                    jArr[i21] = j25;
                    int i22 = i18 + 1;
                    jArr[i4] = jArr[i22];
                    jArr[i22] = j27;
                    sort(jArr, i3, i17 - 2, z);
                    sort(jArr, i18 + 2, i4, false);
                    if (i17 < i14 && i16 < i18) {
                        while (jArr[i17] == j25) {
                            i17++;
                        }
                        while (jArr[i18] == j27) {
                            i18--;
                        }
                        int i23 = i17 - 1;
                        loop13: while (true) {
                            i23++;
                            if (i23 > i18) {
                                break;
                            }
                            long j30 = jArr[i23];
                            if (j30 == j25) {
                                jArr[i23] = jArr[i17];
                                jArr[i17] = j30;
                                i17++;
                            } else if (j30 == j27) {
                                while (true) {
                                    long j31 = jArr[i18];
                                    if (j31 == j27) {
                                        int i24 = i18 - 1;
                                        if (i18 == i23) {
                                            i18 = i24;
                                            break loop13;
                                        }
                                        i18 = i24;
                                    } else {
                                        if (j31 == j25) {
                                            jArr[i23] = jArr[i17];
                                            jArr[i17] = j25;
                                            i17++;
                                        } else {
                                            jArr[i23] = j31;
                                        }
                                        jArr[i18] = j30;
                                        i18--;
                                    }
                                }
                            } else {
                                continue;
                            }
                        }
                    }
                    sort(jArr, i17, i18, false);
                    return;
                }
            }
        }
        long j32 = jArr[i12];
        int i25 = i3;
        int i26 = i25;
        int i27 = i4;
        while (i25 <= i27) {
            long j33 = jArr[i25];
            if (j33 != j32) {
                if (j33 < j32) {
                    jArr[i25] = jArr[i26];
                    jArr[i26] = j33;
                    i26++;
                } else {
                    while (true) {
                        j = jArr[i27];
                        if (j <= j32) {
                            break;
                        } else {
                            i27--;
                        }
                    }
                    if (j < j32) {
                        jArr[i25] = jArr[i26];
                        jArr[i26] = jArr[i27];
                        i26++;
                    } else {
                        jArr[i25] = j32;
                    }
                    jArr[i27] = j33;
                    i27--;
                }
            }
            i25++;
        }
        sort(jArr, i3, i26 - 1, z);
        sort(jArr, i27 + 1, i4, false);
    }

    static void sort(short[] sArr, int i, int i2, short[] sArr2, int i3, int i4) {
        int i5;
        if (i2 - i <= 3200) {
            doSort(sArr, i, i2, sArr2, i3, i4);
            return;
        }
        int i6 = 65536;
        int[] iArr = new int[65536];
        int i7 = i - 1;
        while (true) {
            i7++;
            if (i7 > i2) {
                break;
            }
            int i8 = sArr[i7] - ShortCompanionObject.MIN_VALUE;
            iArr[i8] = iArr[i8] + 1;
        }
        int i9 = i2 + 1;
        while (i9 > i) {
            do {
                i6--;
                i5 = iArr[i6];
            } while (i5 == 0);
            short s = (short) (i6 - 32768);
            do {
                i9--;
                sArr[i9] = s;
                i5--;
            } while (i5 > 0);
        }
    }

    private static void doSort(short[] sArr, int i, int i2, short[] sArr2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        short[] sArr3 = sArr;
        short[] sArr4 = sArr2;
        int i8 = 1;
        if (i2 - i < 286) {
            sort(sArr3, i, i2, true);
            return;
        }
        int[] iArr = new int[68];
        int i9 = 0;
        iArr[0] = i;
        int i10 = i;
        int i11 = 0;
        while (i10 < i2) {
            while (i10 < i2) {
                int i12 = i10 + 1;
                if (sArr3[i10] != sArr3[i12]) {
                    break;
                } else {
                    i10 = i12;
                }
            }
            if (i10 == i2) {
                break;
            }
            short s = sArr3[i10];
            short s2 = sArr3[i10 + 1];
            if (s < s2) {
                do {
                    i10++;
                    if (i10 > i2) {
                        break;
                    }
                } while (sArr3[i10 - 1] <= sArr3[i10]);
            } else if (s > s2) {
                do {
                    i10++;
                    if (i10 > i2) {
                        break;
                    }
                } while (sArr3[i10 - 1] >= sArr3[i10]);
                int i13 = iArr[i11] - 1;
                int i14 = i10;
                while (true) {
                    i13++;
                    i14--;
                    if (i13 >= i14) {
                        break;
                    }
                    short s3 = sArr3[i13];
                    sArr3[i13] = sArr3[i14];
                    sArr3[i14] = s3;
                }
            }
            int i15 = iArr[i11];
            if (i15 > i && sArr3[i15] >= sArr3[i15 - 1]) {
                i11--;
            }
            i11++;
            if (i11 != 67) {
                iArr[i11] = i10;
            } else {
                sort(sArr3, i, i2, true);
                return;
            }
        }
        if (i11 == 0) {
            return;
        }
        if (i11 != 1 || iArr[i11] <= i2) {
            int i16 = i2 + 1;
            if (iArr[i11] < i16) {
                i11++;
                iArr[i11] = i16;
            }
            byte b = 0;
            int i17 = 1;
            while (true) {
                i17 <<= 1;
                if (i17 >= i11) {
                    break;
                } else {
                    b = (byte) (b ^ 1);
                }
            }
            int i18 = i16 - i;
            if (sArr4 == null || i4 < i18 || i3 + i18 > sArr4.length) {
                sArr4 = new short[i18];
                i5 = 0;
            } else {
                i5 = i3;
            }
            if (b == 0) {
                System.arraycopy(sArr3, i, sArr4, i5, i18);
                i7 = i5 - i;
                i6 = 0;
                short[] sArr5 = sArr4;
                sArr4 = sArr3;
                sArr3 = sArr5;
            } else {
                i6 = i5 - i;
                i7 = 0;
            }
            while (i11 > i8) {
                int i19 = i9;
                for (int i20 = 2; i20 <= i11; i20 += 2) {
                    int i21 = iArr[i20];
                    int i22 = iArr[i20 - 1];
                    int i23 = iArr[i20 - 2];
                    int i24 = i22;
                    int i25 = i23;
                    while (i23 < i21) {
                        if (i24 >= i21 || (i25 < i22 && sArr3[i25 + i7] <= sArr3[i24 + i7])) {
                            sArr4[i23 + i6] = sArr3[i25 + i7];
                            i25++;
                        } else {
                            sArr4[i23 + i6] = sArr3[i24 + i7];
                            i24++;
                        }
                        i23++;
                    }
                    i19++;
                    iArr[i19] = i21;
                }
                if ((i11 & 1) != 0) {
                    int i26 = iArr[i11 - 1];
                    int i27 = i16;
                    while (true) {
                        i27--;
                        if (i27 < i26) {
                            break;
                        } else {
                            sArr4[i27 + i6] = sArr3[i27 + i7];
                        }
                    }
                    i19++;
                    iArr[i19] = i16;
                }
                i11 = i19;
                i9 = 0;
                i8 = 1;
                short[] sArr6 = sArr4;
                sArr4 = sArr3;
                sArr3 = sArr6;
                int i28 = i7;
                i7 = i6;
                i6 = i28;
            }
        }
    }

    private static void sort(short[] sArr, int i, int i2, boolean z) {
        short s;
        short s2;
        short s3;
        int i3 = (i2 - i) + 1;
        if (i3 < 47) {
            if (z) {
                int i4 = i;
                while (i4 < i2) {
                    int i5 = i4 + 1;
                    short s4 = sArr[i5];
                    while (true) {
                        short s5 = sArr[i4];
                        if (s4 < s5) {
                            sArr[i4 + 1] = s5;
                            int i6 = i4 - 1;
                            if (i4 == i) {
                                i4 = i6;
                                break;
                            }
                            i4 = i6;
                        }
                    }
                    sArr[i4 + 1] = s4;
                    i4 = i5;
                }
                return;
            }
            while (i < i2) {
                i++;
                if (sArr[i] < sArr[i - 1]) {
                    while (true) {
                        int i7 = i + 1;
                        if (i7 > i2) {
                            break;
                        }
                        short s6 = sArr[i];
                        short s7 = sArr[i7];
                        if (s6 < s7) {
                            s7 = s6;
                            s6 = s7;
                        }
                        while (true) {
                            i--;
                            short s8 = sArr[i];
                            if (s6 >= s8) {
                                break;
                            } else {
                                sArr[i + 2] = s8;
                            }
                        }
                        int i8 = i + 1;
                        sArr[i8 + 1] = s6;
                        while (true) {
                            i8--;
                            short s9 = sArr[i8];
                            if (s7 < s9) {
                                sArr[i8 + 1] = s9;
                            }
                        }
                        sArr[i8 + 1] = s7;
                        i = i7 + 1;
                    }
                    short s10 = sArr[i2];
                    while (true) {
                        i2--;
                        short s11 = sArr[i2];
                        if (s10 < s11) {
                            sArr[i2 + 1] = s11;
                        } else {
                            sArr[i2 + 1] = s10;
                            return;
                        }
                    }
                }
            }
            return;
        }
        int i9 = (i3 >> 3) + (i3 >> 6) + 1;
        int i10 = (i + i2) >>> 1;
        int i11 = i10 - i9;
        int i12 = i11 - i9;
        int i13 = i10 + i9;
        int i14 = i9 + i13;
        short s12 = sArr[i11];
        short s13 = sArr[i12];
        if (s12 < s13) {
            sArr[i11] = s13;
            sArr[i12] = s12;
        }
        short s14 = sArr[i10];
        short s15 = sArr[i11];
        if (s14 < s15) {
            sArr[i10] = s15;
            sArr[i11] = s14;
            short s16 = sArr[i12];
            if (s14 < s16) {
                sArr[i11] = s16;
                sArr[i12] = s14;
            }
        }
        short s17 = sArr[i13];
        short s18 = sArr[i10];
        if (s17 < s18) {
            sArr[i13] = s18;
            sArr[i10] = s17;
            short s19 = sArr[i11];
            if (s17 < s19) {
                sArr[i10] = s19;
                sArr[i11] = s17;
                short s20 = sArr[i12];
                if (s17 < s20) {
                    sArr[i11] = s20;
                    sArr[i12] = s17;
                }
            }
        }
        short s21 = sArr[i14];
        short s22 = sArr[i13];
        if (s21 < s22) {
            sArr[i14] = s22;
            sArr[i13] = s21;
            short s23 = sArr[i10];
            if (s21 < s23) {
                sArr[i13] = s23;
                sArr[i10] = s21;
                short s24 = sArr[i11];
                if (s21 < s24) {
                    sArr[i10] = s24;
                    sArr[i11] = s21;
                    short s25 = sArr[i12];
                    if (s21 < s25) {
                        sArr[i11] = s25;
                        sArr[i12] = s21;
                    }
                }
            }
        }
        short s26 = sArr[i12];
        short s27 = sArr[i11];
        if (s26 != s27 && s27 != (s2 = sArr[i10]) && s2 != (s3 = sArr[i13]) && s3 != sArr[i14]) {
            sArr[i11] = sArr[i];
            sArr[i13] = sArr[i2];
            int i15 = i;
            do {
                i15++;
            } while (sArr[i15] < s27);
            int i16 = i2;
            do {
                i16--;
            } while (sArr[i16] > s3);
            int i17 = i15 - 1;
            loop9: while (true) {
                i17++;
                if (i17 > i16) {
                    break;
                }
                short s28 = sArr[i17];
                if (s28 < s27) {
                    sArr[i17] = sArr[i15];
                    sArr[i15] = s28;
                    i15++;
                } else if (s28 > s3) {
                    while (true) {
                        short s29 = sArr[i16];
                        if (s29 > s3) {
                            int i18 = i16 - 1;
                            if (i16 == i17) {
                                i16 = i18;
                                break loop9;
                            }
                            i16 = i18;
                        } else {
                            if (s29 < s27) {
                                sArr[i17] = sArr[i15];
                                sArr[i15] = sArr[i16];
                                i15++;
                            } else {
                                sArr[i17] = s29;
                            }
                            sArr[i16] = s28;
                            i16--;
                        }
                    }
                } else {
                    continue;
                }
            }
            int i19 = i15 - 1;
            sArr[i] = sArr[i19];
            sArr[i19] = s27;
            int i20 = i16 + 1;
            sArr[i2] = sArr[i20];
            sArr[i20] = s3;
            sort(sArr, i, i15 - 2, z);
            sort(sArr, i16 + 2, i2, false);
            if (i15 < i12 && i14 < i16) {
                while (sArr[i15] == s27) {
                    i15++;
                }
                while (sArr[i16] == s3) {
                    i16--;
                }
                int i21 = i15 - 1;
                loop13: while (true) {
                    i21++;
                    if (i21 > i16) {
                        break;
                    }
                    short s30 = sArr[i21];
                    if (s30 == s27) {
                        sArr[i21] = sArr[i15];
                        sArr[i15] = s30;
                        i15++;
                    } else if (s30 == s3) {
                        while (true) {
                            short s31 = sArr[i16];
                            if (s31 == s3) {
                                int i22 = i16 - 1;
                                if (i16 == i21) {
                                    i16 = i22;
                                    break loop13;
                                }
                                i16 = i22;
                            } else {
                                if (s31 == s27) {
                                    sArr[i21] = sArr[i15];
                                    sArr[i15] = s27;
                                    i15++;
                                } else {
                                    sArr[i21] = s31;
                                }
                                sArr[i16] = s30;
                                i16--;
                            }
                        }
                    } else {
                        continue;
                    }
                }
            }
            sort(sArr, i15, i16, false);
            return;
        }
        short s32 = sArr[i10];
        int i23 = i;
        int i24 = i23;
        int i25 = i2;
        while (i23 <= i25) {
            short s33 = sArr[i23];
            if (s33 != s32) {
                if (s33 < s32) {
                    sArr[i23] = sArr[i24];
                    sArr[i24] = s33;
                    i24++;
                } else {
                    while (true) {
                        s = sArr[i25];
                        if (s <= s32) {
                            break;
                        } else {
                            i25--;
                        }
                    }
                    if (s < s32) {
                        sArr[i23] = sArr[i24];
                        sArr[i24] = sArr[i25];
                        i24++;
                    } else {
                        sArr[i23] = s32;
                    }
                    sArr[i25] = s33;
                    i25--;
                }
            }
            i23++;
        }
        sort(sArr, i, i24 - 1, z);
        sort(sArr, i25 + 1, i2, false);
    }

    static void sort(char[] cArr, int i, int i2, char[] cArr2, int i3, int i4) {
        int i5;
        if (i2 - i <= 3200) {
            doSort(cArr, i, i2, cArr2, i3, i4);
            return;
        }
        int i6 = 65536;
        int[] iArr = new int[65536];
        int i7 = i - 1;
        while (true) {
            i7++;
            if (i7 > i2) {
                break;
            }
            char c = cArr[i7];
            iArr[c] = iArr[c] + 1;
        }
        int i8 = i2 + 1;
        while (i8 > i) {
            do {
                i6--;
                i5 = iArr[i6];
            } while (i5 == 0);
            char c2 = (char) i6;
            do {
                i8--;
                cArr[i8] = c2;
                i5--;
            } while (i5 > 0);
        }
    }

    private static void doSort(char[] cArr, int i, int i2, char[] cArr2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        char[] cArr3 = cArr;
        char[] cArr4 = cArr2;
        int i8 = 1;
        if (i2 - i < 286) {
            sort(cArr3, i, i2, true);
            return;
        }
        int[] iArr = new int[68];
        int i9 = 0;
        iArr[0] = i;
        int i10 = i;
        int i11 = 0;
        while (i10 < i2) {
            while (i10 < i2) {
                int i12 = i10 + 1;
                if (cArr3[i10] != cArr3[i12]) {
                    break;
                } else {
                    i10 = i12;
                }
            }
            if (i10 == i2) {
                break;
            }
            char c = cArr3[i10];
            char c2 = cArr3[i10 + 1];
            if (c < c2) {
                do {
                    i10++;
                    if (i10 > i2) {
                        break;
                    }
                } while (cArr3[i10 - 1] <= cArr3[i10]);
            } else if (c > c2) {
                do {
                    i10++;
                    if (i10 > i2) {
                        break;
                    }
                } while (cArr3[i10 - 1] >= cArr3[i10]);
                int i13 = iArr[i11] - 1;
                int i14 = i10;
                while (true) {
                    i13++;
                    i14--;
                    if (i13 >= i14) {
                        break;
                    }
                    char c3 = cArr3[i13];
                    cArr3[i13] = cArr3[i14];
                    cArr3[i14] = c3;
                }
            }
            int i15 = iArr[i11];
            if (i15 > i && cArr3[i15] >= cArr3[i15 - 1]) {
                i11--;
            }
            i11++;
            if (i11 != 67) {
                iArr[i11] = i10;
            } else {
                sort(cArr3, i, i2, true);
                return;
            }
        }
        if (i11 == 0) {
            return;
        }
        if (i11 != 1 || iArr[i11] <= i2) {
            int i16 = i2 + 1;
            if (iArr[i11] < i16) {
                i11++;
                iArr[i11] = i16;
            }
            byte b = 0;
            int i17 = 1;
            while (true) {
                i17 <<= 1;
                if (i17 >= i11) {
                    break;
                } else {
                    b = (byte) (b ^ 1);
                }
            }
            int i18 = i16 - i;
            if (cArr4 == null || i4 < i18 || i3 + i18 > cArr4.length) {
                cArr4 = new char[i18];
                i5 = 0;
            } else {
                i5 = i3;
            }
            if (b == 0) {
                System.arraycopy(cArr3, i, cArr4, i5, i18);
                i7 = i5 - i;
                i6 = 0;
                char[] cArr5 = cArr4;
                cArr4 = cArr3;
                cArr3 = cArr5;
            } else {
                i6 = i5 - i;
                i7 = 0;
            }
            while (i11 > i8) {
                int i19 = i9;
                for (int i20 = 2; i20 <= i11; i20 += 2) {
                    int i21 = iArr[i20];
                    int i22 = iArr[i20 - 1];
                    int i23 = iArr[i20 - 2];
                    int i24 = i22;
                    int i25 = i23;
                    while (i23 < i21) {
                        if (i24 >= i21 || (i25 < i22 && cArr3[i25 + i7] <= cArr3[i24 + i7])) {
                            cArr4[i23 + i6] = cArr3[i25 + i7];
                            i25++;
                        } else {
                            cArr4[i23 + i6] = cArr3[i24 + i7];
                            i24++;
                        }
                        i23++;
                    }
                    i19++;
                    iArr[i19] = i21;
                }
                if ((i11 & 1) != 0) {
                    int i26 = iArr[i11 - 1];
                    int i27 = i16;
                    while (true) {
                        i27--;
                        if (i27 < i26) {
                            break;
                        } else {
                            cArr4[i27 + i6] = cArr3[i27 + i7];
                        }
                    }
                    i19++;
                    iArr[i19] = i16;
                }
                i11 = i19;
                i9 = 0;
                i8 = 1;
                char[] cArr6 = cArr4;
                cArr4 = cArr3;
                cArr3 = cArr6;
                int i28 = i7;
                i7 = i6;
                i6 = i28;
            }
        }
    }

    private static void sort(char[] cArr, int i, int i2, boolean z) {
        char c;
        char c2;
        char c3;
        int i3 = (i2 - i) + 1;
        if (i3 < 47) {
            if (z) {
                int i4 = i;
                while (i4 < i2) {
                    int i5 = i4 + 1;
                    char c4 = cArr[i5];
                    while (true) {
                        char c5 = cArr[i4];
                        if (c4 < c5) {
                            cArr[i4 + 1] = c5;
                            int i6 = i4 - 1;
                            if (i4 == i) {
                                i4 = i6;
                                break;
                            }
                            i4 = i6;
                        }
                    }
                    cArr[i4 + 1] = c4;
                    i4 = i5;
                }
                return;
            }
            while (i < i2) {
                i++;
                if (cArr[i] < cArr[i - 1]) {
                    while (true) {
                        int i7 = i + 1;
                        if (i7 > i2) {
                            break;
                        }
                        char c6 = cArr[i];
                        char c7 = cArr[i7];
                        if (c6 < c7) {
                            c7 = c6;
                            c6 = c7;
                        }
                        while (true) {
                            i--;
                            char c8 = cArr[i];
                            if (c6 >= c8) {
                                break;
                            } else {
                                cArr[i + 2] = c8;
                            }
                        }
                        int i8 = i + 1;
                        cArr[i8 + 1] = c6;
                        while (true) {
                            i8--;
                            char c9 = cArr[i8];
                            if (c7 < c9) {
                                cArr[i8 + 1] = c9;
                            }
                        }
                        cArr[i8 + 1] = c7;
                        i = i7 + 1;
                    }
                    char c10 = cArr[i2];
                    while (true) {
                        i2--;
                        char c11 = cArr[i2];
                        if (c10 < c11) {
                            cArr[i2 + 1] = c11;
                        } else {
                            cArr[i2 + 1] = c10;
                            return;
                        }
                    }
                }
            }
            return;
        }
        int i9 = (i3 >> 3) + (i3 >> 6) + 1;
        int i10 = (i + i2) >>> 1;
        int i11 = i10 - i9;
        int i12 = i11 - i9;
        int i13 = i10 + i9;
        int i14 = i9 + i13;
        char c12 = cArr[i11];
        char c13 = cArr[i12];
        if (c12 < c13) {
            cArr[i11] = c13;
            cArr[i12] = c12;
        }
        char c14 = cArr[i10];
        char c15 = cArr[i11];
        if (c14 < c15) {
            cArr[i10] = c15;
            cArr[i11] = c14;
            char c16 = cArr[i12];
            if (c14 < c16) {
                cArr[i11] = c16;
                cArr[i12] = c14;
            }
        }
        char c17 = cArr[i13];
        char c18 = cArr[i10];
        if (c17 < c18) {
            cArr[i13] = c18;
            cArr[i10] = c17;
            char c19 = cArr[i11];
            if (c17 < c19) {
                cArr[i10] = c19;
                cArr[i11] = c17;
                char c20 = cArr[i12];
                if (c17 < c20) {
                    cArr[i11] = c20;
                    cArr[i12] = c17;
                }
            }
        }
        char c21 = cArr[i14];
        char c22 = cArr[i13];
        if (c21 < c22) {
            cArr[i14] = c22;
            cArr[i13] = c21;
            char c23 = cArr[i10];
            if (c21 < c23) {
                cArr[i13] = c23;
                cArr[i10] = c21;
                char c24 = cArr[i11];
                if (c21 < c24) {
                    cArr[i10] = c24;
                    cArr[i11] = c21;
                    char c25 = cArr[i12];
                    if (c21 < c25) {
                        cArr[i11] = c25;
                        cArr[i12] = c21;
                    }
                }
            }
        }
        char c26 = cArr[i12];
        char c27 = cArr[i11];
        if (c26 != c27 && c27 != (c2 = cArr[i10]) && c2 != (c3 = cArr[i13]) && c3 != cArr[i14]) {
            cArr[i11] = cArr[i];
            cArr[i13] = cArr[i2];
            int i15 = i;
            do {
                i15++;
            } while (cArr[i15] < c27);
            int i16 = i2;
            do {
                i16--;
            } while (cArr[i16] > c3);
            int i17 = i15 - 1;
            loop9: while (true) {
                i17++;
                if (i17 > i16) {
                    break;
                }
                char c28 = cArr[i17];
                if (c28 < c27) {
                    cArr[i17] = cArr[i15];
                    cArr[i15] = c28;
                    i15++;
                } else if (c28 > c3) {
                    while (true) {
                        char c29 = cArr[i16];
                        if (c29 > c3) {
                            int i18 = i16 - 1;
                            if (i16 == i17) {
                                i16 = i18;
                                break loop9;
                            }
                            i16 = i18;
                        } else {
                            if (c29 < c27) {
                                cArr[i17] = cArr[i15];
                                cArr[i15] = cArr[i16];
                                i15++;
                            } else {
                                cArr[i17] = c29;
                            }
                            cArr[i16] = c28;
                            i16--;
                        }
                    }
                } else {
                    continue;
                }
            }
            int i19 = i15 - 1;
            cArr[i] = cArr[i19];
            cArr[i19] = c27;
            int i20 = i16 + 1;
            cArr[i2] = cArr[i20];
            cArr[i20] = c3;
            sort(cArr, i, i15 - 2, z);
            sort(cArr, i16 + 2, i2, false);
            if (i15 < i12 && i14 < i16) {
                while (cArr[i15] == c27) {
                    i15++;
                }
                while (cArr[i16] == c3) {
                    i16--;
                }
                int i21 = i15 - 1;
                loop13: while (true) {
                    i21++;
                    if (i21 > i16) {
                        break;
                    }
                    char c30 = cArr[i21];
                    if (c30 == c27) {
                        cArr[i21] = cArr[i15];
                        cArr[i15] = c30;
                        i15++;
                    } else if (c30 == c3) {
                        while (true) {
                            char c31 = cArr[i16];
                            if (c31 == c3) {
                                int i22 = i16 - 1;
                                if (i16 == i21) {
                                    i16 = i22;
                                    break loop13;
                                }
                                i16 = i22;
                            } else {
                                if (c31 == c27) {
                                    cArr[i21] = cArr[i15];
                                    cArr[i15] = c27;
                                    i15++;
                                } else {
                                    cArr[i21] = c31;
                                }
                                cArr[i16] = c30;
                                i16--;
                            }
                        }
                    } else {
                        continue;
                    }
                }
            }
            sort(cArr, i15, i16, false);
            return;
        }
        char c32 = cArr[i10];
        int i23 = i;
        int i24 = i23;
        int i25 = i2;
        while (i23 <= i25) {
            char c33 = cArr[i23];
            if (c33 != c32) {
                if (c33 < c32) {
                    cArr[i23] = cArr[i24];
                    cArr[i24] = c33;
                    i24++;
                } else {
                    while (true) {
                        c = cArr[i25];
                        if (c <= c32) {
                            break;
                        } else {
                            i25--;
                        }
                    }
                    if (c < c32) {
                        cArr[i23] = cArr[i24];
                        cArr[i24] = cArr[i25];
                        i24++;
                    } else {
                        cArr[i23] = c32;
                    }
                    cArr[i25] = c33;
                    i25--;
                }
            }
            i23++;
        }
        sort(cArr, i, i24 - 1, z);
        sort(cArr, i25 + 1, i2, false);
    }

    static void sort(byte[] bArr, int i, int i2) {
        int i3;
        if (i2 - i <= 29) {
            int i4 = i;
            while (i4 < i2) {
                int i5 = i4 + 1;
                byte b = bArr[i5];
                while (true) {
                    byte b2 = bArr[i4];
                    if (b < b2) {
                        bArr[i4 + 1] = b2;
                        int i6 = i4 - 1;
                        if (i4 == i) {
                            i4 = i6;
                            break;
                        }
                        i4 = i6;
                    }
                }
                bArr[i4 + 1] = b;
                i4 = i5;
            }
            return;
        }
        int i7 = 256;
        int[] iArr = new int[256];
        int i8 = i - 1;
        while (true) {
            i8++;
            if (i8 > i2) {
                break;
            }
            int i9 = bArr[i8] + ByteCompanionObject.MIN_VALUE;
            iArr[i9] = iArr[i9] + 1;
        }
        int i10 = i2 + 1;
        while (i10 > i) {
            do {
                i7--;
                i3 = iArr[i7];
            } while (i3 == 0);
            byte b3 = (byte) (i7 - 128);
            do {
                i10--;
                bArr[i10] = b3;
                i3--;
            } while (i3 > 0);
        }
    }

    static void sort(float[] fArr, int i, int i2, float[] fArr2, int i3, int i4) {
        while (i <= i2 && Float.isNaN(fArr[i2])) {
            i2--;
        }
        int i5 = i2;
        while (true) {
            i2--;
            if (i2 < i) {
                break;
            }
            float f = fArr[i2];
            if (f != f) {
                fArr[i2] = fArr[i5];
                fArr[i5] = f;
                i5--;
            }
        }
        doSort(fArr, i, i5, fArr2, i3, i4);
        int i6 = i5;
        while (i < i6) {
            int i7 = (i + i6) >>> 1;
            if (fArr[i7] < 0.0f) {
                i = i7 + 1;
            } else {
                i6 = i7;
            }
        }
        while (i <= i5 && Float.floatToRawIntBits(fArr[i]) < 0) {
            i++;
        }
        int i8 = i - 1;
        while (true) {
            i++;
            if (i > i5) {
                return;
            }
            float f2 = fArr[i];
            if (f2 != 0.0f) {
                return;
            }
            if (Float.floatToRawIntBits(f2) < 0) {
                fArr[i] = 0.0f;
                i8++;
                fArr[i8] = -0.0f;
            }
        }
    }

    private static void doSort(float[] fArr, int i, int i2, float[] fArr2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        float[] fArr3 = fArr;
        float[] fArr4 = fArr2;
        if (i2 - i < 286) {
            sort(fArr3, i, i2, true);
            return;
        }
        int[] iArr = new int[68];
        iArr[0] = i;
        int i8 = i;
        int i9 = 0;
        while (i8 < i2) {
            while (i8 < i2) {
                int i10 = i8 + 1;
                if (fArr3[i8] != fArr3[i10]) {
                    break;
                } else {
                    i8 = i10;
                }
            }
            if (i8 == i2) {
                break;
            }
            float f = fArr3[i8];
            float f2 = fArr3[i8 + 1];
            if (f < f2) {
                do {
                    i8++;
                    if (i8 > i2) {
                        break;
                    }
                } while (fArr3[i8 - 1] <= fArr3[i8]);
            } else if (f > f2) {
                do {
                    i8++;
                    if (i8 > i2) {
                        break;
                    }
                } while (fArr3[i8 - 1] >= fArr3[i8]);
                int i11 = iArr[i9] - 1;
                int i12 = i8;
                while (true) {
                    i11++;
                    i12--;
                    if (i11 >= i12) {
                        break;
                    }
                    float f3 = fArr3[i11];
                    fArr3[i11] = fArr3[i12];
                    fArr3[i12] = f3;
                }
            }
            int i13 = iArr[i9];
            if (i13 > i && fArr3[i13] >= fArr3[i13 - 1]) {
                i9--;
            }
            i9++;
            if (i9 != 67) {
                iArr[i9] = i8;
            } else {
                sort(fArr3, i, i2, true);
                return;
            }
        }
        if (i9 == 0) {
            return;
        }
        if (i9 != 1 || iArr[i9] <= i2) {
            int i14 = i2 + 1;
            if (iArr[i9] < i14) {
                i9++;
                iArr[i9] = i14;
            }
            byte b = 0;
            int i15 = 1;
            while (true) {
                i15 <<= 1;
                if (i15 >= i9) {
                    break;
                } else {
                    b = (byte) (b ^ 1);
                }
            }
            int i16 = i14 - i;
            if (fArr4 == null || i4 < i16 || i3 + i16 > fArr4.length) {
                fArr4 = new float[i16];
                i5 = 0;
            } else {
                i5 = i3;
            }
            if (b == 0) {
                System.arraycopy(fArr3, i, fArr4, i5, i16);
                i7 = i5 - i;
                i6 = 0;
                float[] fArr5 = fArr4;
                fArr4 = fArr3;
                fArr3 = fArr5;
            } else {
                i6 = i5 - i;
                i7 = 0;
            }
            while (i9 > 1) {
                int i17 = 0;
                for (int i18 = 2; i18 <= i9; i18 += 2) {
                    int i19 = iArr[i18];
                    int i20 = iArr[i18 - 1];
                    int i21 = iArr[i18 - 2];
                    int i22 = i20;
                    int i23 = i21;
                    while (i21 < i19) {
                        if (i22 >= i19 || (i23 < i20 && fArr3[i23 + i7] <= fArr3[i22 + i7])) {
                            fArr4[i21 + i6] = fArr3[i23 + i7];
                            i23++;
                        } else {
                            fArr4[i21 + i6] = fArr3[i22 + i7];
                            i22++;
                        }
                        i21++;
                    }
                    i17++;
                    iArr[i17] = i19;
                }
                if ((i9 & 1) != 0) {
                    int i24 = iArr[i9 - 1];
                    int i25 = i14;
                    while (true) {
                        i25--;
                        if (i25 < i24) {
                            break;
                        } else {
                            fArr4[i25 + i6] = fArr3[i25 + i7];
                        }
                    }
                    i17++;
                    iArr[i17] = i14;
                }
                i9 = i17;
                float[] fArr6 = fArr4;
                fArr4 = fArr3;
                fArr3 = fArr6;
                int i26 = i7;
                i7 = i6;
                i6 = i26;
            }
        }
    }

    private static void sort(float[] fArr, int i, int i2, boolean z) {
        float f;
        int i3 = (i2 - i) + 1;
        if (i3 < 47) {
            if (z) {
                int i4 = i;
                while (i4 < i2) {
                    int i5 = i4 + 1;
                    float f2 = fArr[i5];
                    while (true) {
                        float f3 = fArr[i4];
                        if (f2 < f3) {
                            fArr[i4 + 1] = f3;
                            int i6 = i4 - 1;
                            if (i4 == i) {
                                i4 = i6;
                                break;
                            }
                            i4 = i6;
                        }
                    }
                    fArr[i4 + 1] = f2;
                    i4 = i5;
                }
                return;
            }
            while (i < i2) {
                i++;
                if (fArr[i] < fArr[i - 1]) {
                    while (true) {
                        int i7 = i + 1;
                        if (i7 > i2) {
                            break;
                        }
                        float f4 = fArr[i];
                        float f5 = fArr[i7];
                        if (f4 < f5) {
                            f5 = f4;
                            f4 = f5;
                        }
                        while (true) {
                            i--;
                            float f6 = fArr[i];
                            if (f4 >= f6) {
                                break;
                            } else {
                                fArr[i + 2] = f6;
                            }
                        }
                        int i8 = i + 1;
                        fArr[i8 + 1] = f4;
                        while (true) {
                            i8--;
                            float f7 = fArr[i8];
                            if (f5 < f7) {
                                fArr[i8 + 1] = f7;
                            }
                        }
                        fArr[i8 + 1] = f5;
                        i = i7 + 1;
                    }
                    float f8 = fArr[i2];
                    while (true) {
                        i2--;
                        float f9 = fArr[i2];
                        if (f8 < f9) {
                            fArr[i2 + 1] = f9;
                        } else {
                            fArr[i2 + 1] = f8;
                            return;
                        }
                    }
                }
            }
            return;
        }
        int i9 = (i3 >> 3) + (i3 >> 6) + 1;
        int i10 = (i + i2) >>> 1;
        int i11 = i10 - i9;
        int i12 = i11 - i9;
        int i13 = i10 + i9;
        int i14 = i9 + i13;
        float f10 = fArr[i11];
        float f11 = fArr[i12];
        if (f10 < f11) {
            fArr[i11] = f11;
            fArr[i12] = f10;
        }
        float f12 = fArr[i10];
        float f13 = fArr[i11];
        if (f12 < f13) {
            fArr[i10] = f13;
            fArr[i11] = f12;
            float f14 = fArr[i12];
            if (f12 < f14) {
                fArr[i11] = f14;
                fArr[i12] = f12;
            }
        }
        float f15 = fArr[i13];
        float f16 = fArr[i10];
        if (f15 < f16) {
            fArr[i13] = f16;
            fArr[i10] = f15;
            float f17 = fArr[i11];
            if (f15 < f17) {
                fArr[i10] = f17;
                fArr[i11] = f15;
                float f18 = fArr[i12];
                if (f15 < f18) {
                    fArr[i11] = f18;
                    fArr[i12] = f15;
                }
            }
        }
        float f19 = fArr[i14];
        float f20 = fArr[i13];
        if (f19 < f20) {
            fArr[i14] = f20;
            fArr[i13] = f19;
            float f21 = fArr[i10];
            if (f19 < f21) {
                fArr[i13] = f21;
                fArr[i10] = f19;
                float f22 = fArr[i11];
                if (f19 < f22) {
                    fArr[i10] = f22;
                    fArr[i11] = f19;
                    float f23 = fArr[i12];
                    if (f19 < f23) {
                        fArr[i11] = f23;
                        fArr[i12] = f19;
                    }
                }
            }
        }
        float f24 = fArr[i12];
        float f25 = fArr[i11];
        if (f24 != f25) {
            float f26 = fArr[i10];
            if (f25 != f26) {
                float f27 = fArr[i13];
                if (f26 != f27 && f27 != fArr[i14]) {
                    fArr[i11] = fArr[i];
                    fArr[i13] = fArr[i2];
                    int i15 = i;
                    do {
                        i15++;
                    } while (fArr[i15] < f25);
                    int i16 = i2;
                    do {
                        i16--;
                    } while (fArr[i16] > f27);
                    int i17 = i15 - 1;
                    loop9: while (true) {
                        i17++;
                        if (i17 > i16) {
                            break;
                        }
                        float f28 = fArr[i17];
                        if (f28 < f25) {
                            fArr[i17] = fArr[i15];
                            fArr[i15] = f28;
                            i15++;
                        } else if (f28 > f27) {
                            while (true) {
                                float f29 = fArr[i16];
                                if (f29 > f27) {
                                    int i18 = i16 - 1;
                                    if (i16 == i17) {
                                        i16 = i18;
                                        break loop9;
                                    }
                                    i16 = i18;
                                } else {
                                    if (f29 < f25) {
                                        fArr[i17] = fArr[i15];
                                        fArr[i15] = fArr[i16];
                                        i15++;
                                    } else {
                                        fArr[i17] = f29;
                                    }
                                    fArr[i16] = f28;
                                    i16--;
                                }
                            }
                        } else {
                            continue;
                        }
                    }
                    int i19 = i15 - 1;
                    fArr[i] = fArr[i19];
                    fArr[i19] = f25;
                    int i20 = i16 + 1;
                    fArr[i2] = fArr[i20];
                    fArr[i20] = f27;
                    sort(fArr, i, i15 - 2, z);
                    sort(fArr, i16 + 2, i2, false);
                    if (i15 < i12 && i14 < i16) {
                        while (fArr[i15] == f25) {
                            i15++;
                        }
                        while (fArr[i16] == f27) {
                            i16--;
                        }
                        int i21 = i15 - 1;
                        loop13: while (true) {
                            i21++;
                            if (i21 > i16) {
                                break;
                            }
                            float f30 = fArr[i21];
                            if (f30 == f25) {
                                fArr[i21] = fArr[i15];
                                fArr[i15] = f30;
                                i15++;
                            } else if (f30 == f27) {
                                while (true) {
                                    float f31 = fArr[i16];
                                    if (f31 == f27) {
                                        int i22 = i16 - 1;
                                        if (i16 == i21) {
                                            i16 = i22;
                                            break loop13;
                                        }
                                        i16 = i22;
                                    } else {
                                        if (f31 == f25) {
                                            fArr[i21] = fArr[i15];
                                            fArr[i15] = fArr[i16];
                                            i15++;
                                        } else {
                                            fArr[i21] = f31;
                                        }
                                        fArr[i16] = f30;
                                        i16--;
                                    }
                                }
                            } else {
                                continue;
                            }
                        }
                    }
                    sort(fArr, i15, i16, false);
                    return;
                }
            }
        }
        float f32 = fArr[i10];
        int i23 = i;
        int i24 = i23;
        int i25 = i2;
        while (i23 <= i25) {
            float f33 = fArr[i23];
            if (f33 != f32) {
                if (f33 < f32) {
                    fArr[i23] = fArr[i24];
                    fArr[i24] = f33;
                    i24++;
                } else {
                    while (true) {
                        f = fArr[i25];
                        if (f <= f32) {
                            break;
                        } else {
                            i25--;
                        }
                    }
                    if (f < f32) {
                        fArr[i23] = fArr[i24];
                        fArr[i24] = fArr[i25];
                        i24++;
                    } else {
                        fArr[i23] = f;
                    }
                    fArr[i25] = f33;
                    i25--;
                }
            }
            i23++;
        }
        sort(fArr, i, i24 - 1, z);
        sort(fArr, i25 + 1, i2, false);
    }

    static void sort(double[] dArr, int i, int i2, double[] dArr2, int i3, int i4) {
        while (i <= i2 && Double.isNaN(dArr[i2])) {
            i2--;
        }
        int i5 = i2;
        while (true) {
            i2--;
            if (i2 < i) {
                break;
            }
            double d = dArr[i2];
            if (d != d) {
                dArr[i2] = dArr[i5];
                dArr[i5] = d;
                i5--;
            }
        }
        doSort(dArr, i, i5, dArr2, i3, i4);
        int i6 = i5;
        while (i < i6) {
            int i7 = (i + i6) >>> 1;
            if (dArr[i7] < 0.0d) {
                i = i7 + 1;
            } else {
                i6 = i7;
            }
        }
        while (i <= i5 && Double.doubleToRawLongBits(dArr[i]) < 0) {
            i++;
        }
        int i8 = i - 1;
        while (true) {
            i++;
            if (i > i5) {
                return;
            }
            double d2 = dArr[i];
            if (d2 != 0.0d) {
                return;
            }
            if (Double.doubleToRawLongBits(d2) < 0) {
                dArr[i] = 0.0d;
                i8++;
                dArr[i8] = -0.0d;
            }
        }
    }

    private static void doSort(double[] dArr, int i, int i2, double[] dArr2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        double[] dArr3 = dArr;
        double[] dArr4 = dArr2;
        if (i2 - i < 286) {
            sort(dArr3, i, i2, true);
            return;
        }
        int[] iArr = new int[68];
        iArr[0] = i;
        int i8 = i;
        int i9 = 0;
        while (i8 < i2) {
            while (i8 < i2) {
                int i10 = i8 + 1;
                if (dArr3[i8] != dArr3[i10]) {
                    break;
                } else {
                    i8 = i10;
                }
            }
            if (i8 == i2) {
                break;
            }
            double d = dArr3[i8];
            double d2 = dArr3[i8 + 1];
            if (d < d2) {
                do {
                    i8++;
                    if (i8 > i2) {
                        break;
                    }
                } while (dArr3[i8 - 1] <= dArr3[i8]);
            } else if (d > d2) {
                do {
                    i8++;
                    if (i8 > i2) {
                        break;
                    }
                } while (dArr3[i8 - 1] >= dArr3[i8]);
                int i11 = iArr[i9] - 1;
                int i12 = i8;
                while (true) {
                    i11++;
                    i12--;
                    if (i11 >= i12) {
                        break;
                    }
                    double d3 = dArr3[i11];
                    dArr3[i11] = dArr3[i12];
                    dArr3[i12] = d3;
                }
            }
            int i13 = iArr[i9];
            if (i13 > i && dArr3[i13] >= dArr3[i13 - 1]) {
                i9--;
            }
            i9++;
            if (i9 != 67) {
                iArr[i9] = i8;
            } else {
                sort(dArr3, i, i2, true);
                return;
            }
        }
        if (i9 == 0) {
            return;
        }
        if (i9 != 1 || iArr[i9] <= i2) {
            int i14 = i2 + 1;
            if (iArr[i9] < i14) {
                i9++;
                iArr[i9] = i14;
            }
            byte b = 0;
            int i15 = 1;
            while (true) {
                i15 <<= 1;
                if (i15 >= i9) {
                    break;
                } else {
                    b = (byte) (b ^ 1);
                }
            }
            int i16 = i14 - i;
            if (dArr4 == null || i4 < i16 || i3 + i16 > dArr4.length) {
                dArr4 = new double[i16];
                i5 = 0;
            } else {
                i5 = i3;
            }
            if (b == 0) {
                System.arraycopy(dArr3, i, dArr4, i5, i16);
                i7 = i5 - i;
                i6 = 0;
                double[] dArr5 = dArr4;
                dArr4 = dArr3;
                dArr3 = dArr5;
            } else {
                i6 = i5 - i;
                i7 = 0;
            }
            while (i9 > 1) {
                int i17 = 0;
                for (int i18 = 2; i18 <= i9; i18 += 2) {
                    int i19 = iArr[i18];
                    int i20 = iArr[i18 - 1];
                    int i21 = iArr[i18 - 2];
                    int i22 = i20;
                    int i23 = i21;
                    while (i21 < i19) {
                        if (i22 >= i19 || (i23 < i20 && dArr3[i23 + i7] <= dArr3[i22 + i7])) {
                            dArr4[i21 + i6] = dArr3[i23 + i7];
                            i23++;
                        } else {
                            dArr4[i21 + i6] = dArr3[i22 + i7];
                            i22++;
                        }
                        i21++;
                    }
                    i17++;
                    iArr[i17] = i19;
                }
                if ((i9 & 1) != 0) {
                    int i24 = iArr[i9 - 1];
                    int i25 = i14;
                    while (true) {
                        i25--;
                        if (i25 < i24) {
                            break;
                        } else {
                            dArr4[i25 + i6] = dArr3[i25 + i7];
                        }
                    }
                    i17++;
                    iArr[i17] = i14;
                }
                i9 = i17;
                double[] dArr6 = dArr4;
                dArr4 = dArr3;
                dArr3 = dArr6;
                int i26 = i7;
                i7 = i6;
                i6 = i26;
            }
        }
    }

    private static void sort(double[] dArr, int i, int i2, boolean z) {
        double d;
        int i3 = i;
        int i4 = i2;
        int i5 = (i4 - i3) + 1;
        if (i5 < 47) {
            if (z) {
                int i6 = i3;
                while (i6 < i4) {
                    int i7 = i6 + 1;
                    double d2 = dArr[i7];
                    while (true) {
                        double d3 = dArr[i6];
                        if (d2 < d3) {
                            dArr[i6 + 1] = d3;
                            int i8 = i6 - 1;
                            if (i6 == i3) {
                                i6 = i8;
                                break;
                            }
                            i6 = i8;
                        }
                    }
                    dArr[i6 + 1] = d2;
                    i6 = i7;
                }
                return;
            }
            while (i3 < i4) {
                i3++;
                if (dArr[i3] < dArr[i3 - 1]) {
                    while (true) {
                        int i9 = i3 + 1;
                        if (i9 > i4) {
                            break;
                        }
                        double d4 = dArr[i3];
                        double d5 = dArr[i9];
                        if (d4 < d5) {
                            d4 = d5;
                            d5 = d4;
                        }
                        while (true) {
                            i3--;
                            double d6 = dArr[i3];
                            if (d4 >= d6) {
                                break;
                            } else {
                                dArr[i3 + 2] = d6;
                            }
                        }
                        int i10 = i3 + 1;
                        dArr[i10 + 1] = d4;
                        while (true) {
                            i10--;
                            double d7 = dArr[i10];
                            if (d5 < d7) {
                                dArr[i10 + 1] = d7;
                            }
                        }
                        dArr[i10 + 1] = d5;
                        i3 = i9 + 1;
                    }
                    double d8 = dArr[i4];
                    while (true) {
                        i4--;
                        double d9 = dArr[i4];
                        if (d8 < d9) {
                            dArr[i4 + 1] = d9;
                        } else {
                            dArr[i4 + 1] = d8;
                            return;
                        }
                    }
                }
            }
            return;
        }
        int i11 = (i5 >> 3) + (i5 >> 6) + 1;
        int i12 = (i3 + i4) >>> 1;
        int i13 = i12 - i11;
        int i14 = i13 - i11;
        int i15 = i12 + i11;
        int i16 = i11 + i15;
        double d10 = dArr[i13];
        double d11 = dArr[i14];
        if (d10 < d11) {
            dArr[i13] = d11;
            dArr[i14] = d10;
        }
        double d12 = dArr[i12];
        double d13 = dArr[i13];
        if (d12 < d13) {
            dArr[i12] = d13;
            dArr[i13] = d12;
            double d14 = dArr[i14];
            if (d12 < d14) {
                dArr[i13] = d14;
                dArr[i14] = d12;
            }
        }
        double d15 = dArr[i15];
        double d16 = dArr[i12];
        if (d15 < d16) {
            dArr[i15] = d16;
            dArr[i12] = d15;
            double d17 = dArr[i13];
            if (d15 < d17) {
                dArr[i12] = d17;
                dArr[i13] = d15;
                double d18 = dArr[i14];
                if (d15 < d18) {
                    dArr[i13] = d18;
                    dArr[i14] = d15;
                }
            }
        }
        double d19 = dArr[i16];
        double d20 = dArr[i15];
        if (d19 < d20) {
            dArr[i16] = d20;
            dArr[i15] = d19;
            double d21 = dArr[i12];
            if (d19 < d21) {
                dArr[i15] = d21;
                dArr[i12] = d19;
                double d22 = dArr[i13];
                if (d19 < d22) {
                    dArr[i12] = d22;
                    dArr[i13] = d19;
                    double d23 = dArr[i14];
                    if (d19 < d23) {
                        dArr[i13] = d23;
                        dArr[i14] = d19;
                    }
                }
            }
        }
        double d24 = dArr[i14];
        double d25 = dArr[i13];
        if (d24 != d25) {
            double d26 = dArr[i12];
            if (d25 != d26) {
                double d27 = dArr[i15];
                if (d26 != d27 && d27 != dArr[i16]) {
                    dArr[i13] = dArr[i3];
                    dArr[i15] = dArr[i4];
                    int i17 = i3;
                    do {
                        i17++;
                    } while (dArr[i17] < d25);
                    int i18 = i4;
                    do {
                        i18--;
                    } while (dArr[i18] > d27);
                    int i19 = i17 - 1;
                    loop9: while (true) {
                        i19++;
                        if (i19 > i18) {
                            break;
                        }
                        double d28 = dArr[i19];
                        if (d28 < d25) {
                            dArr[i19] = dArr[i17];
                            dArr[i17] = d28;
                            i17++;
                        } else if (d28 > d27) {
                            while (true) {
                                double d29 = dArr[i18];
                                if (d29 > d27) {
                                    int i20 = i18 - 1;
                                    if (i18 == i19) {
                                        i18 = i20;
                                        break loop9;
                                    }
                                    i18 = i20;
                                } else {
                                    if (d29 < d25) {
                                        dArr[i19] = dArr[i17];
                                        dArr[i17] = dArr[i18];
                                        i17++;
                                    } else {
                                        dArr[i19] = d29;
                                    }
                                    dArr[i18] = d28;
                                    i18--;
                                }
                            }
                        } else {
                            continue;
                        }
                    }
                    int i21 = i17 - 1;
                    dArr[i3] = dArr[i21];
                    dArr[i21] = d25;
                    int i22 = i18 + 1;
                    dArr[i4] = dArr[i22];
                    dArr[i22] = d27;
                    sort(dArr, i3, i17 - 2, z);
                    sort(dArr, i18 + 2, i4, false);
                    if (i17 < i14 && i16 < i18) {
                        while (dArr[i17] == d25) {
                            i17++;
                        }
                        while (dArr[i18] == d27) {
                            i18--;
                        }
                        int i23 = i17 - 1;
                        loop13: while (true) {
                            i23++;
                            if (i23 > i18) {
                                break;
                            }
                            double d30 = dArr[i23];
                            if (d30 == d25) {
                                dArr[i23] = dArr[i17];
                                dArr[i17] = d30;
                                i17++;
                            } else if (d30 == d27) {
                                while (true) {
                                    double d31 = dArr[i18];
                                    if (d31 == d27) {
                                        int i24 = i18 - 1;
                                        if (i18 == i23) {
                                            i18 = i24;
                                            break loop13;
                                        }
                                        i18 = i24;
                                    } else {
                                        if (d31 == d25) {
                                            dArr[i23] = dArr[i17];
                                            dArr[i17] = dArr[i18];
                                            i17++;
                                        } else {
                                            dArr[i23] = d31;
                                        }
                                        dArr[i18] = d30;
                                        i18--;
                                    }
                                }
                            } else {
                                continue;
                            }
                        }
                    }
                    sort(dArr, i17, i18, false);
                    return;
                }
            }
        }
        double d32 = dArr[i12];
        int i25 = i3;
        int i26 = i25;
        int i27 = i4;
        while (i25 <= i27) {
            double d33 = dArr[i25];
            if (d33 != d32) {
                if (d33 < d32) {
                    dArr[i25] = dArr[i26];
                    dArr[i26] = d33;
                    i26++;
                } else {
                    while (true) {
                        d = dArr[i27];
                        if (d <= d32) {
                            break;
                        } else {
                            i27--;
                        }
                    }
                    if (d < d32) {
                        dArr[i25] = dArr[i26];
                        dArr[i26] = dArr[i27];
                        i26++;
                    } else {
                        dArr[i25] = d;
                    }
                    dArr[i27] = d33;
                    i27--;
                }
            }
            i25++;
        }
        sort(dArr, i3, i26 - 1, z);
        sort(dArr, i27 + 1, i4, false);
    }
}
