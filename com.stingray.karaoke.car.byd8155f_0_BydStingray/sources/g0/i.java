package g0;

import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class i implements o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final o f14681a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final o f14682b;

    public i(o oVar, o oVar2) {
        this.f14681a = oVar;
        this.f14682b = oVar2;
    }

    @Override // g0.o
    public final boolean e(InterfaceC2782k interfaceC2782k) {
        return this.f14681a.e(interfaceC2782k) && this.f14682b.e(interfaceC2782k);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof i) {
            i iVar = (i) obj;
            if (A7.m.a(this.f14681a, iVar.f14681a) && A7.m.a(this.f14682b, iVar.f14682b)) {
                return true;
            }
        }
        return false;
    }

    @Override // g0.o
    public final Object h(Object obj, InterfaceC2785n interfaceC2785n) {
        return this.f14682b.h(this.f14681a.h(obj, interfaceC2785n), interfaceC2785n);
    }

    public final int hashCode() {
        return (this.f14682b.hashCode() * 31) + this.f14681a.hashCode();
    }

    public final String toString() {
        return android.hardware.bydauto.ac.tsimpl.a.k(new StringBuilder("["), (String) h("", h.f14680m), ']');
    }
}
