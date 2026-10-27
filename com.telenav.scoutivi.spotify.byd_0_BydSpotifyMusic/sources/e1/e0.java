package e1;

import android.graphics.Matrix;
import android.util.Log;
import android.view.View;
import java.lang.reflect.Field;

/* JADX INFO: compiled from: ViewUtilsBase.java */
/* JADX INFO: loaded from: classes.dex */
class e0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Field f6096a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static boolean f6097b;

    e0() {
    }

    public void a(View view) {
        throw null;
    }

    public float b(View view) {
        throw null;
    }

    public void c(View view) {
        throw null;
    }

    public void d(View view, int i7, int i8, int i9, int i10) {
        throw null;
    }

    public void e(View view, float f7) {
        throw null;
    }

    public void f(View view, int i7) {
        if (!f6097b) {
            try {
                Field declaredField = View.class.getDeclaredField("mViewFlags");
                f6096a = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException unused) {
                Log.i("ViewUtilsBase", "fetchViewFlagsField: ");
            }
            f6097b = true;
        }
        Field field = f6096a;
        if (field != null) {
            try {
                f6096a.setInt(view, i7 | (field.getInt(view) & (-13)));
            } catch (IllegalAccessException unused2) {
            }
        }
    }

    public void g(View view, Matrix matrix) {
        throw null;
    }

    public void h(View view, Matrix matrix) {
        throw null;
    }
}
