package F8;

import Q7.InterfaceC0654g;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import n7.AbstractC1998m;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: F8.v, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0286v implements K, I8.f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public AbstractC0287w f2483a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final LinkedHashSet f2484b;
    public final int c;

    public C0286v(AbstractCollection abstractCollection) {
        A7.m.f(abstractCollection, "typesToIntersect");
        abstractCollection.isEmpty();
        LinkedHashSet linkedHashSet = new LinkedHashSet(abstractCollection);
        this.f2484b = linkedHashSet;
        this.c = linkedHashSet.hashCode();
    }

    public final A b() {
        H.f2437m.getClass();
        return C0269d.t(H.f2438n, this, n7.u.l, false, h6.i.g("member scope for intersection type", this.f2484b), new A.F(this, 15));
    }

    public final String c(InterfaceC2782k interfaceC2782k) {
        A7.m.f(interfaceC2782k, "getProperTypeRelatedToStringify");
        return AbstractC1998m.f0(AbstractC1998m.u0(this.f2484b, new F0.K(interfaceC2782k, 2)), " & ", "{", "}", new C0285u(0, interfaceC2782k), 24);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C0286v) {
            return A7.m.a(this.f2484b, ((C0286v) obj).f2484b);
        }
        return false;
    }

    public final int hashCode() {
        return this.c;
    }

    @Override // F8.K
    public final N7.i l() {
        N7.i iVarL = ((AbstractC0287w) this.f2484b.iterator().next()).B0().l();
        A7.m.e(iVarL, "intersectedTypes.iterato…xt().constructor.builtIns");
        return iVarL;
    }

    @Override // F8.K
    public final boolean m() {
        return false;
    }

    @Override // F8.K
    public final InterfaceC0654g n() {
        return null;
    }

    @Override // F8.K
    public final Collection o() {
        return this.f2484b;
    }

    @Override // F8.K
    public final List p() {
        return n7.u.l;
    }

    public final String toString() {
        return c(C0271f.o);
    }
}
