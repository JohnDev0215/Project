package kotlin.collections.builders;

import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import p.ie1;
import p.t1;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class SetBuilder<E> extends t1 implements Serializable {
    private final MapBuilder<E, ?> backing;

    public SetBuilder(MapBuilder mapBuilder) {
        this.backing = mapBuilder;
    }

    private final Object writeReplace() throws NotSerializableException {
        if (this.backing.o()) {
            return new SerializedCollection(1, this);
        }
        throw new NotSerializableException("The set cannot be serialized while it is being built.");
    }

    @Override // p.t1
    public final int a() {
        return this.backing.size();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean add(Object obj) {
        return this.backing.e(obj) >= 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean addAll(Collection collection) {
        v03.j(collection, "elements");
        this.backing.h();
        return super.addAll(collection);
    }

    public final void b() {
        this.backing.g();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.backing.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        return this.backing.containsKey(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        return this.backing.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        MapBuilder<E, ?> mapBuilder = this.backing;
        mapBuilder.getClass();
        return new ie1(mapBuilder, 1);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        MapBuilder<E, ?> mapBuilder = this.backing;
        mapBuilder.h();
        int iL = mapBuilder.l(obj);
        if (iL >= 0) {
            mapBuilder.r(iL);
            if (iL >= 0) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean removeAll(Collection collection) {
        v03.j(collection, "elements");
        this.backing.h();
        return super.removeAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean retainAll(Collection collection) {
        v03.j(collection, "elements");
        this.backing.h();
        return super.retainAll(collection);
    }

    public SetBuilder() {
        this(new MapBuilder());
    }
}
