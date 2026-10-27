package y0;

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
    public static Method f5909a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f5910b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Field f5911c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static boolean f5912d;

    public void a(View view) {
        if (view.getVisibility() == 0) {
            view.setTag(R$id.save_non_transition_alpha, null);
        }
    }

    @SuppressLint({"PrivateApi"})
    public final void b() {
        if (f5910b) {
            return;
        }
        try {
            Class cls = Integer.TYPE;
            Method declaredMethod = View.class.getDeclaredMethod("setFrame", cls, cls, cls, cls);
            f5909a = declaredMethod;
            declaredMethod.setAccessible(true);
        } catch (NoSuchMethodException e3) {
            Log.i("ViewUtilsBase", "Failed to retrieve setFrame method", e3);
        }
        f5910b = true;
    }

    public float c(View view) {
        Float f3 = (Float) view.getTag(R$id.save_non_transition_alpha);
        return f3 != null ? view.getAlpha() / f3.floatValue() : view.getAlpha();
    }

    public void d(View view) {
        int i3 = R$id.save_non_transition_alpha;
        if (view.getTag(i3) == null) {
            view.setTag(i3, Float.valueOf(view.getAlpha()));
        }
    }

    public void e(View view, int i3, int i4, int i5, int i6) {
        b();
        Method method = f5909a;
        if (method != null) {
            try {
                method.invoke(view, Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i6));
            } catch (IllegalAccessException unused) {
            } catch (InvocationTargetException e3) {
                throw new RuntimeException(e3.getCause());
            }
        }
    }

    public void f(View view, float f3) {
        Float f4 = (Float) view.getTag(R$id.save_non_transition_alpha);
        if (f4 != null) {
            view.setAlpha(f4.floatValue() * f3);
        } else {
            view.setAlpha(f3);
        }
    }

    public void g(View view, int i3) {
        if (!f5912d) {
            try {
                Field declaredField = View.class.getDeclaredField("mViewFlags");
                f5911c = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException unused) {
                Log.i("ViewUtilsBase", "fetchViewFlagsField: ");
            }
            f5912d = true;
        }
        Field field = f5911c;
        if (field != null) {
            try {
                f5911c.setInt(view, i3 | (field.getInt(view) & (-13)));
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
