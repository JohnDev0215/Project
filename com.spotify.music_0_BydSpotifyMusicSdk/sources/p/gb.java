package p;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class gb extends s1 {
    public static final Object[] l = new Object[0];
    public int c;
    public Object[] e = l;
    public int k;

    @Override // p.s1
    public final int a() {
        return this.k;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        addLast(obj);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        v03.j(collection, "elements");
        if (collection.isEmpty()) {
            return false;
        }
        d(collection.size() + a());
        c(h(a() + this.c), collection);
        return true;
    }

    public final void addLast(Object obj) {
        d(a() + 1);
        this.e[h(a() + this.c)] = obj;
        this.k = a() + 1;
    }

    @Override // p.s1
    public final Object b(int i) {
        int i2 = this.k;
        if (i < 0 || i >= i2) {
            throw new IndexOutOfBoundsException(hf2.c("index: ", i, ", size: ", i2));
        }
        if (i == v03.q(this)) {
            if (isEmpty()) {
                throw new NoSuchElementException("ArrayDeque is empty.");
            }
            int iH = h(v03.q(this) + this.c);
            Object[] objArr = this.e;
            Object obj = objArr[iH];
            objArr[iH] = null;
            this.k--;
            return obj;
        }
        if (i == 0) {
            return removeFirst();
        }
        int iH2 = h(this.c + i);
        Object[] objArr2 = this.e;
        Object obj2 = objArr2[iH2];
        if (i < (this.k >> 1)) {
            int i3 = this.c;
            if (iH2 >= i3) {
                rb.k0(i3 + 1, i3, iH2, objArr2, objArr2);
            } else {
                rb.k0(1, 0, iH2, objArr2, objArr2);
                Object[] objArr3 = this.e;
                objArr3[0] = objArr3[objArr3.length - 1];
                int i4 = this.c;
                rb.k0(i4 + 1, i4, objArr3.length - 1, objArr3, objArr3);
            }
            Object[] objArr4 = this.e;
            int i5 = this.c;
            objArr4[i5] = null;
            this.c = e(i5);
        } else {
            int iH3 = h(v03.q(this) + this.c);
            if (iH2 <= iH3) {
                Object[] objArr5 = this.e;
                rb.k0(iH2, iH2 + 1, iH3 + 1, objArr5, objArr5);
            } else {
                Object[] objArr6 = this.e;
                rb.k0(iH2, iH2 + 1, objArr6.length, objArr6, objArr6);
                Object[] objArr7 = this.e;
                objArr7[objArr7.length - 1] = objArr7[0];
                rb.k0(0, 1, iH3 + 1, objArr7, objArr7);
            }
            this.e[iH3] = null;
        }
        this.k--;
        return obj2;
    }

    public final void c(int i, Collection collection) {
        Iterator it = collection.iterator();
        int length = this.e.length;
        while (i < length && it.hasNext()) {
            this.e[i] = it.next();
            i++;
        }
        int i2 = this.c;
        for (int i3 = 0; i3 < i2 && it.hasNext(); i3++) {
            this.e[i3] = it.next();
        }
        this.k = collection.size() + a();
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        int iH = h(this.k + this.c);
        int i = this.c;
        if (i < iH) {
            Object[] objArr = this.e;
            v03.j(objArr, "<this>");
            Arrays.fill(objArr, i, iH, (Object) null);
        } else if (!isEmpty()) {
            Object[] objArr2 = this.e;
            Arrays.fill(objArr2, this.c, objArr2.length, (Object) null);
            Object[] objArr3 = this.e;
            v03.j(objArr3, "<this>");
            Arrays.fill(objArr3, 0, iH, (Object) null);
        }
        this.c = 0;
        this.k = 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    public final void d(int i) {
        if (i < 0) {
            throw new IllegalStateException("Deque is too big.");
        }
        Object[] objArr = this.e;
        if (i <= objArr.length) {
            return;
        }
        if (objArr == l) {
            if (i < 10) {
                i = 10;
            }
            this.e = new Object[i];
            return;
        }
        int length = objArr.length;
        int i2 = length + (length >> 1);
        if (i2 - i < 0) {
            i2 = i;
        }
        if (i2 - 2147483639 > 0) {
            i2 = i > 2147483639 ? Integer.MAX_VALUE : 2147483639;
        }
        Object[] objArr2 = new Object[i2];
        rb.k0(0, this.c, objArr.length, objArr, objArr2);
        Object[] objArr3 = this.e;
        int length2 = objArr3.length;
        int i3 = this.c;
        rb.k0(length2 - i3, 0, i3, objArr3, objArr2);
        this.c = 0;
        this.e = objArr2;
    }

    public final int e(int i) {
        v03.j(this.e, "<this>");
        if (i == r0.length - 1) {
            return 0;
        }
        return i + 1;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        int iA = a();
        if (i < 0 || i >= iA) {
            throw new IndexOutOfBoundsException(hf2.c("index: ", i, ", size: ", iA));
        }
        return this.e[h(this.c + i)];
    }

    public final int h(int i) {
        Object[] objArr = this.e;
        return i >= objArr.length ? i - objArr.length : i;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        int i;
        int iH = h(a() + this.c);
        int length = this.c;
        if (length < iH) {
            while (length < iH) {
                if (v03.b(obj, this.e[length])) {
                    i = this.c;
                } else {
                    length++;
                }
            }
            return -1;
        }
        if (length < iH) {
            return -1;
        }
        int length2 = this.e.length;
        while (true) {
            if (length >= length2) {
                for (int i2 = 0; i2 < iH; i2++) {
                    if (v03.b(obj, this.e[i2])) {
                        length = i2 + this.e.length;
                        i = this.c;
                    }
                }
                return -1;
            }
            if (v03.b(obj, this.e[length])) {
                i = this.c;
                break;
            }
            length++;
        }
        return length - i;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean isEmpty() {
        return a() == 0;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        int length;
        int i;
        int iH = h(this.k + this.c);
        int i2 = this.c;
        if (i2 < iH) {
            length = iH - 1;
            if (i2 <= length) {
                while (!v03.b(obj, this.e[length])) {
                    if (length != i2) {
                        length--;
                    }
                }
                i = this.c;
                return length - i;
            }
            return -1;
        }
        if (i2 > iH) {
            int i3 = iH - 1;
            while (true) {
                if (-1 >= i3) {
                    Object[] objArr = this.e;
                    v03.j(objArr, "<this>");
                    length = objArr.length - 1;
                    int i4 = this.c;
                    if (i4 <= length) {
                        while (!v03.b(obj, this.e[length])) {
                            if (length != i4) {
                                length--;
                            }
                        }
                        i = this.c;
                    }
                } else {
                    if (v03.b(obj, this.e[i3])) {
                        length = i3 + this.e.length;
                        i = this.c;
                        break;
                    }
                    i3--;
                }
            }
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        int iIndexOf = indexOf(obj);
        if (iIndexOf == -1) {
            return false;
        }
        b(iIndexOf);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean removeAll(Collection collection) {
        int iH;
        v03.j(collection, "elements");
        boolean z = false;
        z = false;
        z = false;
        if (!isEmpty() && this.e.length != 0) {
            int iH2 = h(this.k + this.c);
            int i = this.c;
            if (i < iH2) {
                iH = i;
                while (i < iH2) {
                    Object obj = this.e[i];
                    if (!collection.contains(obj)) {
                        this.e[iH] = obj;
                        iH++;
                    } else {
                        z = true;
                    }
                    i++;
                }
                Object[] objArr = this.e;
                v03.j(objArr, "<this>");
                Arrays.fill(objArr, iH, iH2, (Object) null);
            } else {
                int length = this.e.length;
                boolean z2 = false;
                int i2 = i;
                while (i < length) {
                    Object[] objArr2 = this.e;
                    Object obj2 = objArr2[i];
                    objArr2[i] = null;
                    if (!collection.contains(obj2)) {
                        this.e[i2] = obj2;
                        i2++;
                    } else {
                        z2 = true;
                    }
                    i++;
                }
                iH = h(i2);
                for (int i3 = 0; i3 < iH2; i3++) {
                    Object[] objArr3 = this.e;
                    Object obj3 = objArr3[i3];
                    objArr3[i3] = null;
                    if (!collection.contains(obj3)) {
                        this.e[iH] = obj3;
                        iH = e(iH);
                    } else {
                        z2 = true;
                    }
                }
                z = z2;
            }
            if (z) {
                int length2 = iH - this.c;
                if (length2 < 0) {
                    length2 += this.e.length;
                }
                this.k = length2;
            }
        }
        return z;
    }

    public final Object removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        Object[] objArr = this.e;
        int i = this.c;
        Object obj = objArr[i];
        objArr[i] = null;
        this.c = e(i);
        this.k = a() - 1;
        return obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean retainAll(Collection collection) {
        int iH;
        v03.j(collection, "elements");
        boolean z = false;
        z = false;
        z = false;
        if (!isEmpty() && this.e.length != 0) {
            int iH2 = h(this.k + this.c);
            int i = this.c;
            if (i < iH2) {
                iH = i;
                while (i < iH2) {
                    Object obj = this.e[i];
                    if (collection.contains(obj)) {
                        this.e[iH] = obj;
                        iH++;
                    } else {
                        z = true;
                    }
                    i++;
                }
                Object[] objArr = this.e;
                v03.j(objArr, "<this>");
                Arrays.fill(objArr, iH, iH2, (Object) null);
            } else {
                int length = this.e.length;
                boolean z2 = false;
                int i2 = i;
                while (i < length) {
                    Object[] objArr2 = this.e;
                    Object obj2 = objArr2[i];
                    objArr2[i] = null;
                    if (collection.contains(obj2)) {
                        this.e[i2] = obj2;
                        i2++;
                    } else {
                        z2 = true;
                    }
                    i++;
                }
                iH = h(i2);
                for (int i3 = 0; i3 < iH2; i3++) {
                    Object[] objArr3 = this.e;
                    Object obj3 = objArr3[i3];
                    objArr3[i3] = null;
                    if (collection.contains(obj3)) {
                        this.e[iH] = obj3;
                        iH = e(iH);
                    } else {
                        z2 = true;
                    }
                }
                z = z2;
            }
            if (z) {
                int length2 = iH - this.c;
                if (length2 < 0) {
                    length2 += this.e.length;
                }
                this.k = length2;
            }
        }
        return z;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i, Object obj) {
        int iA = a();
        if (i < 0 || i >= iA) {
            throw new IndexOutOfBoundsException(hf2.c("index: ", i, ", size: ", iA));
        }
        int iH = h(this.c + i);
        Object[] objArr = this.e;
        Object obj2 = objArr[iH];
        objArr[iH] = obj;
        return obj2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray() {
        return toArray(new Object[a()]);
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i, Object obj) {
        int length;
        int i2 = this.k;
        if (i < 0 || i > i2) {
            throw new IndexOutOfBoundsException(hf2.c("index: ", i, ", size: ", i2));
        }
        if (i == i2) {
            addLast(obj);
            return;
        }
        if (i == 0) {
            d(i2 + 1);
            int length2 = this.c;
            if (length2 == 0) {
                Object[] objArr = this.e;
                v03.j(objArr, "<this>");
                length2 = objArr.length;
            }
            int i3 = length2 - 1;
            this.c = i3;
            this.e[i3] = obj;
            this.k++;
            return;
        }
        d(i2 + 1);
        int iH = h(this.c + i);
        int i4 = this.k;
        if (i < ((i4 + 1) >> 1)) {
            if (iH == 0) {
                Object[] objArr2 = this.e;
                v03.j(objArr2, "<this>");
                length = objArr2.length - 1;
            } else {
                length = iH - 1;
            }
            int length3 = this.c;
            if (length3 == 0) {
                Object[] objArr3 = this.e;
                v03.j(objArr3, "<this>");
                length3 = objArr3.length;
            }
            int i5 = length3 - 1;
            int i6 = this.c;
            if (length >= i6) {
                Object[] objArr4 = this.e;
                objArr4[i5] = objArr4[i6];
                rb.k0(i6, i6 + 1, length + 1, objArr4, objArr4);
            } else {
                Object[] objArr5 = this.e;
                rb.k0(i6 - 1, i6, objArr5.length, objArr5, objArr5);
                Object[] objArr6 = this.e;
                objArr6[objArr6.length - 1] = objArr6[0];
                rb.k0(0, 1, length + 1, objArr6, objArr6);
            }
            this.e[length] = obj;
            this.c = i5;
        } else {
            int iH2 = h(i4 + this.c);
            if (iH < iH2) {
                Object[] objArr7 = this.e;
                rb.k0(iH + 1, iH, iH2, objArr7, objArr7);
            } else {
                Object[] objArr8 = this.e;
                rb.k0(1, 0, iH2, objArr8, objArr8);
                Object[] objArr9 = this.e;
                objArr9[0] = objArr9[objArr9.length - 1];
                rb.k0(iH + 1, iH, objArr9.length - 1, objArr9, objArr9);
            }
            this.e[iH] = obj;
        }
        this.k++;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray(Object[] objArr) {
        v03.j(objArr, "array");
        int length = objArr.length;
        int i = this.k;
        if (length < i) {
            Object objNewInstance = Array.newInstance(objArr.getClass().getComponentType(), i);
            v03.g(objNewInstance, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.arrayOfNulls>");
            objArr = (Object[]) objNewInstance;
        }
        int iH = h(this.k + this.c);
        int i2 = this.c;
        if (i2 < iH) {
            rb.l0(this.e, objArr, i2, iH, 2);
        } else if (!isEmpty()) {
            Object[] objArr2 = this.e;
            rb.k0(0, this.c, objArr2.length, objArr2, objArr);
            Object[] objArr3 = this.e;
            rb.k0(objArr3.length - this.c, 0, iH, objArr3, objArr);
        }
        int length2 = objArr.length;
        int i3 = this.k;
        if (length2 > i3) {
            objArr[i3] = null;
        }
        return objArr;
    }

    @Override // java.util.AbstractList, java.util.List
    public final boolean addAll(int i, Collection collection) {
        v03.j(collection, "elements");
        int i2 = this.k;
        if (i >= 0 && i <= i2) {
            if (collection.isEmpty()) {
                return false;
            }
            int i3 = this.k;
            if (i == i3) {
                return addAll(collection);
            }
            d(collection.size() + i3);
            int iH = h(this.k + this.c);
            int iH2 = h(this.c + i);
            int size = collection.size();
            if (i < ((this.k + 1) >> 1)) {
                int i4 = this.c;
                int length = i4 - size;
                if (iH2 < i4) {
                    Object[] objArr = this.e;
                    rb.k0(length, i4, objArr.length, objArr, objArr);
                    if (size >= iH2) {
                        Object[] objArr2 = this.e;
                        rb.k0(objArr2.length - size, 0, iH2, objArr2, objArr2);
                    } else {
                        Object[] objArr3 = this.e;
                        rb.k0(objArr3.length - size, 0, size, objArr3, objArr3);
                        Object[] objArr4 = this.e;
                        rb.k0(0, size, iH2, objArr4, objArr4);
                    }
                } else if (length >= 0) {
                    Object[] objArr5 = this.e;
                    rb.k0(length, i4, iH2, objArr5, objArr5);
                } else {
                    Object[] objArr6 = this.e;
                    length += objArr6.length;
                    int i5 = iH2 - i4;
                    int length2 = objArr6.length - length;
                    if (length2 >= i5) {
                        rb.k0(length, i4, iH2, objArr6, objArr6);
                    } else {
                        rb.k0(length, i4, i4 + length2, objArr6, objArr6);
                        Object[] objArr7 = this.e;
                        rb.k0(0, this.c + length2, iH2, objArr7, objArr7);
                    }
                }
                this.c = length;
                int length3 = iH2 - size;
                if (length3 < 0) {
                    length3 += this.e.length;
                }
                c(length3, collection);
            } else {
                int i6 = iH2 + size;
                if (iH2 < iH) {
                    int i7 = size + iH;
                    Object[] objArr8 = this.e;
                    if (i7 <= objArr8.length) {
                        rb.k0(i6, iH2, iH, objArr8, objArr8);
                    } else if (i6 >= objArr8.length) {
                        rb.k0(i6 - objArr8.length, iH2, iH, objArr8, objArr8);
                    } else {
                        int length4 = iH - (i7 - objArr8.length);
                        rb.k0(0, length4, iH, objArr8, objArr8);
                        Object[] objArr9 = this.e;
                        rb.k0(i6, iH2, length4, objArr9, objArr9);
                    }
                } else {
                    Object[] objArr10 = this.e;
                    rb.k0(size, 0, iH, objArr10, objArr10);
                    Object[] objArr11 = this.e;
                    if (i6 >= objArr11.length) {
                        rb.k0(i6 - objArr11.length, iH2, objArr11.length, objArr11, objArr11);
                    } else {
                        rb.k0(0, objArr11.length - size, objArr11.length, objArr11, objArr11);
                        Object[] objArr12 = this.e;
                        rb.k0(i6, iH2, objArr12.length - size, objArr12, objArr12);
                    }
                }
                c(iH2, collection);
            }
            return true;
        }
        throw new IndexOutOfBoundsException(hf2.c("index: ", i, ", size: ", i2));
    }
}
