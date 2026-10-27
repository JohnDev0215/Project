package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import p.g81;
import p.l13;
import p.ou;
import p.ry0;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public abstract class a implements Collection, g81 {
    public abstract int a();

    @Override // java.util.Collection
    public final boolean add(Object obj) {
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
    public boolean contains(Object obj) {
        if (isEmpty()) {
            return false;
        }
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            if (v03.b(it.next(), obj)) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Collection
    public final boolean containsAll(Collection collection) {
        v03.j(collection, "elements");
        if (collection.isEmpty()) {
            return true;
        }
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Collection
    public final boolean isEmpty() {
        return size() == 0;
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
    public final boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final /* bridge */ int size() {
        return a();
    }

    @Override // java.util.Collection
    public final Object[] toArray() {
        return l13.n(this);
    }

    public final String toString() {
        return ou.h0(this, ", ", "[", "]", new ry0() { // from class: kotlin.collections.AbstractCollection$toString$1
            {
                super(1);
            }

            @Override // p.ry0
            public final Object invoke(Object obj) {
                return obj == this.this$0 ? "(this Collection)" : String.valueOf(obj);
            }
        }, 24);
    }

    @Override // java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        v03.j(objArr, "array");
        return l13.o(this, objArr);
    }
}
