package r0;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.PopupWindow;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import o0.t;

/* JADX INFO: compiled from: PopupWindowCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Method f7727a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f7728b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Field f7729c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static boolean f7730d;

    public static void a(PopupWindow popupWindow, boolean z7) {
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 23) {
            popupWindow.setOverlapAnchor(z7);
            return;
        }
        if (i8 >= 21) {
            if (!f7730d) {
                try {
                    Field declaredField = PopupWindow.class.getDeclaredField("mOverlapAnchor");
                    f7729c = declaredField;
                    declaredField.setAccessible(true);
                } catch (NoSuchFieldException e8) {
                    Log.i("PopupWindowCompatApi21", "Could not fetch mOverlapAnchor field from PopupWindow", e8);
                }
                f7730d = true;
            }
            Field field = f7729c;
            if (field != null) {
                try {
                    field.set(popupWindow, Boolean.valueOf(z7));
                } catch (IllegalAccessException e9) {
                    Log.i("PopupWindowCompatApi21", "Could not set overlap anchor field in PopupWindow", e9);
                }
            }
        }
    }

    public static void b(PopupWindow popupWindow, int i8) {
        if (Build.VERSION.SDK_INT >= 23) {
            popupWindow.setWindowLayoutType(i8);
            return;
        }
        if (!f7728b) {
            try {
                Method declaredMethod = PopupWindow.class.getDeclaredMethod("setWindowLayoutType", Integer.TYPE);
                f7727a = declaredMethod;
                declaredMethod.setAccessible(true);
            } catch (Exception unused) {
            }
            f7728b = true;
        }
        Method method = f7727a;
        if (method != null) {
            try {
                method.invoke(popupWindow, Integer.valueOf(i8));
            } catch (Exception unused2) {
            }
        }
    }

    public static void c(PopupWindow popupWindow, View view, int i8, int i9, int i10) {
        if (Build.VERSION.SDK_INT >= 19) {
            popupWindow.showAsDropDown(view, i8, i9, i10);
            return;
        }
        if ((o0.d.b(i10, t.A(view)) & 7) == 5) {
            i8 -= popupWindow.getWidth() - view.getWidth();
        }
        popupWindow.showAsDropDown(view, i8, i9);
    }
}
