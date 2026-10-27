package U0;

import A7.l;
import A7.m;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import n7.u;

/* JADX INFO: loaded from: classes.dex */
public final class b implements Collection, B7.a {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final b f8957n = new b(u.l);
    public final List l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f8958m;

    public b(List list) {
        this.l = list;
        this.f8958m = list.size();
    }

    @Override // java.util.Collection
    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final boolean contains(Object obj) {
        if (!(obj instanceof a)) {
            return false;
        }
        return this.l.contains((a) obj);
    }

    @Override // java.util.Collection
    public final boolean containsAll(Collection collection) {
        return this.l.containsAll(collection);
    }

    @Override // java.util.Collection
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof b) {
            return m.a(this.l, ((b) obj).l);
        }
        return false;
    }

    @Override // java.util.Collection
    public final int hashCode() {
        return this.l.hashCode();
    }

    @Override // java.util.Collection
    public final boolean isEmpty() {
        return this.l.isEmpty();
    }

    @Override // java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return this.l.iterator();
    }

    @Override // java.util.Collection
    public final boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final boolean removeIf(Predicate predicate) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final int size() {
        return this.f8958m;
    }

    @Override // java.util.Collection
    public final Object[] toArray() {
        return l.a(this);
    }

    public final String toString() {
        return "LocaleList(localeList=" + this.l + ')';
    }

    @Override // java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        return l.b(this, objArr);
    }
}
