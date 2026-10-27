package D8;

import F8.AbstractC0287w;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0654g;
import a.AbstractC0904a;
import j8.C1565j;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import l8.InterfaceC1788f;
import n7.AbstractC2000o;
import n7.AbstractC2004s;
import o8.C2057b;
import o8.C2061f;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class g extends r {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final G8.f f1226g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final E8.i f1227h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final E8.i f1228i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ k f1229j;

    public g(k kVar, G8.f fVar) {
        A7.m.f(fVar, "kotlinTypeRefiner");
        this.f1229j = kVar;
        B8.k kVar2 = kVar.f1248w;
        C1565j c1565j = kVar.f1241p;
        List list = c1565j.B;
        A7.m.e(list, "classProto.functionList");
        List list2 = c1565j.f17375C;
        A7.m.e(list2, "classProto.propertyList");
        List list3 = c1565j.f17376D;
        A7.m.e(list3, "classProto.typeAliasList");
        List list4 = c1565j.f17401v;
        A7.m.e(list4, "classProto.nestedClassNameList");
        InterfaceC1788f interfaceC1788f = (InterfaceC1788f) kVar.f1248w.f780b;
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(list4, 10));
        Iterator it = list4.iterator();
        while (it.hasNext()) {
            arrayList.add(AbstractC0904a.F(interfaceC1788f, ((Number) it.next()).intValue()));
        }
        super(kVar2, list, list2, list3, new d(0, arrayList));
        this.f1226g = fVar;
        B8.i iVar = (B8.i) kVar2.f779a;
        E8.o oVar = iVar.f762a;
        e eVar = new e(this, 0);
        E8.l lVar = (E8.l) oVar;
        lVar.getClass();
        this.f1227h = new E8.i(lVar, eVar);
        E8.o oVar2 = iVar.f762a;
        e eVar2 = new e(this, 1);
        E8.l lVar2 = (E8.l) oVar2;
        lVar2.getClass();
        this.f1228i = new E8.i(lVar2, eVar2);
    }

    @Override // D8.r, y8.o, y8.n
    public final Collection a(C2061f c2061f, Y7.b bVar) {
        A7.m.f(c2061f, "name");
        s(c2061f, bVar);
        return super.a(c2061f, bVar);
    }

    @Override // D8.r, y8.o, y8.n
    public final Collection b(C2061f c2061f, Y7.b bVar) {
        A7.m.f(c2061f, "name");
        s(c2061f, bVar);
        return super.b(c2061f, bVar);
    }

    @Override // D8.r, y8.o, y8.p
    public final InterfaceC0654g c(C2061f c2061f, Y7.b bVar) {
        InterfaceC0652e interfaceC0652e;
        A7.m.f(c2061f, "name");
        A7.m.f(bVar, "location");
        s(c2061f, bVar);
        B8.t tVar = this.f1229j.f1234A;
        return (tVar == null || (interfaceC0652e = (InterfaceC0652e) ((E8.j) tVar.f808n).b(c2061f)) == null) ? super.c(c2061f, bVar) : interfaceC0652e;
    }

    @Override // y8.o, y8.p
    public final Collection d(y8.f fVar, InterfaceC2782k interfaceC2782k) {
        A7.m.f(fVar, "kindFilter");
        A7.m.f(interfaceC2782k, "nameFilter");
        return (Collection) this.f1227h.a();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.Collection] */
    /* JADX WARN: Type inference failed for: r0v4, types: [n7.u] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.util.ArrayList] */
    @Override // D8.r
    public final void h(ArrayList arrayList, InterfaceC2782k interfaceC2782k) {
        ?? arrayList2;
        A7.m.f(interfaceC2782k, "nameFilter");
        B8.t tVar = this.f1229j.f1234A;
        if (tVar != null) {
            Set<C2061f> setKeySet = ((LinkedHashMap) tVar.f807m).keySet();
            arrayList2 = new ArrayList();
            for (C2061f c2061f : setKeySet) {
                A7.m.f(c2061f, "name");
                InterfaceC0652e interfaceC0652e = (InterfaceC0652e) ((E8.j) tVar.f808n).b(c2061f);
                if (interfaceC0652e != null) {
                    arrayList2.add(interfaceC0652e);
                }
            }
        } else {
            arrayList2 = 0;
        }
        if (arrayList2 == 0) {
            arrayList2 = n7.u.l;
        }
        arrayList.addAll(arrayList2);
    }

    @Override // D8.r
    public final void j(ArrayList arrayList, C2061f c2061f) {
        A7.m.f(c2061f, "name");
        ArrayList arrayList2 = new ArrayList();
        Iterator it = ((Collection) this.f1228i.a()).iterator();
        while (it.hasNext()) {
            arrayList2.addAll(((AbstractC0287w) it.next()).z0().a(c2061f, Y7.b.f10799n));
        }
        B8.k kVar = this.f1267b;
        arrayList.addAll(((B8.i) kVar.f779a).f772n.d(c2061f, this.f1229j));
        ArrayList arrayList3 = new ArrayList(arrayList);
        ((G8.l) ((B8.i) kVar.f779a).f774q).d.h(c2061f, arrayList2, arrayList3, this.f1229j, new f(arrayList, 0));
    }

    @Override // D8.r
    public final void k(ArrayList arrayList, C2061f c2061f) {
        A7.m.f(c2061f, "name");
        ArrayList arrayList2 = new ArrayList();
        Iterator it = ((Collection) this.f1228i.a()).iterator();
        while (it.hasNext()) {
            arrayList2.addAll(((AbstractC0287w) it.next()).z0().b(c2061f, Y7.b.f10799n));
        }
        ArrayList arrayList3 = new ArrayList(arrayList);
        ((G8.l) ((B8.i) this.f1267b.f779a).f774q).d.h(c2061f, arrayList2, arrayList3, this.f1229j, new f(arrayList, 0));
    }

    @Override // D8.r
    public final C2057b l(C2061f c2061f) {
        A7.m.f(c2061f, "name");
        return this.f1229j.f1244s.d(c2061f);
    }

    @Override // D8.r
    public final Set n() {
        List listO = this.f1229j.f1250y.o();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator it = listO.iterator();
        while (it.hasNext()) {
            Set setG = ((AbstractC0287w) it.next()).z0().g();
            if (setG == null) {
                return null;
            }
            AbstractC2004s.L(linkedHashSet, setG);
        }
        return linkedHashSet;
    }

    @Override // D8.r
    public final Set o() {
        k kVar = this.f1229j;
        List listO = kVar.f1250y.o();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator it = listO.iterator();
        while (it.hasNext()) {
            AbstractC2004s.L(linkedHashSet, ((AbstractC0287w) it.next()).z0().e());
        }
        linkedHashSet.addAll(((B8.i) this.f1267b.f779a).f772n.a(kVar));
        return linkedHashSet;
    }

    @Override // D8.r
    public final Set p() {
        List listO = this.f1229j.f1250y.o();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator it = listO.iterator();
        while (it.hasNext()) {
            AbstractC2004s.L(linkedHashSet, ((AbstractC0287w) it.next()).z0().f());
        }
        return linkedHashSet;
    }

    @Override // D8.r
    public final boolean r(u uVar) {
        return ((B8.i) this.f1267b.f779a).o.e(this.f1229j, uVar);
    }

    public final void s(C2061f c2061f, Y7.b bVar) {
        A7.m.f(c2061f, "name");
        A7.m.f(bVar, "location");
        A7.m.f(((B8.i) this.f1267b.f779a).f768i, "<this>");
        A7.m.f(this.f1229j, "scopeOwner");
    }
}
