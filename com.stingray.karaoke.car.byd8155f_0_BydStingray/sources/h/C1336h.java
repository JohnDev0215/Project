package h;

import android.app.Activity;
import android.window.OnBackInvokedDispatcher;

/* JADX INFO: renamed from: h.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1336h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C1336h f15098a = new C1336h();

    public final OnBackInvokedDispatcher a(Activity activity) {
        A7.m.f(activity, "activity");
        OnBackInvokedDispatcher onBackInvokedDispatcher = activity.getOnBackInvokedDispatcher();
        A7.m.e(onBackInvokedDispatcher, "activity.getOnBackInvokedDispatcher()");
        return onBackInvokedDispatcher;
    }
}
