package androidx.activity;

import android.view.inputmethod.InputMethodManager;
import androidx.lifecycle.Lifecycle$Event;
import p.p91;
import p.s91;

/* JADX INFO: loaded from: classes.dex */
final class ImmLeaksCleaner implements p91 {
    public static int c;

    @Override // p.p91
    public final void d(s91 s91Var, Lifecycle$Event lifecycle$Event) {
        if (lifecycle$Event != Lifecycle$Event.ON_DESTROY) {
            return;
        }
        if (c == 0) {
            try {
                c = 2;
                InputMethodManager.class.getDeclaredField("mServedView").setAccessible(true);
                InputMethodManager.class.getDeclaredField("mNextServedView").setAccessible(true);
                InputMethodManager.class.getDeclaredField("mH").setAccessible(true);
                c = 1;
            } catch (NoSuchFieldException unused) {
            }
        }
        if (c == 1) {
            throw null;
        }
    }
}
