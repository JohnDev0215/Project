package defpackage;

import android.animation.Animator;
import android.content.Context;
import android.util.Log;
import android.view.ActionMode;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: LGEmailActionModeWorkaroundImpl.java */
/* JADX INFO: loaded from: classes3.dex */
public final class x41 {
    public static Object a(Object obj, String str) throws NoSuchFieldException {
        Field declaredField = obj.getClass().getDeclaredField(str);
        declaredField.setAccessible(true);
        return declaredField.get(obj);
    }

    public static void b(Context context, ActionMode actionMode) {
        boolean z;
        int i;
        String packageName = context.getPackageName();
        int iD = zt1.d(packageName);
        if (iD != -1 && (i = context.getApplicationInfo().targetSdkVersion) >= 23 && i <= 24 && "com.lge.email".equals(packageName) && iD <= 67502100) {
            C0175b.s("Working around action mode LG Email bug in WebView (http://crbug.com/651706). APK name: com.lge.email, versionCode: ", iD, "cr_Ime");
            z = true;
        } else {
            z = false;
        }
        if (z) {
            try {
                v41 v41Var = new v41((ActionMode.Callback2) a(actionMode, "mCallback"));
                Field declaredField = actionMode.getClass().getDeclaredField("mCallback");
                declaredField.setAccessible(true);
                declaredField.set(actionMode, v41Var);
                Object objA = a(actionMode, "mFloatingToolbar");
                Object objA2 = a(objA, "mPopup");
                ViewGroup viewGroup = (ViewGroup) a(objA2, "mContentContainer");
                PopupWindow popupWindow = (PopupWindow) a(objA2, "mPopupWindow");
                Method declaredMethod = objA.getClass().getDeclaredMethod("createExitAnimation", View.class, Integer.TYPE, Animator.AnimatorListener.class);
                declaredMethod.setAccessible(true);
                Object objInvoke = declaredMethod.invoke(null, viewGroup, 150, new w41(popupWindow, viewGroup));
                Field declaredField2 = objA2.getClass().getDeclaredField("mDismissAnimation");
                declaredField2.setAccessible(true);
                declaredField2.set(objA2, objInvoke);
            } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException unused) {
            } catch (Exception e) {
                Log.w("cr_Ime", "Error occurred during LGEmailActionModeWorkaround: ", e);
            }
        }
    }
}
