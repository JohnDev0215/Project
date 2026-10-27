package y5;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: StringsJVM.kt */
/* JADX INFO: loaded from: classes.dex */
public class p extends o {
    public static /* synthetic */ boolean A(String str, String str2, boolean z6, int i7, Object obj) {
        if ((i7 & 2) != 0) {
            z6 = false;
        }
        return y(str, str2, z6);
    }

    public static final boolean m(String str, String suffix, boolean z6) {
        kotlin.jvm.internal.l.e(str, "<this>");
        kotlin.jvm.internal.l.e(suffix, "suffix");
        return !z6 ? str.endsWith(suffix) : q(str, str.length() - suffix.length(), suffix, 0, suffix.length(), true);
    }

    public static /* synthetic */ boolean n(String str, String str2, boolean z6, int i7, Object obj) {
        if ((i7 & 2) != 0) {
            z6 = false;
        }
        return m(str, str2, z6);
    }

    public static boolean o(String str, String str2, boolean z6) {
        return str == null ? str2 == null : !z6 ? str.equals(str2) : str.equalsIgnoreCase(str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final boolean p(java.lang.CharSequence r4) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.l.e(r4, r0)
            int r0 = r4.length()
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L3e
            v5.c r0 = y5.q.J(r4)
            boolean r3 = r0 instanceof java.util.Collection
            if (r3 == 0) goto L20
            r3 = r0
            java.util.Collection r3 = (java.util.Collection) r3
            boolean r3 = r3.isEmpty()
            if (r3 == 0) goto L20
        L1e:
            r4 = r2
            goto L3c
        L20:
            java.util.Iterator r0 = r0.iterator()
        L24:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L1e
            r3 = r0
            h5.d0 r3 = (h5.d0) r3
            int r3 = r3.a()
            char r3 = r4.charAt(r3)
            boolean r3 = y5.b.c(r3)
            if (r3 != 0) goto L24
            r4 = r1
        L3c:
            if (r4 == 0) goto L3f
        L3e:
            r1 = r2
        L3f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: y5.p.p(java.lang.CharSequence):boolean");
    }

    public static final boolean q(String str, int i7, String other, int i8, int i9, boolean z6) {
        kotlin.jvm.internal.l.e(str, "<this>");
        kotlin.jvm.internal.l.e(other, "other");
        return !z6 ? str.regionMatches(i7, other, i8, i9) : str.regionMatches(z6, i7, other, i8, i9);
    }

    public static /* synthetic */ boolean r(String str, int i7, String str2, int i8, int i9, boolean z6, int i10, Object obj) {
        if ((i10 & 16) != 0) {
            z6 = false;
        }
        return q(str, i7, str2, i8, i9, z6);
    }

    public static String s(CharSequence charSequence, int i7) {
        kotlin.jvm.internal.l.e(charSequence, "<this>");
        int i8 = 1;
        if (!(i7 >= 0)) {
            throw new IllegalArgumentException(("Count 'n' must be non-negative, but was " + i7 + '.').toString());
        }
        if (i7 == 0) {
            return "";
        }
        if (i7 == 1) {
            return charSequence.toString();
        }
        int length = charSequence.length();
        if (length == 0) {
            return "";
        }
        if (length == 1) {
            char cCharAt = charSequence.charAt(0);
            char[] cArr = new char[i7];
            for (int i9 = 0; i9 < i7; i9++) {
                cArr[i9] = cCharAt;
            }
            return new String(cArr);
        }
        StringBuilder sb = new StringBuilder(charSequence.length() * i7);
        if (1 <= i7) {
            while (true) {
                sb.append(charSequence);
                if (i8 == i7) {
                    break;
                }
                i8++;
            }
        }
        String string = sb.toString();
        kotlin.jvm.internal.l.d(string, "{\n                    va…tring()\n                }");
        return string;
    }

    public static final String t(String str, char c7, char c8, boolean z6) {
        kotlin.jvm.internal.l.e(str, "<this>");
        if (!z6) {
            String strReplace = str.replace(c7, c8);
            kotlin.jvm.internal.l.d(strReplace, "this as java.lang.String…replace(oldChar, newChar)");
            return strReplace;
        }
        StringBuilder sb = new StringBuilder(str.length());
        for (int i7 = 0; i7 < str.length(); i7++) {
            char cCharAt = str.charAt(i7);
            if (c.d(cCharAt, c7, z6)) {
                cCharAt = c8;
            }
            sb.append(cCharAt);
        }
        String string = sb.toString();
        kotlin.jvm.internal.l.d(string, "StringBuilder(capacity).…builderAction).toString()");
        return string;
    }

    public static final String u(String str, String oldValue, String newValue, boolean z6) {
        kotlin.jvm.internal.l.e(str, "<this>");
        kotlin.jvm.internal.l.e(oldValue, "oldValue");
        kotlin.jvm.internal.l.e(newValue, "newValue");
        int i7 = 0;
        int iM = q.M(str, oldValue, 0, z6);
        if (iM < 0) {
            return str;
        }
        int length = oldValue.length();
        int iB = v5.h.b(length, 1);
        int length2 = (str.length() - length) + newValue.length();
        if (length2 < 0) {
            throw new OutOfMemoryError();
        }
        StringBuilder sb = new StringBuilder(length2);
        do {
            sb.append((CharSequence) str, i7, iM);
            sb.append(newValue);
            i7 = iM + length;
            if (iM >= str.length()) {
                break;
            }
            iM = q.M(str, oldValue, iM + iB, z6);
        } while (iM > 0);
        sb.append((CharSequence) str, i7, str.length());
        String string = sb.toString();
        kotlin.jvm.internal.l.d(string, "stringBuilder.append(this, i, length).toString()");
        return string;
    }

    public static /* synthetic */ String v(String str, char c7, char c8, boolean z6, int i7, Object obj) {
        if ((i7 & 4) != 0) {
            z6 = false;
        }
        return t(str, c7, c8, z6);
    }

    public static /* synthetic */ String w(String str, String str2, String str3, boolean z6, int i7, Object obj) {
        if ((i7 & 4) != 0) {
            z6 = false;
        }
        return u(str, str2, str3, z6);
    }

    public static boolean x(String str, String prefix, int i7, boolean z6) {
        kotlin.jvm.internal.l.e(str, "<this>");
        kotlin.jvm.internal.l.e(prefix, "prefix");
        return !z6 ? str.startsWith(prefix, i7) : q(str, i7, prefix, 0, prefix.length(), z6);
    }

    public static boolean y(String str, String prefix, boolean z6) {
        kotlin.jvm.internal.l.e(str, "<this>");
        kotlin.jvm.internal.l.e(prefix, "prefix");
        return !z6 ? str.startsWith(prefix) : q(str, 0, prefix, 0, prefix.length(), z6);
    }

    public static /* synthetic */ boolean z(String str, String str2, int i7, boolean z6, int i8, Object obj) {
        if ((i8 & 4) != 0) {
            z6 = false;
        }
        return x(str, str2, i7, z6);
    }
}
