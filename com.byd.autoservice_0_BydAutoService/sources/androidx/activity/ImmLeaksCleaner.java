package androidx.activity;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.lifecycle.Lifecycle;
import java.lang.reflect.Field;
import t0.d;
import t0.f;

/* JADX INFO: loaded from: classes.dex */
public final class ImmLeaksCleaner implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f121a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Field f122b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Field f123c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Field f124d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Activity f125e;

    public ImmLeaksCleaner(Activity activity) {
        this.f125e = activity;
    }

    public static void h() {
        try {
            f121a = 2;
            Field declaredField = InputMethodManager.class.getDeclaredField("mServedView");
            f123c = declaredField;
            declaredField.setAccessible(true);
            Field declaredField2 = InputMethodManager.class.getDeclaredField("mNextServedView");
            f124d = declaredField2;
            declaredField2.setAccessible(true);
            Field declaredField3 = InputMethodManager.class.getDeclaredField("mH");
            f122b = declaredField3;
            declaredField3.setAccessible(true);
            f121a = 1;
        } catch (NoSuchFieldException unused) {
        }
    }

    @Override // t0.d
    public void d(f fVar, Lifecycle.Event event) {
        if (event != Lifecycle.Event.ON_DESTROY) {
            return;
        }
        if (f121a == 0) {
            h();
        }
        if (f121a == 1) {
            InputMethodManager inputMethodManager = (InputMethodManager) this.f125e.getSystemService("input_method");
            try {
                Object obj = f122b.get(inputMethodManager);
                if (obj == null) {
                    return;
                }
                synchronized (obj) {
                    try {
                        try {
                            View view = (View) f123c.get(inputMethodManager);
                            if (view == null) {
                                return;
                            }
                            if (view.isAttachedToWindow()) {
                                return;
                            }
                            try {
                                f124d.set(inputMethodManager, null);
                                inputMethodManager.isActive();
                            } catch (IllegalAccessException unused) {
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    } catch (ClassCastException unused2) {
                    } catch (IllegalAccessException unused3) {
                    }
                }
            } catch (IllegalAccessException unused4) {
            }
        }
    }
}
