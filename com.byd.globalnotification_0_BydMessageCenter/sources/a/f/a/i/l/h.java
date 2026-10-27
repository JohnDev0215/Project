package a.f.a.i.l;

import a.f.a.i.c;
import a.f.a.i.d;
import a.f.a.i.l.b;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static b.a f594a = new b.a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static int f595b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static int f596c;

    public static boolean a(int i, a.f.a.i.d dVar) {
        d.a aVar = d.a.WRAP_CONTENT;
        d.a aVar2 = d.a.MATCH_CONSTRAINT;
        d.a aVar3 = d.a.FIXED;
        d.a aVarM = dVar.m();
        d.a aVarT = dVar.t();
        a.f.a.i.d dVar2 = dVar.X;
        a.f.a.i.e eVar = dVar2 != null ? (a.f.a.i.e) dVar2 : null;
        if (eVar != null) {
            eVar.m();
        }
        if (eVar != null) {
            eVar.t();
        }
        boolean z = aVarM == aVar3 || dVar.D() || aVarM == aVar || (aVarM == aVar2 && dVar.t == 0 && dVar.a0 == 0.0f && dVar.x(0)) || (aVarM == aVar2 && dVar.t == 1 && dVar.y(0, dVar.u()));
        boolean z2 = aVarT == aVar3 || dVar.E() || aVarT == aVar || (aVarT == aVar2 && dVar.u == 0 && dVar.a0 == 0.0f && dVar.x(1)) || (aVarT == aVar2 && dVar.u == 1 && dVar.y(1, dVar.l()));
        if (dVar.a0 <= 0.0f || !(z || z2)) {
            return z && z2;
        }
        return true;
    }

    public static void b(int i, a.f.a.i.d dVar, b.InterfaceC0007b interfaceC0007b, boolean z) {
        boolean z2;
        a.f.a.i.c cVar;
        a.f.a.i.c cVar2;
        a.f.a.i.c cVar3;
        a.f.a.i.c cVar4;
        d.a aVar = d.a.MATCH_CONSTRAINT;
        if (dVar.o) {
            return;
        }
        f595b++;
        int i2 = 0;
        if (!(dVar instanceof a.f.a.i.e) && dVar.C()) {
            int i3 = i + 1;
            if (a(i3, dVar)) {
                a.f.a.i.e.e0(i3, dVar, interfaceC0007b, new b.a(), 0);
            }
        }
        a.f.a.i.c cVarI = dVar.i(c.a.LEFT);
        a.f.a.i.c cVarI2 = dVar.i(c.a.RIGHT);
        int iC = cVarI.c();
        int iC2 = cVarI2.c();
        HashSet<a.f.a.i.c> hashSet = cVarI.f543a;
        if (hashSet != null && cVarI.f545c) {
            Iterator<a.f.a.i.c> it = hashSet.iterator();
            while (it.hasNext()) {
                a.f.a.i.c next = it.next();
                a.f.a.i.d dVar2 = next.f546d;
                int i4 = i + 1;
                boolean zA = a(i4, dVar2);
                if (dVar2.C() && zA) {
                    a.f.a.i.e.e0(i4, dVar2, interfaceC0007b, new b.a(), i2);
                }
                int i5 = ((next == dVar2.L && (cVar4 = dVar2.N.f548f) != null && cVar4.f545c) || (next == dVar2.N && (cVar3 = dVar2.L.f548f) != null && cVar3.f545c)) ? 1 : i2;
                if (dVar2.m() != aVar || zA) {
                    if (!dVar2.C()) {
                        a.f.a.i.c cVar5 = dVar2.L;
                        if (next == cVar5 && dVar2.N.f548f == null) {
                            int iD = cVar5.d() + iC;
                            dVar2.L(iD, dVar2.u() + iD);
                        } else {
                            a.f.a.i.c cVar6 = dVar2.N;
                            if (next == cVar6 && dVar2.L.f548f == null) {
                                int iD2 = iC - cVar6.d();
                                dVar2.L(iD2 - dVar2.u(), iD2);
                            } else if (i5 != 0 && !dVar2.A()) {
                                c(i4, interfaceC0007b, dVar2, z);
                            }
                        }
                        b(i4, dVar2, interfaceC0007b, z);
                    }
                } else if (dVar2.m() == aVar && dVar2.x >= 0 && dVar2.w >= 0 && ((dVar2.k0 == 8 || (dVar2.t == 0 && dVar2.a0 == 0.0f)) && !dVar2.A() && !dVar2.I && i5 != 0 && !dVar2.A())) {
                    d(i4, dVar, interfaceC0007b, dVar2, z);
                }
                i2 = 0;
            }
        }
        if (dVar instanceof a.f.a.i.f) {
            return;
        }
        HashSet<a.f.a.i.c> hashSet2 = cVarI2.f543a;
        if (hashSet2 != null && cVarI2.f545c) {
            Iterator<a.f.a.i.c> it2 = hashSet2.iterator();
            while (it2.hasNext()) {
                a.f.a.i.c next2 = it2.next();
                a.f.a.i.d dVar3 = next2.f546d;
                int i6 = i + 1;
                boolean zA2 = a(i6, dVar3);
                if (dVar3.C() && zA2) {
                    z2 = false;
                    a.f.a.i.e.e0(i6, dVar3, interfaceC0007b, new b.a(), 0);
                } else {
                    z2 = false;
                }
                boolean z3 = ((next2 == dVar3.L && (cVar2 = dVar3.N.f548f) != null && cVar2.f545c) || (next2 == dVar3.N && (cVar = dVar3.L.f548f) != null && cVar.f545c)) ? true : z2;
                if (dVar3.m() != aVar || zA2) {
                    if (!dVar3.C()) {
                        a.f.a.i.c cVar7 = dVar3.L;
                        if (next2 == cVar7 && dVar3.N.f548f == null) {
                            int iD3 = cVar7.d() + iC2;
                            dVar3.L(iD3, dVar3.u() + iD3);
                        } else {
                            a.f.a.i.c cVar8 = dVar3.N;
                            if (next2 == cVar8 && dVar3.L.f548f == null) {
                                int iD4 = iC2 - cVar8.d();
                                dVar3.L(iD4 - dVar3.u(), iD4);
                            } else if (z3 && !dVar3.A()) {
                                c(i6, interfaceC0007b, dVar3, z);
                            }
                        }
                        b(i6, dVar3, interfaceC0007b, z);
                    }
                } else if (dVar3.m() == aVar && dVar3.x >= 0 && dVar3.w >= 0 && (dVar3.k0 == 8 || (dVar3.t == 0 && dVar3.a0 == 0.0f))) {
                    if (!dVar3.A() && !dVar3.I && z3 && !dVar3.A()) {
                        d(i6, dVar, interfaceC0007b, dVar3, z);
                    }
                }
            }
        }
        dVar.o = true;
    }

    public static void c(int i, b.InterfaceC0007b interfaceC0007b, a.f.a.i.d dVar, boolean z) {
        float f2 = dVar.h0;
        int iC = dVar.L.f548f.c();
        int iC2 = dVar.N.f548f.c();
        int iD = dVar.L.d() + iC;
        int iD2 = iC2 - dVar.N.d();
        if (iC == iC2) {
            f2 = 0.5f;
        } else {
            iC = iD;
            iC2 = iD2;
        }
        int iU = dVar.u();
        int i2 = (iC2 - iC) - iU;
        if (iC > iC2) {
            i2 = (iC - iC2) - iU;
        }
        int i3 = ((int) (i2 > 0 ? (f2 * i2) + 0.5f : f2 * i2)) + iC;
        int i4 = i3 + iU;
        if (iC > iC2) {
            i4 = i3 - iU;
        }
        dVar.L(i3, i4);
        b(i + 1, dVar, interfaceC0007b, z);
    }

    public static void d(int i, a.f.a.i.d dVar, b.InterfaceC0007b interfaceC0007b, a.f.a.i.d dVar2, boolean z) {
        float f2 = dVar2.h0;
        int iD = dVar2.L.d() + dVar2.L.f548f.c();
        int iC = dVar2.N.f548f.c() - dVar2.N.d();
        if (iC >= iD) {
            int iU = dVar2.u();
            if (dVar2.k0 != 8) {
                int i2 = dVar2.t;
                if (i2 == 2) {
                    if (!(dVar instanceof a.f.a.i.e)) {
                        dVar = dVar.X;
                    }
                    iU = (int) (dVar2.h0 * 0.5f * dVar.u());
                } else if (i2 == 0) {
                    iU = iC - iD;
                }
                iU = Math.max(dVar2.w, iU);
                int i3 = dVar2.x;
                if (i3 > 0) {
                    iU = Math.min(i3, iU);
                }
            }
            int i4 = iD + ((int) ((f2 * ((iC - iD) - iU)) + 0.5f));
            dVar2.L(i4, iU + i4);
            b(i + 1, dVar2, interfaceC0007b, z);
        }
    }

    public static void e(int i, b.InterfaceC0007b interfaceC0007b, a.f.a.i.d dVar) {
        float f2 = dVar.i0;
        int iC = dVar.M.f548f.c();
        int iC2 = dVar.O.f548f.c();
        int iD = dVar.M.d() + iC;
        int iD2 = iC2 - dVar.O.d();
        if (iC == iC2) {
            f2 = 0.5f;
        } else {
            iC = iD;
            iC2 = iD2;
        }
        int iL = dVar.l();
        int i2 = (iC2 - iC) - iL;
        if (iC > iC2) {
            i2 = (iC - iC2) - iL;
        }
        int i3 = (int) (i2 > 0 ? (f2 * i2) + 0.5f : f2 * i2);
        int i4 = iC + i3;
        int i5 = i4 + iL;
        if (iC > iC2) {
            i4 = iC - i3;
            i5 = i4 - iL;
        }
        dVar.M(i4, i5);
        g(i + 1, dVar, interfaceC0007b);
    }

    public static void f(int i, a.f.a.i.d dVar, b.InterfaceC0007b interfaceC0007b, a.f.a.i.d dVar2) {
        float f2 = dVar2.i0;
        int iD = dVar2.M.d() + dVar2.M.f548f.c();
        int iC = dVar2.O.f548f.c() - dVar2.O.d();
        if (iC >= iD) {
            int iL = dVar2.l();
            if (dVar2.k0 != 8) {
                int i2 = dVar2.u;
                if (i2 == 2) {
                    if (!(dVar instanceof a.f.a.i.e)) {
                        dVar = dVar.X;
                    }
                    iL = (int) (f2 * 0.5f * dVar.l());
                } else if (i2 == 0) {
                    iL = iC - iD;
                }
                iL = Math.max(dVar2.z, iL);
                int i3 = dVar2.A;
                if (i3 > 0) {
                    iL = Math.min(i3, iL);
                }
            }
            int i4 = iD + ((int) ((f2 * ((iC - iD) - iL)) + 0.5f));
            dVar2.M(i4, iL + i4);
            g(i + 1, dVar2, interfaceC0007b);
        }
    }

    public static void g(int i, a.f.a.i.d dVar, b.InterfaceC0007b interfaceC0007b) {
        a.f.a.i.c cVar;
        a.f.a.i.c cVar2;
        a.f.a.i.c cVar3;
        a.f.a.i.c cVar4;
        d.a aVar = d.a.MATCH_CONSTRAINT;
        if (dVar.p) {
            return;
        }
        boolean z = true;
        f596c++;
        if (!(dVar instanceof a.f.a.i.e) && dVar.C()) {
            int i2 = i + 1;
            if (a(i2, dVar)) {
                a.f.a.i.e.e0(i2, dVar, interfaceC0007b, new b.a(), 0);
            }
        }
        a.f.a.i.c cVarI = dVar.i(c.a.TOP);
        a.f.a.i.c cVarI2 = dVar.i(c.a.BOTTOM);
        int iC = cVarI.c();
        int iC2 = cVarI2.c();
        HashSet<a.f.a.i.c> hashSet = cVarI.f543a;
        if (hashSet != null && cVarI.f545c) {
            Iterator<a.f.a.i.c> it = hashSet.iterator();
            while (it.hasNext()) {
                a.f.a.i.c next = it.next();
                a.f.a.i.d dVar2 = next.f546d;
                int i3 = i + 1;
                boolean zA = a(i3, dVar2);
                if (dVar2.C() && zA) {
                    a.f.a.i.e.e0(i3, dVar2, interfaceC0007b, new b.a(), 0);
                }
                boolean z2 = ((next == dVar2.M && (cVar4 = dVar2.O.f548f) != null && cVar4.f545c) || (next == dVar2.O && (cVar3 = dVar2.M.f548f) != null && cVar3.f545c)) ? z : false;
                if (dVar2.t() != aVar || zA) {
                    if (!dVar2.C()) {
                        a.f.a.i.c cVar5 = dVar2.M;
                        if (next == cVar5 && dVar2.O.f548f == null) {
                            int iD = cVar5.d() + iC;
                            dVar2.M(iD, dVar2.l() + iD);
                        } else {
                            a.f.a.i.c cVar6 = dVar2.O;
                            if (next == cVar6 && dVar2.M.f548f == null) {
                                int iD2 = iC - cVar6.d();
                                dVar2.M(iD2 - dVar2.l(), iD2);
                            } else if (z2 && !dVar2.B()) {
                                e(i3, interfaceC0007b, dVar2);
                            }
                        }
                        g(i3, dVar2, interfaceC0007b);
                    }
                } else if (dVar2.t() == aVar && dVar2.A >= 0 && dVar2.z >= 0 && ((dVar2.k0 == 8 || (dVar2.u == 0 && dVar2.a0 == 0.0f)) && !dVar2.B() && !dVar2.I && z2 && !dVar2.B())) {
                    f(i3, dVar, interfaceC0007b, dVar2);
                }
                z = true;
            }
        }
        if (dVar instanceof a.f.a.i.f) {
            return;
        }
        HashSet<a.f.a.i.c> hashSet2 = cVarI2.f543a;
        if (hashSet2 != null && cVarI2.f545c) {
            Iterator<a.f.a.i.c> it2 = hashSet2.iterator();
            while (it2.hasNext()) {
                a.f.a.i.c next2 = it2.next();
                a.f.a.i.d dVar3 = next2.f546d;
                int i4 = i + 1;
                boolean zA2 = a(i4, dVar3);
                if (dVar3.C() && zA2) {
                    a.f.a.i.e.e0(i4, dVar3, interfaceC0007b, new b.a(), 0);
                }
                boolean z3 = (next2 == dVar3.M && (cVar2 = dVar3.O.f548f) != null && cVar2.f545c) || (next2 == dVar3.O && (cVar = dVar3.M.f548f) != null && cVar.f545c);
                if (dVar3.t() != aVar || zA2) {
                    if (!dVar3.C()) {
                        a.f.a.i.c cVar7 = dVar3.M;
                        if (next2 == cVar7 && dVar3.O.f548f == null) {
                            int iD3 = cVar7.d() + iC2;
                            dVar3.M(iD3, dVar3.l() + iD3);
                        } else {
                            a.f.a.i.c cVar8 = dVar3.O;
                            if (next2 == cVar8 && dVar3.M.f548f == null) {
                                int iD4 = iC2 - cVar8.d();
                                dVar3.M(iD4 - dVar3.l(), iD4);
                            } else if (z3 && !dVar3.B()) {
                                e(i4, interfaceC0007b, dVar3);
                            }
                        }
                        g(i4, dVar3, interfaceC0007b);
                    }
                } else if (dVar3.t() == aVar && dVar3.A >= 0 && dVar3.z >= 0 && (dVar3.k0 == 8 || (dVar3.u == 0 && dVar3.a0 == 0.0f))) {
                    if (!dVar3.B() && !dVar3.I && z3 && !dVar3.B()) {
                        f(i4, dVar, interfaceC0007b, dVar3);
                    }
                }
            }
        }
        a.f.a.i.c cVarI3 = dVar.i(c.a.BASELINE);
        if (cVarI3.f543a != null && cVarI3.f545c) {
            int iC3 = cVarI3.c();
            for (a.f.a.i.c cVar9 : cVarI3.f543a) {
                a.f.a.i.d dVar4 = cVar9.f546d;
                int i5 = i + 1;
                boolean zA3 = a(i5, dVar4);
                if (dVar4.C() && zA3) {
                    a.f.a.i.e.e0(i5, dVar4, interfaceC0007b, new b.a(), 0);
                }
                if (dVar4.t() != aVar || zA3) {
                    if (!dVar4.C() && cVar9 == dVar4.P) {
                        int iD5 = cVar9.d() + iC3;
                        if (dVar4.G) {
                            int i6 = iD5 - dVar4.e0;
                            int i7 = dVar4.Z + i6;
                            dVar4.d0 = i6;
                            dVar4.M.j(i6);
                            dVar4.O.j(i7);
                            a.f.a.i.c cVar10 = dVar4.P;
                            cVar10.f544b = iD5;
                            cVar10.f545c = true;
                            dVar4.n = true;
                        }
                        g(i5, dVar4, interfaceC0007b);
                    }
                }
            }
        }
        dVar.p = true;
    }
}
