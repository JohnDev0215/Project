package n0;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.PopupWindow;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import l0.t;

/* JADX INFO: compiled from: PopupWindowCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Method f4074a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f4075b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Field f4076c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static boolean f4077d;

    public static void a(PopupWindow popupWindow, boolean z3) {
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 23) {
            popupWindow.setOverlapAnchor(z3);
            return;
        }
        if (i3 >= 21) {
            if (!f4077d) {
                try {
                    Field declaredField = PopupWindow.class.getDeclaredField("mOverlapAnchor");
                    f4076c = declaredField;
                    declaredField.setAccessible(true);
                } catch (NoSuchFieldException e3) {
                    Log.i("PopupWindowCompatApi21", "Could not fetch mOverlapAnchor field from PopupWindow", e3);
                }
                f4077d = true;
            }
            Field field = f4076c;
            if (field != null) {
                try {
                    field.set(popupWindow, Boolean.valueOf(z3));
                } catch (IllegalAccessException e4) {
                    Log.i("PopupWindowCompatApi21", "Could not set overlap anchor field in PopupWindow", e4);
                }
            }
        }
    }

    public static void b(PopupWindow popupWindow, int i3) {
        if (Build.VERSION.SDK_INT >= 23) {
            popupWindow.setWindowLayoutType(i3);
            return;
        }
        if (!f4075b) {
            try {
                Method declaredMethod = PopupWindow.class.getDeclaredMethod("setWindowLayoutType", Integer.TYPE);
                f4074a = declaredMethod;
                declaredMethod.setAccessible(true);
            } catch (Exception unused) {
            }
            f4075b = true;
        }
        Method method = f4074a;
        if (method != null) {
            try {
                method.invoke(popupWindow, Integer.valueOf(i3));
            } catch (Exception unused2) {
            }
        }
    }

    public static void c(PopupWindow popupWindow, View view, int i3, int i4, int i5) {
        if (Build.VERSION.SDK_INT >= 19) {
            popupWindow.showAsDropDown(view, i3, i4, i5);
            return;
        }
        if ((l0.d.b(i5, t.z(view)) & 7) == 5) {
            i3 -= popupWindow.getWidth() - view.getWidth();
        }
        popupWindow.showAsDropDown(view, i3, i4);
    }
}
