package p;

import android.view.View;
import android.view.WindowInsets;

/* JADX INFO: loaded from: classes.dex */
public abstract class pt2 {
    public static fx2 a(View view) {
        WindowInsets rootWindowInsets = view.getRootWindowInsets();
        if (rootWindowInsets == null) {
            return null;
        }
        fx2 fx2VarG = fx2.g(rootWindowInsets, null);
        dx2 dx2Var = fx2VarG.a;
        dx2Var.p(fx2VarG);
        dx2Var.d(view.getRootView());
        return fx2VarG;
    }

    public static int b(View view) {
        return view.getScrollIndicators();
    }

    public static void c(View view, int i) {
        view.setScrollIndicators(i);
    }

    public static void d(View view, int i, int i2) {
        view.setScrollIndicators(i, i2);
    }
}
