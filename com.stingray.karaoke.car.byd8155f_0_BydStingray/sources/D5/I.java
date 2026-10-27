package d5;

import a.AbstractC0904a;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes.dex */
public abstract class I extends AbstractC1075D implements List, RandomAccess {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final C1078G f13384m = new C1078G(d0.f13415p, 0);

    public static d0 A(Long l, Long l10, Long l11, Long l12, Long l13) {
        Object[] objArr = {l, l10, l11, l12, l13};
        AbstractC1095q.d(5, objArr);
        return t(5, objArr);
    }

    public static d0 B(Object obj) {
        Object[] objArr = {obj};
        AbstractC1095q.d(1, objArr);
        return t(1, objArr);
    }

    public static d0 C(Object obj, Object obj2) {
        Object[] objArr = {obj, obj2};
        AbstractC1095q.d(2, objArr);
        return t(2, objArr);
    }

    public static d0 D(b0 b0Var, List list) {
        b0Var.getClass();
        if (list == null) {
            list = AbstractC1095q.q(list.iterator());
        }
        Object[] array = list.toArray();
        AbstractC1095q.d(array.length, array);
        Arrays.sort(array, b0Var);
        return t(array.length, array);
    }

    public static d0 t(int i7, Object[] objArr) {
        return i7 == 0 ? d0.f13415p : new d0(i7, objArr);
    }

    public static C1077F u() {
        return new C1077F(4);
    }

    public static C1077F v(int i7) {
        AbstractC1095q.e(i7, "expectedSize");
        return new C1077F(i7);
    }

    public static I w(Collection collection) {
        if (!(collection instanceof AbstractC1075D)) {
            Object[] array = collection.toArray();
            AbstractC1095q.d(array.length, array);
            return t(array.length, array);
        }
        I iA = ((AbstractC1075D) collection).a();
        if (!iA.r()) {
            return iA;
        }
        Object[] array2 = iA.toArray(AbstractC1075D.l);
        return t(array2.length, array2);
    }

    public static d0 x(Object[] objArr) {
        if (objArr.length == 0) {
            return d0.f13415p;
        }
        Object[] objArr2 = (Object[]) objArr.clone();
        AbstractC1095q.d(objArr2.length, objArr2);
        return t(objArr2.length, objArr2);
    }

    public static d0 z() {
        return d0.f13415p;
    }

    @Override // java.util.List
    /* JADX INFO: renamed from: E, reason: merged with bridge method [inline-methods] */
    public I subList(int i7, int i10) {
        P8.n.s(i7, i10, size());
        int i11 = i10 - i7;
        return i11 == size() ? this : i11 == 0 ? d0.f13415p : new H(this, i7, i11);
    }

    @Override // d5.AbstractC1075D
    public final I a() {
        return this;
    }

    @Override // java.util.List
    public final void add(int i7, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public final boolean addAll(int i7, Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // d5.AbstractC1075D, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // d5.AbstractC1075D
    public int d(int i7, Object[] objArr) {
        int size = size();
        for (int i10 = 0; i10 < size; i10++) {
            objArr[i7 + i10] = get(i10);
        }
        return i7 + size;
    }

    @Override // java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            int size = size();
            if (size == list.size()) {
                if (list instanceof RandomAccess) {
                    for (int i7 = 0; i7 < size; i7++) {
                        if (AbstractC0904a.A(get(i7), list.get(i7))) {
                        }
                    }
                    return true;
                }
                Iterator it = iterator();
                Iterator it2 = list.iterator();
                while (it.hasNext()) {
                    if (it2.hasNext() && AbstractC0904a.A(it.next(), it2.next())) {
                    }
                }
                return !it2.hasNext();
            }
        }
        return false;
    }

    @Override // java.util.Collection, java.util.List
    public final int hashCode() {
        int size = size();
        int i7 = 1;
        for (int i10 = 0; i10 < size; i10++) {
            i7 = ~(~(get(i10).hashCode() + (i7 * 31)));
        }
        return i7;
    }

    @Override // java.util.List
    public final int indexOf(Object obj) {
        if (obj == null) {
            return -1;
        }
        int size = size();
        for (int i7 = 0; i7 < size; i7++) {
            if (obj.equals(get(i7))) {
                return i7;
            }
        }
        return -1;
    }

    @Override // d5.AbstractC1075D, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator iterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    public final int lastIndexOf(Object obj) {
        if (obj == null) {
            return -1;
        }
        for (int size = size() - 1; size >= 0; size--) {
            if (obj.equals(get(size))) {
                return size;
            }
        }
        return -1;
    }

    @Override // java.util.List
    public final Object remove(int i7) {
        throw new UnsupportedOperationException();
    }

    @Override // d5.AbstractC1075D
    /* JADX INFO: renamed from: s */
    public final s0 iterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    public final Object set(int i7, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
    public final C1078G listIterator(int i7) {
        P8.n.r(i7, size());
        return isEmpty() ? f13384m : new C1078G(this, i7);
    }

    public ListIterator listIterator() {
        return listIterator(0);
    }
}
