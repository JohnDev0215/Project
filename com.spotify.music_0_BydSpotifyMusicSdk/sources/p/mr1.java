package p;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public final class mr1 extends a22 {
    public RecyclerView a;
    public final ae2 b = new ae2(this);
    public cr1 c;
    public cr1 d;

    public static int b(View view, dr1 dr1Var) {
        return ((dr1Var.c(view) / 2) + dr1Var.d(view)) - ((dr1Var.g() / 2) + dr1Var.f());
    }

    public static View c(y12 y12Var, dr1 dr1Var) {
        int iV = y12Var.v();
        View view = null;
        if (iV == 0) {
            return null;
        }
        int iG = (dr1Var.g() / 2) + dr1Var.f();
        int i = Integer.MAX_VALUE;
        for (int i2 = 0; i2 < iV; i2++) {
            View viewU = y12Var.u(i2);
            int iAbs = Math.abs(((dr1Var.c(viewU) / 2) + dr1Var.d(viewU)) - iG);
            if (iAbs < i) {
                view = viewU;
                i = iAbs;
            }
        }
        return view;
    }

    public final int[] a(y12 y12Var, View view) {
        int[] iArr = new int[2];
        if (y12Var.d()) {
            iArr[0] = b(view, d(y12Var));
        } else {
            iArr[0] = 0;
        }
        if (y12Var.e()) {
            iArr[1] = b(view, e(y12Var));
        } else {
            iArr[1] = 0;
        }
        return iArr;
    }

    public final dr1 d(y12 y12Var) {
        cr1 cr1Var = this.d;
        if (cr1Var == null || cr1Var.a != y12Var) {
            this.d = new cr1(y12Var, 0);
        }
        return this.d;
    }

    public final dr1 e(y12 y12Var) {
        cr1 cr1Var = this.c;
        if (cr1Var == null || cr1Var.a != y12Var) {
            this.c = new cr1(y12Var, 1);
        }
        return this.c;
    }

    public final void f() {
        y12 layoutManager;
        RecyclerView recyclerView = this.a;
        if (recyclerView == null || (layoutManager = recyclerView.getLayoutManager()) == null) {
            return;
        }
        View viewC = layoutManager.e() ? c(layoutManager, e(layoutManager)) : layoutManager.d() ? c(layoutManager, d(layoutManager)) : null;
        if (viewC == null) {
            return;
        }
        int[] iArrA = a(layoutManager, viewC);
        int i = iArrA[0];
        if (i == 0 && iArrA[1] == 0) {
            return;
        }
        this.a.b0(i, iArrA[1], false);
    }
}
