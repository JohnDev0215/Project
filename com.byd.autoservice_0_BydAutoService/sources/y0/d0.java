package y0;

import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.view.View;

/* JADX INFO: compiled from: ViewUtilsApi21.java */
/* JADX INFO: loaded from: classes.dex */
public class d0 extends c0 {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static boolean f5885f = true;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static boolean f5886g = true;

    @Override // y0.h0
    @SuppressLint({"NewApi"})
    public void h(View view, Matrix matrix) {
        if (f5885f) {
            try {
                view.transformMatrixToGlobal(matrix);
            } catch (NoSuchMethodError unused) {
                f5885f = false;
            }
        }
    }

    @Override // y0.h0
    @SuppressLint({"NewApi"})
    public void i(View view, Matrix matrix) {
        if (f5886g) {
            try {
                view.transformMatrixToLocal(matrix);
            } catch (NoSuchMethodError unused) {
                f5886g = false;
            }
        }
    }
}
