package w;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;

/* JADX INFO: compiled from: Optimizer.java */
/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean[] f5516a = new boolean[3];

    public static void a(d dVar, v.d dVar2, ConstraintWidget constraintWidget) {
        constraintWidget.f853k = -1;
        constraintWidget.f855l = -1;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = dVar.P[0];
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (dimensionBehaviour != dimensionBehaviour2 && constraintWidget.P[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            int i3 = constraintWidget.E.f829e;
            int iQ = dVar.Q() - constraintWidget.G.f829e;
            ConstraintAnchor constraintAnchor = constraintWidget.E;
            constraintAnchor.f831g = dVar2.q(constraintAnchor);
            ConstraintAnchor constraintAnchor2 = constraintWidget.G;
            constraintAnchor2.f831g = dVar2.q(constraintAnchor2);
            dVar2.f(constraintWidget.E.f831g, i3);
            dVar2.f(constraintWidget.G.f831g, iQ);
            constraintWidget.f853k = 2;
            constraintWidget.l0(i3, iQ);
        }
        if (dVar.P[1] == dimensionBehaviour2 || constraintWidget.P[1] != ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            return;
        }
        int i4 = constraintWidget.F.f829e;
        int iW = dVar.w() - constraintWidget.H.f829e;
        ConstraintAnchor constraintAnchor3 = constraintWidget.F;
        constraintAnchor3.f831g = dVar2.q(constraintAnchor3);
        ConstraintAnchor constraintAnchor4 = constraintWidget.H;
        constraintAnchor4.f831g = dVar2.q(constraintAnchor4);
        dVar2.f(constraintWidget.F.f831g, i4);
        dVar2.f(constraintWidget.H.f831g, iW);
        if (constraintWidget.f836b0 > 0 || constraintWidget.P() == 8) {
            ConstraintAnchor constraintAnchor5 = constraintWidget.I;
            constraintAnchor5.f831g = dVar2.q(constraintAnchor5);
            dVar2.f(constraintWidget.I.f831g, constraintWidget.f836b0 + i4);
        }
        constraintWidget.f855l = 2;
        constraintWidget.A0(i4, iW);
    }

    public static final boolean b(int i3, int i4) {
        return (i3 & i4) == i4;
    }
}
