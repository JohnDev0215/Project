package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX WARN: Unexpected interfaces in signature: [java.lang.Object] */
/* JADX INFO: renamed from: kotlin.collections.c, reason: case insensitive filesystem */
/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.1")
public abstract class AbstractC0112c<E> extends AbstractCollection<E> implements List<E> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final a f1486c = new a(null);

    /* JADX INFO: renamed from: kotlin.collections.c$a */
    /* JADX INFO: compiled from: caradapter-bug */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0080\u0003\u0018\u0000:\u0001\u0005B\t\b\u0012¢\u0006\u0004\b\u0013\u0010\u0014B\u0011\b\u0016\u0012\u0006\u0010\u0016\u001a\u00020\u0015¢\u0006\u0004\b\u0013\u0010\u0017J\u001d\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001¢\u0006\u0004\b\u0005\u0010\u0006J\u001d\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001¢\u0006\u0004\b\u0007\u0010\u0006J%\u0010\n\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u0015\u0010\r\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\u001d\u0010\u0011\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\f¢\u0006\u0004\b\u0011\u0010\u0012¨\u0006\u0018"}, d2 = {"Lkotlin/collections/c$a;", "Lkotlin/Int;", "index", "size", "Lkotlin/Unit;", "a", "(II)V", "b", "fromIndex", "toIndex", "c", "(III)V", "Ljava/util/Collection;", "e", "(Ljava/util/Collection;)I", "other", "Lkotlin/Boolean;", "d", "(Ljava/util/Collection;Ljava/util/Collection;)Z", "<init>", "()V", "Lkotlin/jvm/internal/o;", "p0", "(Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
    public static final class a {
        private a() {
        }

        public final void a(int index, int size) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
            }
        }

        public final void b(int index, int size) {
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
            }
        }

        public final void c(int fromIndex, int toIndex, int size) {
            if (fromIndex < 0 || toIndex > size) {
                throw new IndexOutOfBoundsException("fromIndex: " + fromIndex + ", toIndex: " + toIndex + ", size: " + size);
            }
            if (fromIndex <= toIndex) {
                return;
            }
            throw new IllegalArgumentException("fromIndex: " + fromIndex + " > toIndex: " + toIndex);
        }

        public final boolean d(@NotNull Collection<?> c2, @NotNull Collection<?> other) {
            kotlin.jvm.internal.q.c(c2, "c");
            kotlin.jvm.internal.q.c(other, "other");
            if (c2.size() != other.size()) {
                return false;
            }
            Iterator<?> it = other.iterator();
            Iterator<?> it2 = c2.iterator();
            while (it2.hasNext()) {
                if (!kotlin.jvm.internal.q.a(it2.next(), it.next())) {
                    return false;
                }
            }
            return true;
        }

        public final int e(@NotNull Collection<?> c2) {
            kotlin.jvm.internal.q.c(c2, "c");
            Iterator<?> it = c2.iterator();
            int iHashCode = 1;
            while (it.hasNext()) {
                Object next = it.next();
                iHashCode = (iHashCode * 31) + (next != null ? next.hashCode() : 0);
            }
            return iHashCode;
        }

        public /* synthetic */ a(kotlin.jvm.internal.o oVar) {
            this();
        }
    }

    /* JADX WARN: Unexpected interfaces in signature: [java.lang.Object] */
    /* JADX INFO: renamed from: kotlin.collections.c$b */
    /* JADX INFO: compiled from: caradapter-bug */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0092\u0004\u0018\u00002\u00020\u0001:\u0001\u000eB\u0011\b\u0016\u0012\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0006\u0010\u0007R*\u0010\n\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b8\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0014"}, d2 = {"Lkotlin/collections/c$b;", "Ljava/util/Iterator;", "Lkotlin/Boolean;", "hasNext", "()Z", "Lkotlin/Any;", "next", "()Ljava/lang/Object;", "Lkotlin/Int;", "value", "c", "I", "a", "()I", "b", "(I)V", "Lkotlin/collections/c;", "p0", "<init>", "(Lkotlin/collections/AbstractList;)V", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
    private class b implements Iterator<E> {
        private int c;

        public b() {
        }

        /* JADX INFO: renamed from: a, reason: from getter */
        protected final int getC() {
            return this.c;
        }

        protected final void b(int i) {
            this.c = i;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.c < AbstractC0112c.this.size();
        }

        @Override // java.util.Iterator
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            AbstractC0112c abstractC0112c = AbstractC0112c.this;
            int i = this.c;
            this.c = i + 1;
            return (E) abstractC0112c.get(i);
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    /* JADX WARN: Unexpected interfaces in signature: [java.lang.Object] */
    /* JADX INFO: renamed from: kotlin.collections.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: caradapter-bug */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0092\u0004\u0018\u00002\u00020\u00012\u00020\u0002:\u0001\u0012B\u0019\b\u0016\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u000f\u001a\u00020\u0006¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0004\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\f\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\f\u0010\b¨\u0006\u0013"}, d2 = {"Lkotlin/collections/c$c;", "Ljava/util/ListIterator;", "Lkotlin/collections/c$b;", "Lkotlin/Boolean;", "hasPrevious", "()Z", "Lkotlin/Int;", "nextIndex", "()I", "Lkotlin/Any;", "previous", "()Ljava/lang/Object;", "previousIndex", "Lkotlin/collections/c;", "p0", "p1", "<init>", "(Lkotlin/collections/AbstractList;I)V", "c", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
    private class C0046c extends AbstractC0112c<E>.b implements ListIterator<E> {
        public C0046c(int i) {
            super();
            AbstractC0112c.f1486c.b(i, AbstractC0112c.this.size());
            b(i);
        }

        @Override // java.util.ListIterator
        public void add(E e) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            return getC() > 0;
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return getC();
        }

        @Override // java.util.ListIterator
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            AbstractC0112c abstractC0112c = AbstractC0112c.this;
            b(getC() - 1);
            return (E) abstractC0112c.get(getC());
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return getC() - 1;
        }

        @Override // java.util.ListIterator
        public void set(E e) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    /* JADX INFO: renamed from: kotlin.collections.c$d */
    /* JADX INFO: compiled from: caradapter-bug */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0002\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\u00020\u00022\u00020\u0003:\u0001\tB!\b\u0016\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u0004\u0012\u0006\u0010\u0012\u001a\u00020\u0004¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0007\u0010\bR\u0016\u0010\t\u001a\u00020\u00048\u0000@\u0000X\u0082\u000e¢\u0006\u0006\n\u0004\b\t\u0010\nR\u0016\u0010\u000b\u001a\u00020\u00048\u0016@\u0000X\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\r\u001a\u00020\u00038\u0000@\u0000X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000eR\u0016\u0010\u000f\u001a\u00020\u00048\u0000@\u0000X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010\n¨\u0006\u0015"}, d2 = {"Lkotlin/collections/c$d;", "E", "Ljava/util/RandomAccess;", "Lkotlin/collections/c;", "Lkotlin/Int;", "index", "Lkotlin/Any;", "get", "(I)Ljava/lang/Object;", "d", "I", "a", "()I", "e", "Lkotlin/collections/c;", "f", "p0", "p1", "p2", "<init>", "(Lkotlin/collections/AbstractList;II)V", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
    private static final class d<E> extends AbstractC0112c<E> implements RandomAccess {
        private int d;
        private final AbstractC0112c<E> e;
        private final int f;

        /* JADX WARN: Multi-variable type inference failed */
        public d(@NotNull AbstractC0112c<? extends E> abstractC0112c, int i, int i2) {
            kotlin.jvm.internal.q.c(abstractC0112c, "list");
            this.e = abstractC0112c;
            this.f = i;
            AbstractC0112c.f1486c.c(i, i2, abstractC0112c.size());
            this.d = i2 - this.f;
        }

        @Override // kotlin.collections.AbstractCollection
        /* JADX INFO: renamed from: a, reason: from getter */
        public int getD() {
            return this.d;
        }

        @Override // kotlin.collections.AbstractC0112c, java.util.List
        public E get(int index) {
            AbstractC0112c.f1486c.a(index, this.d);
            return this.e.get(this.f + index);
        }
    }

    protected AbstractC0112c() {
    }

    @Override // java.util.List
    public void add(int i, E e) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public boolean addAll(int i, Collection<? extends E> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection, java.util.List
    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof List) {
            return f1486c.d(this, (Collection) obj);
        }
        return false;
    }

    public abstract E get(int i);

    @Override // java.util.Collection, java.util.List
    public int hashCode() {
        return f1486c.e(this);
    }

    @Override // java.util.List
    public int indexOf(Object obj) {
        Iterator<E> it = iterator();
        int i = 0;
        while (it.hasNext()) {
            if (kotlin.jvm.internal.q.a(it.next(), obj)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.List
    @NotNull
    public Iterator<E> iterator() {
        return new b();
    }

    @Override // java.util.List
    public int lastIndexOf(Object obj) {
        ListIterator<E> listIterator = listIterator(size());
        while (listIterator.hasPrevious()) {
            if (kotlin.jvm.internal.q.a(listIterator.previous(), obj)) {
                return listIterator.nextIndex();
            }
        }
        return -1;
    }

    @Override // java.util.List
    @NotNull
    public ListIterator<E> listIterator() {
        return new C0046c(0);
    }

    @Override // java.util.List
    public E remove(int i) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public E set(int i, E e) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    @NotNull
    public List<E> subList(int i, int i2) {
        return new d(this, i, i2);
    }

    @Override // java.util.List
    @NotNull
    public ListIterator<E> listIterator(int i) {
        return new C0046c(i);
    }
}
