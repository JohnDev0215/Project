package defpackage;

import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.util.Property;
import android.view.View;
import java.lang.reflect.Field;

/* JADX INFO: renamed from: r7, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1419r7 {
    public static final AbstractC1639v7 a;
    public static Field b;
    public static boolean c;
    public static final Property d;

    static {
        if (Build.VERSION.SDK_INT >= 22) {
            a = new C1584u7();
        } else {
            a = new C1529t7();
        }
        d = new C1310p7(Float.class, "translationAlpha");
        new C1365q7(Rect.class, "clipBounds");
    }

    public static float a(View view) {
        return a.b(view);
    }

    public static B7 b(View view) {
        return new A7(view);
    }

    public static void c(View view, int i, int i2, int i3, int i4) {
        a.d(view, i, i2, i3, i4);
    }

    public static void d(View view, int i) {
        if (!c) {
            try {
                Field declaredField = View.class.getDeclaredField("mViewFlags");
                b = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException unused) {
                Log.i("ViewUtils", "fetchViewFlagsField: ");
            }
            c = true;
        }
        Field field = b;
        if (field != null) {
            try {
                b.setInt(view, i | (field.getInt(view) & (-13)));
            } catch (IllegalAccessException unused2) {
            }
        }
    }
}
