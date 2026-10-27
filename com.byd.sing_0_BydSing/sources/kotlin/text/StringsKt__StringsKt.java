package kotlin.text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: Strings.kt */
/* JADX INFO: loaded from: classes.dex */
public class StringsKt__StringsKt extends o {
    public static /* synthetic */ int A(CharSequence charSequence, String str, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = s(charSequence);
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return y(charSequence, str, i, z);
    }

    public static final int B(CharSequence charSequence, char[] cArr, int i, boolean z) {
        kotlin.jvm.internal.h.d(charSequence, "$this$lastIndexOfAny");
        kotlin.jvm.internal.h.d(cArr, "chars");
        if (!z && cArr.length == 1 && (charSequence instanceof String)) {
            return ((String) charSequence).lastIndexOf(kotlin.collections.f.g(cArr), i);
        }
        for (int iB = kotlin.q.n.b(i, s(charSequence)); iB >= 0; iB--) {
            char cCharAt = charSequence.charAt(iB);
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
                return iB;
            }
        }
        return -1;
    }

    private static final kotlin.r.a<kotlin.q.h> C(CharSequence charSequence, String[] strArr, int i, final boolean z, int i2) {
        G(i2);
        final List listA = kotlin.collections.e.a(strArr);
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
                kotlin.jvm.internal.h.d(charSequence2, "$receiver");
                Pair pairR = StringsKt__StringsKt.r(charSequence2, listA, i3, z, false);
                if (pairR != null) {
                    return kotlin.h.a(pairR.getFirst(), Integer.valueOf(((String) pairR.getSecond()).length()));
                }
                return null;
            }
        });
    }

    static /* synthetic */ kotlin.r.a D(CharSequence charSequence, String[] strArr, int i, boolean z, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        if ((i3 & 8) != 0) {
            i2 = 0;
        }
        return C(charSequence, strArr, i, z, i2);
    }

    public static final boolean E(CharSequence charSequence, int i, CharSequence charSequence2, int i2, int i3, boolean z) {
        kotlin.jvm.internal.h.d(charSequence, "$this$regionMatchesImpl");
        kotlin.jvm.internal.h.d(charSequence2, "other");
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

    public static String F(String str, CharSequence charSequence, CharSequence charSequence2) {
        kotlin.jvm.internal.h.d(str, "$this$removeSurrounding");
        kotlin.jvm.internal.h.d(charSequence, "prefix");
        kotlin.jvm.internal.h.d(charSequence2, "suffix");
        if (str.length() < charSequence.length() + charSequence2.length() || !L(str, charSequence, false, 2, null) || !q(str, charSequence2, false, 2, null)) {
            return str;
        }
        String strSubstring = str.substring(charSequence.length(), str.length() - charSequence2.length());
        kotlin.jvm.internal.h.c(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public static final void G(int i) {
        if (i >= 0) {
            return;
        }
        throw new IllegalArgumentException(("Limit must be non-negative, but was " + i).toString());
    }

    public static final List<String> H(CharSequence charSequence, String[] strArr, boolean z, int i) {
        kotlin.jvm.internal.h.d(charSequence, "$this$split");
        kotlin.jvm.internal.h.d(strArr, "delimiters");
        if (strArr.length == 1) {
            String str = strArr[0];
            if (!(str.length() == 0)) {
                return I(charSequence, str, z, i);
            }
        }
        Iterable iterableA = kotlin.r.g.a(D(charSequence, strArr, 0, z, i, 2, null));
        ArrayList arrayList = new ArrayList(kotlin.collections.l.i(iterableA, 10));
        Iterator it = iterableA.iterator();
        while (it.hasNext()) {
            arrayList.add(M(charSequence, (kotlin.q.h) it.next()));
        }
        return arrayList;
    }

    private static final List<String> I(CharSequence charSequence, String str, boolean z, int i) {
        G(i);
        int length = 0;
        int iT = t(charSequence, str, 0, z);
        if (iT == -1 || i == 1) {
            return kotlin.collections.j.b(charSequence.toString());
        }
        boolean z2 = i > 0;
        ArrayList arrayList = new ArrayList(z2 ? kotlin.q.n.b(i, 10) : 10);
        do {
            arrayList.add(charSequence.subSequence(length, iT).toString());
            length = str.length() + iT;
            if (z2 && arrayList.size() == i - 1) {
                break;
            }
            iT = t(charSequence, str, length, z);
        } while (iT != -1);
        arrayList.add(charSequence.subSequence(length, charSequence.length()).toString());
        return arrayList;
    }

    public static /* synthetic */ List J(CharSequence charSequence, String[] strArr, boolean z, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return H(charSequence, strArr, z, i);
    }

    public static final boolean K(CharSequence charSequence, CharSequence charSequence2, boolean z) {
        kotlin.jvm.internal.h.d(charSequence, "$this$startsWith");
        kotlin.jvm.internal.h.d(charSequence2, "prefix");
        return (!z && (charSequence instanceof String) && (charSequence2 instanceof String)) ? o.l((String) charSequence, (String) charSequence2, false, 2, null) : E(charSequence, 0, charSequence2, 0, charSequence2.length(), z);
    }

    public static /* synthetic */ boolean L(CharSequence charSequence, CharSequence charSequence2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return K(charSequence, charSequence2, z);
    }

    public static final String M(CharSequence charSequence, kotlin.q.h hVar) {
        kotlin.jvm.internal.h.d(charSequence, "$this$substring");
        kotlin.jvm.internal.h.d(hVar, "range");
        return charSequence.subSequence(hVar.g().intValue(), hVar.f().intValue() + 1).toString();
    }

    public static final String N(String str, char c2, String str2) {
        kotlin.jvm.internal.h.d(str, "$this$substringAfterLast");
        kotlin.jvm.internal.h.d(str2, "missingDelimiterValue");
        int iZ = z(str, c2, 0, false, 6, null);
        if (iZ == -1) {
            return str2;
        }
        String strSubstring = str.substring(iZ + 1, str.length());
        kotlin.jvm.internal.h.c(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public static /* synthetic */ String O(String str, char c2, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = str;
        }
        return N(str, c2, str2);
    }

    public static CharSequence P(CharSequence charSequence) {
        kotlin.jvm.internal.h.d(charSequence, "$this$trim");
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

    public static boolean n(CharSequence charSequence, CharSequence charSequence2, boolean z) {
        kotlin.jvm.internal.h.d(charSequence, "$this$contains");
        kotlin.jvm.internal.h.d(charSequence2, "other");
        if (charSequence2 instanceof String) {
            if (w(charSequence, (String) charSequence2, 0, z, 2, null) >= 0) {
                return true;
            }
        } else if (v(charSequence, charSequence2, 0, charSequence.length(), z, false, 16, null) >= 0) {
            return true;
        }
        return false;
    }

    public static /* synthetic */ boolean o(CharSequence charSequence, CharSequence charSequence2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return n(charSequence, charSequence2, z);
    }

    public static final boolean p(CharSequence charSequence, CharSequence charSequence2, boolean z) {
        kotlin.jvm.internal.h.d(charSequence, "$this$endsWith");
        kotlin.jvm.internal.h.d(charSequence2, "suffix");
        return (!z && (charSequence instanceof String) && (charSequence2 instanceof String)) ? o.f((String) charSequence, (String) charSequence2, false, 2, null) : E(charSequence, charSequence.length() - charSequence2.length(), charSequence2, 0, charSequence2.length(), z);
    }

    public static /* synthetic */ boolean q(CharSequence charSequence, CharSequence charSequence2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return p(charSequence, charSequence2, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Pair<Integer, String> r(CharSequence charSequence, Collection<String> collection, int i, boolean z, boolean z2) {
        Object next;
        Object next2;
        if (!z && collection.size() == 1) {
            String str = (String) kotlin.collections.i.k(collection);
            int iW = !z2 ? w(charSequence, str, i, false, 4, null) : A(charSequence, str, i, false, 4, null);
            if (iW < 0) {
                return null;
            }
            return kotlin.h.a(Integer.valueOf(iW), str);
        }
        kotlin.q.f hVar = !z2 ? new kotlin.q.h(kotlin.q.n.a(i, 0), charSequence.length()) : kotlin.q.n.d(kotlin.q.n.b(i, s(charSequence)), 0);
        if (charSequence instanceof String) {
            int iA = hVar.a();
            int iB = hVar.b();
            int iC = hVar.c();
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
                        if (o.h(str2, 0, (String) charSequence, iA, str2.length(), z)) {
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
            int iA2 = hVar.a();
            int iB2 = hVar.b();
            int iC2 = hVar.c();
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
                        if (E(str4, 0, charSequence, iA2, str4.length(), z)) {
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

    public static final int s(CharSequence charSequence) {
        kotlin.jvm.internal.h.d(charSequence, "$this$lastIndex");
        return charSequence.length() - 1;
    }

    public static final int t(CharSequence charSequence, String str, int i, boolean z) {
        kotlin.jvm.internal.h.d(charSequence, "$this$indexOf");
        kotlin.jvm.internal.h.d(str, "string");
        return (z || !(charSequence instanceof String)) ? v(charSequence, str, i, charSequence.length(), z, false, 16, null) : ((String) charSequence).indexOf(str, i);
    }

    private static final int u(CharSequence charSequence, CharSequence charSequence2, int i, int i2, boolean z, boolean z2) {
        kotlin.q.f hVar = !z2 ? new kotlin.q.h(kotlin.q.n.a(i, 0), kotlin.q.n.b(i2, charSequence.length())) : kotlin.q.n.d(kotlin.q.n.b(i, s(charSequence)), kotlin.q.n.a(i2, 0));
        if ((charSequence instanceof String) && (charSequence2 instanceof String)) {
            int iA = hVar.a();
            int iB = hVar.b();
            int iC = hVar.c();
            if (iC >= 0) {
                if (iA > iB) {
                    return -1;
                }
            } else if (iA < iB) {
                return -1;
            }
            while (!o.h((String) charSequence2, 0, (String) charSequence, iA, charSequence2.length(), z)) {
                if (iA == iB) {
                    return -1;
                }
                iA += iC;
            }
            return iA;
        }
        int iA2 = hVar.a();
        int iB2 = hVar.b();
        int iC2 = hVar.c();
        if (iC2 >= 0) {
            if (iA2 > iB2) {
                return -1;
            }
        } else if (iA2 < iB2) {
            return -1;
        }
        while (!E(charSequence2, 0, charSequence, iA2, charSequence2.length(), z)) {
            if (iA2 == iB2) {
                return -1;
            }
            iA2 += iC2;
        }
        return iA2;
    }

    static /* synthetic */ int v(CharSequence charSequence, CharSequence charSequence2, int i, int i2, boolean z, boolean z2, int i3, Object obj) {
        if ((i3 & 16) != 0) {
            z2 = false;
        }
        return u(charSequence, charSequence2, i, i2, z, z2);
    }

    public static /* synthetic */ int w(CharSequence charSequence, String str, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return t(charSequence, str, i, z);
    }

    public static final int x(CharSequence charSequence, char c2, int i, boolean z) {
        kotlin.jvm.internal.h.d(charSequence, "$this$lastIndexOf");
        return (z || !(charSequence instanceof String)) ? B(charSequence, new char[]{c2}, i, z) : ((String) charSequence).lastIndexOf(c2, i);
    }

    public static final int y(CharSequence charSequence, String str, int i, boolean z) {
        kotlin.jvm.internal.h.d(charSequence, "$this$lastIndexOf");
        kotlin.jvm.internal.h.d(str, "string");
        return (z || !(charSequence instanceof String)) ? u(charSequence, str, i, 0, z, true) : ((String) charSequence).lastIndexOf(str, i);
    }

    public static /* synthetic */ int z(CharSequence charSequence, char c2, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = s(charSequence);
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return x(charSequence, c2, i, z);
    }
}
