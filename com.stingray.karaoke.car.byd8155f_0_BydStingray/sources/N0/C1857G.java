package n0;

/* JADX INFO: renamed from: n0.G, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1857G extends AbstractC1858H {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final m0.e f19377a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C1880i f19378b;

    public C1857G(m0.e eVar) {
        C1880i c1880iI;
        this.f19377a = eVar;
        if (h6.i.u(eVar)) {
            c1880iI = null;
        } else {
            c1880iI = AbstractC1861K.i();
            InterfaceC1859I.a(c1880iI, eVar);
        }
        this.f19378b = c1880iI;
    }

    @Override // n0.AbstractC1858H
    public final m0.d a() {
        m0.e eVar = this.f19377a;
        return new m0.d(eVar.f18884a, eVar.f18885b, eVar.c, eVar.d);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C1857G) {
            return A7.m.a(this.f19377a, ((C1857G) obj).f19377a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f19377a.hashCode();
    }
}
