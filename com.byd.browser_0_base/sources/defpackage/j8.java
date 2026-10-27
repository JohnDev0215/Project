package defpackage;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: compiled from: ArrayDeque.kt */
/* JADX INFO: loaded from: classes.dex */
public final class j8<E> extends AbstractC0205q<E> {
    public static final Object[] h = new Object[0];
    public int e;
    public Object[] f = h;
    public int g;

    public final void a(int i, Collection<? extends E> collection) {
        Iterator<? extends E> it = collection.iterator();
        int length = this.f.length;
        while (i < length && it.hasNext()) {
            this.f[i] = it.next();
            i++;
        }
        int i2 = this.e;
        for (int i3 = 0; i3 < i2 && it.hasNext(); i3++) {
            this.f[i3] = it.next();
        }
        this.g = collection.size() + this.g;
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i, E e) {
        int length;
        int i2 = this.g;
        if (i < 0 || i > i2) {
            throw new IndexOutOfBoundsException("index: " + i + ", size: " + i2);
        }
        if (i == i2) {
            addLast(e);
            return;
        }
        if (i == 0) {
            b(i2 + 1);
            int length2 = this.e;
            if (length2 == 0) {
                Object[] objArr = this.f;
                i21.e(objArr, "<this>");
                length2 = objArr.length;
            }
            int i3 = length2 - 1;
            this.e = i3;
            this.f[i3] = e;
            this.g++;
            return;
        }
        b(i2 + 1);
        int iD = d(this.e + i);
        int i4 = this.g;
        if (i < ((i4 + 1) >> 1)) {
            if (iD == 0) {
                Object[] objArr2 = this.f;
                i21.e(objArr2, "<this>");
                length = objArr2.length - 1;
            } else {
                length = iD - 1;
            }
            int length3 = this.e;
            if (length3 == 0) {
                Object[] objArr3 = this.f;
                i21.e(objArr3, "<this>");
                length3 = objArr3.length;
            }
            int i5 = length3 - 1;
            int i6 = this.e;
            if (length >= i6) {
                Object[] objArr4 = this.f;
                objArr4[i5] = objArr4[i6];
                r8.q0(i6, i6 + 1, length + 1, objArr4, objArr4);
            } else {
                Object[] objArr5 = this.f;
                r8.q0(i6 - 1, i6, objArr5.length, objArr5, objArr5);
                Object[] objArr6 = this.f;
                objArr6[objArr6.length - 1] = objArr6[0];
                r8.q0(0, 1, length + 1, objArr6, objArr6);
            }
            this.f[length] = e;
            this.e = i5;
        } else {
            int iD2 = d(this.e + i4);
            if (iD < iD2) {
                Object[] objArr7 = this.f;
                r8.q0(iD + 1, iD, iD2, objArr7, objArr7);
            } else {
                Object[] objArr8 = this.f;
                r8.q0(1, 0, iD2, objArr8, objArr8);
                Object[] objArr9 = this.f;
                objArr9[0] = objArr9[objArr9.length - 1];
                r8.q0(iD + 1, iD, objArr9.length - 1, objArr9, objArr9);
            }
            this.f[iD] = e;
        }
        this.g++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final boolean addAll(int i, Collection<? extends E> collection) {
        i21.e(collection, "elements");
        int i2 = this.g;
        if (i < 0 || i > i2) {
            throw new IndexOutOfBoundsException("index: " + i + ", size: " + i2);
        }
        if (collection.isEmpty()) {
            return false;
        }
        int i3 = this.g;
        if (i == i3) {
            return addAll(collection);
        }
        b(collection.size() + i3);
        int iD = d(this.e + this.g);
        int iD2 = d(this.e + i);
        int size = collection.size();
        if (i < ((this.g + 1) >> 1)) {
            int i4 = this.e;
            int length = i4 - size;
            if (iD2 < i4) {
                Object[] objArr = this.f;
                r8.q0(length, i4, objArr.length, objArr, objArr);
                if (size >= iD2) {
                    Object[] objArr2 = this.f;
                    r8.q0(objArr2.length - size, 0, iD2, objArr2, objArr2);
                } else {
                    Object[] objArr3 = this.f;
                    r8.q0(objArr3.length - size, 0, size, objArr3, objArr3);
                    Object[] objArr4 = this.f;
                    r8.q0(0, size, iD2, objArr4, objArr4);
                }
            } else if (length >= 0) {
                Object[] objArr5 = this.f;
                r8.q0(length, i4, iD2, objArr5, objArr5);
            } else {
                Object[] objArr6 = this.f;
                length += objArr6.length;
                int i5 = iD2 - i4;
                int length2 = objArr6.length - length;
                if (length2 >= i5) {
                    r8.q0(length, i4, iD2, objArr6, objArr6);
                } else {
                    r8.q0(length, i4, i4 + length2, objArr6, objArr6);
                    Object[] objArr7 = this.f;
                    r8.q0(0, this.e + length2, iD2, objArr7, objArr7);
                }
            }
            this.e = length;
            int length3 = iD2 - size;
            if (length3 < 0) {
                length3 += this.f.length;
            }
            a(length3, collection);
        } else {
            int i6 = iD2 + size;
            if (iD2 < iD) {
                int i7 = size + iD;
                Object[] objArr8 = this.f;
                if (i7 <= objArr8.length) {
                    r8.q0(i6, iD2, iD, objArr8, objArr8);
                } else if (i6 >= objArr8.length) {
                    r8.q0(i6 - objArr8.length, iD2, iD, objArr8, objArr8);
                } else {
                    int length4 = iD - (i7 - objArr8.length);
                    r8.q0(0, length4, iD, objArr8, objArr8);
                    Object[] objArr9 = this.f;
                    r8.q0(i6, iD2, length4, objArr9, objArr9);
                }
            } else {
                Object[] objArr10 = this.f;
                r8.q0(size, 0, iD, objArr10, objArr10);
                Object[] objArr11 = this.f;
                if (i6 >= objArr11.length) {
                    r8.q0(i6 - objArr11.length, iD2, objArr11.length, objArr11, objArr11);
                } else {
                    r8.q0(0, objArr11.length - size, objArr11.length, objArr11, objArr11);
                    Object[] objArr12 = this.f;
                    r8.q0(i6, iD2, objArr12.length - size, objArr12, objArr12);
                }
            }
            a(iD2, collection);
        }
        return true;
    }

    public final void addLast(E e) {
        b(this.g + 1);
        this.f[d(this.e + this.g)] = e;
        this.g++;
    }

    public final void b(int i) {
        if (i < 0) {
            throw new IllegalStateException("Deque is too big.");
        }
        Object[] objArr = this.f;
        if (i <= objArr.length) {
            return;
        }
        if (objArr == h) {
            if (i < 10) {
                i = 10;
            }
            this.f = new Object[i];
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
        r8.q0(0, this.e, objArr.length, objArr, objArr2);
        Object[] objArr3 = this.f;
        int length2 = objArr3.length;
        int i3 = this.e;
        r8.q0(length2 - i3, 0, i3, objArr3, objArr2);
        this.e = 0;
        this.f = objArr2;
    }

    public final int c(int i) {
        i21.e(this.f, "<this>");
        if (i == r1.length - 1) {
            return 0;
        }
        return i + 1;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        int iD = d(this.e + this.g);
        int i = this.e;
        if (i < iD) {
            Object[] objArr = this.f;
            i21.e(objArr, "<this>");
            Arrays.fill(objArr, i, iD, (Object) null);
        } else if (!isEmpty()) {
            Object[] objArr2 = this.f;
            Arrays.fill(objArr2, this.e, objArr2.length, (Object) null);
            Object[] objArr3 = this.f;
            i21.e(objArr3, "<this>");
            Arrays.fill(objArr3, 0, iD, (Object) null);
        }
        this.e = 0;
        this.g = 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    public final int d(int i) {
        Object[] objArr = this.f;
        return i >= objArr.length ? i - objArr.length : i;
    }

    @Override // java.util.AbstractList, java.util.List
    public final E get(int i) {
        int i2 = this.g;
        if (i >= 0 && i < i2) {
            return (E) this.f[d(this.e + i)];
        }
        throw new IndexOutOfBoundsException("index: " + i + ", size: " + i2);
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        int i;
        int iD = d(this.e + this.g);
        int length = this.e;
        if (length < iD) {
            while (length < iD) {
                if (i21.a(obj, this.f[length])) {
                    i = this.e;
                } else {
                    length++;
                }
            }
            return -1;
        }
        if (length < iD) {
            return -1;
        }
        int length2 = this.f.length;
        while (true) {
            if (length >= length2) {
                for (int i2 = 0; i2 < iD; i2++) {
                    if (i21.a(obj, this.f[i2])) {
                        length = i2 + this.f.length;
                        i = this.e;
                    }
                }
                return -1;
            }
            if (i21.a(obj, this.f[length])) {
                i = this.e;
                break;
            }
            length++;
        }
        return length - i;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean isEmpty() {
        return this.g == 0;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        int length;
        int i;
        int iD = d(this.e + this.g);
        int i2 = this.e;
        if (i2 < iD) {
            length = iD - 1;
            if (i2 <= length) {
                while (!i21.a(obj, this.f[length])) {
                    if (length != i2) {
                        length--;
                    }
                }
                i = this.e;
                return length - i;
            }
            return -1;
        }
        if (i2 > iD) {
            int i3 = iD - 1;
            while (true) {
                if (-1 >= i3) {
                    Object[] objArr = this.f;
                    i21.e(objArr, "<this>");
                    length = objArr.length - 1;
                    int i4 = this.e;
                    if (i4 <= length) {
                        while (!i21.a(obj, this.f[length])) {
                            if (length != i4) {
                                length--;
                            }
                        }
                        i = this.e;
                    }
                } else {
                    if (i21.a(obj, this.f[i3])) {
                        length = i3 + this.f.length;
                        i = this.e;
                        break;
                    }
                    i3--;
                }
            }
            return length - i;
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        int iIndexOf = indexOf(obj);
        if (iIndexOf == -1) {
            return false;
        }
        remove(iIndexOf);
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean removeAll(Collection<? extends Object> collection) {
        int iD;
        i21.e(collection, "elements");
        boolean z = false;
        z = false;
        z = false;
        if (!isEmpty()) {
            if ((this.f.length == 0) == false) {
                int iD2 = d(this.e + this.g);
                int i = this.e;
                if (i < iD2) {
                    iD = i;
                    while (i < iD2) {
                        Object obj = this.f[i];
                        if (!collection.contains(obj)) {
                            this.f[iD] = obj;
                            iD++;
                        } else {
                            z = true;
                        }
                        i++;
                    }
                    Object[] objArr = this.f;
                    i21.e(objArr, "<this>");
                    Arrays.fill(objArr, iD, iD2, (Object) null);
                } else {
                    int length = this.f.length;
                    boolean z2 = false;
                    int i2 = i;
                    while (i < length) {
                        Object[] objArr2 = this.f;
                        Object obj2 = objArr2[i];
                        objArr2[i] = null;
                        if (!collection.contains(obj2)) {
                            this.f[i2] = obj2;
                            i2++;
                        } else {
                            z2 = true;
                        }
                        i++;
                    }
                    iD = d(i2);
                    for (int i3 = 0; i3 < iD2; i3++) {
                        Object[] objArr3 = this.f;
                        Object obj3 = objArr3[i3];
                        objArr3[i3] = null;
                        if (!collection.contains(obj3)) {
                            this.f[iD] = obj3;
                            iD = c(iD);
                        } else {
                            z2 = true;
                        }
                    }
                    z = z2;
                }
                if (z) {
                    int length2 = iD - this.e;
                    if (length2 < 0) {
                        length2 += this.f.length;
                    }
                    this.g = length2;
                }
            }
        }
        return z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean retainAll(Collection<? extends Object> collection) {
        int iD;
        i21.e(collection, "elements");
        boolean z = false;
        z = false;
        z = false;
        if (!isEmpty()) {
            if ((this.f.length == 0) == false) {
                int iD2 = d(this.e + this.g);
                int i = this.e;
                if (i < iD2) {
                    iD = i;
                    while (i < iD2) {
                        Object obj = this.f[i];
                        if (collection.contains(obj)) {
                            this.f[iD] = obj;
                            iD++;
                        } else {
                            z = true;
                        }
                        i++;
                    }
                    Object[] objArr = this.f;
                    i21.e(objArr, "<this>");
                    Arrays.fill(objArr, iD, iD2, (Object) null);
                } else {
                    int length = this.f.length;
                    boolean z2 = false;
                    int i2 = i;
                    while (i < length) {
                        Object[] objArr2 = this.f;
                        Object obj2 = objArr2[i];
                        objArr2[i] = null;
                        if (collection.contains(obj2)) {
                            this.f[i2] = obj2;
                            i2++;
                        } else {
                            z2 = true;
                        }
                        i++;
                    }
                    iD = d(i2);
                    for (int i3 = 0; i3 < iD2; i3++) {
                        Object[] objArr3 = this.f;
                        Object obj3 = objArr3[i3];
                        objArr3[i3] = null;
                        if (collection.contains(obj3)) {
                            this.f[iD] = obj3;
                            iD = c(iD);
                        } else {
                            z2 = true;
                        }
                    }
                    z = z2;
                }
                if (z) {
                    int length2 = iD - this.e;
                    if (length2 < 0) {
                        length2 += this.f.length;
                    }
                    this.g = length2;
                }
            }
        }
        return z;
    }

    @Override // java.util.AbstractList, java.util.List
    public final E set(int i, E e) {
        int i2 = this.g;
        if (i < 0 || i >= i2) {
            throw new IndexOutOfBoundsException("index: " + i + ", size: " + i2);
        }
        int iD = d(this.e + i);
        Object[] objArr = this.f;
        E e2 = (E) objArr[iD];
        objArr[iD] = e;
        return e2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray() {
        return toArray(new Object[this.g]);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final <T> T[] toArray(T[] tArr) {
        i21.e(tArr, "array");
        int length = tArr.length;
        int i = this.g;
        if (length < i) {
            Object objNewInstance = Array.newInstance(tArr.getClass().getComponentType(), i);
            i21.c(objNewInstance, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.arrayOfNulls>");
            tArr = (T[]) ((Object[]) objNewInstance);
        }
        int iD = d(this.e + this.g);
        int i2 = this.e;
        if (i2 < iD) {
            r8.r0(this.f, tArr, 0, i2, iD, 2);
        } else if (!isEmpty()) {
            Object[] objArr = this.f;
            r8.q0(0, this.e, objArr.length, objArr, tArr);
            Object[] objArr2 = this.f;
            r8.q0(objArr2.length - this.e, 0, iD, objArr2, tArr);
        }
        int length2 = tArr.length;
        int i3 = this.g;
        if (length2 > i3) {
            tArr[i3] = null;
        }
        return tArr;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(E e) {
        addLast(e);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends E> collection) {
        i21.e(collection, "elements");
        if (collection.isEmpty()) {
            return false;
        }
        b(collection.size() + this.g);
        a(d(this.e + this.g), collection);
        return true;
    }
}
