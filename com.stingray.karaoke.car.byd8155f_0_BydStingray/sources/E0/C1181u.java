package e0;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import n7.AbstractC1990e;

/* JADX INFO: renamed from: e0.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1181u implements InterfaceC1154A, Map, B7.e {
    public C1180t l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final C1175o f13733m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final C1175o f13734n;
    public final C1175o o;

    public C1181u() {
        Y.c cVar = Y.c.f10586n;
        C1180t c1180t = new C1180t(cVar);
        if (AbstractC1174n.f13720a.p() != null) {
            C1180t c1180t2 = new C1180t(cVar);
            c1180t2.f13675a = 1;
            c1180t.f13676b = c1180t2;
        }
        this.l = c1180t;
        this.f13733m = new C1175o(this, 0);
        this.f13734n = new C1175o(this, 1);
        this.o = new C1175o(this, 2);
    }

    public final C1180t b() {
        C1180t c1180t = this.l;
        A7.m.d(c1180t, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateMap.StateMapStateRecord<K of androidx.compose.runtime.snapshots.SnapshotStateMap, V of androidx.compose.runtime.snapshots.SnapshotStateMap>");
        return (C1180t) AbstractC1174n.t(c1180t, this);
    }

    @Override // java.util.Map
    public final void clear() {
        AbstractC1167g abstractC1167gK;
        C1180t c1180t = this.l;
        A7.m.d(c1180t, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateMap.StateMapStateRecord<K of androidx.compose.runtime.snapshots.SnapshotStateMap, V of androidx.compose.runtime.snapshots.SnapshotStateMap>");
        C1180t c1180t2 = (C1180t) AbstractC1174n.i(c1180t);
        Y.c cVar = Y.c.f10586n;
        if (cVar != c1180t2.c) {
            C1180t c1180t3 = this.l;
            A7.m.d(c1180t3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateMap.StateMapStateRecord<K of androidx.compose.runtime.snapshots.SnapshotStateMap, V of androidx.compose.runtime.snapshots.SnapshotStateMap>");
            synchronized (AbstractC1174n.f13721b) {
                abstractC1167gK = AbstractC1174n.k();
                C1180t c1180t4 = (C1180t) AbstractC1174n.w(c1180t3, this, abstractC1167gK);
                synchronized (AbstractC1179s.f13732b) {
                    c1180t4.c = cVar;
                    c1180t4.d++;
                }
            }
            AbstractC1174n.n(abstractC1167gK, this);
        }
    }

    @Override // java.util.Map
    public final boolean containsKey(Object obj) {
        return b().c.containsKey(obj);
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        return b().c.containsValue(obj);
    }

    @Override // e0.InterfaceC1154A
    public final AbstractC1156C d() {
        return this.l;
    }

    @Override // java.util.Map
    public final Set entrySet() {
        return this.f13733m;
    }

    @Override // java.util.Map
    public final Object get(Object obj) {
        return b().c.get(obj);
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return ((AbstractC1990e) b().c).isEmpty();
    }

    @Override // java.util.Map
    public final Set keySet() {
        return this.f13734n;
    }

    @Override // java.util.Map
    public final Object put(Object obj, Object obj2) {
        W.d dVar;
        int i7;
        Object objPut;
        AbstractC1167g abstractC1167gK;
        boolean z6;
        do {
            Object obj3 = AbstractC1179s.f13732b;
            synchronized (obj3) {
                C1180t c1180t = this.l;
                A7.m.d(c1180t, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateMap.StateMapStateRecord<K of androidx.compose.runtime.snapshots.SnapshotStateMap, V of androidx.compose.runtime.snapshots.SnapshotStateMap>");
                C1180t c1180t2 = (C1180t) AbstractC1174n.i(c1180t);
                dVar = c1180t2.c;
                i7 = c1180t2.d;
            }
            A7.m.c(dVar);
            Y.e eVar = (Y.e) dVar.m();
            objPut = eVar.put(obj, obj2);
            W.d dVarA = eVar.a();
            if (A7.m.a(dVarA, dVar)) {
                break;
            }
            C1180t c1180t3 = this.l;
            A7.m.d(c1180t3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateMap.StateMapStateRecord<K of androidx.compose.runtime.snapshots.SnapshotStateMap, V of androidx.compose.runtime.snapshots.SnapshotStateMap>");
            synchronized (AbstractC1174n.f13721b) {
                abstractC1167gK = AbstractC1174n.k();
                C1180t c1180t4 = (C1180t) AbstractC1174n.w(c1180t3, this, abstractC1167gK);
                synchronized (obj3) {
                    int i10 = c1180t4.d;
                    if (i10 == i7) {
                        c1180t4.c = dVarA;
                        c1180t4.d = i10 + 1;
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                }
            }
            AbstractC1174n.n(abstractC1167gK, this);
        } while (!z6);
        return objPut;
    }

    @Override // java.util.Map
    public final void putAll(Map map) {
        W.d dVar;
        int i7;
        AbstractC1167g abstractC1167gK;
        boolean z6;
        do {
            Object obj = AbstractC1179s.f13732b;
            synchronized (obj) {
                C1180t c1180t = this.l;
                A7.m.d(c1180t, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateMap.StateMapStateRecord<K of androidx.compose.runtime.snapshots.SnapshotStateMap, V of androidx.compose.runtime.snapshots.SnapshotStateMap>");
                C1180t c1180t2 = (C1180t) AbstractC1174n.i(c1180t);
                dVar = c1180t2.c;
                i7 = c1180t2.d;
            }
            A7.m.c(dVar);
            Y.e eVar = (Y.e) dVar.m();
            eVar.putAll(map);
            W.d dVarA = eVar.a();
            if (A7.m.a(dVarA, dVar)) {
                return;
            }
            C1180t c1180t3 = this.l;
            A7.m.d(c1180t3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateMap.StateMapStateRecord<K of androidx.compose.runtime.snapshots.SnapshotStateMap, V of androidx.compose.runtime.snapshots.SnapshotStateMap>");
            synchronized (AbstractC1174n.f13721b) {
                abstractC1167gK = AbstractC1174n.k();
                C1180t c1180t4 = (C1180t) AbstractC1174n.w(c1180t3, this, abstractC1167gK);
                synchronized (obj) {
                    int i10 = c1180t4.d;
                    if (i10 == i7) {
                        c1180t4.c = dVarA;
                        c1180t4.d = i10 + 1;
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                }
            }
            AbstractC1174n.n(abstractC1167gK, this);
        } while (!z6);
    }

    @Override // e0.InterfaceC1154A
    public final void q(AbstractC1156C abstractC1156C) {
        A7.m.d(abstractC1156C, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateMap.StateMapStateRecord<K of androidx.compose.runtime.snapshots.SnapshotStateMap, V of androidx.compose.runtime.snapshots.SnapshotStateMap>");
        this.l = (C1180t) abstractC1156C;
    }

    @Override // java.util.Map
    public final Object remove(Object obj) {
        W.d dVar;
        int i7;
        Object objRemove;
        AbstractC1167g abstractC1167gK;
        boolean z6;
        do {
            Object obj2 = AbstractC1179s.f13732b;
            synchronized (obj2) {
                C1180t c1180t = this.l;
                A7.m.d(c1180t, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateMap.StateMapStateRecord<K of androidx.compose.runtime.snapshots.SnapshotStateMap, V of androidx.compose.runtime.snapshots.SnapshotStateMap>");
                C1180t c1180t2 = (C1180t) AbstractC1174n.i(c1180t);
                dVar = c1180t2.c;
                i7 = c1180t2.d;
            }
            A7.m.c(dVar);
            W.c cVarM = dVar.m();
            objRemove = cVarM.remove(obj);
            W.d dVarA = cVarM.a();
            if (A7.m.a(dVarA, dVar)) {
                break;
            }
            C1180t c1180t3 = this.l;
            A7.m.d(c1180t3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateMap.StateMapStateRecord<K of androidx.compose.runtime.snapshots.SnapshotStateMap, V of androidx.compose.runtime.snapshots.SnapshotStateMap>");
            synchronized (AbstractC1174n.f13721b) {
                abstractC1167gK = AbstractC1174n.k();
                C1180t c1180t4 = (C1180t) AbstractC1174n.w(c1180t3, this, abstractC1167gK);
                synchronized (obj2) {
                    int i10 = c1180t4.d;
                    if (i10 == i7) {
                        c1180t4.c = dVarA;
                        c1180t4.d = i10 + 1;
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                }
            }
            AbstractC1174n.n(abstractC1167gK, this);
        } while (!z6);
        return objRemove;
    }

    @Override // java.util.Map
    public final int size() {
        AbstractC1990e abstractC1990e = (AbstractC1990e) b().c;
        abstractC1990e.getClass();
        return ((Y.c) abstractC1990e).f10587m;
    }

    public final String toString() {
        C1180t c1180t = this.l;
        A7.m.d(c1180t, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateMap.StateMapStateRecord<K of androidx.compose.runtime.snapshots.SnapshotStateMap, V of androidx.compose.runtime.snapshots.SnapshotStateMap>");
        return "SnapshotStateMap(value=" + ((C1180t) AbstractC1174n.i(c1180t)).c + ")@" + hashCode();
    }

    @Override // java.util.Map
    public final Collection values() {
        return this.o;
    }
}
