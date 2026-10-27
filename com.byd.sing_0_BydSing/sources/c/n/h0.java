package c.n;

import android.graphics.Matrix;
import android.view.View;

/* JADX INFO: compiled from: ViewUtilsApi29.java */
/* JADX INFO: loaded from: classes.dex */
class h0 extends g0 {
    h0() {
    }

    @Override // c.n.d0, c.n.i0
    public float c(View view) {
        return view.getTransitionAlpha();
    }

    @Override // c.n.f0, c.n.i0
    public void e(View view, int i, int i2, int i3, int i4) {
        view.setLeftTopRightBottom(i, i2, i3, i4);
    }

    @Override // c.n.d0, c.n.i0
    public void f(View view, float f) {
        view.setTransitionAlpha(f);
    }

    @Override // c.n.g0, c.n.i0
    public void g(View view, int i) {
        view.setTransitionVisibility(i);
    }

    @Override // c.n.e0, c.n.i0
    public void h(View view, Matrix matrix) {
        view.transformMatrixToGlobal(matrix);
    }

    @Override // c.n.e0, c.n.i0
    public void i(View view, Matrix matrix) {
        view.transformMatrixToLocal(matrix);
    }
}
