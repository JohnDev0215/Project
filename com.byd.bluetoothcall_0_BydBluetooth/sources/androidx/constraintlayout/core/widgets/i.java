package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.widgets.ConstraintWidget;

/* JADX INFO: compiled from: Optimizer.java */
/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static boolean[] f887a = new boolean[3];

    static void a(d dVar, androidx.constraintlayout.core.d dVar2, ConstraintWidget constraintWidget) {
        constraintWidget.u = -1;
        constraintWidget.v = -1;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = dVar.c0[0];
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (dimensionBehaviour != dimensionBehaviour2 && constraintWidget.c0[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            int i = constraintWidget.R.g;
            int iY = dVar.Y() - constraintWidget.T.g;
            ConstraintAnchor constraintAnchor = constraintWidget.R;
            constraintAnchor.i = dVar2.q(constraintAnchor);
            ConstraintAnchor constraintAnchor2 = constraintWidget.T;
            constraintAnchor2.i = dVar2.q(constraintAnchor2);
            dVar2.f(constraintWidget.R.i, i);
            dVar2.f(constraintWidget.T.i, iY);
            constraintWidget.u = 2;
            constraintWidget.R0(i, iY);
        }
        if (dVar.c0[1] == dimensionBehaviour2 || constraintWidget.c0[1] != ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            return;
        }
        int i2 = constraintWidget.S.g;
        int iZ = dVar.z() - constraintWidget.U.g;
        ConstraintAnchor constraintAnchor3 = constraintWidget.S;
        constraintAnchor3.i = dVar2.q(constraintAnchor3);
        ConstraintAnchor constraintAnchor4 = constraintWidget.U;
        constraintAnchor4.i = dVar2.q(constraintAnchor4);
        dVar2.f(constraintWidget.S.i, i2);
        dVar2.f(constraintWidget.U.i, iZ);
        if (constraintWidget.o0 > 0 || constraintWidget.X() == 8) {
            ConstraintAnchor constraintAnchor5 = constraintWidget.V;
            constraintAnchor5.i = dVar2.q(constraintAnchor5);
            dVar2.f(constraintWidget.V.i, constraintWidget.o0 + i2);
        }
        constraintWidget.v = 2;
        constraintWidget.i1(i2, iZ);
    }

    public static final boolean b(int i, int i2) {
        return (i & i2) == i2;
    }
}
