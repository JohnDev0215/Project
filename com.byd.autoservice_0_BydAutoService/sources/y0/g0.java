package y0;

import android.graphics.Matrix;
import android.view.View;

/* JADX INFO: compiled from: ViewUtilsApi29.java */
/* JADX INFO: loaded from: classes.dex */
public class g0 extends f0 {
    @Override // y0.c0, y0.h0
    public float c(View view) {
        return view.getTransitionAlpha();
    }

    @Override // y0.e0, y0.h0
    public void e(View view, int i3, int i4, int i5, int i6) {
        view.setLeftTopRightBottom(i3, i4, i5, i6);
    }

    @Override // y0.c0, y0.h0
    public void f(View view, float f3) {
        view.setTransitionAlpha(f3);
    }

    @Override // y0.f0, y0.h0
    public void g(View view, int i3) {
        view.setTransitionVisibility(i3);
    }

    @Override // y0.d0, y0.h0
    public void h(View view, Matrix matrix) {
        view.transformMatrixToGlobal(matrix);
    }

    @Override // y0.d0, y0.h0
    public void i(View view, Matrix matrix) {
        view.transformMatrixToLocal(matrix);
    }
}
