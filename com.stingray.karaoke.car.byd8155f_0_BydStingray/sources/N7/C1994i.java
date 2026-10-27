package n7;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: renamed from: n7.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1994i implements Collection, B7.a {
    public final Object[] l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final boolean f20156m;

    public C1994i(Object[] objArr, boolean z6) {
        A7.m.f(objArr, "values");
        this.l = objArr;
        this.f20156m = z6;
    }

    @Override // java.util.Collection
    public final boolean add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final boolean contains(Object obj) {
        return AbstractC1996k.z(obj, this.l);
    }

    @Override // java.util.Collection
    public final boolean containsAll(Collection collection) {
        A7.m.f(collection, "elements");
        Collection collection2 = collection;
        if (collection2.isEmpty()) {
            return true;
        }
        Iterator it = collection2.iterator();
        while (it.hasNext()) {
            if (!AbstractC1996k.z(it.next(), this.l)) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Collection
    public final boolean isEmpty() {
        return this.l.length == 0;
    }

    @Override // java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return A7.m.h(this.l);
    }

    @Override // java.util.Collection
    public final boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final int size() {
        return this.l.length;
    }

    @Override // java.util.Collection
    public final Object[] toArray() {
        Object[] objArr = this.l;
        A7.m.f(objArr, "<this>");
        if (this.f20156m && objArr.getClass().equals(Object[].class)) {
            return objArr;
        }
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length, Object[].class);
        A7.m.e(objArrCopyOf, "copyOf(...)");
        return objArrCopyOf;
    }

    @Override // java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        A7.m.f(objArr, "array");
        return A7.l.b(this, objArr);
    }
}
