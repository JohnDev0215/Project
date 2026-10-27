package defpackage;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.ArrayList;

/* JADX INFO: compiled from: BasicMeasure.java */
/* JADX INFO: loaded from: classes.dex */
public class f6 {
    public final ArrayList<ConstraintWidget> a = new ArrayList<>();
    public a b = new a();
    public w5 c;

    /* JADX INFO: compiled from: BasicMeasure.java */
    public static class a {
        public ConstraintWidget.DimensionBehaviour a;
        public ConstraintWidget.DimensionBehaviour b;
        public int c;
        public int d;
        public int e;
        public int f;
        public int g;
        public boolean h;
        public boolean i;
        public boolean j;
    }

    /* JADX INFO: compiled from: BasicMeasure.java */
    public interface b {
        void a(ConstraintWidget constraintWidget, a aVar);

        void b();
    }

    public f6(w5 w5Var) {
        this.c = w5Var;
    }

    public final boolean a(b bVar, ConstraintWidget constraintWidget, boolean z) {
        this.b.a = constraintWidget.z();
        this.b.b = constraintWidget.N();
        this.b.c = constraintWidget.Q();
        this.b.d = constraintWidget.w();
        a aVar = this.b;
        aVar.i = false;
        aVar.j = z;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = aVar.a;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        boolean z2 = dimensionBehaviour == dimensionBehaviour2;
        boolean z3 = aVar.b == dimensionBehaviour2;
        boolean z4 = z2 && constraintWidget.T > 0.0f;
        boolean z5 = z3 && constraintWidget.T > 0.0f;
        if (z4 && constraintWidget.o[0] == 4) {
            aVar.a = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        if (z5 && constraintWidget.o[1] == 4) {
            aVar.b = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        bVar.a(constraintWidget, aVar);
        constraintWidget.F0(this.b.e);
        constraintWidget.i0(this.b.f);
        constraintWidget.h0(this.b.h);
        constraintWidget.c0(this.b.g);
        a aVar2 = this.b;
        aVar2.j = false;
        return aVar2.i;
    }

    public final void b(w5 w5Var) {
        int size = w5Var.G0.size();
        b bVarW0 = w5Var.W0();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = w5Var.G0.get(i);
            if (!(constraintWidget instanceof y5) && (!constraintWidget.f.e.j || !constraintWidget.g.e.j)) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviourT = constraintWidget.t(0);
                ConstraintWidget.DimensionBehaviour dimensionBehaviourT2 = constraintWidget.t(1);
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (dimensionBehaviourT == dimensionBehaviour && constraintWidget.m != 1 && dimensionBehaviourT2 == dimensionBehaviour && constraintWidget.n != 1) {
                    continue;
                } else {
                    a(bVarW0, constraintWidget, false);
                    if (w5Var.L0 != null) {
                        throw null;
                    }
                }
            }
        }
        bVarW0.b();
    }

    public final void c(w5 w5Var, String str, int i, int i2) {
        int iF = w5Var.F();
        int iE = w5Var.E();
        w5Var.v0(0);
        w5Var.u0(0);
        w5Var.F0(i);
        w5Var.i0(i2);
        w5Var.v0(iF);
        w5Var.u0(iE);
        this.c.M0();
    }

