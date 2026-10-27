package N0;

import S0.AbstractC0703o;
import h2.AbstractC1347d;
import n0.C1891t;
import x.AbstractC2641j;

/* JADX INFO: renamed from: N0.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0414i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final G f4382a;

    public C0414i(G g7) {
        this.f4382a = g7;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0414i)) {
            return false;
        }
        G g7 = this.f4382a;
        C0414i c0414i = (C0414i) obj;
        if (!A7.m.a(g7.f4340a, c0414i.f4382a.f4340a)) {
            return false;
        }
        G g10 = c0414i.f4382a;
        if (!g7.f4341b.d(g10.f4341b) || !A7.m.a(g7.c, g10.c) || g7.d != g10.d || g7.f4342e != g10.f4342e || !C7.a.F(g7.f4343f, g10.f4343f) || !A7.m.a(g7.f4344g, g10.f4344g) || g7.f4345h != g10.f4345h || g7.f4346i != g10.f4346i) {
            return false;
        }
        long j3 = g7.f4347j;
        return a1.a.h(j3) == a1.a.h(g10.f4347j) && a1.a.g(j3) == a1.a.g(g10.f4347j);
    }

    public final int hashCode() {
        G g7 = this.f4382a;
        int iHashCode = g7.f4340a.hashCode() * 31;
        L l = g7.f4341b;
        D d = l.f4358a;
        d.getClass();
        a1.n[] nVarArr = a1.m.f11041b;
        int iHashCode2 = Long.hashCode(d.f4325b) * 31;
        S0.A a10 = d.c;
        int i7 = (iHashCode2 + (a10 != null ? a10.l : 0)) * 31;
        S0.w wVar = d.d;
        int iHashCode3 = (i7 + (wVar != null ? Integer.hashCode(wVar.f7860a) : 0)) * 31;
        S0.x xVar = d.f4326e;
        int iHashCode4 = (iHashCode3 + (xVar != null ? Integer.hashCode(xVar.f7861a) : 0)) * 31;
        AbstractC0703o abstractC0703o = d.f4327f;
        int iHashCode5 = (iHashCode4 + (abstractC0703o != null ? abstractC0703o.hashCode() : 0)) * 31;
        String str = d.f4328g;
        int iC = AbstractC1347d.c((iHashCode5 + (str != null ? str.hashCode() : 0)) * 31, 31, d.f4329h);
        Y0.a aVar = d.f4330i;
        int iHashCode6 = (iC + (aVar != null ? Float.hashCode(aVar.f10605a) : 0)) * 31;
        Y0.n nVar = d.f4331j;
        int iHashCode7 = (iHashCode6 + (nVar != null ? nVar.hashCode() : 0)) * 31;
        U0.b bVar = d.f4332k;
        int iHashCode8 = (iHashCode7 + (bVar != null ? bVar.l.hashCode() : 0)) * 31;
        int i10 = C1891t.l;
        int iC2 = AbstractC1347d.c(iHashCode8, 31, d.l);
        x xVar2 = d.o;
        int iHashCode9 = (l.f4359b.hashCode() + ((iC2 + (xVar2 != null ? xVar2.hashCode() : 0)) * 31)) * 31;
        y yVar = l.c;
        int iHashCode10 = (g7.f4346i.hashCode() + ((g7.f4345h.hashCode() + ((g7.f4344g.hashCode() + AbstractC2641j.b(g7.f4343f, AbstractC1347d.d((((g7.c.hashCode() + ((iHashCode9 + (yVar != null ? yVar.hashCode() : 0) + iHashCode) * 31)) * 31) + g7.d) * 31, 31, g7.f4342e), 31)) * 31)) * 31)) * 31;
        long j3 = g7.f4347j;
        return Integer.hashCode(a1.a.g(j3)) + ((Integer.hashCode(a1.a.h(j3)) + iHashCode10) * 31);
    }
}
