package c9;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import r9.C2319i;
import x3.AbstractC2673a;

/* JADX INFO: renamed from: c9.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1041b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C1041b f12543a = new C1041b();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C1041b f12544b = new C1041b();
    public static final C1041b c = new C1041b();

    public static final C1051l a(C1041b c1041b, String str) {
        C1051l c1051l = new C1051l(str);
        C1051l.d.put(str, c1051l);
        return c1051l;
    }

    public static String b(String str, int i7, int i10, String str2, boolean z6, boolean z10, boolean z11, boolean z12, Charset charset, int i11) {
        int i12 = (i11 & 1) != 0 ? 0 : i7;
        int length = (i11 & 2) != 0 ? str.length() : i10;
        boolean z13 = (i11 & 8) != 0 ? false : z6;
        boolean z14 = (i11 & 16) != 0 ? false : z10;
        boolean z15 = (i11 & 32) != 0 ? false : z11;
        boolean z16 = (i11 & 64) != 0 ? false : z12;
        int i13 = 128;
        Charset charset2 = (i11 & 128) != 0 ? null : charset;
        A7.m.f(str, "<this>");
        int iCharCount = i12;
        while (iCharCount < length) {
            int iCodePointAt = str.codePointAt(iCharCount);
            int i14 = 32;
            int i15 = 127;
            if (iCodePointAt < 32 || iCodePointAt == 127 || ((iCodePointAt >= i13 && !z16) || Q8.h.D0(str2, (char) iCodePointAt) || ((iCodePointAt == 37 && (!z13 || (z14 && !d(str, iCharCount, length)))) || (iCodePointAt == 43 && z15)))) {
                C2319i c2319i = new C2319i();
                c2319i.d0(str, i12, iCharCount);
                C2319i c2319i2 = null;
                while (iCharCount < length) {
                    int iCodePointAt2 = str.codePointAt(iCharCount);
                    if (!z13 || (iCodePointAt2 != 9 && iCodePointAt2 != 10 && iCodePointAt2 != 12 && iCodePointAt2 != 13)) {
                        if (iCodePointAt2 == 43 && z15) {
                            c2319i.c0(z13 ? "+" : "%2B");
                        } else {
                            if (iCodePointAt2 >= i14 && iCodePointAt2 != i15) {
                                if ((iCodePointAt2 < 128 || z16) && !Q8.h.D0(str2, (char) iCodePointAt2) && (iCodePointAt2 != 37 || (z13 && (!z14 || d(str, iCharCount, length))))) {
                                    c2319i.e0(iCodePointAt2);
                                }
                            }
                            if (c2319i2 == null) {
                                c2319i2 = new C2319i();
                            }
                            if (charset2 == null || charset2.equals(StandardCharsets.UTF_8)) {
                                c2319i2.e0(iCodePointAt2);
                            } else {
                                int iCharCount2 = Character.charCount(iCodePointAt2) + iCharCount;
                                if (iCharCount < 0) {
                                    throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.e(iCharCount, "beginIndex < 0: ").toString());
                                }
                                if (iCharCount2 < iCharCount) {
                                    throw new IllegalArgumentException(A7.k.i("endIndex < beginIndex: ", " < ", iCharCount2, iCharCount).toString());
                                }
                                if (iCharCount2 > str.length()) {
                                    StringBuilder sbJ = M5.b.j("endIndex > string.length: ", iCharCount2, " > ");
                                    sbJ.append(str.length());
                                    throw new IllegalArgumentException(sbJ.toString().toString());
                                }
                                if (charset2.equals(Q8.a.f7211a)) {
                                    c2319i2.d0(str, iCharCount, iCharCount2);
                                } else {
                                    String strSubstring = str.substring(iCharCount, iCharCount2);
                                    A7.m.e(strSubstring, "substring(...)");
                                    byte[] bytes = strSubstring.getBytes(charset2);
                                    A7.m.e(bytes, "getBytes(...)");
                                    c2319i2.W(bytes, 0, bytes.length);
                                }
                            }
                            while (!c2319i2.j()) {
                                byte bZ = c2319i2.z();
                                c2319i.X(37);
                                char[] cArr = w.f12618j;
                                c2319i.X(cArr[((bZ & 255) >> 4) & 15]);
                                c2319i.X(cArr[bZ & 15]);
                            }
                        }
                    }
                    iCharCount += Character.charCount(iCodePointAt2);
                    i15 = 127;
                    i14 = 32;
                }
                return c2319i.Q();
            }
            iCharCount += Character.charCount(iCodePointAt);
            i13 = 128;
        }
        String strSubstring2 = str.substring(i12, length);
        A7.m.e(strSubstring2, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring2;
    }

    public static boolean d(String str, int i7, int i10) {
        int i11 = i7 + 2;
        return i11 < i10 && str.charAt(i7) == '%' && d9.b.r(str.charAt(i7 + 1)) != -1 && d9.b.r(str.charAt(i11)) != -1;
    }

    public static String e(String str, int i7, int i10, boolean z6, int i11) {
        int i12;
        if ((i11 & 1) != 0) {
            i7 = 0;
        }
        if ((i11 & 2) != 0) {
            i10 = str.length();
        }
        if ((i11 & 4) != 0) {
            z6 = false;
        }
        A7.m.f(str, "<this>");
        int iCharCount = i7;
        while (iCharCount < i10) {
            char cCharAt = str.charAt(iCharCount);
            if (cCharAt == '%' || (cCharAt == '+' && z6)) {
                C2319i c2319i = new C2319i();
                c2319i.d0(str, i7, iCharCount);
                while (iCharCount < i10) {
                    int iCodePointAt = str.codePointAt(iCharCount);
                    if (iCodePointAt == 37 && (i12 = iCharCount + 2) < i10) {
                        int iR = d9.b.r(str.charAt(iCharCount + 1));
                        int iR2 = d9.b.r(str.charAt(i12));
                        if (iR == -1 || iR2 == -1) {
                            c2319i.e0(iCodePointAt);
                            iCharCount += Character.charCount(iCodePointAt);
                        } else {
                            c2319i.X((iR << 4) + iR2);
                            iCharCount = Character.charCount(iCodePointAt) + i12;
                        }
                    } else if (iCodePointAt == 43 && z6) {
                        c2319i.X(32);
                        iCharCount++;
                    } else {
                        c2319i.e0(iCodePointAt);
                        iCharCount += Character.charCount(iCodePointAt);
                    }
                }
                return c2319i.Q();
            }
            iCharCount++;
        }
        String strSubstring = str.substring(i7, i10);
        A7.m.e(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public static ArrayList f(String str) {
        ArrayList arrayList = new ArrayList();
        int i7 = 0;
        while (i7 <= str.length()) {
            int iJ0 = Q8.h.J0(str, '&', i7, false, 4);
            if (iJ0 == -1) {
                iJ0 = str.length();
            }
            int iJ02 = Q8.h.J0(str, '=', i7, false, 4);
            if (iJ02 == -1 || iJ02 > iJ0) {
                String strSubstring = str.substring(i7, iJ0);
                A7.m.e(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
                arrayList.add(strSubstring);
                arrayList.add(null);
            } else {
                String strSubstring2 = str.substring(i7, iJ02);
                A7.m.e(strSubstring2, "this as java.lang.String…ing(startIndex, endIndex)");
                arrayList.add(strSubstring2);
                String strSubstring3 = str.substring(iJ02 + 1, iJ0);
                A7.m.e(strSubstring3, "this as java.lang.String…ing(startIndex, endIndex)");
                arrayList.add(strSubstring3);
            }
            i7 = iJ0 + 1;
        }
        return arrayList;
    }

    public static void g(ArrayList arrayList, StringBuilder sb) {
        A7.m.f(arrayList, "<this>");
        G7.b bVarW = AbstractC2673a.W(AbstractC2673a.b0(0, arrayList.size()), 2);
        int i7 = bVarW.l;
        int i10 = bVarW.f2712m;
        int i11 = bVarW.f2713n;
        if ((i11 <= 0 || i7 > i10) && (i11 >= 0 || i10 > i7)) {
            return;
        }
        while (true) {
            String str = (String) arrayList.get(i7);
            String str2 = (String) arrayList.get(i7 + 1);
            if (i7 > 0) {
                sb.append('&');
            }
            sb.append(str);
            if (str2 != null) {
                sb.append('=');
                sb.append(str2);
            }
            if (i7 == i10) {
                return;
            } else {
                i7 += i11;
            }
        }
    }

    public synchronized C1051l c(String str) {
        C1051l c1051l;
        String strConcat;
        try {
            A7.m.f(str, "javaName");
            LinkedHashMap linkedHashMap = C1051l.d;
            c1051l = (C1051l) linkedHashMap.get(str);
            if (c1051l == null) {
                if (Q8.o.z0(str, "TLS_", false)) {
                    String strSubstring = str.substring(4);
                    A7.m.e(strSubstring, "this as java.lang.String).substring(startIndex)");
                    strConcat = "SSL_".concat(strSubstring);
                } else if (Q8.o.z0(str, "SSL_", false)) {
                    String strSubstring2 = str.substring(4);
                    A7.m.e(strSubstring2, "this as java.lang.String).substring(startIndex)");
                    strConcat = "TLS_".concat(strSubstring2);
                } else {
                    strConcat = str;
                }
                c1051l = (C1051l) linkedHashMap.get(strConcat);
                if (c1051l == null) {
                    c1051l = new C1051l(str);
                }
                linkedHashMap.put(str, c1051l);
            }
        } catch (Throwable th) {
            throw th;
        }
        return c1051l;
    }
}
