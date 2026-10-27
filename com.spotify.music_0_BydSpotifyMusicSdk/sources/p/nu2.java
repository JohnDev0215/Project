package p;

import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* JADX INFO: loaded from: classes.dex */
public abstract class nu2 extends j00 {
    public ou2 a;
    public int b = 0;

    public nu2() {
    }

    @Override // p.j00
    public boolean g(CoordinatorLayout coordinatorLayout, View view, int i) {
        r(coordinatorLayout, view, i);
        if (this.a == null) {
            this.a = new ou2(view);
        }
        ou2 ou2Var = this.a;
        View view2 = ou2Var.a;
        ou2Var.b = view2.getTop();
        ou2Var.c = view2.getLeft();
        this.a.a();
        int i2 = this.b;
        if (i2 == 0) {
            return true;
        }
        ou2 ou2Var2 = this.a;
        if (ou2Var2.d != i2) {
            ou2Var2.d = i2;
            ou2Var2.a();
        }
        this.b = 0;
        return true;
    }

    public void r(CoordinatorLayout coordinatorLayout, View view, int i) {
        coordinatorLayout.q(view, i);
    }

    public nu2(int i) {
    }
}
