package u;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Set;
import n7.AbstractC1996k;
import n7.AbstractC1998m;
import v.AbstractC2530a;

/* JADX INFO: renamed from: u.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2466f implements Collection, Set, B7.b, B7.f {
    public int[] l = AbstractC2530a.f22938a;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object[] f22598m = AbstractC2530a.c;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f22599n;

    public C2466f(Object obj) {
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean add(Object obj) {
        int i7;
        int iD;
        int i10 = this.f22599n;
        if (obj == null) {
            iD = AbstractC2473m.d(this, null, 0);
            i7 = 0;
        } else {
            int iHashCode = obj.hashCode();
            i7 = iHashCode;
            iD = AbstractC2473m.d(this, obj, iHashCode);
        }
        if (iD >= 0) {
            return false;
        }
        int i11 = ~iD;
        int[] iArr = this.l;
        if (i10 >= iArr.length) {
            int i12 = 8;
            if (i10 >= 8) {
                i12 = (i10 >> 1) + i10;
            } else if (i10 < 4) {
                i12 = 4;
            }
            Object[] objArr = this.f22598m;
            AbstractC2473m.b(this, i12);
            if (i10 != this.f22599n) {
                throw new ConcurrentModificationException();
            }
            int[] iArr2 = this.l;
            if (iArr2.length != 0) {
                AbstractC1996k.F(0, iArr.length, 6, iArr, iArr2);
                AbstractC1996k.G(0, objArr.length, 6, objArr, this.f22598m);
            }
        }
        if (i11 < i10) {
            int[] iArr3 = this.l;
            int i13 = i11 + 1;
            AbstractC1996k.C(i13, i11, i10, iArr3, iArr3);
            Object[] objArr2 = this.f22598m;
            AbstractC1996k.D(i13, i11, i10, objArr2, objArr2);
        }
        int i14 = this.f22599n;
        if (i10 == i14) {
            int[] iArr4 = this.l;
            if (i11 < iArr4.length) {
                iArr4[i11] = i7;
                this.f22598m[i11] = obj;
                this.f22599n = i14 + 1;
                return true;
            }
        }
        throw new ConcurrentModificationException();
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean addAll(Collection collection) {
        A7.m.f(collection, "elements");
        int size = collection.size() + this.f22599n;
        int i7 = this.f22599n;
        int[] iArr = this.l;
        boolean zAdd = false;
        if (iArr.length < size) {
            Object[] objArr = this.f22598m;
            AbstractC2473m.b(this, size);
            int i10 = this.f22599n;
            if (i10 > 0) {
                AbstractC1996k.F(0, i10, 6, iArr, this.l);
                AbstractC1996k.G(0, this.f22599n, 6, objArr, this.f22598m);
            }
        }
        if (this.f22599n != i7) {
            throw new ConcurrentModificationException();
        }
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            zAdd |= add(it.next());
        }
        return zAdd;
    }

    @Override // java.util.Collection, java.util.Set
    public final void clear() {
        if (this.f22599n != 0) {
            this.l = AbstractC2530a.f22938a;
            this.f22598m = AbstractC2530a.c;
            this.f22599n = 0;
        }
        if (this.f22599n != 0) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        return (obj == null ? AbstractC2473m.d(this, null, 0) : AbstractC2473m.d(this, obj, obj.hashCode())) >= 0;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean containsAll(Collection collection) {
        A7.m.f(collection, "elements");
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    public final Object d(int i7) {
        int i10 = this.f22599n;
        Object[] objArr = this.f22598m;
        Object obj = objArr[i7];
        if (i10 <= 1) {
            clear();
        } else {
            int i11 = i10 - 1;
            int[] iArr = this.l;
            if (iArr.length <= 8 || i10 >= iArr.length / 3) {
                if (i7 < i11) {
                    int i12 = i7 + 1;
                    AbstractC1996k.C(i7, i12, i10, iArr, iArr);
                    Object[] objArr2 = this.f22598m;
                    AbstractC1996k.D(i7, i12, i10, objArr2, objArr2);
                }
                this.f22598m[i11] = null;
            } else {
                AbstractC2473m.b(this, i10 > 8 ? i10 + (i10 >> 1) : 8);
                if (i7 > 0) {
                    AbstractC1996k.F(0, i7, 6, iArr, this.l);
                    AbstractC1996k.G(0, i7, 6, objArr, this.f22598m);
                }
                if (i7 < i11) {
                    int i13 = i7 + 1;
                    AbstractC1996k.C(i7, i13, i10, iArr, this.l);
                    AbstractC1996k.D(i7, i13, i10, objArr, this.f22598m);
                }
            }
            if (i10 != this.f22599n) {
                throw new ConcurrentModificationException();
            }
            this.f22599n = i11;
        }
        return obj;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof Set) && this.f22599n == ((Set) obj).size()) {
            try {
                int i7 = this.f22599n;
                for (int i10 = 0; i10 < i7; i10++) {
                    if (((Set) obj).contains(this.f22598m[i10])) {
                    }
                }
                return true;
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public final int hashCode() {
        int[] iArr = this.l;
        int i7 = this.f22599n;
        int i10 = 0;
        for (int i11 = 0; i11 < i7; i11++) {
            i10 += iArr[i11];
        }
        return i10;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        return this.f22599n <= 0;
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new C2461a(this);
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        int iD = obj == null ? AbstractC2473m.d(this, null, 0) : AbstractC2473m.d(this, obj, obj.hashCode());
        if (iD < 0) {
            return false;
        }
        d(iD);
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean removeAll(Collection collection) {
        A7.m.f(collection, "elements");
        Iterator it = collection.iterator();
        boolean zRemove = false;
        while (it.hasNext()) {
            zRemove |= remove(it.next());
        }
        return zRemove;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean retainAll(Collection collection) {
        A7.m.f(collection, "elements");
        boolean z6 = false;
        for (int i7 = this.f22599n - 1; -1 < i7; i7--) {
            if (!AbstractC1998m.T(collection, this.f22598m[i7])) {
                d(i7);
                z6 = true;
            }
        }
        return z6;
    }

    @Override // java.util.Collection, java.util.Set
    public final int size() {
        return this.f22599n;
    }

    @Override // java.util.Collection, java.util.Set
    public final Object[] toArray() {
        return AbstractC1996k.I(this.f22598m, 0, this.f22599n);
    }

    public final String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f22599n * 14);
        sb.append('{');
        int i7 = this.f22599n;
        for (int i10 = 0; i10 < i7; i10++) {
            if (i10 > 0) {
                sb.append(", ");
            }
            Object obj = this.f22598m[i10];
            if (obj != this) {
                sb.append(obj);
            } else {
                sb.append("(this Set)");
            }
        }
        sb.append('}');
        String string = sb.toString();
        A7.m.e(string, "StringBuilder(capacity).…builderAction).toString()");
        return string;
    }

    @Override // java.util.Collection, java.util.Set
    public final Object[] toArray(Object[] objArr) {
        A7.m.f(objArr, "array");
        int i7 = this.f22599n;
        if (objArr.length < i7) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), i7);
        } else if (objArr.length > i7) {
            objArr[i7] = null;
        }
        AbstractC1996k.D(0, 0, this.f22599n, this.f22598m, objArr);
        return objArr;
    }
}
