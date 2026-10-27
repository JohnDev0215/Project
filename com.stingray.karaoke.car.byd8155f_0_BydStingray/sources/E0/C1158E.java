package e0;

import T.C0735d;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import x3.AbstractC2673a;

/* JADX INFO: renamed from: e0.E, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1158E implements List, B7.c {
    public final C1178r l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f13679m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f13680n;
    public int o;

    public C1158E(C1178r c1178r, int i7, int i10) {
        this.l = c1178r;
        this.f13679m = i7;
        this.f13680n = c1178r.s();
        this.o = i10 - i7;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean add(Object obj) {
        d();
        int i7 = this.f13679m + this.o;
        C1178r c1178r = this.l;
        c1178r.add(i7, obj);
        this.o++;
        this.f13680n = c1178r.s();
        return true;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean addAll(Collection collection) {
        return addAll(this.o, collection);
    }

    @Override // java.util.List, java.util.Collection
    public final void clear() {
        int i7;
        X.c cVar;
        AbstractC1167g abstractC1167gK;
        boolean z6;
        if (this.o > 0) {
            d();
            C1178r c1178r = this.l;
            int i10 = this.f13679m;
            int i11 = this.o + i10;
            c1178r.getClass();
            do {
                Object obj = AbstractC1179s.f13731a;
                synchronized (obj) {
                    C1177q c1177q = c1178r.l;
                    A7.m.d(c1177q, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
                    C1177q c1177q2 = (C1177q) AbstractC1174n.i(c1177q);
                    i7 = c1177q2.d;
                    cVar = c1177q2.c;
                }
                A7.m.c(cVar);
                X.g gVarS = cVar.s();
                gVarS.subList(i10, i11).clear();
                X.c cVarQ = gVarS.q();
                if (A7.m.a(cVarQ, cVar)) {
                    break;
                }
                C1177q c1177q3 = c1178r.l;
                A7.m.d(c1177q3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
                synchronized (AbstractC1174n.f13721b) {
                    abstractC1167gK = AbstractC1174n.k();
                    C1177q c1177q4 = (C1177q) AbstractC1174n.w(c1177q3, c1178r, abstractC1167gK);
                    synchronized (obj) {
                        int i12 = c1177q4.d;
                        if (i12 == i7) {
                            c1177q4.c = cVarQ;
                            c1177q4.d = i12 + 1;
                            z6 = true;
                            c1177q4.f13730e++;
                        } else {
                            z6 = false;
                        }
                    }
                }
                AbstractC1174n.n(abstractC1167gK, c1178r);
            } while (!z6);
            this.o = 0;
            this.f13680n = this.l.s();
        }
    }

    @Override // java.util.List, java.util.Collection
    public final boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean containsAll(Collection collection) {
        Collection collection2 = collection;
        if ((collection2 instanceof Collection) && collection2.isEmpty()) {
            return true;
        }
        Iterator it = collection2.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    public final void d() {
        if (this.l.s() != this.f13680n) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.List
    public final Object get(int i7) {
        d();
        AbstractC1179s.a(i7, this.o);
        return this.l.get(this.f13679m + i7);
    }

    @Override // java.util.List
    public final int indexOf(Object obj) {
        d();
        int i7 = this.o;
        int i10 = this.f13679m;
        Iterator it = AbstractC2673a.b0(i10, i7 + i10).iterator();
        while (it.hasNext()) {
            int iB = ((n7.y) it).b();
            if (A7.m.a(obj, this.l.get(iB))) {
                return iB - i10;
            }
        }
        return -1;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean isEmpty() {
        return this.o == 0;
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    public final int lastIndexOf(Object obj) {
        d();
        int i7 = this.o;
        int i10 = this.f13679m;
        for (int i11 = (i7 + i10) - 1; i11 >= i10; i11--) {
            if (A7.m.a(obj, this.l.get(i11))) {
                return i11 - i10;
            }
        }
        return -1;
    }

    @Override // java.util.List
    public final ListIterator listIterator() {
        return listIterator(0);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean remove(Object obj) {
        int iIndexOf = indexOf(obj);
        if (iIndexOf < 0) {
            return false;
        }
        remove(iIndexOf);
        return true;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean removeAll(Collection collection) {
        Iterator it = collection.iterator();
        while (true) {
            boolean z6 = false;
            while (it.hasNext()) {
                if (remove(it.next()) || z6) {
                    z6 = true;
                }
            }
            return z6;
        }
    }

    @Override // java.util.List, java.util.Collection
    public final boolean retainAll(Collection collection) {
        int i7;
        X.c cVar;
        AbstractC1167g abstractC1167gK;
        boolean z6;
        d();
        C1178r c1178r = this.l;
        int i10 = this.f13679m;
        int i11 = this.o + i10;
        int size = c1178r.size();
        do {
            Object obj = AbstractC1179s.f13731a;
            synchronized (obj) {
                C1177q c1177q = c1178r.l;
                A7.m.d(c1177q, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
                C1177q c1177q2 = (C1177q) AbstractC1174n.i(c1177q);
                i7 = c1177q2.d;
                cVar = c1177q2.c;
            }
            A7.m.c(cVar);
            X.g gVarS = cVar.s();
            gVarS.subList(i10, i11).retainAll(collection);
            X.c cVarQ = gVarS.q();
            if (A7.m.a(cVarQ, cVar)) {
                break;
            }
            C1177q c1177q3 = c1178r.l;
            A7.m.d(c1177q3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
            synchronized (AbstractC1174n.f13721b) {
                abstractC1167gK = AbstractC1174n.k();
                C1177q c1177q4 = (C1177q) AbstractC1174n.w(c1177q3, c1178r, abstractC1167gK);
                synchronized (obj) {
                    int i12 = c1177q4.d;
                    if (i12 == i7) {
                        c1177q4.c = cVarQ;
                        c1177q4.d = i12 + 1;
                        c1177q4.f13730e++;
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                }
            }
            AbstractC1174n.n(abstractC1167gK, c1178r);
        } while (!z6);
        int size2 = size - c1178r.size();
        if (size2 > 0) {
            this.f13680n = this.l.s();
            this.o -= size2;
        }
        return size2 > 0;
    }

    @Override // java.util.List
    public final Object set(int i7, Object obj) {
        AbstractC1179s.a(i7, this.o);
        d();
        int i10 = i7 + this.f13679m;
        C1178r c1178r = this.l;
        Object obj2 = c1178r.set(i10, obj);
        this.f13680n = c1178r.s();
        return obj2;
    }

    @Override // java.util.List, java.util.Collection
    public final int size() {
        return this.o;
    }

    @Override // java.util.List
    public final List subList(int i7, int i10) {
        if (!(i7 >= 0 && i7 <= i10 && i10 <= this.o)) {
            C0735d.X("fromIndex or toIndex are out of bounds");
            throw null;
        }
        d();
        int i11 = this.f13679m;
        return new C1158E(this.l, i7 + i11, i10 + i11);
    }

    @Override // java.util.List, java.util.Collection
    public final Object[] toArray() {
        return A7.l.a(this);
    }

    @Override // java.util.List
    public final ListIterator listIterator(int i7) {
        d();
        A7.x xVar = new A7.x();
        xVar.l = i7 - 1;
        return new C1157D(xVar, this);
    }

    @Override // java.util.List, java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        return A7.l.b(this, objArr);
    }

    @Override // java.util.List
    public final boolean addAll(int i7, Collection collection) {
        d();
        int i10 = i7 + this.f13679m;
        C1178r c1178r = this.l;
        boolean zAddAll = c1178r.addAll(i10, collection);
        if (zAddAll) {
            this.o = collection.size() + this.o;
            this.f13680n = c1178r.s();
        }
        return zAddAll;
    }

    @Override // java.util.List
    public final Object remove(int i7) {
        d();
        int i10 = this.f13679m + i7;
        C1178r c1178r = this.l;
        Object objRemove = c1178r.remove(i10);
        this.o--;
        this.f13680n = c1178r.s();
        return objRemove;
    }

    @Override // java.util.List
    public final void add(int i7, Object obj) {
        d();
        int i10 = this.f13679m + i7;
        C1178r c1178r = this.l;
        c1178r.add(i10, obj);
        this.o++;
        this.f13680n = c1178r.s();
    }
}
