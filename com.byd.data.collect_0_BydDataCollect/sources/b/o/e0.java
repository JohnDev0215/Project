package b.o;

import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.view.View;

/* JADX INFO: compiled from: ViewUtilsApi21.java */
/* JADX INFO: loaded from: classes.dex */
class e0 extends d0 {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static boolean f2855f = true;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static boolean f2856g = true;

    e0() {
    }

    @Override // b.o.i0
    @SuppressLint({"NewApi"})
    public void h(View view, Matrix matrix) {
        if (f2855f) {
            try {
                view.transformMatrixToGlobal(matrix);
            } catch (NoSuchMethodError unused) {
                f2855f = false;
            }
        }
    }

    @Override // b.o.i0
    @SuppressLint({"NewApi"})
    public void i(View view, Matrix matrix) {
        if (f2856g) {
            try {
                view.transformMatrixToLocal(matrix);
            } catch (NoSuchMethodError unused) {
                f2856g = false;
            }
        }
    }
}
