package p;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class qb implements Collection, Set, h81 {
    public int[] c = zu.d;
    public Object[] e = zu.e;
    public int k;

    public qb(int i) {
        if (i > 0) {
            l13.a(this, i);
        }
    }

    public final Object a(int i) {
        int i2 = this.k;
        Object[] objArr = this.e;
        Object obj = objArr[i];
        if (i2 <= 1) {
            clear();
        } else {
            int i3 = i2 - 1;
            int[] iArr = this.c;
            if (iArr.length <= 8 || i2 >= iArr.length / 3) {
                if (i < i3) {
                    int i4 = i + 1;
                    rb.j0(i, i4, i2, iArr, iArr);
                    Object[] objArr2 = this.e;
                    rb.k0(i, i4, i2, objArr2, objArr2);
                }
                this.e[i3] = null;
            } else {
                l13.a(this, i2 > 8 ? i2 + (i2 >> 1) : 8);
                if (i > 0) {
                    rb.j0(0, 0, i, iArr, this.c);
                    rb.l0(objArr, this.e, 0, i, 6);
                }
                if (i < i3) {
                    int i5 = i + 1;
                    rb.j0(i, i5, i2, iArr, this.c);
                    rb.k0(i, i5, i2, objArr, this.e);
                }
            }
            if (i2 != this.k) {
                throw new ConcurrentModificationException();
            }
            this.k = i3;
        }
        return obj;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean add(Object obj) {
        int i;
        int iG;
        int i2 = this.k;
        if (obj == null) {
            iG = l13.g(this, null, 0);
            i = 0;
        } else {
            int iHashCode = obj.hashCode();
            i = iHashCode;
            iG = l13.g(this, obj, iHashCode);
        }
        if (iG >= 0) {
            return false;
        }
        int i3 = ~iG;
        int[] iArr = this.c;
        if (i2 >= iArr.length) {
            int i4 = 8;
            if (i2 >= 8) {
                i4 = (i2 >> 1) + i2;
            } else if (i2 < 4) {
                i4 = 4;
            }
            Object[] objArr = this.e;
            l13.a(this, i4);
            if (i2 != this.k) {
                throw new ConcurrentModificationException();
            }
            int[] iArr2 = this.c;
            if (!(iArr2.length == 0)) {
                rb.j0(0, 0, iArr.length, iArr, iArr2);
                rb.l0(objArr, this.e, 0, objArr.length, 6);
            }
        }
        if (i3 < i2) {
            int[] iArr3 = this.c;
            int i5 = i3 + 1;
            rb.j0(i5, i3, i2, iArr3, iArr3);
            Object[] objArr2 = this.e;
            rb.k0(i5, i3, i2, objArr2, objArr2);
        }
        int i6 = this.k;
        if (i2 == i6) {
            int[] iArr4 = this.c;
            if (i3 < iArr4.length) {
                iArr4[i3] = i;
                this.e[i3] = obj;
                this.k = i6 + 1;
                return true;
            }
        }
        throw new ConcurrentModificationException();
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean addAll(Collection collection) {
        v03.j(collection, "elements");
        int size = collection.size() + this.k;
        int i = this.k;
        int[] iArr = this.c;
        boolean zAdd = false;
        if (iArr.length < size) {
            Object[] objArr = this.e;
            l13.a(this, size);
            int i2 = this.k;
            if (i2 > 0) {
                rb.j0(0, 0, i2, iArr, this.c);
                rb.l0(objArr, this.e, 0, this.k, 6);
            }
        }
        if (this.k != i) {
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
        if (this.k != 0) {
            this.c = zu.d;
            this.e = zu.e;
            this.k = 0;
        }
        if (this.k != 0) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        return (obj == null ? l13.g(this, null, 0) : l13.g(this, obj, obj.hashCode())) >= 0;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean containsAll(Collection collection) {
        v03.j(collection, "elements");
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof Set) && this.k == ((Set) obj).size()) {
            try {
                int i = this.k;
                for (int i2 = 0; i2 < i; i2++) {
                    if (((Set) obj).contains(this.e[i2])) {
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
        int[] iArr = this.c;
        int i = this.k;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 += iArr[i3];
        }
        return i2;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        return this.k <= 0;
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new jb(this);
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        int iG = obj == null ? l13.g(this, null, 0) : l13.g(this, obj, obj.hashCode());
        if (iG < 0) {
            return false;
        }
        a(iG);
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean removeAll(Collection collection) {
        v03.j(collection, "elements");
        Iterator it = collection.iterator();
        boolean zRemove = false;
        while (it.hasNext()) {
            zRemove |= remove(it.next());
        }
        return zRemove;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean retainAll(Collection collection) {
        v03.j(collection, "elements");
        boolean z = false;
        for (int i = this.k - 1; -1 < i; i--) {
            if (!ou.Z(collection, this.e[i])) {
                a(i);
                z = true;
            }
        }
        return z;
    }

    @Override // java.util.Collection, java.util.Set
    public final int size() {
        return this.k;
    }

    @Override // java.util.Collection, java.util.Set
    public final Object[] toArray(Object[] objArr) {
        v03.j(objArr, "array");
        int i = this.k;
        if (objArr.length < i) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), i);
        } else if (objArr.length > i) {
            objArr[i] = null;
        }
        rb.k0(0, 0, this.k, this.e, objArr);
        return objArr;
    }

    public final String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.k * 14);
        sb.append('{');
        int i = this.k;
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            Object obj = this.e[i2];
            if (obj != this) {
                sb.append(obj);
            } else {
                sb.append("(this Set)");
            }
        }
        sb.append('}');
        String string = sb.toString();
        v03.i(string, "StringBuilder(capacity).…builderAction).toString()");
        return string;
    }

    @Override // java.util.Collection, java.util.Set
    public final Object[] toArray() {
        return rb.n0(0, this.k, this.e);
    }
}
