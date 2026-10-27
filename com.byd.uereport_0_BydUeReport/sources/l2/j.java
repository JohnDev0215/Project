package l2;

import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;

/* JADX INFO: compiled from: RyuFloat.java */
/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[][] f6238a = {new int[]{536870912, 0}, new int[]{671088640, 0}, new int[]{838860800, 0}, new int[]{1048576000, 0}, new int[]{655360000, 0}, new int[]{819200000, 0}, new int[]{1024000000, 0}, new int[]{640000000, 0}, new int[]{800000000, 0}, new int[]{1000000000, 0}, new int[]{625000000, 0}, new int[]{781250000, 0}, new int[]{976562500, 0}, new int[]{610351562, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED}, new int[]{762939453, 268435456}, new int[]{953674316, 872415232}, new int[]{596046447, 1619001344}, new int[]{745058059, 1486880768}, new int[]{931322574, 1321730048}, new int[]{582076609, 289210368}, new int[]{727595761, 898383872}, new int[]{909494701, 1659850752}, new int[]{568434188, 1305842176}, new int[]{710542735, 1632302720}, new int[]{888178419, 1503507488}, new int[]{555111512, 671256724}, new int[]{693889390, 839070905}, new int[]{867361737, 2122580455}, new int[]{542101086, 521306416}, new int[]{677626357, 1725374844}, new int[]{847032947, 546105819}, new int[]{1058791184, 145761362}, new int[]{661744490, 91100851}, new int[]{827180612, 1187617888}, new int[]{1033975765, 1484522360}, new int[]{646234853, 1196261931}, new int[]{807793566, 2032198326}, new int[]{1009741958, 1466506084}, new int[]{631088724, 379695390}, new int[]{788860905, 474619238}, new int[]{986076131, 1130144959}, new int[]{616297582, 437905143}, new int[]{770371977, 1621123253}, new int[]{962964972, 415791331}, new int[]{601853107, 1333611405}, new int[]{752316384, 1130143345}, new int[]{940395480, 1412679181}};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[][] f6239b = {new int[]{268435456, 1}, new int[]{214748364, 1717986919}, new int[]{171798691, 1803886265}, new int[]{137438953, 1013612282}, new int[]{219902325, 1192282922}, new int[]{175921860, 953826338}, new int[]{140737488, 763061070}, new int[]{225179981, 791400982}, new int[]{180143985, 203624056}, new int[]{144115188, 162899245}, new int[]{230584300, 1978625710}, new int[]{184467440, 1582900568}, new int[]{147573952, 1266320455}, new int[]{236118324, 308125809}, new int[]{188894659, 675997377}, new int[]{151115727, 970294631}, new int[]{241785163, 1981968139}, new int[]{193428131, 297084323}, new int[]{154742504, 1955654377}, new int[]{247588007, 1840556814}, new int[]{198070406, 613451992}, new int[]{158456325, 61264864}, new int[]{253530120, 98023782}, new int[]{202824096, 78419026}, new int[]{162259276, 1780722139}, new int[]{259614842, 1990161963}, new int[]{207691874, 733136111}, new int[]{166153499, 1016005619}, new int[]{265845599, 337118801}, new int[]{212676479, 699191770}, new int[]{170141183, 988850146}};

    public static int a(float f8, char[] cArr, int i8) {
        int i9;
        boolean z7;
        int i10;
        int i11;
        int i12;
        int i13;
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
        if (Float.isNaN(f8)) {
            int i24 = i8 + 1;
            cArr[i8] = 'N';
            int i25 = i24 + 1;
            cArr[i24] = 'a';
            i23 = i25 + 1;
            cArr[i25] = 'N';
        } else {
            if (f8 == Float.POSITIVE_INFINITY) {
                int i26 = i8 + 1;
                cArr[i8] = 'I';
                int i27 = i26 + 1;
                cArr[i26] = 'n';
                int i28 = i27 + 1;
                cArr[i27] = 'f';
                int i29 = i28 + 1;
                cArr[i28] = 'i';
                int i30 = i29 + 1;
                cArr[i29] = 'n';
                int i31 = i30 + 1;
                cArr[i30] = 'i';
                int i32 = i31 + 1;
                cArr[i31] = 't';
                cArr[i32] = 'y';
                return (i32 + 1) - i8;
            }
            if (f8 == Float.NEGATIVE_INFINITY) {
                int i33 = i8 + 1;
                cArr[i8] = '-';
                int i34 = i33 + 1;
                cArr[i33] = 'I';
                int i35 = i34 + 1;
                cArr[i34] = 'n';
                int i36 = i35 + 1;
                cArr[i35] = 'f';
                int i37 = i36 + 1;
                cArr[i36] = 'i';
                int i38 = i37 + 1;
                cArr[i37] = 'n';
                int i39 = i38 + 1;
                cArr[i38] = 'i';
                int i40 = i39 + 1;
                cArr[i39] = 't';
                i23 = i40 + 1;
                cArr[i40] = 'y';
            } else {
                int iFloatToIntBits = Float.floatToIntBits(f8);
                if (iFloatToIntBits != 0) {
                    if (iFloatToIntBits == Integer.MIN_VALUE) {
                        int i41 = i8 + 1;
                        cArr[i8] = '-';
                        int i42 = i41 + 1;
                        cArr[i41] = '0';
                        int i43 = i42 + 1;
                        cArr[i42] = '.';
                        cArr[i43] = '0';
                        return (i43 + 1) - i8;
                    }
                    int i44 = (iFloatToIntBits >> 23) & 255;
                    int i45 = 8388607 & iFloatToIntBits;
                    if (i44 == 0) {
                        i9 = -149;
                    } else {
                        i9 = (i44 - 127) - 23;
                        i45 |= 8388608;
                    }
                    boolean z8 = iFloatToIntBits < 0;
                    boolean z9 = (i45 & 1) == 0;
                    int i46 = i45 * 4;
                    int i47 = i46 + 2;
                    int i48 = i46 - ((((long) i45) != 8388608 || i44 <= 1) ? 2 : 1);
                    int i49 = i9 - 2;
                    if (i49 >= 0) {
                        i19 = (int) ((((long) i49) * 3010299) / 10000000);
                        int i50 = i19 == 0 ? 1 : (int) ((((((long) i19) * 23219280) + 10000000) - 1) / 10000000);
                        int i51 = (-i49) + i19;
                        int[][] iArr = f6239b;
                        long j8 = iArr[i19][0];
                        z7 = z9;
                        long j9 = iArr[i19][1];
                        long j10 = i46;
                        int i52 = (((i50 + 59) - 1) + i51) - 31;
                        int i53 = (int) (((j10 * j8) + ((j10 * j9) >> 31)) >> i52);
                        long j11 = i47;
                        i14 = (int) (((j11 * j8) + ((j11 * j9) >> 31)) >> i52);
                        int i54 = i48;
                        long j12 = i54;
                        int i55 = (int) (((j8 * j12) + ((j12 * j9) >> 31)) >> i52);
                        if (i19 == 0 || (i14 - 1) / 10 > i55 / 10) {
                            i18 = 0;
                        } else {
                            int i56 = i19 - 1;
                            i18 = (int) ((((((long) iArr[i56][0]) * j10) + ((((long) iArr[i56][1]) * j10) >> 31)) >> (((i51 - 1) + (((i56 == 0 ? 1 : (int) ((((((long) i56) * 23219280) + 10000000) - 1) / 10000000)) + 59) - 1)) - 31)) % 10);
                        }
                        int i57 = 0;
                        while (i47 > 0 && i47 % 5 == 0) {
                            i47 /= 5;
                            i57++;
                        }
                        int i58 = 0;
                        while (i46 > 0 && i46 % 5 == 0) {
                            i46 /= 5;
                            i58++;
                        }
                        int i59 = 0;
                        while (i54 > 0 && i54 % 5 == 0) {
                            i54 /= 5;
                            i59++;
                        }
                        int i60 = i57 >= i19 ? 1 : 0;
                        i13 = i58 >= i19 ? 1 : 0;
                        i15 = i59 >= i19 ? 1 : 0;
                        i10 = 0;
                        i12 = i60;
                        i16 = i55;
                        i17 = i53;
                    } else {
                        z7 = z9;
                        int i61 = -i49;
                        int i62 = (int) ((((long) i61) * 6989700) / 10000000);
                        int i63 = i61 - i62;
                        int i64 = i63 == 0 ? 1 : (int) ((((((long) i63) * 23219280) + 10000000) - 1) / 10000000);
                        int[][] iArr2 = f6238a;
                        long j13 = iArr2[i63][0];
                        long j14 = iArr2[i63][1];
                        int i65 = (i62 - (i64 - 61)) - 31;
                        long j15 = i46;
                        int i66 = (int) (((j15 * j13) + ((j15 * j14) >> 31)) >> i65);
                        long j16 = i47;
                        int i67 = (int) (((j16 * j13) + ((j16 * j14) >> 31)) >> i65);
                        long j17 = i48;
                        int i68 = (int) (((j13 * j17) + ((j17 * j14) >> 31)) >> i65);
                        if (i62 == 0 || (i67 - 1) / 10 > i68 / 10) {
                            i10 = 0;
                            i11 = 0;
                        } else {
                            int i69 = i63 + 1;
                            i10 = 0;
                            i11 = (int) ((((((long) iArr2[i69][0]) * j15) + ((j15 * ((long) iArr2[i69][1])) >> 31)) >> (((i62 - 1) - ((i69 == 0 ? 1 : (int) ((((((long) i69) * 23219280) + 10000000) - 1) / 10000000)) - 61)) - 31)) % 10);
                        }
                        int i70 = i62 + i49;
                        i12 = 1 >= i62 ? 1 : i10;
                        i13 = (i62 >= 23 || (i46 & ((1 << (i62 + (-1))) - 1)) != 0) ? i10 : 1;
                        i14 = i67;
                        i15 = (i48 % 2 == 1 ? i10 : 1) >= i62 ? 1 : i10;
                        i16 = i68;
                        i17 = i66;
                        i18 = i11;
                        i19 = i70;
                    }
                    int i71 = 1000000000;
                    int i72 = 10;
                    while (i72 > 0 && i14 < i71) {
                        i71 /= 10;
                        i72--;
                    }
                    int i73 = (i19 + i72) - 1;
                    int i74 = (i73 < -3 || i73 >= 7) ? 1 : i10;
                    if (i12 != 0 && !z7) {
                        i14--;
                    }
                    int i75 = i10;
                    while (true) {
                        int i76 = i14 / 10;
                        int i77 = i16 / 10;
                        if (i76 <= i77 || (i14 < 100 && i74 != 0)) {
                            break;
                        }
                        i15 &= i16 % 10 == 0 ? 1 : i10;
                        i18 = i17 % 10;
                        i17 /= 10;
                        i75++;
                        i14 = i76;
                        i16 = i77;
                    }
                    if (i15 != 0 && z7) {
                        while (i16 % 10 == 0 && (i14 >= 100 || i74 == 0)) {
                            i14 /= 10;
                            i18 = i17 % 10;
                            i17 /= 10;
                            i16 /= 10;
                            i75++;
                        }
                    }
                    if (i13 != 0 && i18 == 5 && i17 % 2 == 0) {
                        i18 = 4;
                    }
                    int i78 = i17 + (((i17 != i16 || (i15 != 0 && z7)) && i18 < 5) ? i10 : 1);
                    int i79 = i72 - i75;
                    if (z8) {
                        i20 = i8 + 1;
                        cArr[i8] = '-';
                    } else {
                        i20 = i8;
                    }
                    if (i74 != 0) {
                        for (int i80 = i10; i80 < i79 - 1; i80++) {
                            int i81 = i78 % 10;
                            i78 /= 10;
                            cArr[(i20 + i79) - i80] = (char) (i81 + 48);
                        }
                        cArr[i20] = (char) ((i78 % 10) + 48);
                        cArr[i20 + 1] = '.';
                        int i82 = i20 + i79 + 1;
                        if (i79 == 1) {
                            cArr[i82] = '0';
                            i82++;
                        }
                        int i83 = i82 + 1;
                        cArr[i82] = 'E';
                        if (i73 < 0) {
                            cArr[i83] = '-';
                            i73 = -i73;
                            i83++;
                        }
                        if (i73 >= 10) {
                            i22 = 48;
                            cArr[i83] = (char) ((i73 / 10) + 48);
                            i83++;
                        } else {
                            i22 = 48;
                        }
                        i21 = i83 + 1;
                        cArr[i83] = (char) ((i73 % 10) + i22);
                    } else {
                        int i84 = 48;
                        if (i73 < 0) {
                            int i85 = i20 + 1;
                            cArr[i20] = '0';
                            int i86 = i85 + 1;
                            cArr[i85] = '.';
                            int i87 = -1;
                            while (i87 > i73) {
                                cArr[i86] = '0';
                                i87--;
                                i86++;
                            }
                            int i88 = i86;
                            int i89 = i10;
                            while (i89 < i79) {
                                cArr[((i86 + i79) - i89) - 1] = (char) ((i78 % 10) + i84);
                                i78 /= 10;
                                i88++;
                                i89++;
                                i84 = 48;
                            }
                            i21 = i88;
                        } else {
                            int i90 = i73 + 1;
                            if (i90 >= i79) {
                                for (int i91 = i10; i91 < i79; i91++) {
                                    cArr[((i20 + i79) - i91) - 1] = (char) ((i78 % 10) + 48);
                                    i78 /= 10;
                                }
                                int i92 = i20 + i79;
                                while (i79 < i90) {
                                    cArr[i92] = '0';
                                    i79++;
                                    i92++;
                                }
                                int i93 = i92 + 1;
                                cArr[i92] = '.';
                                i21 = i93 + 1;
                                cArr[i93] = '0';
                            } else {
                                int i94 = i20 + 1;
                                for (int i95 = i10; i95 < i79; i95++) {
                                    if ((i79 - i95) - 1 == i73) {
                                        cArr[((i94 + i79) - i95) - 1] = '.';
                                        i94--;
                                    }
                                    cArr[((i94 + i79) - i95) - 1] = (char) ((i78 % 10) + 48);
                                    i78 /= 10;
                                }
                                i21 = i20 + i79 + 1;
                            }
                        }
                    }
                    return i21 - i8;
                }
                int i96 = i8 + 1;
                cArr[i8] = '0';
                int i97 = i96 + 1;
                cArr[i96] = '.';
                i23 = i97 + 1;
                cArr[i97] = '0';
            }
        }
        return i23 - i8;
    }

    public static String b(float f8) {
        char[] cArr = new char[15];
        return new String(cArr, 0, a(f8, cArr, 0));
    }
}
