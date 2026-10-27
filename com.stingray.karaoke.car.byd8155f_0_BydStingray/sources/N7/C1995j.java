package n7;

import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: renamed from: n7.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1995j extends AbstractC1991f {
    public static final Object[] o = new Object[0];
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object[] f20157m = o;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f20158n;

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i7, Object obj) {
        int i10 = this.f20158n;
        if (i7 < 0 || i7 > i10) {
            throw new IndexOutOfBoundsException(A7.k.i("index: ", ", size: ", i7, i10));
        }
        if (i7 == i10) {
            addLast(obj);
            return;
        }
        if (i7 == 0) {
            addFirst(obj);
            return;
        }
        x();
        r(this.f20158n + 1);
        int iW = w(this.l + i7);
        int i11 = this.f20158n;
        if (i7 < ((i11 + 1) >> 1)) {
            int iQ = iW == 0 ? AbstractC1996k.Q(this.f20157m) : iW - 1;
            int i12 = this.l;
            int iQ2 = i12 == 0 ? AbstractC1996k.Q(this.f20157m) : i12 - 1;
            int i13 = this.l;
            if (iQ >= i13) {
                Object[] objArr = this.f20157m;
                objArr[iQ2] = objArr[i13];
                AbstractC1996k.D(i13, i13 + 1, iQ + 1, objArr, objArr);
            } else {
                Object[] objArr2 = this.f20157m;
                AbstractC1996k.D(i13 - 1, i13, objArr2.length, objArr2, objArr2);
                Object[] objArr3 = this.f20157m;
                objArr3[objArr3.length - 1] = objArr3[0];
                AbstractC1996k.D(0, 1, iQ + 1, objArr3, objArr3);
            }
            this.f20157m[iQ] = obj;
            this.l = iQ2;
        } else {
            int iW2 = w(i11 + this.l);
            if (iW < iW2) {
                Object[] objArr4 = this.f20157m;
                AbstractC1996k.D(iW + 1, iW, iW2, objArr4, objArr4);
            } else {
                Object[] objArr5 = this.f20157m;
                AbstractC1996k.D(1, 0, iW2, objArr5, objArr5);
                Object[] objArr6 = this.f20157m;
                objArr6[0] = objArr6[objArr6.length - 1];
                AbstractC1996k.D(iW + 1, iW, objArr6.length - 1, objArr6, objArr6);
            }
            this.f20157m[iW] = obj;
        }
        this.f20158n++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final boolean addAll(int i7, Collection collection) {
        A7.m.f(collection, "elements");
        int i10 = this.f20158n;
        if (i7 < 0 || i7 > i10) {
            throw new IndexOutOfBoundsException(A7.k.i("index: ", ", size: ", i7, i10));
        }
        if (collection.isEmpty()) {
            return false;
        }
        if (i7 == this.f20158n) {
            return addAll(collection);
        }
        x();
        r(collection.size() + this.f20158n);
        int iW = w(this.f20158n + this.l);
        int iW2 = w(this.l + i7);
        int size = collection.size();
        if (i7 < ((this.f20158n + 1) >> 1)) {
            int i11 = this.l;
            int length = i11 - size;
            if (iW2 < i11) {
                Object[] objArr = this.f20157m;
                AbstractC1996k.D(length, i11, objArr.length, objArr, objArr);
                if (size >= iW2) {
                    Object[] objArr2 = this.f20157m;
                    AbstractC1996k.D(objArr2.length - size, 0, iW2, objArr2, objArr2);
                } else {
                    Object[] objArr3 = this.f20157m;
                    AbstractC1996k.D(objArr3.length - size, 0, size, objArr3, objArr3);
                    Object[] objArr4 = this.f20157m;
                    AbstractC1996k.D(0, size, iW2, objArr4, objArr4);
                }
            } else if (length >= 0) {
                Object[] objArr5 = this.f20157m;
                AbstractC1996k.D(length, i11, iW2, objArr5, objArr5);
            } else {
                Object[] objArr6 = this.f20157m;
                length += objArr6.length;
                int i12 = iW2 - i11;
                int length2 = objArr6.length - length;
                if (length2 >= i12) {
                    AbstractC1996k.D(length, i11, iW2, objArr6, objArr6);
                } else {
                    AbstractC1996k.D(length, i11, i11 + length2, objArr6, objArr6);
                    Object[] objArr7 = this.f20157m;
                    AbstractC1996k.D(0, this.l + length2, iW2, objArr7, objArr7);
                }
            }
            this.l = length;
            q(u(iW2 - size), collection);
        } else {
            int i13 = iW2 + size;
            if (iW2 < iW) {
                int i14 = size + iW;
                Object[] objArr8 = this.f20157m;
                if (i14 <= objArr8.length) {
                    AbstractC1996k.D(i13, iW2, iW, objArr8, objArr8);
                } else if (i13 >= objArr8.length) {
                    AbstractC1996k.D(i13 - objArr8.length, iW2, iW, objArr8, objArr8);
                } else {
                    int length3 = iW - (i14 - objArr8.length);
                    AbstractC1996k.D(0, length3, iW, objArr8, objArr8);
                    Object[] objArr9 = this.f20157m;
                    AbstractC1996k.D(i13, iW2, length3, objArr9, objArr9);
                }
            } else {
                Object[] objArr10 = this.f20157m;
                AbstractC1996k.D(size, 0, iW, objArr10, objArr10);
                Object[] objArr11 = this.f20157m;
                if (i13 >= objArr11.length) {
                    AbstractC1996k.D(i13 - objArr11.length, iW2, objArr11.length, objArr11, objArr11);
                } else {
                    AbstractC1996k.D(0, objArr11.length - size, objArr11.length, objArr11, objArr11);
                    Object[] objArr12 = this.f20157m;
                    AbstractC1996k.D(i13, iW2, objArr12.length - size, objArr12, objArr12);
                }
            }
            q(iW2, collection);
        }
        return true;
    }

    public final void addFirst(Object obj) {
        x();
        r(this.f20158n + 1);
        int i7 = this.l;
        int iQ = i7 == 0 ? AbstractC1996k.Q(this.f20157m) : i7 - 1;
        this.l = iQ;
        this.f20157m[iQ] = obj;
        this.f20158n++;
    }

    public final void addLast(Object obj) {
        x();
        r(d() + 1);
        this.f20157m[w(d() + this.l)] = obj;
        this.f20158n = d() + 1;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        if (!isEmpty()) {
            x();
            v(this.l, w(d() + this.l));
        }
        this.l = 0;
        this.f20158n = 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override // n7.AbstractC1991f
    public final int d() {
        return this.f20158n;
    }

    public final Object first() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        return this.f20157m[this.l];
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i7) {
        int iD = d();
        if (i7 < 0 || i7 >= iD) {
            throw new IndexOutOfBoundsException(A7.k.i("index: ", ", size: ", i7, iD));
        }
        return this.f20157m[w(this.l + i7)];
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        int i7;
        int iW = w(d() + this.l);
        int length = this.l;
        if (length < iW) {
            while (length < iW) {
                if (A7.m.a(obj, this.f20157m[length])) {
                    i7 = this.l;
                } else {
                    length++;
                }
            }
            return -1;
        }
        if (length < iW) {
            return -1;
        }
        int length2 = this.f20157m.length;
        while (true) {
            if (length >= length2) {
                for (int i10 = 0; i10 < iW; i10++) {
                    if (A7.m.a(obj, this.f20157m[i10])) {
                        length = i10 + this.f20157m.length;
                        i7 = this.l;
                    }
                }
                return -1;
            }
            if (A7.m.a(obj, this.f20157m[length])) {
                i7 = this.l;
                break;
            }
            length++;
        }
        return length - i7;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean isEmpty() {
        return d() == 0;
    }

    @Override // n7.AbstractC1991f
    public final Object l(int i7) {
        int i10 = this.f20158n;
        if (i7 < 0 || i7 >= i10) {
            throw new IndexOutOfBoundsException(A7.k.i("index: ", ", size: ", i7, i10));
        }
        if (i7 == AbstractC1999n.C(this)) {
            return removeLast();
        }
        if (i7 == 0) {
            return removeFirst();
        }
        x();
        int iW = w(this.l + i7);
        Object[] objArr = this.f20157m;
        Object obj = objArr[iW];
        if (i7 < (this.f20158n >> 1)) {
            int i11 = this.l;
            if (iW >= i11) {
                AbstractC1996k.D(i11 + 1, i11, iW, objArr, objArr);
            } else {
                AbstractC1996k.D(1, 0, iW, objArr, objArr);
                Object[] objArr2 = this.f20157m;
                objArr2[0] = objArr2[objArr2.length - 1];
                int i12 = this.l;
                AbstractC1996k.D(i12 + 1, i12, objArr2.length - 1, objArr2, objArr2);
            }
            Object[] objArr3 = this.f20157m;
            int i13 = this.l;
            objArr3[i13] = null;
            this.l = s(i13);
        } else {
            int iW2 = w(AbstractC1999n.C(this) + this.l);
            if (iW <= iW2) {
                Object[] objArr4 = this.f20157m;
                AbstractC1996k.D(iW, iW + 1, iW2 + 1, objArr4, objArr4);
            } else {
                Object[] objArr5 = this.f20157m;
                AbstractC1996k.D(iW, iW + 1, objArr5.length, objArr5, objArr5);
                Object[] objArr6 = this.f20157m;
                objArr6[objArr6.length - 1] = objArr6[0];
                AbstractC1996k.D(0, 1, iW2 + 1, objArr6, objArr6);
            }
            this.f20157m[iW2] = null;
        }
        this.f20158n--;
        return obj;
    }

    public final Object last() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        return this.f20157m[w(AbstractC1999n.C(this) + this.l)];
    }

    @Override // java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        int iQ;
        int i7;
        int iW = w(d() + this.l);
        int i10 = this.l;
        if (i10 < iW) {
            iQ = iW - 1;
            if (i10 <= iQ) {
                while (!A7.m.a(obj, this.f20157m[iQ])) {
                    if (iQ != i10) {
                        iQ--;
                    }
                }
                i7 = this.l;
                return iQ - i7;
            }
            return -1;
        }
        if (i10 > iW) {
            int i11 = iW - 1;
            while (true) {
                if (-1 >= i11) {
                    iQ = AbstractC1996k.Q(this.f20157m);
                    int i12 = this.l;
                    if (i12 <= iQ) {
                        while (!A7.m.a(obj, this.f20157m[iQ])) {
                            if (iQ != i12) {
                                iQ--;
                            }
                        }
                        i7 = this.l;
                    }
                } else {
                    if (A7.m.a(obj, this.f20157m[i11])) {
                        iQ = i11 + this.f20157m.length;
                        i7 = this.l;
                        break;
                    }
                    i11--;
                }
            }
        }
        return -1;
    }

    public final void q(int i7, Collection collection) {
        Iterator it = collection.iterator();
        int length = this.f20157m.length;
        while (i7 < length && it.hasNext()) {
            this.f20157m[i7] = it.next();
            i7++;
        }
        int i10 = this.l;
        for (int i11 = 0; i11 < i10 && it.hasNext(); i11++) {
            this.f20157m[i11] = it.next();
        }
        this.f20158n = collection.size() + d();
    }

    public final void r(int i7) {
        if (i7 < 0) {
            throw new IllegalStateException("Deque is too big.");
        }
        Object[] objArr = this.f20157m;
        if (i7 <= objArr.length) {
            return;
        }
        if (objArr == o) {
            if (i7 < 10) {
                i7 = 10;
            }
            this.f20157m = new Object[i7];
            return;
        }
        int length = objArr.length;
        int i10 = length + (length >> 1);
        if (i10 - i7 < 0) {
            i10 = i7;
        }
        if (i10 - 2147483639 > 0) {
            i10 = i7 > 2147483639 ? Integer.MAX_VALUE : 2147483639;
        }
        Object[] objArr2 = new Object[i10];
        AbstractC1996k.D(0, this.l, objArr.length, objArr, objArr2);
        Object[] objArr3 = this.f20157m;
        int length2 = objArr3.length;
        int i11 = this.l;
        AbstractC1996k.D(length2 - i11, 0, i11, objArr3, objArr2);
        this.l = 0;
        this.f20157m = objArr2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        int iIndexOf = indexOf(obj);
        if (iIndexOf == -1) {
            return false;
        }
        l(iIndexOf);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean removeAll(Collection collection) {
        int iW;
        A7.m.f(collection, "elements");
        boolean z6 = false;
        z6 = false;
        z6 = false;
        if (!isEmpty() && this.f20157m.length != 0) {
            int iW2 = w(this.f20158n + this.l);
            int i7 = this.l;
            if (i7 < iW2) {
                iW = i7;
                while (i7 < iW2) {
                    Object obj = this.f20157m[i7];
                    if (collection.contains(obj)) {
                        z6 = true;
                    } else {
                        this.f20157m[iW] = obj;
                        iW++;
                    }
                    i7++;
                }
                AbstractC1996k.J(this.f20157m, null, iW, iW2);
            } else {
                int length = this.f20157m.length;
                boolean z10 = false;
                int i10 = i7;
                while (i7 < length) {
                    Object[] objArr = this.f20157m;
                    Object obj2 = objArr[i7];
                    objArr[i7] = null;
                    if (collection.contains(obj2)) {
                        z10 = true;
                    } else {
                        this.f20157m[i10] = obj2;
                        i10++;
                    }
                    i7++;
                }
                iW = w(i10);
                for (int i11 = 0; i11 < iW2; i11++) {
                    Object[] objArr2 = this.f20157m;
                    Object obj3 = objArr2[i11];
                    objArr2[i11] = null;
                    if (collection.contains(obj3)) {
                        z10 = true;
                    } else {
                        this.f20157m[iW] = obj3;
                        iW = s(iW);
                    }
                }
                z6 = z10;
            }
            if (z6) {
                x();
                this.f20158n = u(iW - this.l);
            }
        }
        return z6;
    }

    public final Object removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        x();
        Object[] objArr = this.f20157m;
        int i7 = this.l;
        Object obj = objArr[i7];
        objArr[i7] = null;
        this.l = s(i7);
        this.f20158n = d() - 1;
        return obj;
    }

    public final Object removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        x();
        int iW = w(AbstractC1999n.C(this) + this.l);
        Object[] objArr = this.f20157m;
        Object obj = objArr[iW];
        objArr[iW] = null;
        this.f20158n = d() - 1;
        return obj;
    }

    @Override // java.util.AbstractList
    public final void removeRange(int i7, int i10) {
        h6.r.c(i7, i10, this.f20158n);
        int i11 = i10 - i7;
        if (i11 == 0) {
            return;
        }
        if (i11 == this.f20158n) {
            clear();
            return;
        }
        if (i11 == 1) {
            l(i7);
            return;
        }
        x();
        if (i7 < this.f20158n - i10) {
            int iW = w((i7 - 1) + this.l);
            int iW2 = w((i10 - 1) + this.l);
            while (i7 > 0) {
                int i12 = iW + 1;
                int iMin = Math.min(i7, Math.min(i12, iW2 + 1));
                Object[] objArr = this.f20157m;
                int i13 = iW2 - iMin;
                int i14 = iW - iMin;
                AbstractC1996k.D(i13 + 1, i14 + 1, i12, objArr, objArr);
                iW = u(i14);
                iW2 = u(i13);
                i7 -= iMin;
            }
            int iW3 = w(this.l + i11);
            v(this.l, iW3);
            this.l = iW3;
        } else {
            int iW4 = w(this.l + i10);
            int iW5 = w(this.l + i7);
            int i15 = this.f20158n;
            while (true) {
                i15 -= i10;
                if (i15 <= 0) {
                    break;
                }
                Object[] objArr2 = this.f20157m;
                i10 = Math.min(i15, Math.min(objArr2.length - iW4, objArr2.length - iW5));
                Object[] objArr3 = this.f20157m;
                int i16 = iW4 + i10;
                AbstractC1996k.D(iW5, iW4, i16, objArr3, objArr3);
                iW4 = w(i16);
                iW5 = w(iW5 + i10);
            }
            int iW6 = w(this.f20158n + this.l);
            v(u(iW6 - i11), iW6);
        }
        this.f20158n -= i11;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean retainAll(Collection collection) {
        int iW;
        A7.m.f(collection, "elements");
        boolean z6 = false;
        z6 = false;
        z6 = false;
        if (!isEmpty() && this.f20157m.length != 0) {
            int iW2 = w(this.f20158n + this.l);
            int i7 = this.l;
            if (i7 < iW2) {
                iW = i7;
                while (i7 < iW2) {
                    Object obj = this.f20157m[i7];
                    if (collection.contains(obj)) {
                        this.f20157m[iW] = obj;
                        iW++;
                    } else {
                        z6 = true;
                    }
                    i7++;
                }
                AbstractC1996k.J(this.f20157m, null, iW, iW2);
            } else {
                int length = this.f20157m.length;
                boolean z10 = false;
                int i10 = i7;
                while (i7 < length) {
                    Object[] objArr = this.f20157m;
                    Object obj2 = objArr[i7];
                    objArr[i7] = null;
                    if (collection.contains(obj2)) {
                        this.f20157m[i10] = obj2;
                        i10++;
                    } else {
                        z10 = true;
                    }
                    i7++;
                }
                iW = w(i10);
                for (int i11 = 0; i11 < iW2; i11++) {
                    Object[] objArr2 = this.f20157m;
                    Object obj3 = objArr2[i11];
                    objArr2[i11] = null;
                    if (collection.contains(obj3)) {
                        this.f20157m[iW] = obj3;
                        iW = s(iW);
                    } else {
                        z10 = true;
                    }
                }
                z6 = z10;
            }
            if (z6) {
                x();
                this.f20158n = u(iW - this.l);
            }
        }
        return z6;
    }

    public final int s(int i7) {
        if (i7 == AbstractC1996k.Q(this.f20157m)) {
            return 0;
        }
        return i7 + 1;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i7, Object obj) {
        int iD = d();
        if (i7 < 0 || i7 >= iD) {
            throw new IndexOutOfBoundsException(A7.k.i("index: ", ", size: ", i7, iD));
        }
        int iW = w(this.l + i7);
        Object[] objArr = this.f20157m;
        Object obj2 = objArr[iW];
        objArr[iW] = obj;
        return obj2;
    }

    public final Object t() {
        if (isEmpty()) {
            return null;
        }
        return this.f20157m[w(AbstractC1999n.C(this) + this.l)];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray() {
        return toArray(new Object[d()]);
    }

    public final int u(int i7) {
        return i7 < 0 ? i7 + this.f20157m.length : i7;
    }

    public final void v(int i7, int i10) {
        if (i7 < i10) {
            AbstractC1996k.J(this.f20157m, null, i7, i10);
            return;
        }
        Object[] objArr = this.f20157m;
        Arrays.fill(objArr, i7, objArr.length, (Object) null);
        AbstractC1996k.J(this.f20157m, null, 0, i10);
    }

    public final int w(int i7) {
        Object[] objArr = this.f20157m;
        return i7 >= objArr.length ? i7 - objArr.length : i7;
    }

    public final void x() {
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray(Object[] objArr) {
        A7.m.f(objArr, "array");
        int length = objArr.length;
        int i7 = this.f20158n;
        if (length < i7) {
            Object objNewInstance = Array.newInstance(objArr.getClass().getComponentType(), i7);
            A7.m.d(objNewInstance, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.arrayOfNulls>");
            objArr = (Object[]) objNewInstance;
        }
        int iW = w(this.f20158n + this.l);
        int i10 = this.l;
        if (i10 < iW) {
            AbstractC1996k.G(i10, iW, 2, this.f20157m, objArr);
        } else if (!isEmpty()) {
            Object[] objArr2 = this.f20157m;
            AbstractC1996k.D(0, this.l, objArr2.length, objArr2, objArr);
            Object[] objArr3 = this.f20157m;
            AbstractC1996k.D(objArr3.length - this.l, 0, iW, objArr3, objArr);
        }
        int i11 = this.f20158n;
        if (i11 < objArr.length) {
            objArr[i11] = null;
        }
        return objArr;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        addLast(obj);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        A7.m.f(collection, "elements");
        if (collection.isEmpty()) {
            return false;
        }
        x();
        r(collection.size() + d());
        q(w(d() + this.l), collection);
        return true;
    }
}
