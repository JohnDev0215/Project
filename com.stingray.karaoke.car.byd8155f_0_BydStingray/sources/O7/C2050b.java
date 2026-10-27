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
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import n7.AbstractC1991f;
import n7.AbstractC1996k;

/* JADX INFO: renamed from: o7.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2050b extends AbstractC1991f implements RandomAccess, Serializable {
    public static final C2050b o;
    public Object[] l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f20543m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f20544n;

    static {
        C2050b c2050b = new C2050b(0);
        c2050b.f20544n = true;
        o = c2050b;
    }

    public C2050b(int i7) {
        if (i7 < 0) {
            throw new IllegalArgumentException("capacity must be non-negative.");
        }
        this.l = new Object[i7];
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        t();
        int i7 = this.f20543m;
        ((AbstractList) this).modCount++;
        u(i7, 1);
        this.l[i7] = obj;
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        m.f(collection, "elements");
        t();
        int size = collection.size();
        r(this.f20543m, collection, size);
        return size > 0;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        t();
        w(0, this.f20543m);
    }

    @Override // n7.AbstractC1991f
    public final int d() {
        return this.f20543m;
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (obj != this) {
            if (!(obj instanceof List)) {
                return false;
            }
            if (!AbstractC1386c.a(this.l, 0, this.f20543m, (List) obj)) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i7) {
        int i10 = this.f20543m;
        if (i7 < 0 || i7 >= i10) {
            throw new IndexOutOfBoundsException(k.i("index: ", ", size: ", i7, i10));
        }
        return this.l[i7];
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        Object[] objArr = this.l;
        int i7 = this.f20543m;
        int iHashCode = 1;
        for (int i10 = 0; i10 < i7; i10++) {
            Object obj = objArr[i10];
            iHashCode = (iHashCode * 31) + (obj != null ? obj.hashCode() : 0);
        }
        return iHashCode;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        for (int i7 = 0; i7 < this.f20543m; i7++) {
            if (m.a(this.l[i7], obj)) {
                return i7;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean isEmpty() {
        return this.f20543m == 0;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return listIterator(0);
    }

    @Override // n7.AbstractC1991f
    public final Object l(int i7) {
        t();
        int i10 = this.f20543m;
        if (i7 < 0 || i7 >= i10) {
            throw new IndexOutOfBoundsException(k.i("index: ", ", size: ", i7, i10));
        }
        return v(i7);
    }

    @Override // java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        for (int i7 = this.f20543m - 1; i7 >= 0; i7--) {
            if (m.a(this.l[i7], obj)) {
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
        u(i7, i10);
        Iterator it = collection.iterator();
        for (int i11 = 0; i11 < i10; i11++) {
            this.l[i7 + i11] = it.next();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
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
        t();
        return x(0, this.f20543m, collection, false) > 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean retainAll(Collection collection) {
        m.f(collection, "elements");
        t();
        return x(0, this.f20543m, collection, true) > 0;
    }

    public final void s(int i7, Object obj) {
        ((AbstractList) this).modCount++;
        u(i7, 1);
        this.l[i7] = obj;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i7, Object obj) {
        t();
        int i10 = this.f20543m;
        if (i7 < 0 || i7 >= i10) {
            throw new IndexOutOfBoundsException(k.i("index: ", ", size: ", i7, i10));
        }
        Object[] objArr = this.l;
        Object obj2 = objArr[i7];
        objArr[i7] = obj;
        return obj2;
    }

    @Override // java.util.AbstractList, java.util.List
    public final List subList(int i7, int i10) {
        r.c(i7, i10, this.f20543m);
        return new C2049a(this.l, i7, i10 - i7, null, this);
    }

    public final void t() {
        if (this.f20544n) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray(Object[] objArr) {
        m.f(objArr, "array");
        int length = objArr.length;
        int i7 = this.f20543m;
        if (length < i7) {
            Object[] objArrCopyOfRange = Arrays.copyOfRange(this.l, 0, i7, objArr.getClass());
            m.e(objArrCopyOfRange, "copyOfRange(...)");
            return objArrCopyOfRange;
        }
        AbstractC1996k.D(0, 0, i7, this.l, objArr);
        int i10 = this.f20543m;
        if (i10 < objArr.length) {
            objArr[i10] = null;
        }
        return objArr;
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        return AbstractC1386c.b(this.l, 0, this.f20543m, this);
    }

    public final void u(int i7, int i10) {
        int i11 = this.f20543m + i10;
        if (i11 < 0) {
            throw new OutOfMemoryError();
        }
        Object[] objArr = this.l;
        if (i11 > objArr.length) {
            int length = objArr.length;
            int i12 = length + (length >> 1);
            if (i12 - i11 < 0) {
                i12 = i11;
            }
            if (i12 - 2147483639 > 0) {
                i12 = i11 > 2147483639 ? Integer.MAX_VALUE : 2147483639;
            }
            Object[] objArrCopyOf = Arrays.copyOf(objArr, i12);
            m.e(objArrCopyOf, "copyOf(...)");
            this.l = objArrCopyOf;
        }
        Object[] objArr2 = this.l;
        AbstractC1996k.D(i7 + i10, i7, this.f20543m, objArr2, objArr2);
        this.f20543m += i10;
    }

    public final Object v(int i7) {
        ((AbstractList) this).modCount++;
        Object[] objArr = this.l;
        Object obj = objArr[i7];
        AbstractC1996k.D(i7, i7 + 1, this.f20543m, objArr, objArr);
        Object[] objArr2 = this.l;
        int i10 = this.f20543m - 1;
        m.f(objArr2, "<this>");
        objArr2[i10] = null;
        this.f20543m--;
        return obj;
    }

    public final void w(int i7, int i10) {
        if (i10 > 0) {
            ((AbstractList) this).modCount++;
        }
        Object[] objArr = this.l;
        AbstractC1996k.D(i7, i7 + i10, this.f20543m, objArr, objArr);
        Object[] objArr2 = this.l;
        int i11 = this.f20543m;
        AbstractC1386c.r(objArr2, i11 - i10, i11);
        this.f20543m -= i10;
    }

    public final int x(int i7, int i10, Collection collection, boolean z6) {
        int i11 = 0;
        int i12 = 0;
        while (i11 < i10) {
            int i13 = i7 + i11;
            if (collection.contains(this.l[i13]) == z6) {
                Object[] objArr = this.l;
                i11++;
                objArr[i12 + i7] = objArr[i13];
                i12++;
            } else {
                i11++;
            }
        }
        int i14 = i10 - i12;
        Object[] objArr2 = this.l;
        AbstractC1996k.D(i7 + i12, i10 + i7, this.f20543m, objArr2, objArr2);
        Object[] objArr3 = this.l;
        int i15 = this.f20543m;
        AbstractC1386c.r(objArr3, i15 - i14, i15);
        if (i14 > 0) {
            ((AbstractList) this).modCount++;
        }
        this.f20543m -= i14;
        return i14;
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i7) {
        int i10 = this.f20543m;
        if (i7 < 0 || i7 > i10) {
            throw new IndexOutOfBoundsException(k.i("index: ", ", size: ", i7, i10));
        }
        return new C0167q(this, i7);
    }

    @Override // java.util.AbstractList, java.util.List
    public final boolean addAll(int i7, Collection collection) {
        m.f(collection, "elements");
        t();
        int i10 = this.f20543m;
        if (i7 >= 0 && i7 <= i10) {
            int size = collection.size();
            r(i7, collection, size);
            return size > 0;
        }
        throw new IndexOutOfBoundsException(k.i("index: ", ", size: ", i7, i10));
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i7, Object obj) {
        t();
        int i10 = this.f20543m;
        if (i7 >= 0 && i7 <= i10) {
            ((AbstractList) this).modCount++;
            u(i7, 1);
            this.l[i7] = obj;
            return;
        }
        throw new IndexOutOfBoundsException(k.i("index: ", ", size: ", i7, i10));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray() {
        return AbstractC1996k.I(this.l, 0, this.f20543m);
    }
}
