package V;

import A7.l;
import A7.m;
import H7.H;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/* JADX INFO: loaded from: classes.dex */
public final class a implements List, B7.c {
    public final d l;

    public a(d dVar) {
        this.l = dVar;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean add(Object obj) {
        this.l.b(obj);
        return true;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean addAll(Collection collection) {
        d dVar = this.l;
        return dVar.e(dVar.f9279n, collection);
    }

    @Override // java.util.List, java.util.Collection
    public final void clear() {
        this.l.g();
    }

    @Override // java.util.List, java.util.Collection
    public final boolean contains(Object obj) {
        return this.l.i(obj);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean containsAll(Collection collection) {
        d dVar = this.l;
        dVar.getClass();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!dVar.i(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.List
    public final Object get(int i7) {
        H.k(i7, this);
        return this.l.l[i7];
    }

    @Override // java.util.List
    public final int indexOf(Object obj) {
        return this.l.k(obj);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean isEmpty() {
        return this.l.l();
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return new c(0, this);
    }

    @Override // java.util.List
    public final int lastIndexOf(Object obj) {
        d dVar = this.l;
        int i7 = dVar.f9279n;
        if (i7 > 0) {
            int i10 = i7 - 1;
            Object[] objArr = dVar.l;
            while (!m.a(obj, objArr[i10])) {
                i10--;
                if (i10 < 0) {
                }
            }
            return i10;
        }
        return -1;
    }

    @Override // java.util.List
    public final ListIterator listIterator() {
        return new c(0, this);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean remove(Object obj) {
        return this.l.n(obj);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean removeAll(Collection collection) {
        d dVar = this.l;
        dVar.getClass();
        if (collection.isEmpty()) {
            return false;
        }
        int i7 = dVar.f9279n;
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            dVar.n(it.next());
        }
        return i7 != dVar.f9279n;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean retainAll(Collection collection) {
        d dVar = this.l;
        int i7 = dVar.f9279n;
        for (int i10 = i7 - 1; -1 < i10; i10--) {
            if (!collection.contains(dVar.l[i10])) {
                dVar.o(i10);
            }
        }
        return i7 != dVar.f9279n;
    }

    @Override // java.util.List
    public final Object set(int i7, Object obj) {
        H.k(i7, this);
        Object[] objArr = this.l.l;
        Object obj2 = objArr[i7];
        objArr[i7] = obj;
        return obj2;
    }

    @Override // java.util.List, java.util.Collection
    public final int size() {
        return this.l.f9279n;
    }

    @Override // java.util.List
    public final List subList(int i7, int i10) {
        H.l(i7, i10, this);
        return new b(i7, i10, this);
    }

    @Override // java.util.List, java.util.Collection
    public final Object[] toArray() {
        return l.a(this);
    }

    @Override // java.util.List
    public final void add(int i7, Object obj) {
        this.l.a(i7, obj);
    }

    @Override // java.util.List
    public final ListIterator listIterator(int i7) {
        return new c(i7, this);
    }

    @Override // java.util.List
    public final Object remove(int i7) {
        H.k(i7, this);
        return this.l.o(i7);
    }

    @Override // java.util.List, java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        return l.b(this, objArr);
    }

    @Override // java.util.List
    public final boolean addAll(int i7, Collection collection) {
        return this.l.e(i7, collection);
    }
}
