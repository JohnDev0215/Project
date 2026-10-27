package kotlin.text;

import com.bydux.api.common.tools.cmd.BydAdbHelper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: Strings.kt */
/* JADX INFO: loaded from: classes.dex */
public class StringsKt__StringsKt extends s {
    public static /* synthetic */ boolean A(CharSequence charSequence, char c2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return y(charSequence, c2, z);
    }

    public static /* synthetic */ boolean B(CharSequence charSequence, CharSequence charSequence2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return z(charSequence, charSequence2, z);
    }

    public static final boolean C(CharSequence charSequence, CharSequence charSequence2, boolean z) {
        kotlin.jvm.internal.i.c(charSequence, "$this$endsWith");
        kotlin.jvm.internal.i.c(charSequence2, "suffix");
        return (!z && (charSequence instanceof String) && (charSequence2 instanceof String)) ? s.i((String) charSequence, (String) charSequence2, false, 2, null) : Z(charSequence, charSequence.length() - charSequence2.length(), charSequence2, 0, charSequence2.length(), z);
    }

    public static /* synthetic */ boolean D(CharSequence charSequence, CharSequence charSequence2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return C(charSequence, charSequence2, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Pair<Integer, String> E(CharSequence charSequence, Collection<String> collection, int i, boolean z, boolean z2) {
        Object next;
        Object next2;
        if (!z && collection.size() == 1) {
            String str = (String) kotlin.collections.i.E(collection);
            int iM = !z2 ? M(charSequence, str, i, false, 4, null) : R(charSequence, str, i, false, 4, null);
            if (iM < 0) {
                return null;
            }
            return kotlin.h.a(Integer.valueOf(iM), str);
        }
        kotlin.o.a cVar = !z2 ? new kotlin.o.c(kotlin.o.f.b(i, 0), charSequence.length()) : kotlin.o.f.f(kotlin.o.f.c(i, G(charSequence)), 0);
        if (charSequence instanceof String) {
            int iA = cVar.a();
            int iB = cVar.b();
            int iC = cVar.c();
            if (iC < 0 ? iA >= iB : iA <= iB) {
                while (true) {
                    Iterator<T> it = collection.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            next2 = null;
                            break;
                        }
                        next2 = it.next();
                        String str2 = (String) next2;
                        if (s.m(str2, 0, (String) charSequence, iA, str2.length(), z)) {
                            break;
                        }
                    }
                    String str3 = (String) next2;
                    if (str3 == null) {
                        if (iA == iB) {
                            break;
                        }
                        iA += iC;
                    } else {
                        return kotlin.h.a(Integer.valueOf(iA), str3);
                    }
                }
            }
        } else {
            int iA2 = cVar.a();
            int iB2 = cVar.b();
            int iC2 = cVar.c();
            if (iC2 < 0 ? iA2 >= iB2 : iA2 <= iB2) {
                while (true) {
                    Iterator<T> it2 = collection.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            next = null;
                            break;
                        }
                        next = it2.next();
                        String str4 = (String) next;
                        if (Z(str4, 0, charSequence, iA2, str4.length(), z)) {
                            break;
                        }
                    }
                    String str5 = (String) next;
                    if (str5 == null) {
                        if (iA2 == iB2) {
                            break;
                        }
                        iA2 += iC2;
                    } else {
                        return kotlin.h.a(Integer.valueOf(iA2), str5);
                    }
                }
            }
        }
        return null;
    }

    public static final kotlin.o.c F(CharSequence charSequence) {
        kotlin.jvm.internal.i.c(charSequence, "$this$indices");
        return new kotlin.o.c(0, charSequence.length() - 1);
    }

    public static final int G(CharSequence charSequence) {
        kotlin.jvm.internal.i.c(charSequence, "$this$lastIndex");
        return charSequence.length() - 1;
    }

    public static final int H(CharSequence charSequence, char c2, int i, boolean z) {
        kotlin.jvm.internal.i.c(charSequence, "$this$indexOf");
        return (z || !(charSequence instanceof String)) ? N(charSequence, new char[]{c2}, i, z) : ((String) charSequence).indexOf(c2, i);
    }

    public static final int I(CharSequence charSequence, String str, int i, boolean z) {
        kotlin.jvm.internal.i.c(charSequence, "$this$indexOf");
        kotlin.jvm.internal.i.c(str, "string");
        return (z || !(charSequence instanceof String)) ? K(charSequence, str, i, charSequence.length(), z, false, 16, null) : ((String) charSequence).indexOf(str, i);
    }

    private static final int J(CharSequence charSequence, CharSequence charSequence2, int i, int i2, boolean z, boolean z2) {
        kotlin.o.a cVar = !z2 ? new kotlin.o.c(kotlin.o.f.b(i, 0), kotlin.o.f.c(i2, charSequence.length())) : kotlin.o.f.f(kotlin.o.f.c(i, G(charSequence)), kotlin.o.f.b(i2, 0));
        if ((charSequence instanceof String) && (charSequence2 instanceof String)) {
            int iA = cVar.a();
            int iB = cVar.b();
            int iC = cVar.c();
            if (iC >= 0) {
                if (iA > iB) {
                    return -1;
                }
            } else if (iA < iB) {
                return -1;
            }
            while (!s.m((String) charSequence2, 0, (String) charSequence, iA, charSequence2.length(), z)) {
                if (iA == iB) {
                    return -1;
                }
                iA += iC;
            }
            return iA;
        }
        int iA2 = cVar.a();
        int iB2 = cVar.b();
        int iC2 = cVar.c();
        if (iC2 >= 0) {
            if (iA2 > iB2) {
                return -1;
            }
        } else if (iA2 < iB2) {
            return -1;
        }
        while (!Z(charSequence2, 0, charSequence, iA2, charSequence2.length(), z)) {
            if (iA2 == iB2) {
                return -1;
            }
            iA2 += iC2;
        }
        return iA2;
    }

    static /* synthetic */ int K(CharSequence charSequence, CharSequence charSequence2, int i, int i2, boolean z, boolean z2, int i3, Object obj) {
        if ((i3 & 16) != 0) {
            z2 = false;
        }
        return J(charSequence, charSequence2, i, i2, z, z2);
    }

    public static /* synthetic */ int L(CharSequence charSequence, char c2, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return H(charSequence, c2, i, z);
    }

    public static /* synthetic */ int M(CharSequence charSequence, String str, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return I(charSequence, str, i, z);
    }

    public static final int N(CharSequence charSequence, char[] cArr, int i, boolean z) {
        boolean z2;
        kotlin.jvm.internal.i.c(charSequence, "$this$indexOfAny");
        kotlin.jvm.internal.i.c(cArr, "chars");
        if (!z && cArr.length == 1 && (charSequence instanceof String)) {
            return ((String) charSequence).indexOf(kotlin.collections.g.q(cArr), i);
        }
        int iB = kotlin.o.f.b(i, 0);
        int iG = G(charSequence);
        if (iB > iG) {
            return -1;
        }
        while (true) {
            char cCharAt = charSequence.charAt(iB);
            int length = cArr.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    z2 = false;
                    break;
                }
                if (c.d(cArr[i2], cCharAt, z)) {
                    z2 = true;
                    break;
                }
                i2++;
            }
            if (z2) {
                return iB;
            }
            if (iB == iG) {
                return -1;
            }
            iB++;
        }
    }

    public static final int O(CharSequence charSequence, char c2, int i, boolean z) {
        kotlin.jvm.internal.i.c(charSequence, "$this$lastIndexOf");
        return (z || !(charSequence instanceof String)) ? S(charSequence, new char[]{c2}, i, z) : ((String) charSequence).lastIndexOf(c2, i);
    }

    public static final int P(CharSequence charSequence, String str, int i, boolean z) {
        kotlin.jvm.internal.i.c(charSequence, "$this$lastIndexOf");
        kotlin.jvm.internal.i.c(str, "string");
        return (z || !(charSequence instanceof String)) ? J(charSequence, str, i, 0, z, true) : ((String) charSequence).lastIndexOf(str, i);
    }

    public static /* synthetic */ int Q(CharSequence charSequence, char c2, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = G(charSequence);
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return O(charSequence, c2, i, z);
    }

    public static /* synthetic */ int R(CharSequence charSequence, String str, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = G(charSequence);
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return P(charSequence, str, i, z);
    }

    public static final int S(CharSequence charSequence, char[] cArr, int i, boolean z) {
        kotlin.jvm.internal.i.c(charSequence, "$this$lastIndexOfAny");
        kotlin.jvm.internal.i.c(cArr, "chars");
        if (!z && cArr.length == 1 && (charSequence instanceof String)) {
            return ((String) charSequence).lastIndexOf(kotlin.collections.g.q(cArr), i);
        }
        for (int iC = kotlin.o.f.c(i, G(charSequence)); iC >= 0; iC--) {
            char cCharAt = charSequence.charAt(iC);
            int length = cArr.length;
            boolean z2 = false;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                }
                if (c.d(cArr[i2], cCharAt, z)) {
                    z2 = true;
                    break;
                }
                i2++;
            }
            if (z2) {
                return iC;
            }
        }
        return -1;
    }

    public static final kotlin.p.d<String> T(CharSequence charSequence) {
        kotlin.jvm.internal.i.c(charSequence, "$this$lineSequence");
        return k0(charSequence, new String[]{"\r\n", BydAdbHelper.COMMAND_LINE_END, "\r"}, false, 0, 6, null);
    }

    public static final List<String> U(CharSequence charSequence) {
        kotlin.jvm.internal.i.c(charSequence, "$this$lines");
        return kotlin.p.j.i(T(charSequence));
    }

    private static final kotlin.p.d<kotlin.o.c> V(CharSequence charSequence, final char[] cArr, int i, final boolean z, int i2) {
        if (i2 >= 0) {
            return new e(charSequence, i, i2, new kotlin.jvm.b.p<CharSequence, Integer, Pair<? extends Integer, ? extends Integer>>() { // from class: kotlin.text.StringsKt__StringsKt$rangesDelimitedBy$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.b.p
                public /* bridge */ /* synthetic */ Pair<? extends Integer, ? extends Integer> invoke(CharSequence charSequence2, Integer num) {
                    return invoke(charSequence2, num.intValue());
                }

                public final Pair<Integer, Integer> invoke(CharSequence charSequence2, int i3) {
                    kotlin.jvm.internal.i.c(charSequence2, "$receiver");
                    int iN = StringsKt__StringsKt.N(charSequence2, cArr, i3, z);
                    if (iN < 0) {
                        return null;
                    }
                    return kotlin.h.a(Integer.valueOf(iN), 1);
                }
            });
        }
        throw new IllegalArgumentException(("Limit must be non-negative, but was " + i2 + '.').toString());
    }

    private static final kotlin.p.d<kotlin.o.c> W(CharSequence charSequence, String[] strArr, int i, final boolean z, int i2) {
        if (i2 >= 0) {
            final List listB = kotlin.collections.f.b(strArr);
            return new e(charSequence, i, i2, new kotlin.jvm.b.p<CharSequence, Integer, Pair<? extends Integer, ? extends Integer>>() { // from class: kotlin.text.StringsKt__StringsKt$rangesDelimitedBy$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.b.p
                public /* bridge */ /* synthetic */ Pair<? extends Integer, ? extends Integer> invoke(CharSequence charSequence2, Integer num) {
                    return invoke(charSequence2, num.intValue());
                }

                public final Pair<Integer, Integer> invoke(CharSequence charSequence2, int i3) {
                    kotlin.jvm.internal.i.c(charSequence2, "$receiver");
                    Pair pairE = StringsKt__StringsKt.E(charSequence2, listB, i3, z, false);
                    if (pairE != null) {
                        return kotlin.h.a(pairE.getFirst(), Integer.valueOf(((String) pairE.getSecond()).length()));
                    }
                    return null;
                }
            });
        }
        throw new IllegalArgumentException(("Limit must be non-negative, but was " + i2 + '.').toString());
    }

    static /* synthetic */ kotlin.p.d X(CharSequence charSequence, char[] cArr, int i, boolean z, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        if ((i3 & 8) != 0) {
            i2 = 0;
        }
        return V(charSequence, cArr, i, z, i2);
    }

    static /* synthetic */ kotlin.p.d Y(CharSequence charSequence, String[] strArr, int i, boolean z, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        if ((i3 & 8) != 0) {
            i2 = 0;
        }
        return W(charSequence, strArr, i, z, i2);
    }

    public static final boolean Z(CharSequence charSequence, int i, CharSequence charSequence2, int i2, int i3, boolean z) {
        kotlin.jvm.internal.i.c(charSequence, "$this$regionMatchesImpl");
        kotlin.jvm.internal.i.c(charSequence2, "other");
        if (i2 < 0 || i < 0 || i > charSequence.length() - i3 || i2 > charSequence2.length() - i3) {
            return false;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            if (!c.d(charSequence.charAt(i + i4), charSequence2.charAt(i2 + i4), z)) {
                return false;
            }
        }
        return true;
    }

    public static String a0(String str, CharSequence charSequence) {
        kotlin.jvm.internal.i.c(str, "$this$removePrefix");
        kotlin.jvm.internal.i.c(charSequence, "prefix");
        if (!m0(str, charSequence, false, 2, null)) {
            return str;
        }
        String strSubstring = str.substring(charSequence.length());
        kotlin.jvm.internal.i.b(strSubstring, "(this as java.lang.String).substring(startIndex)");
        return strSubstring;
    }

    public static String b0(String str, CharSequence charSequence) {
        kotlin.jvm.internal.i.c(str, "$this$removeSuffix");
        kotlin.jvm.internal.i.c(charSequence, "suffix");
        if (!D(str, charSequence, false, 2, null)) {
            return str;
        }
        String strSubstring = str.substring(0, str.length() - charSequence.length());
        kotlin.jvm.internal.i.b(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public static String c0(String str, CharSequence charSequence) {
        kotlin.jvm.internal.i.c(str, "$this$removeSurrounding");
        kotlin.jvm.internal.i.c(charSequence, "delimiter");
        return d0(str, charSequence, charSequence);
    }

    public static final String d0(String str, CharSequence charSequence, CharSequence charSequence2) {
        kotlin.jvm.internal.i.c(str, "$this$removeSurrounding");
        kotlin.jvm.internal.i.c(charSequence, "prefix");
        kotlin.jvm.internal.i.c(charSequence2, "suffix");
        if (str.length() < charSequence.length() + charSequence2.length() || !m0(str, charSequence, false, 2, null) || !D(str, charSequence2, false, 2, null)) {
            return str;
        }
        String strSubstring = str.substring(charSequence.length(), str.length() - charSequence2.length());
        kotlin.jvm.internal.i.b(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public static final List<String> e0(CharSequence charSequence, char[] cArr, boolean z, int i) {
        kotlin.jvm.internal.i.c(charSequence, "$this$split");
        kotlin.jvm.internal.i.c(cArr, "delimiters");
        if (cArr.length == 1) {
            return f0(charSequence, String.valueOf(cArr[0]), z, i);
        }
        Iterable iterableB = kotlin.p.j.b(X(charSequence, cArr, 0, z, i, 2, null));
        ArrayList arrayList = new ArrayList(kotlin.collections.l.p(iterableB, 10));
        Iterator it = iterableB.iterator();
        while (it.hasNext()) {
            arrayList.add(n0(charSequence, (kotlin.o.c) it.next()));
        }
        return arrayList;
    }

    private static final List<String> f0(CharSequence charSequence, String str, boolean z, int i) {
        int length = 0;
        if (!(i >= 0)) {
            throw new IllegalArgumentException(("Limit must be non-negative, but was " + i + '.').toString());
        }
        int I = I(charSequence, str, 0, z);
        if (I == -1 || i == 1) {
            return kotlin.collections.j.b(charSequence.toString());
        }
        boolean z2 = i > 0;
        ArrayList arrayList = new ArrayList(z2 ? kotlin.o.f.c(i, 10) : 10);
        do {
            arrayList.add(charSequence.subSequence(length, I).toString());
            length = str.length() + I;
            if (z2 && arrayList.size() == i - 1) {
                break;
            }
            I = I(charSequence, str, length, z);
        } while (I != -1);
        arrayList.add(charSequence.subSequence(length, charSequence.length()).toString());
        return arrayList;
    }

    public static /* synthetic */ List g0(CharSequence charSequence, char[] cArr, boolean z, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return e0(charSequence, cArr, z, i);
    }

    public static final kotlin.p.d<String> h0(final CharSequence charSequence, char[] cArr, boolean z, int i) {
        kotlin.jvm.internal.i.c(charSequence, "$this$splitToSequence");
        kotlin.jvm.internal.i.c(cArr, "delimiters");
        return kotlin.p.j.g(X(charSequence, cArr, 0, z, i, 2, null), new kotlin.jvm.b.l<kotlin.o.c, String>() { // from class: kotlin.text.StringsKt__StringsKt$splitToSequence$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.b.l
            public final String invoke(kotlin.o.c cVar) {
                kotlin.jvm.internal.i.c(cVar, "it");
                return StringsKt__StringsKt.n0(charSequence, cVar);
            }
        });
    }

    public static final kotlin.p.d<String> i0(final CharSequence charSequence, String[] strArr, boolean z, int i) {
        kotlin.jvm.internal.i.c(charSequence, "$this$splitToSequence");
        kotlin.jvm.internal.i.c(strArr, "delimiters");
        return kotlin.p.j.g(Y(charSequence, strArr, 0, z, i, 2, null), new kotlin.jvm.b.l<kotlin.o.c, String>() { // from class: kotlin.text.StringsKt__StringsKt$splitToSequence$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.b.l
            public final String invoke(kotlin.o.c cVar) {
                kotlin.jvm.internal.i.c(cVar, "it");
                return StringsKt__StringsKt.n0(charSequence, cVar);
            }
        });
    }

    public static /* synthetic */ kotlin.p.d j0(CharSequence charSequence, char[] cArr, boolean z, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return h0(charSequence, cArr, z, i);
    }

    public static /* synthetic */ kotlin.p.d k0(CharSequence charSequence, String[] strArr, boolean z, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return i0(charSequence, strArr, z, i);
    }

    public static final boolean l0(CharSequence charSequence, CharSequence charSequence2, boolean z) {
        kotlin.jvm.internal.i.c(charSequence, "$this$startsWith");
        kotlin.jvm.internal.i.c(charSequence2, "prefix");
        return (!z && (charSequence instanceof String) && (charSequence2 instanceof String)) ? s.w((String) charSequence, (String) charSequence2, false, 2, null) : Z(charSequence, 0, charSequence2, 0, charSequence2.length(), z);
    }

    public static /* synthetic */ boolean m0(CharSequence charSequence, CharSequence charSequence2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return l0(charSequence, charSequence2, z);
    }

    public static final String n0(CharSequence charSequence, kotlin.o.c cVar) {
        kotlin.jvm.internal.i.c(charSequence, "$this$substring");
        kotlin.jvm.internal.i.c(cVar, "range");
        return charSequence.subSequence(cVar.g().intValue(), cVar.f().intValue() + 1).toString();
    }

    public static final String o0(String str, char c2, String str2) {
        kotlin.jvm.internal.i.c(str, "$this$substringAfterLast");
        kotlin.jvm.internal.i.c(str2, "missingDelimiterValue");
        int iQ = Q(str, c2, 0, false, 6, null);
        if (iQ == -1) {
            return str2;
        }
        String strSubstring = str.substring(iQ + 1, str.length());
        kotlin.jvm.internal.i.b(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public static /* synthetic */ String p0(String str, char c2, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = str;
        }
        return o0(str, c2, str2);
    }

    public static CharSequence q0(CharSequence charSequence) {
        kotlin.jvm.internal.i.c(charSequence, "$this$trim");
        int length = charSequence.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean zC = b.c(charSequence.charAt(!z ? i : length));
            if (z) {
                if (!zC) {
                    break;
                }
                length--;
            } else if (zC) {
                i++;
            } else {
                z = true;
            }
        }
        return charSequence.subSequence(i, length + 1);
    }

    public static final boolean y(CharSequence charSequence, char c2, boolean z) {
        kotlin.jvm.internal.i.c(charSequence, "$this$contains");
        return L(charSequence, c2, 0, z, 2, null) >= 0;
    }

    public static final boolean z(CharSequence charSequence, CharSequence charSequence2, boolean z) {
        kotlin.jvm.internal.i.c(charSequence, "$this$contains");
        kotlin.jvm.internal.i.c(charSequence2, "other");
        if (charSequence2 instanceof String) {
            if (M(charSequence, (String) charSequence2, 0, z, 2, null) >= 0) {
                return true;
            }
        } else if (K(charSequence, charSequence2, 0, charSequence.length(), z, false, 16, null) >= 0) {
            return true;
        }
        return false;
    }
}
