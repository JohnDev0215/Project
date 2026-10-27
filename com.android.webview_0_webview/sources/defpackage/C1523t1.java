package defpackage;

import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: renamed from: t1, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1523t1 implements Collection {
    public final /* synthetic */ AbstractC1578u1 s;

    public C1523t1(AbstractC1578u1 abstractC1578u1) {
        this.s = abstractC1578u1;
    }

    @Override // java.util.Collection
    public boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public boolean addAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public void clear() {
        this.s.a();
    }

    @Override // java.util.Collection
    public boolean contains(Object obj) {
        return this.s.f(obj) >= 0;
    }

    @Override // java.util.Collection
    public boolean containsAll(Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return this.s.d() == 0;
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Iterator iterator() {
        return new C1304p1(this.s, 1);
    }

    @Override // java.util.Collection
    public boolean remove(Object obj) {
        int iF = this.s.f(obj);
        if (iF < 0) {
            return false;
        }
        this.s.h(iF);
        return true;
    }

    @Override // java.util.Collection
    public boolean removeAll(Collection collection) {
        int iD = this.s.d();
        int i = 0;
        boolean z = false;
        while (i < iD) {
            if (collection.contains(this.s.b(i, 1))) {
                this.s.h(i);
                i--;
                iD--;
                z = true;
            }
            i++;
        }
        return z;
    }

    @Override // java.util.Collection
    public boolean retainAll(Collection collection) {
        int iD = this.s.d();
        int i = 0;
        boolean z = false;
        while (i < iD) {
            if (!collection.contains(this.s.b(i, 1))) {
                this.s.h(i);
                i--;
                iD--;
                z = true;
            }
            i++;
        }
        return z;
    }

    @Override // java.util.Collection
    public int size() {
        return this.s.d();
    }

    @Override // java.util.Collection
    public Object[] toArray() {
        return this.s.l(1);
    }

    @Override // java.util.Collection
    public Object[] toArray(Object[] objArr) {
        return this.s.m(objArr, 1);
    }
}
