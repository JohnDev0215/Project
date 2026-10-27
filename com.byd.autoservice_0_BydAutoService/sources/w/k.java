package w;

import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.ArrayList;

/* JADX INFO: compiled from: WidgetContainer.java */
/* JADX INFO: loaded from: classes.dex */
public class k extends ConstraintWidget {
    public ArrayList<ConstraintWidget> G0 = new ArrayList<>();

    public ArrayList<ConstraintWidget> L0() {
        return this.G0;
    }

    public void M0() {
        ArrayList<ConstraintWidget> arrayList = this.G0;
        if (arrayList == null) {
            return;
        }
        int size = arrayList.size();
        for (int i3 = 0; i3 < size; i3++) {
            ConstraintWidget constraintWidget = this.G0.get(i3);
            if (constraintWidget instanceof k) {
                ((k) constraintWidget).M0();
            }
        }
    }

    public void N0(ConstraintWidget constraintWidget) {
        this.G0.remove(constraintWidget);
        constraintWidget.x0(null);
    }

    public void O0() {
        this.G0.clear();
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void Z() {
        this.G0.clear();
        super.Z();
    }

    public void a(ConstraintWidget constraintWidget) {
        this.G0.add(constraintWidget);
        if (constraintWidget.H() != null) {
            ((k) constraintWidget.H()).N0(constraintWidget);
        }
        constraintWidget.x0(this);
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void b0(v.c cVar) {
        super.b0(cVar);
        int size = this.G0.size();
        for (int i3 = 0; i3 < size; i3++) {
            this.G0.get(i3).b0(cVar);
        }
    }
}
