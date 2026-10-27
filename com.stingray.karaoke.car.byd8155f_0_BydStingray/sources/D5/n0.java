package d5;

import c5.InterfaceC1028e;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class n0 extends AbstractCollection implements Set {
    public final Set l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final InterfaceC1028e f13445m;

    public n0(Set set, InterfaceC1028e interfaceC1028e) {
        this.l = set;
        this.f13445m = interfaceC1028e;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean add(Object obj) {
        if (this.f13445m.apply(obj)) {
            return this.l.add(obj);
        }
        throw new IllegalArgumentException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean addAll(Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!this.f13445m.apply(it.next())) {
                throw new IllegalArgumentException();
            }
        }
        return this.l.addAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        Set set = this.l;
        boolean z6 = set instanceof RandomAccess;
        InterfaceC1028e interfaceC1028e = this.f13445m;
        if (!z6 || !(set instanceof List)) {
            Iterator it = set.iterator();
            interfaceC1028e.getClass();
            while (it.hasNext()) {
                if (interfaceC1028e.apply(it.next())) {
                    it.remove();
                }
            }
            return;
        }
        List list = (List) set;
        interfaceC1028e.getClass();
        int i7 = 0;
        for (int i10 = 0; i10 < list.size(); i10++) {
            Object obj = list.get(i10);
            if (!interfaceC1028e.apply(obj)) {
                if (i10 > i7) {
                    try {
                        list.set(i7, obj);
                    } catch (IllegalArgumentException unused) {
                        AbstractC1095q.s(list, interfaceC1028e, i7, i10);
                        return;
                    } catch (UnsupportedOperationException unused2) {
                        AbstractC1095q.s(list, interfaceC1028e, i7, i10);
                        return;
                    }
                }
                i7++;
            }
        }
        list.subList(i7, list.size()).clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        boolean zContains;
        Set set = this.l;
        set.getClass();
        try {
            zContains = set.contains(obj);
        } catch (ClassCastException | NullPointerException unused) {
            zContains = false;
        }
        if (zContains) {
            return this.f13445m.apply(obj);
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean containsAll(Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean equals(Object obj) {
        return AbstractC1095q.j(this, obj);
    }

    @Override // java.util.Collection, java.util.Set
    public final int hashCode() {
        return AbstractC1095q.n(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        Iterator it = this.l.iterator();
        InterfaceC1028e interfaceC1028e = this.f13445m;
        P8.n.q(interfaceC1028e, "predicate");
        int i7 = 0;
        while (true) {
            if (!it.hasNext()) {
                i7 = -1;
                break;
            }
            if (interfaceC1028e.apply(it.next())) {
                break;
            }
            i7++;
        }
        return true ^ (i7 != -1);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        Iterator it = this.l.iterator();
        it.getClass();
        InterfaceC1028e interfaceC1028e = this.f13445m;
        interfaceC1028e.getClass();
        return new N(it, interfaceC1028e);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        return contains(obj) && this.l.remove(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean removeAll(Collection collection) {
        Iterator it = this.l.iterator();
        boolean z6 = false;
        while (it.hasNext()) {
            Object next = it.next();
            if (this.f13445m.apply(next) && collection.contains(next)) {
                it.remove();
                z6 = true;
            }
        }
        return z6;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean retainAll(Collection collection) {
        Iterator it = this.l.iterator();
        boolean z6 = false;
        while (it.hasNext()) {
            Object next = it.next();
            if (this.f13445m.apply(next) && !collection.contains(next)) {
                it.remove();
                z6 = true;
            }
        }
        return z6;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        Iterator it = this.l.iterator();
        int i7 = 0;
        while (it.hasNext()) {
            if (this.f13445m.apply(it.next())) {
                i7++;
            }
        }
        return i7;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final Object[] toArray() {
        return AbstractC1095q.q(iterator()).toArray();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final Object[] toArray(Object[] objArr) {
        return AbstractC1095q.q(iterator()).toArray(objArr);
    }
}
