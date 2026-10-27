package defpackage;

import android.graphics.Rect;
import android.view.View;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class X0 {
    public static Method a;

    static {
        try {
            Method declaredMethod = View.class.getDeclaredMethod("computeFitSystemWindows", Rect.class, Rect.class);
            a = declaredMethod;
            if (declaredMethod.isAccessible()) {
                return;
            }
            a.setAccessible(true);
        } catch (NoSuchMethodException unused) {
        }
    }

    public static boolean a(View view) {
        return AbstractC1251o3.k(view) == 1;
    }
}
