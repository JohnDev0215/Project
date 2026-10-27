package androidx.appcompat.widget;

import android.graphics.Rect;
import android.view.View;
import defpackage.in3;
import defpackage.om3;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class ViewUtils {
    public static final Method a;

    static {
        try {
            Method declaredMethod = View.class.getDeclaredMethod("computeFitSystemWindows", Rect.class, Rect.class);
            a = declaredMethod;
            if (declaredMethod.isAccessible()) {
                return;
            }
            declaredMethod.setAccessible(true);
        } catch (NoSuchMethodException unused) {
        }
    }

    private ViewUtils() {
    }

    public static boolean a(View view) {
        WeakHashMap<View, in3> weakHashMap = om3.a;
        return om3.e.c(view) == 1;
    }
}
