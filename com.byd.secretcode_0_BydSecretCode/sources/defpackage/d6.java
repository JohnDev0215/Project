package defpackage;

import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.ArrayList;

/* JADX INFO: compiled from: WidgetContainer.java */
/* JADX INFO: loaded from: classes.dex */
public class d6 extends ConstraintWidget {
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
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = this.G0.get(i);
            if (constraintWidget instanceof d6) {
                ((d6) constraintWidget).M0();
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
            ((d6) constraintWidget.H()).N0(constraintWidget);
        }
        constraintWidget.x0(this);
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void b0(m5 m5Var) {
        super.b0(m5Var);
        int size = this.G0.size();
        for (int i = 0; i < size; i++) {
            this.G0.get(i).b0(m5Var);
        }
    }
}
