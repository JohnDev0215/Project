package i5;

import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.jvm.internal.l;

/* JADX INFO: compiled from: SetBuilder.kt */
/* JADX INFO: loaded from: classes.dex */
public final class j<E> extends h5.e<E> implements Serializable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final d<E, ?> f7043e;

    public j(d<E, ?> backing) {
        l.e(backing, "backing");
        this.f7043e = backing;
    }

    private final Object writeReplace() throws NotSerializableException {
        if (this.f7043e.C()) {
            return new h(this, 1);
        }
        throw new NotSerializableException("The set cannot be serialized while it is being built.");
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean add(E e7) {
        return this.f7043e.i(e7) >= 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean addAll(Collection<? extends E> elements) {
        l.e(elements, "elements");
        this.f7043e.l();
        return super.addAll(elements);
    }

    @Override // h5.e
    public int c() {
        return this.f7043e.size();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        this.f7043e.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        return this.f7043e.containsKey(obj);
    }

    public final Set<E> d() {
        this.f7043e.k();
        return this;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return this.f7043e.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<E> iterator() {
        return this.f7043e.D();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean remove(Object obj) {
        return this.f7043e.K(obj) >= 0;
    }

    @Override // java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean removeAll(Collection<? extends Object> elements) {
        l.e(elements, "elements");
        this.f7043e.l();
        return super.removeAll(elements);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean retainAll(Collection<? extends Object> elements) {
        l.e(elements, "elements");
        this.f7043e.l();
        return super.retainAll(elements);
    }

    public j() {
        this(new d());
    }

    public j(int i7) {
        this(new d(i7));
    }
}
