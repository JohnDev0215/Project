package a.f.a.i;

import a.f.a.i.d;

/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean[] f566a = new boolean[3];

    public static void a(e eVar, a.f.a.d dVar, d dVar2) {
        d.a aVar = d.a.MATCH_PARENT;
        d.a aVar2 = d.a.WRAP_CONTENT;
        dVar2.q = -1;
        dVar2.r = -1;
        if (eVar.W[0] != aVar2 && dVar2.W[0] == aVar) {
            int i = dVar2.L.g;
            int iU = eVar.u() - dVar2.N.g;
            c cVar = dVar2.L;
            cVar.i = dVar.l(cVar);
            c cVar2 = dVar2.N;
            cVar2.i = dVar.l(cVar2);
            dVar.e(dVar2.L.i, i);
            dVar.e(dVar2.N.i, iU);
            dVar2.q = 2;
            dVar2.c0 = i;
            int i2 = iU - i;
            dVar2.Y = i2;
            int i3 = dVar2.f0;
            if (i2 < i3) {
                dVar2.Y = i3;
            }
        }
        if (eVar.W[1] == aVar2 || dVar2.W[1] != aVar) {
            return;
        }
        int i4 = dVar2.M.g;
        int iL = eVar.l() - dVar2.O.g;
        c cVar3 = dVar2.M;
        cVar3.i = dVar.l(cVar3);
        c cVar4 = dVar2.O;
        cVar4.i = dVar.l(cVar4);
        dVar.e(dVar2.M.i, i4);
        dVar.e(dVar2.O.i, iL);
        if (dVar2.e0 > 0 || dVar2.k0 == 8) {
            c cVar5 = dVar2.P;
            cVar5.i = dVar.l(cVar5);
            dVar.e(dVar2.P.i, dVar2.e0 + i4);
        }
        dVar2.r = 2;
        dVar2.d0 = i4;
        int i5 = iL - i4;
        dVar2.Z = i5;
        int i6 = dVar2.g0;
        if (i5 < i6) {
            dVar2.Z = i6;
        }
    }

    public static final boolean b(int i, int i2) {
        return (i & i2) == i2;
    }
}
