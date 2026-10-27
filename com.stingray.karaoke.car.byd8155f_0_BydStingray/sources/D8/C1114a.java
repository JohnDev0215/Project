package d8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import n7.AbstractC1998m;
import n7.AbstractC2000o;
import o8.C2061f;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: d8.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1114a implements InterfaceC1116c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final W7.n f13561a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final A7.o f13562b;
    public final S0.p c;
    public final LinkedHashMap d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final LinkedHashMap f13563e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final LinkedHashMap f13564f;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v4, types: [A7.o, z7.k] */
    /* JADX WARN: Type inference failed for: r6v6, types: [A7.o, z7.k] */
    public C1114a(W7.n nVar, InterfaceC2782k interfaceC2782k) {
        A7.m.f(nVar, "jClass");
        this.f13561a = nVar;
        this.f13562b = (A7.o) interfaceC2782k;
        S0.p pVar = new S0.p(this, 13);
        this.c = pVar;
        P8.g gVar = new P8.g(AbstractC1998m.S(nVar.d()), true, pVar);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        P8.f fVar = new P8.f(gVar);
        while (fVar.hasNext()) {
            Object next = fVar.next();
            C2061f c2061fC = ((W7.w) next).c();
            Object arrayList = linkedHashMap.get(c2061fC);
            if (arrayList == null) {
                arrayList = new ArrayList();
                linkedHashMap.put(c2061fC, arrayList);
            }
            ((List) arrayList).add(next);
        }
        this.d = linkedHashMap;
        P8.g gVar2 = new P8.g(AbstractC1998m.S(this.f13561a.b()), true, this.f13562b);
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        P8.f fVar2 = new P8.f(gVar2);
        while (fVar2.hasNext()) {
            Object next2 = fVar2.next();
            linkedHashMap2.put(((W7.t) next2).c(), next2);
        }
        this.f13563e = linkedHashMap2;
        ArrayList arrayListE = this.f13561a.e();
        ?? r62 = this.f13562b;
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayListE) {
            if (((Boolean) r62.b(obj)).booleanValue()) {
                arrayList2.add(obj);
            }
        }
        int iQ = n7.z.q(AbstractC2000o.J(arrayList2, 10));
        LinkedHashMap linkedHashMap3 = new LinkedHashMap(iQ < 16 ? 16 : iQ);
        for (Object obj2 : arrayList2) {
            linkedHashMap3.put(((W7.z) obj2).c(), obj2);
        }
        this.f13564f = linkedHashMap3;
    }

    @Override // d8.InterfaceC1116c
    public final Collection a(C2061f c2061f) {
        A7.m.f(c2061f, "name");
        List list = (List) this.d.get(c2061f);
        return list != null ? list : n7.u.l;
    }

    @Override // d8.InterfaceC1116c
    public final Set b() {
        P8.g gVar = new P8.g(AbstractC1998m.S(this.f13561a.d()), true, this.c);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        P8.f fVar = new P8.f(gVar);
        while (fVar.hasNext()) {
            linkedHashSet.add(((W7.w) fVar.next()).c());
        }
        return linkedHashSet;
    }

    @Override // d8.InterfaceC1116c
    public final W7.t c(C2061f c2061f) {
        A7.m.f(c2061f, "name");
        return (W7.t) this.f13563e.get(c2061f);
    }

    @Override // d8.InterfaceC1116c
    public final Set d() {
        return this.f13564f.keySet();
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [A7.o, z7.k] */
    @Override // d8.InterfaceC1116c
    public final Set e() {
        P8.g gVar = new P8.g(AbstractC1998m.S(this.f13561a.b()), true, this.f13562b);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        P8.f fVar = new P8.f(gVar);
        while (fVar.hasNext()) {
            linkedHashSet.add(((W7.t) fVar.next()).c());
        }
        return linkedHashSet;
    }

    @Override // d8.InterfaceC1116c
    public final W7.z f(C2061f c2061f) {
        A7.m.f(c2061f, "name");
        return (W7.z) this.f13564f.get(c2061f);
    }
}
