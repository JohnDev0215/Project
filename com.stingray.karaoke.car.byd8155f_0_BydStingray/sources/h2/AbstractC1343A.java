package h2;

import H7.H;
import android.app.UiModeManager;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.AudioFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import e2.W;
import h5.C1373E;
import java.io.Closeable;
import java.io.IOException;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import z2.C2758d;

/* JADX INFO: renamed from: h2.A, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1343A {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f15195a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f15196b;
    public static final String c;
    public static final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f15197e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final byte[] f15198f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final long[] f15199g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final Pattern f15200h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final Pattern f15201i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static HashMap f15202j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final String[] f15203k;
    public static final String[] l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final int[] f15204m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final int[] f15205n;
    public static final int[] o;

    static {
        int i7 = Build.VERSION.SDK_INT;
        f15195a = i7;
        String str = Build.DEVICE;
        f15196b = str;
        String str2 = Build.MANUFACTURER;
        c = str2;
        String str3 = Build.MODEL;
        d = str3;
        f15197e = str + ", " + str3 + ", " + str2 + ", " + i7;
        f15198f = new byte[0];
        f15199g = new long[0];
        Pattern.compile("(\\d\\d\\d\\d)\\-(\\d\\d)\\-(\\d\\d)[Tt](\\d\\d):(\\d\\d):(\\d\\d)([\\.,](\\d+))?([Zz]|((\\+|\\-)(\\d?\\d):?(\\d\\d)))?");
        Pattern.compile("^(-)?P(([0-9]*)Y)?(([0-9]*)M)?(([0-9]*)D)?(T(([0-9]*)H)?(([0-9]*)M)?(([0-9.]*)S)?)?$");
        f15200h = Pattern.compile("%([A-Fa-f0-9]{2})");
        f15201i = Pattern.compile("(?:.*\\.)?isml?(?:/(manifest(.*))?)?", 2);
        f15203k = new String[]{"alb", "sq", "arm", "hy", "baq", "eu", "bur", "my", "tib", "bo", "chi", "zh", "cze", "cs", "dut", "nl", "ger", "de", "gre", "el", "fre", "fr", "geo", "ka", "ice", "is", "mac", "mk", "mao", "mi", "may", "ms", "per", "fa", "rum", "ro", "scc", "hbs-srp", "slo", "sk", "wel", "cy", "id", "ms-ind", "iw", "he", "heb", "he", "ji", "yi", "arb", "ar-arb", "in", "ms-ind", "ind", "ms-ind", "nb", "no-nob", "nob", "no-nob", "nn", "no-nno", "nno", "no-nno", "tw", "ak-twi", "twi", "ak-twi", "bs", "hbs-bos", "bos", "hbs-bos", "hr", "hbs-hrv", "hrv", "hbs-hrv", "sr", "hbs-srp", "srp", "hbs-srp", "cmn", "zh-cmn", "hak", "zh-hak", "nan", "zh-nan", "hsn", "zh-hsn"};
        l = new String[]{"i-lux", "lb", "i-hak", "zh-hak", "i-navajo", "nv", "no-bok", "no-nob", "no-nyn", "no-nno", "zh-guoyu", "zh-cmn", "zh-hakka", "zh-hak", "zh-min-nan", "zh-nan", "zh-xiang", "zh-hsn"};
        f15204m = new int[]{0, 79764919, 159529838, 222504665, 319059676, 398814059, 445009330, 507990021, 638119352, 583659535, 797628118, 726387553, 890018660, 835552979, 1015980042, 944750013, 1276238704, 1221641927, 1167319070, 1095957929, 1595256236, 1540665371, 1452775106, 1381403509, 1780037320, 1859660671, 1671105958, 1733955601, 2031960084, 2111593891, 1889500026, 1952343757, -1742489888, -1662866601, -1851683442, -1788833735, -1960329156, -1880695413, -2103051438, -2040207643, -1104454824, -1159051537, -1213636554, -1284997759, -1389417084, -1444007885, -1532160278, -1603531939, -734892656, -789352409, -575645954, -646886583, -952755380, -1007220997, -827056094, -898286187, -231047128, -151282273, -71779514, -8804623, -515967244, -436212925, -390279782, -327299027, 881225847, 809987520, 1023691545, 969234094, 662832811, 591600412, 771767749, 717299826, 311336399, 374308984, 453813921, 533576470, 25881363, 88864420, 134795389, 214552010, 2023205639, 2086057648, 1897238633, 1976864222, 1804852699, 1867694188, 1645340341, 1724971778, 1587496639, 1516133128, 1461550545, 1406951526, 1302016099, 1230646740, 1142491917, 1087903418, -1398421865, -1469785312, -1524105735, -1578704818, -1079922613, -1151291908, -1239184603, -1293773166, -1968362705, -1905510760, -2094067647, -2014441994, -1716953613, -1654112188, -1876203875, -1796572374, -525066777, -462094256, -382327159, -302564546, -206542021, -143559028, -97365931, -17609246, -960696225, -1031934488, -817968335, -872425850, -709327229, -780559564, -600130067, -654598054, 1762451694, 1842216281, 1619975040, 1682949687, 2047383090, 2127137669, 1938468188, 2001449195, 1325665622, 1271206113, 1183200824, 1111960463, 1543535498, 1489069629, 1434599652, 1363369299, 622672798, 568075817, 748617968, 677256519, 907627842, 853037301, 1067152940, 995781531, 51762726, 131386257, 177728840, 240578815, 269590778, 349224269, 429104020, 491947555, -248556018, -168932423, -122852000, -60002089, -500490030, -420856475, -341238852, -278395381, -685261898, -739858943, -559578920, -630940305, -1004286614, -1058877219, -845023740, -916395085, -1119974018, -1174433591, -1262701040, -1333941337, -1371866206, -1426332139, -1481064244, -1552294533, -1690935098, -1611170447, -1833673816, -1770699233, -2009983462, -1930228819, -2119160460, -2056179517, 1569362073, 1498123566, 1409854455, 1355396672, 1317987909, 1246755826, 1192025387, 1137557660, 2072149281, 2135122070, 1912620623, 1992383480, 1753615357, 1816598090, 1627664531, 1707420964, 295390185, 358241886, 404320391, 483945776, 43990325, 106832002, 186451547, 266083308, 932423249, 861060070, 1041341759, 986742920, 613929101, 542559546, 756411363, 701822548, -978770311, -1050133554, -869589737, -924188512, -693284699, -764654318, -550540341, -605129092, -475935807, -413084042, -366743377, -287118056, -257573603, -194731862, -114850189, -35218492, -1984365303, -1921392450, -2143631769, -2063868976, -1698919467, -1635936670, -1824608069, -1744851700, -1347415887, -1418654458, -1506661409, -1561119128, -1129027987, -1200260134, -1254728445, -1309196108};
        f15205n = new int[]{0, 4129, 8258, 12387, 16516, 20645, 24774, 28903, 33032, 37161, 41290, 45419, 49548, 53677, 57806, 61935};
        o = new int[]{0, 7, 14, 9, 28, 27, 18, 21, 56, 63, 54, 49, 36, 35, 42, 45, 112, 119, 126, 121, 108, 107, 98, 101, 72, 79, 70, 65, 84, 83, 90, 93, 224, 231, 238, 233, 252, 251, 242, 245, 216, 223, 214, 209, 196, 195, 202, 205, 144, 151, 158, 153, 140, 139, 130, 133, 168, 175, 166, 161, 180, 179, 186, 189, 199, 192, 201, 206, 219, 220, 213, 210, 255, 248, 241, 246, 227, 228, 237, 234, 183, 176, 185, 190, 171, 172, 165, 162, 143, 136, 129, 134, 147, 148, 157, 154, 39, 32, 41, 46, 59, 60, 53, 50, 31, 24, 17, 22, 3, 4, 13, 10, 87, 80, 89, 94, 75, 76, 69, 66, 111, 104, 97, 102, 115, 116, 125, 122, 137, 142, 135, 128, 149, 146, 155, 156, 177, 182, 191, 184, 173, 170, 163, 164, 249, 254, 247, 240, 229, 226, 235, 236, 193, 198, 207, 200, 221, 218, 211, 212, 105, 110, 103, 96, 117, 114, 123, 124, 81, 86, 95, 88, 77, 74, 67, 68, 25, 30, 23, 16, 5, 2, 11, 12, 33, 38, 47, 40, 61, 58, 51, 52, 78, 73, 64, 71, 82, 85, 92, 91, 118, 113, 120, 127, 106, 109, 100, 99, 62, 57, 48, 55, 34, 37, 44, 43, 6, 1, 8, 15, 26, 29, 20, 19, 174, 169, 160, 167, 178, 181, 188, 187, 150, 145, 152, 159, 138, 141, 132, 131, 222, 217, 208, 215, 194, 197, 204, 203, 230, 225, 232, 239, 250, 253, 244, 243};
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x002e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean A(e2.W r4) {
        /*
            r0 = 0
            if (r4 != 0) goto L4
            return r0
        L4:
            int r1 = r4.e()
            r2 = 1
            if (r1 != r2) goto L17
            r3 = 2
            boolean r3 = r4.R0(r3)
            if (r3 == 0) goto L17
            r4.c()
        L15:
            r0 = r2
            goto L24
        L17:
            r3 = 4
            if (r1 != r3) goto L24
            boolean r1 = r4.R0(r3)
            if (r1 == 0) goto L24
            r4.Y()
            goto L15
        L24:
            boolean r1 = r4.R0(r2)
            if (r1 == 0) goto L2e
            r4.g()
            goto L2f
        L2e:
            r2 = r0
        L2f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: h2.AbstractC1343A.A(e2.W):boolean");
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static int B(Uri uri, String str) {
        int i7;
        if (str != null) {
            switch (str) {
                case "application/x-mpegURL":
                    return 2;
                case "application/vnd.ms-sstr+xml":
                    return 1;
                case "application/dash+xml":
                    return 0;
                case "application/x-rtsp":
                    return 3;
                default:
                    return 4;
            }
        }
        String scheme = uri.getScheme();
        if (scheme != null && H.z("rtsp", scheme)) {
            return 3;
        }
        String lastPathSegment = uri.getLastPathSegment();
        if (lastPathSegment != null) {
            int iLastIndexOf = lastPathSegment.lastIndexOf(46);
            if (iLastIndexOf >= 0) {
                String strR = H.R(lastPathSegment.substring(iLastIndexOf + 1));
                strR.getClass();
                switch (strR.hashCode()) {
                    case 104579:
                        if (strR.equals("ism")) {
                        }
                        break;
                    case 108321:
                        if (strR.equals("mpd")) {
                        }
                        break;
                    case 3242057:
                        if (strR.equals("isml")) {
                        }
                        break;
                    case 3299913:
                        if (strR.equals("m3u8")) {
                        }
                        break;
                }
                /*  JADX ERROR: Method code generation error
                    java.lang.NullPointerException: Switch insn not found in header
                    	at java.base/java.util.Objects.requireNonNull(Objects.java:259)
                    	at jadx.core.codegen.RegionGen.makeSwitch(RegionGen.java:246)
                    	at jadx.core.dex.regions.SwitchRegion.generate(SwitchRegion.java:88)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                    	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                    	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:305)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:284)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:412)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:337)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:303)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
                    */
                /*
                    Method dump skipped, instruction units count: 284
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: h2.AbstractC1343A.B(android.net.Uri, java.lang.String):int");
            }

            public static boolean C(t tVar, t tVar2, Inflater inflater) {
                if (tVar.a() <= 0) {
                    return false;
                }
                if (tVar2.f15241a.length < tVar.a()) {
                    tVar2.b(tVar.a() * 2);
                }
                if (inflater == null) {
                    inflater = new Inflater();
                }
                inflater.setInput(tVar.f15241a, tVar.f15242b, tVar.a());
                int iInflate = 0;
                while (true) {
                    try {
                        byte[] bArr = tVar2.f15241a;
                        iInflate += inflater.inflate(bArr, iInflate, bArr.length - iInflate);
                        if (!inflater.finished()) {
                            if (inflater.needsDictionary() || inflater.needsInput()) {
                                break;
                            }
                            byte[] bArr2 = tVar2.f15241a;
                            if (iInflate == bArr2.length) {
                                tVar2.b(bArr2.length * 2);
                            }
                        } else {
                            tVar2.G(iInflate);
                            inflater.reset();
                            return true;
                        }
                    } catch (DataFormatException unused) {
                        return false;
                    } finally {
                        inflater.reset();
                    }
                }
                return false;
            }

            public static boolean D(int i7) {
                return i7 == 3 || i7 == 2 || i7 == 268435456 || i7 == 21 || i7 == 1342177280 || i7 == 22 || i7 == 1610612736 || i7 == 4;
            }

            public static boolean E(Context context) {
                int i7 = f15195a;
                if (i7 >= 29 && context.getApplicationInfo().targetSdkVersion >= 29) {
                    String str = d;
                    if ((i7 != 30 || (!H.z(str, "moto g(20)") && !H.z(str, "rmx3231"))) && (i7 != 34 || !H.z(str, "sm-x200"))) {
                        return false;
                    }
                }
                return true;
            }

            public static boolean F(Context context) {
                UiModeManager uiModeManager = (UiModeManager) context.getApplicationContext().getSystemService("uimode");
                return uiModeManager != null && uiModeManager.getCurrentModeType() == 4;
            }

            public static void G(ArrayList arrayList, int i7, int i10, int i11) {
                ArrayDeque arrayDeque = new ArrayDeque();
                for (int i12 = (i10 - i7) - 1; i12 >= 0; i12--) {
                    arrayDeque.addFirst(arrayList.remove(i7 + i12));
                }
                arrayList.addAll(Math.min(i11, arrayList.size()), arrayDeque);
            }

            public static long H(long j3) {
                return (j3 == -9223372036854775807L || j3 == Long.MIN_VALUE) ? j3 : j3 * 1000;
            }

            public static String I(String str) {
                if (str == null) {
                    return null;
                }
                String strReplace = str.replace('_', '-');
                if (!strReplace.isEmpty() && !strReplace.equals("und")) {
                    str = strReplace;
                }
                String strR = H.R(str);
                int i7 = 0;
                String str2 = strR.split("-", 2)[0];
                if (f15202j == null) {
                    String[] iSOLanguages = Locale.getISOLanguages();
                    int length = iSOLanguages.length;
                    String[] strArr = f15203k;
                    HashMap map = new HashMap(length + strArr.length);
                    for (String str3 : iSOLanguages) {
                        try {
                            String iSO3Language = new Locale(str3).getISO3Language();
                            if (!TextUtils.isEmpty(iSO3Language)) {
                                map.put(iSO3Language, str3);
                            }
                        } catch (MissingResourceException unused) {
                        }
                    }
                    for (int i10 = 0; i10 < strArr.length; i10 += 2) {
                        map.put(strArr[i10], strArr[i10 + 1]);
                    }
                    f15202j = map;
                }
                String str4 = (String) f15202j.get(str2);
                if (str4 != null) {
                    StringBuilder sbI = M5.b.i(str4);
                    sbI.append(strR.substring(str2.length()));
                    strR = sbI.toString();
                    str2 = str4;
                }
                if (!"no".equals(str2) && !"i".equals(str2) && !"zh".equals(str2)) {
                    return strR;
                }
                while (true) {
                    String[] strArr2 = l;
                    if (i7 >= strArr2.length) {
                        return strR;
                    }
                    if (strR.startsWith(strArr2[i7])) {
                        return strArr2[i7 + 1] + strR.substring(strArr2[i7].length());
                    }
                    i7 += 2;
                }
            }

            public static Object[] J(int i7, Object[] objArr) {
                AbstractC1346c.c(i7 <= objArr.length);
                return Arrays.copyOf(objArr, i7);
            }

            public static void K(Handler handler, Runnable runnable) {
                if (handler.getLooper().getThread().isAlive()) {
                    if (handler.getLooper() == Looper.myLooper()) {
                        runnable.run();
                    } else {
                        handler.post(runnable);
                    }
                }
            }

            public static long L(long j3, int i7) {
                return N(j3, 1000000L, i7, RoundingMode.DOWN);
            }

            public static void M(long[] jArr, long j3) {
                RoundingMode roundingMode = RoundingMode.DOWN;
                int i7 = 0;
                if (j3 >= 1000000 && j3 % 1000000 == 0) {
                    long jP = l9.d.P(j3, 1000000L, RoundingMode.UNNECESSARY);
                    while (i7 < jArr.length) {
                        jArr[i7] = l9.d.P(jArr[i7], jP, roundingMode);
                        i7++;
                    }
                    return;
                }
                if (j3 < 1000000 && 1000000 % j3 == 0) {
                    long jP2 = l9.d.P(1000000L, j3, RoundingMode.UNNECESSARY);
                    while (i7 < jArr.length) {
                        jArr[i7] = l9.d.f0(jArr[i7], jP2);
                        i7++;
                    }
                    return;
                }
                for (int i10 = 0; i10 < jArr.length; i10++) {
                    long j10 = jArr[i10];
                    if (j10 != 0) {
                        if (j3 >= j10 && j3 % j10 == 0) {
                            jArr[i10] = l9.d.P(1000000L, l9.d.P(j3, j10, RoundingMode.UNNECESSARY), roundingMode);
                        } else if (j3 >= j10 || j10 % j3 != 0) {
                            jArr[i10] = O(j10, 1000000L, j3, roundingMode);
                        } else {
                            jArr[i10] = l9.d.f0(1000000L, l9.d.P(j10, j3, RoundingMode.UNNECESSARY));
                        }
                    }
                }
            }

            public static long N(long j3, long j10, long j11, RoundingMode roundingMode) {
                if (j3 == 0 || j10 == 0) {
                    return 0L;
                }
                return (j11 < j10 || j11 % j10 != 0) ? (j11 >= j10 || j10 % j11 != 0) ? (j11 < j3 || j11 % j3 != 0) ? (j11 >= j3 || j3 % j11 != 0) ? O(j3, j10, j11, roundingMode) : l9.d.f0(j10, l9.d.P(j3, j11, RoundingMode.UNNECESSARY)) : l9.d.P(j10, l9.d.P(j11, j3, RoundingMode.UNNECESSARY), roundingMode) : l9.d.f0(j3, l9.d.P(j10, j11, RoundingMode.UNNECESSARY)) : l9.d.P(j3, l9.d.P(j11, j10, RoundingMode.UNNECESSARY), roundingMode);
            }

            /* JADX WARN: Removed duplicated region for block: B:59:0x00ee  */
            /* JADX WARN: Removed duplicated region for block: B:60:0x00f0  */
            /* JADX WARN: Removed duplicated region for block: B:64:0x00f6  */
            /* JADX WARN: Removed duplicated region for block: B:67:0x00fb  */
            /* JADX WARN: Removed duplicated region for block: B:69:0x00fd  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public static long O(long r9, long r11, long r13, java.math.RoundingMode r15) {
                /*
                    Method dump skipped, instruction units count: 318
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: h2.AbstractC1343A.O(long, long, long, java.math.RoundingMode):long");
            }

            public static boolean P(W w10, boolean z6) {
                if (w10 == null || !w10.C() || w10.e() == 1 || w10.e() == 4) {
                    return true;
                }
                return z6 && w10.p0() != 0;
            }

            public static String[] Q(String str) {
                return str.split("/", -1);
            }

            public static String[] R(String str) {
                return TextUtils.isEmpty(str) ? new String[0] : str.trim().split("(\\s*,\\s*)", -1);
            }

            public static C1373E S(h5.y yVar, h5.q qVar) {
                C1373E c1373e = new C1373E();
                A2.m mVar = new A2.m(c1373e, 5, yVar);
                h5.r rVar = h5.r.l;
                c1373e.b(mVar, rVar);
                yVar.b(new T1.k(yVar, c1373e, qVar, 2), rVar);
                return c1373e;
            }

            public static long T(long j3) {
                return (j3 == -9223372036854775807L || j3 == Long.MIN_VALUE) ? j3 : j3 / 1000;
            }

            public static boolean a(Object obj, Object obj2) {
                return Objects.equals(obj, obj2);
            }

            public static int b(long[] jArr, long j3, boolean z6) {
                int i7;
                int iBinarySearch = Arrays.binarySearch(jArr, j3);
                if (iBinarySearch < 0) {
                    return ~iBinarySearch;
                }
                while (true) {
                    i7 = iBinarySearch + 1;
                    if (i7 >= jArr.length || jArr[i7] != j3) {
                        break;
                    }
                    iBinarySearch = i7;
                }
                return z6 ? iBinarySearch : i7;
            }

            public static int c(p pVar, long j3) {
                int i7 = pVar.f15229a - 1;
                int i10 = 0;
                while (i10 <= i7) {
                    int i11 = (i10 + i7) >>> 1;
                    if (pVar.e(i11) < j3) {
                        i10 = i11 + 1;
                    } else {
                        i7 = i11 - 1;
                    }
                }
                int i12 = i7 + 1;
                if (i12 < pVar.f15229a && pVar.e(i12) == j3) {
                    return i12;
                }
                if (i7 == -1) {
                    return 0;
                }
                return i7;
            }

            public static int d(int[] iArr, int i7, boolean z6, boolean z10) {
                int i10;
                int i11;
                int iBinarySearch = Arrays.binarySearch(iArr, i7);
                if (iBinarySearch < 0) {
                    i11 = -(iBinarySearch + 2);
                } else {
                    while (true) {
                        i10 = iBinarySearch - 1;
                        if (i10 < 0 || iArr[i10] != i7) {
                            break;
                        }
                        iBinarySearch = i10;
                    }
                    i11 = z6 ? iBinarySearch : i10;
                }
                return z10 ? Math.max(0, i11) : i11;
            }

            public static int e(long[] jArr, long j3, boolean z6) {
                int i7;
                int iBinarySearch = Arrays.binarySearch(jArr, j3);
                if (iBinarySearch < 0) {
                    i7 = -(iBinarySearch + 2);
                } else {
                    while (true) {
                        int i10 = iBinarySearch - 1;
                        if (i10 < 0 || jArr[i10] != j3) {
                            break;
                        }
                        iBinarySearch = i10;
                    }
                    i7 = iBinarySearch;
                }
                return z6 ? Math.max(0, i7) : i7;
            }

            public static int f(int i7, int i10) {
                return ((i7 + i10) - 1) / i10;
            }

            public static void g(Closeable closeable) {
                if (closeable != null) {
                    try {
                        closeable.close();
                    } catch (IOException unused) {
                    }
                }
            }

            public static float h(float f10, float f11, float f12) {
                return Math.max(f11, Math.min(f10, f12));
            }

            public static int i(int i7, int i10, int i11) {
                return Math.max(i10, Math.min(i7, i11));
            }

            public static long j(long j3, long j10, long j11) {
                return Math.max(j10, Math.min(j3, j11));
            }

            public static int k(int i7, int i10, int i11, byte[] bArr) {
                while (i7 < i10) {
                    i11 = f15204m[((i11 >>> 24) ^ (bArr[i7] & 255)) & 255] ^ (i11 << 8);
                    i7++;
                }
                return i11;
            }

            public static Handler l(C2758d c2758d) {
                Looper looperMyLooper = Looper.myLooper();
                AbstractC1346c.k(looperMyLooper);
                return new Handler(looperMyLooper, c2758d);
            }

            public static String m(byte[] bArr) {
                return new String(bArr, StandardCharsets.UTF_8);
            }

            public static int n(int i7) {
                if (i7 == 20) {
                    return 30;
                }
                if (i7 == 22) {
                    return 31;
                }
                if (i7 == 30) {
                    return 34;
                }
                switch (i7) {
                    case 2:
                    case 3:
                        return 3;
                    case 4:
                    case 5:
                    case 6:
                        return 21;
                    case 7:
                    case 8:
                        return 23;
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                        return 28;
                    default:
                        switch (i7) {
                            case 14:
                                return 25;
                            case 15:
                            case 16:
                            case 17:
                            case 18:
                                return 28;
                            default:
                                return Integer.MAX_VALUE;
                        }
                }
            }

            public static AudioFormat o(int i7, int i10, int i11) {
                return new AudioFormat.Builder().setSampleRate(i7).setChannelMask(i10).setEncoding(i11).build();
            }

            public static int p(int i7) {
                int i10 = f15195a;
                if (i7 == 10) {
                    return i10 >= 32 ? 737532 : 6396;
                }
                if (i7 == 12) {
                    return 743676;
                }
                if (i7 == 24) {
                    return i10 >= 32 ? 67108860 : 0;
                }
                switch (i7) {
                    case 1:
                        return 4;
                    case 2:
                        return 12;
                    case 3:
                        return 28;
                    case 4:
                        return 204;
                    case 5:
                        return 220;
                    case 6:
                        return 252;
                    case 7:
                        return 1276;
                    case 8:
                        return 6396;
                    default:
                        return 0;
                }
            }

            public static Looper q() {
                Looper looperMyLooper = Looper.myLooper();
                return looperMyLooper != null ? looperMyLooper : Looper.getMainLooper();
            }

            public static int r(int i7) {
                if (i7 == 2 || i7 == 4) {
                    return 6005;
                }
                if (i7 == 10) {
                    return 6004;
                }
                if (i7 == 7) {
                    return 6005;
                }
                if (i7 == 8) {
                    return 6003;
                }
                switch (i7) {
                    case 15:
                        return 6003;
                    case 16:
                    case 18:
                        return 6005;
                    case 17:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                        return 6004;
                    default:
                        switch (i7) {
                            case 24:
                            case 25:
                            case 26:
                            case 27:
                            case 28:
                                return 6002;
                            default:
                                return 6006;
                        }
                }
            }

            public static int s(String str) {
                String[] strArrSplit;
                int length;
                if (str == null || (length = (strArrSplit = str.split("_", -1)).length) < 2) {
                    return 0;
                }
                String str2 = strArrSplit[length - 1];
                boolean z6 = length >= 3 && "neg".equals(strArrSplit[length - 2]);
                try {
                    str2.getClass();
                    int i7 = Integer.parseInt(str2);
                    return z6 ? -i7 : i7;
                } catch (NumberFormatException unused) {
                    return 0;
                }
            }

            public static long t(float f10, long j3) {
                return f10 == 1.0f ? j3 : Math.round(j3 * ((double) f10));
            }

            public static int u(int i7) {
                if (i7 == 8) {
                    return 3;
                }
                if (i7 == 16) {
                    return 2;
                }
                if (i7 != 24) {
                    return i7 != 32 ? 0 : 22;
                }
                return 21;
            }

            /* JADX WARN: Removed duplicated region for block: B:22:0x0026  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public static int v(int r4, int r5) {
                /*
                    r0 = 2
                    if (r4 == r0) goto L29
                    r1 = 3
                    if (r4 == r1) goto L28
                    r2 = 4
                    if (r4 == r2) goto L26
                    r3 = 21
                    if (r4 == r3) goto L24
                    r3 = 22
                    if (r4 == r3) goto L26
                    r3 = 268435456(0x10000000, float:2.524355E-29)
                    if (r4 == r3) goto L29
                    r0 = 1342177280(0x50000000, float:8.589935E9)
                    if (r4 == r0) goto L24
                    r0 = 1610612736(0x60000000, float:3.689349E19)
                    if (r4 != r0) goto L1e
                    goto L26
                L1e:
                    java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
                    r4.<init>()
                    throw r4
                L24:
                    r0 = r1
                    goto L29
                L26:
                    r0 = r2
                    goto L29
                L28:
                    r0 = 1
                L29:
                    int r0 = r0 * r5
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: h2.AbstractC1343A.v(int, int):int");
            }

            public static String w(StringBuilder sb, Formatter formatter, long j3) {
                if (j3 == -9223372036854775807L) {
                    j3 = 0;
                }
                String str = j3 < 0 ? "-" : "";
                long jAbs = (Math.abs(j3) + 500) / 1000;
                long j10 = jAbs % 60;
                long j11 = (jAbs / 60) % 60;
                long j12 = jAbs / 3600;
                sb.setLength(0);
                return j12 > 0 ? formatter.format("%s%d:%02d:%02d", str, Long.valueOf(j12), Long.valueOf(j11), Long.valueOf(j10)).toString() : formatter.format("%s%02d:%02d", str, Long.valueOf(j11), Long.valueOf(j10)).toString();
            }

            public static String[] x() {
                Configuration configuration = Resources.getSystem().getConfiguration();
                String[] strArrSplit = f15195a >= 24 ? configuration.getLocales().toLanguageTags().split(",", -1) : new String[]{configuration.locale.toLanguageTag()};
                for (int i7 = 0; i7 < strArrSplit.length; i7++) {
                    strArrSplit[i7] = I(strArrSplit[i7]);
                }
                return strArrSplit;
            }

            public static String y(String str) {
                try {
                    Class<?> cls = Class.forName("android.os.SystemProperties");
                    return (String) cls.getMethod("get", String.class).invoke(cls, str);
                } catch (Exception e10) {
                    AbstractC1346c.q("Util", "Failed to read system property ".concat(str), e10);
                    return null;
                }
            }

            public static String z(int i7) {
                switch (i7) {
                    case -2:
                        return "none";
                    case -1:
                        return "unknown";
                    case 0:
                        return "default";
                    case 1:
                        return "audio";
                    case 2:
                        return "video";
                    case 3:
                        return "text";
                    case 4:
                        return "image";
                    case 5:
                        return "metadata";
                    case 6:
                        return "camera motion";
                    default:
                        return i7 >= 10000 ? A7.k.g("custom (", i7, ")") : "?";
                }
            }
        }
