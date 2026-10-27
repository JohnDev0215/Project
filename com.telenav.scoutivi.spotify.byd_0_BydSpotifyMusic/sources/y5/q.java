package y5;

import h5.x;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: Strings.kt */
/* JADX INFO: loaded from: classes.dex */
public class q extends p {

    /* JADX INFO: compiled from: Strings.kt */
    static final class a extends kotlin.jvm.internal.m implements r5.p<CharSequence, Integer, g5.k<? extends Integer, ? extends Integer>> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ char[] f10015e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ boolean f10016g;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(char[] cArr, boolean z6) {
            super(2);
            this.f10015e = cArr;
            this.f10016g = z6;
        }

        public final g5.k<Integer, Integer> a(CharSequence $receiver, int i7) {
            kotlin.jvm.internal.l.e($receiver, "$this$$receiver");
            int iR = q.R($receiver, this.f10015e, i7, this.f10016g);
            if (iR < 0) {
                return null;
            }
            return g5.o.a(Integer.valueOf(iR), 1);
        }

        @Override // r5.p
        public /* bridge */ /* synthetic */ g5.k<? extends Integer, ? extends Integer> invoke(CharSequence charSequence, Integer num) {
            return a(charSequence, num.intValue());
        }
    }

    /* JADX INFO: compiled from: Strings.kt */
    static final class b extends kotlin.jvm.internal.m implements r5.p<CharSequence, Integer, g5.k<? extends Integer, ? extends Integer>> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ List<String> f10017e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ boolean f10018g;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(List<String> list, boolean z6) {
            super(2);
            this.f10017e = list;
            this.f10018g = z6;
        }

        public final g5.k<Integer, Integer> a(CharSequence $receiver, int i7) {
            kotlin.jvm.internal.l.e($receiver, "$this$$receiver");
            g5.k kVarI = q.I($receiver, this.f10017e, i7, this.f10018g, false);
            if (kVarI != null) {
                return g5.o.a(kVarI.c(), Integer.valueOf(((String) kVarI.d()).length()));
            }
            return null;
        }

        @Override // r5.p
        public /* bridge */ /* synthetic */ g5.k<? extends Integer, ? extends Integer> invoke(CharSequence charSequence, Integer num) {
            return a(charSequence, num.intValue());
        }
    }

    /* JADX INFO: compiled from: Strings.kt */
    static final class c extends kotlin.jvm.internal.m implements r5.l<v5.c, String> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ CharSequence f10019e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(CharSequence charSequence) {
            super(1);
            this.f10019e = charSequence;
        }

        @Override // r5.l
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(v5.c it) {
            kotlin.jvm.internal.l.e(it, "it");
            return q.q0(this.f10019e, it);
        }
    }

    public static final boolean C(CharSequence charSequence, char c7, boolean z6) {
        kotlin.jvm.internal.l.e(charSequence, "<this>");
        return P(charSequence, c7, 0, z6, 2, null) >= 0;
    }

    public static final boolean D(CharSequence charSequence, CharSequence other, boolean z6) {
        kotlin.jvm.internal.l.e(charSequence, "<this>");
        kotlin.jvm.internal.l.e(other, "other");
        if (other instanceof String) {
            if (Q(charSequence, (String) other, 0, z6, 2, null) >= 0) {
                return true;
            }
        } else if (O(charSequence, other, 0, charSequence.length(), z6, false, 16, null) >= 0) {
            return true;
        }
        return false;
    }

    public static /* synthetic */ boolean E(CharSequence charSequence, char c7, boolean z6, int i7, Object obj) {
        if ((i7 & 2) != 0) {
            z6 = false;
        }
        return C(charSequence, c7, z6);
    }

    public static /* synthetic */ boolean F(CharSequence charSequence, CharSequence charSequence2, boolean z6, int i7, Object obj) {
        if ((i7 & 2) != 0) {
            z6 = false;
        }
        return D(charSequence, charSequence2, z6);
    }

    public static final boolean G(CharSequence charSequence, CharSequence suffix, boolean z6) {
        kotlin.jvm.internal.l.e(charSequence, "<this>");
        kotlin.jvm.internal.l.e(suffix, "suffix");
        return (!z6 && (charSequence instanceof String) && (suffix instanceof String)) ? p.n((String) charSequence, (String) suffix, false, 2, null) : f0(charSequence, charSequence.length() - suffix.length(), suffix, 0, suffix.length(), z6);
    }

    public static /* synthetic */ boolean H(CharSequence charSequence, CharSequence charSequence2, boolean z6, int i7, Object obj) {
        if ((i7 & 2) != 0) {
            z6 = false;
        }
        return G(charSequence, charSequence2, z6);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final g5.k<Integer, String> I(CharSequence charSequence, Collection<String> collection, int i7, boolean z6, boolean z7) {
        Object next;
        Object next2;
        if (!z6 && collection.size() == 1) {
            String str = (String) x.F(collection);
            int iQ = !z7 ? Q(charSequence, str, i7, false, 4, null) : V(charSequence, str, i7, false, 4, null);
            if (iQ < 0) {
                return null;
            }
            return g5.o.a(Integer.valueOf(iQ), str);
        }
        v5.a cVar = !z7 ? new v5.c(v5.h.b(i7, 0), charSequence.length()) : v5.h.g(v5.h.d(i7, K(charSequence)), 0);
        if (charSequence instanceof String) {
            int iC = cVar.c();
            int iD = cVar.d();
            int iE = cVar.e();
            if ((iE > 0 && iC <= iD) || (iE < 0 && iD <= iC)) {
                while (true) {
                    Iterator<T> it = collection.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            next2 = null;
                            break;
                        }
                        next2 = it.next();
                        String str2 = (String) next2;
                        if (p.q(str2, 0, (String) charSequence, iC, str2.length(), z6)) {
                            break;
                        }
                    }
                    String str3 = (String) next2;
                    if (str3 == null) {
                        if (iC == iD) {
                            break;
                        }
                        iC += iE;
                    } else {
                        return g5.o.a(Integer.valueOf(iC), str3);
                    }
                }
            }
        } else {
            int iC2 = cVar.c();
            int iD2 = cVar.d();
            int iE2 = cVar.e();
            if ((iE2 > 0 && iC2 <= iD2) || (iE2 < 0 && iD2 <= iC2)) {
                while (true) {
                    Iterator<T> it2 = collection.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            next = null;
                            break;
                        }
                        next = it2.next();
                        String str4 = (String) next;
                        if (f0(str4, 0, charSequence, iC2, str4.length(), z6)) {
                            break;
                        }
                    }
                    String str5 = (String) next;
                    if (str5 == null) {
                        if (iC2 == iD2) {
                            break;
                        }
                        iC2 += iE2;
                    } else {
                        return g5.o.a(Integer.valueOf(iC2), str5);
                    }
                }
            }
        }
        return null;
    }

    public static final v5.c J(CharSequence charSequence) {
        kotlin.jvm.internal.l.e(charSequence, "<this>");
        return new v5.c(0, charSequence.length() - 1);
    }

    public static final int K(CharSequence charSequence) {
        kotlin.jvm.internal.l.e(charSequence, "<this>");
        return charSequence.length() - 1;
    }

    public static final int L(CharSequence charSequence, char c7, int i7, boolean z6) {
        kotlin.jvm.internal.l.e(charSequence, "<this>");
        return (z6 || !(charSequence instanceof String)) ? R(charSequence, new char[]{c7}, i7, z6) : ((String) charSequence).indexOf(c7, i7);
    }

    public static final int M(CharSequence charSequence, String string, int i7, boolean z6) {
        kotlin.jvm.internal.l.e(charSequence, "<this>");
        kotlin.jvm.internal.l.e(string, "string");
        return (z6 || !(charSequence instanceof String)) ? O(charSequence, string, i7, charSequence.length(), z6, false, 16, null) : ((String) charSequence).indexOf(string, i7);
    }

    private static final int N(CharSequence charSequence, CharSequence charSequence2, int i7, int i8, boolean z6, boolean z7) {
        v5.a cVar = !z7 ? new v5.c(v5.h.b(i7, 0), v5.h.d(i8, charSequence.length())) : v5.h.g(v5.h.d(i7, K(charSequence)), v5.h.b(i8, 0));
        if ((charSequence instanceof String) && (charSequence2 instanceof String)) {
            int iC = cVar.c();
            int iD = cVar.d();
            int iE = cVar.e();
            if ((iE <= 0 || iC > iD) && (iE >= 0 || iD > iC)) {
                return -1;
            }
            while (!p.q((String) charSequence2, 0, (String) charSequence, iC, charSequence2.length(), z6)) {
                if (iC == iD) {
                    return -1;
                }
                iC += iE;
            }
            return iC;
        }
        int iC2 = cVar.c();
        int iD2 = cVar.d();
        int iE2 = cVar.e();
        if ((iE2 <= 0 || iC2 > iD2) && (iE2 >= 0 || iD2 > iC2)) {
            return -1;
        }
        while (!f0(charSequence2, 0, charSequence, iC2, charSequence2.length(), z6)) {
            if (iC2 == iD2) {
                return -1;
            }
            iC2 += iE2;
        }
        return iC2;
    }

    static /* synthetic */ int O(CharSequence charSequence, CharSequence charSequence2, int i7, int i8, boolean z6, boolean z7, int i9, Object obj) {
        if ((i9 & 16) != 0) {
            z7 = false;
        }
        return N(charSequence, charSequence2, i7, i8, z6, z7);
    }

    public static /* synthetic */ int P(CharSequence charSequence, char c7, int i7, boolean z6, int i8, Object obj) {
        if ((i8 & 2) != 0) {
            i7 = 0;
        }
        if ((i8 & 4) != 0) {
            z6 = false;
        }
        return L(charSequence, c7, i7, z6);
    }

    public static /* synthetic */ int Q(CharSequence charSequence, String str, int i7, boolean z6, int i8, Object obj) {
        if ((i8 & 2) != 0) {
            i7 = 0;
        }
        if ((i8 & 4) != 0) {
            z6 = false;
        }
        return M(charSequence, str, i7, z6);
    }

    public static final int R(CharSequence charSequence, char[] chars, int i7, boolean z6) {
        boolean z7;
        kotlin.jvm.internal.l.e(charSequence, "<this>");
        kotlin.jvm.internal.l.e(chars, "chars");
        if (!z6 && chars.length == 1 && (charSequence instanceof String)) {
            return ((String) charSequence).indexOf(h5.l.t(chars), i7);
        }
        int iB = v5.h.b(i7, 0);
        int iK = K(charSequence);
        if (iB > iK) {
            return -1;
        }
        while (true) {
            char cCharAt = charSequence.charAt(iB);
            int length = chars.length;
            int i8 = 0;
            while (true) {
                if (i8 >= length) {
                    z7 = false;
                    break;
                }
                if (y5.c.d(chars[i8], cCharAt, z6)) {
                    z7 = true;
                    break;
                }
                i8++;
            }
            if (z7) {
                return iB;
            }
            if (iB == iK) {
                return -1;
            }
            iB++;
        }
    }

    public static final int S(CharSequence charSequence, char c7, int i7, boolean z6) {
        kotlin.jvm.internal.l.e(charSequence, "<this>");
        return (z6 || !(charSequence instanceof String)) ? W(charSequence, new char[]{c7}, i7, z6) : ((String) charSequence).lastIndexOf(c7, i7);
    }

    public static final int T(CharSequence charSequence, String string, int i7, boolean z6) {
        kotlin.jvm.internal.l.e(charSequence, "<this>");
        kotlin.jvm.internal.l.e(string, "string");
        return (z6 || !(charSequence instanceof String)) ? N(charSequence, string, i7, 0, z6, true) : ((String) charSequence).lastIndexOf(string, i7);
    }

    public static /* synthetic */ int U(CharSequence charSequence, char c7, int i7, boolean z6, int i8, Object obj) {
        if ((i8 & 2) != 0) {
            i7 = K(charSequence);
        }
        if ((i8 & 4) != 0) {
            z6 = false;
        }
        return S(charSequence, c7, i7, z6);
    }

    public static /* synthetic */ int V(CharSequence charSequence, String str, int i7, boolean z6, int i8, Object obj) {
        if ((i8 & 2) != 0) {
            i7 = K(charSequence);
        }
        if ((i8 & 4) != 0) {
            z6 = false;
        }
        return T(charSequence, str, i7, z6);
    }

    public static final int W(CharSequence charSequence, char[] chars, int i7, boolean z6) {
        kotlin.jvm.internal.l.e(charSequence, "<this>");
        kotlin.jvm.internal.l.e(chars, "chars");
        if (!z6 && chars.length == 1 && (charSequence instanceof String)) {
            return ((String) charSequence).lastIndexOf(h5.l.t(chars), i7);
        }
        for (int iD = v5.h.d(i7, K(charSequence)); -1 < iD; iD--) {
            char cCharAt = charSequence.charAt(iD);
            int length = chars.length;
            boolean z7 = false;
            int i8 = 0;
            while (true) {
                if (i8 >= length) {
                    break;
                }
                if (y5.c.d(chars[i8], cCharAt, z6)) {
                    z7 = true;
                    break;
                }
                i8++;
            }
            if (z7) {
                return iD;
            }
        }
        return -1;
    }

    public static final x5.d<String> X(CharSequence charSequence) {
        kotlin.jvm.internal.l.e(charSequence, "<this>");
        return n0(charSequence, new String[]{"\r\n", "\n", "\r"}, false, 0, 6, null);
    }

    public static final List<String> Y(CharSequence charSequence) {
        kotlin.jvm.internal.l.e(charSequence, "<this>");
        return x5.l.l(X(charSequence));
    }

    public static final CharSequence Z(CharSequence charSequence, int i7, char c7) {
        kotlin.jvm.internal.l.e(charSequence, "<this>");
        if (i7 < 0) {
            throw new IllegalArgumentException("Desired length " + i7 + " is less than zero.");
        }
        if (i7 <= charSequence.length()) {
            return charSequence.subSequence(0, charSequence.length());
        }
        StringBuilder sb = new StringBuilder(i7);
        int length = i7 - charSequence.length();
        int i8 = 1;
        if (1 <= length) {
            while (true) {
                sb.append(c7);
                if (i8 == length) {
                    break;
                }
                i8++;
            }
        }
        sb.append(charSequence);
        return sb;
    }

    public static String a0(String str, int i7, char c7) {
        kotlin.jvm.internal.l.e(str, "<this>");
        return Z(str, i7, c7).toString();
    }

    private static final x5.d<v5.c> b0(CharSequence charSequence, char[] cArr, int i7, boolean z6, int i8) {
        i0(i8);
        return new e(charSequence, i7, i8, new a(cArr, z6));
    }

    private static final x5.d<v5.c> c0(CharSequence charSequence, String[] strArr, int i7, boolean z6, int i8) {
        i0(i8);
        return new e(charSequence, i7, i8, new b(h5.k.b(strArr), z6));
    }

    static /* synthetic */ x5.d d0(CharSequence charSequence, char[] cArr, int i7, boolean z6, int i8, int i9, Object obj) {
        if ((i9 & 2) != 0) {
            i7 = 0;
        }
        if ((i9 & 4) != 0) {
            z6 = false;
        }
        if ((i9 & 8) != 0) {
            i8 = 0;
        }
        return b0(charSequence, cArr, i7, z6, i8);
    }

    static /* synthetic */ x5.d e0(CharSequence charSequence, String[] strArr, int i7, boolean z6, int i8, int i9, Object obj) {
        if ((i9 & 2) != 0) {
            i7 = 0;
        }
        if ((i9 & 4) != 0) {
            z6 = false;
        }
        if ((i9 & 8) != 0) {
            i8 = 0;
        }
        return c0(charSequence, strArr, i7, z6, i8);
    }

    public static final boolean f0(CharSequence charSequence, int i7, CharSequence other, int i8, int i9, boolean z6) {
        kotlin.jvm.internal.l.e(charSequence, "<this>");
        kotlin.jvm.internal.l.e(other, "other");
        if (i8 < 0 || i7 < 0 || i7 > charSequence.length() - i9 || i8 > other.length() - i9) {
            return false;
        }
        for (int i10 = 0; i10 < i9; i10++) {
            if (!y5.c.d(charSequence.charAt(i7 + i10), other.charAt(i8 + i10), z6)) {
                return false;
            }
        }
        return true;
    }

    public static String g0(String str, CharSequence prefix) {
        kotlin.jvm.internal.l.e(str, "<this>");
        kotlin.jvm.internal.l.e(prefix, "prefix");
        if (!p0(str, prefix, false, 2, null)) {
            return str;
        }
        String strSubstring = str.substring(prefix.length());
        kotlin.jvm.internal.l.d(strSubstring, "this as java.lang.String).substring(startIndex)");
        return strSubstring;
    }

    public static String h0(String str, CharSequence suffix) {
        kotlin.jvm.internal.l.e(str, "<this>");
        kotlin.jvm.internal.l.e(suffix, "suffix");
        if (!H(str, suffix, false, 2, null)) {
            return str;
        }
        String strSubstring = str.substring(0, str.length() - suffix.length());
        kotlin.jvm.internal.l.d(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public static final void i0(int i7) {
        if (i7 >= 0) {
            return;
        }
        throw new IllegalArgumentException(("Limit must be non-negative, but was " + i7).toString());
    }

    public static final List<String> j0(CharSequence charSequence, char[] delimiters, boolean z6, int i7) {
        kotlin.jvm.internal.l.e(charSequence, "<this>");
        kotlin.jvm.internal.l.e(delimiters, "delimiters");
        if (delimiters.length == 1) {
            return k0(charSequence, String.valueOf(delimiters[0]), z6, i7);
        }
        Iterable iterableE = x5.l.e(d0(charSequence, delimiters, 0, z6, i7, 2, null));
        ArrayList arrayList = new ArrayList(h5.q.p(iterableE, 10));
        Iterator it = iterableE.iterator();
        while (it.hasNext()) {
            arrayList.add(q0(charSequence, (v5.c) it.next()));
        }
        return arrayList;
    }

    private static final List<String> k0(CharSequence charSequence, String str, boolean z6, int i7) {
        i0(i7);
        int length = 0;
        int iM = M(charSequence, str, 0, z6);
        if (iM == -1 || i7 == 1) {
            return h5.o.d(charSequence.toString());
        }
        boolean z7 = i7 > 0;
        ArrayList arrayList = new ArrayList(z7 ? v5.h.d(i7, 10) : 10);
        do {
            arrayList.add(charSequence.subSequence(length, iM).toString());
            length = str.length() + iM;
            if (z7 && arrayList.size() == i7 - 1) {
                break;
            }
            iM = M(charSequence, str, length, z6);
        } while (iM != -1);
        arrayList.add(charSequence.subSequence(length, charSequence.length()).toString());
        return arrayList;
    }

    public static /* synthetic */ List l0(CharSequence charSequence, char[] cArr, boolean z6, int i7, int i8, Object obj) {
        if ((i8 & 2) != 0) {
            z6 = false;
        }
        if ((i8 & 4) != 0) {
            i7 = 0;
        }
        return j0(charSequence, cArr, z6, i7);
    }

    public static final x5.d<String> m0(CharSequence charSequence, String[] delimiters, boolean z6, int i7) {
        kotlin.jvm.internal.l.e(charSequence, "<this>");
        kotlin.jvm.internal.l.e(delimiters, "delimiters");
        return x5.l.j(e0(charSequence, delimiters, 0, z6, i7, 2, null), new c(charSequence));
    }

    public static /* synthetic */ x5.d n0(CharSequence charSequence, String[] strArr, boolean z6, int i7, int i8, Object obj) {
        if ((i8 & 2) != 0) {
            z6 = false;
        }
        if ((i8 & 4) != 0) {
            i7 = 0;
        }
        return m0(charSequence, strArr, z6, i7);
    }

    public static final boolean o0(CharSequence charSequence, CharSequence prefix, boolean z6) {
        kotlin.jvm.internal.l.e(charSequence, "<this>");
        kotlin.jvm.internal.l.e(prefix, "prefix");
        return (!z6 && (charSequence instanceof String) && (prefix instanceof String)) ? p.A((String) charSequence, (String) prefix, false, 2, null) : f0(charSequence, 0, prefix, 0, prefix.length(), z6);
    }

    public static /* synthetic */ boolean p0(CharSequence charSequence, CharSequence charSequence2, boolean z6, int i7, Object obj) {
        if ((i7 & 2) != 0) {
            z6 = false;
        }
        return o0(charSequence, charSequence2, z6);
    }

    public static final String q0(CharSequence charSequence, v5.c range) {
        kotlin.jvm.internal.l.e(charSequence, "<this>");
        kotlin.jvm.internal.l.e(range, "range");
        return charSequence.subSequence(range.i().intValue(), range.h().intValue() + 1).toString();
    }

    public static final String r0(String str, String delimiter, String missingDelimiterValue) {
        kotlin.jvm.internal.l.e(str, "<this>");
        kotlin.jvm.internal.l.e(delimiter, "delimiter");
        kotlin.jvm.internal.l.e(missingDelimiterValue, "missingDelimiterValue");
        int iQ = Q(str, delimiter, 0, false, 6, null);
        if (iQ == -1) {
            return missingDelimiterValue;
        }
        String strSubstring = str.substring(iQ + delimiter.length(), str.length());
        kotlin.jvm.internal.l.d(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public static /* synthetic */ String s0(String str, String str2, String str3, int i7, Object obj) {
        if ((i7 & 2) != 0) {
            str3 = str;
        }
        return r0(str, str2, str3);
    }

    public static final String t0(String str, char c7, String missingDelimiterValue) {
        kotlin.jvm.internal.l.e(str, "<this>");
        kotlin.jvm.internal.l.e(missingDelimiterValue, "missingDelimiterValue");
        int iU = U(str, c7, 0, false, 6, null);
        if (iU == -1) {
            return missingDelimiterValue;
        }
        String strSubstring = str.substring(iU + 1, str.length());
        kotlin.jvm.internal.l.d(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public static /* synthetic */ String u0(String str, char c7, String str2, int i7, Object obj) {
        if ((i7 & 2) != 0) {
            str2 = str;
        }
        return t0(str, c7, str2);
    }

    public static final String v0(String str, char c7, String missingDelimiterValue) {
        kotlin.jvm.internal.l.e(str, "<this>");
        kotlin.jvm.internal.l.e(missingDelimiterValue, "missingDelimiterValue");
        int iP = P(str, c7, 0, false, 6, null);
        if (iP == -1) {
            return missingDelimiterValue;
        }
        String strSubstring = str.substring(0, iP);
        kotlin.jvm.internal.l.d(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public static final String w0(String str, String delimiter, String missingDelimiterValue) {
        kotlin.jvm.internal.l.e(str, "<this>");
        kotlin.jvm.internal.l.e(delimiter, "delimiter");
        kotlin.jvm.internal.l.e(missingDelimiterValue, "missingDelimiterValue");
        int iQ = Q(str, delimiter, 0, false, 6, null);
        if (iQ == -1) {
            return missingDelimiterValue;
        }
        String strSubstring = str.substring(0, iQ);
        kotlin.jvm.internal.l.d(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public static /* synthetic */ String x0(String str, char c7, String str2, int i7, Object obj) {
        if ((i7 & 2) != 0) {
            str2 = str;
        }
        return v0(str, c7, str2);
    }

    public static /* synthetic */ String y0(String str, String str2, String str3, int i7, Object obj) {
        if ((i7 & 2) != 0) {
            str3 = str;
        }
        return w0(str, str2, str3);
    }

    public static CharSequence z0(CharSequence charSequence) {
        kotlin.jvm.internal.l.e(charSequence, "<this>");
        int length = charSequence.length() - 1;
        int i7 = 0;
        boolean z6 = false;
        while (i7 <= length) {
            boolean zC = y5.b.c(charSequence.charAt(!z6 ? i7 : length));
            if (z6) {
                if (!zC) {
                    break;
                }
                length--;
            } else if (zC) {
                i7++;
            } else {
                z6 = true;
            }
        }
        return charSequence.subSequence(i7, length + 1);
    }
}
