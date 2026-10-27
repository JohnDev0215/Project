package i5;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import kotlin.jvm.internal.l;

/* JADX INFO: compiled from: MapBuilder.kt */
/* JADX INFO: loaded from: classes.dex */
public final class e<K, V> extends a<Map.Entry<K, V>, K, V> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final d<K, V> f7035e;

    public e(d<K, V> backing) {
        l.e(backing, "backing");
        this.f7035e = backing;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean addAll(Collection<? extends Map.Entry<K, V>> elements) {
        l.e(elements, "elements");
        throw new UnsupportedOperationException();
    }

    @Override // h5.e
    public int c() {
        return this.f7035e.size();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        this.f7035e.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean containsAll(Collection<? extends Object> elements) {
        l.e(elements, "elements");
        return this.f7035e.n(elements);
    }

    @Override // i5.a
    public boolean e(Map.Entry<? extends K, ? extends V> element) {
        l.e(element, "element");
        return this.f7035e.o(element);
    }

    @Override // i5.a
    public boolean f(Map.Entry element) {
        l.e(element, "element");
        return this.f7035e.I(element);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public boolean add(Map.Entry<K, V> element) {
        l.e(element, "element");
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return this.f7035e.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<Map.Entry<K, V>> iterator() {
        return this.f7035e.s();
    }

    @Override // java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean removeAll(Collection<? extends Object> elements) {
        l.e(elements, "elements");
        this.f7035e.l();
        return super.removeAll(elements);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean retainAll(Collection<? extends Object> elements) {
        l.e(elements, "elements");
        this.f7035e.l();
        return super.retainAll(elements);
    }
}
