package b.e.b.k;

import b.e.b.k.e;

/* JADX INFO: compiled from: Optimizer.java */
/* JADX INFO: loaded from: classes.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static boolean[] f2464a = new boolean[3];

    static void a(f fVar, b.e.b.d dVar, e eVar) {
        eVar.k = -1;
        eVar.l = -1;
        e.b bVar = fVar.P[0];
        e.b bVar2 = e.b.WRAP_CONTENT;
        if (bVar != bVar2 && eVar.P[0] == e.b.MATCH_PARENT) {
            int i2 = eVar.E.f2426e;
            int iQ = fVar.Q() - eVar.G.f2426e;
            d dVar2 = eVar.E;
            dVar2.f2428g = dVar.q(dVar2);
            d dVar3 = eVar.G;
            dVar3.f2428g = dVar.q(dVar3);
            dVar.f(eVar.E.f2428g, i2);
            dVar.f(eVar.G.f2428g, iQ);
            eVar.k = 2;
            eVar.l0(i2, iQ);
        }
        if (fVar.P[1] == bVar2 || eVar.P[1] != e.b.MATCH_PARENT) {
            return;
        }
        int i3 = eVar.F.f2426e;
        int iW = fVar.w() - eVar.H.f2426e;
        d dVar4 = eVar.F;
        dVar4.f2428g = dVar.q(dVar4);
        d dVar5 = eVar.H;
        dVar5.f2428g = dVar.q(dVar5);
        dVar.f(eVar.F.f2428g, i3);
        dVar.f(eVar.H.f2428g, iW);
        if (eVar.b0 > 0 || eVar.P() == 8) {
            d dVar6 = eVar.I;
            dVar6.f2428g = dVar.q(dVar6);
            dVar.f(eVar.I.f2428g, eVar.b0 + i3);
        }
        eVar.l = 2;
        eVar.A0(i3, iW);
    }

    public static final boolean b(int i2, int i3) {
        return (i2 & i3) == i3;
    }
}
