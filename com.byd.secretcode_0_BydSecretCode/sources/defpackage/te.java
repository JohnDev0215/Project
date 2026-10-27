package defpackage;

import android.graphics.Matrix;
import android.view.View;

/* JADX INFO: compiled from: ViewUtilsApi29.java */
/* JADX INFO: loaded from: classes.dex */
public class te extends se {
    @Override // defpackage.pe, defpackage.ue
    public float c(View view) {
        return view.getTransitionAlpha();
    }

    @Override // defpackage.re, defpackage.ue
    public void e(View view, int i, int i2, int i3, int i4) {
        view.setLeftTopRightBottom(i, i2, i3, i4);
    }

    @Override // defpackage.pe, defpackage.ue
    public void f(View view, float f) {
        view.setTransitionAlpha(f);
    }

    @Override // defpackage.se, defpackage.ue
    public void g(View view, int i) {
        view.setTransitionVisibility(i);
    }

    @Override // defpackage.qe, defpackage.ue
    public void h(View view, Matrix matrix) {
        view.transformMatrixToGlobal(matrix);
    }

    @Override // defpackage.qe, defpackage.ue
    public void i(View view, Matrix matrix) {
        view.transformMatrixToLocal(matrix);
    }
}
