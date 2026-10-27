package V;

import A7.l;
import A7.m;
import H7.H;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/* JADX INFO: loaded from: classes.dex */
public final class b implements List, B7.c {
    public final Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f9275m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f9276n;

    public b(int i7, int i10, List list) {
        this.l = list;
        this.f9275m = i7;
        this.f9276n = i10;
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object, java.util.List] */
    @Override // java.util.List, java.util.Collection
    public final boolean add(Object obj) {
        int i7 = this.f9276n;
        this.f9276n = i7 + 1;
        this.l.add(i7, obj);
        return true;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, java.util.List] */
    @Override // java.util.List
    public final boolean addAll(int i7, Collection collection) {
        this.l.addAll(i7 + this.f9275m, collection);
        this.f9276n = collection.size() + this.f9276n;
        return collection.size() > 0;
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.Object, java.util.List] */
    @Override // java.util.List, java.util.Collection
    public final void clear() {
        int i7 = this.f9276n - 1;
        int i10 = this.f9275m;
        if (i10 <= i7) {
            while (true) {
                this.l.remove(i7);
                if (i7 == i10) {
                    break;
                } else {
                    i7--;
                }
            }
        }
        this.f9276n = i10;
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.Object, java.util.List] */
    @Override // java.util.List, java.util.Collection
    public final boolean contains(Object obj) {
        int i7 = this.f9276n;
        for (int i10 = this.f9275m; i10 < i7; i10++) {
            if (m.a(this.l.get(i10), obj)) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean containsAll(Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object, java.util.List] */
    @Override // java.util.List
    public final Object get(int i7) {
        H.k(i7, this);
        return this.l.get(i7 + this.f9275m);
    }

    /* JADX WARN: Type inference failed for: r3v0, types: [java.lang.Object, java.util.List] */
    @Override // java.util.List
    public final int indexOf(Object obj) {
        int i7 = this.f9276n;
        int i10 = this.f9275m;
        for (int i11 = i10; i11 < i7; i11++) {
            if (m.a(this.l.get(i11), obj)) {
                return i11 - i10;
            }
        }
        return -1;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean isEmpty() {
        return this.f9276n == this.f9275m;
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return new c(0, this);
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.Object, java.util.List] */
    @Override // java.util.List
    public final int lastIndexOf(Object obj) {
        int i7 = this.f9276n - 1;
        int i10 = this.f9275m;
        if (i10 > i7) {
            return -1;
        }
        while (!m.a(this.l.get(i7), obj)) {
            if (i7 == i10) {
                return -1;
            }
            i7--;
        }
        return i7 - i10;
    }

    @Override // java.util.List
    public final ListIterator listIterator() {
        return new c(0, this);
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.Object, java.util.List] */
    @Override // java.util.List, java.util.Collection
    public final boolean remove(Object obj) {
        int i7 = this.f9276n;
        for (int i10 = this.f9275m; i10 < i7; i10++) {
            ?? r22 = this.l;
            if (m.a(r22.get(i10), obj)) {
                r22.remove(i10);
                this.f9276n--;
                return true;
            }
        }
        return false;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean removeAll(Collection collection) {
        int i7 = this.f9276n;
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            remove(it.next());
        }
        return i7 != this.f9276n;
    }

    /* JADX WARN: Type inference failed for: r3v0, types: [java.lang.Object, java.util.List] */
    @Override // java.util.List, java.util.Collection
    public final boolean retainAll(Collection collection) {
        int i7 = this.f9276n;
        int i10 = i7 - 1;
        int i11 = this.f9275m;
        if (i11 <= i10) {
            while (true) {
                ?? r32 = this.l;
                if (!collection.contains(r32.get(i10))) {
                    r32.remove(i10);
                    this.f9276n--;
                }
                if (i10 == i11) {
                    break;
                }
                i10--;
            }
        }
        return i7 != this.f9276n;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object, java.util.List] */
    @Override // java.util.List
    public final Object set(int i7, Object obj) {
        H.k(i7, this);
        return this.l.set(i7 + this.f9275m, obj);
    }

    @Override // java.util.List, java.util.Collection
    public final int size() {
        return this.f9276n - this.f9275m;
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

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, java.util.List] */
    @Override // java.util.List
    public final void add(int i7, Object obj) {
        this.l.add(i7 + this.f9275m, obj);
        this.f9276n++;
    }

    @Override // java.util.List
    public final ListIterator listIterator(int i7) {
        return new c(i7, this);
    }

    @Override // java.util.List, java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        return l.b(this, objArr);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, java.util.List] */
    @Override // java.util.List, java.util.Collection
    public final boolean addAll(Collection collection) {
        this.l.addAll(this.f9276n, collection);
        this.f9276n = collection.size() + this.f9276n;
        return collection.size() > 0;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, java.util.List] */
    @Override // java.util.List
    public final Object remove(int i7) {
        H.k(i7, this);
        this.f9276n--;
        return this.l.remove(i7 + this.f9275m);
    }
}
