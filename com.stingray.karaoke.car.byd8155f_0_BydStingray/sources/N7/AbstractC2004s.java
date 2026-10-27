package n7;

import A7.E;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: n7.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2004s extends AbstractC2003r {
    public static void L(Collection collection, Iterable iterable) {
        A7.m.f(collection, "<this>");
        A7.m.f(iterable, "elements");
        if (iterable instanceof Collection) {
            collection.addAll((Collection) iterable);
            return;
        }
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            collection.add(it.next());
        }
    }

    public static void M(List list, InterfaceC2782k interfaceC2782k) {
        int iC;
        A7.m.f(list, "<this>");
        A7.m.f(interfaceC2782k, "predicate");
        if (!(list instanceof RandomAccess)) {
            if ((list instanceof B7.a) && !(list instanceof B7.b)) {
                E.g(list, "kotlin.collections.MutableIterable");
                throw null;
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (((Boolean) interfaceC2782k.b(it.next())).booleanValue()) {
                    it.remove();
                }
            }
            return;
        }
        int iC2 = AbstractC1999n.C(list);
        int i7 = 0;
        if (iC2 >= 0) {
            int i10 = 0;
            while (true) {
                Object obj = list.get(i7);
                if (!((Boolean) interfaceC2782k.b(obj)).booleanValue()) {
                    if (i10 != i7) {
                        list.set(i10, obj);
                    }
                    i10++;
                }
                if (i7 == iC2) {
                    break;
                } else {
                    i7++;
                }
            }
            i7 = i10;
        }
        if (i7 >= list.size() || i7 > (iC = AbstractC1999n.C(list))) {
            return;
        }
        while (true) {
            list.remove(iC);
            if (iC == i7) {
                return;
            } else {
                iC--;
            }
        }
    }

    public static Object N(List list) {
        if (list.isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        return list.remove(0);
    }

    public static Object O(ArrayList arrayList) {
        A7.m.f(arrayList, "<this>");
        if (arrayList.isEmpty()) {
            return null;
        }
        return arrayList.remove(0);
    }

    public static Object P(List list) {
        A7.m.f(list, "<this>");
        if (list.isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        return list.remove(AbstractC1999n.C(list));
    }
}
