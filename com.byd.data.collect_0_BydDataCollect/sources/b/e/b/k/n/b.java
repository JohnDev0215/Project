package b.e.b.k.n;

import b.e.b.k.d;
import b.e.b.k.e;
import java.util.ArrayList;

/* JADX INFO: compiled from: BasicMeasure.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ArrayList<b.e.b.k.e> f2465a = new ArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private a f2466b = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private b.e.b.k.f f2467c;

    /* JADX INFO: compiled from: BasicMeasure.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public e.b f2468a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public e.b f2469b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f2470c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f2471d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f2472e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f2473f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f2474g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f2475h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public boolean f2476i;
        public boolean j;
    }

    /* JADX INFO: renamed from: b.e.b.k.n.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BasicMeasure.java */
    public interface InterfaceC0038b {
        void a(b.e.b.k.e eVar, a aVar);

        void b();
    }

    public b(b.e.b.k.f fVar) {
        this.f2467c = fVar;
    }

    private boolean a(InterfaceC0038b interfaceC0038b, b.e.b.k.e eVar, boolean z) {
        this.f2466b.f2468a = eVar.z();
        this.f2466b.f2469b = eVar.N();
        this.f2466b.f2470c = eVar.Q();
        this.f2466b.f2471d = eVar.w();
        a aVar = this.f2466b;
        aVar.f2476i = false;
        aVar.j = z;
        e.b bVar = aVar.f2468a;
        e.b bVar2 = e.b.MATCH_CONSTRAINT;
        boolean z2 = bVar == bVar2;
        boolean z3 = aVar.f2469b == bVar2;
        boolean z4 = z2 && eVar.T > 0.0f;
        boolean z5 = z3 && eVar.T > 0.0f;
        if (z4 && eVar.o[0] == 4) {
            aVar.f2468a = e.b.FIXED;
        }
        if (z5 && eVar.o[1] == 4) {
            aVar.f2469b = e.b.FIXED;
        }
        interfaceC0038b.a(eVar, aVar);
        eVar.F0(this.f2466b.f2472e);
        eVar.i0(this.f2466b.f2473f);
        eVar.h0(this.f2466b.f2475h);
        eVar.c0(this.f2466b.f2474g);
        a aVar2 = this.f2466b;
        aVar2.j = false;
        return aVar2.f2476i;
    }

    private void b(b.e.b.k.f fVar) {
        int size = fVar.G0.size();
        InterfaceC0038b interfaceC0038bW0 = fVar.W0();
        for (int i2 = 0; i2 < size; i2++) {
            b.e.b.k.e eVar = fVar.G0.get(i2);
            if (!(eVar instanceof b.e.b.k.h) && (!eVar.f2443f.f2517e.j || !eVar.f2444g.f2517e.j)) {
                e.b bVarT = eVar.t(0);
                e.b bVarT2 = eVar.t(1);
                e.b bVar = e.b.MATCH_CONSTRAINT;
                if (bVarT == bVar && eVar.m != 1 && bVarT2 == bVar && eVar.n != 1) {
                    continue;
                } else {
                    a(interfaceC0038bW0, eVar, false);
                    if (fVar.L0 != null) {
                        throw null;
                    }
                }
            }
        }
        interfaceC0038bW0.b();
    }

    private void c(b.e.b.k.f fVar, String str, int i2, int i3) {
        int iF = fVar.F();
        int iE = fVar.E();
        fVar.v0(0);
        fVar.u0(0);
        fVar.F0(i2);
        fVar.i0(i3);
        fVar.v0(iF);
        fVar.u0(iE);
        this.f2467c.M0();
    }

    public long d(b.e.b.k.f fVar, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        boolean zT0;
        int i11;
        boolean z;
        boolean z2;
        int i12;
        int i13;
        InterfaceC0038b interfaceC0038b;
        int i14;
        boolean z3;
        boolean zV0;
        int i15;
        InterfaceC0038b interfaceC0038bW0 = fVar.W0();
        int size = fVar.G0.size();
        int iQ = fVar.Q();
        int iW = fVar.w();
        boolean zB = b.e.b.k.k.b(i2, 128);
        boolean z4 = zB || b.e.b.k.k.b(i2, 64);
        if (z4) {
            for (int i16 = 0; i16 < size; i16++) {
                b.e.b.k.e eVar = fVar.G0.get(i16);
                e.b bVarZ = eVar.z();
                e.b bVar = e.b.MATCH_CONSTRAINT;
                boolean z5 = (bVarZ == bVar) && (eVar.N() == bVar) && eVar.u() > 0.0f;
                if ((eVar.W() && z5) || ((eVar.Y() && z5) || (eVar instanceof b.e.b.k.l) || eVar.W() || eVar.Y())) {
                    z4 = false;
                    break;
                }
            }
        }
        if (z4 && b.e.b.d.f2371b != null) {
            throw null;
        }
        int i17 = 2;
        if (z4 && ((i5 == 1073741824 && i7 == 1073741824) || zB)) {
            int iMin = Math.min(fVar.D(), i6);
            int iMin2 = Math.min(fVar.C(), i8);
            if (i5 == 1073741824 && fVar.Q() != iMin) {
                fVar.F0(iMin);
                fVar.Z0();
            }
            if (i7 == 1073741824 && fVar.w() != iMin2) {
                fVar.i0(iMin2);
                fVar.Z0();
            }
            if (i5 == 1073741824 && i7 == 1073741824) {
                zT0 = fVar.T0(zB);
                i11 = 2;
            } else {
                boolean zU0 = fVar.U0(zB);
                if (i5 == 1073741824) {
                    zV0 = zU0 & fVar.V0(zB, 0);
                    i15 = 1;
                } else {
                    zV0 = zU0;
                    i15 = 0;
                }
                if (i7 == 1073741824) {
                    boolean zV02 = fVar.V0(zB, 1) & zV0;
                    i11 = i15 + 1;
                    zT0 = zV02;
                } else {
                    i11 = i15;
                    zT0 = zV0;
                }
            }
            if (zT0) {
                fVar.J0(i5 == 1073741824, i7 == 1073741824);
            }
        } else {
            zT0 = false;
            i11 = 0;
        }
        if (zT0 && i11 == 2) {
            return 0L;
        }
        if (size > 0) {
            b(fVar);
        }
        int iX0 = fVar.X0();
        int size2 = this.f2465a.size();
        if (size > 0) {
            c(fVar, "First pass", iQ, iW);
        }
        if (size2 > 0) {
            e.b bVarZ2 = fVar.z();
            e.b bVar2 = e.b.WRAP_CONTENT;
            boolean z6 = bVarZ2 == bVar2;
            boolean z7 = fVar.N() == bVar2;
            int iMax = Math.max(fVar.Q(), this.f2467c.F());
            int iMax2 = Math.max(fVar.w(), this.f2467c.E());
            int i18 = 0;
            boolean zA = false;
            while (i18 < size2) {
                b.e.b.k.e eVar2 = this.f2465a.get(i18);
                if (eVar2 instanceof b.e.b.k.l) {
                    int iQ2 = eVar2.Q();
                    int iW2 = eVar2.w();
                    i14 = iX0;
                    boolean zA2 = zA | a(interfaceC0038bW0, eVar2, true);
                    if (fVar.L0 != null) {
                        throw null;
                    }
                    int iQ3 = eVar2.Q();
                    int iW3 = eVar2.w();
                    if (iQ3 != iQ2) {
                        eVar2.F0(iQ3);
                        if (z6 && eVar2.J() > iMax) {
                            iMax = Math.max(iMax, eVar2.J() + eVar2.n(d.b.RIGHT).c());
                        }
                        z3 = true;
                    } else {
                        z3 = zA2;
                    }
                    if (iW3 != iW2) {
                        eVar2.i0(iW3);
                        if (z7 && eVar2.q() > iMax2) {
                            iMax2 = Math.max(iMax2, eVar2.q() + eVar2.n(d.b.BOTTOM).c());
                        }
                        z3 = true;
                    }
                    zA = z3 | ((b.e.b.k.l) eVar2).W0();
                } else {
                    i14 = iX0;
                }
                i18++;
                iX0 = i14;
                i17 = 2;
            }
            int i19 = iX0;
            int i20 = i17;
            int i21 = 0;
            while (i21 < i20) {
                int i22 = 0;
                while (i22 < size2) {
                    b.e.b.k.e eVar3 = this.f2465a.get(i22);
                    if (((eVar3 instanceof b.e.b.k.i) && !(eVar3 instanceof b.e.b.k.l)) || (eVar3 instanceof b.e.b.k.h) || eVar3.P() == 8 || ((eVar3.f2443f.f2517e.j && eVar3.f2444g.f2517e.j) || (eVar3 instanceof b.e.b.k.l))) {
                        i13 = i21;
                        i12 = size2;
                        interfaceC0038b = interfaceC0038bW0;
                    } else {
                        int iQ4 = eVar3.Q();
                        int iW4 = eVar3.w();
                        i12 = size2;
                        int iO = eVar3.o();
                        i13 = i21;
                        zA |= a(interfaceC0038bW0, eVar3, true);
                        if (fVar.L0 != null) {
                            throw null;
                        }
                        int iQ5 = eVar3.Q();
                        interfaceC0038b = interfaceC0038bW0;
                        int iW5 = eVar3.w();
                        if (iQ5 != iQ4) {
                            eVar3.F0(iQ5);
                            if (z6 && eVar3.J() > iMax) {
                                iMax = Math.max(iMax, eVar3.J() + eVar3.n(d.b.RIGHT).c());
                            }
                            zA = true;
                        }
                        if (iW5 != iW4) {
                            eVar3.i0(iW5);
                            if (z7 && eVar3.q() > iMax2) {
                                iMax2 = Math.max(iMax2, eVar3.q() + eVar3.n(d.b.BOTTOM).c());
                            }
                            zA = true;
                        }
                        if (eVar3.T() && iO != eVar3.o()) {
                            zA = true;
                        }
                    }
                    i22++;
                    size2 = i12;
                    interfaceC0038bW0 = interfaceC0038b;
                    i21 = i13;
                }
                int i23 = i21;
                int i24 = size2;
                InterfaceC0038b interfaceC0038b2 = interfaceC0038bW0;
                if (zA) {
                    c(fVar, "intermediate pass", iQ, iW);
                    zA = false;
                }
                interfaceC0038bW0 = interfaceC0038b2;
                i21 = i23 + 1;
                i20 = 2;
                size2 = i24;
            }
            if (zA) {
                c(fVar, "2nd pass", iQ, iW);
                if (fVar.Q() < iMax) {
                    fVar.F0(iMax);
                    z = true;
                } else {
                    z = false;
                }
                if (fVar.w() < iMax2) {
                    fVar.i0(iMax2);
                    z2 = true;
                } else {
                    z2 = z;
                }
                if (z2) {
                    c(fVar, "3rd pass", iQ, iW);
                }
            }
            iX0 = i19;
        }
        fVar.i1(iX0);
        return 0L;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0032  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void e(b.e.b.k.f r7) {
        /*
            r6 = this;
            java.util.ArrayList<b.e.b.k.e> r0 = r6.f2465a
            r0.clear()
            java.util.ArrayList<b.e.b.k.e> r0 = r7.G0
            int r0 = r0.size()
            r1 = 0
        Lc:
            if (r1 >= r0) goto L3a
            java.util.ArrayList<b.e.b.k.e> r2 = r7.G0
            java.lang.Object r2 = r2.get(r1)
            b.e.b.k.e r2 = (b.e.b.k.e) r2
            b.e.b.k.e$b r3 = r2.z()
            b.e.b.k.e$b r4 = b.e.b.k.e.b.MATCH_CONSTRAINT
            if (r3 == r4) goto L32
            b.e.b.k.e$b r3 = r2.z()
            b.e.b.k.e$b r5 = b.e.b.k.e.b.MATCH_PARENT
            if (r3 == r5) goto L32
            b.e.b.k.e$b r3 = r2.N()
            if (r3 == r4) goto L32
            b.e.b.k.e$b r3 = r2.N()
            if (r3 != r5) goto L37
        L32:
            java.util.ArrayList<b.e.b.k.e> r3 = r6.f2465a
            r3.add(r2)
        L37:
            int r1 = r1 + 1
            goto Lc
        L3a:
            r7.Z0()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: b.e.b.k.n.b.e(b.e.b.k.f):void");
    }
}
