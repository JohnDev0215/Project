package b.e.b.j;

import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: compiled from: HelperWidget.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends ConstraintWidget implements g {
    public ConstraintWidget[] G0 = new ConstraintWidget[4];
    public int H0 = 0;

    @Override // b.e.b.j.g
    public void a(ConstraintWidget constraintWidget) {
        if (constraintWidget == this || constraintWidget == null) {
            return;
        }
        int i2 = this.H0 + 1;
        ConstraintWidget[] constraintWidgetArr = this.G0;
        if (i2 > constraintWidgetArr.length) {
            this.G0 = (ConstraintWidget[]) Arrays.copyOf(constraintWidgetArr, constraintWidgetArr.length * 2);
        }
        ConstraintWidget[] constraintWidgetArr2 = this.G0;
        int i3 = this.H0;
        constraintWidgetArr2[i3] = constraintWidget;
        this.H0 = i3 + 1;
    }

    @Override // b.e.b.j.g
    public void b(d dVar) {
    }

    @Override // b.e.b.j.g
    public void c() {
        this.H0 = 0;
        Arrays.fill(this.G0, (Object) null);
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void l(ConstraintWidget constraintWidget, HashMap<ConstraintWidget, ConstraintWidget> map) {
        super.l(constraintWidget, map);
        h hVar = (h) constraintWidget;
        this.H0 = 0;
        int i2 = hVar.H0;
        for (int i3 = 0; i3 < i2; i3++) {
            a(map.get(hVar.G0[i3]));
        }
    }
}
