package C0;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes.dex */
public final class h0 implements Collection, B7.a {
    public final Set l = new LinkedHashSet();

    @Override // java.util.Collection
    public final boolean add(Object obj) {
        return this.l.add(obj);
    }

    @Override // java.util.Collection
    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final void clear() {
        this.l.clear();
    }

    @Override // java.util.Collection
    public final boolean contains(Object obj) {
        return this.l.contains(obj);
    }

    @Override // java.util.Collection
    public final boolean containsAll(Collection collection) {
        return this.l.containsAll(collection);
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
        return this.l.remove(obj);
    }

    @Override // java.util.Collection
    public final boolean removeAll(Collection collection) {
        return this.l.remove(collection);
    }

    @Override // java.util.Collection
    public final boolean removeIf(Predicate predicate) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final boolean retainAll(Collection collection) {
        return this.l.retainAll(collection);
    }

    @Override // java.util.Collection
    public final int size() {
        return this.l.size();
    }

    @Override // java.util.Collection
    public final Object[] toArray() {
        return A7.l.a(this);
    }

    @Override // java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        return A7.l.b(this, objArr);
    }
}
