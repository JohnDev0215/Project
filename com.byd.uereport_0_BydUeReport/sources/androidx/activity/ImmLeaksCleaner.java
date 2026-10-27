package androidx.activity;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.lifecycle.Lifecycle;
import java.lang.reflect.Field;
import x0.d;
import x0.f;

/* JADX INFO: loaded from: classes.dex */
public final class ImmLeaksCleaner implements d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static int f228d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static Field f229e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static Field f230f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static Field f231g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Activity f232h;

    public ImmLeaksCleaner(Activity activity) {
        this.f232h = activity;
    }

    public static void d() {
        try {
            f228d = 2;
            Field declaredField = InputMethodManager.class.getDeclaredField("mServedView");
            f230f = declaredField;
            declaredField.setAccessible(true);
            Field declaredField2 = InputMethodManager.class.getDeclaredField("mNextServedView");
            f231g = declaredField2;
            declaredField2.setAccessible(true);
            Field declaredField3 = InputMethodManager.class.getDeclaredField("mH");
            f229e = declaredField3;
            declaredField3.setAccessible(true);
            f228d = 1;
        } catch (NoSuchFieldException unused) {
        }
    }

    @Override // x0.d
    public void onStateChanged(f fVar, Lifecycle.Event event) {
        if (event != Lifecycle.Event.ON_DESTROY) {
            return;
        }
        if (f228d == 0) {
            d();
        }
        if (f228d == 1) {
            InputMethodManager inputMethodManager = (InputMethodManager) this.f232h.getSystemService("input_method");
            try {
                Object obj = f229e.get(inputMethodManager);
                if (obj == null) {
                    return;
                }
                synchronized (obj) {
                    try {
                        try {
                            View view = (View) f230f.get(inputMethodManager);
                            if (view == null) {
                                return;
                            }
                            if (view.isAttachedToWindow()) {
                                return;
                            }
                            try {
                                f231g.set(inputMethodManager, null);
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
