package r;

import r.e;

/* JADX INFO: compiled from: Optimizer.java */
/* JADX INFO: loaded from: classes.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static boolean[] f9317a = new boolean[3];

    static void a(f fVar, o.d dVar, e eVar) {
        eVar.f9242t = -1;
        eVar.f9244u = -1;
        e.b bVar = fVar.f9207b0[0];
        e.b bVar2 = e.b.WRAP_CONTENT;
        if (bVar != bVar2 && eVar.f9207b0[0] == e.b.MATCH_PARENT) {
            int i7 = eVar.Q.f9199g;
            int iY = fVar.Y() - eVar.S.f9199g;
            d dVar2 = eVar.Q;
            dVar2.f9201i = dVar.q(dVar2);
            d dVar3 = eVar.S;
            dVar3.f9201i = dVar.q(dVar3);
            dVar.f(eVar.Q.f9201i, i7);
            dVar.f(eVar.S.f9201i, iY);
            eVar.f9242t = 2;
            eVar.R0(i7, iY);
        }
        if (fVar.f9207b0[1] == bVar2 || eVar.f9207b0[1] != e.b.MATCH_PARENT) {
            return;
        }
        int i8 = eVar.R.f9199g;
        int iZ = fVar.z() - eVar.T.f9199g;
        d dVar4 = eVar.R;
        dVar4.f9201i = dVar.q(dVar4);
        d dVar5 = eVar.T;
        dVar5.f9201i = dVar.q(dVar5);
        dVar.f(eVar.R.f9201i, i8);
        dVar.f(eVar.T.f9201i, iZ);
        if (eVar.f9231n0 > 0 || eVar.X() == 8) {
            d dVar6 = eVar.U;
            dVar6.f9201i = dVar.q(dVar6);
            dVar.f(eVar.U.f9201i, eVar.f9231n0 + i8);
        }
        eVar.f9244u = 2;
        eVar.i1(i8, iZ);
    }

    public static final boolean b(int i7, int i8) {
        return (i7 & i8) == i8;
    }
}
