package c9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;
import n7.AbstractC1996k;

/* JADX INFO: loaded from: classes.dex */
public final class u implements Iterable, B7.a {
    public final String[] l;

    public u(String[] strArr) {
        this.l = strArr;
    }

    public final String d(String str) {
        A7.m.f(str, "name");
        String[] strArr = this.l;
        int length = strArr.length - 2;
        int iN = n7.z.n(length, 0, -2);
        if (iN <= length) {
            while (!Q8.o.t0(str, strArr[length], true)) {
                if (length != iN) {
                    length -= 2;
                }
            }
            return strArr[length + 1];
        }
        return null;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof u) {
            if (Arrays.equals(this.l, ((u) obj).l)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(this.l);
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        int size = size();
        m7.i[] iVarArr = new m7.i[size];
        for (int i7 = 0; i7 < size; i7++) {
            iVarArr[i7] = new m7.i(l(i7), s(i7));
        }
        return A7.m.h(iVarArr);
    }

    public final String l(int i7) {
        return this.l[i7 * 2];
    }

    public final A7.D q() {
        A7.D d = new A7.D(5, false);
        ArrayList arrayList = d.l;
        A7.m.f(arrayList, "<this>");
        String[] strArr = this.l;
        A7.m.f(strArr, "elements");
        arrayList.addAll(AbstractC1996k.x(strArr));
        return d;
    }

    public final TreeMap r() {
        Comparator comparator = String.CASE_INSENSITIVE_ORDER;
        A7.m.e(comparator, "CASE_INSENSITIVE_ORDER");
        TreeMap treeMap = new TreeMap(comparator);
        int size = size();
        for (int i7 = 0; i7 < size; i7++) {
            String strL = l(i7);
            Locale locale = Locale.US;
            A7.m.e(locale, "US");
            String lowerCase = strL.toLowerCase(locale);
            A7.m.e(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            List arrayList = (List) treeMap.get(lowerCase);
            if (arrayList == null) {
                arrayList = new ArrayList(2);
                treeMap.put(lowerCase, arrayList);
            }
            arrayList.add(s(i7));
        }
        return treeMap;
    }

    public final String s(int i7) {
        return this.l[(i7 * 2) + 1];
    }

    public final int size() {
        return this.l.length / 2;
    }

    public final List t(String str) {
        A7.m.f(str, "name");
        int size = size();
        ArrayList arrayList = null;
        for (int i7 = 0; i7 < size; i7++) {
            if (str.equalsIgnoreCase(l(i7))) {
                if (arrayList == null) {
                    arrayList = new ArrayList(2);
                }
                arrayList.add(s(i7));
            }
        }
        if (arrayList == null) {
            return n7.u.l;
        }
        List listUnmodifiableList = Collections.unmodifiableList(arrayList);
        A7.m.e(listUnmodifiableList, "{\n      Collections.unmodifiableList(result)\n    }");
        return listUnmodifiableList;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        int size = size();
        for (int i7 = 0; i7 < size; i7++) {
            String strL = l(i7);
            String strS = s(i7);
            sb.append(strL);
            sb.append(": ");
            if (d9.b.q(strL)) {
                strS = "██";
            }
            sb.append(strS);
            sb.append("\n");
        }
        String string = sb.toString();
        A7.m.e(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }
}
