package i5;

import java.util.Collection;
import java.util.Iterator;
import kotlin.jvm.internal.l;

/* JADX INFO: compiled from: MapBuilder.kt */
/* JADX INFO: loaded from: classes.dex */
public final class g<V> extends h5.c<V> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final d<?, V> f7037e;

    public g(d<?, V> backing) {
        l.e(backing, "backing");
        this.f7037e = backing;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean add(V v6) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean addAll(Collection<? extends V> elements) {
        l.e(elements, "elements");
        throw new UnsupportedOperationException();
    }

    @Override // h5.c
    public int c() {
        return this.f7037e.size();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public void clear() {
        this.f7037e.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean contains(Object obj) {
        return this.f7037e.containsValue(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return this.f7037e.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<V> iterator() {
        return this.f7037e.N();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean remove(Object obj) {
        return this.f7037e.M(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean removeAll(Collection<? extends Object> elements) {
        l.e(elements, "elements");
        this.f7037e.l();
        return super.removeAll(elements);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean retainAll(Collection<? extends Object> elements) {
        l.e(elements, "elements");
        this.f7037e.l();
        return super.retainAll(elements);
    }
}
