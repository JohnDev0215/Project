package p1;

import java.util.ArrayList;
import java.util.HashSet;

/* JADX INFO: renamed from: p1.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2081i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C2074b f20670a = new C2074b();

    public static boolean a(o1.d dVar) {
        int[] iArr = dVar.f20311q0;
        int i7 = iArr[0];
        int i10 = iArr[1];
        o1.e eVar = dVar.f20276U;
        if (eVar == null) {
            eVar = null;
        }
        if (eVar != null) {
            int i11 = eVar.f20311q0[0];
        }
        if (eVar != null) {
            int i12 = eVar.f20311q0[1];
        }
        boolean z6 = i7 == 1 || dVar.B() || i7 == 2 || (i7 == 3 && dVar.f20313s == 0 && dVar.f20279X == 0.0f && dVar.u(0)) || (i7 == 3 && dVar.f20313s == 1 && dVar.v(0, dVar.r()));
        boolean z10 = i10 == 1 || dVar.C() || i10 == 2 || (i10 == 3 && dVar.f20314t == 0 && dVar.f20279X == 0.0f && dVar.u(1)) || (i10 == 3 && dVar.f20314t == 1 && dVar.v(1, dVar.l()));
        if (dVar.f20279X <= 0.0f || !(z6 || z10)) {
            return z6 && z10;
        }
        return true;
    }

    public static o b(o1.d dVar, int i7, ArrayList arrayList, o oVar) {
        int i10;
        int i11 = i7 == 0 ? dVar.f20307o0 : dVar.f20309p0;
        if (i11 != -1 && (oVar == null || i11 != oVar.f20677b)) {
            int i12 = 0;
            while (true) {
                if (i12 >= arrayList.size()) {
                    break;
                }
                o oVar2 = (o) arrayList.get(i12);
                if (oVar2.f20677b == i11) {
                    if (oVar != null) {
                        oVar.c(i7, oVar2);
                        arrayList.remove(oVar);
                    }
                    oVar = oVar2;
                } else {
                    i12++;
                }
            }
        } else if (i11 != -1) {
            return oVar;
        }
        if (oVar == null) {
            if (dVar instanceof o1.i) {
                o1.i iVar = (o1.i) dVar;
                int i13 = 0;
                while (true) {
                    if (i13 >= iVar.f20386s0) {
                        i10 = -1;
                        break;
                    }
                    o1.d dVar2 = iVar.f20385r0[i13];
                    if ((i7 == 0 && (i10 = dVar2.f20307o0) != -1) || (i7 == 1 && (i10 = dVar2.f20309p0) != -1)) {
                        break;
                    }
                    i13++;
                }
                if (i10 != -1) {
                    int i14 = 0;
                    while (true) {
                        if (i14 >= arrayList.size()) {
                            break;
                        }
                        o oVar3 = (o) arrayList.get(i14);
                        if (oVar3.f20677b == i10) {
                            oVar = oVar3;
                            break;
                        }
                        i14++;
                    }
                }
            }
            if (oVar == null) {
                oVar = new o();
                oVar.f20676a = new ArrayList();
                oVar.d = null;
                oVar.f20678e = -1;
                int i15 = o.f20675f;
                o.f20675f = i15 + 1;
                oVar.f20677b = i15;
                oVar.c = i7;
            }
            arrayList.add(oVar);
        }
        ArrayList arrayList2 = oVar.f20676a;
        if (!arrayList2.contains(dVar)) {
            arrayList2.add(dVar);
            if (dVar instanceof o1.h) {
                o1.h hVar = (o1.h) dVar;
                hVar.f20382u0.c(hVar.f20383v0 == 0 ? 1 : 0, arrayList, oVar);
            }
            int i16 = oVar.f20677b;
            if (i7 == 0) {
                dVar.f20307o0 = i16;
                dVar.f20265J.c(i7, arrayList, oVar);
                dVar.f20267L.c(i7, arrayList, oVar);
            } else {
                dVar.f20309p0 = i16;
                dVar.f20266K.c(i7, arrayList, oVar);
                dVar.f20269N.c(i7, arrayList, oVar);
                dVar.f20268M.c(i7, arrayList, oVar);
            }
            dVar.f20272Q.c(i7, arrayList, oVar);
        }
        return oVar;
    }

    public static void c(int i7, o1.d dVar, InterfaceC2075c interfaceC2075c, boolean z6) {
        o1.c cVar;
        o1.c cVar2;
        o1.c cVar3;
        o1.c cVar4;
        if (dVar.f20305n) {
            return;
        }
        if (!(dVar instanceof o1.e) && dVar.A() && a(dVar)) {
            o1.e.b0(dVar, interfaceC2075c, new C2074b());
        }
        o1.c cVarJ = dVar.j(2);
        o1.c cVarJ2 = dVar.j(4);
        int iD = cVarJ.d();
        int iD2 = cVarJ2.d();
        HashSet<o1.c> hashSet = cVarJ.f20250a;
        char c = 0;
        if (hashSet != null && cVarJ.c) {
            for (o1.c cVar5 : hashSet) {
                o1.d dVar2 = cVar5.d;
                int i10 = i7 + 1;
                boolean zA = a(dVar2);
                if (dVar2.A() && zA) {
                    o1.e.b0(dVar2, interfaceC2075c, new C2074b());
                }
                o1.c cVar6 = dVar2.f20265J;
                o1.c cVar7 = dVar2.f20267L;
                char c10 = ((cVar5 == cVar6 && (cVar4 = cVar7.f20253f) != null && cVar4.c) || (cVar5 == cVar7 && (cVar3 = cVar6.f20253f) != null && cVar3.c)) ? (char) 1 : c;
                int i11 = dVar2.f20311q0[c];
                if (i11 != 3 || zA) {
                    if (!dVar2.A()) {
                        if (cVar5 == cVar6 && cVar7.f20253f == null) {
                            int iE = cVar6.e() + iD;
                            dVar2.L(iE, dVar2.r() + iE);
                            c(i10, dVar2, interfaceC2075c, z6);
                        } else if (cVar5 == cVar7 && cVar6.f20253f == null) {
                            int iE2 = iD - cVar7.e();
                            dVar2.L(iE2 - dVar2.r(), iE2);
                            c(i10, dVar2, interfaceC2075c, z6);
                        } else if (c10 != 0 && !dVar2.y()) {
                            d(i10, dVar2, interfaceC2075c, z6);
                        }
                    }
                } else if (i11 == 3 && dVar2.f20317w >= 0 && dVar2.f20316v >= 0 && ((dVar2.f20295h0 == 8 || (dVar2.f20313s == 0 && dVar2.f20279X == 0.0f)) && !dVar2.y() && !dVar2.f20262G && c10 != 0 && !dVar2.y())) {
                    e(i10, dVar, interfaceC2075c, dVar2, z6);
                }
                c = 0;
            }
        }
        if (dVar instanceof o1.h) {
            return;
        }
        HashSet<o1.c> hashSet2 = cVarJ2.f20250a;
        if (hashSet2 != null && cVarJ2.c) {
            for (o1.c cVar8 : hashSet2) {
                o1.d dVar3 = cVar8.d;
                int i12 = i7 + 1;
                boolean zA2 = a(dVar3);
                if (dVar3.A() && zA2) {
                    o1.e.b0(dVar3, interfaceC2075c, new C2074b());
                }
                o1.c cVar9 = dVar3.f20265J;
                o1.c cVar10 = dVar3.f20267L;
                boolean z10 = (cVar8 == cVar9 && (cVar2 = cVar10.f20253f) != null && cVar2.c) || (cVar8 == cVar10 && (cVar = cVar9.f20253f) != null && cVar.c);
                int i13 = dVar3.f20311q0[0];
                if (i13 != 3 || zA2) {
                    if (!dVar3.A()) {
                        if (cVar8 == cVar9 && cVar10.f20253f == null) {
                            int iE3 = cVar9.e() + iD2;
                            dVar3.L(iE3, dVar3.r() + iE3);
                            c(i12, dVar3, interfaceC2075c, z6);
                        } else if (cVar8 == cVar10 && cVar9.f20253f == null) {
                            int iE4 = iD2 - cVar10.e();
                            dVar3.L(iE4 - dVar3.r(), iE4);
                            c(i12, dVar3, interfaceC2075c, z6);
                        } else if (z10 && !dVar3.y()) {
                            d(i12, dVar3, interfaceC2075c, z6);
                        }
                    }
                } else if (i13 == 3 && dVar3.f20317w >= 0 && dVar3.f20316v >= 0) {
                    if (dVar3.f20295h0 != 8) {
                        if (dVar3.f20313s == 0) {
                            if (dVar3.f20279X == 0.0f) {
                            }
                        }
                    }
                    if (!dVar3.y() && !dVar3.f20262G && z10 && !dVar3.y()) {
                        e(i12, dVar, interfaceC2075c, dVar3, z6);
                    }
                }
            }
        }
        dVar.f20305n = true;
    }

    public static void d(int i7, o1.d dVar, InterfaceC2075c interfaceC2075c, boolean z6) {
        float f10 = dVar.f20289e0;
        o1.c cVar = dVar.f20265J;
        int iD = cVar.f20253f.d();
        o1.c cVar2 = dVar.f20267L;
        int iD2 = cVar2.f20253f.d();
        int iE = cVar.e() + iD;
        int iE2 = iD2 - cVar2.e();
        if (iD == iD2) {
            f10 = 0.5f;
        } else {
            iD = iE;
            iD2 = iE2;
        }
        int iR = dVar.r();
        int i10 = (iD2 - iD) - iR;
        if (iD > iD2) {
            i10 = (iD - iD2) - iR;
        }
        int i11 = ((int) (i10 > 0 ? (f10 * i10) + 0.5f : f10 * i10)) + iD;
        int i12 = i11 + iR;
        if (iD > iD2) {
            i12 = i11 - iR;
        }
        dVar.L(i11, i12);
        c(i7 + 1, dVar, interfaceC2075c, z6);
    }

    public static void e(int i7, o1.d dVar, InterfaceC2075c interfaceC2075c, o1.d dVar2, boolean z6) {
        float f10 = dVar2.f20289e0;
        o1.c cVar = dVar2.f20265J;
        int iE = cVar.e() + cVar.f20253f.d();
        o1.c cVar2 = dVar2.f20267L;
        int iD = cVar2.f20253f.d() - cVar2.e();
        if (iD >= iE) {
            int iR = dVar2.r();
            if (dVar2.f20295h0 != 8) {
                int i10 = dVar2.f20313s;
                if (i10 == 2) {
                    iR = (int) (dVar2.f20289e0 * 0.5f * (dVar instanceof o1.e ? dVar.r() : dVar.f20276U.r()));
                } else if (i10 == 0) {
                    iR = iD - iE;
                }
                iR = Math.max(dVar2.f20316v, iR);
                int i11 = dVar2.f20317w;
                if (i11 > 0) {
                    iR = Math.min(i11, iR);
                }
            }
            int i12 = iE + ((int) ((f10 * ((iD - iE) - iR)) + 0.5f));
            dVar2.L(i12, iR + i12);
            c(i7 + 1, dVar2, interfaceC2075c, z6);
        }
    }

    public static void f(int i7, o1.d dVar, InterfaceC2075c interfaceC2075c) {
        float f10 = dVar.f20291f0;
        o1.c cVar = dVar.f20266K;
        int iD = cVar.f20253f.d();
        o1.c cVar2 = dVar.f20268M;
        int iD2 = cVar2.f20253f.d();
        int iE = cVar.e() + iD;
        int iE2 = iD2 - cVar2.e();
        if (iD == iD2) {
            f10 = 0.5f;
        } else {
            iD = iE;
            iD2 = iE2;
        }
        int iL = dVar.l();
        int i10 = (iD2 - iD) - iL;
        if (iD > iD2) {
            i10 = (iD - iD2) - iL;
        }
        int i11 = (int) (i10 > 0 ? (f10 * i10) + 0.5f : f10 * i10);
        int i12 = iD + i11;
        int i13 = i12 + iL;
        if (iD > iD2) {
            i12 = iD - i11;
            i13 = i12 - iL;
        }
        dVar.M(i12, i13);
        i(i7 + 1, dVar, interfaceC2075c);
    }

    public static void g(int i7, o1.d dVar, InterfaceC2075c interfaceC2075c, o1.d dVar2) {
        float f10 = dVar2.f20291f0;
        o1.c cVar = dVar2.f20266K;
        int iE = cVar.e() + cVar.f20253f.d();
        o1.c cVar2 = dVar2.f20268M;
        int iD = cVar2.f20253f.d() - cVar2.e();
        if (iD >= iE) {
            int iL = dVar2.l();
            if (dVar2.f20295h0 != 8) {
                int i10 = dVar2.f20314t;
                if (i10 == 2) {
                    iL = (int) (f10 * 0.5f * (dVar instanceof o1.e ? dVar.l() : dVar.f20276U.l()));
                } else if (i10 == 0) {
                    iL = iD - iE;
                }
                iL = Math.max(dVar2.f20319y, iL);
                int i11 = dVar2.f20320z;
                if (i11 > 0) {
                    iL = Math.min(i11, iL);
                }
            }
            int i12 = iE + ((int) ((f10 * ((iD - iE) - iL)) + 0.5f));
            dVar2.M(i12, iL + i12);
            i(i7 + 1, dVar2, interfaceC2075c);
        }
    }

    public static boolean h(int i7, int i10, int i11, int i12) {
        return (i11 == 1 || i11 == 2 || (i11 == 4 && i7 != 2)) || (i12 == 1 || i12 == 2 || (i12 == 4 && i10 != 2));
    }

    public static void i(int i7, o1.d dVar, InterfaceC2075c interfaceC2075c) {
        o1.c cVar;
        o1.c cVar2;
        o1.c cVar3;
        o1.c cVar4;
        o1.c cVar5;
        if (dVar.o) {
            return;
        }
        if (!(dVar instanceof o1.e) && dVar.A() && a(dVar)) {
            o1.e.b0(dVar, interfaceC2075c, new C2074b());
        }
        o1.c cVarJ = dVar.j(3);
        o1.c cVarJ2 = dVar.j(5);
        int iD = cVarJ.d();
        int iD2 = cVarJ2.d();
        HashSet<o1.c> hashSet = cVarJ.f20250a;
        if (hashSet != null && cVarJ.c) {
            for (o1.c cVar6 : hashSet) {
                o1.d dVar2 = cVar6.d;
                int i10 = i7 + 1;
                boolean zA = a(dVar2);
                if (dVar2.A() && zA) {
                    o1.e.b0(dVar2, interfaceC2075c, new C2074b());
                }
                o1.c cVar7 = dVar2.f20266K;
                o1.c cVar8 = dVar2.f20268M;
                boolean z6 = (cVar6 == cVar7 && (cVar5 = cVar8.f20253f) != null && cVar5.c) || (cVar6 == cVar8 && (cVar4 = cVar7.f20253f) != null && cVar4.c);
                int i11 = dVar2.f20311q0[1];
                if (i11 != 3 || zA) {
                    if (!dVar2.A()) {
                        if (cVar6 == cVar7 && cVar8.f20253f == null) {
                            int iE = cVar7.e() + iD;
                            dVar2.M(iE, dVar2.l() + iE);
                            i(i10, dVar2, interfaceC2075c);
                        } else if (cVar6 == cVar8 && cVar7.f20253f == null) {
                            int iE2 = iD - cVar8.e();
                            dVar2.M(iE2 - dVar2.l(), iE2);
                            i(i10, dVar2, interfaceC2075c);
                        } else if (z6 && !dVar2.z()) {
                            f(i10, dVar2, interfaceC2075c);
                        }
                    }
                } else if (i11 == 3 && dVar2.f20320z >= 0 && dVar2.f20319y >= 0 && (dVar2.f20295h0 == 8 || (dVar2.f20314t == 0 && dVar2.f20279X == 0.0f))) {
                    if (!dVar2.z() && !dVar2.f20262G && z6 && !dVar2.z()) {
                        g(i10, dVar, interfaceC2075c, dVar2);
                    }
                }
            }
        }
        if (dVar instanceof o1.h) {
            return;
        }
        HashSet<o1.c> hashSet2 = cVarJ2.f20250a;
        if (hashSet2 != null && cVarJ2.c) {
            for (o1.c cVar9 : hashSet2) {
                o1.d dVar3 = cVar9.d;
                int i12 = i7 + 1;
                boolean zA2 = a(dVar3);
                if (dVar3.A() && zA2) {
                    o1.e.b0(dVar3, interfaceC2075c, new C2074b());
                }
                o1.c cVar10 = dVar3.f20266K;
                o1.c cVar11 = dVar3.f20268M;
                boolean z10 = (cVar9 == cVar10 && (cVar3 = cVar11.f20253f) != null && cVar3.c) || (cVar9 == cVar11 && (cVar2 = cVar10.f20253f) != null && cVar2.c);
                int i13 = dVar3.f20311q0[1];
                if (i13 != 3 || zA2) {
                    if (!dVar3.A()) {
                        if (cVar9 == cVar10 && cVar11.f20253f == null) {
                            int iE3 = cVar10.e() + iD2;
                            dVar3.M(iE3, dVar3.l() + iE3);
                            i(i12, dVar3, interfaceC2075c);
                        } else if (cVar9 == cVar11 && cVar10.f20253f == null) {
                            int iE4 = iD2 - cVar11.e();
                            dVar3.M(iE4 - dVar3.l(), iE4);
                            i(i12, dVar3, interfaceC2075c);
                        } else if (z10 && !dVar3.z()) {
                            f(i12, dVar3, interfaceC2075c);
                        }
                    }
                } else if (i13 == 3 && dVar3.f20320z >= 0 && dVar3.f20319y >= 0) {
                    if (dVar3.f20295h0 != 8) {
                        if (dVar3.f20314t == 0) {
                            if (dVar3.f20279X == 0.0f) {
                            }
                        }
                    }
                    if (!dVar3.z() && !dVar3.f20262G && z10 && !dVar3.z()) {
                        g(i12, dVar, interfaceC2075c, dVar3);
                    }
                }
            }
        }
        o1.c cVarJ3 = dVar.j(6);
        if (cVarJ3.f20250a != null && cVarJ3.c) {
            int iD3 = cVarJ3.d();
            for (o1.c cVar12 : cVarJ3.f20250a) {
                o1.d dVar4 = cVar12.d;
                int i14 = i7 + 1;
                boolean zA3 = a(dVar4);
                if (dVar4.A() && zA3) {
                    o1.e.b0(dVar4, interfaceC2075c, new C2074b());
                }
                if (dVar4.f20311q0[1] != 3 || zA3) {
                    if (!dVar4.A() && cVar12 == (cVar = dVar4.f20269N)) {
                        int iE5 = cVar12.e() + iD3;
                        if (dVar4.f20261F) {
                            int i15 = iE5 - dVar4.f20285b0;
                            int i16 = dVar4.f20278W + i15;
                            dVar4.f20283a0 = i15;
                            dVar4.f20266K.l(i15);
                            dVar4.f20268M.l(i16);
                            cVar.l(iE5);
                            dVar4.f20303m = true;
                        }
                        i(i14, dVar4, interfaceC2075c);
                    }
                }
            }
        }
        dVar.o = true;
    }
}
