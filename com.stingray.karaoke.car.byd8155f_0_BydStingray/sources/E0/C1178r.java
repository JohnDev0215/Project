package e0;

import E0.C0167q;
import T.C0735d;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: e0.r, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1178r implements InterfaceC1154A, List, RandomAccess, B7.c {
    public C1177q l;

    public C1178r() {
        X.j jVar = X.j.f9965m;
        C1177q c1177q = new C1177q(jVar);
        if (AbstractC1174n.f13720a.p() != null) {
            C1177q c1177q2 = new C1177q(jVar);
            c1177q2.f13675a = 1;
            c1177q.f13676b = c1177q2;
        }
        this.l = c1177q;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean add(Object obj) {
        int i7;
        X.c cVar;
        boolean z6;
        AbstractC1167g abstractC1167gK;
        do {
            Object obj2 = AbstractC1179s.f13731a;
            synchronized (obj2) {
                C1177q c1177q = this.l;
                A7.m.d(c1177q, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
                C1177q c1177q2 = (C1177q) AbstractC1174n.i(c1177q);
                i7 = c1177q2.d;
                cVar = c1177q2.c;
            }
            A7.m.c(cVar);
            X.c cVarQ = cVar.q(obj);
            z6 = false;
            if (cVarQ.equals(cVar)) {
                return false;
            }
            C1177q c1177q3 = this.l;
            A7.m.d(c1177q3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
            synchronized (AbstractC1174n.f13721b) {
                abstractC1167gK = AbstractC1174n.k();
                C1177q c1177q4 = (C1177q) AbstractC1174n.w(c1177q3, this, abstractC1167gK);
                synchronized (obj2) {
                    int i10 = c1177q4.d;
                    if (i10 == i7) {
                        c1177q4.c = cVarQ;
                        c1177q4.f13730e++;
                        c1177q4.d = i10 + 1;
                        z6 = true;
                    }
                }
            }
            AbstractC1174n.n(abstractC1167gK, this);
        } while (!z6);
        return true;
    }

    @Override // java.util.List
    public final boolean addAll(int i7, Collection collection) {
        return t(new E.x(i7, collection));
    }

    @Override // java.util.List, java.util.Collection
    public final void clear() {
        AbstractC1167g abstractC1167gK;
        C1177q c1177q = this.l;
        A7.m.d(c1177q, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
        synchronized (AbstractC1174n.f13721b) {
            abstractC1167gK = AbstractC1174n.k();
            C1177q c1177q2 = (C1177q) AbstractC1174n.w(c1177q, this, abstractC1167gK);
            synchronized (AbstractC1179s.f13731a) {
                c1177q2.c = X.j.f9965m;
                c1177q2.d++;
                c1177q2.f13730e++;
            }
        }
        AbstractC1174n.n(abstractC1167gK, this);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean contains(Object obj) {
        return r().c.contains(obj);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean containsAll(Collection collection) {
        return r().c.containsAll(collection);
    }

    @Override // e0.InterfaceC1154A
    public final AbstractC1156C d() {
        return this.l;
    }

    @Override // java.util.List
    public final Object get(int i7) {
        return r().c.get(i7);
    }

    @Override // java.util.List
    public final int indexOf(Object obj) {
        return r().c.indexOf(obj);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean isEmpty() {
        return r().c.isEmpty();
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return listIterator();
    }

    @Override // java.util.List
    public final int lastIndexOf(Object obj) {
        return r().c.lastIndexOf(obj);
    }

    @Override // java.util.List
    public final ListIterator listIterator() {
        return new C0167q(this, 0);
    }

    @Override // e0.InterfaceC1154A
    public final void q(AbstractC1156C abstractC1156C) {
        abstractC1156C.f13676b = this.l;
        this.l = (C1177q) abstractC1156C;
    }

    public final C1177q r() {
        C1177q c1177q = this.l;
        A7.m.d(c1177q, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
        return (C1177q) AbstractC1174n.t(c1177q, this);
    }

    @Override // java.util.List
    public final Object remove(int i7) {
        int i10;
        X.c cVar;
        AbstractC1167g abstractC1167gK;
        boolean z6;
        Object obj = get(i7);
        do {
            Object obj2 = AbstractC1179s.f13731a;
            synchronized (obj2) {
                C1177q c1177q = this.l;
                A7.m.d(c1177q, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
                C1177q c1177q2 = (C1177q) AbstractC1174n.i(c1177q);
                i10 = c1177q2.d;
                cVar = c1177q2.c;
            }
            A7.m.c(cVar);
            X.c cVarU = cVar.u(i7);
            if (A7.m.a(cVarU, cVar)) {
                break;
            }
            C1177q c1177q3 = this.l;
            A7.m.d(c1177q3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
            synchronized (AbstractC1174n.f13721b) {
                abstractC1167gK = AbstractC1174n.k();
                C1177q c1177q4 = (C1177q) AbstractC1174n.w(c1177q3, this, abstractC1167gK);
                synchronized (obj2) {
                    int i11 = c1177q4.d;
                    if (i11 == i10) {
                        c1177q4.c = cVarU;
                        z6 = true;
                        c1177q4.f13730e++;
                        c1177q4.d = i11 + 1;
                    } else {
                        z6 = false;
                    }
                }
            }
            AbstractC1174n.n(abstractC1167gK, this);
        } while (!z6);
        return obj;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean removeAll(Collection collection) {
        int i7;
        X.c cVar;
        boolean z6;
        AbstractC1167g abstractC1167gK;
        do {
            Object obj = AbstractC1179s.f13731a;
            synchronized (obj) {
                C1177q c1177q = this.l;
                A7.m.d(c1177q, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
                C1177q c1177q2 = (C1177q) AbstractC1174n.i(c1177q);
                i7 = c1177q2.d;
                cVar = c1177q2.c;
            }
            A7.m.c(cVar);
            X.c cVarT = cVar.t(new X.b(0, collection));
            z6 = false;
            if (A7.m.a(cVarT, cVar)) {
                return false;
            }
            C1177q c1177q3 = this.l;
            A7.m.d(c1177q3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
            synchronized (AbstractC1174n.f13721b) {
                abstractC1167gK = AbstractC1174n.k();
                C1177q c1177q4 = (C1177q) AbstractC1174n.w(c1177q3, this, abstractC1167gK);
                synchronized (obj) {
                    int i10 = c1177q4.d;
                    if (i10 == i7) {
                        c1177q4.c = cVarT;
                        c1177q4.f13730e++;
                        c1177q4.d = i10 + 1;
                        z6 = true;
                    }
                }
            }
            AbstractC1174n.n(abstractC1167gK, this);
        } while (!z6);
        return true;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean retainAll(Collection collection) {
        return t(new X.b(2, collection));
    }

    public final int s() {
        C1177q c1177q = this.l;
        A7.m.d(c1177q, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
        return ((C1177q) AbstractC1174n.i(c1177q)).f13730e;
    }

    @Override // java.util.List
    public final Object set(int i7, Object obj) {
        int i10;
        X.c cVar;
        AbstractC1167g abstractC1167gK;
        boolean z6;
        Object obj2 = get(i7);
        do {
            Object obj3 = AbstractC1179s.f13731a;
            synchronized (obj3) {
                C1177q c1177q = this.l;
                A7.m.d(c1177q, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
                C1177q c1177q2 = (C1177q) AbstractC1174n.i(c1177q);
                i10 = c1177q2.d;
                cVar = c1177q2.c;
            }
            A7.m.c(cVar);
            X.c cVarV = cVar.v(i7, obj);
            if (cVarV.equals(cVar)) {
                break;
            }
            C1177q c1177q3 = this.l;
            A7.m.d(c1177q3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
            synchronized (AbstractC1174n.f13721b) {
                abstractC1167gK = AbstractC1174n.k();
                C1177q c1177q4 = (C1177q) AbstractC1174n.w(c1177q3, this, abstractC1167gK);
                synchronized (obj3) {
                    int i11 = c1177q4.d;
                    if (i11 == i10) {
                        c1177q4.c = cVarV;
                        c1177q4.d = i11 + 1;
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                }
            }
            AbstractC1174n.n(abstractC1167gK, this);
        } while (!z6);
        return obj2;
    }

    @Override // java.util.List, java.util.Collection
    public final int size() {
        return r().c.d();
    }

    @Override // java.util.List
    public final List subList(int i7, int i10) {
        if (i7 >= 0 && i7 <= i10 && i10 <= size()) {
            return new C1158E(this, i7, i10);
        }
        C0735d.X("fromIndex or toIndex are out of bounds");
        throw null;
    }

    public final boolean t(InterfaceC2782k interfaceC2782k) {
        int i7;
        X.c cVar;
        Object objB;
        AbstractC1167g abstractC1167gK;
        boolean z6;
        do {
            Object obj = AbstractC1179s.f13731a;
            synchronized (obj) {
                C1177q c1177q = this.l;
                A7.m.d(c1177q, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
                C1177q c1177q2 = (C1177q) AbstractC1174n.i(c1177q);
                i7 = c1177q2.d;
                cVar = c1177q2.c;
            }
            A7.m.c(cVar);
            X.g gVarS = cVar.s();
            objB = interfaceC2782k.b(gVarS);
            X.c cVarQ = gVarS.q();
            if (A7.m.a(cVarQ, cVar)) {
                break;
            }
            C1177q c1177q3 = this.l;
            A7.m.d(c1177q3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
            synchronized (AbstractC1174n.f13721b) {
                abstractC1167gK = AbstractC1174n.k();
                C1177q c1177q4 = (C1177q) AbstractC1174n.w(c1177q3, this, abstractC1167gK);
                synchronized (obj) {
                    int i10 = c1177q4.d;
                    if (i10 == i7) {
                        c1177q4.c = cVarQ;
                        c1177q4.d = i10 + 1;
                        z6 = true;
                        c1177q4.f13730e++;
                    } else {
                        z6 = false;
                    }
                }
            }
            AbstractC1174n.n(abstractC1167gK, this);
        } while (!z6);
        return ((Boolean) objB).booleanValue();
    }

    @Override // java.util.List, java.util.Collection
    public final Object[] toArray() {
        return A7.l.a(this);
    }

    public final String toString() {
        C1177q c1177q = this.l;
        A7.m.d(c1177q, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
        return "SnapshotStateList(value=" + ((C1177q) AbstractC1174n.i(c1177q)).c + ")@" + hashCode();
    }

    @Override // java.util.List, java.util.Collection
    public final boolean addAll(Collection collection) {
        int i7;
        X.c cVar;
        boolean z6;
        AbstractC1167g abstractC1167gK;
        do {
            Object obj = AbstractC1179s.f13731a;
            synchronized (obj) {
                C1177q c1177q = this.l;
                A7.m.d(c1177q, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
                C1177q c1177q2 = (C1177q) AbstractC1174n.i(c1177q);
                i7 = c1177q2.d;
                cVar = c1177q2.c;
            }
            A7.m.c(cVar);
            X.c cVarR = cVar.r(collection);
            z6 = false;
            if (A7.m.a(cVarR, cVar)) {
                return false;
            }
            C1177q c1177q3 = this.l;
            A7.m.d(c1177q3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
            synchronized (AbstractC1174n.f13721b) {
                abstractC1167gK = AbstractC1174n.k();
                C1177q c1177q4 = (C1177q) AbstractC1174n.w(c1177q3, this, abstractC1167gK);
                synchronized (obj) {
                    int i10 = c1177q4.d;
                    if (i10 == i7) {
                        c1177q4.c = cVarR;
                        c1177q4.f13730e++;
                        c1177q4.d = i10 + 1;
                        z6 = true;
                    }
                }
            }
            AbstractC1174n.n(abstractC1167gK, this);
        } while (!z6);
        return true;
    }

    @Override // java.util.List
    public final ListIterator listIterator(int i7) {
        return new C0167q(this, i7);
    }

    @Override // java.util.List, java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        return A7.l.b(this, objArr);
    }

    @Override // java.util.List
    public final void add(int i7, Object obj) {
        int i10;
        X.c cVar;
        AbstractC1167g abstractC1167gK;
        boolean z6;
        do {
            Object obj2 = AbstractC1179s.f13731a;
            synchronized (obj2) {
                C1177q c1177q = this.l;
                A7.m.d(c1177q, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
                C1177q c1177q2 = (C1177q) AbstractC1174n.i(c1177q);
                i10 = c1177q2.d;
                cVar = c1177q2.c;
            }
            A7.m.c(cVar);
            X.c cVarL = cVar.l(i7, obj);
            if (cVarL.equals(cVar)) {
                return;
            }
            C1177q c1177q3 = this.l;
            A7.m.d(c1177q3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
            synchronized (AbstractC1174n.f13721b) {
                abstractC1167gK = AbstractC1174n.k();
                C1177q c1177q4 = (C1177q) AbstractC1174n.w(c1177q3, this, abstractC1167gK);
                synchronized (obj2) {
                    int i11 = c1177q4.d;
                    if (i11 == i10) {
                        c1177q4.c = cVarL;
                        z6 = true;
                        c1177q4.f13730e++;
                        c1177q4.d = i11 + 1;
                    } else {
                        z6 = false;
                    }
                }
            }
            AbstractC1174n.n(abstractC1167gK, this);
        } while (!z6);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean remove(Object obj) {
        int i7;
        X.c cVar;
        boolean z6;
        AbstractC1167g abstractC1167gK;
        do {
            Object obj2 = AbstractC1179s.f13731a;
            synchronized (obj2) {
                C1177q c1177q = this.l;
                A7.m.d(c1177q, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
                C1177q c1177q2 = (C1177q) AbstractC1174n.i(c1177q);
                i7 = c1177q2.d;
                cVar = c1177q2.c;
            }
            A7.m.c(cVar);
            int iIndexOf = cVar.indexOf(obj);
            X.c cVarU = iIndexOf != -1 ? cVar.u(iIndexOf) : cVar;
            z6 = false;
            if (A7.m.a(cVarU, cVar)) {
                return false;
            }
            C1177q c1177q3 = this.l;
            A7.m.d(c1177q3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
            synchronized (AbstractC1174n.f13721b) {
                abstractC1167gK = AbstractC1174n.k();
                C1177q c1177q4 = (C1177q) AbstractC1174n.w(c1177q3, this, abstractC1167gK);
                synchronized (obj2) {
                    int i10 = c1177q4.d;
                    if (i10 == i7) {
                        c1177q4.c = cVarU;
                        c1177q4.f13730e++;
                        c1177q4.d = i10 + 1;
                        z6 = true;
                    }
                }
            }
            AbstractC1174n.n(abstractC1167gK, this);
        } while (!z6);
        return true;
    }
}
