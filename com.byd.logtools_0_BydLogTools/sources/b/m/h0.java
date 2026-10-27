package b.m;

import android.graphics.Matrix;
import android.view.View;

/* JADX INFO: compiled from: ViewUtilsApi29.java */
/* JADX INFO: loaded from: classes.dex */
class h0 extends g0 {
    h0() {
    }

    @Override // b.m.d0, b.m.i0
    public float c(View view) {
        return view.getTransitionAlpha();
    }

    @Override // b.m.f0, b.m.i0
    public void e(View view, int i, int i2, int i3, int i4) {
        view.setLeftTopRightBottom(i, i2, i3, i4);
    }

    @Override // b.m.d0, b.m.i0
    public void f(View view, float f2) {
        view.setTransitionAlpha(f2);
    }

    @Override // b.m.g0, b.m.i0
    public void g(View view, int i) {
        view.setTransitionVisibility(i);
    }

    @Override // b.m.e0, b.m.i0
    public void h(View view, Matrix matrix) {
        view.transformMatrixToGlobal(matrix);
    }

    @Override // b.m.e0, b.m.i0
    public void i(View view, Matrix matrix) {
        view.transformMatrixToLocal(matrix);
    }
}
