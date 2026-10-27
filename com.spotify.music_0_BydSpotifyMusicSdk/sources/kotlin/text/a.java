package kotlin.text;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p.bk0;
import p.lu;
import p.ng2;
import p.ou;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public abstract class a extends v03 {
    public static String W(String str) {
        int length;
        Comparable comparable;
        v03.j(str, "<this>");
        List listS0 = b.s0(str);
        ArrayList arrayList = new ArrayList();
        for (Object obj : listS0) {
            if (!ng2.c0((String) obj)) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(lu.W(arrayList));
        Iterator it = arrayList.iterator();
        while (true) {
            length = 0;
            if (!it.hasNext()) {
                break;
            }
            String str2 = (String) it.next();
            int length2 = str2.length();
            while (true) {
                if (length >= length2) {
                    length = -1;
                    break;
                }
                if (!bk0.J(str2.charAt(length))) {
                    break;
                }
                length++;
            }
            if (length == -1) {
                length = str2.length();
            }
            arrayList2.add(Integer.valueOf(length));
        }
        Iterator it2 = arrayList2.iterator();
        if (it2.hasNext()) {
            comparable = (Comparable) it2.next();
            while (it2.hasNext()) {
                Comparable comparable2 = (Comparable) it2.next();
                if (comparable.compareTo(comparable2) > 0) {
                    comparable = comparable2;
                }
            }
        } else {
            comparable = null;
        }
        Integer num = (Integer) comparable;
        int iIntValue = num != null ? num.intValue() : 0;
        int length3 = str.length();
        listS0.size();
        int iQ = v03.q(listS0);
        ArrayList arrayList3 = new ArrayList();
        for (Object obj2 : listS0) {
            int i = length + 1;
            if (length < 0) {
                v03.P();
                throw null;
            }
            String str3 = (String) obj2;
            if ((length == 0 || length == iQ) && ng2.c0(str3)) {
                str3 = null;
            } else {
                v03.j(str3, "<this>");
                if (iIntValue < 0) {
                    throw new IllegalArgumentException(u4.f("Requested character count ", iIntValue, " is less than zero.").toString());
                }
                int length4 = str3.length();
                if (iIntValue <= length4) {
                    length4 = iIntValue;
                }
                String strSubstring = str3.substring(length4);
                v03.i(strSubstring, "this as java.lang.String).substring(startIndex)");
                String str4 = (String) StringsKt__IndentKt$getIndentFunction$1.c.invoke(strSubstring);
                if (str4 != null) {
                    str3 = str4;
                }
            }
            if (str3 != null) {
                arrayList3.add(str3);
            }
            length = i;
        }
        StringBuilder sb = new StringBuilder(length3);
        ou.f0(arrayList3, sb, "\n", "", "", -1, "...", null);
        String string = sb.toString();
        v03.i(string, "mapIndexedNotNull { inde…\"\\n\")\n        .toString()");
        return string;
    }

    public static String X(String str) {
        v03.j(str, "<this>");
        if (!(!ng2.c0("|"))) {
            throw new IllegalArgumentException("marginPrefix must be non-blank string.".toString());
        }
        List listS0 = b.s0(str);
        int length = str.length();
        listS0.size();
        int iQ = v03.q(listS0);
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Object obj : listS0) {
            int i2 = i + 1;
            String strSubstring = null;
            if (i < 0) {
                v03.P();
                throw null;
            }
            String str2 = (String) obj;
            if ((i != 0 && i != iQ) || !ng2.c0(str2)) {
                int length2 = str2.length();
                int i3 = 0;
                while (true) {
                    if (i3 >= length2) {
                        i3 = -1;
                        break;
                    }
                    if (!bk0.J(str2.charAt(i3))) {
                        break;
                    }
                    i3++;
                }
                if (i3 != -1 && ng2.f0(i3, str2, "|", false)) {
                    strSubstring = str2.substring("|".length() + i3);
                    v03.i(strSubstring, "this as java.lang.String).substring(startIndex)");
                }
                if (strSubstring == null || (strSubstring = (String) StringsKt__IndentKt$getIndentFunction$1.c.invoke(strSubstring)) == null) {
                    strSubstring = str2;
                }
            }
            if (strSubstring != null) {
                arrayList.add(strSubstring);
            }
            i = i2;
        }
        StringBuilder sb = new StringBuilder(length);
        ou.f0(arrayList, sb, "\n", "", "", -1, "...", null);
        String string = sb.toString();
        v03.i(string, "mapIndexedNotNull { inde…\"\\n\")\n        .toString()");
        return string;
    }
}
