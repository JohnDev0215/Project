package a0;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.ArrayList;

/* JADX INFO: compiled from: BasicMeasure.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList<ConstraintWidget> f2a = new ArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public a f3b = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public z.d f4c;

    /* JADX INFO: compiled from: BasicMeasure.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ConstraintWidget.DimensionBehaviour f5a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ConstraintWidget.DimensionBehaviour f6b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f7c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f8d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f9e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f10f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f11g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f12h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public boolean f13i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public boolean f14j;
    }

    /* JADX INFO: renamed from: a0.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BasicMeasure.java */
    public interface InterfaceC0000b {
        void a(ConstraintWidget constraintWidget, a aVar);

        void b();
    }

    public b(z.d dVar) {
        this.f4c = dVar;
    }

    public final boolean a(InterfaceC0000b interfaceC0000b, ConstraintWidget constraintWidget, boolean z7) {
        this.f3b.f5a = constraintWidget.z();
        this.f3b.f6b = constraintWidget.N();
        this.f3b.f7c = constraintWidget.Q();
        this.f3b.f8d = constraintWidget.w();
        a aVar = this.f3b;
        aVar.f13i = false;
        aVar.f14j = z7;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = aVar.f5a;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        boolean z8 = dimensionBehaviour == dimensionBehaviour2;
        boolean z9 = aVar.f6b == dimensionBehaviour2;
        boolean z10 = z8 && constraintWidget.T > 0.0f;
        boolean z11 = z9 && constraintWidget.T > 0.0f;
        if (z10 && constraintWidget.f952o[0] == 4) {
            aVar.f5a = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        if (z11 && constraintWidget.f952o[1] == 4) {
            aVar.f6b = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        interfaceC0000b.a(constraintWidget, aVar);
        constraintWidget.F0(this.f3b.f9e);
        constraintWidget.i0(this.f3b.f10f);
        constraintWidget.h0(this.f3b.f12h);
        constraintWidget.c0(this.f3b.f11g);
        a aVar2 = this.f3b;
        aVar2.f14j = false;
        return aVar2.f13i;
    }

    public final void b(z.d dVar) {
        int size = dVar.G0.size();
        InterfaceC0000b interfaceC0000bW0 = dVar.W0();
        for (int i8 = 0; i8 < size; i8++) {
            ConstraintWidget constraintWidget = dVar.G0.get(i8);
            if (!(constraintWidget instanceof z.f) && (!constraintWidget.f934f.f994e.f987j || !constraintWidget.f936g.f994e.f987j)) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviourT = constraintWidget.t(0);
                ConstraintWidget.DimensionBehaviour dimensionBehaviourT2 = constraintWidget.t(1);
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (dimensionBehaviourT == dimensionBehaviour && constraintWidget.f948m != 1 && dimensionBehaviourT2 == dimensionBehaviour && constraintWidget.f950n != 1) {
                    continue;
                } else {
                    a(interfaceC0000bW0, constraintWidget, false);
                    if (dVar.L0 != null) {
                        throw null;
                    }
                }
            }
        }
        interfaceC0000bW0.b();
    }

    public final void c(z.d dVar, String str, int i8, int i9) {
        int iF = dVar.F();
        int iE = dVar.E();
        dVar.v0(0);
        dVar.u0(0);
        dVar.F0(i8);
        dVar.i0(i9);
        dVar.v0(iF);
        dVar.u0(iE);
        this.f4c.M0();
    }

    public long d(z.d dVar, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16) {
        boolean zT0;
        int i17;
        boolean z7;
        boolean z8;
        int i18;
        int i19;
        InterfaceC0000b interfaceC0000b;
        int i20;
        boolean z9;
        boolean zV0;
        int i21;
        InterfaceC0000b interfaceC0000bW0 = dVar.W0();
        int size = dVar.G0.size();
        int iQ = dVar.Q();
        int iW = dVar.w();
        boolean zB = z.i.b(i8, 128);
        boolean z10 = zB || z.i.b(i8, 64);
        if (z10) {
            for (int i22 = 0; i22 < size; i22++) {
                ConstraintWidget constraintWidget = dVar.G0.get(i22);
                ConstraintWidget.DimensionBehaviour dimensionBehaviourZ = constraintWidget.z();
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                boolean z11 = (dimensionBehaviourZ == dimensionBehaviour) && (constraintWidget.N() == dimensionBehaviour) && constraintWidget.u() > 0.0f;
                if ((constraintWidget.W() && z11) || ((constraintWidget.Y() && z11) || (constraintWidget instanceof z.j) || constraintWidget.W() || constraintWidget.Y())) {
                    z10 = false;
                    break;
                }
            }
        }
        if (z10 && y.d.f9117b != null) {
            throw null;
        }
        int i23 = 2;
        if (z10 && ((i11 == 1073741824 && i13 == 1073741824) || zB)) {
            int iMin = Math.min(dVar.D(), i12);
            int iMin2 = Math.min(dVar.C(), i14);
            if (i11 == 1073741824 && dVar.Q() != iMin) {
                dVar.F0(iMin);
                dVar.Z0();
            }
            if (i13 == 1073741824 && dVar.w() != iMin2) {
                dVar.i0(iMin2);
                dVar.Z0();
            }
            if (i11 == 1073741824 && i13 == 1073741824) {
                zT0 = dVar.T0(zB);
                i17 = 2;
            } else {
                boolean zU0 = dVar.U0(zB);
                if (i11 == 1073741824) {
                    zV0 = zU0 & dVar.V0(zB, 0);
                    i21 = 1;
                } else {
                    zV0 = zU0;
                    i21 = 0;
                }
                if (i13 == 1073741824) {
                    boolean zV02 = dVar.V0(zB, 1) & zV0;
                    i17 = i21 + 1;
                    zT0 = zV02;
                } else {
                    i17 = i21;
                    zT0 = zV0;
                }
            }
            if (zT0) {
                dVar.J0(i11 == 1073741824, i13 == 1073741824);
            }
        } else {
            zT0 = false;
            i17 = 0;
        }
        if (zT0 && i17 == 2) {
            return 0L;
        }
        if (size > 0) {
            b(dVar);
        }
        int iX0 = dVar.X0();
        int size2 = this.f2a.size();
        if (size > 0) {
            c(dVar, "First pass", iQ, iW);
        }
        if (size2 > 0) {
            ConstraintWidget.DimensionBehaviour dimensionBehaviourZ2 = dVar.z();
            ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            boolean z12 = dimensionBehaviourZ2 == dimensionBehaviour2;
            boolean z13 = dVar.N() == dimensionBehaviour2;
            int iMax = Math.max(dVar.Q(), this.f4c.F());
            int iMax2 = Math.max(dVar.w(), this.f4c.E());
            int i24 = 0;
            boolean zA = false;
            while (i24 < size2) {
                ConstraintWidget constraintWidget2 = this.f2a.get(i24);
                if (constraintWidget2 instanceof z.j) {
                    int iQ2 = constraintWidget2.Q();
                    int iW2 = constraintWidget2.w();
                    i20 = iX0;
                    boolean zA2 = zA | a(interfaceC0000bW0, constraintWidget2, true);
                    if (dVar.L0 != null) {
                        throw null;
                    }
                    int iQ3 = constraintWidget2.Q();
                    int iW3 = constraintWidget2.w();
                    if (iQ3 != iQ2) {
                        constraintWidget2.F0(iQ3);
                        if (z12 && constraintWidget2.J() > iMax) {
                            iMax = Math.max(iMax, constraintWidget2.J() + constraintWidget2.n(ConstraintAnchor.Type.RIGHT).c());
                        }
                        z9 = true;
                    } else {
                        z9 = zA2;
                    }
                    if (iW3 != iW2) {
                        constraintWidget2.i0(iW3);
                        if (z13 && constraintWidget2.q() > iMax2) {
                            iMax2 = Math.max(iMax2, constraintWidget2.q() + constraintWidget2.n(ConstraintAnchor.Type.BOTTOM).c());
                        }
                        z9 = true;
                    }
                    zA = z9 | ((z.j) constraintWidget2).W0();
                } else {
                    i20 = iX0;
                }
                i24++;
                iX0 = i20;
                i23 = 2;
            }
            int i25 = iX0;
            int i26 = i23;
            int i27 = 0;
            while (i27 < i26) {
                int i28 = 0;
                while (i28 < size2) {
                    ConstraintWidget constraintWidget3 = this.f2a.get(i28);
                    if (((constraintWidget3 instanceof z.g) && !(constraintWidget3 instanceof z.j)) || (constraintWidget3 instanceof z.f) || constraintWidget3.P() == 8 || ((constraintWidget3.f934f.f994e.f987j && constraintWidget3.f936g.f994e.f987j) || (constraintWidget3 instanceof z.j))) {
                        i19 = i27;
                        i18 = size2;
                        interfaceC0000b = interfaceC0000bW0;
                    } else {
                        int iQ4 = constraintWidget3.Q();
                        int iW4 = constraintWidget3.w();
                        i18 = size2;
                        int iO = constraintWidget3.o();
                        i19 = i27;
                        zA |= a(interfaceC0000bW0, constraintWidget3, true);
                        if (dVar.L0 != null) {
                            throw null;
                        }
                        int iQ5 = constraintWidget3.Q();
                        interfaceC0000b = interfaceC0000bW0;
                        int iW5 = constraintWidget3.w();
                        if (iQ5 != iQ4) {
                            constraintWidget3.F0(iQ5);
                            if (z12 && constraintWidget3.J() > iMax) {
                                iMax = Math.max(iMax, constraintWidget3.J() + constraintWidget3.n(ConstraintAnchor.Type.RIGHT).c());
                            }
                            zA = true;
                        }
                        if (iW5 != iW4) {
                            constraintWidget3.i0(iW5);
                            if (z13 && constraintWidget3.q() > iMax2) {
                                iMax2 = Math.max(iMax2, constraintWidget3.q() + constraintWidget3.n(ConstraintAnchor.Type.BOTTOM).c());
                            }
                            zA = true;
                        }
                        if (constraintWidget3.T() && iO != constraintWidget3.o()) {
                            zA = true;
                        }
                    }
                    i28++;
                    size2 = i18;
                    interfaceC0000bW0 = interfaceC0000b;
                    i27 = i19;
                }
                int i29 = i27;
                int i30 = size2;
                InterfaceC0000b interfaceC0000b2 = interfaceC0000bW0;
                if (zA) {
                    c(dVar, "intermediate pass", iQ, iW);
                    zA = false;
                }
                interfaceC0000bW0 = interfaceC0000b2;
                i27 = i29 + 1;
                i26 = 2;
                size2 = i30;
            }
            if (zA) {
                c(dVar, "2nd pass", iQ, iW);
                if (dVar.Q() < iMax) {
                    dVar.F0(iMax);
                    z7 = true;
                } else {
                    z7 = false;
                }
                if (dVar.w() < iMax2) {
                    dVar.i0(iMax2);
                    z8 = true;
                } else {
                    z8 = z7;
                }
                if (z8) {
                    c(dVar, "3rd pass", iQ, iW);
                }
            }
            iX0 = i25;
        }
        dVar.i1(iX0);
        return 0L;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0032  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void e(z.d r7) {
        /*
            r6 = this;
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.ConstraintWidget> r0 = r6.f2a
            r0.clear()
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.ConstraintWidget> r0 = r7.G0
            int r0 = r0.size()
            r1 = 0
        Lc:
            if (r1 >= r0) goto L3a
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.ConstraintWidget> r2 = r7.G0
            java.lang.Object r2 = r2.get(r1)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r2 = (androidx.constraintlayout.solver.widgets.ConstraintWidget) r2
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = r2.z()
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r4 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r3 == r4) goto L32
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = r2.z()
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r5 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_PARENT
            if (r3 == r5) goto L32
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = r2.N()
            if (r3 == r4) goto L32
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = r2.N()
            if (r3 != r5) goto L37
        L32:
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.ConstraintWidget> r3 = r6.f2a
            r3.add(r2)
        L37:
            int r1 = r1 + 1
            goto Lc
        L3a:
            r7.Z0()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: a0.b.e(z.d):void");
    }
}
