package defpackage;

import defpackage.ae;
import defpackage.nx;
import defpackage.qx;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: Direct.java */
/* JADX INFO: loaded from: classes.dex */
public final class da0 {
    public static final ae.a a = new ae.a();

    public static boolean a(qx qxVar) {
        qx.a[] aVarArr = qxVar.V;
        qx.a aVar = aVarArr[0];
        qx.a aVar2 = aVarArr[1];
        qx qxVar2 = qxVar.W;
        rx rxVar = qxVar2 != null ? (rx) qxVar2 : null;
        qx.a aVar3 = qx.a.FIXED;
        if (rxVar != null) {
            qx.a aVar4 = rxVar.V[0];
        }
        if (rxVar != null) {
            qx.a aVar5 = rxVar.V[1];
        }
        qx.a aVar6 = qx.a.MATCH_CONSTRAINT;
        qx.a aVar7 = qx.a.WRAP_CONTENT;
        boolean z = aVar == aVar3 || qxVar.E() || aVar == aVar7 || (aVar == aVar6 && qxVar.s == 0 && qxVar.Z == 0.0f && qxVar.x(0)) || (aVar == aVar6 && qxVar.s == 1 && qxVar.y(0, qxVar.u()));
        boolean z2 = aVar2 == aVar3 || qxVar.F() || aVar2 == aVar7 || (aVar2 == aVar6 && qxVar.t == 0 && qxVar.Z == 0.0f && qxVar.x(1)) || (aVar2 == aVar6 && qxVar.t == 1 && qxVar.y(1, qxVar.o()));
        if (qxVar.Z <= 0.0f || !(z || z2)) {
            return z && z2;
        }
        return true;
    }

    public static void b(int i, qx qxVar, ae.b bVar, boolean z) {
        nx nxVar;
        nx nxVar2;
        Iterator<nx> it;
        boolean z2;
        nx nxVar3;
        nx nxVar4;
        if (qxVar.n) {
            return;
        }
        if (!(qxVar instanceof rx) && qxVar.D() && a(qxVar)) {
            rx.Y(qxVar, bVar, new ae.a());
        }
        nx nxVarM = qxVar.m(nx.a.LEFT);
        nx nxVarM2 = qxVar.m(nx.a.RIGHT);
        int iD = nxVarM.d();
        int iD2 = nxVarM2.d();
        HashSet<nx> hashSet = nxVarM.a;
        qx.a aVar = qx.a.MATCH_CONSTRAINT;
        if (hashSet != null && nxVarM.c) {
            Iterator<nx> it2 = hashSet.iterator();
            while (it2.hasNext()) {
                nx next = it2.next();
                qx qxVar2 = next.d;
                int i2 = i + 1;
                boolean zA = a(qxVar2);
                if (qxVar2.D() && zA) {
                    rx.Y(qxVar2, bVar, new ae.a());
                }
                nx nxVar5 = qxVar2.K;
                nx nxVar6 = qxVar2.M;
                if ((next == nxVar5 && (nxVar4 = nxVar6.f) != null && nxVar4.c) || (next == nxVar6 && (nxVar3 = nxVar5.f) != null && nxVar3.c)) {
                    it = it2;
                    z2 = true;
                } else {
                    it = it2;
                    z2 = false;
                }
                qx.a aVar2 = qxVar2.V[0];
                if (aVar2 != aVar || zA) {
                    if (!qxVar2.D()) {
                        if (next == nxVar5 && nxVar6.f == null) {
                            int iE = nxVar5.e() + iD;
                            qxVar2.M(iE, qxVar2.u() + iE);
                            b(i2, qxVar2, bVar, z);
                        } else if (next == nxVar6 && nxVar5.f == null) {
                            int iE2 = iD - nxVar6.e();
                            qxVar2.M(iE2 - qxVar2.u(), iE2);
                            b(i2, qxVar2, bVar, z);
                        } else if (z2 && !qxVar2.B()) {
                            c(i2, qxVar2, bVar, z);
                        }
                    }
                } else if (aVar2 == aVar && qxVar2.w >= 0 && qxVar2.v >= 0 && ((qxVar2.j0 == 8 || (qxVar2.s == 0 && qxVar2.Z == 0.0f)) && !qxVar2.B() && !qxVar2.H && z2 && !qxVar2.B())) {
                    d(i2, qxVar, bVar, qxVar2, z);
                }
                it2 = it;
            }
        }
        if (qxVar instanceof rt0) {
            return;
        }
        HashSet<nx> hashSet2 = nxVarM2.a;
        if (hashSet2 != null && nxVarM2.c) {
            Iterator<nx> it3 = hashSet2.iterator();
            while (it3.hasNext()) {
                nx next2 = it3.next();
                qx qxVar3 = next2.d;
                int i3 = i + 1;
                boolean zA2 = a(qxVar3);
                if (qxVar3.D() && zA2) {
                    rx.Y(qxVar3, bVar, new ae.a());
                }
                nx nxVar7 = qxVar3.K;
                nx nxVar8 = qxVar3.M;
                boolean z3 = (next2 == nxVar7 && (nxVar2 = nxVar8.f) != null && nxVar2.c) || (next2 == nxVar8 && (nxVar = nxVar7.f) != null && nxVar.c);
                qx.a aVar3 = qxVar3.V[0];
                if (aVar3 != aVar || zA2) {
                    if (!qxVar3.D()) {
                        if (next2 == nxVar7 && nxVar8.f == null) {
                            int iE3 = nxVar7.e() + iD2;
                            qxVar3.M(iE3, qxVar3.u() + iE3);
                            b(i3, qxVar3, bVar, z);
                        } else if (next2 == nxVar8 && nxVar7.f == null) {
                            int iE4 = iD2 - nxVar8.e();
                            qxVar3.M(iE4 - qxVar3.u(), iE4);
                            b(i3, qxVar3, bVar, z);
                        } else if (z3 && !qxVar3.B()) {
                            c(i3, qxVar3, bVar, z);
                        }
                    }
                } else if (aVar3 == aVar && qxVar3.w >= 0 && qxVar3.v >= 0) {
                    if (qxVar3.j0 != 8) {
                        if (qxVar3.s == 0) {
                            if (qxVar3.Z == 0.0f) {
                            }
                        }
                    }
                    if (!qxVar3.B() && !qxVar3.H && z3 && !qxVar3.B()) {
                        d(i3, qxVar, bVar, qxVar3, z);
                    }
                }
            }
        }
        qxVar.n = true;
    }

