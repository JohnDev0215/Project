package J;

import A7.m;
import a1.k;
import m0.f;
import n0.AbstractC1858H;
import n0.C1856F;
import n0.C1857G;
import n0.InterfaceC1866P;
import n7.z;

/* JADX INFO: loaded from: classes.dex */
public final class d implements InterfaceC1866P {
    public final a l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final a f3039m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final a f3040n;
    public final a o;

    public d(a aVar, a aVar2, a aVar3, a aVar4) {
        this.l = aVar;
        this.f3039m = aVar2;
        this.f3040n = aVar3;
        this.o = aVar4;
    }

    public static d a(d dVar, b bVar, b bVar2, b bVar3, int i7) {
        a aVar = bVar;
        if ((i7 & 1) != 0) {
            aVar = dVar.l;
        }
        a aVar2 = dVar.f3039m;
        a aVar3 = bVar2;
        if ((i7 & 4) != 0) {
            aVar3 = dVar.f3040n;
        }
        dVar.getClass();
        return new d(aVar, aVar2, aVar3, bVar3);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        if (!m.a(this.l, dVar.l)) {
            return false;
        }
        if (!m.a(this.f3039m, dVar.f3039m)) {
            return false;
        }
        if (m.a(this.f3040n, dVar.f3040n)) {
            return m.a(this.o, dVar.o);
        }
        return false;
    }

    public final int hashCode() {
        return this.o.hashCode() + ((this.f3040n.hashCode() + ((this.f3039m.hashCode() + (this.l.hashCode() * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "RoundedCornerShape(topStart = " + this.l + ", topEnd = " + this.f3039m + ", bottomEnd = " + this.f3040n + ", bottomStart = " + this.o + ')';
    }

    @Override // n0.InterfaceC1866P
    public final AbstractC1858H v(long j3, k kVar, a1.b bVar) {
        float fA = this.l.a(j3, bVar);
        float fA2 = this.f3039m.a(j3, bVar);
        float fA3 = this.f3040n.a(j3, bVar);
        float fA4 = this.o.a(j3, bVar);
        float fC = f.c(j3);
        float f10 = fA + fA4;
        if (f10 > fC) {
            float f11 = fC / f10;
            fA *= f11;
            fA4 *= f11;
        }
        float f12 = fA2 + fA3;
        if (f12 > fC) {
            float f13 = fC / f12;
            fA2 *= f13;
            fA3 *= f13;
        }
        if (fA < 0.0f || fA2 < 0.0f || fA3 < 0.0f || fA4 < 0.0f) {
            throw new IllegalArgumentException(("Corner size in Px can't be negative(topStart = " + fA + ", topEnd = " + fA2 + ", bottomEnd = " + fA3 + ", bottomStart = " + fA4 + ")!").toString());
        }
        if (fA + fA2 + fA3 + fA4 == 0.0f) {
            return new C1856F(h6.f.a(0L, j3));
        }
        m0.d dVarA = h6.f.a(0L, j3);
        k kVar2 = k.l;
        float f14 = kVar == kVar2 ? fA : fA2;
        long jA = z.a(f14, f14);
        if (kVar == kVar2) {
            fA = fA2;
        }
        long jA2 = z.a(fA, fA);
        float f15 = kVar == kVar2 ? fA3 : fA4;
        long jA3 = z.a(f15, f15);
        if (kVar != kVar2) {
            fA4 = fA3;
        }
        return new C1857G(new m0.e(dVarA.f18882a, dVarA.f18883b, dVarA.c, dVarA.d, jA, jA2, jA3, z.a(fA4, fA4)));
    }
}
