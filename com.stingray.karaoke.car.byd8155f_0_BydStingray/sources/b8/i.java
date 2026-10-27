package B8;

import F8.C0277l;
import Q7.H;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0671y;
import T.C0746i0;
import h8.AbstractC1387d;
import h8.C1391h;
import java.util.List;
import java.util.Set;
import l8.AbstractC1783a;
import l8.C1790h;
import l8.InterfaceC1788f;
import n0.C1886o;
import o8.C2057b;
import p8.C2132g;

/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final E8.o f762a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final InterfaceC0671y f763b;
    public final j c;
    public final InterfaceC0093e d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final InterfaceC0089a f764e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final H f765f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final j f766g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final l f767h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Y7.a f768i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final m f769j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final Iterable f770k;
    public final t l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final j f771m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final S7.b f772n;
    public final S7.d o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final C2132g f773p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final G8.k f774q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final S7.a f775r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final List f776s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final g f777t;

    public i(E8.o oVar, InterfaceC0671y interfaceC0671y, InterfaceC0093e interfaceC0093e, InterfaceC0089a interfaceC0089a, H h10, l lVar, m mVar, Iterable iterable, t tVar, S7.b bVar, S7.d dVar, C2132g c2132g, G8.l lVar2, C1886o c1886o, List list, int i7) {
        G8.l lVar3;
        j jVar = j.f778b;
        j jVar2 = j.d;
        Y7.a aVar = Y7.a.f10797a;
        j jVar3 = h.f761a;
        if ((i7 & 65536) != 0) {
            G8.k.f2736b.getClass();
            lVar3 = G8.j.f2735b;
        } else {
            lVar3 = lVar2;
        }
        S7.a aVar2 = S7.a.f8078e;
        List listJ = (i7 & 524288) != 0 ? AbstractC1387d.j(C0277l.f2472a) : list;
        A7.m.f(oVar, "storageManager");
        A7.m.f(interfaceC0671y, "moduleDescriptor");
        A7.m.f(iterable, "fictitiousClassDescriptorFactories");
        A7.m.f(bVar, "additionalClassPartsProvider");
        A7.m.f(dVar, "platformDependentDeclarationFilter");
        A7.m.f(c2132g, "extensionRegistryLite");
        A7.m.f(lVar3, "kotlinTypeChecker");
        A7.m.f(listJ, "typeAttributeTranslators");
        this.f762a = oVar;
        this.f763b = interfaceC0671y;
        this.c = jVar;
        this.d = interfaceC0093e;
        this.f764e = interfaceC0089a;
        this.f765f = h10;
        this.f766g = jVar2;
        this.f767h = lVar;
        this.f768i = aVar;
        this.f769j = mVar;
        this.f770k = iterable;
        this.l = tVar;
        this.f771m = jVar3;
        this.f772n = bVar;
        this.o = dVar;
        this.f773p = c2132g;
        this.f774q = lVar3;
        this.f775r = aVar2;
        this.f776s = listJ;
        this.f777t = new g(this);
    }

    public final k a(Q7.D d, InterfaceC1788f interfaceC1788f, C0746i0 c0746i0, C1790h c1790h, AbstractC1783a abstractC1783a, C1391h c1391h) {
        A7.m.f(d, "descriptor");
        A7.m.f(interfaceC1788f, "nameResolver");
        A7.m.f(abstractC1783a, "metadataVersion");
        return new k(this, interfaceC1788f, d, c0746i0, c1790h, abstractC1783a, c1391h, null, n7.u.l);
    }

    public final InterfaceC0652e b(C2057b c2057b) {
        A7.m.f(c2057b, "classId");
        Set set = g.c;
        return this.f777t.a(c2057b, null);
    }
}
