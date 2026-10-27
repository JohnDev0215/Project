package b.x;

import android.graphics.Matrix;
import android.view.View;

/* JADX INFO: compiled from: ViewUtilsApi29.java */
/* JADX INFO: loaded from: classes.dex */
public class h0 extends g0 {
    @Override // b.x.d0, b.x.i0
    public float c(View view) {
        return view.getTransitionAlpha();
    }

    @Override // b.x.f0, b.x.i0
    public void e(View view, int i2, int i3, int i4, int i5) {
        view.setLeftTopRightBottom(i2, i3, i4, i5);
    }

    @Override // b.x.d0, b.x.i0
    public void f(View view, float f2) {
        view.setTransitionAlpha(f2);
    }

    @Override // b.x.g0, b.x.i0
    public void g(View view, int i2) {
        view.setTransitionVisibility(i2);
    }

    @Override // b.x.e0, b.x.i0
    public void h(View view, Matrix matrix) {
        view.transformMatrixToGlobal(matrix);
    }

    @Override // b.x.e0, b.x.i0
    public void i(View view, Matrix matrix) {
        view.transformMatrixToLocal(matrix);
    }
}
