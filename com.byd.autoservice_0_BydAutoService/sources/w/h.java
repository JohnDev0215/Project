package w;

import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: compiled from: HelperWidget.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends ConstraintWidget implements g {
    public ConstraintWidget[] G0 = new ConstraintWidget[4];
    public int H0 = 0;

    @Override // w.g
    public void a(ConstraintWidget constraintWidget) {
        if (constraintWidget == this || constraintWidget == null) {
            return;
        }
        int i3 = this.H0 + 1;
        ConstraintWidget[] constraintWidgetArr = this.G0;
        if (i3 > constraintWidgetArr.length) {
            this.G0 = (ConstraintWidget[]) Arrays.copyOf(constraintWidgetArr, constraintWidgetArr.length * 2);
        }
        ConstraintWidget[] constraintWidgetArr2 = this.G0;
        int i4 = this.H0;
        constraintWidgetArr2[i4] = constraintWidget;
        this.H0 = i4 + 1;
    }

    @Override // w.g
    public void b(d dVar) {
    }

    @Override // w.g
    public void c() {
        this.H0 = 0;
        Arrays.fill(this.G0, (Object) null);
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void l(ConstraintWidget constraintWidget, HashMap<ConstraintWidget, ConstraintWidget> map) {
        super.l(constraintWidget, map);
        h hVar = (h) constraintWidget;
        this.H0 = 0;
        int i3 = hVar.H0;
        for (int i4 = 0; i4 < i3; i4++) {
            a(map.get(hVar.G0[i4]));
        }
    }
}
