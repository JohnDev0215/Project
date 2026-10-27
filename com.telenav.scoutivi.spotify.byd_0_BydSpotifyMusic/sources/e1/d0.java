package e1;

import android.graphics.Matrix;
import android.view.View;

/* JADX INFO: compiled from: ViewUtilsApi29.java */
/* JADX INFO: loaded from: classes.dex */
class d0 extends c0 {
    d0() {
    }

    @Override // e1.z, e1.e0
    public float b(View view) {
        return view.getTransitionAlpha();
    }

    @Override // e1.b0, e1.e0
    public void d(View view, int i7, int i8, int i9, int i10) {
        view.setLeftTopRightBottom(i7, i8, i9, i10);
    }

    @Override // e1.z, e1.e0
    public void e(View view, float f7) {
        view.setTransitionAlpha(f7);
    }

    @Override // e1.c0, e1.e0
    public void f(View view, int i7) {
        view.setTransitionVisibility(i7);
    }

    @Override // e1.a0, e1.e0
    public void g(View view, Matrix matrix) {
        view.transformMatrixToGlobal(matrix);
    }

    @Override // e1.a0, e1.e0
    public void h(View view, Matrix matrix) {
        view.transformMatrixToLocal(matrix);
    }
}
