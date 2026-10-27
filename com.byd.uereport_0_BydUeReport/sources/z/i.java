package z;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;

/* JADX INFO: compiled from: Optimizer.java */
/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean[] f9483a = new boolean[3];

    public static void a(d dVar, y.d dVar2, ConstraintWidget constraintWidget) {
        constraintWidget.f944k = -1;
        constraintWidget.f946l = -1;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = dVar.P[0];
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (dimensionBehaviour != dimensionBehaviour2 && constraintWidget.P[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            int i8 = constraintWidget.E.f920e;
            int iQ = dVar.Q() - constraintWidget.G.f920e;
            ConstraintAnchor constraintAnchor = constraintWidget.E;
            constraintAnchor.f922g = dVar2.q(constraintAnchor);
            ConstraintAnchor constraintAnchor2 = constraintWidget.G;
            constraintAnchor2.f922g = dVar2.q(constraintAnchor2);
            dVar2.f(constraintWidget.E.f922g, i8);
            dVar2.f(constraintWidget.G.f922g, iQ);
            constraintWidget.f944k = 2;
            constraintWidget.l0(i8, iQ);
        }
        if (dVar.P[1] == dimensionBehaviour2 || constraintWidget.P[1] != ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            return;
        }
        int i9 = constraintWidget.F.f920e;
        int iW = dVar.w() - constraintWidget.H.f920e;
        ConstraintAnchor constraintAnchor3 = constraintWidget.F;
        constraintAnchor3.f922g = dVar2.q(constraintAnchor3);
        ConstraintAnchor constraintAnchor4 = constraintWidget.H;
        constraintAnchor4.f922g = dVar2.q(constraintAnchor4);
        dVar2.f(constraintWidget.F.f922g, i9);
        dVar2.f(constraintWidget.H.f922g, iW);
        if (constraintWidget.f927b0 > 0 || constraintWidget.P() == 8) {
            ConstraintAnchor constraintAnchor5 = constraintWidget.I;
            constraintAnchor5.f922g = dVar2.q(constraintAnchor5);
            dVar2.f(constraintWidget.I.f922g, constraintWidget.f927b0 + i9);
        }
        constraintWidget.f946l = 2;
        constraintWidget.A0(i9, iW);
    }

    public static final boolean b(int i8, int i9) {
        return (i8 & i9) == i9;
    }
}
