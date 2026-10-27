package b.a.a.n;

import com.airbnb.lottie.utils.Utils;
import net.sqlcipher.database.SQLiteDatabase;

/* JADX INFO: compiled from: RyuFloat.java */
/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[][] f459a = {new int[]{536870912, 0}, new int[]{671088640, 0}, new int[]{838860800, 0}, new int[]{1048576000, 0}, new int[]{655360000, 0}, new int[]{819200000, 0}, new int[]{1024000000, 0}, new int[]{640000000, 0}, new int[]{800000000, 0}, new int[]{Utils.SECOND_IN_NANOS, 0}, new int[]{625000000, 0}, new int[]{781250000, 0}, new int[]{976562500, 0}, new int[]{610351562, 1073741824}, new int[]{762939453, SQLiteDatabase.CREATE_IF_NECESSARY}, new int[]{953674316, 872415232}, new int[]{596046447, 1619001344}, new int[]{745058059, 1486880768}, new int[]{931322574, 1321730048}, new int[]{582076609, 289210368}, new int[]{727595761, 898383872}, new int[]{909494701, 1659850752}, new int[]{568434188, 1305842176}, new int[]{710542735, 1632302720}, new int[]{888178419, 1503507488}, new int[]{555111512, 671256724}, new int[]{693889390, 839070905}, new int[]{867361737, 2122580455}, new int[]{542101086, 521306416}, new int[]{677626357, 1725374844}, new int[]{847032947, 546105819}, new int[]{1058791184, 145761362}, new int[]{661744490, 91100851}, new int[]{827180612, 1187617888}, new int[]{1033975765, 1484522360}, new int[]{646234853, 1196261931}, new int[]{807793566, 2032198326}, new int[]{1009741958, 1466506084}, new int[]{631088724, 379695390}, new int[]{788860905, 474619238}, new int[]{986076131, 1130144959}, new int[]{616297582, 437905143}, new int[]{770371977, 1621123253}, new int[]{962964972, 415791331}, new int[]{601853107, 1333611405}, new int[]{752316384, 1130143345}, new int[]{940395480, 1412679181}};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[][] f460b = {new int[]{SQLiteDatabase.CREATE_IF_NECESSARY, 1}, new int[]{214748364, 1717986919}, new int[]{171798691, 1803886265}, new int[]{137438953, 1013612282}, new int[]{219902325, 1192282922}, new int[]{175921860, 953826338}, new int[]{140737488, 763061070}, new int[]{225179981, 791400982}, new int[]{180143985, 203624056}, new int[]{144115188, 162899245}, new int[]{230584300, 1978625710}, new int[]{184467440, 1582900568}, new int[]{147573952, 1266320455}, new int[]{236118324, 308125809}, new int[]{188894659, 675997377}, new int[]{151115727, 970294631}, new int[]{241785163, 1981968139}, new int[]{193428131, 297084323}, new int[]{154742504, 1955654377}, new int[]{247588007, 1840556814}, new int[]{198070406, 613451992}, new int[]{158456325, 61264864}, new int[]{253530120, 98023782}, new int[]{202824096, 78419026}, new int[]{162259276, 1780722139}, new int[]{259614842, 1990161963}, new int[]{207691874, 733136111}, new int[]{166153499, 1016005619}, new int[]{265845599, 337118801}, new int[]{212676479, 699191770}, new int[]{170141183, 988850146}};

    public static int a(float f2, char[] cArr, int i) {
        int i2;
        boolean z;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        if (Float.isNaN(f2)) {
            int i17 = i + 1;
            cArr[i] = 'N';
            int i18 = i17 + 1;
            cArr[i17] = 'a';
            i16 = i18 + 1;
            cArr[i18] = 'N';
        } else {
            if (f2 == Float.POSITIVE_INFINITY) {
                int i19 = i + 1;
                cArr[i] = 'I';
                int i20 = i19 + 1;
                cArr[i19] = 'n';
                int i21 = i20 + 1;
                cArr[i20] = 'f';
                int i22 = i21 + 1;
                cArr[i21] = 'i';
                int i23 = i22 + 1;
                cArr[i22] = 'n';
                int i24 = i23 + 1;
                cArr[i23] = 'i';
                int i25 = i24 + 1;
                cArr[i24] = 't';
                cArr[i25] = 'y';
                return (i25 + 1) - i;
            }
            if (f2 == Float.NEGATIVE_INFINITY) {
                int i26 = i + 1;
                cArr[i] = '-';
                int i27 = i26 + 1;
                cArr[i26] = 'I';
                int i28 = i27 + 1;
                cArr[i27] = 'n';
                int i29 = i28 + 1;
                cArr[i28] = 'f';
                int i30 = i29 + 1;
                cArr[i29] = 'i';
                int i31 = i30 + 1;
                cArr[i30] = 'n';
                int i32 = i31 + 1;
                cArr[i31] = 'i';
                int i33 = i32 + 1;
                cArr[i32] = 't';
                i16 = i33 + 1;
                cArr[i33] = 'y';
            } else {
                int iFloatToIntBits = Float.floatToIntBits(f2);
                if (iFloatToIntBits != 0) {
                    if (iFloatToIntBits == Integer.MIN_VALUE) {
                        int i34 = i + 1;
                        cArr[i] = '-';
                        int i35 = i34 + 1;
                        cArr[i34] = '0';
                        int i36 = i35 + 1;
                        cArr[i35] = '.';
                        cArr[i36] = '0';
                        return (i36 + 1) - i;
                    }
                    int i37 = (iFloatToIntBits >> 23) & 255;
                    int i38 = 8388607 & iFloatToIntBits;
                    if (i37 == 0) {
                        i2 = -149;
                    } else {
                        i2 = (i37 - 127) - 23;
                        i38 |= 8388608;
                    }
                    boolean z2 = iFloatToIntBits < 0;
                    boolean z3 = (i38 & 1) == 0;
                    int i39 = i38 * 4;
                    int i40 = i39 + 2;
                    int i41 = i39 - ((((long) i38) != 8388608 || i37 <= 1) ? 2 : 1);
                    int i42 = i2 - 2;
                    if (i42 >= 0) {
                        i12 = (int) ((((long) i42) * 3010299) / 10000000);
                        int i43 = i12 == 0 ? 1 : (int) ((((((long) i12) * 23219280) + 10000000) - 1) / 10000000);
                        int i44 = (-i42) + i12;
                        int[][] iArr = f460b;
                        long j = iArr[i12][0];
                        z = z3;
                        long j2 = iArr[i12][1];
                        long j3 = i39;
                        int i45 = (((i43 + 59) - 1) + i44) - 31;
                        int i46 = (int) (((j3 * j) + ((j3 * j2) >> 31)) >> i45);
                        long j4 = i40;
                        i7 = (int) (((j4 * j) + ((j4 * j2) >> 31)) >> i45);
                        int i47 = i41;
                        long j5 = i47;
                        int i48 = (int) (((j * j5) + ((j5 * j2) >> 31)) >> i45);
                        if (i12 == 0 || (i7 - 1) / 10 > i48 / 10) {
                            i11 = 0;
                        } else {
                            int i49 = i12 - 1;
                            i11 = (int) ((((((long) iArr[i49][0]) * j3) + ((((long) iArr[i49][1]) * j3) >> 31)) >> (((i44 - 1) + (((i49 == 0 ? 1 : (int) ((((((long) i49) * 23219280) + 10000000) - 1) / 10000000)) + 59) - 1)) - 31)) % 10);
                        }
                        int i50 = 0;
                        while (i40 > 0 && i40 % 5 == 0) {
                            i40 /= 5;
                            i50++;
                        }
                        int i51 = 0;
                        while (i39 > 0 && i39 % 5 == 0) {
                            i39 /= 5;
                            i51++;
                        }
                        int i52 = 0;
                        while (i47 > 0 && i47 % 5 == 0) {
                            i47 /= 5;
                            i52++;
                        }
                        int i53 = i50 >= i12 ? 1 : 0;
                        i6 = i51 >= i12 ? 1 : 0;
                        i8 = i52 >= i12 ? 1 : 0;
                        i3 = 0;
                        i5 = i53;
                        i9 = i48;
                        i10 = i46;
                    } else {
                        z = z3;
                        int i54 = -i42;
                        int i55 = (int) ((((long) i54) * 6989700) / 10000000);
                        int i56 = i54 - i55;
                        int i57 = i56 == 0 ? 1 : (int) ((((((long) i56) * 23219280) + 10000000) - 1) / 10000000);
                        int[][] iArr2 = f459a;
                        long j6 = iArr2[i56][0];
                        long j7 = iArr2[i56][1];
                        int i58 = (i55 - (i57 - 61)) - 31;
                        long j8 = i39;
                        int i59 = (int) (((j8 * j6) + ((j8 * j7) >> 31)) >> i58);
                        long j9 = i40;
                        int i60 = (int) (((j9 * j6) + ((j9 * j7) >> 31)) >> i58);
                        long j10 = i41;
                        int i61 = (int) (((j6 * j10) + ((j10 * j7) >> 31)) >> i58);
                        if (i55 == 0 || (i60 - 1) / 10 > i61 / 10) {
                            i3 = 0;
                            i4 = 0;
                        } else {
                            int i62 = i56 + 1;
                            i3 = 0;
                            i4 = (int) ((((((long) iArr2[i62][0]) * j8) + ((j8 * ((long) iArr2[i62][1])) >> 31)) >> (((i55 - 1) - ((i62 == 0 ? 1 : (int) ((((((long) i62) * 23219280) + 10000000) - 1) / 10000000)) - 61)) - 31)) % 10);
                        }
                        int i63 = i55 + i42;
                        i5 = 1 >= i55 ? 1 : i3;
                        i6 = (i55 >= 23 || (i39 & ((1 << (i55 + (-1))) - 1)) != 0) ? i3 : 1;
                        i7 = i60;
                        i8 = (i41 % 2 == 1 ? i3 : 1) >= i55 ? 1 : i3;
                        i9 = i61;
                        i10 = i59;
                        i11 = i4;
                        i12 = i63;
                    }
                    int i64 = Utils.SECOND_IN_NANOS;
                    int i65 = 10;
                    while (i65 > 0 && i7 < i64) {
                        i64 /= 10;
                        i65--;
                    }
                    int i66 = (i12 + i65) - 1;
                    int i67 = (i66 < -3 || i66 >= 7) ? 1 : i3;
                    if (i5 != 0 && !z) {
                        i7--;
                    }
                    int i68 = i3;
                    while (true) {
                        int i69 = i7 / 10;
                        int i70 = i9 / 10;
                        if (i69 <= i70 || (i7 < 100 && i67 != 0)) {
                            break;
                        }
                        i8 &= i9 % 10 == 0 ? 1 : i3;
                        i11 = i10 % 10;
                        i10 /= 10;
                        i68++;
                        i7 = i69;
                        i9 = i70;
                    }
                    if (i8 != 0 && z) {
                        while (i9 % 10 == 0 && (i7 >= 100 || i67 == 0)) {
                            i7 /= 10;
                            i11 = i10 % 10;
                            i10 /= 10;
                            i9 /= 10;
                            i68++;
                        }
                    }
                    if (i6 != 0 && i11 == 5 && i10 % 2 == 0) {
                        i11 = 4;
                    }
                    int i71 = i10 + (((i10 != i9 || (i8 != 0 && z)) && i11 < 5) ? i3 : 1);
                    int i72 = i65 - i68;
                    if (z2) {
                        i13 = i + 1;
                        cArr[i] = '-';
                    } else {
                        i13 = i;
                    }
                    if (i67 != 0) {
                        for (int i73 = i3; i73 < i72 - 1; i73++) {
                            int i74 = i71 % 10;
                            i71 /= 10;
                            cArr[(i13 + i72) - i73] = (char) (i74 + 48);
                        }
                        cArr[i13] = (char) ((i71 % 10) + 48);
                        cArr[i13 + 1] = '.';
                        int i75 = i13 + i72 + 1;
                        if (i72 == 1) {
                            cArr[i75] = '0';
                            i75++;
                        }
                        int i76 = i75 + 1;
                        cArr[i75] = 'E';
                        if (i66 < 0) {
                            cArr[i76] = '-';
                            i66 = -i66;
                            i76++;
                        }
                        if (i66 >= 10) {
                            i15 = 48;
                            cArr[i76] = (char) ((i66 / 10) + 48);
                            i76++;
                        } else {
                            i15 = 48;
                        }
                        i14 = i76 + 1;
                        cArr[i76] = (char) ((i66 % 10) + i15);
                    } else {
                        int i77 = 48;
                        if (i66 < 0) {
                            int i78 = i13 + 1;
                            cArr[i13] = '0';
                            int i79 = i78 + 1;
                            cArr[i78] = '.';
                            int i80 = -1;
                            while (i80 > i66) {
                                cArr[i79] = '0';
                                i80--;
                                i79++;
                            }
                            int i81 = i79;
                            int i82 = i3;
                            while (i82 < i72) {
                                cArr[((i79 + i72) - i82) - 1] = (char) ((i71 % 10) + i77);
                                i71 /= 10;
                                i81++;
                                i82++;
                                i77 = 48;
                            }
                            i14 = i81;
                        } else {
                            int i83 = i66 + 1;
                            if (i83 >= i72) {
                                for (int i84 = i3; i84 < i72; i84++) {
                                    cArr[((i13 + i72) - i84) - 1] = (char) ((i71 % 10) + 48);
                                    i71 /= 10;
                                }
                                int i85 = i13 + i72;
                                while (i72 < i83) {
                                    cArr[i85] = '0';
                                    i72++;
                                    i85++;
                                }
                                int i86 = i85 + 1;
                                cArr[i85] = '.';
                                i14 = i86 + 1;
                                cArr[i86] = '0';
                            } else {
                                int i87 = i13 + 1;
                                for (int i88 = i3; i88 < i72; i88++) {
                                    if ((i72 - i88) - 1 == i66) {
                                        cArr[((i87 + i72) - i88) - 1] = '.';
                                        i87--;
                                    }
                                    cArr[((i87 + i72) - i88) - 1] = (char) ((i71 % 10) + 48);
                                    i71 /= 10;
                                }
                                i14 = i13 + i72 + 1;
                            }
                        }
                    }
                    return i14 - i;
                }
                int i89 = i + 1;
                cArr[i] = '0';
                int i90 = i89 + 1;
                cArr[i89] = '.';
                i16 = i90 + 1;
                cArr[i90] = '0';
            }
        }
        return i16 - i;
    }

    public static String b(float f2) {
        char[] cArr = new char[15];
        return new String(cArr, 0, a(f2, cArr, 0));
    }
}
