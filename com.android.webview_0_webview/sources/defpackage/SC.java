package defpackage;

import android.animation.Animator;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.ActionMode;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class SC {
    public static Object a(Object obj, String str) throws NoSuchFieldException {
        Field declaredField = obj.getClass().getDeclaredField(str);
        declaredField.setAccessible(true);
        return declaredField.get(obj);
    }

    public static void b(Context context, ActionMode actionMode) {
        boolean z;
        int i;
        PackageInfo packageInfo;
        String packageName = context.getPackageName();
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException unused) {
        }
        int i2 = packageInfo != null ? packageInfo.versionCode : -1;
        if (i2 != -1 && (i = context.getApplicationInfo().targetSdkVersion) >= 23 && i <= 24 && "com.lge.email".equals(packageName) && i2 <= 67502100) {
            Ts.f("Ime", Z7.a("Working around action mode LG Email bug in WebView (http://crbug.com/651706). APK name: com.lge.email, versionCode: ", i2), new Object[0]);
            z = true;
        } else {
            z = false;
        }
        if (z) {
            try {
                c(actionMode, "mCallback", new PC((ActionMode.Callback2) a(actionMode, "mCallback")));
                Object objA = a(actionMode, "mFloatingToolbar");
                Object objA2 = a(objA, "mPopup");
                ViewGroup viewGroup = (ViewGroup) a(objA2, "mContentContainer");
                PopupWindow popupWindow = (PopupWindow) a(objA2, "mPopupWindow");
                Method declaredMethod = objA.getClass().getDeclaredMethod("createExitAnimation", View.class, Integer.TYPE, Animator.AnimatorListener.class);
                declaredMethod.setAccessible(true);
                c(objA2, "mDismissAnimation", declaredMethod.invoke(null, viewGroup, 150, new RC(popupWindow, viewGroup)));
            } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException unused2) {
            } catch (Exception e) {
                Ts.f("Ime", "Error occurred during LGEmailActionModeWorkaround: ", e);
            }
        }
    }

    public static void c(Object obj, String str, Object obj2) throws IllegalAccessException, NoSuchFieldException {
        Field declaredField = obj.getClass().getDeclaredField(str);
        declaredField.setAccessible(true);
        declaredField.set(obj, obj2);
    }
}
