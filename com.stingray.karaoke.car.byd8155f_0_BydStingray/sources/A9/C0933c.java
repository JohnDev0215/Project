package a9;

import A7.m;
import B2.q;
import S8.C0711h;
import S8.InterfaceC0709f;
import S8.InterfaceC0710g;
import S8.v0;
import U8.l;
import X8.r;
import h8.AbstractC1387d;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import n7.AbstractC1998m;
import q7.InterfaceC2194h;
import s7.AbstractC2410c;
import z7.InterfaceC2786o;

/* JADX INFO: renamed from: a9.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0933c implements InterfaceC0709f, InterfaceC0934d, v0 {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f11227q = AtomicReferenceFieldUpdater.newUpdater(C0933c.class, Object.class, "state$volatile");
    public final InterfaceC2194h l;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public r f11229n;
    private volatile /* synthetic */ Object state$volatile = AbstractC0935e.f11231a;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ArrayList f11228m = new ArrayList(2);
    public int o = -1;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Object f11230p = AbstractC0935e.d;

    public C0933c(InterfaceC2194h interfaceC2194h) {
        this.l = interfaceC2194h;
    }

    @Override // S8.InterfaceC0709f
    public final void a(Throwable th) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f11227q;
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (obj == AbstractC0935e.f11232b) {
                return;
            }
            q qVar = AbstractC0935e.c;
            while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, qVar)) {
                if (atomicReferenceFieldUpdater.get(this) != obj) {
                    break;
                }
            }
            ArrayList arrayList = this.f11228m;
            if (arrayList == null) {
                return;
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((C0931a) it.next()).a();
            }
            this.f11230p = AbstractC0935e.d;
            this.f11228m = null;
            return;
        }
    }

    @Override // S8.v0
    public final void b(r rVar, int i7) {
        this.f11229n = rVar;
        this.o = i7;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [A7.j, z7.o] */
    public final Object c(AbstractC2410c abstractC2410c) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f11227q;
        Object obj = atomicReferenceFieldUpdater.get(this);
        m.d(obj, "null cannot be cast to non-null type kotlinx.coroutines.selects.SelectImplementation.ClauseData<R of kotlinx.coroutines.selects.SelectImplementation>");
        C0931a c0931a = (C0931a) obj;
        Object obj2 = this.f11230p;
        ArrayList<C0931a> arrayList = this.f11228m;
        if (arrayList != null) {
            for (C0931a c0931a2 : arrayList) {
                if (c0931a2 != c0931a) {
                    c0931a2.a();
                }
            }
            atomicReferenceFieldUpdater.set(this, AbstractC0935e.f11232b);
            this.f11230p = AbstractC0935e.d;
            this.f11228m = null;
        }
        return c0931a.d.invoke(c0931a.c.g(c0931a.f11219a, null, obj2), abstractC2410c);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object d(s7.AbstractC2410c r11) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 227
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a9.C0933c.d(s7.c):java.lang.Object");
    }

    public final C0931a e(Object obj) {
        ArrayList arrayList = this.f11228m;
        Object obj2 = null;
        if (arrayList == null) {
            return null;
        }
        Iterator it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (((C0931a) next).f11219a == obj) {
                obj2 = next;
                break;
            }
        }
        C0931a c0931a = (C0931a) obj2;
        if (c0931a != null) {
            return c0931a;
        }
        throw new IllegalStateException(("Clause with object " + obj + " is not found").toString());
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [A7.j, z7.o] */
    public final void f(C0931a c0931a, boolean z6) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f11227q;
        if (atomicReferenceFieldUpdater.get(this) instanceof C0931a) {
            return;
        }
        l lVar = c0931a.f11219a;
        if (!z6) {
            ArrayList arrayList = this.f11228m;
            m.c(arrayList);
            if (!arrayList.isEmpty()) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    if (((C0931a) it.next()).f11219a == lVar) {
                        throw new IllegalStateException(("Cannot use select clauses on the same object: " + lVar).toString());
                    }
                }
            }
        }
        c0931a.f11220b.g(lVar, this, null);
        if (this.f11230p != AbstractC0935e.d) {
            atomicReferenceFieldUpdater.set(this, c0931a);
            return;
        }
        if (!z6) {
            ArrayList arrayList2 = this.f11228m;
            m.c(arrayList2);
            arrayList2.add(c0931a);
        }
        c0931a.f11222f = this.f11229n;
        c0931a.f11223g = this.o;
        this.f11229n = null;
        this.o = -1;
    }

    public final int g(Object obj, Object obj2) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f11227q;
            Object obj3 = atomicReferenceFieldUpdater.get(this);
            if (!(obj3 instanceof InterfaceC0710g)) {
                if (m.a(obj3, AbstractC0935e.f11232b) || (obj3 instanceof C0931a)) {
                    return 3;
                }
                if (m.a(obj3, AbstractC0935e.c)) {
                    return 2;
                }
                if (m.a(obj3, AbstractC0935e.f11231a)) {
                    List listJ = AbstractC1387d.j(obj);
                    while (!atomicReferenceFieldUpdater.compareAndSet(this, obj3, listJ)) {
                        if (atomicReferenceFieldUpdater.get(this) != obj3) {
                            break;
                        }
                    }
                    return 1;
                }
                if (!(obj3 instanceof List)) {
                    throw new IllegalStateException(("Unexpected state: " + obj3).toString());
                }
                ArrayList arrayListO0 = AbstractC1998m.o0((Collection) obj3, obj);
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj3, arrayListO0)) {
                    if (atomicReferenceFieldUpdater.get(this) != obj3) {
                        break;
                    }
                }
                return 1;
            }
            C0931a c0931aE = e(obj);
            if (c0931aE != null) {
                C0711h c0711h = c0931aE.f11221e;
                InterfaceC2786o interfaceC2786o = c0711h != null ? (InterfaceC2786o) c0711h.g(this, null, obj2) : null;
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj3, c0931aE)) {
                    if (atomicReferenceFieldUpdater.get(this) != obj3) {
                        break;
                    }
                }
                InterfaceC0710g interfaceC0710g = (InterfaceC0710g) obj3;
                this.f11230p = obj2;
                q qVarI = interfaceC0710g.i(m7.q.f19267a, interfaceC2786o);
                if (qVarI == null) {
                    this.f11230p = AbstractC0935e.d;
                    return 2;
                }
                interfaceC0710g.r(qVarI);
                return 0;
            }
            continue;
        }
    }
}
