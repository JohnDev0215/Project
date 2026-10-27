package u;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: renamed from: u.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2462b implements Set {
    public final /* synthetic */ C2465e l;

    public C2462b(C2465e c2465e) {
        this.l = c2465e;
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Set, java.util.Collection
    public final void clear() {
        this.l.clear();
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean contains(Object obj) {
        return this.l.containsKey(obj);
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean containsAll(Collection collection) {
        return this.l.l(collection);
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean equals(Object obj) {
        C2465e c2465e = this.l;
        if (this == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            try {
                if (c2465e.f22587n == set.size()) {
                    if (c2465e.l(set)) {
                        return true;
                    }
                }
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    @Override // java.util.Set, java.util.Collection
    public final int hashCode() {
        C2465e c2465e = this.l;
        int iHashCode = 0;
        for (int i7 = c2465e.f22587n - 1; i7 >= 0; i7--) {
            Object objG = c2465e.g(i7);
            iHashCode += objG == null ? 0 : objG.hashCode();
        }
        return iHashCode;
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean isEmpty() {
        return this.l.isEmpty();
    }

    @Override // java.util.Set, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return new C2461a(this.l, 0);
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean remove(Object obj) {
        C2465e c2465e = this.l;
        int iE = c2465e.e(obj);
        if (iE < 0) {
            return false;
        }
        c2465e.i(iE);
        return true;
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean removeAll(Collection collection) {
        return this.l.n(collection);
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean retainAll(Collection collection) {
        C2465e c2465e = this.l;
        int i7 = c2465e.f22587n;
        for (int i10 = i7 - 1; i10 >= 0; i10--) {
            if (!collection.contains(c2465e.g(i10))) {
                c2465e.i(i10);
            }
        }
        return i7 != c2465e.f22587n;
    }

    @Override // java.util.Set, java.util.Collection
    public final int size() {
        return this.l.f22587n;
    }

    @Override // java.util.Set, java.util.Collection
    public final Object[] toArray() {
        C2465e c2465e = this.l;
        int i7 = c2465e.f22587n;
        Object[] objArr = new Object[i7];
        for (int i10 = 0; i10 < i7; i10++) {
            objArr[i10] = c2465e.g(i10);
        }
        return objArr;
    }

    @Override // java.util.Set, java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        C2465e c2465e = this.l;
        int i7 = c2465e.f22587n;
        if (objArr.length < i7) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), i7);
        }
        for (int i10 = 0; i10 < i7; i10++) {
            objArr[i10] = c2465e.g(i10);
        }
        if (objArr.length > i7) {
            objArr[i7] = null;
        }
        return objArr;
    }
}
