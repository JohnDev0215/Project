package defpackage;

import android.util.Log;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: renamed from: s7, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1474s7 extends AbstractC1639v7 {
    public static Method a;
    public static boolean b;
    public static Method c;
    public static boolean d;

    @Override // defpackage.AbstractC1639v7
    public void a(View view) {
    }

    @Override // defpackage.AbstractC1639v7
    public float b(View view) {
        if (!d) {
            try {
                Method declaredMethod = View.class.getDeclaredMethod("getTransitionAlpha", new Class[0]);
                c = declaredMethod;
                declaredMethod.setAccessible(true);
            } catch (NoSuchMethodException e) {
                Log.i("ViewUtilsApi19", "Failed to retrieve getTransitionAlpha method", e);
            }
            d = true;
        }
        Method method = c;
        if (method != null) {
            try {
                return ((Float) method.invoke(view, new Object[0])).floatValue();
            } catch (IllegalAccessException unused) {
            } catch (InvocationTargetException e2) {
                throw new RuntimeException(e2.getCause());
            }
        }
        return super.b(view);
    }

    @Override // defpackage.AbstractC1639v7
    public void c(View view) {
    }

    @Override // defpackage.AbstractC1639v7
    public void e(View view, float f) {
        if (!b) {
            try {
                Method declaredMethod = View.class.getDeclaredMethod("setTransitionAlpha", Float.TYPE);
                a = declaredMethod;
                declaredMethod.setAccessible(true);
            } catch (NoSuchMethodException e) {
                Log.i("ViewUtilsApi19", "Failed to retrieve setTransitionAlpha method", e);
            }
            b = true;
        }
        Method method = a;
        if (method == null) {
            view.setAlpha(f);
            return;
        }
        try {
            method.invoke(view, Float.valueOf(f));
        } catch (IllegalAccessException unused) {
        } catch (InvocationTargetException e2) {
            throw new RuntimeException(e2.getCause());
        }
    }
}
