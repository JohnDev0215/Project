package o7;

import A7.k;
import A7.m;
import E0.C0167q;
import h6.r;
import h8.AbstractC1386c;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import n7.AbstractC1991f;
import n7.AbstractC1996k;

/* JADX INFO: renamed from: o7.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2049a extends AbstractC1991f implements RandomAccess, Serializable {
    public Object[] l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f20540m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f20541n;
    public final C2049a o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final C2050b f20542p;

    public C2049a(Object[] objArr, int i7, int i10, C2049a c2049a, C2050b c2050b) {
        m.f(objArr, "backing");
        m.f(c2050b, "root");
        this.l = objArr;
        this.f20540m = i7;
        this.f20541n = i10;
        this.o = c2049a;
        this.f20542p = c2050b;
        ((AbstractList) this).modCount = ((AbstractList) c2050b).modCount;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        u();
        t();
        s(this.f20540m + this.f20541n, obj);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        m.f(collection, "elements");
        u();
        t();
        int size = collection.size();
        r(this.f20540m + this.f20541n, collection, size);
        return size > 0;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        u();
        t();
        w(this.f20540m, this.f20541n);
    }

    @Override // n7.AbstractC1991f
    public final int d() {
        t();
        return this.f20541n;
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        t();
        if (obj != this) {
            if (obj instanceof List) {
                if (AbstractC1386c.a(this.l, this.f20540m, this.f20541n, (List) obj)) {
                }
            }
            return false;
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i7) {
        t();
        int i10 = this.f20541n;
        if (i7 < 0 || i7 >= i10) {
            throw new IndexOutOfBoundsException(k.i("index: ", ", size: ", i7, i10));
        }
        return this.l[this.f20540m + i7];
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        t();
        Object[] objArr = this.l;
        int i7 = this.f20541n;
        int iHashCode = 1;
        for (int i10 = 0; i10 < i7; i10++) {
            Object obj = objArr[this.f20540m + i10];
            iHashCode = (iHashCode * 31) + (obj != null ? obj.hashCode() : 0);
        }
        return iHashCode;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        t();
        for (int i7 = 0; i7 < this.f20541n; i7++) {
            if (m.a(this.l[this.f20540m + i7], obj)) {
                return i7;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean isEmpty() {
        t();
        return this.f20541n == 0;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return listIterator(0);
    }

    @Override // n7.AbstractC1991f
    public final Object l(int i7) {
        u();
        t();
        int i10 = this.f20541n;
        if (i7 < 0 || i7 >= i10) {
            throw new IndexOutOfBoundsException(k.i("index: ", ", size: ", i7, i10));
        }
        return v(this.f20540m + i7);
    }

    @Override // java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        t();
        for (int i7 = this.f20541n - 1; i7 >= 0; i7--) {
            if (m.a(this.l[this.f20540m + i7], obj)) {
                return i7;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator() {
        return listIterator(0);
    }

    public final void r(int i7, Collection collection, int i10) {
        ((AbstractList) this).modCount++;
        C2050b c2050b = this.f20542p;
        C2049a c2049a = this.o;
        if (c2049a != null) {
            c2049a.r(i7, collection, i10);
        } else {
            C2050b c2050b2 = C2050b.o;
            c2050b.r(i7, collection, i10);
        }
        this.l = c2050b.l;
        this.f20541n += i10;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        u();
        t();
        int iIndexOf = indexOf(obj);
        if (iIndexOf >= 0) {
            l(iIndexOf);
        }
        return iIndexOf >= 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean removeAll(Collection collection) {
        m.f(collection, "elements");
        u();
        t();
        return x(this.f20540m, this.f20541n, collection, false) > 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean retainAll(Collection collection) {
        m.f(collection, "elements");
        u();
        t();
        return x(this.f20540m, this.f20541n, collection, true) > 0;
    }

    public final void s(int i7, Object obj) {
        ((AbstractList) this).modCount++;
        C2050b c2050b = this.f20542p;
        C2049a c2049a = this.o;
        if (c2049a != null) {
            c2049a.s(i7, obj);
        } else {
            C2050b c2050b2 = C2050b.o;
            c2050b.s(i7, obj);
        }
        this.l = c2050b.l;
        this.f20541n++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i7, Object obj) {
        u();
        t();
        int i10 = this.f20541n;
        if (i7 < 0 || i7 >= i10) {
            throw new IndexOutOfBoundsException(k.i("index: ", ", size: ", i7, i10));
        }
        Object[] objArr = this.l;
        int i11 = this.f20540m;
        Object obj2 = objArr[i11 + i7];
        objArr[i11 + i7] = obj;
        return obj2;
    }

    @Override // java.util.AbstractList, java.util.List
    public final List subList(int i7, int i10) {
        r.c(i7, i10, this.f20541n);
        return new C2049a(this.l, this.f20540m + i7, i10 - i7, this, this.f20542p);
    }

    public final void t() {
        if (((AbstractList) this.f20542p).modCount != ((AbstractList) this).modCount) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray(Object[] objArr) {
        m.f(objArr, "array");
        t();
        int length = objArr.length;
        int i7 = this.f20541n;
        int i10 = this.f20540m;
        if (length < i7) {
            Object[] objArrCopyOfRange = Arrays.copyOfRange(this.l, i10, i7 + i10, objArr.getClass());
            m.e(objArrCopyOfRange, "copyOfRange(...)");
            return objArrCopyOfRange;
        }
        AbstractC1996k.D(0, i10, i7 + i10, this.l, objArr);
        int i11 = this.f20541n;
        if (i11 < objArr.length) {
            objArr[i11] = null;
        }
        return objArr;
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        t();
        return AbstractC1386c.b(this.l, this.f20540m, this.f20541n, this);
    }

    public final void u() {
        if (this.f20542p.f20544n) {
            throw new UnsupportedOperationException();
        }
    }

    public final Object v(int i7) {
        Object objV;
        ((AbstractList) this).modCount++;
        C2049a c2049a = this.o;
        if (c2049a != null) {
            objV = c2049a.v(i7);
        } else {
            C2050b c2050b = C2050b.o;
            objV = this.f20542p.v(i7);
        }
        this.f20541n--;
        return objV;
    }

    public final void w(int i7, int i10) {
        if (i10 > 0) {
            ((AbstractList) this).modCount++;
        }
        C2049a c2049a = this.o;
        if (c2049a != null) {
            c2049a.w(i7, i10);
        } else {
            C2050b c2050b = C2050b.o;
            this.f20542p.w(i7, i10);
        }
        this.f20541n -= i10;
    }

    public final int x(int i7, int i10, Collection collection, boolean z6) {
        int iX;
        C2049a c2049a = this.o;
        if (c2049a != null) {
            iX = c2049a.x(i7, i10, collection, z6);
        } else {
            C2050b c2050b = C2050b.o;
            iX = this.f20542p.x(i7, i10, collection, z6);
        }
        if (iX > 0) {
            ((AbstractList) this).modCount++;
        }
        this.f20541n -= iX;
        return iX;
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i7) {
        t();
        int i10 = this.f20541n;
        if (i7 < 0 || i7 > i10) {
            throw new IndexOutOfBoundsException(k.i("index: ", ", size: ", i7, i10));
        }
        return new C0167q(this, i7);
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i7, Object obj) {
        u();
        t();
        int i10 = this.f20541n;
        if (i7 >= 0 && i7 <= i10) {
            s(this.f20540m + i7, obj);
            return;
        }
        throw new IndexOutOfBoundsException(k.i("index: ", ", size: ", i7, i10));
    }

    @Override // java.util.AbstractList, java.util.List
    public final boolean addAll(int i7, Collection collection) {
        m.f(collection, "elements");
        u();
        t();
        int i10 = this.f20541n;
        if (i7 >= 0 && i7 <= i10) {
            int size = collection.size();
            r(this.f20540m + i7, collection, size);
            return size > 0;
        }
        throw new IndexOutOfBoundsException(k.i("index: ", ", size: ", i7, i10));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray() {
        t();
        Object[] objArr = this.l;
        int i7 = this.f20541n;
        int i10 = this.f20540m;
        return AbstractC1996k.I(objArr, i10, i7 + i10);
    }
}
