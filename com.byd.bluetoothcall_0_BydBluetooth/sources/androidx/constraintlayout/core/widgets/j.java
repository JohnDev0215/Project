package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;

/* JADX INFO: compiled from: Placeholder.java */
/* JADX INFO: loaded from: classes.dex */
public class j extends k {
    @Override // androidx.constraintlayout.core.widgets.k
    public void F1(int i, int i2, int i3, int i4) {
        int iC1 = C1() + D1() + 0;
        int iE1 = E1() + B1() + 0;
        if (this.V0 > 0) {
            iC1 += this.U0[0].Y();
            iE1 += this.U0[0].z();
        }
        int iMax = Math.max(K(), iC1);
        int iMax2 = Math.max(J(), iE1);
        if (i != 1073741824) {
            i2 = i == Integer.MIN_VALUE ? Math.min(iMax, i2) : i == 0 ? iMax : 0;
        }
        if (i3 != 1073741824) {
            i4 = i3 == Integer.MIN_VALUE ? Math.min(iMax2, i4) : i3 == 0 ? iMax2 : 0;
        }
        K1(i2, i4);
        n1(i2);
        O0(i4);
        J1(this.V0 > 0);
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void g(androidx.constraintlayout.core.d dVar, boolean z) {
        super.g(dVar, z);
        if (this.V0 > 0) {
            ConstraintWidget constraintWidget = this.U0[0];
            constraintWidget.w0();
            ConstraintAnchor.Type type = ConstraintAnchor.Type.LEFT;
            constraintWidget.j(type, this, type);
            ConstraintAnchor.Type type2 = ConstraintAnchor.Type.RIGHT;
            constraintWidget.j(type2, this, type2);
            ConstraintAnchor.Type type3 = ConstraintAnchor.Type.TOP;
            constraintWidget.j(type3, this, type3);
            ConstraintAnchor.Type type4 = ConstraintAnchor.Type.BOTTOM;
            constraintWidget.j(type4, this, type4);
        }
    }
}
