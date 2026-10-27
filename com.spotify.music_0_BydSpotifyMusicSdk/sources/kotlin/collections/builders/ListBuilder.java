package kotlin.collections.builders;

import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import p.f60;
import p.hf2;
import p.rb;
import p.s1;
import p.u11;
import p.v03;
import p.va1;

/* JADX INFO: loaded from: classes.dex */
public final class ListBuilder<E> extends s1 implements RandomAccess, Serializable {
    private E[] array;
    private final ListBuilder<E> backing;
    private boolean isReadOnly;
    private int length;
    private int offset;
    private final ListBuilder<E> root;

    /* JADX WARN: Multi-variable type inference failed */
    public ListBuilder(Object[] objArr, int i, int i2, boolean z, ListBuilder listBuilder, ListBuilder listBuilder2) {
        this.array = objArr;
        this.offset = i;
        this.length = i2;
        this.isReadOnly = z;
        this.backing = listBuilder;
        this.root = listBuilder2;
    }

    private final Object writeReplace() throws NotSerializableException {
        ListBuilder<E> listBuilder;
        if (this.isReadOnly || ((listBuilder = this.root) != null && listBuilder.isReadOnly)) {
            return new SerializedCollection(0, this);
        }
        throw new NotSerializableException("The list cannot be serialized while it is being built.");
    }

    @Override // p.s1
    public final int a() {
        return this.length;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        n();
        i(this.offset + this.length, obj);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        v03.j(collection, "elements");
        n();
        int size = collection.size();
        h(this.offset + this.length, collection, size);
        return size > 0;
    }

