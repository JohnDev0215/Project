package p;

import android.app.Activity;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public abstract class l8 {
    public static OnBackInvokedDispatcher a(Activity activity) {
        return activity.getOnBackInvokedDispatcher();
    }

    public static OnBackInvokedCallback b(Object obj, s8 s8Var) {
        Objects.requireNonNull(s8Var);
        lp1 lp1Var = new lp1(1, s8Var);
        u2.d(obj).registerOnBackInvokedCallback(1000000, lp1Var);
        return lp1Var;
    }

    public static void c(Object obj, Object obj2) {
        u2.d(obj).unregisterOnBackInvokedCallback(u2.b(obj2));
    }
}
