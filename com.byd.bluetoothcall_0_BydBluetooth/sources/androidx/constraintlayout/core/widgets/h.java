package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.widgets.analyzer.n;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: compiled from: HelperWidget.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends ConstraintWidget implements g {
    public ConstraintWidget[] U0 = new ConstraintWidget[4];
    public int V0 = 0;

    @Override // androidx.constraintlayout.core.widgets.g
    public void a() {
        this.V0 = 0;
        Arrays.fill(this.U0, (Object) null);
    }

    @Override // androidx.constraintlayout.core.widgets.g
    public void b(d dVar) {
    }

    @Override // androidx.constraintlayout.core.widgets.g
    public void c(ConstraintWidget constraintWidget) {
        if (constraintWidget == this || constraintWidget == null) {
            return;
        }
        int i = this.V0 + 1;
        ConstraintWidget[] constraintWidgetArr = this.U0;
        if (i > constraintWidgetArr.length) {
            this.U0 = (ConstraintWidget[]) Arrays.copyOf(constraintWidgetArr, constraintWidgetArr.length * 2);
        }
        ConstraintWidget[] constraintWidgetArr2 = this.U0;
        int i2 = this.V0;
        constraintWidgetArr2[i2] = constraintWidget;
        this.V0 = i2 + 1;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void n(ConstraintWidget constraintWidget, HashMap<ConstraintWidget, ConstraintWidget> map) {
        super.n(constraintWidget, map);
        h hVar = (h) constraintWidget;
        this.V0 = 0;
        int i = hVar.V0;
        for (int i2 = 0; i2 < i; i2++) {
            c(map.get(hVar.U0[i2]));
        }
    }

    public void u1(ArrayList<n> arrayList, int i, n nVar) {
        for (int i2 = 0; i2 < this.V0; i2++) {
            nVar.a(this.U0[i2]);
        }
        for (int i3 = 0; i3 < this.V0; i3++) {
            androidx.constraintlayout.core.widgets.analyzer.h.a(this.U0[i3], i, arrayList, nVar);
        }
    }

    public int v1(int i) {
        int i2;
        int i3;
        for (int i4 = 0; i4 < this.V0; i4++) {
            ConstraintWidget constraintWidget = this.U0[i4];
            if (i == 0 && (i3 = constraintWidget.S0) != -1) {
                return i3;
            }
            if (i == 1 && (i2 = constraintWidget.T0) != -1) {
                return i2;
            }
        }
        return -1;
    }
}
