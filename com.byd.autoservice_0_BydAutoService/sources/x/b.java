package x;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.ArrayList;

/* JADX INFO: compiled from: BasicMeasure.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList<ConstraintWidget> f5681a = new ArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public a f5682b = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public w.d f5683c;

    /* JADX INFO: compiled from: BasicMeasure.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ConstraintWidget.DimensionBehaviour f5684a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ConstraintWidget.DimensionBehaviour f5685b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f5686c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f5687d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f5688e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f5689f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f5690g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f5691h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public boolean f5692i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public boolean f5693j;
    }

    /* JADX INFO: renamed from: x.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BasicMeasure.java */
    public interface InterfaceC0072b {
        void a(ConstraintWidget constraintWidget, a aVar);

        void b();
    }

    public b(w.d dVar) {
        this.f5683c = dVar;
    }

    public final boolean a(InterfaceC0072b interfaceC0072b, ConstraintWidget constraintWidget, boolean z3) {
        this.f5682b.f5684a = constraintWidget.z();
        this.f5682b.f5685b = constraintWidget.N();
        this.f5682b.f5686c = constraintWidget.Q();
        this.f5682b.f5687d = constraintWidget.w();
        a aVar = this.f5682b;
        aVar.f5692i = false;
        aVar.f5693j = z3;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = aVar.f5684a;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        boolean z4 = dimensionBehaviour == dimensionBehaviour2;
        boolean z5 = aVar.f5685b == dimensionBehaviour2;
        boolean z6 = z4 && constraintWidget.T > 0.0f;
        boolean z7 = z5 && constraintWidget.T > 0.0f;
        if (z6 && constraintWidget.f861o[0] == 4) {
            aVar.f5684a = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        if (z7 && constraintWidget.f861o[1] == 4) {
            aVar.f5685b = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        interfaceC0072b.a(constraintWidget, aVar);
        constraintWidget.F0(this.f5682b.f5688e);
        constraintWidget.i0(this.f5682b.f5689f);
        constraintWidget.h0(this.f5682b.f5691h);
        constraintWidget.c0(this.f5682b.f5690g);
        a aVar2 = this.f5682b;
        aVar2.f5693j = false;
        return aVar2.f5692i;
    }

    public final void b(w.d dVar) {
        int size = dVar.G0.size();
        InterfaceC0072b interfaceC0072bW0 = dVar.W0();
        for (int i3 = 0; i3 < size; i3++) {
            ConstraintWidget constraintWidget = dVar.G0.get(i3);
            if (!(constraintWidget instanceof w.f) && (!constraintWidget.f843f.f903e.f896j || !constraintWidget.f845g.f903e.f896j)) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviourT = constraintWidget.t(0);
                ConstraintWidget.DimensionBehaviour dimensionBehaviourT2 = constraintWidget.t(1);
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (dimensionBehaviourT == dimensionBehaviour && constraintWidget.f857m != 1 && dimensionBehaviourT2 == dimensionBehaviour && constraintWidget.f859n != 1) {
                    continue;
                } else {
                    a(interfaceC0072bW0, constraintWidget, false);
                    if (dVar.L0 != null) {
                        throw null;
                    }
                }
            }
        }
        interfaceC0072bW0.b();
    }

    public final void c(w.d dVar, String str, int i3, int i4) {
        int iF = dVar.F();
        int iE = dVar.E();
        dVar.v0(0);
        dVar.u0(0);
        dVar.F0(i3);
        dVar.i0(i4);
        dVar.v0(iF);
        dVar.u0(iE);
        this.f5683c.M0();
    }

    public long d(w.d dVar, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11) {
        boolean zT0;
        int i12;
        boolean z3;
        boolean z4;
        int i13;
        int i14;
        InterfaceC0072b interfaceC0072b;
        int i15;
        boolean z5;
        boolean zV0;
        int i16;
        InterfaceC0072b interfaceC0072bW0 = dVar.W0();
        int size = dVar.G0.size();
        int iQ = dVar.Q();
        int iW = dVar.w();
        boolean zB = w.i.b(i3, 128);
        boolean z6 = zB || w.i.b(i3, 64);
        if (z6) {
            for (int i17 = 0; i17 < size; i17++) {
                ConstraintWidget constraintWidget = dVar.G0.get(i17);
                ConstraintWidget.DimensionBehaviour dimensionBehaviourZ = constraintWidget.z();
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                boolean z7 = (dimensionBehaviourZ == dimensionBehaviour) && (constraintWidget.N() == dimensionBehaviour) && constraintWidget.u() > 0.0f;
                if ((constraintWidget.W() && z7) || ((constraintWidget.Y() && z7) || (constraintWidget instanceof w.j) || constraintWidget.W() || constraintWidget.Y())) {
                    z6 = false;
                    break;
                }
            }
        }
        if (z6 && v.d.f5372b != null) {
            throw null;
        }
        int i18 = 2;
        if (z6 && ((i6 == 1073741824 && i8 == 1073741824) || zB)) {
            int iMin = Math.min(dVar.D(), i7);
            int iMin2 = Math.min(dVar.C(), i9);
            if (i6 == 1073741824 && dVar.Q() != iMin) {
                dVar.F0(iMin);
                dVar.Z0();
            }
            if (i8 == 1073741824 && dVar.w() != iMin2) {
                dVar.i0(iMin2);
                dVar.Z0();
            }
            if (i6 == 1073741824 && i8 == 1073741824) {
                zT0 = dVar.T0(zB);
                i12 = 2;
            } else {
                boolean zU0 = dVar.U0(zB);
                if (i6 == 1073741824) {
                    zV0 = zU0 & dVar.V0(zB, 0);
                    i16 = 1;
                } else {
                    zV0 = zU0;
                    i16 = 0;
                }
                if (i8 == 1073741824) {
                    boolean zV02 = dVar.V0(zB, 1) & zV0;
                    i12 = i16 + 1;
                    zT0 = zV02;
                } else {
                    i12 = i16;
                    zT0 = zV0;
                }
            }
            if (zT0) {
                dVar.J0(i6 == 1073741824, i8 == 1073741824);
            }
        } else {
            zT0 = false;
            i12 = 0;
        }
        if (zT0 && i12 == 2) {
            return 0L;
        }
        if (size > 0) {
            b(dVar);
        }
        int iX0 = dVar.X0();
        int size2 = this.f5681a.size();
        if (size > 0) {
            c(dVar, "First pass", iQ, iW);
        }
        if (size2 > 0) {
            ConstraintWidget.DimensionBehaviour dimensionBehaviourZ2 = dVar.z();
            ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            boolean z8 = dimensionBehaviourZ2 == dimensionBehaviour2;
            boolean z9 = dVar.N() == dimensionBehaviour2;
            int iMax = Math.max(dVar.Q(), this.f5683c.F());
            int iMax2 = Math.max(dVar.w(), this.f5683c.E());
            int i19 = 0;
            boolean zA = false;
            while (i19 < size2) {
                ConstraintWidget constraintWidget2 = this.f5681a.get(i19);
                if (constraintWidget2 instanceof w.j) {
                    int iQ2 = constraintWidget2.Q();
                    int iW2 = constraintWidget2.w();
                    i15 = iX0;
                    boolean zA2 = zA | a(interfaceC0072bW0, constraintWidget2, true);
                    if (dVar.L0 != null) {
                        throw null;
                    }
                    int iQ3 = constraintWidget2.Q();
                    int iW3 = constraintWidget2.w();
                    if (iQ3 != iQ2) {
                        constraintWidget2.F0(iQ3);
                        if (z8 && constraintWidget2.J() > iMax) {
                            iMax = Math.max(iMax, constraintWidget2.J() + constraintWidget2.n(ConstraintAnchor.Type.RIGHT).c());
                        }
                        z5 = true;
                    } else {
                        z5 = zA2;
                    }
                    if (iW3 != iW2) {
                        constraintWidget2.i0(iW3);
                        if (z9 && constraintWidget2.q() > iMax2) {
                            iMax2 = Math.max(iMax2, constraintWidget2.q() + constraintWidget2.n(ConstraintAnchor.Type.BOTTOM).c());
                        }
                        z5 = true;
                    }
                    zA = z5 | ((w.j) constraintWidget2).W0();
                } else {
                    i15 = iX0;
                }
                i19++;
                iX0 = i15;
                i18 = 2;
            }
            int i20 = iX0;
            int i21 = i18;
            int i22 = 0;
            while (i22 < i21) {
                int i23 = 0;
                while (i23 < size2) {
                    ConstraintWidget constraintWidget3 = this.f5681a.get(i23);
                    if (((constraintWidget3 instanceof w.g) && !(constraintWidget3 instanceof w.j)) || (constraintWidget3 instanceof w.f) || constraintWidget3.P() == 8 || ((constraintWidget3.f843f.f903e.f896j && constraintWidget3.f845g.f903e.f896j) || (constraintWidget3 instanceof w.j))) {
                        i14 = i22;
                        i13 = size2;
                        interfaceC0072b = interfaceC0072bW0;
                    } else {
                        int iQ4 = constraintWidget3.Q();
                        int iW4 = constraintWidget3.w();
                        i13 = size2;
                        int iO = constraintWidget3.o();
                        i14 = i22;
                        zA |= a(interfaceC0072bW0, constraintWidget3, true);
                        if (dVar.L0 != null) {
                            throw null;
                        }
                        int iQ5 = constraintWidget3.Q();
                        interfaceC0072b = interfaceC0072bW0;
                        int iW5 = constraintWidget3.w();
                        if (iQ5 != iQ4) {
                            constraintWidget3.F0(iQ5);
                            if (z8 && constraintWidget3.J() > iMax) {
                                iMax = Math.max(iMax, constraintWidget3.J() + constraintWidget3.n(ConstraintAnchor.Type.RIGHT).c());
                            }
                            zA = true;
                        }
                        if (iW5 != iW4) {
                            constraintWidget3.i0(iW5);
                            if (z9 && constraintWidget3.q() > iMax2) {
                                iMax2 = Math.max(iMax2, constraintWidget3.q() + constraintWidget3.n(ConstraintAnchor.Type.BOTTOM).c());
                            }
                            zA = true;
                        }
                        if (constraintWidget3.T() && iO != constraintWidget3.o()) {
                            zA = true;
                        }
                    }
                    i23++;
                    size2 = i13;
                    interfaceC0072bW0 = interfaceC0072b;
                    i22 = i14;
                }
                int i24 = i22;
                int i25 = size2;
                InterfaceC0072b interfaceC0072b2 = interfaceC0072bW0;
                if (zA) {
                    c(dVar, "intermediate pass", iQ, iW);
                    zA = false;
                }
                interfaceC0072bW0 = interfaceC0072b2;
                i22 = i24 + 1;
                i21 = 2;
                size2 = i25;
            }
            if (zA) {
                c(dVar, "2nd pass", iQ, iW);
                if (dVar.Q() < iMax) {
                    dVar.F0(iMax);
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (dVar.w() < iMax2) {
                    dVar.i0(iMax2);
                    z4 = true;
                } else {
                    z4 = z3;
                }
                if (z4) {
                    c(dVar, "3rd pass", iQ, iW);
                }
            }
            iX0 = i20;
        }
        dVar.i1(iX0);
        return 0L;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0032  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void e(w.d r7) {
        /*
            r6 = this;
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.ConstraintWidget> r0 = r6.f5681a
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
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.ConstraintWidget> r3 = r6.f5681a
            r3.add(r2)
        L37:
            int r1 = r1 + 1
            goto Lc
        L3a:
            r7.Z0()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: x.b.e(w.d):void");
    }
}
