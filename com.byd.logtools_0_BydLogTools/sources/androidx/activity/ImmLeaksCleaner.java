package androidx.activity;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.d;
import androidx.lifecycle.f;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
final class ImmLeaksCleaner implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static int f67a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Field f68b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static Field f69c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static Field f70d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Activity f71e;

    ImmLeaksCleaner(Activity activity) {
        this.f71e = activity;
    }

    private static void h() {
        try {
            f67a = 2;
            Field declaredField = InputMethodManager.class.getDeclaredField("mServedView");
            f69c = declaredField;
            declaredField.setAccessible(true);
            Field declaredField2 = InputMethodManager.class.getDeclaredField("mNextServedView");
            f70d = declaredField2;
            declaredField2.setAccessible(true);
            Field declaredField3 = InputMethodManager.class.getDeclaredField("mH");
            f68b = declaredField3;
            declaredField3.setAccessible(true);
            f67a = 1;
        } catch (NoSuchFieldException unused) {
        }
    }

    @Override // androidx.lifecycle.d
    public void d(f fVar, Lifecycle.Event event) {
        if (event != Lifecycle.Event.ON_DESTROY) {
            return;
        }
        if (f67a == 0) {
            h();
        }
        if (f67a == 1) {
            InputMethodManager inputMethodManager = (InputMethodManager) this.f71e.getSystemService("input_method");
            try {
                Object obj = f68b.get(inputMethodManager);
                if (obj == null) {
                    return;
                }
                synchronized (obj) {
                    try {
                        try {
                            View view = (View) f69c.get(inputMethodManager);
                            if (view == null) {
                                return;
                            }
                            if (view.isAttachedToWindow()) {
                                return;
                            }
                            try {
                                f70d.set(inputMethodManager, null);
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
