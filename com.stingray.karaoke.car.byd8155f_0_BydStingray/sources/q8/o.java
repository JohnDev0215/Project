package Q8;

import g1.AbstractC1274a;

/* JADX INFO: loaded from: classes.dex */
public abstract class o extends n {
    public static Integer A0(String str) {
        boolean z6;
        int i7;
        int i10;
        AbstractC1274a.y(10);
        int length = str.length();
        if (length == 0) {
            return null;
        }
        int i11 = 0;
        char cCharAt = str.charAt(0);
        int i12 = -2147483647;
        if (A7.m.g(cCharAt, 48) < 0) {
            i7 = 1;
            if (length == 1) {
                return null;
            }
            if (cCharAt == '+') {
                z6 = false;
            } else {
                if (cCharAt != '-') {
                    return null;
                }
                i12 = Integer.MIN_VALUE;
                z6 = true;
            }
        } else {
            z6 = false;
            i7 = 0;
        }
        int i13 = -59652323;
        while (i7 < length) {
            int iDigit = Character.digit((int) str.charAt(i7), 10);
            if (iDigit < 0) {
                return null;
            }
            if ((i11 < i13 && (i13 != -59652323 || i11 < (i13 = i12 / 10))) || (i10 = i11 * 10) < i12 + iDigit) {
                return null;
            }
            i11 = i10 - iDigit;
            i7++;
        }
        return z6 ? Integer.valueOf(i11) : Integer.valueOf(-i11);
    }

    public static Long B0(String str) {
        boolean z6;
        A7.m.f(str, "<this>");
        AbstractC1274a.y(10);
        int length = str.length();
        if (length == 0) {
            return null;
        }
        int i7 = 0;
        char cCharAt = str.charAt(0);
        long j3 = -9223372036854775807L;
        if (A7.m.g(cCharAt, 48) < 0) {
            z6 = true;
            if (length == 1) {
                return null;
            }
            if (cCharAt == '+') {
                z6 = false;
                i7 = 1;
            } else {
                if (cCharAt != '-') {
                    return null;
                }
                j3 = Long.MIN_VALUE;
                i7 = 1;
            }
        } else {
            z6 = false;
        }
        long j10 = -256204778801521550L;
        long j11 = 0;
        long j12 = -256204778801521550L;
        while (i7 < length) {
            int iDigit = Character.digit((int) str.charAt(i7), 10);
            if (iDigit < 0) {
                return null;
            }
            if (j11 < j12) {
                if (j12 != j10) {
                    return null;
                }
                j12 = j3 / ((long) 10);
                if (j11 < j12) {
                    return null;
                }
            }
            long j13 = j11 * ((long) 10);
            long j14 = iDigit;
            if (j13 < j3 + j14) {
                return null;
            }
            j11 = j13 - j14;
            i7++;
            j10 = -256204778801521550L;
        }
        return z6 ? Long.valueOf(j11) : Long.valueOf(-j11);
    }

    public static boolean s0(String str, String str2, boolean z6) {
        A7.m.f(str, "<this>");
        return !z6 ? str.endsWith(str2) : u0(str.length() - str2.length(), 0, str2.length(), str, str2, true);
    }

    public static boolean t0(String str, String str2, boolean z6) {
        return str == null ? str2 == null : !z6 ? str.equals(str2) : str.equalsIgnoreCase(str2);
    }

    public static final boolean u0(int i7, int i10, int i11, String str, String str2, boolean z6) {
        A7.m.f(str, "<this>");
        A7.m.f(str2, "other");
        return !z6 ? str.regionMatches(i7, str2, i10, i11) : str.regionMatches(z6, i7, str2, i10, i11);
    }

    public static String v0(int i7, String str) {
        if (i7 < 0) {
            throw new IllegalArgumentException(("Count 'n' must be non-negative, but was " + i7 + '.').toString());
        }
        if (i7 == 0) {
            return "";
        }
        int i10 = 1;
        if (i7 == 1) {
            return str.toString();
        }
        int length = str.length();
        if (length == 0) {
            return "";
        }
        if (length == 1) {
            char cCharAt = str.charAt(0);
            char[] cArr = new char[i7];
            for (int i11 = 0; i11 < i7; i11++) {
                cArr[i11] = cCharAt;
            }
            return new String(cArr);
        }
        StringBuilder sb = new StringBuilder(str.length() * i7);
        if (1 <= i7) {
            while (true) {
                sb.append((CharSequence) str);
                if (i10 == i7) {
                    break;
                }
                i10++;
            }
        }
        String string = sb.toString();
        A7.m.c(string);
        return string;
    }

    public static String w0(String str, char c, char c10) {
        A7.m.f(str, "<this>");
        String strReplace = str.replace(c, c10);
        A7.m.e(strReplace, "replace(...)");
        return strReplace;
    }

    public static String x0(String str, String str2, String str3) {
        A7.m.f(str, "<this>");
        A7.m.f(str3, "newValue");
        int iH0 = h.H0(0, str, str2, false);
        if (iH0 < 0) {
            return str;
        }
        int length = str2.length();
        int i7 = length >= 1 ? length : 1;
        int length2 = str3.length() + (str.length() - length);
        if (length2 < 0) {
            throw new OutOfMemoryError();
        }
        StringBuilder sb = new StringBuilder(length2);
        int i10 = 0;
        do {
            sb.append((CharSequence) str, i10, iH0);
            sb.append(str3);
            i10 = iH0 + length;
            if (iH0 >= str.length()) {
                break;
            }
            iH0 = h.H0(iH0 + i7, str, str2, false);
        } while (iH0 > 0);
        sb.append((CharSequence) str, i10, str.length());
        String string = sb.toString();
        A7.m.e(string, "toString(...)");
        return string;
    }

    public static boolean y0(int i7, String str, String str2, boolean z6) {
        A7.m.f(str, "<this>");
        return !z6 ? str.startsWith(str2, i7) : u0(i7, 0, str2.length(), str, str2, z6);
    }

    public static boolean z0(String str, String str2, boolean z6) {
        A7.m.f(str, "<this>");
        A7.m.f(str2, "prefix");
        return !z6 ? str.startsWith(str2) : u0(0, 0, str2.length(), str, str2, z6);
    }
}
