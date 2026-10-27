package F1;

import android.view.View;
import android.view.WindowInsets;

/* JADX INFO: loaded from: classes.dex */
public abstract class B {
    public static V a(View view) {
        WindowInsets rootWindowInsets = view.getRootWindowInsets();
        if (rootWindowInsets == null) {
            return null;
        }
        V vC = V.c(rootWindowInsets, null);
        U u4 = vC.f2321a;
        u4.m(vC);
        u4.d(view.getRootView());
        return vC;
    }
}
