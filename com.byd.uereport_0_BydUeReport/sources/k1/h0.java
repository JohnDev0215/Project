package k1;

import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.util.Log;
import android.view.View;
import androidx.transition.R$id;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: ViewUtilsBase.java */
/* JADX INFO: loaded from: classes.dex */
public class h0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Method f5753a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f5754b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Field f5755c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static boolean f5756d;

    public void a(View view) {
        if (view.getVisibility() == 0) {
            view.setTag(R$id.save_non_transition_alpha, null);
        }
    }

    @SuppressLint({"PrivateApi"})
    public final void b() {
        if (f5754b) {
            return;
        }
        try {
            Class cls = Integer.TYPE;
            Method declaredMethod = View.class.getDeclaredMethod("setFrame", cls, cls, cls, cls);
            f5753a = declaredMethod;
            declaredMethod.setAccessible(true);
        } catch (NoSuchMethodException e8) {
            Log.i("ViewUtilsBase", "Failed to retrieve setFrame method", e8);
        }
        f5754b = true;
    }

    public float c(View view) {
        Float f8 = (Float) view.getTag(R$id.save_non_transition_alpha);
        return f8 != null ? view.getAlpha() / f8.floatValue() : view.getAlpha();
    }

    public void d(View view) {
        int i8 = R$id.save_non_transition_alpha;
        if (view.getTag(i8) == null) {
            view.setTag(i8, Float.valueOf(view.getAlpha()));
        }
    }

    public void e(View view, int i8, int i9, int i10, int i11) {
        b();
        Method method = f5753a;
        if (method != null) {
            try {
                method.invoke(view, Integer.valueOf(i8), Integer.valueOf(i9), Integer.valueOf(i10), Integer.valueOf(i11));
            } catch (IllegalAccessException unused) {
            } catch (InvocationTargetException e8) {
                throw new RuntimeException(e8.getCause());
            }
        }
    }

    public void f(View view, float f8) {
        Float f9 = (Float) view.getTag(R$id.save_non_transition_alpha);
        if (f9 != null) {
            view.setAlpha(f9.floatValue() * f8);
        } else {
            view.setAlpha(f8);
        }
    }

    public void g(View view, int i8) {
        if (!f5756d) {
            try {
                Field declaredField = View.class.getDeclaredField("mViewFlags");
                f5755c = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException unused) {
                Log.i("ViewUtilsBase", "fetchViewFlagsField: ");
            }
            f5756d = true;
        }
        Field field = f5755c;
        if (field != null) {
            try {
                f5755c.setInt(view, i8 | (field.getInt(view) & (-13)));
            } catch (IllegalAccessException unused2) {
            }
        }
    }

    public void h(View view, Matrix matrix) {
        Object parent = view.getParent();
        if (parent instanceof View) {
            h((View) parent, matrix);
            matrix.preTranslate(-r0.getScrollX(), -r0.getScrollY());
        }
        matrix.preTranslate(view.getLeft(), view.getTop());
        Matrix matrix2 = view.getMatrix();
        if (matrix2.isIdentity()) {
            return;
        }
        matrix.preConcat(matrix2);
    }

    public void i(View view, Matrix matrix) {
        Object parent = view.getParent();
        if (parent instanceof View) {
            i((View) parent, matrix);
            matrix.postTranslate(r0.getScrollX(), r0.getScrollY());
        }
        matrix.postTranslate(-view.getLeft(), -view.getTop());
        Matrix matrix2 = view.getMatrix();
        if (matrix2.isIdentity()) {
            return;
        }
        Matrix matrix3 = new Matrix();
        if (matrix2.invert(matrix3)) {
            matrix.postConcat(matrix3);
        }
    }
}
