package d8;

import Q7.InterfaceC0652e;
import Q7.InterfaceC0654g;
import Q7.InterfaceC0657j;
import T.C0741g;
import c8.C1036a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import o8.AbstractC2063h;
import o8.C2061f;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: d8.v, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1135v extends AbstractC1112D {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final W7.x f13625n;
    public final C1129p o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final E8.h f13626p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final E8.j f13627q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1135v(N0.q qVar, W7.x xVar, C1129p c1129p) {
        super(qVar, null);
        A7.m.f(c1129p, "ownerDescriptor");
        this.f13625n = xVar;
        this.o = c1129p;
        E8.l lVar = ((C1036a) qVar.f4399m).f12411a;
        C1134u c1134u = new C1134u(qVar, 0, this);
        lVar.getClass();
        this.f13626p = new E8.h(lVar, c1134u);
        this.f13627q = lVar.c(new C0741g(this, 9, qVar));
    }

    @Override // d8.z, y8.o, y8.n
    public final Collection b(C2061f c2061f, Y7.b bVar) {
        A7.m.f(c2061f, "name");
        return n7.u.l;
    }

    @Override // y8.o, y8.p
    public final InterfaceC0654g c(C2061f c2061f, Y7.b bVar) {
        A7.m.f(c2061f, "name");
        A7.m.f(bVar, "location");
        return v(c2061f, null);
    }

    @Override // d8.z, y8.o, y8.p
    public final Collection d(y8.f fVar, InterfaceC2782k interfaceC2782k) {
        A7.m.f(fVar, "kindFilter");
        A7.m.f(interfaceC2782k, "nameFilter");
        if (!fVar.a(y8.f.l | y8.f.f24189e)) {
            return n7.u.l;
        }
        Iterable iterable = (Iterable) this.d.a();
        ArrayList arrayList = new ArrayList();
        for (Object obj : iterable) {
            InterfaceC0657j interfaceC0657j = (InterfaceC0657j) obj;
            if (interfaceC0657j instanceof InterfaceC0652e) {
                C2061f name = ((InterfaceC0652e) interfaceC0657j).getName();
                A7.m.e(name, "it.name");
                if (((Boolean) interfaceC2782k.b(name)).booleanValue()) {
                    arrayList.add(obj);
                }
            }
        }
        return arrayList;
    }

    @Override // d8.z
    public final Set h(y8.f fVar, y8.k kVar) {
        A7.m.f(fVar, "kindFilter");
        if (!fVar.a(y8.f.f24189e)) {
            return n7.w.l;
        }
        Set set = (Set) this.f13626p.a();
        if (set == null) {
            this.f13625n.getClass();
            return new LinkedHashSet();
        }
        HashSet hashSet = new HashSet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            hashSet.add(C2061f.e((String) it.next()));
        }
        return hashSet;
    }

    @Override // d8.z
    public final Set i(y8.f fVar, y8.k kVar) {
        A7.m.f(fVar, "kindFilter");
        return n7.w.l;
    }

    @Override // d8.z
    public final InterfaceC1116c k() {
        return C1115b.f13565a;
    }

    @Override // d8.z
    public final void m(LinkedHashSet linkedHashSet, C2061f c2061f) {
        A7.m.f(c2061f, "name");
    }

    @Override // d8.z
    public final Set o(y8.f fVar) {
        A7.m.f(fVar, "kindFilter");
        return n7.w.l;
    }

    @Override // d8.z
    public final InterfaceC0657j q() {
        return this.o;
    }

    public final InterfaceC0652e v(C2061f c2061f, W7.n nVar) {
        C2061f c2061f2 = AbstractC2063h.f20575a;
        A7.m.f(c2061f, "name");
        String strB = c2061f.b();
        A7.m.e(strB, "name.asString()");
        if (strB.length() <= 0 || c2061f.f20573m) {
            return null;
        }
        Set set = (Set) this.f13626p.a();
        if (nVar != null || set == null || set.contains(c2061f.b())) {
            return (InterfaceC0652e) this.f13627q.b(new C1130q(c2061f, nVar));
        }
        return null;
    }
}
