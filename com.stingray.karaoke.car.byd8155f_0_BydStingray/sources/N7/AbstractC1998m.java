package n7;

import h8.AbstractC1387d;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import java.util.Set;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: n7.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1998m extends AbstractC2004s {
    public static ArrayList A0(Collection collection) {
        A7.m.f(collection, "<this>");
        return new ArrayList(collection);
    }

    public static final List B0(Iterable iterable) {
        A7.m.f(iterable, "<this>");
        if (iterable instanceof Collection) {
            return A0((Collection) iterable);
        }
        ArrayList arrayList = new ArrayList();
        x0(iterable, arrayList);
        return arrayList;
    }

    public static Set C0(Iterable iterable) {
        A7.m.f(iterable, "<this>");
        if (iterable instanceof Collection) {
            return new LinkedHashSet((Collection) iterable);
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        x0(iterable, linkedHashSet);
        return linkedHashSet;
    }

    public static Set D0(Iterable iterable) {
        A7.m.f(iterable, "<this>");
        boolean z6 = iterable instanceof Collection;
        w wVar = w.l;
        if (!z6) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            x0(iterable, linkedHashSet);
            int size = linkedHashSet.size();
            return size != 0 ? size != 1 ? linkedHashSet : AbstractC1985B.t(linkedHashSet.iterator().next()) : wVar;
        }
        Collection collection = (Collection) iterable;
        int size2 = collection.size();
        if (size2 == 0) {
            return wVar;
        }
        if (size2 == 1) {
            return AbstractC1985B.t(iterable instanceof List ? ((List) iterable).get(0) : collection.iterator().next());
        }
        LinkedHashSet linkedHashSet2 = new LinkedHashSet(z.q(collection.size()));
        x0(iterable, linkedHashSet2);
        return linkedHashSet2;
    }

    public static P8.p E0(List list) {
        A7.m.f(list, "<this>");
        return new P8.p(new A3.g(list, 9), 2);
    }

    public static ArrayList F0(Collection collection, Collection collection2) {
        A7.m.f(collection, "<this>");
        A7.m.f(collection2, "other");
        Iterator it = collection.iterator();
        Iterator it2 = collection2.iterator();
        ArrayList arrayList = new ArrayList(Math.min(AbstractC2000o.J(collection, 10), AbstractC2000o.J(collection2, 10)));
        while (it.hasNext() && it2.hasNext()) {
            arrayList.add(new m7.i(it.next(), it2.next()));
        }
        return arrayList;
    }

    public static final int Q(int i7, List list) {
        if (i7 >= 0 && i7 <= AbstractC1999n.C(list)) {
            return AbstractC1999n.C(list) - i7;
        }
        StringBuilder sbJ = M5.b.j("Element index ", i7, " must be in range [");
        sbJ.append(new G7.d(0, AbstractC1999n.C(list), 1));
        sbJ.append("].");
        throw new IndexOutOfBoundsException(sbJ.toString());
    }

    public static final int R(int i7, List list) {
        if (i7 >= 0 && i7 <= list.size()) {
            return list.size() - i7;
        }
        StringBuilder sbJ = M5.b.j("Position index ", i7, " must be in range [");
        sbJ.append(new G7.d(0, list.size(), 1));
        sbJ.append("].");
        throw new IndexOutOfBoundsException(sbJ.toString());
    }

    public static P8.o S(Iterable iterable) {
        A7.m.f(iterable, "<this>");
        return new P8.o(iterable, 2);
    }

    public static boolean T(Iterable iterable, Object obj) {
        A7.m.f(iterable, "<this>");
        return iterable instanceof Collection ? ((Collection) iterable).contains(obj) : c0(iterable, obj) >= 0;
    }

    public static List U(Iterable iterable) {
        A7.m.f(iterable, "<this>");
        return z0(C0(iterable));
    }

    public static List V(List list) {
        A7.m.f(list, "<this>");
        int size = list.size() - 1;
        if (size <= 0) {
            return u.l;
        }
        if (size == 1) {
            return AbstractC1387d.j(g0(list));
        }
        ArrayList arrayList = new ArrayList(size);
        if (list instanceof RandomAccess) {
            int size2 = list.size();
            for (int i7 = 1; i7 < size2; i7++) {
                arrayList.add(list.get(i7));
            }
        } else {
            ListIterator listIterator = list.listIterator(1);
            while (listIterator.hasNext()) {
                arrayList.add(listIterator.next());
            }
        }
        return arrayList;
    }

    public static List W(List list) {
        A7.m.f(list, "<this>");
        int size = list.size() - 1;
        if (size < 0) {
            size = 0;
        }
        return v0(size, list);
    }

    public static Object X(Iterable iterable) {
        A7.m.f(iterable, "<this>");
        if (iterable instanceof List) {
            return Y((List) iterable);
        }
        Iterator it = iterable.iterator();
        if (it.hasNext()) {
            return it.next();
        }
        throw new NoSuchElementException("Collection is empty.");
    }

    public static Object Y(List list) {
        A7.m.f(list, "<this>");
        if (list.isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        return list.get(0);
    }

    public static Object Z(Iterable iterable) {
        if (iterable instanceof List) {
            List list = (List) iterable;
            if (list.isEmpty()) {
                return null;
            }
            return list.get(0);
        }
        Iterator it = iterable.iterator();
        if (it.hasNext()) {
            return it.next();
        }
        return null;
    }

    public static Object a0(List list) {
        A7.m.f(list, "<this>");
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public static Object b0(int i7, List list) {
        A7.m.f(list, "<this>");
        if (i7 < 0 || i7 >= list.size()) {
            return null;
        }
        return list.get(i7);
    }

    public static int c0(Iterable iterable, Object obj) {
        A7.m.f(iterable, "<this>");
        if (iterable instanceof List) {
            return ((List) iterable).indexOf(obj);
        }
        int i7 = 0;
        for (Object obj2 : iterable) {
            if (i7 < 0) {
                AbstractC1999n.I();
                throw null;
            }
            if (A7.m.a(obj, obj2)) {
                return i7;
            }
            i7++;
        }
        return -1;
    }

    public static final void d0(Iterable iterable, StringBuilder sb, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i7, CharSequence charSequence4, InterfaceC2782k interfaceC2782k) {
        A7.m.f(iterable, "<this>");
        A7.m.f(sb, "buffer");
        A7.m.f(charSequence, "separator");
        A7.m.f(charSequence2, "prefix");
        A7.m.f(charSequence3, "postfix");
        A7.m.f(charSequence4, "truncated");
        sb.append(charSequence2);
        int i10 = 0;
        for (Object obj : iterable) {
            i10++;
            if (i10 > 1) {
                sb.append(charSequence);
            }
            if (i7 >= 0 && i10 > i7) {
                break;
            } else {
                io.sentry.config.a.K(sb, obj, interfaceC2782k);
            }
        }
        if (i7 >= 0 && i10 > i7) {
            sb.append(charSequence4);
        }
        sb.append(charSequence3);
    }

    public static String f0(Iterable iterable, String str, String str2, String str3, InterfaceC2782k interfaceC2782k, int i7) {
        if ((i7 & 1) != 0) {
            str = ", ";
        }
        String str4 = str;
        String str5 = (i7 & 2) != 0 ? "" : str2;
        String str6 = (i7 & 4) != 0 ? "" : str3;
        if ((i7 & 32) != 0) {
            interfaceC2782k = null;
        }
        A7.m.f(iterable, "<this>");
        A7.m.f(str4, "separator");
        A7.m.f(str5, "prefix");
        A7.m.f(str6, "postfix");
        StringBuilder sb = new StringBuilder();
        d0(iterable, sb, str4, str5, str6, -1, "...", interfaceC2782k);
        return sb.toString();
    }

    public static Object g0(Iterable iterable) {
        A7.m.f(iterable, "<this>");
        if (iterable instanceof List) {
            return h0((List) iterable);
        }
        Iterator it = iterable.iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException("Collection is empty.");
        }
        Object next = it.next();
        while (it.hasNext()) {
            next = it.next();
        }
        return next;
    }

    public static Object h0(List list) {
        A7.m.f(list, "<this>");
        if (list.isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        return list.get(AbstractC1999n.C(list));
    }

    public static Object i0(List list) {
        A7.m.f(list, "<this>");
        if (list.isEmpty()) {
            return null;
        }
        return list.get(list.size() - 1);
    }

    public static Comparable j0(ArrayList arrayList) {
        Iterator it = arrayList.iterator();
        if (!it.hasNext()) {
            return null;
        }
        Comparable comparable = (Comparable) it.next();
        while (it.hasNext()) {
            Comparable comparable2 = (Comparable) it.next();
            if (comparable.compareTo(comparable2) < 0) {
                comparable = comparable2;
            }
        }
        return comparable;
    }

    public static ArrayList k0(List list, Serializable serializable) {
        A7.m.f(list, "<this>");
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(list, 10));
        boolean z6 = false;
        for (Object obj : list) {
            boolean z10 = true;
            if (!z6 && A7.m.a(obj, serializable)) {
                z6 = true;
                z10 = false;
            }
            if (z10) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static ArrayList l0(Iterable iterable, Iterable iterable2) {
        A7.m.f(iterable, "<this>");
        if (iterable instanceof Collection) {
            return n0((Collection) iterable, iterable2);
        }
        ArrayList arrayList = new ArrayList();
        AbstractC2004s.L(arrayList, iterable);
        AbstractC2004s.L(arrayList, iterable2);
        return arrayList;
    }

    public static ArrayList m0(Iterable iterable, Object obj) {
        if (iterable instanceof Collection) {
            return o0((Collection) iterable, obj);
        }
        ArrayList arrayList = new ArrayList();
        AbstractC2004s.L(arrayList, iterable);
        arrayList.add(obj);
        return arrayList;
    }

    public static ArrayList n0(Collection collection, Iterable iterable) {
        A7.m.f(collection, "<this>");
        A7.m.f(iterable, "elements");
        if (!(iterable instanceof Collection)) {
            ArrayList arrayList = new ArrayList(collection);
            AbstractC2004s.L(arrayList, iterable);
            return arrayList;
        }
        Collection collection2 = (Collection) iterable;
        ArrayList arrayList2 = new ArrayList(collection2.size() + collection.size());
        arrayList2.addAll(collection);
        arrayList2.addAll(collection2);
        return arrayList2;
    }

    public static ArrayList o0(Collection collection, Object obj) {
        A7.m.f(collection, "<this>");
        ArrayList arrayList = new ArrayList(collection.size() + 1);
        arrayList.addAll(collection);
        arrayList.add(obj);
        return arrayList;
    }

    public static List p0(Iterable iterable) {
        A7.m.f(iterable, "<this>");
        if ((iterable instanceof Collection) && ((Collection) iterable).size() <= 1) {
            return z0(iterable);
        }
        List listB0 = B0(iterable);
        Collections.reverse(listB0);
        return listB0;
    }

    public static Object q0(Iterable iterable) {
        A7.m.f(iterable, "<this>");
        if (iterable instanceof List) {
            return r0((List) iterable);
        }
        Iterator it = iterable.iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException("Collection is empty.");
        }
        Object next = it.next();
        if (it.hasNext()) {
            throw new IllegalArgumentException("Collection has more than one element.");
        }
        return next;
    }

    public static Object r0(List list) {
        A7.m.f(list, "<this>");
        int size = list.size();
        if (size == 0) {
            throw new NoSuchElementException("List is empty.");
        }
        if (size == 1) {
            return list.get(0);
        }
        throw new IllegalArgumentException("List has more than one element.");
    }

    public static Object s0(Iterable iterable) {
        A7.m.f(iterable, "<this>");
        if (iterable instanceof List) {
            List list = (List) iterable;
            if (list.size() == 1) {
                return list.get(0);
            }
            return null;
        }
        Iterator it = iterable.iterator();
        if (!it.hasNext()) {
            return null;
        }
        Object next = it.next();
        if (it.hasNext()) {
            return null;
        }
        return next;
    }

    public static Object t0(List list) {
        A7.m.f(list, "<this>");
        if (list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    public static List u0(Collection collection, Comparator comparator) {
        A7.m.f(collection, "<this>");
        if (!(collection instanceof Collection)) {
            List listB0 = B0(collection);
            AbstractC2003r.K(listB0, comparator);
            return listB0;
        }
        Collection collection2 = collection;
        if (collection2.size() <= 1) {
            return z0(collection);
        }
        Object[] array = collection2.toArray(new Object[0]);
        A7.m.f(array, "<this>");
        if (array.length > 1) {
            Arrays.sort(array, comparator);
        }
        return AbstractC1996k.x(array);
    }

    public static List v0(int i7, List list) {
        A7.m.f(list, "<this>");
        if (i7 < 0) {
            throw new IllegalArgumentException(A7.k.g("Requested element count ", i7, " is less than zero.").toString());
        }
        if (i7 == 0) {
            return u.l;
        }
        if (i7 >= list.size()) {
            return z0(list);
        }
        if (i7 == 1) {
            return AbstractC1387d.j(X(list));
        }
        ArrayList arrayList = new ArrayList(i7);
        Iterator it = list.iterator();
        int i10 = 0;
        while (it.hasNext()) {
            arrayList.add(it.next());
            i10++;
            if (i10 == i7) {
                break;
            }
        }
        return AbstractC1999n.G(arrayList);
    }

    public static List w0(int i7, List list) {
        A7.m.f(list, "<this>");
        if (i7 < 0) {
            throw new IllegalArgumentException(A7.k.g("Requested element count ", i7, " is less than zero.").toString());
        }
        if (i7 == 0) {
            return u.l;
        }
        int size = list.size();
        if (i7 >= size) {
            return z0(list);
        }
        if (i7 == 1) {
            return AbstractC1387d.j(h0(list));
        }
        ArrayList arrayList = new ArrayList(i7);
        if (list instanceof RandomAccess) {
            for (int i10 = size - i7; i10 < size; i10++) {
                arrayList.add(list.get(i10));
            }
        } else {
            ListIterator listIterator = list.listIterator(size - i7);
            while (listIterator.hasNext()) {
                arrayList.add(listIterator.next());
            }
        }
        return arrayList;
    }

    public static final void x0(Iterable iterable, AbstractCollection abstractCollection) {
        A7.m.f(iterable, "<this>");
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            abstractCollection.add(it.next());
        }
    }

    public static int[] y0(List list) {
        A7.m.f(list, "<this>");
        int[] iArr = new int[list.size()];
        Iterator it = list.iterator();
        int i7 = 0;
        while (it.hasNext()) {
            iArr[i7] = ((Number) it.next()).intValue();
            i7++;
        }
        return iArr;
    }

    public static List z0(Iterable iterable) {
        A7.m.f(iterable, "<this>");
        if (!(iterable instanceof Collection)) {
            return AbstractC1999n.G(B0(iterable));
        }
        Collection collection = (Collection) iterable;
        int size = collection.size();
        if (size == 0) {
            return u.l;
        }
        if (size != 1) {
            return A0(collection);
        }
        return AbstractC1387d.j(iterable instanceof List ? ((List) iterable).get(0) : collection.iterator().next());
    }
}
