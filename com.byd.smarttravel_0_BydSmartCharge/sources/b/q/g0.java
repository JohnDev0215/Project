package b.q;

import android.graphics.Matrix;
import android.view.View;

/* JADX INFO: compiled from: ViewUtilsApi29.java */
/* JADX INFO: loaded from: classes.dex */
public class g0 extends f0 {
    @Override // b.q.c0, b.q.h0
    public float c(View view) {
        return view.getTransitionAlpha();
    }

    @Override // b.q.e0, b.q.h0
    public void e(View view, int i, int i2, int i3, int i4) {
        view.setLeftTopRightBottom(i, i2, i3, i4);
    }

    @Override // b.q.c0, b.q.h0
    public void f(View view, float f) {
        view.setTransitionAlpha(f);
    }

    @Override // b.q.f0, b.q.h0
    public void g(View view, int i) {
        view.setTransitionVisibility(i);
    }

    @Override // b.q.d0, b.q.h0
    public void h(View view, Matrix matrix) {
        view.transformMatrixToGlobal(matrix);
    }

    @Override // b.q.d0, b.q.h0
    public void i(View view, Matrix matrix) {
        view.transformMatrixToLocal(matrix);
    }
}
