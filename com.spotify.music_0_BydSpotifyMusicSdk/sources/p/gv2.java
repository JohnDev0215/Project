package p;

import android.graphics.Matrix;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public abstract class gv2 extends fv2 {
    public static boolean x = true;
    public static boolean y = true;

    public void N(View view, Matrix matrix) {
        if (x) {
            try {
                view.transformMatrixToGlobal(matrix);
            } catch (NoSuchMethodError unused) {
                x = false;
            }
        }
    }

    public void O(View view, Matrix matrix) {
        if (y) {
            try {
                view.transformMatrixToLocal(matrix);
            } catch (NoSuchMethodError unused) {
                y = false;
            }
        }
    }
}
