package defpackage;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: renamed from: q1, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1359q1 implements Set {
    public final /* synthetic */ AbstractC1578u1 s;

    public C1359q1(AbstractC1578u1 abstractC1578u1) {
        this.s = abstractC1578u1;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean addAll(Collection collection) {
        int iD = this.s.d();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            this.s.g(entry.getKey(), entry.getValue());
        }
        return iD != this.s.d();
    }

    @Override // java.util.Set, java.util.Collection
    public void clear() {
        this.s.a();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean contains(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        int iE = this.s.e(entry.getKey());
        if (iE < 0) {
            return false;
        }
        return AbstractC1139m1.c(this.s.b(iE, 1), entry.getValue());
    }

    @Override // java.util.Set, java.util.Collection
    public boolean containsAll(Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
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
            Object objB2 = this.s.b(iD, 1);
            iHashCode += (objB == null ? 0 : objB.hashCode()) ^ (objB2 == null ? 0 : objB2.hashCode());
        }
        return iHashCode;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean isEmpty() {
        return this.s.d() == 0;
    }

    @Override // java.util.Set, java.util.Collection, java.lang.Iterable
    public Iterator iterator() {
        return new C1468s1(this.s);
    }

    @Override // java.util.Set, java.util.Collection
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Set, java.util.Collection
    public int size() {
        return this.s.d();
    }

    @Override // java.util.Set, java.util.Collection
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Set, java.util.Collection
    public Object[] toArray(Object[] objArr) {
        throw new UnsupportedOperationException();
    }
}
