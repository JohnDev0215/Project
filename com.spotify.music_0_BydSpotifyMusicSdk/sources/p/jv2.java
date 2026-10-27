package p;

import android.graphics.Matrix;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class jv2 extends iv2 {
    @Override // p.iv2, p.u11
    public final void K(View view, int i) {
        view.setTransitionVisibility(i);
    }

    @Override // p.fv2
    public final float L(View view) {
        return view.getTransitionAlpha();
    }

    @Override // p.fv2
    public final void M(View view, float f) {
        view.setTransitionAlpha(f);
    }

    @Override // p.gv2
    public final void N(View view, Matrix matrix) {
        view.transformMatrixToGlobal(matrix);
    }

    @Override // p.gv2
    public final void O(View view, Matrix matrix) {
        view.transformMatrixToLocal(matrix);
    }

    @Override // p.hv2
    public final void P(View view, int i, int i2, int i3, int i4) {
        view.setLeftTopRightBottom(i, i2, i3, i4);
    }
}
