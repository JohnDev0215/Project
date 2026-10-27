package androidx.activity;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.e;
import androidx.lifecycle.g;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
final class ImmLeaksCleaner implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static int f68a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Field f69b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static Field f70c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static Field f71d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Activity f72e;

    ImmLeaksCleaner(Activity activity) {
        this.f72e = activity;
    }

    private static void h() {
        try {
            f68a = 2;
            Field declaredField = InputMethodManager.class.getDeclaredField("mServedView");
            f70c = declaredField;
            declaredField.setAccessible(true);
            Field declaredField2 = InputMethodManager.class.getDeclaredField("mNextServedView");
            f71d = declaredField2;
            declaredField2.setAccessible(true);
            Field declaredField3 = InputMethodManager.class.getDeclaredField("mH");
            f69b = declaredField3;
            declaredField3.setAccessible(true);
            f68a = 1;
        } catch (NoSuchFieldException unused) {
        }
    }

    @Override // androidx.lifecycle.e
    public void d(g gVar, Lifecycle.Event event) {
        if (event != Lifecycle.Event.ON_DESTROY) {
            return;
        }
        if (f68a == 0) {
            h();
        }
        if (f68a == 1) {
            InputMethodManager inputMethodManager = (InputMethodManager) this.f72e.getSystemService("input_method");
            try {
                Object obj = f69b.get(inputMethodManager);
                if (obj == null) {
                    return;
                }
                synchronized (obj) {
                    try {
                        try {
                            View view = (View) f70c.get(inputMethodManager);
                            if (view == null) {
                                return;
                            }
                            if (view.isAttachedToWindow()) {
                                return;
                            }
                            try {
                                f71d.set(inputMethodManager, null);
                                inputMethodManager.isActive();
                            } catch (IllegalAccessException unused) {
                            }
                        } catch (ClassCastException unused2) {
                        } catch (IllegalAccessException unused3) {
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            } catch (IllegalAccessException unused4) {
            }
        }
    }
}
