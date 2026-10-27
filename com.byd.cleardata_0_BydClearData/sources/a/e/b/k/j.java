package a.e.b.k;

import a.e.b.k.e;

/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static boolean[] f222a = new boolean[3];

    static void a(f fVar, a.e.b.d dVar, e eVar) {
        eVar.k = -1;
        eVar.l = -1;
        e.b bVar = fVar.N[0];
        e.b bVar2 = e.b.WRAP_CONTENT;
        if (bVar != bVar2 && eVar.N[0] == e.b.MATCH_PARENT) {
            int i = eVar.C.e;
            int iN = fVar.N() - eVar.E.e;
            d dVar2 = eVar.C;
            dVar2.g = dVar.q(dVar2);
            d dVar3 = eVar.E;
            dVar3.g = dVar.q(dVar3);
            dVar.f(eVar.C.g, i);
            dVar.f(eVar.E.g, iN);
            eVar.k = 2;
            eVar.h0(i, iN);
        }
        if (fVar.N[1] == bVar2 || eVar.N[1] != e.b.MATCH_PARENT) {
            return;
        }
        int i2 = eVar.D.e;
        int iT = fVar.t() - eVar.F.e;
        d dVar4 = eVar.D;
        dVar4.g = dVar.q(dVar4);
        d dVar5 = eVar.F;
        dVar5.g = dVar.q(dVar5);
        dVar.f(eVar.D.g, i2);
        dVar.f(eVar.F.g, iT);
        if (eVar.Z > 0 || eVar.M() == 8) {
            d dVar6 = eVar.G;
            dVar6.g = dVar.q(dVar6);
            dVar.f(eVar.G.g, eVar.Z + i2);
        }
        eVar.l = 2;
        eVar.w0(i2, iT);
    }

    public static final boolean b(int i, int i2) {
        return (i & i2) == i2;
    }
}
