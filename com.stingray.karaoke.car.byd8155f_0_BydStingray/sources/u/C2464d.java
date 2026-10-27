package u;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: renamed from: u.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2464d implements Collection {
    public final /* synthetic */ C2465e l;

    public C2464d(C2465e c2465e) {
        this.l = c2465e;
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
        this.l.clear();
    }

    @Override // java.util.Collection
    public final boolean contains(Object obj) {
        return this.l.b(obj) >= 0;
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
        return this.l.isEmpty();
    }

    @Override // java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return new C2461a(this.l, 1);
    }

    @Override // java.util.Collection
    public final boolean remove(Object obj) {
        C2465e c2465e = this.l;
        int iB = c2465e.b(obj);
        if (iB < 0) {
            return false;
        }
        c2465e.i(iB);
        return true;
    }

    @Override // java.util.Collection
    public final boolean removeAll(Collection collection) {
        C2465e c2465e = this.l;
        int i7 = c2465e.f22587n;
        int i10 = 0;
        boolean z6 = false;
        while (i10 < i7) {
            if (collection.contains(c2465e.k(i10))) {
                c2465e.i(i10);
                i10--;
                i7--;
                z6 = true;
            }
            i10++;
        }
        return z6;
    }

    @Override // java.util.Collection
    public final boolean retainAll(Collection collection) {
        C2465e c2465e = this.l;
        int i7 = c2465e.f22587n;
        int i10 = 0;
        boolean z6 = false;
        while (i10 < i7) {
            if (!collection.contains(c2465e.k(i10))) {
                c2465e.i(i10);
                i10--;
                i7--;
                z6 = true;
            }
            i10++;
        }
        return z6;
    }

    @Override // java.util.Collection
    public final int size() {
        return this.l.f22587n;
    }

    @Override // java.util.Collection
    public final Object[] toArray() {
        C2465e c2465e = this.l;
        int i7 = c2465e.f22587n;
        Object[] objArr = new Object[i7];
        for (int i10 = 0; i10 < i7; i10++) {
            objArr[i10] = c2465e.k(i10);
        }
        return objArr;
    }

    @Override // java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        C2465e c2465e = this.l;
        int i7 = c2465e.f22587n;
        if (objArr.length < i7) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), i7);
        }
        for (int i10 = 0; i10 < i7; i10++) {
            objArr[i10] = c2465e.k(i10);
        }
        if (objArr.length > i7) {
            objArr[i7] = null;
        }
        return objArr;
    }
}
