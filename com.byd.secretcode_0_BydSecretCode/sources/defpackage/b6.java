package defpackage;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;

/* JADX INFO: compiled from: Optimizer.java */
/* JADX INFO: loaded from: classes.dex */
public class b6 {
    public static boolean[] a = new boolean[3];

    public static void a(w5 w5Var, n5 n5Var, ConstraintWidget constraintWidget) {
        constraintWidget.k = -1;
        constraintWidget.l = -1;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = w5Var.P[0];
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (dimensionBehaviour != dimensionBehaviour2 && constraintWidget.P[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            int i = constraintWidget.E.e;
            int iQ = w5Var.Q() - constraintWidget.G.e;
            ConstraintAnchor constraintAnchor = constraintWidget.E;
            constraintAnchor.g = n5Var.q(constraintAnchor);
            ConstraintAnchor constraintAnchor2 = constraintWidget.G;
            constraintAnchor2.g = n5Var.q(constraintAnchor2);
            n5Var.f(constraintWidget.E.g, i);
            n5Var.f(constraintWidget.G.g, iQ);
            constraintWidget.k = 2;
            constraintWidget.l0(i, iQ);
        }
        if (w5Var.P[1] == dimensionBehaviour2 || constraintWidget.P[1] != ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            return;
        }
        int i2 = constraintWidget.F.e;
        int iW = w5Var.w() - constraintWidget.H.e;
        ConstraintAnchor constraintAnchor3 = constraintWidget.F;
        constraintAnchor3.g = n5Var.q(constraintAnchor3);
        ConstraintAnchor constraintAnchor4 = constraintWidget.H;
        constraintAnchor4.g = n5Var.q(constraintAnchor4);
        n5Var.f(constraintWidget.F.g, i2);
        n5Var.f(constraintWidget.H.g, iW);
        if (constraintWidget.b0 > 0 || constraintWidget.P() == 8) {
            ConstraintAnchor constraintAnchor5 = constraintWidget.I;
            constraintAnchor5.g = n5Var.q(constraintAnchor5);
            n5Var.f(constraintWidget.I.g, constraintWidget.b0 + i2);
        }
        constraintWidget.l = 2;
        constraintWidget.A0(i2, iW);
    }

    public static final boolean b(int i, int i2) {
        return (i & i2) == i2;
    }
}
