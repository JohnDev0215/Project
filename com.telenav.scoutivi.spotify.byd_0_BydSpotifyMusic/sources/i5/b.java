package i5;

import h5.k;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import kotlin.jvm.internal.l;

/* JADX INFO: compiled from: ListBuilder.kt */
/* JADX INFO: loaded from: classes.dex */
public final class b<E> extends h5.d<E> implements RandomAccess, Serializable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private E[] f7008e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f7009g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f7010h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private boolean f7011i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final b<E> f7012j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final b<E> f7013k;

    /* JADX INFO: compiled from: ListBuilder.kt */
    private static final class a<E> implements ListIterator<E>, s5.a {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final b<E> f7014e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private int f7015g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private int f7016h;

        public a(b<E> list, int i7) {
            l.e(list, "list");
            this.f7014e = list;
            this.f7015g = i7;
            this.f7016h = -1;
        }

        @Override // java.util.ListIterator
        public void add(E e7) {
            b<E> bVar = this.f7014e;
            int i7 = this.f7015g;
            this.f7015g = i7 + 1;
            bVar.add(i7, e7);
            this.f7016h = -1;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public boolean hasNext() {
            return this.f7015g < ((b) this.f7014e).f7010h;
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            return this.f7015g > 0;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public E next() {
            if (this.f7015g >= ((b) this.f7014e).f7010h) {
                throw new NoSuchElementException();
            }
            int i7 = this.f7015g;
            this.f7015g = i7 + 1;
            this.f7016h = i7;
            return (E) ((b) this.f7014e).f7008e[((b) this.f7014e).f7009g + this.f7016h];
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return this.f7015g;
        }

        @Override // java.util.ListIterator
        public E previous() {
            int i7 = this.f7015g;
            if (i7 <= 0) {
                throw new NoSuchElementException();
            }
            int i8 = i7 - 1;
            this.f7015g = i8;
            this.f7016h = i8;
            return (E) ((b) this.f7014e).f7008e[((b) this.f7014e).f7009g + this.f7016h];
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return this.f7015g - 1;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public void remove() {
            int i7 = this.f7016h;
            if (!(i7 != -1)) {
                throw new IllegalStateException("Call next() or previous() before removing element from the iterator.".toString());
            }
            this.f7014e.remove(i7);
            this.f7015g = this.f7016h;
            this.f7016h = -1;
        }

        @Override // java.util.ListIterator
        public void set(E e7) {
            int i7 = this.f7016h;
            if (!(i7 != -1)) {
                throw new IllegalStateException("Call next() or previous() before replacing element from the iterator.".toString());
            }
            this.f7014e.set(i7, e7);
        }
    }

    private b(E[] eArr, int i7, int i8, boolean z6, b<E> bVar, b<E> bVar2) {
        this.f7008e = eArr;
        this.f7009g = i7;
        this.f7010h = i8;
        this.f7011i = z6;
        this.f7012j = bVar;
        this.f7013k = bVar2;
    }

    private final void h(int i7, Collection<? extends E> collection, int i8) {
        b<E> bVar = this.f7012j;
        if (bVar != null) {
            bVar.h(i7, collection, i8);
            this.f7008e = this.f7012j.f7008e;
            this.f7010h += i8;
        } else {
            o(i7, i8);
            Iterator<? extends E> it = collection.iterator();
            for (int i9 = 0; i9 < i8; i9++) {
                this.f7008e[i7 + i9] = it.next();
            }
        }
    }

    private final void i(int i7, E e7) {
        b<E> bVar = this.f7012j;
        if (bVar == null) {
            o(i7, 1);
            this.f7008e[i7] = e7;
        } else {
            bVar.i(i7, e7);
            this.f7008e = this.f7012j.f7008e;
            this.f7010h++;
        }
    }

    private final void k() {
        if (p()) {
            throw new UnsupportedOperationException();
        }
    }

    private final boolean l(List<?> list) {
        return c.h(this.f7008e, this.f7009g, this.f7010h, list);
    }

    private final void m(int i7) {
        if (this.f7012j != null) {
            throw new IllegalStateException();
        }
        if (i7 < 0) {
            throw new OutOfMemoryError();
        }
        E[] eArr = this.f7008e;
        if (i7 > eArr.length) {
            this.f7008e = (E[]) c.e(this.f7008e, h5.g.f6652e.a(eArr.length, i7));
        }
    }

    private final void n(int i7) {
        m(this.f7010h + i7);
    }

    private final void o(int i7, int i8) {
        n(i8);
        E[] eArr = this.f7008e;
        k.d(eArr, eArr, i7 + i8, i7, this.f7009g + this.f7010h);
        this.f7010h += i8;
    }

    private final boolean p() {
        b<E> bVar;
        return this.f7011i || ((bVar = this.f7013k) != null && bVar.f7011i);
    }

    private final E q(int i7) {
        b<E> bVar = this.f7012j;
        if (bVar != null) {
            this.f7010h--;
            return bVar.q(i7);
        }
        E[] eArr = this.f7008e;
        E e7 = eArr[i7];
        k.d(eArr, eArr, i7, i7 + 1, this.f7009g + this.f7010h);
        c.f(this.f7008e, (this.f7009g + this.f7010h) - 1);
        this.f7010h--;
        return e7;
    }

    private final void r(int i7, int i8) {
        b<E> bVar = this.f7012j;
        if (bVar != null) {
            bVar.r(i7, i8);
        } else {
            E[] eArr = this.f7008e;
            k.d(eArr, eArr, i7, i7 + i8, this.f7010h);
            E[] eArr2 = this.f7008e;
            int i9 = this.f7010h;
            c.g(eArr2, i9 - i8, i9);
        }
        this.f7010h -= i8;
    }

    private final int s(int i7, int i8, Collection<? extends E> collection, boolean z6) {
        b<E> bVar = this.f7012j;
        if (bVar != null) {
            int iS = bVar.s(i7, i8, collection, z6);
            this.f7010h -= iS;
            return iS;
        }
        int i9 = 0;
        int i10 = 0;
        while (i9 < i8) {
            int i11 = i7 + i9;
            if (collection.contains(this.f7008e[i11]) == z6) {
                E[] eArr = this.f7008e;
                i9++;
                eArr[i10 + i7] = eArr[i11];
                i10++;
            } else {
                i9++;
            }
        }
        int i12 = i8 - i10;
        E[] eArr2 = this.f7008e;
        k.d(eArr2, eArr2, i7 + i10, i8 + i7, this.f7010h);
        E[] eArr3 = this.f7008e;
        int i13 = this.f7010h;
        c.g(eArr3, i13 - i12, i13);
        this.f7010h -= i12;
        return i12;
    }

    private final Object writeReplace() throws NotSerializableException {
        if (p()) {
            return new h(this, 0);
        }
        throw new NotSerializableException("The list cannot be serialized while it is being built.");
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(E e7) {
        k();
        i(this.f7009g + this.f7010h, e7);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends E> elements) {
        l.e(elements, "elements");
        k();
        int size = elements.size();
        h(this.f7009g + this.f7010h, elements, size);
        return size > 0;
    }

    @Override // h5.d
    public int c() {
        return this.f7010h;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        k();
        r(this.f7009g, this.f7010h);
    }

    @Override // h5.d
    public E d(int i7) {
        k();
        h5.b.f6640e.a(i7, this.f7010h);
        return q(this.f7009g + i7);
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof List) && l((List) obj));
    }

    @Override // java.util.AbstractList, java.util.List
    public E get(int i7) {
        h5.b.f6640e.a(i7, this.f7010h);
        return this.f7008e[this.f7009g + i7];
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public int hashCode() {
        return c.i(this.f7008e, this.f7009g, this.f7010h);
    }

    @Override // java.util.AbstractList, java.util.List
    public int indexOf(Object obj) {
        for (int i7 = 0; i7 < this.f7010h; i7++) {
            if (l.a(this.f7008e[this.f7009g + i7], obj)) {
                return i7;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean isEmpty() {
        return this.f7010h == 0;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator<E> iterator() {
        return new a(this, 0);
    }

    public final List<E> j() {
        if (this.f7012j != null) {
            throw new IllegalStateException();
        }
        k();
        this.f7011i = true;
        return this;
    }

    @Override // java.util.AbstractList, java.util.List
    public int lastIndexOf(Object obj) {
        for (int i7 = this.f7010h - 1; i7 >= 0; i7--) {
            if (l.a(this.f7008e[this.f7009g + i7], obj)) {
                return i7;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractList, java.util.List
    public ListIterator<E> listIterator() {
        return new a(this, 0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object obj) {
        k();
        int iIndexOf = indexOf(obj);
        if (iIndexOf >= 0) {
            remove(iIndexOf);
        }
        return iIndexOf >= 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean removeAll(Collection<? extends Object> elements) {
        l.e(elements, "elements");
        k();
        return s(this.f7009g, this.f7010h, elements, false) > 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean retainAll(Collection<? extends Object> elements) {
        l.e(elements, "elements");
        k();
        return s(this.f7009g, this.f7010h, elements, true) > 0;
    }

    @Override // java.util.AbstractList, java.util.List
    public E set(int i7, E e7) {
        k();
        h5.b.f6640e.a(i7, this.f7010h);
        E[] eArr = this.f7008e;
        int i8 = this.f7009g;
        E e8 = eArr[i8 + i7];
        eArr[i8 + i7] = e7;
        return e8;
    }

    @Override // java.util.AbstractList, java.util.List
    public List<E> subList(int i7, int i8) {
        h5.b.f6640e.c(i7, i8, this.f7010h);
        E[] eArr = this.f7008e;
        int i9 = this.f7009g + i7;
        int i10 = i8 - i7;
        boolean z6 = this.f7011i;
        b<E> bVar = this.f7013k;
        return new b(eArr, i9, i10, z6, this, bVar == null ? this : bVar);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public <T> T[] toArray(T[] destination) {
        l.e(destination, "destination");
        int length = destination.length;
        int i7 = this.f7010h;
        if (length < i7) {
            E[] eArr = this.f7008e;
            int i8 = this.f7009g;
            T[] tArr = (T[]) Arrays.copyOfRange(eArr, i8, i7 + i8, destination.getClass());
            l.d(tArr, "copyOfRange(array, offse…h, destination.javaClass)");
            return tArr;
        }
        E[] eArr2 = this.f7008e;
        int i9 = this.f7009g;
        k.d(eArr2, destination, 0, i9, i7 + i9);
        int length2 = destination.length;
        int i10 = this.f7010h;
        if (length2 > i10) {
            destination[i10] = null;
        }
        return destination;
    }

    @Override // java.util.AbstractCollection
    public String toString() {
        return c.j(this.f7008e, this.f7009g, this.f7010h);
    }

    @Override // java.util.AbstractList, java.util.List
    public ListIterator<E> listIterator(int i7) {
        h5.b.f6640e.b(i7, this.f7010h);
        return new a(this, i7);
    }

    @Override // java.util.AbstractList, java.util.List
    public void add(int i7, E e7) {
        k();
        h5.b.f6640e.b(i7, this.f7010h);
        i(this.f7009g + i7, e7);
    }

    @Override // java.util.AbstractList, java.util.List
    public boolean addAll(int i7, Collection<? extends E> elements) {
        l.e(elements, "elements");
        k();
        h5.b.f6640e.b(i7, this.f7010h);
        int size = elements.size();
        h(this.f7009g + i7, elements, size);
        return size > 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public Object[] toArray() {
        E[] eArr = this.f7008e;
        int i7 = this.f7009g;
        return k.h(eArr, i7, this.f7010h + i7);
    }

    public b() {
        this(10);
    }

    public b(int i7) {
        this(c.d(i7), 0, 0, false, null, null);
    }
}
