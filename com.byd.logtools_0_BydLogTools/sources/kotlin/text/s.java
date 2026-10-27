package kotlin.text;

import java.util.Comparator;
import okhttp3.HttpUrl;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: StringsJVM.kt */
/* JADX INFO: loaded from: classes.dex */
public class s extends r {
    public static final boolean h(String str, String str2, boolean z) {
        kotlin.jvm.internal.i.c(str, "$this$endsWith");
        kotlin.jvm.internal.i.c(str2, "suffix");
        return !z ? str.endsWith(str2) : m(str, str.length() - str2.length(), str2, 0, str2.length(), true);
    }

    public static /* synthetic */ boolean i(String str, String str2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return h(str, str2, z);
    }

    public static boolean j(String str, String str2, boolean z) {
        return str == null ? str2 == null : !z ? str.equals(str2) : str.equalsIgnoreCase(str2);
    }

    public static Comparator<String> k(kotlin.jvm.internal.l lVar) {
        kotlin.jvm.internal.i.c(lVar, "$this$CASE_INSENSITIVE_ORDER");
        Comparator<String> comparator = String.CASE_INSENSITIVE_ORDER;
        kotlin.jvm.internal.i.b(comparator, "java.lang.String.CASE_INSENSITIVE_ORDER");
        return comparator;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final boolean l(java.lang.CharSequence r4) {
        /*
            java.lang.String r0 = "$this$isBlank"
            kotlin.jvm.internal.i.c(r4, r0)
            int r0 = r4.length()
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L3e
            kotlin.o.c r0 = kotlin.text.StringsKt__StringsKt.F(r4)
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
            kotlin.collections.u r3 = (kotlin.collections.u) r3
            int r3 = r3.b()
            char r3 = r4.charAt(r3)
            boolean r3 = kotlin.text.b.c(r3)
            if (r3 != 0) goto L24
            r4 = r1
        L3c:
            if (r4 == 0) goto L3f
        L3e:
            r1 = r2
        L3f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.s.l(java.lang.CharSequence):boolean");
    }

    public static final boolean m(String str, int i, String str2, int i2, int i3, boolean z) {
        kotlin.jvm.internal.i.c(str, "$this$regionMatches");
        kotlin.jvm.internal.i.c(str2, "other");
        return !z ? str.regionMatches(i, str2, i2, i3) : str.regionMatches(z, i, str2, i2, i3);
    }

    public static /* synthetic */ boolean n(String str, int i, String str2, int i2, int i3, boolean z, int i4, Object obj) {
        if ((i4 & 16) != 0) {
            z = false;
        }
        return m(str, i, str2, i2, i3, z);
    }

    public static String o(CharSequence charSequence, int i) {
        kotlin.jvm.internal.i.c(charSequence, "$this$repeat");
        int i2 = 1;
        if (!(i >= 0)) {
            throw new IllegalArgumentException(("Count 'n' must be non-negative, but was " + i + '.').toString());
        }
        if (i == 0) {
            return HttpUrl.FRAGMENT_ENCODE_SET;
        }
        if (i == 1) {
            return charSequence.toString();
        }
        int length = charSequence.length();
        if (length == 0) {
            return HttpUrl.FRAGMENT_ENCODE_SET;
        }
        if (length == 1) {
            char cCharAt = charSequence.charAt(0);
            char[] cArr = new char[i];
            for (int i3 = 0; i3 < i; i3++) {
                cArr[i3] = cCharAt;
            }
            return new String(cArr);
        }
        StringBuilder sb = new StringBuilder(charSequence.length() * i);
        if (1 <= i) {
            while (true) {
                sb.append(charSequence);
                if (i2 == i) {
                    break;
                }
                i2++;
            }
        }
        String string = sb.toString();
        kotlin.jvm.internal.i.b(string, "sb.toString()");
        return string;
    }

    public static final String p(String str, char c2, char c3, boolean z) {
        kotlin.jvm.internal.i.c(str, "$this$replace");
        if (z) {
            return kotlin.p.j.f(StringsKt__StringsKt.j0(str, new char[]{c2}, z, 0, 4, null), String.valueOf(c3), null, null, 0, null, null, 62, null);
        }
        String strReplace = str.replace(c2, c3);
        kotlin.jvm.internal.i.b(strReplace, "(this as java.lang.Strin…replace(oldChar, newChar)");
        return strReplace;
    }

    public static final String q(String str, String str2, String str3, boolean z) {
        kotlin.jvm.internal.i.c(str, "$this$replace");
        kotlin.jvm.internal.i.c(str2, "oldValue");
        kotlin.jvm.internal.i.c(str3, "newValue");
        return kotlin.p.j.f(StringsKt__StringsKt.k0(str, new String[]{str2}, z, 0, 4, null), str3, null, null, 0, null, null, 62, null);
    }

    public static /* synthetic */ String r(String str, char c2, char c3, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return p(str, c2, c3, z);
    }

    public static /* synthetic */ String s(String str, String str2, String str3, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return q(str, str2, str3, z);
    }

    public static boolean t(String str, String str2, int i, boolean z) {
        kotlin.jvm.internal.i.c(str, "$this$startsWith");
        kotlin.jvm.internal.i.c(str2, "prefix");
        return !z ? str.startsWith(str2, i) : m(str, i, str2, 0, str2.length(), z);
    }

    public static boolean u(String str, String str2, boolean z) {
        kotlin.jvm.internal.i.c(str, "$this$startsWith");
        kotlin.jvm.internal.i.c(str2, "prefix");
        return !z ? str.startsWith(str2) : m(str, 0, str2, 0, str2.length(), z);
    }

    public static /* synthetic */ boolean v(String str, String str2, int i, boolean z, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = false;
        }
        return t(str, str2, i, z);
    }

    public static /* synthetic */ boolean w(String str, String str2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return u(str, str2, z);
    }
}
