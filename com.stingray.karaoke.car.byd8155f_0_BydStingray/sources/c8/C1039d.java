package c8;

import A7.m;
import B8.A;
import E8.f;
import E8.g;
import E8.l;
import N0.q;
import Q7.H;
import W7.x;
import d8.C1129p;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import n7.AbstractC1999n;
import n7.u;
import o8.C2058c;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: c8.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1039d implements H {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final q f12434a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final E8.e f12435b;

    public C1039d(C1036a c1036a) {
        this.f12434a = new q(c1036a, C1037b.f12431m, new m7.d());
        l lVar = c1036a.f12411a;
        lVar.getClass();
        this.f12435b = new E8.e(lVar, new ConcurrentHashMap(3, 1.0f, 2), new f(), 0);
    }

    @Override // Q7.H
    public final boolean a(C2058c c2058c) {
        m.f(c2058c, "fqName");
        ((C1036a) this.f12434a.f4399m).f12412b.getClass();
        return false;
    }

    @Override // Q7.H
    public final void b(C2058c c2058c, ArrayList arrayList) {
        m.f(c2058c, "fqName");
        O8.l.a(arrayList, d(c2058c));
    }

    @Override // Q7.E
    public final List c(C2058c c2058c) {
        m.f(c2058c, "fqName");
        return AbstractC1999n.E(d(c2058c));
    }

    public final C1129p d(C2058c c2058c) throws Throwable {
        ((C1036a) this.f12434a.f4399m).f12412b.getClass();
        m.f(c2058c, "fqName");
        A a10 = new A(this, 29, new x(c2058c));
        E8.e eVar = this.f12435b;
        eVar.getClass();
        Object objB = eVar.b(new g(c2058c, a10));
        if (objB != null) {
            return (C1129p) objB;
        }
        E8.e.f(3);
        throw null;
    }

    @Override // Q7.E
    public final Collection r(C2058c c2058c, InterfaceC2782k interfaceC2782k) {
        m.f(c2058c, "fqName");
        m.f(interfaceC2782k, "nameFilter");
        List list = (List) d(c2058c).f13619w.a();
        return list == null ? u.l : list;
    }

    public final String toString() {
        return "LazyJavaPackageFragmentProvider of module " + ((C1036a) this.f12434a.f4399m).o;
    }
}
