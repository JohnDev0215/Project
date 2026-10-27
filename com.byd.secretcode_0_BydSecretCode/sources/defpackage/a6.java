package defpackage;

import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: compiled from: HelperWidget.java */
/* JADX INFO: loaded from: classes.dex */
public class a6 extends ConstraintWidget implements z5 {
    public ConstraintWidget[] G0 = new ConstraintWidget[4];
    public int H0 = 0;

    @Override // defpackage.z5
    public void a(ConstraintWidget constraintWidget) {
        if (constraintWidget == this || constraintWidget == null) {
            return;
        }
        int i = this.H0 + 1;
        ConstraintWidget[] constraintWidgetArr = this.G0;
        if (i > constraintWidgetArr.length) {
            this.G0 = (ConstraintWidget[]) Arrays.copyOf(constraintWidgetArr, constraintWidgetArr.length * 2);
        }
        ConstraintWidget[] constraintWidgetArr2 = this.G0;
        int i2 = this.H0;
        constraintWidgetArr2[i2] = constraintWidget;
        this.H0 = i2 + 1;
    }

    @Override // defpackage.z5
    public void b(w5 w5Var) {
    }

    @Override // defpackage.z5
    public void c() {
        this.H0 = 0;
        Arrays.fill(this.G0, (Object) null);
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void l(ConstraintWidget constraintWidget, HashMap<ConstraintWidget, ConstraintWidget> map) {
        super.l(constraintWidget, map);
        a6 a6Var = (a6) constraintWidget;
        this.H0 = 0;
        int i = a6Var.H0;
        for (int i2 = 0; i2 < i; i2++) {
            a(map.get(a6Var.G0[i2]));
        }
    }
}
