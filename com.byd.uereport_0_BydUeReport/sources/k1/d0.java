package k1;

import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.view.View;

/* JADX INFO: compiled from: ViewUtilsApi21.java */
/* JADX INFO: loaded from: classes.dex */
public class d0 extends c0 {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static boolean f5729f = true;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static boolean f5730g = true;

    @Override // k1.h0
    @SuppressLint({"NewApi"})
    public void h(View view, Matrix matrix) {
        if (f5729f) {
            try {
                view.transformMatrixToGlobal(matrix);
            } catch (NoSuchMethodError unused) {
                f5729f = false;
            }
        }
    }

    @Override // k1.h0
    @SuppressLint({"NewApi"})
    public void i(View view, Matrix matrix) {
        if (f5730g) {
            try {
                view.transformMatrixToLocal(matrix);
            } catch (NoSuchMethodError unused) {
                f5730g = false;
            }
        }
    }
}
