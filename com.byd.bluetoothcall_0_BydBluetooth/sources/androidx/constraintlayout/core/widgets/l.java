package androidx.constraintlayout.core.widgets;

import java.util.ArrayList;

/* JADX INFO: compiled from: WidgetContainer.java */
/* JADX INFO: loaded from: classes.dex */
public class l extends ConstraintWidget {
    public ArrayList<ConstraintWidget> U0 = new ArrayList<>();

    public void c(ConstraintWidget constraintWidget) {
        this.U0.add(constraintWidget);
        if (constraintWidget.M() != null) {
            ((l) constraintWidget.M()).w1(constraintWidget);
        }
        constraintWidget.f1(this);
    }

    public ArrayList<ConstraintWidget> u1() {
        return this.U0;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void v0() {
        this.U0.clear();
        super.v0();
    }

    public void v1() {
        ArrayList<ConstraintWidget> arrayList = this.U0;
        if (arrayList == null) {
            return;
        }
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = this.U0.get(i);
            if (constraintWidget instanceof l) {
                ((l) constraintWidget).v1();
            }
        }
    }

    public void w1(ConstraintWidget constraintWidget) {
        this.U0.remove(constraintWidget);
        constraintWidget.v0();
    }

    public void x1() {
        this.U0.clear();
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void z0(androidx.constraintlayout.core.c cVar) {
        super.z0(cVar);
        int size = this.U0.size();
        for (int i = 0; i < size; i++) {
            this.U0.get(i).z0(cVar);
        }
    }
}
