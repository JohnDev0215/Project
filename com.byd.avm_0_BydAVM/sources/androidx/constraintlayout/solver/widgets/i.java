package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.widgets.ConstraintWidget;

/* JADX INFO: compiled from: Optimizer.java */
/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static boolean[] f700a = new boolean[3];

    static void a(d dVar, androidx.constraintlayout.solver.d dVar2, ConstraintWidget constraintWidget) {
        constraintWidget.k = -1;
        constraintWidget.l = -1;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = dVar.P[0];
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (dimensionBehaviour != dimensionBehaviour2 && constraintWidget.P[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            int i = constraintWidget.E.e;
            int iQ = dVar.Q() - constraintWidget.G.e;
            ConstraintAnchor constraintAnchor = constraintWidget.E;
            constraintAnchor.g = dVar2.q(constraintAnchor);
            ConstraintAnchor constraintAnchor2 = constraintWidget.G;
            constraintAnchor2.g = dVar2.q(constraintAnchor2);
            dVar2.f(constraintWidget.E.g, i);
            dVar2.f(constraintWidget.G.g, iQ);
            constraintWidget.k = 2;
            constraintWidget.l0(i, iQ);
        }
        if (dVar.P[1] == dimensionBehaviour2 || constraintWidget.P[1] != ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            return;
        }
        int i2 = constraintWidget.F.e;
        int iW = dVar.w() - constraintWidget.H.e;
        ConstraintAnchor constraintAnchor3 = constraintWidget.F;
        constraintAnchor3.g = dVar2.q(constraintAnchor3);
        ConstraintAnchor constraintAnchor4 = constraintWidget.H;
        constraintAnchor4.g = dVar2.q(constraintAnchor4);
        dVar2.f(constraintWidget.F.g, i2);
        dVar2.f(constraintWidget.H.g, iW);
        if (constraintWidget.b0 > 0 || constraintWidget.P() == 8) {
            ConstraintAnchor constraintAnchor5 = constraintWidget.I;
            constraintAnchor5.g = dVar2.q(constraintAnchor5);
            dVar2.f(constraintWidget.I.g, constraintWidget.b0 + i2);
        }
        constraintWidget.l = 2;
        constraintWidget.A0(i2, iW);
    }

    public static final boolean b(int i, int i2) {
        return (i & i2) == i2;
    }
}