    public static void c(int i, qx qxVar, ae.b bVar, boolean z) {
        float f = qxVar.g0;
        nx nxVar = qxVar.K;
        int iD = nxVar.f.d();
        nx nxVar2 = qxVar.M;
        int iD2 = nxVar2.f.d();
        int iE = nxVar.e() + iD;
        int iE2 = iD2 - nxVar2.e();
        if (iD == iD2) {
            f = 0.5f;
        } else {
            iD = iE;
            iD2 = iE2;
        }
        int iU = qxVar.u();
        int i2 = (iD2 - iD) - iU;
        if (iD > iD2) {
            i2 = (iD - iD2) - iU;
        }
        int i3 = ((int) (i2 > 0 ? (f * i2) + 0.5f : f * i2)) + iD;
        int i4 = i3 + iU;
        if (iD > iD2) {
            i4 = i3 - iU;
        }
        qxVar.M(i3, i4);
        b(i + 1, qxVar, bVar, z);
    }

    public static void d(int i, qx qxVar, ae.b bVar, qx qxVar2, boolean z) {
        float f = qxVar2.g0;
        nx nxVar = qxVar2.K;
        int iE = nxVar.e() + nxVar.f.d();
        nx nxVar2 = qxVar2.M;
        int iD = nxVar2.f.d() - nxVar2.e();
        if (iD >= iE) {
            int iU = qxVar2.u();
            if (qxVar2.j0 != 8) {
                int i2 = qxVar2.s;
                if (i2 == 2) {
                    iU = (int) (qxVar2.g0 * 0.5f * (qxVar instanceof rx ? qxVar.u() : qxVar.W.u()));
                } else if (i2 == 0) {
                    iU = iD - iE;
                }
                iU = Math.max(qxVar2.v, iU);
                int i3 = qxVar2.w;
                if (i3 > 0) {
                    iU = Math.min(i3, iU);
                }
            }
            int i4 = iE + ((int) ((f * ((iD - iE) - iU)) + 0.5f));
            qxVar2.M(i4, iU + i4);
            b(i + 1, qxVar2, bVar, z);
        }
    }

