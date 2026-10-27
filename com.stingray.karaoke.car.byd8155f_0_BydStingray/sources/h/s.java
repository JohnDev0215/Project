package h;

import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final s f15137a = new s();

    public final OnBackInvokedCallback a(InterfaceC2772a interfaceC2772a) {
        A7.m.f(interfaceC2772a, "onBackInvoked");
        return new e1.n(interfaceC2772a, 1);
    }

    public final void b(Object obj, int i7, Object obj2) {
        A7.m.f(obj, "dispatcher");
        A7.m.f(obj2, "callback");
        ((OnBackInvokedDispatcher) obj).registerOnBackInvokedCallback(i7, (OnBackInvokedCallback) obj2);
    }

    public final void c(Object obj, Object obj2) {
        A7.m.f(obj, "dispatcher");
        A7.m.f(obj2, "callback");
        ((OnBackInvokedDispatcher) obj).unregisterOnBackInvokedCallback((OnBackInvokedCallback) obj2);
    }
}
