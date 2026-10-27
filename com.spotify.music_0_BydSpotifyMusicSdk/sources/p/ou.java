package p;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import java.util.Set;
import kotlin.collections.EmptyList;
import kotlin.collections.EmptySet;

/* JADX INFO: loaded from: classes.dex */
public abstract class ou extends nu {
    public static sb Y(Iterable iterable) {
        v03.j(iterable, "<this>");
        return new sb(1, iterable);
    }

    public static boolean Z(Iterable iterable, Object obj) {
        v03.j(iterable, "<this>");
        return iterable instanceof Collection ? ((Collection) iterable).contains(obj) : e0(iterable, obj) >= 0;
    }

    public static List a0(List list) {
        v03.j(list, "<this>");
        int size = list.size() - 1;
        if (size <= 0) {
            return EmptyList.c;
        }
        if (size == 1) {
            return v03.B(i0(list));
        }
        ArrayList arrayList = new ArrayList(size);
        if (list instanceof RandomAccess) {
            int size2 = list.size();
            for (int i = 1; i < size2; i++) {
                arrayList.add(list.get(i));
            }
        } else {
            ListIterator listIterator = list.listIterator(1);
            while (listIterator.hasNext()) {
                arrayList.add(listIterator.next());
            }
        }
        return arrayList;
    }

    public static Object b0(Collection collection) {
        v03.j(collection, "<this>");
        if (collection instanceof List) {
            return c0((List) collection);
        }
        Iterator it = collection.iterator();
        if (it.hasNext()) {
            return it.next();
        }
        throw new NoSuchElementException("Collection is empty.");
    }

    public static Object c0(List list) {
        v03.j(list, "<this>");
        if (list.isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        return list.get(0);
    }

    public static Object d0(List list) {
        v03.j(list, "<this>");
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public static int e0(Iterable iterable, Object obj) {
        v03.j(iterable, "<this>");
        if (iterable instanceof List) {
            return ((List) iterable).indexOf(obj);
        }
        int i = 0;
        for (Object obj2 : iterable) {
            if (i < 0) {
                v03.P();
                throw null;
            }
            if (v03.b(obj, obj2)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static final void f0(Iterable iterable, StringBuilder sb, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, ry0 ry0Var) {
        v03.j(iterable, "<this>");
        v03.j(charSequence, "separator");
        v03.j(charSequence2, "prefix");
        v03.j(charSequence3, "postfix");
        v03.j(charSequence4, "truncated");
        sb.append(charSequence2);
        int i2 = 0;
        for (Object obj : iterable) {
            i2++;
            if (i2 > 1) {
                sb.append(charSequence);
            }
            if (i >= 0 && i2 > i) {
                break;
            } else {
                v03.a(sb, obj, ry0Var);
            }
        }
        if (i >= 0 && i2 > i) {
            sb.append(charSequence4);
        }
        sb.append(charSequence3);
    }

    public static String h0(Iterable iterable, String str, String str2, String str3, ry0 ry0Var, int i) {
        if ((i & 1) != 0) {
            str = ", ";
        }
        String str4 = str;
        String str5 = (i & 2) != 0 ? "" : str2;
        String str6 = (i & 4) != 0 ? "" : str3;
        int i2 = (i & 8) != 0 ? -1 : 0;
        CharSequence charSequence = (i & 16) != 0 ? "..." : null;
        ry0 ry0Var2 = (i & 32) != 0 ? null : ry0Var;
        v03.j(iterable, "<this>");
        v03.j(str4, "separator");
        v03.j(str5, "prefix");
        v03.j(str6, "postfix");
        v03.j(charSequence, "truncated");
        StringBuilder sb = new StringBuilder();
        f0(iterable, sb, str4, str5, str6, i2, charSequence, ry0Var2);
        String string = sb.toString();
        v03.i(string, "joinTo(StringBuilder(), …ed, transform).toString()");
        return string;
    }

    public static Object i0(List list) {
        v03.j(list, "<this>");
        if (list.isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        return list.get(v03.q(list));
    }

    public static ArrayList j0(List list, Object obj) {
        v03.j(list, "<this>");
        ArrayList arrayList = new ArrayList(list.size() + 1);
        arrayList.addAll(list);
        arrayList.add(obj);
        return arrayList;
    }

    public static ArrayList k0(List list, List list2) {
        v03.j(list2, "<this>");
        v03.j(list, "elements");
        ArrayList arrayList = new ArrayList(list.size() + list2.size());
        arrayList.addAll(list2);
        arrayList.addAll(list);
        return arrayList;
    }

    public static List l0(List list, int i) {
        v03.j(list, "<this>");
        if (i < 0) {
            throw new IllegalArgumentException(u4.f("Requested element count ", i, " is less than zero.").toString());
        }
        if (i == 0) {
            return EmptyList.c;
        }
        if (i >= list.size()) {
            return n0(list);
        }
        if (i == 1) {
            return v03.B(b0(list));
        }
        ArrayList arrayList = new ArrayList(i);
        Iterator it = list.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            arrayList.add(it.next());
            i2++;
            if (i2 == i) {
                break;
            }
        }
        return v03.F(arrayList);
    }

    public static final void m0(Iterable iterable, AbstractCollection abstractCollection) {
        v03.j(iterable, "<this>");
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            abstractCollection.add(it.next());
        }
    }

    public static List n0(Iterable iterable) {
        ArrayList arrayListO0;
        v03.j(iterable, "<this>");
        boolean z = iterable instanceof Collection;
        if (!z) {
            if (z) {
                arrayListO0 = o0((Collection) iterable);
            } else {
                ArrayList arrayList = new ArrayList();
                m0(iterable, arrayList);
                arrayListO0 = arrayList;
            }
            return v03.F(arrayListO0);
        }
        Collection collection = (Collection) iterable;
        int size = collection.size();
        if (size == 0) {
            return EmptyList.c;
        }
        if (size != 1) {
            return o0(collection);
        }
        return v03.B(iterable instanceof List ? ((List) iterable).get(0) : iterable.iterator().next());
    }

    public static ArrayList o0(Collection collection) {
        v03.j(collection, "<this>");
        return new ArrayList(collection);
    }

    public static Set p0(Collection collection) {
        v03.j(collection, "<this>");
        return new LinkedHashSet(collection);
    }

    public static Set q0(Collection collection) {
        v03.j(collection, "<this>");
        int size = collection.size();
        if (size == 0) {
            return EmptySet.c;
        }
        if (size != 1) {
            LinkedHashSet linkedHashSet = new LinkedHashSet(he1.B(collection.size()));
            m0(collection, linkedHashSet);
            return linkedHashSet;
        }
        Set setSingleton = Collections.singleton(collection instanceof List ? ((List) collection).get(0) : collection.iterator().next());
        v03.i(setSingleton, "singleton(element)");
        return setSingleton;
    }
}
