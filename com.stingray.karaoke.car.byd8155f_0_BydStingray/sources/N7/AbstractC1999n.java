package n7;

import h8.AbstractC1387d;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: renamed from: n7.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1999n extends AbstractC1387d {
    public static int A(ArrayList arrayList, Comparable comparable) {
        int size = arrayList.size();
        A7.m.f(arrayList, "<this>");
        int i7 = 0;
        H(arrayList.size(), 0, size);
        int i10 = size - 1;
        while (i7 <= i10) {
            int i11 = (i7 + i10) >>> 1;
            int iB = h6.f.b((Comparable) arrayList.get(i11), comparable);
            if (iB < 0) {
                i7 = i11 + 1;
            } else {
                if (iB <= 0) {
                    return i11;
                }
                i10 = i11 - 1;
            }
        }
        return -(i7 + 1);
    }

    public static G7.d B(Collection collection) {
        A7.m.f(collection, "<this>");
        return new G7.d(0, collection.size() - 1, 1);
    }

    public static int C(List list) {
        A7.m.f(list, "<this>");
        return list.size() - 1;
    }

    public static List D(Object... objArr) {
        A7.m.f(objArr, "elements");
        return objArr.length > 0 ? AbstractC1996k.x(objArr) : u.l;
    }

    public static List E(Object obj) {
        return obj != null ? AbstractC1387d.j(obj) : u.l;
    }

    public static ArrayList F(Object... objArr) {
        return objArr.length == 0 ? new ArrayList() : new ArrayList(new C1994i(objArr, true));
    }

    public static final List G(List list) {
        int size = list.size();
        return size != 0 ? size != 1 ? list : AbstractC1387d.j(list.get(0)) : u.l;
    }

    public static final void H(int i7, int i10, int i11) {
        if (i10 > i11) {
            throw new IllegalArgumentException("fromIndex (" + i10 + ") is greater than toIndex (" + i11 + ").");
        }
        if (i10 < 0) {
            throw new IndexOutOfBoundsException(A7.k.g("fromIndex (", i10, ") is less than zero."));
        }
        if (i11 <= i7) {
            return;
        }
        throw new IndexOutOfBoundsException("toIndex (" + i11 + ") is greater than size (" + i7 + ").");
    }

    public static void I() {
        throw new ArithmeticException("Index overflow has happened.");
    }

    public static ArrayList z(Object... objArr) {
        return objArr.length == 0 ? new ArrayList() : new ArrayList(new C1994i(objArr, true));
    }
}
