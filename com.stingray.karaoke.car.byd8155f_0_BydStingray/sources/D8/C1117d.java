package d8;

import H7.H;
import K7.K;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0654g;
import Q7.InterfaceC0655h;
import c8.C1036a;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import n7.AbstractC1996k;
import n7.AbstractC2004s;
import o8.C2061f;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: d8.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1117d implements y8.n {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final /* synthetic */ H7.w[] f13566f;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final N0.q f13567b;
    public final C1129p c;
    public final C1135v d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final E8.i f13568e;

    static {
        A7.B b10 = A7.A.f443a;
        f13566f = new H7.w[]{b10.h(new A7.s(b10.b(C1117d.class), "kotlinScopes", "getKotlinScopes()[Lorg/jetbrains/kotlin/resolve/scopes/MemberScope;"))};
    }

    public C1117d(N0.q qVar, W7.x xVar, C1129p c1129p) {
        A7.m.f(c1129p, "packageFragment");
        this.f13567b = qVar;
        this.c = c1129p;
        this.d = new C1135v(qVar, xVar, c1129p);
        E8.l lVar = ((C1036a) qVar.f4399m).f12411a;
        K k10 = new K(this, 29);
        lVar.getClass();
        this.f13568e = new E8.i(lVar, k10);
    }

    @Override // y8.n
    public final Collection a(C2061f c2061f, Y7.b bVar) {
        A7.m.f(c2061f, "name");
        i(c2061f, bVar);
        y8.n[] nVarArrH = h();
        Collection collectionA = this.d.a(c2061f, bVar);
        for (y8.n nVar : nVarArrH) {
            collectionA = H.s(collectionA, nVar.a(c2061f, bVar));
        }
        return collectionA == null ? n7.w.l : collectionA;
    }

    @Override // y8.n
    public final Collection b(C2061f c2061f, Y7.b bVar) {
        A7.m.f(c2061f, "name");
        i(c2061f, bVar);
        y8.n[] nVarArrH = h();
        this.d.getClass();
        Collection collectionS = n7.u.l;
        for (y8.n nVar : nVarArrH) {
            collectionS = H.s(collectionS, nVar.b(c2061f, bVar));
        }
        return collectionS == null ? n7.w.l : collectionS;
    }

    @Override // y8.p
    public final InterfaceC0654g c(C2061f c2061f, Y7.b bVar) {
        A7.m.f(c2061f, "name");
        A7.m.f(bVar, "location");
        i(c2061f, bVar);
        C1135v c1135v = this.d;
        c1135v.getClass();
        InterfaceC0654g interfaceC0654g = null;
        InterfaceC0652e interfaceC0652eV = c1135v.v(c2061f, null);
        if (interfaceC0652eV != null) {
            return interfaceC0652eV;
        }
        for (y8.n nVar : h()) {
            InterfaceC0654g interfaceC0654gC = nVar.c(c2061f, bVar);
            if (interfaceC0654gC != null) {
                if (!(interfaceC0654gC instanceof InterfaceC0655h) || !((InterfaceC0655h) interfaceC0654gC).c0()) {
                    return interfaceC0654gC;
                }
                if (interfaceC0654g == null) {
                    interfaceC0654g = interfaceC0654gC;
                }
            }
        }
        return interfaceC0654g;
    }

    @Override // y8.p
    public final Collection d(y8.f fVar, InterfaceC2782k interfaceC2782k) {
        A7.m.f(fVar, "kindFilter");
        A7.m.f(interfaceC2782k, "nameFilter");
        y8.n[] nVarArrH = h();
        Collection collectionD = this.d.d(fVar, interfaceC2782k);
        for (y8.n nVar : nVarArrH) {
            collectionD = H.s(collectionD, nVar.d(fVar, interfaceC2782k));
        }
        return collectionD == null ? n7.w.l : collectionD;
    }

    @Override // y8.n
    public final Set e() {
        y8.n[] nVarArrH = h();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (y8.n nVar : nVarArrH) {
            AbstractC2004s.L(linkedHashSet, nVar.e());
        }
        linkedHashSet.addAll(this.d.e());
        return linkedHashSet;
    }

    @Override // y8.n
    public final Set f() {
        y8.n[] nVarArrH = h();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (y8.n nVar : nVarArrH) {
            AbstractC2004s.L(linkedHashSet, nVar.f());
        }
        linkedHashSet.addAll(this.d.f());
        return linkedHashSet;
    }

    @Override // y8.n
    public final Set g() {
        HashSet hashSetJ = h6.e.j(AbstractC1996k.w(h()));
        if (hashSetJ == null) {
            return null;
        }
        hashSetJ.addAll(this.d.g());
        return hashSetJ;
    }

    public final y8.n[] h() {
        return (y8.n[]) C7.a.N(this.f13568e, f13566f[0]);
    }

    public final void i(C2061f c2061f, Y7.b bVar) {
        A7.m.f(c2061f, "name");
        A7.m.f(bVar, "location");
        C1036a c1036a = (C1036a) this.f13567b.f4399m;
        io.sentry.config.a.l0(c1036a.f12421n, bVar, this.c, c2061f);
    }

    public final String toString() {
        return "scope for " + this.c;
    }
}