    public long d(w5 w5Var, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        boolean zT0;
        int i10;
        boolean z;
        boolean z2;
        int i11;
        int i12;
        b bVar;
        int i13;
        boolean z3;
        boolean zV0;
        int i14;
        b bVarW0 = w5Var.W0();
        int size = w5Var.G0.size();
        int iQ = w5Var.Q();
        int iW = w5Var.w();
        boolean zB = b6.b(i, 128);
        boolean z4 = zB || b6.b(i, 64);
        if (z4) {
            for (int i15 = 0; i15 < size; i15++) {
                ConstraintWidget constraintWidget = w5Var.G0.get(i15);
                ConstraintWidget.DimensionBehaviour dimensionBehaviourZ = constraintWidget.z();
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                boolean z5 = (dimensionBehaviourZ == dimensionBehaviour) && (constraintWidget.N() == dimensionBehaviour) && constraintWidget.u() > 0.0f;
                if ((constraintWidget.W() && z5) || ((constraintWidget.Y() && z5) || (constraintWidget instanceof c6) || constraintWidget.W() || constraintWidget.Y())) {
                    z4 = false;
                    break;
                }
            }
        }
        if (z4 && n5.b != null) {
            throw null;
        }
        int i16 = 2;
        if (z4 && ((i4 == 1073741824 && i6 == 1073741824) || zB)) {
            int iMin = Math.min(w5Var.D(), i5);
            int iMin2 = Math.min(w5Var.C(), i7);
            if (i4 == 1073741824 && w5Var.Q() != iMin) {
                w5Var.F0(iMin);
                w5Var.Z0();
            }
            if (i6 == 1073741824 && w5Var.w() != iMin2) {
                w5Var.i0(iMin2);
                w5Var.Z0();
            }
            if (i4 == 1073741824 && i6 == 1073741824) {
                zT0 = w5Var.T0(zB);
                i10 = 2;
            } else {
                boolean zU0 = w5Var.U0(zB);
                if (i4 == 1073741824) {
                    zV0 = zU0 & w5Var.V0(zB, 0);
                    i14 = 1;
                } else {
                    zV0 = zU0;
                    i14 = 0;
                }
                if (i6 == 1073741824) {
                    boolean zV02 = w5Var.V0(zB, 1) & zV0;
                    i10 = i14 + 1;
                    zT0 = zV02;
                } else {
                    i10 = i14;
                    zT0 = zV0;
                }
            }
            if (zT0) {
                w5Var.J0(i4 == 1073741824, i6 == 1073741824);
            }
        } else {
            zT0 = false;
            i10 = 0;
        }
        if (zT0 && i10 == 2) {
            return 0L;
        }
        if (size > 0) {
            b(w5Var);
        }
        int iX0 = w5Var.X0();
        int size2 = this.a.size();
        if (size > 0) {
            c(w5Var, "First pass", iQ, iW);
        }
        if (size2 > 0) {
            ConstraintWidget.DimensionBehaviour dimensionBehaviourZ2 = w5Var.z();
            ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            boolean z6 = dimensionBehaviourZ2 == dimensionBehaviour2;
            boolean z7 = w5Var.N() == dimensionBehaviour2;
            int iMax = Math.max(w5Var.Q(), this.c.F());
            int iMax2 = Math.max(w5Var.w(), this.c.E());
            int i17 = 0;
            boolean zA = false;
            while (i17 < size2) {
                ConstraintWidget constraintWidget2 = this.a.get(i17);
                if (constraintWidget2 instanceof c6) {
                    int iQ2 = constraintWidget2.Q();
                    int iW2 = constraintWidget2.w();
                    i13 = iX0;
                    boolean zA2 = zA | a(bVarW0, constraintWidget2, true);
                    if (w5Var.L0 != null) {
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
                    zA = z3 | ((c6) constraintWidget2).W0();
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
                    ConstraintWidget constraintWidget3 = this.a.get(i21);
                    if (((constraintWidget3 instanceof z5) && !(constraintWidget3 instanceof c6)) || (constraintWidget3 instanceof y5) || constraintWidget3.P() == 8 || ((constraintWidget3.f.e.j && constraintWidget3.g.e.j) || (constraintWidget3 instanceof c6))) {
                        i12 = i20;
                        i11 = size2;
                        bVar = bVarW0;
                    } else {
                        int iQ4 = constraintWidget3.Q();
                        int iW4 = constraintWidget3.w();
                        i11 = size2;
                        int iO = constraintWidget3.o();
                        i12 = i20;
                        zA |= a(bVarW0, constraintWidget3, true);
                        if (w5Var.L0 != null) {
                            throw null;
                        }
                        int iQ5 = constraintWidget3.Q();
                        bVar = bVarW0;
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
                    bVarW0 = bVar;
                    i20 = i12;
                }
                int i22 = i20;
                int i23 = size2;
                b bVar2 = bVarW0;
                if (zA) {
                    c(w5Var, "intermediate pass", iQ, iW);
                    zA = false;
                }
                bVarW0 = bVar2;
                i20 = i22 + 1;
                i19 = 2;
                size2 = i23;
            }
            if (zA) {
                c(w5Var, "2nd pass", iQ, iW);
                if (w5Var.Q() < iMax) {
                    w5Var.F0(iMax);
                    z = true;
                } else {
                    z = false;
                }
                if (w5Var.w() < iMax2) {
                    w5Var.i0(iMax2);
                    z2 = true;
                } else {
                    z2 = z;
                }
                if (z2) {
                    c(w5Var, "3rd pass", iQ, iW);
                }
            }
            iX0 = i18;
        }
        w5Var.i1(iX0);
        return 0L;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0032  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void e(defpackage.w5 r7) {
        /*
            r6 = this;
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.ConstraintWidget> r0 = r6.a
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
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.ConstraintWidget> r3 = r6.a
            r3.add(r2)
        L37:
            int r1 = r1 + 1
            goto Lc
        L3a:
            r7.Z0()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.f6.e(w5):void");
    }
}
