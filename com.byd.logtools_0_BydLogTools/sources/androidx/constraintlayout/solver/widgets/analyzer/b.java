package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.ArrayList;

/* JADX INFO: compiled from: BasicMeasure.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ArrayList<ConstraintWidget> f996a = new ArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private a f997b = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private androidx.constraintlayout.solver.widgets.d f998c;

    /* JADX INFO: compiled from: BasicMeasure.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ConstraintWidget.DimensionBehaviour f999a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ConstraintWidget.DimensionBehaviour f1000b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1001c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1002d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1003e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1004f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f1005g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f1006h;
        public boolean i;
        public boolean j;
    }

    /* JADX INFO: renamed from: androidx.constraintlayout.solver.widgets.analyzer.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BasicMeasure.java */
    public interface InterfaceC0010b {
        void a(ConstraintWidget constraintWidget, a aVar);

        void b();
    }

    public b(androidx.constraintlayout.solver.widgets.d dVar) {
        this.f998c = dVar;
    }

    private boolean a(InterfaceC0010b interfaceC0010b, ConstraintWidget constraintWidget, boolean z) {
        this.f997b.f999a = constraintWidget.z();
        this.f997b.f1000b = constraintWidget.N();
        this.f997b.f1001c = constraintWidget.Q();
        this.f997b.f1002d = constraintWidget.w();
        a aVar = this.f997b;
        aVar.i = false;
        aVar.j = z;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = aVar.f999a;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        boolean z2 = dimensionBehaviour == dimensionBehaviour2;
        boolean z3 = aVar.f1000b == dimensionBehaviour2;
        boolean z4 = z2 && constraintWidget.T > 0.0f;
        boolean z5 = z3 && constraintWidget.T > 0.0f;
        if (z4 && constraintWidget.o[0] == 4) {
            aVar.f999a = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        if (z5 && constraintWidget.o[1] == 4) {
            aVar.f1000b = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        interfaceC0010b.a(constraintWidget, aVar);
        constraintWidget.F0(this.f997b.f1003e);
        constraintWidget.i0(this.f997b.f1004f);
        constraintWidget.h0(this.f997b.f1006h);
        constraintWidget.c0(this.f997b.f1005g);
        a aVar2 = this.f997b;
        aVar2.j = false;
        return aVar2.i;
    }

    private void b(androidx.constraintlayout.solver.widgets.d dVar) {
        int size = dVar.G0.size();
        InterfaceC0010b interfaceC0010bW0 = dVar.W0();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = dVar.G0.get(i);
            if (!(constraintWidget instanceof androidx.constraintlayout.solver.widgets.f) && (!constraintWidget.f971f.f990e.j || !constraintWidget.f972g.f990e.j)) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviourT = constraintWidget.t(0);
                ConstraintWidget.DimensionBehaviour dimensionBehaviourT2 = constraintWidget.t(1);
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (dimensionBehaviourT == dimensionBehaviour && constraintWidget.m != 1 && dimensionBehaviourT2 == dimensionBehaviour && constraintWidget.n != 1) {
                    continue;
                } else {
                    a(interfaceC0010bW0, constraintWidget, false);
                    if (dVar.L0 != null) {
                        throw null;
                    }
                }
            }
        }
        interfaceC0010bW0.b();
    }

    private void c(androidx.constraintlayout.solver.widgets.d dVar, String str, int i, int i2) {
        int iF = dVar.F();
        int iE = dVar.E();
        dVar.v0(0);
        dVar.u0(0);
        dVar.F0(i);
        dVar.i0(i2);
        dVar.v0(iF);
        dVar.u0(iE);
        this.f998c.M0();
    }

    public long d(androidx.constraintlayout.solver.widgets.d dVar, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        boolean zT0;
        int i10;
        boolean z;
        boolean z2;
        int i11;
        int i12;
        InterfaceC0010b interfaceC0010b;
        int i13;
        boolean z3;
        boolean zV0;
        int i14;
        InterfaceC0010b interfaceC0010bW0 = dVar.W0();
        int size = dVar.G0.size();
        int iQ = dVar.Q();
        int iW = dVar.w();
        boolean zB = androidx.constraintlayout.solver.widgets.i.b(i, 128);
        boolean z4 = zB || androidx.constraintlayout.solver.widgets.i.b(i, 64);
        if (z4) {
            for (int i15 = 0; i15 < size; i15++) {
                ConstraintWidget constraintWidget = dVar.G0.get(i15);
                ConstraintWidget.DimensionBehaviour dimensionBehaviourZ = constraintWidget.z();
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                boolean z5 = (dimensionBehaviourZ == dimensionBehaviour) && (constraintWidget.N() == dimensionBehaviour) && constraintWidget.u() > 0.0f;
                if ((constraintWidget.W() && z5) || ((constraintWidget.Y() && z5) || (constraintWidget instanceof androidx.constraintlayout.solver.widgets.j) || constraintWidget.W() || constraintWidget.Y())) {
                    z4 = false;
                    break;
                }
            }
        }
        if (z4 && androidx.constraintlayout.solver.d.f933b != null) {
            throw null;
        }
        int i16 = 2;
        if (z4 && ((i4 == 1073741824 && i6 == 1073741824) || zB)) {
            int iMin = Math.min(dVar.D(), i5);
            int iMin2 = Math.min(dVar.C(), i7);
            if (i4 == 1073741824 && dVar.Q() != iMin) {
                dVar.F0(iMin);
                dVar.Z0();
            }
            if (i6 == 1073741824 && dVar.w() != iMin2) {
                dVar.i0(iMin2);
                dVar.Z0();
            }
            if (i4 == 1073741824 && i6 == 1073741824) {
                zT0 = dVar.T0(zB);
                i10 = 2;
            } else {
                boolean zU0 = dVar.U0(zB);
                if (i4 == 1073741824) {
                    zV0 = zU0 & dVar.V0(zB, 0);
                    i14 = 1;
                } else {
                    zV0 = zU0;
                    i14 = 0;
                }
                if (i6 == 1073741824) {
                    boolean zV02 = dVar.V0(zB, 1) & zV0;
                    i10 = i14 + 1;
                    zT0 = zV02;
                } else {
                    i10 = i14;
                    zT0 = zV0;
                }
            }
            if (zT0) {
                dVar.J0(i4 == 1073741824, i6 == 1073741824);
            }
        } else {
            zT0 = false;
            i10 = 0;
        }
        if (zT0 && i10 == 2) {
            return 0L;
        }
        if (size > 0) {
            b(dVar);
        }
        int iX0 = dVar.X0();
        int size2 = this.f996a.size();
        if (size > 0) {
            c(dVar, "First pass", iQ, iW);
        }
        if (size2 > 0) {
            ConstraintWidget.DimensionBehaviour dimensionBehaviourZ2 = dVar.z();
            ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            boolean z6 = dimensionBehaviourZ2 == dimensionBehaviour2;
            boolean z7 = dVar.N() == dimensionBehaviour2;
            int iMax = Math.max(dVar.Q(), this.f998c.F());
            int iMax2 = Math.max(dVar.w(), this.f998c.E());
            int i17 = 0;
            boolean zA = false;
            while (i17 < size2) {
                ConstraintWidget constraintWidget2 = this.f996a.get(i17);
                if (constraintWidget2 instanceof androidx.constraintlayout.solver.widgets.j) {
                    int iQ2 = constraintWidget2.Q();
                    int iW2 = constraintWidget2.w();
                    i13 = iX0;
                    boolean zA2 = zA | a(interfaceC0010bW0, constraintWidget2, true);
                    if (dVar.L0 != null) {
                        throw null;
                    }
                    int iQ3 = constraintWidget2.Q();
                    int iW3 = constraintWidget2.w();
                    if (iQ3 != iQ2) {
                        constraintWidget2.F0(iQ3);
                        if (z6 && constraintWidget2.J() > iMax) {
                            iMax = Math.max(iMax, constraintWidget2.J() + constraintWidget2.n(ConstraintAnchor.Type.RIGHT).c());
                        }
                        z3 = true;
                    } else {
                        z3 = zA2;
                    }
                    if (iW3 != iW2) {
                        constraintWidget2.i0(iW3);
                        if (z7 && constraintWidget2.q() > iMax2) {
                            iMax2 = Math.max(iMax2, constraintWidget2.q() + constraintWidget2.n(ConstraintAnchor.Type.BOTTOM).c());
                        }
                        z3 = true;
                    }
                    zA = z3 | ((androidx.constraintlayout.solver.widgets.j) constraintWidget2).W0();
                } else {
                    i13 = iX0;
                }
                i17++;
                iX0 = i13;
                i16 = 2;
            }
            int i18 = iX0;
            int i19 = i16;
            int i20 = 0;
            while (i20 < i19) {
                int i21 = 0;
                while (i21 < size2) {
                    ConstraintWidget constraintWidget3 = this.f996a.get(i21);
                    if (((constraintWidget3 instanceof androidx.constraintlayout.solver.widgets.g) && !(constraintWidget3 instanceof androidx.constraintlayout.solver.widgets.j)) || (constraintWidget3 instanceof androidx.constraintlayout.solver.widgets.f) || constraintWidget3.P() == 8 || ((constraintWidget3.f971f.f990e.j && constraintWidget3.f972g.f990e.j) || (constraintWidget3 instanceof androidx.constraintlayout.solver.widgets.j))) {
                        i12 = i20;
                        i11 = size2;
                        interfaceC0010b = interfaceC0010bW0;
                    } else {
                        int iQ4 = constraintWidget3.Q();
                        int iW4 = constraintWidget3.w();
                        i11 = size2;
                        int iO = constraintWidget3.o();
                        i12 = i20;
                        zA |= a(interfaceC0010bW0, constraintWidget3, true);
                        if (dVar.L0 != null) {
                            throw null;
                        }
                        int iQ5 = constraintWidget3.Q();
                        interfaceC0010b = interfaceC0010bW0;
                        int iW5 = constraintWidget3.w();
                        if (iQ5 != iQ4) {
                            constraintWidget3.F0(iQ5);
                            if (z6 && constraintWidget3.J() > iMax) {
                                iMax = Math.max(iMax, constraintWidget3.J() + constraintWidget3.n(ConstraintAnchor.Type.RIGHT).c());
                            }
                            zA = true;
                        }
                        if (iW5 != iW4) {
                            constraintWidget3.i0(iW5);
                            if (z7 && constraintWidget3.q() > iMax2) {
                                iMax2 = Math.max(iMax2, constraintWidget3.q() + constraintWidget3.n(ConstraintAnchor.Type.BOTTOM).c());
                            }
                            zA = true;
                        }
                        if (constraintWidget3.T() && iO != constraintWidget3.o()) {
                            zA = true;
                        }
                    }
                    i21++;
                    size2 = i11;
                    interfaceC0010bW0 = interfaceC0010b;
                    i20 = i12;
                }
                int i22 = i20;
                int i23 = size2;
                InterfaceC0010b interfaceC0010b2 = interfaceC0010bW0;
                if (zA) {
                    c(dVar, "intermediate pass", iQ, iW);
                    zA = false;
                }
                interfaceC0010bW0 = interfaceC0010b2;
                i20 = i22 + 1;
                i19 = 2;
                size2 = i23;
            }
            if (zA) {
                c(dVar, "2nd pass", iQ, iW);
                if (dVar.Q() < iMax) {
                    dVar.F0(iMax);
                    z = true;
                } else {
                    z = false;
                }
                if (dVar.w() < iMax2) {
                    dVar.i0(iMax2);
                    z2 = true;
                } else {
                    z2 = z;
                }
                if (z2) {
                    c(dVar, "3rd pass", iQ, iW);
                }
            }
            iX0 = i18;
        }
        dVar.i1(iX0);
        return 0L;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0032  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void e(androidx.constraintlayout.solver.widgets.d r7) {
        /*
            r6 = this;
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.ConstraintWidget> r0 = r6.f996a
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
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.ConstraintWidget> r3 = r6.f996a
            r3.add(r2)
        L37:
            int r1 = r1 + 1
            goto Lc
        L3a:
            r7.Z0()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.analyzer.b.e(androidx.constraintlayout.solver.widgets.d):void");
    }
}
