package defpackage;

import defpackage.qx;

/* JADX INFO: compiled from: Optimizer.java */
/* JADX INFO: loaded from: classes.dex */
public final class xs1 {
    public static final boolean[] a = new boolean[3];

    public static void a(rx rxVar, q71 q71Var, qx qxVar) {
        qxVar.p = -1;
        qxVar.q = -1;
        qx.a aVar = rxVar.V[0];
        qx.a aVar2 = qx.a.WRAP_CONTENT;
        qx.a aVar3 = qx.a.MATCH_PARENT;
        if (aVar != aVar2 && qxVar.V[0] == aVar3) {
            nx nxVar = qxVar.K;
            int i = nxVar.g;
            int iU = rxVar.u();
            nx nxVar2 = qxVar.M;
            int i2 = iU - nxVar2.g;
            nxVar.i = q71Var.k(nxVar);
            nxVar2.i = q71Var.k(nxVar2);
            q71Var.d(nxVar.i, i);
            q71Var.d(nxVar2.i, i2);
            qxVar.p = 2;
            qxVar.b0 = i;
            int i3 = i2 - i;
            qxVar.X = i3;
            int i4 = qxVar.e0;
            if (i3 < i4) {
                qxVar.X = i4;
            }
        }
        if (rxVar.V[1] == aVar2 || qxVar.V[1] != aVar3) {
            return;
        }
        nx nxVar3 = qxVar.L;
        int i5 = nxVar3.g;
        int iO = rxVar.o();
        nx nxVar4 = qxVar.N;
        int i6 = iO - nxVar4.g;
        nxVar3.i = q71Var.k(nxVar3);
        nxVar4.i = q71Var.k(nxVar4);
        q71Var.d(nxVar3.i, i5);
        q71Var.d(nxVar4.i, i6);
        if (qxVar.d0 > 0 || qxVar.j0 == 8) {
            nx nxVar5 = qxVar.O;
            nxVar5.i = q71Var.k(nxVar5);
            q71Var.d(nxVar5.i, qxVar.d0 + i5);
        }
        qxVar.q = 2;
        qxVar.c0 = i5;
        int i7 = i6 - i5;
        qxVar.Y = i7;
        int i8 = qxVar.f0;
        if (i7 < i8) {
            qxVar.Y = i8;
        }
    }

    public static final boolean b(int i, int i2) {
        return (i & i2) == i2;
    }
}