    public static void e(int i, qx qxVar, ae.b bVar) {
        float f = qxVar.h0;
        nx nxVar = qxVar.L;
        int iD = nxVar.f.d();
        nx nxVar2 = qxVar.N;
        int iD2 = nxVar2.f.d();
        int iE = nxVar.e() + iD;
        int iE2 = iD2 - nxVar2.e();
        if (iD == iD2) {
            f = 0.5f;
        } else {
            iD = iE;
            iD2 = iE2;
        }
        int iO = qxVar.o();
        int i2 = (iD2 - iD) - iO;
        if (iD > iD2) {
            i2 = (iD - iD2) - iO;
        }
        int i3 = (int) (i2 > 0 ? (f * i2) + 0.5f : f * i2);
        int i4 = iD + i3;
        int i5 = i4 + iO;
        if (iD > iD2) {
            i4 = iD - i3;
            i5 = i4 - iO;
        }
        qxVar.N(i4, i5);
        g(i + 1, qxVar, bVar);
    }

    public static void f(int i, qx qxVar, ae.b bVar, qx qxVar2) {
        float f = qxVar2.h0;
        nx nxVar = qxVar2.L;
        int iE = nxVar.e() + nxVar.f.d();
        nx nxVar2 = qxVar2.N;
        int iD = nxVar2.f.d() - nxVar2.e();
        if (iD >= iE) {
            int iO = qxVar2.o();
            if (qxVar2.j0 != 8) {
                int i2 = qxVar2.t;
                if (i2 == 2) {
                    iO = (int) (f * 0.5f * (qxVar instanceof rx ? qxVar.o() : qxVar.W.o()));
                } else if (i2 == 0) {
                    iO = iD - iE;
                }
                iO = Math.max(qxVar2.y, iO);
                int i3 = qxVar2.z;
                if (i3 > 0) {
                    iO = Math.min(i3, iO);
                }
            }
            int i4 = iE + ((int) ((f * ((iD - iE) - iO)) + 0.5f));
            qxVar2.N(i4, iO + i4);
            g(i + 1, qxVar2, bVar);
        }
    }

