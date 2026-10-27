package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

/* JADX INFO: compiled from: AbstractList.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class a<E> extends AbstractCollection<E> implements List<E>, kotlin.jvm.internal.n.a {
    public static final C0093a Companion = new C0093a(null);

    /* JADX INFO: renamed from: kotlin.collections.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AbstractList.kt */
    public static final class C0093a {
        private C0093a() {
        }

        public final void a(int i, int i2) {
            if (i < 0 || i >= i2) {
                throw new IndexOutOfBoundsException("index: " + i + ", size: " + i2);
            }
        }

        public final void b(int i, int i2) {
            if (i < 0 || i > i2) {
                throw new IndexOutOfBoundsException("index: " + i + ", size: " + i2);
            }
        }

        public final void c(int i, int i2, int i3) {
            if (i < 0 || i2 > i3) {
                throw new IndexOutOfBoundsException("fromIndex: " + i + ", toIndex: " + i2 + ", size: " + i3);
            }
            if (i <= i2) {
                return;
            }
            throw new IllegalArgumentException("fromIndex: " + i + " > toIndex: " + i2);
        }

        public final boolean d(Collection<?> collection, Collection<?> collection2) {
            kotlin.jvm.internal.i.c(collection, "c");
            kotlin.jvm.internal.i.c(collection2, "other");
            if (collection.size() != collection2.size()) {
                return false;
            }
            Iterator<?> it = collection2.iterator();
            Iterator<?> it2 = collection.iterator();
            while (it2.hasNext()) {
                if (!kotlin.jvm.internal.i.a(it2.next(), it.next())) {
                    return false;
                }
            }
            return true;
        }

        public final int e(Collection<?> collection) {
            kotlin.jvm.internal.i.c(collection, "c");
            Iterator<?> it = collection.iterator();
            int iHashCode = 1;
            while (it.hasNext()) {
                Object next = it.next();
                iHashCode = (iHashCode * 31) + (next != null ? next.hashCode() : 0);
            }
            return iHashCode;
        }

        public /* synthetic */ C0093a(kotlin.jvm.internal.f fVar) {
            this();
        }
    }

    /* JADX INFO: compiled from: AbstractList.kt */
    private class b implements Iterator<E>, kotlin.jvm.internal.n.a {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f5089b;

        public b() {
        }

        protected final int a() {
            return this.f5089b;
        }

        protected final void b(int i) {
            this.f5089b = i;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f5089b < a.this.size();
        }

        @Override // java.util.Iterator
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            a aVar = a.this;
            int i = this.f5089b;
            this.f5089b = i + 1;
            return (E) aVar.get(i);
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    /* JADX INFO: compiled from: AbstractList.kt */
    private class c extends a<E>.b implements ListIterator<E>, kotlin.jvm.internal.n.a {
        public c(int i) {
            super();
            a.Companion.b(i, a.this.size());
            b(i);
        }

        @Override // java.util.ListIterator
        public void add(E e2) {
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
            a aVar = a.this;
            b(a() - 1);
            return (E) aVar.get(a());
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return a() - 1;
        }

        @Override // java.util.ListIterator
        public void set(E e2) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    /* JADX INFO: compiled from: AbstractList.kt */
    private static final class d<E> extends a<E> implements RandomAccess {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f5092b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final a<E> f5093c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final int f5094d;

        /* JADX WARN: Multi-variable type inference failed */
        public d(a<? extends E> aVar, int i, int i2) {
            kotlin.jvm.internal.i.c(aVar, "list");
            this.f5093c = aVar;
            this.f5094d = i;
            a.Companion.c(i, i2, aVar.size());
            this.f5092b = i2 - i;
        }

        @Override // kotlin.collections.a, java.util.List
        public E get(int i) {
            a.Companion.a(i, this.f5092b);
            return this.f5093c.get(this.f5094d + i);
        }

        @Override // kotlin.collections.a, kotlin.collections.AbstractCollection
        public int getSize() {
            return this.f5092b;
        }
    }

    protected a() {
    }

    @Override // java.util.List
    public void add(int i, E e2) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public boolean addAll(int i, Collection<? extends E> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof List) {
            return Companion.d(this, (Collection) obj);
        }
        return false;
    }

    @Override // java.util.List
    public abstract E get(int i);

    @Override // kotlin.collections.AbstractCollection
    public abstract int getSize();

    @Override // java.util.Collection, java.util.List
    public int hashCode() {
        return Companion.e(this);
    }

    @Override // java.util.List
    public int indexOf(Object obj) {
        Iterator<E> it = iterator();
        int i = 0;
        while (it.hasNext()) {
            if (kotlin.jvm.internal.i.a(it.next(), obj)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return new b();
    }

    @Override // java.util.List
    public int lastIndexOf(Object obj) {
        ListIterator<E> listIterator = listIterator(size());
        while (listIterator.hasPrevious()) {
            if (kotlin.jvm.internal.i.a(listIterator.previous(), obj)) {
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
    public E remove(int i) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public E set(int i, E e2) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public List<E> subList(int i, int i2) {
        return new d(this, i, i2);
    }

    @Override // java.util.List
    public ListIterator<E> listIterator(int i) {
        return new c(i);
    }
}
