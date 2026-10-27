package androidx.activity;

import android.view.inputmethod.InputMethodManager;
import androidx.lifecycle.d;
import androidx.lifecycle.f;
import defpackage.b71;

/* JADX INFO: loaded from: classes.dex */
final class ImmLeaksCleaner implements f {
    public static int e;

    public ImmLeaksCleaner() {
        throw null;
    }

    @Override // androidx.lifecycle.f
    public final void a(b71 b71Var, d.a aVar) {
        if (aVar != d.a.ON_DESTROY) {
            return;
        }
        if (e == 0) {
            try {
                e = 2;
                InputMethodManager.class.getDeclaredField("mServedView").setAccessible(true);
                InputMethodManager.class.getDeclaredField("mNextServedView").setAccessible(true);
                InputMethodManager.class.getDeclaredField("mH").setAccessible(true);
                e = 1;
            } catch (NoSuchFieldException unused) {
            }
        }
        if (e == 1) {
            throw null;
        }
    }
}
