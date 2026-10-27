package h5;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

/* JADX INFO: compiled from: AbstractList.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class b<E> extends h5.a<E> implements List<E> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final a f6640e = new a(null);

    /* JADX INFO: compiled from: AbstractList.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }

        public final void a(int i7, int i8) {
            if (i7 < 0 || i7 >= i8) {
                throw new IndexOutOfBoundsException("index: " + i7 + ", size: " + i8);
            }
        }

        public final void b(int i7, int i8) {
            if (i7 < 0 || i7 > i8) {
                throw new IndexOutOfBoundsException("index: " + i7 + ", size: " + i8);
            }
        }

        public final void c(int i7, int i8, int i9) {
            if (i7 < 0 || i8 > i9) {
                throw new IndexOutOfBoundsException("fromIndex: " + i7 + ", toIndex: " + i8 + ", size: " + i9);
            }
            if (i7 <= i8) {
                return;
            }
            throw new IllegalArgumentException("fromIndex: " + i7 + " > toIndex: " + i8);
        }

        public final boolean d(Collection<?> c7, Collection<?> other) {
            kotlin.jvm.internal.l.e(c7, "c");
            kotlin.jvm.internal.l.e(other, "other");
            if (c7.size() != other.size()) {
                return false;
            }
            Iterator<?> it = other.iterator();
            Iterator<?> it2 = c7.iterator();
            while (it2.hasNext()) {
                if (!kotlin.jvm.internal.l.a(it2.next(), it.next())) {
                    return false;
                }
            }
            return true;
        }

        public final int e(Collection<?> c7) {
            kotlin.jvm.internal.l.e(c7, "c");
            Iterator<?> it = c7.iterator();
            int iHashCode = 1;
            while (it.hasNext()) {
                Object next = it.next();
                iHashCode = (iHashCode * 31) + (next != null ? next.hashCode() : 0);
            }
            return iHashCode;
        }
    }

    /* JADX INFO: renamed from: h5.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AbstractList.kt */
    private class C0082b implements Iterator<E>, s5.a {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f6641e;

        public C0082b() {
        }

        protected final int a() {
            return this.f6641e;
        }

        protected final void b(int i7) {
            this.f6641e = i7;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f6641e < b.this.size();
        }

        @Override // java.util.Iterator
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            b<E> bVar = b.this;
            int i7 = this.f6641e;
            this.f6641e = i7 + 1;
            return bVar.get(i7);
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    /* JADX INFO: compiled from: AbstractList.kt */
    private class c extends b<E>.C0082b implements ListIterator<E> {
        public c(int i7) {
            super();
            b.f6640e.b(i7, b.this.size());
            b(i7);
        }

        @Override // java.util.ListIterator
        public void add(E e7) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            return a() > 0;
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return a();
        }

        @Override // java.util.ListIterator
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            b<E> bVar = b.this;
            b(a() - 1);
            return bVar.get(a());
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return a() - 1;
        }

        @Override // java.util.ListIterator
        public void set(E e7) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    /* JADX INFO: compiled from: AbstractList.kt */
    private static final class d<E> extends b<E> implements RandomAccess {

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private final b<E> f6644g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private final int f6645h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private int f6646i;

        /* JADX WARN: Multi-variable type inference failed */
        public d(b<? extends E> list, int i7, int i8) {
            kotlin.jvm.internal.l.e(list, "list");
            this.f6644g = list;
            this.f6645h = i7;
            b.f6640e.c(i7, i8, list.size());
            this.f6646i = i8 - i7;
        }

        @Override // h5.a
        public int c() {
            return this.f6646i;
        }

        @Override // h5.b, java.util.List
        public E get(int i7) {
            b.f6640e.a(i7, this.f6646i);
            return this.f6644g.get(this.f6645h + i7);
        }
    }

    protected b() {
    }

    @Override // java.util.List
    public void add(int i7, E e7) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public boolean addAll(int i7, Collection<? extends E> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof List) {
            return f6640e.d(this, (Collection) obj);
        }
        return false;
    }

    @Override // java.util.List
    public abstract E get(int i7);

    @Override // java.util.Collection, java.util.List
    public int hashCode() {
        return f6640e.e(this);
    }

    @Override // java.util.List
    public int indexOf(E e7) {
        Iterator<E> it = iterator();
        int i7 = 0;
        while (it.hasNext()) {
            if (kotlin.jvm.internal.l.a(it.next(), e7)) {
                return i7;
            }
            i7++;
        }
        return -1;
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator<E> iterator() {
        return new C0082b();
    }

    @Override // java.util.List
    public int lastIndexOf(E e7) {
        ListIterator<E> listIterator = listIterator(size());
        while (listIterator.hasPrevious()) {
            if (kotlin.jvm.internal.l.a(listIterator.previous(), e7)) {
                return listIterator.nextIndex();
            }
        }
        return -1;
    }

    @Override // java.util.List
    public ListIterator<E> listIterator() {
        return new c(0);
    }

    @Override // java.util.List
    public E remove(int i7) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public E set(int i7, E e7) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public List<E> subList(int i7, int i8) {
        return new d(this, i7, i8);
    }

    @Override // java.util.List
    public ListIterator<E> listIterator(int i7) {
        return new c(i7);
    }
}
