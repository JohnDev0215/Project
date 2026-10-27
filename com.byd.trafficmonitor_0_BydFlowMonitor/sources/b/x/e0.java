package b.x;

import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.view.View;

/* JADX INFO: compiled from: ViewUtilsApi21.java */
/* JADX INFO: loaded from: classes.dex */
public class e0 extends d0 {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static boolean f3251f = true;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static boolean f3252g = true;

    @Override // b.x.i0
    @SuppressLint({"NewApi"})
    public void h(View view, Matrix matrix) {
        if (f3251f) {
            try {
                view.transformMatrixToGlobal(matrix);
            } catch (NoSuchMethodError unused) {
                f3251f = false;
            }
        }
    }

    @Override // b.x.i0
    @SuppressLint({"NewApi"})
    public void i(View view, Matrix matrix) {
        if (f3252g) {
            try {
                view.transformMatrixToLocal(matrix);
            } catch (NoSuchMethodError unused) {
                f3252g = false;
            }
        }
    }
}
