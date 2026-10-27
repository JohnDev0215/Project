package E0;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.UnaryOperator;

/* JADX INFO: loaded from: classes.dex */
public final class r implements List, B7.a {
    public final int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f1695m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C0168s f1696n;

    public r(C0168s c0168s, int i7, int i10) {
        this.f1696n = c0168s;
        this.l = i7;
        this.f1695m = i10;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ void add(int i7, Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public final boolean addAll(int i7, Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final boolean contains(Object obj) {
        return (obj instanceof g0.n) && indexOf((g0.n) obj) != -1;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean containsAll(Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!contains((g0.n) it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.List
    public final Object get(int i7) {
        Object obj = this.f1696n.l[i7 + this.l];
        A7.m.d(obj, "null cannot be cast to non-null type androidx.compose.ui.Modifier.Node");
        return (g0.n) obj;
    }

    @Override // java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof g0.n)) {
            return -1;
        }
        g0.n nVar = (g0.n) obj;
        int i7 = this.l;
        int i10 = this.f1695m;
        if (i7 > i10) {
            return -1;
        }
        int i11 = i7;
        while (!A7.m.a(this.f1696n.l[i11], nVar)) {
            if (i11 == i10) {
                return -1;
            }
            i11++;
        }
        return i11 - i7;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        int i7 = this.l;
        return new C0167q(this.f1696n, i7, i7, this.f1695m);
    }

    @Override // java.util.List
    public final int lastIndexOf(Object obj) {
        if (!(obj instanceof g0.n)) {
            return -1;
        }
        g0.n nVar = (g0.n) obj;
        int i7 = this.f1695m;
        int i10 = this.l;
        if (i10 > i7) {
            return -1;
        }
        while (!A7.m.a(this.f1696n.l[i7], nVar)) {
            if (i7 == i10) {
                return -1;
            }
            i7--;
        }
        return i7 - i10;
    }

    @Override // java.util.List
    public final ListIterator listIterator() {
        int i7 = this.l;
        return new C0167q(this.f1696n, i7, i7, this.f1695m);
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object remove(int i7) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public final void replaceAll(UnaryOperator unaryOperator) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object set(int i7, Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final int size() {
        return this.f1695m - this.l;
    }

    @Override // java.util.List
    public final void sort(Comparator comparator) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public final List subList(int i7, int i10) {
        int i11 = this.l;
        return new r(this.f1696n, i7 + i11, i11 + i10);
    }

    @Override // java.util.List, java.util.Collection
    public final Object[] toArray() {
        return A7.l.a(this);
    }

    @Override // java.util.List, java.util.Collection
    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public final ListIterator listIterator(int i7) {
        int i10 = this.l;
        int i11 = this.f1695m;
        return new C0167q(this.f1696n, i7 + i10, i10, i11);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        return A7.l.b(this, objArr);
    }
}
