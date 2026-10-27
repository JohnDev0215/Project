package s;

import java.util.ArrayList;
import java.util.Iterator;
import r.d;
import r.e;
import s.b;

/* JADX INFO: compiled from: Direct.java */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static b.a f9421a = new b.a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static int f9422b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static int f9423c = 0;

    private static boolean a(int i7, r.e eVar) {
        e.b bVar;
        e.b bVar2;
        e.b bVarC = eVar.C();
        e.b bVarV = eVar.V();
        r.f fVar = eVar.M() != null ? (r.f) eVar.M() : null;
        if (fVar != null) {
            fVar.C();
            e.b bVar3 = e.b.FIXED;
        }
        if (fVar != null) {
            fVar.V();
            e.b bVar4 = e.b.FIXED;
        }
        e.b bVar5 = e.b.FIXED;
        boolean z6 = bVarC == bVar5 || eVar.p0() || bVarC == e.b.WRAP_CONTENT || (bVarC == (bVar2 = e.b.MATCH_CONSTRAINT) && eVar.f9248w == 0 && eVar.f9215f0 == 0.0f && eVar.c0(0)) || (bVarC == bVar2 && eVar.f9248w == 1 && eVar.f0(0, eVar.Y()));
        boolean z7 = bVarV == bVar5 || eVar.q0() || bVarV == e.b.WRAP_CONTENT || (bVarV == (bVar = e.b.MATCH_CONSTRAINT) && eVar.f9250x == 0 && eVar.f9215f0 == 0.0f && eVar.c0(1)) || (bVarV == bVar && eVar.f9250x == 1 && eVar.f0(1, eVar.z()));
        if (eVar.f9215f0 <= 0.0f || !(z6 || z7)) {
            return z6 && z7;
        }
        return true;
    }

    private static void b(int i7, r.e eVar, b.InterfaceC0143b interfaceC0143b, boolean z6) {
        r.d dVar;
        r.d dVar2;
        r.d dVar3;
        r.d dVar4;
        if (eVar.i0()) {
            return;
        }
        boolean z7 = true;
        f9422b++;
        if (!(eVar instanceof r.f) && eVar.o0()) {
            int i8 = i7 + 1;
            if (a(i8, eVar)) {
                r.f.V1(i8, eVar, interfaceC0143b, new b.a(), b.a.f9383k);
            }
        }
        r.d dVarQ = eVar.q(d.b.LEFT);
        r.d dVarQ2 = eVar.q(d.b.RIGHT);
        int iE = dVarQ.e();
        int iE2 = dVarQ2.e();
        if (dVarQ.d() != null && dVarQ.n()) {
            Iterator<r.d> it = dVarQ.d().iterator();
            while (it.hasNext()) {
                r.d next = it.next();
                r.e eVar2 = next.f9196d;
                int i9 = i7 + 1;
                boolean zA = a(i9, eVar2);
                if (eVar2.o0() && zA) {
                    r.f.V1(i9, eVar2, interfaceC0143b, new b.a(), b.a.f9383k);
                }
                boolean z8 = ((next == eVar2.Q && (dVar4 = eVar2.S.f9198f) != null && dVar4.n()) || (next == eVar2.S && (dVar3 = eVar2.Q.f9198f) != null && dVar3.n())) ? z7 : false;
                e.b bVarC = eVar2.C();
                e.b bVar = e.b.MATCH_CONSTRAINT;
                if (bVarC != bVar || zA) {
                    if (!eVar2.o0()) {
                        r.d dVar5 = eVar2.Q;
                        if (next == dVar5 && eVar2.S.f9198f == null) {
                            int iF = dVar5.f() + iE;
                            eVar2.I0(iF, eVar2.Y() + iF);
                            b(i9, eVar2, interfaceC0143b, z6);
                        } else {
                            r.d dVar6 = eVar2.S;
                            if (next == dVar6 && dVar5.f9198f == null) {
                                int iF2 = iE - dVar6.f();
                                eVar2.I0(iF2 - eVar2.Y(), iF2);
                                b(i9, eVar2, interfaceC0143b, z6);
                            } else if (z8 && !eVar2.k0()) {
                                d(i9, interfaceC0143b, eVar2, z6);
                            }
                        }
                    }
                } else if (eVar2.C() == bVar && eVar2.A >= 0 && eVar2.f9254z >= 0 && ((eVar2.X() == 8 || (eVar2.f9248w == 0 && eVar2.x() == 0.0f)) && !eVar2.k0() && !eVar2.n0() && z8 && !eVar2.k0())) {
                    e(i9, eVar, interfaceC0143b, eVar2, z6);
                }
                z7 = true;
            }
        }
        if (eVar instanceof r.h) {
            return;
        }
        if (dVarQ2.d() != null && dVarQ2.n()) {
            Iterator<r.d> it2 = dVarQ2.d().iterator();
            while (it2.hasNext()) {
                r.d next2 = it2.next();
                r.e eVar3 = next2.f9196d;
                int i10 = i7 + 1;
                boolean zA2 = a(i10, eVar3);
                if (eVar3.o0() && zA2) {
                    r.f.V1(i10, eVar3, interfaceC0143b, new b.a(), b.a.f9383k);
                }
                boolean z9 = (next2 == eVar3.Q && (dVar2 = eVar3.S.f9198f) != null && dVar2.n()) || (next2 == eVar3.S && (dVar = eVar3.Q.f9198f) != null && dVar.n());
                e.b bVarC2 = eVar3.C();
                e.b bVar2 = e.b.MATCH_CONSTRAINT;
                if (bVarC2 != bVar2 || zA2) {
                    if (!eVar3.o0()) {
                        r.d dVar7 = eVar3.Q;
                        if (next2 == dVar7 && eVar3.S.f9198f == null) {
                            int iF3 = dVar7.f() + iE2;
                            eVar3.I0(iF3, eVar3.Y() + iF3);
                            b(i10, eVar3, interfaceC0143b, z6);
                        } else {
                            r.d dVar8 = eVar3.S;
                            if (next2 == dVar8 && dVar7.f9198f == null) {
                                int iF4 = iE2 - dVar8.f();
                                eVar3.I0(iF4 - eVar3.Y(), iF4);
                                b(i10, eVar3, interfaceC0143b, z6);
                            } else if (z9 && !eVar3.k0()) {
                                d(i10, interfaceC0143b, eVar3, z6);
                            }
                        }
                    }
                } else if (eVar3.C() == bVar2 && eVar3.A >= 0 && eVar3.f9254z >= 0 && (eVar3.X() == 8 || (eVar3.f9248w == 0 && eVar3.x() == 0.0f))) {
                    if (!eVar3.k0() && !eVar3.n0() && z9 && !eVar3.k0()) {
                        e(i10, eVar, interfaceC0143b, eVar3, z6);
                    }
                }
            }
        }
        eVar.s0();
    }

    private static void c(int i7, r.a aVar, b.InterfaceC0143b interfaceC0143b, int i8, boolean z6) {
        if (aVar.w1()) {
            if (i8 == 0) {
                b(i7 + 1, aVar, interfaceC0143b, z6);
            } else {
                i(i7 + 1, aVar, interfaceC0143b);
            }
        }
    }

    private static void d(int i7, b.InterfaceC0143b interfaceC0143b, r.e eVar, boolean z6) {
        float fA = eVar.A();
        int iE = eVar.Q.f9198f.e();
        int iE2 = eVar.S.f9198f.e();
        int iF = eVar.Q.f() + iE;
        int iF2 = iE2 - eVar.S.f();
        if (iE == iE2) {
            fA = 0.5f;
        } else {
            iE = iF;
            iE2 = iF2;
        }
        int iY = eVar.Y();
        int i8 = (iE2 - iE) - iY;
        if (iE > iE2) {
            i8 = (iE - iE2) - iY;
        }
        int i9 = ((int) (i8 > 0 ? (fA * i8) + 0.5f : fA * i8)) + iE;
        int i10 = i9 + iY;
        if (iE > iE2) {
            i10 = i9 - iY;
        }
        eVar.I0(i9, i10);
        b(i7 + 1, eVar, interfaceC0143b, z6);
    }

    private static void e(int i7, r.e eVar, b.InterfaceC0143b interfaceC0143b, r.e eVar2, boolean z6) {
        float fA = eVar2.A();
        int iE = eVar2.Q.f9198f.e() + eVar2.Q.f();
        int iE2 = eVar2.S.f9198f.e() - eVar2.S.f();
        if (iE2 >= iE) {
            int iY = eVar2.Y();
            if (eVar2.X() != 8) {
                int i8 = eVar2.f9248w;
                if (i8 == 2) {
                    iY = (int) (eVar2.A() * 0.5f * (eVar instanceof r.f ? eVar.Y() : eVar.M().Y()));
                } else if (i8 == 0) {
                    iY = iE2 - iE;
                }
                iY = Math.max(eVar2.f9254z, iY);
                int i9 = eVar2.A;
                if (i9 > 0) {
                    iY = Math.min(i9, iY);
                }
            }
            int i10 = iE + ((int) ((fA * ((iE2 - iE) - iY)) + 0.5f));
            eVar2.I0(i10, iY + i10);
            b(i7 + 1, eVar2, interfaceC0143b, z6);
        }
    }

    private static void f(int i7, b.InterfaceC0143b interfaceC0143b, r.e eVar) {
        float fT = eVar.T();
        int iE = eVar.R.f9198f.e();
        int iE2 = eVar.T.f9198f.e();
        int iF = eVar.R.f() + iE;
        int iF2 = iE2 - eVar.T.f();
        if (iE == iE2) {
            fT = 0.5f;
        } else {
            iE = iF;
            iE2 = iF2;
        }
        int iZ = eVar.z();
        int i8 = (iE2 - iE) - iZ;
        if (iE > iE2) {
            i8 = (iE - iE2) - iZ;
        }
        int i9 = (int) (i8 > 0 ? (fT * i8) + 0.5f : fT * i8);
        int i10 = iE + i9;
        int i11 = i10 + iZ;
        if (iE > iE2) {
            i10 = iE - i9;
            i11 = i10 - iZ;
        }
        eVar.L0(i10, i11);
        i(i7 + 1, eVar, interfaceC0143b);
    }

    private static void g(int i7, r.e eVar, b.InterfaceC0143b interfaceC0143b, r.e eVar2) {
        float fT = eVar2.T();
        int iE = eVar2.R.f9198f.e() + eVar2.R.f();
        int iE2 = eVar2.T.f9198f.e() - eVar2.T.f();
        if (iE2 >= iE) {
            int iZ = eVar2.z();
            if (eVar2.X() != 8) {
                int i8 = eVar2.f9250x;
                if (i8 == 2) {
                    iZ = (int) (fT * 0.5f * (eVar instanceof r.f ? eVar.z() : eVar.M().z()));
                } else if (i8 == 0) {
                    iZ = iE2 - iE;
                }
                iZ = Math.max(eVar2.C, iZ);
                int i9 = eVar2.D;
                if (i9 > 0) {
                    iZ = Math.min(i9, iZ);
                }
            }
            int i10 = iE + ((int) ((fT * ((iE2 - iE) - iZ)) + 0.5f));
            eVar2.L0(i10, iZ + i10);
            i(i7 + 1, eVar2, interfaceC0143b);
        }
    }

    public static void h(r.f fVar, b.InterfaceC0143b interfaceC0143b) {
        e.b bVarC = fVar.C();
        e.b bVarV = fVar.V();
        f9422b = 0;
        f9423c = 0;
        fVar.y0();
        ArrayList<r.e> arrayListU1 = fVar.u1();
        int size = arrayListU1.size();
        for (int i7 = 0; i7 < size; i7++) {
            arrayListU1.get(i7).y0();
        }
        boolean zS1 = fVar.S1();
        if (bVarC == e.b.FIXED) {
            fVar.I0(0, fVar.Y());
        } else {
            fVar.J0(0);
        }
        boolean z6 = false;
        boolean z7 = false;
        for (int i8 = 0; i8 < size; i8++) {
            r.e eVar = arrayListU1.get(i8);
            if (eVar instanceof r.h) {
                r.h hVar = (r.h) eVar;
                if (hVar.v1() == 1) {
                    if (hVar.w1() != -1) {
                        hVar.z1(hVar.w1());
                    } else if (hVar.x1() != -1 && fVar.p0()) {
                        hVar.z1(fVar.Y() - hVar.x1());
                    } else if (fVar.p0()) {
                        hVar.z1((int) ((hVar.y1() * fVar.Y()) + 0.5f));
                    }
                    z6 = true;
                }
            } else if ((eVar instanceof r.a) && ((r.a) eVar).A1() == 0) {
                z7 = true;
            }
        }
        if (z6) {
            for (int i9 = 0; i9 < size; i9++) {
                r.e eVar2 = arrayListU1.get(i9);
                if (eVar2 instanceof r.h) {
                    r.h hVar2 = (r.h) eVar2;
                    if (hVar2.v1() == 1) {
                        b(0, hVar2, interfaceC0143b, zS1);
                    }
                }
            }
        }
        b(0, fVar, interfaceC0143b, zS1);
        if (z7) {
            for (int i10 = 0; i10 < size; i10++) {
                r.e eVar3 = arrayListU1.get(i10);
                if (eVar3 instanceof r.a) {
                    r.a aVar = (r.a) eVar3;
                    if (aVar.A1() == 0) {
                        c(0, aVar, interfaceC0143b, 0, zS1);
                    }
                }
            }
        }
        if (bVarV == e.b.FIXED) {
            fVar.L0(0, fVar.z());
        } else {
            fVar.K0(0);
        }
        boolean z8 = false;
        boolean z9 = false;
        for (int i11 = 0; i11 < size; i11++) {
            r.e eVar4 = arrayListU1.get(i11);
            if (eVar4 instanceof r.h) {
                r.h hVar3 = (r.h) eVar4;
                if (hVar3.v1() == 0) {
                    if (hVar3.w1() != -1) {
                        hVar3.z1(hVar3.w1());
                    } else if (hVar3.x1() != -1 && fVar.q0()) {
                        hVar3.z1(fVar.z() - hVar3.x1());
                    } else if (fVar.q0()) {
                        hVar3.z1((int) ((hVar3.y1() * fVar.z()) + 0.5f));
                    }
                    z8 = true;
                }
            } else if ((eVar4 instanceof r.a) && ((r.a) eVar4).A1() == 1) {
                z9 = true;
            }
        }
        if (z8) {
            for (int i12 = 0; i12 < size; i12++) {
                r.e eVar5 = arrayListU1.get(i12);
                if (eVar5 instanceof r.h) {
                    r.h hVar4 = (r.h) eVar5;
                    if (hVar4.v1() == 0) {
                        i(1, hVar4, interfaceC0143b);
                    }
                }
            }
        }
        i(0, fVar, interfaceC0143b);
        if (z9) {
            for (int i13 = 0; i13 < size; i13++) {
                r.e eVar6 = arrayListU1.get(i13);
                if (eVar6 instanceof r.a) {
                    r.a aVar2 = (r.a) eVar6;
                    if (aVar2.A1() == 1) {
                        c(0, aVar2, interfaceC0143b, 1, zS1);
                    }
                }
            }
        }
        for (int i14 = 0; i14 < size; i14++) {
            r.e eVar7 = arrayListU1.get(i14);
            if (eVar7.o0() && a(0, eVar7)) {
                r.f.V1(0, eVar7, interfaceC0143b, f9421a, b.a.f9383k);
                if (!(eVar7 instanceof r.h)) {
                    b(0, eVar7, interfaceC0143b, zS1);
                    i(0, eVar7, interfaceC0143b);
                } else if (((r.h) eVar7).v1() == 0) {
                    i(0, eVar7, interfaceC0143b);
                } else {
                    b(0, eVar7, interfaceC0143b, zS1);
                }
            }
        }
    }

    private static void i(int i7, r.e eVar, b.InterfaceC0143b interfaceC0143b) {
        r.d dVar;
        r.d dVar2;
        r.d dVar3;
        r.d dVar4;
        if (eVar.r0()) {
            return;
        }
        f9423c++;
        if (!(eVar instanceof r.f) && eVar.o0()) {
            int i8 = i7 + 1;
            if (a(i8, eVar)) {
                r.f.V1(i8, eVar, interfaceC0143b, new b.a(), b.a.f9383k);
            }
        }
        r.d dVarQ = eVar.q(d.b.TOP);
        r.d dVarQ2 = eVar.q(d.b.BOTTOM);
        int iE = dVarQ.e();
        int iE2 = dVarQ2.e();
        if (dVarQ.d() != null && dVarQ.n()) {
            Iterator<r.d> it = dVarQ.d().iterator();
            while (it.hasNext()) {
                r.d next = it.next();
                r.e eVar2 = next.f9196d;
                int i9 = i7 + 1;
                boolean zA = a(i9, eVar2);
                if (eVar2.o0() && zA) {
                    r.f.V1(i9, eVar2, interfaceC0143b, new b.a(), b.a.f9383k);
                }
                boolean z6 = (next == eVar2.R && (dVar4 = eVar2.T.f9198f) != null && dVar4.n()) || (next == eVar2.T && (dVar3 = eVar2.R.f9198f) != null && dVar3.n());
                e.b bVarV = eVar2.V();
                e.b bVar = e.b.MATCH_CONSTRAINT;
                if (bVarV != bVar || zA) {
                    if (!eVar2.o0()) {
                        r.d dVar5 = eVar2.R;
                        if (next == dVar5 && eVar2.T.f9198f == null) {
                            int iF = dVar5.f() + iE;
                            eVar2.L0(iF, eVar2.z() + iF);
                            i(i9, eVar2, interfaceC0143b);
                        } else {
                            r.d dVar6 = eVar2.T;
                            if (next == dVar6 && dVar5.f9198f == null) {
                                int iF2 = iE - dVar6.f();
                                eVar2.L0(iF2 - eVar2.z(), iF2);
                                i(i9, eVar2, interfaceC0143b);
                            } else if (z6 && !eVar2.m0()) {
                                f(i9, interfaceC0143b, eVar2);
                            }
                        }
                    }
                } else if (eVar2.V() == bVar && eVar2.D >= 0 && eVar2.C >= 0 && (eVar2.X() == 8 || (eVar2.f9250x == 0 && eVar2.x() == 0.0f))) {
                    if (!eVar2.m0() && !eVar2.n0() && z6 && !eVar2.m0()) {
                        g(i9, eVar, interfaceC0143b, eVar2);
                    }
                }
            }
        }
        if (eVar instanceof r.h) {
            return;
        }
        if (dVarQ2.d() != null && dVarQ2.n()) {
            Iterator<r.d> it2 = dVarQ2.d().iterator();
            while (it2.hasNext()) {
                r.d next2 = it2.next();
                r.e eVar3 = next2.f9196d;
                int i10 = i7 + 1;
                boolean zA2 = a(i10, eVar3);
                if (eVar3.o0() && zA2) {
                    r.f.V1(i10, eVar3, interfaceC0143b, new b.a(), b.a.f9383k);
                }
                boolean z7 = (next2 == eVar3.R && (dVar2 = eVar3.T.f9198f) != null && dVar2.n()) || (next2 == eVar3.T && (dVar = eVar3.R.f9198f) != null && dVar.n());
                e.b bVarV2 = eVar3.V();
                e.b bVar2 = e.b.MATCH_CONSTRAINT;
                if (bVarV2 != bVar2 || zA2) {
                    if (!eVar3.o0()) {
                        r.d dVar7 = eVar3.R;
                        if (next2 == dVar7 && eVar3.T.f9198f == null) {
                            int iF3 = dVar7.f() + iE2;
                            eVar3.L0(iF3, eVar3.z() + iF3);
                            i(i10, eVar3, interfaceC0143b);
                        } else {
                            r.d dVar8 = eVar3.T;
                            if (next2 == dVar8 && dVar7.f9198f == null) {
                                int iF4 = iE2 - dVar8.f();
                                eVar3.L0(iF4 - eVar3.z(), iF4);
                                i(i10, eVar3, interfaceC0143b);
                            } else if (z7 && !eVar3.m0()) {
                                f(i10, interfaceC0143b, eVar3);
                            }
                        }
                    }
                } else if (eVar3.V() == bVar2 && eVar3.D >= 0 && eVar3.C >= 0 && (eVar3.X() == 8 || (eVar3.f9250x == 0 && eVar3.x() == 0.0f))) {
                    if (!eVar3.m0() && !eVar3.n0() && z7 && !eVar3.m0()) {
                        g(i10, eVar, interfaceC0143b, eVar3);
                    }
                }
            }
        }
        r.d dVarQ3 = eVar.q(d.b.BASELINE);
        if (dVarQ3.d() != null && dVarQ3.n()) {
            int iE3 = dVarQ3.e();
            for (r.d dVar9 : dVarQ3.d()) {
                r.e eVar4 = dVar9.f9196d;
                int i11 = i7 + 1;
                boolean zA3 = a(i11, eVar4);
                if (eVar4.o0() && zA3) {
                    r.f.V1(i11, eVar4, interfaceC0143b, new b.a(), b.a.f9383k);
                }
                if (eVar4.V() != e.b.MATCH_CONSTRAINT || zA3) {
                    if (!eVar4.o0() && dVar9 == eVar4.U) {
                        eVar4.H0(dVar9.f() + iE3);
                        i(i11, eVar4, interfaceC0143b);
                    }
                }
            }
        }
        eVar.t0();
    }
}
