package e0;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import n7.AbstractC1990e;
import n7.AbstractC1998m;
import n7.AbstractC2000o;

/* JADX INFO: renamed from: e0.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1175o implements Set, B7.f {
    public final C1181u l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f13729m;

    public C1175o(C1181u c1181u, int i7) {
        this.f13729m = i7;
        this.l = c1181u;
    }

    private final boolean d(Collection collection) {
        W.d dVar;
        int i7;
        boolean z6;
        AbstractC1167g abstractC1167gK;
        Collection<Map.Entry> collection2 = collection;
        int iQ = n7.z.q(AbstractC2000o.J(collection2, 10));
        if (iQ < 16) {
            iQ = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iQ);
        for (Map.Entry entry : collection2) {
            linkedHashMap.put(entry.getKey(), entry.getValue());
        }
        C1181u c1181u = this.l;
        boolean z10 = false;
        do {
            synchronized (AbstractC1179s.f13732b) {
                C1180t c1180t = c1181u.l;
                A7.m.d(c1180t, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateMap.StateMapStateRecord<K of androidx.compose.runtime.snapshots.SnapshotStateMap, V of androidx.compose.runtime.snapshots.SnapshotStateMap>");
                C1180t c1180t2 = (C1180t) AbstractC1174n.i(c1180t);
                dVar = c1180t2.c;
                i7 = c1180t2.d;
            }
            A7.m.c(dVar);
            W.c cVarM = dVar.m();
            Iterator it = c1181u.f13733m.iterator();
            while (true) {
                z6 = true;
                if (!((C1186z) it).hasNext()) {
                    break;
                }
                Map.Entry entry2 = (Map.Entry) ((C1186z) it).next();
                if (!linkedHashMap.containsKey(entry2.getKey()) || !A7.m.a(linkedHashMap.get(entry2.getKey()), entry2.getValue())) {
                    cVarM.remove(entry2.getKey());
                    z10 = true;
                }
            }
            W.d dVarA = cVarM.a();
            if (A7.m.a(dVarA, dVar)) {
                break;
            }
            C1180t c1180t3 = c1181u.l;
            A7.m.d(c1180t3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateMap.StateMapStateRecord<K of androidx.compose.runtime.snapshots.SnapshotStateMap, V of androidx.compose.runtime.snapshots.SnapshotStateMap>");
            synchronized (AbstractC1174n.f13721b) {
                abstractC1167gK = AbstractC1174n.k();
                C1180t c1180t4 = (C1180t) AbstractC1174n.w(c1180t3, c1181u, abstractC1167gK);
                synchronized (AbstractC1179s.f13732b) {
                    int i10 = c1180t4.d;
                    if (i10 == i7) {
                        c1180t4.c = dVarA;
                        c1180t4.d = i10 + 1;
                    } else {
                        z6 = false;
                    }
                }
            }
            AbstractC1174n.n(abstractC1167gK, c1181u);
        } while (!z6);
        return z10;
    }

    private final boolean l(Collection collection) {
        W.d dVar;
        int i7;
        boolean z6;
        AbstractC1167g abstractC1167gK;
        Set setD0 = AbstractC1998m.D0(collection);
        C1181u c1181u = this.l;
        boolean z10 = false;
        do {
            synchronized (AbstractC1179s.f13732b) {
                C1180t c1180t = c1181u.l;
                A7.m.d(c1180t, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateMap.StateMapStateRecord<K of androidx.compose.runtime.snapshots.SnapshotStateMap, V of androidx.compose.runtime.snapshots.SnapshotStateMap>");
                C1180t c1180t2 = (C1180t) AbstractC1174n.i(c1180t);
                dVar = c1180t2.c;
                i7 = c1180t2.d;
            }
            A7.m.c(dVar);
            W.c cVarM = dVar.m();
            Iterator it = c1181u.f13733m.iterator();
            while (true) {
                z6 = true;
                if (!((C1186z) it).hasNext()) {
                    break;
                }
                Map.Entry entry = (Map.Entry) ((C1186z) it).next();
                if (!setD0.contains(entry.getKey())) {
                    cVarM.remove(entry.getKey());
                    z10 = true;
                }
            }
            W.d dVarA = cVarM.a();
            if (A7.m.a(dVarA, dVar)) {
                break;
            }
            C1180t c1180t3 = c1181u.l;
            A7.m.d(c1180t3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateMap.StateMapStateRecord<K of androidx.compose.runtime.snapshots.SnapshotStateMap, V of androidx.compose.runtime.snapshots.SnapshotStateMap>");
            synchronized (AbstractC1174n.f13721b) {
                abstractC1167gK = AbstractC1174n.k();
                C1180t c1180t4 = (C1180t) AbstractC1174n.w(c1180t3, c1181u, abstractC1167gK);
                synchronized (AbstractC1179s.f13732b) {
                    int i10 = c1180t4.d;
                    if (i10 == i7) {
                        c1180t4.c = dVarA;
                        c1180t4.d = i10 + 1;
                    } else {
                        z6 = false;
                    }
                }
            }
            AbstractC1174n.n(abstractC1167gK, c1181u);
        } while (!z6);
        return z10;
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean add(Object obj) {
        switch (this.f13729m) {
            case 0:
                AbstractC1179s.g();
                throw null;
            case 1:
                AbstractC1179s.g();
                throw null;
            default:
                AbstractC1179s.g();
                throw null;
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean addAll(Collection collection) {
        switch (this.f13729m) {
            case 0:
                AbstractC1179s.g();
                throw null;
            case 1:
                AbstractC1179s.g();
                throw null;
            default:
                AbstractC1179s.g();
                throw null;
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final void clear() {
        this.l.clear();
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean contains(Object obj) {
        switch (this.f13729m) {
            case 0:
                if (!(obj instanceof Map.Entry) || ((obj instanceof B7.a) && !(obj instanceof B7.d))) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                return A7.m.a(this.l.get(entry.getKey()), entry.getValue());
            case 1:
                return this.l.containsKey(obj);
            default:
                return this.l.containsValue(obj);
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean containsAll(Collection collection) {
        switch (this.f13729m) {
            case 0:
                Collection collection2 = collection;
                if (!(collection2 instanceof Collection) || !collection2.isEmpty()) {
                    Iterator it = collection2.iterator();
                    while (it.hasNext()) {
                        if (!contains((Map.Entry) it.next())) {
                            break;
                        }
                    }
                }
                break;
            case 1:
                Collection collection3 = collection;
                if (!(collection3 instanceof Collection) || !collection3.isEmpty()) {
                    Iterator it2 = collection3.iterator();
                    while (it2.hasNext()) {
                        if (!this.l.containsKey(it2.next())) {
                            break;
                        }
                    }
                }
                break;
            default:
                Collection collection4 = collection;
                if (!(collection4 instanceof Collection) || !collection4.isEmpty()) {
                    Iterator it3 = collection4.iterator();
                    while (it3.hasNext()) {
                        if (!this.l.containsValue(it3.next())) {
                            break;
                        }
                    }
                }
                break;
        }
        return true;
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean isEmpty() {
        return this.l.isEmpty();
    }

    @Override // java.util.Set, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        switch (this.f13729m) {
            case 0:
                C1181u c1181u = this.l;
                return new C1186z(c1181u, ((W.b) ((AbstractC1990e) c1181u.b().c).entrySet()).iterator(), 0);
            case 1:
                C1181u c1181u2 = this.l;
                return new C1186z(c1181u2, ((W.b) ((AbstractC1990e) c1181u2.b().c).entrySet()).iterator(), 1);
            default:
                C1181u c1181u3 = this.l;
                return new C1186z(c1181u3, ((W.b) ((AbstractC1990e) c1181u3.b().c).entrySet()).iterator(), 2);
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean remove(Object obj) {
        Object next;
        switch (this.f13729m) {
            case 0:
                if ((obj instanceof Map.Entry) && (!(obj instanceof B7.a) || (obj instanceof B7.d))) {
                    if (this.l.remove(((Map.Entry) obj).getKey()) != null) {
                    }
                    break;
                }
                break;
            case 1:
                if (this.l.remove(obj) != null) {
                }
                break;
            default:
                C1181u c1181u = this.l;
                Iterator it = c1181u.f13733m.iterator();
                while (true) {
                    if (((C1186z) it).hasNext()) {
                        next = ((C1186z) it).next();
                        if (A7.m.a(((Map.Entry) next).getValue(), obj)) {
                        }
                    } else {
                        next = null;
                    }
                }
                Map.Entry entry = (Map.Entry) next;
                if (entry != null) {
                    c1181u.remove(entry.getKey());
                }
                break;
        }
        return true;
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean removeAll(Collection collection) {
        W.d dVar;
        int i7;
        boolean z6;
        AbstractC1167g abstractC1167gK;
        switch (this.f13729m) {
            case 0:
                Iterator it = collection.iterator();
                while (true) {
                    boolean z10 = false;
                    while (it.hasNext()) {
                        if (this.l.remove(((Map.Entry) it.next()).getKey()) != null || z10) {
                            z10 = true;
                        }
                    }
                    return z10;
                }
                break;
            case 1:
                Iterator it2 = collection.iterator();
                while (true) {
                    boolean z11 = false;
                    while (it2.hasNext()) {
                        if (this.l.remove(it2.next()) != null || z11) {
                            z11 = true;
                        }
                    }
                    return z11;
                }
                break;
            default:
                Set setD0 = AbstractC1998m.D0(collection);
                C1181u c1181u = this.l;
                boolean z12 = false;
                do {
                    synchronized (AbstractC1179s.f13732b) {
                        C1180t c1180t = c1181u.l;
                        A7.m.d(c1180t, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateMap.StateMapStateRecord<K of androidx.compose.runtime.snapshots.SnapshotStateMap, V of androidx.compose.runtime.snapshots.SnapshotStateMap>");
                        C1180t c1180t2 = (C1180t) AbstractC1174n.i(c1180t);
                        dVar = c1180t2.c;
                        i7 = c1180t2.d;
                    }
                    A7.m.c(dVar);
                    W.c cVarM = dVar.m();
                    Iterator it3 = c1181u.f13733m.iterator();
                    while (true) {
                        z6 = true;
                        if (!((C1186z) it3).hasNext()) {
                            W.d dVarA = cVarM.a();
                            if (!A7.m.a(dVarA, dVar)) {
                                C1180t c1180t3 = c1181u.l;
                                A7.m.d(c1180t3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateMap.StateMapStateRecord<K of androidx.compose.runtime.snapshots.SnapshotStateMap, V of androidx.compose.runtime.snapshots.SnapshotStateMap>");
                                synchronized (AbstractC1174n.f13721b) {
                                    abstractC1167gK = AbstractC1174n.k();
                                    C1180t c1180t4 = (C1180t) AbstractC1174n.w(c1180t3, c1181u, abstractC1167gK);
                                    synchronized (AbstractC1179s.f13732b) {
                                        int i10 = c1180t4.d;
                                        if (i10 == i7) {
                                            c1180t4.c = dVarA;
                                            c1180t4.d = i10 + 1;
                                        } else {
                                            z6 = false;
                                        }
                                    }
                                }
                                AbstractC1174n.n(abstractC1167gK, c1181u);
                            }
                            return z12;
                        }
                        Map.Entry entry = (Map.Entry) ((C1186z) it3).next();
                        if (setD0.contains(entry.getValue())) {
                            cVarM.remove(entry.getKey());
                            z12 = true;
                        }
                    }
                } while (!z6);
                return z12;
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean retainAll(Collection collection) {
        W.d dVar;
        int i7;
        boolean z6;
        AbstractC1167g abstractC1167gK;
        switch (this.f13729m) {
            case 0:
                return d(collection);
            case 1:
                return l(collection);
            default:
                Set setD0 = AbstractC1998m.D0(collection);
                C1181u c1181u = this.l;
                boolean z10 = false;
                do {
                    synchronized (AbstractC1179s.f13732b) {
                        C1180t c1180t = c1181u.l;
                        A7.m.d(c1180t, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateMap.StateMapStateRecord<K of androidx.compose.runtime.snapshots.SnapshotStateMap, V of androidx.compose.runtime.snapshots.SnapshotStateMap>");
                        C1180t c1180t2 = (C1180t) AbstractC1174n.i(c1180t);
                        dVar = c1180t2.c;
                        i7 = c1180t2.d;
                    }
                    A7.m.c(dVar);
                    W.c cVarM = dVar.m();
                    Iterator it = c1181u.f13733m.iterator();
                    while (true) {
                        z6 = true;
                        if (!((C1186z) it).hasNext()) {
                            W.d dVarA = cVarM.a();
                            if (!A7.m.a(dVarA, dVar)) {
                                C1180t c1180t3 = c1181u.l;
                                A7.m.d(c1180t3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateMap.StateMapStateRecord<K of androidx.compose.runtime.snapshots.SnapshotStateMap, V of androidx.compose.runtime.snapshots.SnapshotStateMap>");
                                synchronized (AbstractC1174n.f13721b) {
                                    abstractC1167gK = AbstractC1174n.k();
                                    C1180t c1180t4 = (C1180t) AbstractC1174n.w(c1180t3, c1181u, abstractC1167gK);
                                    synchronized (AbstractC1179s.f13732b) {
                                        int i10 = c1180t4.d;
                                        if (i10 == i7) {
                                            c1180t4.c = dVarA;
                                            c1180t4.d = i10 + 1;
                                        } else {
                                            z6 = false;
                                        }
                                    }
                                }
                                AbstractC1174n.n(abstractC1167gK, c1181u);
                            }
                            return z10;
                        }
                        Map.Entry entry = (Map.Entry) ((C1186z) it).next();
                        if (!setD0.contains(entry.getValue())) {
                            cVarM.remove(entry.getKey());
                            z10 = true;
                        }
                    }
                } while (!z6);
                return z10;
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final int size() {
        return this.l.size();
    }

    @Override // java.util.Set, java.util.Collection
    public final Object[] toArray() {
        return A7.l.a(this);
    }

    @Override // java.util.Set, java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        return A7.l.b(this, objArr);
    }
}
