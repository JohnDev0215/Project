package w6;

import A7.l;
import A7.m;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/* JADX INFO: loaded from: classes.dex */
public final class b implements d, List, B7.c {
    public final ArrayList l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f23340m;

    public b() {
        this.l = new ArrayList();
    }

    @Override // java.util.List
    public final void add(int i7, Object obj) {
        d dVar = (d) obj;
        m.f(dVar, "element");
        this.f23340m = true;
        this.l.add(i7, dVar);
    }

    @Override // java.util.List
    public final boolean addAll(int i7, Collection collection) {
        m.f(collection, "elements");
        this.f23340m = true;
        return this.l.addAll(i7, collection);
    }

    @Override // java.util.List, java.util.Collection
    public final void clear() {
        this.f23340m = true;
        this.l.clear();
    }

    @Override // java.util.List, java.util.Collection
    public final boolean contains(Object obj) {
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        m.f(dVar, "element");
        return this.l.contains(dVar);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean containsAll(Collection collection) {
        m.f(collection, "elements");
        return this.l.containsAll(collection);
    }

    @Override // w6.d
    public final boolean d() {
        ArrayList arrayList;
        if (!this.f23340m && ((arrayList = this.l) == null || !arrayList.isEmpty())) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                if (((d) it.next()).d()) {
                    return true;
                }
            }
        }
        return this.f23340m;
    }

    @Override // java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof d)) {
            return -1;
        }
        d dVar = (d) obj;
        m.f(dVar, "element");
        return this.l.indexOf(dVar);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean isEmpty() {
        return this.l.isEmpty();
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return this.l.iterator();
    }

    @Override // w6.d
    public final void l() {
        this.f23340m = false;
        Iterator it = this.l.iterator();
        while (it.hasNext()) {
            ((d) it.next()).l();
        }
    }

    @Override // java.util.List
    public final int lastIndexOf(Object obj) {
        if (!(obj instanceof d)) {
            return -1;
        }
        d dVar = (d) obj;
        m.f(dVar, "element");
        return this.l.indexOf(dVar);
    }

    @Override // java.util.List
    public final ListIterator listIterator() {
        return this.l.listIterator();
    }

    @Override // java.util.List, java.util.Collection
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final boolean add(d dVar) {
        m.f(dVar, "element");
        this.f23340m = true;
        return this.l.add(dVar);
    }

    @Override // java.util.List
    /* JADX INFO: renamed from: r, reason: merged with bridge method [inline-methods] */
    public final d get(int i7) {
        return (d) this.l.get(i7);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean remove(Object obj) {
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        m.f(dVar, "element");
        this.f23340m = true;
        return this.l.remove(dVar);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean removeAll(Collection collection) {
        m.f(collection, "elements");
        this.f23340m = true;
        return this.l.removeAll(collection);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean retainAll(Collection collection) {
        m.f(collection, "elements");
        this.f23340m = true;
        return this.l.retainAll(collection);
    }

    @Override // java.util.List
    public final Object set(int i7, Object obj) {
        d dVar = (d) obj;
        m.f(dVar, "element");
        this.f23340m = true;
        return (d) this.l.set(i7, dVar);
    }

    @Override // java.util.List, java.util.Collection
    public final int size() {
        return this.l.size();
    }

    @Override // java.util.List
    public final List subList(int i7, int i10) {
        return this.l.subList(i7, i10);
    }

    @Override // java.util.List, java.util.Collection
    public final Object[] toArray() {
        return l.a(this);
    }

    @Override // java.util.List
    public final ListIterator listIterator(int i7) {
        return this.l.listIterator(i7);
    }

    @Override // java.util.List, java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        m.f(objArr, "array");
        return l.b(this, objArr);
    }

    public b(b bVar) {
        m.f(bVar, "items");
        ArrayList arrayList = new ArrayList();
        this.l = arrayList;
        arrayList.addAll(bVar);
        this.f23340m = true;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean addAll(Collection collection) {
        m.f(collection, "elements");
        this.f23340m = true;
        return this.l.addAll(collection);
    }

    @Override // java.util.List
    public final Object remove(int i7) {
        this.f23340m = true;
        return (d) this.l.remove(i7);
    }
}