    @Override // p.s1
    public final Object b(int i) {
        n();
        int i2 = this.length;
        if (i < 0 || i >= i2) {
            throw new IndexOutOfBoundsException(hf2.c("index: ", i, ", size: ", i2));
        }
        return p(this.offset + i);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        n();
        q(this.offset, this.length);
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (obj != this) {
            if (!(obj instanceof List)) {
                return false;
            }
            List list = (List) obj;
            E[] eArr = this.array;
            int i = this.offset;
            int i2 = this.length;
            if (i2 != list.size()) {
                return false;
            }
            for (int i3 = 0; i3 < i2; i3++) {
                if (!v03.b(eArr[i + i3], list.get(i3))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        int i2 = this.length;
        if (i < 0 || i >= i2) {
            throw new IndexOutOfBoundsException(hf2.c("index: ", i, ", size: ", i2));
        }
        return this.array[this.offset + i];
    }

    public final void h(int i, Collection collection, int i2) {
        ListBuilder<E> listBuilder = this.backing;
        if (listBuilder != null) {
            listBuilder.h(i, collection, i2);
            this.array = this.backing.array;
            this.length += i2;
        } else {
            o(i, i2);
            Iterator<E> it = collection.iterator();
            for (int i3 = 0; i3 < i2; i3++) {
                this.array[i + i3] = it.next();
            }
        }
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        E[] eArr = this.array;
        int i = this.offset;
        int i2 = this.length;
        int iHashCode = 1;
        for (int i3 = 0; i3 < i2; i3++) {
            E e = eArr[i + i3];
            iHashCode = (iHashCode * 31) + (e != null ? e.hashCode() : 0);
        }
        return iHashCode;
    }

    public final void i(int i, Object obj) {
        ListBuilder<E> listBuilder = this.backing;
        if (listBuilder == null) {
            o(i, 1);
            ((E[]) this.array)[i] = obj;
        } else {
            listBuilder.i(i, obj);
            this.array = this.backing.array;
            this.length++;
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        for (int i = 0; i < this.length; i++) {
            if (v03.b(this.array[this.offset + i], obj)) {
                return i;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean isEmpty() {
        return this.length == 0;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return new va1(this, 0);
    }

    @Override // java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        for (int i = this.length - 1; i >= 0; i--) {
            if (v03.b(this.array[this.offset + i], obj)) {
                return i;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator() {
        return new va1(this, 0);
    }

    public final void m() {
        if (this.backing != null) {
            throw new IllegalStateException();
        }
        n();
        this.isReadOnly = true;
    }

    public final void n() {
        ListBuilder<E> listBuilder;
        if (this.isReadOnly || ((listBuilder = this.root) != null && listBuilder.isReadOnly)) {
            throw new UnsupportedOperationException();
        }
    }

    public final void o(int i, int i2) {
        int i3 = this.length + i2;
        if (this.backing != null) {
            throw new IllegalStateException();
        }
        if (i3 < 0) {
            throw new OutOfMemoryError();
        }
        E[] eArr = this.array;
        if (i3 > eArr.length) {
            int length = eArr.length;
            int i4 = length + (length >> 1);
            if (i4 - i3 < 0) {
                i4 = i3;
            }
            if (i4 - 2147483639 > 0) {
                i4 = i3 > 2147483639 ? Integer.MAX_VALUE : 2147483639;
            }
            v03.j(eArr, "<this>");
            E[] eArr2 = (E[]) Arrays.copyOf(eArr, i4);
            v03.i(eArr2, "copyOf(this, newSize)");
            this.array = eArr2;
        }
        E[] eArr3 = this.array;
        rb.k0(i + i2, i, this.offset + this.length, eArr3, eArr3);
        this.length += i2;
    }

    public final Object p(int i) {
        ListBuilder<E> listBuilder = this.backing;
        if (listBuilder != null) {
            this.length--;
            return listBuilder.p(i);
        }
        E[] eArr = this.array;
        E e = eArr[i];
        rb.k0(i, i + 1, this.offset + this.length, eArr, eArr);
        E[] eArr2 = this.array;
        int i2 = (this.offset + this.length) - 1;
        v03.j(eArr2, "<this>");
        eArr2[i2] = null;
        this.length--;
        return e;
    }

    public final void q(int i, int i2) {
        ListBuilder<E> listBuilder = this.backing;
        if (listBuilder != null) {
            listBuilder.q(i, i2);
        } else {
            E[] eArr = this.array;
            rb.k0(i, i + i2, this.length, eArr, eArr);
            E[] eArr2 = this.array;
            int i3 = this.length;
            f60.E(i3 - i2, i3, eArr2);
        }
        this.length -= i2;
    }

    public final int r(int i, int i2, Collection collection, boolean z) {
        ListBuilder<E> listBuilder = this.backing;
        if (listBuilder != null) {
            int iR = listBuilder.r(i, i2, collection, z);
            this.length -= iR;
            return iR;
        }
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            int i5 = i + i3;
            if (collection.contains(this.array[i5]) == z) {
                E[] eArr = this.array;
                i3++;
                eArr[i4 + i] = eArr[i5];
                i4++;
            } else {
                i3++;
            }
        }
        int i6 = i2 - i4;
        E[] eArr2 = this.array;
        rb.k0(i + i4, i2 + i, this.length, eArr2, eArr2);
        E[] eArr3 = this.array;
        int i7 = this.length;
        f60.E(i7 - i6, i7, eArr3);
        this.length -= i6;
        return i6;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        n();
        int iIndexOf = indexOf(obj);
        if (iIndexOf >= 0) {
            b(iIndexOf);
        }
        return iIndexOf >= 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean removeAll(Collection collection) {
        v03.j(collection, "elements");
        n();
        return r(this.offset, this.length, collection, false) > 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean retainAll(Collection collection) {
        v03.j(collection, "elements");
        n();
        return r(this.offset, this.length, collection, true) > 0;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i, Object obj) {
        n();
        int i2 = this.length;
        if (i < 0 || i >= i2) {
            throw new IndexOutOfBoundsException(hf2.c("index: ", i, ", size: ", i2));
        }
        Object[] objArr = this.array;
        int i3 = this.offset;
        Object obj2 = objArr[i3 + i];
        objArr[i3 + i] = obj;
        return obj2;
    }

    @Override // java.util.AbstractList, java.util.List
    public final List subList(int i, int i2) {
        u11.t(i, i2, this.length);
        E[] eArr = this.array;
        int i3 = this.offset + i;
        int i4 = i2 - i;
        boolean z = this.isReadOnly;
        ListBuilder<E> listBuilder = this.root;
        return new ListBuilder(eArr, i3, i4, z, this, listBuilder == null ? this : listBuilder);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray(Object[] objArr) {
        v03.j(objArr, "destination");
        int length = objArr.length;
        int i = this.length;
        if (length < i) {
            E[] eArr = this.array;
            int i2 = this.offset;
            Object[] objArrCopyOfRange = Arrays.copyOfRange(eArr, i2, i + i2, objArr.getClass());
            v03.i(objArrCopyOfRange, "copyOfRange(array, offse…h, destination.javaClass)");
            return objArrCopyOfRange;
        }
        E[] eArr2 = this.array;
        int i3 = this.offset;
        rb.k0(0, i3, i + i3, eArr2, objArr);
        int length2 = objArr.length;
        int i4 = this.length;
        if (length2 > i4) {
            objArr[i4] = null;
        }
        return objArr;
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        E[] eArr = this.array;
        int i = this.offset;
        int i2 = this.length;
        StringBuilder sb = new StringBuilder((i2 * 3) + 2);
        sb.append("[");
        for (int i3 = 0; i3 < i2; i3++) {
            if (i3 > 0) {
                sb.append(", ");
            }
            sb.append(eArr[i + i3]);
        }
        sb.append("]");
        String string = sb.toString();
        v03.i(string, "sb.toString()");
        return string;
    }

    public ListBuilder() {
        this(10);
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i) {
        int i2 = this.length;
        if (i < 0 || i > i2) {
            throw new IndexOutOfBoundsException(hf2.c("index: ", i, ", size: ", i2));
        }
        return new va1(this, i);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ListBuilder(int i) {
        this(new Object[i], 0, 0, false, null, null);
        if (i >= 0) {
            return;
        }
        throw new IllegalArgumentException("capacity must be non-negative.".toString());
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i, Object obj) {
        n();
        int i2 = this.length;
        if (i >= 0 && i <= i2) {
            i(this.offset + i, obj);
            return;
        }
        throw new IndexOutOfBoundsException(hf2.c("index: ", i, ", size: ", i2));
    }

    @Override // java.util.AbstractList, java.util.List
    public final boolean addAll(int i, Collection collection) {
        v03.j(collection, "elements");
        n();
        int i2 = this.length;
        if (i >= 0 && i <= i2) {
            int size = collection.size();
            h(this.offset + i, collection, size);
            return size > 0;
        }
        throw new IndexOutOfBoundsException(hf2.c("index: ", i, ", size: ", i2));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray() {
        E[] eArr = this.array;
        int i = this.offset;
        return rb.n0(i, this.length + i, eArr);
    }
}
