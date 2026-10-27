package b.e.b.j;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;

/* JADX INFO: compiled from: Optimizer.java */
/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean[] f1421a = new boolean[3];

    public static void a(d dVar, b.e.b.d dVar2, ConstraintWidget constraintWidget) {
        constraintWidget.k = -1;
        constraintWidget.l = -1;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = dVar.P[0];
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (dimensionBehaviour != dimensionBehaviour2 && constraintWidget.P[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            int i2 = constraintWidget.E.f333e;
            int iQ = dVar.Q() - constraintWidget.G.f333e;
            ConstraintAnchor constraintAnchor = constraintWidget.E;
            constraintAnchor.f335g = dVar2.q(constraintAnchor);
            ConstraintAnchor constraintAnchor2 = constraintWidget.G;
            constraintAnchor2.f335g = dVar2.q(constraintAnchor2);
            dVar2.f(constraintWidget.E.f335g, i2);
            dVar2.f(constraintWidget.G.f335g, iQ);
            constraintWidget.k = 2;
            constraintWidget.l0(i2, iQ);
        }
        if (dVar.P[1] == dimensionBehaviour2 || constraintWidget.P[1] != ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            return;
        }
        int i3 = constraintWidget.F.f333e;
        int iW = dVar.w() - constraintWidget.H.f333e;
        ConstraintAnchor constraintAnchor3 = constraintWidget.F;
        constraintAnchor3.f335g = dVar2.q(constraintAnchor3);
        ConstraintAnchor constraintAnchor4 = constraintWidget.H;
        constraintAnchor4.f335g = dVar2.q(constraintAnchor4);
        dVar2.f(constraintWidget.F.f335g, i3);
        dVar2.f(constraintWidget.H.f335g, iW);
        if (constraintWidget.b0 > 0 || constraintWidget.P() == 8) {
            ConstraintAnchor constraintAnchor5 = constraintWidget.I;
            constraintAnchor5.f335g = dVar2.q(constraintAnchor5);
            dVar2.f(constraintWidget.I.f335g, constraintWidget.b0 + i3);
        }
        constraintWidget.l = 2;
        constraintWidget.A0(i3, iW);
    }

    public static final boolean b(int i2, int i3) {
        return (i2 & i3) == i3;
    }
}
