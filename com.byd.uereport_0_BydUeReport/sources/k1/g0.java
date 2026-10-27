package k1;

import android.graphics.Matrix;
import android.view.View;

/* JADX INFO: compiled from: ViewUtilsApi29.java */
/* JADX INFO: loaded from: classes.dex */
public class g0 extends f0 {
    @Override // k1.c0, k1.h0
    public float c(View view) {
        return view.getTransitionAlpha();
    }

    @Override // k1.e0, k1.h0
    public void e(View view, int i8, int i9, int i10, int i11) {
        view.setLeftTopRightBottom(i8, i9, i10, i11);
    }

    @Override // k1.c0, k1.h0
    public void f(View view, float f8) {
        view.setTransitionAlpha(f8);
    }

    @Override // k1.f0, k1.h0
    public void g(View view, int i8) {
        view.setTransitionVisibility(i8);
    }

    @Override // k1.d0, k1.h0
    public void h(View view, Matrix matrix) {
        view.transformMatrixToGlobal(matrix);
    }

    @Override // k1.d0, k1.h0
    public void i(View view, Matrix matrix) {
        view.transformMatrixToLocal(matrix);
    }
}
