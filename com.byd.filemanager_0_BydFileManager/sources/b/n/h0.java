package b.n;

import android.graphics.Matrix;
import android.view.View;

/* JADX INFO: compiled from: ViewUtilsApi29.java */
/* JADX INFO: loaded from: classes.dex */
class h0 extends g0 {
    h0() {
    }

    @Override // b.n.d0, b.n.i0
    public float c(View view) {
        return view.getTransitionAlpha();
    }

    @Override // b.n.f0, b.n.i0
    public void e(View view, int i, int i2, int i3, int i4) {
        view.setLeftTopRightBottom(i, i2, i3, i4);
    }

    @Override // b.n.d0, b.n.i0
    public void f(View view, float f) {
        view.setTransitionAlpha(f);
    }

    @Override // b.n.g0, b.n.i0
    public void g(View view, int i) {
        view.setTransitionVisibility(i);
    }

    @Override // b.n.e0, b.n.i0
    public void h(View view, Matrix matrix) {
        view.transformMatrixToGlobal(matrix);
    }

    @Override // b.n.e0, b.n.i0
    public void i(View view, Matrix matrix) {
        view.transformMatrixToLocal(matrix);
    }
}
