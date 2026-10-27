package F0;

import android.graphics.Matrix;
import android.view.View;
import android.view.ViewParent;
import n0.AbstractC1861K;

/* JADX INFO: renamed from: F0.o0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0220o0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Matrix f2239a = new Matrix();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int[] f2240b = new int[2];

    public void a(View view, float[] fArr) {
        Matrix matrix = this.f2239a;
        matrix.reset();
        view.transformMatrixToGlobal(matrix);
        ViewParent parent = view.getParent();
        while (parent instanceof View) {
            view = parent;
            parent = view.getParent();
        }
        int[] iArr = this.f2240b;
        view.getLocationOnScreen(iArr);
        int i7 = iArr[0];
        int i10 = iArr[1];
        view.getLocationInWindow(iArr);
        matrix.postTranslate(iArr[0] - i7, iArr[1] - i10);
        AbstractC1861K.y(matrix, fArr);
    }
}
