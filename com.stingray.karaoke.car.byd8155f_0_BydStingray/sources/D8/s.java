package D8;

import Q7.D;
import Q7.InterfaceC0654g;
import T.C0746i0;
import T7.B;
import h8.C1391h;
import j8.C;
import j8.X;
import j8.e0;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import l8.AbstractC1783a;
import l8.C1790h;
import l8.InterfaceC1788f;
import n7.AbstractC1998m;
import n7.AbstractC2004s;
import o8.C2057b;
import o8.C2058c;
import o8.C2061f;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class s extends r {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final D f1269g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String f1270h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final C2058c f1271i;

    public s(D d, C c, InterfaceC1788f interfaceC1788f, AbstractC1783a abstractC1783a, C1391h c1391h, B8.i iVar, String str, InterfaceC2772a interfaceC2772a) {
        C1790h c1790h;
        A7.m.f(d, "packageDescriptor");
        A7.m.f(c, "proto");
        A7.m.f(interfaceC1788f, "nameResolver");
        A7.m.f(abstractC1783a, "metadataVersion");
        A7.m.f(iVar, "components");
        A7.m.f(str, "debugName");
        X x8 = c.f17081r;
        A7.m.e(x8, "proto.typeTable");
        C0746i0 c0746i0 = new C0746i0(x8);
        e0 e0Var = c.f17082s;
        A7.m.e(e0Var, "proto.versionRequirementTable");
        if (e0Var.f17332m.size() == 0) {
            c1790h = C1790h.f18862a;
        } else {
            A7.m.e(e0Var.f17332m, "table.requirementList");
            c1790h = new C1790h();
        }
        B8.k kVarA = iVar.a(d, interfaceC1788f, c0746i0, c1790h, abstractC1783a, c1391h);
        List list = c.o;
        A7.m.e(list, "proto.functionList");
        List list2 = c.f17079p;
        A7.m.e(list2, "proto.propertyList");
        List list3 = c.f17080q;
        A7.m.e(list3, "proto.typeAliasList");
        super(kVarA, list, list2, list3, interfaceC2772a);
        this.f1269g = d;
        this.f1270h = str;
        this.f1271i = ((B) d).f8767q;
    }

    @Override // D8.r, y8.o, y8.p
    public final InterfaceC0654g c(C2061f c2061f, Y7.b bVar) {
        A7.m.f(c2061f, "name");
        A7.m.f(bVar, "location");
        io.sentry.config.a.l0(((B8.i) this.f1267b.f779a).f768i, bVar, this.f1269g, c2061f);
        return super.c(c2061f, bVar);
    }

    @Override // y8.o, y8.p
    public final Collection d(y8.f fVar, InterfaceC2782k interfaceC2782k) {
        A7.m.f(fVar, "kindFilter");
        A7.m.f(interfaceC2782k, "nameFilter");
        List listI = i(fVar, interfaceC2782k);
        Iterable iterable = ((B8.i) this.f1267b.f779a).f770k;
        ArrayList arrayList = new ArrayList();
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            AbstractC2004s.L(arrayList, ((S7.c) it.next()).a(this.f1271i));
        }
        return AbstractC1998m.n0(listI, arrayList);
    }

    @Override // D8.r
    public final void h(ArrayList arrayList, InterfaceC2782k interfaceC2782k) {
        A7.m.f(interfaceC2782k, "nameFilter");
    }

    @Override // D8.r
    public final C2057b l(C2061f c2061f) {
        A7.m.f(c2061f, "name");
        return new C2057b(this.f1271i, c2061f);
    }

    @Override // D8.r
    public final Set n() {
        return n7.w.l;
    }

    @Override // D8.r
    public final Set o() {
        return n7.w.l;
    }

    @Override // D8.r
    public final Set p() {
        return n7.w.l;
    }

    @Override // D8.r
    public final boolean q(C2061f c2061f) {
        A7.m.f(c2061f, "name");
        if (!super.q(c2061f)) {
            Iterable iterable = ((B8.i) this.f1267b.f779a).f770k;
            if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                Iterator it = iterable.iterator();
                while (it.hasNext()) {
                    if (((S7.c) it.next()).b(this.f1271i, c2061f)) {
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final String toString() {
        return this.f1270h;
    }
}
