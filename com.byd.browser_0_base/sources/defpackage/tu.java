package defpackage;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: compiled from: _Collections.kt */
/* JADX INFO: loaded from: classes.dex */
public class tu extends su {
    public static final <T> boolean p0(Iterable<? extends T> iterable, T t) {
        int iIndexOf;
        i21.e(iterable, "<this>");
        if (iterable instanceof Collection) {
            return ((Collection) iterable).contains(t);
        }
        if (!(iterable instanceof List)) {
            Iterator<? extends T> it = iterable.iterator();
            int i = 0;
            while (true) {
                if (!it.hasNext()) {
                    iIndexOf = -1;
                    break;
                }
                T next = it.next();
                if (i < 0) {
                    throw new ArithmeticException("Index overflow has happened.");
                }
                if (i21.a(t, next)) {
                    iIndexOf = i;
                    break;
                }
                i++;
            }
        } else {
            iIndexOf = ((List) iterable).indexOf(t);
        }
        return iIndexOf >= 0;
    }

    public static final void q0(Iterable iterable, AbstractCollection abstractCollection) {
        i21.e(iterable, "<this>");
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            abstractCollection.add(it.next());
        }
    }

    public static final ArrayList r0(Collection collection) {
        i21.e(collection, "<this>");
        return new ArrayList(collection);
    }

    public static final Set s0(ArrayList arrayList) {
        jh0 jh0Var = jh0.e;
        int size = arrayList.size();
        if (size == 0) {
            return jh0Var;
        }
        if (size != 1) {
            LinkedHashSet linkedHashSet = new LinkedHashSet(oj2.M(arrayList.size()));
            q0(arrayList, linkedHashSet);
            return linkedHashSet;
        }
        Set setSingleton = Collections.singleton(arrayList.get(0));
        i21.d(setSingleton, "singleton(element)");
        return setSingleton;
    }
}
