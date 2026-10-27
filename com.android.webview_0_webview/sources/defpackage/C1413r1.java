package defpackage;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: renamed from: r1, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1413r1 implements Set {
    public final /* synthetic */ AbstractC1578u1 s;

    public C1413r1(AbstractC1578u1 abstractC1578u1) {
        this.s = abstractC1578u1;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean addAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Set, java.util.Collection
    public void clear() {
        this.s.a();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean contains(Object obj) {
        return this.s.e(obj) >= 0;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean containsAll(Collection collection) {
        Map mapC = this.s.c();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!mapC.containsKey(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean equals(Object obj) {
        return AbstractC1578u1.j(this, obj);
    }

    @Override // java.util.Set, java.util.Collection
    public int hashCode() {
        int iHashCode = 0;
        for (int iD = this.s.d() - 1; iD >= 0; iD--) {
            Object objB = this.s.b(iD, 0);
            iHashCode += objB == null ? 0 : objB.hashCode();
        }
        return iHashCode;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean isEmpty() {
        return this.s.d() == 0;
    }

    @Override // java.util.Set, java.util.Collection, java.lang.Iterable
    public Iterator iterator() {
        return new C1304p1(this.s, 0);
    }

    @Override // java.util.Set, java.util.Collection
    public boolean remove(Object obj) {
        int iE = this.s.e(obj);
        if (iE < 0) {
            return false;
        }
        this.s.h(iE);
        return true;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean removeAll(Collection collection) {
        Map mapC = this.s.c();
        int size = mapC.size();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            mapC.remove(it.next());
        }
        return size != mapC.size();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean retainAll(Collection collection) {
        return AbstractC1578u1.k(this.s.c(), collection);
    }

    @Override // java.util.Set, java.util.Collection
    public int size() {
        return this.s.d();
    }

    @Override // java.util.Set, java.util.Collection
    public Object[] toArray() {
        return this.s.l(0);
    }

    @Override // java.util.Set, java.util.Collection
    public Object[] toArray(Object[] objArr) {
        return this.s.m(objArr, 0);
    }
}
