package b.f.b.j.l;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.ArrayList;

/* JADX INFO: compiled from: BasicMeasure.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList<ConstraintWidget> f2234a = new ArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public a f2235b = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b.f.b.j.d f2236c;

    /* JADX INFO: compiled from: BasicMeasure.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ConstraintWidget.DimensionBehaviour f2237a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ConstraintWidget.DimensionBehaviour f2238b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f2239c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f2240d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f2241e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f2242f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f2243g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f2244h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public boolean f2245i;
        public boolean j;
    }

    /* JADX INFO: renamed from: b.f.b.j.l.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BasicMeasure.java */
    public interface InterfaceC0027b {
        void a(ConstraintWidget constraintWidget, a aVar);

        void b();
    }

    public b(b.f.b.j.d dVar) {
        this.f2236c = dVar;
    }

    public final boolean a(InterfaceC0027b interfaceC0027b, ConstraintWidget constraintWidget, boolean z) {
        this.f2235b.f2237a = constraintWidget.z();
        this.f2235b.f2238b = constraintWidget.N();
        this.f2235b.f2239c = constraintWidget.Q();
        this.f2235b.f2240d = constraintWidget.w();
        a aVar = this.f2235b;
        aVar.f2245i = false;
        aVar.j = z;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = aVar.f2237a;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        boolean z2 = dimensionBehaviour == dimensionBehaviour2;
        boolean z3 = aVar.f2238b == dimensionBehaviour2;
        boolean z4 = z2 && constraintWidget.T > 0.0f;
        boolean z5 = z3 && constraintWidget.T > 0.0f;
        if (z4 && constraintWidget.o[0] == 4) {
            aVar.f2237a = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        if (z5 && constraintWidget.o[1] == 4) {
            aVar.f2238b = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        interfaceC0027b.a(constraintWidget, aVar);
        constraintWidget.F0(this.f2235b.f2241e);
        constraintWidget.i0(this.f2235b.f2242f);
        constraintWidget.h0(this.f2235b.f2244h);
        constraintWidget.c0(this.f2235b.f2243g);
        a aVar2 = this.f2235b;
        aVar2.j = false;
        return aVar2.f2245i;
    }

    public final void b(b.f.b.j.d dVar) {
        int size = dVar.G0.size();
        InterfaceC0027b interfaceC0027bW0 = dVar.W0();
        for (int i2 = 0; i2 < size; i2++) {
            ConstraintWidget constraintWidget = dVar.G0.get(i2);
            if (!(constraintWidget instanceof b.f.b.j.f) && (!constraintWidget.f632f.f653e.j || !constraintWidget.f633g.f653e.j)) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviourT = constraintWidget.t(0);
                ConstraintWidget.DimensionBehaviour dimensionBehaviourT2 = constraintWidget.t(1);
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (dimensionBehaviourT == dimensionBehaviour && constraintWidget.m != 1 && dimensionBehaviourT2 == dimensionBehaviour && constraintWidget.n != 1) {
                    continue;
                } else {
                    a(interfaceC0027bW0, constraintWidget, false);
                    if (dVar.L0 != null) {
                        throw null;
                    }
                }
            }
        }
        interfaceC0027bW0.b();
    }

    public final void c(b.f.b.j.d dVar, String str, int i2, int i3) {
        int iF = dVar.F();
        int iE = dVar.E();
        dVar.v0(0);
        dVar.u0(0);
        dVar.F0(i2);
        dVar.i0(i3);
        dVar.v0(iF);
        dVar.u0(iE);
        this.f2236c.M0();
    }

    public long d(b.f.b.j.d dVar, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        boolean zT0;
        int i11;
        boolean z;
        boolean z2;
        int i12;
        int i13;
        InterfaceC0027b interfaceC0027b;
        int i14;
        boolean z3;
        boolean zV0;
        int i15;
        InterfaceC0027b interfaceC0027bW0 = dVar.W0();
        int size = dVar.G0.size();
        int iQ = dVar.Q();
        int iW = dVar.w();
        boolean zB = b.f.b.j.i.b(i2, 128);
        boolean z4 = zB || b.f.b.j.i.b(i2, 64);
        if (z4) {
            for (int i16 = 0; i16 < size; i16++) {
                ConstraintWidget constraintWidget = dVar.G0.get(i16);
                ConstraintWidget.DimensionBehaviour dimensionBehaviourZ = constraintWidget.z();
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                boolean z5 = (dimensionBehaviourZ == dimensionBehaviour) && (constraintWidget.N() == dimensionBehaviour) && constraintWidget.u() > 0.0f;
                if ((constraintWidget.W() && z5) || ((constraintWidget.Y() && z5) || (constraintWidget instanceof b.f.b.j.j) || constraintWidget.W() || constraintWidget.Y())) {
                    z4 = false;
                    break;
                }
            }
        }
        if (z4 && b.f.b.d.f2187b != null) {
            throw null;
        }
        int i17 = 2;
        if (z4 && ((i5 == 1073741824 && i7 == 1073741824) || zB)) {
            int iMin = Math.min(dVar.D(), i6);
            int iMin2 = Math.min(dVar.C(), i8);
            if (i5 == 1073741824 && dVar.Q() != iMin) {
                dVar.F0(iMin);
                dVar.Z0();
            }
            if (i7 == 1073741824 && dVar.w() != iMin2) {
                dVar.i0(iMin2);
                dVar.Z0();
            }
            if (i5 == 1073741824 && i7 == 1073741824) {
                zT0 = dVar.T0(zB);
                i11 = 2;
            } else {
                boolean zU0 = dVar.U0(zB);
                if (i5 == 1073741824) {
                    zV0 = zU0 & dVar.V0(zB, 0);
                    i15 = 1;
                } else {
                    zV0 = zU0;
                    i15 = 0;
                }
                if (i7 == 1073741824) {
                    boolean zV02 = dVar.V0(zB, 1) & zV0;
                    i11 = i15 + 1;
                    zT0 = zV02;
                } else {
                    i11 = i15;
                    zT0 = zV0;
                }
            }
            if (zT0) {
                dVar.J0(i5 == 1073741824, i7 == 1073741824);
            }
        } else {
            zT0 = false;
            i11 = 0;
        }
        if (zT0 && i11 == 2) {
            return 0L;
        }
        if (size > 0) {
            b(dVar);
        }
        int iX0 = dVar.X0();
        int size2 = this.f2234a.size();
        if (size > 0) {
            c(dVar, "First pass", iQ, iW);
        }
        if (size2 > 0) {
            ConstraintWidget.DimensionBehaviour dimensionBehaviourZ2 = dVar.z();
            ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            boolean z6 = dimensionBehaviourZ2 == dimensionBehaviour2;
            boolean z7 = dVar.N() == dimensionBehaviour2;
            int iMax = Math.max(dVar.Q(), this.f2236c.F());
            int iMax2 = Math.max(dVar.w(), this.f2236c.E());
            int i18 = 0;
            boolean zA = false;
            while (i18 < size2) {
                ConstraintWidget constraintWidget2 = this.f2234a.get(i18);
                if (constraintWidget2 instanceof b.f.b.j.j) {
                    int iQ2 = constraintWidget2.Q();
                    int iW2 = constraintWidget2.w();
                    i14 = iX0;
                    boolean zA2 = zA | a(interfaceC0027bW0, constraintWidget2, true);
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
                    zA = z3 | ((b.f.b.j.j) constraintWidget2).W0();
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
                    ConstraintWidget constraintWidget3 = this.f2234a.get(i22);
                    if (((constraintWidget3 instanceof b.f.b.j.g) && !(constraintWidget3 instanceof b.f.b.j.j)) || (constraintWidget3 instanceof b.f.b.j.f) || constraintWidget3.P() == 8 || ((constraintWidget3.f632f.f653e.j && constraintWidget3.f633g.f653e.j) || (constraintWidget3 instanceof b.f.b.j.j))) {
                        i13 = i21;
                        i12 = size2;
                        interfaceC0027b = interfaceC0027bW0;
                    } else {
                        int iQ4 = constraintWidget3.Q();
                        int iW4 = constraintWidget3.w();
                        i12 = size2;
                        int iO = constraintWidget3.o();
                        i13 = i21;
                        zA |= a(interfaceC0027bW0, constraintWidget3, true);
                        if (dVar.L0 != null) {
                            throw null;
                        }
                        int iQ5 = constraintWidget3.Q();
                        interfaceC0027b = interfaceC0027bW0;
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
                    i22++;
                    size2 = i12;
                    interfaceC0027bW0 = interfaceC0027b;
                    i21 = i13;
                }
                int i23 = i21;
                int i24 = size2;
                InterfaceC0027b interfaceC0027b2 = interfaceC0027bW0;
                if (zA) {
                    c(dVar, "intermediate pass", iQ, iW);
                    zA = false;
                }
                interfaceC0027bW0 = interfaceC0027b2;
                i21 = i23 + 1;
                i20 = 2;
                size2 = i24;
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
            iX0 = i19;
        }
        dVar.i1(iX0);
        return 0L;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0032  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void e(b.f.b.j.d r7) {
        /*
            r6 = this;
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.ConstraintWidget> r0 = r6.f2234a
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
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.ConstraintWidget> r3 = r6.f2234a
            r3.add(r2)
        L37:
            int r1 = r1 + 1
            goto Lc
        L3a:
            r7.Z0()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: b.f.b.j.l.b.e(b.f.b.j.d):void");
    }
}
