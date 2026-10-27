package d5;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* JADX INFO: renamed from: d5.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C1090l extends AbstractCollection implements List {
    public final Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Collection f13439m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final C1090l f13440n;
    public final Collection o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Y f13441p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Y f13442q;

    public C1090l(Y y10, Object obj, List list, C1090l c1090l) {
        this.f13442q = y10;
        this.f13441p = y10;
        this.l = obj;
        this.f13439m = list;
        this.f13440n = c1090l;
        this.o = c1090l == null ? null : c1090l.f13439m;
    }

    public final void a() {
        C1090l c1090l = this.f13440n;
        if (c1090l != null) {
            c1090l.a();
        } else {
            this.f13441p.o.put(this.l, this.f13439m);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        d();
        boolean zIsEmpty = this.f13439m.isEmpty();
        boolean zAdd = this.f13439m.add(obj);
        if (zAdd) {
            this.f13441p.f13401p++;
            if (zIsEmpty) {
                a();
            }
        }
        return zAdd;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean zAddAll = this.f13439m.addAll(collection);
        if (zAddAll) {
            this.f13441p.f13401p += this.f13439m.size() - size;
            if (size == 0) {
                a();
            }
        }
        return zAddAll;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        int size = size();
        if (size == 0) {
            return;
        }
        this.f13439m.clear();
        this.f13441p.f13401p -= size;
        l();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        d();
        return this.f13439m.contains(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean containsAll(Collection collection) {
        d();
        return this.f13439m.containsAll(collection);
    }

    public final void d() {
        Collection collection;
        C1090l c1090l = this.f13440n;
        if (c1090l != null) {
            c1090l.d();
            if (c1090l.f13439m != this.o) {
                throw new ConcurrentModificationException();
            }
        } else {
            if (!this.f13439m.isEmpty() || (collection = (Collection) this.f13441p.o.get(this.l)) == null) {
                return;
            }
            this.f13439m = collection;
        }
    }

    @Override // java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        d();
        return this.f13439m.equals(obj);
    }

    @Override // java.util.List
    public final Object get(int i7) {
        d();
        return ((List) this.f13439m).get(i7);
    }

    @Override // java.util.Collection, java.util.List
    public final int hashCode() {
        d();
        return this.f13439m.hashCode();
    }

    @Override // java.util.List
    public final int indexOf(Object obj) {
        d();
        return ((List) this.f13439m).indexOf(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        d();
        return new C1081c(this);
    }

    public final void l() {
        C1090l c1090l = this.f13440n;
        if (c1090l != null) {
            c1090l.l();
        } else if (this.f13439m.isEmpty()) {
            this.f13441p.o.remove(this.l);
        }
    }

    @Override // java.util.List
    public final int lastIndexOf(Object obj) {
        d();
        return ((List) this.f13439m).lastIndexOf(obj);
    }

    @Override // java.util.List
    public final ListIterator listIterator() {
        d();
        return new C1089k(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        d();
        boolean zRemove = this.f13439m.remove(obj);
        if (zRemove) {
            Y y10 = this.f13441p;
            y10.f13401p--;
            l();
        }
        return zRemove;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean removeAll(Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean zRemoveAll = this.f13439m.removeAll(collection);
        if (zRemoveAll) {
            this.f13441p.f13401p += this.f13439m.size() - size;
            l();
        }
        return zRemoveAll;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean retainAll(Collection collection) {
        collection.getClass();
        int size = size();
        boolean zRetainAll = this.f13439m.retainAll(collection);
        if (zRetainAll) {
            this.f13441p.f13401p += this.f13439m.size() - size;
            l();
        }
        return zRetainAll;
    }

    @Override // java.util.List
    public final Object set(int i7, Object obj) {
        d();
        return ((List) this.f13439m).set(i7, obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        d();
        return this.f13439m.size();
    }

    @Override // java.util.List
    public final List subList(int i7, int i10) {
        d();
        List listSubList = ((List) this.f13439m).subList(i7, i10);
        C1090l c1090l = this.f13440n;
        if (c1090l == null) {
            c1090l = this;
        }
        Y y10 = this.f13442q;
        y10.getClass();
        boolean z6 = listSubList instanceof RandomAccess;
        Object obj = this.l;
        return z6 ? new C1086h(y10, obj, listSubList, c1090l) : new C1090l(y10, obj, listSubList, c1090l);
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        d();
        return this.f13439m.toString();
    }

    @Override // java.util.List
    public final ListIterator listIterator(int i7) {
        d();
        return new C1089k(this, i7);
    }

    @Override // java.util.List
    public final Object remove(int i7) {
        d();
        Object objRemove = ((List) this.f13439m).remove(i7);
        Y y10 = this.f13442q;
        y10.f13401p--;
        l();
        return objRemove;
    }

    @Override // java.util.List
    public final void add(int i7, Object obj) {
        d();
        boolean zIsEmpty = this.f13439m.isEmpty();
        ((List) this.f13439m).add(i7, obj);
        this.f13442q.f13401p++;
        if (zIsEmpty) {
            a();
        }
    }

    @Override // java.util.List
    public final boolean addAll(int i7, Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean zAddAll = ((List) this.f13439m).addAll(i7, collection);
        if (zAddAll) {
            this.f13442q.f13401p += this.f13439m.size() - size;
            if (size == 0) {
                a();
            }
        }
        return zAddAll;
    }
}
