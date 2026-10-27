package y5;

import h5.x;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: Indent.kt */
/* JADX INFO: loaded from: classes.dex */
public class i extends h {

    /* JADX INFO: compiled from: Indent.kt */
    static final class a extends kotlin.jvm.internal.m implements r5.l<String, String> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final a f10013e = new a();

        a() {
            super(1);
        }

        @Override // r5.l
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(String line) {
            kotlin.jvm.internal.l.e(line, "line");
            return line;
        }
    }

    /* JADX INFO: compiled from: Indent.kt */
    static final class b extends kotlin.jvm.internal.m implements r5.l<String, String> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ String f10014e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(String str) {
            super(1);
            this.f10014e = str;
        }

        @Override // r5.l
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(String line) {
            kotlin.jvm.internal.l.e(line, "line");
            return this.f10014e + line;
        }
    }

    private static final r5.l<String, String> b(String str) {
        return str.length() == 0 ? a.f10013e : new b(str);
    }

    private static final int c(String str) {
        int length = str.length();
        int i7 = 0;
        while (true) {
            if (i7 >= length) {
                i7 = -1;
                break;
            }
            if (!y5.b.c(str.charAt(i7))) {
                break;
            }
            i7++;
        }
        return i7 == -1 ? str.length() : i7;
    }

    public static final String d(String str, String newIndent) {
        String strInvoke;
        kotlin.jvm.internal.l.e(str, "<this>");
        kotlin.jvm.internal.l.e(newIndent, "newIndent");
        List<String> listY = q.Y(str);
        ArrayList arrayList = new ArrayList();
        for (Object obj : listY) {
            if (!p.p((String) obj)) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(h5.q.p(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(Integer.valueOf(c((String) it.next())));
        }
        Integer num = (Integer) x.C(arrayList2);
        int i7 = 0;
        int iIntValue = num != null ? num.intValue() : 0;
        int length = str.length() + (newIndent.length() * listY.size());
        r5.l<String, String> lVarB = b(newIndent);
        int i8 = h5.p.i(listY);
        ArrayList arrayList3 = new ArrayList();
        for (Object obj2 : listY) {
            int i9 = i7 + 1;
            if (i7 < 0) {
                h5.p.o();
            }
            String str2 = (String) obj2;
            if ((i7 == 0 || i7 == i8) && p.p(str2)) {
                str2 = null;
            } else {
                String strA0 = s.A0(str2, iIntValue);
                if (strA0 != null && (strInvoke = lVarB.invoke(strA0)) != null) {
                    str2 = strInvoke;
                }
            }
            if (str2 != null) {
                arrayList3.add(str2);
            }
            i7 = i9;
        }
        String string = ((StringBuilder) x.x(arrayList3, new StringBuilder(length), (124 & 2) != 0 ? ", " : "\n", (124 & 4) != 0 ? "" : null, (124 & 8) == 0 ? null : "", (124 & 16) != 0 ? -1 : 0, (124 & 32) != 0 ? "..." : null, (124 & 64) != 0 ? null : null)).toString();
        kotlin.jvm.internal.l.d(string, "mapIndexedNotNull { inde…\"\\n\")\n        .toString()");
        return string;
    }

    public static final String e(String str, String newIndent, String marginPrefix) {
        int i7;
        String strInvoke;
        kotlin.jvm.internal.l.e(str, "<this>");
        kotlin.jvm.internal.l.e(newIndent, "newIndent");
        kotlin.jvm.internal.l.e(marginPrefix, "marginPrefix");
        if (!(!p.p(marginPrefix))) {
            throw new IllegalArgumentException("marginPrefix must be non-blank string.".toString());
        }
        List<String> listY = q.Y(str);
        int length = str.length() + (newIndent.length() * listY.size());
        r5.l<String, String> lVarB = b(newIndent);
        int i8 = h5.p.i(listY);
        ArrayList arrayList = new ArrayList();
        int i9 = 0;
        for (Object obj : listY) {
            int i10 = i9 + 1;
            if (i9 < 0) {
                h5.p.o();
            }
            String str2 = (String) obj;
            String strSubstring = null;
            if ((i9 == 0 || i9 == i8) && p.p(str2)) {
                str2 = null;
            } else {
                int length2 = str2.length();
                int i11 = 0;
                while (true) {
                    if (i11 >= length2) {
                        i7 = -1;
                        break;
                    }
                    if (!y5.b.c(str2.charAt(i11))) {
                        i7 = i11;
                        break;
                    }
                    i11++;
                }
                if (i7 != -1) {
                    int i12 = i7;
                    if (p.z(str2, marginPrefix, i7, false, 4, null)) {
                        strSubstring = str2.substring(i12 + marginPrefix.length());
                        kotlin.jvm.internal.l.d(strSubstring, "this as java.lang.String).substring(startIndex)");
                    }
                }
                if (strSubstring != null && (strInvoke = lVarB.invoke(strSubstring)) != null) {
                    str2 = strInvoke;
                }
            }
            if (str2 != null) {
                arrayList.add(str2);
            }
            i9 = i10;
        }
        String string = ((StringBuilder) x.x(arrayList, new StringBuilder(length), (124 & 2) != 0 ? ", " : "\n", (124 & 4) != 0 ? "" : null, (124 & 8) == 0 ? null : "", (124 & 16) != 0 ? -1 : 0, (124 & 32) != 0 ? "..." : null, (124 & 64) != 0 ? null : null)).toString();
        kotlin.jvm.internal.l.d(string, "mapIndexedNotNull { inde…\"\\n\")\n        .toString()");
        return string;
    }

    public static String f(String str) {
        kotlin.jvm.internal.l.e(str, "<this>");
        return d(str, "");
    }

    public static final String g(String str, String marginPrefix) {
        kotlin.jvm.internal.l.e(str, "<this>");
        kotlin.jvm.internal.l.e(marginPrefix, "marginPrefix");
        return e(str, "", marginPrefix);
    }

    public static /* synthetic */ String h(String str, String str2, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            str2 = "|";
        }
        return g(str, str2);
    }
}
