package a.e.b.k.m;

import a.e.b.k.d;
import a.e.b.k.e;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ArrayList<a.e.b.k.e> f223a = new ArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private a f224b = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private a.e.b.k.f f225c;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public e.b f226a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public e.b f227b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f228c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f229d;
        public int e;
        public int f;
        public int g;
        public boolean h;
        public boolean i;
        public boolean j;
    }

    /* JADX INFO: renamed from: a.e.b.k.m.b$b, reason: collision with other inner class name */
    public interface InterfaceC0006b {
        void a(a.e.b.k.e eVar, a aVar);

        void b();
    }

    public b(a.e.b.k.f fVar) {
        this.f225c = fVar;
    }

    private boolean a(InterfaceC0006b interfaceC0006b, a.e.b.k.e eVar, boolean z) {
        this.f224b.f226a = eVar.w();
        this.f224b.f227b = eVar.K();
        this.f224b.f228c = eVar.N();
        this.f224b.f229d = eVar.t();
        a aVar = this.f224b;
        aVar.i = false;
        aVar.j = z;
        e.b bVar = aVar.f226a;
        e.b bVar2 = e.b.MATCH_CONSTRAINT;
        boolean z2 = bVar == bVar2;
        boolean z3 = aVar.f227b == bVar2;
        boolean z4 = z2 && eVar.R > 0.0f;
        boolean z5 = z3 && eVar.R > 0.0f;
        if (z4 && eVar.o[0] == 4) {
            aVar.f226a = e.b.FIXED;
        }
        if (z5 && eVar.o[1] == 4) {
            aVar.f227b = e.b.FIXED;
        }
        interfaceC0006b.a(eVar, aVar);
        eVar.B0(this.f224b.e);
        eVar.e0(this.f224b.f);
        eVar.d0(this.f224b.h);
        eVar.Y(this.f224b.g);
        a aVar2 = this.f224b;
        aVar2.j = false;
        return aVar2.i;
    }

    private void b(a.e.b.k.f fVar) {
        int size = fVar.w0.size();
        InterfaceC0006b interfaceC0006bR0 = fVar.R0();
        for (int i = 0; i < size; i++) {
            a.e.b.k.e eVar = fVar.w0.get(i);
            if (!(eVar instanceof a.e.b.k.g) && (!eVar.f.e.j || !eVar.g.e.j)) {
                e.b bVarQ = eVar.q(0);
                e.b bVarQ2 = eVar.q(1);
                e.b bVar = e.b.MATCH_CONSTRAINT;
                if (bVarQ == bVar && eVar.m != 1 && bVarQ2 == bVar && eVar.n != 1) {
                    continue;
                } else {
                    a(interfaceC0006bR0, eVar, false);
                    if (fVar.B0 != null) {
                        throw null;
                    }
                }
            }
        }
        interfaceC0006bR0.b();
    }

    private void c(a.e.b.k.f fVar, String str, int i, int i2) {
        int iC = fVar.C();
        int iB = fVar.B();
        fVar.r0(0);
        fVar.q0(0);
        fVar.B0(i);
        fVar.e0(i2);
        fVar.r0(iC);
        fVar.q0(iB);
        this.f225c.H0();
    }

    public long d(a.e.b.k.f fVar, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        boolean zO0;
        int i10;
        boolean z;
        boolean z2;
        int i11;
        int i12;
        InterfaceC0006b interfaceC0006b;
        int i13;
        boolean z3;
        boolean zQ0;
        int i14;
        InterfaceC0006b interfaceC0006bR0 = fVar.R0();
        int size = fVar.w0.size();
        int iN = fVar.N();
        int iT = fVar.t();
        boolean zB = a.e.b.k.j.b(i, 128);
        boolean z4 = zB || a.e.b.k.j.b(i, 64);
        if (z4) {
            for (int i15 = 0; i15 < size; i15++) {
                a.e.b.k.e eVar = fVar.w0.get(i15);
                e.b bVarW = eVar.w();
                e.b bVar = e.b.MATCH_CONSTRAINT;
                boolean z5 = (bVarW == bVar) && (eVar.K() == bVar) && eVar.r() > 0.0f;
                if ((eVar.T() && z5) || ((eVar.V() && z5) || (eVar instanceof a.e.b.k.k) || eVar.T() || eVar.V())) {
                    z4 = false;
                    break;
                }
            }
        }
        if (z4 && a.e.b.d.f180b != null) {
            throw null;
        }
        int i16 = 2;
        if (z4 && ((i4 == 1073741824 && i6 == 1073741824) || zB)) {
            int iMin = Math.min(fVar.A(), i5);
            int iMin2 = Math.min(fVar.z(), i7);
            if (i4 == 1073741824 && fVar.N() != iMin) {
                fVar.B0(iMin);
                fVar.T0();
            }
            if (i6 == 1073741824 && fVar.t() != iMin2) {
                fVar.e0(iMin2);
                fVar.T0();
            }
            if (i4 == 1073741824 && i6 == 1073741824) {
                zO0 = fVar.O0(zB);
                i10 = 2;
            } else {
                boolean zP0 = fVar.P0(zB);
                if (i4 == 1073741824) {
                    zQ0 = zP0 & fVar.Q0(zB, 0);
                    i14 = 1;
                } else {
                    zQ0 = zP0;
                    i14 = 0;
                }
                if (i6 == 1073741824) {
                    boolean zQ02 = fVar.Q0(zB, 1) & zQ0;
                    i10 = i14 + 1;
                    zO0 = zQ02;
                } else {
                    i10 = i14;
                    zO0 = zQ0;
                }
            }
            if (zO0) {
                fVar.F0(i4 == 1073741824, i6 == 1073741824);
            }
        } else {
            zO0 = false;
            i10 = 0;
        }
        if (zO0 && i10 == 2) {
            return 0L;
        }
        if (size > 0) {
            b(fVar);
        }
        int iS0 = fVar.S0();
        int size2 = this.f223a.size();
        if (size > 0) {
            c(fVar, "First pass", iN, iT);
        }
        if (size2 > 0) {
            e.b bVarW2 = fVar.w();
            e.b bVar2 = e.b.WRAP_CONTENT;
            boolean z6 = bVarW2 == bVar2;
            boolean z7 = fVar.K() == bVar2;
            int iMax = Math.max(fVar.N(), this.f225c.C());
            int iMax2 = Math.max(fVar.t(), this.f225c.B());
            int i17 = 0;
            boolean zA = false;
            while (i17 < size2) {
                a.e.b.k.e eVar2 = this.f223a.get(i17);
                if (eVar2 instanceof a.e.b.k.k) {
                    int iN2 = eVar2.N();
                    int iT2 = eVar2.t();
                    i13 = iS0;
                    boolean zA2 = zA | a(interfaceC0006bR0, eVar2, true);
                    if (fVar.B0 != null) {
                        throw null;
                    }
                    int iN3 = eVar2.N();
                    int iT3 = eVar2.t();
                    if (iN3 != iN2) {
                        eVar2.B0(iN3);
                        if (z6 && eVar2.G() > iMax) {
                            iMax = Math.max(iMax, eVar2.G() + eVar2.k(d.b.RIGHT).b());
                        }
                        z3 = true;
                    } else {
                        z3 = zA2;
                    }
                    if (iT3 != iT2) {
                        eVar2.e0(iT3);
                        if (z7 && eVar2.n() > iMax2) {
                            iMax2 = Math.max(iMax2, eVar2.n() + eVar2.k(d.b.BOTTOM).b());
                        }
                        z3 = true;
                    }
                    zA = z3 | ((a.e.b.k.k) eVar2).I0();
                } else {
                    i13 = iS0;
                }
                i17++;
                iS0 = i13;
                i16 = 2;
            }
            int i18 = iS0;
            int i19 = i16;
            int i20 = 0;
            while (i20 < i19) {
                int i21 = 0;
                while (i21 < size2) {
                    a.e.b.k.e eVar3 = this.f223a.get(i21);
                    if (((eVar3 instanceof a.e.b.k.h) && !(eVar3 instanceof a.e.b.k.k)) || (eVar3 instanceof a.e.b.k.g) || eVar3.M() == 8 || ((eVar3.f.e.j && eVar3.g.e.j) || (eVar3 instanceof a.e.b.k.k))) {
                        i12 = i20;
                        i11 = size2;
                        interfaceC0006b = interfaceC0006bR0;
                    } else {
                        int iN4 = eVar3.N();
                        int iT4 = eVar3.t();
                        i11 = size2;
                        int iL = eVar3.l();
                        i12 = i20;
                        zA |= a(interfaceC0006bR0, eVar3, true);
                        if (fVar.B0 != null) {
                            throw null;
                        }
                        int iN5 = eVar3.N();
                        interfaceC0006b = interfaceC0006bR0;
                        int iT5 = eVar3.t();
                        if (iN5 != iN4) {
                            eVar3.B0(iN5);
                            if (z6 && eVar3.G() > iMax) {
                                iMax = Math.max(iMax, eVar3.G() + eVar3.k(d.b.RIGHT).b());
                            }
                            zA = true;
                        }
                        if (iT5 != iT4) {
                            eVar3.e0(iT5);
                            if (z7 && eVar3.n() > iMax2) {
                                iMax2 = Math.max(iMax2, eVar3.n() + eVar3.k(d.b.BOTTOM).b());
                            }
                            zA = true;
                        }
                        if (eVar3.Q() && iL != eVar3.l()) {
                            zA = true;
                        }
                    }
                    i21++;
                    size2 = i11;
                    interfaceC0006bR0 = interfaceC0006b;
                    i20 = i12;
                }
                int i22 = i20;
                int i23 = size2;
                InterfaceC0006b interfaceC0006b2 = interfaceC0006bR0;
                if (zA) {
                    c(fVar, "intermediate pass", iN, iT);
                    zA = false;
                }
                interfaceC0006bR0 = interfaceC0006b2;
                i20 = i22 + 1;
                i19 = 2;
                size2 = i23;
            }
            if (zA) {
                c(fVar, "2nd pass", iN, iT);
                if (fVar.N() < iMax) {
                    fVar.B0(iMax);
                    z = true;
                } else {
                    z = false;
                }
                if (fVar.t() < iMax2) {
                    fVar.e0(iMax2);
                    z2 = true;
                } else {
                    z2 = z;
                }
                if (z2) {
                    c(fVar, "3rd pass", iN, iT);
                }
            }
            iS0 = i18;
        }
        fVar.c1(iS0);
        return 0L;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0032  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void e(a.e.b.k.f r7) {
        /*
            r6 = this;
            java.util.ArrayList<a.e.b.k.e> r0 = r6.f223a
            r0.clear()
            java.util.ArrayList<a.e.b.k.e> r0 = r7.w0
            int r0 = r0.size()
            r1 = 0
        Lc:
            if (r1 >= r0) goto L3a
            java.util.ArrayList<a.e.b.k.e> r2 = r7.w0
            java.lang.Object r2 = r2.get(r1)
            a.e.b.k.e r2 = (a.e.b.k.e) r2
            a.e.b.k.e$b r3 = r2.w()
            a.e.b.k.e$b r4 = a.e.b.k.e.b.MATCH_CONSTRAINT
            if (r3 == r4) goto L32
            a.e.b.k.e$b r3 = r2.w()
            a.e.b.k.e$b r5 = a.e.b.k.e.b.MATCH_PARENT
            if (r3 == r5) goto L32
            a.e.b.k.e$b r3 = r2.K()
            if (r3 == r4) goto L32
            a.e.b.k.e$b r3 = r2.K()
            if (r3 != r5) goto L37
        L32:
            java.util.ArrayList<a.e.b.k.e> r3 = r6.f223a
            r3.add(r2)
        L37:
            int r1 = r1 + 1
            goto Lc
        L3a:
            r7.T0()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: a.e.b.k.m.b.e(a.e.b.k.f):void");
    }
}
