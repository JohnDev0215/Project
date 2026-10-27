package Q8;

import g1.AbstractC1274a;
import h8.AbstractC1387d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import n7.AbstractC1998m;
import n7.AbstractC1999n;
import n7.AbstractC2000o;
import n7.u;

/* JADX INFO: loaded from: classes.dex */
public abstract class i extends io.sentry.config.a {
    public static String q0(String str) {
        List listJ;
        int length;
        Comparable comparable;
        String strSubstring;
        A7.m.f(str, "<this>");
        d dVar = new d(str);
        if (dVar.hasNext()) {
            Object next = dVar.next();
            if (dVar.hasNext()) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(next);
                while (dVar.hasNext()) {
                    arrayList.add(dVar.next());
                }
                listJ = arrayList;
            } else {
                listJ = AbstractC1387d.j(next);
            }
        } else {
            listJ = u.l;
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : listJ) {
            if (!h.M0((String) obj)) {
                arrayList2.add(obj);
            }
        }
        ArrayList arrayList3 = new ArrayList(AbstractC2000o.J(arrayList2, 10));
        Iterator it = arrayList2.iterator();
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
                if (!AbstractC1274a.T(str2.charAt(length))) {
                    break;
                }
                length++;
            }
            if (length == -1) {
                length = str2.length();
            }
            arrayList3.add(Integer.valueOf(length));
        }
        Iterator it2 = arrayList3.iterator();
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
        listJ.size();
        int iC = AbstractC1999n.C(listJ);
        ArrayList arrayList4 = new ArrayList();
        for (Object obj2 : listJ) {
            int i7 = length + 1;
            if (length < 0) {
                AbstractC1999n.I();
                throw null;
            }
            String str3 = (String) obj2;
            if ((length == 0 || length == iC) && h.M0(str3)) {
                strSubstring = null;
            } else {
                A7.m.f(str3, "<this>");
                if (iIntValue < 0) {
                    throw new IllegalArgumentException(A7.k.g("Requested character count ", iIntValue, " is less than zero.").toString());
                }
                int length4 = str3.length();
                if (iIntValue <= length4) {
                    length4 = iIntValue;
                }
                strSubstring = str3.substring(length4);
                A7.m.e(strSubstring, "substring(...)");
            }
            if (strSubstring != null) {
                arrayList4.add(strSubstring);
            }
            length = i7;
        }
        StringBuilder sb = new StringBuilder(length3);
        AbstractC1998m.d0(arrayList4, sb, "\n", (124 & 4) != 0 ? "" : null, (124 & 8) != 0 ? "" : null, -1, "...", (124 & 64) != 0 ? null : null);
        return sb.toString();
    }

    public static String r0(String str) {
        List listJ;
        A7.m.f(str, "<this>");
        if (h.M0("|")) {
            throw new IllegalArgumentException("marginPrefix must be non-blank string.");
        }
        d dVar = new d(str);
        if (dVar.hasNext()) {
            Object next = dVar.next();
            if (dVar.hasNext()) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(next);
                while (dVar.hasNext()) {
                    arrayList.add(dVar.next());
                }
                listJ = arrayList;
            } else {
                listJ = AbstractC1387d.j(next);
            }
        } else {
            listJ = u.l;
        }
        int length = str.length();
        listJ.size();
        int iC = AbstractC1999n.C(listJ);
        ArrayList arrayList2 = new ArrayList();
        int i7 = 0;
        for (Object obj : listJ) {
            int i10 = i7 + 1;
            String strSubstring = null;
            if (i7 < 0) {
                AbstractC1999n.I();
                throw null;
            }
            String str2 = (String) obj;
            if ((i7 != 0 && i7 != iC) || !h.M0(str2)) {
                int length2 = str2.length();
                int i11 = 0;
                while (true) {
                    if (i11 >= length2) {
                        i11 = -1;
                        break;
                    }
                    if (!AbstractC1274a.T(str2.charAt(i11))) {
                        break;
                    }
                    i11++;
                }
                if (i11 != -1 && o.y0(i11, str2, "|", false)) {
                    strSubstring = str2.substring("|".length() + i11);
                    A7.m.e(strSubstring, "substring(...)");
                }
                if (strSubstring == null) {
                    strSubstring = str2;
                }
            }
            if (strSubstring != null) {
                arrayList2.add(strSubstring);
            }
            i7 = i10;
        }
        StringBuilder sb = new StringBuilder(length);
        AbstractC1998m.d0(arrayList2, sb, "\n", (124 & 4) != 0 ? "" : null, (124 & 8) != 0 ? "" : null, -1, "...", (124 & 64) != 0 ? null : null);
        return sb.toString();
    }
}
