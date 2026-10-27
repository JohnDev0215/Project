package p;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class mb implements Collection {
    public final /* synthetic */ nb c;

    public mb(nb nbVar) {
        this.c = nbVar;
    }

    @Override // java.util.Collection
    public final boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public final void clear() {
        this.c.clear();
    }

    @Override // java.util.Collection
    public final boolean contains(Object obj) {
        return this.c.a(obj) >= 0;
    }

    @Override // java.util.Collection
    public final boolean containsAll(Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Collection
    public final boolean isEmpty() {
        return this.c.isEmpty();
    }

    @Override // java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return new jb(this.c, 1);
    }

    @Override // java.util.Collection
    public final boolean remove(Object obj) {
        nb nbVar = this.c;
        int iA = nbVar.a(obj);
        if (iA < 0) {
            return false;
        }
        nbVar.g(iA);
        return true;
    }

    @Override // java.util.Collection
    public final boolean removeAll(Collection collection) {
        nb nbVar = this.c;
        int i = nbVar.k;
        int i2 = 0;
        boolean z = false;
        while (i2 < i) {
            if (collection.contains(nbVar.i(i2))) {
                nbVar.g(i2);
                i2--;
                i--;
                z = true;
            }
            i2++;
        }
        return z;
    }

    @Override // java.util.Collection
    public final boolean retainAll(Collection collection) {
        nb nbVar = this.c;
        int i = nbVar.k;
        int i2 = 0;
        boolean z = false;
        while (i2 < i) {
            if (!collection.contains(nbVar.i(i2))) {
                nbVar.g(i2);
                i2--;
                i--;
                z = true;
            }
            i2++;
        }
        return z;
    }

    @Override // java.util.Collection
    public final int size() {
        return this.c.k;
    }

    @Override // java.util.Collection
    public final Object[] toArray() {
        nb nbVar = this.c;
        int i = nbVar.k;
        Object[] objArr = new Object[i];
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = nbVar.i(i2);
        }
        return objArr;
    }

    @Override // java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        nb nbVar = this.c;
        int i = nbVar.k;
        if (objArr.length < i) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), i);
        }
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = nbVar.i(i2);
        }
        if (objArr.length > i) {
            objArr[i] = null;
        }
        return objArr;
    }
}
