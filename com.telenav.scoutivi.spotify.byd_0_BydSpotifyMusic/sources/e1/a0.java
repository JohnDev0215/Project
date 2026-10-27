package e1;

import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.view.View;

/* JADX INFO: compiled from: ViewUtilsApi21.java */
/* JADX INFO: loaded from: classes.dex */
class a0 extends z {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static boolean f6044d = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static boolean f6045e = true;

    a0() {
    }

    @Override // e1.e0
    @SuppressLint({"NewApi"})
    public void g(View view, Matrix matrix) {
        if (f6044d) {
            try {
                view.transformMatrixToGlobal(matrix);
            } catch (NoSuchMethodError unused) {
                f6044d = false;
            }
        }
    }

    @Override // e1.e0
    @SuppressLint({"NewApi"})
    public void h(View view, Matrix matrix) {
        if (f6045e) {
            try {
                view.transformMatrixToLocal(matrix);
            } catch (NoSuchMethodError unused) {
                f6045e = false;
            }
        }
    }
}
