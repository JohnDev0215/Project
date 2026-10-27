package androidx.navigation;

import android.view.View;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: Navigation.java */
/* JADX INFO: loaded from: classes.dex */
public final class q {
    public static NavController a(View view) {
        NavController navControllerB = b(view);
        if (navControllerB != null) {
            return navControllerB;
        }
        throw new IllegalStateException("View " + view + " does not have a NavController set");
    }

    private static NavController b(View view) {
        while (view != null) {
            NavController navControllerC = c(view);
            if (navControllerC != null) {
                return navControllerC;
            }
            Object parent = view.getParent();
            view = parent instanceof View ? (View) parent : null;
        }
        return null;
    }

    private static NavController c(View view) {
        Object tag = view.getTag(t.nav_controller_view_tag);
        if (tag instanceof WeakReference) {
            return (NavController) ((WeakReference) tag).get();
        }
        if (tag instanceof NavController) {
            return (NavController) tag;
        }
        return null;
    }

    public static void d(View view, NavController navController) {
        view.setTag(t.nav_controller_view_tag, navController);
    }
}
