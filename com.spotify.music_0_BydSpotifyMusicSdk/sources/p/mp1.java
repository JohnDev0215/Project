package p;

import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;

/* JADX INFO: loaded from: classes.dex */
public final class mp1 {
    public static final mp1 a = new mp1();

    public final OnBackInvokedCallback a(py0 py0Var) {
        v03.j(py0Var, "onBackInvoked");
        return new lp1(0, py0Var);
    }

    public final void b(Object obj, int i, Object obj2) {
        v03.j(obj, "dispatcher");
        v03.j(obj2, "callback");
        ((OnBackInvokedDispatcher) obj).registerOnBackInvokedCallback(i, (OnBackInvokedCallback) obj2);
    }

    public final void c(Object obj, Object obj2) {
        v03.j(obj, "dispatcher");
        v03.j(obj2, "callback");
        ((OnBackInvokedDispatcher) obj).unregisterOnBackInvokedCallback((OnBackInvokedCallback) obj2);
    }
}
