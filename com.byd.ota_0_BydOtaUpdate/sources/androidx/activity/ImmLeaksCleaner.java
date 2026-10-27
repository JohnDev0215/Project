package androidx.activity;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.lifecycle.Lifecycle;
import b.l.d;
import b.l.f;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
public final class ImmLeaksCleaner implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f66a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Field f67b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Field f68c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Field f69d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Activity f70e;

    public ImmLeaksCleaner(Activity activity) {
        this.f70e = activity;
    }

    public static void h() {
        try {
            f66a = 2;
            Field declaredField = InputMethodManager.class.getDeclaredField("mServedView");
            f68c = declaredField;
            declaredField.setAccessible(true);
            Field declaredField2 = InputMethodManager.class.getDeclaredField("mNextServedView");
            f69d = declaredField2;
            declaredField2.setAccessible(true);
            Field declaredField3 = InputMethodManager.class.getDeclaredField("mH");
            f67b = declaredField3;
            declaredField3.setAccessible(true);
            f66a = 1;
        } catch (NoSuchFieldException unused) {
        }
    }

    @Override // b.l.d
    public void d(f fVar, Lifecycle.Event event) {
        if (event != Lifecycle.Event.ON_DESTROY) {
            return;
        }
        if (f66a == 0) {
            h();
        }
        if (f66a == 1) {
            InputMethodManager inputMethodManager = (InputMethodManager) this.f70e.getSystemService("input_method");
            try {
                Object obj = f67b.get(inputMethodManager);
                if (obj == null) {
                    return;
                }
                synchronized (obj) {
                    try {
                        try {
                            View view = (View) f68c.get(inputMethodManager);
                            if (view == null) {
                                return;
                            }
                            if (view.isAttachedToWindow()) {
                                return;
                            }
                            try {
                                f69d.set(inputMethodManager, null);
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