    public static void g(int i, qx qxVar, ae.b bVar) {
        nx nxVar;
        nx nxVar2;
        nx nxVar3;
        nx nxVar4;
        nx nxVar5;
        if (qxVar.o) {
            return;
        }
        if (!(qxVar instanceof rx) && qxVar.D() && a(qxVar)) {
            rx.Y(qxVar, bVar, new ae.a());
        }
        nx nxVarM = qxVar.m(nx.a.TOP);
        nx nxVarM2 = qxVar.m(nx.a.BOTTOM);
        int iD = nxVarM.d();
        int iD2 = nxVarM2.d();
        HashSet<nx> hashSet = nxVarM.a;
        qx.a aVar = qx.a.MATCH_CONSTRAINT;
        if (hashSet != null && nxVarM.c) {
            Iterator<nx> it = hashSet.iterator();
            while (it.hasNext()) {
                nx next = it.next();
                qx qxVar2 = next.d;
                int i2 = i + 1;
                boolean zA = a(qxVar2);
                if (qxVar2.D() && zA) {
                    rx.Y(qxVar2, bVar, new ae.a());
                }
                nx nxVar6 = qxVar2.L;
                nx nxVar7 = qxVar2.N;
                boolean z = (next == nxVar6 && (nxVar5 = nxVar7.f) != null && nxVar5.c) || (next == nxVar7 && (nxVar4 = nxVar6.f) != null && nxVar4.c);
                qx.a aVar2 = qxVar2.V[1];
                if (aVar2 != aVar || zA) {
                    if (!qxVar2.D()) {
                        if (next == nxVar6 && nxVar7.f == null) {
                            int iE = nxVar6.e() + iD;
                            qxVar2.N(iE, qxVar2.o() + iE);
                            g(i2, qxVar2, bVar);
                        } else if (next == nxVar7 && nxVar6.f == null) {
                            int iE2 = iD - nxVar7.e();
                            qxVar2.N(iE2 - qxVar2.o(), iE2);
                            g(i2, qxVar2, bVar);
                        } else if (z && !qxVar2.C()) {
                            e(i2, qxVar2, bVar);
                        }
                    }
                } else if (aVar2 == aVar && qxVar2.z >= 0 && qxVar2.y >= 0 && (qxVar2.j0 == 8 || (qxVar2.t == 0 && qxVar2.Z == 0.0f))) {
                    if (!qxVar2.C() && !qxVar2.H && z && !qxVar2.C()) {
                        f(i2, qxVar, bVar, qxVar2);
                    }
                }
            }
        }
        if (qxVar instanceof rt0) {
            return;
        }
        HashSet<nx> hashSet2 = nxVarM2.a;
        if (hashSet2 != null && nxVarM2.c) {
            Iterator<nx> it2 = hashSet2.iterator();
            while (it2.hasNext()) {
                nx next2 = it2.next();
                qx qxVar3 = next2.d;
                int i3 = i + 1;
                boolean zA2 = a(qxVar3);
                if (qxVar3.D() && zA2) {
                    rx.Y(qxVar3, bVar, new ae.a());
                }
                nx nxVar8 = qxVar3.L;
                nx nxVar9 = qxVar3.N;
                boolean z2 = (next2 == nxVar8 && (nxVar3 = nxVar9.f) != null && nxVar3.c) || (next2 == nxVar9 && (nxVar2 = nxVar8.f) != null && nxVar2.c);
                qx.a aVar3 = qxVar3.V[1];
                if (aVar3 != aVar || zA2) {
                    if (!qxVar3.D()) {
                        if (next2 == nxVar8 && nxVar9.f == null) {
                            int iE3 = nxVar8.e() + iD2;
                            qxVar3.N(iE3, qxVar3.o() + iE3);
                            g(i3, qxVar3, bVar);
                        } else if (next2 == nxVar9 && nxVar8.f == null) {
                            int iE4 = iD2 - nxVar9.e();
                            qxVar3.N(iE4 - qxVar3.o(), iE4);
                            g(i3, qxVar3, bVar);
                        } else if (z2 && !qxVar3.C()) {
                            e(i3, qxVar3, bVar);
                        }
                    }
                } else if (aVar3 == aVar && qxVar3.z >= 0 && qxVar3.y >= 0) {
                    if (qxVar3.j0 != 8) {
                        if (qxVar3.t == 0) {
                            if (qxVar3.Z == 0.0f) {
                            }
                        }
                    }
                    if (!qxVar3.C() && !qxVar3.H && z2 && !qxVar3.C()) {
                        f(i3, qxVar, bVar, qxVar3);
                    }
                }
            }
        }
        nx nxVarM3 = qxVar.m(nx.a.BASELINE);
        if (nxVarM3.a != null && nxVarM3.c) {
            int iD3 = nxVarM3.d();
            for (nx nxVar10 : nxVarM3.a) {
                qx qxVar4 = nxVar10.d;
                int i4 = i + 1;
                boolean zA3 = a(qxVar4);
                if (qxVar4.D() && zA3) {
                    rx.Y(qxVar4, bVar, new ae.a());
                }
                if (qxVar4.V[1] != aVar || zA3) {
                    if (!qxVar4.D() && nxVar10 == (nxVar = qxVar4.O)) {
                        int iE5 = nxVar10.e() + iD3;
                        if (qxVar4.F) {
                            int i5 = iE5 - qxVar4.d0;
                            int i6 = qxVar4.Y + i5;
                            qxVar4.c0 = i5;
                            qxVar4.L.l(i5);
                            qxVar4.N.l(i6);
                            nxVar.l(iE5);
                            qxVar4.m = true;
                        }
                        g(i4, qxVar4, bVar);
                    }
                }
            }
        }
        qxVar.o = true;
    }
}
